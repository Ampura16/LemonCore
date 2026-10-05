package org.bukkit.event.block;

import java.util.List;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.world.StructureGrowEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家使用骨粉向某方块施肥后导致方块发生变化时触发本事件.
 * 将在合适之时调用 {@link StructureGrowEvent}.
 * <p>原文：
 * Called with the block changes resulting from a player fertilizing a given
 * block with bonemeal. Will be called after the applicable
 * {@link StructureGrowEvent}.
 */
public class BlockFertilizeEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Player player;
    private final List<BlockState> blocks;

    private boolean cancelled;

    @ApiStatus.Internal
    public BlockFertilizeEvent(@NotNull Block block, @Nullable Player player, @NotNull List<BlockState> blocks) {
        super(block);
        this.player = player;
        this.blocks = blocks;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取进行施肥的玩家.
     * <p>原文：
     * Gets the player that triggered the fertilization.
     *
     * @return 进行施肥的玩家, 若不适用则为null
     * <p>原文：triggering player, or {@code null} if not applicable
     */
    @Nullable
    public Player getPlayer() {
        return this.player;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回由于施肥而产生变化的所有方块的列表.
     * <p>原文：
     * Gets a list of all blocks changed by the fertilization.
     *
     * @return 产生变化的方块的列表
     * <p>原文：list of all changed blocks
     */
    @NotNull
    public List<BlockState> getBlocks() {
        return this.blocks;
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
