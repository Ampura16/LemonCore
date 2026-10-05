package com.destroystokyo.paper.event.player;

import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家射出弹射物时调用。
 * <p>
 * 注意，箭矢不会触发该事件，因为玩家并非直接发射箭矢，而是借助弓或弩射出。插件可改为监听 {@link EntityShootBowEvent} 以处理这些操作。
 * <p>原文：
 * Called when a player shoots a projectile.
 * <p>
 * Notably this event is not called for arrows as the player does not launch them, rather shoots them with the help
 * of a bow or crossbow. A plugin may listen to {@link EntityShootBowEvent}
 * for these actions instead.
 */
@NullMarked
public class PlayerLaunchProjectileEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Projectile projectile;
    private final ItemStack itemStack;
    private boolean consumeItem = true;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerLaunchProjectileEvent(final Player shooter, final ItemStack itemStack, final Projectile projectile) {
        super(shooter);
        this.itemStack = itemStack;
        this.projectile = projectile;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取将由此事件发射的弹射物
     * <p>原文：
     * Gets the projectile which will be launched by this event
     *
     * @return 发射的弹射物
     * <p>原文：the launched projectile
     */
    public Projectile getProjectile() {
        return this.projectile;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取用于发射弹射物的 ItemStack
     * <p>原文：
     * Get the ItemStack used to fire the projectile
     *
     * @return 所使用的 ItemStack
     * <p>原文：The ItemStack used
     */
    public ItemStack getItemStack() {
        return this.itemStack;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取是否消耗该 ItemStack
     * <p>原文：
     * Get whether to consume the ItemStack or not
     *
     * @return {@code true} 表示消耗
     * <p>原文：{@code true} to consume
     */
    public boolean shouldConsume() {
        return this.consumeItem;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否消耗该 ItemStack
     * <p>原文：
     * Set whether to consume the ItemStack or not
     *
     * @param consumeItem {@code true} 表示消耗
     * <p>原文：{@code true} to consume
     */
    public void setShouldConsume(final boolean consumeItem) {
        this.consumeItem = consumeItem;
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
