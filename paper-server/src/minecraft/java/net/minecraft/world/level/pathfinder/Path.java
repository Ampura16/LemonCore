package net.minecraft.world.level.pathfinder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.util.VisibleForDebug;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

/**
 * 表示一条由寻路器（PathFinder）计算得到的路径.
 * 路径由一系列 {@link Node} 组成,实体沿路径节点依次移动到目标点 {@link #target}.
 */
public final class Path {
    /** 用于网络序列化/反序列化 {@link Path} 的编解码器. */
    public static final StreamCodec<FriendlyByteBuf, Path> STREAM_CODEC = StreamCodec.of((buffer, value) -> value.writeToStream(buffer), Path::createFromStream);
    /** 组成该路径的节点列表,按从起点到终点的顺序排列. */
    public final List<Node> nodes;
    /** 调试信息（仅在调试模式下生成）,包含开放集、闭合集与目标节点集合. */
    private Path.@Nullable DebugData debugData;
    /** 当前实体即将移动到的下一个节点在 {@link #nodes} 中的索引. */
    private int nextNodeIndex;
    /** 路径最终要到达的目标方块坐标. */
    private final BlockPos target;
    /** 路径终点到目标点的曼哈顿距离,若路径为空则为 {@link Float#MAX_VALUE}. */
    private final float distToTarget;
    /** 该路径是否成功到达（reach）目标点. */
    private final boolean reached;

    /**
     * 构造一条路径.
     *
     * @param nodes   路径节点列表
     * @param target  目标方块坐标
     * @param reached 是否已到达目标
     */
    public Path(List<Node> nodes, BlockPos target, boolean reached) {
        this.nodes = nodes;
        this.target = target;
        this.distToTarget = nodes.isEmpty() ? Float.MAX_VALUE : this.nodes.get(this.nodes.size() - 1).distanceManhattan(this.target);
        this.reached = reached;
    }

    /**
     * 将当前节点索引前进一位,表示实体已经到达当前节点,准备前往下一个节点.
     */
    public void advance() {
        this.nextNodeIndex++;
    }

    /**
     * 判断路径是否尚未开始移动.
     *
     * @return 若下一节点索引小于等于 0,返回 true
     */
    public boolean notStarted() {
        return this.nextNodeIndex <= 0;
    }

    /**
     * 判断路径是否已经走完.
     *
     * @return 若下一节点索引超出节点列表范围,返回 true
     */
    public boolean isDone() {
        return this.nextNodeIndex >= this.nodes.size();
    }

    /**
     * 获取路径的终点节点.
     *
     * @return 最后一个节点,若路径为空则返回 null
     */
    public @Nullable Node getEndNode() {
        return !this.nodes.isEmpty() ? this.nodes.get(this.nodes.size() - 1) : null;
    }

    /**
     * 根据索引获取指定节点.
     *
     * @param index 节点索引
     * @return 对应的节点
     */
    public Node getNode(int index) {
        return this.nodes.get(index);
    }

    /**
     * 将节点列表截断到指定长度,多余的节点将被移除.
     *
     * @param length 保留的节点数量
     */
    public void truncateNodes(int length) {
        if (this.nodes.size() > length) {
            this.nodes.subList(length, this.nodes.size()).clear();
        }
    }

    /**
     * 替换指定索引处的节点.
     *
     * @param index 要替换的位置
     * @param node  新的节点
     */
    public void replaceNode(int index, Node node) {
        this.nodes.set(index, node);
    }

    /**
     * 获取路径包含的节点总数.
     *
     * @return 节点数量
     */
    public int getNodeCount() {
        return this.nodes.size();
    }

    /**
     * 获取下一个待前往节点的索引.
     *
     * @return 下一节点索引
     */
    public int getNextNodeIndex() {
        return this.nextNodeIndex;
    }

    /**
     * 设置下一个待前往节点的索引（用于恢复/跳转路径进度）.
     *
     * @param currentIndex 新的下一节点索引
     */
    public void setNextNodeIndex(int currentIndex) {
        this.nextNodeIndex = currentIndex;
    }

