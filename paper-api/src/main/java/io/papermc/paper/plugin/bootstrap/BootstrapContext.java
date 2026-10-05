package io.papermc.paper.plugin.bootstrap;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager;
import io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示在引导和插件实例化逻辑期间提供给 {@link PluginBootstrap} 的上下文。引导上下文可用于在插件引导期间访问通常提供给 {@link org.bukkit.plugin.Plugin} 实例的数据或逻辑，例如插件的配置或日志记录器。
 * <p>原文：
 * Represents the context provided to a {@link PluginBootstrap} during both the bootstrapping and plugin
 * instantiation logic.
 * A bootstrap context may be used to access data or logic usually provided to {@link org.bukkit.plugin.Plugin} instances
 * like the plugin's configuration or logger during the plugins bootstrap.
 */
@ApiStatus.Experimental
@NullMarked
@ApiStatus.NonExtendable
public interface BootstrapContext extends PluginProviderContext, LifecycleEventOwner {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取生命周期事件管理器，用于注册 {@link BootstrapContext} 上允许的生命周期事件的处理器。
     * <p>原文：
     * Get the lifecycle event manager for registering handlers
     * for lifecycle events allowed on the {@link BootstrapContext}.
     *
     * @return 生命周期事件管理器
     * <p>原文：the lifecycle event manager
     */
    LifecycleEventManager<BootstrapContext> getLifecycleManager();
}
