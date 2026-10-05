package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'berries' 表示方块是否带有浆果.
 * <p>原文：
 * 'berries' indicates whether the block has berries.
 */
public interface CaveVinesPlant extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'berries' 属性的值.
     * <p>原文：
     * Gets the value of the 'berries' property.
     *
     * @return 'berries' 的值
     * <p>原文：the 'berries' value
     * @deprecated bad name, use {@link #hasBerries()}
     */
    @Deprecated
    default boolean isBerries() {
        return this.hasBerries();
    }

    /**
     * Gets the value of the 'berries' property.
     *
     * @return the 'berries' value
     */
    boolean hasBerries();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'berries' 属性的值.
     * <p>原文：
     * Sets the value of the 'berries' property.
     *
     * @param berries 新的 'berries' 值
     * <p>原文：the new 'berries' value
     */
    void setBerries(boolean berries);
}
