package org.bukkit.event.block;

import org.bukkit.block.Block;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当方块接受到的红石信号变化时触发此事件
 * <p>原文：
 * Called when a redstone current changes
 */
public class BlockRedstoneEvent extends BlockEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final int oldCurrent;
    private int newCurrent;

    @ApiStatus.Internal
    public BlockRedstoneEvent(@NotNull final Block block, final int oldCurrent, final int newCurrent) {
        super(block);
        this.oldCurrent = oldCurrent;
        this.newCurrent = newCurrent;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块之前的红石信号强度(0~15).
     * <p>原文：
     * Gets the old current of this block
     *
     * @return 红石信号强度(0~15)
     * <p>原文：The previous current
     */
    public int getOldCurrent() {
        return this.oldCurrent;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个方块收到的红石信号强度将要变成的值(0~15).
     * <p>原文：
     * Gets the new current of this block
     *
     * @return 红石信号强度(0~15)
     * <p>原文：The new current
     */
    public int getNewCurrent() {
        return this.newCurrent;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个方块收到的红石信号强度将要变成的值(0~15).
     * <p>原文：
     * Sets the new current of this block
     *
     * @param newCurrent 红石信号强度,取值(0~15)
     * <p>原文：The new current to set
     */
    public void setNewCurrent(int newCurrent) {
        this.newCurrent = newCurrent;
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
