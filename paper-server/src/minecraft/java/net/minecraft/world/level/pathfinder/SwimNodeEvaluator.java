package net.minecraft.world.level.pathfinder;

import com.google.common.collect.Maps;
import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import java.util.Map;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.PathNavigationRegion;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import org.jspecify.annotations.Nullable;

/**
 * 水中游泳型节点评估器,供 {@link net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation} 使用,
 * 用于水生实体（如鱼、鱿鱼、海豚）在水中寻路.
 * 与 {@link WalkNodeEvaluator} 不同,本类考虑三维（含上下方向）的六个正交邻居,
 * 并支持"破水而出"（breach）的特殊路径类型,仅供海豚等允许露出水面的实体使用.
 */
public class SwimNodeEvaluator extends NodeEvaluator {
    /** 是否允许实体破水而出（露出水面）,如海豚. */
    private final boolean allowBreaching;
    /** 按坐标缓存的路径类型结果,避免同一次寻路中重复计算. */
    private final Long2ObjectMap<PathType> pathTypesByPosCache = new Long2ObjectOpenHashMap<>();

    /**
     * 构造一个游泳节点评估器.
     *
     * @param allowBreaching 是否允许实体破水而出
     */
    public SwimNodeEvaluator(boolean allowBreaching) {
        this.allowBreaching = allowBreaching;
    }

    /**
     * 准备一次寻路任务:调用父类准备逻辑,并清空路径类型缓存.
     *
     * @param level 寻路所在的区域
     * @param mob   进行寻路的实体
     */
    @Override
    public void prepare(PathNavigationRegion level, Mob mob) {
        super.prepare(level, mob);
        this.pathTypesByPosCache.clear();
    }

    /**
     * 结束一次寻路任务:调用父类结束逻辑,并清空路径类型缓存.
     */
    @Override
    public void done() {
        super.done();
        this.pathTypesByPosCache.clear();
    }

    /**
     * 计算寻路的起始节点,取实体包围盒最小角（X、Z）及略高于底部（Y + 0.5）的方块坐标.
     *
     * @return 起始节点
     */
    @Override
    public Node getStart() {
        return this.getNode(
            Mth.floor(this.mob.getBoundingBox().minX), Mth.floor(this.mob.getBoundingBox().minY + 0.5), Mth.floor(this.mob.getBoundingBox().minZ)
        );
    }

    /**
     * 根据目标世界坐标获取对应的目标节点.
     *
     * @param x 目标 X 坐标
     * @param y 目标 Y 坐标
     * @param z 目标 Z 坐标
     * @return 对应的目标节点
     */
    @Override
    public Target getTarget(double x, double y, double z) {
        return this.getTargetNodeAt(x, y, z);
    }

    /**
     * 计算给定节点在三维空间内的所有可行邻居节点:
     * 首先尝试全部六个正交方向（{@link Direction#values()},包含上下）,
     * 然后针对水平面上的四个对角方向,若相邻两条直角边节点均存在且惩罚值非负
     * （见 {@link #hasMalus}）,则尝试对应的水平对角邻居节点.
     *
     * @param outputArray 用于接收邻居节点的输出数组
     * @param node        当前节点
     * @return 有效邻居的数量
     */
    @Override
    public int getNeighbors(Node[] outputArray, Node node) {
        int i = 0;
        Map<Direction, Node> map = Maps.newEnumMap(Direction.class);

        for (Direction direction : Direction.values()) {
            Node node1 = this.findAcceptedNode(node.x + direction.getStepX(), node.y + direction.getStepY(), node.z + direction.getStepZ());
            map.put(direction, node1);
            if (this.isNodeValid(node1)) {
                outputArray[i++] = node1;
            }
        }

        for (Direction direction1 : Direction.Plane.HORIZONTAL) {
            Direction clockWise = direction1.getClockWise();
            if (hasMalus(map.get(direction1)) && hasMalus(map.get(clockWise))) {
                Node node2 = this.findAcceptedNode(
                    node.x + direction1.getStepX() + clockWise.getStepX(), node.y, node.z + direction1.getStepZ() + clockWise.getStepZ()
                );
                if (this.isNodeValid(node2)) {
                    outputArray[i++] = node2;
                }
            }
        }

        return i;
    }

    /**
     * 判断一个节点是否有效:非空且未被关闭.
     *
     * @param node 待判断的节点
     * @return 是否有效
     */
    protected boolean isNodeValid(@Nullable Node node) {
        return node != null && !node.closed;
    }

