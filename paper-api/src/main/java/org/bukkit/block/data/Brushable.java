package org.bukkit.block.data;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'dusted' 表示方块被刷子清除的程度，即露出的程度。
 * <p>原文：
 * 'dusted' represents how far uncovered by brush the block is.
 */
public interface Brushable extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'dusted' 属性的值。
     * <p>原文：
     * Gets the value of the 'dusted' property.
     *
     * @return 'dusted' 属性的值
     * <p>原文：the 'dusted' value
     */
    int getDusted();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'dusted' 属性的值。
     * <p>原文：
     * Sets the value of the 'dusted' property.
     *
     * @param dusted 'dusted' 属性的新值
     * <p>原文：the new 'dusted' value
     */
    void setDusted(int dusted);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'dusted' 属性允许的最大值。
     * <p>原文：
     * Gets the maximum allowed value of the 'dusted' property.
     *
     * @return 'dusted' 属性允许的最大值
     * <p>原文：the maximum 'dusted' value
     */
    int getMaximumDusted();
}
