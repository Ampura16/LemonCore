package org.bukkit.event.inventory;

import com.google.common.base.Preconditions;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当某个实体或方块（例如漏斗）尝试将物品直接从一个物品栏移动到另一个物品栏时调用。
 * <p>
 * 当此事件被调用时，发起者可能已经从源物品栏中移除了该物品，并准备将其移动到目标物品栏中。
 * <p>
 * 如果此事件被取消，物品将被返回到源物品栏（如果需要）。
 * <p>
 * 如果此事件未被取消，发起者将尝试将 ItemStack 放入目标物品栏。如果这不可能且 ItemStack 未被修改，源物品栏槽位将恢复到之前的状态。否则，任何额外的物品将被丢弃。
 * <p>原文：
 * Called when some entity or block (e.g. hopper) tries to move items directly
 * from one inventory to another.
 * <p>
 * When this event is called, the initiator may already have removed the item
 * from the source inventory and is ready to move it into the destination
 * inventory.
 * <p>
 * If this event is cancelled, the items will be returned to the source
 * inventory, if needed.
 * <p>
 * If this event is not cancelled, the initiator will try to put the ItemStack
 * into the destination inventory. If this is not possible and the ItemStack
 * has not been modified, the source inventory slot will be restored to its
 * former state. Otherwise any additional items will be discarded.
 */
public class InventoryMoveItemEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Inventory sourceInventory;
    private final Inventory destinationInventory;
    private ItemStack itemStack;
    private final boolean didSourceInitiate;

    private boolean cancelled;

    @ApiStatus.Internal
    public InventoryMoveItemEvent(@NotNull final Inventory sourceInventory, @NotNull final ItemStack itemStack, @NotNull final Inventory destinationInventory, final boolean didSourceInitiate) {
        Preconditions.checkArgument(itemStack != null, "ItemStack cannot be null");
        this.sourceInventory = sourceInventory;
        this.itemStack = itemStack;
        this.destinationInventory = destinationInventory;
        this.didSourceInitiate = didSourceInitiate;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 ItemStack 被取出的物品栏。
     * <p>原文：
     * Gets the Inventory that the ItemStack is being taken from
     *
     * @return ItemStack 被取出的物品栏
     * <p>原文：Gets the Inventory that the ItemStack is being taken from
     * <p>原文：Inventory that the ItemStack is being taken from
     */
    @NotNull
    public Inventory getSource() {
        return this.sourceInventory;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取正在移动的 ItemStack；如果被修改，原始物品将不会从源物品栏中移除。
     * <p>原文：
     * Gets the ItemStack being moved; if modified, the original item will not
     * be removed from the source inventory.
     *
     * @return ItemStack
     * <p>原文：Gets the ItemStack being moved; if modified, the original item will not
     * be removed from the source inventory.
     * <p>原文：ItemStack
     */
    @NotNull
    public ItemStack getItem() {
        return this.itemStack;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置正在移动的 ItemStack；如果这与原始 ItemStack 不同，原始物品将不会从源物品栏中移除。
     * <p>原文：
     * Sets the ItemStack being moved; if this is different from the original
     * ItemStack, the original item will not be removed from the source
     * inventory.
     *
     * @param itemStack ItemStack
     * <p>原文：Sets the ItemStack being moved; if this is different from the original
     * ItemStack, the original item will not be removed from the source
     * inventory.
     * <p>原文：The ItemStack
     */
    public void setItem(@NotNull ItemStack itemStack) {
        Preconditions.checkArgument(itemStack != null, "ItemStack cannot be null. Cancel the event if you want nothing to be transferred.");
        this.itemStack = itemStack.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 ItemStack 被放入的物品栏。
     * <p>原文：
     * Gets the Inventory that the ItemStack is being put into
     *
     * @return ItemStack 被放入的物品栏
     * <p>原文：Gets the Inventory that the ItemStack is being put into
     * <p>原文：Inventory that the ItemStack is being put into
     */
    @NotNull
    public Inventory getDestination() {
        return this.destinationInventory;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取发起传输的物品栏。这将始终是目标或源物品栏。
     * <p>原文：
     * Gets the Inventory that initiated the transfer. This will always be
     * either the destination or source Inventory.
     *
     * @return 发起传输的物品栏
     * <p>原文：Gets the Inventory that initiated the transfer. This will always be
     * either the destination or source Inventory.
     * <p>原文：Inventory that initiated the transfer
     */
    @NotNull
    public Inventory getInitiator() {
        return this.didSourceInitiate ? this.sourceInventory : this.destinationInventory;
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
