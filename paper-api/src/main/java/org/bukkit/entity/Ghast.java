package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表恶魂.
 * <p>原文：
 * Represents a Ghast.
 */
public interface Ghast extends Flying, Enemy {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取恶魂是否正在充能.
     * <p>原文：
     * Gets whether the Ghast is charging
     *
     * @return 恶魂是否正在充能
     * <p>原文：Whether the Ghast is charging
     */
    boolean isCharging();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置恶魂是否正在充能.
     * <p>原文：
     * Sets whether the Ghast is charging
     *
     * @param flag 恶魂是否正在充能
     * <p>原文：Whether the Ghast is charging
     */
    void setCharging(boolean flag);

    /**
     * Returns the explosion power of shot fireballs.
     *
     * @return explosion power of shot fireballs
     */
    int getExplosionPower();

    /**
     * Sets the explosion power of shot fireballs.
     *
     * @param explosionPower explosion power of shot fireballs
     * @throws IllegalArgumentException if the explosion power is less than 0 or greater than 127
     */
    void setExplosionPower(int explosionPower);
}
