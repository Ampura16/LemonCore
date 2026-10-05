package com.destroystokyo.paper.event.player;

import net.kyori.adventure.text.Component;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家的重生点被设置时调用，无论是由玩家自己还是其他方式设置。<br> 取消该事件将阻止设置重生点。
 * <p>原文：
 * Called when a player's spawn is set, either by themselves or otherwise.
 * <br>
 * Cancelling this event will prevent the spawn from being set.
 */
@NullMarked
public class PlayerSetSpawnEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Cause cause;
    private @Nullable Location location;
    private boolean forced;
    private boolean notifyPlayer;
    private @Nullable Component notification;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerSetSpawnEvent(final Player player, final Cause cause, final @Nullable Location location, final boolean forced, final boolean notifyPlayer, final @Nullable Component notification) {
        super(player);
        this.cause = cause;
        this.location = location;
        this.forced = forced;
        this.notifyPlayer = notifyPlayer;
        this.notification = notification;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件的原因。
     * <p>原文：
     * Gets the cause of this event.
     *
     * @return 原因
     * <p>原文：the cause
     */
    public Cause getCause() {
        return this.cause;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取重生点被设置到的位置。该位置的偏航角即重生角度。修改该位置将改变玩家最终的重生点。使用 {@link Location#clone()} 获取该位置的副本。
     * <p>原文：
     * Gets the location that the spawn is set to. The yaw
     * of this location is the spawn angle. Mutating this location
     * will change the resulting spawn point of the player. Use
     * {@link Location#clone()} to get a copy of this location.
     *
     * @return 出生位置；如果要移除该位置则为 {@code null}
     * <p>原文：the spawn location, or {@code null} if removing the location
     */
    public @Nullable Location getLocation() {
        return this.location;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置要作为出生位置的位置。该位置的偏航角即出生角度。
     * <p>原文：
     * Sets the location to be set as the spawn location. The yaw
     * of this location is the spawn angle.
     *
     * @param location 出生位置，或 {@code null} 以移除出生位置
     * <p>原文：the spawn location, or {@code null} to remove the spawn location
     */
    public void setLocation(final @Nullable Location location) {
        this.location = location != null ? location.clone() : null;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取这是否为强制出生位置
     * <p>原文：
     * Gets if this is a force spawn location
     *
     * @return 如果被强制则为 {@code true}
     * <p>原文：{@code true} if forced
     */
    public boolean isForced() {
        return this.forced;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置这是否为强制出生位置
     * <p>原文：
     * Sets if this is a forced spawn location
     *
     * @param forced {@code true} 表示强制
     * <p>原文：{@code true} to force
     */
    public void setForced(final boolean forced) {
        this.forced = forced;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此操作是否会通知玩家其重生点已设置。
     * <p>原文：
     * Gets if this action will notify the player their spawn
     * has been set.
     *
     * @return {@code true} 表示通知
     * <p>原文：{@code true} to notify
     */
    public boolean willNotifyPlayer() {
        return this.notifyPlayer;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此操作是否会通知玩家其重生点已设置。
     * <p>原文：
     * Sets if this action will notify the player that their spawn
     * has been set.
     *
     * @param notifyPlayer {@code true} 表示通知
     * <p>原文：{@code true} to notify
     */
    public void setNotifyPlayer(final boolean notifyPlayer) {
        this.notifyPlayer = notifyPlayer;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取当 {@link #willNotifyPlayer()} 返回 {@code true} 时将发送给玩家的通知消息。
     * <p>原文：
     * Gets the notification message that will be sent to the player
     * if {@link #willNotifyPlayer()} returns {@code true}.
     *
     * @return 如果不通知则为 {@code null}
     * <p>原文：{@code null} if no notification
     */
    public @Nullable Component getNotification() {
        return this.notification;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置将发送给玩家的通知消息。
     * <p>原文：
     * Sets the notification message that will be sent to the player.
     *
     * @param notification {@code null} 表示不发送消息
     * <p>原文：{@code null} to send no message
     */
    public void setNotification(final @Nullable Component notification) {
        this.notification = notification;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public enum Cause {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 当玩家成功与床交互时。
         * <p>原文：
         * When a player interacts successfully with a bed.
         */
        BED,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 当玩家成功与重生锚交互时。
         * <p>原文：
         * When a player interacts successfully with a respawn anchor.
         */
        RESPAWN_ANCHOR,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 当玩家重生时。
         * <p>原文：
         * When a player respawns.
         */
        PLAYER_RESPAWN,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 当对玩家使用 {@code /spawnpoint} 命令时。
         * <p>原文：
         * When the {@code /spawnpoint} command is used on a player.
         */
        COMMAND,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 当插件使用 {@link Player#setRespawnLocation(Location)} 或 {@link Player#setRespawnLocation(Location, boolean)} 时。
         * <p>原文：
         * When a plugin uses {@link Player#setRespawnLocation(Location)} or
         * {@link Player#setRespawnLocation(Location, boolean)}.
         */
        PLUGIN,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 后备原因。
         * <p>原文：
         * Fallback cause.
         */
        UNKNOWN,
    }
}
