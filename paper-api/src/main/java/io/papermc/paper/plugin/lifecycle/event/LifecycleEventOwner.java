package io.papermc.paper.plugin.lifecycle.event;

import io.papermc.paper.plugin.configuration.PluginMeta;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 由被视为生命周期事件已注册处理器所有者的类型实现。通常实现此接口的类型还会提供一个 {@link LifecycleEventManager}，你可以在其中注册事件处理器。
 * <p>原文：
 * Implemented by types that are considered owners
 * of registered handlers for lifecycle events. Generally
 * the types that implement this interface also provide
 * a {@link LifecycleEventManager} where you can register
 * event handlers.
 */
@ApiStatus.NonExtendable
public interface LifecycleEventOwner {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此插件的插件元数据。
     * <p>原文：
     * Get the plugin meta for this plugin.
     *
     * @return 插件元数据
     * <p>原文：the plugin meta
     */
    PluginMeta getPluginMeta();
}
