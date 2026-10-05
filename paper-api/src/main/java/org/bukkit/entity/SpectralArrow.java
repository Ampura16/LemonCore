package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一支光灵箭.
 * <p>原文：
 * Represents a spectral arrow.
 */
public interface SpectralArrow extends AbstractArrow {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此箭矢施加发光效果的持续时间.
     * <p>原文：
     * Returns the amount of time that this arrow will apply
     * the glowing effect for.
     *
     * @return 发光效果的刻数
     * <p>原文：the glowing effect ticks
     */
    int getGlowingTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置施加发光效果的持续时间.
     * <p>原文：
     * Sets the amount of time to apply the glowing effect for.
     *
     * @param duration 发光效果的刻数
     * <p>原文：the glowing effect ticks
     */
    void setGlowingTicks(int duration);
}
