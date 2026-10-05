package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体燃烧时触发该事件
 * <p>
 * 如果这个事件被取消,那么这个实体将不会燃烧.
 * <p>原文：
 * Called when an entity combusts.
 * <p>
 * If this event is cancelled, the entity will not combust.
 */
public class EntityCombustEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private float duration;

    private boolean cancelled;

    @ApiStatus.Internal
    @Deprecated(since = "1.21", forRemoval = true)
    public EntityCombustEvent(@NotNull final Entity combustee, final int duration) {
        this(combustee, (float) duration);
    }

    @ApiStatus.Internal
    public EntityCombustEvent(@NotNull final Entity combustee, final float duration) {
        super(combustee);
        this.duration = duration;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 应被燃烧的时间长度(秒)
     * <p>原文：the amount of time (in seconds) the combustee should be alight
     *     for
     */
    public float getDuration() {
        return this.duration;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 应被燃烧的时间(秒).
     * <p>
     * 这个数值仅会延长燃烧的时间,不会减少现有的燃烧时间.
     * <p>原文：
     * The number of seconds the combustee should be alight for.
     * <p>
     * This value will only ever increase the combustion time, not decrease
     * existing combustion times.
     *
     * @param duration 燃烧的持续时间(秒).
     * <p>原文：the time in seconds to be alight for.
     */
    public void setDuration(float duration) {
        this.duration = duration;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 应被燃烧的时间(秒).
     * <p>
     * 这个数值仅会延长燃烧的时间,不会减少现有的燃烧时间.
     * <p>原文：
     * The number of seconds the combustee should be alight for.
     * <p>
     * This value will only ever increase the combustion time, not decrease
     * existing combustion times.
     *
     * @param duration 燃烧的持续时间(秒).
     * <p>原文：the time in seconds to be alight for.
     * @see #setDuration(float)
     * @deprecated duration 现在为浮点数
     * <p>原文：duration is now a float
     */
    @Deprecated(since = "1.21", forRemoval = true)
    public void setDuration(int duration) {
        this.duration = duration;
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
