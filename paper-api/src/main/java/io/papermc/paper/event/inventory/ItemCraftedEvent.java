package io.papermc.paper.event.inventory;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家从合成网格的结果槽取出合成物品时调用。
 * <p>原文：
 * Called when a player picks up a crafted item from the result slot of a crafting grid.
 */
@NullMarked
public class ItemCraftedEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final ItemStack craftedItem;

    @ApiStatus.Internal
    public ItemCraftedEvent(final Player player, final ItemStack craftedItem) {
        this.player = player;
        this.craftedItem = craftedItem;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取通过取出合成物品而触发事件的玩家。
     * <p>原文：
     * Gets the player who triggered the event by picking up the crafted item.
     *
     * @return 玩家
     * <p>原文：the player
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家合成并取出的物品。
     * <p>原文：
     * Gets the item that was crafted and picked up by the player.
     *
     * @return 合成的物品
     * <p>原文：the crafted item
     */
    public ItemStack getCraftedItem() {
        return this.craftedItem.clone();
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
