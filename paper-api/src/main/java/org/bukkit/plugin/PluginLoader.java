package org.bukkit.plugin;

import java.io.File;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.bukkit.event.Event;
import org.bukkit.event.Listener;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表插件加载器, 加载器会处理对指定类别的插件的直接访问.
 * <p>原文：
 * Represents a plugin loader, which handles direct access to specific types
 * of plugins
 */
@Deprecated(forRemoval = true) // Paper - The PluginLoader system will not function in the near future
public interface PluginLoader {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 加载指定插件文件.
     * <p>原文：
     * Loads the plugin contained in the specified file
     *
     * @param file 要加载的文件
     * <p>原文：File to attempt to load
     * @return 加载后的Plugin对象,若加载失败返回null
     * <p>原文：Plugin that was contained in the specified file, or null if
     *     unsuccessful
     * @throws InvalidPluginException 若指定插件不是一个插件
     * <p>原文：Thrown when the specified file is not a
     *     plugin
     * @throws UnknownDependencyException 若插件要求的依赖找不到
     * <p>原文：If a required dependency could not
     *     be found
     */
    @NotNull
    public Plugin loadPlugin(@NotNull File file) throws InvalidPluginException, UnknownDependencyException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从指定jar文件中加载插件描述文件.
     * <p>原文：
     * Loads a PluginDescriptionFile from the specified file
     *
     * @param file 要加载的文件
     * <p>原文：File to attempt to load from
     * @return 插件jar包内的plugin.yml经解析后的PluginDescriptionFile对象
     * <p>原文：A new PluginDescriptionFile loaded from the plugin.yml in the
     *     specified file
     * @throws InvalidDescriptionException 若plugin.yml存在问题不能加载
     * <p>原文：If the plugin description file
     *     could not be created
     */
    @NotNull
    public PluginDescriptionFile getPluginDescription(@NotNull File file) throws InvalidDescriptionException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回插件加载器的文件名过滤器列表.
     * <p>原文：
     * Returns a list of all filename filters expected by this PluginLoader
     *
     * @return 插件文件名过滤器
     * <p>原文：The filters
     */
    @NotNull
    public Pattern[] getPluginFileFilters();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建并返回给定的监听器中为对应事件类注册过的监听器.
     * <p>原文：
     * Creates and returns registered listeners for the event classes used in
     * this listener
     *
     * @param listener 处理事件回调的监听器对象
     * <p>原文：The object that will handle the eventual call back
     * @param plugin 创建已注册监听器所需插件对象
     * <p>原文：The plugin to use when creating registered listeners
     * @return 已注册的监听器
     * <p>原文：The registered listeners.
     */
    @NotNull
    public Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(@NotNull Listener listener, @NotNull Plugin plugin);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 启用指定的插件.
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
     * @param plugin 要停用的插件.
     * <p>原文：Plugin to disable
     */
    public void disablePlugin(@NotNull Plugin plugin);
}
