package org.bukkit.block;

import org.bukkit.inventory.BrewerInventory;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表酿造台.
 * <p>原文：
 * Represents a captured state of a brewing stand.
 */
public interface BrewingStand extends Container {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回酿造还需多长时间.
     * <p>原文：
     * How much time is left in the brewing cycle.
     *
     * @return 酿造时间
     * <p>原文：Brew Time
     */
    int getBrewingTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置酿造还需多长时间完成.
     * <p>原文：
     * Set the time left before brewing completes.
     *
     * @param brewTime 酿造时间
     * <p>原文：Brewing time
     */
    void setBrewingTime(int brewTime);

    // Paper start - Add recipeBrewTime
    /**
     * Sets the recipe time for the brewing process which is
     * used to compute the progress of the brewing process with
     * {@link #getBrewingTime()}.
     *
     * @param recipeBrewTime recipe brew time (in ticks)
     * @throws IllegalArgumentException if the recipe brew time is non-positive
     */
    @org.jetbrains.annotations.ApiStatus.Experimental
    void setRecipeBrewTime(@org.jetbrains.annotations.Range(from = 1, to = Integer.MAX_VALUE) int recipeBrewTime);

    /**
     * Gets the recipe time for the brewing process which is
     * used to compute the progress of the brewing process with
     * {@link #getBrewingTime()}.
     *
     * @return recipe brew time (in ticks)
     */
    @org.jetbrains.annotations.ApiStatus.Experimental
    @org.jetbrains.annotations.Range(from = 1, to = Integer.MAX_VALUE) int getRecipeBrewTime();
    // Paper end - Add recipeBrewTime

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取酿造台当前剩余的燃料.
     * <p>原文：
     * Get the level of current fuel for brewing.
     *
     * @return 酿造台当前剩余的燃料
     * <p>原文：The fuel level
     */
    int getFuelLevel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置酿造台当前剩余的燃料.
     * <p>原文：
     * Set the level of current fuel for brewing.
     *
     * @param level 酿造台当前剩余的燃料
     * <p>原文：fuel level
     */
    void setFuelLevel(int level);

    @NotNull
    @Override
    BrewerInventory getInventory();

    @NotNull
    @Override
    BrewerInventory getSnapshotInventory();
}
