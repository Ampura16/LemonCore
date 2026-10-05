package org.bukkit.event.raid;

import org.bukkit.Raid;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当袭击被触发时触发本事件 (例如:带有不祥之兆效果的玩家进入村庄).
 * <p>原文：
 * Called when a {@link Raid} is triggered (e.g: a player with Bad Omen effect
 * enters a village).
 */
public class RaidTriggerEvent extends RaidEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private boolean cancelled;

    @ApiStatus.Internal
    public RaidTriggerEvent(@NotNull Raid raid, @NotNull World world, @NotNull Player player) {
        super(raid, world);
        this.player = player;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回触发此袭击的玩家.
     * <p>原文：
     * Returns the player who triggered the raid.
     *
     * @return 触发此袭击的玩家
     * <p>原文：triggering player
     */
    @NotNull
    public Player getPlayer() {
        return this.player;
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
