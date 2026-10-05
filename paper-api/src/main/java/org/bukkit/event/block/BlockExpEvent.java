package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 方块产生经验的时候(比如煤矿被被打破会掉落经验)时触发.
 * <p>原文：
 * An event that's called when a block yields experience.
 */
public class BlockExpEvent extends BlockEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private int exp;

    @ApiStatus.Internal
    public BlockExpEvent(@NotNull Block block, int exp) {
        super(block);

        this.exp = exp;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取方块在事件被执行之后掉落的经验.
     * <p>原文：
     * Get the experience dropped by the block after the event has processed
     *
     * @return 方块掉落的经验
     * <p>原文：The experience to drop
     */
    public int getExpToDrop() {
        return this.exp;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个方块在事件被执行之后会掉落多少经验.
     * <p>原文：
     * Set the amount of experience dropped by the block after the event has
     * processed
     *
     * @param exp 应该大于或等于1,否则啥也不会掉落
     * <p>原文：1 or higher to drop experience, else nothing will drop
     */
    public void setExpToDrop(int exp) {
        this.exp = exp;
    }

    @Override
    @NotNull
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
