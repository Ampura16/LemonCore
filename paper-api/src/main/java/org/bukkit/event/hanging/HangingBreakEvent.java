package org.bukkit.event.hanging;

import org.bukkit.entity.Hanging;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个悬挂实体被移除时调用.
 * <p>原文：
 * Triggered when a hanging entity is removed
 */
public class HangingBreakEvent extends HangingEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final HangingBreakEvent.RemoveCause cause;
    private boolean cancelled;

    @ApiStatus.Internal
    public HangingBreakEvent(@NotNull final Hanging hanging, @NotNull final HangingBreakEvent.RemoveCause cause) {
        super(hanging);
        this.cause = cause;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个悬挂实体被移除的原因.
     * <p>原文：
     * Gets the cause for the hanging entity's removal
     *
     * @return 这个悬挂实体被移除的原因
     * <p>原文：the RemoveCause for the hanging entity's removal
     */
    @NotNull
    public HangingBreakEvent.RemoveCause getCause() {
        return this.cause;
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

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 指定移除原因的一个枚举
     * <p>原文：
     * An enum to specify the cause of the removal
     */
    public enum RemoveCause {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 被一个实体移除
         * <p>原文：
         * Removed by an entity
         */
        ENTITY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 因爆炸而移除
         * <p>原文：
         * Removed by an explosion
         */
        EXPLOSION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 放置方块时自动脱落
         * <p>原文：
         * Removed by placing a block on it
         */
        OBSTRUCTION,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 破坏它背后的方块而自动脱落
         * <p>原文：
         * Removed by destroying the block behind it, etc
         */
        PHYSICS,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 未分类（未知）的移除原因（默认）
         * <p>原文：
         * Removed by an uncategorised cause
         */
        DEFAULT,
    }
}
