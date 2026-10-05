package org.bukkit.event.inventory;

import org.bukkit.event.inventory.InventoryType.SlotType;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当处于创造模式的玩家在物品栏/快捷栏中放下或拾取物品，以及当他们在创造模式下从物品栏中丢弃物品时，会调用此事件。
 * <p>原文：
 * This event is called when a player in creative mode puts down or picks up
 * an item in their inventory / hotbar and when they drop items from their
 * Inventory while in creative mode.
 */
public class InventoryCreativeEvent extends InventoryClickEvent {

    private ItemStack item;

    @ApiStatus.Internal
    public InventoryCreativeEvent(@NotNull InventoryView view, @NotNull SlotType type, int slot, @NotNull ItemStack newItem) {
        super(view, type, slot, ClickType.CREATIVE, InventoryAction.PLACE_ALL);
        this.item = newItem;
    }

    @Override
    @NotNull
    public ItemStack getCursor() {
        return this.item;
    }

    @Override
    public void setCursor(@NotNull ItemStack item) {
        this.item = item;
    }
}
