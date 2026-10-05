package org.bukkit.material;

import org.bukkit.Material;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个炼药锅。
 * <p>原文：
 * Represents a cauldron
 *
 * @deprecated 所有 MaterialData 的用法均已弃用并可能被移除。
 * 请使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Cauldron extends MaterialData {
    private static final int CAULDRON_FULL = 3;
    private static final int CAULDRON_EMPTY = 0;

    public Cauldron() {
        super(Material.LEGACY_CAULDRON);
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
    public Cauldron(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param data 原始数据值
     * <p>原文：the raw data value
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public Cauldron(byte data) {
        super(Material.LEGACY_CAULDRON, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查炼药锅是否已满。
     * <p>原文：
     * Check if the cauldron is full.
     *
     * @return 如果已满则返回 true。
     * <p>
     * 原文：Check if the cauldron is full.
     * <p>原文：True if it is full.
     */
    public boolean isFull() {
        return getData() >= CAULDRON_FULL;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查炼药锅是否为空。
     * <p>原文：
     * Check if the cauldron is empty.
     *
     * @return 如果为空则返回 true。
     * <p>
     * 原文：Check if the cauldron is empty.
     * <p>原文：True if it is empty.
     */
    public boolean isEmpty() {
        return getData() <= CAULDRON_EMPTY;
    }

    @Override
    public String toString() {
        return (isEmpty() ? "EMPTY" : (isFull() ? "FULL" : getData() + "/3 FULL")) + " CAULDRON";
    }

    @Override
    public Cauldron clone() {
        return (Cauldron) super.clone();
    }
}
