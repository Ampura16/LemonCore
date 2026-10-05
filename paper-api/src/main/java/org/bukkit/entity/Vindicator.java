package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表卫道士.
 * <p>原文：
 * Represents a Vindicator.
 */
public interface Vindicator extends Illager {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回卫道士是否处于"强尼"模式.
     * <p>
     * 当此模式激活时，卫道士将对所有生物敌对.
     * <p>原文：
     * Returns whether a vindicator is in "Johnny" mode.
     *
     * When this mode is active, vindicators will be hostile to all mobs.
     *
     * @return 如果处于强尼模式则返回 true
     * <p>原文：true if johnny
     */
    boolean isJohnny();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置卫道士的强尼状态.
     * <p>原文：
     * Sets the Johnny state of a vindicator.
     *
     * @param johnny 新的强尼状态
     * <p>原文：new johnny state
     */
    void setJohnny(boolean johnny);
}
