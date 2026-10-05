package com.destroystokyo.paper.event.server;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在服务器完成一次主循环刻后调用
 * <p>原文：
 * Called when the server has finished ticking the main loop
 */
@NullMarked
public class ServerTickEndEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final int tickNumber;
    private final double tickDuration;
    private final long timeEnd;

    @ApiStatus.Internal
    public ServerTickEndEvent(final int tickNumber, final double tickDuration, final long timeRemaining) {
        this.tickNumber = tickNumber;
        this.tickDuration = tickDuration;
        this.timeEnd = System.nanoTime() + timeRemaining;
    }

    /**
     * @return 自启动以来这是第几刻（第一刻 = 1）
     * <p>原文：What tick this was since start (first tick = 1)
     */
    public int getTickNumber() {
        return this.tickNumber;
    }

    /**
     * @return 此刻耗费的时间（毫秒）
     * <p>原文：Time in milliseconds of how long this tick took
     */
    public double getTickDuration() {
        return this.tickDuration;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 距下一刻开始剩余的纳秒数。
     * <p>
     * 如果该值为负，表示服务器已超出刻时间上限，TPS 已下降。
     * <p>
     * 该方法会持续返回更新后的剩余时间（返回值不是静态的）。
     * <p>原文：
     * Amount of nanoseconds remaining before the next tick should start.
     * <p>
     * If this value is negative, then that means the server has exceeded the tick time limit and TPS has been lost.
     * <p>
     * Method will continuously return the updated time remaining value. (return value is not static)
     *
     * @return 距下一刻开始剩余的纳秒数
     * <p>原文：Amount of nanoseconds remaining before the next tick should start
     */
    public long getTimeRemaining() {
        return this.timeEnd - System.nanoTime();
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
