package io.papermc.paper.event.connection.configuration;

import io.papermc.paper.connection.PlayerConfigurationConnection;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 允许你配置玩家的事件。该事件是异步的，允许你对玩家运行配置代码。事件执行完毕后，玩家连接才会继续。
 * <p>
 * 该事件发生在配置之后、玩家进入世界之前。
 * <p>原文：
 * An event that allows you to configure the player.
 * This is async and allows you to run configuration code on the player.
 * Once this event has finished execution, the player connection will continue.
 * <p>
 * This occurs after configuration, but before the player has entered the world.
 */
public class AsyncPlayerConnectionConfigureEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final PlayerConfigurationConnection connection;

    @ApiStatus.Internal
    public AsyncPlayerConnectionConfigureEvent(final PlayerConfigurationConnection connection) {
        super(true);
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
