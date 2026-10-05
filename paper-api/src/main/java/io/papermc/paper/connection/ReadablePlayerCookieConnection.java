package io.papermc.paper.connection;

import java.util.concurrent.CompletableFuture;
import org.bukkit.NamespacedKey;

public interface ReadablePlayerCookieConnection extends PlayerConnection {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从此连接获取 cookie。
     * <p>原文：
     * Retrieves a cookie from this connection.
     *
     * @param key 标识该 cookie 的键
     * <p>原文：the key identifying the cookie
     * @return 一个 {@link CompletableFuture}，在收到 Cookie 响应或以其他方式可用时完成。如果客户端中未设置该 cookie，{@link CompletableFuture} 将以 null 值完成。
     * <p>原文：a {@link CompletableFuture} that will be completed when the
     * Cookie response is received or otherwise available. If the cookie is not
     * set in the client, the {@link CompletableFuture} will complete with a
     * null value.
     */
    CompletableFuture<byte[]> retrieveCookie(NamespacedKey key);
}
