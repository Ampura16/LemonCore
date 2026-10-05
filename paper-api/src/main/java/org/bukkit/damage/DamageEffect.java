package org.bukkit.damage;

import com.google.common.base.Preconditions;
import io.papermc.paper.InternalAPIBridge;
import org.bukkit.Sound;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表造成伤害时发生的效果类型. 目前, 效果仅决定播放的声音.
 * <p>原文：
 * Represents a type of effect that occurs when damage is inflicted. Currently,
 * effects only determine the sound that plays.
 */
public interface DamageEffect {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 默认的伤害效果.
     * <p>原文：
     * The default damage effect.
     */
    public static final DamageEffect HURT = getDamageEffect("hurt");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 荆棘.
     * <p>原文：
     * Thorns.
     */
    public static final DamageEffect THORNS = getDamageEffect("thorns");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 溺水.
     * <p>原文：
     * Drowning.
     */
    public static final DamageEffect DROWNING = getDamageEffect("drowning");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 单次燃烧刻(火焰、岩浆等).
     * <p>原文：
     * A single burn tick (fire, lava, etc.).
     */
    public static final DamageEffect BURNING = getDamageEffect("burning");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 被浆果丛刺伤.
     * <p>原文：
     * Poked by a berry bush.
     */
    public static final DamageEffect POKING = getDamageEffect("poking");
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 冻结刻(细雪).
     * <p>原文：
     * Freeze tick (powder snow).
     */
    public static final DamageEffect FREEZING = getDamageEffect("freezing");

    @NotNull
    private static DamageEffect getDamageEffect(@NotNull String key) {
        return Preconditions.checkNotNull(InternalAPIBridge.get().getDamageEffect(key), "No DamageEffect found for %s. This is a bug.", key);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此{@link DamageEffect}播放的{@link Sound}.
     * <p>原文：
     * Get the {@link Sound} played for this {@link DamageEffect}.
     *
     * @return 声音
     * <p>原文：the sound
     */
    @NotNull
    public Sound getSound();
}