    /**
     * 计算实体在指定节点处应处于的世界坐标（考虑实体宽度进行居中偏移）.
     *
     * @param entity 目标实体
     * @param index  节点索引
     * @return 实体在该节点处的位置
     */
    public Vec3 getEntityPosAtNode(Entity entity, int index) {
        Node node = this.nodes.get(index);
        double d = node.x + (int)(entity.getBbWidth() + 1.0F) * 0.5;
        double d1 = node.y;
        double d2 = node.z + (int)(entity.getBbWidth() + 1.0F) * 0.5;
        return new Vec3(d, d1, d2);
    }

    /**
     * 获取指定索引节点对应的方块坐标.
     *
     * @param index 节点索引
     * @return 该节点的方块坐标
     */
    public BlockPos getNodePos(int index) {
        return this.nodes.get(index).asBlockPos();
    }

    /**
     * 获取实体在下一节点处应处于的世界坐标.
     *
     * @param entity 目标实体
     * @return 下一节点处的位置
     */
    public Vec3 getNextEntityPos(Entity entity) {
        return this.getEntityPosAtNode(entity, this.nextNodeIndex);
    }

    /**
     * 获取下一个节点对应的方块坐标.
     *
     * @return 下一节点的方块坐标
     */
    public BlockPos getNextNodePos() {
        return this.nodes.get(this.nextNodeIndex).asBlockPos();
    }

    /**
     * 获取下一个待前往的节点.
     *
     * @return 下一个节点
     */
    public Node getNextNode() {
        return this.nodes.get(this.nextNodeIndex);
    }

    /**
     * 获取上一个已经过的节点.
     *
     * @return 上一节点,若尚未移动过则返回 null
     */
    public @Nullable Node getPreviousNode() {
        return this.nextNodeIndex > 0 ? this.nodes.get(this.nextNodeIndex - 1) : null;
    }

    /**
     * 判断两条路径的节点序列是否相同.
     *
     * @param path 待比较的路径,可为 null
     * @return 若节点序列相等则返回 true
     */
    public boolean sameAs(@Nullable Path path) {
        return path != null && this.nodes.equals(path.nodes);
    }

    /**
     * 判断两个 Path 对象是否完全相等（包括进度、调试数据、目标等所有字段）.
     */
    @Override
    public boolean equals(Object other) {
        return other instanceof Path path
            && this.nextNodeIndex == path.nextNodeIndex
            && this.debugData == path.debugData
            && this.reached == path.reached
            && this.target.equals(path.target)
            && this.nodes.equals(path.nodes);
    }

    /**
     * 计算哈希值,基于下一节点索引与节点列表内容.
     */
    @Override
    public int hashCode() {
        return this.nextNodeIndex + this.nodes.hashCode() * 31;
    }

    /**
     * 判断路径是否成功到达了目标点.
     *
     * @return 是否到达目标
     */
    public boolean canReach() {
        return this.reached;
    }

    /**
     * 设置调试数据（仅用于调试可视化）,包含开放集、闭合集与目标节点集合.
     *
     * @param openSet    寻路结束时仍在开放集中的节点
     * @param closedSet  已处理（关闭）的节点
     * @param targetNodes 目标节点集合
     */
    @VisibleForDebug
    void setDebug(Node[] openSet, Node[] closedSet, Set<Target> targetNodes) {
        this.debugData = new Path.DebugData(openSet, closedSet, targetNodes);
    }

    /**
     * 获取该路径的调试数据.
     *
     * @return 调试数据,若未启用调试则为 null
     */
    public Path.@Nullable DebugData debugData() {
        return this.debugData;
    }

    /**
     * 将路径写入网络字节缓冲区（用于调试路径的网络同步,要求必须存在调试数据）.
     *
     * @param buffer 目标缓冲区
     * @throws IllegalStateException 若缺少调试数据
     */
    public void writeToStream(FriendlyByteBuf buffer) {
        if (this.debugData != null && !this.debugData.targetNodes.isEmpty()) {
            buffer.writeBoolean(this.reached);
            buffer.writeInt(this.nextNodeIndex);
            buffer.writeBlockPos(this.target);
            buffer.writeCollection(this.nodes, (buffer1, value) -> value.writeToStream(buffer1));
            this.debugData.write(buffer);
        } else {
            throw new IllegalStateException("Missing debug data");
        }
    }

