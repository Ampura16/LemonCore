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
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.PathNavigationRegion;
import net.minecraft.world.level.block.BaseRailBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoorBlock;
import net.minecraft.world.level.block.FenceGateBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jspecify.annotations.Nullable;

/**
 * 陆地行走型节点评估器,是最常用的 {@link NodeEvaluator} 实现,
 * 供 {@link net.minecraft.world.entity.ai.navigation.GroundPathNavigation} 使用,
 * 用于判断实体在陆地上寻路时各方块的可通行性、危险性以及邻居节点的生成逻辑.
 * {@link AmphibiousNodeEvaluator}、{@link FlyNodeEvaluator} 均继承自本类,
 * 复用其大部分基础逻辑并做特化扩展.
 */
public class WalkNodeEvaluator extends NodeEvaluator {
    /** 判断栅栏柱之间是否留有可通行缝隙的间距阈值. */
    public static final double SPACE_BETWEEN_WALL_POSTS = 0.5;
    /** 默认的实体跳跃高度（当 {@code mob.maxUpStep()} 更小时使用该值）. */
    private static final double DEFAULT_MOB_JUMP_HEIGHT = 1.125;
    /** 按坐标缓存的路径类型结果,避免同一次寻路中重复计算. */
    private final Long2ObjectMap<PathType> pathTypesByPosCacheByMob = new Long2ObjectOpenHashMap<>();
    /** 按包围盒缓存的碰撞检测结果. */
    private final Object2BooleanMap<AABB> collisionCache = new Object2BooleanOpenHashMap<>();
    /** 可复用的水平方向邻居节点数组,用于对角线邻居的合法性判断. */
    private final Node[] reusableNeighbors = new Node[Direction.Plane.HORIZONTAL.length()];

    /**
     * 准备一次寻路任务:调用父类准备逻辑,并通知实体寻路开始.
     *
     * @param level 寻路所在的区域
     * @param mob   进行寻路的实体
     */
    @Override
    public void prepare(PathNavigationRegion level, Mob mob) {
        super.prepare(level, mob);
        mob.onPathfindingStart();
    }

    /**
     * 结束一次寻路任务:通知实体寻路结束,并清空各类缓存.
     */
    @Override
    public void done() {
        this.mob.onPathfindingDone();
        this.pathTypesByPosCacheByMob.clear();
        this.collisionCache.clear();
        super.done();
    }

    /**
     * 计算寻路的起始节点.
     * 会根据实体当前是否在流体中、是否着地等情况,向上/向下探测出合适的起始 Y 坐标,
     * 并在实体所在方块无法作为起点时,进一步尝试实体包围盒四个角落的位置作为候选起点.
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
     * 根据方块坐标构造起始节点,并设置其路径类型与移动惩罚值.
     *
     * @param pos 起始方块坐标
     * @return 构造好的起始节点
     */
    protected Node getStartNode(BlockPos pos) {
        Node node = this.getNode(pos);
        node.type = this.getCachedPathType(node.x, node.y, node.z);
        node.costMalus = this.mob.getPathfindingMalus(node.type);
        return node;
    }

    /**
     * 判断给定位置是否可以作为寻路的起点:
     * 要求该位置的路径类型不是 {@link PathType#OPEN}（悬空）,
     * 且实体对该路径类型的惩罚值非负（即可通行）.
     *
     * @param pos 待判断的方块坐标
     * @return 是否可作为起点
     */
    protected boolean canStartAt(BlockPos pos) {
        PathType cachedPathType = this.getCachedPathType(pos.getX(), pos.getY(), pos.getZ());
        return cachedPathType != PathType.OPEN && this.mob.getPathfindingMalus(cachedPathType) >= 0.0F;
    }

