package org.bukkit.event.inventory;

import org.bukkit.entity.Item;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当漏斗/漏斗矿车收起掉落的物品时触发本事件.
 * <p>原文：
 * Called when a hopper or hopper minecart picks up a dropped item.
 */
public class InventoryPickupItemEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Inventory inventory;
    private final Item item;

    private boolean cancelled;

    @ApiStatus.Internal
    public InventoryPickupItemEvent(@NotNull final Inventory inventory, @NotNull final Item item) {
        this.inventory = inventory;
        this.item = item;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取捡起这个物品的背包.
     * <p>原文：
     * Gets the Inventory that picked up the item
     */
    @NotNull
    public Inventory getInventory() {
        return this.inventory;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被捡起的物品实体.
     * <p>原文：
     * Gets the Item entity that was picked up
     */
    @NotNull
    public Item getItem() {
        return this.item;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
