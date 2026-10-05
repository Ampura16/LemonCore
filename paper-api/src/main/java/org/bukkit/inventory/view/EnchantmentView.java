package org.bukkit.inventory.view;

import org.bukkit.enchantments.EnchantmentOffer;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个提供与附魔台视图数据相关额外方法的 {@link InventoryView} 实例。
 * <p>原文：
 * An instance of {@link InventoryView} which provides extra methods related to
 * enchantment table view data.
 */
public interface EnchantmentView extends InventoryView {

    @NotNull
    @Override
    EnchantingInventory getTopInventory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此视图中使用的随机附魔种子。
     * <p>原文：
     * Gets the random enchantment seed used in this view
     *
     * @return 使用的随机种子
     * <p>
     * 原文：Gets the random enchantment seed used in this view
     * <p>原文：The random seed used
     */
    int getEnchantmentSeed();

    // Paper start - add enchantment seed update API
    /**
     * Sets the random enchantment seed used in this view. Loses its effect once the view is closed.
     *
     * @param seed the random seed to use
     */
    void setEnchantmentSeed(int seed);
    // Paper end - add enchantment seed update API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此EnchantmentView的附魔选项。
     * <p>原文：
     * Gets the offers of this EnchantmentView
     *
     * @return 提供的附魔选项
     * <p>
     * 原文：Gets the offers of this EnchantmentView
     * <p>原文：The enchantment offers that are provided
     */
    @Nullable EnchantmentOffer @NotNull [] getOffers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置要提供给玩家的附魔选项。
     * <p>原文：
     * Sets the offers to provide to the player.
     *
     * @param offers 要提供的附魔选项
     * <p>原文：The offers to provide
     * @throws IllegalArgumentException 如果数组长度不为3
     * <p>
     * 原文：Sets the offers to provide to the player.
     * <p>原文：if the array length isn't 3
     */
    void setOffers(@Nullable EnchantmentOffer @NotNull [] offers) throws IllegalArgumentException;
}