    /**
     * 根据目标世界坐标获取对应的目标节点 {@link Target}.
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
     * 计算给定节点的所有可行邻居节点,包括四个水平方向的直接邻居以及四个对角线邻居,
     * 并写入 {@code outputArray} 中返回实际有效的邻居数量.
     * 对角线邻居仅在两个相邻的直角方向邻居均合法时才会被尝试（避免"穿墙抄近道"）.
     *
     * @param outputArray 用于接收邻居节点的输出数组
     * @param node        当前节点
     * @return 有效邻居的数量
     */
    @Override
    public int getNeighbors(Node[] outputArray, Node node) {
        int i = 0;
        int i1 = 0;
        PathType cachedPathType = this.getCachedPathType(node.x, node.y + 1, node.z);
        PathType cachedPathType1 = this.getCachedPathType(node.x, node.y, node.z);
        if (this.mob.getPathfindingMalus(cachedPathType) >= 0.0F && cachedPathType1 != PathType.STICKY_HONEY) {
            i1 = Mth.floor(Math.max(1.0F, this.mob.maxUpStep()));
        }

        double floorLevel = this.getFloorLevel(new BlockPos(node.x, node.y, node.z));

        for (Direction direction : Direction.Plane.HORIZONTAL) {
            Node node1 = this.findAcceptedNode(node.x + direction.getStepX(), node.y, node.z + direction.getStepZ(), i1, floorLevel, direction, cachedPathType1);
            this.reusableNeighbors[direction.get2DDataValue()] = node1;
            if (this.isNeighborValid(node1, node)) {
                outputArray[i++] = node1;
            }
        }

        for (Direction directionx : Direction.Plane.HORIZONTAL) {
            Direction clockWise = directionx.getClockWise();
            if (this.isDiagonalValid(node, this.reusableNeighbors[directionx.get2DDataValue()], this.reusableNeighbors[clockWise.get2DDataValue()])) {
                Node node2 = this.findAcceptedNode(
                    node.x + directionx.getStepX() + clockWise.getStepX(),
                    node.y,
                    node.z + directionx.getStepZ() + clockWise.getStepZ(),
                    i1,
                    floorLevel,
                    directionx,
                    cachedPathType1
                );
                if (this.isDiagonalValid(node2)) {
                    outputArray[i++] = node2;
                }
            }
        }

        return i;
    }

    /**
     * 判断一个水平方向邻居节点是否有效:
     * 非空、未被关闭,且其惩罚值非负（可通行）,
     * 或者当前节点本身的惩罚值为负（此时允许穿越危险节点以脱离危险区）.
     *
     * @param neighbor 候选邻居节点
     * @param node     当前节点
     * @return 是否有效
     */
    protected boolean isNeighborValid(@Nullable Node neighbor, Node node) {
        return neighbor != null && !neighbor.closed && (neighbor.costMalus >= 0.0F || node.costMalus < 0.0F);
    }

    /**
     * 判断沿某个对角方向移动是否合法:
     * 若两条直角边邻居中任一为 null、被关闭,或高度高于根节点（表示需要向上跳才能到达,不允许斜切）,
     * 则不合法;若两条边中任一是可通行的门（{@code WALKABLE_DOOR}）,也不允许走对角线（避免斜穿门框）;
     * 否则要求两条边邻居本身惩罚值非负（可通行）,或它们高度低于根节点（下坡）,
     * 或者两者都是栅栏且实体体宽小于 0.5（允许瘦小实体从两根栅栏间的缝隙穿过,见 {@link #SPACE_BETWEEN_WALL_POSTS}）.
     *
     * @param root  对角线移动的起始（根）节点
     * @param xNode 沿 X 方向的直角边邻居节点
     * @param zNode 沿 Z 方向的直角边邻居节点
     * @return 该对角线方向是否可以尝试
     */
    protected boolean isDiagonalValid(Node root, @Nullable Node xNode, @Nullable Node zNode) {
        if (zNode == null || xNode == null || zNode.y > root.y || xNode.y > root.y) {
            return false;
        } else if (xNode.type != PathType.WALKABLE_DOOR && zNode.type != PathType.WALKABLE_DOOR) {
            boolean flag = zNode.type == PathType.FENCE && xNode.type == PathType.FENCE && this.mob.getBbWidth() < 0.5;
            return (zNode.y < root.y || zNode.costMalus >= 0.0F || flag) && (xNode.y < root.y || xNode.costMalus >= 0.0F || flag);
        } else {
            return false;
        }
    }

