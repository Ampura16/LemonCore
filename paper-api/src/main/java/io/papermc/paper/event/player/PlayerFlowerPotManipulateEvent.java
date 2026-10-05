package io.papermc.paper.event.player;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家向花盆放入物品或从中取出物品时调用。
 * <p>原文：
 * Called when a player places an item in or takes an item out of a flowerpot.
 */
@NullMarked
public class PlayerFlowerPotManipulateEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block flowerpot;
    private final ItemStack item;
    private final boolean placing;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerFlowerPotManipulateEvent(final Player player, final Block flowerpot, final ItemStack item, final boolean placing) {
        super(player);
        this.flowerpot = flowerpot;
        this.item = item;
        this.placing = placing;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件涉及的花盆。
     * <p>原文：
     * Gets the flowerpot that is involved in this event.
     *
     * @return 此事件涉及的花盆
     * <p>原文：the flowerpot that is involved with this event
     */
    public Block getFlowerpot() {
        return this.flowerpot;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正在放入或从花盆中取出的物品。使用 {@link #isPlacing()} 检查是否为放入。
     * <p>原文：
     * Gets the item being placed, or taken from, the flower pot.
     * Check if placing with {@link #isPlacing()}.
     *
     * @return 放入花盆或从花盆中取出的物品
     * <p>原文：the item placed, or taken from, the flowerpot
     */
    public ItemStack getItem() {
        return this.item;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取物品是否正被放入花盆。
     * <p>原文：
     * Gets if the item is being placed into the flowerpot.
     *
     * @return 物品是否正被放入花盆
     * <p>原文：if the item is being placed into the flowerpot
     */
    public boolean isPlacing() {
        return this.placing;
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
