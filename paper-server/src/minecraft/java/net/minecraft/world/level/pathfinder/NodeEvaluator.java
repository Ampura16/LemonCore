package net.minecraft.world.level.pathfinder;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.PathNavigationRegion;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.state.BlockState;

/**
 * 所有节点评估器（Node Evaluator）的抽象基类,是寻路系统中连接
 * {@link PathFinder}
 * 与具体世界方块判定逻辑的核心组件.
 * 不同的子类（如
 * {@link WalkNodeEvaluator}
 * 、
 * {@link SwimNodeEvaluator}
 * 、
 * {@link FlyNodeEvaluator}
 * 、
 * {@link AmphibiousNodeEvaluator}
 * ）针对不同的
 * 移动方式（陆地行走、游泳、飞行、两栖）实现具体的起点计算、
 * 邻居生成以及路径类型判定逻辑.
 *
 * @author DELL
 * @date 2026/09/20
 */
public abstract class NodeEvaluator {
    /** 当前寻路任务的上下文,提供方块状态与路径类型缓存查询. */
    protected PathfindingContext currentContext;
    /** 当前正在进行寻路的实体. */
    protected Mob mob;
    /** 按坐标哈希缓存的节点集合,保证同一坐标复用同一个 {@link Node} 实例. */
    protected final Int2ObjectMap<Node> nodes = new Int2ObjectOpenHashMap<>();
    /** 实体包围盒对应的方块宽度（向上取整）,用于遍历实体占用的方块范围. */
    protected int entityWidth;
    /** 实体包围盒对应的方块高度（向上取整）. */
    protected int entityHeight;
    /** 实体包围盒对应的方块深度（向上取整,通常与宽度相同）. */
    protected int entityDepth;
    /** 实体是否能够通过门（即使门是关闭状态也能视为可通行）.默认允许. */
    protected boolean canPassDoors = true;
    /** 实体是否能够主动打开门. */
    protected boolean canOpenDoors;
    /** 实体是否能够漂浮在水面（而非直接沉底寻路）. */
    protected boolean canFloat;
    /** 实体是否能够直接跨越/走上栅栏. */
    protected boolean canWalkOverFences;

    /**
     * 准备一次寻路任务:构造寻路上下文,记录当前实体,清空节点缓存,
     * 并根据实体包围盒尺寸计算出遍历所需的宽度、高度、深度.
     *
     * @param level 寻路所在的区域
     * @param mob   进行寻路的实体
     */
    public void prepare(PathNavigationRegion level, Mob mob) {
        this.currentContext = new PathfindingContext(level, mob);
        this.mob = mob;
        this.nodes.clear();
        this.entityWidth = Mth.floor(mob.getBbWidth() + 1.0F);
        this.entityHeight = Mth.floor(mob.getBbHeight() + 1.0F);
        this.entityDepth = Mth.floor(mob.getBbWidth() + 1.0F);
    }

    /**
     * 结束一次寻路任务,清空上下文与实体引用,释放引用以便垃圾回收.
     */
    public void done() {
        this.currentContext = null;
        this.mob = null;
    }

    /**
     * 根据方块坐标获取（或创建）对应的节点.
     *
     * @param pos 方块坐标
     * @return 对应节点
     */
    protected Node getNode(BlockPos pos) {
        return this.getNode(pos.getX(), pos.getY(), pos.getZ());
    }

    /**
     * 根据坐标获取（或创建）对应的节点,节点按坐标哈希缓存于 {@link #nodes},
     * 保证同一坐标始终复用同一个 {@link Node} 实例.
     *
     * @param x 坐标 X
     * @param y 坐标 Y
     * @param z 坐标 Z
     * @return 对应节点
     */
    protected Node getNode(int x, int y, int z) {
        return this.nodes.computeIfAbsent(Node.createHash(x, y, z), key -> new Node(x, y, z));
    }

    /**
     * 计算并返回寻路的起始节点,由具体子类根据实体所处环境（陆地/水/空中）实现.
     *
     * @return 起始节点
     */
    public abstract Node getStart();

    /**
     * 根据目标世界坐标获取对应的目标节点,由具体子类实现.
     *
     * @param x 目标 X 坐标
     * @param y 目标 Y 坐标
     * @param z 目标 Z 坐标
     * @return 目标节点
     */
    public abstract Target getTarget(double x, double y, double z);

