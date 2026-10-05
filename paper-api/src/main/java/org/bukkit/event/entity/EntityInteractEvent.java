package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个实体与其他物体互交时触发本事件
 * <p>原文：
 * Called when an entity interacts with an object
 */
public class EntityInteractEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    protected Block block;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityInteractEvent(@NotNull final Entity entity, @NotNull final Block block) {
        super(entity);
        this.block = block;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回实体交互的方块
     * <p>原文：
     * Returns the involved block
     *
     * @return 被点击的方块
     * <p>原文：the block clicked with this item.
     */
    @NotNull
    public Block getBlock() {
        return this.block;
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
