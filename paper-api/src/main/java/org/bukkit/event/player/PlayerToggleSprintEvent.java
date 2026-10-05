package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家切换疾跑状态时调用此事件
 * <p>原文：
 * Called when a player toggles their sprinting state
 */
public class PlayerToggleSprintEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final boolean isSprinting;
    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerToggleSprintEvent(@NotNull final Player player, final boolean isSprinting) {
        super(player);
        this.isSprinting = isSprinting;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得玩家目前的疾跑状态(正在疾跑/没有疾跑).
     * <p>
     * Gets whether the player is now sprinting or not.
     * <p>原文：
     * Gets whether the player is now sprinting or not.
     *
     * @return 疾跑状态
     * <p>原文：sprinting state
     */
    public boolean isSprinting() {
        return this.isSprinting;
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
