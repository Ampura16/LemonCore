package io.papermc.paper.plugin.provider.classloader;

import org.bukkit.plugin.java.PluginClassLoader;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 插件类加载器存储是一种<b>内部</b>类型，用于管理服务器上现有的类加载器。
 * <p>
 * Paper 类加载器存储还负责通过 {@link #registerOpenGroup(ConfiguredPluginClassLoader)}、{@link #registerSpigotGroup(PluginClassLoader)} 和 {@link
 * #registerAccessBackedGroup(ConfiguredPluginClassLoader, ClassLoaderAccess)} 将添加的 {@link ConfiguredPluginClassLoader} 存储到 {@link PluginClassLoaderGroup} 中。
 * <p>
 * 组分为全局组和插件所属的组。
 * <ul>
 * <li>全局组持有所有已注册的类加载器，其存在只是为了与 Spigot 旧版类加载器处理方式保持向后兼容。</li>
 * <li>插件组只包含各插件可以访问的类加载器，因此可以正确地隔离不相关的类加载器。</li>
 * </ul>
 * <p>原文：
 * The plugin classloader storage is an <b>internal</b> type that is used to manage existing classloaders on the server.
 * <p>
 * The paper classloader storage is also responsible for storing added {@link ConfiguredPluginClassLoader}s into
 * {@link PluginClassLoaderGroup}s, via {@link #registerOpenGroup(ConfiguredPluginClassLoader)},
 * {@link #registerSpigotGroup(PluginClassLoader)} and {@link
 * #registerAccessBackedGroup(ConfiguredPluginClassLoader, ClassLoaderAccess)}.
 * <p>
 * Groups are differentiated into the global group or plugin owned groups.
 * <ul>
 * <li>The global group holds all registered class loaders and merely exists to maintain backwards compatibility with
 * spigots legacy classloader handling.</li>
 * <li>The plugin groups only contains the classloaders that each plugin has access to and hence serves to properly
 * separates unrelated classloaders.</li>
 * </ul>
 */
@ApiStatus.Internal
public interface PaperClassLoaderStorage {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 访问 {@link PaperClassLoaderStorageAccess} 的共享实例。
     * <p>原文：
     * Access to the shared instance of the {@link PaperClassLoaderStorageAccess}.
     *
     * @return 服务器中统一使用的 {@link PaperClassLoaderStorage} 单例实例
     * <p>原文：the singleton instance of the {@link PaperClassLoaderStorage} used throughout the server
     */
    static PaperClassLoaderStorage instance() {
        return PaperClassLoaderStorageAccess.INSTANCE;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将旧版 Spigot {@link PluginClassLoader} 注册到加载器存储中，创建一个包装该单个插件类加载器、并可传递访问全局组的组。
     * <p>原文：
     * Registers a legacy spigot {@link PluginClassLoader} into the loader storage, creating a group wrapping
     * the single plugin class loader with transitive access to the global group.
     *
     * @param pluginClassLoader 要注册的旧版 Spigot 插件类加载器
     * <p>原文：the legacy spigot plugin class loader to register
     * @return 插件类加载器被放入的组
     * <p>原文：the group the plugin class loader was placed into
     */
    PluginClassLoaderGroup registerSpigotGroup(PluginClassLoader pluginClassLoader);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将 Paper 配置的插件类加载器注册到新的开放组中，可完全访问全局插件类加载器组。
     * <p>
     * 因此，该方法允许已配置的插件类加载器访问在此存储中注册的所有其他类加载器。
     * <p>原文：
     * Registers a paper configured plugin classloader into a new open group, with full access to the global
     * plugin class loader group.
     * <p>
     * This method hence allows the configured plugin class loader to access all other class loaders registered in this
     * storage.
     *
     * @param classLoader 要注册的已配置插件类加载器
     * <p>原文：the configured plugin class loader to register
     * @return 插件类加载器被放入的组
     * <p>原文：the group the plugin class loader was placed into
     */
    PluginClassLoaderGroup registerOpenGroup(ConfiguredPluginClassLoader classLoader);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将 Paper 配置的类加载器注册到新的、由访问支持的组中。与开放组不同，由访问支持的类加载器组只能访问传入的 {@link ClassLoaderAccess} 授予访问权限的类加载器。
     * <p>原文：
     * Registers a paper configured classloader into a new, access backed group.
     * The access backed classloader group, different from an open group, only has access to the classloaders
     * the passed {@link ClassLoaderAccess} grants access to.
     *
     * @param classLoader 要注册的已配置插件类加载器
     * <p>原文：the configured plugin class loader to register
     * @param access 类加载器访问，定义传入的插件类加载器应被授予访问哪些其他类加载器的权限。
     * <p>原文：the class loader access that defines what other classloaders the passed plugin class loader
     *                    should be granted access to.
     * @return 插件类加载器被放入的组。
     * <p>原文：the group the plugin class loader was placed into.
     */
    PluginClassLoaderGroup registerAccessBackedGroup(ConfiguredPluginClassLoader classLoader, ClassLoaderAccess access);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从此存储中注销已配置的类加载器。这会将传入的类加载器从其可能所属的任何组（包括全局组）中移除。
     * <p>
     * 注意：<b>强烈</b>不建议使用此方法，因为不鼓励在运行时修改类加载器
     * <p>原文：
     * Unregisters a configured class loader from this storage.
     * This removes the passed class loaders from any group it may have been a part of, including the global group.
     * <p>
     * Note: this method is <b>highly</b> discouraged from being used, as mutation of the classloaders at runtime
     * is not encouraged
     *
     * @param configuredPluginClassLoader 要从此存储中移除的类加载器。
     * <p>原文：the class loader to remove from this storage.
     */
    void unregisterClassloader(ConfiguredPluginClassLoader configuredPluginClassLoader);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将已配置的插件类加载器直接注册到全局组中，而不添加到任何现有组。
     * <p>
     * 注意：该方法会不安全地将插件类加载器直接注入全局组，从而绕过 Paper 插件 API 引入的组结构，因此应谨慎使用。
     * <p>原文：
     * Registers a configured plugin class loader directly into the global group without adding it to
     * any existing groups.
     * <p>
     * Note: this method unsafely injects the plugin classloader directly into the global group, which bypasses the
     * group structure paper's plugin API introduced. This method should hence be used with caution.
     *
     * @param pluginLoader 应直接注册到全局组中的已配置插件类加载器实例。
     * <p>原文：the configured plugin classloader instance that should be registered directly into the global
     *                     group.
     * @return 一个简单的布尔标志：若类加载器已注册则为 {@code true}，若类加载器已属于全局组则为 {@code false}。
     * <p>原文：a simple boolean flag, {@code true} if the classloader was registered or {@code false} if the classloader
     * was already part of the global group.
     */
    boolean registerUnsafePlugin(ConfiguredPluginClassLoader pluginLoader);

}
