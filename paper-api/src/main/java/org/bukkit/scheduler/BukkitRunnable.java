package org.bukkit.scheduler;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 这个类提供了简单的处理执行任务的方法.
 * <p>原文：
 * This class is provided as an easy way to handle scheduling tasks.
 */
public abstract class BukkitRunnable implements Runnable {
    private BukkitTask task;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 若该任务被取消返回true.
     * <p>原文：
     * Returns true if this task has been cancelled.
     *
     * @return 若该任务被取消返回true
     * <p>原文：true if the task has been cancelled
     * @throws IllegalStateException 若任务还未被调度
     * <p>原文：if task was not scheduled yet
     */
    public synchronized boolean isCancelled() throws IllegalStateException {
        checkScheduled();
        return task.isCancelled();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试取消这个任务
     * <p>原文：
     * Attempts to cancel this task.
     *
     * @throws IllegalStateException 如果任务还没有被执行
     * <p>原文：if task was not scheduled yet
     */
    public synchronized void cancel() throws IllegalStateException {
        Bukkit.getScheduler().cancelTask(getTaskId());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让Bukkit任务调度器在下一个tick运行任务.
     * <p>原文：
     * Schedules this in the Bukkit scheduler to run on next tick.
     *
     * @param plugin 创建任务的插件
     * <p>原文：the reference to the plugin scheduling task
     * @return 包含任务id的{@link BukkitTask}
     * <p>原文：a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果plugin为null
     * <p>原文：if plugin is null
     * @throws IllegalStateException 如果任务已经被执行/准备执行
     * <p>原文：if this was already scheduled
     * @see BukkitScheduler#runTask(Plugin, Runnable)
     */
    @NotNull
    public synchronized BukkitTask runTask(@NotNull Plugin plugin) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(Bukkit.getScheduler().runTask(plugin, (Runnable) this));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在Bukkit任务调度器异步运行这个任务.
     * <p>
     * <b>异步任务不能访问任何Bukkit里的API.应着重保证异步任务的线程安全.</b>
     * <p>原文：
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules this in the Bukkit scheduler to run asynchronously.
     *
     * @param plugin 创建任务的插件
     * <p>原文：the reference to the plugin scheduling task
     * @return 包含任务id的{@link BukkitTask}
     * <p>原文：a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果plugin为null
     * <p>原文：if plugin is null
     * @throws IllegalStateException 如果这个任务已经被执行/准备执行
     * <p>原文：if this was already scheduled
     * @see BukkitScheduler#runTaskAsynchronously(Plugin, Runnable)
     */
    @NotNull
    public synchronized BukkitTask runTaskAsynchronously(@NotNull Plugin plugin) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(Bukkit.getScheduler().runTaskAsynchronously(plugin, (Runnable) this));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定tick后执行该任务.
     * <p>原文：
     * Schedules this to run after the specified number of server ticks.
     *
     * @param plugin 创建任务的插件
     * <p>原文：the reference to the plugin scheduling task
     * @param delay 在任务执行前等待的时间/tick
     * <p>原文：the ticks to wait before running the task
     * @return 包含任务id的{@link BukkitTask}
     * <p>原文：a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果plugin为null
     * <p>原文：if plugin is null
     * @throws IllegalStateException 如果这个任务已经被执行/准备执行
     * <p>原文：if this was already scheduled
     * @see BukkitScheduler#runTaskLater(Plugin, Runnable, long)
     */
    @NotNull
    public synchronized BukkitTask runTaskLater(@NotNull Plugin plugin, long delay) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(Bukkit.getScheduler().runTaskLater(plugin, (Runnable) this, delay));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让Bukkit任务调度器在指定的tick后异步执行这个任务.
     * <p>
     * <b>异步任务不能访问任何Bukkit里的API.应着重保证异步任务的线程安全.</b>
     * <p>原文：
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules this to run asynchronously after the specified number of
     * server ticks.
     *
     * @param plugin 创建任务的插件
     * <p>原文：the reference to the plugin scheduling task
     * @param delay 在任务执行前等待的时间/tick
     * <p>原文：the ticks to wait before running the task
     * @return 包含任务id的BukkitTask
     * <p>原文：a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果plugin为null
     * <p>原文：if plugin is null
     * @throws IllegalStateException 如果这个任务已经被执行/准备执行
     * <p>原文：if this was already scheduled
     * @see BukkitScheduler#runTaskLaterAsynchronously(Plugin, Runnable, long)
     */
    @NotNull
    public synchronized BukkitTask runTaskLaterAsynchronously(@NotNull Plugin plugin, long delay) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, (Runnable) this, delay));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定时间后开始以指定的间隔不断执行任务.
     * <p>原文：
     * Schedules this to repeatedly run until cancelled, starting after the
     * specified number of server ticks.
     *
     * @param plugin 创建任务的插件
     * <p>原文：the reference to the plugin scheduling task
     * @param delay 在任务执行前等待的时间/tick
     * <p>原文：the ticks to wait before running the task
     * @param period 重复执行任务之间的时间间隔/tick
     * <p>原文：the ticks to wait between runs
     * @return 包含任务id的BukkitTask
     * <p>原文：a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * <p>原文：if plugin is null
     * @throws IllegalStateException 如果这个任务已经被执行/准备执行
     * <p>原文：if this was already scheduled
     * @see BukkitScheduler#runTaskTimer(Plugin, Runnable, long, long)
     */
    @NotNull
    public synchronized BukkitTask runTaskTimer(@NotNull Plugin plugin, long delay, long period) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(Bukkit.getScheduler().runTaskTimer(plugin, (Runnable) this, delay, period));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让Bukkit任务调度器在指定的tick后开始异步的以指定的间隔不断执行任务.
     * <p>
     * <b>异步任务不能访问任何Bukkit里的API.应着重保证异步任务的线程安全.</b>
     * <p>原文：
     * <b>Asynchronous tasks should never access any API in Bukkit. Great care
     * should be taken to assure the thread-safety of asynchronous tasks.</b>
     * <p>
     * Schedules this to repeatedly run asynchronously until cancelled,
     * starting after the specified number of server ticks.
     *
     * @param plugin 创建任务的插件
     * <p>原文：the reference to the plugin scheduling task
     * @param delay 在任务执行前等待的时间/tick
     * <p>原文：the ticks to wait before running the task for the first
     *     time
     * @param period 重复执行任务之间的时间间隔/tick
     * <p>原文：the ticks to wait between runs
     * @return 包含任务id的BukkitTask
     * <p>原文：a BukkitTask that contains the id number
     * @throws IllegalArgumentException 如果插件为null
     * <p>原文：if plugin is null
     * @throws IllegalStateException 如果这个任务已经被执行/准备执行
     * <p>原文：if this was already scheduled
     * @see BukkitScheduler#runTaskTimerAsynchronously(Plugin, Runnable, long,
     *     long)
     */
    @NotNull
    public synchronized BukkitTask runTaskTimerAsynchronously(@NotNull Plugin plugin, long delay, long period) throws IllegalArgumentException, IllegalStateException {
        checkNotYetScheduled();
        return setupTask(Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, (Runnable) this, delay, period));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个已执行的任务的任务id.
     * <p>原文：
     * Gets the task id for this runnable.
     *
     * @return 这个任务的任务id
     * <p>原文：the task id that this runnable was scheduled as
     * @throws IllegalStateException 如果这个任务还没有被执行
     * <p>原文：if task was not scheduled yet
     */
    public synchronized int getTaskId() throws IllegalStateException {
        checkScheduled();
        return task.getTaskId();
    }

    private void checkScheduled() {
        if (task == null) {
            throw new IllegalStateException("Not scheduled yet");
        }
    }

    private void checkNotYetScheduled() {
        if (task != null) {
            throw new IllegalStateException("Already scheduled as " + task.getTaskId());
        }
    }

    @NotNull
    private BukkitTask setupTask(@NotNull final BukkitTask task) {
        this.task = task;
        return task;
    }
}
