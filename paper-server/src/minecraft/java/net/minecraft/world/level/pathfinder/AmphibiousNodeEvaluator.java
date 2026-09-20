package net.minecraft.world.level.pathfinder;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.PathNavigationRegion;
import org.jspecify.annotations.Nullable;

/**
 * 两栖型节点评估器,继承自 {@link WalkNodeEvaluator},
 * 用于既能在陆地行走也能在水中游泳的实体（如青蛙、乌龟）的寻路计算.
 * 通过临时调整水、陆地、水域边界的移动惩罚值,使实体在寻路时更偏好走水路;
 * 并额外支持垂直方向（上/下）的水中邻居生成.
 */
public class AmphibiousNodeEvaluator extends WalkNodeEvaluator {
    /** 是否更偏好在浅水（海平面以下不超过 10 格）中游泳,而非深水. */
    private final boolean prefersShallowSwimming;
    /** 寻路前的 {@link PathType#WALKABLE} 原始惩罚值,供 {@link #done} 还原. */
    private float oldWalkableCost;
    /** 寻路前的 {@link PathType#WATER_BORDER} 原始惩罚值,供 {@link #done} 还原. */
    private float oldWaterBorderCost;

    /**
     * 构造一个两栖节点评估器.
     *
     * @param prefersShallowSwimming 是否偏好浅水游泳
     */
    public AmphibiousNodeEvaluator(boolean prefersShallowSwimming) {
        this.prefersShallowSwimming = prefersShallowSwimming;
    }

    /**
     * 准备一次寻路任务:调用父类准备逻辑后,临时将 {@link PathType#WATER} 惩罚值设为 0
     * （水中不额外计代价）,并保存 {@link PathType#WALKABLE}、{@link PathType#WATER_BORDER}
     * 的原始惩罚值,分别提高到 6.0F 和 4.0F,使实体在寻路时更倾向于走水路而非陆地.
     *
     * @param level 寻路所在的区域
     * @param mob   进行寻路的实体
     */
    @Override
    public void prepare(PathNavigationRegion level, Mob mob) {
        super.prepare(level, mob);
        mob.setPathfindingMalus(PathType.WATER, 0.0F);
        this.oldWalkableCost = mob.getPathfindingMalus(PathType.WALKABLE);
        mob.setPathfindingMalus(PathType.WALKABLE, 6.0F);
        this.oldWaterBorderCost = mob.getPathfindingMalus(PathType.WATER_BORDER);
        mob.setPathfindingMalus(PathType.WATER_BORDER, 4.0F);
    }

    /**
     * 结束一次寻路任务:将 {@link #prepare} 中临时调整的 {@link PathType#WALKABLE}、
     * {@link PathType#WATER_BORDER} 惩罚值还原为原始值,再调用父类结束逻辑.
     */
    @Override
    public void done() {
        this.mob.setPathfindingMalus(PathType.WALKABLE, this.oldWalkableCost);
        this.mob.setPathfindingMalus(PathType.WATER_BORDER, this.oldWaterBorderCost);
        super.done();
    }

    /**
     * 计算寻路的起始节点.若实体不在水中,直接复用父类 {@link WalkNodeEvaluator#getStart()}
     * 的陆地起点计算逻辑;若在水中,则以实体包围盒最小角（X、Z）及略高于底部（Y + 0.5）
     * 的方块坐标作为起点.
     *
     * @return 起始节点
     */
    @Override
    public Node getStart() {
        return !this.mob.isInWater()
            ? super.getStart()
            : this.getStartNode(
            new BlockPos(
                Mth.floor(this.mob.getBoundingBox().minX), Mth.floor(this.mob.getBoundingBox().minY + 0.5), Mth.floor(this.mob.getBoundingBox().minZ)
            )
        );
    }

    /**
     * 根据目标世界坐标获取对应的目标节点,将目标 Y 坐标 +0.5 后再转换为目标节点
     * （与 {@link #getStart()} 中水中起点的 Y 偏移保持一致）.
     *
     * @param x 目标 X 坐标
     * @param y 目标 Y 坐标
     * @param z 目标 Z 坐标
     * @return 对应的目标节点
     */
    @Override
    public Target getTarget(double x, double y, double z) {
        return this.getTargetNodeAt(x, y + 0.5, z);
    }

