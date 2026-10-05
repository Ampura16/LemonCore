package org.bukkit.material;

import org.bukkit.CoalType;
import org.bukkit.Material;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表不同类型的煤炭。
 * <p>原文：
 * Represents the different types of coals.
 *
 * @deprecated 所有 MaterialData 的用法均已弃用并可能被移除。
 * 请使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Coal extends MaterialData {
    public Coal() {
        super(Material.LEGACY_COAL);
    }

    public Coal(CoalType type) {
        this();
        setType(type);
    }

    public Coal(final Material type) {
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
    public Coal(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此煤炭的当前类型。
     * <p>原文：
     * Gets the current type of this coal
     *
     * @return 此煤炭的 CoalType
     * <p>
     * 原文：Gets the current type of this coal
     * <p>原文：CoalType of this coal
     */
    public CoalType getType() {
        return CoalType.getByData(getData());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此煤炭的类型。
     * <p>原文：
     * Sets the type of this coal
     *
     * @param type 此煤炭的新类型
     * <p>
     * 原文：Sets the type of this coal
     * <p>原文：New type of this coal
     */
    public void setType(CoalType type) {
        setData(type.getData());
    }

    @Override
    public String toString() {
        return getType() + " " + super.toString();
    }

    @Override
    public Coal clone() {
        return (Coal) super.clone();
    }
}
