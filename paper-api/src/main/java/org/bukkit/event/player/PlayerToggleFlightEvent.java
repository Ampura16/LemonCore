package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家切换飞行状态则调用此事件.
 * <p>原文：
 * Called when a player toggles their flying state
 */
public class PlayerToggleFlightEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final boolean isFlying;
    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerToggleFlightEvent(@NotNull final Player player, final boolean isFlying) {
        super(player);
        this.isFlying = isFlying;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回玩家是否正在尝试飞行或停止飞行.
     * <p>原文：
     * Returns whether the player is trying to start or stop flying.
     *
     * @return 飞行状态
     * <p>原文：flying state
     */
    public boolean isFlying() {
        return this.isFlying;
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
