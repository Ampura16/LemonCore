package io.papermc.paper.threadedregions.scheduler;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Consumer;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 实体可能以任意刻延迟在世界间移动、对玩家临时移除（如终末之诗）、从世界状态中部分移除（如处于非活动但未移除）、在不同刻区域间传送、在世界间传送，甚至被完全从服务器移除。实体状态的这种不确定性使得调度任务时难以避免上述任一状态导致的未定义行为。
 * <p>
 * 该类旨在消除这些状态：它提供的接口只在实体位于某个世界中时、在该区域的所属线程上运行任务，并提供当前的 Entity 对象。调度器还允许任务提供一个“退役”回调，若实体在已调度任务执行前被移除，将调用该回调。该调度器完全线程安全，可以从任意线程上下文调度任务。调度器还会正确指示任务是否调度成功（即调度器未退役），因此调度任务的代码可以知道给定回调最终是否会被调用——这对非主线程上下文可能至关重要。
 * </p>
 * <p>原文：
 * An entity can move between worlds with an arbitrary tick delay, be temporarily removed
 * for players (i.e end credits), be partially removed from world state (i.e inactive but not removed),
 * teleport between ticking regions, teleport between worlds, and even be removed entirely from the server.
 * The uncertainty of an entity's state can make it difficult to schedule tasks without worrying about undefined
 * behaviors resulting from any of the states listed previously.
 *
 * <p>
 * This class is designed to eliminate those states by providing an interface to run tasks only when an entity
 * is contained in a world, on the owning thread for the region, and by providing the current Entity object.
 * The scheduler also allows a task to provide a callback, the "retired" callback, that will be invoked
 * if the entity is removed before a task that was scheduled could be executed. The scheduler is also
 * completely thread-safe, allowing tasks to be scheduled from any thread context. The scheduler also indicates
 * properly whether a task was scheduled successfully (i.e scheduler not retired), thus the code scheduling any task
 * knows whether the given callbacks will be invoked eventually or not - which may be critical for off-thread
 * contexts.
 * </p>
 */
