package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一条河豚.
 * <p>原文：
 * Represents a puffer fish.
 */
public interface PufferFish extends Fish {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此河豚的当前膨胀状态(即膨胀程度).
     * <p>原文：
     * Returns the current puff state of this fish (i.e. how inflated it is).
     *
     * @return 当前膨胀状态
     * <p>原文：current puff state
     */
    int getPuffState();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此河豚的当前膨胀状态(即膨胀程度).
     * <p>原文：
     * Sets the current puff state of this fish (i.e. how inflated it is).
     *
     * @param state 新的膨胀状态
     * <p>原文：new puff state
     */
    void setPuffState(int state);
}
