package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体生成于世界内时触发本事件.
 * <p>
 * 如果本事件被取消, 那么实体将不会生成.
 * <p>原文：
 * Called when an entity is spawned into a world.
 * <p>
 * If this event is cancelled, the entity will not spawn.
 */
public class EntitySpawnEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private boolean cancelled;

    @ApiStatus.Internal
    public EntitySpawnEvent(@NotNull final Entity spawnee) {
        super(spawnee);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体出生的位置.
     * <p>原文：
     * Gets the location at which the entity is spawning.
     *
     * @return 实体出生的位置
     * <p>原文：The location at which the entity is spawning
     */
    @NotNull
    public Location getLocation() {
        return this.getEntity().getLocation();
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
