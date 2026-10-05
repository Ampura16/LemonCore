package io.papermc.paper.plugin.provider.classloader;

import io.papermc.paper.plugin.configuration.PluginMeta;
import java.io.Closeable;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 已配置的插件类加载器是一种<b>内部</b>抽象，代表服务器在运行时用于加载和访问插件类的类加载器。
 * <p>
 * 它实现 {@link Closeable}，以定义关闭实现此接口的类加载器的能力。
 * <p>原文：
 * The configured plugin class loader represents an <b>internal</b> abstraction over the classloaders used by the server
 * to load and access a plugins classes during runtime.
 * <p>
 * It implements {@link Closeable} to define the ability to shutdown and close the classloader that implements this
 * interface.
 */
@NullMarked
@ApiStatus.Internal
public interface ConfiguredPluginClassLoader extends Closeable {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此插件类加载器为其提供类型访问的插件的配置。
     * <p>原文：
     * Provides the configuration of the plugin that this plugin classloader provides type access to.
     *
     * @return 插件元数据实例，包含该插件实例的所有元信息。
     * <p>原文：the plugin meta instance, holding all meta information about the plugin instance.
     */
    PluginMeta getConfiguration();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 尝试使用传入的全限定名从此插件类加载器加载类。可通过以下参数配置查找逻辑，以决定类查找的范围宽窄。
     * <p>原文：
     * Attempts to load a class from this plugin class loader using the passed fully qualified name.
     * This lookup logic can be configured through the following parameters to define how wide or how narrow the
     * class lookup should be.
     *
     * @param name 要加载的类的全限定名
     * <p>原文：the fully qualified name of the class to load
     * @param resolve 是否应在需要时解析该类
     * <p>原文：whether the class should be resolved if needed or not
     * @param checkGlobal 此查找是否应检查传递依赖，包括旧版 Spigot 全局类加载器或 Paper 的 {@link PluginClassLoaderGroup}
     * <p>原文：whether this lookup should check transitive dependencies, including either the legacy spigot
     *                       global class loader or the paper {@link PluginClassLoaderGroup}
     * @param checkLibraries 是否应在已定义的库中检查该类
     * <p>原文：whether the defined libraries should be checked for the class or not
     * @return 在传入的限制下，于传入的全限定类名处找到的类
     * <p>原文：the class found at the fully qualified class name passed under the passed restrictions
     * @throws ClassNotFoundException 如果在传入的限制下找不到该类
     * <p>原文：if the class could not be found considering the passed restrictions
     * @see ClassLoader#loadClass(String)
     * @see Class#forName(String, boolean, ClassLoader)
     */
    Class<?> loadClass(String name,
                       boolean resolve,
                       boolean checkGlobal,
                       boolean checkLibraries) throws ClassNotFoundException;

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 初始化此已配置的插件类加载器与传入的 Java 插件，使二者相互关联。该逻辑应在类加载器与实例化的 {@link JavaPlugin} 之间的初始设置加载时恰好调用一次。
     * <p>原文：
     * Initializes both this configured plugin class loader and the java plugin passed to link to each other.
     * This logic is to be called exactly once when the initial setup between the class loader and the instantiated
     * {@link JavaPlugin} is loaded.
     *
     * @param plugin 应与此类加载器互相链接的 {@link JavaPlugin}。
     * <p>原文：the {@link JavaPlugin} that should be interlinked with this class loader.
     */
    void init(JavaPlugin plugin);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此类加载器持有的插件。
     * <p>原文：
     * Gets the plugin held by this class loader.
     *
     * @return 插件；如果尚不存在则为 null
     * <p>原文：the plugin or null if it doesn't exist yet
     */
    @Nullable JavaPlugin getPlugin();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取底层类加载器所使用的插件类加载器组
     * <p>原文：
     * Get the plugin classloader group
     * that is used by the underlying classloader
     * @return 类加载器
     * <p>原文：classloader
     */
    @Nullable PluginClassLoaderGroup getGroup();
}
