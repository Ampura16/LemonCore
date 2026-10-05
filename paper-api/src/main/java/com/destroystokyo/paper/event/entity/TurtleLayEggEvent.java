package com.destroystokyo.paper.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Turtle;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当海龟产卵时触发
 * <p>原文：
 * Fired when a Turtle lays eggs
 */
@NullMarked
public class TurtleLayEggEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Location location;
    private int eggCount;

    private boolean cancelled;

    @ApiStatus.Internal
    public TurtleLayEggEvent(final Turtle turtle, final Location location, final int eggCount) {
        super(turtle);
        this.location = location;
        this.eggCount = eggCount;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 正在产卵的海龟
     * <p>原文：
     * The turtle laying the eggs
     *
     * @return 海龟
     * <p>原文：The turtle
     */
    @Override
    public Turtle getEntity() {
        return (Turtle) super.getEntity();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取产卵的位置
     * <p>原文：
     * Get the location where the eggs are being laid
     *
     * @return 蛋的位置
     * <p>原文：Location of eggs
     */
    public Location getLocation() {
        return this.location.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取产卵的数量
     * <p>原文：
     * Get the number of eggs being laid
     *
     * @return 蛋的数量
     * <p>原文：Number of eggs
     */
    public int getEggCount() {
        return this.eggCount;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置产卵的数量
     * <p>原文：
     * Set the number of eggs being laid
     *
     * @param eggCount 蛋的数量
     * <p>原文：Number of eggs
     */
    public void setEggCount(final int eggCount) {
        if (eggCount < 1) {
            this.cancelled = true;
            return;
        }
        this.eggCount = Math.min(eggCount, 4);
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
