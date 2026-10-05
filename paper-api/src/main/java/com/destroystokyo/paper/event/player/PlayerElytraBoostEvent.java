package com.destroystokyo.paper.event.player;

import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家使用烟花火箭为鞘翅飞行加速时触发
 * <p>原文：
 * Fired when a player boosts elytra flight with a firework
 */
@NullMarked
public class PlayerElytraBoostEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ItemStack itemStack;
    private final Firework firework;
    private boolean consume = true;
    private final EquipmentSlot hand;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerElytraBoostEvent(final Player player, final ItemStack itemStack, final Firework firework, final EquipmentSlot hand) {
        super(player);
        this.itemStack = itemStack;
        this.firework = firework;
        this.hand = hand;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取所使用的烟花火箭物品堆
     * <p>原文：
     * Get the firework itemstack used
     *
     * @return 烟花火箭的 ItemStack
     * <p>原文：ItemStack of firework
     */
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取生成的烟花火箭实体
     * <p>原文：
     * Get the firework entity that was spawned
     *
     * @return 烟花火箭实体
     * <p>原文：Firework entity
     */
    public Firework getFirework() {
        return this.firework;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否消耗该烟花火箭
     * <p>原文：
     * Get whether to consume the firework or not
     *
     * @return {@code true} 表示消耗
     * <p>原文：{@code true} to consume
     */
    public boolean shouldConsume() {
        return this.consume;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否消耗该烟花火箭
     * <p>原文：
     * Set whether to consume the firework or not
     *
     * @param consume {@code true} 表示消耗
     * <p>原文：{@code true} to consume
     */
    public void setShouldConsume(final boolean consume) {
        this.consume = consume;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取为此玩家加速所用烟花火箭所在的手。
     * <p>原文：
     * Gets the hand holding the firework used for boosting this player.
     *
     * @return 交互手
     * <p>原文：interaction hand
     */
    public EquipmentSlot getHand() {
        return this.hand;
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
