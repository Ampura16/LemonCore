package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 某玩家工具耐久消耗完毕时触发(比如铲子，打火石，铁制工具).
 * <p>
 * 当物品耐久值为0时，将触发此事件.事件结束后，物品数量将设为 {@code item amount - 1}, 耐久值将复位至0.
 * <p>原文：
 * Fired when a player's item breaks (such as a shovel or flint and steel).
 * <p>
 * After this event, the item's amount will be set to {@code item amount - 1}
 * and its durability will be reset to 0.
 */
public class PlayerItemBreakEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ItemStack brokenItem;

    @ApiStatus.Internal
    public PlayerItemBreakEvent(@NotNull final Player player, @NotNull final ItemStack brokenItem) {
        super(player);
        this.brokenItem = brokenItem;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得因耐久值不足将要被销毁的物品.
     * <p>原文：
     * Gets the item that broke
     *
     * @return 这个物品
     * <p>原文：The broken item
     */
    @NotNull
    public ItemStack getBrokenItem() {
        return this.brokenItem;
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
