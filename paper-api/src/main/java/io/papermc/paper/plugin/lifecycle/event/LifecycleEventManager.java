package io.papermc.paper.plugin.lifecycle.event;

import io.papermc.paper.plugin.lifecycle.event.handler.LifecycleEventHandler;
import io.papermc.paper.plugin.lifecycle.event.handler.configuration.LifecycleEventHandlerConfiguration;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEventType;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 管理插件的生命周期事件。可从 {@link org.bukkit.plugin.Plugin} 或 {@link io.papermc.paper.plugin.bootstrap.BootstrapContext} 获取。
 * <p>原文：
 * Manages a plugin's lifecycle events. Can be obtained
 * from {@link org.bukkit.plugin.Plugin} or {@link io.papermc.paper.plugin.bootstrap.BootstrapContext}.
 *
 * @param <O> 所属类型，{@link org.bukkit.plugin.Plugin} 或 {@link io.papermc.paper.plugin.bootstrap.BootstrapContext}
 * <p>原文：the owning type, {@link org.bukkit.plugin.Plugin} or {@link io.papermc.paper.plugin.bootstrap.BootstrapContext}
 */
@ApiStatus.NonExtendable
public interface LifecycleEventManager<O extends LifecycleEventOwner> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为特定事件类型注册事件处理器。<p> 这是创建新的 {@link LifecycleEventHandlerConfiguration} 并仅传入 {@link LifecycleEventHandler} 的简写。<pre>{@code
     * LifecycleEventHandler<RegistrarEvent<Commands>> handler = new Handler();
     * manager.registerEventHandler(LifecycleEvents.COMMANDS, handler);
     * }</pre> 等价于 <pre>{@code
     * LifecycleEventHandler<RegistrarEvent<Commands>> handler = new Handler();
     * manager.registerEventHandler(LifecycleEvents.COMMANDS.newHandler(handler));
     * }</pre>
     * <p>原文：
     * Registers an event handler for a specific event type.
     * <p>
     * This is shorthand for creating a new {@link LifecycleEventHandlerConfiguration} and
     * just passing in the {@link LifecycleEventHandler}.
     * <pre>{@code
     * LifecycleEventHandler<RegistrarEvent<Commands>> handler = new Handler();
     * manager.registerEventHandler(LifecycleEvents.COMMANDS, handler);
     * }</pre>
     * is equivalent to
     * <pre>{@code
     * LifecycleEventHandler<RegistrarEvent<Commands>> handler = new Handler();
     * manager.registerEventHandler(LifecycleEvents.COMMANDS.newHandler(handler));
     * }</pre>
     *
     * @param eventType 要监听的事件类型
     * <p>原文：the event type to listen to
     * @param eventHandler 该事件的处理器
     * <p>原文：the handler for that event
     * @param <E> 事件对象的类型
     * <p>原文：the type of the event object
     */
    default <E extends LifecycleEvent> void registerEventHandler(final LifecycleEventType<? super O, ? extends E, ?> eventType, final LifecycleEventHandler<? super E> eventHandler) {
        this.registerEventHandler(eventType.newHandler(eventHandler));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 注册事件处理器配置。
     * <p>
     * 配置通过 {@link LifecycleEventType#newHandler(LifecycleEventHandler)} 创建。不同事件类型在 {@link LifecycleEventType#newHandler(LifecycleEventHandler)} 返回的类构建器对象上可能有不同的可用配置选项。
     * <p>原文：
     * Registers an event handler configuration.
     * <p>
     * Configurations are created via {@link LifecycleEventType#newHandler(LifecycleEventHandler)}.
     * Event types may have different configurations options available on the builder-like object
     * returned by {@link LifecycleEventType#newHandler(LifecycleEventHandler)}.
     *
     * @param handlerConfiguration 要注册的处理器配置
     * <p>原文：the handler configuration to register
     */
    void registerEventHandler(LifecycleEventHandlerConfiguration<? super O> handlerConfiguration);
}
