package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 为某些物品或方块处理特定的元数据.
 * <p>原文：
 * Handles specific metadata for certain items or blocks
 *
 * @deprecated 所有关于MaterialData的用法已过时，并且将来会被移除.
 * 请使用 {@link org.bukkit.block.data.BlockData}.
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class MaterialData implements Cloneable {
    private final Material type;
    private byte data = 0;

    public MaterialData(final Material type) {
        this(type, (byte) 0);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param type the type
     * @param data the raw data value
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public MaterialData(final Material type, final byte data) {
        this.type = type;
        this.data = data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此物品的原始数据值.
     * <p>原文：
     * Gets the raw data in this material
     *
     * @return 原始数据值
     * <p>原文：Raw data
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public byte getData() {
        return data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此物品的原始数据值.
     * <p>原文：
     * Sets the raw data of this material
     *
     * @param data 原始数据值
     * <p>原文：New raw data
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public void setData(byte data) {
        this.data = data;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此MaterialData代表的Material.
     * <p>原文：
     * Gets the Material that this MaterialData represents
     *
     * @return 此MaterialData代表的Material物品
     * <p>原文：Material represented by this MaterialData
     */
    public Material getItemType() {
        return type;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 基于此MaterialData创建一个新的物品堆实例.
     * <p>原文：
     * Creates a new ItemStack based on this MaterialData
     *
     * @return 包含此MaterialData所含数据的ItemStack实例
     * <p>原文：New ItemStack containing a copy of this MaterialData
     * @deprecated 该方法创建了一个堆叠数为0的物品堆，一般没什么用.
     * 请考虑 {@link #toItemStack(int)}.
     * <p>原文：this method creates an ItemStack of size 0 which is not
     * generally useful. Consider {@link #toItemStack(int)}.
     */
    @Deprecated(since = "1.12")
    public ItemStack toItemStack() {
        return new ItemStack(type, 0, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 基于此MaterialData创建一个新的物品堆实例.
     * <p>原文：
     * Creates a new ItemStack based on this MaterialData
     *
     * @param amount 物品堆叠数量
     * <p>原文：The stack size of the new stack
     * @return 包含此MaterialData所含数据的ItemStack实例
     * <p>原文：New ItemStack containing a copy of this MaterialData
     */
    public ItemStack toItemStack(int amount) {
        return new ItemStack(type, amount, data);
    }

    @Override
    public String toString() {
        return getItemType() + "(" + getData() + ")";
    }

    @Override
    public int hashCode() {
        return ((getItemType().hashCode() << 8) ^ getData());
    }

    @Override
    public boolean equals(Object obj) {
        if (obj != null && obj instanceof MaterialData) {
            MaterialData md = (MaterialData) obj;

            return (md.getItemType() == getItemType() && md.getData() == getData());
        } else {
            return false;
        }
    }

    @Override
    public MaterialData clone() {
        try {
            return (MaterialData) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new Error(e);
        }
    }
}
