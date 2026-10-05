package io.papermc.paper.registry.event;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;
import io.papermc.paper.registry.RegistryKey;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 所有注册表事件的基础类型。
 * <p>原文：
 * Base type for all registry events.
 *
 * @param <T> 注册表条目类型
 * <p>原文：registry entry type
 */
@ApiStatus.NonExtendable
public interface RegistryEvent<T> extends LifecycleEvent {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件所涉及注册表的键。
     * <p>原文：
     * Get the key for the registry this event pertains to.
     *
     * @return 注册表键
     * <p>原文：the registry key
     */
    RegistryKey<T> registryKey();
}
