package io.papermc.paper.potion;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示与持续时间配对的 {@link PotionEffectType}。
 * <p>原文：
 * Represents a {@link PotionEffectType} paired with a duration.
 */
@NullMarked
public sealed interface SuspiciousEffectEntry permits SuspiciousEffectEntryImpl {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取效果类型。
     * <p>原文：
     * Gets the effect type.
     *
     * @return 效果类型
     * <p>原文：effect type
     */
    PotionEffectType effect();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此效果实例的持续时间。
     * <p>原文：
     * Gets the duration for this effect instance.
     *
     * @return 持续时间（刻）或 {@link PotionEffect#INFINITE_DURATION}
     * <p>原文：duration (in ticks) or {@link PotionEffect#INFINITE_DURATION}
     */
    int duration();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建 SuspiciousEffectEntry 的新实例。
     * <p>原文：
     * Creates a new instance of SuspiciousEffectEntry.
     *
     * @param effectType 效果类型
     * <p>原文：effect type
     * @param duration 持续时间（刻）或 {@link PotionEffect#INFINITE_DURATION}
     * <p>原文：duration (in ticks) or {@link PotionEffect#INFINITE_DURATION}
     * @return 条目的新实例
     * <p>原文：new instance of an entry
     */
    @Contract(value = "_, _ -> new", pure = true)
    static SuspiciousEffectEntry create(final PotionEffectType effectType, final int duration) {
        return new SuspiciousEffectEntryImpl(effectType, duration);
    }
}
