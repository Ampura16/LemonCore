package org.bukkit.event.entity;

import org.bukkit.entity.Firework;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当烟花爆炸时触发本事件
 * <p>原文：
 * Called when a firework explodes.
 */
public class FireworkExplodeEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private boolean cancelled;

    @ApiStatus.Internal
    public FireworkExplodeEvent(@NotNull final Firework firework) {
        super(firework);
    }

    @NotNull
    @Override
    public Firework getEntity() {
        return (Firework) super.getEntity();
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此事件的取消状态。如果烟花的爆炸被取消，烟花仍然将被移除，但烟花颗粒不会显示.
     * <p>原文：
     * {@inheritDoc}
     * <p>
     * If the firework explosion is cancelled, the firework will
     * still be removed, but no particles will be displayed.
     *
     * @param cancel 是否取消事件
     */
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
