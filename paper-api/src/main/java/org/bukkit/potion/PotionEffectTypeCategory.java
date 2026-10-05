package org.bukkit.potion;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示 {@link PotionEffectType} 的类别及其对实体的影响。
 * <p>原文：
 * Represents a category of {@link PotionEffectType} and its effect on an entity.
 */
public enum PotionEffectTypeCategory {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 对实体产生积极影响的有益效果，例如生命恢复、伤害吸收或抗火。
     * <p>原文：
     * Beneficial effects that positively impact an entity, such as Regeneration,
     * Absorption, or Fire Resistance.
     */
    BENEFICIAL,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 对实体产生负面影响的有害效果，例如失明、凋零或漂浮。
     * <p>原文：
     * Harmful effects that negatively impact an entity, such as Blindness, Wither,
     * or Levitation.
     */
    HARMFUL,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 对实体既无积极也无负面影响的中性效果，例如发光或不祥之兆。
     * <p>原文：
     * Neutral effects that have neither a positive nor negative effect on an
     * entity, such as Glowing or Bad Omen.
     */
    NEUTRAL;

}
