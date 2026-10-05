package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'charges' 值代表重生锚还能使用几次.
 * <p>原文：
 * 'charges' represents the amount of times the anchor may still be used.
 */
public interface RespawnAnchor extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'charges' 属性的值.
     * <p>原文：
     * Gets the value of the 'charges' property.
     *
     * @return 属性 'charges' 的值
     * <p>原文：the 'charges' value
     */
    int getCharges();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'charges' 属性的值.
     * <p>原文：
     * Sets the value of the 'charges' property.
     *
     * @param charges 新的 'charges' 属性值
     * <p>原文：the new 'charges' value
     */
    void setCharges(int charges);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'charges' 属性所能允许的最大值.
     * <p>原文：
     * Gets the maximum allowed value of the 'charges' property.
     *
     * @return 最大 'charges' 属性值
     * <p>原文：the maximum 'charges' value
     */
    int getMaximumCharges();
}
