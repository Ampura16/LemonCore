package org.bukkit.material;

import org.bukkit.Material;
import org.bukkit.TreeSpecies;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表不同的木台阶.
 * <p>原文：
 * Represents the different types of wooden steps.
 *
 * @see Material#LEGACY_WOOD_STEP
 * @deprecated 所有MaterialData的用法都已弃用并将被移除。请使用{@link org.bukkit.block.data.BlockData}。
 * <p>原文：all usage of MaterialData is deprecated and subject to removal.
 * Use {@link org.bukkit.block.data.BlockData}.
 */
@Deprecated(since = "1.13", forRemoval = true)
public class WoodenStep extends Wood {
    protected static final Material DEFAULT_TYPE = Material.LEGACY_WOOD_STEP;
    protected static final boolean DEFAULT_INVERTED = false;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造本类.
     * <p>原文：
     * Constructs a wooden step.
     */
    public WoodenStep() {
        this(DEFAULT_SPECIES, DEFAULT_INVERTED);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以指定的树种构造本类.
     * <p>原文：
     * Constructs a wooden step of the given tree species.
     *
     * @param species 木台阶的树种
     * <p>原文：the species of the wooden step
     */
    public WoodenStep(TreeSpecies species) {
        this(species, DEFAULT_INVERTED);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以指定的树种和台阶类型(上半台阶和普通台阶)构造本类.
     * <p>原文：
     * Constructs a wooden step of the given type and tree species, either
     * inverted or not.
     *
     * @param species 木台阶的树种
     * <p>原文：the species of the wooden step
     * @param inv 为true时表示上半台阶（方块顶部），为false时表示普通台阶（底部台阶）
     * <p>原文：true the step is at the top of the block
     */
    public WoodenStep(final TreeSpecies species, boolean inv) {
        super(DEFAULT_TYPE, species);
        setInverted(inv);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param type 材质类型
     * <p>原文：the type
     * @param data 原始数据值
     * <p>原文：the raw data value
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public WoodenStep(final Material type, final byte data) {
        super(type, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此台阶是否被反转.
     * <p>原文：
     * Test if step is inverted
     *
     * @return 上半台阶(反转的台阶)为true，普通台阶(底部台阶)为false
     * <p>原文：true if inverted (top half), false if normal (bottom half)
     */
    @SuppressWarnings("deprecation")
    public boolean isInverted() {
        return ((getData() & 0x8) != 0);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置台阶是否被反转.
     * <p>原文：
     * Set step inverted state
     *
     * @param inv - 上半台阶(反转的台阶)为true，普通台阶(底部台阶)为false
     * <p>原文：- true if step is inverted (top half), false if step is normal
     * (bottom half)
     */
    @SuppressWarnings("deprecation")
    public void setInverted(boolean inv) {
        int dat = getData() & 0x7;
        if (inv) {
            dat |= 0x8;
        }
        setData((byte) dat);
    }

    @Override
    public WoodenStep clone() {
        return (WoodenStep) super.clone();
    }

    @Override
    public String toString() {
        return super.toString() + " " + getSpecies() + (isInverted() ? " inverted" : "");
    }
}
