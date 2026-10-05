package io.papermc.paper.command.brigadier.argument.position;

import io.papermc.paper.math.FinePosition;
import io.papermc.paper.math.Position;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 不含 y 值的精确坐标。
 * <p>原文：
 * A fine position, which does not hold a y value.
 */
@ApiStatus.Experimental
@NullMarked
public interface ColumnFinePosition {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此位置的 X 坐标。
     * <p>原文：
     * Gets the X-coordinate of this position.
     *
     * @return double 类型的 X 坐标
     * <p>原文：the X-coordinate as a double
     */
    double x();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此位置的 Z 坐标。
     * <p>原文：
     * Gets the Z-coordinate of this position.
     *
     * @return double 类型的 Z 坐标
     * <p>原文：the Z-coordinate as a double
     */
    double z();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 通过提供 Y 坐标，将这个基于列的位置转换为完整的三维 {@link FinePosition}。
     * <p>原文：
     * Converts this column-based position into a full 3D {@link FinePosition}
     * by supplying a Y-coordinate.
     *
     * @param y 新位置中要包含的 Y 坐标
     * <p>原文：the Y-coordinate to include in the new position
     * @return 表示完整三维位置的 {@link FinePosition}
     * <p>原文：a {@link FinePosition} representing the full 3D position
     */
    default FinePosition toPosition(final double y) {
        return Position.fine(this.x(), y, this.z());
    }
}
