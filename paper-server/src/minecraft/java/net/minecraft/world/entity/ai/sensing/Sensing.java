package net.minecraft.world.entity.ai.sensing;

import it.unimi.dsi.fastutil.ints.Int2LongMap;
import it.unimi.dsi.fastutil.ints.Int2LongOpenHashMap;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import it.unimi.dsi.fastutil.ints.IntSet;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.pathfinder.Node;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.level.pathfinder.VoxelNodeEvaluator;

public class Sensing {
    private final Mob mob;
    private final IntSet seen = new IntOpenHashSet();
    private final IntSet unseen = new IntOpenHashSet();
    /** 本 tick 内的感知结果缓存(仅体素寻路实体使用) */
    private final IntSet perceived = new IntOpenHashSet();
    private final IntSet unperceived = new IntOpenHashSet();
    /** 路径可达性缓存: 实体 id -> (过期时间 << 1 | 是否可达), 路径探测开销较大, 需要跨 tick 缓存 */
    private final Int2LongMap reachableCache = new Int2LongOpenHashMap();
    /** 路径可达性缓存的基础有效期(tick), 实际有效期会附加随机抖动以错开大量实体的探测时间 */
    private static final int REACHABLE_CACHE_TICKS = 20;
    private static final int REACHABLE_CACHE_JITTER = 10;
    /** 路径终点与目标之间允许的最大水平距离平方(与 TargetGoal#canReach 一致)及最大高度差 */
    private static final double REACHABLE_HORIZONTAL_DISTANCE_SQR = 2.25;
    private static final int REACHABLE_VERTICAL_DISTANCE = 2;

    public Sensing(Mob mob) {
        this.mob = mob;
        this.reachableCache.defaultReturnValue(-1L);
    }

    public void tick() {
        this.seen.clear();
        this.unseen.clear();
        this.perceived.clear();
        this.unperceived.clear();
        if (!this.reachableCache.isEmpty() && this.mob.tickCount % 200 == 0) {
            long gameTime = this.mob.level().getGameTime();
            this.reachableCache.values().removeIf(packed -> (packed >>> 1) <= gameTime);
        }
    }

    public boolean hasLineOfSight(Entity entity) {
        int id = entity.getId();
        if (this.seen.contains(id)) {
            return true;
        } else if (this.unseen.contains(id)) {
            return false;
        } else {
            ProfilerFiller profilerFiller = Profiler.get();
            profilerFiller.push("hasLineOfSight");
            boolean hasLineOfSight = this.mob.hasLineOfSight(entity);
            profilerFiller.pop();
            if (hasLineOfSight) {
                this.seen.add(id);
            } else {
                this.unseen.add(id);
            }

            return hasLineOfSight;
        }
    }

    /**
     * 判断实体能否"感知"到目标, 用于索敌与追击.
     * <p>
     * 未使用体素寻路的实体与原版 {@link #hasLineOfSight(Entity)} 完全一致.
     * 使用体素寻路({@link VoxelNodeEvaluator})的实体满足以下任一条件即可感知:
     * <ol>
     *     <li>原版视线畅通</li>
     *     <li>体素视线畅通: 玻璃、玻璃板/铁栏杆、树叶不遮挡视线</li>
     *     <li>目标位于 {@link VoxelNodeEvaluator#SENSE_RADIUS} 格内, 且可以经由路径到达(结果会缓存一段时间)</li>
     * </ol>
     * 注意: 发动近战攻击本身仍要求原版视线, 不会隔墙攻击.
     *
     * @param entity 目标实体
     * @return 是否可以感知
     */
    public boolean canPerceive(Entity entity) {
        if (this.hasLineOfSight(entity)) {
            return true;
        } else if (!VoxelNodeEvaluator.isUsedBy(this.mob)) {
            return false;
        }

        int id = entity.getId();
        if (this.perceived.contains(id)) {
            return true;
        } else if (this.unperceived.contains(id)) {
            return false;
        }

        ProfilerFiller profilerFiller = Profiler.get();
        profilerFiller.push("canPerceive");
        boolean canPerceive = VoxelNodeEvaluator.hasVoxelLineOfSight(this.mob, entity) || this.canReachWithinSenseRadius(entity);
        profilerFiller.pop();
        if (canPerceive) {
            this.perceived.add(id);
        } else {
            this.unperceived.add(id);
        }

        return canPerceive;
    }

    /**
     * 判断目标是否处于近距离感知半径内并且可以经由路径到达.
     *
     * @param entity 目标实体
     * @return 是否可达
     */
    private boolean canReachWithinSenseRadius(Entity entity) {
        if (this.mob.distanceToSqr(entity) > VoxelNodeEvaluator.SENSE_RADIUS * VoxelNodeEvaluator.SENSE_RADIUS) {
            return false;
        }

        int id = entity.getId();
        long gameTime = this.mob.level().getGameTime();
        long packed = this.reachableCache.get(id);
        if (packed != -1L && (packed >>> 1) > gameTime) {
            return (packed & 1L) != 0L;
        }

        boolean reachable = false;
        Path path = this.mob.getNavigation().probePath(entity, 0);
        Node endNode = path == null ? null : path.getEndNode();
        if (endNode != null) {
            int dx = endNode.x - entity.getBlockX();
            int dz = endNode.z - entity.getBlockZ();
            reachable = dx * dx + dz * dz <= REACHABLE_HORIZONTAL_DISTANCE_SQR
                && Math.abs(endNode.y - entity.getBlockY()) <= REACHABLE_VERTICAL_DISTANCE;
        }

        long expireAt = gameTime + REACHABLE_CACHE_TICKS + this.mob.getRandom().nextInt(REACHABLE_CACHE_JITTER);
        this.reachableCache.put(id, expireAt << 1 | (reachable ? 1L : 0L));
        return reachable;
    }
}
