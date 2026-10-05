package org.bukkit.inventory;

import org.bukkit.inventory.view.AnvilView;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 铁毡的背包界面接口
 * <p>原文：
 * Interface to the inventory of an Anvil.
 */
public interface AnvilInventory extends Inventory {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取要对于重命名的物品的字符串, 当返回空字符串时则表示是物品的默认名称
     * <p>原文：
     * Get the name to be applied to the repaired item. An empty string denotes
     * the default item name.
     *
     * @return 重命名的字符串
     * <p>原文：the rename text
     * @deprecated 请使用 {@link AnvilView#getRenameText()}.
     * <p>原文：use {@link AnvilView#getRenameText()}.
     */
    @Deprecated(forRemoval = true, since = "1.21")
    @Nullable
    String getRenameText();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取完成当前修复所需物品的数量.
     * <p>原文：
     * Get the item cost (in amount) to complete the current repair.
     *
     * @return 物品数量
     * <p>原文：the amount
     * @deprecated 请使用 {@link AnvilView#getRepairItemCountCost()}.
     * <p>原文：use {@link AnvilView#getRepairItemCountCost()}.
     */
    @Deprecated(forRemoval = true, since = "1.21")
    int getRepairCostAmount();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置完成当前修复所需物品的数量.
     * <p>原文：
     * Set the item cost (in amount) to complete the current repair.
     *
     * @param amount 物品数量
     * <p>原文：the amount
     * @deprecated 请使用 {@link AnvilView#setRepairItemCountCost(int)}.
     * <p>原文：use {@link AnvilView#setRepairItemCountCost(int)}.
     */
    @Deprecated(forRemoval = true, since = "1.21")
    void setRepairCostAmount(int amount);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取完成当前修复所需要的等级
     * <p>原文：
     * Get the experience cost (in levels) to complete the current repair.
     *
     * @return 需要花费的经验
     * <p>原文：the experience cost
     * @deprecated 请使用 {@link AnvilView#getRepairCost()}.
     * <p>原文：use {@link AnvilView#getRepairCost()}.
     */
    @Deprecated(forRemoval = true, since = "1.21")
    int getRepairCost();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置完成当前修复所需要的等级.
     * <p>原文：
     * Set the experience cost (in levels) to complete the current repair.
     *
     * @param levels 给定的等级
     * <p>原文：the experience cost
     * @deprecated 请使用 {@link AnvilView#setRepairCost(int)}.
     * <p>原文：use {@link AnvilView#setRepairCost(int)}.
     */
    @Deprecated(forRemoval = true, since = "1.21")
    void setRepairCost(int levels);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前修复所允许的最大经验等级花费.
     * 如果 {@link #getRepairCost()} 的结果超过返回值, 修复结果将因"太贵"而变为空气.
     * <p>
     * 默认情况下, 此等级设置为 40. 创造模式的玩家忽略此最大修复花费.
     * <p>原文：
     * Get the maximum experience cost (in levels) to be allowed by the current
     * repair. If the result of {@link #getRepairCost()} exceeds the returned
     * value, the repair result will be air due to being "too expensive".
     * <p>
     * By default, this level is set to 40. Players in creative mode ignore the
     * maximum repair cost.
     *
     * @return 最大经验花费
     * <p>原文：the maximum experience cost
     * @deprecated 请使用 {@link AnvilView#getMaximumRepairCost()}.
     * <p>原文：use {@link AnvilView#getMaximumRepairCost()}.
     */
    @Deprecated(forRemoval = true, since = "1.21")
    int getMaximumRepairCost();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前修复所允许的最大经验等级花费.
     * 原版 Minecraft 的默认值为 40.
     * <p>原文：
     * Set the maximum experience cost (in levels) to be allowed by the current
     * repair. The default value set by vanilla Minecraft is 40.
     *
     * @param levels 最大经验等级花费
     * <p>原文：the maximum experience cost
     * @deprecated 请使用 {@link AnvilView#setMaximumRepairCost(int)}.
     * <p>原文：use {@link AnvilView#setMaximumRepairCost(int)}.
     */
    @Deprecated(forRemoval = true, since = "1.21")
    void setMaximumRepairCost(int levels);

    // Paper start
    /**
     * Gets the item in the left input slot.
     *
     * @return item in the first slot
     */
    @Nullable
    default ItemStack getFirstItem() {
        return getItem(0);
    }

    /**
     * Sets the item in the left input slot.
     *
     * @param firstItem item to set
     */
    default void setFirstItem(@Nullable ItemStack firstItem) {
        setItem(0, firstItem);
    }

    /**
     * Gets the item in the right input slot.
     *
     * @return item in the second slot
     */
    @Nullable
    default ItemStack getSecondItem() {
        return getItem(1);
    }

    /**
     * Sets the item in the right input slot.
     *
     * @param secondItem item to set
     */
    default void setSecondItem(@Nullable ItemStack secondItem) {
        setItem(1, secondItem);
    }

    /**
     * Gets the item in the result slot.
     *
     * @return item in the result slot
     */
    @Nullable
    default ItemStack getResult() {
        return getItem(2);
    }

    /**
     * Sets the item in the result slot.
     * Note that the client might not be able to take out the item if it does not match the input items.
     *
     * @param result item to set
     */
    default void setResult(@Nullable ItemStack result) {
        setItem(2, result);
    }
    // Paper end
}
