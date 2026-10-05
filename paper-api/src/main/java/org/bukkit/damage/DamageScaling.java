package org.bukkit.damage;

import org.bukkit.entity.Player;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一种关于服务器难度的伤害缩放方式.
 * <p>原文：
 * A means of damage scaling with respect to the server's difficulty.
 */
public enum DamageScaling {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 伤害不缩放.
     * <p>原文：
     * Damage is not scaled.
     */
    NEVER,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 伤害仅在{@link DamageSource#getCausingEntity() 造成伤害的实体}不是{@link Player}时缩放.
     * <p>原文：
     * Damage is scaled only when the
     * {@link DamageSource#getCausingEntity() causing entity} is not a
     * {@link Player}.
     */
    WHEN_CAUSED_BY_LIVING_NON_PLAYER,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 伤害总是缩放.
     * <p>原文：
     * Damage is always scaled.
     */
    ALWAYS;
}
