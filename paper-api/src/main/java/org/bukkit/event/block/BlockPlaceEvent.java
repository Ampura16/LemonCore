package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个方块被玩家放置的时候触发此事件.
 * <p>
 * 如果方块放置事件被取消，那么这个方块将不能被放置
 * <p>原文：
 * Called when a block is placed by a player.
 * <p>
 * If this event is cancelled, the block will not be placed.
 */
public class BlockPlaceEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    protected Block placedAgainst;
    protected ItemStack itemInHand;
    protected Player player;
    protected BlockState replacedBlockState;
    protected boolean canBuild;
    protected EquipmentSlot hand;

    protected boolean cancelled;

    @ApiStatus.Internal
    @Deprecated(since = "1.9", forRemoval = true)
    public BlockPlaceEvent(@NotNull final Block placedBlock, @NotNull final BlockState replacedBlockState, @NotNull final Block placedAgainst, @NotNull final ItemStack itemInHand, @NotNull final Player thePlayer, final boolean canBuild) {
        this(placedBlock, replacedBlockState, placedAgainst, itemInHand, thePlayer, canBuild, EquipmentSlot.HAND);
    }

    @ApiStatus.Internal
    public BlockPlaceEvent(@NotNull final Block placedBlock, @NotNull final BlockState replacedBlockState, @NotNull final Block placedAgainst, @NotNull final ItemStack itemInHand, @NotNull final Player thePlayer, final boolean canBuild, @NotNull final EquipmentSlot hand) {
        super(placedBlock);
        this.placedAgainst = placedAgainst;
        this.itemInHand = itemInHand;
        this.player = thePlayer;
        this.replacedBlockState = replacedBlockState;
        this.canBuild = canBuild;
        this.hand = hand;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块是依附在哪个方块上放置的.
     * <p>原文：
     * Gets the block that this block was placed against
     *
     * @return 放置时依附的方块
     * <p>原文：Block the block that the new block was placed against
     */
    @NotNull
    public Block getBlockAgainst() {
        return this.placedAgainst;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家放置的方块的物品形式(在玩家手上).
     * <p>原文：
     * Gets the item in the player's hand when they placed the block.
     *
     * @return 这个方块的ItemStack对象
     * <p>原文：The ItemStack for the item in the player's hand when they
     *     placed the block
     */
    @NotNull
    public ItemStack getItemInHand() {
        return this.itemInHand;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得放置这个方块的玩家.
     * <p>原文：
     * Gets the player who placed the block involved in this event.
     *
     * @return 放置这个方块的玩家
     * <p>原文：The Player who placed the block involved in this event
     */
    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得被放置的方块(还未真正被放置).
     * <p>原文：
     * Clarity method for getting the placed block. Not really needed except
     * for reasons of clarity.
     *
     * @return 被放置后的方块
     * <p>原文：The Block that was placed
     */
    @NotNull
    public Block getBlockPlaced() {
        return this.getBlock();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被替换的方块的 BlockState. 通常被替换的方块是空气.
     * <p>原文：
     * Gets the BlockState for the block which was replaced. Material type air
     * mostly.
     *
     * @return 被替换的方块的 BlockState.
     * <p>原文：The BlockState for the block which was replaced.
     */
    @NotNull
    public BlockState getBlockReplacedState() {
        return this.replacedBlockState;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块是否可以建造.
     * <p>
     * 默认情况下，如果玩家还在重生/生成时服务器将阻止它们. 注意，这跟BLOCK_CANBUILD是完全不同的检测.这是指玩家而不是像仙人掌一样的物理现象.
     * <p>原文：
     * Gets the value whether the player would be allowed to build here.
     * Defaults to {@code false} if the server was going to stop them (such as, the
     * player is in Spawn). Note that this is an entirely different check
     * than BLOCK_CANBUILD, as this refers to a player, not universe-physics
     * rule like cactus on dirt.
     *
     * @return boolean 服务器是否允许此方块的建造
     * <p>原文：boolean whether the server would allow a player to build here
     */
    public boolean canBuild() {
        return this.canBuild;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个方块是否可以被放置.
     * <p>原文：
     * Sets the canBuild state of this event. Set to {@code true} if you want the
     * player to be able to build.
     *
     * @param canBuild 是否允许玩家放置这个方块
     * <p>原文：{@code true} if you want the player to be able to build
     */
    public void setBuild(boolean canBuild) {
        this.canBuild = canBuild;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取放置这个方块的手.
     * <p>原文：
     * Gets the hand which placed the block
     *
     * @return 主手或副手，取决于放置方块所用的手
     * <p>原文：Main or off-hand, depending on which hand was used to place the block
     */
    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
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
