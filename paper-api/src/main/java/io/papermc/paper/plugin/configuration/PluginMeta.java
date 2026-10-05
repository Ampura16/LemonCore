package io.papermc.paper.plugin.configuration;

import java.util.List;
import java.util.Locale;
import net.kyori.adventure.key.KeyPattern;
import net.kyori.adventure.key.Namespaced;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.PluginLoadOrder;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 该类是插件配置的抽象。
 * <p>原文：
 * This class acts as an abstraction for a plugin configuration.
 */
@NullMarked
@ApiStatus.NonExtendable
public interface PluginMeta extends Namespaced {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供插件的名称。该名称在服务器上所有已加载插件中唯一标识此插件。<ul> <li>只会包含字母数字、下划线、连字符和句点：[a-zA-Z0-9_\-\.]。<li>通常用于标识插件数据文件夹。<li>该名称也是 {@link #getPluginDependencies()}、{@link #getPluginSoftDependencies()} 和 {@link #getLoadBeforePlugins()} 中引用的标记。</ul> <p> 在 plugin.yml 中，该条目名为 <code>name</code>。<p> 示例：<blockquote><pre>name: MyPlugin</pre></blockquote>
     * <p>原文：
     * Provides the name of the plugin. This name uniquely identifies the plugin amongst all loaded plugins on the
     * server.
     * <ul>
     * <li>Will only contain alphanumeric characters, underscores, hyphens,
     *     and periods: [a-zA-Z0-9_\-\.].
     * <li>Typically used for identifying the plugin data folder.
     * <li>The name also acts as the token referenced in {@link #getPluginDependencies()},
     * {@link #getPluginSoftDependencies()}, and {@link #getLoadBeforePlugins()}.
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>name</code>.
     * <p>
     * Example:<blockquote><pre>name: MyPlugin</pre></blockquote>
     *
     * @return 插件名称
     * <p>原文：the name of the plugin
     */
    String getName();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回插件的显示名称，包括版本。
     * <p>原文：
     * Returns the display name of the plugin, including the version.
     *
     * @return 插件及其版本的描述性名称
     * <p>原文：a descriptive name of the plugin and respective version
     */
    default String getDisplayName() {
        return this.getName() + " v" + this.getVersion();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供插件主类的全限定类名。该位置应为 {@link JavaPlugin} 的子类型。
     * <p>原文：
     * Provides the fully qualified class name of the main class for the plugin.
     * A subtype of {@link JavaPlugin} is expected at this location.
     *
     * @return 插件主类的全限定类名。
     * <p>原文：the fully qualified class name of the plugin's main class.
     */
    String getMainClass();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回插件应在服务器启动逻辑的哪个阶段加载。
     * <p>原文：
     * Returns the phase of the server startup logic that the plugin should be loaded.
     *
     * @return 插件加载顺序
     * <p>原文：the plugin load order
     * @see PluginLoadOrder for further details regards the available load orders.
     */
    PluginLoadOrder getLoadOrder();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供插件自身定义的版本。插件版本没有固有的格式定义或强制要求，但常见做法是采用语义化版本。
     * <p>原文：
     * Provides the version of this plugin as defined by the plugin.
     * There is no inherit format defined/enforced for the version of a plugin, however a common approach
     * might be semantic versioning.
     *
     * @return 插件版本的字符串表示
     * <p>原文：the string representation of the plugin's version
     */
    String getVersion();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供插件日志记录器应使用的前缀。日志记录器前缀允许插件覆盖日志记录器前缀的通常默认值（即插件名称）。
     * <p>原文：
     * Provides the prefix that should be used for the plugin logger.
     * The logger prefix allows plugins to overwrite the usual default of the logger prefix, which is the name of the
     * plugin.
     *
     * @return 插件定义的日志前缀的特定覆盖。如果插件未定义自定义日志前缀，此方法将返回 null
     * <p>原文：the specific overwrite of the logger prefix as defined by the plugin. If the plugin did not define a
     *     custom logger prefix, this method will return null
     */
    @Nullable String getLoggerPrefix();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此插件加载所必需的依赖列表。列表包含依赖的唯一标识符，遵循 {@link #getName()} 中规定的约束。
     * <p>
     * 如果此列表定义的任何依赖未安装在服务器上，此插件将加载失败。
     * <p>原文：
     * Provides a list of dependencies that are required for this plugin to load.
     * The list holds the unique identifiers, following the constraints laid out in {@link #getName()}, of the
     * dependencies.
     * <p>
     * If any of the dependencies defined by this list are not installed on the server, this plugin will fail to load.
     *
     * @return 必需依赖名称的不可变列表
     * <p>原文：an immutable list of required dependency names
     */
    List<String> getPluginDependencies();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此插件使用但非必需的依赖列表。列表包含软依赖的唯一标识符，遵循 {@link #getName()} 中规定的约束。
     * <p>
     * 如果这些依赖已安装在服务器上，它们将先被加载并作为依赖提供给此插件；但即使未安装这些依赖，插件也会加载。
     * <p>原文：
     * Provides a list of dependencies that are used but not required by this plugin.
     * The list holds the unique identifiers, following the constraints laid out in {@link #getName()}, of the soft
     * dependencies.
     * <p>
     * If these dependencies are installed on the server, they will be loaded first and supplied as dependencies to this
     * plugin, however the plugin will load even if these dependencies are not installed.
     *
     * @return 软依赖的不可变列表
     * <p>原文：immutable list of soft dependencies
     */
    List<String> getPluginSoftDependencies();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供应在此插件之前加载的插件列表。列表包含应在此插件元数据所描述的插件之前加载的插件的唯一标识符，遵循 {@link #getName()} 中规定的约束。
     * <p>
     * 该方法所提供列表中引用的插件不被视为此插件的依赖，因此在运行时对此插件不可用，它们只是在此插件之前加载。
     * <p>原文：
     * Provides a list of plugins that should be loaded before this plugin is loaded.
     * The list holds the unique identifiers, following the constraints laid out in {@link #getName()}, of the
     * plugins that should be loaded before the plugin described by this plugin meta.
     * <p>
     * The plugins referenced in the list provided by this method are not considered dependencies of this plugin and
     * are hence not available to the plugin at runtime. They merely load before this plugin.
     *
     * @return 在此插件之前加载的插件的不可变列表
     * <p>原文：immutable list of plugins to load before this plugin
     */
    List<String> getLoadBeforePlugins();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此插件提供的插件/依赖列表。列表包含其为之提供预期类的每个插件的唯一标识符，遵循 {@link #getName()} 中规定的约束。
     * <p>原文：
     * Returns the list of plugins/dependencies that this plugin provides.
     * The list holds the unique identifiers, following the constraints laid out in {@link #getName()}, for each plugin
     * it provides the expected classes for.
     *
     * @return 所提供插件/依赖的不可变列表
     * <p>原文：immutable list of provided plugins/dependencies
     */
    List<String> getProvidedPlugins();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供被认定为此插件创作者的作者列表。作者名称没有特定格式。
     * <p>原文：
     * Provides the list of authors that are credited with creating this plugin.
     * The author names are in no particular format.
     *
     * @return 插件作者的不可变列表
     * <p>原文：an immutable list of the plugin's authors
     */
    List<String> getAuthors();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供为插件做出贡献但不被视为作者的贡献者列表。贡献者名称没有特定格式。
     * <p>原文：
     * Provides a list of contributors that contributed to the plugin but are not considered authors.
     * The names of the contributors are in no particular format.
     *
     * @return 插件贡献者的不可变列表
     * <p>原文：an immutable list of the plugin's contributors
     */
    List<String> getContributors();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 对插件所提供功能的易读描述。
     * <p>原文：
     * Gives a human-friendly description of the functionality the plugin
     * provides.
     *
     * @return 描述；如果插件未定义易读的描述则为 null。
     * <p>原文：description or null if the plugin did not define a human readable description.
     */
    @Nullable String getDescription();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供插件或插件作者的网站。所定义的字符串值<b>不保证</b>是 URL 形式。
     * <p>原文：
     * Provides the website for the plugin or the plugin's author.
     * The defined string value is <b>not guaranteed</b> to be in the form of a url.
     *
     * @return 作为此插件/其作者主要站点的网站的字符串表示。
     * <p>原文：a string representation of the website that serves as the main hub for this plugin/its author.
     */
    @Nullable String getWebsite();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供通过插件元数据实例定义的权限列表。
     * <p>原文：
     * Provides the list of permissions that are defined via the plugin meta instance.
     *
     * @return 权限的不可变列表
     * <p>原文：an immutable list of permissions
     */
    List<Permission> getPermissions();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供适用于此插件元数据中所定义权限的默认值。
     * <p>原文：
     * Provides the default values that apply to the permissions defined in this plugin meta.
     *
     * @return Bukkit 权限默认值容器。
     * <p>原文：the bukkit permission default container.
     * @see #getPermissions()
     */
    PermissionDefault getPermissionDefault();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此插件支持的 API 版本。未指定版本时可为 null，此时应视为旧版插件（仅限 Spigot 插件）
     * <p>原文：
     * Gets the api version that this plugin supports.
     * Nullable if this version is not specified, and should be
     * considered legacy (spigot plugins only)
     *
     * @return 由主版本号和次版本号组成的版本字符串（例如 1.18 或 1.19）。1.18.2 这样的小版本会被统一为其主发布版本（此例中为 1.18）
     * <p>原文：the version string made up of the major and minor version (e.g. 1.18 or 1.19). Minor versions like 1.18.2
     * are unified to their major release version (in this example 1.18)
     */
    @Nullable String getAPIVersion();

    @KeyPattern.Namespace
    @SuppressWarnings("PatternValidation")
    @Override
    default String namespace() {
        return this.getName().toLowerCase(Locale.ROOT);
    }
}
