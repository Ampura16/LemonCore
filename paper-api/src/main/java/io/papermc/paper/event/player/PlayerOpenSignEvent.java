package io.papermc.paper.event.player;

import org.bukkit.block.Sign;
import org.bukkit.block.sign.Side;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在玩家开始编辑告示牌文字时调用。
 * <p>
 * 取消该事件将阻止告示牌编辑界面打开。
 * <p>原文：
 * Called when a player begins editing a sign's text.
 * <p>
 * Cancelling this event stops the sign editing menu from opening.
 */
@NullMarked
public class PlayerOpenSignEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Sign sign;
    private final Side side;
    private final Cause cause;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerOpenSignEvent(final Player editor, final Sign sign, final Side side, final Cause cause) {
        super(editor);
        this.sign = sign;
        this.side = side;
        this.cause = cause;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取被点击的告示牌。
     * <p>原文：
     * Gets the sign that was clicked.
     *
     * @return 被点击的 {@link Sign}
     * <p>原文：{@link Sign} that was clicked
     */
    public Sign getSign() {
        return this.sign;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取告示牌被点击的是哪一面。
     * <p>原文：
     * Gets which side of the sign was clicked.
     *
     * @return 被点击的 {@link Side}
     * <p>原文：{@link Side} that was clicked
     * @see Sign#getSide(Side)
     */
    public Side getSide() {
        return this.side;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 打开此告示牌的原因。
     * <p>原文：
     * The cause of this sign open.
     *
     * @return 原因
     * <p>原文：the cause
     */
    public Cause getCause() {
        return this.cause;
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
     * {@link PlayerOpenSignEvent} 的原因。
     * <p>原文：
     * The cause of the {@link PlayerOpenSignEvent}.
     */
    public enum Cause {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 该事件由放置告示牌触发。
         * <p>原文：
         * The event was triggered by the placement of a sign.
         */
        PLACE,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 该事件由与告示牌的交互触发。
         * <p>原文：
         * The event was triggered by an interaction with a sign.
         */
        INTERACT,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 该事件由插件通过 {@link HumanEntity#openSign(Sign, Side)} 触发
         * <p>原文：
         * The event was triggered via a plugin with {@link HumanEntity#openSign(Sign, Side)}
         */
        PLUGIN,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 任何未知原因的后备原因。
         * <p>原文：
         * Fallback cause for any unknown cause.
         */
        UNKNOWN,
    }
}
