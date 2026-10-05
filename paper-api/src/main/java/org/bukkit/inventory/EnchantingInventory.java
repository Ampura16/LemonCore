package org.bukkit.inventory;

import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 附魔界面接口
 * <p>原文：
 * Interface to the inventory of an Enchantment Table.
 */
public interface EnchantingInventory extends Inventory {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将被附魔的物品.
     * <p>原文：
     * Set the item being enchanted.
     *
     * @param item 将被附魔的物品
     * <p>原文：The new item
     */
    void setItem(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将被附魔的物品.
     * <p>原文：
     * Get the item being enchanted.
     *
     * @return 将被附魔的物品.
     * <p>原文：The current item.
     */
    @Nullable
    ItemStack getItem();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将被用于附魔的第二物品(如青金石).
     * <p>原文：
     * Set the secondary item being used for the enchant.
     *
     * @param item 将被用于附魔的第二物品
     * <p>原文：The new item
     */
    void setSecondary(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将被用于附魔的第二物品(如青金石).
     * <p>原文：
     * Get the secondary item being used for the enchant.
     *
     * @return 将被用于附魔的第二物品
     * <p>原文：The second item
     */
    @Nullable
    ItemStack getSecondary();
}
