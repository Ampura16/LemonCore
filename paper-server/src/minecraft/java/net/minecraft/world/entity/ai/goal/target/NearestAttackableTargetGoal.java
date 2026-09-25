package net.minecraft.world.entity.ai.goal.target;

import java.util.EnumSet;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.VoxelNodeEvaluator;
import net.minecraft.world.phys.AABB;
import org.jspecify.annotations.Nullable;

public class NearestAttackableTargetGoal<T extends LivingEntity> extends TargetGoal {
    private static final int DEFAULT_RANDOM_INTERVAL = 10;
    /** 体素寻路实体搜索玩家时的随机间隔(原版为 10, 即平均约 10 tick 才搜索一次) */
    private static final int VOXEL_PLAYER_RANDOM_INTERVAL = 2;
    /** 体素寻路实体搜索其他实体时的最小随机间隔 */
    private static final int VOXEL_MIN_RANDOM_INTERVAL = 2;
    protected final Class<T> targetType;
    protected final int randomInterval;
    protected @Nullable LivingEntity target;
    protected TargetingConditions targetConditions;

    public NearestAttackableTargetGoal(Mob mob, Class<T> targetType, boolean mustSee) {
        this(mob, targetType, 10, mustSee, false, null);
    }

    public NearestAttackableTargetGoal(Mob mob, Class<T> targetType, boolean mustSee, TargetingConditions.Selector selector) {
        this(mob, targetType, 10, mustSee, false, selector);
    }

    public NearestAttackableTargetGoal(Mob mob, Class<T> targetType, boolean mustSee, boolean mustReach) {
        this(mob, targetType, 10, mustSee, mustReach, null);
    }

    public NearestAttackableTargetGoal(
        Mob mob, Class<T> targetType, int interval, boolean mustSee, boolean mustReach, TargetingConditions.@Nullable Selector selector
    ) {
        super(mob, mustSee, mustReach);
        this.targetType = targetType;
        // 体素寻路实体: 更快地搜索目标, 并使用增强感知(透明方块/近距离可达目标)
        boolean voxel = VoxelNodeEvaluator.isUsedBy(mob);
        this.randomInterval = reducedTickDelay(voxel ? getVoxelRandomInterval(targetType, interval) : interval);
        this.setFlags(EnumSet.of(Goal.Flag.TARGET));
        this.targetConditions = TargetingConditions.forCombat().range(this.getFollowDistance()).selector(selector);
        if (voxel) {
            this.targetConditions.useVoxelPerception();
        }
    }

    /**
     * 计算体素寻路实体的搜索间隔: 以玩家为目标时固定为较短间隔; 其他目标减半但不低于下限.
     * 间隔为 0 表示调用方要求每次都搜索, 保持不变.
     */
    private static int getVoxelRandomInterval(Class<?> targetType, int interval) {
        if (interval <= 0) {
            return interval;
        } else if (targetType == Player.class || targetType == ServerPlayer.class) {
            return Math.min(interval, VOXEL_PLAYER_RANDOM_INTERVAL);
        } else {
            return Math.min(interval, Math.max(VOXEL_MIN_RANDOM_INTERVAL, interval / 2));
        }
    }

    @Override
    public boolean canUse() {
        if (this.randomInterval > 0 && this.mob.getRandom().nextInt(this.randomInterval) != 0) {
            return false;
        } else {
            this.findTarget();
            return this.target != null;
        }
    }

    protected AABB getTargetSearchArea(double targetDistance) {
        return this.mob.getBoundingBox().inflate(targetDistance, targetDistance, targetDistance);
    }

    protected void findTarget() {
        ServerLevel serverLevel = getServerLevel(this.mob);
        if (this.targetType != Player.class && this.targetType != ServerPlayer.class) {
            this.target = serverLevel.getNearestEntity(
                this.mob.level().getEntitiesOfClass(this.targetType, this.getTargetSearchArea(this.getFollowDistance()), entity -> true),
                this.getTargetConditions(),
                this.mob,
                this.mob.getX(),
                this.mob.getEyeY(),
                this.mob.getZ()
            );
        } else {
            this.target = serverLevel.getNearestPlayer(this.getTargetConditions(), this.mob, this.mob.getX(), this.mob.getEyeY(), this.mob.getZ());
        }
    }

    @Override
    public void start() {
        this.mob.setTarget(this.target, this.target instanceof ServerPlayer ? org.bukkit.event.entity.EntityTargetEvent.TargetReason.CLOSEST_PLAYER : org.bukkit.event.entity.EntityTargetEvent.TargetReason.CLOSEST_ENTITY); // CraftBukkit - reason
        super.start();
    }

    public void setTarget(@Nullable LivingEntity target) {
        this.target = target;
    }

    private TargetingConditions getTargetConditions() {
        return this.targetConditions.range(this.getFollowDistance());
    }
}
