package org.bukkit.inventory;

import com.google.common.base.Preconditions;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.material.MaterialData;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个有序（即普通的）合成配方。
 * <p>原文：
 * Represents a shaped (ie normal) crafting recipe.
 */
public class ShapedRecipe extends CraftingRecipe {
    private String[] rows;
    private Map<Character, RecipeChoice> ingredients = new HashMap<>();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个合成指定物品的配方。构造函数仅决定结果和类型；要设置实际配方，你需要调用适当的方法。
     * <p>原文：
     * Create a shaped recipe to craft the specified ItemStack. The
     * constructor merely determines the result and type; to set the actual
     * recipe, you'll need to call the appropriate methods.
     *
     * @param result 你希望配方创建的物品。
     * <p>原文：The item you want the recipe to create.
     * @see ShapedRecipe#shape(String...)
     * @see ShapedRecipe#setIngredient(char, Material)
     * @see ShapedRecipe#setIngredient(char, RecipeChoice)
     * @deprecated 配方必须有键。请改用 {@link #ShapedRecipe(NamespacedKey, ItemStack)}。
     * <p>原文：Create a shaped recipe to craft the specified ItemStack. The
     * constructor merely determines the result and type; to set the actual
     * recipe, you'll need to call the appropriate methods.
     * <p>原文：Recipes must have keys. Use {@link #ShapedRecipe(NamespacedKey, ItemStack)}
     * instead.
     */
    @Deprecated(since = "1.12")
    public ShapedRecipe(@NotNull ItemStack result) {
        this(NamespacedKey.randomKey(), result);
        new Throwable("Warning: A plugin is creating a recipe using a Deprecated method. This will cause you to receive warnings stating 'Tried to load unrecognized recipe: bukkit:<ID>'. Please ask the author to give their recipe a static key using NamespacedKey.").printStackTrace(); // Paper
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个合成指定物品的配方。构造函数仅决定结果和类型；要设置实际配方，你需要调用适当的方法。
     * <p>原文：
     * Create a shaped recipe to craft the specified ItemStack. The
     * constructor merely determines the result and type; to set the actual
     * recipe, you'll need to call the appropriate methods.
     *
     * @param key 配方的唯一键。
     * <p>原文：the unique recipe key
     * @param result 你希望配方创建的物品。
     * <p>原文：The item you want the recipe to create.
     * @exception IllegalArgumentException 如果 {@code result} 是空物品（AIR）。
     * <p>原文：if the {@code result} is an empty item (AIR)
     * @see ShapedRecipe#shape(String...)
     * @see ShapedRecipe#setIngredient(char, Material)
     * @see ShapedRecipe#setIngredient(char, RecipeChoice)
     */
    public ShapedRecipe(@NotNull NamespacedKey key, @NotNull ItemStack result) {
        super(key, checkResult(result));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此配方的形状为指定的行。每个字符代表不同的成分；空格字符必须为空，每个字符代表什么需要单独设置。第一行对应工作台最上方的部分，例如如果提供三行，第一行代表工作台最上面一行。
     * <p>原文：
     * Set the shape of this recipe to the specified rows. Each character
     * represents a different ingredient; excluding space characters, which
     * must be empty, exactly what each character represents is set separately.
     * The first row supplied corresponds with the upper most part of the recipe
     * on the workbench e.g. if all three rows are supplies the first string
     * represents the top row on the workbench.
     *
     * @param shape 配方的行（最多3行）。
     * <p>原文：The rows of the recipe (up to 3 rows).
     * @return 修改后的配方，以便链式调用。
     * <p>原文：Set the shape of this recipe to the specified rows. Each character
     * represents a different ingredient; excluding space characters, which
     * must be empty, exactly what each character represents is set separately.
     * The first row supplied corresponds with the upper most part of the recipe
     * on the workbench e.g. if all three rows are supplies the first string
     * represents the top row on the workbench.
     * <p>原文：The changed recipe, so you can chain calls.
     */
    @NotNull
    public ShapedRecipe shape(@NotNull final String... shape) {
        Preconditions.checkArgument(shape != null, "Must provide a shape");
        Preconditions.checkArgument(shape.length > 0 && shape.length < 4, "Crafting recipes should be 1, 2 or 3 rows, not ", shape.length);

        int lastLen = -1;
        for (String row : shape) {
            Preconditions.checkArgument(row != null, "Shape cannot have null rows");
            Preconditions.checkArgument(row.length() > 0 && row.length() < 4, "Crafting rows should be 1, 2, or 3 characters, not ", row.length());

            Preconditions.checkArgument(lastLen == -1 || lastLen == row.length(), "Crafting recipes must be rectangular");
            lastLen = row.length();
        }
        this.rows = new String[shape.length];
        for (int i = 0; i < shape.length; i++) {
            this.rows[i] = shape[i];
        }

        // Remove character mappings for characters that no longer exist in the shape
        HashMap<Character, RecipeChoice> newIngredients = new HashMap<>();
        for (String row : shape) {
            for (char c : row.toCharArray()) {
                // SPIGOT-7770: Space in recipe shape must represent no ingredient
                if (c == ' ') {
                    continue;
                }

                newIngredients.put(c, ingredients.get(c));
            }
        }
        this.ingredients = newIngredients;

        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置配方形状中某个字符引用的 {@link RecipeChoice}。注意，在设置成分之前，必须先使用 {@link #shape(String...)} 定义配方的形状。
     * <p>原文：
     * Sets the material that a character in the recipe shape refers to.
     * <p>
     * Note that before an ingredient can be set, the recipe's shape must be defined
     * with {@link #shape(String...)}.
     *
     * @param key 形状中代表成分的字符。
     * <p>原文：The character that represents the ingredient in the shape.
     * @param ingredient 成分。
     * <p>原文：The ingredient.
     * @return 修改后的配方，以便链式调用。
     * <p>原文：The changed recipe, so you can chain calls.
     * @throws IllegalArgumentException 如果 {@code key} 是空格字符。
     * <p>原文：if the {@code key} is a space character
     * @throws IllegalArgumentException 如果 {@code key} 是空格字符。
     * <p>原文：if the {@code key} does not appear in the shape.
     * @deprecated use {@link #setIngredient(char, RecipeChoice)}
     */
    @NotNull
    @Deprecated // Paper
    public ShapedRecipe setIngredient(char key, @NotNull MaterialData ingredient) {
        return setIngredient(key, ingredient.getItemType(), ingredient.getData());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置配方形状中某个字符代表的材料。注意，在设置成分之前，必须先使用 {@link #shape(String...)} 定义配方的形状。
     * <p>原文：
     * Sets the material that a character in the recipe shape refers to.
     * <p>
     * Note that before an ingredient can be set, the recipe's shape must be defined
     * with {@link #shape(String...)}.
     *
     * @param key 形状中代表成分的字符。
     * <p>原文：The character that represents the ingredient in the shape.
     * @param ingredient 成分。
     * <p>原文：The ingredient.
     * @return 修改后的配方，以便链式调用。
     * <p>原文：The changed recipe, so you can chain calls.
     * @throws IllegalArgumentException 如果 {@code key} 是空格字符。
     * <p>原文：if the {@code key} is a space character
     * @throws IllegalArgumentException 如果 {@code key} 是空格字符。
     * <p>原文：if the {@code key} does not appear in the shape.
     */
    @NotNull
    public ShapedRecipe setIngredient(char key, @NotNull Material ingredient) {
        return setIngredient(key, ingredient, 0);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置配方形状中某个字符代表的材料。注意，在设置成分之前，必须先使用 {@link #shape(String...)} 定义配方的形状。
     * <p>原文：
     * Sets the material that a character in the recipe shape refers to.
     * <p>
     * Note that before an ingredient can be set, the recipe's shape must be defined
     * with {@link #shape(String...)}.
     *
     * @param key 形状中代表成分的字符。
     * <p>原文：The character that represents the ingredient in the shape.
     * @param ingredient 成分。
     * <p>原文：The ingredient.
     * @param raw 原始材料数据的整数值。
     * <p>原文：The raw material data as an integer.
     * @return 修改后的配方，以便链式调用。
     * <p>原文：The changed recipe, so you can chain calls.
     * @throws IllegalArgumentException 如果 {@code key} 是空格字符。
     * <p>原文：if the {@code key} is a space character
     * @throws IllegalArgumentException 如果 {@code key} 是空格字符。
     * <p>原文：if the {@code key} does not appear in the shape.
     * @deprecated 魔法值。
     * <p>原文：Sets the material that a character in the recipe shape refers to.
     * <p>
     * Note that before an ingredient can be set, the recipe's shape must be defined
     * with {@link #shape(String...)}.
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    @NotNull
    public ShapedRecipe setIngredient(char key, @NotNull Material ingredient, int raw) {
        Preconditions.checkArgument(key != ' ', "Space in recipe shape must represent no ingredient");
        Preconditions.checkArgument(ingredients.containsKey(key), "Symbol does not appear in the shape:", key);

        // -1 is the old wildcard, map to Short.MAX_VALUE as the new one
        if (raw == -1) {
            raw = Short.MAX_VALUE;
        }

        ingredients.put(key, new RecipeChoice.MaterialChoice(Collections.singletonList(ingredient)));
        return this;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置配方形状中某个字符代表的材料。注意，在设置成分之前，必须先使用 {@link #shape(String...)} 定义配方的形状。
     * <p>原文：
     * Sets the {@link RecipeChoice} that a character in the recipe shape refers to.
     * <p>
     * Note that before an ingredient can be set, the recipe's shape must be defined
     * with {@link #shape(String...)}.
     *
     * @param key 形状中代表成分的字符。
     * <p>原文：The character that represents the ingredient in the shape.
     * @param ingredient 成分。
     * <p>原文：The ingredient.
     * @return 修改后的配方，以便链式调用。
     * <p>原文：The changed recipe, so you can chain calls.
     * @throws IllegalArgumentException 如果 {@code key} 是空格字符。
     * <p>原文：if the {@code key} is a space character
     * @throws IllegalArgumentException 如果 {@code key} 是空格字符。
     * <p>原文：if the {@code key} does not appear in the shape.
     */
    @NotNull
    public ShapedRecipe setIngredient(char key, @NotNull RecipeChoice ingredient) {
        Preconditions.checkArgument(key != ' ', "Space in recipe shape must represent no ingredient");
        Preconditions.checkArgument(ingredients.containsKey(key), "Symbol does not appear in the shape:", key);

        ingredients.put(key, ingredient.validate(false).clone()); // Paper
        return this;
    }

    // Paper start
    @NotNull
    public ShapedRecipe setIngredient(char key, @NotNull ItemStack item) {
        Preconditions.checkArgument(!item.getType().isAir(), "Item cannot be air"); // Paper
        return setIngredient(key, new RecipeChoice.ExactChoice(item.clone())); // Paper
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取成分映射的副本。
     * <p>原文：
     * Get a copy of the ingredients map.
     *
     * @return 字符到成分的映射。
     * <p>原文：Get a copy of the ingredients map.
     * <p>原文：The mapping of character to ingredients.
     * @deprecated Use {@link #getChoiceMap()} instead for more complete data.
     */
    @Deprecated // Paper
    @NotNull
    public Map<Character, ItemStack> getIngredientMap() {
        HashMap<Character, ItemStack> result = new HashMap<Character, ItemStack>();
        for (Map.Entry<Character, RecipeChoice> ingredient : ingredients.entrySet()) {
            if (ingredient.getValue() == null) {
                result.put(ingredient.getKey(), null);
            } else {
                result.put(ingredient.getKey(), ingredient.getValue().getItemStack().clone());
            }
        }
        return result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取选择映射的副本。
     * <p>原文：
     * Get a copy of the choice map.
     *
     * @return 字符到成分的映射。
     * <p>原文：Get a copy of the choice map.
     * <p>原文：The mapping of character to ingredients.
     */
    @NotNull
    public Map<Character, RecipeChoice> getChoiceMap() {
        Map<Character, RecipeChoice> result = new HashMap<>();
        for (Map.Entry<Character, RecipeChoice> ingredient : ingredients.entrySet()) {
            if (ingredient.getValue() == null) {
                result.put(ingredient.getKey(), null);
            } else {
                result.put(ingredient.getKey(), ingredient.getValue().clone());
            }
        }
        return result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取形状。
     * <p>原文：
     * Get the shape.
     *
     * @return 配方的形状。
     * <p>原文：The recipe's shape.
     * @throws NullPointerException 尚未设置时抛出。
     * <p>原文：Get the shape.
     * <p>原文：when not set yet
     */
    public @NotNull String @NotNull [] getShape() {
        return rows.clone();
    }
}
