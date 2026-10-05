package org.bukkit.inventory;

import com.google.common.base.Preconditions;
import java.util.Collections;
import org.bukkit.Keyed;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个切石配方。
 * <p>原文：
 * Represents a Stonecutting recipe.
 */
public class StonecuttingRecipe implements Recipe, Keyed {
    private final NamespacedKey key;
    private ItemStack output;
    private RecipeChoice ingredient;
    private String group = "";

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个切石配方以合成指定的物品。
     * <p>原文：
     * Create a Stonecutting recipe to craft the specified ItemStack.
     *
     * @param key 配方的唯一键。
     * <p>原文：The unique recipe key
     * @param result 你希望配方创建的物品。
     * <p>原文：The item you want the recipe to create.
     * @param source 输入材料。
     * <p>原文：Create a Stonecutting recipe to craft the specified ItemStack.
     * <p>原文：The input material.
     */
    public StonecuttingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull Material source) {
        this(key, result, new RecipeChoice.MaterialChoice(Collections.singletonList(source)));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个烹饪配方以合成指定的物品。
     * <p>原文：
     * Create a Stonecutting recipe to craft the specified ItemStack.
     *
     * @param key 配方的唯一键。
     * <p>原文：The unique recipe key
     * @param result 你希望配方创建的物品。
     * <p>原文：The item you want the recipe to create.
     * @param input 输入选择。
     * <p>原文：Create a cooking recipe to craft the specified ItemStack.
     * <p>原文：The input choices.
     */
    public StonecuttingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice input) {
        Preconditions.checkArgument(!result.isEmpty(), "Recipe cannot have an empty result."); // Paper
        this.key = key;
        this.output = new ItemStack(result);
        this.ingredient = input.validate(false).clone(); // Paper
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此烹饪配方的输入。
     * <p>原文：
     * Sets the input of this Stonecutting recipe.
     *
     * @param input 输入材料。
     * <p>原文：The input material.
     * @return 修改后的配方，以便链式调用。
     * <p>原文：Sets the input of this cooking recipe.
     * <p>原文：The changed recipe, so you can chain calls.
     */
    @NotNull
    public StonecuttingRecipe setInput(@NotNull Material input) {
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
     * <p>原文：Get the input material.
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
     * Sets the input of this Stonecutting recipe.
     *
     * @param input 输入选择。
     * <p>原文：The input choice.
     * @return 修改后的配方，以便链式调用。
     * <p>原文：Sets the input of this cooking recipe.
     * <p>原文：The changed recipe, so you can chain calls.
     */
    @NotNull
    public StonecuttingRecipe setInputChoice(@NotNull RecipeChoice input) {
        this.ingredient = input.validate(false).clone(); // Paper
        return (StonecuttingRecipe) this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取输入选择。
     * <p>原文：
     * Get the input choice.
     *
     * @return 输入选择。
     * <p>原文：Get the input choice.
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
     * @return 结果物品栈。
     * <p>原文：Get the result of this recipe.
     * <p>原文：The resulting stack.
     */
    @NotNull
    @Override
    public ItemStack getResult() {
        return output.clone();
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return key;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此配方的分组。相同分组的配方在客户端显示时可能会被分组在一起。
     * <p>原文：
     * Get the group of this recipe. Recipes with the same group may be grouped
     * together when displayed in the client.
     *
     * @return 配方分组。空字符串表示无分组。不能为 null。
     * <p>原文：Get the group of this recipe. Recipes with the same group may be grouped
     * together when displayed in the client.
     * <p>原文：recipe group. An empty string denotes no group. May not be null.
     */
    @NotNull
    public String getGroup() {
        return group;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此配方的分组。相同分组的配方在客户端显示时可能会被分组在一起。
     * <p>原文：
     * Set the group of this recipe. Recipes with the same group may be grouped
     * together when displayed in the client.
     *
     * @param group 配方分组。空字符串表示无分组。不能为 null。
     * <p>原文：Set the group of this recipe. Recipes with the same group may be grouped
     * together when displayed in the client.
     * <p>原文：recipe group. An empty string denotes no group. May not be
     * null.
     */
    public void setGroup(@NotNull String group) {
        Preconditions.checkArgument(group != null, "group cannot be null");
        this.group = group;
    }
}
