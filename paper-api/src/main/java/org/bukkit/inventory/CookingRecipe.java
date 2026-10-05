package org.bukkit.inventory;

import com.google.common.base.Preconditions;
import java.util.Collections;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.recipe.CookingBookCategory;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个烹饪配方。
 * <p>原文：
 * Represents a cooking recipe.
 *
 * @param <T> 配方类型
 * <p>原文：type of recipe
 */
public abstract class CookingRecipe<T extends CookingRecipe> implements Recipe, Keyed {
    private final NamespacedKey key;
    private ItemStack output;
    private RecipeChoice ingredient;
    private float experience;
    private int cookingTime;
    private String group = "";
    private CookingBookCategory category = CookingBookCategory.MISC;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个烹饪配方来制作指定的物品堆。
     * <p>原文：
     * Create a cooking recipe to craft the specified ItemStack.
     *
     * @param key 唯一的配方键
     * <p>原文：The unique recipe key
     * @param result 你希望配方创建的物品。
     * <p>原文：The item you want the recipe to create.
     * @param source 输入材料。
     * <p>原文：The input material.
     * @param experience 此配方提供的经验值
     * <p>原文：The experience given by this recipe
     * @param cookingTime 烹饪时间（以刻为单位）
     * <p>原文：The cooking time (in ticks)
     */
    public CookingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull Material source, float experience, int cookingTime) {
        this(key, result, new RecipeChoice.MaterialChoice(Collections.singletonList(source)), experience, cookingTime);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个烹饪配方来制作指定的物品堆。
     * <p>原文：
     * Create a cooking recipe to craft the specified ItemStack.
     *
     * @param key 唯一的配方键
     * <p>原文：The unique recipe key
     * @param result 你希望配方创建的物品。
     * <p>原文：The item you want the recipe to create.
     * @param input 输入选项。
     * <p>原文：The input choices.
     * @param experience 此配方提供的经验值
     * <p>原文：The experience given by this recipe
     * @param cookingTime 烹饪时间（以刻为单位）
     * <p>原文：The cooking time (in ticks)
     */
    public CookingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice input, float experience, int cookingTime) {
        Preconditions.checkArgument(!result.isEmpty(), "Recipe cannot have an empty result."); // Paper
        this.key = key;
        this.output = new ItemStack(result);
        this.ingredient = input.validate(false).clone(); // Paper
        this.experience = experience;
        this.cookingTime = cookingTime;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此烹饪配方的输入。
     * <p>原文：
     * Sets the input of this cooking recipe.
     *
     * @param input 输入材料。
     * <p>原文：The input material.
     * @return 更改后的配方，以便你可以链式调用。
     * <p>原文：The changed recipe, so you can chain calls.
     */
    @NotNull
    public CookingRecipe setInput(@NotNull Material input) {
        this.ingredient = new RecipeChoice.MaterialChoice(Collections.singletonList(input));
        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取输入材料。
     * <p>原文：
     * Get the input material.
     *
     * @return 输入材料。
     * <p>原文：The input material.
     * @deprecated Use {@link #getInputChoice()} instead for more complete data.
     */
    @Deprecated // Paper
    @NotNull
    public ItemStack getInput() {
        return this.ingredient.getItemStack();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此烹饪配方的输入。
     * <p>原文：
     * Sets the input of this cooking recipe.
     *
     * @param input 输入选项。
     * <p>原文：The input choice.
     * @return 更改后的配方，以便你可以链式调用。
     * <p>原文：The changed recipe, so you can chain calls.
     */
    @NotNull
    public T setInputChoice(@NotNull RecipeChoice input) {
        this.ingredient = input.validate(false).clone(); // Paper
        return (T) this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取输入选项。
     * <p>原文：
     * Get the input choice.
     *
     * @return 输入选项。
     * <p>原文：The input choice.
     */
    @NotNull
    public RecipeChoice getInputChoice() {
        return this.ingredient.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此配方的结果。
     * <p>原文：
     * Get the result of this recipe.
     *
     * @return 结果物品堆。
     * <p>原文：The resulting stack.
     */
    @NotNull
    @Override
    public ItemStack getResult() {
        return output.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此配方提供的经验值。
     * <p>原文：
     * Sets the experience given by this recipe.
     *
     * @param experience 经验等级
     * <p>原文：the experience level
     */
    public void setExperience(float experience) {
        this.experience = experience;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此配方提供的经验值。
     * <p>原文：
     * Get the experience given by this recipe.
     *
     * @return 经验等级
     * <p>原文：experience level
     */
    public float getExperience() {
        return experience;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此配方的烹饪时间（以刻为单位）。
     * <p>原文：
     * Set the cooking time for this recipe in ticks.
     *
     * @param cookingTime 新的烹饪时间
     * <p>原文：new cooking time
     */
    public void setCookingTime(int cookingTime) {
        Preconditions.checkArgument(cookingTime >= 0, "cookingTime must be >= 0");
        this.cookingTime = cookingTime;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此配方的烹饪时间（以刻为单位）。
     * <p>原文：
     * Get the cooking time for this recipe in ticks.
     *
     * @return 烹饪时间
     * <p>原文：cooking time
     */
    public int getCookingTime() {
        return cookingTime;
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
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
     *
     * 如果未设置，则默认为 {@link CookingBookCategory#MISC}。
     * <p>原文：
     * Gets the category which this recipe will appear in the recipe book under.
     *
     * Defaults to {@link CookingBookCategory#MISC} if not set.
     *
     * @return 配方书类别
     * <p>原文：recipe book category
     */
    @NotNull
    public CookingBookCategory getCategory() {
        return category;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此配方在配方书中出现的类别。
     *
     * 如果未设置，则默认为 {@link CookingBookCategory#MISC}。
     * <p>原文：
     * Sets the category which this recipe will appear in the recipe book under.
     *
     * Defaults to {@link CookingBookCategory#MISC} if not set.
     *
     * @param category 配方书类别
     * <p>原文：recipe book category
     */
    public void setCategory(@NotNull CookingBookCategory category) {
        Preconditions.checkArgument(category != null, "category cannot be null");
        this.category = category;
    }
}
