package org.bukkit.block;

import java.util.Map;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.FurnaceInventory;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表熔炉.
 * <p>
 * 译注:本页出现的时间均以tick为单位, tick(刻)是服务器内部表示时间的方式, 理想状态下1 tick为50ms.
 * <p>原文：
 * Represents a captured state of a furnace.
 */
public interface Furnace extends Container {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取燃烧持续时间.
     * <p>原文：
     * Get burn time.
     *
     * @return 燃烧持续时间
     * <p>原文：Burn time
     */
    public short getBurnTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置燃烧持续时间.
     * 大于0的燃烧持续时间将使熔炉燃烧, 同时小于0的时间将熄灭它.
     * <p>原文：
     * Set burn time.
     *
     * A burn time greater than 0 will cause this block to be lit, whilst a time
     * less than 0 will extinguish it.
     *
     * @param burnTime 燃烧持续时间
     * <p>原文：Burn time
     */
    public void setBurnTime(short burnTime);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品已烧炼时间.
     * <p>原文：
     * Get cook time.
     *
     * This is the amount of time the item has been cooking for.
     *
     * @return 物品已烧炼时间
     * <p>原文：Cook time
     */
    public short getCookTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品已烧炼时间.
     * <p>原文：
     * Set cook time.
     *
     * This is the amount of time the item has been cooking for.
     *
     * @param cookTime 物品已烧炼时间
     * <p>原文：Cook time
     */
    public void setCookTime(short cookTime);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取烧炼总时间.
     * 本时间表示烧炼物品所需的时间.
     * <p>原文：
     * Get cook time total.
     *
     * This is the amount of time the item is required to cook for.
     *
     * @return 烧炼总时间
     * <p>原文：Cook time total
     */
    public int getCookTimeTotal();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置烧炼总时间.
     * 本时间表示烧炼物品所需的时间.
     * <p>原文：
     * Set cook time.
     *
     * This is the amount of time the item is required to cook for.
     *
     * @param cookTimeTotal 烧炼总时间
     * <p>原文：Cook time total
     */
    public void setCookTimeTotal(int cookTimeTotal);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此熔炉中使用的配方.
     *
     * <b>注意:</b> 当结果物品被手动从熔炉中取出时, 已使用的配方会被重置.
     * <p>原文：
     * Get the recipes used in this furnace.
     *
     * <b>Note:</b> These recipes used are reset when the result item is
     * manually taken from the furnace.
     *
     * @return 包含已使用配方和使用次数的不可变映射
     * <p>原文：An immutable map with the recipes used and the times used
     */
    @NotNull
    public Map<CookingRecipe<?>, Integer> getRecipesUsed();

    // Paper start
    /**
     * Gets the cook speed multiplier that this {@link Furnace} will cook
     * compared to vanilla.
     *
     * @return the multiplier, a value between 0 and 200
     */
    public double getCookSpeedMultiplier();

    /**
     * Sets the speed multiplier that this {@link Furnace} will cook
     * compared to vanilla.
     *
     * @param multiplier the multiplier to set, a value between 0 and 200
     * @throws IllegalArgumentException if value is less than 0
     * @throws IllegalArgumentException if value is more than 200
     */
    public void setCookSpeedMultiplier(double multiplier);

    /**
     * Gets the number of times a recipe has been used since the
     * last player removed items from the result slot. This is used
     * to calculate experience rewards when withdrawing items from furnaces.
     *
     * @param furnaceRecipe the recipe to query the count for
     * @return the count or 0 if none found
     */
    int getRecipeUsedCount(@NotNull org.bukkit.NamespacedKey furnaceRecipe);

    /**
     * Checks if the recipe has a used count present on this furnace.
     *
     * @param furnaceRecipe the recipe to check if a count exists for
     * @return true if there is a positive count, else false
     */
    boolean hasRecipeUsedCount(@NotNull org.bukkit.NamespacedKey furnaceRecipe);

    /**
     * Sets the number of times a recipe has been used. This is used
     * to calculate experience rewards when withdrawing items from furnaces.
     *
     * @param furnaceRecipe the recipe to set the count for
     * @param count the count, a non-positive number will remove the recipe
     */
    void setRecipeUsedCount(@NotNull org.bukkit.inventory.CookingRecipe<?> furnaceRecipe, int count);

    /**
     * Sets all recipes used by this furnace.
     *
     * @param recipesUsed the recipes used
     */
    void setRecipesUsed(@NotNull Map<CookingRecipe<?>, Integer> recipesUsed);
    // Paper end

    @NotNull
    @Override
    public FurnaceInventory getInventory();

    @NotNull
    @Override
    public FurnaceInventory getSnapshotInventory();
}
