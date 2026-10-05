package org.bukkit.event.player;

import org.bukkit.Warning;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityPickupItemEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家捡起掉落物品事件.
 * <p>原文：
 * Thrown when a player picks an item up from the ground
 *
 * @deprecated {@link EntityPickupItemEvent}
 */
@Deprecated(since = "1.12")
@Warning
public class PlayerPickupItemEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Item item;
    private final int remaining;
    private boolean flyAtPlayer = true;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerPickupItemEvent(@NotNull final Player player, @NotNull final Item item, final int remaining) {
        super(player);
        this.item = item;
        this.remaining = remaining;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此玩家在地面捡起的物品.
     * <p>原文：
     * Gets the Item picked up by the player.
     *
     * @return 此掉落物品
     * <p>原文：Item
     */
    @NotNull
    public Item getItem() {
        return this.item;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得地面剩余掉落物品数量(如果有的话).
     * <p>原文：
     * Gets the amount remaining on the ground, if any
     *
     * @return 剩余掉落物品数量
     * <p>原文：amount remaining on the ground
     */
    public int getRemaining() {
        return this.remaining;
    }

    /**
     * Set if the item will fly at the player
     * <p>
     * Cancelling the event will set this value to {@code false}.
     *
     * @param flyAtPlayer {@code true} for item to fly at player
     */
    public void setFlyAtPlayer(boolean flyAtPlayer) {
        this.flyAtPlayer = flyAtPlayer;
    }

    /**
     * Gets if the item will fly at the player
     *
     * @return {@code true} if the item will fly at the player
     */
    public boolean getFlyAtPlayer() {
        return this.flyAtPlayer;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
        this.flyAtPlayer = !cancel;
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
