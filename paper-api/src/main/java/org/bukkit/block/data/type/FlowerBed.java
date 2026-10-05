package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'flower_amount' 表示花瓣的数量.
 * <p>原文：
 * 'flower_amount' represents the number of flowers.
 */
public interface FlowerBed extends Directional {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'flower_amount' 属性的值.
     * <p>原文：
     * Gets the value of the 'flower_amount' property.
     *
     * @return 'flower_amount' 的值
     * <p>原文：the 'flower_amount' value
     */
    int getFlowerAmount();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'flower_amount' 属性的值.
     * <p>原文：
     * Sets the value of the 'flower_amount' property.
     *
     * @param flowerAmount the new 'flower_amount' value
     */
    void setFlowerAmount(int flowerAmount);

    // Paper start
    /**
     * Gets the minimum allowed value of the 'flower_amount' property.
     *
     * @return the minimum 'flower_amount' value
     */
    int getMinimumFlowerAmount();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'flower_amount' 属性允许的最大值.
     * <p>原文：
     * Gets the maximum allowed value of the 'flower_amount' property.
     *
     * @return 'flower_amount' 的最大值
     * <p>原文：the maximum 'flower_amount' value
     */
    int getMaximumFlowerAmount();
}
