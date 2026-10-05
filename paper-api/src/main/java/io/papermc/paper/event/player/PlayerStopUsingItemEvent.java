package io.papermc.paper.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.inventory.ItemStack;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当服务器检测到玩家停止使用物品时调用。例如手持弓、可食用物品或望远镜时松开交互键。
 * <p>原文：
 * Called when the server detects a player stopping using an item.
 * Examples of this are letting go of the interact button when holding a bow, an edible item, or a spyglass.
 */
@NullMarked
public class PlayerStopUsingItemEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ItemStack item;
    private final int ticksHeldFor;

    public PlayerStopUsingItemEvent(final Player player, final ItemStack item, final int ticksHeldFor) {
        super(player);
        this.item = item;
        this.ticksHeldFor = ticksHeldFor;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取玩家正在松开的具体物品
     * <p>原文：
     * Gets the exact item the player is releasing
     *
     * @return 玩家松开的具体物品的 ItemStack
     * <p>原文：ItemStack the exact item the player released
     */
    public ItemStack getItem() {
        return this.item;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取物品被持续使用的刻数
     * <p>原文：
     * Gets the number of ticks the item was held for
     *
     * @return int 类型，物品被持续使用的刻数
     * <p>原文：int the number of ticks the item was held for
     */
    public int getTicksHeldFor() {
        return this.ticksHeldFor;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
