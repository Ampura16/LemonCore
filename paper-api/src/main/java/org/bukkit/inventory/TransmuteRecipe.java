package org.bukkit.inventory;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一种配方，当与额外材料结合时会改变输入材料的类型，但保留所有自定义数据。仅使用结果物品栈的物品类型。
 * <br>
 * 在原版中用于给潜影盒染色。
 * <p>原文：
 * Represents a recipe which will change the type of the input material when
 * combined with an additional material, but preserve all custom data. Only the
 * item type of the result stack will be used.
 * <br>
 * Used for dyeing shulker boxes in Vanilla.
 */
public class TransmuteRecipe extends CraftingRecipe implements ComplexRecipe {

    private final RecipeChoice input;
    private final RecipeChoice material;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个转化配方以产生指定类型的结果。
     * <p>原文：
     * Create a transmute recipe to produce a result of the specified type.
     *
     * @param key 配方的唯一键。
     * <p>原文：the unique recipe key
     * @param result 转化后的结果材料。
     * <p>原文：the transmuted result material
     * @param input 输入成分。
     * <p>原文：the input ingredient
     * @param material 额外成分。
     * <p>原文：Create a transmute recipe to produce a result of the specified type.
     * <p>原文：the additional ingredient
     */
    public TransmuteRecipe(@NotNull NamespacedKey key, @NotNull Material result, @NotNull RecipeChoice input, @NotNull RecipeChoice material) {
        super(key, checkResult(new ItemStack(result)));
        this.input = input.validate(false).clone(); // Paper
        this.material = material.validate(false).clone(); // Paper
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将被转化的输入材料。
     * <p>原文：
     * Gets the input material, which will be transmuted.
     *
     * @return 转化的输入。
     * <p>原文：Gets the input material, which will be transmuted.
     * <p>原文：the input from transmutation
     */
    @NotNull
    public RecipeChoice getInput() {
        return input.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取触发转化所需的额外材料。
     * <p>原文：
     * Gets the additional material required to cause the transmutation.
     *
     * @return 成分材料。
     * <p>原文：Gets the additional material required to cause the transmutation.
     * <p>原文：the ingredient material
     */
    @NotNull
    public RecipeChoice getMaterial() {
        return material.clone();
    }
}
