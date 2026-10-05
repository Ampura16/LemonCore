package com.destroystokyo.paper.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当实体决定开始向某个位置移动时触发。
 * <p>
 * 该事件不会因实体的实际移动而触发，仅在其选择开始向某位置移动时触发。
 * <p>原文：
 * Fired when an Entity decides to start moving towards a location.
 * <p>
 * This event does not fire for the entities actual movement. Only when it
 * is choosing to start moving to a location.
 */
@NullMarked
public class EntityPathfindEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final @Nullable Entity targetEntity;
    private final Location location;
    private boolean cancelled;

    @ApiStatus.Internal
    public EntityPathfindEvent(final Entity entity, final Location location, final @Nullable Entity targetEntity) {
        super(entity);
        this.targetEntity = targetEntity;
        this.location = location;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 正在寻路的实体。
     * <p>原文：
     * The Entity that is pathfinding.
     *
     * @return 正在寻路的实体。
     * <p>原文：The Entity that is pathfinding.
     */
    @Override
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果实体正在尝试寻路到某个实体，则返回相关实体。<br> 否则返回 {@code null}。
     * <p>原文：
     * If the Entity is trying to pathfind to an entity, this is the entity in relation.
     * <br>
     * Otherwise, this will return {@code null}.
     *
     * @return 目标实体或 {@code null}
     * <p>原文：The entity target or {@code null}
     */
    public @Nullable Entity getTargetEntity() {
        return this.targetEntity;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 实体即将移动到的位置。<br> 注意，如果目标恰好是一个实体
     * <p>原文：
     * The Location of where the entity is about to move to.
     * <br>
     * Note that if the target happened to of been an entity
     *
     * @return 实体尝试寻路前往的位置。
     * <p>原文：Location of where the entity is trying to pathfind to.
     */
    public Location getLoc() {
        return this.location.clone();
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
