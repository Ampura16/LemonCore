package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家的经验值冷却时间改变时触发.
 * <p>原文：
 * Called when a player's experience cooldown changes.
 */
public class PlayerExpCooldownChangeEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ChangeReason reason;
    private int newCooldown;

    @ApiStatus.Internal
    public PlayerExpCooldownChangeEvent(@NotNull final Player player, int newCooldown, @NotNull ChangeReason reason) {
        super(player);
        this.newCooldown = newCooldown;
        this.reason = reason;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取变更原因.
     * <p>原文：
     * Gets the reason for the change.
     *
     * @return 变更原因
     * <p>原文：The reason for the change
     */
    @NotNull
    public ChangeReason getReason() {
        return this.reason;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家的新冷却时间.
     * <p>原文：
     * Gets the new cooldown for the player.
     *
     * @return 新冷却时间
     * <p>原文：The new cooldown
     * @see Player#getExpCooldown()
     */
    public int getNewCooldown() {
        return this.newCooldown;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家的新冷却时间.
     * <p>原文：
     * Sets the new cooldown for the player.
     *
     * @param newCooldown 要设置的新冷却时间
     * <p>原文：The new cooldown to set
     * @see Player#setExpCooldown(int)
     */
    public void setNewCooldown(int newCooldown) {
        this.newCooldown = newCooldown;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public enum ChangeReason {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 冷却时间由拾取经验球设置.
         * <p>原文：
         * The cooldown was set by picking up an experience orb.
         */
        PICKUP_ORB,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 冷却时间由插件设置.
         * <p>原文：
         * The cooldown was set by a plugin.
         *
         * @see Player#setExpCooldown(int)
         */
        PLUGIN;
    }
}
