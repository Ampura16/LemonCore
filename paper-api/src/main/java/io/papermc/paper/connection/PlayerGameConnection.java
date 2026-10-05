package io.papermc.paper.connection;

import org.bukkit.entity.Player;

public interface PlayerGameConnection extends PlayerCommonConnection {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将玩家推回配置阶段。
     * <p>
     * 默认情况下，玩家会一直停留在该阶段，直到其连接被 {@link PlayerConfigurationConnection#completeReconfiguration()} 释放
     * <p>原文：
     * Bumps the player to the configuration stage.
     * <p>
     * This will, by default, cause the player to stay until their connection is released by
     * {@link PlayerConfigurationConnection#completeReconfiguration()}
     */
    void reenterConfiguration();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取与此游戏连接关联的玩家。
     * <p>原文：
     * Gets the player that is associated with this game connection.
     *
     * @return 玩家
     * <p>原文：player
     */
    Player getPlayer();
}
