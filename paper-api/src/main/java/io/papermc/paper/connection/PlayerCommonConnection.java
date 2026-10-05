package io.papermc.paper.connection;

import java.util.Map;

import com.destroystokyo.paper.ClientOption;
import org.bukkit.ServerLinks;
import org.bukkit.plugin.messaging.PluginMessageRecipient;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示在 GAME 与 CONFIG 阶段之间共享属性的连接。
 * <p>原文：
 * Represents a connection that has properties shared between the GAME and CONFIG stage.
 */
public interface PlayerCommonConnection extends WritablePlayerCookieConnection, ReadablePlayerCookieConnection, PluginMessageRecipient {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 发送数据以显示在此连接的报告日志中。这有助于调试可能导致玩家断开连接的服务器状态。
     * <p>
     * 这些数据以键 - 值的格式呈现，键的长度限制为 128 个字符，值限制为 4096 个字符，最多可发送 32 个条目。
     * <p>原文：
     * Sends data to appear in this connection's report logs.
     * This is useful for debugging server state that may be causing
     * player disconnects.
     * <p>
     * These are formatted as key - value, where keys are limited to a length of 128 characters,
     * values are limited to 4096, and 32 maximum entries can be sent.
     *
     * @param details 报告详情
     * <p>原文：report details
     */
    void sendReportDetails(Map<String, String> details);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 向此连接发送给定的服务器链接。
     * <p>原文：
     * Sends the given server links to this connection.
     *
     * @param links 要发送的链接
     * <p>原文：links to send
     */
    void sendLinks(ServerLinks links);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将此连接转移到另一台服务器。
     * <p>原文：
     * Transfers this connection to another server.
     *
     * @param host 主机
     * <p>原文：host
     * @param port 端口
     * <p>原文：port
     */
    void transfer(String host, int port);

    /**
     * @param type 客户端选项
     * <p>原文：client option
     * @return 玩家的客户端选项值
     * <p>原文：the client option value of the player
     */
    <T> T getClientOption(ClientOption<T> type);
}
