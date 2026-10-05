package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当非玩家实体从一处传送到另一处时触发本事件.
 * <br>
 * 本事件可能因实体自然行为(末影人、潜影贝), 寻路动作(狼), 或者命令(/teleport)而触发.
 * <p>原文：
 * Thrown when a non-player entity is teleported from one location to another.
 * <br>
 * This may be as a result of natural causes (Enderman, Shulker), pathfinding
 * (Wolf), or commands (/teleport).
 */
public class EntityTeleportEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private Location from;
    private Location to;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityTeleportEvent(@NotNull Entity entity, @NotNull Location from, @Nullable Location to) {
        super(entity);
        this.from = from;
        this.to = to;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体传送起始位置.
     * <p>原文：
     * Gets the location that this entity moved from
     *
     * @return 传送起始位置
     * <p>原文：Location this entity moved from
     */
    @NotNull
    public Location getFrom() {
        return this.from;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体传送起始位置.
     * <p>原文：
     * Sets the location that this entity moved from
     *
     * @param from 传送起始位置
     * <p>原文：New location this entity moved from
     */
    public void setFrom(@NotNull Location from) {
        this.from = from.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体传送目标位置.
     * <p>原文：
     * Gets the location that this entity moved to
     *
     * @return 目标位置
     * <p>原文：Location the entity moved to
     */
    @Nullable
    public Location getTo() {
        return this.to;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体传送目标位置.
     * <p>原文：
     * Sets the location that this entity moved to
     *
     * @param to 目标位置
     * <p>原文：New Location this entity moved to
     */
    public void setTo(@Nullable Location to) {
        this.to = to != null ? to.clone() : null;
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
