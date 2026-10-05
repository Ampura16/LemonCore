package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'tip' 表示此方块是否为尖端.
 * <p>原文：
 * 'tip' indicates whether this block is a tip.
 */
public interface HangingMoss extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'tip' 属性的值.
     * <p>原文：
     * Gets the value of the 'tip' property.
     *
     * @return 'tip' 的值
     * <p>原文：the 'tip' value
     */
    boolean isTip();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'tip' 属性的值.
     * <p>原文：
     * Sets the value of the 'tip' property.
     *
     * @param tip 新的 'tip' 值
     * <p>原文：the new 'tip' value
     */
    void setTip(boolean tip);
}
