package org.bukkit.event.entity;

import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当生物实体被驯服时触发本事件.
 * <p>原文：
 * Thrown when a LivingEntity is tamed
 */
public class EntityTameEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final AnimalTamer owner;
    private boolean cancelled;

    @ApiStatus.Internal
    public EntityTameEvent(@NotNull final LivingEntity entity, @NotNull final AnimalTamer owner) {
        super(entity);
        this.owner = owner;
    }

    @NotNull
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取驯服此实体的玩家.
     * <p>原文：
     * Gets the owning AnimalTamer
     *
     * @return 驯服此实体的玩家
     * <p>原文：the owning AnimalTamer
     */
    @NotNull
    public AnimalTamer getOwner() {
        return this.owner;
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
