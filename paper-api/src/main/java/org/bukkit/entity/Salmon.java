package org.bukkit.entity;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表鲑鱼。
 * <p>原文：
 * Represents a salmon fish.
 */
public interface Salmon extends io.papermc.paper.entity.SchoolableFish { // Paper - Schooling Fish API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此鲑鱼的变种。
     * <p>原文：
     * Get the variant of this salmon.
     *
     * @return 鲑鱼变种
     * <p>原文：salmon variant
     */
    @NotNull
    public Variant getVariant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此鲑鱼的变种。
     * <p>原文：
     * Set the variant of this salmon.
     *
     * @param variant 鲑鱼变种
     * <p>原文：salmon variant
     */
    public void setVariant(@NotNull Variant variant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表鲑鱼的变种 - 即其大小。
     * <p>原文：
     * Represents the variant of a salmon - ie its size.
     */
    public enum Variant {

        // Start generate - SalmonVariant
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 小型鲑鱼。
         */
        SMALL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 默认鲑鱼。
         */
        MEDIUM,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 大型鲑鱼。
         */
        LARGE;
        // End generate - SalmonVariant
    }
}
