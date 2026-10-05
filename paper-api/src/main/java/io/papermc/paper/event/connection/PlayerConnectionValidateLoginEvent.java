package io.papermc.paper.event.connection;

import io.papermc.paper.connection.PlayerConfigurationConnection;
import io.papermc.paper.connection.PlayerConnection;
import io.papermc.paper.connection.PlayerLoginConnection;
import net.kyori.adventure.text.Component;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 验证玩家连接能否登录。
 * <p>
 * 在玩家首次尝试登录或即将完成配置时调用。
 * <p>原文：
 * Validates whether a player connection is able to log in.
 * <p>
 * Called when a player is attempting to log in for the first time,
 * or is finishing up being configured.
 *
 * @implNote 自 1.21.7 起，在这些阶段中 {@link #getConnection() connection} 为 {@link PlayerLoginConnection} 或 {@link PlayerConfigurationConnection}，从而可以访问特定于阶段的 API。<br>但这些底层连接类型不保证在不同版本间保持稳定。
 * <p>原文：Since 1.21.7, during these phases, the {@link #getConnection() connection}
 * is either {@link PlayerLoginConnection} or {@link PlayerConfigurationConnection},
 * allowing access to phase-specific API.
 * <br>However, these underlying connection types are not guaranteed to be stable across versions.
 */
public class PlayerConnectionValidateLoginEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final PlayerConnection connection;
    private @Nullable Component kickMessage;

    @ApiStatus.Internal
    public PlayerConnectionValidateLoginEvent(final PlayerConnection connection, final @Nullable Component kickMessage) {
        super(false);
        this.connection = connection;
        this.kickMessage = kickMessage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件中玩家的连接。
     * <p>原文：
     * Gets the connection of the player in this event.
     * @apiNote 不支持通过此连接断开玩家连接或使用任何可能发送数据包的方法。
     * <p>原文：Disconnecting the player through this connection
     * or using any methods that may send packets is not supported.
     *
     * @implNote 自 1.21.7 起，根据触发阶段的不同，此连接为 {@link PlayerLoginConnection} 或 {@link PlayerConfigurationConnection}，从而可以访问特定于阶段的 API。<br>但这些底层连接类型不保证在不同版本间保持稳定。
     * <p>原文：Since 1.21.7, this connection is either
     * {@link PlayerLoginConnection} or {@link PlayerConfigurationConnection}
     * depending on which phase it is fired in, allowing access to phase-specific API.
     * <br>However, these underlying connection types are not guaranteed to be stable across versions.
     *
     * @return 连接
     * <p>原文：connection
     */
    public PlayerConnection getConnection() {
        return this.connection;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 允许玩家登录。这会跳过所有登录验证检查。
     * <p>原文：
     * Allows the player to log in.
     * This skips any login validation checks.
     */
    public void allow() {
        this.kickMessage = null;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 禁止玩家登录，并给出指定原因
     * <p>原文：
     * Disallows the player from logging in, with the given reason
     *
     * @param message 显示给用户的踢出消息
     * <p>原文：Kick message to display to the user
     */
    public void kickMessage(final Component message) {
        this.kickMessage = message;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家不被允许加入服务器的原因。如果允许玩家登录则为 null。
     * <p>原文：
     * Gets the reason for why a player is not allowed to join the server.
     * This will be null in the case that the player is allowed to log in.
     *
     * @return 拒绝原因
     * <p>原文：disallow reason
     */
    public @Nullable Component getKickMessage() {
        return this.kickMessage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否允许玩家进入下一阶段。
     * <p>原文：
     * Gets if the player is allowed to enter the next stage.
     *
     * @return 是否允许
     * <p>原文：if allowed
     */
    public boolean isAllowed() {
        return this.kickMessage == null;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
