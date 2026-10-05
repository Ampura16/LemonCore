package io.papermc.paper.connection;

import org.bukkit.NamespacedKey;

public interface WritablePlayerCookieConnection extends PlayerConnection {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 在此玩家的客户端中存储 cookie。
     * <p>原文：
     * Stores a cookie in this player's client.
     *
     * @param key 标识该 cookie 的键
     * <p>原文：the key identifying the cookie
     * @param value 要存储在 cookie 中的数据
     * <p>原文：the data to store in the cookie
     * @throws IllegalStateException 如果此时无法存储 cookie
     * <p>原文：if a cookie cannot be stored at this time
     */
    void storeCookie(NamespacedKey key, byte[] value);
}
