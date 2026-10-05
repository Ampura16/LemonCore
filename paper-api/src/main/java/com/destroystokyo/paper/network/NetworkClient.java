package com.destroystokyo.paper.network;

import java.net.InetSocketAddress;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示连接到服务器的客户端。
 * <p>原文：
 * Represents a client connected to the server.
 */
@NullMarked
public interface NetworkClient {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回客户端的套接字地址。
     * <p>原文：
     * Returns the socket address of the client.
     *
     * @return 客户端的套接字地址
     * <p>原文：The client's socket address
     */
    InetSocketAddress getAddress();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回客户端的协议版本。
     * <p>原文：
     * Returns the protocol version of the client.
     *
     * @return 客户端的协议版本；如果未知则为 {@code -1}
     * <p>原文：The client's protocol version, or {@code -1} if unknown
     * @see <a href="https://minecraft.wiki/w/Minecraft_Wiki:Projects/wiki.vg_merge/Protocol_version_numbers">List of protocol
     *     version numbers</a>
     */
    int getProtocolVersion();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回客户端所连接的虚拟主机。
     * <p>
     * 虚拟主机是指客户端连接服务器时使用的主机名/端口。
     * </p>
     * <p>原文：
     * Returns the virtual host the client is connected to.
     *
     * <p>The virtual host refers to the hostname/port the client used to
     * connect to the server.</p>
     *
     * @return 客户端的虚拟主机；如果未知则为 {@code null}
     * <p>原文：The client's virtual host, or {@code null} if unknown
     */
    @Nullable InetSocketAddress getVirtualHost();

}
