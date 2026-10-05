package org.bukkit.entity;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表鹦鹉.
 * <p>原文：
 * Represents a Parrot.
 */
public interface Parrot extends Tameable, Sittable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得鹦鹉变体.
     * <p>原文：
     * Get the variant of this parrot.
     *
     * @return 鹦鹉变体
     * <p>原文：parrot variant
     */
    @NotNull
    public Variant getVariant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置鹦鹉变体.
     * <p>原文：
     * Set the variant of this parrot.
     *
     * @param variant 鹦鹉变体
     * <p>原文：parrot variant
     */
    public void setVariant(@NotNull Variant variant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取鹦鹉是否正在跳舞.
     * <p>原文：
     * Gets whether a parrot is dancing
     *
     * @return 鹦鹉是否正在跳舞
     * <p>原文：Whether the parrot is dancing
     */
    public boolean isDancing();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表鹦鹉变体(颜色).
     * <p>原文：
     * Represents the variant of a parrot - ie its color.
     */
    public enum Variant {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 传统鹦鹉 - 红翅膀.
         * <p>原文：
         * Classic parrot - red with colored wingtips.
         */
        RED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 蓝色.
         * <p>原文：
         * Royal blue colored parrot.
         */
        BLUE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 绿色.
         * <p>原文：
         * Green colored parrot.
         */
        GREEN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 蓝绿色鹦鹉.
         * <p>原文：
         * Cyan colored parrot.
         */
        CYAN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 灰色鹦鹉.
         * <p>原文：
         * Gray colored parrot.
         */
        GRAY;
    }
}
