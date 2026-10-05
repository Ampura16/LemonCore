package io.papermc.paper.registry.event.type;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEventType;
import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.event.RegistryEntryAddEvent;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link RegistryEntryAddEvent} 的生命周期事件类型。
 * <p>原文：
 * Lifecycle event type for {@link RegistryEntryAddEvent}s.
 *
 * @param <T> 注册表条目类型
 * <p>原文：registry entry type
 * @param <B> 注册表条目构建器类型
 * <p>原文：registry entry builder type
 */
@ApiStatus.NonExtendable
public interface RegistryEntryAddEventType<T, B extends RegistryBuilder<T>> extends LifecycleEventType<BootstrapContext, RegistryEntryAddEvent<T, B>, RegistryEntryAddConfiguration<T>> {
}
