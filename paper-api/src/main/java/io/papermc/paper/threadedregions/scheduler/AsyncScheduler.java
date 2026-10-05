package io.papermc.paper.threadedregions.scheduler;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 插件可用于调度任务、使其脱离服务器刻处理异步执行的调度器。
 * <p>原文：
 * Scheduler that may be used by plugins to schedule tasks to execute asynchronously from the server tick process.
 */
public interface AsyncScheduler {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度指定任务立即异步执行。
     * <p>原文：
     * Schedules the specified task to be executed asynchronously immediately.
     * @param plugin 拥有指定任务的插件。
     * <p>原文：Plugin which owns the specified task.
     * @param task 指定的任务。
     * <p>原文：Specified task.
     * @return 表示已调度任务的 {@link ScheduledTask}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task.
     */
    @NotNull ScheduledTask runNow(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度指定任务，在时间延迟过后异步执行。
     * <p>原文：
     * Schedules the specified task to be executed asynchronously after the time delay has passed.
     * @param plugin 拥有指定任务的插件。
     * <p>原文：Plugin which owns the specified task.
     * @param task 指定的任务。
     * <p>原文：Specified task.
     * @param delay 任务执行前需经过的延迟时间。
     * <p>原文：The time delay to pass before the task should be executed.
     * @param unit 延迟时间的时间单位。
     * <p>原文：The time unit for the time delay.
     * @return 表示已调度任务的 {@link ScheduledTask}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task.
     */
    @NotNull ScheduledTask runDelayed(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task, long delay,
                                      @NotNull TimeUnit unit);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度指定任务，在初始延迟过后异步执行，之后按指定周期定期执行。
     * <p>原文：
     * Schedules the specified task to be executed asynchronously after the initial delay has passed,
     * and then periodically executed with the specified period.
     * @param plugin 拥有指定任务的插件。
     * <p>原文：Plugin which owns the specified task.
     * @param task 指定的任务。
     * <p>原文：Specified task.
     * @param initialDelay 任务首次执行前需经过的延迟时间。
     * <p>原文：The time delay to pass before the first execution of the task.
     * @param period 任务首次执行之后两次执行之间的时间间隔。
     * <p>原文：The time between task executions after the first execution of the task.
     * @param unit 初始延迟和周期的时间单位。
     * <p>原文：The time unit for the initial delay and period.
     * @return 表示已调度任务的 {@link ScheduledTask}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task.
     */
    @NotNull ScheduledTask runAtFixedRate(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
                                          long initialDelay, long period, @NotNull TimeUnit unit);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 尝试取消指定插件调度的所有任务。
     * <p>原文：
     * Attempts to cancel all tasks scheduled by the specified plugin.
     * @param plugin 指定的插件。
     * <p>原文：Specified plugin.
     */
    void cancelTasks(@NotNull Plugin plugin);
}
