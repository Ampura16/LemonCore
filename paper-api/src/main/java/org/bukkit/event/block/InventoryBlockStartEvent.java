package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.inventory.FurnaceStartSmeltEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 用于以下情况：
 * <ul>
 * <li>熔炉开始熔炼 {@link FurnaceStartSmeltEvent}</li>
 * <li>酿造台开始酿造 {@link BrewingStartEvent}</li>
 * <li>营火开始烹饪 {@link CampfireStartEvent}</li>
 * </ul>
 * <p>原文：
 * Used when:
 * <ul>
 * <li>A Furnace starts smelting {@link FurnaceStartSmeltEvent}</li>
 * <li>A Brewing-Stand starts brewing {@link BrewingStartEvent}</li>
 * <li>A Campfire starts cooking {@link CampfireStartEvent}</li>
 * </ul>
 */
public class InventoryBlockStartEvent extends BlockEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    protected ItemStack source;

    @ApiStatus.Internal
    public InventoryBlockStartEvent(@NotNull final Block block, @NotNull ItemStack source) {
        super(block);
        this.source = source;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此事件的源 ItemStack.
     * <p>原文：
     * Gets the source ItemStack for this event.
     *
     * @return 源 ItemStack
     * <p>原文：the source ItemStack
     */
    @NotNull
    public ItemStack getSource() {
        return this.source;
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
