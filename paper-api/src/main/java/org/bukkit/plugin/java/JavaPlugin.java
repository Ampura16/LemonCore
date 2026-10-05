package org.bukkit.plugin.java;

import com.google.common.base.Preconditions;
import io.papermc.paper.command.brigadier.BasicCommand;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.generator.BiomeProvider;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.PluginBase;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginLoader;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个 Java 插件及其主类. 其包含了插件正常加载并工作所需的基础方法和字段.
 * 这是一个 {@link org.bukkit.plugin.Plugin} 的间接实现.
 * <p>原文：
 * Represents a Java plugin and its main class. It contains fundamental methods
 * and fields for a plugin to be loaded and work properly. This is an indirect
 * implementation of {@link org.bukkit.plugin.Plugin}.
 */
@NullMarked
public abstract class JavaPlugin extends PluginBase {
    private boolean isEnabled = false;
    private PluginLoader loader = null;
    private Server server = null;
    private File file = null;
    private PluginDescriptionFile description = null;
    private io.papermc.paper.plugin.configuration.PluginMeta pluginMeta = null;
    private File dataFolder = null;
    private ClassLoader classLoader = null;
    private boolean naggable = true;
    private FileConfiguration newConfig = null;
    private File configFile = null;
    private Logger logger = null;
    @SuppressWarnings("deprecation")
    private final io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager<org.bukkit.plugin.Plugin> lifecycleEventManager = org.bukkit.Bukkit.getUnsafe().createPluginLifecycleEventManager(this, () -> this.allowsLifecycleRegistration);
    private boolean allowsLifecycleRegistration = true;
    private boolean isBeingEnabled = false;

    public JavaPlugin() {
        if (this.getClass().getClassLoader() instanceof io.papermc.paper.plugin.provider.classloader.ConfiguredPluginClassLoader configuredPluginClassLoader) {
            configuredPluginClassLoader.init(this);
        } else {
            throw new IllegalStateException("JavaPlugin requires to be created by a valid classloader.");
        }
    }

