package io.papermc.paper.plugin.lifecycle.event.registrar;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 公开某种 {@link Registrar} 以便管理各类事物的生命周期事件。可参阅 {@link Registrar} 的各实现以了解该事件的用途。
 * <p>原文：
 * A lifecycle event that exposes a {@link Registrar} of some kind
 * to allow management of various things. Look at implementations of
 * {@link Registrar} for an idea of what uses this event.
 *
 * @param <R> 注册器类型
 * <p>原文：registrar type
 * @see ReloadableRegistrarEvent
 */
@ApiStatus.NonExtendable
public interface RegistrarEvent<R extends Registrar> extends LifecycleEvent {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取与此事件相关的注册器。
     * <p>原文：
     * Get the registrar related to this event.
     *
     * @return 注册器
     * <p>原文：the registrar
     */
    @Contract(pure = true)
    R registrar();
}
