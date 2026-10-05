package io.papermc.paper.math;

import org.bukkit.Axis;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 以整数表示的位置。
 * <p>
 * <b>在移除 Experimental 注解之前可能出现破坏性变更。</b>
 * <p>原文：
 * A position represented with integers.
 * <p>
 * <b>May see breaking changes until Experimental annotation is removed.</b>
 *
 * @see FinePosition
 */
@ApiStatus.Experimental
@NullMarked
public interface BlockPosition extends Position {

    @Override
    default double x() {
        return this.blockX();
    }

    @Override
    default double y() {
        return this.blockY();
    }

    @Override
    default double z() {
        return this.blockZ();
    }

    @Override
    default boolean isBlock() {
        return true;
    }

    @Override
    default boolean isFine() {
        return false;
    }

    @Override
    default BlockPosition toBlock() {
        return this;
    }

    @Override
    default BlockPosition offset(final int x, final int y, final int z) {
        return x == 0 && y == 0 && z == 0 ? this : new BlockPositionImpl(this.blockX() + x, this.blockY() + y, this.blockZ() + z);
    }

    @Override
    default FinePosition offset(final double x, final double y, final double z) {
        return new FinePositionImpl(this.blockX() + x, this.blockY() + y, this.blockZ() + z);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回沿指定方向偏移 1 的方块坐标。
     * <p>原文：
     * Returns a block position offset by 1 in the direction specified.
     *
     * @param blockFace 要朝其偏移的方块面
     * <p>原文：the block face to offset towards
     * @return 偏移后的方块位置
     * <p>原文：the offset block position
     */
    @Contract(value = "_ -> new", pure = true)
    default BlockPosition offset(final BlockFace blockFace) {
        return this.offset(blockFace, 1);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回沿指定方向偏移（方向乘以指定量）的方块坐标。
     * <p>原文：
     * Returns a block position offset in the direction specified
     * multiplied by the amount.
     *
     * @param blockFace 要朝其偏移的方块面
     * <p>原文：the block face to offset towards
     * @param amount 朝该方向移动的次数
     * <p>原文：the number of times to move in that direction
     * @return 偏移后的方块位置
     * <p>原文：the offset block position
     */
    @Contract(pure = true)
    default BlockPosition offset(final BlockFace blockFace, final int amount) {
        return amount == 0 ? this : new BlockPositionImpl(this.blockX() + (blockFace.getModX() * amount), this.blockY() + (blockFace.getModY() * amount), this.blockZ() + (blockFace.getModZ() * amount));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回沿指定轴偏移指定量的方块坐标。
     * <p>原文：
     * Returns a block position offset by the amount along
     * the specified axis.
     *
     * @param axis 要沿其偏移的轴
     * <p>原文：the axis to offset along
     * @param amount 沿该轴偏移的量
     * <p>原文：the amount to offset along that axis
     * @return 偏移后的方块位置
     * <p>原文：the offset block position
     */
    @Contract(pure = true)
    default BlockPosition offset(final Axis axis, final int amount) {
        return amount == 0 ? this : switch (axis) {
            case X -> new BlockPositionImpl(this.blockX() + amount, this.blockY(), this.blockZ());
            case Y -> new BlockPositionImpl(this.blockX(), this.blockY() + amount, this.blockZ());
            case Z -> new BlockPositionImpl(this.blockX(), this.blockY(), this.blockZ() + amount);
        };
    }
}
