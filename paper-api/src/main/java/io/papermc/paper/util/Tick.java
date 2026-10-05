package io.papermc.paper.util;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalUnit;
import java.util.Objects;
import net.kyori.adventure.util.Ticks;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示一个服务器刻目标时长的 TemporalUnit，定义为 50 毫秒。注意，该类并非用于测量某一刻实际耗费的时长，而是用于时间与刻之间的简单换算。
 * <p>原文：
 * A TemporalUnit that represents the target length of one server tick. This is defined
 * as 50 milliseconds. Note that this class is not for measuring the length that a tick
 * took, rather it is used for simple conversion between times and ticks.
 *
 * @see #tick()
 */
@NullMarked
public final class Tick implements TemporalUnit {

    private static final Tick INSTANCE = new Tick(Ticks.SINGLE_TICK_DURATION_MS);

    private final long milliseconds;

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取刻时间单位的实例。
     * <p>原文：
     * Gets the instance of the tick temporal unit.
     *
     * @return 刻实例
     * <p>原文：the tick instance
     */
    public static Tick tick() {
        return INSTANCE;
    }

    /**
     * Creates a new tick.
     *
     * @param length the length of the tick in milliseconds
     * @see #tick()
     */
    private Tick(final long length) {
        this.milliseconds = length;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从刻数创建持续时间。这是以刻数和 {@link #tick()} 调用 {@link Duration#of(long, TemporalUnit)} 的简写。
     * <p>原文：
     * Creates a duration from an amount of ticks. This is shorthand for
     * {@link Duration#of(long, TemporalUnit)} called with the amount of ticks and
     * {@link #tick()}.
     *
     * @param ticks 刻数
     * <p>原文：the amount of ticks
     * @return 持续时间
     * <p>原文：the duration
     */
    public static Duration of(final long ticks) {
        return Duration.of(ticks, INSTANCE);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取所提供时长中包含的完整刻数。注意，该方法返回 {@code int}，因为 Minecraft 以此单位存储刻。
     * <p>原文：
     * Gets the number of whole ticks that occur in the provided duration. Note that this
     * method returns an {@code int} as this is the unit that Minecraft stores ticks in.
     *
     * @param duration 持续时间
     * <p>原文：the duration
     * @return 此时长中完整刻的数量
     * <p>原文：the number of whole ticks in this duration
     * @throws ArithmeticException 如果时长为零或发生溢出
     * <p>原文：if the duration is zero or an overflow occurs
     */
    public int fromDuration(final Duration duration) {
        Objects.requireNonNull(duration, "duration cannot be null");
        return Math.toIntExact(Math.floorDiv(duration.toMillis(), this.milliseconds));
    }

    @Override
    public Duration getDuration() {
        return Duration.ofMillis(this.milliseconds);
    }

    // Note: This is a workaround in order to allow calculations with this duration.
    // See: Duration#add
    @Override
    public boolean isDurationEstimated() {
        return false;
    }

    @Override
    public boolean isDateBased() {
        return false;
    }

    @Override
    public boolean isTimeBased() {
        return true;
    }

    @SuppressWarnings("unchecked") // following ChronoUnit#addTo
    @Override
    public <R extends Temporal> R addTo(final R temporal, final long amount) {
        return (R) temporal.plus(this.getDuration().multipliedBy(amount));
    }

    @Override
    public long between(final Temporal start, final Temporal end) {
        return start.until(end, ChronoUnit.MILLIS) / this.milliseconds;
    }
}
