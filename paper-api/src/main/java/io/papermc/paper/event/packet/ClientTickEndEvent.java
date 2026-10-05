package io.papermc.paper.event.packet;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当服务器收到 {@code minecraft:client_tick_end} 数据包时调用。
 * <p>原文：
 * Called when a {@code minecraft:client_tick_end} packet is received by the server.
 */
@NullMarked
public class ClientTickEndEvent extends PlayerEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    @ApiStatus.Internal
    public ClientTickEndEvent(final Player player) {
        super(player);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
