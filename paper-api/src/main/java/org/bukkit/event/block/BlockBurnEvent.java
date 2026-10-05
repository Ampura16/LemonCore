package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个方块被火烧掉的时候触发此事件.
 * <p>
 * 若方块点燃事件被取消，此方块就不会被火烧毁.
 * <p>原文：
 * Called when a block is destroyed as a result of being burnt by fire.
 * <p>
 * If this event is cancelled, the block will not be destroyed as a
 * result of being burnt by fire.
 */
public class BlockBurnEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block ignitingBlock;
    private boolean cancelled;

    @Deprecated(since = "1.11.2", forRemoval = true)
    @ApiStatus.Internal
    public BlockBurnEvent(@NotNull final Block block) {
        this(block, null);
    }

    @ApiStatus.Internal
    public BlockBurnEvent(@NotNull final Block block, @Nullable final Block ignitingBlock) {
        super(block);
        this.ignitingBlock = ignitingBlock;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取点燃这个方块的方块.
     * <p>原文：
     * Gets the block which ignited this block.
     *
     * @return 点燃这个方块的方块，或如果源方块不存在时为null
     * <p>原文：The Block that ignited and burned this block, or {@code null} if no
     * source block exists
     */
    @Nullable
    public Block getIgnitingBlock() {
        return this.ignitingBlock;
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
