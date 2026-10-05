package io.papermc.paper.event.player;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.MerchantRecipe;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家与独立的商人 GUI 交易时调用。
 * <p>原文：
 * Called when a player trades with a standalone merchant GUI.
 */
@NullMarked
public class PlayerPurchaseEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Merchant merchant;
    private boolean rewardExp;
    private boolean increaseTradeUses;
    private MerchantRecipe trade;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerPurchaseEvent(final Player player, final Merchant merchant, final MerchantRecipe trade, final boolean rewardExp, final boolean increaseTradeUses) {
        super(player);
        this.merchant = merchant;
        this.trade = trade;
        this.rewardExp = rewardExp;
        this.increaseTradeUses = increaseTradeUses;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家正在与之交易的商人
     * <p>原文：
     * Gets the merchant that the player is trading with
     *
     * @return 商人
     * <p>原文：the merchant
     */
    public Merchant getMerchant() {
        return merchant;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取与此事件关联的交易
     * <p>原文：
     * Gets the associated trade with this event
     *
     * @return 交易
     * <p>原文：the trade
     */
    public MerchantRecipe getTrade() {
        return this.trade;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置交易。随后将用于确定下一次的价格
     * <p>原文：
     * Sets the trade. This is then used to determine the next prices
     *
     * @param trade 要使用的交易
     * <p>原文：the trade to use
     */
    public void setTrade(final MerchantRecipe trade) {
        Preconditions.checkArgument(trade != null, "Trade cannot be null!");
        this.trade = trade;
    }

    /**
     * @return 交易是否会尝试奖励经验
     * <p>原文：will trade try to reward exp
     */
    public boolean isRewardingExp() {
        return this.rewardExp;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此次交易是否会尝试奖励经验
     * <p>原文：
     * Sets whether the trade will try to reward exp
     *
     * @param rewardExp 尝试奖励经验
     * <p>原文：try to reward exp
     */
    public void setRewardExp(final boolean rewardExp) {
        this.rewardExp = rewardExp;
    }

    /**
     * @return 该交易是否计入交易使用次数
     * <p>原文：whether the trade will count as a use of the trade
     */
    public boolean willIncreaseTradeUses() {
        return this.increaseTradeUses;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此次交易是否计为一次使用
     * <p>原文：
     * Sets whether the trade will count as a use
     *
     * @param increaseTradeUses {@code true} 表示计入，否则为 {@code false}
     * <p>原文：{@code true} to count, {@code false} otherwise
     */
    public void setIncreaseTradeUses(final boolean increaseTradeUses) {
        this.increaseTradeUses = increaseTradeUses;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

}
