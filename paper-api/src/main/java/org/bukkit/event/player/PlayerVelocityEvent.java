package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家移动速度变化事件.
 * <p>原文：
 * Called when the velocity of a player changes.
 */
public class PlayerVelocityEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private Vector velocity;
    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerVelocityEvent(@NotNull final Player player, @NotNull final Vector velocity) {
        super(player);
        this.velocity = velocity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到玩家目前的移动速度.
     * <p>原文：
     * Gets the velocity vector that will be sent to the player
     *
     * @return 玩家移动速度
     * <p>原文：Vector the player will get
     */
    @NotNull
    public Vector getVelocity() {
        return this.velocity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家移动速度 (单位: 米每tick).
     * <p>原文：
     * Sets the velocity vector in meters per tick that will be sent to the player
     *
     * @param velocity 玩家速度
     * <p>原文：The velocity vector that will be sent to the player
     */
    public void setVelocity(@NotNull Vector velocity) {
        this.velocity = velocity.clone();
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
