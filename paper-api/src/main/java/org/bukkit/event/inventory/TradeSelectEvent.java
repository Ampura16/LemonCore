package org.bukkit.event.inventory;

import org.bukkit.event.HandlerList;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantInventory;
import org.bukkit.inventory.view.MerchantView;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 每当玩家在交易侧边栏点击新交易时调用此事件。
 * <p>
 * 此事件允许用户获取交易的索引，让他们通过 Merchant 获取 MerchantRecipe。
 * <p>原文：
 * This event is called whenever a player clicks a new trade on the trades
 * sidebar.
 * <p>
 * This event allows the user to get the index of the trade, letting them get
 * the MerchantRecipe via the Merchant.
 */
public class TradeSelectEvent extends InventoryInteractEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final int index;

    @ApiStatus.Internal
    public TradeSelectEvent(@NotNull MerchantView transaction, int newIndex) {
        super(transaction);
        this.index = newIndex;
    }

    @NotNull
    @Override
    public MerchantInventory getInventory() {
        return (MerchantInventory) super.getInventory();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取涉及的 Merchant。
     * <p>原文：
     * Get the Merchant involved.
     *
     * @return Merchant
     * <p>原文：Get the Merchant involved.
     * <p>原文：the Merchant
     */
    @NotNull
    public Merchant getMerchant() {
        return this.getInventory().getMerchant();
    }

    @NotNull
    @Override
    public MerchantView getView() {
        return (MerchantView) super.getView();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用于获取玩家点击的交易的索引。
     * <p>原文：
     * Used to get the index of the trade the player clicked on.
     *
     * @return 玩家点击的交易的索引
     * <p>原文：Used to get the index of the trade the player clicked on.
     * <p>原文：The index of the trade clicked by the player
     */
    public int getIndex() {
        return this.index;
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
