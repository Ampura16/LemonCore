package org.bukkit.event.player;

import org.bukkit.Warning;
import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家打开告示牌时触发此事件.
 * <p>原文：
 * This event is fired when a sign is opened by the player.
 *
 * @deprecated use {@link io.papermc.paper.event.player.PlayerOpenSignEvent}
 */
@Deprecated(forRemoval = true)
@Warning
public class PlayerSignOpenEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Sign sign;
    private final Side side;
    private final Cause cause;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerSignOpenEvent(@NotNull final Player player, @NotNull final Sign sign, @NotNull final Side side, @NotNull final Cause cause) {
        super(player);
        this.sign = sign;
        this.side = side;
        this.cause = cause;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被打开的告示牌.
     * <p>原文：
     * Gets the sign that was opened.
     *
     * @return 被打开的告示牌
     * <p>原文：opened sign
     */
    @NotNull
    public Sign getSign() {
        return this.sign;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被打开的告示牌的面.
     * <p>原文：
     * Gets side of the sign opened.
     *
     * @return 被打开的告示牌的面
     * <p>原文：side of sign opened
     */
    @NotNull
    public Side getSide() {
        return this.side;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取告示牌打开的原因.
     * <p>原文：
     * Gets the cause of the sign open.
     *
     * @return 告示牌打开的原因
     * <p>原文：sign open cause
     */
    @NotNull
    public Cause getCause() {
        return this.cause;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
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

    public enum Cause {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示告示牌因交互而被打开.
         * <p>原文：
         * Indicate the sign was opened because of an interaction.
         */
        INTERACT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示告示牌因放置而被打开.
         * <p>原文：
         * Indicate the sign was opened because the sign was placed.
         */
        PLACE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示告示牌因插件而被打开.
         * <p>原文：
         * Indicate the sign was opened because of a plugin.
         */
        PLUGIN,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 表示告示牌因未知原因而被打开.
         * <p>原文：
         * Indicate the sign was opened for an unknown reason.
         */
        UNKNOWN
    }
}
