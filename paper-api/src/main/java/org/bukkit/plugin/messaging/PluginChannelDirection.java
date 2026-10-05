package org.bukkit.plugin.messaging;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表插件通道的不同指向。
 * <p>原文：
 * Represents the different directions a plugin channel may go.
 */
public enum PluginChannelDirection {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 插件通道正在从客户端发往服务器(正在接收).
     * <p>原文：
     * The plugin channel is being sent to the server from a client.
     */
    INCOMING,

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 插件通道正在从服务器发往客户端(正在发送).
     * <p>原文：
     * The plugin channel is being sent to a client from the server.
     */
    OUTGOING
}
