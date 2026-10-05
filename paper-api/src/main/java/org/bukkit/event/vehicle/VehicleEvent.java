package org.bukkit.event.vehicle;

import org.bukkit.entity.Vehicle;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表所有和载具有关的事件
 * <p>原文：
 * Represents a vehicle-related event.
 */
public abstract class VehicleEvent extends Event {

    protected Vehicle vehicle;

    protected VehicleEvent(@NotNull final Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取发生事件的载具.
     * <p>原文：
     * Get the vehicle.
     *
     * @return 发生事件的载具
     * <p>原文：the vehicle
     */
    @NotNull
    public final Vehicle getVehicle() {
        return this.vehicle;
    }
}
