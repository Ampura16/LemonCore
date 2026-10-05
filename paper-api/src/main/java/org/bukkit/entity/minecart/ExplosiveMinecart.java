package org.bukkit.entity.minecart;

import org.bukkit.entity.Explosive;
import org.bukkit.entity.Minecart;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表TNT矿车，可以被引爆.
 * <p>原文：
 * Represents a Minecart with TNT inside it that can explode when triggered.
 */
public interface ExplosiveMinecart extends Minecart, Explosive {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此矿车的引信时间（以tick为单位）。
     * <p>
     * 如果将引信时间设置为非零值，将会点燃此爆炸物。
     * <p>原文：
     * Set the fuse ticks of this minecart.
     *
     * If the fuse ticks are set to a non-zero value, this will ignite the
     * explosive.
     *
     * @param ticks 引信时间（以tick为单位）
     * <p>原文：the ticks
     */
    public void setFuseTicks(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此矿车的引信时间（以tick为单位）。
     * <p>
     * 如果引信时间降为0，矿车将会爆炸。
     * <p>原文：
     * Get the fuse ticks of this minecart.
     *
     * If the fuse ticks reach 0, the minecart will explode.
     *
     * @return 引信时间（以tick为单位），如果此矿车的引信尚未点燃则返回-1
     * <p>原文：the fuse ticks, or -1 if this minecart's fuse has not yet been
     * ignited
     */
    public int getFuseTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取基于矿车速度计算的爆炸威力增加系数。
     * <p>原文：
     * Gets the factor by which explosion yield increases based on Minecart
     * speed.
     *
     * @return 增加系数
     * <p>原文：increase factor
     */
    public float getExplosionSpeedFactor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置基于矿车速度计算的爆炸威力增加系数。
     * <p>原文：
     * Sets the factor by which explosion yield increases based on Minecart
     * speed.
     *
     * @param factor 新的系数
     * <p>原文：new factor
     */
    public void setExplosionSpeedFactor(float factor);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 点燃此矿车的引信。
     * <p>原文：
     * Ignite this minecart's fuse naturally.
     */
    public void ignite();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此矿车的引信是否已被点燃。
     * <p>原文：
     * Check whether or not this minecart's fuse has been ignited.
     *
     * @return 如果已点燃则为true，否则为false
     * <p>原文：true if ignited, false otherwise
     */
    public boolean isIgnited();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 立即引爆此矿车，爆炸威力由其当前移动速度决定。
     * <p>原文：
     * Immediately explode this minecart with the power assumed by its current
     * movement.
     */
    public void explode();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以指定威力立即引爆此矿车。
     * <p>原文：
     * Immediately explode this minecart with the given power.
     *
     * @param power 爆炸威力，必须为正数且不能超过5.0
     * <p>原文：the power to use. Must be positive and cannot exceed 25.0
     */
    public void explode(double power);
}
