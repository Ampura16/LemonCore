package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当试炼密室中的宝库即将显示物品时触发.
 * <p>原文：
 * Called when a vault in a trial chamber is about to display an item.
 */
public class VaultDisplayItemEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private ItemStack displayItem;
    private boolean cancelled;

    @ApiStatus.Internal
    public VaultDisplayItemEvent(@NotNull Block vault, @Nullable ItemStack displayItem) {
        super(vault);
        this.displayItem = displayItem;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将在宝库内显示的物品.
     * <p>原文：
     * Gets the item that will be displayed inside the vault.
     *
     * @return 要显示的物品
     * <p>原文：the item to be displayed
     */
    @Nullable
    public ItemStack getDisplayItem() {
        return this.displayItem;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将在宝库内显示的物品.
     * <p>原文：
     * Sets the item that will be displayed inside the vault.
     *
     * @param displayItem 要显示的物品
     * <p>原文：the item to be displayed
     */
    public void setDisplayItem(@Nullable ItemStack displayItem) {
        this.displayItem = displayItem;
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
