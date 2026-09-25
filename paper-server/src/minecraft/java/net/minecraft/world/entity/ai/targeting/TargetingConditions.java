package net.minecraft.world.entity.ai.targeting;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import org.jspecify.annotations.Nullable;

public class TargetingConditions {
    public static final TargetingConditions DEFAULT = forCombat();
    private static final double MIN_VISIBILITY_DISTANCE_FOR_INVISIBLE_TARGET = 2.0;
    private final boolean isCombat;
    private double range = -1.0;
    private boolean checkLineOfSight = true;
    private boolean testInvisible = true;
    /** 使用 Sensing#canPerceive 替代纯视线检测(体素寻路实体的增强感知) */
    private boolean voxelPerception;
    private TargetingConditions.@Nullable Selector selector;

    private TargetingConditions(boolean isCombat) {
        this.isCombat = isCombat;
    }

    public static TargetingConditions forCombat() {
        return new TargetingConditions(true);
    }

    public static TargetingConditions forNonCombat() {
        return new TargetingConditions(false);
    }

    public TargetingConditions copy() {
        TargetingConditions targetingConditions = this.isCombat ? forCombat() : forNonCombat();
        targetingConditions.range = this.range;
        targetingConditions.checkLineOfSight = this.checkLineOfSight;
        targetingConditions.testInvisible = this.testInvisible;
        targetingConditions.voxelPerception = this.voxelPerception;
        targetingConditions.selector = this.selector;
        return targetingConditions;
    }

    public TargetingConditions range(double range) {
        this.range = range;
        return this;
    }

    public TargetingConditions ignoreLineOfSight() {
        this.checkLineOfSight = false;
        return this;
    }

    /**
     * 视线检测改为使用 {@link net.minecraft.world.entity.ai.sensing.Sensing#canPerceive(net.minecraft.world.entity.Entity)}:
     * 对体素寻路实体而言, 玻璃/玻璃板/树叶后的目标以及近距离内可经由路径到达的目标同样视为可见.
     *
     * @return this
     */
    public TargetingConditions useVoxelPerception() {
        this.voxelPerception = true;
        return this;
    }

    public TargetingConditions ignoreInvisibilityTesting() {
        this.testInvisible = false;
        return this;
    }

    public TargetingConditions selector(TargetingConditions.@Nullable Selector selector) {
        this.selector = selector;
        return this;
    }

    public boolean test(ServerLevel level, @Nullable LivingEntity entity, LivingEntity target) {
        if (entity == target) {
            return false;
        } else if (!target.canBeSeenByAnyone()) {
            return false;
        } else if (this.selector != null && !this.selector.test(target, level)) {
            return false;
        } else {
            if (entity == null) {
                if (this.isCombat && (!target.canBeSeenAsEnemy() || level.getDifficulty() == Difficulty.PEACEFUL)) {
                    return false;
                }
            } else {
                if (this.isCombat && (!entity.canAttack(target) || !entity.canAttackType(target.getType()) || entity.isAlliedTo(target))) {
                    return false;
                }

                if (this.range > 0.0) {
                    double d = this.testInvisible ? target.getVisibilityPercent(entity) : 1.0;
                    double max = Math.max(this.range * d, 2.0);
                    double d1 = entity.distanceToSqr(target.getX(), target.getY(), target.getZ());
                    if (d1 > max * max) {
                        return false;
                    }
                }

                if (this.checkLineOfSight && entity instanceof Mob mob
                    && !(this.voxelPerception ? mob.getSensing().canPerceive(target) : mob.getSensing().hasLineOfSight(target))) {
                    return false;
                }
            }

            return true;
        }
    }

    @FunctionalInterface
    public interface Selector {
        boolean test(LivingEntity entity, ServerLevel level);
    }
}
