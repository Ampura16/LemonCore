package org.bukkit.plugin;

import java.io.File;
import java.util.Set;
import org.bukkit.event.Event;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 服务器的插件管理器.
 * <p>原文：
 * Handles all plugin management from the Server
 */
public interface PluginManager extends io.papermc.paper.plugin.PermissionManager { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 注册指定的插件加载器.
     * <p>原文：
     * Registers the specified plugin loader
     *
     * @param loader 要注册的 PluginLoader 类
     * <p>原文：Class name of the PluginLoader to register
     * @throws IllegalArgumentException 当给定的类不是一个有效的 PluginLoader 时抛出
     * <p>原文：Thrown when the given Class is not a
     *     valid PluginLoader
     */
    @Deprecated(forRemoval = true) // Paper - The PluginLoader system will not function in the near future
    public void registerInterface(@NotNull Class<? extends PluginLoader> loader) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测指定插件是否已加载并在合适时返回其对象.
     * <p>
     * 请注意插件名区分大小写.
     * <p>原文：
     * Checks if the given plugin is loaded and returns it when applicable
     * <p>
     * Please note that the name of the plugin is case-insensitive
     *
     * @param name 要检测的插件的名称
     * <p>原文：Name of the plugin to check
     * @return 如果此插件存在, 返回对应插件对象, 否则返回null
     * <p>原文：Plugin if it exists, otherwise null
     */
    @Nullable
    public Plugin getPlugin(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有已载入的插件对象.
     * <p>原文：
     * Gets a list of all currently loaded plugins
     *
     * @return 返回类型为Plugin的数组
     * <p>原文：Array of Plugins
     */
    @NotNull
    public Plugin[] getPlugins();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测指定插件是否已启用.
     * <p>
     * 请注意插件名区分大小写.
     * <p>原文：
     * Checks if the given plugin is enabled or not
     * <p>
     * Please note that the name of the plugin is case-insensitive.
     *
     * @param name 要检测的插件的名称
     * <p>原文：Name of the plugin to check
     * @return 如果此插件已启用, 返回true
     * <p>原文：true if the plugin is enabled, otherwise false
     */
    public boolean isPluginEnabled(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查指定插件是否启用.
     * <p>原文：
     * Checks if the given plugin is enabled or not
     *
     * @param plugin 要检测的插件的对象
     * <p>原文：Plugin to check
     * @return 如果此插件已启用, 返回true
     * <p>原文：true if the plugin is enabled, otherwise false
     */
    @Contract("null -> false")
    public boolean isPluginEnabled(@Nullable Plugin plugin);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 加载某个文件为插件.
     * <p>
     * 文件必须对当前的插件加载器有效才会被加载.
     * <p>原文：
     * Loads the plugin in the specified file
     * <p>
     * File must be valid according to the current enabled Plugin interfaces
     *
     * @param file 要加载的插件文件
     * <p>原文：File containing the plugin to load
     * @return 加载成功后的插件对象, 若插件文件无效返回null
     * <p>原文：The Plugin loaded, or null if it was invalid
     * @throws InvalidPluginException 若指定文件不是一个有效的插件
     * <p>原文：Thrown when the specified file is not a
     *     valid plugin
     * @throws InvalidDescriptionException 若插件包含一个无效的插件描述文件(plugin.yml)
     * <p>原文：Thrown when the specified file
     *     contains an invalid description
     * @throws UnknownDependencyException 若插件要求的依赖找不到
     * <p>原文：If a required dependency could not
     *     be resolved
     */
    @Nullable
    public Plugin loadPlugin(@NotNull File file) throws InvalidPluginException, InvalidDescriptionException, UnknownDependencyException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 载入指定目录内的插件.
     * <p>原文：
     * Loads the plugins contained within the specified directory
     *
     * @param directory 指定目录的File对象
     * <p>原文：Directory to check for plugins
     * @return 返回带有已成功载入的插件Plugin对象的数组
     * <p>原文：A list of all plugins loaded
     */
    @NotNull
    public Plugin[] loadPlugins(@NotNull File directory);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 载入指定文件列表中的插件.
     * <p>原文：
     * Loads the plugins in the list of the files
     *
     * @param files 包含待加载插件的文件列表
     * <p>原文：List of files containing plugins to load
     * @return 返回所有已加载插件的数组
     * <p>原文：A list of all plugins loaded
     */
    @NotNull
    public Plugin[] loadPlugins(@NotNull File[] files);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 停用所有已载入的插件.
     * <p>原文：
     * Disables all the loaded plugins
     */
    public void disablePlugins();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 停用并清除所有已启用插件的Plugin对象.
     * <p>原文：
     * Disables and removes all plugins
     */
    public void clearPlugins();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 调用一个具有详细信息的事件.
     * <p>
     * 译注:(针对 Bukkkit 中对本接口的唯一实现{@link SimplePluginManager}来说)本方法是线程安全的, 但不可以在同步代码块/同步线程中触发异步事件,
     * 也不可以从异步线程中触发同步事件.
     * <p>原文：
     * Calls an event with the given details
     *
     * @param event 事件
     * <p>原文：Event details
     * @throws IllegalStateException 当从同步代码中触发异步事件时抛出
     *     <p>
     *     <i>注意:这是插件开发者应尽的义务, 不应用其来测试同步状态. 这是逻辑流有缺陷的迹象</i>
     * <p>原文：Thrown when an asynchronous event is
     *     fired from synchronous code.
     *     <p>
     *     <i>Note: This is best-effort basis, and should not be used to test
     *     synchronized state. This is an indicator for flawed flow logic.</i>
     */
    public void callEvent(@NotNull Event event) throws IllegalStateException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 注册在指定监听器类中的所有事件.
     * <p>原文：
     * Registers all the events in the given listener class
     *
     * @param listener 要注册的监听器
     * <p>原文：Listener to register
     * @param plugin 注册事件的插件
     * <p>原文：Plugin to register
     */
    public void registerEvents(@NotNull Listener listener, @NotNull Plugin plugin);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将指定的执行器注册至指定的事件类.
     * <p>原文：
     * Registers the specified executor to the given event class
     *
     * @param event 要注册的事件类型
     * <p>原文：Event type to register
     * @param listener 要注册的监听器
     * <p>原文：Listener to register
     * @param priority 要注册的事件的优先级
     * <p>原文：Priority to register this event at
     * @param executor 要注册的EventExecutor
     * <p>原文：EventExecutor to register
     * @param plugin 注册事件的插件
     * <p>原文：Plugin to register
     */
    public void registerEvent(@NotNull Class<? extends Event> event, @NotNull Listener listener, @NotNull EventPriority priority, @NotNull EventExecutor executor, @NotNull Plugin plugin);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将指定的执行器注册至指定的事件类.
     * <p>原文：
     * Registers the specified executor to the given event class
     *
     * @param event 要注册的事件类型
     * <p>原文：Event type to register
     * @param listener 要注册的监听器
     * <p>原文：Listener to register
     * @param priority 要注册的事件的优先级
     * <p>原文：Priority to register this event at
     * @param executor 要注册的EventExecutor
     * <p>原文：EventExecutor to register
     * @param plugin 注册事件的插件
     * <p>原文：Plugin to register
     * @param ignoreCancelled 是否忽略已取消的事件
     * <p>原文：Whether to pass cancelled events or not
     */
    public void registerEvent(@NotNull Class<? extends Event> event, @NotNull Listener listener, @NotNull EventPriority priority, @NotNull EventExecutor executor, @NotNull Plugin plugin, boolean ignoreCancelled);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 启用指定插件.
     * <p>
     * 尝试启用一个已启用的插件, 什么都不会发生.
     * <p>原文：
     * Enables the specified plugin
     * <p>
     * Attempting to enable a plugin that is already enabled will have no
     * effect
     *
     * @param plugin 要启用的插件
     * <p>原文：Plugin to enable
     */
    public void enablePlugin(@NotNull Plugin plugin);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 停用指定的插件.
     * <p>
     * 尝试停用一个已停用的插件, 什么都不会发生.
     * <p>原文：
     * Disables the specified plugin
     * <p>
     * Attempting to disable a plugin that is not enabled will have no effect
     *
     * @param plugin 要停用的插件
     * <p>原文：Plugin to disable
     */
    public void disablePlugin(@NotNull Plugin plugin);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以指定的完整权限节点获取对应{@link Permission}对象.
     * <p>原文：
     * Gets a {@link Permission} from its fully qualified name
     *
     * @param name Name of the permission
     * @return 对应{@link Permission}对象, 若权限节点不存在返回null
     * <p>原文：Permission, or null if none
     */
    @Nullable
    public Permission getPermission(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加一个{@link Permission}至本插件管理器.
     * <p>
     * 如果已有同名权限, 会抛出一个异常.
     * <p>原文：
     * Adds a {@link Permission} to this plugin manager.
     * <p>
     * If a permission is already defined with the given name of the new
     * permission, an exception will be thrown.
     *
     * @param perm 要添加的权限
     * <p>原文：Permission to add
     * @throws IllegalArgumentException 如果同名权限已存在
     * <p>原文：Thrown when a permission with the same
     *     name already exists
     */
    public void addPermission(@NotNull Permission perm);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从本插件管理器中移除已注册的{@link Permission 权限}.
     * <p>
     * 如果指定的权限未在本插件管理器中注册, 什么都不会发生.
     * <p>原文：
     * Removes a {@link Permission} registration from this plugin manager.
     * <p>
     * If the specified permission does not exist in this plugin manager,
     * nothing will happen.
     * <p>
     * Removing a permission registration will <b>not</b> remove the
     * permission from any {@link Permissible}s that have it.
     *
     * @param perm 要移除的权限
     * <p>原文：Permission to remove
     */
    public void removePermission(@NotNull Permission perm);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从本插件管理器中移除已注册的{@link Permission 权限}.
     * <p>
     * 如果指定的权限未在本插件管理器中注册, 什么都不会发生.
     * <p>原文：
     * Removes a {@link Permission} registration from this plugin manager.
     * <p>
     * If the specified permission does not exist in this plugin manager,
     * nothing will happen.
     * <p>
     * Removing a permission registration will <b>not</b> remove the
     * permission from any {@link Permissible}s that have it.
     *
     * @param name 要移除的权限
     * <p>原文：Permission to remove
     */
    public void removePermission(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取普通玩家默认拥有的权限或op默认拥有的权限.
     * <p>原文：
     * Gets the default permissions for the given op status
     *
     * @param op 是否获取op状态下默认拥有的权限
     * <p>原文：Which set of default permissions to get
     * @return 返回 普通玩家/OP 默认拥有的权限
     * <p>原文：The default permissions
     */
    @NotNull
    public Set<Permission> getDefaultPermissions(boolean op);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 重新计算指定{@link Permission 权限}的默认值.
     * <p>
     * 若指定的权限未在本插件管理器注册, 将没有效果.
     * <p>原文：
     * Recalculates the defaults for the given {@link Permission}.
     * <p>
     * This will have no effect if the specified permission is not registered
     * here.
     *
     * @param perm 要重算的权限
     * <p>原文：Permission to recalculate
     */
    public void recalculatePermissionDefaults(@NotNull Permission perm);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以指定的权限名订阅与其有关的 Permissible.
     * <p>
     * 若指定的权限以任何形式改变, 要求重新计算传入的Permissible.
     * <p>原文：
     * Subscribes the given Permissible for information about the requested
     * Permission, by name.
     * <p>
     * If the specified Permission changes in any form, the Permissible will
     * be asked to recalculate.
     *
     * @param permission 要订阅的权限
     * <p>原文：Permission to subscribe to
     * @param permissible 要订阅的Permissible
     * <p>原文：Permissible subscribing
     */
    public void subscribeToPermission(@NotNull String permission, @NotNull Permissible permissible);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以指定的权限名退订与其有关的 Permissible.
     * <p>原文：
     * Unsubscribes the given Permissible for information about the requested
     * Permission, by name.
     *
     * @param permission 要退订的权限
     * <p>原文：Permission to unsubscribe from
     * @param permissible 已订阅的Permissible
     * <p>原文：Permissible subscribing
     */
    public void unsubscribeFromPermission(@NotNull String permission, @NotNull Permissible permissible);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据权限名获取此权限已订阅的{@link Permissible}的集合.
     * <p>原文：
     * Gets a set containing all subscribed {@link Permissible}s to the given
     * permission, by name
     *
     * @param permission 要检索的权限
     * <p>原文：Permission to query for
     * @return 包含此权限已订阅的所有{@link Permissible}的集合
     * <p>原文：Set containing all subscribed permissions
     */
    @NotNull
    public Set<Permissible> getPermissionSubscriptions(@NotNull String permission);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为普通玩家/op默认权限订阅指定{@link Permissible}.
     * <p>
     * 若指定的默认权限列表以任何形式改动, 要求重新计算传入的Permissible.
     * <p>原文：
     * Subscribes to the given Default permissions by operator status
     * <p>
     * If the specified defaults change in any form, the Permissible will be
     * asked to recalculate.
     *
     * @param op 是否根据op的默认权限列表订阅
     * <p>原文：Default list to subscribe to
     * @param permissible 要订阅的Permissible
     * <p>原文：Permissible subscribing
     */
    public void subscribeToDefaultPerms(boolean op, @NotNull Permissible permissible);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 退订普通玩家/op默认权限已订阅的{@link Permissible}.
     * <p>原文：
     * Unsubscribes from the given Default permissions by operator status
     *
     * @param op 是否根据op的默认权限列表退订
     * <p>原文：Default list to unsubscribe from
     * @param permissible 权限已订阅的Permissible
     * <p>原文：Permissible subscribing
     */
    public void unsubscribeFromDefaultPerms(boolean op, @NotNull Permissible permissible);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取普通玩家/op默认权限列表已订阅的{@link Permissible}.
     * <p>原文：
     * Gets a set containing all subscribed {@link Permissible}s to the given
     * default list, by op status
     *
     * @param op 是否根据op的默认权限列表检索
     * <p>原文：Default list to query for
     * @return 包含所有权限订阅者的集合
     * <p>原文：Set containing all subscribed permissions
     */
    @NotNull
    public Set<Permissible> getDefaultPermSubscriptions(boolean op);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取已注册的权限的集合.
     * <p>
     * 此集合是一个副本, 对其编辑不会作用于内部的权限列表.
     * <p>原文：
     * Gets a set of all registered permissions.
     * <p>
     * This set is a copy and will not be modified live.
     *
     * @return 包含所有已注册权限的集合
     * <p>原文：Set containing all current registered permissions
     */
    @NotNull
    public Set<Permission> getPermissions();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回是否在事件调用中使用插件计时器.
     * <p>
     * 译注:Timings是一个性能分析工具, 通过Timings可以了解
     * 服务器的性能损耗情况, 通过它可以定位插件/服务器代码中性能较差的方法, 并针对性地进行优化.
     * 可以通过"/timings on"开始记录, "/timings paste"上传记录, "/timings off"关闭记录 (仅针对Spigot及其衍生服务端).
     * <p>原文：
     * Returns whether or not timing code should be used for event calls
     *
     * @return 若已启用Timings
     * <p>原文：True if event timings are to be used
     */
    public boolean useTimings();

    // Paper start
    /**
     * @hidden
     */
    @org.jetbrains.annotations.ApiStatus.Internal
    boolean isTransitiveDependency(io.papermc.paper.plugin.configuration.PluginMeta pluginMeta, io.papermc.paper.plugin.configuration.PluginMeta dependencyConfig);

    /**
     * Sets the permission manager to be used for this server.
     *
     * @param permissionManager permission manager
     */
    @org.jetbrains.annotations.ApiStatus.Experimental
    void overridePermissionManager(@NotNull Plugin plugin, @Nullable io.papermc.paper.plugin.PermissionManager permissionManager);
    // Paper end
}
