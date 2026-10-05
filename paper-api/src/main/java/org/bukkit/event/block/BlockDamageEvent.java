package org.bukkit.event.block;

import com.google.common.base.Preconditions;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 方块被玩家损坏的事件.
 * <p>
 * 若本事件被取消，方块将不会损坏.
 * <p>原文：
 * Called when a block is damaged by a player.
 * <p>
 * If this event is cancelled, the block will not be damaged.
 *
 * @see BlockDamageAbortEvent
 */
public class BlockDamageEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final BlockFace blockFace;
    private final ItemStack itemInHand;
    private final Player player;
    private boolean instaBreak;

    private boolean cancelled;

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public BlockDamageEvent(@NotNull final Player player, @NotNull final Block block, @NotNull final ItemStack itemInHand, final boolean instaBreak) {
        this(player, block, null, itemInHand, instaBreak);
    }

    @ApiStatus.Internal
    public BlockDamageEvent(@NotNull final Player player, @NotNull final Block block, @NotNull final BlockFace blockFace, @NotNull final ItemStack itemInHand, final boolean instaBreak) { // Paper - Expose BlockFace
        super(block);
        this.instaBreak = instaBreak;
        this.blockFace = blockFace;
        this.itemInHand = itemInHand;
        this.player = player;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取试图损坏这个方块的玩家.
     * <p>原文：
     * Gets the player damaging the block involved in this event.
     *
     * @return 损坏这个方块的玩家
     * <p>原文：The player damaging the block involved in this event
     */
    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回方块是否立即被玩家损坏(比如创造模式).
     * <p>原文：
     * Gets if the block is set to instantly break when damaged by the player.
     *
     * @return 如果方块在被玩家损坏时是被立即破坏的，返回true，反之false
     * <p>原文：{@code true} if the block should instantly break when damaged by the
     *     player
     */
    public boolean getInstaBreak() {
        return this.instaBreak;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置方块是否立即被玩家损坏(比如创造模式).
     * <p>原文：
     * Sets if the block should instantly break when damaged by the player.
     *
     * @param instaBreak {@code true} if you want the block to instantly break when damaged
     *     by the player
     */
    public void setInstaBreak(boolean instaBreak) {
        this.instaBreak = instaBreak;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前玩家手上的物品.
     * <p>原文：
     * Gets the ItemStack for the item currently in the player's hand.
     *
     * @return 当前玩家手上的物品
     * <p>原文：The ItemStack for the item currently in the player's hand
     */
    @NotNull
    public ItemStack getItemInHand() {
        return this.itemInHand;
    }

    /**
     * Gets the BlockFace the player is interacting with.
     *
     * @return The BlockFace clicked to damage the block
     */
    @NotNull
    public org.bukkit.block.BlockFace getBlockFace() {
        Preconditions.checkState(this.blockFace != null, "BlockFace is not available for this event, most likely due to a bad constructor call by a plugin");
        return this.blockFace;
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