    /**
     * 判断一个节点是否具有非负惩罚值（即可通行）,供对角邻居判断使用.
     *
     * @param node 待判断的节点
     * @return 是否可通行
     */
    private static boolean hasMalus(@Nullable Node node) {
        return node != null && node.costMalus >= 0.0F;
    }

    /**
     * 计算并返回给定坐标处被接受的节点.
     * 仅当该坐标的路径类型为 {@link PathType#WATER},
     * 或（当 {@link #allowBreaching} 为 true 时）为 {@link PathType#BREACH} 时才会生成节点;
     * 若该坐标实际上没有流体（即为破水而出的空气格）,会额外增加 8.0 的惩罚值,
     * 以降低实体长时间露出水面移动的倾向.
     *
     * @param x 目标 X 坐标
     * @param y 目标 Y 坐标
     * @param z 目标 Z 坐标
     * @return 被接受的节点;若该坐标不可通行则返回 null
     */
    protected @Nullable Node findAcceptedNode(int x, int y, int z) {
        Node node = null;
        PathType cachedBlockType = this.getCachedBlockType(x, y, z);
        if (this.allowBreaching && cachedBlockType == PathType.BREACH || cachedBlockType == PathType.WATER) {
            float pathfindingMalus = this.mob.getPathfindingMalus(cachedBlockType);
            if (pathfindingMalus >= 0.0F) {
                node = this.getNode(x, y, z);
                node.type = cachedBlockType;
                node.costMalus = Math.max(node.costMalus, pathfindingMalus);
                if (this.currentContext.level().getFluidState(new BlockPos(x, y, z)).isEmpty()) {
                    node.costMalus += 8.0F;
                }
            }
        }

        return node;
    }

    /**
     * 获取（并按坐标缓存）指定坐标的路径类型,避免同一次寻路中重复计算.
     *
     * @param x 坐标 X
     * @param y 坐标 Y
     * @param z 坐标 Z
     * @return 对应坐标的路径类型
     */
    protected PathType getCachedBlockType(int x, int y, int z) {
        return this.pathTypesByPosCache.computeIfAbsent(BlockPos.asLong(x, y, z), l -> this.getPathType(this.currentContext, x, y, z));
    }

    /**
     * 获取单个坐标点的路径类型,委托给 {@link #getPathTypeOfMob} 计算.
     *
     * @param context 寻路上下文
     * @param x       坐标 X
     * @param y       坐标 Y
     * @param z       坐标 Z
     * @return 该坐标处的路径类型
     */
    @Override
    public PathType getPathType(PathfindingContext context, int x, int y, int z) {
        return this.getPathTypeOfMob(context, x, y, z, this.mob);
    }

    /**
     * 遍历实体包围盒（{@code entityWidth} x {@code entityHeight} x {@code entityDepth}）
     * 范围内所有方块,判定实体在该坐标处的综合路径类型:
     * 若某方块无流体、可作为水中寻路的可通行方块且为空气,则返回 {@link PathType#BREACH}
     * （表示此处是水面破水口,允许海豚等实体探出水面）;
     * 若某方块不含水流体标签（{@link net.minecraft.tags.FluidTags#WATER}）,
     * 则直接返回 {@link PathType#BLOCKED}（包围盒内一旦出现非水非破水面方块即视为不可通行）;
     * 若包围盒内所有方块均为水,最后再单独检查基准坐标方块是否可用于水中寻路,
     * 可通行则返回 {@link PathType#WATER},否则返回 {@link PathType#BLOCKED}.
     *
     * @param context 寻路上下文
     * @param x       坐标 X
     * @param y       坐标 Y
     * @param z       坐标 Z
     * @param mob     进行寻路的实体
     * @return 该实体在此坐标处的综合路径类型
     */
    @Override
    public PathType getPathTypeOfMob(PathfindingContext context, int x, int y, int z, Mob mob) {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        for (int i = x; i < x + this.entityWidth; i++) {
            for (int i1 = y; i1 < y + this.entityHeight; i1++) {
                for (int i2 = z; i2 < z + this.entityDepth; i2++) {
                    BlockState blockState = context.getBlockState(mutableBlockPos.set(i, i1, i2));
                    FluidState fluidState = blockState.getFluidState();
                    if (fluidState.isEmpty() && blockState.isPathfindable(PathComputationType.WATER) && blockState.isAir()) {
                        return PathType.BREACH;
                    }

                    if (!fluidState.is(FluidTags.WATER)) {
                        return PathType.BLOCKED;
                    }
                }
            }
        }

        BlockState blockState1 = context.getBlockState(mutableBlockPos);
        return blockState1.isPathfindable(PathComputationType.WATER) ? PathType.WATER : PathType.BLOCKED;
    }
}
