package org.bukkit.event.vehicle;

import org.bukkit.Location;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 载具移动事件
 * <p>原文：
 * Raised when a vehicle moves.
 */
public class VehicleMoveEvent extends VehicleEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Location from;
    private final Location to;

    @ApiStatus.Internal
    public VehicleMoveEvent(@NotNull final Vehicle vehicle, @NotNull final Location from, @NotNull final Location to) {
        super(vehicle);

        this.from = from;
        this.to = to;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取载具的上一个位置.
     * <p>原文：
     * Get the previous position.
     *
     * @return 旧的位置
     * <p>原文：Old position.
     */
    @NotNull
    public Location getFrom() {
        return this.from.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取载具新的位置.
     * <p>原文：
     * Get the next position.
     *
     * @return 新的位置
     * <p>原文：New position.
     */
    @NotNull
    public Location getTo() {
        return this.to.clone();
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
