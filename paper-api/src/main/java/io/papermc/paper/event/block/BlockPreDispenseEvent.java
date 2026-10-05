package io.papermc.paper.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class BlockPreDispenseEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ItemStack itemStack;
    private final int slot;

    private boolean cancelled;

    @ApiStatus.Internal
    public BlockPreDispenseEvent(final Block block, final ItemStack itemStack, final int slot) {
        super(block);
        this.itemStack = itemStack;
        this.slot = slot;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取将被发射的 {@link ItemStack}。
     * <p>原文：
     * Gets the {@link ItemStack} to be dispensed.
     *
     * @return 将被发射的物品
     * <p>原文：The item to be dispensed
     */
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取发射器要从中发射的物品栏槽位。
     * <p>原文：
     * Gets the inventory slot of the dispenser to dispense from.
     *
     * @return 物品栏槽位
     * <p>原文：The inventory slot
     */
    public int getSlot() {
        return this.slot;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