    @Deprecated(forRemoval = true)
    protected JavaPlugin(final JavaPluginLoader loader, final PluginDescriptionFile description, final File dataFolder, final File file) {
        final ClassLoader classLoader = this.getClass().getClassLoader();
        if (classLoader instanceof PluginClassLoader) {
            throw new IllegalStateException("Cannot use initialization constructor at runtime");
        }
        init(loader, loader.server, description, dataFolder, file, classLoader);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回存放插件文件数据的文件夹.
     * 文件夹可能不存在.
     * <p>原文：
     * Returns the folder that the plugin data files are located in. The
     * folder may not yet exist.
     *
     * @return 文件夹
     * <p>原文：The folder.
     */
    @Override
    public final File getDataFolder() {
        return dataFolder;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个插件关联的PluginLoader.
     * <p>原文：
     * Gets the associated PluginLoader responsible for this plugin
     *
     * @return PluginLoader控制的插件.
     * <p>原文：PluginLoader that controls this plugin
     * @deprecated Plugin loading now occurs at a point which makes it impossible to expose this
     * behavior. This instance will only throw unsupported operation exceptions.
     */
    @Override
    @Deprecated(forRemoval = true)
    public final PluginLoader getPluginLoader() {
        return loader;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回正在运行此插件的服务器对象.
     * <p>原文：
     * Returns the Server instance currently running this plugin
     *
     * @return 服务器正在运行的插件
     * <p>原文：Server running this plugin
     */
    @Override
    public final Server getServer() {
        return server;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回插件是否已正常启用.
     * <p>原文：
     * Returns a value indicating whether this plugin is currently enabled
     *
     * @return 插件是否启用
     * <p>原文：true if this plugin is enabled, otherwise false
     */
    @Override
    public final boolean isEnabled() {
        return isEnabled;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个插件的文件.
     * <p>原文：
     * Returns the file which contains this plugin
     *
     * @return 插件的核心文件
     * <p>原文：File containing this plugin
     */
    protected File getFile() {
        return file;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回插件的plugin.yml的内容的描述信息.
     * <p>原文：
     * Returns the plugin.yml file containing the details for this plugin
     *
     * @return plugin.yml的内容描述信息
     * <p>原文：Contents of the plugin.yml file
     * @deprecated No longer applicable to all types of plugins
     */
    @Override
    @Deprecated
    public final PluginDescriptionFile getDescription() {
        return description;
    }

    public final io.papermc.paper.plugin.configuration.PluginMeta getPluginMeta() {
        return this.pluginMeta;
    }

    @Override
    public FileConfiguration getConfig() {
        if (newConfig == null) {
            reloadConfig();
        }
        return newConfig;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 提供jar中文本文件的读取器.
     * <p>
     * 返回的 reader 将以 UTF-8 编码读取文本.
     * <p>原文：
     * Provides a reader for a text file located inside the jar.
     * <p>
     * The returned reader will read text with the UTF-8 charset.
     *
     * @param file 需要加载的文本资源文件名
     * <p>原文：the filename of the resource to load
     * @return null 如果 {@link #getResource(String)} 返回 null
     * <p>原文：null if {@link #getResource(String)} returns null
     * @throws IllegalArgumentException 如果文件为 null
     * <p>原文：if file is null
     * @see ClassLoader#getResourceAsStream(String)
     */
    protected final @Nullable Reader getTextResource(String file) {
        final InputStream in = getResource(file);

        return in == null ? null : new InputStreamReader(in, StandardCharsets.UTF_8);
    }

    @Override
    public void reloadConfig() {
        newConfig = YamlConfiguration.loadConfiguration(configFile);

        final InputStream defConfigStream = getResource("config.yml");
        if (defConfigStream == null) {
            return;
        }

        newConfig.setDefaults(YamlConfiguration.loadConfiguration(new InputStreamReader(defConfigStream, StandardCharsets.UTF_8)));
    }

    @Override
    public void saveConfig() {
        try {
            getConfig().save(configFile);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Could not save config to " + configFile, ex);
        }
    }

    @Override
    public void saveDefaultConfig() {
        if (!configFile.exists()) {
            saveResource("config.yml", false);
        }
    }

    @Override
    public void saveResource(String resourcePath, boolean replace) {
        if (resourcePath == null || resourcePath.equals("")) {
            throw new IllegalArgumentException("ResourcePath cannot be null or empty");
        }

        resourcePath = resourcePath.replace('\\', '/');
        InputStream in = getResource(resourcePath);
        if (in == null) {
            throw new IllegalArgumentException("The embedded resource '" + resourcePath + "' cannot be found in " + file);
        }

        File outFile = new File(dataFolder, resourcePath);
        int lastIndex = resourcePath.lastIndexOf('/');
        File outDir = new File(dataFolder, resourcePath.substring(0, lastIndex >= 0 ? lastIndex : 0));

        if (!outDir.exists()) {
            outDir.mkdirs();
        }

        try {
            if (!outFile.exists() || replace) {
                OutputStream out = new FileOutputStream(outFile);
                byte[] buf = new byte[1024];
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }
                out.close();
                in.close();
            } else {
                logger.log(Level.WARNING, "Could not save " + outFile.getName() + " to " + outFile + " because " + outFile.getName() + " already exists.");
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Could not save " + outFile.getName() + " to " + outFile, ex);
        }
    }

    @Override
    public @Nullable InputStream getResource(String filename) {
        if (filename == null) {
            throw new IllegalArgumentException("Filename cannot be null");
        }

        try {
            URL url = getClassLoader().getResource(filename);

            if (url == null) {
                return null;
            }

            URLConnection connection = url.openConnection();
            connection.setUseCaches(false);
            return connection.getInputStream();
        } catch (IOException ex) {
            return null;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个插件的ClassLoader.
     * <p>原文：
     * Returns the ClassLoader which holds this plugin
     *
     * @return ClassLoader控制的这个插件
     * <p>原文：ClassLoader holding this plugin
     */
    protected final ClassLoader getClassLoader() {
        return classLoader;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置插件开启的状态.
     * <p>原文：
     * Sets the enabled state of this plugin
     *
     * @param enabled true表示设置插件为开启, false就是设置插件为关闭
     * <p>原文：true if enabled, otherwise false
     */
    @org.jetbrains.annotations.ApiStatus.Internal
    public final void setEnabled(final boolean enabled) {
        if (isEnabled != enabled) {
            isEnabled = enabled;

            if (isEnabled) {
                this.isBeingEnabled = true;
                try {
                    onEnable();
                } finally {
                    this.allowsLifecycleRegistration = false;
                    this.isBeingEnabled = false;
                }
            } else {
                onDisable();
            }
        }
    }

    private static class DummyPluginLoaderImplHolder {
        private static final PluginLoader INSTANCE =  net.kyori.adventure.util.Services.service(PluginLoader.class)
            .orElseThrow();
    }
    public final void init(PluginLoader loader, Server server, PluginDescriptionFile description, File dataFolder, File file, ClassLoader classLoader) {
        init(server, description, dataFolder, file, classLoader, description, com.destroystokyo.paper.utils.PaperPluginLogger.getLogger(description));
        this.pluginMeta = description;
    }
    public final void init(Server server, PluginDescriptionFile description, File dataFolder, File file, ClassLoader classLoader, io.papermc.paper.plugin.configuration.@Nullable PluginMeta configuration, Logger logger) {
        this.loader = DummyPluginLoaderImplHolder.INSTANCE;
        this.server = server;
        this.file = file;
        this.description = description;
        this.dataFolder = dataFolder;
        this.classLoader = classLoader;
        this.configFile = new File(dataFolder, "config.yml");
        this.pluginMeta = configuration;
        this.logger = logger;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public @Nullable List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return null;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个插件在plugin.yml里注册的命令
     * 命令需要在{@link PluginDescriptionFile#getCommands()
     * PluginDescriptionFile}里已被注册
     * <p>原文：
     * Gets the command with the given name, specific to this plugin. Commands
     * need to be registered in the {@link PluginDescriptionFile#getCommands()
     * PluginDescriptionFile} to exist at runtime.
     *
     * @param name 一个属于这个插件注册了的命令
     * <p>原文：name or alias of the command
     * @return 如果有返回值表示命令存在, 否则返回null
     * <p>原文：the plugin command if found, otherwise null
     * @throws UnsupportedOperationException if this plugin is a paper plugin and the method is called in {@link #onEnable()}
     * @see #registerCommand(String, String, Collection, BasicCommand)
     */
    public @Nullable PluginCommand getCommand(String name) {
        if (this.isBeingEnabled && !(pluginMeta instanceof PluginDescriptionFile)) {
            throw new UnsupportedOperationException("""
                You are trying to call JavaPlugin#getCommand on a Paper plugin during startup:
                you are probably trying to get a command you tried to define in paper-plugin.yml.
                Paper plugins do not support YAML-based command declarations!
                You can use JavaPlugin#registerCommand to define commands in Paper plugins.
                Please check the documentation for more information on how to define commands in Paper plugins: https://docs.papermc.io/paper/dev/getting-started/paper-plugins#commands
                """);
        }
        String alias = name.toLowerCase(Locale.ROOT);
        PluginCommand command = getServer().getPluginCommand(alias);

        if (command == null || command.getPlugin() != this) {
            command = getServer().getPluginCommand(description.getName().toLowerCase(Locale.ROOT) + ":" + alias);
        }

        if (command != null && command.getPlugin() == this) {
            return command;
        } else {
            return null;
        }
    }

    /**
     * Registers a command for this plugin. Only valid to be called inside {@link #onEnable()}.
     *
     * <p>Commands have certain overriding behavior:
     * <ul>
     *   <li>Aliases will not override already existing commands (excluding namespaced ones)</li>
     *   <li>Aliases are <b>not</b> Brigadier redirects, they just copy the command to a different label</li>
     *   <li>The main command/namespaced label will override already existing commands</li>
     * </ul>
     *
     * @param label        the label of the to-be-registered command
     * @param basicCommand the basic command instance to register
     * @see LifecycleEvents#COMMANDS
     */
    public void registerCommand(final String label, final BasicCommand basicCommand) {
        this.registerCommand(label, null, Collections.emptyList(), basicCommand);
    }

    /**
     * Registers a command for this plugin. Only valid to be called inside {@link #onEnable()}.
     *
     * <p>Commands have certain overriding behavior:
     * <ul>
     *   <li>Aliases will not override already existing commands (excluding namespaced ones)</li>
     *   <li>Aliases are <b>not</b> Brigadier redirects, they just copy the command to a different label</li>
     *   <li>The main command/namespaced label will override already existing commands</li>
     * </ul>
     *
     * @param label        the label of the to-be-registered command
     * @param description  the help description for the root literal node
     * @param basicCommand the basic command instance to register
     * @see LifecycleEvents#COMMANDS
     */
    public void registerCommand(final String label, final @Nullable String description, final BasicCommand basicCommand) {
        this.registerCommand(label, description, Collections.emptyList(), basicCommand);
    }

    /**
     * Registers a command for this plugin. Only valid to be called inside {@link #onEnable()}.
     *
     * <p>Commands have certain overriding behavior:
     * <ul>
     *   <li>Aliases will not override already existing commands (excluding namespaced ones)</li>
     *   <li>Aliases are <b>not</b> Brigadier redirects, they just copy the command to a different label</li>
     *   <li>The main command/namespaced label will override already existing commands</li>
     * </ul>
     *
     * @param label        the label of the to-be-registered command
     * @param aliases      a collection of aliases to register the basic command under.
     * @param basicCommand the basic command instance to register
     * @see LifecycleEvents#COMMANDS
     */
    public void registerCommand(final String label, final Collection<String> aliases, final BasicCommand basicCommand) {
        this.registerCommand(label, null, aliases, basicCommand);
    }

    /**
     * Registers a command for this plugin. Only valid to be called inside {@link #onEnable()}.
     *
     * <p>Commands have certain overriding behavior:
     * <ul>
     *   <li>Aliases will not override already existing commands (excluding namespaced ones)</li>
     *   <li>Aliases are <b>not</b> Brigadier redirects, they just copy the command to a different label</li>
     *   <li>The main command/namespaced label will override already existing commands</li>
     * </ul>
     *
     * @param label        the label of the to-be-registered command
     * @param description  the help description for the root literal node
     * @param aliases      a collection of aliases to register the basic command under.
     * @param basicCommand the basic command instance to register
     * @see LifecycleEvents#COMMANDS
     */
    public void registerCommand(final String label, final @Nullable String description, final Collection<String> aliases, final BasicCommand basicCommand) {
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, event -> {
            event.registrar().register(label, description, aliases, basicCommand);
        });
    }

    @Override
    public void onLoad() {}

    @Override
    public void onDisable() {}

    @Override
    public void onEnable() {}

    @Override
    public @Nullable ChunkGenerator getDefaultWorldGenerator(String worldName, @Nullable String id) {
        return null;
    }

    @Override
    public @Nullable BiomeProvider getDefaultBiomeProvider(String worldName, @Nullable String id) {
        return null;
    }

    @Override
    public final boolean isNaggable() {
        return naggable;
    }

    @Override
    public final void setNaggable(boolean canNag) {
        this.naggable = canNag;
    }

    @Override
    public Logger getLogger() {
        return logger;
    }

    @Override
    public String toString() {
        return description.getFullName();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 本方法可快速访问某个插件主类(或其子类)对应的插件实例.
     * <p>
     * 例外:如果插件jar中的插件类不继承JavaPlugin类, 将可能是不同的jar/类加载器.
     * <p>
     * 译注:这个怎么用呢? 比如我们知道某个插件主类叫 BukkitPluginMain,
     * 如果要获取其实例, 就可以这样做:
     * <pre>
     * BukkitPluginMain plugin = JavaPlugin.getPlugin(BukkitPluginMain.class);
     * </pre>
     * <p>原文：
     * This method provides fast access to the plugin that has {@link
     * #getProvidingPlugin(Class) provided} the given plugin class, which is
     * usually the plugin that implemented it.
     * <p>
     * An exception to this would be if plugin's jar that contained the class
     * does not extend the class, where the intended plugin would have
     * resided in a different jar / classloader.
     *
     * @param <T> 任何一个继承了JavaPlugin的类
     * <p>原文：a class that extends JavaPlugin
     * @param clazz 所需的类
     * <p>原文：the class desired
     * @return 提供并实现了给定类的插件的实例
     * <p>原文：the plugin that provides and implements said class
     * @throws IllegalArgumentException 如果参数 clazz 为 nul
     * <p>原文：if clazz is null
     * @throws IllegalArgumentException 如果参数 clazz 为 nul
     * <p>原文：if clazz does not extend {@link
     *     JavaPlugin}
     * @throws IllegalStateException 如果这个类不是插件提供的,抛出无效的状态(IllegalStateException)错误.
     *     比如, 如果调用
     *     <code>JavaPlugin.getPlugin(JavaPlugin.class)</code>
     * <p>原文：if clazz was not provided by a plugin,
     *     for example, if called with
     *     <code>JavaPlugin.getPlugin(JavaPlugin.class)</code>
     * @throws IllegalStateException 如果这个类不是插件提供的,抛出无效的状态(IllegalStateException)错误.
     *     比如, 如果调用
     *     <code>JavaPlugin.getPlugin(JavaPlugin.class)</code>
     * <p>原文：if called from the static initializer for
     *     given JavaPlugin
     * @throws ClassCastException 如果插件提供的类没有继承类, 抛出(ClassCastException)错误
     * <p>原文：if plugin that provided the class does not
     *     extend the class
     */
    public static <T extends JavaPlugin> T getPlugin(Class<T> clazz) {
        Preconditions.checkArgument(clazz != null, "Null class cannot have a plugin");
        if (!JavaPlugin.class.isAssignableFrom(clazz)) {
            throw new IllegalArgumentException(clazz + " does not extend " + JavaPlugin.class);
        }
        final ClassLoader cl = clazz.getClassLoader();
        if (!(cl instanceof io.papermc.paper.plugin.provider.classloader.ConfiguredPluginClassLoader configuredPluginClassLoader)) {
            throw new IllegalArgumentException(clazz + " is not initialized by a " + io.papermc.paper.plugin.provider.classloader.ConfiguredPluginClassLoader.class);
        }
        JavaPlugin plugin = configuredPluginClassLoader.getPlugin();
        if (plugin == null) {
            throw new IllegalStateException("Cannot get plugin for " + clazz + " from a static initializer");
        }
        return clazz.cast(plugin);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取提供给定类的插件.
     * <p>原文：
     * This method provides fast access to the plugin that has provided the
     * given class.
     *
     * @param clazz 属于某个插件的类
     * <p>原文：a class belonging to a plugin
     * @return 提供此类的插件
     * <p>原文：the plugin that provided the class
     * @throws IllegalArgumentException 如果这个类不属于某个JavaPlugin
     * <p>原文：if the class is not provided by a
     *     JavaPlugin
     * @throws IllegalArgumentException 如果这个类不属于某个JavaPlugin
     * <p>原文：if class is null
     * @throws IllegalStateException 如果从静态初始化代码块调用
     * <p>原文：if called from the static initializer for
     *     given JavaPlugin
     */
    public static JavaPlugin getProvidingPlugin(Class<?> clazz) {
        Preconditions.checkArgument(clazz != null, "Null class cannot have a plugin");
        final ClassLoader cl = clazz.getClassLoader();
        if (!(cl instanceof io.papermc.paper.plugin.provider.classloader.ConfiguredPluginClassLoader configuredPluginClassLoader)) {
            throw new IllegalArgumentException(clazz + " is not provided by a " + io.papermc.paper.plugin.provider.classloader.ConfiguredPluginClassLoader.class);
        }
        JavaPlugin plugin = configuredPluginClassLoader.getPlugin();
        if (plugin == null) {
            throw new IllegalStateException("Cannot get plugin for " + clazz + " from a static initializer");
        }
        return plugin;
    }

    @Override
    public final io.papermc.paper.plugin.lifecycle.event.LifecycleEventManager<org.bukkit.plugin.Plugin> getLifecycleManager() {
        return this.lifecycleEventManager;
    }
}
