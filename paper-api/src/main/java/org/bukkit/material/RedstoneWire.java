package org.bukkit.material;

import org.bukkit.Material;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表红石线。
 * <p>原文：
 * Represents redstone wire
 *
 * @deprecated 所有 MaterialData 的用法都已弃用并可能被移除。
 * 请使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class RedstoneWire extends MaterialData implements Redstone {
    public RedstoneWire() {
        super(Material.LEGACY_REDSTONE_WIRE);
    }

    public RedstoneWire(final Material type) {
        super(type);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param type 物品类型
     * <p>原文：the type
     * @param data 原始数据值
     * <p>原文：the raw data value
     * @deprecated 魔法值
     * <p>原文：Magic value
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public RedstoneWire(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此材质的当前状态，指示其是否已通电。
     * <p>原文：
     * Gets the current state of this Material, indicating if it's powered or
     * unpowered
     *
     * @return 如果已通电则返回 true，否则返回 false
     * <p>原文：Gets the current state of this Material, indicating if it's powered or
     * unpowered
     * <p>原文：true if powered, otherwise false
     */
    @Override
    public boolean isPowered() {
        return getData() > 0;
    }

    @Override
    public String toString() {
        return super.toString() + " " + (isPowered() ? "" : "NOT ") + "POWERED";
    }

    @Override
    public RedstoneWire clone() {
        return (RedstoneWire) super.clone();
    }
}
