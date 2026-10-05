package org.bukkit.plugin;

import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 扩展 RegisteredListener 以包含计时信息.
 * <p>原文：
 * Extends RegisteredListener to include timing information
 */
public class TimedRegisteredListener extends RegisteredListener {
    private int count;
    private long totalTime;
    private Class<? extends Event> eventClass;
    private boolean multiple = false;

    public TimedRegisteredListener(@NotNull final Listener pluginListener, @NotNull final EventExecutor eventExecutor, @NotNull final EventPriority eventPriority, @NotNull final Plugin registeredPlugin, final boolean listenCancelled) {
        super(pluginListener, eventExecutor, eventPriority, registeredPlugin, listenCancelled);
    }

    @Override
    public void callEvent(@NotNull Event event) throws EventException {
        if (event.isAsynchronous()) {
            super.callEvent(event);
            return;
        }
        count++;
        Class<? extends Event> newEventClass = event.getClass();
        if (this.eventClass == null) {
            this.eventClass = newEventClass;
        } else if (!this.eventClass.equals(newEventClass)) {
            multiple = true;
            this.eventClass = getCommonSuperclass(newEventClass, this.eventClass).asSubclass(Event.class);
        }
        long start = System.nanoTime();
        super.callEvent(event);
        totalTime += System.nanoTime() - start;
    }

    @NotNull
    private static Class<?> getCommonSuperclass(@NotNull Class<?> class1, @NotNull Class<?> class2) {
        while (!class1.isAssignableFrom(class2)) {
            class1 = class1.getSuperclass();
        }
        return class1;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 重置此监听器的调用计数和总时间.
     * <p>原文：
     * Resets the call count and total time for this listener
     */
    public void reset() {
        count = 0;
        totalTime = 0;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此监听器被调用的总次数.
     * <p>原文：
     * Gets the total times this listener has been called
     *
     * @return 此监听器被调用的次数
     * <p>原文：Times this listener has been called
     */
    public int getCount() {
        return count;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此监听器所有调用所花费的总时间.
     * <p>原文：
     * Gets the total time calls to this listener have taken
     *
     * @return 此监听器所有调用的总时间
     * <p>原文：Total time for all calls of this listener
     */
    public long getTotalTime() {
        return totalTime;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此监听器处理的事件类. 如果处理了多个事件类, 将返回最近的共享超类, 使得对于此监听器处理的任何事件, <code>this.getEventClass().isAssignableFrom(event.getClass())</code> 成立, 并且不存在 <code>this.getEventClass().isAssignableFrom(clazz) {@literal && this.getEventClass() != clazz &&} event.getClass().isAssignableFrom(clazz)</code> 的类.
     * <p>原文：
     * Gets the class of the events this listener handled. If it handled
     * multiple classes of event, the closest shared superclass will be
     * returned, such that for any event this listener has handled,
     * <code>this.getEventClass().isAssignableFrom(event.getClass())</code>
     * and no class <code>this.getEventClass().isAssignableFrom(clazz)
     * {@literal && this.getEventClass() != clazz &&}
     * event.getClass().isAssignableFrom(clazz)</code> for all handled events.
     *
     * @return 此 RegisteredListener 处理的事件类
     * <p>原文：the event class handled by this RegisteredListener
     */
    @Nullable
    public Class<? extends Event> getEventClass() {
        return eventClass;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此监听器是否处理了多个事件, 即存在两个事件使得 <code>eventA.getClass() != eventB.getClass()</code>.
     * <p>原文：
     * Gets whether this listener has handled multiple events, such that for
     * some two events, <code>eventA.getClass() != eventB.getClass()</code>.
     *
     * @return 如果此监听器处理了多个事件则返回 true
     * <p>原文：true if this listener has handled multiple events
     */
    public boolean hasMultiple() {
        return multiple;
    }
}
