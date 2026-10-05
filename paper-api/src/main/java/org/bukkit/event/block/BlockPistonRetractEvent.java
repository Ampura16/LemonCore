package org.bukkit.event.block;

import java.util.List;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 活塞臂缩回事件
 * <p>原文：
 * Called when a piston retracts
 */
public class BlockPistonRetractEvent extends BlockPistonEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final List<Block> blocks;

    @ApiStatus.Internal
    public BlockPistonRetractEvent(@NotNull final Block block, @NotNull final List<Block> blocks, @NotNull final BlockFace direction) {
        super(block, direction);

        this.blocks = blocks;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果缩回的活塞是粘性的,获取被这个活塞粘住的方块的位置.
     * <p>原文：
     * Gets the location where the possible moving block might be if the
     * retracting piston is sticky.
     *
     * @return 这个活塞粘住的方块的位置
     * <p>原文：The possible location of the possibly moving block.
     */
    @Deprecated(since = "1.8")
    @NotNull
    public Location getRetractLocation() {
        return this.getBlock().getRelative(getDirection(), 2).getLocation();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被活塞移动的不可改变的方块列表.
     * <p>原文：
     * Get an immutable list of the blocks which will be moved by the
     * retracting.
     *
     * @return 被活塞移动的方块列表.
     * <p>原文：Immutable list of the moved blocks.
     */
    @NotNull
    @Unmodifiable
    public List<Block> getBlocks() {
        return this.blocks;
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
