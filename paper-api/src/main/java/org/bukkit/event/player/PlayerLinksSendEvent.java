package org.bukkit.event.player;

import io.papermc.paper.connection.PlayerCommonConnection;
import io.papermc.paper.connection.PlayerConfigurationConnection;
import org.bukkit.ServerLinks;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当链接列表发送给玩家时触发.
 * <p>原文：
 * This event is called when the list of links is sent to the player.
 */
@ApiStatus.Experimental
public class PlayerLinksSendEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ServerLinks links;
    private final PlayerCommonConnection connection;

    @ApiStatus.Internal
    public PlayerLinksSendEvent(@NotNull final PlayerConfigurationConnection connection, @NotNull final ServerLinks links) {
        this.connection = connection;
        this.links = links;
    }

    /**
     * Gets the connection that received the links.
     * @return connection
     */
    @NotNull
    public PlayerCommonConnection getConnection() {
        return connection;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取要发送的链接以进行修改.
     * <p>原文：
     * Gets the links to be sent, for modification.
     *
     * @return 链接
     * <p>原文：the links
     */
    @NotNull
    public ServerLinks getLinks() {
        return this.links;
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
