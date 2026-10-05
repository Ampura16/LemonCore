package org.bukkit.event.block;

import com.google.common.base.Preconditions;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当液体方块的液面高度因相邻方块而发生变化时触发本事件.
 * <p>原文：
 * Called when the fluid level of a block changes due to changes in adjacent
 * blocks.
 */
public class FluidLevelChangeEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private BlockData newData;
    private boolean cancelled;

    @ApiStatus.Internal
    public FluidLevelChangeEvent(@NotNull Block fluid, @NotNull BlockData newData) {
        super(fluid);
        this.newData = newData;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取变化后的方块的新数据.
     * <p>原文：
     * Gets the new data of the changed block.
     *
     * @return 新方块数据
     * <p>原文：new data
     */
    @NotNull
    public BlockData getNewData() {
        return this.newData;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置变化后的方块的新数据. 必须与旧方块数据描述的物品种类相同.
     * <p>原文：
     * Sets the new data of the changed block. Must be of the same Material as
     * the old one.
     *
     * @param newData 新方块数据
     * <p>原文：the new data
     */
    public void setNewData(@NotNull BlockData newData) {
        Preconditions.checkArgument(newData != null, "newData null");
        Preconditions.checkArgument(this.newData.getMaterial().equals(newData.getMaterial()), "Cannot change fluid type");

        this.newData = newData.clone();
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
