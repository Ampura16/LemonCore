package org.bukkit.block.structure;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表{@link org.bukkit.block.Structure 结构方块}如何旋转.
 * <p>原文：
 * Represents how a {@link org.bukkit.block.Structure} can be rotated.
 */
public enum StructureRotation {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 无旋转.
     * <p>原文：
     * No rotation.
     */
    NONE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 顺时针旋转90度.
     * <p>原文：
     * Rotated clockwise 90 degrees.
     */
    CLOCKWISE_90,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 顺时针旋转180度.
     * <p>原文：
     * Rotated clockwise 180 degrees.
     */
    CLOCKWISE_180,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 逆时针旋转90度.
     * <br>
     * 相当于顺时针旋转270度.
     * <p>原文：
     * Rotated counter clockwise 90 degrees.
     * <br>
     * Equivalent to rotating clockwise 270 degrees.
     */
    COUNTERCLOCKWISE_90;
}
