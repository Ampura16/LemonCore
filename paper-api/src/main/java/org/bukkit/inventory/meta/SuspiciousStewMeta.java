package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表能够设置自定义药水效果的迷之炖菜.
 * <p>原文：
 * Represents a suspicious stew that can have custom effects.
 */
public interface SuspiciousStewMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此迷之炖菜是否拥有药水效果.
     * <p>原文：
     * Checks for the presence of custom potion effects.
     *
     * @return 是否拥有药水效果
     * <p>原文：true if custom potion effects are applied
     */
    boolean hasCustomEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个迷之炖菜的全部自定义效果.
     * <p>
     * 插件应该在调用这个方法之前检查 hasCustomEffects() 是否返回 true.
     * <p>原文：
     * Gets an immutable list containing all custom potion effects applied to
     * this suspicious stew.
     * <p>
     * Plugins should check that hasCustomEffects() returns true before calling
     * this method.
     *
     * @return 自定义效果列表 (不可变)
     * <p>原文：the immutable list of custom potion effects
     */
    @NotNull
    List<PotionEffect> getCustomEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加一个自定义药水效果到这个迷之炖菜上.
     * <p>原文：
     * Adds a custom potion effect to this suspicious stew.
     *
     * @deprecated use {@link #addCustomEffect(io.papermc.paper.potion.SuspiciousEffectEntry, boolean)} as PotionEffect suggests that all attributes are used. In fact, only the PotionEffectType and the duration are used.
     * @param effect 要添加的药水效果
     * <p>原文：the potion effect to add
     * @param overwrite 如果有相同类型的效果存在想要覆盖就设为 true
     * <p>原文：true if any existing effect of the same type should be
     * overwritten
     * @return 如果迷之炖菜的属性改变了则为 true
     * <p>原文：true if the suspicious stew meta changed as a result of this call
     */
    @Deprecated // Paper - add overloads to use suspicious effect entry to mushroom cow and suspicious stew meta
    boolean addCustomEffect(@NotNull PotionEffect effect, boolean overwrite);

    // Paper start - add overloads to use suspicious effect entry to mushroom cow and suspicious stew meta
    /**
     * Adds a custom potion effect to this suspicious stew.
     *
     * @param suspiciousEffectEntry the suspicious effect entry to add
     * @param overwrite true if any existing effect of the same type should be
     * overwritten
     * @return true if the suspicious stew meta changed as a result of this call
     * as a result of this call
     */
    boolean addCustomEffect(@NotNull io.papermc.paper.potion.SuspiciousEffectEntry suspiciousEffectEntry, boolean overwrite);
    // Paper end - add overloads to use suspicious effect entry to mushroom cow and suspicious stew meta

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除这个迷之炖菜的一个自定义效果.
     * <p>原文：
     * Removes a custom potion effect from this suspicious stew.
     *
     * @param type 要移除的药水效果类型
     * <p>原文：the potion effect type to remove
     * @return 如果迷之炖菜的属性改变了则为 true
     * <p>原文：true if the suspicious stew meta changed as a result of this call
     */
    boolean removeCustomEffect(@NotNull PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查这个迷之炖菜是否有指定的药水效果.
     * <p>原文：
     * Checks for a specific custom potion effect type on this suspicious stew.
     *
     * @param type 要检查的药水效果
     * <p>原文：the potion effect type to check for
     * @return 存在此药水效果则返回 true
     * <p>原文：true if the suspicious stew has this effect
     */
    boolean hasCustomEffect(@NotNull PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除这个迷之炖菜的全部自定义药水效果.
     * <p>原文：
     * Removes all custom potion effects from this suspicious stew.
     *
     * @return 如果迷之炖菜的属性改变了则为 true
     * <p>原文：true if the suspicious stew meta changed as a result of this call
     */
    boolean clearCustomEffects();

    @Override
    SuspiciousStewMeta clone();
}
