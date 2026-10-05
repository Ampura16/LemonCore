package io.papermc.paper.event.block;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockExpEvent;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当方块迫使另一个方块破坏并掉落物品时调用。
 * <p>
 * 目前在活塞和液体流动时调用。
 * <p>原文：
 * Called when a block forces another block to break and drop items.
 * <p>
 * Currently called for piston's and liquid flows.
 */
@NullMarked
public class BlockBreakBlockEvent extends BlockExpEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block source;
    private final List<ItemStack> drops;

    @ApiStatus.Internal
    public BlockBreakBlockEvent(final Block block, final Block source, final List<ItemStack> drops) {
        super(block, 0);
        this.source = source;
        this.drops = drops;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件的可变掉落物列表
     * <p>原文：
     * Gets a mutable list of drops for this event
     *
     * @return 掉落物
     * <p>原文：the drops
     */
    public List<ItemStack> getDrops() {
        return this.drops;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取导致此事件的方块（例如活塞或相邻的液体）
     * <p>原文：
     * Gets the block that cause this (e.g. a piston, or adjacent liquid)
     *
     * @return 来源
     * <p>原文：the source
     */
    public Block getSource() {
        return this.source;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