    /**
     * 判断对角线目标节点本身是否有效:非空、未关闭、不是可通行门,且惩罚值非负.
     *
     * @param node 待判断的对角线目标节点
     * @return 是否有效
     */
    protected boolean isDiagonalValid(@Nullable Node node) {
        return node != null && !node.closed && node.type != PathType.WALKABLE_DOOR && node.costMalus >= 0.0F;
    }

    /**
     * 判断某种路径类型对应的方块是否只有"部分碰撞体积"（如栅栏、门）,
     * 这类方块需要额外的无碰撞检测（{@link #canReachWithoutCollision}）才能确认真正可通行.
     *
     * @param pathType 待判断的路径类型
     * @return 是否具有部分碰撞体积
     */
    private static boolean doesBlockHavePartialCollision(PathType pathType) {
        return pathType == PathType.FENCE || pathType == PathType.DOOR_WOOD_CLOSED || pathType == PathType.DOOR_IRON_CLOSED;
    }

    /**
     * 通过将实体包围盒沿直线方向逐步移动到目标节点位置,逐段检测碰撞,
     * 判断实体是否能够无碰撞地到达该节点（用于栅栏/门等部分碰撞方块的精确判定）.
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
     * 获取指定方块坐标下方地面的高度（考虑实体是否可漂浮/两栖,若在水中则取水面高度）.
     *
     * @param pos 方块坐标
     * @return 地面高度
     */
    protected double getFloorLevel(BlockPos pos) {
        BlockGetter blockGetter = this.currentContext.level();
        return (this.canFloat() || this.isAmphibious()) && blockGetter.getFluidState(pos).is(FluidTags.WATER)
            ? pos.getY() + 0.5
            : getFloorLevel(blockGetter, pos);
    }

    /**
     * 静态方法:计算给定方块下方碰撞形状的最高点作为地面高度.
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
     * 是否为两栖型评估器（默认 false,由 {@link AmphibiousNodeEvaluator} 等子类覆写）.
     *
     * @return 是否两栖
     */
    protected boolean isAmphibious() {
        return false;
    }

