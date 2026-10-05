package org.bukkit.event.inventory;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.InventoryBlockStartEvent;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当熔炉开始熔炼时调用。
 * <p>原文：
 * Called when any of the furnace-like blocks start smelting.
 * <p>
 * Furnace-like blocks are {@link org.bukkit.block.Furnace},
 * {@link org.bukkit.block.Smoker}, and {@link org.bukkit.block.BlastFurnace}.
 */
public class FurnaceStartSmeltEvent extends InventoryBlockStartEvent {

    private final CookingRecipe<?> recipe;
    private int totalCookTime;

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public FurnaceStartSmeltEvent(@NotNull final Block furnace, @NotNull ItemStack source, @NotNull final CookingRecipe<?> recipe) {
        this(furnace, source, recipe, recipe.getCookingTime());
    }

    @ApiStatus.Internal
    public FurnaceStartSmeltEvent(final @NotNull Block furnace, final @NotNull ItemStack source, final @NotNull CookingRecipe<?> recipe, final int cookingTime) {
        super(furnace, source);
        this.recipe = recipe;
        this.totalCookTime = cookingTime;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与此事件关联的熔炉配方。
     * <p>原文：
     * Gets the FurnaceRecipe associated with this event
     *
     * @return 正在烹饪的熔炉配方
     * <p>原文：Gets the FurnaceRecipe associated with this event
     * <p>原文：the FurnaceRecipe being cooked
     */
    @NotNull
    public CookingRecipe<?> getRecipe() {
        return this.recipe;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与此事件关联的总烹饪时间。
     * <p>原文：
     * Gets the total cook time associated with this event
     *
     * @return 总烹饪时间
     * <p>原文：Gets the total cook time associated with this event
     * <p>原文：the total cook time
     */
    public int getTotalCookTime() {
        return this.totalCookTime;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此事件的总烹饪时间。
     * <p>原文：
     * Sets the total cook time for this event
     *
     * @param cookTime 新的总烹饪时间
     * <p>原文：Sets the total cook time for this event
     * <p>原文：the new total cook time
     */
    public void setTotalCookTime(int cookTime) {
        this.totalCookTime = cookTime;
    }
}
