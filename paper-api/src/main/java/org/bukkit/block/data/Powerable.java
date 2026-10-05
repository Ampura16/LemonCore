package org.bukkit.block.data;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'powered' 数据值表示该方块是否被充能.
 * 也就是说, 方块正接受大于 0 的红石信号.
 * <p>原文：
 * 'powered' indicates whether this block is in the powered state or not, i.e.
 * receiving a redstone current of power &gt; 0.
 */
public interface Powerable extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'powered' 数据值.
     * <p>原文：
     * Gets the value of the 'powered' property.
     *
     * @return 方块是否被充能
     * <p>原文：the 'powered' value
     */
    boolean isPowered();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'powered' 数据值.
     * <p>原文：
     * Sets the value of the 'powered' property.
     *
     * @param powered 方块是否被充能
     * <p>原文：the new 'powered' value
     */
    void setPowered(boolean powered);
}
