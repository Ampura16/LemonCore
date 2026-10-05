package com.destroystokyo.paper.event.player;

import java.net.InetAddress;
import java.util.UUID;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * <p>
 * 当玩家断开连接时调用此事件。如果服务器处于正版验证模式，保证所提供的 UUID 和用户名已经过验证。
 * <p>
 * 此事件会对尚未登录到世界的玩家调用，而 {@link PlayerQuitEvent} 仅对已登录到世界的玩家调用。
 * <p>
 * 此事件也会对已登录到世界的玩家调用，但触发时玩家是否存在于世界中是未定义的（即插件能否使用事件参数获取 Player 对象是未定义的）。但可以保证，如果玩家已登录到世界，此事件会在 {@link PlayerQuitEvent} 之后调用。
 * <p>
 * 保证除非事先触发了 {@link AsyncPlayerPreLoginEvent}，否则此事件永远不会触发，并且对同一连接，此事件不会与 {@link AsyncPlayerPreLoginEvent} 并行调用。
 * <p>
 * 取消 {@link AsyncPlayerPreLoginEvent} 可保证永远不会调用相应的 {@code PlayerConnectionCloseEvent}。
 * <p>
 * 此事件可能异步或同步调用。插件应检查 {@link Event#isAsynchronous()} 并据此处理。
 * <p>原文：
 * <p>
 * This event is invoked when a player has disconnected. It is guaranteed that,
 * if the server is in online-mode, that the provided uuid and username have been
 * validated.
 * <p>
 * The event is invoked for players who have not yet logged into the world, whereas
 * {@link PlayerQuitEvent} is only invoked on players who have logged into the world.
 * <p>
 * The event is invoked for players who have already logged into the world,
 * although whether or not the player exists in the world at the time of
 * firing is undefined. (That is, whether the plugin can retrieve a Player object
 * using the event parameters is undefined). However, it is guaranteed that this
 * event is invoked AFTER {@link PlayerQuitEvent}, if the player has already logged into the world.
 * <p>
 * This event is guaranteed to never fire unless {@link AsyncPlayerPreLoginEvent} has
 * been fired beforehand, and this event may not be called in parallel with
 * {@link AsyncPlayerPreLoginEvent} for the same connection.
 * <p>
 * Cancelling the {@link AsyncPlayerPreLoginEvent} guarantees the corresponding
 * {@code PlayerConnectionCloseEvent} is never called.
 * <p>
 * The event may be invoked asynchronously or synchronously. Plugins should check
 * {@link Event#isAsynchronous()} and handle accordingly.
 */
@NullMarked
public class PlayerConnectionCloseEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final UUID playerUniqueId;
    private final String playerName;
    private final InetAddress ipAddress;

    @ApiStatus.Internal
    public PlayerConnectionCloseEvent(final UUID playerUniqueId, final String playerName, final InetAddress ipAddress, final boolean async) {
        super(async);
        this.playerUniqueId = playerUniqueId;
        this.playerName = playerName;
        this.ipAddress = ipAddress;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回正在断开连接的玩家的 {@code UUID}。
     * <p>原文：
     * Returns the {@code UUID} of the player disconnecting.
     */
    public UUID getPlayerUniqueId() {
        return this.playerUniqueId;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回正在断开连接的玩家的名称。
     * <p>原文：
     * Returns the name of the player disconnecting.
     */
    public String getPlayerName() {
        return this.playerName;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回玩家的 IP 地址。
     * <p>原文：
     * Returns the player's IP address.
     */
    public InetAddress getIpAddress() {
        return this.ipAddress;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
