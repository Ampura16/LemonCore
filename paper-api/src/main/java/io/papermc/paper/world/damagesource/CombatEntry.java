package io.papermc.paper.world.damagesource;

import io.papermc.paper.InternalAPIBridge;
import org.bukkit.damage.DamageSource;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示一条战斗记录
 * <p>原文：
 * Represents a combat entry
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface CombatEntry {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取伤害来源。
     * <p>原文：
     * Gets the damage source.
     *
     * @return 伤害来源
     * <p>原文：the damage source
     */
    DamageSource getDamageSource();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取造成的伤害量。
     * <p>原文：
     * Gets the amount of damage caused.
     *
     * @return 造成的伤害量
     * <p>原文：the amount of damage caused
     */
    float getDamage();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取受到此次伤害时的摔落位置类型。
     * <p>原文：
     * Gets the fall location type at the time of this damage.
     *
     * @return 摔落位置类型
     * <p>原文：the fall location type
     */
    @Nullable FallLocationType getFallLocationType();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取受到此次伤害时的摔落距离。
     * <p>原文：
     * Gets the fall distance at the time of this damage.
     *
     * @return 摔落距离
     * <p>原文：the fall distance
     */
    float getFallDistance();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的战斗记录。
     * <p>
     * 摔落位置和摔落距离将根据实体的当前状态计算。
     * <p>原文：
     * Creates a new combat entry.
     * <p>
     * The fall location and fall distance will be calculated from the entity's current state.
     *
     * @param entity 实体
     * <p>原文：entity
     * @param damageSource 伤害来源
     * <p>原文：damage source
     * @param damage 伤害量
     * <p>原文：damage amount
     * @return 战斗记录
     * <p>原文：combat entry
     * @see #combatEntry(DamageSource, float, FallLocationType, float)
     */
    static CombatEntry combatEntry(final LivingEntity entity, final DamageSource damageSource, final float damage) {
        return InternalAPIBridge.get().createCombatEntry(entity, damageSource, damage);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的战斗记录
     * <p>原文：
     * Creates a new combat entry
     *
     * @param damageSource 伤害来源
     * <p>原文：damage source
     * @param damage 伤害量
     * <p>原文：damage amount
     * @param fallLocationType 摔落位置类型
     * <p>原文：fall location type
     * @param fallDistance 摔落距离
     * <p>原文：fall distance
     * @return 新的战斗记录
     * <p>原文：a new combat entry
     * @see CombatTracker#calculateFallLocationType()
     * @see Entity#getFallDistance()
     */
    static CombatEntry combatEntry(
        final DamageSource damageSource,
        final float damage,
        @Nullable final FallLocationType fallLocationType,
        final float fallDistance
    ) {
        return InternalAPIBridge.get().createCombatEntry(damageSource, damage, fallLocationType, fallDistance);
    }

}
