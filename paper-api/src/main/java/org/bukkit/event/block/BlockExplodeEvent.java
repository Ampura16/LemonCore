package org.bukkit.event.block;

import java.util.List;
import org.bukkit.ExplosionResult;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 方块爆炸事件.
 * <p>
 * 注意由于爆炸的自然性, {@link #getBlock()} 将永远是一个空气方块.
 * 请使用 {@link #getExplodedBlockState()} 获取发生爆炸的方块的信息.
 * <p>
 * 译注:{@link #getBlock()}方法返回的是引起爆炸的方块(比如TNT),并不是被炸掉的方块.
 * <p>原文：
 * Called when a block explodes.
 * <p>
 * Note that due to the nature of explosions, {@link #getBlock()} will always be
 * an air block. {@link #getExplodedBlockState()} should be used to get
 * information about the block state that exploded.
 * <p>
 * The event isn't called if the {@link org.bukkit.GameRules#MOB_GRIEFING}
 * is disabled as no block interaction will occur.
 */
public class BlockExplodeEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final BlockState blockState;
    private final List<Block> blocks;
    private float yield;
    private final ExplosionResult result;

    private boolean cancelled;

    @ApiStatus.Internal
    public BlockExplodeEvent(@NotNull final Block block, @NotNull final BlockState blockState, @NotNull final List<Block> blocks, final float yield, @NotNull final ExplosionResult result) {
        super(block);
        this.blockState = blockState;
        this.blocks = blocks;
        this.yield = yield;
        this.result = result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此次爆炸的结果 (如果事件未被取消).
     * <p>原文：
     * Returns the result of the explosion if it is not cancelled.
     *
     * @return 爆炸结果
     * <p>原文：the result of the explosion
     */
    @NotNull
    public ExplosionResult getExplosionResult() {
        return this.result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回发生爆炸的方块的已捕获状态.
     * <p>原文：
     * Returns the captured BlockState of the block that exploded.
     *
     * @return 方块状态
     * <p>原文：the block state
     */
    @NotNull
    public BlockState getExplodedBlockState() {
        return this.blockState;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回由于此爆炸事件而将受影响或已受影响的方块列表.
     * <p>原文：
     * Returns the list of blocks that would have been removed or were removed
     * from the explosion event.
     *
     * @return 所有受影响的方块的列表
     * <p>原文：All blown-up blocks
     */
    @NotNull
    public List<Block> blockList() {
        return this.blocks;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这次爆炸炸毁的方块掉落的可能性(小数百分比).
     * <p>原文：
     * Returns the percentage of blocks to drop from this explosion
     *
     * @return 小数百分率
     * <p>原文：The yield.
     */
    public float getYield() {
        return this.yield;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这次爆炸炸毁的方块掉落的可能性(小数百分比).
     * <p>原文：
     * Sets the percentage of blocks to drop from this explosion
     *
     * @param yield 小数百分率
     * <p>原文：The new yield percentage
     */
    public void setYield(float yield) {
        this.yield = yield;
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
