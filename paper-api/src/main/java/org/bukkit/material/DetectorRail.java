package org.bukkit.material;

import org.bukkit.Material;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个探测铁轨。
 * <p>原文：
 * Represents a detector rail
 *
 * @deprecated 所有 MaterialData 的用法均已弃用并可能被移除。
 * 请使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class DetectorRail extends ExtendedRails implements PressureSensor {
    public DetectorRail() {
        super(Material.LEGACY_DETECTOR_RAIL);
    }

    public DetectorRail(final Material type) {
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
    public DetectorRail(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public boolean isPressed() {
        return (getData() & 0x8) == 0x8;
    }

    public void setPressed(boolean isPressed) {
        setData((byte) (isPressed ? (getData() | 0x8) : (getData() & ~0x8)));
    }

    @Override
    public DetectorRail clone() {
        return (DetectorRail) super.clone();
    }
}
