package net.minecraft.world.level.pathfinder;

import it.unimi.dsi.fastutil.longs.Long2ObjectMap;
import it.unimi.dsi.fastutil.longs.Long2ObjectOpenHashMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanMap;
import it.unimi.dsi.fastutil.objects.Object2BooleanOpenHashMap;
import java.util.EnumSet;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.PathNavigationRegion;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.IronBarsBlock;
import net.minecraft.world.level.block.TransparentBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

/**
 * 基于体素(Voxel)的三维寻路节点评估器,用于替代传统的 {@link NodeEvaluator}.
 * 
 * 主要改进:
 * - 地面寻路规则与原版 WalkNodeEvaluator 保持一致(楼梯/台阶本身即可通过 maxUpStep 与跳跃处理)
 * - 支持梯子、藤蔓等可攀爬方块的竖直移动, 可解决多层建筑等复杂路线
 * - 提供体素视线检测 {@link #hasVoxelLineOfSight(LivingEntity, Entity)}: 玻璃、玻璃板/铁栏杆、树叶视为透明
 * - 提供寻路预算/搜索距离倍率, 由 PathNavigation 在使用本评估器时自动应用
 * - 通过 {@link #isUsedBy(Mob)} 供感知(Sensing)、索敌与近战 AI 判断是否启用增强逻辑
 *
 * @author Ampura16
 * @date 2026/09/26
 */
public class VoxelNodeEvaluator extends NodeEvaluator {
    /** 栅栏柱之间可通行缝隙的间距阈值 */
    public static final double SPACE_BETWEEN_WALL_POSTS = 0.5;
    /** 默认实体跳跃高度 */
    private static final double DEFAULT_MOB_JUMP_HEIGHT = 1.125;
    /** 体素视线检测的最大距离(方块数), 与原版 LivingEntity#hasLineOfSight 的 128 格保持一致 */
    public static final double MAX_LINE_OF_SIGHT_DISTANCE = 128.0;
    /** 近距离感知半径: 该距离内即使被墙体遮挡, 只要目标可以经由路径到达, 也视为可感知 */
    public static final double SENSE_RADIUS = 64.0;
    /**
     * 寻路节点预算倍率. 原版预算为 FOLLOW_RANGE * 16 个节点, 在迷宫/多层建筑中常在找到绕行路线前耗尽,
     * 导致只能得到一条"尽量靠近"的残缺路径. 由 PathNavigation 在构造和刷新预算时应用.
     */
    public static final float SEARCH_BUDGET_MULTIPLIER = 2.0F;
    /**
     * 寻路搜索距离倍率. 原版限制路径实际长度不超过 FOLLOW_RANGE, 而绕行路线的路程通常明显大于直线距离,
     * 因此放宽搜索区域与路径长度上限, 避免目标在追踪范围内却因为需要绕路而找不到完整路径.
     */
    public static final float SEARCH_RANGE_MULTIPLIER = 1.5F;
    /** 体素路径类型缓存 */
    private final Long2ObjectMap<PathType> voxelPathTypesCache = new Long2ObjectOpenHashMap<>();
    /** 体素碰撞缓存 */
    private final Object2BooleanMap<AABB> voxelCollisionCache = new Object2BooleanOpenHashMap<>();
    /** 可复用的水平方向邻居节点数组 */
    private final Node[] reusableNeighbors = new Node[Direction.Plane.HORIZONTAL.length()];
    /** 地形高度缓存,用于多层级地形检测 */
    private final Long2ObjectMap<Double> terrainHeightCache = new Long2ObjectOpenHashMap<>();
    /** 可复用的可变坐标, 仅用于可攀爬方块查询 */
    private final BlockPos.MutableBlockPos climbCheckPos = new BlockPos.MutableBlockPos();

    /**
     * 准备一次寻路任务:初始化体素缓存和上下文
     *
     * @param level 寻路所在的区域
     * @param mob   进行寻路的实体
     */
    @Override
    public void prepare(PathNavigationRegion level, Mob mob) {
        super.prepare(level, mob);
        this.voxelPathTypesCache.clear();
        this.voxelCollisionCache.clear();
        this.terrainHeightCache.clear();
        mob.onPathfindingStart();
    }

    /**
     * 结束一次寻路任务:清理所有缓存
     */
    @Override
    public void done() {
        this.mob.onPathfindingDone();
        this.voxelPathTypesCache.clear();
        this.voxelCollisionCache.clear();
        this.terrainHeightCache.clear();
        super.done();
    }

