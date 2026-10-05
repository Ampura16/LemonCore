package org.bukkit.block.data.type;

import org.bukkit.block.data.Lightable;
import org.bukkit.block.data.Waterlogged;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'candles' 表示存在的蜡烛数量.
 * <p>原文：
 * 'candles' represents the number of candles which are present.
 */
public interface Candle extends Lightable, Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'candles' 属性的值.
     * <p>原文：
     * Gets the value of the 'candles' property.
     *
     * @return 'candles' 的值
     * <p>原文：the 'candles' value
     */
    int getCandles();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'candles' 属性的值.
     * <p>原文：
     * Sets the value of the 'candles' property.
     *
     * @param candles 新的 'candles' 值
     * <p>原文：the new 'candles' value
     */
    void setCandles(int candles);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'candles' 属性允许的最大值.
     * <p>原文：
     * Gets the maximum allowed value of the 'candles' property.
     *
     * @return 'candles' 的最大值
     * <p>原文：the maximum 'candles' value
     */
    int getMaximumCandles();

    // Paper start
    /**
     * Gets the minimum allowed value of the 'candles' property.
     *
     * @return the minimum 'candles' value
     */
    int getMinimumCandles();
    // Paper end
}
