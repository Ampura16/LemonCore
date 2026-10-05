package io.papermc.paper.connection;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.jspecify.annotations.Nullable;

public interface PlayerLoginConnection extends ReadablePlayerCookieConnection {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此连接经过验证的档案。根据连接所处的阶段，可能返回 null。
     * <p>原文：
     * Gets the authenticated profile for this connection.
     * This may return null depending on what stage this connection is at.
     *
     * @return 经过验证的档案；如果不存在则为 null
     * <p>原文：authenticated profile, or null if not present
     */
    @Nullable PlayerProfile getAuthenticatedProfile();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此连接请求验证所用的玩家档案。
     * <p>原文：
     * Gets the player profile that this connection is requesting to authenticate as.
     *
     * @return 不安全的未验证档案；如果未发送则为 null
     * <p>原文：the unsafe unauthenticated profile, or null if not sent
     */
    @Nullable
    PlayerProfile getUnsafeProfile();
}
