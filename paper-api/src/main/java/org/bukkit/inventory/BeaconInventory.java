package org.bukkit.inventory;

import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 信标的背包界面接口
 * <p>原文：
 * Interface to the inventory of a Beacon.
 */
public interface BeaconInventory extends Inventory {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置信标内的物品.
     * <p>原文：
     * Set the item powering the beacon.
     *
     * @param item 新的物品.
     * <p>原文：The new item
     */
    void setItem(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回信标内的物品.
     * <p>原文：
     * Get the item powering the beacon.
     *
     * @return 物品
     * <p>原文：The current item.
     */
    @Nullable
    ItemStack getItem();
}
