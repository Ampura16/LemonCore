package io.papermc.paper.event.connection.configuration;

import io.papermc.paper.connection.PlayerConfigurationConnection;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示此玩家正在被重新配置，即除非通过 {@link PlayerConfigurationConnection#completeReconfiguration()} 被踢出，否则此连接将停留在配置阶段
 * <p>原文：
 * Indicates that this player is being reconfigured, meaning that this connection will be held in the configuration
 * stage unless kicked out through {@link PlayerConfigurationConnection#completeReconfiguration()}
 */
public class PlayerConnectionReconfigureEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final PlayerConfigurationConnection connection;

    @ApiStatus.Internal
    public PlayerConnectionReconfigureEvent(final PlayerConfigurationConnection connection) {
        super(!Bukkit.isPrimaryThread());
        this.connection = connection;
    }

    public PlayerConfigurationConnection getConnection() {
        return this.connection;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