    /**
     * 计算并返回给定坐标处的"被接受"的邻居节点.
     * 若目标位置地面高度与当前节点地面高度差超过实体可跳跃高度则拒绝;
     * 否则先按当前坐标缓存的路径类型构造候选节点,并对部分碰撞类型（栅栏/门）做无碰撞校验;
     * 若该坐标不可直接行走,则依次尝试:向上跳一格（{@link #tryJumpOn}）、
     * 向下寻找非水方块（{@link #tryFindFirstNonWaterBelow}）、
     * 向下寻找可站立地面（{@link #tryFindFirstGroundNodeBelow}）,
     * 或对有部分碰撞的方块直接标记为关闭节点（{@link #getClosedNode}）.
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
    protected @Nullable Node findAcceptedNode(int x, int y, int z, int verticalDeltaLimit, double nodeFloorLevel, Direction direction, PathType pathType) {
        Node node = null;
        BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();
        double floorLevel = this.getFloorLevel(mutableBlockPos.set(x, y, z));
        if (floorLevel - nodeFloorLevel > this.getMobJumpHeight()) {
            return null;
        } else {
            PathType cachedPathType = this.getCachedPathType(x, y, z);
            float pathfindingMalus = this.mob.getPathfindingMalus(cachedPathType);
            if (pathfindingMalus >= 0.0F) {
                node = this.getNodeAndUpdateCostToMax(x, y, z, cachedPathType, pathfindingMalus);
            }

            if (doesBlockHavePartialCollision(pathType) && node != null && node.costMalus >= 0.0F && !this.canReachWithoutCollision(node)) {
                node = null;
            }

            if (cachedPathType != PathType.WALKABLE && (!this.isAmphibious() || cachedPathType != PathType.WATER)) {
                if ((node == null || node.costMalus < 0.0F)
                    && verticalDeltaLimit > 0
                    && (cachedPathType != PathType.FENCE || this.canWalkOverFences())
                    && cachedPathType != PathType.UNPASSABLE_RAIL
                    && cachedPathType != PathType.TRAPDOOR
                    && cachedPathType != PathType.POWDER_SNOW) {
                    node = this.tryJumpOn(x, y, z, verticalDeltaLimit, nodeFloorLevel, direction, pathType, mutableBlockPos);
                } else if (!this.isAmphibious() && cachedPathType == PathType.WATER && !this.canFloat()) {
                    node = this.tryFindFirstNonWaterBelow(x, y, z, node);
                } else if (cachedPathType == PathType.OPEN) {
                    node = this.tryFindFirstGroundNodeBelow(x, y, z);
                } else if (doesBlockHavePartialCollision(cachedPathType) && node == null) {
                    node = this.getClosedNode(x, y, z, cachedPathType);
                }

                return node;
            } else {
                return node;
            }
        }
    }

    /**
     * 获取实体的跳跃高度,取默认值 {@link #DEFAULT_MOB_JUMP_HEIGHT} 与实体 {@code maxUpStep()} 中的较大者.
     *
     * @return 跳跃高度
     */
    private double getMobJumpHeight() {
        return Math.max(1.125, (double)this.mob.maxUpStep());
    }

    /**
     * 获取（或创建）指定坐标的节点,设置其路径类型,并将惩罚值更新为已有值与新值中的较大者
     * （避免多次访问同一节点时惩罚值被意外降低）.
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
     * 获取一个被标记为完全阻塞（{@link PathType#BLOCKED}）的节点,
     * 惩罚值设为 -1.0F,表示不可通行（例如坠落距离超过实体最大摔落距离时的兜底节点）.
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
     * 获取一个被标记为"已关闭"（不再展开）的节点,用于具有部分碰撞体积的方块
     * （栅栏、木门、铁门）,使用该路径类型对应的默认惩罚值.
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
     * 尝试让实体向上跳一格以越过障碍物.
     * 会递归调用 {@link #findAcceptedNode} 检查上方一格是否可接受,
     * 若实体体宽小于 1（较窄）,还会额外检查跳跃过程中头部经过的空间（AABB）
     * 是否与方块发生碰撞,防止窄小实体在跳跃转角处卡住或撞头.
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
    private @Nullable Node tryJumpOn(
        int x, int y, int z, int verticalDeltaLimit, double nodeFloorLevel, Direction direction, PathType pathType, BlockPos.MutableBlockPos pos
    ) {
        Node node = this.findAcceptedNode(x, y + 1, z, verticalDeltaLimit - 1, nodeFloorLevel, direction, pathType);
        if (node == null) {
            return null;
        } else if (this.mob.getBbWidth() >= 1.0F) {
            return node;
        } else if (node.type != PathType.OPEN && node.type != PathType.WALKABLE) {
            return node;
        } else {
            double d = x - direction.getStepX() + 0.5;
            double d1 = z - direction.getStepZ() + 0.5;
            double d2 = this.mob.getBbWidth() / 2.0;
            AABB aabb = new AABB(
                d - d2,
                this.getFloorLevel(pos.set(d, (double)(y + 1), d1)) + 0.001,
                d1 - d2,
                d + d2,
                this.mob.getBbHeight() + this.getFloorLevel(pos.set((double)node.x, (double)node.y, (double)node.z)) - 0.002,
                d1 + d2
            );
            return this.hasCollisions(aabb) ? null : node;
        }
    }

    /**
     * 当实体不能漂浮且当前列是水体时,从当前高度向下探测,
     * 直到找到第一个非水方块为止,沿途更新经过的水层节点惩罚值.
     *
     * @param x    列坐标 X
     * @param y    起始高度（会从其下方一格开始探测）
     * @param z    列坐标 Z
     * @param node 已有的候选节点（若探测过程中未找到非水方块则原样返回）
     * @return 第一个非水方块处对应的节点（或原 node）
     */
    private @Nullable Node tryFindFirstNonWaterBelow(int x, int y, int z, @Nullable Node node) {
        y--;

        while (y > this.mob.level().getMinY()) {
            PathType cachedPathType = this.getCachedPathType(x, y, z);
            if (cachedPathType != PathType.WATER) {
                return node;
            }

            node = this.getNodeAndUpdateCostToMax(x, y, z, cachedPathType, this.mob.getPathfindingMalus(cachedPathType));
            y--;
        }

        return node;
    }

