package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 物品被方块(比如发射器,投掷器等)射出的事件.
 * <p>
 * 取消该事件, 物品就不会被射出.
 * <p>原文：
 * Called when an item is dispensed from a block.
 * <p>
 * If this event is cancelled, the block will not dispense the
 * item.
 */
public class BlockDispenseEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private ItemStack item;
    private Vector velocity;

    private boolean cancelled;

    @ApiStatus.Internal
    public BlockDispenseEvent(@NotNull final Block block, @NotNull final ItemStack item, @NotNull final Vector velocity) {
        super(block);
        this.item = item;
        this.velocity = velocity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被射出的物品.
     * <p>
     * 注意:修改返回的item对象没用, 必须用 {@link #setItem(org.bukkit.inventory.ItemStack)} 代替.
     * <p>原文：
     * Gets the item that is being dispensed. Modifying the returned item will
     * have no effect, you must use {@link
     * #setItem(ItemStack)} instead.
     *
     * @return 被射出的物品
     * <p>原文：An ItemStack for the item being dispensed
     */
    @NotNull
    public ItemStack getItem() {
        return this.item.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置被射出的物品.
     * <p>原文：
     * Sets the item being dispensed.
     *
     * @param item 被射出的物品
     * <p>原文：the item being dispensed
     */
    public void setItem(@NotNull ItemStack item) {
        this.item = item;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品射出的速度、角度 (单位: 米每tick).
     * <p>
     * 注意: 修改返回的对象并不会修改速度, 必须
     * 用{@link #setVelocity(org.bukkit.util.Vector)}.
     * <p>原文：
     * Gets the velocity in meters per tick.
     * <p>
     * Note: Modifying the returned Vector will not change the velocity, you
     * must use {@link #setVelocity(Vector)} instead.
     *
     * @return 射出物品的速度的Vector(向量)
     * <p>原文：A Vector for the dispensed item's velocity
     */
    @NotNull
    public Vector getVelocity() {
        return this.velocity.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品射出的速度速度、角度 (单位: 米每tick).
     * <p>原文：
     * Sets the velocity of the item being dispensed in meters per tick.
     *
     * @param velocity the velocity of the item being dispensed
     */
    public void setVelocity(@NotNull Vector velocity) {
        this.velocity = velocity.clone();
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
