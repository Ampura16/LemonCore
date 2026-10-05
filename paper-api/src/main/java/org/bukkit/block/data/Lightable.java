package org.bukkit.block.data;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'lit' 表示此方块 (红石或熔炉) 是否在发光 ———— 不是烧毁他们的意思.
 * <p>原文：
 * 'lit' denotes whether this block (either a redstone torch or furnace) is
 * currently lit - that is not burned out.
 */
public interface Lightable extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'lit' 数据值.
     * <p>原文：
     * Gets the value of the 'lit' property.
     *
     * @return 方块是否在发光
     * <p>原文：the 'lit' value
     */
    boolean isLit();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'lit' 数据值.
     * <p>原文：
     * Sets the value of the 'lit' property.
     *
     * @param lit 方块是否在发光
     * <p>原文：the new 'lit' value
     */
    void setLit(boolean lit);
}
