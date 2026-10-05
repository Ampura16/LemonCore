package io.papermc.paper.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在玩家右键空地图创建已填充地图时调用。
 * <p>原文：
 * Called when a player creates a filled map by right-clicking an empty map.
 */
@NullMarked
public class PlayerMapFilledEvent extends PlayerEvent {
    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ItemStack originalItem;
    private ItemStack createdMap;

    @ApiStatus.Internal
    public PlayerMapFilledEvent(final Player player, final ItemStack originalItem, final ItemStack createdMap) {
        super(player);
        this.originalItem = originalItem;
        this.createdMap = createdMap;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回被消耗前的空地图的副本。
     * <p>原文：
     * Returns a copy of the empty map before it was consumed.
     *
     * @return 原始物品的克隆
     * <p>原文：cloned original item
     */
    public ItemStack getOriginalItem() {
        return this.originalItem.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回所创建的已填充地图的副本。
     * <p>原文：
     * Returns a copy of the filled map which was created.
     *
     * @return 所创建地图物品的克隆
     * <p>原文：cloned created map item
     */
    public ItemStack getCreatedMap() {
        return this.createdMap.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置将要创建的已填充地图。
     * <p>原文：
     * Sets the filled map that will be created.
     *
     * @param createdMap 地图物品
     * <p>原文：map item
     */
    public void setCreatedMap(final ItemStack createdMap) {
        this.createdMap = createdMap.clone();
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
