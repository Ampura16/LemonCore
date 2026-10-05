package org.bukkit.entity;

import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表唤魔者召唤的尖牙.
 * <p>原文：
 * Represents Evoker Fangs.
 */
public interface EvokerFangs extends Entity {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取召唤此尖牙的{@link LivingEntity 生物}.
     * <p>原文：
     * Gets the {@link LivingEntity} which summoned the fangs.
     *
     * @return 召唤此尖牙的{@link LivingEntity 生物}
     * <p>原文：the {@link LivingEntity} which summoned the fangs
     */
    @Nullable
    LivingEntity getOwner();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置召唤此尖牙的{@link LivingEntity 生物}.
     * <p>原文：
     * Sets the {@link LivingEntity} which summoned the fangs.
     *
     * @param owner 召唤此尖牙的{@link LivingEntity 生物}
     * <p>原文：the {@link LivingEntity} which summoned the fangs
     */
    void setOwner(@Nullable LivingEntity owner);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取尖牙攻击前的延迟 (tick).
     * <p>原文：
     * Get the delay in ticks until the fang attacks.
     *
     * @return 延迟时间
     * <p>原文：the delay
     */
    int getAttackDelay();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置尖牙攻击前的延迟 (tick).
     * <p>原文：
     * Set the delay in ticks until the fang attacks.
     *
     * @param delay 延迟时间, 必须为正数
     * <p>原文：the delay, must be positive
     */
    void setAttackDelay(int delay);
}