    /**
     * 将连续坐标向下取整为方块坐标,并据此构造一个目标节点 {@link Target}.
     * 供各子类的 {@link #getTarget} 实现复用.
     *
     * @param x 目标 X 坐标
     * @param y 目标 Y 坐标
     * @param z 目标 Z 坐标
     * @return 对应的目标节点
     */
    protected Target getTargetNodeAt(double x, double y, double z) {
        return new Target(this.getNode(Mth.floor(x), Mth.floor(y), Mth.floor(z)));
    }

    /**
     * 计算给定节点的所有可行邻居节点,写入 {@code outputArray} 并返回数量,
     * 由具体子类根据移动方式（陆地/水中/飞行）实现.
     *
     * @param outputArray 用于接收邻居节点的输出数组
     * @param node        当前节点
     * @return 有效邻居的数量
     */
    public abstract int getNeighbors(Node[] outputArray, Node node);

    /**
     * 综合考虑实体整个包围盒范围,计算指定坐标处对该实体而言的路径类型,
     * 由具体子类实现.
     *
     * @param context 寻路上下文
     * @param x       坐标 X
     * @param y       坐标 Y
     * @param z       坐标 Z
     * @param mob     进行寻路的实体
     * @return 该实体在此坐标处的路径类型
     */
    public abstract PathType getPathTypeOfMob(PathfindingContext context, int x, int y, int z, Mob mob);

    /**
     * 获取单个坐标点（不考虑实体包围盒）的路径类型,由具体子类实现.
     *
     * @param context 寻路上下文
     * @param x       坐标 X
     * @param y       坐标 Y
     * @param z       坐标 Z
     * @return 该坐标处的路径类型
     */
    public abstract PathType getPathType(PathfindingContext context, int x, int y, int z);

    /**
     * 便捷方法:根据实体与方块坐标,构造一个临时的 {@link PathfindingContext},
     * 再计算该坐标处的路径类型.
     *
     * @param mob 进行判定的实体
     * @param pos 待判定坐标
     * @return 该坐标处的路径类型
     */
    public PathType getPathType(Mob mob, BlockPos pos) {
        return this.getPathType(new PathfindingContext(mob.level(), mob), pos.getX(), pos.getY(), pos.getZ());
    }

    /**
     * 设置实体是否能够通过门.
     *
     * @param canPassDoors 是否能通过门
     */
    public void setCanPassDoors(boolean canPassDoors) {
        this.canPassDoors = canPassDoors;
    }

    /**
     * 设置实体是否能够主动打开门.
     *
     * @param canOpenDoors 是否能开门
     */
    public void setCanOpenDoors(boolean canOpenDoors) {
        this.canOpenDoors = canOpenDoors;
    }

    /**
     * 设置实体是否能够漂浮.
     *
     * @param canFloat 是否能漂浮
     */
    public void setCanFloat(boolean canFloat) {
        this.canFloat = canFloat;
    }

    /**
     * 设置实体是否能够跨越栅栏.
     *
     * @param canWalkOverFences 是否能跨越栅栏
     */
    public void setCanWalkOverFences(boolean canWalkOverFences) {
        this.canWalkOverFences = canWalkOverFences;
    }

    /**
     * 获取实体是否能够通过门.
     *
     * @return 是否能通过门
     */
    public boolean canPassDoors() {
        return this.canPassDoors;
    }

    /**
     * 获取实体是否能够主动打开门.
     *
     * @return 是否能开门
     */
    public boolean canOpenDoors() {
        return this.canOpenDoors;
    }

    /**
     * 获取实体是否能够漂浮.
     *
     * @return 是否能漂浮
     */
    public boolean canFloat() {
        return this.canFloat;
    }

    /**
     * 获取实体是否能够跨越栅栏.
     *
     * @return 是否能跨越栅栏
     */
    public boolean canWalkOverFences() {
        return this.canWalkOverFences;
    }

    /**
     * 判断给定方块状态是否属于"燃烧方块"（会造成火焰伤害的方块）,
     * 包括火焰、岩浆、岩浆块、点燃的营火以及岩浆炼药锅.
     * 该方法供 {@link WalkNodeEvaluator#getPathTypeFromState} 用于判定
     * {@link PathType#DAMAGE_FIRE} 类型.
     *
     * @param state 待判断的方块状态
     * @return 是否为燃烧方块
     */
    public static boolean isBurningBlock(BlockState state) {
        return state.is(BlockTags.FIRE)
            || state.is(Blocks.LAVA)
            || state.is(Blocks.MAGMA_BLOCK)
            || CampfireBlock.isLitCampfire(state)
            || state.is(Blocks.LAVA_CAULDRON);
    }
}
