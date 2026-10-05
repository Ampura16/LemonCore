package io.papermc.paper.plugin.lifecycle.event.handler.configuration;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 同时允许“监视”处理器和带优先级处理器的处理器配置。默认优先级为 0。
 * <p>原文：
 * Handler configuration that allows both "monitor" and prioritized handlers.
 * The default priority is 0.
 *
 * @param <O> 所需的所属者类型
 * <p>原文：the required owner type
 */
@ApiStatus.NonExtendable
public interface PrioritizedLifecycleEventHandlerConfiguration<O extends LifecycleEventOwner> extends LifecycleEventHandlerConfiguration<O> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此处理器的优先级。会重置之前对 {@link #monitor()} 的所有调用。数值越小，处理器运行越早。
     * <p>原文：
     * Sets the priority for this handler. Resets
     * all previous calls to {@link #monitor()}. A
     * lower numeric value correlates to the handler
     * being run earlier.
     *
     * @param priority 数值优先级
     * <p>原文：the numerical priority
     * @return 此配置，用于链式调用
     * <p>原文：this configuration for chaining
     */
    @Contract("_ -> this")
    PrioritizedLifecycleEventHandlerConfiguration<O> priority(int priority);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将此处理器配置设为“监视器”。这类处理器最后运行，插件只应使用它来观察之前运行的处理器所做的任何更改。
     * <p>原文：
     * Sets this handler configuration to be considered a "monitor".
     * These handlers will run last and should only be used by plugins
     * to observe any changes from previously ran handlers.
     *
     * @return 此配置，用于链式调用
     * <p>原文：this configuration for chaining
     */
    @Contract("-> this")
    PrioritizedLifecycleEventHandlerConfiguration<O> monitor();

}
