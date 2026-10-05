package org.bukkit.event.vehicle;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 载具被损毁的事件（包含被玩家/自然损坏）。若有一个船直接被"删除"了，这个事件将不会被调用.
 * <p>原文：
 * Raised when a vehicle is destroyed, which could be caused by either a
 * player or the environment. This is not raised if the boat is simply
 * 'removed' due to other means.
 */
public class VehicleDestroyEvent extends VehicleEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity attacker;
    private boolean cancelled;

    @ApiStatus.Internal
    public VehicleDestroyEvent(@NotNull final Vehicle vehicle, @Nullable final Entity attacker) {
        super(vehicle);
        this.attacker = attacker;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取损毁载具的实体，可能为null.
     * <p>原文：
     * Gets the Entity that has destroyed the vehicle, potentially null
     *
     * @return 损毁载具的实体，可能为null
     * <p>原文：the Entity that has destroyed the vehicle, potentially null
     */
    @Nullable
    public Entity getAttacker() {
        return this.attacker;
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
