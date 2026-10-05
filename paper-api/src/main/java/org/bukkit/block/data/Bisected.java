package org.bukkit.block.data;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'half' 值表示该方块是一个两格高方块的哪一部分.
 * <br>
 * 在游戏中, (top, bottom) 或 (upper, lower) 两种叫法可能都存在.
 * <p>原文：
 * 'half' denotes which half of a two block tall material this block is.
 * <br>
 * In game it may be referred to as either (top, bottom) or (upper, lower).
 */
public interface Bisected extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'half' 属性的值.
     * <p>原文：
     * Gets the value of the 'half' property.
     *
     * @return 属性 'half' 的值
     * <p>原文：the 'half' value
     */
    @NotNull
    Half getHalf();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'half' 属性的值.
     * <p>原文：
     * Sets the value of the 'half' property.
     *
     * @param half 新的 'half' 属性值
     * <p>原文：the new 'half' value
     */
    void setHalf(@NotNull Half half);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 垂直方向上一个方块的二分部分.
     * <p>原文：
     * The half of a vertically bisected block.
     */
    public enum Half {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 上半部分方块, 通常情况下 y 值更高
         * <p>原文：
         * The top half of the block, normally with the higher y coordinate.
         */
        TOP,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 下半部分方块, 通常情况下 y 值更低
         * <p>原文：
         * The bottom half of the block, normally with the lower y coordinate.
         */
        BOTTOM;
    }
}
