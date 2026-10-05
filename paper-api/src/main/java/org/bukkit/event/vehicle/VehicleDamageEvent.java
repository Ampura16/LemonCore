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
 * 载具受到伤害的事件
 * <p>原文：
 * Raised when a vehicle receives damage.
 */
public class VehicleDamageEvent extends VehicleEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity attacker;
    private double damage;

    private boolean cancelled;

    @ApiStatus.Internal
    public VehicleDamageEvent(@NotNull final Vehicle vehicle, @Nullable final Entity attacker, final double damage) {
        super(vehicle);
        this.attacker = attacker;
        this.damage = damage;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取攻击这个载具的实体.
     * <p>原文：
     * Gets the Entity that is attacking the vehicle
     *
     * @return 攻击这个载具的实体
     * <p>原文：the Entity that is attacking the vehicle
     */
    @Nullable
    public Entity getAttacker() {
        return this.attacker;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取载具受到的伤害.
     * <p>原文：
     * Gets the damage done to the vehicle
     *
     * @return 载具受到的伤害
     * <p>原文：the damage done to the vehicle
     */
    public double getDamage() {
        return this.damage;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置载具受到的伤害.
     * <p>原文：
     * Sets the damage done to the vehicle
     *
     * @param damage 载具受到的伤害
     * <p>原文：The damage
     */
    public void setDamage(double damage) {
        this.damage = damage;
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
