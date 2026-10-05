package io.papermc.paper.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家睡眠时间足以被计入跳过夜晚/雷暴时调用。
 * <p>
 * 取消该事件将阻止该玩家被计为深度睡眠，除非其离开并重新上床。
 * <p>原文：
 * Called when a player has slept long enough
 * to count as passing the night/storm.
 * <p>
 * Cancelling this event will prevent the player from being counted as deeply sleeping
 * unless they exit and re-enter the bed.
 */
@NullMarked
public class PlayerDeepSleepEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerDeepSleepEvent(final Player player) {
        super(player);
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
