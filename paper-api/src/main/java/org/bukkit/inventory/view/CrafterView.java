package org.bukkit.inventory.view;

import org.bukkit.inventory.CrafterInventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个提供与合成器视图数据相关额外方法的 {@link InventoryView} 实例。
 * <p>原文：
 * An instance of {@link InventoryView} which provides extra methods related to
 * crafter view data.
 */
public interface CrafterView extends InventoryView {

    @NotNull
    @Override
    CrafterInventory getTopInventory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查给定的合成器槽位是否被禁用。
     * <p>原文：
     * Checks if the given crafter slot is disabled.
     *
     * @param slot 要检查的槽位
     * <p>原文：the slot to check
     * @return 如果槽位被禁用则返回true，否则返回false
     * <p>
     * 原文：Checks if the given crafter slot is disabled.
     * <p>原文：whether the slot is disabled
     */
    boolean isSlotDisabled(int slot);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此合成器视图是否被通电。
     * <p>原文：
     * Checks whether or not this crafter view is powered.
     *
     * @return 如果合成器被通电则返回true
     * <p>
     * 原文：Checks whether or not this crafter view is powered.
     * <p>原文：true if the crafter is powered
     */
    boolean isPowered();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置合成器槽位的状态。
     * <p>原文：
     * Sets the status of the crafter slot.
     *
     * @param slot 要设置状态的槽位
     * <p>原文：the slot to set the status of
     * @param disabled 如果槽位应被禁用则为true，否则为false
     * <p>
     * 原文：Sets the status of the crafter slot.
     * <p>原文：whether the slot should be disabled
     */
    void setSlotDisabled(int slot, boolean disabled);
}
