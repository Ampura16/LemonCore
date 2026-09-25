package net.minecraft.world.entity.ai.goal;

import java.util.EnumSet;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.level.pathfinder.VoxelNodeEvaluator;

public class MeleeAttackGoal extends Goal {
    protected final PathfinderMob mob;
    private final double speedModifier;
    private final boolean followingTargetEvenIfNotSeen;
    private Path path;
    private double pathedTargetX;
    private double pathedTargetY;
    private double pathedTargetZ;
    private int ticksUntilNextPathRecalculation;
    private int ticksUntilNextAttack;
    private final int attackInterval = 20;
    private long lastCanUseCheck;
    private static final long COOLDOWN_BETWEEN_CAN_USE_CHECKS = 20L;
    /** 体素寻路实体的 canUse 检查冷却: 获得目标后尽快开始追击 */
    private static final long VOXEL_COOLDOWN_BETWEEN_CAN_USE_CHECKS = 5L;

    public MeleeAttackGoal(PathfinderMob mob, double speedModifier, boolean followingTargetEvenIfNotSeen) {
        this.mob = mob;
        this.speedModifier = speedModifier;
        this.followingTargetEvenIfNotSeen = followingTargetEvenIfNotSeen;
        this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    @Override
    public boolean canUse() {
        long gameTime = this.mob.level().getGameTime();
        long cooldown = VoxelNodeEvaluator.isUsedBy(this.mob) ? VOXEL_COOLDOWN_BETWEEN_CAN_USE_CHECKS : COOLDOWN_BETWEEN_CAN_USE_CHECKS;
        if (gameTime - this.lastCanUseCheck < cooldown) {
            return false;
        } else {
            this.lastCanUseCheck = gameTime;
            LivingEntity target = this.mob.getTarget();
            if (target == null) {
                return false;
            } else if (!target.isAlive()) {
                return false;
            } else {
                this.path = this.mob.getNavigation().createPath(target, 0);
                return this.path != null || this.mob.isWithinMeleeAttackRange(target);
            }
        }
    }

    @Override
    public boolean canContinueToUse() {
        LivingEntity target = this.mob.getTarget();
        if (target == null) {
            return false;
        } else if (!target.isAlive()) {
            return false;
        } else {
            // 体素寻路实体在能感知目标时持续追击, 而不是走完当前(可能是残缺的)路径就放弃
            boolean voxelPerceived = VoxelNodeEvaluator.isUsedBy(this.mob) && this.mob.getSensing().canPerceive(target);
            return !this.followingTargetEvenIfNotSeen && !voxelPerceived
                ? !this.mob.getNavigation().isDone()
                : this.mob.isWithinHome(target.blockPosition()) && !(target instanceof Player player && (player.isSpectator() || player.isCreative()));
        }
    }

    @Override
    public void start() {
        this.mob.getNavigation().moveTo(this.path, this.speedModifier);
        this.mob.setAggressive(true);
        this.ticksUntilNextPathRecalculation = 0;
        this.ticksUntilNextAttack = 0;
    }

    @Override
    public void stop() {
        LivingEntity target = this.mob.getTarget();
        if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(target)) {
            this.mob.setTarget(null);
        }

        this.mob.setAggressive(false);
        this.mob.getNavigation().stop();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true;
    }

    @Override
    public void tick() {
        LivingEntity target = this.mob.getTarget();
        if (target != null) {
            this.mob.getLookControl().setLookAt(target, 30.0F, 30.0F);
            this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
            if ((this.followingTargetEvenIfNotSeen || this.canTrackTarget(target))
                && this.ticksUntilNextPathRecalculation <= 0
                && (
                    this.pathedTargetX == 0.0 && this.pathedTargetY == 0.0 && this.pathedTargetZ == 0.0
                        || target.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0
                        || this.mob.getRandom().nextFloat() < 0.05F
                )) {
                this.pathedTargetX = target.getX();
                this.pathedTargetY = target.getY();
                this.pathedTargetZ = target.getZ();
                this.ticksUntilNextPathRecalculation = 4 + this.mob.getRandom().nextInt(7);
                double d = this.mob.distanceToSqr(target);
                if (d > 1024.0) {
                    this.ticksUntilNextPathRecalculation += 10;
                } else if (d > 256.0) {
                    this.ticksUntilNextPathRecalculation += 5;
                }

                if (!this.mob.getNavigation().moveTo(target, this.speedModifier)) {
                    this.ticksUntilNextPathRecalculation += 15;
                }

                this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
            }

            this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
            this.checkAndPerformAttack(target);
        }
    }

    /**
     * 是否可以追踪目标(用于重新规划路径): 原版要求视线畅通; 体素寻路实体使用 Sensing#canPerceive 增强感知.
     *
     * @param target 目标
     * @return 是否可追踪
     */
    protected boolean canTrackTarget(LivingEntity target) {
        return VoxelNodeEvaluator.isUsedBy(this.mob) ? this.mob.getSensing().canPerceive(target) : this.mob.getSensing().hasLineOfSight(target);
    }

    protected void checkAndPerformAttack(LivingEntity target) {
        if (this.canPerformAttack(target)) {
            this.resetAttackCooldown();
            this.mob.swing(InteractionHand.MAIN_HAND);
            this.mob.doHurtTarget(getServerLevel(this.mob), target);
        }
    }

    protected void resetAttackCooldown() {
        this.ticksUntilNextAttack = this.adjustedTickDelay(20);
    }

    protected boolean isTimeToAttack() {
        return this.ticksUntilNextAttack <= 0;
    }

    protected boolean canPerformAttack(LivingEntity entity) {
        return this.isTimeToAttack() && this.mob.isWithinMeleeAttackRange(entity) && this.mob.getSensing().hasLineOfSight(entity);
    }

    protected int getTicksUntilNextAttack() {
        return this.ticksUntilNextAttack;
    }

    protected int getAttackInterval() {
        return this.adjustedTickDelay(20);
    }
}
