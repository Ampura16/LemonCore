package org.bukkit.inventory;

import com.google.common.base.Preconditions;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个有序或无序的合成配方。
 * <p>原文：
 * Represents a shaped or shapeless crafting recipe.
 */
public abstract class CraftingRecipe implements Recipe, Keyed {
    private final NamespacedKey key;
    private final ItemStack output;
    private String group = "";
    private CraftingBookCategory category = CraftingBookCategory.MISC;

    protected CraftingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result) {
        Preconditions.checkArgument(key != null, "key cannot be null");
        this.key = key;
        this.output = new ItemStack(result);
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此配方的结果。
     * <p>原文：
     * Get the result of this recipe.
     *
     * @return 结果物品堆。
     * <p>原文：The result stack.
     */
    @Override
    @NotNull
    public ItemStack getResult() {
        return output.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此配方的分组。具有相同分组的配方在客户端显示时可能会被分组在一起。
     * <p>原文：
     * Get the group of this recipe. Recipes with the same group may be grouped
     * together when displayed in the client.
     *
     * @return 配方分组。空字符串表示没有分组。不能为 null。
     * <p>原文：recipe group. An empty string denotes no group. May not be null.
     */
    @NotNull
    public String getGroup() {
        return group;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此配方的分组。具有相同分组的配方在客户端显示时可能会被分组在一起。
     * <p>原文：
     * Set the group of this recipe. Recipes with the same group may be grouped
     * together when displayed in the client.
     *
     * @param group 配方分组。空字符串表示没有分组。不能为 null。
     * <p>原文：recipe group. An empty string denotes no group. May not be
     * null.
     */
    public void setGroup(@NotNull String group) {
        Preconditions.checkArgument(group != null, "group cannot be null");
        this.group = group;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此配方在配方书中出现的类别。
     * <br>
     * 如果未设置，则默认为 {@link CraftingBookCategory#MISC}。
     * <p>原文：
     * Gets the category which this recipe will appear in the recipe book under.
     * <br>
     * Defaults to {@link CraftingBookCategory#MISC} if not set.
     *
     * @return 配方书类别
     * <p>原文：recipe book category
     */
    @NotNull
    public CraftingBookCategory getCategory() {
        return category;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此配方在配方书中出现的类别。
     * <br>
     * 如果未设置，则默认为 {@link CraftingBookCategory#MISC}。
     * <p>原文：
     * Sets the category which this recipe will appear in the recipe book under.
     * <br>
     * Defaults to {@link CraftingBookCategory#MISC} if not set.
     *
     * @param category 配方书类别
     * <p>原文：recipe book category
     */
    public void setCategory(@NotNull CraftingBookCategory category) {
        Preconditions.checkArgument(category != null, "category cannot be null");
        this.category = category;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查用于 CraftingRecipe 相关构造器的 ItemStack 是否为空。
     * <p>原文：
     * Checks an ItemStack to be used in constructors related to CraftingRecipe
     * is not empty.
     *
     * @param result 一个 ItemStack
     * <p>原文：an ItemStack
     * @return 相同的结果 ItemStack
     * <p>原文：the same result ItemStack
     * @throws IllegalArgumentException 如果 {@code result} 是空物品（AIR）
     * <p>原文：if the {@code result} is an empty item
     * (AIR)
     */
    @ApiStatus.Internal
    @NotNull
    protected static ItemStack checkResult(@NotNull ItemStack result) {
        Preconditions.checkArgument(!result.isEmpty(), "Recipe cannot have an empty result."); // Paper
        return result;
    }
}
