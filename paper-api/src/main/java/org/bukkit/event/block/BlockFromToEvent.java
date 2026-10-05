package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 液体流动/龙蛋自己传送的事件(源方块到目标方块).
 * <p>
 * 如果事件被取消,方块不会移动/流动.
 * <p>原文：
 * Represents events with a source block and a destination block, currently
 * only applies to liquid (lava and water) and teleporting dragon eggs.
 * <p>
 * If this event is cancelled, the block will not move (the liquid
 * will not flow).
 */
public class BlockFromToEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    protected Block to;
    protected BlockFace face;

    protected boolean cancelled;

    @ApiStatus.Internal
    public BlockFromToEvent(@NotNull final Block block, @NotNull final BlockFace face) {
        super(block);
        this.face = face;
    }

    @ApiStatus.Internal
    public BlockFromToEvent(@NotNull final Block block, @NotNull final Block toBlock) {
        this(block, BlockFace.SELF);
        this.to = toBlock;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取方块想要移动到的{@link BlockFace 朝向}.
     * <p>原文：
     * Gets the BlockFace that the block is moving to.
     *
     * @return 方块想要移动到的{@link BlockFace 朝向}
     * <p>原文：The BlockFace that the block is moving to
     */
    @NotNull
    public BlockFace getFace() {
        return this.face;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取方块要移动到的地方的一个快捷的方法.
     * <p>
     * Convenience method for getting the faced Block.
     * <p>原文：
     * Convenience method for getting the faced Block.
     *
     * @return 目标方块
     * <p>原文：The faced Block
     */
    @NotNull
    public Block getToBlock() {
        if (this.to == null) {
            this.to = this.block.getRelative(this.face);
        }
        return this.to;
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
