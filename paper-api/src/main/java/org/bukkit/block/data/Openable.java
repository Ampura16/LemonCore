package org.bukkit.block.data;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'open' 表示此方块是否开着.
 * <p>原文：
 * 'open' denotes whether this block is currently opened.
 */
public interface Openable extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'open' 属性值.
     * <p>原文：
     * Gets the value of the 'open' property.
     *
     * @return 门是否开着
     * <p>原文：the 'open' value
     */
    boolean isOpen();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'open' 属性值.
     * <p>原文：
     * Sets the value of the 'open' property.
     *
     * @param open 门是否开着
     * <p>原文：the new 'open' value
     */
    void setOpen(boolean open);
}
