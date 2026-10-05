package com.destroystokyo.paper.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Mob;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface RangedEntity extends Mob {
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用远程攻击攻击指定实体。
     * <p>原文：
     * Attack the specified entity using a ranged attack.
     *
     * @param target 要作为目标的实体
     * <p>原文：the entity to target
     * @param charge 攻击的“蓄力”程度（对于弓攻击，即弓被拉开的程度）。应为 0 到 1 之间的值，表示为 targetDistance/maxDistance。
     * <p>原文：How "charged" the attack is (how far back the bow was pulled for Bow attacks).
     *               This should be a value between 0 and 1, represented as targetDistance/maxDistance.
     */
    void rangedAttack(LivingEntity target, float charge);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置实体正在举起双手“蓄力”攻击
     * <p>原文：
     * Sets that the Entity is "charging" up an attack, by raising its hands
     *
     * @param raiseHands 实体是否举起双手蓄力攻击
     * <p>原文：Whether the entities hands are raised to charge attack
     * @deprecated 请使用 {@link #setAggressive(boolean)}
     * <p>原文：use {@link #setAggressive(boolean)}
     */
    @Deprecated(since = "1.19.2")
    void setChargingAttack(boolean raiseHands);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link LivingEntity#isHandRaised()} 的别名，表示实体是否正在蓄力攻击
     * <p>原文：
     * Alias to {@link LivingEntity#isHandRaised()}, if the entity is charging an attack
     * @return 实体是否举起了双手
     * <p>原文：If entities hands are raised
     * @deprecated 请使用 {@link #isHandRaised()}
     * <p>原文：use {@link #isHandRaised()}
     */
    @Deprecated(since = "1.19.2")
    default boolean isChargingAttack() {
        return this.isHandRaised();
    }
}
