package org.bukkit.material;

import org.bukkit.Material;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表命令方块.
 * <p>原文：
 * Represents a command block
 *
 * @deprecated 所有MaterialData的用法都已弃用并将被移除。
 * 请使用{@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Command extends MaterialData implements Redstone {
    public Command() {
        super(Material.LEGACY_COMMAND);
    }

    public Command(final Material type) {
        super(type);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param type 类型
     * <p>原文：the type
     * @param data 原始数据值
     * <p>原文：the raw data value
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public Command(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块是否充能.
     * <p>原文：
     * Gets the current state of this Material, indicating if it's powered or
     * unpowered
     *
     * @return 这个方块是否充能
     * <p>原文：true if powered, otherwise false
     */
    @Override
    public boolean isPowered() {
        return (getData() & 1) != 0;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个方块是否充能.
     * <p>原文：
     * Sets the current state of this Material
     *
     * @param bool 这个方块是否充能
     * <p>原文：whether or not the command block is powered
     */
    public void setPowered(boolean bool) {
        setData((byte) (bool ? (getData() | 1) : (getData() & -2)));
    }

    @Override
    public String toString() {
        return super.toString() + " " + (isPowered() ? "" : "NOT ") + "POWERED";
    }

    @Override
    public Command clone() {
        return (Command) super.clone();
    }
}
