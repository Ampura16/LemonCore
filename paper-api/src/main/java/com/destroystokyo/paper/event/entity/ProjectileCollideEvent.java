package com.destroystokyo.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当弹射物与实体碰撞时调用
 * <p>
 * 该事件在 {@link EntityDamageByEntityEvent} <b>之前</b>调用，取消该事件将让弹射物继续飞行
 * <p>原文：
 * Called when a projectile collides with an entity
 * <p>
 * This event is called <b>before</b> {@link EntityDamageByEntityEvent}, and cancelling it will allow the projectile to continue flying
 *
 * @deprecated 已弃用，请使用 {@link org.bukkit.event.entity.ProjectileHitEvent} 并检查是否有被击中的实体
 * <p>原文：Deprecated, use {@link org.bukkit.event.entity.ProjectileHitEvent} and check if there is a hit entity
 */
@Deprecated(since = "1.19.3")
public class ProjectileCollideEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @NotNull private final Entity collidedWith;

    private boolean cancelled;

    @ApiStatus.Internal
    public ProjectileCollideEvent(@NotNull Projectile projectile, @NotNull Entity collidedWith) {
        super(projectile);
        this.collidedWith = collidedWith;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取发生碰撞的弹射物
     * <p>原文：
     * Get the projectile that collided
     *
     * @return 发生碰撞的弹射物
     * <p>原文：the projectile that collided
     */
    @NotNull
    public Projectile getEntity() {
        return (Projectile) super.getEntity();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取弹射物碰撞到的实体
     * <p>原文：
     * Get the entity the projectile collided with
     *
     * @return 碰撞到的实体
     * <p>原文：the entity collided with
     */
    @NotNull
    public Entity getCollidedWith() {
        return this.collidedWith;
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
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
