package io.papermc.paper.connection;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import net.kyori.adventure.text.Component;
import org.jspecify.annotations.Nullable;

public interface PlayerConnection {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 断开玩家连接。
     * <p>
     * 注意，在连接相关事件期间调用此方法可能导致未定义行为。
     * <p>原文：
     * Disconnects the player connection.
     * <p>
     * Note that calling this during connection related events may cause undefined behavior.
     *
     * @param component 断开连接原因
     * <p>原文：disconnect reason
     */
    void disconnect(Component component);

    /**
     * {@return whether this connection is currently open and active}
     */
    boolean isConnected();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此连接是否来自转移的连接。
     * <p>
     * 请注意，这是由客户端发送并存储在客户端上的。
     * <p>原文：
     * Gets if this connection originated from a transferred connection.
     * <p>
     * Do note that this is sent and stored on the client.
     *
     * @return 是否为转移的连接
     * <p>原文：is transferred
     */
    boolean isTransferred();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取连接的原始远程地址。根据频道的建立方式，这可能是代理地址或 Unix 域套接字地址。
     * <p>原文：
     * Gets the raw remote address of the connection. This may be a proxy address
     * or a Unix domain socket address, depending on how the channel was established.
     *
     * @return 频道的远程 {@link SocketAddress}
     * <p>原文：the remote {@link SocketAddress} of the channel
     */
    SocketAddress getAddress();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家的真实客户端地址。如果连接位于代理之后，这将是从代理握手中提取的玩家实际 IP 地址。
     * <p>原文：
     * Gets the real client address of the player. If the connection is behind a proxy,
     * this will be the actual player’s IP address extracted from the proxy handshake.
     *
     * @return 客户端 {@link InetSocketAddress}
     * <p>原文：the client {@link InetSocketAddress}
     */
    InetSocketAddress getClientAddress();

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

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此玩家代理的套接字地址
     * <p>原文：
     * Gets the socket address of this player's proxy
     *
     * @return 玩家的代理地址；如果服务器未启用 Proxy Protocol 或玩家未连接到 HAProxy 实例则为 null
     * <p>原文：the player's proxy address, null if the server doesn't have Proxy Protocol enabled, or the player didn't connect to an HAProxy instance
     */
    @Nullable InetSocketAddress getHAProxyAddress();
}
