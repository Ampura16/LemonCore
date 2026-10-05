package org.bukkit.entity;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 美西螈.
 * <p>原文：
 * An Axolotl.
 */
public interface Axolotl extends Animals, io.papermc.paper.entity.Bucketable { // Paper - Bucketable API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此美西螈是否正在装死.
     *
     * 美西螈在水下受到伤害时可能会装死.
     * <p>原文：
     * Gets if this axolotl is playing dead.
     *
     * An axolotl may play dead when it is damaged underwater.
     *
     * @return 装死状态
     * <p>原文：playing dead status
     */
    boolean isPlayingDead();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此美西螈是否正在装死.
     *
     * 美西螈在水下受到伤害时可能会装死.
     * <p>原文：
     * Sets if this axolotl is playing dead.
     *
     * An axolotl may play dead when it is damaged underwater.
     *
     * @param playingDead 装死状态
     * <p>原文：playing dead status
     */
    void setPlayingDead(boolean playingDead);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此美西螈的变种.
     * <p>原文：
     * Get the variant of this axolotl.
     *
     * @return 美西螈变种
     * <p>原文：axolotl variant
     */
    @NotNull
    Variant getVariant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此美西螈的变种.
     * <p>原文：
     * Set the variant of this axolotl.
     *
     * @param variant 美西螈变种
     * <p>原文：axolotl variant
     */
    void setVariant(@NotNull Variant variant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 美西螈的变种 - 即其颜色.
     * <p>原文：
     * Represents the variant of an axolotl - ie its color.
     */
    public enum Variant {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 白化 (粉色) 美西螈.
         * <p>原文：
         * Leucistic (pink) axolotl.
         */
        LUCY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 棕色美西螈.
         * <p>原文：
         * Brown axolotl.
         */
        WILD,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 金色美西螈.
         * <p>原文：
         * Gold axolotl.
         */
        GOLD,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 青色美西螈.
         * <p>原文：
         * Cyan axolotl.
         */
        CYAN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 蓝色美西螈.
         * <p>原文：
         * Blue axolotl.
         */
        BLUE;
    }
}
