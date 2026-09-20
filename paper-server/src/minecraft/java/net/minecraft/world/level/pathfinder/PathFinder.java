package net.minecraft.world.level.pathfinder;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Function;
import java.util.stream.Collectors;
import net.minecraft.core.BlockPos;
import net.minecraft.util.profiling.Profiler;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.util.profiling.metrics.MetricCategory;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.level.PathNavigationRegion;
import org.jspecify.annotations.Nullable;

/**
 * A* 寻路算法的核心实现类.
 * 负责在给定的
 * {@link NodeEvaluator}
 * （节点评估器）的支持下,
 * 从起点出发搜索到达一组目标位置的最优路径
 * {@link Path}
 * .
 *
 * @author DELL
 * @date 2026/09/20
 */
public class PathFinder {
    /** 启发式函数（h 值）的放大系数,用于加速搜索（贪心倾向）. */
    private static final float FUDGING = 1.5F;
    /** 用于存放当前节点邻居的可复用数组,避免频繁分配. */
    private final Node[] neighbors = new Node[32];
    /** 单次搜索允许访问的最大节点数量,超过则终止搜索并返回当前最优结果. */
    private int maxVisitedNodes;
    /** 节点评估器,决定如何生成起点、目标以及邻居节点. */
    public final NodeEvaluator nodeEvaluator;
    /** 用于 A* 搜索的开放集（最小堆）,按节点的 f 值排序. */
    private final BinaryHeap openSet = new BinaryHeap();
    /** 是否捕获调试数据的开关,默认关闭. */
    private BooleanSupplier captureDebug = () -> false;

    /**
     * 构造一个寻路器.
     *
     * @param nodeEvaluator   节点评估器
     * @param maxVisitedNodes 最大访问节点数
     */
    public PathFinder(NodeEvaluator nodeEvaluator, int maxVisitedNodes) {
        this.nodeEvaluator = nodeEvaluator;
        this.maxVisitedNodes = maxVisitedNodes;
    }

    /**
     * 设置是否捕获调试数据的判断逻辑（例如是否有调试订阅者）.
     *
     * @param captureDebug 返回是否应捕获调试数据的布尔提供者
     */
    public void setCaptureDebug(BooleanSupplier captureDebug) {
        this.captureDebug = captureDebug;
    }

    /**
     * 设置单次搜索允许访问的最大节点数量.
     *
     * @param maxVisitedNodes 最大访问节点数
     */
    public void setMaxVisitedNodes(int maxVisitedNodes) {
        this.maxVisitedNodes = maxVisitedNodes;
    }

    /**
     * 在指定区域内为实体计算一条通往目标集合中某个位置的路径.
     * 会先准备节点评估器、获取起点,并将目标坐标集合转换为
     * {@code Target -> BlockPos} 的条目列表后交给内部重载方法处理.
     *
     * @param region                    寻路所在的区域上下文
     * @param mob                       进行寻路的实体
     * @param targets                   候选目标方块坐标集合
     * @param maxRange                  搜索允许的最大距离
     * @param reachRange                判定“到达目标”的距离阈值
     * @param maxVisitedNodesMultiplier 最大访问节点数的乘数
     * @return 计算得到的路径;若无法获取起点则返回 null
     */
    public @Nullable Path findPath(PathNavigationRegion region, Mob mob, Set<BlockPos> targets, float maxRange, int reachRange, float maxVisitedNodesMultiplier) {
        this.openSet.clear();
        this.nodeEvaluator.prepare(region, mob);
        Node start = this.nodeEvaluator.getStart();
        if (start == null) {
            return null;
        } else {
            // Paper start - Perf: remove streams and optimize collection
            List<Map.Entry<Target, BlockPos>> map = Lists.newArrayList();
            for (BlockPos pos : targets) {
                map.add(new java.util.AbstractMap.SimpleEntry<>(this.nodeEvaluator.getTarget(pos.getX(), pos.getY(), pos.getZ()), pos));
            }
            // Paper end - Perf: remove streams and optimize collection
            Path path = this.findPath(start, map, maxRange, reachRange, maxVisitedNodesMultiplier);
            this.nodeEvaluator.done();
            return path;
        }
    }