    /**
     * 当当前坐标为悬空（{@link PathType#OPEN}）时,向下探测寻找第一个可站立的地面节点,
     * 若下落高度超过实体的最大安全摔落距离 {@code mob.getMaxFallDistance()},
     * 则提前返回阻塞节点以阻止实体跳崖;若探测到的方块惩罚值为负（不可通行）,
     * 同样返回阻塞节点;若一直探测到世界底部仍未找到落脚点,也返回阻塞节点.
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

            PathType cachedPathType = this.getCachedPathType(x, i, z);
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
     * 判断给定包围盒是否与世界发生碰撞,结果按包围盒缓存于 {@link #collisionCache},
     * 避免同一次寻路中对相同包围盒重复检测.
     *
     * @param boundingBox 待检测的包围盒
     * @return 是否存在碰撞
     */
    private boolean hasCollisions(AABB boundingBox) {
        return this.collisionCache.computeIfAbsent(boundingBox, key -> !this.currentContext.level().noCollision(this.mob, boundingBox));
    }

    /**
     * 获取（并按坐标缓存）指定坐标的路径类型,供实体使用.
     * 缓存于 {@link #pathTypesByPosCacheByMob},避免同一次寻路中重复计算整个实体包围盒的路径类型.
     *
     * @param x 坐标 X
     * @param y 坐标 Y
     * @param z 坐标 Z
     * @return 对应坐标的路径类型
     */
    protected PathType getCachedPathType(int x, int y, int z) {
        return this.pathTypesByPosCacheByMob.computeIfAbsent(BlockPos.asLong(x, y, z), l -> this.getPathTypeOfMob(this.currentContext, x, y, z, this.mob));
    }

