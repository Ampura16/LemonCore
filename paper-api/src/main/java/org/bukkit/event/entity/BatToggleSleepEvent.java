package org.bukkit.event.entity;

import org.bukkit.entity.Bat;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一只蝙蝠将要睡觉或醒来时触发本事件.
 * <p>
 * 如果本事件被取消, 那么这只蝙蝠将不会切换睡眠状态.
 * <p>原文：
 * Called when a bat attempts to sleep or wake up from its slumber.
 * <p>
 * If this event is cancelled, the Bat will not toggle its sleep
 * state.
 */
public class BatToggleSleepEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final boolean awake;

    private boolean cancelled;

    @ApiStatus.Internal
    public BatToggleSleepEvent(@NotNull Bat bat, boolean awake) {
        super(bat);
        this.awake = awake;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这只蝙蝠是否准备苏醒.
     * <p>原文：
     * Get whether the bat is attempting to awaken.
     *
     * @return 如果准备醒来则为 true, 反之为 false
     * <p>原文：{@code true} if trying to awaken, {@code false} otherwise
     */
    public boolean isAwake() {
        return this.awake;
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
