package org.bukkit.event.vehicle;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 实体进入载具的事件
 * <p>原文：
 * Raised when an entity enters a vehicle.
 */
public class VehicleEnterEvent extends VehicleEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity entered;
    private boolean cancelled;

    @ApiStatus.Internal
    public VehicleEnterEvent(@NotNull final Vehicle vehicle, @NotNull final Entity entered) {
        super(vehicle);
        this.entered = entered;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取进入到载具中的实体.
     * <p>原文：
     * Gets the Entity that entered the vehicle.
     *
     * @return 进入到载具中的实体
     * <p>原文：the Entity that entered the vehicle
     */
    @NotNull
    public Entity getEntered() {
        return this.entered;
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