public interface EntityScheduler {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 以给定延迟调度任务。如果由于调度器已退役（实体已移除）而调度失败，则返回 {@code false}。否则，要么在指定延迟后调用运行回调，要么在调度器退役时调用退役回调。注意，退役回调是在关键代码中调用的，因此不应尝试移除该实体、移除其他实体、加载区块、加载世界、修改票据等级等。
     * <p>
     * 保证运行回调和退役回调都在拥有该实体的区域上调用。
     * </p>
     * <p>原文：
     * Schedules a task with the given delay. If the task failed to schedule because the scheduler is retired (entity
     * removed), then returns {@code false}. Otherwise, either the run callback will be invoked after the specified delay,
     * or the retired callback will be invoked if the scheduler is retired.
     * Note that the retired callback is invoked in critical code, so it should not attempt to remove the entity, remove
     * other entities, load chunks, load worlds, modify ticket levels, etc.
     *
     * <p>
     * It is guaranteed that the run and retired callback are invoked on the region which owns the entity.
     * </p>
     * @param run 在指定延迟后运行的回调，不能为 null。
     * <p>原文：The callback to run after the specified delay, may not be null.
     * @param retired 如果实体在运行回调被调用之前退役，则运行的退役回调，可以为 null。
     * <p>原文：Retire callback to run if the entity is retired before the run callback can be invoked, may be null.
     * @param delay 调用运行回调前的延迟（刻）。任何小于 1 的值都被视为 1。
     * <p>原文：The delay in ticks before the run callback is invoked. Any value less-than 1 is treated as 1.
     * @return 如果任务已被调度则为 {@code true}，这意味着运行函数或退役函数之一将被调用（但绝不会两者都调用）；或为 {@code false}，表示由于调度器已退役，运行函数和退役函数都不会被调用。
     * <p>原文：{@code true} if the task was scheduled, which means that either the run function or the retired function
     *         will be invoked (but never both), or {@code false} indicating neither the run nor retired function will be invoked
     *         since the scheduler has been retired.
     */
    boolean execute(@NotNull Plugin plugin, @NotNull Runnable run, @Nullable Runnable retired, long delay);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 调度一个在下一刻执行的任务。如果由于调度器已退役（实体已移除）而调度失败，则返回 {@code null}。否则，要么在指定延迟后调用任务回调，要么在调度器退役时调用退役回调。注意，退役回调是在关键代码中调用的，因此不应尝试移除该实体、移除其他实体、加载区块、加载世界、修改票据等级等。
     * <p>
     * 保证任务回调和退役回调都在拥有该实体的区域上调用。
     * </p>
     * <p>原文：
     * Schedules a task to execute on the next tick. If the task failed to schedule because the scheduler is retired (entity
     * removed), then returns {@code null}. Otherwise, either the task callback will be invoked after the specified delay,
     * or the retired callback will be invoked if the scheduler is retired.
     * Note that the retired callback is invoked in critical code, so it should not attempt to remove the entity, remove
     * other entities, load chunks, load worlds, modify ticket levels, etc.
     *
     * <p>
     * It is guaranteed that the task and retired callback are invoked on the region which owns the entity.
     * </p>
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param task 要执行的任务
     * <p>原文：The task to execute
     * @param retired 如果实体在运行回调被调用之前退役，则运行的退役回调，可以为 null。
     * <p>原文：Retire callback to run if the entity is retired before the run callback can be invoked, may be null.
     * @return 表示已调度任务的 {@link ScheduledTask}；如果实体已被移除则为 {@code null}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task, or {@code null} if the entity has been removed.
     */
    @Nullable ScheduledTask run(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
                                @Nullable Runnable retired);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 以给定延迟调度任务。如果由于调度器已退役（实体已移除）而调度失败，则返回 {@code null}。否则，要么在指定延迟后调用任务回调，要么在调度器退役时调用退役回调。注意，退役回调是在关键代码中调用的，因此不应尝试移除该实体、移除其他实体、加载区块、加载世界、修改票据等级等。
     * <p>
     * 保证任务回调和退役回调都在拥有该实体的区域上调用。
     * </p>
     * <p>原文：
     * Schedules a task with the given delay. If the task failed to schedule because the scheduler is retired (entity
     * removed), then returns {@code null}. Otherwise, either the task callback will be invoked after the specified delay,
     * or the retired callback will be invoked if the scheduler is retired.
     * Note that the retired callback is invoked in critical code, so it should not attempt to remove the entity, remove
     * other entities, load chunks, load worlds, modify ticket levels, etc.
     *
     * <p>
     * It is guaranteed that the task and retired callback are invoked on the region which owns the entity.
     * </p>
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param task 要执行的任务
     * <p>原文：The task to execute
     * @param retired 如果实体在运行回调被调用之前退役，则运行的退役回调，可以为 null。
     * <p>原文：Retire callback to run if the entity is retired before the run callback can be invoked, may be null.
     * @param delayTicks 延迟（刻）。
     * <p>原文：The delay, in ticks.
     * @return 表示已调度任务的 {@link ScheduledTask}；如果实体已被移除则为 {@code null}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task, or {@code null} if the entity has been removed.
     */
    @Nullable ScheduledTask runDelayed(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
                                       @Nullable Runnable retired, long delayTicks);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 以给定的延迟和周期调度重复任务。如果由于调度器已退役（实体已移除）而调度失败，则返回 {@code null}。否则，要么在指定延迟后调用任务回调，要么在调度器退役时调用退役回调。注意，退役回调是在关键代码中调用的，因此不应尝试移除该实体、移除其他实体、加载区块、加载世界、修改票据等级等。
     * <p>
     * 保证任务回调和退役回调都在拥有该实体的区域上调用。
     * </p>
     * <p>原文：
     * Schedules a repeating task with the given delay and period. If the task failed to schedule because the scheduler
     * is retired (entity removed), then returns {@code null}. Otherwise, either the task callback will be invoked after
     * the specified delay, or the retired callback will be invoked if the scheduler is retired.
     * Note that the retired callback is invoked in critical code, so it should not attempt to remove the entity, remove
     * other entities, load chunks, load worlds, modify ticket levels, etc.
     *
     * <p>
     * It is guaranteed that the task and retired callback are invoked on the region which owns the entity.
     * </p>
     * @param plugin 拥有该任务的插件
     * <p>原文：The plugin that owns the task
     * @param task 要执行的任务
     * <p>原文：The task to execute
     * @param retired 如果实体在运行回调被调用之前退役，则运行的退役回调，可以为 null。
     * <p>原文：Retire callback to run if the entity is retired before the run callback can be invoked, may be null.
     * @param initialDelayTicks 初始延迟（刻）。
     * <p>原文：The initial delay, in ticks.
     * @param periodTicks 周期（刻）。
     * <p>原文：The period, in ticks.
     * @return 表示已调度任务的 {@link ScheduledTask}；如果实体已被移除则为 {@code null}。
     * <p>原文：The {@link ScheduledTask} that represents the scheduled task, or {@code null} if the entity has been removed.
     */
    @Nullable ScheduledTask runAtFixedRate(@NotNull Plugin plugin, @NotNull Consumer<ScheduledTask> task,
                                           @Nullable Runnable retired, long initialDelayTicks, long periodTicks);
}
