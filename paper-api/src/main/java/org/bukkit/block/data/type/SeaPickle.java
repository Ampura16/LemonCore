package org.bukkit.block.data.type;

import org.bukkit.block.data.Waterlogged;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'pickles' 值表示该方块有多少个海泡菜.
 * <p>原文：
 * 'pickles' indicates the number of pickles in this block.
 */
public interface SeaPickle extends Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'pickles' 属性的值.
     * <p>原文：
     * Gets the value of the 'pickles' property.
     *
     * @return 属性 'pickles' 的值
     * <p>原文：the 'pickles' value
     */
    int getPickles();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'pickles' 属性的值.
     * <p>原文：
     * Sets the value of the 'pickles' property.
     *
     * @param pickles 新的 'pickles' 属性值
     * <p>原文：the new 'pickles' value
     */
    void setPickles(int pickles);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'pickles' 属性所允许的最小值.
     * <p>原文：
     * Gets the minimum allowed value of the 'pickles' property.
     *
     * @return 最小 'pickles' 属性值
     * <p>原文：the minimum 'pickles' value
     */
    int getMinimumPickles();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'pickles' 属性所允许的最大值.
     * <p>原文：
     * Gets the maximum allowed value of the 'pickles' property.
     *
     * @return 最大 'pickles' 属性值
     * <p>原文：the maximum 'pickles' value
     */
    int getMaximumPickles();
}