    /**
     * 综合考虑实体整个包围盒范围内所有方块的路径类型,计算出该实体在指定坐标处
     * 应采用的"综合路径类型":
     * 若包围盒内存在栅栏或不可通行铁轨,直接返回对应类型（优先阻断）;
     * 否则在包围盒内所有出现的路径类型中,选出惩罚值最高（最差）的类型作为结果
     * （若存在惩罚值为负的类型则立即返回该类型,表示完全不可通行）;
     * 最后对单格宽度实体做一个特殊修正:若综合结果惩罚值为 0 但实体所在方块本身
     * 是开放的（{@code OPEN}）,则仍返回 {@code OPEN}（避免宽度为1的实体被自身脚下以外
     * 的方块误判为不可通行）.
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
        Set<PathType> pathTypeWithinMobBb = this.getPathTypeWithinMobBB(context, x, y, z);
        if (pathTypeWithinMobBb.contains(PathType.FENCE)) {
            return PathType.FENCE;
        } else if (pathTypeWithinMobBb.contains(PathType.UNPASSABLE_RAIL)) {
            return PathType.UNPASSABLE_RAIL;
        } else {
            PathType pathType = PathType.BLOCKED;

            for (PathType pathType1 : pathTypeWithinMobBb) {
                if (mob.getPathfindingMalus(pathType1) < 0.0F) {
                    return pathType1;
                }

                if (mob.getPathfindingMalus(pathType1) >= mob.getPathfindingMalus(pathType)) {
                    pathType = pathType1;
                }
            }

            return this.entityWidth <= 1
                && pathType != PathType.OPEN
                && mob.getPathfindingMalus(pathType) == 0.0F
                && this.getPathType(context, x, y, z) == PathType.OPEN
                ? PathType.OPEN
                : pathType;
        }
    }

    /**
     * 遍历实体包围盒（{@code entityWidth} x {@code entityHeight} x {@code entityDepth}）
     * 范围内所有方块,收集它们各自的路径类型集合,并针对门、铁轨做特殊转换:
     * 关闭的木门若实体能开门且能通过门则转为 {@code WALKABLE_DOOR};
     * 已打开的门若实体不能通过门则转为 {@code BLOCKED};
     * 若实体自身所在方块及其下方均不是铁轨,则该坐标的 {@code RAIL} 会被转为
     * {@code UNPASSABLE_RAIL}（即实体无法从铁轨"跳上/跳下"到此处）.
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
     * 获取单个坐标点的路径类型（不考虑实体包围盒大小）,委托给静态方法
     * {@link #getPathTypeStatic(PathfindingContext, BlockPos.MutableBlockPos)} 计算.
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
     * 便捷静态方法:根据实体和坐标构造一个临时的 {@link PathfindingContext},
     * 再计算该坐标的路径类型.
     *
     * @param mob 进行判定的实体
     * @param pos 待判定坐标
     * @return 该坐标处的路径类型
     */
    public static PathType getPathTypeStatic(Mob mob, BlockPos pos) {
        return getPathTypeStatic(new PathfindingContext(mob.level(), mob), pos.mutable());
    }

    /**
     * 核心静态方法:计算给定坐标的路径类型.
     * 先取该坐标本身的方块状态类型;若为悬空（{@code OPEN}）且不在世界底部,
     * 则进一步依据下方方块的类型将其转换为对应的"悬空危险"变体
     * （如下方是燃烧方块→{@code DAMAGE_FIRE}、蜂蜜块→{@code STICKY_HONEY}、
     * 细雪→{@code DANGER_POWDER_SNOW}、活板门→{@code DANGER_TRAPDOOR} 等）;
     * 若下方是普通地面（{@code OPEN}/{@code WATER}/{@code LAVA}/{@code WALKABLE}）,
     * 则调用 {@link #checkNeighbourBlocks} 进一步检查周围 3x3x3 范围是否存在危险方块.
     *
     * @param context 寻路上下文
     * @param pos     待判定坐标（可变对象）
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
        } else {
            return pathTypeFromState;
        }
    }

    /**
     * 检查以给定坐标为中心的 3x3x3 范围内（不含正上/正下方,即 {@code i != 0 || i2 != 0}）
     * 是否存在危险方块,并据此返回对应的"危险提示"路径类型:
     * 邻近伤害方块→{@code DANGER_OTHER};邻近燃烧方块/岩浆→{@code DANGER_FIRE};
     * 邻近水→{@code WATER_BORDER};邻近凋零玫瑰/垂滴石类方块→{@code DAMAGE_CAUTIOUS}.
     * 若周围没有危险方块,则原样返回传入的 {@code pathType}（通常是 {@code WALKABLE}）.
     *
     * @param context  寻路上下文
     * @param x        中心坐标 X
     * @param y        中心坐标 Y
     * @param z        中心坐标 Z
     * @param pathType 若无危险时的默认返回类型
     * @return 计算得到的路径类型
     */
    public static PathType checkNeighbourBlocks(PathfindingContext context, int x, int y, int z, PathType pathType) {
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

        return pathType;
    }

