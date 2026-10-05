package io.papermc.paper.registry.data;

import io.papermc.paper.registry.RegistryBuilder;
import org.bukkit.damage.DamageEffect;
import org.bukkit.damage.DamageScaling;
import org.bukkit.damage.DamageType;
import org.bukkit.damage.DeathMessageType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link DamageType} 类型的以数据为中心、特定于版本的注册表条目。
 * <p>原文：
 * A data-centric version-specific registry entry for the {@link DamageType} type.
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface DamageTypeRegistryEntry {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供死亡消息翻译键的一部分。（death.attack.&lt;message_id&gt;）
     * <p>
     * <strong>注意</strong> 仅当 {@link #deathMessageType()} 为 {@link DeathMessageType#DEFAULT} 时才使用该翻译键
     * <p>原文：
     * Provides part of the death message translation key. (death.attack.&lt;message_id&gt;)
     * <p>
     * <strong>Note</strong> The translation key is only used if
     * {@link #deathMessageType()} is {@link DeathMessageType#DEFAULT}
     *
     * @return 翻译键的一部分
     * <p>原文：part of the translation key
     */
    String messageId();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此伤害类型的 {@link DamageScaling}。
     * <p>原文：
     * Provides the {@link DamageScaling} for this damage type.
     *
     * @return 伤害缩放
     * <p>原文：the damage scaling
     */
    DamageScaling damageScaling();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此伤害类型造成的饥饿消耗量。
     * <p>原文：
     * Provides the amount of hunger exhaustion caused by this damage type.
     *
     * @return 消耗度
     * <p>原文：the exhaustion
     */
    float exhaustion();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此伤害类型的 {@link DamageEffect}。
     * <p>原文：
     * Provides the {@link DamageEffect} for this damage type.
     *
     * @return 伤害效果
     * <p>原文：the damage effect
     */
    DamageEffect damageEffect();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此伤害类型的 {@link DeathMessageType}。
     * <p>原文：
     * Provides the {@link DeathMessageType} for this damage type.
     *
     * @return 死亡消息类型
     * <p>原文：the death message type
     */
    DeathMessageType deathMessageType();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 插件可在相应注册表事件中修改的 {@link DamageTypeRegistryEntry} 的可变构建器。
     * <p>
     * 每个构建器必须提供以下值：
     * <ul>
     * <li>{@link #messageId(String)}</li>
     * <li>{@link #exhaustion(float)}</li>
     * <li>{@link #damageScaling(DamageScaling)}</li>
     * </ul>
     * <p>原文：
     * A mutable builder for the {@link DamageTypeRegistryEntry} plugins may change in applicable registry events.
     * <p>
     * The following values are required for each builder:
     * <ul>
     *     <li>{@link #messageId(String)}</li>
     *     <li>{@link #exhaustion(float)}</li>
     *     <li>{@link #damageScaling(DamageScaling)}</li>
     * </ul>
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DamageTypeRegistryEntry, RegistryBuilder<DamageType> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置死亡消息翻译键的一部分。
         * <p>原文：
         * Sets part of the death message translation key.
         *
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see DamageTypeRegistryEntry#messageId()
         * @see DamageType#getTranslationKey()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder messageId(String messageId);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此伤害类型造成的饥饿消耗量。
         * <p>原文：
         * Sets the amount of hunger exhaustion caused by this damage type.
         *
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see DamageTypeRegistryEntry#exhaustion()
         * @see DamageType#getExhaustion()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder exhaustion(float exhaustion);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此伤害类型的 {@link DamageScaling}。
         * <p>原文：
         * Sets the {@link DamageScaling} for this damage type.
         *
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see DamageTypeRegistryEntry#damageScaling()
         * @see DamageType#getDamageScaling()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder damageScaling(DamageScaling scaling);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此伤害类型的 {@link DamageEffect}。
         * <p>原文：
         * Sets the {@link DamageEffect} for this damage type.
         *
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see DamageTypeRegistryEntry#damageEffect()
         * @see DamageType#getDamageEffect()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder damageEffect(DamageEffect effect);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此伤害类型的 {@link DeathMessageType}。
         * <p>原文：
         * Sets the {@link DeathMessageType} for this damage type.
         *
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see DamageTypeRegistryEntry#deathMessageType()
         * @see DamageType#getDeathMessageType()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder deathMessageType(DeathMessageType deathMessageType);
    }
}
