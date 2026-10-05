package io.papermc.paper.plugin.lifecycle.event;

import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 所有生命周期事件的基础类型。
 * <p>
 * 生命周期事件通常在旧事件系统不可用时触发，例如服务器初始化早期。
 * <p>原文：
 * Base type for all Lifecycle Events.
 * <p>
 * Lifecycle events are generally fired when the older
 * event system is not available, like during early
 * server initialization.
 * @see LifecycleEvents
 */
@ApiStatus.NonExtendable
public interface LifecycleEvent {
}
