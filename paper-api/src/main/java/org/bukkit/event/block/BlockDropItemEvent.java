package org.bukkit.event.block;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个方块被玩家破坏且掉落物品时触发本事件.
 *
 * 如果方块破坏事件(BlockBreakEvent)被取消, 那么将不会触发本事件.
 *
 * 如果将 BlockBreakEvent 中的 isDropItems 设置为 false (通过 BlockBreakEvent 中的 setDropItems 方法设置), 也不会触发本事件.
 *
 * 如果玩家破坏了一个多方块结构, 例如在石头顶部的火把, 本事件也会被调用. 所有结构的掉落物计入同一个事件内.
 *
 * 当调用本事件时, 意味着此方块已经被破坏, 因此 #getBlock() 大部分情况是 AIR.
 * 请使用 #getBlockState() 获取有关此被破坏的方块的更多信息.
 * <p>原文：
 * Called after a block is broken by a player and potential drops are computed, even if said blocks loot table
 * does not define any drops at the point the event is constructed.
 * <p>
 * If the block break is cancelled, this event won't be called.
 * <p>
 * If {@link BlockBreakEvent#isDropItems()} is set to {@code false}, this event won't be
 * called.
 * <p>
 * If a block is broken and {@link BlockBreakEvent#isDropItems()} is set to {@code true},
 * this event will be called even if the block does not drop any items, for example
 * glass broken by hand. In this case, {@link #getItems()} will be empty.
 * <p>
 * This event will also be called if the player breaks a multi block structure,
 * for example a torch on top of a stone. Both items will be included in the {@link #getItems()} list.
 * <p>
 * The Block is already broken as this event is called, so {@link #getBlock()} will be
 * AIR in most cases. Use {@link #getBlockState()} for more Information about the broken
 * block.
 */
public class BlockDropItemEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final BlockState blockState;
    private final List<Item> items;

    private boolean cancelled;

    @ApiStatus.Internal
    public BlockDropItemEvent(@NotNull Block block, @NotNull BlockState blockState, @NotNull Player player, @NotNull List<Item> items) {
        super(block);
        this.blockState = blockState;
        this.player = player;
        this.items = items;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被破坏的方块的BlockState.
     * <p>原文：
     * Gets the BlockState of the block involved in this event before it was
     * broken.
     *
     * @return 被破坏的方块的BlockState
     * <p>原文：The BlockState of the block involved in this event
     */
    @NotNull
    public BlockState getBlockState() {
        return this.blockState;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取破坏这个方块的玩家.
     * <p>原文：
     * Gets the Player that is breaking the block involved in this event.
     *
     * @return 破坏这个方块的玩家
     * <p>原文：The Player that is breaking the block involved in this event
     */
    @NotNull
    public Player getPlayer() {
        return this.player;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取由于破坏方块而产生的掉落物实体的列表.
     *
     * 这个列表是可变的 - 移除在列表中的物品将导致其不会掉落.
     * 然而向这个列表添加新的物品是不合法的.
     * <p>原文：
     * Gets list of the Item drops caused by the block break.
     * <p>
     * This list is mutable: removing an item from it will cause it to not
     * drop. Adding to the list is allowed.
     *
     * @return 方块被破坏后产生的掉落物的列表
     * <p>原文：The Item the block caused to drop
     */
    @NotNull
    public List<Item> getItems() {
        return this.items;
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