    /**
     * 计算给定节点的所有可行邻居节点.先调用父类 {@link WalkNodeEvaluator#getNeighbors}
     * 得到水平方向（含对角线）邻居,再额外尝试正上方与正下方的邻居节点
     * （允许的向上跳跃格数逻辑与父类 {@link WalkNodeEvaluator#getNeighbors} 一致）;
     * 只有类型为 {@link PathType#WATER} 的垂直邻居才被视为有效（见 {@link #isVerticalNeighborValid}）,
     * 且下方邻居额外要求当前节点不是 {@link PathType#TRAPDOOR}.
     * 最后,若 {@link #prefersShallowSwimming} 为真,对海平面以下超过 10 格的水中邻居节点
     * 惩罚值 +1,使实体更偏好浅水路径.
     *
     * @param outputArray 用于接收邻居节点的输出数组
     * @param node        当前节点
     * @return 有效邻居的数量
     */
    @Override
    public int getNeighbors(Node[] outputArray, Node node) {
        int i = super.getNeighbors(outputArray, node);
        PathType cachedPathType = this.getCachedPathType(node.x, node.y + 1, node.z);
        PathType cachedPathType1 = this.getCachedPathType(node.x, node.y, node.z);
        int floor;
        if (this.mob.getPathfindingMalus(cachedPathType) >= 0.0F && cachedPathType1 != PathType.STICKY_HONEY) {
            floor = Mth.floor(Math.max(1.0F, this.mob.maxUpStep()));
        } else {
            floor = 0;
        }

        double floorLevel = this.getFloorLevel(new BlockPos(node.x, node.y, node.z));
        Node node1 = this.findAcceptedNode(node.x, node.y + 1, node.z, Math.max(0, floor - 1), floorLevel, Direction.UP, cachedPathType1);
        Node node2 = this.findAcceptedNode(node.x, node.y - 1, node.z, floor, floorLevel, Direction.DOWN, cachedPathType1);
        if (this.isVerticalNeighborValid(node1, node)) {
            outputArray[i++] = node1;
        }

        if (this.isVerticalNeighborValid(node2, node) && cachedPathType1 != PathType.TRAPDOOR) {
            outputArray[i++] = node2;
        }

        for (int i1 = 0; i1 < i; i1++) {
            Node node3 = outputArray[i1];
            if (node3.type == PathType.WATER && this.prefersShallowSwimming && node3.y < this.mob.level().getSeaLevel() - 10) {
                node3.costMalus++;
            }
        }

        return i;
    }

    /**
     * 判断一个垂直方向（上/下）邻居节点是否有效:满足父类通用有效性判断
     * （{@link WalkNodeEvaluator#isNeighborValid}）,且节点类型必须是 {@link PathType#WATER}
     * （即只允许在水中垂直移动,不允许在陆地上凭空跳跃/下沉）.
     *
     * @param neighbor 候选垂直邻居节点
     * @param node     当前节点
     * @return 是否有效
     */
    private boolean isVerticalNeighborValid(@Nullable Node neighbor, Node node) {
        return this.isNeighborValid(neighbor, node) && neighbor.type == PathType.WATER;
    }

    /**
     * 标记本评估器为两栖类型,供父类 {@link WalkNodeEvaluator} 中
     * {@code getFloorLevel}、{@code findAcceptedNode} 等方法据此判断
     * 是否按两栖方式处理水体方块（例如允许直接停留在水中而非强制沉底）.
     *
     * @return 始终返回 true
     */
    @Override
    protected boolean isAmphibious() {
        return true;
    }

    /**
     * 获取指定坐标的路径类型.若该坐标本身是 {@link PathType#WATER},
     * 则检查其六个正交方向（{@link Direction#values()}）邻居的路径类型,
     * 只要有一个邻居是 {@link PathType#BLOCKED},就将该坐标视为
     * {@link PathType#WATER_BORDER}（靠近陆地/障碍的水域边缘）;
     * 若所有邻居均非阻塞,则保持 {@link PathType#WATER}.
     * 对于非水坐标,直接委托给父类 {@link WalkNodeEvaluator#getPathType} 处理.
     *
     * @param context 寻路上下文
     * @param x       坐标 X
     * @param y       坐标 Y
     * @param z       坐标 Z
     * @return 该坐标处的路径类型
     */
    @Override
    public PathType getPathType(PathfindingContext context, int x, int y, int z) {
        PathType pathTypeFromState = context.getPathTypeFromState(x, y, z);
        if (pathTypeFromState == PathType.WATER) {
            BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

            for (Direction direction : Direction.values()) {
                mutableBlockPos.set(x, y, z).move(direction);
                PathType pathTypeFromState1 = context.getPathTypeFromState(mutableBlockPos.getX(), mutableBlockPos.getY(), mutableBlockPos.getZ());
                if (pathTypeFromState1 == PathType.BLOCKED) {
                    return PathType.WATER_BORDER;
                }
            }

            return PathType.WATER;
        } else {
            return super.getPathType(context, x, y, z);
        }
    }
}
