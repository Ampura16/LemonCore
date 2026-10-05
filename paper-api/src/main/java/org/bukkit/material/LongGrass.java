package org.bukkit.material;

import org.bukkit.GrassSpecies;
import org.bukkit.Material;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表不同类型的高草。
 * <p>原文：
 * Represents the different types of long grasses.
 *
 * @deprecated 所有 MaterialData 的使用都已弃用并可能被移除。
 * 使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class LongGrass extends MaterialData {
    public LongGrass() {
        super(Material.LEGACY_LONG_GRASS);
    }

    public LongGrass(GrassSpecies species) {
        this();
        setSpecies(species);
    }

    public LongGrass(final Material type) {
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
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public LongGrass(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此草的当前种类
     * <p>原文：
     * Gets the current species of this grass
     *
     * @return 此草的GrassSpecies
     * <p>
     * 原文：Gets the current species of this grass
     * <p>原文：GrassSpecies of this grass
     */
    public GrassSpecies getSpecies() {
        return GrassSpecies.getByData(getData());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此草的种类
     * <p>原文：
     * Sets the species of this grass
     *
     * @param species 此草的新种类
     * <p>
     * 原文：Sets the species of this grass
     * <p>原文：New species of this grass
     */
    public void setSpecies(GrassSpecies species) {
        setData(species.getData());
    }

    @Override
    public String toString() {
        return getSpecies() + " " + super.toString();
    }

    @Override
    public LongGrass clone() {
        return (LongGrass) super.clone();
    }
}
