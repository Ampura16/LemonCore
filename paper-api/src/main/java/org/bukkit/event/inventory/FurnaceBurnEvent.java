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
 * 当一个物品作为燃料被燃烧的时候触发这个事件.
 * <p>原文：
 * Called when an ItemStack is successfully burned as fuel in a furnace-like block such as a
 * {@link org.bukkit.block.Furnace}, {@link org.bukkit.block.Smoker}, or
 * {@link org.bukkit.block.BlastFurnace}.
 */
public class FurnaceBurnEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ItemStack fuel;
    private int burnTime;
    private boolean burning = true;
    private boolean consumeFuel = true;

    private boolean cancelled;

    @ApiStatus.Internal
    public FurnaceBurnEvent(@NotNull final Block furnace, @NotNull final ItemStack fuel, final int burnTime) {
        super(furnace);
        this.fuel = fuel;
        this.burnTime = burnTime;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个事件中的燃料.
     * <p>原文：
     * Gets the fuel ItemStack for this event
     *
     * @return 被燃烧的燃料
     * <p>原文：the fuel ItemStack
     */
    @NotNull
    public ItemStack getFuel() {
        return this.fuel;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此燃料的燃烧时间.
     * <p>原文：
     * Gets the burn time for this fuel
     *
     * @return 此燃料的燃烧时间
     * <p>原文：the burn time for this fuel
     */
    public int getBurnTime() {
        return this.burnTime;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此燃料的燃烧时间.
     * <p>原文：
     * Sets the burn time for this fuel
     *
     * @param burnTime 此燃料的燃烧时间
     * <p>原文：the burn time for this fuel
     */
    public void setBurnTime(@org.jetbrains.annotations.Range(from = Short.MIN_VALUE, to = Short.MAX_VALUE) int burnTime) {
        this.burnTime = Math.clamp(burnTime, Short.MIN_VALUE, Short.MAX_VALUE);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取熔炉中的燃料是否正在燃烧.
     * <p>原文：
     * Gets whether the furnace's fuel is burning or not.
     *
     * @return 熔炉中的燃料是否正在燃烧
     * <p>原文：whether the furnace's fuel is burning or not.
     */
    public boolean isBurning() {
        return this.burning;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置熔炉中的燃料是否正在燃烧.
     * <p>原文：
     * Sets whether the furnace's fuel is burning or not.
     *
     * @param burning 熔炉中的燃料是否正在燃烧
     * <p>原文：{@code true} if the furnace's fuel is burning
     */
    public void setBurning(boolean burning) {
        this.burning = burning;
    }

    /**
     * Gets whether the furnace's fuel will be consumed or not.
     *
     * @return whether the furnace's fuel will be consumed
     */
    public boolean willConsumeFuel() {
        return this.consumeFuel;
    }

    /**
     * Sets whether the furnace's fuel will be consumed or not.
     *
     * @param consumeFuel {@code true} to consume the fuel
     */
    public void setConsumeFuel(boolean consumeFuel) {
        this.consumeFuel = consumeFuel;
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
