package io.papermc.paper.event.player;

import io.papermc.paper.block.bed.BedEnterAction;
import net.kyori.adventure.text.Component;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public class PlayerBedFailEnterEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final FailReason failReason;
    private final Block bed;
    private final BedEnterAction enterAction;
    private boolean willExplode;
    private @Nullable Component message;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerBedFailEnterEvent(final Player player, final FailReason failReason, final Block bed, final boolean willExplode, final @Nullable Component message, BedEnterAction enterAction) {
        super(player);
        this.failReason = failReason;
        this.bed = bed;
        this.enterAction = enterAction;
        this.willExplode = willExplode;
        this.message = message;
    }

    /**
     * @deprecated 该枚举已被更能体现床工作方式的系统取代。参见 {@link #enterAction}
     * <p>原文：This enum has been replaced with a system that better
     * represents how beds work. See {@link #enterAction}
     */
    @ApiStatus.Obsolete(since = "1.21.11")
    public FailReason getFailReason() {
        return this.failReason;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 描述此事件的默认结果。
     * <p>原文：
     * This describes the default outcome of this event.
     *
     * @return 表示此事件默认结果的操作
     * <p>原文：the action representing the default outcome of this event
     */
    @ApiStatus.Experimental
    public BedEnterAction enterAction() {
        return this.enterAction;
    }

    public Block getBed() {
        return this.bed;
    }

    public boolean getWillExplode() {
        return this.willExplode;
    }

    public void setWillExplode(final boolean willExplode) {
        this.willExplode = willExplode;
    }

    public @Nullable Component getMessage() {
        return this.message;
    }

    public void setMessage(final @Nullable Component message) {
        this.message = message;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <b>NOTE</b>: This does not cancel the player getting in the bed, but any messages/explosions
     * that may occur because of the interaction.
     */
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
     * @deprecated 这些枚举已无法可靠地表示床的工作和失败方式，已被更适合新版床机制的 {@link BedEnterAction} 取代
     * <p>原文：Enums no longer represents reliably how beds work and fail. This has been
     * replaced with {@link BedEnterAction} that better fits the new beds
     */
    @ApiStatus.Obsolete(since = "1.21.11")
    public enum FailReason {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 该世界不允许睡觉（例如下界或末地）。上床被阻止，但床不会爆炸。床爆炸时改为使用 {@link #EXPLOSION}。
         * <p>原文：
         * The world doesn't allow sleeping (ex. Nether or The End). Entering
         * the bed is prevented but the bed doesn't explode. When the bed
         * explodes, {@link #EXPLOSION} is used instead.
         */
        NOT_POSSIBLE_HERE,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 由于当前既不是夜晚也没有雷暴，阻止上床。
         * <p>
         * 如果在白天强制允许该事件，玩家会上床（并设置床的位置），但可能立即被弹出。
         * <p>原文：
         * Entering the bed is prevented due to it not being night nor
         * thundering currently.
         * <p>
         * If the event is forcefully allowed during daytime, the player will
         * enter the bed (and set its bed location), but might get immediately
         * thrown out again.
         */
        NOT_POSSIBLE_NOW,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 由于玩家距离太远，阻止上床。
         * <p>原文：
         * Entering the bed is prevented due to the player being too far away.
         */
        TOO_FAR_AWAY,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 床被阻挡。
         * <p>原文：
         * Bed is obstructed.
         */
        OBSTRUCTED,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 由于存在其他问题，阻止上床。
         * <p>原文：
         * Entering the bed is prevented due to there being some other problem.
         */
        OTHER_PROBLEM,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 由于附近有怪物，阻止上床。
         * <p>原文：
         * Entering the bed is prevented due to there being monsters nearby.
         */
        NOT_SAFE,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 阻止上床，且床会爆炸。
         * <p>原文：
         * Entering the bed is prevented and the bed explodes.
         */
        EXPLOSION
    }
}
