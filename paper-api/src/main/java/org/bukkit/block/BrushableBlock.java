package org.bukkit.block;

import org.bukkit.inventory.ItemStack;
import org.bukkit.loot.Lootable;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示可疑的沙子或沙砾的捕获状态。
 * <p>原文：
 * Represents a captured state of suspicious sand or gravel.
 */
public interface BrushableBlock extends Lootable, TileState {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当沙子被完全刷掉并露出时将显示的物品。
     * <p>原文：
     * Get the item which will be revealed when the sand is fully brushed away
     * and uncovered.
     *
     * @return 物品
     * <p>
     * 原文：Get the item which will be revealed when the sand is fully brushed away
     * and uncovered.
     * <p>原文：the item
     */
    @org.jetbrains.annotations.NotNull // Paper
    public ItemStack getItem();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当沙子被完全刷掉并露出时将显示的物品。
     * <p>原文：
     * Sets the item which will be revealed when the sand is fully brushed away
     * and uncovered.
     *
     * @param item 物品
     * <p>
     * 原文：Sets the item which will be revealed when the sand is fully brushed away
     * and uncovered.
     * <p>原文：the item
     */
    public void setItem(@Nullable ItemStack item);
}
