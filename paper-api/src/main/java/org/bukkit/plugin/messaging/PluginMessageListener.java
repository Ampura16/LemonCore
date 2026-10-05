package org.bukkit.plugin.messaging;

import io.papermc.paper.connection.PlayerConnection;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个指定通道的一个监听器，将接收从客户端发送的消息的通知.
 * <p>原文：
 * A listener for a specific Plugin Channel, which will receive notifications
 * of messages sent from a client.
 */
@FunctionalInterface
public interface PluginMessageListener {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当PluginMessageSource(插件消息源)在已注册的通道上发送了插件消息调用此方法.
     * <p>原文：
     * A method that will be thrown when a PluginMessageSource sends a plugin
     * message on a registered channel.
     * <p>
     * Only called for joined players.
     *
     * @param channel 发送消息通过的通道
     * <p>原文：Channel that the message was sent through.
     * @param player 消息来源
     * <p>原文：Source of the message.
     * @param message 发送的原始消息
     * <p>原文：The raw message that was sent.
     * @see #onPluginMessageReceived(String, PlayerConnection, byte[]) for a method that is called for both joined and configuring players
     */
    void onPluginMessageReceived(@NotNull String channel, @NotNull Player player, byte @NotNull [] message);

    /**
     * A method that will be invoked when a PluginMessageSource sends a plugin
     * message on a registered channel.
     * <p>
     * Called for both joined players and players in the configuration stage.
     *
     * @param channel Channel that the message was sent through.
     * @param connection Source of the message.
     * @param message The raw message that was sent.
     */
    @ApiStatus.Experimental
    default void onPluginMessageReceived(@NotNull String channel, @NotNull PlayerConnection connection, byte @NotNull [] message) {
    }
}
