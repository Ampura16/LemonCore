package org.bukkit.event.inventory;

import org.bukkit.block.Block;
import org.bukkit.event.block.BlockCookEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个物品烧炼完毕时触发本事件.
 * <p>原文：
 * Called when an ItemStack is successfully smelted in a furnace-like block
 * such as a {@link org.bukkit.block.Furnace}, {@link org.bukkit.block.Smoker},
 * or {@link org.bukkit.block.BlastFurnace}.
 */
public class FurnaceSmeltEvent extends BlockCookEvent {

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public FurnaceSmeltEvent(@NotNull final Block furnace, @NotNull final ItemStack source, @NotNull final ItemStack result) {
        super(furnace, source, result);
    }

    @ApiStatus.Internal
    public FurnaceSmeltEvent(@NotNull final Block furnace, @NotNull final ItemStack source, @NotNull final ItemStack result, @org.jetbrains.annotations.Nullable org.bukkit.inventory.CookingRecipe<?> recipe) {
        super(furnace, source, result, recipe);
    }
}
