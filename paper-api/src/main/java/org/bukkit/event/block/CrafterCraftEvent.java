package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.CraftingRecipe;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当合成器即将合成物品时触发的事件.
 * <p>原文：
 * Event called when a Crafter is about to craft an item.
 */
public class CrafterCraftEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final CraftingRecipe recipe;
    private ItemStack result;

    private boolean cancelled;

    @ApiStatus.Internal
    public CrafterCraftEvent(@NotNull Block crafter, @NotNull CraftingRecipe recipe, @NotNull ItemStack result) {
        super(crafter);
        this.result = result;
        this.recipe = recipe;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取合成的结果.
     * <p>原文：
     * Gets the result for the craft.
     *
     * @return 合成的结果
     * <p>原文：the result for the craft
     */
    @NotNull
    public ItemStack getResult() {
        return this.result.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置合成的结果.
     * <p>原文：
     * Sets the result of the craft.
     *
     * @param result 合成的结果
     * <p>原文：the result of the craft
     */
    public void setResult(@NotNull ItemStack result) {
        this.result = result.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于合成此物品的配方.
     * <p>原文：
     * Gets the recipe that was used to craft this item.
     *
     * @return 用于合成此物品的配方
     * <p>原文：the recipe that was used to craft this item
     */
    @NotNull
    public CraftingRecipe getRecipe() {
        return this.recipe;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
