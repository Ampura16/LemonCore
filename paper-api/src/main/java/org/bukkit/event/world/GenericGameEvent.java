package org.bukkit.event.world;

import com.google.common.base.Preconditions;
import org.bukkit.GameEvent;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表 Mojang 通用游戏事件.
 *
 * 你应该尽可能地使用 Bukkit 框架提供的其它事件, 此事件在游戏内部主要用于<a href="https://zh.minecraft.wiki/w/Sculk_Sensor">Sculk_Sensor</a>.
 * <p>原文：
 * Represents a generic Mojang game event.
 * <br>
 * Specific Bukkit events should be used where possible, this event is mainly
 * used internally by Sculk sensors.
 */
public class GenericGameEvent extends WorldEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final GameEvent event;
    private final Location location;
    private final Entity entity;
    private int radius;

    private boolean cancelled;

    @ApiStatus.Internal
    public GenericGameEvent(@NotNull GameEvent event, @NotNull Location location, @Nullable Entity entity, int radius, boolean isAsync) {
        super(location.getWorld(), isAsync);
        this.event = event;
        this.location = location;
        this.entity = entity;
        this.radius = radius;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本事件的底层事件.
     * <p>原文：
     * Get the underlying event.
     *
     * @return 底层事件
     * <p>原文：the event
     */
    @NotNull
    public GameEvent getEvent() {
        return this.event;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取事件发生所在的位置.
     * <p>原文：
     * Get the location where the event occurred.
     *
     * @return 事件发生地点
     * <p>原文：event location
     */
    @NotNull
    public Location getLocation() {
        return this.location.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取触发此事件的实体, 若存在.
     * <p>原文：
     * Get the entity which triggered this event, if present.
     *
     * @return 触发此事件的实体, 若不存在返回 null
     * <p>原文：triggering entity or {@code null}
     */
    @Nullable
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此事件广播的范围半径.
     * <p>原文：
     * Get the block radius to which this event will be broadcast.
     *
     * @return 广播范围
     * <p>原文：broadcast radius
     */
    public int getRadius() {
        return this.radius;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此事件广播的范围半径.
     * <p>原文：
     * Set the radius to which the event should be broadcast.
     *
     * @param radius 半径, 必须大于或等于 0
     * <p>原文：radius, must be greater than or equal to 0
     */
    public void setRadius(int radius) {
        Preconditions.checkArgument(radius >= 0, "Radius must be >= 0");
        this.radius = radius;
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
