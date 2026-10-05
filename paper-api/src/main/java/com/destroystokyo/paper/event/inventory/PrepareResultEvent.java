package com.destroystokyo.paper.event.inventory;

import org.bukkit.event.inventory.PrepareInventoryResultEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当物品被放入含有结果槽的物品栏时调用
 * <p>原文：
 * Called when an item is put in an inventory containing a result slot
 */
@NullMarked
public class PrepareResultEvent extends PrepareInventoryResultEvent {

    // HandlerList on PrepareInventoryResultEvent to ensure api compat

    @ApiStatus.Internal
    public PrepareResultEvent(final InventoryView inventory, final @Nullable ItemStack result) {
        super(inventory, result);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取结果物品，可能为 {@code null}。
     * <p>原文：
     * Get result item, may be {@code null}.
     *
     * @return 结果物品
     * <p>原文：result item
     */
    @Override
    public @Nullable ItemStack getResult() {
        return super.getResult();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置结果物品，可以为 {@code null}。
     * <p>原文：
     * Set result item, may be {@code null}.
     *
     * @param result 结果物品
     * <p>原文：result item
     */
    @Override
    public void setResult(final @Nullable ItemStack result) {
        super.setResult(result);
    }
}
