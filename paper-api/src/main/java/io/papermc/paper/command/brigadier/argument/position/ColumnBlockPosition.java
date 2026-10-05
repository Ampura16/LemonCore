package io.papermc.paper.command.brigadier.argument.position;

import io.papermc.paper.math.BlockPosition;
import io.papermc.paper.math.Position;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 不含 y 值的方块坐标。
 * <p>原文：
 * A block position, which does not have a y value.
 */
@ApiStatus.Experimental
@NullMarked
public interface ColumnBlockPosition {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取方块坐标的 X 坐标。
     * <p>原文：
     * Gets the X-coordinate of the block position.
     *
     * @return 方块的 X 坐标
     * <p>原文：the block's X-coordinate
     */
    int blockX();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取方块坐标的 Z 坐标。
     * <p>原文：
     * Gets the Z-coordinate of the block position.
     *
     * @return 方块的 Z 坐标
     * <p>原文：the block's Z-coordinate
     */
    int blockZ();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 通过提供 Y 坐标，将这个基于列的位置转换为完整的三维 {@link BlockPosition}。
     * <p>原文：
     * Converts this column-based position into a full 3D {@link BlockPosition}
     * by supplying a Y-coordinate.
     *
     * @param y 新位置中要包含的 Y 坐标
     * <p>原文：the Y-coordinate to include in the new position
     * @return 表示完整三维位置的 {@link BlockPosition}
     * <p>原文：a {@link BlockPosition} representing the full 3D position
     */
    default BlockPosition toPosition(final int y) {
        return Position.block(this.blockX(), y, this.blockZ());
    }

}
