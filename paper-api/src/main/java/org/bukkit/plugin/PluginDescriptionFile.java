package org.bukkit.plugin;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import java.io.InputStream;
import java.io.Reader;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.TabCompleter;
import org.bukkit.permissions.Permissible;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.AbstractConstruct;
import org.yaml.snakeyaml.constructor.SafeConstructor;
import org.yaml.snakeyaml.nodes.Node;
import org.yaml.snakeyaml.nodes.Tag;
import org.yaml.snakeyaml.representer.Representer;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 这个类是用于储存插件plugin.yml内的数据.
 * 所有的插件都必须有自己的plugin.yml.对于插件来说 plguin.yml是必须使用的标准
 * 这个文件必须位于jar的根目录下.
 * <p>
 * 当Bukkit加载插件时,它必须知道一些基础的关于这个插件的信息.
 * Bukkit从plugin.yml读取数据.
 * plugin.yml由一组属性构成,每个属性位于单独的一行并且没有缩进.
 * <p>
 * 每一个方法(几乎每一个) 都在plugin.yml有其对应的条目.
 * 下面是每一个插件<b>所需</b>的条目.
 *
 * <ul>
 * <li>{@link #getName()} - <code>name</code>
 * <li>{@link #getVersion()} - <code>version</code>
 * <li>{@link #getMain()} - <code>main</code>
 * </ul>
 * <p>
 * 未能填写以上条目,将导致一个异常并且使Bukkit忽视你的插件.
 * <p>
 * 下面是plugin.yml可能拥有的条目表格,具体细节包括各自的方法:
 * <table border=1>
 * <caption>plugin.yml 布局说明</caption>
 * <tr>
 *     <th>条目</th>
 *     <th>方法</th>
 *     <th>概要</th>
 * </tr><tr>
 *     <td><code>name</code></td>
 *     <td>{@link #getName()}</td>
 *     <td>该插件的名字</td>
 * </tr><tr>
 *     <td><code>provides</code></td>
 *     <td>{@link #getProvides()}</td>
 *     <td>此插件提供的插件 API</td>
 * </tr><tr>
 *     <td><code>version</code></td>
 *     <td>{@link #getVersion()}</td>
 *     <td>插件的版本</td>
 * </tr><tr>
 *     <td><code>main</code></td>
 *     <td>{@link #getMain()}</td>
 *     <td>插件主类的位置</td>
 * </tr><tr>
 *     <td><code>author</code><br><code>authors</code></td>
 *     <td>{@link #getAuthors()}</td>
 *     <td>插件的作者们</td>
 * </tr><tr>
 *     <td><code>contributors</code></td>
 *     <td>{@link #getContributors()}</td>
 *     <td>插件的贡献者们</td>
 * </tr><tr>
 *     <td><code>description</code></td>
 *     <td>{@link #getDescription()}</td>
 *     <td>可读的插件描述</td>
 * </tr><tr>
 *     <td><code>website</code></td>
 *     <td>{@link #getWebsite()}</td>
 *     <td>插件的网址</td>
 * </tr><tr>
 *     <td><code>prefix</code></td>
 *     <td>{@link #getPrefix()}</td>
 *     <td>用于控制台的插件前缀</td>
 * </tr><tr>
 *     <td><code>load</code></td>
 *     <td>{@link #getLoad()}</td>
 *     <td>载入插件的时机</td>
 * </tr><tr>
 *     <td><code>depend</code></td>
 *     <td>{@link #getDepend()}</td>
 *     <td>必须的前置插件</td>
 * </tr><tr>
 *     <td><code>softdepend</code></td>
 *     <td>{@link #getSoftDepend()}</td>
 *     <td>非必须的前置插件</td>
 * </tr><tr>
 *     <td><code>loadbefore</code></td>
 *     <td>{@link #getLoadBefore()}</td>
 *     <td>反softdepend,可理解为在某些插件前加载</td>
 * </tr><tr>
 *     <td><code>commands</code></td>
 *     <td>{@link #getCommands()}</td>
 *     <td>插件将被注册的命令</td>
 * </tr><tr>
 *     <td><code>permissions</code></td>
 *     <td>{@link #getPermissions()}</td>
 *     <td>插件将被注册的权限</td>
 * </tr><tr>
 *     <td><code>default-permission</code></td>
 *     <td>{@link #getPermissionDefault()}</td>
 *     <td>插件将注册的基本的{@link Permission#getDefault() default} 权限 {@link #getPermissions() permissions}</td>
 * </tr><tr>
 *     <td><code>awareness</code></td>
 *     <td>{@link #getAwareness()}</td>
 *     <td>插件的概念</td>
 * </tr><tr>
 *     <td><code>api-version</code></td>
 *     <td>{@link #getAPIVersion()}</td>
 *     <td>开发插件时针对的API版本</td>
 * </tr><tr>
 *     <td><code>libraries</code></td>
 *     <td>{@link #getLibraries() ()}</td>
 *     <td>插件使用到的类库</td>
 * </tr>
 * </table>
 * <p>
 * 一个plugin.yml的例子:<blockquote><pre>
 * name: Inferno
 * provides: [Hell]
 * version: 1.4.1
 * description: This plugin is so 31337. You can set yourself on fire.
 * # We could place every author in the authors list, but chose not to for illustrative purposes
 * # Also, having an author distinguishes that person as the project lead, and ensures their
 * # name is displayed first
 * author: CaptainInflamo
 * authors: [Cogito, verrier, EvilSeph]
 * contributors: [Choco, md_5]
 * website: http://www.curse.com/server-mods/minecraft/myplugin
 *
 * main: com.captaininflamo.bukkit.inferno.Inferno
 * depend: [NewFire, FlameWire]
 * api-version: 1.13
 * libraries:
 *     - com.squareup.okhttp3:okhttp:4.9.0
 *
 * commands:
 *  flagrate:
 *    description: Set yourself on fire.
 *    aliases: [combust_me, combustMe]
 *    permission: inferno.flagrate
 *    usage: Syntax error! Simply type /&lt;command&gt; to ignite yourself.
 *  burningdeaths:
 *    description: List how many times you have died by fire.
 *    aliases: [burning_deaths, burningDeaths]
 *    permission: inferno.burningdeaths
 *    usage: |
 *      /&lt;command&gt; [player]
 *      Example: /&lt;command&gt; - see how many times you have burned to death
 *      Example: /&lt;command&gt; CaptainIce - see how many times CaptainIce has burned to death
 *
 * permissions:
 *  inferno.*:
 *    description: Gives access to all Inferno commands
 *    children:
 *      inferno.flagrate: true
 *      inferno.burningdeaths: true
 *      inferno.burningdeaths.others: true
 *  inferno.flagrate:
 *    description: Allows you to ignite yourself
 *    default: true
 *  inferno.burningdeaths:
 *    description: Allows you to see how many times you have burned to death
 *    default: true
 *  inferno.burningdeaths.others:
 *    description: Allows you to see how many times others have burned to death
 *    default: op
 *    children:
 *      inferno.burningdeaths: true
 * </pre></blockquote>
 * <p>原文：
 * This type is the runtime-container for the information in the plugin.yml.
 * All plugins must have a respective plugin.yml. For plugins written in java
 * using the standard plugin loader, this file must be in the root of the jar
 * file.
 * <p>
 * When Bukkit loads a plugin, it needs to know some basic information about
 * it. It reads this information from a YAML file, 'plugin.yml'. This file
 * consists of a set of attributes, each defined on a new line and with no
 * indentation.
 * <p>
 * Every (almost* every) method corresponds with a specific entry in the
 * plugin.yml. These are the <b>required</b> entries for every plugin.yml:
 * <ul>
 * <li>{@link #getName()} - <code>name</code>
 * <li>{@link #getVersion()} - <code>version</code>
 * <li>{@link #getMain()} - <code>main</code>
 * </ul>
 * <p>
 * Failing to include any of these items will throw an exception and cause the
 * server to ignore your plugin.
 * <p>
 * This is a list of the possible yaml keys, with specific details included in
 * the respective method documentations:
 * <table border=1>
 * <caption>The description of the plugin.yml layout</caption>
 * <tr>
 *     <th>Node</th>
 *     <th>Method</th>
 *     <th>Summary</th>
 * </tr><tr>
 *     <td><code>name</code></td>
 *     <td>{@link #getName()}</td>
 *     <td>The unique name of plugin</td>
 * </tr><tr>
 *     <td><code>provides</code></td>
 *     <td>{@link #getProvides()}</td>
 *     <td>The plugin APIs which this plugin provides</td>
 * </tr><tr>
 *     <td><code>version</code></td>
 *     <td>{@link #getVersion()}</td>
 *     <td>A plugin revision identifier</td>
 * </tr><tr>
 *     <td><code>main</code></td>
 *     <td>{@link #getMain()}</td>
 *     <td>The plugin's initial class file</td>
 * </tr><tr>
 *     <td><code>author</code><br><code>authors</code></td>
 *     <td>{@link #getAuthors()}</td>
 *     <td>The plugin authors</td>
 * </tr><tr>
 *     <td><code>contributors</code></td>
 *     <td>{@link #getContributors()}</td>
 *     <td>The plugin contributors</td>
 * </tr><tr>
 *     <td><code>description</code></td>
 *     <td>{@link #getDescription()}</td>
 *     <td>Human readable plugin summary</td>
 * </tr><tr>
 *     <td><code>website</code></td>
 *     <td>{@link #getWebsite()}</td>
 *     <td>The URL to the plugin's site</td>
 * </tr><tr>
 *     <td><code>prefix</code></td>
 *     <td>{@link #getPrefix()}</td>
 *     <td>The token to prefix plugin log entries</td>
 * </tr><tr>
 *     <td><code>load</code></td>
 *     <td>{@link #getLoad()}</td>
 *     <td>The phase of server-startup this plugin will load during</td>
 * </tr><tr>
 *     <td><code>depend</code></td>
 *     <td>{@link #getDepend()}</td>
 *     <td>Other required plugins</td>
 * </tr><tr>
 *     <td><code>softdepend</code></td>
 *     <td>{@link #getSoftDepend()}</td>
 *     <td>Other plugins that add functionality</td>
 * </tr><tr>
 *     <td><code>loadbefore</code></td>
 *     <td>{@link #getLoadBefore()}</td>
 *     <td>The inverse softdepend</td>
 * </tr><tr>
 *     <td><code>commands</code></td>
 *     <td>{@link #getCommands()}</td>
 *     <td>The commands the plugin will register</td>
 * </tr><tr>
 *     <td><code>permissions</code></td>
 *     <td>{@link #getPermissions()}</td>
 *     <td>The permissions the plugin will register</td>
 * </tr><tr>
 *     <td><code>default-permission</code></td>
 *     <td>{@link #getPermissionDefault()}</td>
 *     <td>The default {@link Permission#getDefault() default} permission
 *         state for defined {@link #getPermissions() permissions} the plugin
 *         will register</td>
 * </tr><tr>
 *     <td><code>awareness</code></td>
 *     <td>{@link #getAwareness()}</td>
 *     <td>The concepts that the plugin acknowledges</td>
 * </tr><tr>
 *     <td><code>api-version</code></td>
 *     <td>{@link #getAPIVersion()}</td>
 *     <td>The API version which this plugin was programmed against</td>
 * </tr><tr>
 *     <td><code>libraries</code></td>
 *     <td>{@link #getLibraries() ()}</td>
 *     <td>The libraries to be linked with this plugin</td>
 * </tr>
 * </table>
 * <p>
 * A plugin.yml example:<blockquote><pre>
 * name: Inferno
 * provides: [Hell]
 * version: 1.4.1
 * description: This plugin is so 31337. You can set yourself on fire.
 * # We could place every author in the authors list, but chose not to for illustrative purposes
 * # Also, having an author distinguishes that person as the project lead, and ensures their
 * # name is displayed first
 * author: CaptainInflamo
 * authors: [Cogito, verrier, EvilSeph]
 * contributors: [Choco, md_5]
 * website: http://www.curse.com/server-mods/minecraft/myplugin
 *
 * main: com.captaininflamo.bukkit.inferno.Inferno
 * depend: [NewFire, FlameWire]
 * api-version: 1.13
 * libraries:
 *     - com.squareup.okhttp3:okhttp:4.9.0
 *
 * commands:
 *  flagrate:
 *    description: Set yourself on fire.
 *    aliases: [combust_me, combustMe]
 *    permission: inferno.flagrate
 *    usage: Syntax error! Simply type /&lt;command&gt; to ignite yourself.
 *  burningdeaths:
 *    description: List how many times you have died by fire.
 *    aliases: [burning_deaths, burningDeaths]
 *    permission: inferno.burningdeaths
 *    usage: |
 *      /&lt;command&gt; [player]
 *      Example: /&lt;command&gt; - see how many times you have burned to death
 *      Example: /&lt;command&gt; CaptainIce - see how many times CaptainIce has burned to death
 *
 * permissions:
 *  inferno.*:
 *    description: Gives access to all Inferno commands
 *    children:
 *      inferno.flagrate: true
 *      inferno.burningdeaths: true
 *      inferno.burningdeaths.others: true
 *  inferno.flagrate:
 *    description: Allows you to ignite yourself
 *    default: true
 *  inferno.burningdeaths:
 *    description: Allows you to see how many times you have burned to death
 *    default: true
 *  inferno.burningdeaths.others:
 *    description: Allows you to see how many times others have burned to death
 *    default: op
 *    children:
 *      inferno.burningdeaths: true
 * </pre></blockquote>
 */
public final class PluginDescriptionFile implements io.papermc.paper.plugin.configuration.PluginMeta { // Paper
    private static final Pattern VALID_NAME = Pattern.compile("^[A-Za-z0-9 _.-]+$");
    private static final ThreadLocal<Yaml> YAML = new ThreadLocal<Yaml>() {
        @Override
        @NotNull
        protected Yaml initialValue() {
            DumperOptions dumperOptions = new DumperOptions();
            return new Yaml(new SafeConstructor(new LoaderOptions()) {
                {
                    yamlConstructors.put(null, new AbstractConstruct() {
                        @NotNull
                        @Override
                        public Object construct(@NotNull final Node node) {
                            if (!node.getTag().startsWith("!@")) {
                                // Unknown tag - will fail
                                return SafeConstructor.undefinedConstructor.construct(node);
                            }
                            // Unknown awareness - provide a graceful substitution
                            return new PluginAwareness() {
                                @Override
                                public String toString() {
                                    return node.toString();
                                }
                            };
                        }
                    });
                    for (final PluginAwareness.Flags flag : PluginAwareness.Flags.values()) {
                        yamlConstructors.put(new Tag("!@" + flag.name()), new AbstractConstruct() {
                            @NotNull
                            @Override
                            public PluginAwareness.Flags construct(@NotNull final Node node) {
                                return flag;
                            }
                        });
                    }
                }
            }, new Representer(dumperOptions), dumperOptions, new PluginDescriptionResolver());
        }
    };
    String rawName = null;
    private String name = null;
    private List<String> provides = ImmutableList.of();
    private String main = null;
    private String classLoaderOf = null;
    private List<String> depend = ImmutableList.of();
    private List<String> softDepend = ImmutableList.of();
    private List<String> loadBefore = ImmutableList.of();
    private String version = null;
    private Map<String, Map<String, Object>> commands = ImmutableMap.of();
    private String description = null;
    private List<String> authors = null;
    private List<String> contributors = null;
    private String website = null;
    private String prefix = null;
    private PluginLoadOrder order = PluginLoadOrder.POSTWORLD;
    private List<Permission> permissions = null;
    private Map<?, ?> lazyPermissions = null;
    private PermissionDefault defaultPerm = PermissionDefault.OP;
    private Set<PluginAwareness> awareness = ImmutableSet.of();
    private String apiVersion = null;
    private List<String> libraries = ImmutableList.of();
    // Paper start - plugin loader api
    private String paperPluginLoader;
    @org.jetbrains.annotations.ApiStatus.Internal @org.jetbrains.annotations.Nullable
    public String getPaperPluginLoader() {
        return this.paperPluginLoader;
    }
    // Paper end - plugin loader api
    // Paper start - oh my goddddd
    /**
     * @hidden
     */
    @org.jetbrains.annotations.ApiStatus.Internal
    public PluginDescriptionFile(String rawName, String name, List<String> provides, String main, String classLoaderOf, List<String> depend, List<String> softDepend, List<String> loadBefore, String version, Map<String, Map<String, Object>> commands, String description, List<String> authors, List<String> contributors, String website, String prefix, PluginLoadOrder order, List<Permission> permissions, PermissionDefault defaultPerm, Set<PluginAwareness> awareness, String apiVersion, List<String> libraries) {
        this.rawName = rawName;
        this.name = name;
        this.provides = provides;
        this.main = main;
        this.classLoaderOf = classLoaderOf;
        this.depend = depend;
        this.softDepend = softDepend;
        this.loadBefore = loadBefore;
        this.version = version;
        this.commands = commands;
        this.description = description;
        this.authors = authors;
        this.contributors = contributors;
        this.website = website;
        this.prefix = prefix;
        this.order = order;
        this.permissions = permissions;
        this.defaultPerm = defaultPerm;
        this.awareness = awareness;
        this.apiVersion = apiVersion;
        this.libraries = libraries;
    }

    @Override
    public @NotNull String getMainClass() {
        return this.main;
    }

    @Override
    public @NotNull PluginLoadOrder getLoadOrder() {
        return this.order;
    }

    @Override
    public @Nullable String getLoggerPrefix() {
        return this.prefix;
    }

    @Override
    public @NotNull List<String> getPluginDependencies() {
        return this.depend;
    }

    @Override
    public @NotNull List<String> getPluginSoftDependencies() {
        return this.softDepend;
    }

    @Override
    public @NotNull List<String> getLoadBeforePlugins() {
        return this.loadBefore;
    }

    @Override
    public @NotNull List<String> getProvidedPlugins() {
        return this.provides;
    }
    // Paper end

    public PluginDescriptionFile(@NotNull final InputStream stream) throws InvalidDescriptionException {
        loadMap(asMap(YAML.get().load(stream)));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从指定的 Reader 中读取 PluginDescriptionFile.
     * <p>原文：
     * Loads a PluginDescriptionFile from the specified reader
     *
     * @param reader Reader 对象
     * <p>原文：The reader
     * @throws InvalidDescriptionException 如果 PluginDescriptionFile 是无效的
     * <p>原文：If the PluginDescriptionFile is
     *     invalid
     */
    public PluginDescriptionFile(@NotNull final Reader reader) throws InvalidDescriptionException {
        loadMap(asMap(YAML.get().load(reader)));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构造一个新的 PluginDescriptionFile.
     * <p>原文：
     * Creates a new PluginDescriptionFile with the given detailed
     *
     * @param pluginName 插件名称
     * <p>原文：Name of this plugin
     * @param pluginVersion 插件版本
     * <p>原文：Version of this plugin
     * @param mainClass 插件主类的完整路径
     * <p>原文：Full location of the main class of this plugin
     */
    public PluginDescriptionFile(@NotNull final String pluginName, @NotNull final String pluginVersion, @NotNull final String mainClass) {
        name = rawName = pluginName;

        if (!VALID_NAME.matcher(name).matches()) {
            throw new IllegalArgumentException("name '" + name + "' contains invalid characters.");
        }
        name = name.replace(' ', '_');
        version = pluginVersion;
        main = mainClass;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件的名称. 此名称是插件的唯一标识符.
     * <ul>
     * <li>必须由字母数字字符、下划线、连字符和句点 (a-z,A-Z,0-9, _.-) 组成. 任何其他字符将导致 plugin.yml 加载失败.
     * <li>用于确定插件数据文件夹的名称. 数据文件夹默认放置在 ./plugins/ 目录下, 但不应依赖此行为. 应使用 {@link Plugin#getDataFolder()} 来引用数据文件夹.
     * <li>建议将 jar 文件命名与此一致, 例如 'MyPlugin.jar'.
     * <li>区分大小写.
     * <li>这是在 {@link #getDepend()}, {@link #getSoftDepend()} 和 {@link #getLoadBefore()} 中引用的标识.
     * <li>在插件名称中使用空格已被弃用.
     * </ul>
     * <p>原文：
     * Gives the name of the plugin. This name is a unique identifier for
     * plugins.
     * <ul>
     * <li>Must consist of all alphanumeric characters, underscores, hyphon,
     *     and period (a-z,A-Z,0-9, _.-). Any other character will cause the
     *     plugin.yml to fail loading.
     * <li>Used to determine the name of the plugin's data folder. Data
     *     folders are placed in the ./plugins/ directory by default, but this
     *     behavior should not be relied on. {@link Plugin#getDataFolder()}
     *     should be used to reference the data folder.
     * <li>It is good practice to name your jar the same as this, for example
     *     'MyPlugin.jar'.
     * <li>Case sensitive.
     * <li>It's the token referenced in {@link #getDepend()}, {@link
     *     #getSoftDepend()}, and {@link #getLoadBefore()}.
     * <li>Using spaces in the plugin's name is deprecated.
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>name</code>.
     * <p>
     * Example:<blockquote><pre>name: MyPlugin</pre></blockquote>
     *
     * @return 插件的名称
     * <p>原文：the name of the plugin
     */
    @NotNull
    public String getName() {
        return name;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此插件提供的其他插件 API 列表. 其他插件可以依赖这些 API.
     * <ul>
     * <li>必须由字母数字字符、下划线、连字符和句点 (a-z,A-Z,0-9, _.-) 组成. 任何其他字符将导致 plugin.yml 加载失败.
     * <li>其他插件提供相同 API 或将其用作名称不会导致插件加载失败.
     * <li>区分大小写.
     * <li>此列表中的条目可在 {@link #getDepend()}, {@link #getSoftDepend()} 和 {@link #getLoadBefore()} 中引用.
     * <li><code>provides</code> 必须使用 <a href="https://en.wikipedia.org/wiki/YAML#Lists">YAML 列表格式</a>.
     * </ul>
     * <p>原文：
     * Gives the list of other plugin APIs which this plugin provides.
     * These are usable for other plugins to depend on.
     * <ul>
     * <li>Must consist of all alphanumeric characters, underscores, hyphon,
     *     and period (a-z,A-Z,0-9, _.-). Any other character will cause the
     *     plugin.yml to fail loading.
     * <li>A different plugin providing the same one or using it as their name
     *     will not result in the plugin to fail loading.
     * <li>Case sensitive.
     * <li>An entry of this list can be referenced in {@link #getDepend()},
     *    {@link #getSoftDepend()}, and {@link #getLoadBefore()}.
     * <li><code>provides</code> must be in <a
     *     href="https://en.wikipedia.org/wiki/YAML#Lists">YAML list
     *     format</a>.
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>provides</code>.
     * <p>
     * Example:
     * <blockquote><pre>provides:
     * - OtherPluginName
     * - OldPluginName</pre></blockquote>
     *
     * @return 此插件提供的插件 API 不可变列表
     * <p>原文：immutable list of the plugin APIs which this plugin provides
     */
    @NotNull
    public List<String> getProvides() {
        return provides;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件的版本.
     * <ul>
     * <li>版本是一个任意字符串, 但最常用的格式是 主版本.次版本.构建版本 (例如: 1.4.1).
     * <li>通常在每次发布新功能或修复 bug 时递增版本号.
     * <li>当用户输入 <code>/version PluginName</code> 时显示.
     * </ul>
     * <p>原文：
     * Gives the version of the plugin.
     * <ul>
     * <li>Version is an arbitrary string, however the most common format is
     *     MajorRelease.MinorRelease.Build (eg: 1.4.1).
     * <li>Typically you will increment this every time you release a new
     *     feature or bug fix.
     * <li>Displayed when a user types <code>/version PluginName</code>
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>version</code>.
     * <p>
     * Example:<blockquote><pre>version: 1.4.1</pre></blockquote>
     *
     * @return 插件的版本
     * <p>原文：the version of the plugin
     */
    @NotNull
    public String getVersion() {
        return version;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件主类的完整限定名. 格式应遵循 {@link ClassLoader#loadClass(String)} 语法, 以便在运行时成功解析. 对于大多数插件, 这是扩展 {@link JavaPlugin} 的类.
     * <ul>
     * <li>必须包含完整的命名空间, 包括类文件本身.
     * <li>如果命名空间是 <code>org.bukkit.plugin</code>, 且类文件名为 <code>MyPlugin</code>, 则必须为 <code>org.bukkit.plugin.MyPlugin</code>
     * <li>任何插件都不能使用 <code>org.bukkit.</code> 作为<b>任何类</b> (包括主类) 的基础包.
     * </ul>
     * <p>原文：
     * Gives the fully qualified name of the main class for a plugin. The
     * format should follow the {@link ClassLoader#loadClass(String)} syntax
     * to successfully be resolved at runtime. For most plugins, this is the
     * class that extends {@link JavaPlugin}.
     * <ul>
     * <li>This must contain the full namespace including the class file
     *     itself.
     * <li>If your namespace is <code>org.bukkit.plugin</code>, and your class
     *     file is called <code>MyPlugin</code> then this must be
     *     <code>org.bukkit.plugin.MyPlugin</code>
     * <li>No plugin can use <code>org.bukkit.</code> as a base package for
     *     <b>any class</b>, including the main class.
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>main</code>.
     * <p>
     * Example:
     * <blockquote><pre>main: org.bukkit.plugin.MyPlugin</pre></blockquote>
     *
     * @return 插件的完整限定主类名
     * <p>原文：the fully qualified main class for the plugin
     */
    @NotNull
    public String getMain() {
        return main;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件功能的人类可读描述.
     * <ul>
     * <li>描述可以有多行.
     * <li>当用户输入 <code>/version PluginName</code> 时显示.
     * </ul>
     * <p>原文：
     * Gives a human-friendly description of the functionality the plugin
     * provides.
     * <ul>
     * <li>The description can have multiple lines.
     * <li>Displayed when a user types <code>/version PluginName</code>
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>description</code>.
     * <p>
     * Example:
     * <blockquote><pre>description: This plugin is so 31337. You can set yourself on fire.</pre></blockquote>
     *
     * @return 插件的描述, 如果未指定则返回 null
     * <p>原文：description of this plugin, or null if not specified
     */
    @Nullable
    public String getDescription() {
        return description;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件应在服务器启动的哪个阶段加载.
     * <ul>
     * <li>可能的值见 {@link PluginLoadOrder}.
     * <li>默认为 {@link PluginLoadOrder#POSTWORLD}.
     * <li>每个阶段有特定的注意事项.
     * <li>当不同时, {@link #getDepend()}, {@link #getSoftDepend()} 和 {@link #getLoadBefore()} 在每个阶段内的加载顺序变为相对的. 如果一个插件在 <code>STARTUP</code> 阶段加载, 但其依赖在 <code>POSTWORLD</code> 阶段加载, 则依赖不会在该插件加载之前被加载.
     * </ul>
     * <p>原文：
     * Gives the phase of server startup that the plugin should be loaded.
     * <ul>
     * <li>Possible values are in {@link PluginLoadOrder}.
     * <li>Defaults to {@link PluginLoadOrder#POSTWORLD}.
     * <li>Certain caveats apply to each phase.
     * <li>When different, {@link #getDepend()}, {@link #getSoftDepend()}, and
     *     {@link #getLoadBefore()} become relative in order loaded per-phase.
     *     If a plugin loads at <code>STARTUP</code>, but a dependency loads
     *     at <code>POSTWORLD</code>, the dependency will not be loaded before
     *     the plugin is loaded.
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>load</code>.
     * <p>
     * Example:<blockquote><pre>load: STARTUP</pre></blockquote>
     *
     * @return 插件应加载的阶段
     * <p>原文：the phase when the plugin should be loaded
     */
    @NotNull
    public PluginLoadOrder getLoad() {
        return order;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件的作者列表.
     * <ul>
     * <li>用于标识开发者.
     * <li>在某些服务器错误消息中使用, 以提供有关出现错误时联系谁的有用反馈.
     * <li>建议使用 SpigotMC 论坛账号或电子邮件地址.
     * <li>当用户输入 <code>/version PluginName</code> 时显示.
     * <li><code>authors</code> 必须使用 <a href="https://en.wikipedia.org/wiki/YAML#Lists">YAML 列表格式</a>.
     * </ul>
     * <p>原文：
     * Gives the list of authors for the plugin.
     * <ul>
     * <li>Gives credit to the developer.
     * <li>Used in some server error messages to provide helpful feedback on
     *     who to contact when an error occurs.
     * <li>A SpigotMC forum handle or email address is recommended.
     * <li>Is displayed when a user types <code>/version PluginName</code>
     * <li><code>authors</code> must be in <a
     *     href="https://en.wikipedia.org/wiki/YAML#Lists">YAML list
     *     format</a>.
     * </ul>
     * <p>
     * In the plugin.yml, this has two entries, <code>author</code> and
     * <code>authors</code>.
     * <p>
     * Single author example:
     * <blockquote><pre>author: CaptainInflamo</pre></blockquote>
     * Multiple author example:
     * <blockquote><pre>authors: [Cogito, verrier, EvilSeph]</pre></blockquote>
     * When both are specified, author will be the first entry in the list, so
     * this example:
     * <blockquote><pre>author: Grum
     * authors:
     * - feildmaster
     * - amaranth</pre></blockquote>
     * Is equivalent to this example:
     * <pre>authors: [Grum, feildmaster, aramanth]</pre>
     *
     * @return 插件作者的不可变列表
     * <p>原文：an immutable list of the plugin's authors
     */
    @NotNull
    public List<String> getAuthors() {
        return authors;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件的贡献者列表.
     * <ul>
     * <li>用于标识对插件有贡献但不足以获得作者资格的人员.
     * <li>与 {@link #getAuthors()} 不同, 贡献者不会在服务器错误消息中作为联系方式被提及.
     * <li>建议使用 SpigotMC 论坛账号或电子邮件地址.
     * <li>当用户输入 <code>/version PluginName</code> 时显示.
     * <li><code>contributors</code> 必须使用 <a href="https://en.wikipedia.org/wiki/YAML#Lists">YAML 列表格式</a>.
     * </ul>
     * <p>原文：
     * Gives the list of contributors for the plugin.
     * <ul>
     * <li>Gives credit to those that have contributed to the plugin, though
     *     not enough so to warrant authorship.
     * <li>Unlike {@link #getAuthors()}, contributors will not be mentioned in
     * server error messages as a means of contact.
     * <li>A SpigotMC forum handle or email address is recommended.
     * <li>Is displayed when a user types <code>/version PluginName</code>
     * <li><code>contributors</code> must be in <a
     *     href="https://en.wikipedia.org/wiki/YAML#Lists">YAML list
     *     format</a>.
     * </ul>
     * <p>
     * Example:
     * <blockquote><pre>authors: [Choco, md_5]</pre></blockquote>
     *
     * @return 插件贡献者的不可变列表
     * <p>原文：an immutable list of the plugin's contributors
     */
    @NotNull
    public List<String> getContributors() {
        return contributors;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件或插件作者的网站.
     * <ul>
     * <li>强烈建议提供包含文档和下载的 Curse 页面链接.
     * <li>当用户输入 <code>/version PluginName</code> 时显示.
     * </ul>
     * <p>原文：
     * Gives the plugin's or plugin's author's website.
     * <ul>
     * <li>A link to the Curse page that includes documentation and downloads
     *     is highly recommended.
     * <li>Displayed when a user types <code>/version PluginName</code>
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>website</code>.
     * <p>
     * Example:
     * <blockquote><pre>website: http://www.curse.com/server-mods/minecraft/myplugin</pre></blockquote>
     *
     * @return 插件的网站, 如果未指定则返回 null
     * <p>原文：description of this plugin, or null if not specified
     */
    @Nullable
    public String getWebsite() {
        return website;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此插件所需的其他插件列表.
     * <ul>
     * <li>使用目标插件 {@link #getName()} 中的值来指定依赖.
     * <li>如果此处列出的任何插件未找到, 你的插件将在启动时加载失败.
     * <li>如果多个插件在 <code>depend</code> 中相互引用, 形成一个没有单独插件在 <a href="https://en.wikipedia.org/wiki/Circular_dependency">网络</a> 中列出另一个插件的循环依赖, 则该网络中的所有插件都将失败.
     * <li><code>depend</code> 必须使用 <a href="https://en.wikipedia.org/wiki/YAML#Lists">YAML 列表格式</a>.
     * </ul>
     * <p>原文：
     * Gives a list of other plugins that the plugin requires.
     * <ul>
     * <li>Use the value in the {@link #getName()} of the target plugin to
     *     specify the dependency.
     * <li>If any plugin listed here is not found, your plugin will fail to
     *     load at startup.
     * <li>If multiple plugins list each other in <code>depend</code>,
     *     creating a network with no individual plugin does not list another
     *     plugin in the <a
     *     href=https://en.wikipedia.org/wiki/Circular_dependency>network</a>,
     *     all plugins in that network will fail.
     * <li><code>depend</code> must be in <a
     *     href="https://en.wikipedia.org/wiki/YAML#Lists">YAML list
     *     format</a>.
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>depend</code>.
     * <p>
     * Example:
     * <blockquote><pre>depend:
     * - OnePlugin
     * - AnotherPlugin</pre></blockquote>
     *
     * @return 插件依赖的不可变列表
     * <p>原文：immutable list of the plugin's dependencies
     */
    @NotNull
    public List<String> getDepend() {
        return depend;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件完整功能所需的其他插件列表. {@link PluginManager} 会尽力将此处所有条目视为 {@link #getDepend() 依赖}, 但不会因为这些条目中的任何一个而失败.
     * <ul>
     * <li>使用目标插件 {@link #getName()} 中的值来指定依赖.
     * <li>当列出的插件无法解析时, 它将被忽略且不影响加载顺序.
     * <li>当发生循环依赖 (插件网络相互依赖或软依赖) 时, 将任意选择一个在忽略软依赖时可以解析的插件.
     * <li><code>softdepend</code> 必须使用 <a href="https://en.wikipedia.org/wiki/YAML#Lists">YAML 列表格式</a>.
     * </ul>
     * <p>原文：
     * Gives a list of other plugins that the plugin requires for full
     * functionality. The {@link PluginManager} will make best effort to treat
     * all entries here as if they were a {@link #getDepend() dependency}, but
     * will never fail because of one of these entries.
     * <ul>
     * <li>Use the value in the {@link #getName()} of the target plugin to
     *     specify the dependency.
     * <li>When an unresolvable plugin is listed, it will be ignored and does
     *     not affect load order.
     * <li>When a circular dependency occurs (a network of plugins depending
     *     or soft-depending on each other), it will arbitrarily choose a
     *     plugin that can be resolved when ignoring soft-dependencies.
     * <li><code>softdepend</code> must be in <a
     *     href="https://en.wikipedia.org/wiki/YAML#Lists">YAML list
     *     format</a>.
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>softdepend</code>.
     * <p>
     * Example:
     * <blockquote><pre>softdepend: [OnePlugin, AnotherPlugin]</pre></blockquote>
     *
     * @return 插件首选依赖的不可变列表
     * <p>原文：immutable list of the plugin's preferred dependencies
     */
    @NotNull
    public List<String> getSoftDepend() {
        return softDepend;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取应将此插件视为软依赖的插件列表.
     * <ul>
     * <li>使用目标插件 {@link #getName()} 中的值来指定依赖.
     * <li>此插件应在列出的所有其他插件之前加载.
     * <li>在此处指定另一个插件严格等同于让指定插件的 {@link #getSoftDepend()} 包含 {@link #getName() 此插件}.
     * <li><code>loadbefore</code> 必须使用 <a href="https://en.wikipedia.org/wiki/YAML#Lists">YAML 列表格式</a>.
     * </ul>
     * <p>原文：
     * Gets the list of plugins that should consider this plugin a
     * soft-dependency.
     * <ul>
     * <li>Use the value in the {@link #getName()} of the target plugin to
     *     specify the dependency.
     * <li>The plugin should load before any other plugins listed here.
     * <li>Specifying another plugin here is strictly equivalent to having the
     *     specified plugin's {@link #getSoftDepend()} include {@link
     *     #getName() this plugin}.
     * <li><code>loadbefore</code> must be in <a
     *     href="https://en.wikipedia.org/wiki/YAML#Lists">YAML list
     *     format</a>.
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>loadbefore</code>.
     * <p>
     * Example:
     * <blockquote><pre>loadbefore:
     * - OnePlugin
     * - AnotherPlugin</pre></blockquote>
     *
     * @return 应将此插件视为软依赖的插件不可变列表
     * <p>原文：immutable list of plugins that should consider this plugin a
     *     soft-dependency
     */
    @NotNull
    public List<String> getLoadBefore() {
        return loadBefore;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于插件特定日志消息前缀的标识.
     * <ul>
     * <li>包括所有使用 {@link Plugin#getLogger()} 的消息.
     * <li>如果未指定, 服务器使用插件的 {@link #getName() 名称}.
     * <li>应清楚地指示正在记录的是哪个插件.
     * </ul>
     * <p>原文：
     * Gives the token to prefix plugin-specific logging messages with.
     * <ul>
     * <li>This includes all messages using {@link Plugin#getLogger()}.
     * <li>If not specified, the server uses the plugin's {@link #getName()
     *     name}.
     * <li>This should clearly indicate what plugin is being logged.
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>prefix</code>.
     * <p>
     * Example:<blockquote><pre>prefix: ex-why-zee</pre></blockquote>
     *
     * @return 日志前缀标识, 如果未指定则返回 null
     * <p>原文：the prefixed logging token, or null if not specified
     */
    @Nullable
    public String getPrefix() {
        return prefix;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取命令名称到命令属性的映射. 此映射中的每个条目对应一个单独的命令, 其各自的值是该命令的属性. 除别名外, 每个属性都可以在运行时使用 {@link PluginCommand} 中的方法定义, 在此处定义仅为方便.
     * <table border=1>
     * <caption>命令部分描述</caption>
     * <tr>
     *     <th>节点</th>
     *     <th>方法</th>
     *     <th>类型</th>
     *     <th>描述</th>
     *     <th>示例</th>
     * </tr><tr>
     *     <td><code>description</code></td>
     *     <td>{@link PluginCommand#setDescription(String)}</td>
     *     <td>String</td>
     *     <td>命令的用户友好描述. 适用于文档编写以及游戏内帮助.</td>
     *     <td><blockquote><pre>description: Set yourself on fire</pre></blockquote></td>
     * </tr><tr>
     *     <td><code>aliases</code></td>
     *     <td>{@link PluginCommand#setAliases(List)}</td>
     *     <td>字符串或字符串 <a href="https://en.wikipedia.org/wiki/YAML#Lists">列表</a></td>
     *     <td>替代命令名称, 对于已注册的命令特别有用. <i>别名在运行时定义无效,</i> 因此插件描述文件是正确定义别名的唯一方式.
     *         <p>
     *         注意: 命令别名中不能包含冒号.</td>
     *     <td>单个别名格式:
     *         <blockquote><pre>aliases: combust_me</pre></blockquote> 或
     *         多个别名格式:
     *         <blockquote><pre>aliases: [combust_me, combustMe]</pre></blockquote></td>
     * </tr><tr>
     *     <td><code>permission</code></td>
     *     <td>{@link PluginCommand#setPermission(String)}</td>
     *     <td>String</td>
     *     <td>使用该命令所需的 {@link Permission} 名称. 没有权限的用户将收到指定消息 (见下方 {@linkplain PluginCommand#setPermissionMessage(String)}), 如果未定义特定消息则使用标准消息. 没有权限节点, 任何 {@link PluginCommand#setExecutor(CommandExecutor) CommandExecutor} 或 {@link PluginCommand#setTabCompleter(TabCompleter)} 都不会被调用.</td>
     *     <td><blockquote><pre>permission: inferno.flagrate</pre></blockquote></td>
     * </tr><tr>
     *     <td><code>permission-message</code></td>
     *     <td>{@link PluginCommand#setPermissionMessage(String)}</td>
     *     <td>String</td>
     *     <td><ul>
     *         <li>显示给尝试使用命令但没有所需权限的玩家. 见上方 {@link PluginCommand#getPermission()}.
     *         <li>&lt;permission&gt; 是一个宏, 会被替换为使用该命令所需的权限节点.
     *         <li>使用空引号是表示不向玩家显示任何内容的有效方式.
     *         </ul></td>
     *     <td><blockquote><pre>permission-message: You do not have /&lt;permission&gt;</pre></blockquote></td>
     * </tr><tr>
     *     <td><code>usage</code></td>
     *     <td>{@link PluginCommand#setUsage(String)}</td>
     *     <td>String</td>
     *     <td>当 {@link PluginCommand#setExecutor(CommandExecutor)} {@linkplain CommandExecutor#onCommand(CommandSender, Command, String, String[]) 返回 false} 时显示此消息. &lt;command&gt; 是一个宏, 会被替换为发出的命令.</td>
     *     <td><blockquote><pre>usage: Syntax error! Perhaps you meant /&lt;command&gt; PlayerName?</pre></blockquote>
     *         值得注意的是, 要在 yaml 中使用冒号, 如 <code>usage: Usage: /god [player]</code>, 需要 <a href="http://yaml.org/spec/current.html#id2503232">用双引号包围消息</a>:
     *         <blockquote><pre>usage: "Usage: /god [player]"</pre></blockquote></td>
     * </tr>
     * </table>
     * 命令以 <a href="http://yaml.org/spec/current.html#id2502325">嵌套映射</a> 层次结构组织. 主 (顶层, 无缩进) 节点是 <code>commands</code>, 每个单独的命令名是缩进的, 表示它映射到某个值 (即上表中的属性).
     * <p>原文：
     * Gives the map of command-name to command-properties. Each entry in this
     * map corresponds to a single command and the respective values are the
     * properties of the command. Each property, <i>with the exception of
     * aliases</i>, can be defined at runtime using methods in {@link
     * PluginCommand} and are defined here only as a convenience.
     * <table border=1>
     * <caption>The command section's description</caption>
     * <tr>
     *     <th>Node</th>
     *     <th>Method</th>
     *     <th>Type</th>
     *     <th>Description</th>
     *     <th>Example</th>
     * </tr><tr>
     *     <td><code>description</code></td>
     *     <td>{@link PluginCommand#setDescription(String)}</td>
     *     <td>String</td>
     *     <td>A user-friendly description for a command. It is useful for
     *         documentation purposes as well as in-game help.</td>
     *     <td><blockquote><pre>description: Set yourself on fire</pre></blockquote></td>
     * </tr><tr>
     *     <td><code>aliases</code></td>
     *     <td>{@link PluginCommand#setAliases(List)}</td>
     *     <td>String or <a
     *         href="https://en.wikipedia.org/wiki/YAML#Lists">List</a> of
     *         strings</td>
     *     <td>Alternative command names, with special usefulness for commands
     *         that are already registered. <i>Aliases are not effective when
     *         defined at runtime,</i> so the plugin description file is the
     *         only way to have them properly defined.
     *         <p>
     *         Note: Command aliases may not have a colon in them.</td>
     *     <td>Single alias format:
     *         <blockquote><pre>aliases: combust_me</pre></blockquote> or
     *         multiple alias format:
     *         <blockquote><pre>aliases: [combust_me, combustMe]</pre></blockquote></td>
     * </tr><tr>
     *     <td><code>permission</code></td>
     *     <td>{@link PluginCommand#setPermission(String)}</td>
     *     <td>String</td>
     *     <td>The name of the {@link Permission} required to use the command.
     *         A user without the permission will receive the specified
     *         message (see {@linkplain
     *         PluginCommand#setPermissionMessage(String) below}), or a
     *         standard one if no specific message is defined. Without the
     *         permission node, no {@link
     *         PluginCommand#setExecutor(CommandExecutor) CommandExecutor} or
     *         {@link PluginCommand#setTabCompleter(TabCompleter)} will be called.</td>
     *     <td><blockquote><pre>permission: inferno.flagrate</pre></blockquote></td>
     * </tr><tr>
     *     <td><code>permission-message</code></td>
     *     <td>{@link PluginCommand#setPermissionMessage(String)}</td>
     *     <td>String</td>
     *     <td><ul>
     *         <li>Displayed to a player that attempts to use a command, but
     *             does not have the required permission. See {@link
     *             PluginCommand#getPermission() above}.
     *         <li>&lt;permission&gt; is a macro that is replaced with the
     *             permission node required to use the command.
     *         <li>Using empty quotes is a valid way to indicate nothing
     *             should be displayed to a player.
     *         </ul></td>
     *     <td><blockquote><pre>permission-message: You do not have /&lt;permission&gt;</pre></blockquote></td>
     * </tr><tr>
     *     <td><code>usage</code></td>
     *     <td>{@link PluginCommand#setUsage(String)}</td>
     *     <td>String</td>
     *     <td>This message is displayed to a player when the {@link
     *         PluginCommand#setExecutor(CommandExecutor)} {@linkplain
     *         CommandExecutor#onCommand(CommandSender, Command, String, String[]) returns false}.
     *         &lt;command&gt; is a macro that is replaced the command issued.</td>
     *     <td><blockquote><pre>usage: Syntax error! Perhaps you meant /&lt;command&gt; PlayerName?</pre></blockquote>
     *         It is worth noting that to use a colon in a yaml, like
     *         <code>`usage: Usage: /god [player]'</code>, you need to
     *         <a href="http://yaml.org/spec/current.html#id2503232">surround
     *         the message with double-quote</a>:
     *         <blockquote><pre>usage: "Usage: /god [player]"</pre></blockquote></td>
     * </tr>
     * </table>
     * The commands are structured as a hierarchy of <a
     * href="http://yaml.org/spec/current.html#id2502325">nested mappings</a>.
     * The primary (top-level, no intendentation) node is
     * `<code>commands</code>', while each individual command name is
     * indented, indicating it maps to some value (in our case, the
     * properties of the table above).
     * <p>
     * Here is an example bringing together the piecemeal examples above, as
     * well as few more definitions:<blockquote><pre>
     * commands:
     *  flagrate:
     *    description: Set yourself on fire.
     *    aliases: [combust_me, combustMe]
     *    permission: inferno.flagrate
     *    permission-message: You do not have /&lt;permission&gt;
     *    usage: Syntax error! Perhaps you meant /&lt;command&gt; PlayerName?
     *  burningdeaths:
     *    description: List how many times you have died by fire.
     *    aliases:
     *    - burning_deaths
     *    - burningDeaths
     *    permission: inferno.burningdeaths
     *    usage: |
     *      /&lt;command&gt; [player]
     *      Example: /&lt;command&gt; - see how many times you have burned to death
     *      Example: /&lt;command&gt; CaptainIce - see how many times CaptainIce has burned to death
     *  # The next command has no description, aliases, etc. defined, but is still valid
     *  # Having an empty declaration is useful for defining the description, permission, and messages from a configuration dynamically
     *  apocalypse:
     * </pre></blockquote>
     * Note: Command names may not have a colon in their name.
     *
     * @return 此插件将注册的命令
     * <p>原文：the commands this plugin will register
     */
    @NotNull
    public Map<String, Map<String, Object>> getCommands() {
        return commands;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件将在运行时注册的权限列表, 在启用后立即生效. 定义权限的格式是从权限名称到属性的映射. 要表示没有任何特定属性的映射, 可以使用空花括号 (<code>&#123;&#125;</code>) (与上面的命令不同, 不接受 null 值).
     * <p>
     * 权限的可选属性列表:
     * <table border=1>
     * <caption>权限部分描述</caption>
     * <tr>
     *     <th>节点</th>
     *     <th>描述</th>
     *     <th>示例</th>
     * </tr><tr>
     *     <td><code>description</code></td>
     *     <td>权限用途的明文 (用户友好) 描述.</td>
     *     <td><blockquote><pre>description: Allows you to set yourself on fire</pre></blockquote></td>
     * </tr><tr>
     *     <td><code>default</code></td>
     *     <td>权限的默认状态, 由 {@link Permission#getDefault()} 定义. 如果未定义, 将设置为 {@link PluginDescriptionFile#getPermissionDefault()} 的值.
     *         <p>
     *         参考:<ul>
     *         <li><code>true</code> - 表示对 {@link Permissible 可授权对象} 的正面赋权.
     *         <li><code>false</code> - 表示不对 {@link Permissible 可授权对象} 赋权.
     *         <li><code>op</code> - 表示对 {@link Permissible#isOp() 管理员可授权对象} 的正面赋权.
     *         <li><code>notop</code> - 表示对 {@link Permissible#isOp() 非管理员可授权对象} 的正面赋权.
     *         </ul></td>
     *     <td><blockquote><pre>default: true</pre></blockquote></td>
     * </tr><tr>
     *     <td><code>children</code></td>
     *     <td>允许将其他权限设置为父权限的 {@linkplain Permission#getChildren() 关联} 权限. 当父权限被赋权时, 子权限也会相应地被赋权.
     *         <ul>
     *         <li>当父权限被负面赋权时, 子权限基于其关联关系的反转进行赋权.
     *         <li>当父权限被正面赋权时, 子权限基于其关联关系进行赋权.
     *         </ul>
     *         <p>
     *         子权限可以通过多种方式定义:<ul>
     *         <li>子权限可以定义为名称的 <a href="https://en.wikipedia.org/wiki/YAML#Lists">列表</a>. 使用列表将使所有子权限与其父权限正面关联.
     *         <li>子权限可以定义为映射. 每个权限名称映射到一个布尔值 (表示关联关系) 或一个嵌套的权限定义 (就像另一个权限). 使用嵌套定义将使子权限视为正面关联.
     *         <li>嵌套权限定义必须是这些相同属性的映射. 要定义有效的嵌套权限而不定义任何特定属性, 必须使用空花括号 (<code>&#123;&#125;</code>).
     *          <li>嵌套权限可以拥有自己的嵌套权限作为子权限, 它们也可以有嵌套权限, 依此类推. 权限树的定义深度没有直接限制.
     *         </ul></td>
     *     <td>作为列表:
     *         <blockquote><pre>children: [inferno.flagrate, inferno.burningdeaths]</pre></blockquote>
     *         或作为映射:
     *         <blockquote><pre>children:
     *  inferno.flagrate: true
     *  inferno.burningdeaths: true</pre></blockquote>
     *         展示基本嵌套值的附加示例可在 <a href="doc-files/permissions-example_plugin.yml">此处</a> 查看.
     *         </td>
     * </tr>
     * </table>
     * 权限以 <a href="http://yaml.org/spec/current.html#id2502325">嵌套映射</a> 层次结构组织. 主 (顶层, 无缩进) 节点是 <code>permissions</code>, 每个单独的权限名是缩进的, 表示它映射到某个值 (即上表中的属性).
     * <p>原文：
     * Gives the list of permissions the plugin will register at runtime,
     * immediately preceding enabling. The format for defining permissions is
     * a map from permission name to properties. To represent a map without
     * any specific property, empty <a
     * href="http://yaml.org/spec/current.html#id2502702">curly-braces</a> (
     * <code>&#123;&#125;</code> ) may be used (as a null value is not
     * accepted, unlike the {@link #getCommands() commands} above).
     * <p>
     * A list of optional properties for permissions:
     * <table border=1>
     * <caption>The permission section's description</caption>
     * <tr>
     *     <th>Node</th>
     *     <th>Description</th>
     *     <th>Example</th>
     * </tr><tr>
     *     <td><code>description</code></td>
     *     <td>Plaintext (user-friendly) description of what the permission
     *         is for.</td>
     *     <td><blockquote><pre>description: Allows you to set yourself on fire</pre></blockquote></td>
     * </tr><tr>
     *     <td><code>default</code></td>
     *     <td>The default state for the permission, as defined by {@link
     *         Permission#getDefault()}. If not defined, it will be set to
     *         the value of {@link PluginDescriptionFile#getPermissionDefault()}.
     *         <p>
     *         For reference:<ul>
     *         <li><code>true</code> - Represents a positive assignment to
     *             {@link Permissible permissibles}.
     *         <li><code>false</code> - Represents no assignment to {@link
     *             Permissible permissibles}.
     *         <li><code>op</code> - Represents a positive assignment to
     *             {@link Permissible#isOp() operator permissibles}.
     *         <li><code>notop</code> - Represents a positive assignment to
     *             {@link Permissible#isOp() non-operator permissibiles}.
     *         </ul></td>
     *     <td><blockquote><pre>default: true</pre></blockquote></td>
     * </tr><tr>
     *     <td><code>children</code></td>
     *     <td>Allows other permissions to be set as a {@linkplain
     *         Permission#getChildren() relation} to the parent permission.
     *         When a parent permissions is assigned, child permissions are
     *         respectively assigned as well.
     *         <ul>
     *         <li>When a parent permission is assigned negatively, child
     *             permissions are assigned based on an inversion of their
     *             association.
     *         <li>When a parent permission is assigned positively, child
     *             permissions are assigned based on their association.
     *         </ul>
     *         <p>
     *         Child permissions may be defined in a number of ways:<ul>
     *         <li>Children may be defined as a <a
     *             href="https://en.wikipedia.org/wiki/YAML#Lists">list</a> of
     *             names. Using a list will treat all children associated
     *             positively to their parent.
     *         <li>Children may be defined as a map. Each permission name maps
     *             to either a boolean (representing the association), or a
     *             nested permission definition (just as another permission).
     *             Using a nested definition treats the child as a positive
     *             association.
     *         <li>A nested permission definition must be a map of these same
     *             properties. To define a valid nested permission without
     *             defining any specific property, empty curly-braces (
     *             <code>&#123;&#125;</code> ) must be used.
     *          <li>A nested permission may carry its own nested permissions
     *              as children, as they may also have nested permissions, and
     *              so forth. There is no direct limit to how deep the
     *              permission tree is defined.
     *         </ul></td>
     *     <td>As a list:
     *         <blockquote><pre>children: [inferno.flagrate, inferno.burningdeaths]</pre></blockquote>
     *         Or as a mapping:
     *         <blockquote><pre>children:
     *  inferno.flagrate: true
     *  inferno.burningdeaths: true</pre></blockquote>
     *         An additional example showing basic nested values can be seen
     *         <a href="doc-files/permissions-example_plugin.yml">here</a>.
     *         </td>
     * </tr>
     * </table>
     * The permissions are structured as a hierarchy of <a
     * href="http://yaml.org/spec/current.html#id2502325">nested mappings</a>.
     * The primary (top-level, no indentation) node is
     * `<code>permissions</code>', while each individual permission name is
     * indented, indicating it maps to some value (in our case, the
     * properties of the table above).
     * <p>
     * Here is an example using some of the properties:<blockquote><pre>
     * permissions:
     *  inferno.*:
     *    description: Gives access to all Inferno commands
     *    children:
     *      inferno.flagrate: true
     *      inferno.burningdeaths: true
     *  inferno.flagate:
     *    description: Allows you to ignite yourself
     *    default: true
     *  inferno.burningdeaths:
     *    description: Allows you to see how many times you have burned to death
     *    default: true
     * </pre></blockquote>
     * Another example, with nested definitions, can be found <a
     * href="doc-files/permissions-example_plugin.yml">here</a>.
     *
     * @return 此插件将注册的权限
     * <p>原文：the permissions this plugin will register
     */
    @NotNull
    public List<Permission> getPermissions() {
        if (permissions == null) {
            if (lazyPermissions == null) {
                permissions = ImmutableList.<Permission>of();
            } else {
                permissions = ImmutableList.copyOf(Permission.loadPermissions(lazyPermissions, "Permission node '%s' in plugin description file for " + getFullName() + " is invalid", defaultPerm));
                lazyPermissions = null;
            }
        }
        return permissions;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件注册权限的默认 {@link Permission#getDefault() 默认} 状态.
     * <ul>
     * <li>如果未指定, 将为 {@link PermissionDefault#OP}.
     * <li>使用 {@link PermissionDefault#getByName(String)} 进行匹配.
     * <li>仅影响未定义 <code>default</code> 节点的权限.
     * <li>可以是 {@link PermissionDefault} 中的任何值.
     * </ul>
     * <p>原文：
     * Gives the default {@link Permission#getDefault() default} state of
     * {@link #getPermissions() permissions} registered for the plugin.
     * <ul>
     * <li>If not specified, it will be {@link PermissionDefault#OP}.
     * <li>It is matched using {@link PermissionDefault#getByName(String)}
     * <li>It only affects permissions that do not define the
     *     <code>default</code> node.
     * <li>It may be any value in {@link PermissionDefault}.
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>default-permission</code>.
     * <p>
     * Example:<blockquote><pre>default-permission: NOT_OP</pre></blockquote>
     *
     * @return 插件权限的默认值
     * <p>原文：the default value for the plugin's permissions
     */
    @NotNull
    public PermissionDefault getPermissionDefault() {
        return defaultPerm;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件的每个 {@link PluginAwareness} 集合. 感知表示插件开发者在编译插件时所确认的内容. 某些实现可能定义了 API 中未包含的额外感知. 任何未识别的感知 (不支持的或未来版本中的) 将导致创建一个虚拟对象, 而不是失败.
     * <ul>
     * <li>目前仅支持 {@link PluginAwareness.Flags} 中的枚举值.
     * <li>每个感知以 bang-at (<code>!@</code>) 开头作为标识符.
     * <li>未识别的 (未来的/未实现的) 条目会被静默替换为实现了 PluginAwareness 的通用对象.
     * <li>感知类型必须由运行时定义并由 API 确认, 实际上排除了任何插件类路径中的派生类型.
     * <li><code>awareness</code> 必须使用 <a href="https://en.wikipedia.org/wiki/YAML#Lists">YAML 列表格式</a>.
     * </ul>
     * <p>原文：
     * Gives a set of every {@link PluginAwareness} for a plugin. An awareness
     * dictates something that a plugin developer acknowledges when the plugin
     * is compiled. Some implementations may define extra awarenesses that are
     * not included in the API. Any unrecognized
     * awareness (one unsupported or in a future version) will cause a dummy
     * object to be created instead of failing.
     *
     * <ul>
     * <li>Currently only supports the enumerated values in {@link
     *     PluginAwareness.Flags}.
     * <li>Each awareness starts the identifier with bang-at
     *     (<code>!@</code>).
     * <li>Unrecognized (future / unimplemented) entries are quietly replaced
     *     by a generic object that implements PluginAwareness.
     * <li>A type of awareness must be defined by the runtime and acknowledged
     *     by the API, effectively discluding any derived type from any
     *     plugin's classpath.
     * <li><code>awareness</code> must be in <a
     *     href="https://en.wikipedia.org/wiki/YAML#Lists">YAML list
     *     format</a>.
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>awareness</code>.
     * <p>
     * Example:<blockquote><pre>awareness:
     * - !@UTF8</pre></blockquote>
     * <p>
     * <b>Note:</b> Although unknown versions of some future awareness are
     * gracefully substituted, previous versions of Bukkit (ones prior to the
     * first implementation of awareness) will fail to load a plugin that
     * defines any awareness.
     *
     * @return 包含插件每个感知的集合
     * <p>原文：a set containing every awareness for the plugin
     */
    @NotNull
    public Set<PluginAwareness> getAwareness() {
        return awareness;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回插件的名称, 包括版本. 此方法为便捷方法, 使用 {@link #getName()} 和 {@link #getVersion()} 条目.
     * <p>原文：
     * Returns the name of a plugin, including the version. This method is
     * provided for convenience; it uses the {@link #getName()} and {@link
     * #getVersion()} entries.
     *
     * @return 插件及其版本的描述性名称
     * <p>原文：a descriptive name of the plugin and respective version
     */
    @NotNull
    public String getFullName() {
        return name + " v" + version;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此插件设计支持的 API 版本. 不保证特定格式.
     * <ul>
     * <li>有关支持的 API 版本, 请参阅发行说明.
     * </ul>
     * <p>原文：
     * Gives the API version which this plugin is designed to support. No
     * specific format is guaranteed.
     * <ul>
     * <li>Refer to release notes for supported API versions.
     * </ul>
     * <p>
     * In the plugin.yml, this entry is named <code>api-version</code>.
     * <p>
     * Example:<blockquote><pre>api-version: 1.13</pre></blockquote>
     *
     * @return 插件的 API 版本
     * <p>原文：the version of the plugin
     */
    @Nullable
    public String getAPIVersion() {
        return apiVersion;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此插件所需的类库. 这是一个预览功能.
     * <ul>
     * <li>类库必须是 GAV 说明符, 从 Maven Central 加载.
     * </ul>
     * <p>原文：
     * Gets the libraries this plugin requires. This is a preview feature.
     * <ul>
     * <li>Libraries must be GAV specifiers and are loaded from Maven Central.
     * </ul>
     * <p>
     * Example:<blockquote><pre>libraries:
     *     - com.squareup.okhttp3:okhttp:4.9.0</pre></blockquote>
     *
     * @return 所需的类库
     * <p>原文：required libraries
     */
    @NotNull
    public List<String> getLibraries() {
        return libraries;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 未使用的方法.
     *
     * @return 未使用
     * <p>原文：unused
     * @deprecated 未使用
     * <p>原文：unused
     */
    @Deprecated(since = "1.7.2", forRemoval = true)
    @Nullable
    public String getClassLoaderOf() {
        return classLoaderOf;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将此 PluginDescriptionFile 保存到给定的 writer.
     * <p>原文：
     * Saves this PluginDescriptionFile to the given writer
     *
     * @param writer 输出此文件的 Writer 对象
     * <p>原文：Writer to output this file to
     */
    public void save(@NotNull Writer writer) {
        YAML.get().dump(saveMap(), writer);
    }

    private void loadMap(@NotNull Map<?, ?> map) throws InvalidDescriptionException {
        try {
            name = rawName = map.get("name").toString();

            if (!VALID_NAME.matcher(name).matches()) {
                throw new InvalidDescriptionException("name '" + name + "' contains invalid characters.");
            }
            name = name.replace(' ', '_');
        } catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "name is not defined");
        } catch (ClassCastException ex) {
            throw new InvalidDescriptionException(ex, "name is of wrong type");
        }

        provides = makePluginNameList(map, "provides");

        try {
            version = map.get("version").toString();
        } catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "version is not defined");
        } catch (ClassCastException ex) {
            throw new InvalidDescriptionException(ex, "version is of wrong type");
        }

        try {
            main = map.get("main").toString();
            if (main.startsWith("org.bukkit.")) {
                throw new InvalidDescriptionException("main may not be within the org.bukkit namespace");
            }
        } catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "main is not defined");
        } catch (ClassCastException ex) {
            throw new InvalidDescriptionException(ex, "main is of wrong type");
        }

        if (map.get("commands") != null) {
            ImmutableMap.Builder<String, Map<String, Object>> commandsBuilder = ImmutableMap.<String, Map<String, Object>>builder();
            try {
                for (Map.Entry<?, ?> command : ((Map<?, ?>) map.get("commands")).entrySet()) {
                    ImmutableMap.Builder<String, Object> commandBuilder = ImmutableMap.<String, Object>builder();
                    if (command.getValue() != null) {
                        for (Map.Entry<?, ?> commandEntry : ((Map<?, ?>) command.getValue()).entrySet()) {
                            if (commandEntry.getValue() instanceof Iterable) {
                                // This prevents internal alias list changes
                                ImmutableList.Builder<Object> commandSubList = ImmutableList.<Object>builder();
                                for (Object commandSubListItem : (Iterable<?>) commandEntry.getValue()) {
                                    if (commandSubListItem != null) {
                                        commandSubList.add(commandSubListItem);
                                    }
                                }
                                commandBuilder.put(commandEntry.getKey().toString(), commandSubList.build());
                            } else if (commandEntry.getValue() != null) {
                                commandBuilder.put(commandEntry.getKey().toString(), commandEntry.getValue());
                            }
                        }
                    }
                    commandsBuilder.put(command.getKey().toString(), commandBuilder.build());
                }
            } catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "commands are of wrong type");
            }
            commands = commandsBuilder.build();
        }

        if (map.get("class-loader-of") != null) {
            classLoaderOf = map.get("class-loader-of").toString();
        }

        depend = makePluginNameList(map, "depend");
        softDepend = makePluginNameList(map, "softdepend");
        loadBefore = makePluginNameList(map, "loadbefore");

        if (map.get("website") != null) {
            website = map.get("website").toString();
        }

        if (map.get("description") != null) {
            description = map.get("description").toString();
        }

        if (map.get("load") != null) {
            try {
                order = PluginLoadOrder.valueOf(((String) map.get("load")).toUpperCase(Locale.ROOT).replaceAll("\\W", ""));
            } catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "load is of wrong type");
            } catch (IllegalArgumentException ex) {
                throw new InvalidDescriptionException(ex, "load is not a valid choice");
            }
        }

        if (map.get("authors") != null) {
            ImmutableList.Builder<String> authorsBuilder = ImmutableList.<String>builder();
            if (map.get("author") != null) {
                authorsBuilder.add(map.get("author").toString());
            }
            try {
                for (Object o : (Iterable<?>) map.get("authors")) {
                    authorsBuilder.add(o.toString());
                }
            } catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "authors are of wrong type");
            } catch (NullPointerException ex) {
                throw new InvalidDescriptionException(ex, "authors are improperly defined");
            }
            authors = authorsBuilder.build();
        } else if (map.get("author") != null) {
            authors = ImmutableList.of(map.get("author").toString());
        } else {
            authors = ImmutableList.<String>of();
        }

        if (map.get("contributors") != null) {
            ImmutableList.Builder<String> contributorsBuilder = ImmutableList.<String>builder();
            try {
                for (Object o : (Iterable<?>) map.get("contributors")) {
                    contributorsBuilder.add(o.toString());
                }
            } catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "contributors are of wrong type");
            }
            contributors = contributorsBuilder.build();
        } else {
            contributors = ImmutableList.<String>of();
        }

        if (map.get("default-permission") != null) {
            try {
                defaultPerm = PermissionDefault.getByName(map.get("default-permission").toString());
            } catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "default-permission is of wrong type");
            } catch (IllegalArgumentException ex) {
                throw new InvalidDescriptionException(ex, "default-permission is not a valid choice");
            }
        }

        if (map.get("awareness") instanceof Iterable) {
            Set<PluginAwareness> awareness = new HashSet<PluginAwareness>();
            try {
                for (Object o : (Iterable<?>) map.get("awareness")) {
                    awareness.add((PluginAwareness) o);
                }
            } catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "awareness has wrong type");
            }
            this.awareness = ImmutableSet.copyOf(awareness);
        }

        if (map.get("api-version") != null) {
            apiVersion = map.get("api-version").toString();
        }

        if (map.get("libraries") != null) {
            ImmutableList.Builder<String> contributorsBuilder = ImmutableList.<String>builder();
            try {
                for (Object o : (Iterable<?>) map.get("libraries")) {
                    contributorsBuilder.add(o.toString());
                }
            } catch (ClassCastException ex) {
                throw new InvalidDescriptionException(ex, "libraries are of wrong type");
            }
            libraries = contributorsBuilder.build();
        } else {
            libraries = ImmutableList.<String>of();
        }
        // Paper start - plugin loader api
        if (map.containsKey("paper-plugin-loader")) {
            this.paperPluginLoader = map.get("paper-plugin-loader").toString();
        }

        /*
        Allow skipping the Bukkit/Spigot 'libraries' list. By default, both the 'libraries'
        list and the 'paper-plugin-loader' will contribute libraries. It may be desired to only
        use one or the other. (i.e. 'libraries' on Spigot and 'paper-plugin-loader' on Paper)
        */
        if (map.containsKey("paper-skip-libraries")) {
            String skip = map.get("paper-skip-libraries").toString();
            if (skip.equalsIgnoreCase("true")) {
                this.libraries = ImmutableList.of();
            }
        }
        // Paper end - plugin loader api

        try {
            lazyPermissions = (Map<?, ?>) map.get("permissions");
        } catch (ClassCastException ex) {
            throw new InvalidDescriptionException(ex, "permissions are of the wrong type");
        }

        if (map.get("prefix") != null) {
            prefix = map.get("prefix").toString();
        }
    }

    @NotNull
    private static List<String> makePluginNameList(@NotNull final Map<?, ?> map, @NotNull final String key) throws InvalidDescriptionException {
        final Object value = map.get(key);
        if (value == null) {
            return ImmutableList.of();
        }

        final ImmutableList.Builder<String> builder = ImmutableList.<String>builder();
        try {
            for (final Object entry : (Iterable<?>) value) {
                builder.add(entry.toString().replace(' ', '_'));
            }
        } catch (ClassCastException ex) {
            throw new InvalidDescriptionException(ex, key + " is of wrong type");
        } catch (NullPointerException ex) {
            throw new InvalidDescriptionException(ex, "invalid " + key + " format");
        }
        return builder.build();
    }

    @NotNull
    private Map<String, Object> saveMap() {
        Map<String, Object> map = new HashMap<String, Object>();

        map.put("name", name);
        if (provides != null) {
            map.put("provides", provides);
        }
        map.put("main", main);
        map.put("version", version);
        map.put("order", order.toString());
        map.put("default-permission", defaultPerm.toString());

        if (commands != null) {
            map.put("command", commands);
        }
        if (depend != null) {
            map.put("depend", depend);
        }
        if (softDepend != null) {
            map.put("softdepend", softDepend);
        }
        if (website != null) {
            map.put("website", website);
        }
        if (description != null) {
            map.put("description", description);
        }

        if (authors.size() == 1) {
            map.put("author", authors.get(0));
        } else if (authors.size() > 1) {
            map.put("authors", authors);
        }

        if (contributors != null) {
            map.put("contributors", contributors);
        }

        if (apiVersion != null) {
            map.put("api-version", apiVersion);
        }

        if (libraries != null) {
            map.put("libraries", libraries);
        }

        if (classLoaderOf != null) {
            map.put("class-loader-of", classLoaderOf);
        }

        if (prefix != null) {
            map.put("prefix", prefix);
        }

        return map;
    }

    @NotNull
    private Map<?, ?> asMap(@NotNull Object object) throws InvalidDescriptionException {
        if (object instanceof Map) {
            return (Map<?, ?>) object;
        }
        throw new InvalidDescriptionException("Plugin description file is empty or not properly structured. Is " + object + "but should be a map.");
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件的原始名称. 内部使用.
     *
     * @hidden
     * @return 插件的原始名称
     */
    @ApiStatus.Internal
    @NotNull
    public String getRawName() {
        return rawName;
    }
}
