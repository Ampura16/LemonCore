package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Pose;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体改变它的姿态时触发本事件.
 * <p>原文：
 * Called when an entity changes its pose.
 *
 * @see Entity#getPose()
 */
public class EntityPoseChangeEvent extends EntityEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Pose pose;

    @ApiStatus.Internal
    public EntityPoseChangeEvent(@NotNull Entity entity, @NotNull Pose pose) {
        super(entity);
        this.pose = pose;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体的新姿态.
     * <p>原文：
     * Gets the entity's new pose.
     *
     * @return 新姿态
     * <p>原文：the new pose
     */
    @NotNull
    public Pose getPose() {
        return this.pose;
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
