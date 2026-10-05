package com.destroystokyo.paper.network;

import com.destroystokyo.paper.event.server.PaperServerListPingEvent;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示向服务器请求当前状态的客户端（例如来自服务器列表）。
 * <p>原文：
 * Represents a client requesting the current status from the server (e.g. from
 * the server list).
 *
 * @see PaperServerListPingEvent
 */
public interface StatusClient extends NetworkClient {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回客户端是否使用了不支持 {@link PaperServerListPingEvent} 全部功能的旧版本。
     * <p>
     * 对于原版，所有早于 1.7 的客户端都会返回 {@code true}。
     * </p>
     * <p>原文：
     * Returns whether the client is using an older version that doesn't
     * support all the features in {@link PaperServerListPingEvent}.
     *
     * <p>For Vanilla, this returns {@code true} for all clients older than 1.7.</p>
     *
     * @return 如果客户端使用旧版 ping 则为 {@code true}
     * <p>原文：{@code true} if the client is using legacy ping
     */
    default boolean isLegacy() {
        return false;
    }

}
