package org.bukkit.entity;

import org.bukkit.DyeColor;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表热带鱼.
 * <p>原文：
 * Tropical fish.
 */
public interface TropicalFish extends io.papermc.paper.entity.SchoolableFish { // Paper - Schooling Fish API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取热带鱼的花纹颜色.
     * <p>原文：
     * Gets the color of the fish's pattern.
     *
     * @return 花纹颜色
     * <p>原文：pattern color
     */
    @NotNull
    DyeColor getPatternColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置热带鱼的花纹颜色.
     * <p>原文：
     * Sets the color of the fish's pattern
     *
     * @param color 花纹颜色
     * <p>原文：pattern color
     */
    void setPatternColor(@NotNull DyeColor color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取热带鱼的身体颜色.
     * <p>原文：
     * Gets the color of the fish's body.
     *
     * @return 身体颜色
     * <p>原文：pattern color
     */
    @NotNull
    DyeColor getBodyColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置热带鱼的身体颜色.
     * <p>原文：
     * Sets the color of the fish's body
     *
     * @param color 身体颜色
     * <p>原文：body color
     */
    void setBodyColor(@NotNull DyeColor color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取热带鱼的花纹.
     * <p>原文：
     * Gets the fish's pattern.
     *
     * @return 花纹
     * <p>原文：pattern
     */
    @NotNull
    Pattern getPattern();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置热带鱼的花纹.
     * <p>原文：
     * Sets the fish's pattern
     *
     * @param pattern 新花纹
     * <p>原文：new pattern
     */
    void setPattern(@NotNull Pattern pattern);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表所有不同热带鱼花纹的枚举。请参阅
     * <a href="https://minecraft.wiki/w/Fish">Minecraft Wiki</a>
     * 获取图片。
     * <p>原文：
     * Enumeration of all different fish patterns. Refer to the
     * <a href="https://minecraft.wiki/w/Fish">Minecraft Wiki</a>
     * for pictures.
     */
    public static enum Pattern {

        // Start generate - TropicalFishPattern
        KOB,
        SUNSTREAK,
        SNOOPER,
        DASHER,
        BRINELY,
        SPOTTY,
        FLOPPER,
        STRIPEY,
        GLITTER,
        BLOCKFISH,
        BETTY,
        CLAYFISH;
        // End generate - TropicalFishPattern
    }
}