    /**
     * 根据方块状态本身（不考虑上下文/实体）判断其基础路径类型.
     * 若方块未加载则直接返回 {@code BLOCKED}（Paper 补丁:避免寻路时强制加载区块,
     * 见 {@code WalkNodeEvaluator.java.patch}）.
     * 依次判断:空气→{@code OPEN};活板门/荷叶/大型垂滴叶→{@code TRAPDOOR};
     * 细雪→{@code POWDER_SNOW};仙人掌/甜浆果丛→{@code DAMAGE_OTHER};
     * 蜂蜜块→{@code STICKY_HONEY};可可豆→{@code COCOA};
     * 凋零玫瑰/垂滴石→{@code DAMAGE_CAUTIOUS};
     * 岩浆→{@code LAVA};燃烧方块→{@code DAMAGE_FIRE};
     * 门→依据开关状态与材质返回 {@code DOOR_OPEN}/{@code DOOR_WOOD_CLOSED}/{@code DOOR_IRON_CLOSED};
     * 铁轨→{@code RAIL};树叶→{@code LEAVES};
     * 栅栏/墙/关闭的栅栏门→{@code FENCE};
     * 其余方块若不可寻路则→{@code BLOCKED},否则依据是否为水返回 {@code WATER}/{@code OPEN}.
     *
     * @param level 方块所在的世界
     * @param pos   方块坐标
     * @return 该方块对应的基础路径类型
     */
    protected static PathType getPathTypeFromState(BlockGetter level, BlockPos pos) {
        // Paper start - Do not load chunks during pathfinding
        BlockState blockState = level.getBlockStateIfLoaded(pos);
        if (blockState == null) {
            return PathType.BLOCKED;
        }
        // Paper end
        Block block = blockState.getBlock();
        if (blockState.isAir()) {
            return PathType.OPEN;
        } else if (blockState.is(BlockTags.TRAPDOORS) || blockState.is(Blocks.LILY_PAD) || blockState.is(Blocks.BIG_DRIPLEAF)) {
            return PathType.TRAPDOOR;
        } else if (blockState.is(Blocks.POWDER_SNOW)) {
            return PathType.POWDER_SNOW;
        } else if (blockState.is(Blocks.CACTUS) || blockState.is(Blocks.SWEET_BERRY_BUSH)) {
            return PathType.DAMAGE_OTHER;
        } else if (blockState.is(Blocks.HONEY_BLOCK)) {
            return PathType.STICKY_HONEY;
        } else if (blockState.is(Blocks.COCOA)) {
            return PathType.COCOA;
        } else if (!blockState.is(Blocks.WITHER_ROSE) && !blockState.is(Blocks.POINTED_DRIPSTONE)) {
            FluidState fluidState = blockState.getFluidState();
            if (fluidState.is(FluidTags.LAVA)) {
                return PathType.LAVA;
            } else if (isBurningBlock(blockState)) {
                return PathType.DAMAGE_FIRE;
            } else if (block instanceof DoorBlock doorBlock) {
                if (blockState.getValue(DoorBlock.OPEN)) {
                    return PathType.DOOR_OPEN;
                } else {
                    return doorBlock.type().canOpenByHand() ? PathType.DOOR_WOOD_CLOSED : PathType.DOOR_IRON_CLOSED;
                }
            } else if (block instanceof BaseRailBlock) {
                return PathType.RAIL;
            } else if (block instanceof LeavesBlock) {
                return PathType.LEAVES;
            } else if (!blockState.is(BlockTags.FENCES)
                && !blockState.is(BlockTags.WALLS)
                && (!(block instanceof FenceGateBlock) || blockState.getValue(FenceGateBlock.OPEN))) {
                if (!blockState.isPathfindable(PathComputationType.LAND)) {
                    return PathType.BLOCKED;
                } else {
                    return fluidState.is(FluidTags.WATER) ? PathType.WATER : PathType.OPEN;
                }
            } else {
                return PathType.FENCE;
            }
        } else {
            return PathType.DAMAGE_CAUTIOUS;
        }
    }
}
