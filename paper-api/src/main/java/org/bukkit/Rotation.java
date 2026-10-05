package org.bukkit;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 指定旋转方向的枚举。
 * <p>原文：
 * An enum to specify a rotation based orientation, like that on a clock.
 * <p>
 * It represents how something is viewed, as opposed to cardinal directions.
 */
public enum Rotation {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 无旋转。
     * <p>原文：
     * No rotation
     */
    NONE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 按顺时针方向旋转45度。
     * <p>原文：
     * Rotated clockwise by 45 degrees
     */
    CLOCKWISE_45,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 按顺时针方向旋转90度。
     * <p>原文：
     * Rotated clockwise by 90 degrees
     */
    CLOCKWISE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 按顺时针方向旋转135度。
     * <p>原文：
     * Rotated clockwise by 135 degrees
     */
    CLOCKWISE_135,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 倒置翻转180度。
     * <p>原文：
     * Flipped upside-down, a 180 degree rotation
     */
    FLIPPED,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 倒置翻转180+45度。
     * <p>原文：
     * Flipped upside-down + 45 degree rotation
     */
    FLIPPED_45,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 按逆时针方向旋转90度。
     * <p>原文：
     * Rotated counter-clockwise by 90 degrees
     */
    COUNTER_CLOCKWISE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 按逆时针方向旋转45度。
     * <p>原文：
     * Rotated counter-clockwise by 45 degrees
     */
    COUNTER_CLOCKWISE_45
    ;

    private static final Rotation[] rotations = values();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 按顺时针方向旋转90度。
     * <p>原文：
     * Rotate clockwise by 90 degrees.
     *
     * @return 旋转
     * <p>原文：the relative rotation
     */
    @NotNull
    public Rotation rotateClockwise() {
        return rotations[(this.ordinal() + 1) & 0x7];
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 按逆时针方向旋转90度。
     * <p>原文：
     * Rotate counter-clockwise by 90 degrees.
     *
     * @return 旋转
     * <p>原文：the relative rotation
     */
    @NotNull
    public Rotation rotateCounterClockwise() {
        return rotations[(this.ordinal() - 1) & 0x7];
    }
}
