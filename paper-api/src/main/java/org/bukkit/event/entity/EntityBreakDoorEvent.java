package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当{@link Entity 实体}破坏门时触发本事件.
 * <p>
 * 取消该事件将导致该事件被延迟.
 * <p>原文：
 * Called when an {@link Entity} breaks a door
 * <p>
 * Cancelling the event will cause the event to be delayed
 */
public class EntityBreakDoorEvent extends EntityChangeBlockEvent {

    @ApiStatus.Internal
    public EntityBreakDoorEvent(@NotNull final LivingEntity entity, @NotNull final Block targetBlock, @NotNull final org.bukkit.block.data.BlockData to) {
        super(entity, targetBlock, to);
    }

    @NotNull
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) this.entity;
    }
}
