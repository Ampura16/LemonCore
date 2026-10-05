package io.papermc.paper.connection;

import com.destroystokyo.paper.ClientOption;
import com.destroystokyo.paper.profile.PlayerProfile;
import net.kyori.adventure.audience.Audience;

public interface PlayerConfigurationConnection extends PlayerCommonConnection {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回表示处于配置模式下玩家的受众。可用于在配置阶段与 Adventure API 交互。保证为 {@link PlayerConfigurationConnection} 的实例
     * <p>原文：
     * Returns the audience representing the player in configuration mode.
     * This can be used to interact with the Adventure API during the configuration stage.
     * This is guaranteed to be an instance of {@link PlayerConfigurationConnection}
     *
     * @return 正在配置的玩家受众
     * <p>原文：the configuring player audience
     */
    Audience getAudience();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此连接的档案。
     * <p>原文：
     * Gets the profile for this connection.
     *
     * @return 档案
     * <p>原文：profile
     */
    PlayerProfile getProfile();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 清除玩家的聊天历史及其本地聊天记录。
     * <p>原文：
     * Clears the players chat history and their local chat.
     */
    void clearChat();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 完成此玩家的配置，这将使玩家重新进入游戏。
     * <p>
     * 注意，仅应在你正在重新配置玩家时调用。
     * <p>原文：
     * Completes the configuration for this player, which will cause this player to reenter the game.
     * <p>
     * Note, this should be only be called if you are reconfiguring the player.
     */
    void completeReconfiguration();

}
