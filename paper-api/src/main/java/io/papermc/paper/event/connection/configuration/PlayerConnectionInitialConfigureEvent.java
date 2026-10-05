package io.papermc.paper.event.connection.configuration;

import io.papermc.paper.connection.PlayerConfigurationConnection;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示此玩家是首次被配置，即连接将自动开始配置
 * <p>原文：
 * Indicates that this player is being configured for the first time, meaning that the connection will start being configured automatically
 */
public class PlayerConnectionInitialConfigureEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final PlayerConfigurationConnection connection;

    @ApiStatus.Internal
    public PlayerConnectionInitialConfigureEvent(final PlayerConfigurationConnection connection) {
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
