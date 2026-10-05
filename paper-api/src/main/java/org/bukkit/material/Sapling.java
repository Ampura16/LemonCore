package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表不同类型的朝向特定方向的树苗方块。
 * <p>原文：
 * Represents the different types of Tree block that face a direction.
 *
 * @see Material#LEGACY_SAPLING
 * @deprecated 所有 MaterialData 的用法都已弃用并可能被移除。
 * 请使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Sapling extends Wood {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个树苗。
     * <p>原文：
     * Constructs a sapling.
     */
    public Sapling() {
        this(DEFAULT_SPECIES);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个给定树种的树苗。
     * <p>原文：
     * Constructs a sapling of the given tree species.
     *
     * @param species 树苗的树种
     * <p>原文：Constructs a sapling of the given tree species.
     * <p>原文：the species of the sapling
     */
    public Sapling(TreeSpecies species) {
        this(species, false);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个给定树种的树苗，并指定其是否可以立即生长。
     * <p>原文：
     * Constructs a sapling of the given tree species and if is it instant
     * growable
     *
     * @param species 树苗方块的树种
     * <p>原文：the species of the tree block
     * @param isInstantGrowable 如果树苗应在下次被骨粉催熟时生长则为 true
     * <p>原文：Constructs a sapling of the given tree species and if is it instant
     * growable
     * <p>原文：true if the Sapling should grow when next ticked with bonemeal
     */
    public Sapling(TreeSpecies species, boolean isInstantGrowable) {
        this(Material.LEGACY_SAPLING, species, isInstantGrowable);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个给定类型的树苗。
     * <p>原文：
     * Constructs a sapling of the given type.
     *
     * @param type 树苗方块的类型
     * <p>原文：Constructs a sapling of the given type.
     * <p>原文：the type of tree block
     */
    public Sapling(final Material type) {
        this(type, DEFAULT_SPECIES, false);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个给定类型和树种的树苗。
     * <p>原文：
     * Constructs a sapling of the given type and tree species.
     *
     * @param type 树苗的类型
     * <p>原文：the type of sapling
     * @param species 树苗的树种
     * <p>原文：Constructs a sapling of the given type and tree species.
     * <p>原文：the species of the sapling
     */
    public Sapling(final Material type, TreeSpecies species) {
        this(type, species, false);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个给定类型和树种的树苗，并指定其是否可以立即生长。
     * <p>原文：
     * Constructs a sapling of the given type and tree species and if is it
     * instant growable
     *
     * @param type 树苗的类型
     * <p>原文：the type of sapling
     * @param species 树苗的树种
     * <p>原文：the species of the sapling
     * @param isInstantGrowable 如果树苗应在下次被骨粉催熟时生长则为 true
     * <p>原文：Constructs a sapling of the given type and tree species and if is it
     * instant growable
     * <p>原文：true if the Sapling should grow when next ticked
     * with bonemeal
     */
    public Sapling(final Material type, TreeSpecies species, boolean isInstantGrowable) {
        super(type, species);
        setIsInstantGrowable(isInstantGrowable);
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
    @Deprecated(since = "1.9")
    public Sapling(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查树苗是否会在下次被骨粉催熟时生长。
     * <p>原文：
     * Checks if the Sapling would grow when next ticked with bonemeal
     *
     * @return 如果树苗会在下次被骨粉催熟时生长则返回 true
     * <p>原文：Checks if the Sapling would grow when next ticked with bonemeal
     * <p>原文：true if the Sapling would grow when next ticked with bonemeal
     */
    public boolean isInstantGrowable() {
        return (getData() & 0x8) == 0x8;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此树苗是否会在下次被骨粉催熟时生长。
     * <p>原文：
     * Set whether this sapling will grow when next ticked with bonemeal
     *
     * @param isInstantGrowable 如果树苗应在下次被骨粉催熟时生长则为 true
     * <p>原文：Set whether this sapling will grow when next ticked with bonemeal
     * <p>原文：true if the Sapling should grow when next ticked
     * with bonemeal
     */
    public void setIsInstantGrowable(boolean isInstantGrowable) {
        setData(isInstantGrowable ? (byte) ((getData() & 0x7) | 0x8) : (byte) (getData() & 0x7));
    }

    @Override
    public String toString() {
        return getSpecies() + " " + (isInstantGrowable() ? " IS_INSTANT_GROWABLE " : "") + " " + super.toString();
    }

    @Override
    public Sapling clone() {
        return (Sapling) super.clone();
    }
}
