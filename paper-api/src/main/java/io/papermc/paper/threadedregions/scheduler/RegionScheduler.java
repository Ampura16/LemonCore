package io.papermc.paper.threadedregions.scheduler;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

import java.util.function.Consumer;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 区域任务调度器可用于按位置调度任务，使其在拥有该位置的区域上执行。
 * <p>
 * <b>注意</b>：使用区域调度器为实体调度任务是完全不恰当的。如果你希望调度任务对实体执行操作，应使用 {@link Entity#getScheduler()}，因为实体调度器会在实体被传送时“跟随”实体，而区域任务调度器不会。
 * </p>
 * <p>原文：
 * The region task scheduler can be used to schedule tasks by location to be executed on the region which owns the location.
 * <p>
 * <b>Note</b>: It is entirely inappropriate to use the region scheduler to schedule tasks for entities.
 * If you wish to schedule tasks to perform actions on entities, you should be using {@link Entity#getScheduler()}
 * as the entity scheduler will "follow" an entity if it is teleported, whereas the region task scheduler
 * will not.
 * </p>
 */
public interface RegionScheduler {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度一个在拥有该位置的区域上执行的任务。
     * <p>原文：
     * Schedules a task to be executed on the region which owns the location.
     *
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param world 拥有该任务的区域所在的世界
     * <p>原文：The world of the region that owns the task
     * @param chunkX 拥有该任务的区域的区块 X 坐标
     * <p>原文：The chunk X coordinate of the region that owns the task
     * @param chunkZ 拥有该任务的区域的区块 Z 坐标
     * <p>原文：The chunk Z coordinate of the region that owns the task
     * @param run 要执行的任务
     * <p>原文：The task to execute
     */
    void execute(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ, @NotNull Runnable run);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度一个在拥有该位置的区域上执行的任务。
     * <p>原文：
     * Schedules a task to be executed on the region which owns the location.
     *
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param location 执行的区域应拥有的位置
     * <p>原文：The location at which the region executing should own
     * @param run 要执行的任务
     * <p>原文：The task to execute
     */
    default void execute(@NotNull Plugin plugin, @NotNull Location location, @NotNull Runnable run) {
        this.execute(plugin, location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4, run);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度一个任务，在下一刻于拥有该位置的区域上执行。
     * <p>原文：
     * Schedules a task to be executed on the region which owns the location on the next tick.
     *
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param world 拥有该任务的区域所在的世界
     * <p>原文：The world of the region that owns the task
     * @param chunkX 拥有该任务的区域的区块 X 坐标
     * <p>原文：The chunk X coordinate of the region that owns the task
     * @param chunkZ 拥有该任务的区域的区块 Z 坐标
     * <p>原文：The chunk Z coordinate of the region that owns the task
     * @param task 要执行的任务
     * <p>原文：The task to execute
     * @return 表示已调度任务的 {@link ScheduledTask}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task.
     */
    @NotNull ScheduledTask run(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ, @NotNull Consumer<ScheduledTask> task);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度一个任务，在下一刻于拥有该位置的区域上执行。
     * <p>原文：
     * Schedules a task to be executed on the region which owns the location on the next tick.
     *
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param location 执行的区域应拥有的位置
     * <p>原文：The location at which the region executing should own
     * @param task 要执行的任务
     * <p>原文：The task to execute
     * @return 表示已调度任务的 {@link ScheduledTask}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task.
     */
    default @NotNull ScheduledTask run(@NotNull Plugin plugin, @NotNull Location location, @NotNull Consumer<ScheduledTask> task) {
        return this.run(plugin, location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4, task);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度一个任务，在指定的刻延迟后在拥有该位置的区域上执行。
     * <p>原文：
     * Schedules a task to be executed on the region which owns the location after the specified delay in ticks.
     *
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param world 拥有该任务的区域所在的世界
     * <p>原文：The world of the region that owns the task
     * @param chunkX 拥有该任务的区域的区块 X 坐标
     * <p>原文：The chunk X coordinate of the region that owns the task
     * @param chunkZ 拥有该任务的区域的区块 Z 坐标
     * <p>原文：The chunk Z coordinate of the region that owns the task
     * @param task 要执行的任务
     * <p>原文：The task to execute
     * @param delayTicks 延迟（刻）。
     * <p>原文：The delay, in ticks.
     * @return 表示已调度任务的 {@link ScheduledTask}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task.
     */
    @NotNull ScheduledTask runDelayed(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ, @NotNull Consumer<ScheduledTask> task,
                                      long delayTicks);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度一个任务，在指定的刻延迟后在拥有该位置的区域上执行。
     * <p>原文：
     * Schedules a task to be executed on the region which owns the location after the specified delay in ticks.
     *
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param location 执行的区域应拥有的位置
     * <p>原文：The location at which the region executing should own
     * @param task 要执行的任务
     * <p>原文：The task to execute
     * @param delayTicks 延迟（刻）。
     * <p>原文：The delay, in ticks.
     * @return 表示已调度任务的 {@link ScheduledTask}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task.
     */
    default @NotNull ScheduledTask runDelayed(@NotNull Plugin plugin, @NotNull Location location, @NotNull Consumer<ScheduledTask> task,
                                              long delayTicks) {
        return this.runDelayed(plugin, location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4, task, delayTicks);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度一个重复任务，在初始延迟后以指定周期在拥有该位置的区域上执行。
     * <p>原文：
     * Schedules a repeating task to be executed on the region which owns the location after the initial delay with the
     * specified period.
     *
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param world 拥有该任务的区域所在的世界
     * <p>原文：The world of the region that owns the task
     * @param chunkX 拥有该任务的区域的区块 X 坐标
     * <p>原文：The chunk X coordinate of the region that owns the task
     * @param chunkZ 拥有该任务的区域的区块 Z 坐标
     * <p>原文：The chunk Z coordinate of the region that owns the task
     * @param task 要执行的任务
     * <p>原文：The task to execute
     * @param initialDelayTicks 初始延迟（刻）。
     * <p>原文：The initial delay, in ticks.
     * @param periodTicks 周期（刻）。
     * <p>原文：The period, in ticks.
     * @return 表示已调度任务的 {@link ScheduledTask}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task.
     */
    @NotNull ScheduledTask runAtFixedRate(@NotNull Plugin plugin, @NotNull World world, int chunkX, int chunkZ, @NotNull Consumer<ScheduledTask> task,
                                          long initialDelayTicks, long periodTicks);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度一个重复任务，在初始延迟后以指定周期在拥有该位置的区域上执行。
     * <p>原文：
     * Schedules a repeating task to be executed on the region which owns the location after the initial delay with the
     * specified period.
     *
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param location 执行的区域应拥有的位置
     * <p>原文：The location at which the region executing should own
     * @param task 要执行的任务
     * <p>原文：The task to execute
     * @param initialDelayTicks 初始延迟（刻）。
     * <p>原文：The initial delay, in ticks.
     * @param periodTicks 周期（刻）。
     * <p>原文：The period, in ticks.
     * @return 表示已调度任务的 {@link ScheduledTask}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task.
     */
    default @NotNull ScheduledTask runAtFixedRate(@NotNull Plugin plugin, @NotNull Location location, @NotNull Consumer<ScheduledTask> task,
                                                  long initialDelayTicks, long periodTicks) {
        return this.runAtFixedRate(plugin, location.getWorld(), location.getBlockX() >> 4, location.getBlockZ() >> 4, task, initialDelayTicks, periodTicks);
    }
}
