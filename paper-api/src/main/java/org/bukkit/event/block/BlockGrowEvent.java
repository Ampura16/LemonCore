package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个方块在世界中自然生长的时触发此事件(如小麦生长).
 * <p>
 * 举几个例子:
 * <ul>
 * <li>小麦
 * <li>甘蔗
 * <li>仙人掌
 * <li>西瓜
 * <li>南瓜
 * <li>海龟蛋
 * </ul>
 * <p>
 * 如果此事件被取消,方块将不会生长
 * <p>原文：
 * Called when a block grows naturally in the world.
 * <p>
 * Examples:
 * <ul>
 * <li>Wheat
 * <li>Sugar Cane
 * <li>Cactus
 * <li>Watermelon
 * <li>Pumpkin
 * <li>Turtle Egg
 * </ul>
 * <p>
 * If this event is cancelled, the block will not grow.
 */
public class BlockGrowEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final BlockState newState;
    private boolean cancelled;

    @ApiStatus.Internal
    public BlockGrowEvent(@NotNull final Block block, @NotNull final BlockState newState) {
        super(block);
        this.newState = newState;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块生长之后的新状态.
     * <p>原文：
     * Gets the state of the block where it will form or spread to.
     *
     * @return 这个方块生长之后的新状态
     * <p>原文：The block state for this events block
     */
    @NotNull
    public BlockState getNewState() {
        return this.newState;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
