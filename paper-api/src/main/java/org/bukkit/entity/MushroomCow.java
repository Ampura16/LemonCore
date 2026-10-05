package org.bukkit.entity;

import java.util.List;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表哞菇( {@link AbstractCow} ).
 * <p>原文：
 * Represents a mushroom {@link Cow}
 */
public interface MushroomCow extends AbstractCow, io.papermc.paper.entity.Shearable { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否设置了自定义药水效果，这些效果将应用于下次从此 {@link MushroomCow} 挤奶获得的可疑炖菜。
     * <p>原文：
     * Checks for the presence of custom potion effects to be applied to the
     * next suspicious stew received from milking this {@link MushroomCow}.
     *
     * @return 如果自定义药水效果应用于炖菜则返回 true
     * <p>原文：true if custom potion effects are applied to the stew
     */
    boolean hasEffectsForNextStew();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取包含所有自定义药水效果的不可变列表，这些效果将应用于下次从此 {@link MushroomCow} 挤奶获得的可疑炖菜。
     * <p>
     * 插件在调用此方法前应检查 hasCustomEffects() 是否返回 true。
     * <p>原文：
     * Gets an immutable list containing all custom potion effects applied to
     * the next suspicious stew received from milking this {@link MushroomCow}.
     * <p>
     * Plugins should check that hasCustomEffects() returns true before calling
     * this method.
     *
     * @return 包含自定义药水效果的不可变列表
     * <p>原文：an immutable list of custom potion effects
     */
    @NotNull
    List<PotionEffect> getEffectsForNextStew();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加一个自定义药水效果，该效果将应用于下次从此 {@link MushroomCow} 挤奶获得的可疑炖菜。
     * <p>原文：
     * Adds a custom potion effect to be applied to the next suspicious stew
     * received from milking this {@link MushroomCow}.
     *
     * @deprecated use {@link #addEffectToNextStew(io.papermc.paper.potion.SuspiciousEffectEntry, boolean)} as PotionEffect suggests that all attributes are used. In fact, only the PotionEffectType and the duration are used.
     * @param effect 要添加的药水效果
     * <p>原文：the potion effect to add
     * @param overwrite 如果为 true，则覆盖任何已存在的相同类型效果
     * <p>原文：true if any existing effect of the same type should be
     * overwritten
     * @return 如果应用于可疑炖菜的效果因此次调用而改变则返回 true
     * <p>原文：true if the effects to be applied to the suspicious stew changed
     * as a result of this call
     */
    @Deprecated(forRemoval = true, since = "1.20.2") // Paper - add overloads to use suspicious effect entry to mushroom cow and suspicious stew meta
    boolean addEffectToNextStew(@NotNull PotionEffect effect, boolean overwrite);

    // Paper start - add overloads to use suspicious effect entry to mushroom cow and suspicious stew meta
    /**
     * Adds a suspicious effect entry to be applied to the next suspicious stew
     * received from milking this {@link MushroomCow}.
     *
     * @param suspiciousEffectEntry the suspicious effect entry to add
     * @param overwrite true if any existing effect of the same type should be
     * overwritten
     * @return true if the effects to be applied to the suspicious stew changed
     * as a result of this call
     */
    boolean addEffectToNextStew(@NotNull io.papermc.paper.potion.SuspiciousEffectEntry suspiciousEffectEntry, boolean overwrite);
    // Paper end - add overloads to use suspicious effect entry to mushroom cow and suspicious stew meta

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除一个自定义药水效果，使其不再应用于下次从此 {@link MushroomCow} 挤奶获得的可疑炖菜。
     * <p>原文：
     * Removes a custom potion effect from being applied to the next suspicious
     * stew received from milking this {@link MushroomCow}.
     *
     * @param type 要移除的药水效果类型
     * <p>原文：the potion effect type to remove
     * @return 如果应用于可疑炖菜的效果因此次调用而改变则返回 true
     * <p>原文：true if the effects to be applied to the suspicious stew changed
     * as a result of this call
     */
    boolean removeEffectFromNextStew(@NotNull PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否有特定的自定义药水效果类型将应用于下次从此 {@link MushroomCow} 挤奶获得的可疑炖菜。
     * <p>原文：
     * Checks for a specific custom potion effect type to be applied to the next
     * suspicious stew received from milking this {@link MushroomCow}.
     *
     * @param type 要检查的药水效果类型
     * <p>原文：the potion effect type to check for
     * @return 如果要生成的可疑炖菜包含此效果则返回 true
     * <p>原文：true if the suspicious stew to be generated has this effect
     */
    boolean hasEffectForNextStew(@NotNull PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除所有将应用于下次从此 {@link MushroomCow} 挤奶获得的可疑炖菜的自定义药水效果。
     * <p>原文：
     * Removes all custom potion effects to be applied to the next suspicious
     * stew received from milking this {@link MushroomCow}.
     */
    void clearEffectsForNextStew();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此牛的变种。
     * <p>原文：
     * Get the variant of this cow.
     *
     * @return 牛的变种
     * <p>原文：cow variant
     */
    @NotNull
    public Variant getVariant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此牛的变种。
     * <p>原文：
     * Set the variant of this cow.
     *
     * @param variant 牛的变种
     * <p>原文：cow variant
     */
    public void setVariant(@NotNull Variant variant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表牛的变种，即其颜色。
     * <p>原文：
     * Represents the variant of a cow - ie its color.
     */
    public enum Variant {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 红色哞菇。
         * <p>原文：
         * Red mushroom cow.
         */
        RED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 棕色哞菇。
         * <p>原文：
         * Brown mushroom cow.
         */
        BROWN;
    }
    // Paper start
    /**
     * Gets how long the effect applied to stew
     * from this mushroom cow is.
     *
     * @return duration of the effect (in ticks)
     * @deprecated Mushroom cows can now hold multiple effects, use {@link #getStewEffects()}
     */
    @Deprecated(forRemoval = true, since = "1.20.2")
    @org.jetbrains.annotations.Contract("-> fail")
    default int getStewEffectDuration() {
        throw new UnsupportedOperationException("Mushroom cows can now hold multiple effects. Use #getStewEffects");
    }

    /**
     * Sets how long the effect applied to stew
     * from this mushroom cow is.
     *
     * @param duration duration of the effect (in ticks)
     * @deprecated Mushroom cows can now hold multiple effects, use {@link #setStewEffects(java.util.List)}
     */
    @Deprecated(forRemoval = true, since = "1.20.2")
    @org.jetbrains.annotations.Contract("_ -> fail")
    default void setStewEffectDuration(int duration) {
        throw new UnsupportedOperationException("Mushroom cows can now hold multiple effects. Use #setStewEffects");
    }

    /**
     * Gets the type of effect applied to stew
     * from this mushroom cow is.
     *
     * @return effect type, or null if an effect is currently not set
     * @deprecated Mushroom cows can now hold multiple effects, use {@link #getStewEffects()}
     * @throws UnsupportedOperationException
     */
    @Deprecated(forRemoval = true, since = "1.20.2")
    @org.jetbrains.annotations.Contract("-> fail")
    default org.bukkit.potion.PotionEffectType getStewEffectType() {
        throw new UnsupportedOperationException("Mushroom cows can now hold multiple effects. Use #getStewEffects");
    }

    /**
     * Sets the type of effect applied to stew
     * from this mushroom cow is.
     *
     * @param type new effect type
     *             or null if this cow does not give effects
     * @deprecated Mushroom cows can now hold multiple effects, use {@link #setStewEffects(java.util.List)}
     * @throws UnsupportedOperationException
     */
    @Deprecated(forRemoval = true, since = "1.20.2")
    @org.jetbrains.annotations.Contract("_ -> fail")
    default void setStewEffect(@org.jetbrains.annotations.Nullable org.bukkit.potion.PotionEffectType type) {
        throw new UnsupportedOperationException("Mushroom cows can now hold multiple effects. Use #setStewEffects");
    }

    /**
     * Returns an immutable collection of the effects applied to stew
     * items for this mushroom cow.
     *
     * @return immutable effect entry collection
     */
    java.util.@NotNull @org.jetbrains.annotations.Unmodifiable List<io.papermc.paper.potion.SuspiciousEffectEntry> getStewEffects();

    /**
     * Sets effects applied to stew items for this mushroom cow.
     *
     * @param effects effect entry list
     */
    void setStewEffects(java.util.@NotNull List<io.papermc.paper.potion.SuspiciousEffectEntry> effects);
    // Paper end
}
