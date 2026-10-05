package io.papermc.paper.event.server;

import com.destroystokyo.paper.profile.PlayerProfile;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家的白名单状态改变时调用此事件
 * <p>原文：
 * This event gets called when the whitelist status of a player is changed
 */
@NullMarked
public class WhitelistStateUpdateEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final PlayerProfile playerProfile;
    private final WhitelistStatus status;

    private boolean cancelled;

    @ApiStatus.Internal
    public WhitelistStateUpdateEvent(final PlayerProfile playerProfile, final WhitelistStatus status) {
        this.playerProfile = playerProfile;
        this.status = status;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取白名单状态正在改变的玩家
     * <p>原文：
     * Gets the player whose whitelist status is being changed
     *
     * @return 状态正在被更改的玩家
     * <p>原文：the player whose status is being changed
     */
    public OfflinePlayer getPlayer() {
        return Bukkit.getOfflinePlayer(this.playerProfile.getId());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取白名单状态正在改变的玩家档案
     * <p>原文：
     * Gets the player profile whose whitelist status is being changed
     *
     * @return 状态正在被更改的玩家档案
     * <p>原文：the player profile whose status is being changed
     */
    public PlayerProfile getPlayerProfile() {
        return this.playerProfile;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家档案的状态变化
     * <p>原文：
     * Gets the status change of the player profile
     *
     * @return 白名单状态
     * <p>原文：the whitelist status
     */
    public WhitelistStatus getStatus() {
        return this.status;
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

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 白名单状态变更的枚举
     * <p>原文：
     * Enum for the whitelist status changes
     */
    public enum WhitelistStatus {
        ADDED, REMOVED
    }
}
