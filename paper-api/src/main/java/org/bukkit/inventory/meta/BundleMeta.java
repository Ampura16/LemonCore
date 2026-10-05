package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface BundleMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此物品是否包含任何物品.
     * <p>原文：
     * Returns whether the item has any items.
     *
     * @return 是否存在物品
     * <p>原文：whether items are present
     */
    boolean hasItems();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回存储在此物品中的物品的不可变列表.
     * <p>原文：
     * Returns an immutable list of the items stored in this item.
     *
     * @return 物品列表
     * <p>原文：items
     */
    @NotNull
    List<ItemStack> getItems();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置存储在此物品中的物品.
     * <br>
     * 传入 null 时将移除所有物品.
     * <p>原文：
     * Sets the items stored in this item.
     * <br>
     * Removes all items when given null.
     *
     * @param items 要设置的物品
     * <p>原文：the items to set
     */
    void setItems(@Nullable List<ItemStack> items);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向此物品添加一个物品.
     * <p>原文：
     * Adds an item to this item.
     *
     * @param item 要添加的物品
     * <p>原文：item to add
     */
    void addItem(@NotNull ItemStack item);
}
