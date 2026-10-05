package org.bukkit.event.inventory;

import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当物品被放入砂轮中用于修复或解除附魔的槽位时调用。
 * <p>原文：
 * Called when an item is put in a slot for repair or unenchanting in a grindstone.
 */
public class PrepareGrindstoneEvent extends com.destroystokyo.paper.event.inventory.PrepareGrindstoneEvent { // Paper

    @ApiStatus.Internal
    public PrepareGrindstoneEvent(@NotNull InventoryView inventory, @Nullable ItemStack result) {
        super(inventory, result);
    }

    @NotNull
    @Override
    public GrindstoneInventory getInventory() {
        return super.getInventory();
    }
}
