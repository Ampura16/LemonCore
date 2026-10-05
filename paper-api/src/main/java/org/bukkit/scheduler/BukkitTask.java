package org.bukkit.scheduler;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表由调度器执行的任务.
 * <p>原文：
 * Represents a task being executed by the scheduler
 */
public interface BukkitTask {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个任务的任务id.
     * <p>原文：
     * Returns the taskId for the task.
     *
     * @return 任务id编号
     * <p>原文：Task id number
     */
    public int getTaskId();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回拥有这个任务的插件.
     * <p>原文：
     * Returns the Plugin that owns this task.
     *
     * @return 拥有这个任务的插件
     * <p>原文：The Plugin that owns the task
     */
    @NotNull
    public Plugin getOwner();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果这个任务是同步任务则返回true.
     * <p>原文：
     * Returns true if the Task is a sync task.
     *
     * @return 如果这个任务由主线程运行，则为true
     * <p>原文：true if the task is run by main thread
     */
    public boolean isSync();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 若该任务已被取消则返回true.
     * <p>原文：
     * Returns true if this task has been cancelled.
     *
     * @return 若该任务已被取消则返回true
     * <p>原文：true if the task has been cancelled
     */
    public boolean isCancelled();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试取消该任务.
     * <p>原文：
     * Will attempt to cancel this task.
     */
    public void cancel();
}
