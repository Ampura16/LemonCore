package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 山羊.
 * <p>原文：
 * A Goat.
 */
public interface Goat extends Animals {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此山羊是否有左角.
     * <p>原文：
     * Gets if this goat has its left horn.
     *
     * @return 左角状态
     * <p>原文：left horn status
     */
    boolean hasLeftHorn();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此山羊是否有左角.
     * <p>原文：
     * Sets if this goat has its left horn.
     *
     * @param hasHorn 左角状态
     * <p>原文：left horn status
     */
    void setLeftHorn(boolean hasHorn);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此山羊是否有右角.
     * <p>原文：
     * Gets if this goat has its right horn.
     *
     * @return 右角状态
     * <p>原文：right horn status
     */
    boolean hasRightHorn();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此山羊是否有右角.
     * <p>原文：
     * Sets if this goat has its right horn.
     *
     * @param hasHorn 右角状态
     * <p>原文：right horn status
     */
    void setRightHorn(boolean hasHorn);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此山羊是否为尖叫山羊.
     *
     * 尖叫山羊会发出尖叫声音并更频繁地冲撞. 它们不提供房屋贷款.
     * <p>原文：
     * Gets if this is a screaming goat.
     *
     * A screaming goat makes screaming sounds and rams more often. They do not
     * offer home loans.
     *
     * @return 尖叫状态
     * <p>原文：screaming status
     */
    boolean isScreaming();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此山羊是否为尖叫山羊.
     *
     * 尖叫山羊会发出尖叫声音并更频繁地冲撞. 它们不提供房屋贷款.
     * <p>原文：
     * Sets if this is a screaming goat.
     *
     * A screaming goat makes screaming sounds and rams more often. They do not
     * offer home loans.
     *
     * @param screaming 尖叫状态
     * <p>原文：screaming status
     */
    void setScreaming(boolean screaming);

    /**
     * Makes the goat ram at the specified entity
     * @param entity the entity to ram at
     */
    void ram(@org.jetbrains.annotations.NotNull LivingEntity entity);
}
