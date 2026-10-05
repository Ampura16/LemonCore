package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.CookingRecipe;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当物品堆在方块内成功烹饪时触发本事件 (原版中这个方块一般是营火).
 * <p>
 * 译注:在熔炉内的烧炼不属于烹饪范畴, 因此不会触发本事件. 如果您想监听熔炉相关事件, 可以参考 inventory 事件包中的相关事件.
 * <p>原文：
 * Called when an ItemStack is successfully cooked in a block.
 */
public class BlockCookEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ItemStack source;
    private ItemStack result;
    private final CookingRecipe<?> recipe;

    private boolean cancelled;

    @Deprecated(forRemoval = true)
    @ApiStatus.Internal
    public BlockCookEvent(@NotNull final Block block, @NotNull final ItemStack source, @NotNull final ItemStack result) {
        this(block, source, result, null);
    }

    @ApiStatus.Internal
    public BlockCookEvent(@NotNull final Block block, @NotNull final ItemStack source, @NotNull final ItemStack result, @Nullable CookingRecipe<?> recipe) {
        super(block);
        this.source = source;
        this.result = result;
        this.recipe = recipe;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被烹饪的物品堆 (比如生的食物).
     * <p>原文：
     * Gets the smelted ItemStack for this event
     *
     * @return 被烹饪的原物品堆
     * <p>原文：smelting source ItemStack
     */
    @NotNull
    public ItemStack getSource() {
        return this.source;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取烹饪后得到的物品堆 (比如熟食).
     * <p>原文：
     * Gets the resultant ItemStack for this event
     *
     * @return 烹饪后得到的物品堆
     * <p>原文：smelting result ItemStack
     */
    @NotNull
    public ItemStack getResult() {
        return this.result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置烹饪后得到的物品堆.
     * <p>原文：
     * Sets the resultant ItemStack for this event
     *
     * @param result 烹饪后得到的物品堆
     * <p>原文：new result ItemStack
     */
    public void setResult(@NotNull ItemStack result) {
        this.result = result;
    }

    /**
     * Gets the cooking recipe associated with this event.
     *
     * @return the recipe
     */
    @Nullable
    public org.bukkit.inventory.CookingRecipe<?> getRecipe() {
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
