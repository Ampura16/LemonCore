package org.bukkit.entity;

import java.util.List;
import org.bukkit.Color;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示一只箭矢.
 */
public interface Arrow extends AbstractArrow {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置底层药水数据.
     * <p>原文：
     * Sets the underlying potion data
     *
     * @param data 要设置的基础药水状态的 PotionData
     * <p>原文：PotionData to set the base potion state to
     * @deprecated 升级/延长的药水现在是独立的 {@link PotionType}，请使用 {@link #setBasePotionType} 替代.
     * <p>原文：Upgraded / extended potions are now their own {@link PotionType} use {@link #setBasePotionType} instead.
     */
    @Deprecated(since = "1.20.6", forRemoval = true)
    void setBasePotionData(@Nullable PotionData data);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回关于基础药水的药水数据.
     * <p>原文：
     * Returns the potion data about the base potion
     *
     * @return PotionData 对象
     * <p>原文：a PotionData object
     * @deprecated 升级/延长的药水现在是独立的 {@link PotionType}，请使用 {@link #getBasePotionType()} 替代.
     * <p>原文：Upgraded / extended potions are now their own {@link PotionType} use {@link #getBasePotionType()} instead.
     */
    @Nullable
    @Deprecated(since = "1.20.6", forRemoval = true)
    PotionData getBasePotionData();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置底层药水类型.
     * <p>原文：
     * Sets the underlying potion type
     *
     * @param type 要设置的基础药水状态的 PotionType
     * <p>原文：PotionType to set the base potion state to
     */
    void setBasePotionType(@Nullable PotionType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回关于基础药水的药水类型.
     * <p>原文：
     * Returns the potion type about the base potion
     *
     * @return PotionType 对象
     * <p>原文：a PotionType object
     */
    @Nullable
    PotionType getBasePotionType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此箭矢的颜色.
     * <p>原文：
     * Gets the color of this arrow.
     *
     * @return 箭矢的 {@link Color}，如果未设置颜色则为 null
     * <p>原文：arrow {@link Color} or null if not color is set
     */
    @Nullable
    Color getColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此箭矢的颜色. 将作为其粒子的着色应用.
     * <p>原文：
     * Sets the color of this arrow. Will be applied as a tint to its particles.
     *
     * @param color 箭矢颜色，null 表示清除颜色
     * <p>原文：arrow color, null to clear the color
     */
    void setColor(@Nullable Color color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否存在自定义药水效果.
     * <p>原文：
     * Checks for the presence of custom potion effects.
     *
     * @return 如果应用了自定义药水效果则为 true
     * <p>原文：true if custom potion effects are applied
     */
    boolean hasCustomEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取应用于此箭矢的所有自定义药水效果的不可变列表.
     * <p>
     * 插件应在调用此方法前检查 hasCustomEffects() 是否返回 true.
     * <p>原文：
     * Gets an immutable list containing all custom potion effects applied to
     * this arrow.
     * <p>
     * Plugins should check that hasCustomEffects() returns true before calling
     * this method.
     *
     * @return 自定义药水效果的不可变列表
     * <p>原文：the immutable list of custom potion effects
     */
    @NotNull
    List<PotionEffect> getCustomEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加自定义药水效果到此箭矢.
     * <p>原文：
     * Adds a custom potion effect to this arrow.
     *
     * @param effect 要添加的药水效果
     * <p>原文：the potion effect to add
     * @param overwrite 如果为 true，则覆盖同类型的现有效果
     * <p>原文：true if any existing effect of the same type should be
     * overwritten
     * @return 如果因此次调用添加了效果则为 true
     * <p>原文：true if the effect was added as a result of this call
     */
    boolean addCustomEffect(@NotNull PotionEffect effect, boolean overwrite);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从此箭矢移除自定义药水效果.
     * <p>原文：
     * Removes a custom potion effect from this arrow.
     *
     * @param type 要移除的药水效果类型
     * <p>原文：the potion effect type to remove
     * @return 如果因此次调用移除了效果则为 true
     * <p>原文：true if the effect was removed as a result of this call
     * @throws IllegalArgumentException 如果此操作会使箭矢处于无自定义效果且药水类型为 UNCRAFTABLE 的状态
     * <p>原文：if this operation would leave the Arrow
     * in a state with no Custom Effects and PotionType.UNCRAFTABLE
     */
    boolean removeCustomEffect(@NotNull PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此箭矢上是否存在特定的自定义药水效果类型.
     * <p>原文：
     * Checks for a specific custom potion effect type on this arrow.
     *
     * @param type 要检查的药水效果类型
     * <p>原文：the potion effect type to check for
     * @return 如果药水有此效果则为 true
     * <p>原文：true if the potion has this effect
     */
    boolean hasCustomEffect(@Nullable PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除此箭矢的所有自定义药水效果.
     * <p>原文：
     * Removes all custom potion effects from this arrow.
     *
     * @throws IllegalArgumentException 如果此操作会使箭矢处于无自定义效果且药水类型为 UNCRAFTABLE 的状态
     * <p>原文：if this operation would leave the Arrow
     * in a state with no Custom Effects and PotionType.UNCRAFTABLE
     */
    void clearCustomEffects();
}
