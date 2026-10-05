package com.destroystokyo.paper.exception;

import org.bukkit.scheduler.BukkitTask;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当插件的调度器因异常失败时抛出
 * <p>原文：
 * Thrown when a plugin's scheduler fails with an exception
 */
public class ServerSchedulerException extends ServerPluginException {

    private final BukkitTask task;

    public ServerSchedulerException(String message, Throwable cause, BukkitTask task) {
        super(message, cause, task.getOwner());
        this.task = checkNotNull(task, "task");
    }

    public ServerSchedulerException(Throwable cause, BukkitTask task) {
        super(cause, task.getOwner());
        this.task = checkNotNull(task, "task");
    }

    protected ServerSchedulerException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace, BukkitTask task) {
        super(message, cause, enableSuppression, writableStackTrace, task.getOwner());
        this.task = checkNotNull(task, "task");
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取抛出异常的任务
     * <p>原文：
     * Gets the task which threw the exception
     *
     * @return 抛出异常的任务
     * <p>原文：exception throwing task
     */
    public BukkitTask getTask() {
        return task;
    }
}
