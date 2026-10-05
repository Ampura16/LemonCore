package org.bukkit.inventory;

import org.bukkit.Material;
import org.bukkit.block.BrewingStand;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 酿造台的用户界面接口.
 * <p>原文：
 * Interface to the inventory of a Brewing Stand.
 */
public interface BrewerInventory extends Inventory {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前酿造台的原料.
     * <p>原文：
     * Get the current ingredient for brewing.
     *
     * @return 原料成分.
     * <p>原文：The ingredient.
     */
    @Nullable
    ItemStack getIngredient();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前酿造台的原料成分.
     * <p>原文：
     * Set the current ingredient for brewing.
     *
     * @param ingredient 原料成分
     * <p>原文：The ingredient
     */
    void setIngredient(@Nullable ItemStack ingredient);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前用来酿造的燃料.
     * <p>原文：
     * Get the current fuel for brewing.
     *
     * @return 燃料
     * <p>原文：The fuel
     */
    @Nullable
    ItemStack getFuel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前用来酿造的燃料.一般只有{@link Material#BLAZE_POWDER}将使用此方法.
     * <p>原文：
     * Set the current fuel for brewing. Generally only
     * {@link Material#BLAZE_POWDER} will be of use.
     *
     * @param fuel 燃料
     * <p>原文：The fuel
     */
    void setFuel(@Nullable ItemStack fuel);

    @Override
    @Nullable
    BrewingStand getHolder();
}
