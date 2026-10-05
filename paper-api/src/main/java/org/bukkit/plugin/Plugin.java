package org.bukkit.plugin;

import java.io.File;
import java.io.InputStream;
import java.util.logging.Logger;
import net.kyori.adventure.key.Namespaced;
import org.bukkit.Server;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示一个插件.
 * <p>
 * 这只是一个接口,若要实际上实现该接口,建议使用 {@link PluginBase}.
 * <p>原文：
 * Represents a Plugin
 * <p>
 * The use of {@link PluginBase} is recommended for actual Implementation
 */
public interface Plugin extends TabExecutor, io.papermc.paper.plugin.lifecycle.event.LifecycleEventOwner, Namespaced { // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回插件的数据文件夹, 该文件夹可能尚未创建.
     * <p>原文：
     * Returns the folder that the plugin data files are located in. The
     * folder may not yet exist.
     *
     * @return 此文件夹
     * <p>原文：The folder
     */
    @NotNull
    public File getDataFolder();

    // Paper start - add getDataPath()
    /**
     * Returns the path that the plugin data files are located in.
     * The folder may not yet exist.
     *
     * @see #getDataFolder()
     * @return The folder
     */
    default @NotNull java.nio.file.Path getDataPath() {
        return getDataFolder().toPath();
    }
    // Paper end - add getDataPath()

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回包含该插件详细信息的 plugin.yaml 文件.
     * <p>原文：
     * Returns the plugin.yml file containing the details for this plugin
     *
     * @return plugin.yaml文件内容
     * <p>原文：Contents of the plugin.yml file
     * @deprecated May be inaccurate due to different plugin implementations.
     * @see Plugin#getPluginMeta()
     */
    @Deprecated // Paper
    @NotNull
    public PluginDescriptionFile getDescription();

