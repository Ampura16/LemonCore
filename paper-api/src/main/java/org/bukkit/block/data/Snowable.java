package org.bukkit.block.data;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'snowy' 属性表示此方块是否被雪覆盖 (通常情况下时因为 "它" 的头顶下着雪).
 * <p>原文：
 * 'snowy' denotes whether this block has a snow covered side and top texture
 * (normally because the block above is snow).
 */
public interface Snowable extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'snowy' 属性值.
     * <p>原文：
     * Gets the value of the 'snowy' property.
     *
     * @return 方块是否被雪覆盖
     * <p>原文：the 'snowy' value
     */
    boolean isSnowy();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'snowy' 属性值.
     * <p>原文：
     * Sets the value of the 'snowy' property.
     *
     * @param snowy 方块是否被雪覆盖
     * <p>原文：the new 'snowy' value
     */
    void setSnowy(boolean snowy);
}
