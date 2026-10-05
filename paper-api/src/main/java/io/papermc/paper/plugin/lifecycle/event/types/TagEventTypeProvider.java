package io.papermc.paper.plugin.lifecycle.event.types;

import io.papermc.paper.plugin.bootstrap.BootstrapContext;
import io.papermc.paper.plugin.lifecycle.event.registrar.ReloadableRegistrarEvent;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.tag.PostFlattenTagRegistrar;
import io.papermc.paper.tag.PreFlattenTagRegistrar;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 提供标签注册的事件类型。
 * <p>原文：
 * Provides event types for tag registration.
 *
 * @see PreFlattenTagRegistrar
 * @see PostFlattenTagRegistrar
 */
@ApiStatus.NonExtendable
public interface TagEventTypeProvider {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取标签展开前可设置优先级、可重载的注册器事件。
     * <p>原文：
     * Get a prioritizable, reloadable registrar event for tags before they are flattened.
     *
     * @param registryKey 标签类型的注册表键
     * <p>原文：the registry key for the tag type
     * @return 注册表事件类型
     * <p>原文：the registry event type
     * @param <T> 标签中值的类型
     * <p>原文：the type of value in the tag
     * @see PreFlattenTagRegistrar
     */
    <T> LifecycleEventType.Prioritizable<BootstrapContext, ReloadableRegistrarEvent<PreFlattenTagRegistrar<T>>> preFlatten(RegistryKey<T> registryKey);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取标签展开后可设置优先级、可重载的注册器事件。
     * <p>原文：
     * Get a prioritizable, reloadable registrar event for tags after they are flattened.
     *
     * @param registryKey 标签类型的注册表键
     * <p>原文：the registry key for the tag type
     * @return 注册表事件类型
     * <p>原文：the registry event type
     * @param <T> 标签中值的类型
     * <p>原文：the type of value in the tag
     * @see PostFlattenTagRegistrar
     */
    <T> LifecycleEventType.Prioritizable<BootstrapContext, ReloadableRegistrarEvent<PostFlattenTagRegistrar<T>>> postFlatten(RegistryKey<T> registryKey);
}
