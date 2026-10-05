package org.bukkit.inventory.view;

import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个提供与铁砧视图数据相关额外方法的 {@link InventoryView} 实例。
 * <p>原文：
 * An instance of {@link InventoryView} which provides extra methods related to
 * anvil view data.
 */
public interface AnvilView extends InventoryView {

    @NotNull
    @Override
    AnvilInventory getTopInventory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取铁砧文本字段中指定的重命名文本。
     * <p>原文：
     * Gets the rename text specified within the anvil's text field.
     *
     * @return 如果物品存在则返回铁砧文本字段中的文本，否则返回null
     * <p>
     * 原文：Gets the rename text specified within the anvil's text field.
     * <p>原文：The text within the anvil's text field if an item is present
     * otherwise null
     */
    @Nullable
    String getRenameText();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取修复所需的物品数量。
     * <p>原文：
     * Gets the amount of items needed to repair.
     *
     * @return 修复物品所需的材料数量
     * <p>
     * 原文：Gets the amount of items needed to repair.
     * <p>原文：The amount of materials required to repair the item
     */
    int getRepairItemCountCost();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取修复所需的经验值消耗。
     * <p>原文：
     * Gets the experience cost needed to repair.
     *
     * @return 修复所需的经验值消耗
     * <p>
     * 原文：Gets the experience cost needed to repair.
     * <p>原文：The repair cost in experience
     */
    int getRepairCost();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取修复所需的最大经验值消耗。
     * <p>原文：
     * Gets the maximum repair cost needed to repair.
     *
     * @return 修复所需的最大经验值消耗
     * <p>
     * 原文：Gets the maximum repair cost needed to repair.
     * <p>原文：The maximum repair cost in experience
     */
    int getMaximumRepairCost();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置修复物品所需的修复材料数量。
     * <p>原文：
     * Sets the amount of repair materials required to repair the item.
     *
     * @param amount 修复材料的数量
     * <p>
     * 原文：Sets the amount of repair materials required to repair the item.
     * <p>原文：the amount of repair materials
     */
    void setRepairItemCountCost(int amount);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置修复所需的经验值消耗。
     * <p>原文：
     * Sets the repair cost in experience.
     *
     * @param cost 修复所需的经验值消耗
     * <p>
     * 原文：Sets the repair cost in experience.
     * <p>原文：the experience cost to repair
     */
    void setRepairCost(int cost);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置修复所需的最大经验值消耗。
     * <p>原文：
     * Sets maximum repair cost in experience.
     *
     * @param levels 要设置的等级
     * <p>
     * 原文：Sets maximum repair cost in experience.
     * <p>原文：the levels to set
     */
    void setMaximumRepairCost(int levels);

    // Paper start - bypass anvil level restrictions
    /**
     * Returns whether this view will bypass the vanilla enchantment level restriction
     * when applying enchantments to an item or not.
     * <p>
     * By default, vanilla will limit enchantments applied to items to the respective
     * {@link org.bukkit.enchantments.Enchantment#getMaxLevel()}, even if the applied enchantment itself is above said
     * limit.
     * Disabling this limit via {@link AnvilView#bypassEnchantmentLevelRestriction(boolean)} allows for, e.g., enchanted
     * books to be applied fully, even if their enchantments are beyond the limit.
     *
     * @return {@code true} if this view bypasses the vanilla restrictions.
     */
    boolean bypassesEnchantmentLevelRestriction();

    /**
     * Sets if enchantments applied via this anvil view may bypass vanilla's level restrictions.
     *
     * @param bypassEnchantmentLevelRestriction if this view bypasses the vanilla level restrictions.
     * @see AnvilView#bypassesEnchantmentLevelRestriction()
     */
    void bypassEnchantmentLevelRestriction(boolean bypassEnchantmentLevelRestriction);
    // Paper end - bypass anvil level restrictions
}
