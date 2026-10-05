package org.bukkit.inventory;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个锻造变换配方。
 * <p>原文：
 * Represents a smithing transform recipe.
 */
public class SmithingTransformRecipe extends SmithingRecipe {

    private final RecipeChoice template;

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
     * @param template 模板物品。
     * <p>原文：The template item ({@link RecipeChoice#empty()} can be used)
     * @param base 基础成分。
     * <p>原文：The base ingredient ({@link RecipeChoice#empty()} can be used)
     * @param addition 附加成分。
     * <p>原文：Create a smithing recipe to produce the specified result ItemStack.
     * <p>原文：The addition ingredient ({@link RecipeChoice#empty()} can be used)
     */
    public SmithingTransformRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice template, @NotNull RecipeChoice base, @NotNull RecipeChoice addition) { // Paper - fix issues with recipe api - prevent null choices
        super(key, result, base, addition);
        this.template = template == null ? RecipeChoice.empty() : template.validate(true).clone(); // Paper - fix issues with recipe api - prevent null choices
    }
    // Paper start
    /**
     * Create a smithing recipe to produce the specified result ItemStack.
     *
     * @param key The unique recipe key
     * @param result The item you want the recipe to create.
     * @param template The template item ({@link RecipeChoice#empty()} can be used)
     * @param base The base ingredient ({@link RecipeChoice#empty()} can be used)
     * @param addition The addition ingredient ({@link RecipeChoice#empty()} can be used)
     * @param copyDataComponents whether to copy the data components from the input base item to the output
     */
    public SmithingTransformRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result, @NotNull RecipeChoice template, @NotNull RecipeChoice base, @NotNull RecipeChoice addition, boolean copyDataComponents) {
        super(key, result, base, addition, copyDataComponents);
        this.template = template == null ? RecipeChoice.empty() : template.validate(true).clone(); // Paper - fix issues with recipe api - prevent null choices
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取模板配方物品。
     * <p>原文：
     * Get the template recipe item.
     *
     * @return 模板选择。
     * <p>原文：Get the template recipe item.
     * <p>原文：template choice
     */
    @NotNull // Paper - fix issues with recipe api - prevent null choices
    public RecipeChoice getTemplate() {
        return (template != null) ? template.clone() : null;
    }
}
