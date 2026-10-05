package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Waterlogged;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'hydration' 表示方块的水合程度.
 * <p>原文：
 * Represents a dried ghast block data.
 */
public interface DriedGhast extends Directional, Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'hydration' 属性的值.
     * <p>原文：
     * Gets the hydration level of this dried ghast.
     *
     * @return 'hydration' 的值
     * <p>原文：the hydration level, reaching from 0 to {@link #getMaximumHydration()}
     */
    int getHydration();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'hydration' 属性的值.
     * <p>原文：
     * Sets the hydration level of this dried ghast.
     *
     * @param hydration 新的 'hydration' 值
     * <p>原文：the hydration level, reaching from 0 to {@link #getMaximumHydration()}
     */
    void setHydration(final int hydration);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'hydration' 属性允许的最大值.
     * <p>原文：
     * Provides the maximum hydration level this dried ghast can reach.
     *
     * @return 'hydration' 的最大值
     * <p>原文：the maximum level.
     */
    int getMaximumHydration();

}
