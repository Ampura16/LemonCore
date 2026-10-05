package org.bukkit.event.inventory;

import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.SmithingInventory;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当物品被放入锻造台中用于升级的槽位时调用。
 * <p>原文：
 * Called when an item is put in a slot for upgrade by a Smithing Table.
 */
public class PrepareSmithingEvent extends com.destroystokyo.paper.event.inventory.PrepareResultEvent {

    @ApiStatus.Internal
    public PrepareSmithingEvent(@NotNull InventoryView inventory, @Nullable ItemStack result) {
        super(inventory, result);
    }

    @NotNull
    @Override
    public SmithingInventory getInventory() {
        return (SmithingInventory) super.getInventory();
    }
}
