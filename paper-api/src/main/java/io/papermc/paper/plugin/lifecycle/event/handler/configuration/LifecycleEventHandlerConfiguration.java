package io.papermc.paper.plugin.lifecycle.event.handler.configuration;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import io.papermc.paper.plugin.lifecycle.event.handler.LifecycleEventHandler;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 为生命周期事件构造已配置事件处理器的基础类型。通常通过 {@link io.papermc.paper.plugin.lifecycle.event.types.LifecycleEventType#newHandler(LifecycleEventHandler)} 由 {@link io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents} 中的事件类型创建
 * <p>原文：
 * Base type for constructing configured event handlers for
 * lifecycle events. Usually created via {@link io.papermc.paper.plugin.lifecycle.event.types.LifecycleEventType#newHandler(LifecycleEventHandler)}
 * from event types in {@link io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents}
 *
 * @param <O>
 */
@SuppressWarnings("unused")
@ApiStatus.NonExtendable
public interface LifecycleEventHandlerConfiguration<O extends LifecycleEventOwner> {
}
