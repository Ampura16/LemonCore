package io.papermc.paper.event.world.border;

import com.google.common.base.Preconditions;
import io.papermc.paper.util.Tick;
import java.time.Duration;
import org.bukkit.World;
import org.bukkit.WorldBorder;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当世界边界改变其范围时调用，无论是随时间渐变还是立即改变。
 * <p>原文：
 * Called when a world border changes its bounds, either over time, or instantly.
 */
@NullMarked
public class WorldBorderBoundsChangeEvent extends WorldBorderEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private Type type;
    private final double oldSize;
    private double newSize;
    private long duration;
    private boolean cancelled;

    @ApiStatus.Internal
    public WorldBorderBoundsChangeEvent(final World world, final WorldBorder worldBorder, final Type type, final double oldSize, final double newSize, final long duration) {
        super(world, worldBorder);
        this.type = type;
        this.oldSize = oldSize;
        this.newSize = newSize;
        this.duration = duration;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此变化是立即变化还是随时间渐变。
     * <p>原文：
     * Gets if this change is an instant change or over-time change.
     *
     * @return 变化类型
     * <p>原文：the change type
     */
    public Type getType() {
        return this.type;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取世界边界原来的大小。
     * <p>原文：
     * Gets the old size or the world border.
     *
     * @return 原来的大小
     * <p>原文：the old size
     */
    public double getOldSize() {
        return this.oldSize;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取世界边界的新大小。
     * <p>原文：
     * Gets the new size of the world border.
     *
     * @return 新的大小
     * <p>原文：the new size
     */
    public double getNewSize() {
        return this.newSize;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置世界边界的新大小。
     * <p>原文：
     * Sets the new size of the world border.
     *
     * @param newSize 新的大小
     * <p>原文：the new size
     */
    public void setNewSize(final double newSize) {
        this.newSize = Math.clamp(newSize, 1.0D, this.worldBorder.getMaxSize());
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取变化所用的时间（刻）。立即变化时为 0。
     * <p>原文：
     * Gets the time in ticks for the change. Will be 0 if instant.
     *
     * @return 变化所需时间（刻）
     * <p>原文：the time in ticks for the change
     */
    public @Range(from = 0, to = Integer.MAX_VALUE) long getDurationTicks() {
        return this.duration;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置变化所用的时间（刻）。将使 {@link #getType()} 返回 {@link Type#STARTED_MOVE}。
     * <p>原文：
     * Sets the time in ticks for the change. Will change {@link #getType()} to return
     * {@link Type#STARTED_MOVE}.
     *
     * @param duration 变化所需时间（刻）
     * <p>原文：the time in ticks for the change
     */
    public void setDurationTicks(final @Range(from = 0, to = Integer.MAX_VALUE) long duration) {
        Preconditions.checkArgument(duration >= 0 && duration <= Integer.MAX_VALUE, "duration must be between 0-%s", Integer.MAX_VALUE);
        this.duration = duration;
        if (this.type == Type.INSTANT_MOVE) {
            this.type = Type.STARTED_MOVE;
        }
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取变化所用的时间（毫秒）。立即变化时为 0。
     * <p>原文：
     * Gets the time in milliseconds for the change. Will be 0 if instant.
     *
     * @return 变化所需时间（毫秒）
     * <p>原文：the time in milliseconds for the change
     * @deprecated 改用 {@link #getDurationTicks()}
     * <p>原文：in favor of {@link #getDurationTicks()}
     */
    @Deprecated(forRemoval = true, since = "1.21.11")
    public @NonNegative long getDuration() {
        return Tick.of(this.getDurationTicks()).toMillis();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置变化所用的时间（毫秒）。将使 {@link #getType()} 返回 {@link Type#STARTED_MOVE}。
     * <p>原文：
     * Sets the time in milliseconds for the change. Will change {@link #getType()} to return
     * {@link Type#STARTED_MOVE}.
     *
     * @param duration 变化所需时间（毫秒）
     * <p>原文：the time in milliseconds for the change
     * @deprecated 改用 {@link #setDurationTicks(long)}
     * <p>原文：in favor of {@link #setDurationTicks(long)}
     */
    @Deprecated(forRemoval = true, since = "1.21.11")
    public void setDuration(final @NonNegative long duration) {
        this.setDurationTicks(Tick.tick().fromDuration(Duration.ofMillis(duration)));
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    public enum Type {
        STARTED_MOVE,
        INSTANT_MOVE
    }
}
