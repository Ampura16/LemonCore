package org.bukkit.event.block;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 活塞臂推出事件.
 * <p>原文：
 * Called when a piston extends
 */
public class BlockPistonExtendEvent extends BlockPistonEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final int length;
    private List<Block> blocks;

    @ApiStatus.Internal
    @Deprecated(since = "1.8", forRemoval = true)
    public BlockPistonExtendEvent(@NotNull final Block block, final int length, @NotNull final BlockFace direction) {
        super(block, direction);

        this.length = length;
    }

    @ApiStatus.Internal
    public BlockPistonExtendEvent(@NotNull final Block block, @NotNull final List<Block> blocks, @NotNull final BlockFace direction) {
        super(block, direction);

        this.length = blocks.size();
        this.blocks = blocks;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被活塞移动的方块数.
     * <p>原文：
     * Get the amount of blocks which will be moved while extending.
     *
     * @return 被活塞移动的方块数.
     * <p>原文：the amount of moving blocks
     * @deprecated 由于史莱姆方块的特性,
     * 这个方法是不准确的因为它不包含史莱姆方块粘走的方块,
     * 如MC版本低于1.8请无视此句.
     * <p>原文：slime blocks make the value of this method
     *          inaccurate due to blocks being pushed at the side
     */
    @Deprecated(since = "1.8")
    public int getLength() {
        return this.length;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取被活塞移动的不可改变的方块列表.
     * <p>原文：
     * Get an immutable list of the blocks which will be moved by the
     * extending.
     *
     * @return 被活塞移动的方块列表.
     * <p>原文：Immutable list of the moved blocks.
     */
    @NotNull
    @Unmodifiable
    public List<Block> getBlocks() {
        if (this.blocks == null) {
            List<Block> tmp = new ArrayList<>();
            for (int i = 0; i < this.length; i++) {
                tmp.add(this.block.getRelative(getDirection(), i + 1));
            }
            this.blocks = Collections.unmodifiableList(tmp);
        }
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
