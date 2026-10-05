package io.papermc.paper.event.player;

import org.bukkit.entity.AbstractVillager;
import org.bukkit.entity.Player;
import org.bukkit.inventory.MerchantRecipe;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家与村民或流浪商人交易时调用
 * <p>原文：
 * Called when a player trades with a villager or wandering trader
 */
@NullMarked
public class PlayerTradeEvent extends PlayerPurchaseEvent {

    @ApiStatus.Internal
    public PlayerTradeEvent(final Player player, final AbstractVillager villager, final MerchantRecipe trade, final boolean rewardExp, final boolean increaseTradeUses) {
        super(player, villager, trade, rewardExp, increaseTradeUses);
    }

    @Override
    public AbstractVillager getMerchant() {
        return (AbstractVillager) super.getMerchant();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取与此事件关联的村民或流浪商人
     * <p>原文：
     * Gets the Villager or Wandering trader associated with this event
     *
     * @return 村民或流浪商人
     * <p>原文：the villager or wandering trader
     * @see #getMerchant()
     */
    @ApiStatus.Obsolete
    public AbstractVillager getVillager() {
        return getMerchant();
    }

}
