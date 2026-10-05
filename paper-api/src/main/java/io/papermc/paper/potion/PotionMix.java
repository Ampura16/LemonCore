package io.papermc.paper.potion;

import java.util.Objects;
import java.util.function.Predicate;
import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.RecipeChoice;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示在酿造台中进行的药水酿造配方。
 * <p>原文：
 * Represents a potion mix made in a Brewing Stand.
 */
@NullMarked
public final class PotionMix implements Keyed {

    private final NamespacedKey key;
    private final ItemStack result;
    private final RecipeChoice input;
    private final RecipeChoice ingredient;

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的药水酿造配方。使用 {@link org.bukkit.potion.PotionBrewer#addPotionMix(PotionMix)} 将其添加到服务器。
     * <p>原文：
     * Creates a new potion mix. Add it to the server with {@link org.bukkit.potion.PotionBrewer#addPotionMix(PotionMix)}.
     *
     * @param key 该混合的唯一键
     * <p>原文：a unique key for the mix
     * @param result 将出现在底部 3 个槽位中的结果物品堆
     * <p>原文：the resulting itemstack that will appear in the 3 bottom slots
     * @param input 放入底部 3 个槽位的输入
     * <p>原文：the input placed into the bottom 3 slots
     * @param ingredient 放入顶部槽位的原料
     * <p>原文：the ingredient placed into the top slot
     */
    public PotionMix(final NamespacedKey key, final ItemStack result, final RecipeChoice input, final RecipeChoice ingredient) {
        this.key = key;
        this.result = result.clone();
        this.input = input.clone();
        this.ingredient = ingredient.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 基于 Predicate 创建 {@link RecipeChoice}。这些 RecipeChoice 仅对 {@link PotionMix} 有效，在其他可使用 RecipeChoice 的地方无效。
     * <p>原文：
     * Create a {@link RecipeChoice} based on a Predicate. These RecipeChoices are only
     * valid for {@link PotionMix}, not anywhere else RecipeChoices may be used.
     *
     * @param stackPredicate 物品堆的谓词。
     * <p>原文：a predicate for an itemstack.
     * @return 新的 RecipeChoice
     * <p>原文：a new RecipeChoice
     */
    @Contract(value = "_ -> new", pure = true)
    public static RecipeChoice createPredicateChoice(final Predicate<? super ItemStack> stackPredicate) {
        return new PredicateRecipeChoice(stackPredicate);
    }

    @Override
    public NamespacedKey getKey() {
        return this.key;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取酿造完成后得到的物品堆。
     * <p>原文：
     * Gets the resulting itemstack after the brew has finished.
     *
     * @return 结果物品堆
     * <p>原文：the result itemstack
     */
    public ItemStack getResult() {
        return this.result.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取酿造台底部 3 个槽位的输入。
     * <p>原文：
     * Gets the input for the bottom 3 slots in the brewing stand.
     *
     * @return 底部 3 个槽位的原料
     * <p>原文：the bottom 3 slot ingredients
     */
    public RecipeChoice getInput() {
        return this.input.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取酿造台顶部槽位中的原料。
     * <p>原文：
     * Gets the ingredient in the top slot of the brewing stand.
     *
     * @return 顶部槽位的输入
     * <p>原文：the top slot input
     */
    public RecipeChoice getIngredient() {
        return this.ingredient.clone();
    }

    @Override
    public String toString() {
        return "PotionMix{" +
            "result=" + this.result +
            ", base=" + this.input +
            ", addition=" + this.ingredient +
            '}';
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        final PotionMix potionMix = (PotionMix) o;
        return this.key.equals(potionMix.key) && this.result.equals(potionMix.result) && this.input.equals(potionMix.input) && this.ingredient.equals(potionMix.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.key, this.result, this.input, this.ingredient);
    }
}
