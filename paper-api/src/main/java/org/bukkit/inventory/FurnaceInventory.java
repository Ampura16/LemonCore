package org.bukkit.inventory;

import org.bukkit.block.Furnace;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 熔炉用户界面接口
 * <p>原文：
 * Interface to the inventory of a Furnace.
 */
public interface FurnaceInventory extends Inventory {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前熔炉生成的的物品.
     * <p>原文：
     * Get the current item in the result slot.
     *
     * @return The item
     */
    @Nullable
    ItemStack getResult();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前熔炉的燃料物品.
     * <p>原文：
     * Get the current fuel.
     *
     * @return The item
     */
    @Nullable
    ItemStack getFuel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前熔炉的被冶炼的物品.
     * <p>原文：
     * Get the item currently smelting.
     *
     * @return 物品
     * <p>原文：The item
     */
    @Nullable
    ItemStack getSmelting();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前熔炉的燃料物品.
     * <p>原文：
     * Set the current fuel.
     *
     * @param stack 燃料物品
     * <p>原文：The item
     */
    void setFuel(@Nullable ItemStack stack);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前熔炉的生成物.
     * <p>原文：
     * Set the current item in the result slot.
     *
     * @param stack 生成物
     * <p>原文：The item
     */
    void setResult(@Nullable ItemStack stack);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前熔炉的被冶炼物物品.
     * <p>原文：
     * Set the item currently smelting.
     *
     * @param stack 物品
     * <p>原文：The item
     */
    void setSmelting(@Nullable ItemStack stack);

    // Paper start
    /**
     * Check if an item can be used as a fuel source in this furnace container
     *
     * @param item Item to check
     * @return True if a valid fuel source
     */
    public boolean isFuel(@Nullable ItemStack item);

    /**
     * Check if an item can be smelted in this furnace container
     *
     * @param item Item to check
     * @return True if can be smelt
     */
    public boolean canSmelt(@Nullable ItemStack item);
    // Paper end

    @Override
    @Nullable
    Furnace getHolder();
}
