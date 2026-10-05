package org.bukkit.inventory;

import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 锻造台物品栏的接口。
 * <p>原文：
 * Interface to the inventory of a Smithing table.
 */
public interface SmithingInventory extends Inventory {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查锻造台结果槽中的物品。
     * <p>原文：
     * Check what item is in the result slot of this smithing table.
     *
     * @return 结果物品
     * <p>原文：the result item
     */
    @Nullable
    ItemStack getResult();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置锻造台结果槽中的物品。
     * <p>原文：
     * Set the item in the result slot of the smithing table
     *
     * @param newResult 新的结果物品
     * <p>原文：the new result item
     */
    void setResult(@Nullable ItemStack newResult);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取锻造台上当前形成的配方（如果有）。
     * <p>原文：
     * Get the current recipe formed on the smithing table, if any.
     *
     * @return 配方，如果当前内容不匹配任何配方则返回 null
     * <p>原文：the recipe, or null if the current contents don't match any
     * recipe
     */
    @Nullable
    Recipe getRecipe();

    // Paper start
    /**
     * Gets the input template (first slot).
     *
     * @return input template item
     */
    default @Nullable ItemStack getInputTemplate() {
        return this.getItem(0);
    }

    /**
     * Sets the input template (first slot).
     *
     * @param itemStack item to set
     */
    default void setInputTemplate(@Nullable ItemStack itemStack) {
        this.setItem(0, itemStack);
    }
    /**
     * Gets the input equipment (second slot).
     *
     * @return input equipment item
     */
    default @Nullable ItemStack getInputEquipment() {
        return this.getItem(1);
    }

    /**
     * Sets the input equipment (second slot).
     *
     * @param itemStack item to set
     */
    default void setInputEquipment(@Nullable ItemStack itemStack) {
        this.setItem(1, itemStack);
    }

    /**
     * Gets the input mineral (third slot).
     *
     * @return input mineral item
     */
    default @Nullable ItemStack getInputMineral() {
        return this.getItem(2);
    }

    /**
     * Sets the input mineral (third slot).
     *
     * @param itemStack item to set
     */
    default void setInputMineral(@Nullable ItemStack itemStack) {
        this.setItem(2, itemStack);
    }
    // Paper end
}
