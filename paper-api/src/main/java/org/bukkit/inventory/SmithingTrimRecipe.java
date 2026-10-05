package org.bukkit.inventory;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.meta.trim.TrimPattern;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个锻造装饰配方。
 * <p>原文：
 * Represents a smithing trim recipe.
 */
public class SmithingTrimRecipe extends SmithingRecipe implements ComplexRecipe {

    private final RecipeChoice template;
    private final TrimPattern pattern;

    /**
     * Create a smithing recipe to produce the specified result ItemStack.
     *
     * @param key The unique recipe key
     * @param template The template item ({@link RecipeChoice#empty()} can be used)
     * @param base The base ingredient ({@link RecipeChoice#empty()} can be used)
     * @param addition The addition ingredient ({@link RecipeChoice#empty()} can be used)
     * @param pattern The trim pattern
     */
    public SmithingTrimRecipe(@NotNull NamespacedKey key, @NotNull RecipeChoice template, @NotNull RecipeChoice base, @NotNull RecipeChoice addition, @NotNull TrimPattern pattern) {
        super(key, new ItemStack(Material.AIR), base, addition);
        this.template = template == null ? RecipeChoice.empty() : template.validate(true).clone(); // Don't use null
        this.pattern = pattern;
    }

    /**
     * Create a smithing recipe to produce the specified result ItemStack.
     *
     * @param key The unique recipe key
     * @param template The template item. ({@link RecipeChoice#empty()} can be used)
     * @param base The base ingredient ({@link RecipeChoice#empty()} can be used)
     * @param addition The addition ingredient ({@link RecipeChoice#empty()} can be used)
     * @param pattern The trim pattern
     * @param copyDataComponents whether to copy the data components from the input base item to the output
     */
    public SmithingTrimRecipe(@NotNull NamespacedKey key, @NotNull RecipeChoice template, @NotNull RecipeChoice base, @NotNull RecipeChoice addition, @NotNull TrimPattern pattern, boolean copyDataComponents) {
        super(key, new ItemStack(Material.AIR), base, addition, copyDataComponents);
        this.template = template == null ? RecipeChoice.empty() : template.validate(true).clone(); // Don't use null
        this.pattern = pattern;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个锻造配方以产生指定的结果物品。
     * <p>原文：
     * Create a smithing recipe to produce the specified result ItemStack.
     *
     * @param key 配方的唯一键。
     * <p>原文：The unique recipe key
     * @param template 模板物品。
     * <p>原文：The template item ({@link RecipeChoice#empty()} can be used)
     * @param base 基础成分。
     * <p>原文：The base ingredient ({@link RecipeChoice#empty()} can be used)
     * @param addition 附加成分。
     * <p>原文：The addition ingredient ({@link RecipeChoice#empty()} can be used)
     * @deprecated 必须指定 trimPattern。
     * <p>原文：Create a smithing recipe to produce the specified result ItemStack.
     * <p>原文：use {@link #SmithingTrimRecipe(NamespacedKey, RecipeChoice, RecipeChoice, RecipeChoice, TrimPattern)} instead
     */
    @Deprecated(since = "1.21.5", forRemoval = true)
    public SmithingTrimRecipe(@NotNull NamespacedKey key, @NotNull RecipeChoice template, @NotNull RecipeChoice base, @NotNull RecipeChoice addition) {
        this(key, template, base, addition, patternFromTemplate(template));
    }

    /**
     * Create a smithing recipe to produce the specified result ItemStack.
     *
     * @param key The unique recipe key
     * @param template The template item. ({@link RecipeChoice#empty()} can be used)
     * @param base The base ingredient ({@link RecipeChoice#empty()} can be used)
     * @param addition The addition ingredient ({@link RecipeChoice#empty()} can be used)
     * @param copyDataComponents whether to copy the data components from the input base item to the output
     * @deprecated use {@link #SmithingTrimRecipe(NamespacedKey, RecipeChoice, RecipeChoice, RecipeChoice, TrimPattern, boolean)} instead
     */
    @Deprecated(since = "1.21.5", forRemoval = true)
    public SmithingTrimRecipe(@NotNull NamespacedKey key, @NotNull RecipeChoice template, @NotNull RecipeChoice base, @NotNull RecipeChoice addition, boolean copyDataComponents) {
        this(key, template, base, addition, patternFromTemplate(template), copyDataComponents);
    }

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
    @NotNull
    public RecipeChoice getTemplate() {
        return (template != null) ? template.clone() : null;
    }

    /**
     * Get the trim pattern.
     *
     * @return trim pattern
     */
    @NotNull
    public TrimPattern getTrimPattern() {
        return pattern;
    }

    private static TrimPattern patternFromTemplate(@NotNull RecipeChoice template) {
        if (template instanceof RecipeChoice.ExactChoice exactChoice) {
            return patternFromMaterial(exactChoice.getItemStack().getType());
        } else if (template instanceof RecipeChoice.MaterialChoice materialChoice) {
            return patternFromMaterial(materialChoice.getItemStack().getType());
        } else {
            return TrimPattern.BOLT;
        }
    }

    private static TrimPattern patternFromMaterial(final @NotNull Material material) {
        return switch (material) {
            case BOLT_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.BOLT;
            case COAST_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.COAST;
            case DUNE_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.DUNE;
            case EYE_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.EYE;
            case FLOW_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.FLOW;
            case HOST_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.HOST;
            case RAISER_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.RAISER;
            case RIB_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.RIB;
            case SENTRY_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.SENTRY;
            case SHAPER_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.SHAPER;
            case SILENCE_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.SILENCE;
            case SNOUT_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.SNOUT;
            case SPIRE_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.SPIRE;
            case TIDE_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.TIDE;
            case VEX_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.VEX;
            case WARD_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.WARD;
            case WAYFINDER_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.WAYFINDER;
            case WILD_ARMOR_TRIM_SMITHING_TEMPLATE -> TrimPattern.WILD;
            default -> TrimPattern.BOLT;
        };
    }
}
