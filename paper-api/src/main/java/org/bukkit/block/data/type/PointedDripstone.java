package org.bukkit.block.data.type;

import java.util.Set;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.Waterlogged;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'thickness' 表示滴水石锥的粗细程度.
 * <br>
 * 'vertical_direction' 表示滴水石锥的朝向.
 * <br>
 * 某些方块可能无法朝向所有方向，使用 {@link #getVerticalDirections()} 获取此方块所有可能的方向.
 * <p>原文：
 * 'thickness' represents the dripstone thickness.
 * <br>
 * 'vertical_direction' represents the dripstone orientation.
 * <br>
 * Some blocks may not be able to face in all directions, use
 * {@link #getVerticalDirections()} to get all possible directions for this
 * block.
 */
public interface PointedDripstone extends Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'vertical_direction' 属性的值.
     * <p>原文：
     * Gets the value of the 'vertical_direction' property.
     *
     * @return 'vertical_direction' 的值
     * <p>原文：the 'vertical_direction' value
     */
    @NotNull
    BlockFace getVerticalDirection();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'vertical_direction' 属性的值.
     * <p>原文：
     * Sets the value of the 'vertical_direction' property.
     *
     * @param direction 新的 'vertical_direction' 值
     * <p>原文：the new 'vertical_direction' value
     */
    void setVerticalDirection(@NotNull BlockFace direction);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此方块适用的面.
     * <p>原文：
     * Gets the faces which are applicable to this block.
     *
     * @return 允许的 'vertical_direction' 值
     * <p>原文：the allowed 'vertical_direction' values
     */
    @NotNull
    Set<BlockFace> getVerticalDirections();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'thickness' 属性的值.
     * <p>原文：
     * Gets the value of the 'thickness' property.
     *
     * @return 'thickness' 的值
     * <p>原文：the 'thickness' value
     */
    @NotNull
    Thickness getThickness();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'thickness' 属性的值.
     * <p>原文：
     * Sets the value of the 'thickness' property.
     *
     * @param thickness 新的 'thickness' 值
     * <p>原文：the new 'thickness' value
     */
    void setThickness(@NotNull Thickness thickness);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 滴水石锥的粗细程度，对应于其在多块滴水石锥结构中的位置.
     * <p>原文：
     * Represents the thickness of the dripstone, corresponding to its position
     * within a multi-block dripstone formation.
     */
    public enum Thickness {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 延伸的尖端.
         * <p>原文：
         * Extended tip.
         */
        TIP_MERGE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 仅尖端.
         * <p>原文：
         * Just the tip.
         */
        TIP,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 顶部部分.
         * <p>原文：
         * Top section.
         */
        FRUSTUM,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 中间部分.
         * <p>原文：
         * Middle section.
         */
        MIDDLE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 底部.
         * <p>原文：
         * Base.
         */
        BASE;
    }
}
