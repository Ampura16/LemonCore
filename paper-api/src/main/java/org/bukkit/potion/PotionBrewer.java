package org.bukkit.potion;

import java.util.Collection;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表构建 {@link PotionEffect} 对象的酿造台.
 * (译注:此“酿造台”与游戏中的酿造台无任何关系, 只是对PotionEffect类的包装,
 * 且一般只在1.9版本前的API中使用).
 * <p>原文：
 * Used to manage custom {@link io.papermc.paper.potion.PotionMix}s.
 */
public interface PotionBrewer {

    // Paper start
    /**
     * Adds a new potion mix recipe.
     *
     * @param potionMix the potion mix to add
     */
    void addPotionMix(@NotNull io.papermc.paper.potion.PotionMix potionMix);

    /**
     * Removes a potion mix recipe.
     *
     * @param key the key of the mix to remove
     */
    void removePotionMix(@NotNull org.bukkit.NamespacedKey key);

    /**
     * Resets potion mixes to their default, removing all custom ones.
     */
    void resetPotionMixes();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建指定类型的{@link PotionEffect 药水效果}, 应用指定的时长.
     * <p>原文：
     * Creates a {@link PotionEffect} from the given {@link PotionEffectType},
     * applying duration modifiers and checks.
     *
     * @param potion 药水效果类型
     * <p>原文：The type of potion
     * @param duration 持续时长 (单位为tick)
     * <p>原文：The duration in ticks
     * @param amplifier 效果倍率
     * <p>原文：The amplifier of the effect
     * @return 药水效果对象
     * <p>原文：The resulting potion effect
     * @deprecated use {@link PotionEffectType#createEffect(int, int)} instead.
     */
    @Deprecated(forRemoval = true, since = "1.20.5") // Paper
    @NotNull
    // Paper start - make default
    default PotionEffect createEffect(@NotNull PotionEffectType potion, int duration, int amplifier) {
        return potion.createEffect(duration, amplifier);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回根据指定数据值可应用的效果列表.
     * <p>原文：
     * Returns a collection of {@link PotionEffect} that would be applied from
     * a potion with the given data value.
     *
     * @param damage 药水数据值
     * <p>原文：The data value of the potion
     * @return 效果列表
     * <p>原文：The list of effects
     * @deprecated 无任何作用
     * <p>原文：Non-Functional
     */
    @Deprecated(since = "1.6.2", forRemoval = true) // Paper
    @NotNull
    // Paper start - make default
    default Collection<PotionEffect> getEffectsFromDamage(final int damage) {
        return new java.util.ArrayList<>();
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回从指定药水种类中可应用的效果的列表.
     * <p>原文：
     * Returns a collection of {@link PotionEffect} that would be applied from
     * a potion with the given type.
     *
     * @param type 药水种类
     * <p>原文：The type of the potion
     * @param upgraded 是否为时长延长的药水
     * <p>原文：Whether the potion is upgraded
     * @param extended 是否为升阶的药水
     * <p>原文：Whether the potion is extended
     * @return 效果列表
     * <p>原文：The list of effects
     * @deprecated 时长延长/升阶的药水已经有其专属的 {@link PotionType}. 请使用 {@link PotionType#getPotionEffects()}
     * <p>原文：Upgraded / extended potions are now their own {@link PotionType} use {@link PotionType#getPotionEffects()} instead
     */
    @NotNull
    @Deprecated(since = "1.20.2", forRemoval = true) // Paper
    public Collection<PotionEffect> getEffects(@NotNull PotionType type, boolean upgraded, boolean extended);
}
