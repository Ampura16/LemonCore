package io.papermc.paper.plugin.lifecycle.event.types;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import io.papermc.paper.plugin.lifecycle.event.handler.LifecycleEventHandler;
import io.papermc.paper.plugin.lifecycle.event.handler.configuration.LifecycleEventHandlerConfiguration;
import io.papermc.paper.plugin.lifecycle.event.handler.configuration.MonitorLifecycleEventHandlerConfiguration;
import io.papermc.paper.plugin.lifecycle.event.handler.configuration.PrioritizedLifecycleEventHandlerConfiguration;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 所有生命周期事件类型的基础类型。与实际事件对象 {@link LifecycleEvent} 不同，这是表示事件类型的对象，用于构造 {@link LifecycleEventHandlerConfiguration} 的子类型以供 {@link LifecycleEventManager} 使用
 * <p>原文：
 * Base type for all types of lifecycle events. Differs from
 * {@link LifecycleEvent} which is the actual event object, whereas
 * this is an object representing the type of the event. Used
 * to construct subtypes of {@link LifecycleEventHandlerConfiguration} for
 * use in {@link LifecycleEventManager}
 *
 * @param <O> 所需的所属者类型
 * <p>原文：the required owner type
 * @param <E> 事件对象类型
 * <p>原文：the event object type
 * @param <C> 配置类型
 * <p>原文：the configuration type
 */
@ApiStatus.NonExtendable
public interface LifecycleEventType<O extends LifecycleEventOwner, E extends LifecycleEvent, C extends LifecycleEventHandlerConfiguration<O>> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取生命周期事件的名称。
     * <p>原文：
     * Gets the name of the lifecycle event.
     *
     * @return 名称
     * <p>原文：the name
     */
    @Contract(pure = true)
    String name();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用指定的处理器为该事件创建配置。
     * <p>原文：
     * Create a configuration for this event with the specified
     * handler.
     *
     * @param handler 事件处理器
     * <p>原文：the event handler
     * @return 新配置
     * <p>原文：a new configuration
     * @see LifecycleEventManager#registerEventHandler(LifecycleEventHandlerConfiguration)
     */
    @Contract("_ -> new")
    C newHandler(LifecycleEventHandler<? super E> handler);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 支持将处理器单独注册为最后运行的“监视器”的生命周期事件类型。适用于插件只想观察其他处理器所做更改的情况。
     * <p>原文：
     * Lifecycle event type that supports separate registration
     * of handlers as "monitors" that are run last. Useful
     * if a plugin wants to only observe the changes other handlers
     * made.
     *
     * @param <O> 所需的所属者类型
     * <p>原文：the required owner type
     * @param <E> 事件对象类型
     * <p>原文：the event object type
     */
    @ApiStatus.NonExtendable
    interface Monitorable<O extends LifecycleEventOwner, E extends LifecycleEvent> extends LifecycleEventType<O, E, MonitorLifecycleEventHandlerConfiguration<O>> {
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 同时支持 {@link Monitorable "monitors"} 和基于具体数值的优先级的生命周期事件类型。
     * <p>原文：
     * Lifecycle event type that supports both {@link Monitorable "monitors"} and
     * specific numeric-based priorities.
     *
     * @param <O> 所需的所属者类型
     * <p>原文：the required owner type
     * @param <E> 事件对象类型
     * <p>原文：the event object type
     */
    @ApiStatus.NonExtendable
    interface Prioritizable<O extends LifecycleEventOwner, E extends LifecycleEvent> extends LifecycleEventType<O, E, PrioritizedLifecycleEventHandlerConfiguration<O>> {
    }
}
