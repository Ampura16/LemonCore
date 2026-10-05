package com.destroystokyo.paper.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.potion.PotionEffect;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在信标效果施加给玩家时调用。
 * <p>原文：
 * Called when a beacon effect is being applied to a player.
 */
@NullMarked
public class BeaconEffectEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final boolean primary;
    private PotionEffect effect;

    private boolean cancelled;

    @ApiStatus.Internal
    public BeaconEffectEvent(final Block beacon, final PotionEffect effect, final Player player, final boolean primary) {
        super(beacon);
        this.effect = effect;
        this.player = player;
        this.primary = primary;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正在施加的药水效果。
     * <p>原文：
     * Gets the potion effect being applied.
     *
     * @return 药水效果
     * <p>原文：Potion effect
     */
    public PotionEffect getEffect() {
        return this.effect;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置将要施加的药水效果。
     * <p>原文：
     * Sets the potion effect that will be applied.
     *
     * @param effect 药水效果
     * <p>原文：Potion effect
     */
    public void setEffect(final PotionEffect effect) {
        this.effect = effect;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取被施加药水效果的玩家。
     * <p>原文：
     * Gets the player who the potion effect is being applied to.
     *
     * @return 受影响的玩家
     * <p>原文：Affected player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取该效果是否为信标的主效果。
     * <p>原文：
     * Gets whether the effect is a primary beacon effect.
     *
     * @return 如果此事件代表主效果则为 {@code true}
     * <p>原文：{@code true} if this event represents a primary effect
     */
    public boolean isPrimary() {
        return this.primary;
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
}
