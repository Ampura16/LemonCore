package org.bukkit.event.block;

import org.bukkit.GameEvent;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当Sculk Sensor接收到游戏事件并因此可能激活时调用.
 *
 * 如果方块的默认行为是忽略事件, 则将调用取消.
 * <p>原文：
 * Called when a Sculk sensor receives a game event and hence might activate.
 * <br>
 * Will be called cancelled if the block's default behavior is to ignore the
 * event.
 */
public class BlockReceiveGameEvent extends BlockEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final GameEvent event;
    private final Entity entity;

    private boolean cancelled;

    @ApiStatus.Internal
    public BlockReceiveGameEvent(@NotNull GameEvent event, @NotNull Block block, @Nullable Entity entity) {
        super(block);
        this.event = event;
        this.entity = entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取底层事件.
     * <p>原文：
     * Get the underlying event.
     *
     * @return 事件
     * <p>原文：the event
     */
    @NotNull
    public GameEvent getEvent() {
        return this.event;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取触发此事件的实体(如果存在).
     * <p>原文：
     * Get the entity which triggered this event, if present.
     *
     * @return 触发实体或null
     * <p>原文：triggering entity or {@code null}
     */
    @Nullable
    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
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
