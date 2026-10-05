package org.bukkit.event.entity;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Called when any Entity changes a block and a more specific event is not available.
 */
public class EntityChangeBlockEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block block;
    private final BlockData to;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityChangeBlockEvent(@NotNull final Entity entity, @NotNull final Block block, @NotNull final BlockData to) {
        super(entity);
        this.block = block;
        this.to = to;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回改变之前的方块(Block)类
     * <p>原文：
     * Gets the block the entity is changing
     *
     * @return 改变之前的方块(Block)类
     * <p>原文：the block that is changing
     */
    @NotNull
    public Block getBlock() {
        return this.block;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回改变后的材料(Material)类
     * <p>原文：
     * Gets the Material that the block is changing into
     *
     * @return 改变后的材料(Material)类
     * <p>原文：the material that the block is changing into
     */
    @NotNull
    public Material getTo() {
        return this.to.getMaterial();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回方块改变后的数据.
     * <p>原文：
     * Gets the data for the block that would be changed into
     *
     * @return 改变后的数据
     * <p>原文：the data for the block that would be changed into
     */
    @NotNull
    public BlockData getBlockData() {
        return this.to.clone();
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
