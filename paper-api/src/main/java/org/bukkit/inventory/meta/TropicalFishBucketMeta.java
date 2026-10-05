package org.bukkit.inventory.meta;

import org.bukkit.DyeColor;
import org.bukkit.entity.TropicalFish;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表热带鱼桶.
 * <p>原文：
 * Represents a bucket of tropical fish.
 */
public interface TropicalFishBucketMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取热带鱼花纹的颜色.
     * <p>
     * 插件应该在调用这个方法之前检查 hasVariant() 是否返回 <code>true</code>.
     * <p>原文：
     * Gets the color of the fish's pattern.
     * <p>
     * Plugins should check that hasVariant() returns <code>true</code> before
     * calling this method.
     *
     * @return 花纹颜色
     * <p>原文：pattern color
     */
    @NotNull
    DyeColor getPatternColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置热带鱼花纹的颜色.
     * <p>
     * 当 hasVariant() 返回 <code>false</code> 时设置, 将初始化其它值为未指定的默认值.
     * <p>原文：
     * Sets the color of the fish's pattern.
     * <p>
     * Setting this when hasVariant() returns <code>false</code> will initialize
     * all other values to unspecified defaults.
     *
     * @param color 花纹颜色
     * <p>原文：pattern color
     */
    void setPatternColor(@NotNull DyeColor color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取热带鱼身体的颜色.
     * <p>
     * 插件应该在调用这个方法之前检查 hasVariant() 是否返回 <code>true</code>.
     * <p>原文：
     * Gets the color of the fish's body.
     * <p>
     * Plugins should check that hasVariant() returns <code>true</code> before
     * calling this method.
     *
     * @return 热带鱼身体的颜色
     * <p>原文：pattern color
     */
    @NotNull
    DyeColor getBodyColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置热带鱼身体的颜色.
     * <p>
     * 当 hasVariant() 返回 <code>false</code> 时设置, 将初始化其它值为未指定的默认值.
     * <p>原文：
     * Sets the color of the fish's body.
     * <p>
     * Setting this when hasVariant() returns <code>false</code> will initialize
     * all other values to unspecified defaults.
     *
     * @param color 热带鱼身体的颜色
     * <p>原文：body color
     */
    void setBodyColor(@NotNull DyeColor color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取热带鱼的花纹.
     * <p>
     * 插件应该在调用这个方法之前检查 hasVariant() 是否返回 <code>true</code>.
     * <p>原文：
     * Gets the fish's pattern.
     * <p>
     * Plugins should check that hasVariant() returns <code>true</code> before
     * calling this method.
     *
     * @return 热带鱼的花纹
     * <p>原文：pattern
     */
    @NotNull
    TropicalFish.Pattern getPattern();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置热带鱼的花纹.
     * <p>
     * 当 hasVariant() 返回 <code>false</code> 时设置, 将初始化其它值为未指定的默认值.
     * <p>原文：
     * Sets the fish's pattern.
     * <p>
     * Setting this when hasVariant() returns <code>false</code> will initialize
     * all other values to unspecified defaults.
     *
     * @param pattern 新花纹
     * <p>原文：new pattern
     */
    void setPattern(@NotNull TropicalFish.Pattern pattern);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否存在 variant 标签, 此标签指示会生成何种热带鱼.
     * <p>原文：
     * Checks for existence of a variant tag indicating a specific fish will be
     * spawned.
     *
     * @return 是否存在 variant 标签
     * <p>原文：if there is a variant
     */
    boolean hasVariant();

    @Override
    @NotNull
    TropicalFishBucketMeta clone();
}
