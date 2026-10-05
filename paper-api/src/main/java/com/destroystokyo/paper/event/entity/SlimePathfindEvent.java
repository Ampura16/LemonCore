package com.destroystokyo.paper.event.entity;

import org.bukkit.entity.Slime;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当史莱姆决定开始寻路时触发。
 * <p>
 * 该事件不会因实体的实际移动而触发，仅在其选择开始移动时触发。
 * <p>原文：
 * Fired when a Slime decides to start pathfinding.
 * <p>
 * This event does not fire for the entity's actual movement. Only when it
 * is choosing to start moving.
 */
@NullMarked
public class SlimePathfindEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private boolean cancelled;

    @ApiStatus.Internal
    public SlimePathfindEvent(final Slime slime) {
        super(slime);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 正在寻路的史莱姆。
     * <p>原文：
     * The Slime that is pathfinding.
     *
     * @return 正在寻路的史莱姆。
     * <p>原文：The Slime that is pathfinding.
     */
    @Override
    public Slime getEntity() {
        return (Slime) super.getEntity();
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
