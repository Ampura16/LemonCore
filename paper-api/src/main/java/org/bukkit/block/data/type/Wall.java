package org.bukkit.block.data.type;

import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Waterlogged;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 该类包含 'north', 'east', 'south', 'west', 的高度标识,
 * 用于设置墙在此朝向的高度.
 *
 * 'up' 值表示墙是否中心凸起 (有一个柱垛).
 * <p>原文：
 * This class encompasses the 'north', 'east', 'south', 'west', height flags
 * which are used to set the height of a wall.
 *
 * 'up' denotes whether the well has a center post.
 */
public interface Wall extends Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'up' 属性的值.
     * <p>原文：
     * Gets the value of the 'up' property.
     *
     * @return 属性 'up' 的值
     * <p>原文：the 'up' value
     */
    boolean isUp();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'up' 属性的值.
     * <p>原文：
     * Sets the value of the 'up' property.
     *
     * @param up 新的 'up' 属性值
     * <p>原文：the new 'up' value
     */
    void setUp(boolean up);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取墙在指定朝向上的高度.
     * <p>原文：
     * Gets the height of the specified face.
     *
     * @param face 指定朝向
     * <p>原文：to check
     * @return 墙在该朝向上的高度
     * <p>原文：if face is enabled
     */
    @NotNull
    Height getHeight(@NotNull BlockFace face);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置墙在指定朝向上的高度.
     * <p>原文：
     * Set the height of the specified face.
     *
     * @param face 指定朝向
     * <p>原文：to set
     * @param height 要设置的高度
     * <p>原文：the height
     */
    void setHeight(@NotNull BlockFace face, @NotNull Height height);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 墙在一个朝向上可能存在的不同高度.
     * <p>原文：
     * The different heights a face of a wall may have.
     */
    public enum Height {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 没有墙连接
         * <p>原文：
         * No wall present.
         */
        NONE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 矮墙连接
         * <p>原文：
         * Low wall present.
         */
        LOW,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 高墙连接
         * <p>原文：
         * Tall wall present.
         */
        TALL;
    }
}
