package org.bukkit.inventory;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 合成台的用户界面接口.
 * <p>原文：
 * Interface to the crafting inventories
 */
public interface CraftingInventory extends Inventory {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取合成台产生的合成品.
     * <p>原文：
     * Check what item is in the result slot of this crafting inventory.
     *
     * @return 合成品.
     * <p>原文：The result item.
     */
    @Nullable
    ItemStack getResult();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取合成公式.
     * <p>原文：
     * Get the contents of the crafting matrix.
     *
     * @return 合成公式. 个别条目可能为null.
     * <p>原文：The contents. Individual entries may be null.
     */
    @Nullable ItemStack @NotNull [] getMatrix(); // Paper - make array elements nullable instead array

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置最终合成品.
     * <p>原文：
     * Set the item in the result slot of the crafting inventory.
     *
     * @param newResult 新合成品.
     * <p>原文：The new result item.
     */
    void setResult(@Nullable ItemStack newResult);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 替换合成矩阵的内容.
     * <p>原文：
     * Replace the contents of the crafting matrix
     *
     * @param contents 新的内容. 个别条目可能为null.
     * <p>原文：The new contents. Individual entries may be null.
     * @throws IllegalArgumentException 如果contents的长度大于合成矩阵的大小.
     * <p>原文：if the length of contents is greater
     *     than the size of the crafting matrix.
     */
    void setMatrix(@Nullable ItemStack @NotNull [] contents); // Paper - make array elements nullable instead array

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前合成台上形成的合成配方(如果有的话).
     * <p>原文：
     * Get the current recipe formed on the crafting inventory, if any.
     *
     * @return 配方, 如果当前内容不匹配任何配方则返回null.
     * <p>原文：The recipe, or null if the current contents don't match any
     *     recipe.
     */
    @Nullable
    Recipe getRecipe();
}