    /**
     * 执行 A* 搜索算法的核心逻辑.
     * 从起始节点开始,不断从开放集中取出 f 值最小的节点进行扩展,
     * 直到找到足够接近某个目标（在 {@code reachRange} 范围内）的节点,
     * 或超出最大访问节点数 / 最大搜索范围为止.
     * 搜索结束后,从已知的目标节点（或所有候选目标节点）中重建并选出最优路径.
     *
     * @param node                      起始节点
     * @param positions                 目标节点与其原始方块坐标的条目列表
     * @param maxRange                  搜索允许的最大距离
     * @param reachRange                判定“到达目标”的距离阈值
     * @param maxVisitedNodesMultiplier 最大访问节点数的乘数
     * @return 计算得到的最优路径;若没有任何可行路径则返回 null
     */
    private @Nullable Path findPath(Node node, List<Map.Entry<Target, BlockPos>> positions, float maxRange, int reachRange, float maxVisitedNodesMultiplier) { // Paper - optimize collection
        ProfilerFiller profilerFiller = Profiler.get();
        profilerFiller.push("find_path");
        profilerFiller.markForCharting(MetricCategory.PATH_FINDING);
        // Set<Target> set = targetPositions.keySet(); // Paper - unused
        node.g = 0.0F;
        node.h = this.getBestH(node, positions); // Paper - optimize collection
        node.f = node.h;
        this.openSet.clear();
        this.openSet.insert(node);
        boolean asBoolean = this.captureDebug.getAsBoolean();
        Set<Node> set1 = asBoolean ? new HashSet<>() : Set.of();
        int i = 0;
        List<Map.Entry<Target, BlockPos>> entryList = Lists.newArrayListWithExpectedSize(positions.size()); // Paper - optimize collection
        int i1 = (int)(this.maxVisitedNodes * maxVisitedNodesMultiplier);

        while (!this.openSet.isEmpty()) {
            if (++i >= i1) {
                break;
            }

            Node node1 = this.openSet.pop();
            node1.closed = true;

            // Paper start - optimize collection
            for (int positionIndex = 0, size = positions.size(); positionIndex < size; positionIndex++) {
                final Map.Entry<Target, BlockPos> entry = positions.get(positionIndex);
                Target target = entry.getKey();
                if (node1.distanceManhattan(target) <= reachRange) {
                    target.setReached();
                    entryList.add(entry);
                    // Paper end - Perf: remove streams and optimize collection
                }
            }

            if (!entryList.isEmpty()) { // Paper - Perf: remove streams and optimize collection; rename
                break;
            }

            if (asBoolean) {
                set1.add(node1);
            }

            if (!(node1.distanceTo(node) >= maxRange)) {
                int neighbors = this.nodeEvaluator.getNeighbors(this.neighbors, node1);

                for (int i2 = 0; i2 < neighbors; i2++) {
                    Node node2 = this.neighbors[i2];
                    float f = this.distance(node1, node2);
                    node2.walkedDistance = node1.walkedDistance + f;
                    float f1 = node1.g + f + node2.costMalus;
                    if (node2.walkedDistance < maxRange && (!node2.inOpenSet() || f1 < node2.g)) {
                        node2.cameFrom = node1;
                        node2.g = f1;
                        node2.h = this.getBestH(node2, positions) * 1.5F; // Paper - Perf: remove streams and optimize collection
                        if (node2.inOpenSet()) {
                            this.openSet.changeCost(node2, node2.g + node2.h);
                        } else {
                            node2.f = node2.g + node2.h;
                            this.openSet.insert(node2);
                        }
                    }
                }
            }
        }

        // Paper start - Perf: remove streams and optimize collection
        Path best = null;
        boolean entryListIsEmpty = entryList.isEmpty();
        Comparator<Path> comparator = entryListIsEmpty
            ? Comparator.comparingInt(Path::getNodeCount)
            : Comparator.comparingDouble(Path::getDistToTarget).thenComparingInt(Path::getNodeCount);
        for (Map.Entry<Target, BlockPos> entry : entryListIsEmpty ? positions : entryList) {
            Path path = this.reconstructPath(entry.getKey().getBestNode(), entry.getValue(), !entryListIsEmpty);
            if (best == null || comparator.compare(path, best) < 0) {
                best = path;
            }
        }
        profilerFiller.pop();
        if(asBoolean && best != null) {
            Set<Target> set = Sets.newHashSet();
            for(Map.Entry<Target, BlockPos> entry : positions) {
                set.add(entry.getKey());
            }
            best.setDebug(this.openSet.getHeap(), set1.toArray(Node[]::new), set);
        }
        return best;
        // Paper end - Perf: remove streams and optimize collection
    }

    /**
     * 计算两个节点之间的实际移动代价（距离）.
     * 可被子类覆写以实现不同的距离度量方式.
     *
     * @param first  起始节点
     * @param second 目标节点
     * @return 两节点间的距离
     */
    protected float distance(Node first, Node second) {
        return first.distanceTo(second);
    }

    /**
     * 计算给定节点到所有候选目标中最优（最近）目标的启发式距离（h 值）,
     * 同时更新每个目标记录的“最优前驱节点”信息,供后续路径重建使用.
     *
     * @param node    当前节点
     * @param targets 候选目标条目列表
     * @return 到最近目标的距离
     */
    private float getBestH(Node node, List<Map.Entry<Target, BlockPos>> targets) { // Paper - Perf: remove streams and optimize collection; Set<Target> -> List<Map.Entry<Target, BlockPos>>
        float f = Float.MAX_VALUE;

        // Paper start - Perf: remove streams and optimize collection
        for (int i = 0, targetsSize = targets.size(); i < targetsSize; i++) {
            final Target target = targets.get(i).getKey();
            // Paper end - Perf: remove streams and optimize collection
            float f1 = node.distanceTo(target);
            target.updateBest(f1, node);
            f = Math.min(f1, f);
        }

        return f;
    }

    /**
     * 根据某个节点的 {@code cameFrom} 链,从该节点回溯到起点,
     * 重建出一条完整的路径节点列表,并据此构造 {@link Path} 对象.
     * 该方法在 {@link #findPath(Node, List, float, int, float)} 中被调用,
     * 用于将搜索结束后得到的目标节点（或最优候选节点）转换为最终路径结果.
     *
     * @param node          回溯的起始节点（通常是某个目标的最优前驱节点,
     *                      即 {@code Target.getBestNode()}）
     * @param targetPos     该路径对应的原始目标方块坐标
     * @param reachesTarget 是否实际到达了目标（而非仅仅是距离最近的候选点）
     * @return 由起点到 {@code node} 的完整路径
     */
    private Path reconstructPath(Node node, BlockPos targetPos, boolean reachesTarget) {
        List<Node> list = Lists.newArrayList();
        Node node1 = node;
        list.add(0, node);

        while (node1.cameFrom != null) {
            node1 = node1.cameFrom;
            list.add(0, node1);
        }

        return new Path(list, targetPos, reachesTarget);
    }
}
