package org.bukkit.event.inventory;

import org.bukkit.Warning;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当物品被放入槽位并且结果被计算时调用。
 * <p>原文：
 * Called when an item is put in a slot and the result is calculated.
 *
 * @deprecated use {@link com.destroystokyo.paper.event.inventory.PrepareResultEvent}
 */
@Deprecated @Warning
public class PrepareInventoryResultEvent extends InventoryEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private ItemStack result;

    @ApiStatus.Internal
    public PrepareInventoryResultEvent(@NotNull InventoryView inventory, @Nullable ItemStack result) {
        super(inventory);
        this.result = result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取结果物品，可能为 null。
     * <p>原文：
     * Get result item, may be {@code null}.
     *
     * @return 结果物品
     * <p>原文：Get result item, may be null.
     * <p>原文：result item
     */
    @Nullable
    public ItemStack getResult() {
        return this.result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置结果物品，可能为 null。
     * <p>原文：
     * Set result item, may be {@code null}.
     *
     * @param result 结果物品
     * <p>原文：Set result item, may be null.
     * <p>原文：result item
     */
    public void setResult(@Nullable ItemStack result) {
        this.result = result;
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
