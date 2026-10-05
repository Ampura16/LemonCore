package io.papermc.paper.plugin.lifecycle.event.handler.configuration;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 允许“监视”处理器的事件类型的处理器配置。
 * <p>原文：
 * Handler configuration for event types that allow "monitor" handlers.
 *
 * @param <O> 所需的所属者类型
 * <p>原文：the required owner type
 */
@ApiStatus.NonExtendable
public interface MonitorLifecycleEventHandlerConfiguration<O extends LifecycleEventOwner> extends LifecycleEventHandlerConfiguration<O> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将此处理器配置设为“监视器”。这类处理器最后运行，插件只应使用它来观察之前运行的处理器所做的更改。
     * <p>原文：
     * Sets this handler configuration to be considered a "monitor".
     * These handlers will run last and should only be used by plugins
     * to observe changes from previously run handlers.
     *
     * @return 此配置，用于链式调用
     * <p>原文：this configuration for chaining
     */
    @Contract("-> this")
    MonitorLifecycleEventHandlerConfiguration<O> monitor();
}
