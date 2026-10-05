package org.bukkit.material;

import org.bukkit.DyeColor;
import org.bukkit.Material;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表羊毛/地毯方块.
 * <p>原文：
 * Represents a Wool/Cloth block
 *
 * @deprecated 所有 MaterialData 的用法均已弃用并将在未来移用.
 * 请使用 {@link org.bukkit.block.data.BlockData}.
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Wool extends MaterialData implements Colorable {
    public Wool() {
        super(Material.LEGACY_WOOL);
    }

    public Wool(DyeColor color) {
        this();
        setColor(color);
    }

    public Wool(final Material type) {
        super(type);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param type 物品/方块类型
     * <p>原文：the type
     * @param data 原始数据值
     * <p>原文：the raw data value
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public Wool(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取染料(羊毛)的颜色.
     * <p>原文：
     * Gets the current color of this dye
     *
     * @return DyeColor枚举颜色表示
     * <p>原文：DyeColor of this dye
     */
    @Override
    public DyeColor getColor() {
        return DyeColor.getByWoolData(getData());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置染料(羊毛)的颜色.
     * <p>原文：
     * Sets the color of this dye
     *
     * @param color 颜色
     * <p>原文：New color of this dye
     */
    @Override
    public void setColor(DyeColor color) {
        setData(color.getWoolData());
    }

    @Override
    public String toString() {
        return getColor() + " " + super.toString();
    }

    @Override
    public Wool clone() {
        return (Wool) super.clone();
    }
}
