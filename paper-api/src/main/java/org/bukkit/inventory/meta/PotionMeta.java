package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.Color;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表药水和有自定义药水效果的物品.
 * <p>原文：
 * Represents a potion or item that can have custom effects.
 */
public interface PotionMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置底层药水数据.
     * <p>原文：
     * Sets the underlying potion data
     *
     * @param data 要设置的基础药水状态的 PotionData
     * <p>原文：PotionData to set the base potion state to
     * @deprecated 升级/延长的药水现在是独立的 {@link PotionType}，请使用 {@link #setBasePotionType} 代替.
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
     * @deprecated 升级/延长的药水现在是独立的 {@link PotionType}，请使用 {@link #getBasePotionType()} 代替.
     * <p>原文：Upgraded / extended potions are now their own {@link PotionType} use {@link #getBasePotionType()} instead.
     */
    @Nullable
    @Deprecated(since = "1.20.6", forRemoval = true)
    PotionData getBasePotionData();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置基础药水(主药水)的类型.
     * <p>原文：
     * Sets the underlying potion type
     *
     * @param type 基础药水类型
     * <p>原文：PotionType to set the base potion state to
     */
    void setBasePotionType(@Nullable PotionType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回关于这个基础药水(主药水)的药水类型.
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
     * 检查是否存在基础药水类型.
     * <p>原文：
     * Checks for the presence of a base potion type
     *
     * @return 如果存在基础药水类型则为 true
     * <p>原文：true if a base potion type is present
     */
    boolean hasBasePotionType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测这个药水是否存在药水效果.
     * <p>原文：
     * Checks for the presence of custom potion effects.
     *
     * @return 这个药水是否存在药水效果
     * <p>原文：true if custom potion effects are applied
     */
    boolean hasCustomEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个药水的全部自定义效果.
     * <p>
     * 插件应该在调用这个方法之前检查 hasCustomEffects() 是否返回 true.
     * <p>原文：
     * Gets an immutable list containing all custom potion effects applied to
     * this potion.
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
     * All effects that this potion meta holds.
     * <p>
     * This is a combination of the base potion type and any custom effects.
     *
     * @return an unmodifiable list of all effects.
     */
    @NotNull
    @Unmodifiable List<PotionEffect> getAllEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向此药水添加一个自定义药水效果.
     * <p>原文：
     * Adds a custom potion effect to this potion.
     *
     * @param effect 要添加的药水效果
     * <p>原文：the potion effect to add
     * @param overwrite 如要覆盖已有的同类药水效果就设为 true
     * <p>原文：true if any existing effect of the same type should be
     * overwritten
     * @return 如果药水的属性改变了则为 true
     * <p>原文：true if the potion meta changed as a result of this call
     */
    boolean addCustomEffect(@NotNull PotionEffect effect, boolean overwrite);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除这个药水的一个自定义效果.
     * <p>原文：
     * Removes a custom potion effect from this potion.
     *
     * @param type 要移除的药水效果类型
     * <p>原文：the potion effect type to remove
     * @return 如果药水的属性改变了则为 true
     * <p>原文：true if the potion meta changed as a result of this call
     */
    boolean removeCustomEffect(@NotNull PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查这个药水是否有指定的药水效果.
     * <p>原文：
     * Checks for a specific custom potion effect type on this potion.
     *
     * @param type 要检查的药水效果
     * <p>原文：the potion effect type to check for
     * @return 存在此药水效果则返回 true
     * <p>原文：true if the potion has this effect
     */
    boolean hasCustomEffect(@NotNull PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移动一个药水效果至这个药水效果列表的顶端.
     * <p>
     * 这将会使客户端上的药水名称显示成设置的药水效果.
     * <p>
     * 译注: 第一句不好理解? 比如有三个效果, 有个效果在最后面是最主要的效果, 我们就可以把这个效果移动到最顶上, 这样玩家第一眼看到的就是这个效果啦.
     * <p>原文：
     * Moves a potion effect to the top of the potion effect list.
     * <p>
     * This causes the client to display the potion effect in the potion's name.
     *
     * @param type 要移动的药水效果
     * <p>原文：the potion effect type to move
     * @return 如果药水的属性改变了则为 true
     * <p>原文：true if the potion meta changed as a result of this call
     * @deprecated 请使用 {@link #setBasePotionType(org.bukkit.potion.PotionType)}
     * <p>原文：use {@link #setBasePotionType(org.bukkit.potion.PotionType)}
     */
    @Deprecated(since = "1.9")
    boolean setMainEffect(@NotNull PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除这个药水的全部自定义药水效果.
     * <p>原文：
     * Removes all custom potion effects from this potion.
     *
     * @return 如果药水的属性改变了则为 true
     * <p>原文：true if the potion meta changed as a result of this call
     */
    boolean clearCustomEffects();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查药水是否设置了自定义颜色.
     * <p>原文：
     * Checks for existence of a potion color.
     *
     * @return 是否设置了自定义颜色
     * <p>原文：true if this has a custom potion color
     */
    boolean hasColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取为这瓶药水设置的颜色. 此颜色可在物品栏中药水物品的格子内观察到.
     * <p>
     * 插件应该在调用这个方法之前检查 hasColor() 是否返回 <code>true</code>.
     * <p>原文：
     * Gets the potion color that is set. A custom potion color will alter the
     * display of the potion in an inventory slot.
     * <p>
     * Plugins should check that hasColor() returns <code>true</code> before
     * calling this method.
     *
     * @return 药水颜色
     * <p>原文：the potion color that is set
     */
    @Nullable
    Color getColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置药水的颜色. 此颜色可在物品栏中药水物品的格子内观察到.
     * <p>原文：
     * Sets the potion color. A custom potion color will alter the display of
     * the potion in an inventory slot.
     *
     * @param color 颜色
     * <p>原文：the color to set
     */
    void setColor(@Nullable Color color);

    /**
     * Computes the effective colour of this potion meta.
     * <p>
     * This blends all custom effects, or uses a default fallback color.
     *
     * @return the effective potion color
     */
    @NotNull
    Color computeEffectiveColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否存在自定义药水名称翻译后缀.
     * <p>原文：
     * Checks for existence of a custom potion name translation suffix.
     *
     * @deprecated conflicting name, use {@link #hasCustomPotionName()}
     * @return 如果存在自定义药水名称则为 true
     * <p>原文：true if this has a custom potion name
     */
    @Deprecated(forRemoval = true, since = "1.21.4")
    default boolean hasCustomName() {
        return this.hasCustomPotionName();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取已设置的药水名称翻译后缀.
     * <p>
     * 插件应该在调用此方法之前检查 hasCustomName() 是否返回 <code>true</code>.
     * <p>原文：
     * Gets the potion name translation suffix that is set.
     * <p>
     * Plugins should check that {@link #hasCustomPotionName()} returns {@code true}
     * before calling this method.
     *
     * @deprecated conflicting name, use {@link #getCustomPotionName()}
     * @return 已设置的药水名称
     * <p>原文：the potion name that is set
     */
    @Deprecated(forRemoval = true, since = "1.21.4")
    @Nullable
    default String getCustomName() {
        return this.getCustomPotionName();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置药水名称翻译后缀.
     * <p>原文：
     * Sets the potion name translation suffix.
     *
     * @deprecated conflicting name, use {@link #setCustomPotionName(String)}
     * @param name 要设置的名称
     * <p>原文：the name to set
     */
    @Deprecated(forRemoval = true, since = "1.21.4")
    default void setCustomName(@Nullable String name) {
        this.setCustomPotionName(name);
    }

    /**
     * Checks for existence of a custom potion name translation suffix.
     *
     * @return true if this has a custom potion name
     */
    boolean hasCustomPotionName();

    /**
     * Gets the potion name translation suffix that is set.
     * <p>
     * Plugins should check that {@link #hasCustomPotionName()} returns {@code true}
     * before calling this method.
     *
     * @return the potion name that is set
     */
    @Nullable
    String getCustomPotionName();

    /**
     * Sets the potion name translation suffix.
     *
     * @param name the name to set
     */
    void setCustomPotionName(@Nullable String name);

    @Override
    PotionMeta clone();
}
