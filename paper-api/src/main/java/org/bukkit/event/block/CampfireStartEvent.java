package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.inventory.CampfireRecipe;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当营火开始烹饪时触发.
 * <p>原文：
 * Called when a Campfire starts to cook.
 */
@ApiStatus.Experimental // Paper
public class CampfireStartEvent extends InventoryBlockStartEvent {

    private final CampfireRecipe campfireRecipe;
    private int cookingTime;

    @ApiStatus.Internal
    public CampfireStartEvent(@NotNull final Block campfire, @NotNull ItemStack source, @NotNull CampfireRecipe recipe) {
        super(campfire, source);
        this.cookingTime = recipe.getCookingTime();
        this.campfireRecipe = recipe;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此事件关联的 CampfireRecipe.
     * <p>原文：
     * Gets the CampfireRecipe associated with this event.
     *
     * @return 正在烹饪的 CampfireRecipe
     * <p>原文：the CampfireRecipe being cooked
     */
    @NotNull
    public CampfireRecipe getRecipe() {
        return this.campfireRecipe;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此事件关联的总烹饪时间.
     * <p>原文：
     * Gets the total cook time associated with this event.
     *
     * @return 总烹饪时间
     * <p>原文：the total cook time
     */
    public int getTotalCookTime() {
        return this.cookingTime;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此事件的总烹饪时间.
     * <p>原文：
     * Sets the total cook time for this event.
     *
     * @param cookTime 新的总烹饪时间
     * <p>原文：the new total cook time
     */
    public void setTotalCookTime(int cookTime) {
        this.cookingTime = cookTime;
    }
}
