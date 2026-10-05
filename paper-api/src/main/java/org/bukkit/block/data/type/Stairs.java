package org.bukkit.block.data.type;

import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Waterlogged;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'shape' 值代表阶梯方块的材质和碰撞箱的类型.
 * <p>原文：
 * 'shape' represents the texture and bounding box shape of these stairs.
 */
public interface Stairs extends Bisected, Directional, Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'shape' 属性的值.
     * <p>原文：
     * Gets the value of the 'shape' property.
     *
     * @return 属性 'shape' 的值
     * <p>原文：the 'shape' value
     */
    @NotNull
    Shape getShape();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'shape' 属性的值.
     * <p>原文：
     * Sets the value of the 'shape' property.
     *
     * @param shape 新的 'shape' 属性值
     * <p>原文：the new 'shape' value
     */
    void setShape(@NotNull Shape shape);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 一个阶梯方块可能的的形状 - 用于创建转角的阶梯.
     * <p>原文：
     * The shape of a stair block - used for constructing corners.
     */
    public enum Shape {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 普通的阶梯方块
         * <p>原文：
         * Regular stair block.
         */
        STRAIGHT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 左侧凸出的内角阶梯
         * <p>原文：
         * Inner corner stair block with higher left side.
         */
        INNER_LEFT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 右侧凸出的内角阶梯
         * <p>原文：
         * Inner corner stair block with higher right side.
         */
        INNER_RIGHT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 左侧凸出的外角阶梯
         * <p>原文：
         * Outer corner stair block with higher left side.
         */
        OUTER_LEFT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 右侧凸出的外角阶梯
         * <p>原文：
         * Outer corner stair block with higher right side.
         */
        OUTER_RIGHT;
    }
}
