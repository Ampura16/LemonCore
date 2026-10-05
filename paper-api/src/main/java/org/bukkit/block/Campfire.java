package org.bukkit.block;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表营火.
 * <p>原文：
 * Represents a captured state of a campfire.
 */
public interface Campfire extends TileState {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 物品栏大小
     * <p>原文：The size of the inventory
     * @see Inventory#getSize()
     */
    int getSize();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param index 物品所在的格子
     * <p>原文：The index of the Slot's ItemStack to return
     * @return 格子内的物品
     * <p>原文：The ItemStack in the slot
     * @see Inventory#getItem(int)
     */
    @Nullable
    ItemStack getItem(int index);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param index 放置物品堆于哪个格子
     * <p>原文：The index where to put the ItemStack
     * @param item 要放入的物品堆
     * <p>原文：The ItemStack to set
     * @see Inventory#setItem(int, org.bukkit.inventory.ItemStack)
     */
    void setItem(int index, @Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取某个物品已被烹饪多久.
     * <p>原文：
     * Get cook time.
     *
     * This is the amount of time the item has been cooking for.
     *
     * @param index 物品槽位
     * <p>原文：item slot index
     * @return 已烹饪时长
     * <p>原文：Cook time
     */
    int getCookTime(int index);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置某个物品已被烹饪多久.
     * <p>原文：
     * Set cook time.
     *
     * This is the amount of time the item has been cooking for.
     *
     * @param index 物品槽位
     * <p>原文：item slot index
     * @param cookTime 已烹饪时长
     * <p>原文：Cook time
     */
    void setCookTime(int index, int cookTime);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取烹饪某个物品所需的时间.
     * <p>原文：
     * Get cook time total.
     *
     * This is the amount of time the item is required to cook for.
     *
     * @param index 物品槽位
     * <p>原文：item slot index
     * @return 烹饪总时长
     * <p>原文：Cook time total
     */
    int getCookTimeTotal(int index);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置烹饪某个物品所需的时间.
     * <p>原文：
     * Set cook time.
     *
     * This is the amount of time the item is required to cook for.
     *
     * @param index 物品槽位
     * <p>原文：item slot index
     * @param cookTimeTotal 烹饪总时长
     * <p>原文：Cook time total
     */
    void setCookTimeTotal(int index, int cookTimeTotal);

    /**
     * Disable cooking in all slots.
     */
    void stopCooking();

    /**
     * Re-enable cooking in all slots.
     */
    void startCooking();

    /**
     * Disable cooking in the specified slot index.
     *
     * @param index item slot index
     * @return whether the slot had cooking enabled before this call
     */
    boolean stopCooking(int index);

    /**
     * Re-enable cooking in the specified slot index.
     *
     * @param index item slot index
     * @return whether the slot couldn't cook before this call
     */
    boolean startCooking(int index);

    /**
     * State of slot index.
     *
     * @param index item slot index
     * @return {@code true} if the specified slot index cannot cook
     */
    boolean isCookingDisabled(int index);
}
