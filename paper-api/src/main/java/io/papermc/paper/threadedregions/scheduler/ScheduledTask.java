package io.papermc.paper.threadedregions.scheduler;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示调度到调度器的任务。
 * <p>原文：
 * Represents a task scheduled to a scheduler.
 */
public interface ScheduledTask {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回调度此任务的插件。
     * <p>原文：
     * Returns the plugin that scheduled this task.
     * @return 调度此任务的插件。
     * <p>原文：the plugin that scheduled this task.
     */
    @NotNull Plugin getOwningPlugin();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此任务是否按固定周期执行，而非只执行一次。
     * <p>原文：
     * Returns whether this task executes on a fixed period, as opposed to executing only once.
     * @return 此任务是否按固定周期执行，而不是只执行一次。
     * <p>原文：whether this task executes on a fixed period, as opposed to executing only once.
     */
    boolean isRepeatingTask();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 尝试取消此任务，并返回尝试的结果。无论何种情况，若任务当前正在执行，都不会尝试中止它，但之后的所有执行都会被中止。
     * <p>原文：
     * Attempts to cancel this task, returning the result of the attempt. In all cases, if the task is currently
     * being executed no attempt is made to halt the task, however any executions in the future are halted.
     * @return 取消尝试的结果。
     * <p>原文：the result of the cancellation attempt.
     */
    @NotNull CancelledState cancel();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此任务当前的执行状态。
     * <p>原文：
     * Returns the current execution state of this task.
     * @return 此任务当前的执行状态。
     * <p>原文：the current execution state of this task.
     */
    @NotNull ExecutionState getExecutionState();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回当前执行状态是否为 {@link ExecutionState#CANCELLED} 或 {@link ExecutionState#CANCELLED_RUNNING}。
     * <p>原文：
     * Returns whether the current execution state is {@link ExecutionState#CANCELLED} or {@link ExecutionState#CANCELLED_RUNNING}.
     * @return 当前执行状态是否为 {@link ExecutionState#CANCELLED} 或 {@link ExecutionState#CANCELLED_RUNNING}。
     * <p>原文：whether the current execution state is {@link ExecutionState#CANCELLED} or {@link ExecutionState#CANCELLED_RUNNING}.
     */
    default boolean isCancelled() {
        final ExecutionState state = this.getExecutionState();
        return state == ExecutionState.CANCELLED || state == ExecutionState.CANCELLED_RUNNING;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示尝试取消任务的结果。
     * <p>原文：
     * Represents the result of attempting to cancel a task.
     */
    enum CancelledState {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 任务（无论是否重复）已被调用线程成功取消。任务当前未在执行，将来也不会开始执行。
         * <p>原文：
         * The task (repeating or not) has been successfully cancelled by the caller thread. The task is not executing
         * currently, and it will not begin execution in the future.
         */
        CANCELLED_BY_CALLER,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 任务（无论是否重复）已经被取消。任务当前未在执行，将来也不会开始执行。
         * <p>原文：
         * The task (repeating or not) is already cancelled. The task is not executing currently, and it will not
         * begin execution in the future.
         */
        CANCELLED_ALREADY,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 任务不是重复任务，且由于正在执行而无法取消。
         * <p>原文：
         * The task is not a repeating task, and could not be cancelled because the task is being executed.
         */
        RUNNING,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 任务不是重复任务，且由于已执行完毕而无法取消。
         * <p>原文：
         * The task is not a repeating task, and could not be cancelled because the task has already finished execution.
         */
        ALREADY_EXECUTED,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 调用线程成功阻止了重复任务之后的执行，但该任务当前正在执行。
         * <p>原文：
         * The caller thread successfully stopped future executions of a repeating task, but the task is currently
         * being executed.
         */
        NEXT_RUNS_CANCELLED,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 重复任务之后的执行已被取消，但该任务当前正在执行。
         * <p>原文：
         * The repeating task's future executions are cancelled already, but the task is currently
         * being executed.
         */
        NEXT_RUNS_CANCELLED_ALREADY,
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示任务当前的执行状态。
     * <p>原文：
     * Represents the current execution state of the task.
     */
    enum ExecutionState {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 任务当前未在执行，但将来可能开始执行。
         * <p>原文：
         * The task is currently not executing, but may begin execution in the future.
         */
        IDLE,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 任务当前正在执行。
         * <p>原文：
         * The task is currently executing.
         */
        RUNNING,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 任务不是重复任务，且已执行完毕。
         * <p>原文：
         * The task is not repeating, and the task finished executing.
         */
        FINISHED,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 任务未在执行，将来也不会开始执行。如果该任务不是重复任务，则它从未被执行过。
         * <p>原文：
         * The task is not executing and will not begin execution in the future. If this task is not repeating, then
         * this task was never executed.
         */
        CANCELLED,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 任务是重复任务且当前正在执行，但之后的执行已被取消，不会再发生。
         * <p>原文：
         * The task is repeating and currently executing, but future executions are cancelled and will not occur.
         */
        CANCELLED_RUNNING;
    }
}