    /**
     * 计算寻路的起始节点.
     * 起点必须是实体当前实际所在的位置(与原版一致), 否则生成的路径首个节点可能偏离实体数格,
     * 导致实体原地打转或选择错误的楼层.
     *
     * @return 计算出的起始节点
     */
    @Override
    public Node getStart() {
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        int blockY = this.mob.getBlockY();
        BlockState blockState = this.currentContext.getBlockState(mutableBlockPos.set(this.mob.getX(), (double)blockY, this.mob.getZ()));
        if (!this.mob.canStandOnFluid(blockState.getFluidState())) {
            if (this.canFloat() && this.mob.isInWater()) {
                while (true) {
                    if (!blockState.is(Blocks.WATER) && blockState.getFluidState() != Fluids.WATER.getSource(false)) {
                        blockY--;
                        break;
                    }
                    blockState = this.currentContext.getBlockState(mutableBlockPos.set(this.mob.getX(), (double)(++blockY), this.mob.getZ()));
                }
            } else if (this.mob.onGround()) {
                blockY = Mth.floor(this.mob.getY() + 0.5);
            } else {
                mutableBlockPos.set(this.mob.getX(), this.mob.getY() + 1.0, this.mob.getZ());
                while (mutableBlockPos.getY() > this.currentContext.level().getMinY()) {
                    blockY = mutableBlockPos.getY();
                    mutableBlockPos.setY(mutableBlockPos.getY() - 1);
                    BlockState blockState1 = this.currentContext.getBlockState(mutableBlockPos);
                    if (!blockState1.isAir() && !blockState1.isPathfindable(PathComputationType.LAND)) {
                        break;
                    }
                }
            }
        } else {
            while (this.mob.canStandOnFluid(blockState.getFluidState())) {
                blockState = this.currentContext.getBlockState(mutableBlockPos.set(this.mob.getX(), (double)(++blockY), this.mob.getZ()));
            }
            blockY--;
        }

        BlockPos blockPos = this.mob.blockPosition();
        if (!this.canStartAt(mutableBlockPos.set(blockPos.getX(), blockY, blockPos.getZ()))) {
            AABB boundingBox = this.mob.getBoundingBox();
            if (this.canStartAt(mutableBlockPos.set(boundingBox.minX, (double)blockY, boundingBox.minZ))
                || this.canStartAt(mutableBlockPos.set(boundingBox.minX, (double)blockY, boundingBox.maxZ))
                || this.canStartAt(mutableBlockPos.set(boundingBox.maxX, (double)blockY, boundingBox.minZ))
                || this.canStartAt(mutableBlockPos.set(boundingBox.maxX, (double)blockY, boundingBox.maxZ))) {
                return this.getStartNode(mutableBlockPos);
            }
        }

        return this.getStartNode(new BlockPos(blockPos.getX(), blockY, blockPos.getZ()));
    }

    /**
     * 根据方块坐标构造起始节点
     *
     * @param pos 起始方块坐标
     * @return 构造好的起始节点
     */
    protected Node getStartNode(BlockPos pos) {
        Node node = this.getNode(pos);
        node.type = this.getVoxelPathType(node.x, node.y, node.z);
        node.costMalus = this.mob.getPathfindingMalus(node.type);
        return node;
    }

    /**
     * 判断给定位置是否可以作为寻路的起点
     *
     * @param pos 待判断的方块坐标
     * @return 是否可作为起点
     */
    protected boolean canStartAt(BlockPos pos) {
        PathType pathType = this.getVoxelPathType(pos.getX(), pos.getY(), pos.getZ());
        return pathType != PathType.OPEN && this.mob.getPathfindingMalus(pathType) >= 0.0F;
    }

    /**
     * 根据目标世界坐标获取对应的目标节点
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
     * 计算给定节点的所有可行邻居节点.
     * 水平与对角线邻居沿用原版地面寻路规则(可跨越 maxUpStep/跳跃一格/下落至安全高度),
     * 另外在梯子、藤蔓等可攀爬方块上生成竖直方向的攀爬邻居.
     *
     * @param outputArray 用于接收邻居节点的输出数组
     * @param node        当前节点
     * @return 有效邻居的数量
     */
    @Override
    public int getNeighbors(Node[] outputArray, Node node) {
        int i = 0;
        int verticalDeltaLimit = 0;
        PathType pathTypeAbove = this.getVoxelPathType(node.x, node.y + 1, node.z);
        PathType currentPathType = this.getVoxelPathType(node.x, node.y, node.z);
        
        if (this.mob.getPathfindingMalus(pathTypeAbove) >= 0.0F && currentPathType != PathType.STICKY_HONEY) {
            verticalDeltaLimit = Mth.floor(Math.max(1.0F, this.mob.maxUpStep()));
        }

        double floorLevel = this.getVoxelFloorLevel(new BlockPos(node.x, node.y, node.z));

        // 水平方向邻居
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            Node neighbor = this.findVoxelAcceptedNode(
                node.x + direction.getStepX(), node.y, node.z + direction.getStepZ(),
                verticalDeltaLimit, floorLevel, direction, currentPathType
            );
            this.reusableNeighbors[direction.get2DDataValue()] = neighbor;
            if (this.isVoxelNeighborValid(neighbor, node)) {
                outputArray[i++] = neighbor;
            }
        }

