package com.destroystokyo.paper.event.entity;

import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 每当实体因任何原因（包括区块加载）被添加到世界时触发。
 * <p>
 * 不要与 {@link CreatureSpawnEvent} 混淆
 * <p>原文：
 * Fired any time an entity is being added to the world for any reason (including a chunk loading).
 * <p>
 * Not to be confused with {@link CreatureSpawnEvent}
 */
@NullMarked
public class EntityAddToWorldEvent extends EntityEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final World world;

    @ApiStatus.Internal
    public EntityAddToWorldEvent(final Entity entity, final World world) {
        super(entity);
        this.world = world;
    }

    /**
     * @return 实体被添加到的世界
     * <p>原文：The world that the entity is being added to
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
