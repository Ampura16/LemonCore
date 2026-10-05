package io.papermc.paper.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.inventory.InventoryMoveItemEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当物品即将被漏斗投入堆肥时调用。若要阻止漏斗将物品移入堆肥桶，请取消 {@link InventoryMoveItemEvent}。
 * <p>原文：
 * Called when an item is about to be composted by a hopper.
 * To prevent hoppers from moving items into composters, cancel the {@link InventoryMoveItemEvent}.
 */
@NullMarked
public class CompostItemEvent extends BlockEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ItemStack item;
    private boolean willRaiseLevel;

    @ApiStatus.Internal
    public CompostItemEvent(final Block composter, final ItemStack item, final boolean willRaiseLevel) {
        super(composter);
        this.item = item;
        this.willRaiseLevel = willRaiseLevel;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取对堆肥桶使用的物品。
     * <p>原文：
     * Gets the item that was used on the composter.
     *
     * @return 物品
     * <p>原文：the item
     */
    public ItemStack getItem() {
        return this.item;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取堆肥桶是否会升高一级。
     * <p>原文：
     * Gets whether the composter will rise a level.
     *
     * @return 如果成功则为 {@code true}
     * <p>原文：{@code true} if successful
     */
    public boolean willRaiseLevel() {
        return this.willRaiseLevel;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置堆肥桶是否会升高一级。
     * <p>原文：
     * Sets whether the composter will rise a level.
     *
     * @param willRaiseLevel 如果堆肥桶应升高一级则为 {@code true}
     * <p>原文：{@code true} if the composter should rise a level
     */
    public void setWillRaiseLevel(final boolean willRaiseLevel) {
        this.willRaiseLevel = willRaiseLevel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}