        // 对角线邻居
        for (Direction direction : Direction.Plane.HORIZONTAL) {
            Direction clockWise = direction.getClockWise();
            if (this.isVoxelDiagonalValid(node, this.reusableNeighbors[direction.get2DDataValue()], 
                this.reusableNeighbors[clockWise.get2DDataValue()])) {
                Node diagonalNode = this.findVoxelAcceptedNode(
                    node.x + direction.getStepX() + clockWise.getStepX(),
                    node.y,
                    node.z + direction.getStepZ() + clockWise.getStepZ(),
                    verticalDeltaLimit,
                    floorLevel,
                    direction,
                    currentPathType
                );
                if (this.isVoxelDiagonalValid(diagonalNode)) {
                    outputArray[i++] = diagonalNode;
                }
            }
        }

        // 竖直攀爬邻居: 脚下所在格为可攀爬方块时可向上爬, 下方为可攀爬方块时可向下爬.
        // (普通地面上的竖直移动已由水平邻居中的跳跃/下落处理, 无需额外生成)
        if (this.isClimbable(node.x, node.y, node.z)) {
            Node aboveNode = this.findClimbNode(node.x, node.y + 1, node.z);
            if (this.isVoxelNeighborValid(aboveNode, node)) {
                outputArray[i++] = aboveNode;
            }
        }

        if (this.isClimbable(node.x, node.y - 1, node.z)) {
            Node belowNode = this.findClimbNode(node.x, node.y - 1, node.z);
            if (this.isVoxelNeighborValid(belowNode, node)) {
                outputArray[i++] = belowNode;
            }
        }

