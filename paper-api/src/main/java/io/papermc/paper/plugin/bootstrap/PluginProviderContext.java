package io.papermc.paper.plugin.bootstrap;

import io.papermc.paper.plugin.configuration.PluginMeta;
import java.nio.file.Path;
import net.kyori.adventure.text.logger.slf4j.ComponentLogger;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示在引导和插件实例化逻辑期间提供给 {@link PluginBootstrap} 的上下文。引导上下文可用于在插件引导期间访问通常提供给 {@link org.bukkit.plugin.Plugin} 实例的数据或逻辑，例如插件的配置或日志记录器。
 * <p>原文：
 * Represents the context provided to a {@link PluginBootstrap} during both the bootstrapping and plugin
 * instantiation logic.
 * A bootstrap context may be used to access data or logic usually provided to {@link org.bukkit.plugin.Plugin} instances
 * like the plugin's configuration or logger during the plugins bootstrap.
 */
@ApiStatus.Experimental
@NullMarked
@ApiStatus.NonExtendable
public interface PluginProviderContext {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供插件的配置。
     * <p>原文：
     * Provides the plugin's configuration.
     *
     * @return 插件的配置
     * <p>原文：the plugin's configuration
     */
    PluginMeta getConfiguration();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供插件数据目录的路径。
     * <p>原文：
     * Provides the path to the data directory of the plugin.
     *
     * @return 先前描述的路径
     * <p>原文：the previously described path
     */
    Path getDataDirectory();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此插件使用的日志记录器。
     * <p>原文：
     * Provides the logger used for this plugin.
     *
     * @return 日志记录器实例
     * <p>原文：the logger instance
     */
    ComponentLogger getLogger();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供插件来源的路径，例如插件的 JAR 文件。
     * <p>原文：
     * Provides the path to the originating source of the plugin, such as the plugin's JAR file.
     *
     * @return 先前描述的路径
     * <p>原文：the previously described path
     */
    Path getPluginSource();

}
