package io.papermc.paper.math;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link FinePosition} 和 {@link BlockPosition} 的公共接口。
 * <p>
 * <b>在移除 Experimental 注解之前可能出现破坏性变更。</b>
 * <p>原文：
 * Common interface for {@link FinePosition} and {@link BlockPosition}.
 * <p>
 * <b>May see breaking changes until Experimental annotation is removed.</b>
 */
@ApiStatus.Experimental
@NullMarked
public interface Position {

    FinePosition FINE_ZERO = new FinePositionImpl(0, 0, 0);
    BlockPosition BLOCK_ZERO = new BlockPositionImpl(0, 0, 0);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此位置的方块 x 值
     * <p>原文：
     * Gets the block x value for this position
     *
     * @return 方块 x 值
     * <p>原文：the block x value
     */
    int blockX();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此位置的方块 y 值
     * <p>原文：
     * Gets the block y value for this position
     *
     * @return 方块 y 值
     * <p>原文：the block y value
     */
    int blockY();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此位置的方块 z 值
     * <p>原文：
     * Gets the block z value for this position
     *
     * @return 方块 z 值
     * <p>原文：the block z value
     */
    int blockZ();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此位置的 x 值
     * <p>原文：
     * Gets the x value for this position
     *
     * @return x 值
     * <p>原文：the x value
     */
    double x();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此位置的 y 值
     * <p>原文：
     * Gets the y value for this position
     *
     * @return y 值
     * <p>原文：the y value
     */
    double y();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此位置的 z 值
     * <p>原文：
     * Gets the z value for this position
     *
     * @return z 值
     * <p>原文：the z value
     */
    double z();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查此位置是否表示一个 {@link BlockPosition}
     * <p>原文：
     * Checks of this position represents a {@link BlockPosition}
     *
     * @return 如果是方块则为 true
     * <p>原文：true if block
     */
    boolean isBlock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查此位置是否表示一个 {@link FinePosition}
     * <p>原文：
     * Checks if this position represents a {@link FinePosition}
     *
     * @return 如果良好则为 true
     * <p>原文：true if fine
     */
    boolean isFine();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查此位置的每个分量是否都是有限值。
     * <p>原文：
     * Checks if each component of this position is finite.
     */
    default boolean isFinite() {
        return Double.isFinite(this.x()) && Double.isFinite(this.y()) && Double.isFinite(this.z());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回偏移指定量后的位置。
     * <p>原文：
     * Returns a position offset by the specified amounts.
     *
     * @param x 要偏移的 x 值
     * <p>原文：x value to offset
     * @param y 要偏移的 y 值
     * <p>原文：y value to offset
     * @param z 要偏移的 z 值
     * <p>原文：z value to offset
     * @return 偏移后的位置
     * <p>原文：the offset position
     */
    Position offset(int x, int y, int z);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回偏移指定量后的位置。
     * <p>原文：
     * Returns a position offset by the specified amounts.
     *
     * @param x 要偏移的 x 值
     * <p>原文：x value to offset
     * @param y 要偏移的 y 值
     * <p>原文：y value to offset
     * @param z 要偏移的 z 值
     * <p>原文：z value to offset
     * @return 偏移后的位置
     * <p>原文：the offset position
     */
    FinePosition offset(double x, double y, double z);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回位于此方块坐标所表示方块中心的新位置
     * <p>原文：
     * Returns a new position at the center of the block position this represents
     *
     * @return 新的中心位置
     * <p>原文：a new center position
     */
    @Contract(value = "-> new", pure = true)
    default FinePosition toCenter() {
        return new FinePositionImpl(this.blockX() + 0.5, this.blockY() + 0.5, this.blockZ() + 0.5);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此位置的方块坐标；如果它已是方块坐标则返回自身
     * <p>原文：
     * Returns the block position of this position
     * or itself if it already is a block position
     *
     * @return 方块坐标
     * <p>原文：the block position
     */
    @Contract(pure = true)
    BlockPosition toBlock();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将此位置转换为向量
     * <p>原文：
     * Converts this position to a vector
     *
     * @return 新的向量
     * <p>原文：a new vector
     */
    @Contract(value = "-> new", pure = true)
    default Vector toVector() {
        return new Vector(this.x(), this.y(), this.z());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在此位置以指定世界创建新的 Location 对象
     * <p>原文：
     * Creates a new location object at this position with the specified world
     *
     * @param world 位置对象所在的世界
     * <p>原文：the world for the location object
     * @return 新的位置
     * <p>原文：a new location
     */
    @Contract(value = "_ -> new", pure = true)
    default Location toLocation(final World world) {
        return new Location(world, this.x(), this.y(), this.z());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在该坐标处创建位置
     * <p>原文：
     * Creates a position at the coordinates
     *
     * @param x x 坐标
     * <p>原文：x coord
     * @param y y 坐标
     * <p>原文：y coord
     * @param z z 坐标
     * <p>原文：z coord
     * @return 具有这些坐标的位置
     * <p>原文：a position with those coords
     */
    @Contract(value = "_, _, _ -> new", pure = true)
    static BlockPosition block(final int x, final int y, final int z) {
        return new BlockPositionImpl(x, y, z);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从 Location 创建位置。
     * <p>原文：
     * Creates a position from the location.
     *
     * @param location 要复制其坐标的位置
     * <p>原文：the location to copy the position of
     * @return 该位置处的新位置
     * <p>原文：a new position at that location
     */
    @Contract(value = "_ -> new", pure = true)
    static BlockPosition block(final Location location) {
        return new BlockPositionImpl(location.getBlockX(), location.getBlockY(), location.getBlockZ());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在该坐标处创建位置
     * <p>原文：
     * Creates a position at the coordinates
     *
     * @param x x 坐标
     * <p>原文：x coord
     * @param y y 坐标
     * <p>原文：y coord
     * @param z z 坐标
     * <p>原文：z coord
     * @return 具有这些坐标的位置
     * <p>原文：a position with those coords
     */
    @Contract(value = "_, _, _ -> new", pure = true)
    static FinePosition fine(final double x, final double y, final double z) {
        return new FinePositionImpl(x, y, z);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从 Location 创建位置。
     * <p>原文：
     * Creates a position from the location.
     *
     * @param location 要复制其坐标的位置
     * <p>原文：the location to copy the position of
     * @return 该位置处的新位置
     * <p>原文：a new position at that location
     */
    @Contract(value = "_ -> new", pure = true)
    static FinePosition fine(final Location location) {
        return new FinePositionImpl(location.getX(), location.getY(), location.getZ());
    }
}
