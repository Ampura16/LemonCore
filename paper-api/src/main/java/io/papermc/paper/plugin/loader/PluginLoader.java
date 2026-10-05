package io.papermc.paper.plugin.loader;

import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 插件加载器负责在插件创建之前创建插件的某些方面。
 * <p>
 * 插件加载器的目标是为插件创建预期的/动态的加载环境。目前这仅适用于为插件创建预期的类路径，例如向插件提供外部库。
 * <p>
 * 需要注意，该类将由另一个类加载器调用，因此在该类或其他已加载类中设置的任何静态值在插件加载时都不会保留。
 * <p>原文：
 * A plugin loader is responsible for creating certain aspects of a plugin before it is created.
 * <p>
 * The goal of the plugin loader is the creation of an expected/dynamic environment for the plugin to load into.
 * This, as of right now, only applies to creating the expected classpath for the plugin, e.g. supplying external
 * libraries to the plugin.
 * <p>
 * It should be noted that this class will be called from a different classloader, this will cause any static values
 * set in this class/any other classes loaded not to persist when the plugin loads.
 */
@ApiStatus.Experimental
@NullMarked
@ApiStatus.OverrideOnly
public interface PluginLoader {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 由服务器调用，允许插件配置其运行时类路径。这使插件加载器能够为插件配置依赖，相关 jar 可在运行时下载或提供。
     * <p>原文：
     * Called by the server to allows plugins to configure the runtime classpath that the plugin is run on.
     * This allows plugin loaders to configure dependencies for the plugin where jars can be downloaded or
     * provided during runtime.
     *
     * @param classpathBuilder 可变的类路径构建器，可用于为注册该加载器的插件注册自定义运行时依赖。
     * <p>原文：a mutable classpath builder that may be used to register custom runtime dependencies
     *                         for the plugin the loader was registered for.
     */
    void classloader(PluginClasspathBuilder classpathBuilder);

}
