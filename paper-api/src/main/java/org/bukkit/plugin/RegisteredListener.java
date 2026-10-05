package org.bukkit.plugin;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.EventException;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 存储插件监听器的相关信息.
 * <p>原文：
 * Stores relevant information for plugin listeners
 */
public class RegisteredListener {

    private final Listener listener;
    private final EventPriority priority;
    private final Plugin plugin;
    private final EventExecutor executor;
    private final boolean ignoreCancelled;

    public RegisteredListener(@NotNull final Listener listener, @NotNull final EventExecutor executor, @NotNull final EventPriority priority, @NotNull final Plugin plugin, final boolean ignoreCancelled) {
        this.listener = listener;
        this.priority = priority;
        this.plugin = plugin;
        this.executor = executor;
        this.ignoreCancelled = ignoreCancelled;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此注册的监听器.
     * <p>原文：
     * Gets the listener for this registration
     *
     * @return 已注册的监听器
     * <p>原文：Registered Listener
     */
    @NotNull
    public Listener getListener() {
        return listener;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此注册的插件.
     * <p>原文：
     * Gets the plugin for this registration
     *
     * @return 已注册的插件
     * <p>原文：Registered Plugin
     */
    @NotNull
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此注册的优先级.
     * <p>原文：
     * Gets the priority for this registration
     *
     * @return 已注册的优先级
     * <p>原文：Registered Priority
     */
    @NotNull
    public EventPriority getPriority() {
        return priority;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 调用事件执行器.
     * <p>原文：
     * Calls the event executor
     *
     * @param event 事件对象
     * <p>原文：The event
     * @throws EventException 如果事件处理器抛出异常
     * <p>原文：If an event handler throws an exception.
     */
    public void callEvent(@NotNull final Event event) throws EventException {
        if (event instanceof Cancellable) {
            if (((Cancellable) event).isCancelled() && isIgnoringCancelled()) {
                return;
            }
        }
        executor.execute(listener, event);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此监听器是否忽略已取消的事件.
     * <p>原文：
     * Whether this listener accepts cancelled events
     *
     * @return 当忽略已取消事件时返回 true
     * <p>原文：True when ignoring cancelled events
     */
    public boolean isIgnoringCancelled() {
        return ignoreCancelled;
    }

    /**
     * Get the executor for this registration.
     *
     * @return executor
     */
    @NotNull
    public EventExecutor getExecutor() {
        return this.executor;
    }

    @Override
    public String toString() {
        return "RegisteredListener{"
            + "plugin=\"" + this.plugin.getName()
            + "\", listener=\"" + this.listener
            + "\", executor=\"" + this.executor
            + "\", priority=\"" + this.priority.name() + " (" + this.priority.getSlot() + ")"
            + "\", ignoringCancelled=" + this.ignoreCancelled
            + "}";
    }
}
