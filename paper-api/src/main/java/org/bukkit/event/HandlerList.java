package org.bukkit.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EnumMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map.Entry;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个处理事件的类, 存储每个事件。基于 lahwran's fevents.
 * <p>原文：
 * A list of event handlers, stored per-event. Based on lahwran's fevents.
 */
public class HandlerList {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 包含了所有HandlerList的数组.此数组字段是这个系统速度的关键.
     * <p>原文：
     * Handler array. This field being an array is the key to this system's
     * speed.
     */
    private volatile RegisteredListener[] handlers = null;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 动态HandlerList.使用register()和unreguster()进行改动，当有任何改动的时候自动bake(合并)HandlerList.
     * <p>
     * 译注:“bake”的意思是“烤”，但意思根本对不上，根据源代码来分析大概是“合并”，或者“拷贝” 下方出现的“bake”同.一般情况下这个字段的意义我们是不需要理解的.如果你知道其真正意义请告知我们.
     * <p>原文：
     * Dynamic handler lists. These are changed using register() and
     * unregister() and are automatically baked to the handlers array any time
     * they have changed.
     */
    private final EnumMap<EventPriority, ArrayList<RegisteredListener>> handlerslots;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 所有已经创建的HandlerList,用于bakeAll().
     * <p>原文：
     * List of all HandlerLists which have been created, for use in bakeAll()
     */
    private static final ArrayList<HandlerList> allLists = new ArrayList<>();

