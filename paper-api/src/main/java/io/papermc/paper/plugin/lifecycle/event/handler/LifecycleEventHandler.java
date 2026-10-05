package io.papermc.paper.plugin.lifecycle.event.handler;

import io.papermc.paper.plugin.lifecycle.event.LifecycleEvent;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 特定事件的处理器。可在具体类中实现，也可写成 lambda。
 * <p>原文：
 * A handler for a specific event. Can be implemented
 * in a concrete class or as a lambda.
 *
 * @param <E> 事件
 * <p>原文：the event
 */
@FunctionalInterface
public interface LifecycleEventHandler<E extends LifecycleEvent> {

    void run(E event);
}
