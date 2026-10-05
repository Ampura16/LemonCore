package com.destroystokyo.paper.event.entity;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 每当实体因任何原因（包括区块卸载）从世界中移除时触发。注意：实体在调用该事件之前已更新，因此实体所在的世界可能不等于 {@link #getWorld()}。
 * <p>原文：
 * Fired any time an entity is being removed from a world for any reason (including a chunk unloading).
 * Note: The entity is updated prior to this event being called, as such, the entity's world may not be equal to {@link #getWorld()}.
 */
@NullMarked
public class EntityRemoveFromWorldEvent extends EntityEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final World world;

    @ApiStatus.Internal
    public EntityRemoveFromWorldEvent(final Entity entity, final World world) {
        super(entity);
        this.world = world;
    }

    /**
     * @return 实体被移除所在的世界
     * <p>原文：The world that the entity is being removed from
     */
    public World getWorld() {
        return this.world;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
