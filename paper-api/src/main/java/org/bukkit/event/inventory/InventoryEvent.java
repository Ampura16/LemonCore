
package org.bukkit.event.inventory;

import java.util.List;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表与玩家相关的物品栏事件
 * <p>原文：
 * Represents a player related inventory event
 */
public class InventoryEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    protected InventoryView transaction;

    @ApiStatus.Internal
    public InventoryEvent(@NotNull InventoryView transaction) {
        this.transaction = transaction;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此事件涉及的主要物品栏.
     * <p>原文：
     * Gets the primary Inventory involved in this transaction
     *
     * @return 顶部背包
     * <p>原文：The upper inventory.
     */
    @NotNull
    public Inventory getInventory() {
        return this.transaction.getTopInventory();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取正在查看此主要/顶部物品栏的玩家列表.
     * <p>原文：
     * Gets the list of players viewing the primary (upper) inventory involved
     * in this event
     *
     * @return 正在查看此物品栏的玩家
     * <p>原文：A list of people viewing.
     */
    @NotNull
    public List<HumanEntity> getViewers() {
        return this.transaction.getTopInventory().getViewers();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品栏本身的 InventoryView 对象。
     * <p>原文：
     * Gets the view object itself
     *
     * @return InventoryView
     */
    @NotNull
    public InventoryView getView() {
        return this.transaction;
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
