package org.bukkit.inventory;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个锻造配方。
 * <p>原文：
 * Represents a smithing recipe.
 */
public class SmithingRecipe implements Recipe, Keyed {

    private final NamespacedKey key;
    private final ItemStack result;
    private final RecipeChoice base;
    private final RecipeChoice addition;
    private final boolean copyDataComponents; // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个锻造配方以产生指定的结果物品。
     * <p>原文：
     * Create a smithing recipe to produce the specified result ItemStack.
     *
     * @param key 配方的唯一键。
     * <p>原文：The unique recipe key
     * @param result 你希望配方创建的物品。
     * <p>原文：The item you want the recipe to create.
     * @param base 基础成分。
     * <p>原文：The base ingredient
     * @param addition 附加成分。
     * <p>原文：The addition ingredient
     * @deprecated 从 Minecraft 1.20 开始，锻造配方现在被分为两种不同的配方类型：{@link SmithingTransformRecipe} 和 {@link SmithingTrimRecipe}。此类现在作为这两个类的基类，添加到服务器时不会产生任何效果。
     * <p>原文：Create a smithing recipe to produce the specified result ItemStack.
     * <p>原文：as of Minecraft 1.20, smithing recipes are now separated into two
     * distinct recipe types, {@link SmithingTransformRecipe} and {@link SmithingTrimRecipe}.
     * This class now acts as a base class to these two classes and will do nothing when
     * added to the server.
     */
    @Deprecated(since = "1.20.1")
    public SmithingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @Nullable RecipeChoice base, @Nullable RecipeChoice addition) {
        // Paper start
        this(key, result, base, addition, true);
    }
    /**
     * Create a smithing recipe to produce the specified result ItemStack.
     *
     * @param key The unique recipe key
     * @param result The item you want the recipe to create.
     * @param base The base ingredient
     * @param addition The addition ingredient
     * @param copyDataComponents whether to copy the data components from the input base item to the output
     * @deprecated use {@link SmithingTrimRecipe} or {@link SmithingTransformRecipe}
     */
    @Deprecated
    public SmithingRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @Nullable RecipeChoice base, @Nullable RecipeChoice addition, boolean copyDataComponents) {
        com.google.common.base.Preconditions.checkArgument(!result.isEmpty() || this instanceof ComplexRecipe, "Recipe cannot have an empty result."); // Paper
        this.copyDataComponents = copyDataComponents;
        // Paper end
        this.key = key;
        this.result = result;
        this.base = base == null ? RecipeChoice.empty() : base.validate(true).clone(); // Paper
        this.addition = addition == null ? RecipeChoice.empty() : addition.validate(true).clone(); // Paper
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取基础配方物品。
     * <p>原文：
     * Get the base recipe item.
     *
     * @return 基础选择。
     * <p>原文：Get the base recipe item.
     * <p>原文：base choice
     */
    @NotNull // Paper - fix issues with recipe api
    public RecipeChoice getBase() {
        return (base != null) ? base.clone() : null;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取附加配方物品。
     * <p>原文：
     * Get the addition recipe item.
     *
     * @return 附加选择。
     * <p>原文：Get the addition recipe item.
     * <p>原文：addition choice
     */
    @NotNull // Paper - fix issues with recipe api
    public RecipeChoice getAddition() {
        return (addition != null) ? addition.clone() : null;
    }

    @NotNull
    @Override
    public ItemStack getResult() {
        return result.clone();
    }

    @NotNull
    @Override
    public NamespacedKey getKey() {
        return this.key;
    }

    // Paper start
    /**
     * Whether to copy the NBT of the input base item to the output.
     *
     * @return true to copy the NBT (default for vanilla smithing recipes)
     * @apiNote use {@link #willCopyDataComponents()}
     */
    @org.jetbrains.annotations.ApiStatus.Obsolete(since = "1.20.5")
    public boolean willCopyNbt() {
        return this.willCopyDataComponents();
    }

    /**
     * Whether to copy the data components of the input base item to the output.
     *
     * @return true to copy the data components (default for vanilla smithing recipes)
     */
    public boolean willCopyDataComponents() {
        return this.copyDataComponents;
    }
    // Paper end
}
