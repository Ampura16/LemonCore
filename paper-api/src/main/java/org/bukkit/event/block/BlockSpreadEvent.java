package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个方块基于自然法则地蔓延时触发此事件(比如菌丝的蔓延).
 * <p>
 * 用 {@link BlockFormEvent} 来获取方块 。方块蔓延是“随机”的形式，不是
 * 实际地传播.
 * <p>
 * 例如:
 * <ul>
 * <li>蘑菇的蔓延
 * <li>火的蔓延
 * </ul>
 * <p>
 * 若本事件被取消,方块就不会蔓延.
 * <p>原文：
 * Called when a block spreads based on world conditions.
 * <p>
 * Use {@link BlockFormEvent} to catch blocks that "randomly" form instead of
 * actually spread.
 * <p>
 * Examples:
 * <ul>
 * <li>Mushrooms spreading.
 * <li>Fire spreading.
 * </ul>
 * <p>
 * If this event is cancelled, the block will not spread.
 *
 * @see BlockFormEvent
 */
public class BlockSpreadEvent extends BlockFormEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block source;

    @ApiStatus.Internal
    public BlockSpreadEvent(@NotNull final Block block, @NotNull final Block source, @NotNull final BlockState newState) {
        super(block, newState);
        this.source = source;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个事件中蔓延的源方块.
     * <p>原文：
     * Gets the source block involved in this event.
     *
     * @return 这个事件中蔓延的源方块
     * <p>原文：the Block for the source block involved in this event.
     */
    @NotNull
    public Block getSource() {
        return this.source;
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
