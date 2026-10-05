package org.bukkit.event.player;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家丢出物品事件.
 * <p>原文：
 * Thrown when a player drops an item from their inventory
 */
public class PlayerDropItemEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Item drop;
    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerDropItemEvent(@NotNull final Player player, @NotNull final Item drop) {
        super(player);
        this.drop = drop;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此玩家丢出的物品.
     * <p>原文：
     * Gets the ItemDrop created by the player
     *
     * @return 玩家丢出的物品
     * <p>原文：ItemDrop created by the player
     */
    @NotNull
    public Item getItemDrop() {
        return this.drop;
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
