package org.bukkit.event.player;

import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家切换到另一个世界时触发此事件.
 * <p>原文：
 * Called when a player switches to another world.
 */
public class PlayerChangedWorldEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final World from;

    @ApiStatus.Internal
    public PlayerChangedWorldEvent(@NotNull final Player player, @NotNull final World from) {
        super(player);
        this.from = from;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得玩家切换到目标世界之前所在的世界.
     * <p>原文：
     * Gets the world the player is switching from.
     *
     * @return 玩家切换到目标世界之前所在的世界
     * <p>原文：player's previous world
     */
    @NotNull
    public World getFrom() {
        return this.from;
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
