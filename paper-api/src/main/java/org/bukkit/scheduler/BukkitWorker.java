package org.bukkit.scheduler;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表调度器的worker线程。这给了任务线程对象的信息、任务的所有者和任务id。
 * <p>
 * Workers是用来执行异步任务的。
 * <p>原文：
 * Represents a worker thread for the scheduler. This gives information about
 * the Thread object for the task, owner of the task and the taskId.
 * <p>
 * Workers are used to execute async tasks.
 */
public interface BukkitWorker {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回worker执行的任务的任务id。
     * <p>原文：
     * Returns the taskId for the task being executed by this worker.
     *
     * @return 任务id
     * <p>原文：Task id number
     */
    public int getTaskId();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回拥有这个任务的插件。
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
     * 返回worker的线程。
     * <p>原文：
     * Returns the thread for the worker.
     *
     * @return worker线程对象
     * <p>原文：The Thread object for the worker
     */
    @NotNull
    public Thread getThread();

}
