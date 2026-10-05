package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.block.BlockFace;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 这是 {@link DetectorRail} 和 {@link PoweredRail} 类的父类。
 * <p>原文：
 * This is the superclass for the {@link DetectorRail} and {@link PoweredRail}
 * classes
 *
 * @deprecated 所有 MaterialData 的用法均已弃用并可能被移除。
 * 请使用 {@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class ExtendedRails extends Rails {

    public ExtendedRails(final Material type) {
        super(type);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param type 材料类型
     * <p>原文：the type
     * @param data 原始数据值
     * <p>原文：the raw data value
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public ExtendedRails(final Material type, final byte data) {
        super(type, data);
    }

    @Override
    public boolean isCurve() {
        return false;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * {@inheritDoc}
     *
     * @deprecated 魔法值
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    @Override
    protected byte getConvertedData() {
        return (byte) (getData() & 0x7);
    }

    @Override
    public void setDirection(BlockFace face, boolean isOnSlope) {
        boolean extraBitSet = (getData() & 0x8) == 0x8;

        if (face != BlockFace.WEST && face != BlockFace.EAST && face != BlockFace.NORTH && face != BlockFace.SOUTH) {
            throw new IllegalArgumentException("Detector rails and powered rails cannot be set on a curve!");
        }

        super.setDirection(face, isOnSlope);
        setData((byte) (extraBitSet ? (getData() | 0x8) : (getData() & ~0x8)));
    }

    @Override
    public ExtendedRails clone() {
        return (ExtendedRails) super.clone();
    }
}
