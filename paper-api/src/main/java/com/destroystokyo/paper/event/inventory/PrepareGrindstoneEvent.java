package com.destroystokyo.paper.event.inventory;

import org.bukkit.Warning;
import org.bukkit.inventory.GrindstoneInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当物品被放入砂轮的研磨槽时调用
 * <p>原文：
 * Called when an item is put in a slot for grinding in a Grindstone
 *
 * @deprecated 请使用 {@link org.bukkit.event.inventory.PrepareGrindstoneEvent}
 * <p>原文：use {@link org.bukkit.event.inventory.PrepareGrindstoneEvent}
 */
@Deprecated(since = "1.16.1")
@Warning
public class PrepareGrindstoneEvent extends PrepareResultEvent {

    @ApiStatus.Internal
    public PrepareGrindstoneEvent(@NotNull InventoryView inventory, @Nullable ItemStack result) {
        super(inventory, result);
    }

    @NotNull
    @Override
    public GrindstoneInventory getInventory() {
        return (GrindstoneInventory) super.getInventory();
    }

}
