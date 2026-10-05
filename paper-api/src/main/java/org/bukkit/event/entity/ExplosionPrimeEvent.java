package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Explosive;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体决定爆炸时调用。
 * <p>原文：
 * Called when an entity has made a decision to explode.
 */
public class ExplosionPrimeEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private float radius;
    private boolean fire;

    private boolean cancelled;

    @ApiStatus.Internal
    public ExplosionPrimeEvent(@NotNull final Entity entity, final float radius, final boolean fire) {
        super(entity);
        this.radius = radius;
        this.fire = fire;
    }

    @ApiStatus.Internal
    public ExplosionPrimeEvent(@NotNull final Explosive explosive) {
        this(explosive, explosive.getYield(), explosive.isIncendiary());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取爆炸半径。
     * <p>原文：
     * Gets the radius of the explosion
     *
     * @return 返回爆炸半径
     * <p>原文：returns the radius of the explosion
     */
    public float getRadius() {
        return this.radius;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置爆炸半径。
     * <p>原文：
     * Sets the radius of the explosion
     *
     * @param radius 爆炸半径
     * <p>原文：the radius of the explosion
     */
    public void setRadius(float radius) {
        this.radius = radius;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此爆炸是否会创造火。
     * <p>原文：
     * Gets whether this explosion will create fire or not
     *
     * @return 如果此爆炸会创造火则返回 true
     * <p>原文：{@code true} if this explosion will create fire
     */
    public boolean getFire() {
        return this.fire;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此爆炸是否会创造火。
     * <p>原文：
     * Sets whether this explosion will create fire or not
     *
     * @param fire 如果你希望此爆炸创造火则设置为 true
     * <p>原文：{@code true} if you want this explosion to create fire
     */
    public void setFire(boolean fire) {
        this.fire = fire;
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
}
