package com.destroystokyo.paper.exception;

import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

import static com.google.common.base.Preconditions.*;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当传入的插件消息频道抛出异常时抛出
 * <p>原文：
 * Thrown when an incoming plugin message channel throws an exception
 */
public class ServerPluginMessageException extends ServerPluginException {

    private final Player player;
    private final String channel;
    private final byte[] data;

    public ServerPluginMessageException(String message, Throwable cause, Plugin responsiblePlugin, Player player, String channel, byte[] data) {
        super(message, cause, responsiblePlugin);
        this.player = checkNotNull(player, "player");
        this.channel = checkNotNull(channel, "channel");
        this.data = checkNotNull(data, "data");
    }

    public ServerPluginMessageException(Throwable cause, Plugin responsiblePlugin, Player player, String channel, byte[] data) {
        super(cause, responsiblePlugin);
        this.player = checkNotNull(player, "player");
        this.channel = checkNotNull(channel, "channel");
        this.data = checkNotNull(data, "data");
    }

    protected ServerPluginMessageException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, Plugin responsiblePlugin, Player player, String channel, byte[] data) {
        super(message, cause, enableSuppression, writableStackTrace, responsiblePlugin);
        this.player = checkNotNull(player, "player");
        this.channel = checkNotNull(channel, "channel");
        this.data = checkNotNull(data, "data");
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取接收数据时发生错误的频道
     * <p>原文：
     * Gets the channel to which the error occurred from receiving data from
     *
     * @return 发生异常的频道
     * <p>原文：exception channel
     */
    public String getChannel() {
        return channel;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取发生错误的数据
     * <p>原文：
     * Gets the data to which the error occurred from
     *
     * @return 异常数据
     * <p>原文：exception data
     */
    public byte[] getData() {
        return data;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取导致异常的插件消息所来自的玩家
     * <p>原文：
     * Gets the player which the plugin message causing the exception originated from
     *
     * @return 发生异常的玩家
     * <p>原文：exception player
     */
    public Player getPlayer() {
        return player;
    }
}
