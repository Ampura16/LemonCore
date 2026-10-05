package org.bukkit.block.data.type;

import org.bukkit.block.data.Waterlogged;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'bottom' 值表示脚手架是否浮空.
 * <br>
 * 'distance' 值表示一个脚手架方块与 'bottom' (中心) 脚手架方块的距离.
 * <br>
 * 当 'distance' 值达到 {@link #getMaximumDistance()} 时, 方块就会坠落.
 * <p>原文：
 * 'bottom' indicates whether the scaffolding is floating or not.
 * <br>
 * 'distance' indicates the distance from a scaffolding block placed above a
 * 'bottom' scaffold.
 * <br>
 * When 'distance' reaches {@link #getMaximumDistance()} the block will drop.
 */
public interface Scaffolding extends Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'bottom' 属性的值.
     * <p>原文：
     * Gets the value of the 'bottom' property.
     *
     * @return 属性 'bottom' 的值
     * <p>原文：the 'bottom' value
     */
    boolean isBottom();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'bottom' 属性的值.
     * <p>原文：
     * Sets the value of the 'bottom' property.
     *
     * @param bottom 新的 'bottom' 属性值
     * <p>原文：the new 'bottom' value
     */
    void setBottom(boolean bottom);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'distance' 属性的值.
     * <p>原文：
     * Gets the value of the 'distance' property.
     *
     * @return 属性 'distance' 的值
     * <p>原文：the 'distance' value
     */
    int getDistance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'distance' 属性的值.
     * <p>原文：
     * Sets the value of the 'distance' property.
     *
     * @param distance 新的 'distance' 属性值
     * <p>原文：the new 'distance' value
     */
    void setDistance(int distance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'distance' 属性所能允许的最大值.
     * <p>原文：
     * Gets the maximum allowed value of the 'distance' property.
     *
     * @return 最大 'distance' 属性值
     * <p>原文：the maximum 'distance' value
     */
    int getMaximumDistance();
}
