package io.papermc.paper.threadedregions.scheduler;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 全局区域任务调度器可用于调度将在全局区域上执行的任务。
 * <p>
 * 全局区域负责维护世界日间时间、世界游戏时间、天气循环、睡觉跳过夜晚、为控制台执行命令，以及其他不属于任何特定区域的杂项任务。
 * </p>
 * <p>原文：
 * The global region task scheduler may be used to schedule tasks that will execute on the global region.
 * <p>
 * The global region is responsible for maintaining world day time, world game time, weather cycle,
 * sleep night skipping, executing commands for console, and other misc. tasks that do not belong to any specific region.
 * </p>
 */
public interface GlobalRegionScheduler {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度一个在全局区域上执行的任务。
     * <p>原文：
     * Schedules a task to be executed on the global region.
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param run 要执行的任务
     * <p>原文：The task to execute
     */
    void execute(@NotNull Plugin plugin, @NotNull Runnable run);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度一个任务，在下一刻于全局区域上执行。
     * <p>原文：
     * Schedules a task to be executed on the global region on the next tick.
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param task 要执行的任务
     * <p>原文：The task to execute
     * @return 表示已调度任务的 {@link ScheduledTask}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task.
     */
    @NotNull ScheduledTask run(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度一个任务，在指定的刻延迟后在全局区域上执行。
     * <p>原文：
     * Schedules a task to be executed on the global region after the specified delay in ticks.
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param task 要执行的任务
     * <p>原文：The task to execute
     * @param delayTicks 延迟（刻）。
     * <p>原文：The delay, in ticks.
     * @return 表示已调度任务的 {@link ScheduledTask}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task.
     */
    @NotNull ScheduledTask runDelayed(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task, long delayTicks);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度一个重复任务，在初始延迟后以指定周期在全局区域上执行。
     * <p>原文：
     * Schedules a repeating task to be executed on the global region after the initial delay with the
     * specified period.
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param task 要执行的任务
     * <p>原文：The task to execute
     * @param initialDelayTicks 初始延迟（刻）。
     * <p>原文：The initial delay, in ticks.
     * @param periodTicks 周期（刻）。
     * <p>原文：The period, in ticks.
     * @return 表示已调度任务的 {@link ScheduledTask}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task.
     */
    @NotNull ScheduledTask runAtFixedRate(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
                                          long initialDelayTicks, long periodTicks);

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