        return i;
    }

    /**
     * 判断体素邻居节点是否有效
     *
     * @param neighbor 候选邻居节点
     * @param node     当前节点
     * @return 是否有效
     */
    protected boolean isVoxelNeighborValid(@Nullable Node neighbor, Node node) {
        return neighbor != null && !neighbor.closed && 
               (neighbor.costMalus >= 0.0F || node.costMalus < 0.0F);
    }

    /**
     * 判断体素对角线移动是否合法
     *
     * @param root  对角线移动的起始节点
     * @param xNode 沿 X 方向的直角边邻居节点
     * @param zNode 沿 Z 方向的直角边邻居节点
     * @return 该对角线方向是否可以尝试
     */
    protected boolean isVoxelDiagonalValid(Node root, @Nullable Node xNode, @Nullable Node zNode) {
        if (zNode == null || xNode == null || zNode.y > root.y || xNode.y > root.y) {
            return false;
        }
        if (xNode.type != PathType.WALKABLE_DOOR && zNode.type != PathType.WALKABLE_DOOR) {
            boolean flag = zNode.type == PathType.FENCE && xNode.type == PathType.FENCE && 
                          this.mob.getBbWidth() < 0.5;
            return (zNode.y < root.y || zNode.costMalus >= 0.0F || flag) && 
                   (xNode.y < root.y || xNode.costMalus >= 0.0F || flag);
        }
        return false;
    }

    /**
     * 判断体素对角线目标节点本身是否有效
     *
     * @param node 待判断的对角线目标节点
     * @return 是否有效
     */
    protected boolean isVoxelDiagonalValid(@Nullable Node node) {
        return node != null && !node.closed && node.type != PathType.WALKABLE_DOOR && 
               node.costMalus >= 0.0F;
    }

    /**
     * 计算并返回给定坐标处的体素邻居节点
     * 悬空位置若处于可攀爬方块中(或正上方), 则作为攀爬节点接受, 而不是下落寻找地面
     *
     * @param x                  目标 X 坐标
     * @param y                  目标 Y 坐标
     * @param z                  目标 Z 坐标
     * @param verticalDeltaLimit 允许向上跳跃的剩余格数
     * @param nodeFloorLevel     当前节点的地面高度
     * @param direction          移动方向
     * @param pathType           当前节点的路径类型
     * @return 被接受的邻居节点;若不可行则返回 null
     */
    protected @Nullable Node findVoxelAcceptedNode(int x, int y, int z, int verticalDeltaLimit, 
        double nodeFloorLevel, Direction direction, PathType pathType) {
        Node node = null;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        double floorLevel = this.getVoxelFloorLevel(mutableBlockPos.set(x, y, z));
        
        if (floorLevel - nodeFloorLevel > this.getMobJumpHeight()) {
            return null;
        }

        PathType cachedPathType = this.getVoxelPathType(x, y, z);
        float pathfindingMalus = this.mob.getPathfindingMalus(cachedPathType);
        
        if (pathfindingMalus >= 0.0F) {
            node = this.getNodeAndUpdateCostToMax(x, y, z, cachedPathType, pathfindingMalus);
        }

        if (doesBlockHavePartialCollision(pathType) && node != null && 
            node.costMalus >= 0.0F && !this.canReachWithoutCollision(node)) {
            node = null;
        }

        if (cachedPathType != PathType.WALKABLE && (!this.isAmphibious() || cachedPathType != PathType.WATER)) {
            if ((node == null || node.costMalus < 0.0F) && verticalDeltaLimit > 0
                && (cachedPathType != PathType.FENCE || this.canWalkOverFences())
                && cachedPathType != PathType.UNPASSABLE_RAIL
                && cachedPathType != PathType.TRAPDOOR
                && cachedPathType != PathType.POWDER_SNOW) {
                node = this.tryJumpOn(x, y, z, verticalDeltaLimit, nodeFloorLevel, direction, pathType, mutableBlockPos);
            } else if (!this.isAmphibious() && cachedPathType == PathType.WATER && !this.canFloat()) {
                node = this.tryFindFirstNonWaterBelow(x, y, z, node);
            } else if (cachedPathType == PathType.OPEN) {
                Node climbNode = this.isClimbable(x, y, z) || this.isClimbable(x, y - 1, z) ? this.findClimbNode(x, y, z) : null;
                node = climbNode != null ? climbNode : this.tryFindFirstGroundNodeBelow(x, y, z);
            } else if (doesBlockHavePartialCollision(cachedPathType) && node == null) {
                node = this.getClosedNode(x, y, z, cachedPathType);
            }
            return node;
        }
        return node;
    }

    /**
     * 获取实体的跳跃高度
     *
     * @return 跳跃高度
     */
    private double getMobJumpHeight() {
        return Math.max(1.125, (double)this.mob.maxUpStep());
    }

    /**
     * 获取(或创建)指定坐标的节点,设置其路径类型,并将惩罚值更新为已有值与新值中的较大者
     *
     * @param x        节点 X 坐标
     * @param y        节点 Y 坐标
     * @param z        节点 Z 坐标
     * @param pathType 路径类型
     * @param malus    新的惩罚值
     * @return 更新后的节点
     */
    private Node getNodeAndUpdateCostToMax(int x, int y, int z, PathType pathType, float malus) {
        Node node = this.getNode(x, y, z);
        node.type = pathType;
        node.costMalus = Math.max(node.costMalus, malus);
        return node;
    }

    /**
     * 获取一个被标记为完全阻塞的节点
     *
     * @param x 节点 X 坐标
     * @param y 节点 Y 坐标
     * @param z 节点 Z 坐标
     * @return 阻塞节点
     */
    private Node getBlockedNode(int x, int y, int z) {
        Node node = this.getNode(x, y, z);
        node.type = PathType.BLOCKED;
        node.costMalus = -1.0F;
        return node;
    }

    /**
     * 获取一个被标记为"已关闭"的节点
     *
     * @param x        节点 X 坐标
     * @param y        节点 Y 坐标
     * @param z        节点 Z 坐标
     * @param pathType 路径类型
     * @return 关闭状态的节点
     */
    private Node getClosedNode(int x, int y, int z, PathType pathType) {
        Node node = this.getNode(x, y, z);
        node.closed = true;
        node.type = pathType;
        node.costMalus = pathType.getMalus();
        return node;
    }

    /**
     * 尝试让实体向上跳一格以越过障碍物
     *
     * @param x                  目标 X 坐标
     * @param y                  目标 Y 坐标
     * @param z                  目标 Z 坐标
     * @param verticalDeltaLimit 剩余可向上跳跃的格数
     * @param nodeFloorLevel     起始节点的地面高度
     * @param direction          移动方向
     * @param pathType           起始节点的路径类型
     * @param pos                可复用的可变坐标对象
     * @return 跳跃后到达的节点;若不可行则返回 null
     */
    private @Nullable Node tryJumpOn(int x, int y, int z, int verticalDeltaLimit, 
        double nodeFloorLevel, Direction direction, PathType pathType, BlockPos.MutableBlockPos pos) {
        Node node = this.findVoxelAcceptedNode(x, y + 1, z, verticalDeltaLimit - 1, 
            nodeFloorLevel, direction, pathType);
        if (node == null) {
            return null;
        }
        if (this.mob.getBbWidth() >= 1.0F) {
            return node;
        }
        if (node.type != PathType.OPEN && node.type != PathType.WALKABLE) {
            return node;
        }
        double d = x - direction.getStepX() + 0.5;
        double d1 = z - direction.getStepZ() + 0.5;
        double d2 = this.mob.getBbWidth() / 2.0;
        AABB aabb = new AABB(
            d - d2,
            this.getVoxelFloorLevel(pos.set(d, (double)(y + 1), d1)) + 0.001,
            d1 - d2,
            d + d2,
            this.mob.getBbHeight() + this.getVoxelFloorLevel(pos.set((double)node.x, (double)node.y, (double)node.z)) - 0.002,
            d1 + d2
        );
        return this.hasCollisions(aabb) ? null : node;
    }

    /**
     * 当实体不能漂浮且当前列是水体时,从当前高度向下探测
     *
     * @param x    列坐标 X
     * @param y    起始高度(会从其下方一格开始探测)
     * @param z    列坐标 Z
     * @param node 已有的候选节点
     * @return 第一个非水方块处对应的节点
     */
    private @Nullable Node tryFindFirstNonWaterBelow(int x, int y, int z, @Nullable Node node) {
        y--;
        while (y > this.mob.level().getMinY()) {
            PathType cachedPathType = this.getVoxelPathType(x, y, z);
            if (cachedPathType != PathType.WATER) {
                return node;
            }
            node = this.getNodeAndUpdateCostToMax(x, y, z, cachedPathType, 
                this.mob.getPathfindingMalus(cachedPathType));
            y--;
        }
        return node;
    }

    /**
     * 当当前坐标为悬空时,向下探测寻找第一个可站立的地面节点
     *
     * @param x 列坐标 X
     * @param y 起始悬空高度
     * @param z 列坐标 Z
     * @return 找到的可站立地面节点,或阻塞节点
     */
    private Node tryFindFirstGroundNodeBelow(int x, int y, int z) {
        for (int i = y - 1; i >= this.mob.level().getMinY(); i--) {
            if (y - i > this.mob.getMaxFallDistance()) {
                return this.getBlockedNode(x, i, z);
            }
            PathType cachedPathType = this.getVoxelPathType(x, i, z);
            float pathfindingMalus = this.mob.getPathfindingMalus(cachedPathType);
            if (cachedPathType != PathType.OPEN) {
                if (pathfindingMalus >= 0.0F) {
                    return this.getNodeAndUpdateCostToMax(x, i, z, cachedPathType, pathfindingMalus);
                }
                return this.getBlockedNode(x, i, z);
            }
        }
        return this.getBlockedNode(x, y, z);
    }

    /**
     * 判断给定包围盒是否与世界发生碰撞
     *
     * @param boundingBox 待检测的包围盒
     * @return 是否存在碰撞
     */
    private boolean hasCollisions(AABB boundingBox) {
        return this.voxelCollisionCache.computeIfAbsent(boundingBox, 
            key -> !this.currentContext.level().noCollision(this.mob, boundingBox));
    }

    /**
     * 判断某种路径类型对应的方块是否只有"部分碰撞体积"
     *
     * @param pathType 待判断的路径类型
     * @return 是否具有部分碰撞体积
     */
    private static boolean doesBlockHavePartialCollision(PathType pathType) {
        return pathType == PathType.FENCE || pathType == PathType.DOOR_WOOD_CLOSED || 
               pathType == PathType.DOOR_IRON_CLOSED;
    }

    /**
     * 通过将实体包围盒沿直线方向逐步移动到目标节点位置,逐段检测碰撞
     *
     * @param node 目标节点
     * @return 若整个移动路径均无碰撞则返回 true
     */
    private boolean canReachWithoutCollision(Node node) {
        AABB boundingBox = this.mob.getBoundingBox();
        Vec3 vec3 = new Vec3(
            node.x - this.mob.getX() + boundingBox.getXsize() / 2.0,
            node.y - this.mob.getY() + boundingBox.getYsize() / 2.0,
            node.z - this.mob.getZ() + boundingBox.getZsize() / 2.0
        );
        int ceil = Mth.ceil(vec3.length() / boundingBox.getSize());
        vec3 = vec3.scale(1.0F / ceil);

        for (int i = 1; i <= ceil; i++) {
            boundingBox = boundingBox.move(vec3);
            if (this.hasCollisions(boundingBox)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 获取指定方块坐标下方地面的高度(考虑体素缓存)
     *
     * @param pos 方块坐标
     * @return 地面高度
     */
    protected double getVoxelFloorLevel(BlockPos pos) {
        return this.terrainHeightCache.computeIfAbsent(pos.asLong(), key -> {
            BlockGetter blockGetter = this.currentContext.level();
            if ((this.canFloat() || this.isAmphibious()) && 
                blockGetter.getFluidState(pos).is(FluidTags.WATER)) {
                return pos.getY() + 0.5;
            }
            return getFloorLevel(blockGetter, pos);
        });
    }

    /**
     * 静态方法:计算给定方块下方碰撞形状的最高点作为地面高度
     *
     * @param level 方块所在的世界
     * @param pos   方块坐标
     * @return 地面高度
     */
    public static double getFloorLevel(BlockGetter level, BlockPos pos) {
        BlockPos blockPos = pos.below();
        VoxelShape collisionShape = level.getBlockState(blockPos).getCollisionShape(level, blockPos);
        return blockPos.getY() + (collisionShape.isEmpty() ? 0.0 : collisionShape.max(Direction.Axis.Y));
    }

    /**
     * 是否为两栖型评估器
     *
     * @return 是否两栖
     */
    protected boolean isAmphibious() {
        return false;
    }

    /**
     * 获取(并按坐标缓存)指定坐标的体素路径类型
     *
     * @param x 坐标 X
     * @param y 坐标 Y
     * @param z 坐标 Z
     * @return 对应坐标的路径类型
     */
    protected PathType getVoxelPathType(int x, int y, int z) {
        return this.voxelPathTypesCache.computeIfAbsent(BlockPos.asLong(x, y, z), 
            l -> this.getPathTypeOfMob(this.currentContext, x, y, z, this.mob));
    }

    /**
     * 综合考虑实体整个包围盒范围,计算指定坐标处对该实体而言的路径类型
     *
     * @param context 寻路上下文
     * @param x       坐标 X
     * @param y       坐标 Y
     * @param z       坐标 Z
     * @param mob     进行寻路的实体
     * @return 该实体在此坐标处的路径类型
     */
    @Override
    public PathType getPathTypeOfMob(PathfindingContext context, int x, int y, int z, Mob mob) {
        Set<PathType> pathTypeWithinMobBb = this.getPathTypeWithinMobBB(context, x, y, z);
        if (pathTypeWithinMobBb.contains(PathType.FENCE)) {
            return PathType.FENCE;
        }
        if (pathTypeWithinMobBb.contains(PathType.UNPASSABLE_RAIL)) {
            return PathType.UNPASSABLE_RAIL;
        }
        PathType pathType = PathType.BLOCKED;
        for (PathType pathType1 : pathTypeWithinMobBb) {
            if (mob.getPathfindingMalus(pathType1) < 0.0F) {
                return pathType1;
            }
            if (mob.getPathfindingMalus(pathType1) >= mob.getPathfindingMalus(pathType)) {
                pathType = pathType1;
            }
        }
        return this.entityWidth <= 1 && pathType != PathType.OPEN && 
               mob.getPathfindingMalus(pathType) == 0.0F && 
               this.getPathType(context, x, y, z) == PathType.OPEN ? PathType.OPEN : pathType;
    }

    /**
     * 遍历实体包围盒范围内所有方块,收集它们各自的路径类型集合
     *
     * @param context 寻路上下文
     * @param x       坐标 X
     * @param y       坐标 Y
     * @param z       坐标 Z
     * @return 包围盒内出现的所有路径类型集合
     */
    public Set<PathType> getPathTypeWithinMobBB(PathfindingContext context, int x, int y, int z) {
        EnumSet<PathType> set = EnumSet.noneOf(PathType.class);
        for (int i = 0; i < this.entityWidth; i++) {
            for (int i1 = 0; i1 < this.entityHeight; i1++) {
                for (int i2 = 0; i2 < this.entityDepth; i2++) {
                    int i3 = i + x;
                    int i4 = i1 + y;
                    int i5 = i2 + z;
                    PathType pathType = this.getPathType(context, i3, i4, i5);
                    BlockPos blockPos = this.mob.blockPosition();
                    boolean canPassDoors = this.canPassDoors();
                    if (pathType == PathType.DOOR_WOOD_CLOSED && this.canOpenDoors() && canPassDoors) {
                        pathType = PathType.WALKABLE_DOOR;
                    }
                    if (pathType == PathType.DOOR_OPEN && !canPassDoors) {
                        pathType = PathType.BLOCKED;
                    }
                    if (pathType == PathType.RAIL
                        && this.getPathType(context, blockPos.getX(), blockPos.getY(), blockPos.getZ()) != PathType.RAIL
                        && this.getPathType(context, blockPos.getX(), blockPos.getY() - 1, blockPos.getZ()) != PathType.RAIL) {
                        pathType = PathType.UNPASSABLE_RAIL;
                    }
                    set.add(pathType);
                }
            }
        }
        return set;
    }

    /**
     * 获取单个坐标点的路径类型(不考虑实体包围盒大小)
     *
     * @param context 寻路上下文
     * @param x       坐标 X
     * @param y       坐标 Y
     * @param z       坐标 Z
     * @return 该坐标处的路径类型
     */
    @Override
    public PathType getPathType(PathfindingContext context, int x, int y, int z) {
        return getPathTypeStatic(context, new BlockPos.MutableBlockPos(x, y, z));
    }

    /**
     * 核心静态方法:计算给定坐标的路径类型
     *
     * @param context 寻路上下文
     * @param pos     待判定坐标(可变对象)
     * @return 该坐标处的路径类型
     */
    public static PathType getPathTypeStatic(PathfindingContext context, BlockPos.MutableBlockPos pos) {
        int x = pos.getX();
        int y = pos.getY();
        int z = pos.getZ();
        PathType pathTypeFromState = context.getPathTypeFromState(x, y, z);
        if (pathTypeFromState == PathType.OPEN && y >= context.level().getMinY() + 1) {
            return switch (context.getPathTypeFromState(x, y - 1, z)) {
                case OPEN, WATER, LAVA, WALKABLE -> PathType.OPEN;
                case DAMAGE_FIRE -> PathType.DAMAGE_FIRE;
                case DAMAGE_OTHER -> PathType.DAMAGE_OTHER;
                case STICKY_HONEY -> PathType.STICKY_HONEY;
                case POWDER_SNOW -> PathType.DANGER_POWDER_SNOW;
                case DAMAGE_CAUTIOUS -> PathType.DAMAGE_CAUTIOUS;
                case TRAPDOOR -> PathType.DANGER_TRAPDOOR;
                default -> checkNeighbourBlocks(context, x, y, z, PathType.WALKABLE);
            };
        }
        return pathTypeFromState;
    }

    /**
     * 检查周围3x3x3范围内是否存在危险方块
     * 参考WalkNodeEvaluator的实现,检测火焰、水域、仙人掌等危险
     *
     * @param context     寻路上下文
     * @param x           目标X坐标
     * @param y           目标Y坐标
     * @param z           目标Z坐标
     * @param defaultType 若无危险时的默认返回类型
     * @return 计算得到的路径类型
     */
    public static PathType checkNeighbourBlocks(PathfindingContext context, int x, int y, int z, PathType defaultType) {
        for (int i = -1; i <= 1; i++) {
            for (int i1 = -1; i1 <= 1; i1++) {
                for (int i2 = -1; i2 <= 1; i2++) {
                    if (i != 0 || i2 != 0) {
                        PathType pathTypeFromState = context.getPathTypeFromState(x + i, y + i1, z + i2);
                        if (pathTypeFromState == PathType.DAMAGE_OTHER) {
                            return PathType.DANGER_OTHER;
                        }

                        if (pathTypeFromState == PathType.DAMAGE_FIRE || pathTypeFromState == PathType.LAVA) {
                            return PathType.DANGER_FIRE;
                        }

                        if (pathTypeFromState == PathType.WATER) {
                            return PathType.WATER_BORDER;
                        }

                        if (pathTypeFromState == PathType.DAMAGE_CAUTIOUS) {
                            return PathType.DAMAGE_CAUTIOUS;
                        }
                    }
                }
            }
        }

        return defaultType;
    }

    /**
     * 判断给定格是否为可攀爬方块(梯子、藤蔓等). 脚手架虽属于可攀爬标签, 但实体无法像梯子一样在其中寻路, 故排除.
     *
     * @param x 方块 X 坐标
     * @param y 方块 Y 坐标
     * @param z 方块 Z 坐标
     * @return 是否可攀爬
     */
    private boolean isClimbable(int x, int y, int z) {
        BlockState state = this.currentContext.getBlockState(this.climbCheckPos.set(x, y, z));
        return state.is(BlockTags.CLIMBABLE) && !state.is(Blocks.SCAFFOLDING);
    }

    /**
     * 尝试在给定坐标生成攀爬节点.
     * 要求该格本身或其正下方为可攀爬方块, 且实体碰撞箱在该格内没有碰撞.
     * 实体在可攀爬方块中时, MoveControl 会在目标点高于自身时持续跳跃, 从而实现向上攀爬; 向下则依靠缓慢下落.
     *
     * @param x 节点 X 坐标
     * @param y 节点 Y 坐标
     * @param z 节点 Z 坐标
     * @return 攀爬节点; 若不可行则返回 null
     */
    private @Nullable Node findClimbNode(int x, int y, int z) {
        if (!this.isClimbable(x, y, z) && !this.isClimbable(x, y - 1, z)) {
            return null;
        }

        float malus = this.mob.getPathfindingMalus(PathType.WALKABLE);
        if (malus < 0.0F) {
            return null;
        }

        double halfWidth = this.mob.getBbWidth() / 2.0;
        AABB aabb = new AABB(
            x + 0.5 - halfWidth, y + 0.001, z + 0.5 - halfWidth,
            x + 0.5 + halfWidth, y + this.mob.getBbHeight() - 0.002, z + 0.5 + halfWidth
        );
        if (this.hasCollisions(aabb)) {
            return null;
        }

        Node node = this.getNode(x, y, z);
        if (node.closed) {
            return node;
        }
        node.type = PathType.WALKABLE;
        node.costMalus = Math.max(node.costMalus, malus);
        return node;
    }

    /**
     * 判断实体当前是否使用体素寻路(即其导航的节点评估器为 {@link VoxelNodeEvaluator}).
     * 感知、索敌和近战 AI 只对这类实体启用增强逻辑, 其余实体保持原版行为.
     *
     * @param mob 实体
     * @return 是否使用体素寻路
     */
    public static boolean isUsedBy(Mob mob) {
        return mob.getNavigation().getNodeEvaluator() instanceof VoxelNodeEvaluator;
    }

    /**
     * 体素视线检测: 从观察者眼睛到目标眼睛逐格遍历方块, 与原版 {@link LivingEntity#hasLineOfSight(Entity)} 相同,
     * 但玻璃({@link TransparentBlock})、玻璃板/铁栏杆({@link IronBarsBlock})与树叶视为透明.
     * 未加载区块视为遮挡, 不会触发区块加载.
     *
     * @param looker 观察者
     * @param target 目标实体
     * @return 视线是否畅通
     */
    public static boolean hasVoxelLineOfSight(LivingEntity looker, Entity target) {
        if (target.level() != looker.level()) {
            return false;
        }

        Vec3 from = looker.getEyePosition();
        Vec3 to = new Vec3(target.getX(), target.getEyeY(), target.getZ());
        if (to.distanceToSqr(from) > MAX_LINE_OF_SIGHT_DISTANCE * MAX_LINE_OF_SIGHT_DISTANCE) {
            return false;
        }

        Boolean clear = BlockGetter.traverseBlocks(from, to, looker.level(), (Level level, BlockPos pos) -> {
            BlockState state = level.getBlockStateIfLoaded(pos);
            if (state == null) {
                return Boolean.FALSE;
            }
            if (state.isAir() || isSightTransparent(state)) {
                return null;
            }
            VoxelShape shape = state.getCollisionShape(level, pos);
            return !shape.isEmpty() && shape.clip(from, to, pos) != null ? Boolean.FALSE : null;
        }, level -> Boolean.TRUE);
        return Boolean.TRUE.equals(clear);
    }

    /**
     * 判断方块是否对体素视线透明: 玻璃(含染色/遮光玻璃)、玻璃板与铁栏杆、树叶.
     *
     * @param state 方块状态
     * @return 是否透明
     */
    public static boolean isSightTransparent(BlockState state) {
        return state.getBlock() instanceof TransparentBlock
            || state.getBlock() instanceof IronBarsBlock
            || state.is(BlockTags.LEAVES);
    }
}
