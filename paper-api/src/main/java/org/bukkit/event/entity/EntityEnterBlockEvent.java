package org.bukkit.event.entity;

import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当{@link Entity 实体}进入方块, 保存到那个方块时触发本事件.
 * <p>
 * 蜜蜂进入蜂巢时会触发本事件.
 * <br>
 * 蠹虫“进入”石头方块则不会触发本事件. 对于这种情况,
 * 请监听 {@link EntityChangeBlockEvent}.
 * <p>原文：
 * Called when an {@link Entity} enters a block and is stored in that block.
 * <p>
 * This event is called for bees entering a bee hive.
 * <br>
 * It is not called when a silverfish "enters" a stone block. For that listen to
 * the {@link EntityChangeBlockEvent}.
 */
public class EntityEnterBlockEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Block block;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityEnterBlockEvent(@NotNull final Entity entity, @NotNull final Block block) {
        super(entity);

        this.block = block;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体将进入的方块.
     * <p>原文：
     * Get the block the entity will enter.
     *
     * @return 方块
     * <p>原文：the block
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