    /**
     * 从网络字节缓冲区中读取并重建一条路径（含调试数据）.
     *
     * @param buffer 数据来源缓冲区
     * @return 重建的 Path 对象
     */
    public static Path createFromStream(FriendlyByteBuf buffer) {
        boolean _boolean = buffer.readBoolean();
        int _int = buffer.readInt();
        BlockPos blockPos = buffer.readBlockPos();
        List<Node> list = buffer.readList(Node::createFromStream);
        Path.DebugData debugData = Path.DebugData.read(buffer);
        Path path = new Path(list, blockPos, _boolean);
        path.debugData = debugData;
        path.nextNodeIndex = _int;
        return path;
    }

    /**
     * 返回该路径的字符串表示,仅包含节点数量.
     */
    @Override
    public String toString() {
        return "Path(length=" + this.nodes.size() + ")";
    }

    /**
     * 获取该路径的目标方块坐标.
     *
     * @return 目标坐标
     */
    public BlockPos getTarget() {
        return this.target;
    }

    /**
     * 获取路径终点到目标点的曼哈顿距离.
     *
     * @return 距离目标点的曼哈顿距离,若路径为空则为 {@link Float#MAX_VALUE}
     */
    public float getDistToTarget() {
        return this.distToTarget;
    }

    /**
     * 从缓冲区读取一个节点数组（用于反序列化调试数据中的开放集/闭合集）.
     *
     * @param buffer 数据来源缓冲区
     * @return 读取到的节点数组
     */
    static Node[] readNodeArray(FriendlyByteBuf buffer) {
        Node[] nodes = new Node[buffer.readVarInt()];

        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = Node.createFromStream(buffer);
        }

        return nodes;
    }

    /**
     * 将一个节点数组写入缓冲区（用于序列化调试数据中的开放集/闭合集）.
     *
     * @param buffer    目标缓冲区
     * @param nodeArray 待写入的节点数组
     */
    static void writeNodeArray(FriendlyByteBuf buffer, Node[] nodeArray) {
        buffer.writeVarInt(nodeArray.length);

        for (Node node : nodeArray) {
            node.writeToStream(buffer);
        }
    }

    /**
     * 复制当前路径,生成一个新的 Path 对象（共享同一个节点列表引用）,
     * 并保留调试数据与当前的移动进度.
     *
     * @return 复制得到的新 Path 对象
     */
    public Path copy() {
        Path path = new Path(this.nodes, this.target, this.reached);
        path.debugData = this.debugData;
        path.nextNodeIndex = this.nextNodeIndex;
        return path;
    }

    /**
     * 寻路调试数据,记录 A* 搜索过程中的开放集、闭合集与目标节点集合,
     * 仅在调试可视化场景下使用（见 {@link #setDebug}）.
     *
     * @param openSet     搜索结束时仍处于开放集中的节点
     * @param closedSet   已处理（关闭）的节点
     * @param targetNodes 目标节点集合
     */
    public record DebugData(Node[] openSet, Node[] closedSet, Set<Target> targetNodes) {
        /**
         * 将调试数据写入网络字节缓冲区.
         *
         * @param buffer 目标缓冲区
         */
        public void write(FriendlyByteBuf buffer) {
            buffer.writeCollection(this.targetNodes, (buffer1, value) -> value.writeToStream(buffer1));
            Path.writeNodeArray(buffer, this.openSet);
            Path.writeNodeArray(buffer, this.closedSet);
        }

        /**
         * 从网络字节缓冲区中读取并重建调试数据.
         *
         * @param buffer 数据来源缓冲区
         * @return 重建的 DebugData 对象
         */
        public static Path.DebugData read(FriendlyByteBuf buffer) {
            HashSet<Target> set = buffer.readCollection(HashSet::new, Target::createFromStream);
            Node[] nodeArray = Path.readNodeArray(buffer);
            Node[] nodeArray1 = Path.readNodeArray(buffer);
            return new Path.DebugData(nodeArray, nodeArray1, set);
        }
    }
}
