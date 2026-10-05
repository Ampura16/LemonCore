package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体尝试骑乘另一个实体时调用。
 * <p>原文：
 * Called when an entity attempts to ride another entity.
 */
public class EntityMountEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity mount;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityMountEvent(@NotNull Entity entity, @NotNull Entity mount) {
        super(entity);
        this.mount = mount;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将被骑乘的实体。
     * <p>原文：
     * Gets the entity which will be ridden.
     *
     * @return 被骑乘的实体
     * <p>原文：mounted entity
     */
    @NotNull
    public Entity getMount() {
        return this.mount;
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
