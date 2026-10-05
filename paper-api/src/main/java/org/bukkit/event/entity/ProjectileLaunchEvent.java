package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个抛射物被发射时触发本事件.
 * <p>原文：
 * Called when a projectile is launched.
 */
public class ProjectileLaunchEvent extends EntitySpawnEvent implements Cancellable {

    private boolean cancelled;

    @ApiStatus.Internal
    public ProjectileLaunchEvent(@NotNull Entity entity) {
        super(entity);
    }

    @NotNull
    @Override
    public Projectile getEntity() {
        return (Projectile) this.entity;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }
}