    /**
     * Event types which have instantiated a {@link HandlerList}.
     */
    private static final java.util.Set<String> EVENT_TYPES = java.util.concurrent.ConcurrentHashMap.newKeySet();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 合并(bake)所有处理器列表.最好用在所有正常的事件注册完毕后,即所有插件都加载完了,如果你使用fevents插件系统.
     * <p>原文：
     * Bake all handler lists. Best used just after all normal event
     * registration is complete, ie just after all plugins are loaded if
     * you're using fevents in a plugin system.
     */
    public static void bakeAll() {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                h.bake();
            }
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从所有处理器列表中注销所有监听器.
     * <p>原文：
     * Unregister all listeners from all handler lists.
     */
    public static void unregisterAll() {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                synchronized (h) {
                    for (List<RegisteredListener> list : h.handlerslots.values()) {
                        list.clear();
                    }
                    h.handlers = null;
                }
            }
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从所有处理器列表中注销指定插件的所有监听器.
     * <p>原文：
     * Unregister a specific plugin's listeners from all handler lists.
     *
     * @param plugin 要注销监听器的插件
     * <p>原文：plugin to unregister
     */
    public static void unregisterAll(@NotNull Plugin plugin) {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                h.unregister(plugin);
            }
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从处理器列表中注销一个指定的监听器.
     * <p>原文：
     * Unregister a specific listener from all handler lists.
     *
     * @param listener 要注销的监听器
     * <p>原文：listener to unregister
     */
    public static void unregisterAll(@NotNull Listener listener) {
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                h.unregister(listener);
            }
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用EventPriority来创建和初始化一个新的HandlerList.
     * <p>
     * HandlerList将会被添加到元列表，为了bakeAll()方法.
     * <p>原文：
     * Create a new handler list and initialize using EventPriority.
     * <p>
     * The HandlerList is then added to meta-list for use in bakeAll()
     */
    public HandlerList() {
        java.lang.StackWalker.getInstance(java.util.EnumSet.of(java.lang.StackWalker.Option.RETAIN_CLASS_REFERENCE), 4)
            .walk(s -> s.filter(f -> Event.class.isAssignableFrom(f.getDeclaringClass())).findFirst())
            .map(f -> f.getDeclaringClass().getName())
            .ifPresent(EVENT_TYPES::add);

        handlerslots = new EnumMap<>(EventPriority.class);
        for (EventPriority o : EventPriority.values()) {
            handlerslots.put(o, new ArrayList<>());
        }
        synchronized (allLists) {
            allLists.add(this);
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在处理器列表中注册一个监听器.
     * <p>原文：
     * Register a new listener in this handler list
     *
     * @param listener 要注册的监听器
     * <p>原文：listener to register
     */
    public synchronized void register(@NotNull RegisteredListener listener) {
        if (handlerslots.get(listener.getPriority()).contains(listener))
            throw new IllegalStateException("This listener is already registered to priority " + listener.getPriority().toString());
        handlers = null;
        handlerslots.get(listener.getPriority()).add(listener);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在处理列表中注册一个监听器集合(批量注册监听器).
     * <p>原文：
     * Register a collection of new listeners in this handler list
     *
     * @param listeners 要注册的监听器
     * <p>原文：listeners to register
     */
    public void registerAll(@NotNull Collection<RegisteredListener> listeners) {
        for (RegisteredListener listener : listeners) {
            register(listener);
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从一个指定的排序的插槽删除一个监听器.
     * <p>
     * 译注：“插槽”的意思见本类的handlerslots变量.
     * <p>原文：
     * Remove a listener from a specific order slot
     *
     * @param listener 要移除的监听器
     * <p>原文：listener to remove
     */
    public synchronized void unregister(@NotNull RegisteredListener listener) {
        if (handlerslots.get(listener.getPriority()).remove(listener)) {
            handlers = null;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除一个插件中指定的监听器.
     * <p>原文：
     * Remove a specific plugin's listeners from this handler
     *
     * @param plugin 要移除监听器的插件
     * <p>原文：plugin to remove
     */
    public synchronized void unregister(@NotNull Plugin plugin) {
        boolean changed = false;
        for (List<RegisteredListener> list : handlerslots.values()) {
            for (ListIterator<RegisteredListener> i = list.listIterator(); i.hasNext();) {
                if (i.next().getPlugin().equals(plugin)) {
                    i.remove();
                    changed = true;
                }
            }
        }
        if (changed) handlers = null;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从这个处理列表移除一个指定的监听器。
     * <p>原文：
     * Remove a specific listener from this handler
     *
     * @param listener 要移除的监听器
     * <p>原文：listener to remove
     */
    public synchronized void unregister(@NotNull Listener listener) {
        boolean changed = false;
        for (List<RegisteredListener> list : handlerslots.values()) {
            for (ListIterator<RegisteredListener> i = list.listIterator(); i.hasNext();) {
                if (i.next().getListener().equals(listener)) {
                    i.remove();
                    changed = true;
                }
            }
        }
        if (changed) handlers = null;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 合并(bake)一个HashMap和ArrayLists到二维数组 - 如果不必要，什么也不会做.
     * <p>原文：
     * Bake HashMap and ArrayLists to 2d array - does nothing if not necessary
     */
    public synchronized void bake() {
        if (handlers != null) return; // don't re-bake when still valid
        List<RegisteredListener> entries = new ArrayList<>();
        for (Entry<EventPriority, ArrayList<RegisteredListener>> entry : handlerslots.entrySet()) {
            entries.addAll(entry.getValue());
        }
        handlers = entries.toArray(new RegisteredListener[entries.size()]);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与这个处理器列表相关的已注册的监听器.
     * <p>原文：
     * Get the baked registered listeners associated with this handler list
     *
     * @return 注册过的监听器的数组
     * <p>原文：the array of registered listeners
     */
    public @NotNull RegisteredListener @NotNull [] getRegisteredListeners() {
        RegisteredListener[] handlers;
        while ((handlers = this.handlers) == null) bake(); // This prevents fringe cases of returning null
        return handlers;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与这个处理器列表相关的指定插件注册的监听器.
     * <p>原文：
     * Get a specific plugin's registered listeners associated with this
     * handler list
     *
     * @param plugin 要获取监听器的插件
     * <p>原文：the plugin to get the listeners of
     * @return 注册的监听器列表
     * <p>原文：the list of registered listeners
     */
    @NotNull
    public static ArrayList<RegisteredListener> getRegisteredListeners(@NotNull Plugin plugin) {
        ArrayList<RegisteredListener> listeners = new ArrayList<RegisteredListener>();
        synchronized (allLists) {
            for (HandlerList h : allLists) {
                synchronized (h) {
                    for (List<RegisteredListener> list : h.handlerslots.values()) {
                        for (RegisteredListener listener : list) {
                            if (listener.getPlugin().equals(plugin)) {
                                listeners.add(listener);
                            }
                        }
                    }
                }
            }
        }
        return listeners;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每个事件类型的所有处理器的列表.
     * <p>原文：
     * Get a list of all handler lists for every event type
     *
     * @return 所有处理器的列表
     * <p>原文：the list of all handler lists
     */
    @SuppressWarnings("unchecked")
    @NotNull
    public static ArrayList<HandlerList> getHandlerLists() {
        synchronized (allLists) {
            return (ArrayList<HandlerList>) allLists.clone();
        }
    }
}
