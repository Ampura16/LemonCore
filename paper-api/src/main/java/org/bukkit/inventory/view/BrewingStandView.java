package org.bukkit.inventory.view;

import org.bukkit.inventory.BrewerInventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个提供与酿造台视图数据相关额外方法的 {@link InventoryView} 实例。
 * <p>原文：
 * An instance of {@link InventoryView} which provides extra methods related to
 * brewing stand view data.
 */
public interface BrewingStandView extends InventoryView {

    @NotNull
    @Override
    BrewerInventory getTopInventory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此酿造台的燃料等级。
     * <p>
     * Minecraft中默认最大燃料等级为20。
     * <p>原文：
     * Gets the fuel level of this brewing stand.
     * <p>
     * The default maximum fuel level in minecraft is 20.
     *
     * @return 剩余的燃料等级数量
     * <p>
     * 原文：Gets the fuel level of this brewing stand.
     * <p>
     * The default maximum fuel level in minecraft is 20.
     * <p>原文：The amount of fuel level left
     */
    int getFuelLevel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取剩余的酿造刻数。
     * <p>原文：
     * Gets the amount of brewing ticks left.
     *
     * @return 酿造任务剩余的刻数
     * <p>
     * 原文：Gets the amount of brewing ticks left.
     * <p>原文：The amount of ticks left for the brewing task
     */
    int getBrewingTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置剩余的燃料等级。
     * <p>原文：
     * Sets the fuel level left.
     *
     * @param level 燃料等级，不得小于0
     * <p>原文：the level of the fuel, which is no less than 0
     * @throws IllegalArgumentException 如果等级小于0
     * <p>
     * 原文：Sets the fuel level left.
     * <p>原文：if the level is less than 0
     */
    void setFuelLevel(final int level) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置剩余的酿造刻数。
     * <p>原文：
     * Sets the brewing ticks left.
     *
     * @param ticks 剩余的刻数，不得小于0
     * <p>原文：the ticks left, which is no less than 0
     * @throws IllegalArgumentException 如果刻数小于0
     * <p>
     * 原文：Sets the brewing ticks left.
     * <p>原文：if the ticks are less than 0
     */
    void setBrewingTicks(final int ticks) throws IllegalArgumentException;

    // Paper start - Add recipeBrewTime
    /**
     * Sets the recipe time for the brewing process which is
     * used to compute the progress of the brewing process with
     * {@link #getBrewingTicks()}.
     *
     * @param recipeBrewTime recipe brew time (in ticks)
     * @throws IllegalArgumentException if the recipe brew time is non-positive
     */
    @org.jetbrains.annotations.ApiStatus.Experimental
    void setRecipeBrewTime(@org.jetbrains.annotations.Range(from = 1, to = Integer.MAX_VALUE) int recipeBrewTime);

    /**
     * Gets the recipe time for the brewing process which is
     * used to compute the progress of the brewing process with
     * {@link #getBrewingTicks()}.
     *
     * @return recipe brew time (in ticks)
     */
    @org.jetbrains.annotations.ApiStatus.Experimental
    @org.jetbrains.annotations.Range(from = 1, to = Integer.MAX_VALUE) int getRecipeBrewTime();
    // Paper end - Add recipeBrewTime
}
