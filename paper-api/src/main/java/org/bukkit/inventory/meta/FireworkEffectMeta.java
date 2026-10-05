package org.bukkit.inventory.meta;

import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表可以存储单个FireworkEffect的meta.例如包含{@link Material#FIREWORK_STAR}.
 * <p>原文：
 * Represents a meta that can store a single FireworkEffect. An example
 * includes {@link Material#FIREWORK_STAR}.
 */
public interface FireworkEffectMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个meta的烟花效果.
     * <p>原文：
     * Sets the firework effect for this meta.
     *
     * @param effect 要设置的效果，或者设置null来清空效果
     * <p>原文：the effect to set, or null to indicate none.
     */
    void setEffect(@Nullable FireworkEffect effect);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测这个meta是否拥有烟花效果.
     * <p>原文：
     * Checks if this meta has an effect.
     *
     * @return true表示这个meta拥有烟花效果,否则为false
     * <p>原文：true if this meta has an effect, false otherwise
     */
    boolean hasEffect();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个meta的烟花效果.
     * <p>原文：
     * Gets the firework effect for this meta.
     *
     * @return 当前的效果,如果没有则为null
     * <p>原文：the current effect, or null if none
     */
    @Nullable
    FireworkEffect getEffect();

    @Override
    @NotNull
    FireworkEffectMeta clone();
}
