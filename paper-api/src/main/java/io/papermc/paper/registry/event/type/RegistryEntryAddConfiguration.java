package io.papermc.paper.registry.event.type;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.lifecycle.event.handler.configuration.PrioritizedLifecycleEventHandlerConfiguration;
import io.papermc.paper.registry.TypedKey;
import java.util.function.Predicate;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link io.papermc.paper.registry.event.RegistryEntryAddEvent} 的特定配置。
 * <p>原文：
 * Specific configuration for {@link io.papermc.paper.registry.event.RegistryEntryAddEvent}s.
 *
 * @param <T> 注册表条目类型
 * <p>原文：registry entry type
 */
@ApiStatus.NonExtendable
public interface RegistryEntryAddConfiguration<T> extends PrioritizedLifecycleEventHandlerConfiguration<BootstrapContext> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 仅当正在添加的值与指定键匹配时才调用处理器。
     * <p>原文：
     * Only call the handler if the value being added matches the specified key.
     *
     * @param key 要匹配的键
     * <p>原文：the key to match
     * @return 此配置
     * <p>原文：this configuration
     */
    @Contract(value = "_ -> this", mutates = "this")
    default RegistryEntryAddConfiguration<T> filter(final TypedKey<T> key) {
        return this.filter(key::equals);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 仅当正在添加的值通过所提供的过滤器时才调用处理器。
     * <p>原文：
     * Only call the handler if the value being added passes the provided filter.
     *
     * @param filter 用于匹配键的谓词
     * <p>原文：the predicate to match the key against
     * @return 此配置
     * <p>原文：this configuration
     */
    @Contract(value = "_ -> this", mutates = "this")
    RegistryEntryAddConfiguration<T> filter(Predicate<TypedKey<T>> filter);

    @Override
    RegistryEntryAddConfiguration<T> priority(int priority);

    @Override
    RegistryEntryAddConfiguration<T> monitor();
}
