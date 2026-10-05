package org.bukkit.event.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.CraftingInventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PrepareItemCraftEvent extends InventoryEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final boolean repair;
    private final CraftingInventory matrix;

    @ApiStatus.Internal
    public PrepareItemCraftEvent(@NotNull CraftingInventory matrix, @NotNull InventoryView view, boolean isRepair) {
        super(view);
        this.matrix = matrix;
        this.repair = isRepair;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取已形成的配方。如果此事件是由工具修复触发的，这将是一个代表修复的临时无序配方。
     * <p>原文：
     * Get the recipe that has been formed. If this event was triggered by a
     * tool repair, this will be a temporary shapeless recipe representing the
     * repair.
     *
     * @return 正在合成的配方
     * <p>原文：Get the recipe that has been formed. If this event was triggered by a
     * tool repair, this will be a temporary shapeless recipe representing the
     * repair.
     * <p>原文：The recipe being crafted.
     */
    @Nullable
    public Recipe getRecipe() {
        return this.matrix.getRecipe();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 形成配方的合成物品栏。
     * <p>原文：The crafting inventory on which the recipe was formed.
     * <p>原文：The crafting inventory on which the recipe was formed.
     */
    @NotNull
    @Override
    public CraftingInventory getInventory() {
        return this.matrix;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此事件是否由工具修复操作而非合成配方触发。
     * <p>原文：
     * Check if this event was triggered by a tool repair operation rather
     * than a crafting recipe.
     *
     * @return 如果这是修复则返回 true
     * <p>原文：Check if this event was triggered by a tool repair operation rather
     * than a crafting recipe.
     * <p>原文：{@code true} if this is a repair.
     */
    public boolean isRepair() {
        return this.repair;
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