    // Paper start
    /**
     * Gets the plugin meta for this plugin.
     * @return configuration
     */
    @NotNull
    io.papermc.paper.plugin.configuration.PluginMeta getPluginMeta();
    // Paper end
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个插件的{@link FileConfiguration}实例, 它将读取config.yml中的内容.
     * <p>
     * 如果这个插件中含有config.yml文件, 那么这个config.yml将作为默认的配置文件.
     * <p>原文：
     * Gets a {@link FileConfiguration} for this plugin, read through
     * "config.yml"
     * <p>
     * If there is a default config.yml embedded in this plugin, it will be
     * provided as a default for this Configuration.
     *
     * @return 此插件的配置
     * <p>原文：Plugin configuration
     */
    @NotNull
    public FileConfiguration getConfig();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此插件包中的资源.
     * <p>原文：
     * Gets an embedded resource in this plugin
     *
     * @param filename 资源文件名
     * <p>原文：Filename of the resource
     * @return 如果文件找到则返回相应的InputStream,否则为null
     * <p>原文：File if found, otherwise null
     */
    @Nullable
    public InputStream getResource(@NotNull String filename);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 保存插件的 {@link FileConfiguration}, 这可以使用{@link #getConfig()}获取.
     * <p>原文：
     * Saves the {@link FileConfiguration} retrievable by {@link #getConfig()}.
     */
    public void saveConfig();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将默认的config.yml文件数据保存到{@link #getConfig()}获取的config.yml所在的位置.<br>
     * 这不会覆盖已经存在的config.yml.
     * <p>原文：
     * Saves the raw contents of the default config.yml file to the location
     * retrievable by {@link #getConfig()}.
     * <p>
     * This should fail silently if the config.yml already exists.
     */
    public void saveDefaultConfig();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 保存内置于插件的.jar文件的某个资源的原始内容(假使该资源可以使用{@link #getResource(String)}找到).<br>
     * 保存于插件数据文件夹的资源的结构层次与.jar文件(内容)相同 (同时保存子目录).
     * <p>原文：
     * Saves the raw contents of any resource embedded with a plugin's .jar
     * file assuming it can be found using {@link #getResource(String)}.
     * <p>
     * The resource is saved into the plugin's data folder using the same
     * hierarchy as the .jar file (subdirectories are preserved).
     *
     * @param resourcePath 在插件的.jar文件中查找的内置资源路径.(没有前面的斜杠)
     * <p>原文：the embedded resource path to look for within the
     *     plugin's .jar file. (No preceding slash).
     * @param replace 如果为true，则内置的资源将覆盖现有文件的内容
     * <p>原文：if true, the embedded resource will overwrite the
     *     contents of an existing file.
     * @throws IllegalArgumentException 如果资源路径为null/空,或指向不存在的资源则抛出
     * <p>原文：if the resource path is null, empty,
     *     or points to a nonexistent resource.
     */
    public void saveResource(@NotNull String resourcePath, boolean replace);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 丢弃 {@link #getConfig()}中所有数据并且从磁盘重载.
     * <p>原文：
     * Discards any data in {@link #getConfig()} and reloads from disk.
     */
    public void reloadConfig();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取对此插件负责的关联PluginLoader.
     * <p>原文：
     * Gets the associated PluginLoader responsible for this plugin
     *
     * @return 控制插件的PluginLoader
     * <p>原文：PluginLoader that controls this plugin
     */
    @Deprecated(forRemoval = true) // Paper - The PluginLoader system will not function in the near future
    @NotNull
    public PluginLoader getPluginLoader();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回当前运行此插件的服务器实例.
     * <p>原文：
     * Returns the Server instance currently running this plugin
     *
     * @return 运行此插件的服务器
     * <p>原文：Server running this plugin
     */
    @NotNull
    public Server getServer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回一个布尔值,表示该插件此时是否已经启动.
     * <p>原文：
     * Returns a value indicating whether or not this plugin is currently
     * enabled
     *
     * @return 如果此插件已经启动,则为true,否则为false.
     * <p>原文：true if this plugin is enabled, otherwise false
     */
    public boolean isEnabled();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当这个插件被关闭时调用.
     * <p>原文：
     * Called when this plugin is disabled
     */
    public void onDisable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在一个插件被加载之后启动之前调用.<br>
     * 当有多个插件被加载时,确保所有插件的onLoad()全部调用完毕后才会调用onEnable().
     * <p>原文：
     * Called after a plugin is loaded but before it has been enabled.
     * <p>
     * When multiple plugins are loaded, the onLoad() for all plugins is
     * called before any onEnable() is called.
     */
    public void onLoad();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 启用此插件时调用.
     * <p>原文：
     * Called when this plugin is enabled
     */
    public void onEnable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用一个简单的布尔值表达 我们是否可以把一些东西记录到日志文件.
     * <p>原文：
     * Simple boolean if we can still nag to the logs about things
     *
     * @return 返回一个布尔值如果这个插件是否可以进行记录
     * <p>原文：boolean whether we can nag
     */
    public boolean isNaggable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 Naggable 状态
     * <p>原文：
     * Set naggable state
     *
     * @param canNag 这个插件是可以被记录的吗
     * <p>原文：is this plugin still naggable?
     */
    public void setNaggable(boolean canNag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在服务器配置中指定的默认世界中使用的{@link ChunkGenerator}.
     * <p>原文：
     * Gets a {@link ChunkGenerator} for use in a default world, as specified
     * in the server configuration
     *
     * @param worldName 将应用于世界的名称
     * <p>原文：Name of the world that this will be applied to
     * @param id 唯一 ID,如果有的话,被指定来指示哪个生成器被调用
     * <p>原文：Unique ID, if any, that was specified to indicate which
     *     generator was requested
     * @return 用于默认世界生成的 ChunkGenerator
     * <p>原文：ChunkGenerator for use in the default world generation
     */
    @Nullable
    public ChunkGenerator getDefaultWorldGenerator(@NotNull String worldName, @Nullable String id);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在服务器配置中指定的默认世界中使用的 {@link BiomeProvider}.
     * <p>原文：
     * Gets a {@link BiomeProvider} for use in a default world, as specified
     * in the server configuration
     *
     * @param worldName 将应用于世界的名称
     * <p>原文：Name of the world that this will be applied to
     * @param id 唯一 ID,如果有的话,被指定来指示哪个生物群系提供器被请求
     * <p>原文：Unique ID, if any, that was specified to indicate which
     *     biome provider was requested
     * @return 用于默认世界生成的 BiomeProvider
     * <p>原文：BiomeProvider for use in the default world generation
     */
    @Nullable
    public BiomeProvider getDefaultBiomeProvider(@NotNull String worldName, @Nullable String id);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回与此服务器日志记录器关联的插件日志记录器.返回的日志记录器自动地用插件的名称标记所有日志消息.
     * <p>原文：
     * Returns the plugin logger associated with this server's logger. The
     * returned logger automatically tags all log messages with the plugin's
     * name.
     *
     * @return 与此插件相关联的记录器
     * <p>原文：Logger associated with this plugin
     */
    @NotNull
    public Logger getLogger();

    // Paper start - Adventure component logger
    @NotNull
    default net.kyori.adventure.text.logger.slf4j.ComponentLogger getComponentLogger() {
        return net.kyori.adventure.text.logger.slf4j.ComponentLogger.logger(getLogger().getName());
    }
    // Paper end

    // Paper start - Add SLF4J/Log4J loggers
    @NotNull
    default org.slf4j.Logger getSLF4JLogger() {
        return org.slf4j.LoggerFactory.getLogger(getLogger().getName());
    }

    /**
     * @deprecated use {@link #getSLF4JLogger()}
     */
    @Deprecated
    @NotNull
    default org.apache.logging.log4j.Logger getLog4JLogger() {
        return org.apache.logging.log4j.LogManager.getLogger(getLogger().getName());
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回插件的名称.<br>
     * 这应该返回插件的无修饰名称,并且应该用于比较.
     * <p>原文：
     * Returns the name of the plugin.
     * <p>
     * This should return the bare name of the plugin and should be used for
     * comparison.
     *
     * @return 插件名称
     * <p>原文：name of the plugin
     */
    @NotNull
    public String getName();

    // Paper start - lifecycle events
    /**
     * Get the lifecycle event manager for registering handlers
     * for lifecycle events allowed on the {@link Plugin}.
     *
     * @return the lifecycle event manager
     */
    io.papermc.paper.plugin.lifecycle.event.@NotNull LifecycleEventManager<Plugin> getLifecycleManager();
    // Paper end - lifecycle events
}
