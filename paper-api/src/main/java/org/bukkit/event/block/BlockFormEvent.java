package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个方块因为自然变化被放置、更改或者蔓延时(比如下雪),触发此事件.
 * <p>
 * 使用 {@link BlockSpreadEvent} 来捕获方块，事实上在方块蔓延的时候，不只是
 * "随意地" 构成。
 * <p>
 * 例子:
 * <ul>
 * <li>雪在下雪的时候形成.
 * <li>冰在有雪的生物群系中构成.
 * <li>黑曜石/圆石/(石头)由于(熔岩)与水接触而蔓延(熔岩与水的接触的结果).
 * <li>混凝土由于混凝土粉末与水的混合固化而蔓延.
 * </ul>
 * <p>
 * 如果本事件被取消，方块将不会蔓延等.
 * <p>原文：
 * Called when a block is formed or spreads based on world conditions.
 * <p>
 * Use {@link BlockSpreadEvent} to catch blocks that actually spread and don't
 * just "randomly" form.
 * <p>
 * Examples:
 * <ul>
 * <li>Snow forming due to a snow storm.
 * <li>Ice forming in a snowy Biome like Taiga or Tundra.
 * <li> Obsidian / Cobblestone forming due to contact with water.
 * <li> Concrete forming due to mixing of concrete powder and water.
 * </ul>
 * <p>
 * If this event is cancelled, the block will not be formed.
 *
 * @see BlockSpreadEvent
 */
public class BlockFormEvent extends BlockGrowEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @ApiStatus.Internal
    public BlockFormEvent(@NotNull final Block block, @NotNull final BlockState newState) {
        super(block, newState);
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
