package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家停止破坏方块时触发.
 * <p>原文：
 * Called when a player stops damaging a Block.
 *
 * @see BlockDamageEvent
 */
public class BlockDamageAbortEvent extends BlockEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final ItemStack itemstack;

    public BlockDamageAbortEvent(@NotNull final Player player, @NotNull final Block block, @NotNull final ItemStack itemInHand) {
        super(block);
        this.player = player;
        this.itemstack = itemInHand;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取停止破坏此事件中方块的玩家.
     * <p>原文：
     * Gets the player that stopped damaging the block involved in this event.
     *
     * @return 停止破坏方块的玩家
     * <p>原文：The player that stopped damaging the block
     */
    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家手中当前物品的 ItemStack.
     * <p>原文：
     * Gets the ItemStack for the item currently in the player's hand.
     *
     * @return 玩家手中当前物品的 ItemStack
     * <p>原文：The ItemStack for the item currently in the player's hand
     */
    @NotNull
    public ItemStack getItemInHand() {
        return this.itemstack;
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
