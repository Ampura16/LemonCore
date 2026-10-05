package io.papermc.paper.registry.event;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.lifecycle.event.handler.LifecycleEventHandler;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEventType;
import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.event.type.RegistryEntryAddEventType;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 特定注册表的注册表事件提供器。
 * <p>
 * 支持的事件有：
 * <ul>
 * <li>{@link RegistryEntryAddEvent}（通过 {@link #entryAdd()}）</li>
 * <li>{@link RegistryComposeEvent}（通过 {@link #compose()}）</li>
 * </ul>
 * <p>原文：
 * Provider for registry events for a specific registry.
 * <p>
 * Supported events are:
 * <ul>
 *     <li>{@link RegistryEntryAddEvent} (via {@link #entryAdd()})</li>
 *     <li>{@link RegistryComposeEvent} (via {@link #compose()})</li>
 * </ul>
 *
 * @param <T> 注册表条目类型
 * <p>原文：registry entry type
 * @param <B> 注册表条目构建器类型
 * <p>原文：registry entry builder type
 */
@ApiStatus.NonExtendable
public interface RegistryEventProvider<T, B extends RegistryBuilder<T>> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取 {@link RegistryEntryAddEvent} 的事件类型，该事件在对象被添加到注册表之前立即触发。
     * <p>
     * 可在 {@link io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager#registerEventHandler(LifecycleEventType, LifecycleEventHandler)} 中用于注册 {@link RegistryEntryAddEvent} 的处理器。
     * <p>原文：
     * Gets the event type for {@link RegistryEntryAddEvent} which is fired just before
     * an object is added to a registry.
     * <p>
     * Can be used in {@link io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager#registerEventHandler(LifecycleEventType, LifecycleEventHandler)}
     * to register a handler for {@link RegistryEntryAddEvent}.
     *
     * @return 注册表条目添加事件类型
     * <p>原文：the registry entry add event type
     */
    RegistryEntryAddEventType<T, B> entryAdd();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取 {@link RegistryComposeEvent} 的事件类型，该事件在注册表加载完预期元素后触发，允许注册新对象。
     * <p>
     * 可在 {@link io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager#registerEventHandler(LifecycleEventType, LifecycleEventHandler)} 中用于注册 {@link RegistryComposeEvent} 的处理器。
     * <p>原文：
     * Gets the event type for {@link RegistryComposeEvent} which is fired after
     * a registry is loaded of expected elements. It allows for the registration of new objects.
     * <p>
     * Can be used in {@link io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager#registerEventHandler(LifecycleEventType, LifecycleEventHandler)}
     * to register a handler for {@link RegistryComposeEvent}.
     *
     * @return 注册表组合事件类型
     * <p>原文：the registry compose event type
     */
    LifecycleEventType.Prioritizable<BootstrapContext, RegistryComposeEvent<T, B>> compose();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取与此事件类型提供器关联的注册表键。
     * <p>原文：
     * Gets the registry key associated with this event type provider.
     *
     * @return 注册表键
     * <p>原文：the registry key
     */
    RegistryKey<T> registryKey();
}
