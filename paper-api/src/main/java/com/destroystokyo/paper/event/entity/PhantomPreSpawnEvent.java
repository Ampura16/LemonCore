package com.destroystokyo.paper.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在为疲惫的玩家生成幻翼时调用
 * <p>原文：
 * Called when a phantom is spawned for an exhausted player
 */
@NullMarked
public class PhantomPreSpawnEvent extends PreCreatureSpawnEvent {

    private final Entity entity;

    @ApiStatus.Internal
    public PhantomPreSpawnEvent(final Location location, final Entity entity, final CreatureSpawnEvent.SpawnReason reason) {
        super(location, EntityType.PHANTOM, reason);
        this.entity = entity;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取该幻翼为之生成的实体
     * <p>原文：
     * Get the entity this phantom is spawning for
     *
     * @return 实体
     * <p>原文：the Entity
     */
    public Entity getSpawningEntity() {
        return this.entity;
    }
}
