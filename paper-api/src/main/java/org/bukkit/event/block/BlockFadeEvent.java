package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 方块因为自然条件消退，融化，消失的事件.
 * <p>
 * 例如:
 * <ul>
 * <li>雪被附近的光源融化
 * <li>冰被附近的光源融化
 * <li>火燃烧后破坏可以作为燃料的方块
 * <li>珊瑚因缺水而变为失活的珊瑚.</li>
 * <li>海龟蛋正在破壳, 当海龟正在孵化海龟蛋时.</li>
 * </ul>
 * <p>
 * 如果这事件被取消, 方块不会因为自然条件消退，融化，消失.
 * <p>原文：
 * Called when a block fades, melts or disappears based on world conditions
 * <p>
 * Examples:
 * <ul>
 * <li>Snow melting due to being near a light source.
 * <li>Ice melting due to being near a light source.
 * <li>Fire burning out after time, without destroying fuel block.
 * <li>Coral fading to dead coral due to lack of water</li>
 * <li>Turtle Egg bursting when a turtle hatches</li>
 * </ul>
 * <p>
 * If this event is cancelled, the block will not fade, melt or
 * disappear.
 */
public class BlockFadeEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final BlockState newState;
    private boolean cancelled;

    @ApiStatus.Internal
    public BlockFadeEvent(@NotNull final Block block, @NotNull final BlockState newState) {
        super(block);
        this.newState = newState;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取因为世界条件消退，融化，消失的方块的状态({@link BlockState}).
     * <p>原文：
     * Gets the state of the new block that will replace the block
     * fading, melting or disappearing.
     *
     * @return 方块的新状态({@link BlockState})
     * <p>原文：The block state of the new block that replaces the block
     *     fading, melting or disappearing
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
