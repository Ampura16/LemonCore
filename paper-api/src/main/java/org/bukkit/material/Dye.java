package org.bukkit.material;

import org.bukkit.DyeColor;
import org.bukkit.Material;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表染料。
 * <p>原文：
 * Represents dye
 *
 * @deprecated all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class Dye extends MaterialData implements Colorable {
    public Dye() {
        super(Material.LEGACY_INK_SACK);
    }

    public Dye(final Material type) {
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
     */
    @Deprecated(since = "1.6.2")
    public Dye(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param color 染料的颜色
     * <p>原文：color of the dye
     */
    public Dye(final DyeColor color) {
        super(Material.LEGACY_INK_SACK, color.getDyeData());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此染料的当前颜色。
     * <p>原文：
     * Gets the current color of this dye
     *
     * @return 此染料的 {@link DyeColor}
     * <p>原文：DyeColor of this dye
     */
    @Override
    public DyeColor getColor() {
        return DyeColor.getByDyeData(getData());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此染料的颜色。
     * <p>原文：
     * Sets the color of this dye
     *
     * @param color 此染料的新颜色
     * <p>原文：New color of this dye
     */
    @Override
    public void setColor(DyeColor color) {
        setData(color.getDyeData());
    }

    @Override
    public String toString() {
        return getColor() + " DYE(" + getData() + ")";
    }

    @Override
    public Dye clone() {
        return (Dye) super.clone();
    }
}
