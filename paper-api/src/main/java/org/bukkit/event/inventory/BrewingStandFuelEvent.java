package org.bukkit.event.inventory;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个 ItemStack 即将增加酿造台的燃料等级时调用。
 * <p>原文：
 * Called when an ItemStack is about to increase the fuel level of a brewing
 * stand.
 */
public class BrewingStandFuelEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ItemStack fuel;
    private int fuelPower;
    private boolean consuming = true;

    private boolean cancelled;

    @ApiStatus.Internal
    public BrewingStandFuelEvent(@NotNull Block brewingStand, @NotNull ItemStack fuel, int fuelPower) {
        super(brewingStand);
        this.fuel = fuel;
        this.fuelPower = fuelPower;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取燃料的 ItemStack，在数量被扣除之前。
     * <p>原文：
     * Gets the ItemStack of the fuel before the amount was subtracted.
     *
     * @return 燃料的 ItemStack
     * <p>原文：Gets the ItemStack of the fuel before the amount was subtracted.
     * <p>原文：the fuel ItemStack
     */
    @NotNull
    public ItemStack getFuel() {
        return this.fuel;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此燃料的燃料能量。每单位能量可以为一次酿造操作提供燃料。
     * <p>原文：
     * Gets the fuel power for this fuel. Each unit of power can fuel one
     * brewing operation.
     *
     * @return 此燃料的燃料能量
     * <p>原文：Gets the fuel power for this fuel. Each unit of power can fuel one
     * brewing operation.
     * <p>原文：the fuel power for this fuel
     */
    public int getFuelPower() {
        return this.fuelPower;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此燃料的燃料能量。每单位能量可以为一次酿造操作提供燃料。
     * <p>原文：
     * Sets the fuel power for this fuel. Each unit of power can fuel one
     * brewing operation.
     *
     * @param fuelPower 此燃料的燃料能量
     * <p>原文：Sets the fuel power for this fuel. Each unit of power can fuel one
     * brewing operation.
     * <p>原文：the fuel power for this fuel
     */
    public void setFuelPower(int fuelPower) {
        this.fuelPower = fuelPower;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取酿造台的燃料是否会被减少/消耗。
     * <p>原文：
     * Gets whether the brewing stand's fuel will be reduced / consumed or not.
     *
     * @return 燃料是否会被减少
     * <p>原文：Gets whether the brewing stand's fuel will be reduced / consumed or not.
     * <p>原文：whether the fuel will be reduced or not
     */
    public boolean isConsuming() {
        return this.consuming;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置酿造台的燃料是否会被减少/消耗。
     * <p>原文：
     * Sets whether the brewing stand's fuel will be reduced / consumed or not.
     *
     * @param consuming 燃料是否会被减少
     * <p>原文：Sets whether the brewing stand's fuel will be reduced / consumed or not.
     * <p>原文：whether the fuel will be reduced or not
     */
    public void setConsuming(boolean consuming) {
        this.consuming = consuming;
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
