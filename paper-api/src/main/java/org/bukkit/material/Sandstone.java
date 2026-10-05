package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.SandstoneType;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表不同类型的砂岩。
 * <p>原文：
 * Represents the different types of sandstone.
 *
 * @deprecated 所有 MaterialData 的用法都已弃用并可能被移除。
 * 请使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Sandstone extends MaterialData {
    public Sandstone() {
        super(Material.LEGACY_SANDSTONE);
    }

    public Sandstone(SandstoneType type) {
        this();
        setType(type);
    }

    public Sandstone(final Material type) {
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
    public Sandstone(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此砂岩的当前类型。
     * <p>原文：
     * Gets the current type of this sandstone
     *
     * @return 此砂岩的 SandstoneType
     * <p>原文：Gets the current type of this sandstone
     * <p>原文：SandstoneType of this sandstone
     */
    public SandstoneType getType() {
        return SandstoneType.getByData(getData());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此砂岩的类型。
     * <p>原文：
     * Sets the type of this sandstone
     *
     * @param type 此砂岩的新类型
     * <p>原文：Sets the type of this sandstone
     * <p>原文：New type of this sandstone
     */
    public void setType(SandstoneType type) {
        setData(type.getData());
    }

    @Override
    public String toString() {
        return getType() + " " + super.toString();
    }

    @Override
    public Sandstone clone() {
        return (Sandstone) super.clone();
    }
}
