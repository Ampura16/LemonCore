package org.bukkit;

import com.google.common.collect.ImmutableList;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.logging.Logger;
import io.papermc.paper.configuration.ServerConfiguration;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Warning.WarningState;
import org.bukkit.advancement.Advancement;
import org.bukkit.block.data.BlockData;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarFlag;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.boss.KeyedBossBar;
import org.bukkit.command.CommandException;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityFactory;
import org.bukkit.entity.EntitySnapshot;
import org.bukkit.entity.Player;
import org.bukkit.entity.SpawnCategory;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.help.HelpMap;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemCraftResult;
import org.bukkit.inventory.ItemFactory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MenuType;
import org.bukkit.inventory.Merchant;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.loot.LootTable;
import org.bukkit.map.MapView;
import org.bukkit.packs.ResourcePack;
import org.bukkit.permissions.Permissible;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.ServicesManager;
import org.bukkit.plugin.messaging.Messenger;
import org.bukkit.plugin.messaging.PluginMessageRecipient;
import org.bukkit.profile.PlayerProfile;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.Criteria;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.structure.StructureManager;
import org.bukkit.util.CachedServerIcon;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表执行插件的服务器.
 * <p>原文：
 * Represents a server implementation.
 */
public interface Server extends PluginMessageRecipient, net.kyori.adventure.audience.ForwardingAudience { // Paper

    /**
     * Returns the de facto plugins directory, generally used for storing plugin jars to be loaded,
     * as well as their {@link org.bukkit.plugin.Plugin#getDataFolder() data folders}.
     *
     * <p>Plugins should use {@link org.bukkit.plugin.Plugin#getDataFolder()} rather than traversing this
     * directory manually when determining the location in which to store their data and configuration files.</p>
     *
     * @return plugins directory
     */
    @NotNull
    File getPluginsFolder();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用于所有的操作命令，如控制台命令.
     * <p>原文：
     * Used for all administrative messages, such as an operator using a
     * command.
     * <p>
     * For use in {@link #broadcast(net.kyori.adventure.text.Component, java.lang.String)}.
     */
    public static final String BROADCAST_CHANNEL_ADMINISTRATIVE = "bukkit.broadcast.admin";

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用于所有的公告信息，如通知玩家已加入的用户.
     * <p>原文：
     * Used for all announcement messages, such as informing users that a
     * player has joined.
     * <p>
     * For use in {@link #broadcast(net.kyori.adventure.text.Component, java.lang.String)}.
     */
    public static final String BROADCAST_CHANNEL_USERS = "bukkit.broadcast.user";

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个 {@link Server} 实例的名字.
     * <p>原文：
     * Gets the name of this server implementation.
     *
     * @return 这个服务器实例的名字
     * <p>原文：name of this server implementation
     */
    @NotNull
    public String getName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个 {@link Server} 实例的版本.
     * <p>原文：
     * Gets the version string of this server implementation.
     *
     * @return 这个服务器实例的版本
     * <p>原文：version of this server implementation
     */
    @NotNull
    public String getVersion();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得服务器运行的Bukkit版本.
     * <p>原文：
     * Gets the Bukkit version that this server is running.
     *
     * @return Bukkit版本
     * <p>原文：version of Bukkit
     */
    @NotNull
    public String getBukkitVersion();

    // Paper start - expose game version
    /**
     * Gets the version of game this server implements
     *
     * @return version of game
     */
    @NotNull
    String getMinecraftVersion();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有在线玩家的集合的视图.
     * <p>
     * 此 {@linkplain Collections#unmodifiableCollection(Collection) 视图} is a reused
     * object, making some operations like {@link Collection#size()}
     * zero-allocation.
     * (以下翻译仅供参考)
     * <p>
     * 此集合是由内部表示支持的视图, 因此, 一切修改服务器内部状态的操作将会
     * 立即反映到此集合上. 并不严格保证未来或所有实现都会遵守
     * 返回一个可再利用集合 (一致性)的约定. 不赞成对这个集合执行强制转换或
     * 依赖于接口实现 (例如 {@link Serializable} or {@link List}).
     * <p>
     * 迭代操作 is undefined outside of self-contained main-thread
     * uses. 正常且立即的迭代器会影响集合是否被完全支持.
     * {@link Entity#teleport(Location) 传送},
     * {@link Player#setHealth(double) 死亡},
     * {@link Player#kickPlayer(String) 踢出} 等操作的结果是未知的 (没有罗列完全).
     * 任何对这个集合的异步操作都是不安全的.
     * <p>
     * For safe consequential iteration or mimicking the old array behavior,
     * using {@link Collection#toArray(Object[])} is recommended. For making
     * snapshots, {@link ImmutableList#copyOf(Collection)} is recommended.
     * <p>原文：
     * Gets a view of all currently logged in players. This {@linkplain
     * Collections#unmodifiableCollection(Collection) view} is a reused
     * object, making some operations like {@link Collection#size()}
     * zero-allocation.
     * <p>
     * The collection is a view backed by the internal representation, such
     * that, changes to the internal state of the server will be reflected
     * immediately. However, the reuse of the returned collection (identity)
     * is not strictly guaranteed for future or all implementations. Casting
     * the collection, or relying on interface implementations (like {@link
     * Serializable} or {@link List}), is deprecated.
     * <p>
     * Iteration behavior is undefined outside of self-contained main-thread
     * uses. Normal and immediate iterator use without consequences that
     * affect the collection are fully supported. The effects following
     * (non-exhaustive) {@link Entity#teleport(Location) teleportation},
     * {@link Player#setHealth(double) death}, and {@link Player#kick(
     * Component) kicking} are undefined. Any use of this collection from
     * asynchronous threads is unsafe.
     * <p>
     * For safe consequential iteration or mimicking the old array behavior,
     * using {@link Collection#toArray(Object[])} is recommended. For making
     * snapshots, {@link ImmutableList#copyOf(Collection)} is recommended.
     *
     * @return 所有在线玩家的视图
     * <p>原文：a view of currently online players.
     */
    @NotNull
    public Collection<? extends Player> getOnlinePlayers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器允许进入的最大玩家数.
     * <p>原文：
     * Get the maximum amount of players which can login to this server.
     *
     * @return 服务器的最大玩家数
     * <p>原文：the amount of players this server allows
     */
    public int getMaxPlayers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置服务器允许进入的最大玩家数.
     * <p>原文：
     * Set the maximum amount of players allowed to be logged in at once.
     *
     * @param maxPlayers 服务器的最大玩家数
     * <p>原文：The maximum amount of concurrent players
     */
    void setMaxPlayers(int maxPlayers);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器监听的端口号.
     * <p>原文：
     * Get the game port that the server runs on.
     *
     * @return 服务器端口
     * <p>原文：the port number of this server
     */
    public int getPort();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器的视距.
     * <p>原文：
     * Get the view distance from this server.
     *
     * @return 服务器当前设置的视距
     * <p>原文：the view distance from this server.
     */
    public int getViewDistance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此服务器的模拟距离.
     * <p>原文：
     * Get the simulation distance from this server.
     *
     * @return 此服务器的模拟距离
     * <p>原文：the simulation distance from this server.
     */
    public int getSimulationDistance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器绑定的IP, 如果未指定就返回空字符串.
     * <p>原文：
     * Get the IP that this server is bound to, or empty string if not
     * specified.
     *
     * @return 服务器绑定的IP, 如果未指定就返回空字符串
     * <p>原文：the IP string that this server is bound to, otherwise empty
     *     string
     */
    @NotNull
    public String getIp();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取默认世界的世界类型 (level-type 设置).
     * <p>原文：
     * Get world type (level-type setting) for default world.
     *
     * @return 默认世界的世界类型 (例： DEFAULT, FLAT, DEFAULT_1_1)
     * <p>原文：the value of level-type (e.g. DEFAULT, FLAT, DEFAULT_1_1)
     */
    @NotNull
    public String getWorldType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此服务器是否自然生成结构.
     * <p>原文：
     * Get generate-structures setting.
     *
     * @return true表示服务器可自然生成结构, false反之
     * <p>原文：true if structure generation is enabled, false otherwise
     */
    public boolean getGenerateStructures();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取最大世界大小.
     * <p>原文：
     * Get max world size.
     *
     * @return 为服务器指定的最大世界大小
     * <p>原文：the maximum world size as specified for the server
     */
    public int getMaxWorldSize();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此服务器是否开启了末路之地.
     * <p>原文：
     * Gets whether this server allows the End or not.
     *
     * @return 是否可以进入末路之地
     * <p>原文：whether this server allows the End or not
     */
    public boolean getAllowEnd();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此服务器是否开启了下界.
     * <p>原文：
     * Gets whether this server allows the Nether or not.
     * Separate from the portal game rule.
     *
     * @return 是否可以进入下界
     * <p>原文：whether this server allows the Nether or not
     * @see GameRules#ALLOW_ENTERING_NETHER_USING_PORTALS
     */
    public boolean getAllowNether();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否正在记录玩家的IP地址.
     * <p>原文：
     * Gets whether the server is logging the IP addresses of players.
     *
     * @return 服务器是否正在记录玩家的IP地址
     * <p>原文：whether the server is logging the IP addresses of players
     */
    public boolean isLoggingIPs();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将被启用的数据包列表.
     * <p>原文：
     * Gets a list of packs to be enabled.
     *
     * @return 数据包名称的列表
     * <p>原文：a list of packs names
     */
    @NotNull
    public List<String> getInitialEnabledPacks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取不会被自动启用的数据包列表.
     * <p>原文：
     * Gets a list of packs that will not be enabled automatically.
     *
     * @return 数据包名称的列表
     * <p>原文：a list of packs names
     */
    @NotNull
    public List<String> getInitialDisabledPacks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器Tick管理器.
     * <p>原文：
     * Get the ServerTick Manager.
     *
     * @return ServerTick管理器实例
     * <p>原文：the manager
     */
    @NotNull
    public ServerTickManager getServerTickManager();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器配置为发送给客户端的资源包.
     * <p>原文：
     * Gets the resource pack configured to be sent to clients by the server.
     *
     * @return 服务器资源包
     * <p>原文：the resource pack
     */
    @Nullable
    public ResourcePack getServerResourcePack();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器资源包的URI, 如果未指定则返回空字符串.
     * <p>原文：
     * Gets the server resource pack uri, or empty string if not specified.
     *
     * @return 服务器资源包的URI, 否则返回空字符串
     * <p>原文：the server resource pack uri, otherwise empty string
     */
    @NotNull
    public String getResourcePack();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器资源包的SHA-1摘要, 如果未指定则返回空字符串.
     * <p>原文：
     * Gets the SHA-1 digest of the server resource pack, or empty string if
     * not specified.
     *
     * @return 服务器资源包的SHA-1摘要, 否则返回空字符串
     * <p>原文：the SHA-1 digest of the server resource pack, otherwise empty
     *     string
     */
    @NotNull
    public String getResourcePackHash();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当服务器资源包为必需时显示的自定义提示消息, 如果未指定则返回空字符串.
     * <p>原文：
     * Gets the custom prompt message to be shown when the server resource
     * pack is required, or empty string if not specified.
     *
     * @return 当服务器资源包为必需时显示的自定义提示消息, 否则返回空字符串
     * <p>原文：the custom prompt message to be shown when the server resource,
     *     otherwise empty string
     */
    @NotNull
    public String getResourcePackPrompt();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器资源包是否为强制要求.
     * <p>原文：
     * Gets whether the server resource pack is enforced.
     *
     * @return 服务器资源包是否为强制要求
     * <p>原文：whether the server resource pack is enforced
     */
    public boolean isResourcePackRequired();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此服务器是否开启了白名单.
     * <p>原文：
     * Gets whether this server has a whitelist or not.
     *
     * @return 是否开启了白名单
     * <p>原文：whether this server has a whitelist or not
     */
    public boolean hasWhitelist();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此服务器是否开启白名单.
     * <p>原文：
     * Sets if the server is whitelisted.
     *
     * @param value true为开, false为关
     * <p>原文：true for whitelist on, false for off
     */
    public void setWhitelist(boolean value);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器白名单是否为强制执行.
     * <p>
     * 如果白名单为强制执行, 当服务器白名单被重载时, 不在白名单中的玩家将被断开连接.
     * <p>原文：
     * Gets whether the server whitelist is enforced.
     *
     * If the whitelist is enforced, non-whitelisted players will be
     * disconnected when the server whitelist is reloaded.
     *
     * @return 服务器白名单是否为强制执行
     * <p>原文：whether the server whitelist is enforced
     */
    public boolean isWhitelistEnforced();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置服务器白名单是否为强制执行.
     * <p>
     * 如果白名单为强制执行, 当服务器白名单被重载时, 不在白名单中的玩家将被断开连接.
     * <p>原文：
     * Sets if the server whitelist is enforced.
     *
     * If the whitelist is enforced, non-whitelisted players will be
     * disconnected when the server whitelist is reloaded.
     *
     * @param value true为强制执行, false为不强制执行
     * <p>原文：true for enforced, false for not
     */
    public void setWhitelistEnforced(boolean value);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有已被添加到白名单的玩家.
     * <p>原文：
     * Gets a list of whitelisted players.
     *
     * @return 用Set存储的所有被添加到白名单的玩家
     * <p>原文：a set containing all whitelisted players
     */
    @NotNull
    public Set<OfflinePlayer> getWhitelistedPlayers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从硬盘重载白名单列表.
     * <p>原文：
     * Reloads the whitelist from disk.
     */
    public void reloadWhitelist();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 广播一条消息到所有在线玩家.
     * <p>
     * 这与调用 {@link #broadcast(java.lang.String,
     * java.lang.String)} 并将第二个参数设为 {@link
     * Server#BROADCAST_CHANNEL_USERS} 等效.
     * <p>原文：
     * Broadcast a message to all players.
     * <p>
     * This is the same as calling {@link #broadcast(java.lang.String,
     * java.lang.String)} to {@link #BROADCAST_CHANNEL_USERS}
     *
     * @param message 要广播的消息
     * <p>原文：the message
     * @return 成功接收此消息的玩家数
     * <p>原文：the number of players
     * @deprecated use {@link #broadcast(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    default int broadcastMessage(@NotNull String message) {
        return this.broadcast(message, BROADCAST_CHANNEL_USERS);
    }

    // Paper start
    /**
     * Sends the component to all online players.
     *
     * @param component the component to send
     * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
     */
    @Deprecated
    public default void broadcast(@NotNull net.md_5.bungee.api.chat.BaseComponent component) {
        spigot().broadcast(component);
    }

    /**
     * Sends an array of components as a single message to all online players.
     *
     * @param components the components to send
     * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
     */
    @Deprecated
    public default void broadcast(@NotNull net.md_5.bungee.api.chat.BaseComponent... components) {
        spigot().broadcast(components);
    }
    // Paper end

    /**
     * Sends a message with the MiniMessage format to the server.
     * <p>
     * See <a href="https://docs.advntr.dev/minimessage/">MiniMessage docs</a>
     * for more information on the format.
     *
     * @param message MiniMessage content
     */
    default void sendRichMessage(final @NotNull String message) {
        this.sendMessage(MiniMessage.miniMessage().deserialize(message, this));
    }

    /**
     * Sends a message with the MiniMessage format to the server.
     * <p>
     * See <a href="https://docs.advntr.dev/minimessage/">MiniMessage docs</a> and <a href="https://docs.advntr.dev/minimessage/dynamic-replacements">MiniMessage Placeholders docs</a>
     * for more information on the format.
     *
     * @param message MiniMessage content
     * @param resolvers resolvers to use
     */
    default void sendRichMessage(final @NotNull String message, final @NotNull TagResolver... resolvers) {
        this.sendMessage(MiniMessage.miniMessage().deserialize(message, this, resolvers));
    }

    /**
     * Sends a plain message to the server.
     *
     * @param message plain message
     */
    default void sendPlainMessage(final @NotNull String message) {
        this.sendMessage(Component.text(message));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取更新文件夹的名字. 系统将会在插件加载时选择适当的时机利用此文件夹来安全地更新插件.
     * <p>
     * 更新文件夹的位置相对于插件文件夹(译注:一般指服务端plugins文件夹下的update文件夹).
     * <p>
     * Tips：如何使用更新文件夹来实现更新您的插件呢? (服主和开发者都可以了解下)：
     * <ol>
     * <li>创建更新文件夹，已有则跳过此步.
     * <li>下载您要更新的插件到此目录(plugins/update) (注意：jar文件名必须和在插件目录下的jar文件名一样，否则不起作用).
     * <li>重载/重启服务器.
     * <li>OK，看效果吧.
     * </ol>
     * <p>原文：
     * Gets the name of the update folder. The update folder is used to safely
     * update plugins at the right moment on a plugin load.
     * <p>
     * The update folder name is relative to the plugins folder.
     *
     * @return 更新文件夹的名字
     * <p>原文：the name of the update folder
     */
    @NotNull
    public String getUpdateFolder();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取更新文件夹的 File 实例.
     * <p>
     * 系统将会在插件加载时选择适当的时机利用此文件夹来安全地更新插件.
     * <p>原文：
     * Gets the update folder. The update folder is used to safely update
     * plugins at the right moment on a plugin load.
     *
     * @return 表示更新文件夹的 File 实例
     * <p>原文：the update folder
     */
    @NotNull
    public File getUpdateFolderFile();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器的最小连接间隔设定.
     * <p>
     * 译注:单位为毫秒.
     * <p>原文：
     * Gets the value of the connection throttle setting.
     *
     * @return 服务器的最小连接间隔数设定
     * <p>原文：the value of the connection throttle setting
     */
    public long getConnectionThrottle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每隔多少tick应该生成一次动物.
     * <b>例如:</b>
     * <ul>
     * <li>此值为 1 表示服务器会在每个tick尝试生成动物.
     * <li>此值为 400 表示服务器会每隔400tick尝试生成动物.
     * <li>此值小于 0 表示会使用Minecraft的默认设置.
     * </ul>
     * <b>注意:</b> 如果设为 0, 动物生成会被禁用.
     * 我们推荐使用 spawn-animals 选项来代替将其设为0.
     * <p>
     * Minecraft使用的默认值: 400.
     * <p>原文：
     * Gets default ticks per animal spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn monsters
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn monsters
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, animal spawning will be disabled. We
     * recommend using spawn-animals to control this instead.
     * <p>
     * Minecraft default: 400.
     *
     * @return 每隔多少tick应该生成一次动物
     * <p>原文：the default ticks per animal spawns value
     * @deprecated 建议使用 {@link #getTicksPerSpawns(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getTicksPerSpawns(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getTicksPerAnimalSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.ANIMAL);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每隔多少tick应该生成一次怪物.
     * <b>例如:</b>
     * <ul>
     * <li>此值为 1 表示服务器会在每个tick尝试生成怪物.
     * <li>此值为 400 表示服务器会每隔400tick尝试生成怪物.
     * <li>此值小于 0 表示会使用Minecraft的默认设置.
     * </ul>
     * <b>注意:</b> 如果设为 0, 怪物生成会被禁用.
     * 我们推荐使用 spawn-monsters 选项来代替将其设为0.
     * <p>
     * Minecraft使用的默认值: 1.
     * <p>原文：
     * Gets the default ticks per monster spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn monsters
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn monsters
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, monsters spawning will be disabled. We
     * recommend using spawn-monsters to control this instead.
     * <p>
     * Minecraft default: 1.
     *
     * @return 每隔多少tick应该生成一次怪物
     * <p>原文：the default ticks per monsters spawn value
     * @deprecated 建议使用 {@link #getTicksPerSpawns(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getTicksPerSpawns(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getTicksPerMonsterSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.MONSTER);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每隔多少tick应该生成一次水生动物.
     * <p>
     * <b>例如:</b>
     * <ul>
     * <li>此值为 1 表示服务器会在每个tick尝试生成水生动物.
     * <li>此值为 400 表示服务器会每隔400tick尝试生成水生动物.
     * <li>此值小于 0 表示会使用Minecraft的默认设置.
     * </ul>
     * <p>
     * <b>注意:</b> 如果设为 0, 水生动物生成会被禁用.
     * <p>
     * Minecraft使用的默认值: 1.
     * <p>原文：
     * Gets the default ticks per water mob spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn water mobs
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn water mobs
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, water mobs spawning will be disabled.
     * <p>
     * Minecraft default: 1.
     *
     * @return 每隔多少tick应该生成一次怪物
     * <p>原文：the default ticks per water mobs spawn value
     * @deprecated 建议使用 {@link #getTicksPerSpawns(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getTicksPerSpawns(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getTicksPerWaterSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.WATER_ANIMAL);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每隔多少tick应该生成一次水生环境生物(通常指鱼类).
     * <p>
     * <b>例如:</b>
     * <ul>
     * <li>此值为 1 表示服务器会在每个tick尝试生成水生环境生物.
     * <li>此值为 400 表示服务器会每隔400tick尝试生成水生环境生物.
     * <li>此值小于 0 表示会使用Minecraft的默认设置.
     * </ul>
     * <p>
     * <b>注意:</b> 如果设为 0, 水生环境生物生成会被禁用.
     * <p>
     * Minecraft使用的默认值: 1.
     * <p>原文：
     * Gets the default ticks per water ambient mob spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn water ambient mobs
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn water ambient mobs
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, ambient mobs spawning will be disabled.
     * <p>
     * Minecraft default: 1.
     *
     * @return 每隔多少tick应该生成一次水生环境生物
     * <p>原文：the default ticks per water ambient mobs spawn value
     * @deprecated 建议使用 {@link #getTicksPerSpawns(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getTicksPerSpawns(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getTicksPerWaterAmbientSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.WATER_AMBIENT);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每隔多少tick应该生成一次水下穴居生物.
     * <p>
     * <b>例如:</b>
     * <ul>
     * <li>此值为 1 表示服务器会在每个tick尝试生成水下穴居生物.
     * <li>此值为 400 表示服务器会每隔400tick尝试生成水下穴居生物.
     * <li>此值小于 0 表示会使用Minecraft的默认设置.
     * </ul>
     * <p>
     * <b>注意:</b> 如果设为 0, 水下穴居生物生成会被禁用.
     * <p>
     * Minecraft使用的默认值: 1.
     * <p>原文：
     * Gets the default ticks per water underground creature spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn water underground creature
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn water underground creature
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, water underground creature spawning will be disabled.
     * <p>
     * Minecraft default: 1.
     *
     * @return 每隔多少tick应该生成一次水下穴居生物
     * <p>原文：the default ticks per water underground creature spawn value
     * @deprecated 建议使用 {@link #getTicksPerSpawns(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getTicksPerSpawns(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getTicksPerWaterUndergroundCreatureSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.WATER_UNDERGROUND_CREATURE);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每隔多少tick应该生成一次环境生物(即蝙蝠).
     * <p>
     * <b>例如:</b>
     * <ul>
     * <li>此值为 1 表示服务器会在每个tick尝试生成环境生物.
     * <li>此值为 400 表示服务器会每隔400tick尝试生成环境生物.
     * <li>此值小于 0 表示会使用Minecraft的默认设置.
     * </ul>
     * <p>
     * <b>注意:</b> 如果设为 0, 环境生物生成会被禁用.
     * <p>
     * Minecraft使用的默认值: 1.
     * <p>原文：
     * Gets the default ticks per ambient mob spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn ambient mobs
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn ambient mobs
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, ambient mobs spawning will be disabled.
     * <p>
     * Minecraft default: 1.
     *
     * @return 每隔多少tick应该生成一次环境生物
     * <p>原文：the default ticks per ambient mobs spawn value
     * @deprecated 建议使用 {@link #getTicksPerSpawns(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getTicksPerSpawns(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getTicksPerAmbientSpawns() {
        return this.getTicksPerSpawns(SpawnCategory.AMBIENT);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定{@link SpawnCategory}生成类型的默认tick间隔.
     * <p>
     * <b>例如:</b>
     * <ul>
     * <li>此值为 1 表示服务器会在每个tick尝试生成{@link SpawnCategory}类型的生物.
     * <li>此值为 400 表示服务器会每隔400tick尝试生成{@link SpawnCategory}类型的生物.
     * <li>此值小于 0 表示会使用Minecraft的默认设置.
     * </ul>
     * <p>
     * <b>注意:</b> 如果设为 0, {@link SpawnCategory}类型的生物生成会被禁用.
     * <p>
     * Minecraft使用的默认值: 1.
     * <br>
     * <b>注意: </b> {@link SpawnCategory#MISC}类型不会被考虑在内.
     * <p>原文：
     * Gets the default ticks per {@link SpawnCategory} spawns value.
     * <p>
     * <b>Example Usage:</b>
     * <ul>
     * <li>A value of 1 will mean the server will attempt to spawn {@link SpawnCategory} mobs
     *     every tick.
     * <li>A value of 400 will mean the server will attempt to spawn {@link SpawnCategory} mobs
     *     every 400th tick.
     * <li>A value below 0 will be reset back to Minecraft's default.
     * </ul>
     * <p>
     * <b>Note:</b> If set to 0, {@link SpawnCategory} mobs spawning will be disabled.
     *
     * @param spawnCategory 生成类别
     * <p>原文：the category of spawn
     * @return 指定{@link SpawnCategory}生成类型的默认tick间隔
     * <p>原文：the default ticks per {@link SpawnCategory} mobs spawn value
     * @throws IllegalArgumentException if the category is {@link SpawnCategory#MISC}
     */
    public int getTicksPerSpawns(@NotNull SpawnCategory spawnCategory);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用给定玩家名模糊搜索玩家（名字最近似的玩家）.
     * <p>
     * 使用 {@link #getPlayerExact(String)} 以精准匹配某个玩家，
     * 若要搜索所有匹配的玩家，请使用 {@link #matchPlayer(String)}.
     * <p>
     * 译注: 注意此方法用于模糊搜索——当服务器内有玩家 aaa, aab, abc 时,
     * 使用 <code>getPlayer("ab")</code> 会返回玩家 abc 的实例, 而如果 ab 在线则会返回 ab 的实例
     * 因此<b>可能存在严重的安全问题</b>, 需要精确搜索(例如给予OP时)请用
     * {@link #getPlayerExact(java.lang.String) } 或 {@link
     * #getPlayer(java.util.UUID) } 代替.
     * <p>原文：
     * Gets a player whose name matches the given name closest.
     * <p>
     * Use {@link #getPlayerExact(String)} to get the player matching the input exactly
     * and {@link #matchPlayer(String)} if you want a list of all players matching the input.
     * <p>
     * This method may not return objects for offline players.
     *
     * @param name 用来查找的玩家名
     * <p>原文：the name to look up
     * @return 如果找到了则返回玩家对象, 否则返回null
     * <p>原文：a player if one was found, null otherwise
     */
    @Nullable
    public Player getPlayer(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用给定玩家名精确查找玩家, 不区分大小写.
     * <p>原文：
     * Gets the player with the exact given name, case insensitive.
     *
     * @param name 用于检索的精确玩家名称, 不区分大小写
     * <p>原文：Exact name of the player to retrieve
     * @return 如果找到了则返回玩家对象, 否则返回null
     * <p>原文：a player object if one was found, null otherwise
     */
    @Nullable
    public Player getPlayerExact(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试获取所有匹配给定名称的玩家, 并返回包含了一切可能匹配的列表.
     * <p>
     * 这个列表没有经过任何特殊排序. 如果能在在线玩家内精确匹配到给定名称,
     * 则返回一个只包含单个结果的列表.
     * <p>
     * 译注: 设有在线玩家 abcd, efg, dbca 那么 <code>matchPlayer("bc")</code> 会
     * 返回一个包含 [abcd, dbca] 的列表. 但如果此时玩家 bc 上线了, 再执行相同的查找
     * 就只会返回包含一个结果的列表 [bc].
     * <p>原文：
     * Attempts to match any players with the given name, and returns a list
     * of all possibly matches.
     * <p>
     * This list is not sorted in any particular order. If an exact match is
     * found, the returned list will only contain a single result.
     *
     * @param name 需要匹配的(部分)名称
     * <p>原文：the (partial) name to match
     * @return 包含所有可能的匹配结果的列表
     * <p>原文：list of all possible players
     */
    @NotNull
    public List<Player> matchPlayer(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用给定{@link UUID}获取玩家.
     * <p>原文：
     * Gets the player with the given UUID.
     *
     * @param id 要获取的玩家的{@link UUID}
     * <p>原文：UUID of the player to retrieve
     * @return 如果找到了则返回玩家对象, 否则返回null
     * <p>原文：a player object if one was found, null otherwise
     */
    @Nullable
    public Player getPlayer(@NotNull UUID id);

    // Paper start
    /**
     * Gets the unique ID of the player currently known as the specified player name
     * In Offline Mode, will return an Offline UUID
     *
     * @param playerName the player name to look up the unique ID for
     * @return A UUID, or null if that player name is not registered with Minecraft and the server is in online mode
     */
    @Nullable
    public UUID getPlayerUniqueId(@NotNull String playerName);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取插件管理器以与其他插件进行交互.
     * <p>原文：
     * Gets the plugin manager for interfacing with plugins.
     *
     * @return 此服务器的插件管理器
     * <p>原文：a plugin manager for this Server instance
     */
    @NotNull
    public PluginManager getPluginManager();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于管理调度任务的调度器.
     * <p>原文：
     * Gets the scheduler for managing scheduled events.
     *
     * @return 此服务器的调度器服务
     * <p>原文：a scheduling service for this server
     */
    @NotNull
    public BukkitScheduler getScheduler();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务管理器.
     * <p>原文：
     * Gets a services manager.
     *
     * @return 服务管理器
     * <p>原文：s services manager
     */
    @NotNull
    public ServicesManager getServicesManager();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前服务器加载的所有世界的列表.
     * <p>原文：
     * Gets a list of all worlds on this server.
     *
     * @return 所有已经被服务器加载的世界列表
     * <p>原文：a list of worlds
     */
    @NotNull
    public List<World> getWorlds();

    // Paper start
    /**
     * Gets whether the worlds are being ticked right now.
     *
     * @return true if the worlds are being ticked, false otherwise.
     */
    public boolean isTickingWorlds();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用给定的世界生成器来创建或者加载一个世界.
     * <p>
     * 如果这个世界已经被加载了, 那么就会返回等同于调用
     * <code>getWorld(creator.name())</code> 的结果.
     * <p>原文：
     * Creates or loads a world with the given name using the specified
     * options.
     * <p>
     * If the world is already loaded, it will just return the equivalent of
     * getWorld(creator.name()).
     * <p>
     * Do note that un/loading worlds mid-tick may have potential side effects, we strongly recommend
     * ensuring that you're not un/loading worlds midtick by checking {@link Bukkit#isTickingWorlds()}
     *
     * @param creator 加载或者创建这个世界的时候要用的世界生成器
     * <p>原文：the options to use when creating the world
     * @return 生成的或者加载的世界对象
     * <p>原文：newly created or loaded world
     * @throws IllegalStateException when {@link #isTickingWorlds() isTickingWorlds} is true
     */
    @Nullable
    public World createWorld(@NotNull WorldCreator creator);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 卸载给定名称对应的世界.
     * <p>原文：
     * Unloads a world with the given name.
     * <p>
     * Do note that un/loading worlds mid-tick may have potential side effects, we strongly recommend
     * ensuring that you're not un/loading worlds midtick by checking {@link Bukkit#isTickingWorlds()}
     *
     * @param name 要卸载的世界的名字
     * <p>原文：Name of the world to unload
     * @param save 是否在卸载世界前保存区块数据
     * <p>原文：whether to save the chunks before unloading
     * @return 如果成功返回true, 否则返回false
     * <p>原文：true if successful, false otherwise
     * @throws IllegalStateException when {@link #isTickingWorlds() isTickingWorlds} is true
     */
    public boolean unloadWorld(@NotNull String name, boolean save);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 卸载指定的世界.
     * <p>原文：
     * Unloads the given world.
     * <p>
     * Do note that un/loading worlds mid-tick may have potential side effects, we strongly recommend
     * ensuring that you're not un/loading worlds midtick by checking {@link Bukkit#isTickingWorlds()}
     *
     * @param world 要卸载的世界
     * <p>原文：the world to unload
     * @param save 是否在卸载世界前保存区块数据
     * <p>原文：whether to save the chunks before unloading
     * @return 如果成功返回true, 否则返回false
     * <p>原文：true if successful, false otherwise
     * @throws IllegalStateException when {@link #isTickingWorlds() isTickingWorlds} is true
     */
    public boolean unloadWorld(@NotNull World world, boolean save);

    /**
     * Gets the world that players respawn in.
     *
     * @return the world that players respawn in
     * @see World#getSpawnLocation()
     */
    public @NotNull World getRespawnWorld();

    /**
     * Sets the world that players respawn in.
     *
     * @param world the world that players should respawn in
     * @see World#setSpawnLocation(Location)
     */
    public void setRespawnWorld(@NotNull World world);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用给定的名称查找世界.
     * <p>原文：
     * Gets the world with the given name.
     *
     * @param name 世界名称
     * <p>原文：the name of the world to retrieve
     * @return 给定名称对应世界的实例, 没找到则返回null
     * <p>原文：a world with the given name, or null if none exists
     */
    @Nullable
    public World getWorld(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用给定{@linkplain UUID 唯一ID}查找世界.
     * <p>原文：
     * Gets the world from the given Unique ID.
     *
     * @param uid 要查找的世界的唯一ID
     * <p>原文：a unique-id of the world to retrieve
     * @return 没找到则返回null
     * <p>原文：a world with the given Unique ID, or null if none exists
     */
    @Nullable
    public World getWorld(@NotNull UUID uid);

    // Paper start
    /**
     * Gets the world from the given NamespacedKey
     *
     * @param worldKey the NamespacedKey of the world to retrieve
     * @return a world with the given NamespacedKey, or null if none exists
     */
    @Nullable
    default World getWorld(@NotNull NamespacedKey worldKey) {
        return getWorld((net.kyori.adventure.key.Key) worldKey);
    }

    /**
     * Gets the world from the given Key
     *
     * @param worldKey the Key of the world to retrieve
     * @return a world with the given Key, or null if none exists
     */
    @Nullable
    World getWorld(@NotNull net.kyori.adventure.key.Key worldKey);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个新的虚拟{@link WorldBorder}.
     * <p>
     * 注意, 服务器创建的世界边界不会考虑任何世界缩放效果(即在下界中坐标不会除以8).
     * <p>原文：
     * Create a new virtual {@link WorldBorder}.
     * <p>
     * Note that world borders created by the server will not respect any world
     * scaling effects (i.e. coordinates are not divided by 8 in the nether)
     * and will not deal damage to players outside their bounds.
     *
     * @return 创建的世界边界实例
     * <p>原文：the created world border instance
     * @see Player#setWorldBorder(WorldBorder)
     */
    @NotNull
    public WorldBorder createWorldBorder();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用给定物品ID获取地图.
     * <p>原文：
     * Gets the map from the given item ID.
     *
     * @param id 要获取的地图的ID
     * <p>原文：the id of the map to get
     * @return 如果找到则返回对应的 {@link MapView}, 否则返回null
     * <p>原文：a map view if it exists, or null otherwise
     */
    @Nullable
    MapView getMap(int id);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个新地图并自动分配一个ID.
     * <p>原文：
     * Create a new map with an automatically assigned ID.
     *
     * @param world 地图所属的世界
     * <p>原文：the world the map will belong to
     * @return 新创建的 {@link MapView}
     * <p>原文：a newly created map view
     */
    @NotNull
    public MapView createMap(@NotNull World world);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个新的探索者地图, 以定位最近的指定{@link StructureType}结构.
     * <br>
     * 此方法使用实现的默认值作为搜索半径和是否寻找未探索结构(通常为100, true).
     * <p>原文：
     * Create a new explorer map targeting the closest nearby structure of a
     * given {@link StructureType}.
     * <br>
     * This method uses implementation default values for radius and
     * findUnexplored (usually 100, true).
     *
     * @param world 地图所属的世界
     * <p>原文：the world the map will belong to
     * @param location 查找最近结构的起始位置
     * <p>原文：the origin location to find the nearest structure
     * @param structureType 要查找的结构类型
     * <p>原文：the type of structure to find
     * @return 新创建的物品堆栈
     * <p>原文：a newly created item stack
     * @see World#locateNearestStructure(org.bukkit.Location,
     *      org.bukkit.StructureType, int, boolean)
     * @deprecated use {@link #createExplorerMap(World, Location, org.bukkit.generator.structure.StructureType, org.bukkit.map.MapCursor.Type)}
     */
    @Deprecated // Paper
    @NotNull
    default ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType) {
        return this.createExplorerMap(world, location, structureType, 100, true);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个新的探索者地图, 以定位最近的指定{@link StructureType}结构.
     * <br>
     * 此方法使用实现的默认值作为搜索半径和是否寻找未探索结构(通常为100, true).
     * <p>原文：
     * Create a new explorer map targeting the closest nearby structure of a
     * given {@link StructureType}.
     *
     * @param world 地图所属的世界
     * <p>原文：the world the map will belong to
     * @param location 查找最近结构的起始位置
     * <p>原文：the origin location to find the nearest structure
     * @param structureType 要查找的结构类型
     * <p>原文：the type of structure to find
     * @param radius 搜索半径, 详见 World#locateNearestStructure 获取更多信息
     * <p>原文：radius to search, see World#locateNearestStructure for more
     *               information
     * @param findUnexplored 是否查找未探索的结构
     * <p>原文：whether to find unexplored structures
     * @return 新创建的物品堆栈
     * <p>原文：the newly created item stack
     * @see World#locateNearestStructure(org.bukkit.Location,
     *      org.bukkit.StructureType, int, boolean)
     * @deprecated use {@link #createExplorerMap(World, Location, org.bukkit.generator.structure.StructureType, org.bukkit.map.MapCursor.Type, int, boolean)}
     */
    @Deprecated // Paper
    @NotNull
    public ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType, int radius, boolean findUnexplored);
    // Paper start
    /**
     * Create a new explorer map targeting the closest nearby structure of a
     * given {@link org.bukkit.generator.structure.StructureType}.
     * <br>
     * This method uses implementation default values for radius and
     * findUnexplored (usually 100, true).
     *
     * @param world the world the map will belong to
     * @param location the origin location to find the nearest structure
     * @param structureType the type of structure to find
     * @param mapIcon the map icon to use on the map
     * @return a newly created item stack or null if it can't find a location
     *
     * @see World#locateNearestStructure(org.bukkit.Location,
     *      org.bukkit.generator.structure.StructureType, int, boolean)
     */
    default @Nullable ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull org.bukkit.generator.structure.StructureType structureType, @NotNull org.bukkit.map.MapCursor.Type mapIcon) {
        return this.createExplorerMap(world, location, structureType, mapIcon, 100, true);
    }

    /**
     * Create a new explorer map targeting the closest nearby structure of a
     * given {@link org.bukkit.generator.structure.StructureType}.
     *
     * @param world the world the map will belong to
     * @param location the origin location to find the nearest structure
     * @param structureType the type of structure to find
     * @param mapIcon the map icon to use on the map
     * @param radius radius to search, see World#locateNearestStructure for more
     *               information
     * @param findUnexplored whether to find unexplored structures
     * @return the newly created item stack or null if it can't find a location
     *
     * @see World#locateNearestStructure(org.bukkit.Location,
     *      org.bukkit.generator.structure.StructureType, int, boolean)
     */
    @Nullable ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull org.bukkit.generator.structure.StructureType structureType, @NotNull org.bukkit.map.MapCursor.Type mapIcon, int radius, boolean findUnexplored);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 重新加载服务器并刷新设置和插件信息.
     * <p>原文：
     * Reloads the server, refreshing settings and plugin information.
     */
    public void reload();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 只重载Minecraft游戏数据. 这包括自定义的进度和掉落表.
     * <p>原文：
     * Reload only the Minecraft data for the server. This includes custom
     * advancements and loot tables.
     */
    public void reloadData();

    // Paper start - update reloadable data
    /**
     * Updates all advancement, tag, and recipe data to all connected clients.
     * Useful for updating clients to new advancements/recipes/tags.
     * @see #updateRecipes()
     */
    void updateResources();

    /**
     * Updates recipe data and the recipe book to each player. Useful for
     * updating clients to new recipes.
     * @see #updateResources()
     */
    void updateRecipes();
    // Paper end - update reloadable data

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回与此服务器实例绑定的主{@link Logger}.
     * <p>原文：
     * Returns the primary logger associated with this server instance.
     *
     * @return 与此服务器绑定的 {@link Logger}
     * <p>原文：Logger associated with this server
     * @see org.bukkit.plugin.Plugin#getSLF4JLogger()
     * @apiNote This logger is for the Minecraft server software, not for specific plugins. You should
     * use a logger for a specific plugin, either via {@link org.bukkit.plugin.Plugin#getSLF4JLogger()}
     * or {@link org.bukkit.plugin.Plugin#getLogger()} or create a specific logger for a class via slf4j.
     * That way, log messages contain contextual information about the source of the message.
     */
    @NotNull
    @org.jetbrains.annotations.ApiStatus.Internal // Paper - internalize Bukkit#getLogger
    public Logger getLogger();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用给定命令名或别名获取 {@link PluginCommand}.
     * <p>原文：
     * Gets a {@link PluginCommand} with the given name or alias.
     *
     * @param name 命令名或命令别名
     * <p>原文：the name of the command to retrieve
     * @return 若找到则返回对应的命令实例, 找不到则返回null
     * <p>原文：a plugin command if found, null otherwise
     */
    @Nullable
    public PluginCommand getPluginCommand(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 保存已加载的玩家信息.
     * <p>原文：
     * Writes loaded players to disk.
     */
    public void savePlayers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在服务器执行一个命令 (如果命令存在).
     * <p>原文：
     * Dispatches a command on this server, and executes it if found.
     *
     * @param sender 执行该命令的发送者
     * <p>原文：the apparent sender of the command
     * @param commandLine 命令 + 参数. 例如: <code>test abc 123</code>
     * <p>原文：the command + arguments. Example: <code>test abc
     *     123</code>
     * @return 若命令未找到返回false
     * <p>原文：returns false if no target is found
     * @throws CommandException 当执行命令期间出现未捕获的异常时抛出
     * <p>原文：thrown when the executor for the given command
     *     fails with an unhandled exception
     */
    public boolean dispatchCommand(@NotNull CommandSender sender, @NotNull String commandLine) throws CommandException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向合成管理器添加一个合成配方.
     * <p>原文：
     * Adds a recipe to the crafting manager.
     * Recipes added with this method won't be sent to the client automatically.
     * <p>
     * Players still have to discover recipes via {@link Player#discoverRecipe(NamespacedKey)}
     * before seeing them in their recipe book.
     *
     * @param recipe 要添加的合成配方
     * <p>原文：the recipe to add
     * @return 是否成功的地添加了合成配方
     * <p>原文：true if the recipe was added, false if it wasn't for some reason
     * @see #addRecipe(Recipe, boolean)
     */
    @Contract("null -> false")
    default boolean addRecipe(@Nullable Recipe recipe) {
        return this.addRecipe(recipe, false);
    }

    // Paper start - method to send recipes immediately
    /**
     * Adds a recipe to the crafting manager.
     *
     * @apiNote resendRecipes is ignored for now for stability reasons, recipes will always be updated
     * @param recipe the recipe to add
     * @param resendRecipes true to update the client with the full set of recipes
     * @return true if the recipe was added, false if it wasn't for some reason
     */
    @Contract("null, _ -> false")
    boolean addRecipe(@Nullable Recipe recipe, boolean resendRecipes);
    // Paper end - method to send recipes immediately

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取对指定物品适用的所有合成配方的列表.
     * 在比对过程中物品堆叠数量会被忽略.
     * 如果给定物品堆的耐久度为 -1, 将匹配任意的数据值.
     * <p>原文：
     * Get a list of all recipes for a given item. The stack size is ignored
     * in comparisons. If the durability is -1, it will match any data value.
     *
     * @param result 要匹配的物品
     * <p>原文：the item to match against recipe results
     * @return 对指定物品适用的所有合成配方的列表
     * <p>原文：a list of recipes with the given result
     */
    @NotNull
    public List<Recipe> getRecipesFor(@NotNull ItemStack result);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据指定的 key 获取{@link Recipe 配方}.
     * <p>原文：
     * Get the {@link Recipe} for the given key.
     *
     * @param recipeKey 配方的 key
     * <p>原文：the key of the recipe to return
     * @return 给定 key 对应的配方, 若未找到返回 null
     * <p>原文：the recipe for the given key or null.
     */
    @Nullable
    public Recipe getRecipe(@NotNull NamespacedKey recipeKey);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定物品堆栈列表对应的{@link Recipe 配方}.
     *
     * <p>该列表以合成矩阵的格式排列, 索引遵循以下模式:</p>
     *
     * <pre>
     * [ 0 1 2 ]
     * [ 3 4 5 ]
     * [ 6 7 8 ]
     * </pre>
     *
     * <p>注意: 此方法不会修改提供的物品堆栈数组, 如需修改请使用
     * {@link #craftItem(ItemStack[], World, Player)}.</p>
     * <p>原文：
     * Get the {@link Recipe} for the list of ItemStacks provided.
     *
     * <p>The list is formatted as a crafting matrix where the index follow
     * the pattern below:</p>
     *
     * <pre>
     * [ 0 1 2 ]
     * [ 3 4 5 ]
     * [ 6 7 8 ]
     * </pre>
     *
     * <p>NOTE: This method will not modify the provided ItemStack array, for that, use
     * {@link #craftItem(ItemStack[], World, Player)}.</p>
     *
     * @param craftingMatrix 用于合成的物品列表. 最多包含9个物品.
     * <p>原文：list of items to be crafted from.
     *                       Must not contain more than 9 items.
     * @param world 合成发生所在的世界
     * <p>原文：The world the crafting takes place in.
     * @return 由给定合成矩阵得出的{@link Recipe 配方}
     * <p>原文：the {@link Recipe} resulting from the given crafting matrix.
     */
    @Nullable
    public Recipe getCraftingRecipe(@NotNull ItemStack @NotNull [] craftingMatrix, @NotNull World world);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用提供的{@link ItemStack}列表获取合成后的物品.
     *
     * <p>该列表以合成矩阵的格式排列, 索引遵循以下模式:</p>
     *
     * <pre>
     * [ 0 1 2 ]
     * [ 3 4 5 ]
     * [ 6 7 8 ]
     * </pre>
     *
     * <p>{@link World}和{@link Player}参数是满足Bukkit合成事件所必需的.</p>
     *
     * <p>调用{@link org.bukkit.event.inventory.PrepareItemCraftEvent}以模拟{@link Player}发起合成事件.</p>
     * <p>原文：
     * Get the crafted item using the list of {@link ItemStack} provided.
     *
     * <p>The list is formatted as a crafting matrix where the index follow
     * the pattern below:</p>
     *
     * <pre>
     * [ 0 1 2 ]
     * [ 3 4 5 ]
     * [ 6 7 8 ]
     * </pre>
     *
     * <p>The {@link World} and {@link Player} arguments are required to fulfill the Bukkit Crafting
     * events.</p>
     *
     * <p>Calls {@link org.bukkit.event.inventory.PrepareItemCraftEvent} to imitate the {@link Player}
     * initiating the crafting event.</p>
     *
     * @param craftingMatrix 用于合成的物品列表. 最多包含9个物品.
     * <p>原文：list of items to be crafted from.
     *                       Must not contain more than 9 items.
     * @param world 合成发生所在的世界
     * <p>原文：The world the crafting takes place in.
     * @param player 用于模拟合成事件的玩家
     * <p>原文：The player to imitate the crafting event on.
     * @return 由给定合成矩阵得出的{@link ItemStack}, 如果未找到配方则返回{@link Material#AIR}的物品堆栈
     * <p>原文：the {@link ItemStack} resulting from the given crafting matrix, if no recipe is found
     * an ItemStack of {@link Material#AIR} is returned.
     */
    @NotNull
    default ItemStack craftItem(@NotNull ItemStack @NotNull [] craftingMatrix, @NotNull World world, @NotNull Player player) {
        return this.craftItemResult(craftingMatrix, world, player).getResult();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用提供的{@link ItemStack}列表获取合成后的物品.
     *
     * <p>该列表以合成矩阵的格式排列, 索引遵循以下模式:</p>
     *
     * <pre>
     * [ 0 1 2 ]
     * [ 3 4 5 ]
     * [ 6 7 8 ]
     * </pre>
     * <p>原文：
     * Get the crafted item using the list of {@link ItemStack} provided.
     *
     * <p>The list is formatted as a crafting matrix where the index follow
     * the pattern below:</p>
     *
     * <pre>
     * [ 0 1 2 ]
     * [ 3 4 5 ]
     * [ 6 7 8 ]
     * </pre>
     *
     * @param craftingMatrix 用于合成的物品列表. 最多包含9个物品.
     * <p>原文：list of items to be crafted from.
     *                       Must not contain more than 9 items.
     * @param world 合成发生所在的世界
     * <p>原文：The world the crafting takes place in.
     * @return 由给定合成矩阵得出的{@link ItemStack}, 如果未找到配方则返回{@link Material#AIR}的物品堆栈
     * <p>原文：the {@link ItemStack} resulting from the given crafting matrix, if no recipe is found
     * an ItemStack of {@link Material#AIR} is returned.
     */
    @NotNull
    default ItemStack craftItem(@NotNull ItemStack @NotNull [] craftingMatrix, @NotNull World world) {
        return this.craftItemResult(craftingMatrix, world).getResult();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用提供的{@link ItemStack}列表获取合成后的物品.
     *
     * <p>该列表以合成矩阵的格式排列, 索引遵循以下模式:</p>
     *
     * <pre>
     * [ 0 1 2 ]
     * [ 3 4 5 ]
     * [ 6 7 8 ]
     * </pre>
     *
     * <p>{@link World}和{@link Player}参数是满足Bukkit合成事件所必需的.</p>
     *
     * <p>调用{@link org.bukkit.event.inventory.PrepareItemCraftEvent}以模拟{@link Player}发起合成事件.</p>
     * <p>原文：
     * Get the crafted item using the list of {@link ItemStack} provided.
     *
     * <p>The list is formatted as a crafting matrix where the index follow
     * the pattern below:</p>
     *
     * <pre>
     * [ 0 1 2 ]
     * [ 3 4 5 ]
     * [ 6 7 8 ]
     * </pre>
     *
     * <p>The {@link World} and {@link Player} arguments are required to fulfill the Bukkit Crafting
     * events.</p>
     *
     * <p>Calls {@link org.bukkit.event.inventory.PrepareItemCraftEvent} to imitate the {@link Player}
     * initiating the crafting event.</p>
     *
     * @param craftingMatrix 用于合成的物品列表. 最多包含9个物品.
     * <p>原文：list of items to be crafted from.
     *                       Must not contain more than 9 items.
     * @param world 合成发生所在的世界
     * <p>原文：The world the crafting takes place in.
     * @param player 用于模拟合成事件的玩家
     * <p>原文：The player to imitate the crafting event on.
     * @return 包含合成结果物品、合成矩阵及剩余物品的{@link ItemCraftResult}
     * <p>原文：resulting {@link ItemCraftResult} containing the resulting item, matrix and any overflow items.
     */
    @NotNull
    public ItemCraftResult craftItemResult(@NotNull ItemStack @NotNull [] craftingMatrix, @NotNull World world, @NotNull Player player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用提供的{@link ItemStack}列表获取合成后的物品.
     *
     * <p>该列表以合成矩阵的格式排列, 索引遵循以下模式:</p>
     *
     * <pre>
     * [ 0 1 2 ]
     * [ 3 4 5 ]
     * [ 6 7 8 ]
     * </pre>
     * <p>原文：
     * Get the crafted item using the list of {@link ItemStack} provided.
     *
     * <p>The list is formatted as a crafting matrix where the index follow
     * the pattern below:</p>
     *
     * <pre>
     * [ 0 1 2 ]
     * [ 3 4 5 ]
     * [ 6 7 8 ]
     * </pre>
     *
     * @param craftingMatrix 用于合成的物品列表. 最多包含9个物品.
     * <p>原文：list of items to be crafted from.
     *                       Must not contain more than 9 items.
     * @param world 合成发生所在的世界
     * <p>原文：The world the crafting takes place in.
     * @return 包含合成结果物品、合成矩阵及剩余物品的{@link ItemCraftResult}
     * <p>原文：resulting {@link ItemCraftResult} containing the resulting item, matrix and any overflow items.
     */
    @NotNull
    public ItemCraftResult craftItemResult(@NotNull ItemStack @NotNull [] craftingMatrix, @NotNull World world);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取合成配方列表迭代器.
     * <p>原文：
     * Get an iterator through the list of crafting recipes.
     *
     * @return 迭代器
     * <p>原文：an iterator
     */
    @NotNull
    public Iterator<Recipe> recipeIterator();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 清理所有已添加的合成配方.
     * <p>原文：
     * Clears the list of crafting recipes.
     */
    public void clearRecipes();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 重置自定义合成配方列表至默认状态.
     * <p>原文：
     * Resets the list of crafting recipes to the default.
     */
    public void resetRecipes();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从服务器移除指定的合成配方.
     *
     * <b>注意:移除一个合成配方可能导致与此配方有关的数据的永久丢失
     * (例如配方是否已被玩家发现).</b>
     * <p>原文：
     * Remove a recipe from the server.
     *
     * <b>Note that removing a recipe may cause permanent loss of data
     * associated with that recipe (eg whether it has been discovered by
     * players).</b>
     *
     * @param key 要移除的配方的NamespacedKey
     * <p>原文：NamespacedKey of recipe to remove.
     * @return 若配方成功移除返回true
     * <p>原文：True if recipe was removed
     */
    default boolean removeRecipe(@NotNull NamespacedKey key) {
        return this.removeRecipe(key, false);
    }

    // Paper start - method to resend recipes
    /**
     * Remove a recipe from the server.
     * <p>
     * <b>Note that removing a recipe may cause permanent loss of data
     * associated with that recipe (eg whether it has been discovered by
     * players).</b>
     *
     * @param key NamespacedKey of recipe to remove.
     * @param resendRecipes true to update all clients on the new recipe list.
     *                      Will only update if a recipe was actually removed
     * @return True if recipe was removed
     */
    boolean removeRecipe(@NotNull NamespacedKey key, boolean resendRecipes);
    // Paper end - method to resend recipes

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器配置定义的命令别名列表.
     * <p>原文：
     * Gets a list of command aliases defined in the server properties.
     *
     * @return 命令别名map
     * <p>原文：a map of aliases to command names
     */
    @NotNull
    public Map<String, String[]> getCommandAliases();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取出生地保护的半径范围（以方块为单位）.
     * <p>原文：
     * Gets the radius, in blocks, around each worlds spawn point to protect.
     *
     * @return 出生地保护范围，0为没有保护
     * <p>原文：spawn radius, or 0 if none
     */
    public int getSpawnRadius();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置出生地保护的半径范围.
     * <p>原文：
     * Sets the radius, in blocks, around each worlds spawn point to protect.
     *
     * @param value 新的出生地保护的范围，0设为没有保护
     * <p>原文：new spawn radius, or 0 if none
     */
    public void setSpawnRadius(int value);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否应在玩家输入消息时向客户端发送玩家聊天消息的预览
     * <p>原文：
     * Gets whether the server should send a preview of the player's chat
     * message to the client when the player types a message
     *
     * @return 如果服务器应发送预览则返回true, 否则返回false
     * <p>原文：true if the server should send a preview, false otherwise
     * @deprecated 聊天预览功能已被移除
     * <p>原文：chat previews have been removed
     */
    @Deprecated(since = "1.19.3", forRemoval = true)
    default boolean shouldSendChatPreviews() {
        return false;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否只允许拥有Mojang签名公钥的玩家加入
     * <p>原文：
     * Gets whether the server only allow players with Mojang-signed public key
     * to join
     *
     * @return 如果只有Mojang签名的玩家可以加入则返回true, 否则返回false
     * <p>原文：true if only Mojang-signed players can join, false otherwise
     */
    public boolean isEnforcingSecureProfiles();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此服务器是否允许从其他服务器转移过来的连接.
     * <p>原文：
     * Gets whether this server is allowing connections transferred from other
     * servers.
     *
     * @return 如果服务器接受转移连接则返回true, 否则返回false
     * <p>原文：true if the server accepts transfers, false otherwise
     */
    public boolean isAcceptingTransfers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否在服务器状态中隐藏在线玩家.
     * <p>原文：
     * Gets whether the Server hide online players in server status.
     *
     * @return 如果服务器隐藏在线玩家则返回true, 否则返回false
     * <p>原文：true if the server hide online players, false otherwise
     */
    public boolean getHideOnlinePlayers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否开启了正版模式.
     * <p>原文：
     * Gets whether the Server is in online mode or not.
     *
     * @return true则开启/false反之
     * <p>原文：true if the server authenticates clients, false otherwise
     */
    public boolean getOnlineMode();

    /**
     * Retrieves the server configuration.
     *
     * @return the instance of ServerConfiguration containing the server's configuration details
     */
    @NotNull ServerConfiguration getServerConfig();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否允许飞行.
     * <p>原文：
     * Gets whether this server allows flying or not.
     *
     * @return 服务器是否允许飞行
     * <p>原文：true if the server allows flight, false otherwise
     */
    public boolean getAllowFlight();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否处于极限生存模式.
     * <p>原文：
     * Gets whether the server is in hardcore mode or not.
     *
     * @return 服务器是否处于极限生存模式
     * <p>原文：true if the server mode is hardcore, false otherwise
     */
    public boolean isHardcore();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 关闭服务器, 停止一切在运行的东西.
     * <p>原文：
     * Shutdowns the server, stopping everything.
     */
    public void shutdown();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向有给定权限的用户广播一条消息.
     * <p>原文：
     * Broadcasts the specified message to every user with the given
     * permission name.
     *
     * @param message 要广播的消息
     * <p>原文：message to broadcast
     * @param permission 接受这条公告需要拥有的{@link Permissible
     *     权限许可}
     * <p>原文：the required permission {@link Permissible
     *     permissibles} must have to receive the broadcast
     * @return 成功接收此消息的玩家数
     * <p>原文：number of message recipients
     * @deprecated in favour of {@link #broadcast(net.kyori.adventure.text.Component, String)}
     */
    @Deprecated // Paper
    default int broadcast(@NotNull String message, @NotNull String permission) {
        return this.broadcast(net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().deserialize(message), permission);
    }

    // Paper start
    /**
     * Broadcast a message to all players.
     * <p>
     * This is the same as calling {@link #broadcast(net.kyori.adventure.text.Component,
     * java.lang.String)} with the {@link #BROADCAST_CHANNEL_USERS} permission.
     *
     * @param message the message
     * @return the number of players
     */
    default int broadcast(net.kyori.adventure.text.@NotNull Component message) {
        return this.broadcast(message, BROADCAST_CHANNEL_USERS);
    }

    /**
     * Broadcasts the specified message to every user with the given
     * permission name.
     *
     * @param message message to broadcast
     * @param permission the required permission {@link Permissible
     *     permissibles} must have to receive the broadcast
     * @return number of message recipients
     */
    int broadcast(net.kyori.adventure.text.@NotNull Component message, @NotNull String permission);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以给定名字获取玩家对象, 无论玩家是否在线.
     * <p>
     * 这个方法可能因通过web请求获取玩家名对应UUID而阻塞.
     * <p>
     * 即使此玩家不存在也会返回一个对象. 对于此方法, 任何玩家都是存在的.
     * <p>原文：
     * Gets the player by the given name, regardless if they are offline or
     * online.
     * <p>
     * This method may involve a blocking web request to get the UUID for the
     * given name.
     * <p>
     * This will return an object even if the player does not exist. To this
     * method, all players will exist.
     *
     * @param name 此玩家的玩家名
     * <p>原文：the name the player to retrieve
     * @return 表示此玩家的OfflinePlayer对象
     * <p>原文：an offline player
     * @see #getOfflinePlayer(java.util.UUID)
     */
    // @Deprecated(since = "1.7.5") // Paper
    @NotNull
    public OfflinePlayer getOfflinePlayer(@NotNull String name);

    // Paper start
    /**
     * Gets the player by the given name, regardless if they are offline or
     * online.
     * <p>
     * This will not make a web request to get the UUID for the given name,
     * thus this method will not block. However this method will return
     * {@code null} if the player is not cached.
     * </p>
     *
     * @param name the name of the player to retrieve
     * @return an offline player if cached, {@code null} otherwise
     * @see #getOfflinePlayer(String)
     * @see #getOfflinePlayer(java.util.UUID)
     */
    @Nullable
    public OfflinePlayer getOfflinePlayerIfCached(@NotNull String name);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以给定名字获取玩家对象, 无论玩家是否在线.
     * <p>
     * 即使此玩家不存在也会返回一个对象. 对于此方法, 任何玩家都是存在的.
     * <p>原文：
     * Gets the player by the given UUID, regardless if they are offline or
     * online.
     * <p>
     * This will return an object even if the player does not exist. To this
     * method, all players will exist.
     *
     * @param id 要检索的玩家UUID
     * <p>原文：the UUID of the player to retrieve
     * @return 表示此玩家的OfflinePlayer对象
     * <p>原文：an offline player
     */
    @NotNull
    public OfflinePlayer getOfflinePlayer(@NotNull UUID id);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一份新的{@link PlayerProfile 玩家资料}.
     * <p>原文：
     * Creates a new {@link PlayerProfile}.
     *
     * @param uniqueId 唯一 id
     * <p>原文：the unique id
     * @param name 玩家名
     * <p>原文：the name
     * @return 新 PlayerProfile
     * <p>原文：the new PlayerProfile
     * @throws IllegalArgumentException 如果唯一 id 和玩家名均为 <code>null</code> 或玩家名为空白字符串
     * <p>原文：if both the unique id is
     * <code>null</code> and the name is <code>null</code> or blank
     * @deprecated use {@link #createProfile(UUID, String)}
     */
    @NotNull
    @Deprecated(since = "1.18.1") // Paper
    PlayerProfile createPlayerProfile(@Nullable UUID uniqueId, @Nullable String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一份新的{@link PlayerProfile 玩家资料}.
     * <p>原文：
     * Creates a new {@link PlayerProfile}.
     *
     * @param uniqueId 唯一 id
     * <p>原文：the unique id
     * @return 新 PlayerProfile
     * <p>原文：the new PlayerProfile
     * @throws IllegalArgumentException 如果唯一 id 为 <code>null</code>
     * <p>原文：if the unique id is <code>null</code>
     * @deprecated use {@link #createProfile(UUID)}
     */
    @NotNull
    @Deprecated(since = "1.18.1") // Paper
    PlayerProfile createPlayerProfile(@NotNull UUID uniqueId);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一份新的{@link PlayerProfile 玩家资料}.
     * <p>原文：
     * Creates a new {@link PlayerProfile}.
     *
     * @param name 玩家名
     * <p>原文：the name
     * @return 新 PlayerProfile
     * <p>原文：the new PlayerProfile
     * @throws IllegalArgumentException 如果玩家名为 <code>null</code> 或空白字符串
     * <p>原文：if the name is <code>null</code> or
     * blank
     * @deprecated use {@link #createProfile(String)}
     */
    @NotNull
    @Deprecated(since = "1.18.1") // Paper
    PlayerProfile createPlayerProfile(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有已被封禁的IP地址.
     * <p>原文：
     * Gets a set containing all current IPs that are banned.
     *
     * @return 被封禁IP集合
     * <p>原文：a set containing banned IP addresses
     */
    @NotNull
    public Set<String> getIPBans();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 封禁指定的IP地址.
     * <p>原文：
     * Bans the specified address from the server.
     *
     * @param address 要封禁的IP地址
     * <p>原文：the IP address to ban
     * @deprecated 参见 {@link #banIP(InetAddress)}
     * <p>原文：see {@link #banIP(InetAddress)}
     */
    @Deprecated(since = "1.20.1")
    public void banIP(@NotNull String address);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 解禁指定的IP地址.
     * <p>原文：
     * Unbans the specified address from the server.
     *
     * @param address 要解禁的IP地址
     * <p>原文：the IP address to unban
     * @deprecated 参见 {@link #unbanIP(InetAddress)}
     * <p>原文：see {@link #unbanIP(InetAddress)}
     */
    @Deprecated(since = "1.20.1")
    public void unbanIP(@NotNull String address);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 封禁指定的IP地址.
     * <p>原文：
     * Bans the specified address from the server.
     *
     * @param address 要封禁的IP地址
     * <p>原文：the IP address to ban
     */
    public void banIP(@NotNull InetAddress address);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 解禁指定的IP地址.
     * <p>原文：
     * Unbans the specified address from the server.
     *
     * @param address 要解禁的IP地址
     * <p>原文：the IP address to unban
     */
    public void unbanIP(@NotNull InetAddress address);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有已被封禁的玩家.
     * <p>原文：
     * Gets a set containing all banned players.
     *
     * @return 已被封禁的玩家
     * <p>原文：a set containing banned players
     */
    @NotNull
    public Set<OfflinePlayer> getBannedPlayers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定类型的封禁列表.
     * <p>原文：
     * Gets a ban list for the supplied type.
     *
     * @param type 要获取的封禁列表的类型, 不能为null
     * <p>原文：the type of list to fetch, cannot be null
     * @param <T> 封禁目标
     * <p>原文：The ban target
     * @return 指定类型的封禁列表
     * <p>原文：a ban list of the specified type
     * @deprecated use {@link #getBanList(io.papermc.paper.ban.BanListType)} to enforce the correct return value at compile time.
     */
    @Deprecated // Paper - add BanListType (which has a generic)
    @NotNull
    public <T extends BanList<?>> T getBanList(@NotNull BanList.Type type);

    // Paper start - add BanListType (which has a generic)
    /**
     * Gets a ban list for the supplied type.
     *
     * @param type the type of list to fetch, cannot be null
     * @param <B> The ban target
     *
     * @return a ban list of the specified type
     */
    @NotNull
    <B extends BanList<E>, E> B getBanList(@NotNull io.papermc.paper.ban.BanListType<B> type);
    // Paper end - add BanListType (which has a generic)

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器的所有OP(管理员).
     * <p>原文：
     * Gets a set containing all player operators.
     *
     * @return 服务器OP
     * <p>原文：a set containing player operators
     */
    @NotNull
    public Set<OfflinePlayer> getOperators();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取新玩家的默认{@link GameMode 游戏模式}.
     * <p>原文：
     * Gets the default {@link GameMode} for new players.
     *
     * @return 默认游戏模式
     * <p>原文：the default game mode
     */
    @NotNull
    GameMode getDefaultGameMode();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置新玩家的默认 {@link GameMode}.
     * <p>原文：
     * Sets the default {@link GameMode} for new players.
     *
     * @param mode 新的游戏模式
     * <p>原文：the new game mode
     */
    void setDefaultGameMode(@NotNull GameMode mode);

    /**
     * Gets whether the default gamemode is being enforced.
     *
     * @return {@code true} if the default gamemode is being forced, {@code false} otherwise
     */
    boolean forcesDefaultGameMode();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器的{@link ConsoleCommandSender},
     * 将被作为服务器的标准输入.
     * <p>
     * 译注:该方法用于获取控制台.
     * <p>原文：
     * Gets a {@link ConsoleCommandSender} that may be used as an input source
     * for this server.
     *
     * @return 控制台命令发送者对象
     * <p>原文：a console command sender
     */
    @NotNull
    public ConsoleCommandSender getConsoleSender();

    // Paper start
    /**
     * Creates a special {@link CommandSender} which redirects command feedback (in the form of chat messages) to the
     * specified listener. The returned sender will have the same effective permissions as {@link #getConsoleSender()}.
     *
     * @param feedback feedback listener
     * @return a command sender
     */
    @NotNull
    public CommandSender createCommandSender(final @NotNull java.util.function.Consumer<? super net.kyori.adventure.text.Component> feedback);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取包含所有{@link World 世界}数据的文件夹.
     * <p>
     * 译注:通常此文件夹就是服务端根目录.
     * <p>原文：
     * Gets the folder that contains all of the various {@link World}s.
     *
     * @return 包含所有世界数据的文件夹
     * <p>原文：folder that contains all worlds
     */
    @NotNull
    public File getWorldContainer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有登陆过服务器的玩家.
     * <p>原文：
     * Gets every player that has ever played on this server.
     * <p>
     * <b>This method can be expensive as it loads all the player data files from the disk.</b>
     *
     * @return 包含所有登录过的玩家的数组
     * <p>原文：an array containing all previous players
     */
    public @NotNull OfflinePlayer @NotNull [] getOfflinePlayers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器上管理plugin channel(插件通道)通信的{@link Messenger}实例.
     * <p>原文：
     * Gets the {@link Messenger} responsible for this server.
     *
     * @return Messenger实例
     * <p>原文：messenger responsible for this server
     */
    @NotNull
    public Messenger getMessenger();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器上提供所有帮助主题的{@link HelpMap}实例.
     * <p>原文：
     * Gets the {@link HelpMap} providing help topics for this server.
     *
     * @return HelpMap实例
     * <p>原文：a help map for this server
     */
    @NotNull
    public HelpMap getHelpMap();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以指定类型创建一个空物品栏. 如果type是{@link InventoryType#CHEST}(箱子类型),
     * 新的物品栏的大小则是27, 否则是与物品栏类型对应的正常大小.
     * <br>
     * 如果type是{@link InventoryType#WORKBENCH}, 则这个物品栏不会处理合成配方.
     * 请使用{@link MenuType#CRAFTING}.
     * <br>
     * 同理, 以{@link InventoryType#ENCHANTING}创建的附魔台物品栏也不起作用.
     * 请使用{@link MenuType#ENCHANTMENT}.
     * <p>原文：
     * Creates an empty inventory with the specified type. If the type
     * is {@link InventoryType#CHEST}, the new inventory has a size of 27;
     * otherwise the new inventory has the normal size for its type.
     * <br>
     * {@link InventoryType#WORKBENCH} will not process crafting recipes if
     * created with this method. Use
     * {@link MenuType#CRAFTING} instead.
     * <br>
     * {@link InventoryType#ENCHANTING} will not process {@link ItemStack}s
     * for possible enchanting results. Use
     * {@link MenuType#ENCHANTMENT} instead.
     *
     * @param owner 物品栏的持有者, 不指定持有者为null
     * <p>原文：the holder of the inventory, or null to indicate no holder
     * @param type 要创建的物品栏的种类
     * <p>原文：the type of inventory to create
     * @return 新的物品栏
     * <p>原文：a new inventory
     * @throws IllegalArgumentException 如果这种 {@link InventoryType} 物品栏不能显示给玩家
     * <p>原文：if the {@link InventoryType} cannot be
     * viewed.
     * @see InventoryType#isCreatable()
     */
    @NotNull
    Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type);

    // Paper start
    /**
     * Creates an empty inventory with the specified type and title. If the type
     * is {@link InventoryType#CHEST}, the new inventory has a size of 27;
     * otherwise the new inventory has the normal size for its type.<br>
     * It should be noted that some inventory types do not support titles and
     * may not render with said titles on the Minecraft client.
     * <br>
     * {@link InventoryType#WORKBENCH} will not process crafting recipes if
     * created with this method. Use
     * {@link MenuType#CRAFTING} instead.
     * <br>
     * {@link InventoryType#ENCHANTING} will not process {@link ItemStack}s
     * for possible enchanting results. Use
     * {@link MenuType#ENCHANTMENT} instead.
     *
     * @param owner The holder of the inventory; can be null if there's no holder.
     * @param type The type of inventory to create.
     * @param title The title of the inventory, to be displayed when it is viewed.
     * @return The new inventory.
     * @throws IllegalArgumentException if the {@link InventoryType} cannot be
     * viewed.
     *
     * @see InventoryType#isCreatable()
     */
    @NotNull
    Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type, net.kyori.adventure.text.@NotNull Component title);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以指定类型和标题创建一个空物品栏. 如果type是{@link InventoryType#CHEST}(箱子类型),
     * 新的物品栏的大小则是27, 否则是与物品栏类型对应的正常大小.
     * <br>
     * 如果type是{@link InventoryType#WORKBENCH}, 则这个物品栏不会处理合成配方.
     * 请使用{@link MenuType#CRAFTING}.
     * <br>
     * 同理, 以{@link InventoryType#ENCHANTING}创建的附魔台物品栏也不起作用.
     * 请使用{@link MenuType#ENCHANTMENT}.
     * <p>原文：
     * Creates an empty inventory with the specified type and title. If the type
     * is {@link InventoryType#CHEST}, the new inventory has a size of 27;
     * otherwise the new inventory has the normal size for its type.<br>
     * It should be noted that some inventory types do not support titles and
     * may not render with said titles on the Minecraft client.
     * <br>
     * {@link InventoryType#WORKBENCH} will not process crafting recipes if
     * created with this method. Use
     * {@link MenuType#CRAFTING} instead.
     * <br>
     * {@link InventoryType#ENCHANTING} will not process {@link ItemStack}s
     * for possible enchanting results. Use
     * {@link MenuType#ENCHANTMENT} instead.
     *
     * @param owner 物品栏的持有者, 如果没有持有者可为null
     * <p>原文：The holder of the inventory; can be null if there's no holder.
     * @param type 要创建的物品栏的种类
     * <p>原文：The type of inventory to create.
     * @param title 物品栏的标题, 会显示给玩家
     * <p>原文：The title of the inventory, to be displayed when it is viewed.
     * @return 新的物品栏
     * <p>原文：The new inventory.
     * @throws IllegalArgumentException 如果这种 {@link InventoryType} 物品栏不能显示给玩家
     * <p>原文：if the {@link InventoryType} cannot be
     * viewed.
     * @deprecated in favour of {@link #createInventory(InventoryHolder, InventoryType, net.kyori.adventure.text.Component)}
     * @see InventoryType#isCreatable()
     */
    @Deprecated // Paper
    @NotNull
    Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type, @NotNull String title);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个类型为{@link InventoryType#CHEST}, 有指定大小的空物品栏.
     * <p>原文：
     * Creates an empty inventory of type {@link InventoryType#CHEST} with the
     * specified size.
     *
     * @param owner 物品栏的持有者, 不指定持有者为null
     * <p>原文：the holder of the inventory, or null to indicate no holder
     * @param size 物品栏的大小, 必须为9的倍数
     * <p>原文：a multiple of 9 as the size of inventory to create
     * @return 新的物品栏
     * <p>原文：a new inventory
     * @throws IllegalArgumentException 如果大小不是9的倍数
     * <p>原文：if the size is not a multiple of 9
     */
    @NotNull
    Inventory createInventory(@Nullable InventoryHolder owner, int size) throws IllegalArgumentException;

    // Paper start
    /**
     * Creates an empty inventory of type {@link InventoryType#CHEST} with the
     * specified size and title.
     *
     * @param owner the holder of the inventory, or null to indicate no holder
     * @param size a multiple of 9 as the size of inventory to create
     * @param title the title of the inventory, displayed when inventory is
     *     viewed
     * @return a new inventory
     * @throws IllegalArgumentException if the size is not a multiple of 9
     */
    @NotNull
    Inventory createInventory(@Nullable InventoryHolder owner, int size, net.kyori.adventure.text.@NotNull Component title) throws IllegalArgumentException;
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个类型为{@link InventoryType#CHEST}, 有指定大小和标题的空物品栏.
     * <p>原文：
     * Creates an empty inventory of type {@link InventoryType#CHEST} with the
     * specified size and title.
     *
     * @param owner 物品栏的持有者, 不指定持有者为null
     * <p>原文：the holder of the inventory, or null to indicate no holder
     * @param size 物品栏的大小, 必须为9的倍数
     * <p>原文：a multiple of 9 as the size of inventory to create
     * @param title 物品栏的标题, 会显示给玩家
     * <p>原文：the title of the inventory, displayed when inventory is
     *     viewed
     * @return 新的物品栏
     * <p>原文：a new inventory
     * @throws IllegalArgumentException 如果大小不是9的倍数
     * <p>原文：if the size is not a multiple of 9
     * @deprecated in favour of {@link #createInventory(InventoryHolder, int, net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    @NotNull
    Inventory createInventory(@Nullable InventoryHolder owner, int size, @NotNull String title) throws IllegalArgumentException;

    // Paper start
    /**
     * Creates an empty merchant.
     *
     * @param title the title of the corresponding merchant inventory, displayed
     * when the merchant inventory is viewed
     * @return a new merchant
     * @deprecated The title parameter is no-longer needed when used with
     * {@link MenuType#MERCHANT} and {@link MenuType.Typed#builder()}.
     */
    @Deprecated(since = "1.21.4")
    @NotNull Merchant createMerchant(net.kyori.adventure.text.@Nullable Component title);
    // Paper start
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个空的商人.
     * <p>原文：
     * Creates an empty merchant.
     *
     * @param title 查看商人物品栏时显示的标题
     * <p>原文：the title of the corresponding merchant inventory, displayed
     * when the merchant inventory is viewed
     * @return 新的商人
     * <p>原文：a new merchant
     * @deprecated in favour of {@link #createMerchant(net.kyori.adventure.text.Component)}, The title parameter is
     * no-longer needed when used with {@link MenuType#MERCHANT} and {@link MenuType.Typed#builder()}.
     */
    @NotNull
    @Deprecated // Paper
    Merchant createMerchant(@Nullable String title);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在跳过额外更新之前连续相邻更新的数量.
     * <p>原文：
     * Gets the amount of consecutive neighbor updates before skipping
     * additional ones.
     *
     * @return 连续相邻更新的数量, 如果为负数则表示不使用该限制
     * <p>原文：the amount of consecutive neighbor updates, if the value is
     * negative then the limit it's not used
     */
    int getMaxChainedNeighborUpdates();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个空的商人.
     * <p>原文：
     * Creates an empty merchant.
     *
     * @return 新的商人
     * <p>原文：a new merchant
     */
    @NotNull
    Merchant createMerchant();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个区块最大可生成怪物数量.
     * <p>原文：
     * Gets user-specified limit for number of monsters that can spawn in a
     * chunk.
     *
     * @return 生成限制数
     * <p>原文：the monster spawn limit
     * @deprecated 建议使用 {@link #getSpawnLimit(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getSpawnLimit(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getMonsterSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.MONSTER);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个区块最大可生成的动物数量.
     * <p>原文：
     * Gets user-specified limit for number of animals that can spawn in a
     * chunk.
     *
     * @return 生成限制数
     * <p>原文：the animal spawn limit
     * @deprecated 建议使用 {@link #getSpawnLimit(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getSpawnLimit(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getAnimalSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.ANIMAL);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个区块最大可生成的水生动物数量.
     * <p>原文：
     * Gets user-specified limit for number of water animals that can spawn in
     * a chunk.
     *
     * @return 生成限制数
     * <p>原文：the water animal spawn limit
     * @deprecated 建议使用 {@link #getSpawnLimit(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getSpawnLimit(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getWaterAnimalSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.WATER_ANIMAL);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个区块最大可生成的水生环境数量.
     * <p>原文：
     * Gets user-specified limit for number of water ambient mobs that can spawn
     * in a chunk.
     *
     * @return 生成限制数
     * <p>原文：the water ambient spawn limit
     * @deprecated 建议使用 {@link #getSpawnLimit(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getSpawnLimit(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getWaterAmbientSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.WATER_AMBIENT);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用户指定的区块内可生成的水下穴居生物数量限制.
     * <p>原文：
     * Get user-specified limit for number of water creature underground that can spawn
     * in a chunk.
     *
     * @return 水下穴居生物的生成限制数
     * <p>原文：the water underground creature limit
     * @deprecated 建议使用 {@link #getSpawnLimit(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getSpawnLimit(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getWaterUndergroundCreatureSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.WATER_UNDERGROUND_CREATURE);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个区块最大可生成的环境生物(一般指蝙蝠)数量.
     * <p>原文：
     * Gets user-specified limit for number of ambient mobs that can spawn in
     * a chunk.
     *
     * @return 生成限制数
     * <p>原文：the ambient spawn limit
     * @deprecated 建议使用 {@link #getSpawnLimit(SpawnCategory)}
     * <p>原文：Deprecated in favor of {@link #getSpawnLimit(SpawnCategory)}
     */
    @Deprecated(since = "1.18.1")
    default int getAmbientSpawnLimit() {
        return this.getSpawnLimit(SpawnCategory.AMBIENT);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用户指定的区块内可生成的{@link SpawnCategory}类型生物数量限制.
     *
     * <b>注意: {@link SpawnCategory#MISC}类型不会被考虑在内.</b>
     * <p>原文：
     * Gets user-specified limit for number of {@link SpawnCategory} mobs that can spawn in
     * a chunk.
     *
     * <b>Note: the {@link SpawnCategory#MISC} are not consider.</b>
     *
     * @param spawnCategory 生成类别
     * <p>原文：the category spawn
     * @return 指定{@link SpawnCategory}类型的生成限制数
     * <p>原文：the {@link SpawnCategory} spawn limit
     */
    int getSpawnLimit(@NotNull SpawnCategory spawnCategory);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查当前方法是否在主线程执行。
     * <p>
     * <b>注意:</b> 该方法不应该用于检查当前同步状态,当前线程为主线程表明它确实为同步,但是不能排除其他原因.
     * <p>原文：
     * Checks the current thread against the expected primary thread for the
     * server.
     * <p>
     * <b>Note:</b> this method should not be used to indicate the current
     * synchronized state of the runtime. A current thread matching the main
     * thread indicates that it is synchronized, but a mismatch <b>does not
     * preclude</b> the same assumption.
     *
     * @return 为主线程返回true否则返回false
     * <p>原文：true if the current thread matches the expected primary thread,
     *     false otherwise
     */
    boolean isPrimaryThread();

    // Paper start
    /**
     * Gets the message that is displayed on the server list.
     *
     * @return the server's MOTD
     */
    net.kyori.adventure.text.@NotNull Component motd();

    /**
     * Set the message that is displayed on the server list.
     *
     * @param motd The message to be displayed
     */
    void motd(final net.kyori.adventure.text.@NotNull Component motd);

    /**
     * Gets the default message that is displayed when the server is stopped.
     *
     * @return the shutdown message
     */
    net.kyori.adventure.text.@Nullable Component shutdownMessage();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在客户端服务器列表里显示的消息(服务器的欢迎消息，又称message of the day, 展示在mc客户端的服务器列表).
     * <p>原文：
     * Gets the message that is displayed on the server list.
     *
     * @return 服务器motd
     * <p>原文：the servers MOTD
     * @deprecated in favour of {@link #motd()}
     */
    @NotNull
    @Deprecated // Paper
    String getMotd();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置在客户端服务器列表里显示的消息.
     * <p>原文：
     * Set the message that is displayed on the server list.
     *
     * @param motd 服务器motd
     * <p>原文：The message to be displayed
     * @deprecated in favour of {@link #motd(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    void setMotd(@NotNull String motd);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将发送给客户端的服务器链接
     * <p>原文：
     * Gets the server links which will be sent to clients
     *
     * @return 服务器的链接
     * <p>原文：the server's links
     */
    @NotNull
    @ApiStatus.Experimental
    ServerLinks getServerLinks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器关闭时广播给玩家的默认提示消息.
     * <p>原文：
     * Gets the default message that is displayed when the server is stopped.
     *
     * @return 关服消息
     * <p>原文：the shutdown message
     * @deprecated in favour of {@link #shutdownMessage()}
     */
    @Nullable
    @Deprecated // Paper
    String getShutdownMessage();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器的警告状态.
     * <p>原文：
     * Gets the current warning state for the server.
     *
     * @return 预先配置的警告状态
     * <p>原文：the configured warning state
     */
    @NotNull
    public WarningState getWarningState();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取ItemFactory实例 (用于{@link ItemMeta}).
     * <p>原文：
     * Gets the instance of the item factory (for {@link ItemMeta}).
     *
     * @return ItemFactory实例
     * <p>原文：the item factory
     * @see ItemFactory
     */
    @NotNull
    ItemFactory getItemFactory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体工厂实例 (用于{@link EntitySnapshot}).
     * <p>原文：
     * Gets the instance of the entity factory (for {@link EntitySnapshot}).
     *
     * @return 实体工厂实例
     * <p>原文：the entity factory
     * @see EntityFactory
     */
    @NotNull
    EntityFactory getEntityFactory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取计分板管理器实例.
     * <p>
     * 只在第一个世界加载后存在.
     * <p>原文：
     * Gets the instance of the scoreboard manager.
     * <p>
     * This will only exist after the first world has loaded.
     *
     * @return 计分板管理器实例, 如果未加载任何世界则为null
     * <p>原文：the scoreboard manager or null if no worlds are loaded.
     */
    @NotNull // Paper
    ScoreboardManager getScoreboardManager();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据名称获取(或创建)一个新的{@link Criteria}.
     * <p>原文：
     * Get (or create) a new {@link Criteria} by its name.
     *
     * @param name 条件名称
     * <p>原文：the criteria name
     * @return 条件实例
     * <p>原文：the criteria
     * @see Criteria Criteria for a list of constants
     */
    @NotNull
    Criteria getScoreboardCriteria(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器默认图标.
     * <p>原文：
     * Gets an instance of the server's default server-icon.
     *
     * @return 服务器默认图标,当未定义服务器图标时将返回null(该行为无法担保)
     * <p>原文：the default server-icon; null values may be used by the
     *     implementation to indicate no defined icon, but this behavior is
     *     not guaranteed
     */
    @Nullable
    CachedServerIcon getServerIcon();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为指定文件创建一个缓存的服务器图标.
     * <p>
     * 大小和类型必须在允许范围内(由底层实现定义),否则将会抛出{@link Exception}.
     * <p>原文：
     * Loads an image from a file, and returns a cached image for the specific
     * server-icon.
     * <p>
     * Size and type are implementation defined. An incompatible file is
     * guaranteed to throw an implementation-defined {@link Exception}.
     *
     * @param file 需要被加载的文件
     * <p>原文：the file to load the from
     * @return 一个CachedServerIcon实例,可用于 {@link
     *     ServerListPingEvent#setServerIcon(CachedServerIcon)}
     * <p>原文：a cached server-icon that can be used for a {@link
     *     ServerListPingEvent#setServerIcon(CachedServerIcon)}
     * @throws IllegalArgumentException 如果图片为null
     * <p>原文：if image is null
     * @throws Exception 如果图片规格不适合作为服务器图标
     * <p>原文：if the image does not meet current server server-icon
     *     specifications
     */
    @NotNull
    CachedServerIcon loadServerIcon(@NotNull File file) throws IllegalArgumentException, Exception;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为指定图片创建一个缓存的服务器图标.
     * <p>
     * 大小和类型必须在允许范围内(由底层实现定义),否则将会抛出{@link Exception}.
     * <p>原文：
     * Creates a cached server-icon for the specific image.
     * <p>
     * Size and type are implementation defined. An incompatible file is
     * guaranteed to throw an implementation-defined {@link Exception}.
     *
     * @param image 用于缓存的图片
     * <p>原文：the image to use
     * @return 一个CachedServerIcon实例,可用于 {@link
     *     ServerListPingEvent#setServerIcon(CachedServerIcon)}
     * <p>原文：a cached server-icon that can be used for a {@link
     *     ServerListPingEvent#setServerIcon(CachedServerIcon)}
     * @throws IllegalArgumentException 若image为null
     * <p>原文：if image is null
     * @throws Exception 如果图片规格不适合作为服务器图标
     * <p>原文：if the image does not meet current server
     *     server-icon specifications
     */
    @NotNull
    CachedServerIcon loadServerIcon(@NotNull BufferedImage image) throws IllegalArgumentException, Exception;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置一个空闲超时阈值(IDLE_KICK). 玩家空闲达到这个特定的时间后会被自动踢出服务器.
     * 如果设置为0，该功能将被关闭.
     * <p>原文：
     * Set the idle kick timeout. Any players idle for the specified amount of
     * time will be automatically kicked.
     * <p>
     * A value of 0 will disable the idle kick timeout.
     *
     * @param threshold 玩家空闲时间阈值，以分钟为单位
     * <p>原文：the idle timeout in minutes
     */
    public void setIdleTimeout(int threshold);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取自动踢出闲置玩家的时间.
     * <p>原文：
     * Gets the idle kick timeout.
     *
     * @return the 闲置超时的分钟数
     * <p>原文：the idle timeout in minutes
     */
    public int getIdleTimeout();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取空闲暂停阈值(秒). 为了节省资源, 当没有玩家在线时, 服务器将在该时间后暂停大部分功能.
     * <p>原文：
     * Gets the pause when empty threshold seconds. To save resources, the
     * pause most functions after this time if there are no players online.
     *
     * @return 暂停阈值(秒)
     * <p>原文：the pause threshold in seconds
     */
    public int getPauseWhenEmptyTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置空闲暂停阈值(秒). 为了节省资源, 当没有玩家在线时, 服务器将在该时间后暂停大部分功能.
     * <p>
     * 小于0的值将禁用该设置
     * <p>原文：
     * Sets the pause when empty threshold seconds. To save resources, the
     * pause most functions after this time if there are no players online.
     * <p>
     * A value of less than 1 will disable the setting
     *
     * @param seconds 暂停阈值(秒)
     * <p>原文：the pause threshold in seconds
     */
    public void setPauseWhenEmptyTime(int seconds);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个 ChunkData 实例，以用于区块生成。
     * <p>原文：
     * Create a ChunkData for use in a generator.
     *
     * See {@link ChunkGenerator#generateChunkData(org.bukkit.World, java.util.Random, int, int, org.bukkit.generator.ChunkGenerator.BiomeGrid)}
     *
     * @param world ChunkData对应的世界
     * <p>原文：the world to create the ChunkData for
     * @return 这个世界的新ChunkData实例
     * <p>原文：a new ChunkData for the world
     */
    @NotNull
    public ChunkGenerator.ChunkData createChunkData(@NotNull World world);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个Boss血量条实例. 血量条的进度默认为1.0.
     * <p>原文：
     * Creates a boss bar instance to display to players. The progress
     * defaults to 1.0
     *
     * @param title 血量条的标题
     * <p>原文：the title of the boss bar
     * @param color 血量条的颜色
     * <p>原文：the color of the boss bar
     * @param style 血量条的样式
     * <p>原文：the style of the boss bar
     * @param flags 血量条的附加属性列表（可选）
     * <p>原文：an optional list of flags to set on the boss bar
     * @return 创建的Boss血量条实例
     * <p>原文：the created boss bar
     */
    @NotNull
    BossBar createBossBar(@Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, @NotNull BarFlag... flags);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个Boss血量条实例. 血量条的进度默认为1.0.
     * <br>
     * 该方法创建的实例会被添加到服务器的持久化存储空间中, 命令将可以编辑它们, 服务器重启后会被恢复.
     * <p>原文：
     * Creates a boss bar instance to display to players. The progress defaults
     * to 1.0.
     * <br>
     * This instance is added to the persistent storage of the server and will
     * be editable by commands and restored after restart.
     *
     * @param key boss血量条的key, 将用于获取血量条
     * <p>原文：the key of the boss bar that is used to access the boss bar
     * @param title 血量条的标题
     * <p>原文：the title of the boss bar
     * @param color 血量条的颜色
     * <p>原文：the color of the boss bar
     * @param style 血量条的样式
     * <p>原文：the style of the boss bar
     * @param flags 血量条的附加属性列表（可选）
     * <p>原文：an optional list of flags to set on the boss bar
     * @return 创建的Boss血量条实例
     * <p>原文：the created boss bar
     */
    @NotNull
    KeyedBossBar createBossBar(@NotNull NamespacedKey key, @Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, @NotNull BarFlag... flags);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个不可编辑的用于迭代所有持久存储的boss血量条的迭代器.
     * <ul>
     *   <li>这个血量条<b>不</b>绑定到某一具体的{@link org.bukkit.entity.Boss}</li>
     *   <li>这个血量条<b>不是</b>由{@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}创建的
     *   </li>
     * </ul>
     * 例如:使用bossbar命令创建的boss血量条 (它是持久存储的, 可由此迭代器访问).
     * <p>原文：
     * Gets an unmodifiable iterator through all persistent bossbars.
     * <ul>
     *   <li><b>not</b> bound to a {@link org.bukkit.entity.Boss}</li>
     *   <li>
     *     <b>not</b> created using
     *     {@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}
     *   </li>
     * </ul>
     *
     * e.g. bossbars created using the bossbar command
     *
     * @return bossbar迭代器
     * <p>原文：a bossbar iterator
     */
    @NotNull
    Iterator<KeyedBossBar> getBossBars();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取由指定key标识的{@link KeyedBossBar}.
     * <ul>
     *   <li><b>不</b>绑定到某一具体的{@link org.bukkit.entity.Boss}</li>
     *   <li>
     *     <b>不是</b>由{@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}创建的
     *   </li>
     * </ul>
     *
     * 例如:使用bossbar命令创建的boss血量条
     * <p>原文：
     * Gets the {@link KeyedBossBar} specified by this key.
     * <ul>
     *   <li><b>not</b> bound to a {@link org.bukkit.entity.Boss}</li>
     *   <li>
     *     <b>not</b> created using
     *     {@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}
     *   </li>
     * </ul>
     *
     * e.g. bossbars created using the bossbar command
     *
     * @param key 唯一的boss血量条key
     * <p>原文：unique bossbar key
     * @return boss血量条实例, 如果不存在则返回null
     * <p>原文：bossbar or null if not exists
     */
    @Nullable
    KeyedBossBar getBossBar(@NotNull NamespacedKey key);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除由指定key标识的{@link KeyedBossBar}.
     * <ul>
     *   <li><b>不</b>绑定到某一具体的{@link org.bukkit.entity.Boss}</li>
     *   <li>
     *     <b>不是</b>由{@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}创建的
     *   </li>
     * </ul>
     *
     * 例如:使用bossbar命令创建的boss血量条
     * <p>原文：
     * Removes a {@link KeyedBossBar} specified by this key.
     * <ul>
     *   <li><b>not</b> bound to a {@link org.bukkit.entity.Boss}</li>
     *   <li>
     *     <b>not</b> created using
     *     {@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}
     *   </li>
     * </ul>
     *
     * e.g. bossbars created using the bossbar command
     *
     * @param key 唯一的boss血量条key
     * <p>原文：unique bossbar key
     * @return 如果移除成功则返回true, 否则返回false
     * <p>原文：true if removal succeeded or false
     */
    boolean removeBossBar(@NotNull NamespacedKey key);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用UUID获取实体.
     * <p>原文：
     * Gets an entity on the server by its UUID
     *
     * @param uuid 实体的UUID
     * <p>原文：the UUID of the entity
     * @return 该UUID代表的实体，如果不存在为null
     * <p>原文：the entity with the given UUID, or null if it isn't found
     */
    @Nullable
    Entity getEntity(@NotNull UUID uuid);

    // Paper start
    /**
     * Gets the current server TPS
     *
     * @return current server TPS (1m, 5m, 15m in Paper-Server)
     */
    public double @NotNull [] getTPS();

    /**
     * Get a sample of the servers last tick times (in nanos)
     *
     * @return A sample of the servers last tick times (in nanos)
     */
    long @NotNull [] getTickTimes();

    /**
     * Get the average tick time (in millis)
     *
     * @return Average tick time (in millis)
     */
    double getAverageTickTime();
    // Paper end

    // Paper start
    /**
     * Gets the active {@link org.bukkit.command.CommandMap}
     *
     * @return the active command map
     */
    @NotNull
    org.bukkit.command.CommandMap getCommandMap();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 通过Key获得特定的进度对象.
     * <p>原文：
     * Get the advancement specified by this key.
     *
     * @param key 寻找进度对象所需的key
     * <p>原文：unique advancement key
     * @return 一个进度对象. 如果它不存在，将返回null.
     * <p>原文：advancement or null if not exists
     */
    @Nullable
    Advancement getAdvancement(@NotNull NamespacedKey key);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个用以遍历所有进度的迭代器对象。
     * 进度不能够从该迭代器上删除。
     * <p>原文：
     * Get an iterator through all advancements. Advancements cannot be removed
     * from this iterator,
     *
     * @return 一个进度迭代器对象
     * <p>原文：an advancement iterator
     */
    @NotNull
    Iterator<Advancement> advancementIterator();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为指定的材质创建一个新的{@link BlockData}实例, 所有属性初始化为未指定的默认值.
     * <p>原文：
     * Creates a new {@link BlockData} instance for the specified Material, with
     * all properties initialized to unspecified defaults.
     *
     * @param material 材质
     * <p>原文：the material
     * @return 新的方块数据实例
     * <p>原文：new data instance
     */
    @NotNull
    BlockData createBlockData(@NotNull Material material);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为指定的材质创建一个新的{@link BlockData}实例, 所有属性初始化为未指定的默认值.
     * <p>原文：
     * Creates a new {@link BlockData} instance for the specified Material, with
     * all properties initialized to unspecified defaults.
     *
     * @param material 材质
     * <p>原文：the material
     * @param consumer 返回前在新实例上运行的消费者函数
     * <p>原文：consumer to run on new instance before returning
     * @return 新的方块数据实例
     * <p>原文：new data instance
     */
    @NotNull
    public BlockData createBlockData(@NotNull Material material, @Nullable Consumer<? super BlockData> consumer);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从提供的数据字符串解析材质和属性, 创建一个新的{@link BlockData}实例.
     * <p>原文：
     * Creates a new {@link BlockData} instance with material and properties
     * parsed from provided data.
     *
     * @param data 数据字符串
     * <p>原文：data string
     * @return 新的方块数据实例
     * <p>原文：new data instance
     * @throws IllegalArgumentException 如果指定的数据无效
     * <p>原文：if the specified data is not valid
     */
    @NotNull
    BlockData createBlockData(@NotNull String data) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为指定的材质创建一个新的{@link BlockData}实例, 所有属性初始化为未指定的默认值,
     * 但数据字符串中提供的属性除外.
     * <br>
     * 如果指定了<code>material</code>, 则数据字符串中不能同时包含材质.
     * <p>原文：
     * Creates a new {@link BlockData} instance for the specified Material, with
     * all properties initialized to unspecified defaults, except for those
     * provided in data.
     * <br>
     * If <code>material</code> is specified, then the data string must not also
     * contain the material.
     *
     * @param material 材质
     * <p>原文：the material
     * @param data 数据字符串
     * <p>原文：data string
     * @return 新的方块数据实例
     * <p>原文：new data instance
     * @throws IllegalArgumentException 如果指定的数据无效
     * <p>原文：if the specified data is not valid
     */
    @NotNull
    @Contract("null, null -> fail")
    BlockData createBlockData(@Nullable Material material, @Nullable String data) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器中已定义的标签. 建议插件使用{@link Tag}中的具体标签而非此方法,
     * 因为此方法不保证可用的标签, 且由于缺乏缓存可能导致性能下降.
     * <br>
     * 标签将以特定于实现的方式搜索, 但预期路径格式为 namespace/tags/registry/key.
     * <br>
     * 服务器实现可以仅处理{@link Tag}中指示的注册表.
     * <p>原文：
     * Gets a tag which has already been defined within the server. Plugins are
     * suggested to use the concrete tags in {@link Tag} rather than this method
     * which makes no guarantees about which tags are available, and may also be
     * less performant due to lack of caching.
     * <br>
     * Tags will be searched for in an implementation specific manner, but a
     * path consisting of namespace/tags/registry/key is expected.
     * <br>
     * Server implementations are allowed to handle only the registries
     * indicated in {@link Tag}.
     *
     * @param <T> 标签的类型
     * <p>原文：type of the tag
     * @param registry 要查找的标签注册表
     * <p>原文：the tag registry to look at
     * @param tag 标签的名称
     * <p>原文：the name of the tag
     * @param clazz 标签条目的类
     * <p>原文：the class of the tag entries
     * @return 标签实例, 如果不存在则返回null
     * <p>原文：the tag or null
     */
    @Nullable
    <T extends Keyed> Tag<T> getTag(@NotNull String registry, @NotNull NamespacedKey tag, @NotNull Class<T> clazz);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器定义的所有标签.
     * <br>
     * Server implementations are allowed to handle only the registries
     * indicated in {@link Tag}.
     * <br>
     * 对返回的迭代器的可变性不作保证.
     * <p>原文：
     * Gets all tags which have been defined within the server.
     * <br>
     * Server implementations are allowed to handle only the registries
     * indicated in {@link Tag}.
     * <br>
     * No guarantees are made about the mutability of the returned iterator.
     *
     * @param <T> tag的类型
     * <p>原文：type of the tag
     * @param registry the tag registry to look at
     * @param clazz the class of the tag entries
     * @return all defined tags
     */
    @NotNull
    <T extends Keyed> Iterable<Tag<T>> getTags(@NotNull String registry, @NotNull Class<T> clazz);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定的 {@link LootTable 战利品表}.
     * <p>原文：
     * Gets the specified {@link LootTable}.
     *
     * @param key LootTable的名称
     * <p>原文：the name of the LootTable
     * @return LootTable实例, 若找不到返回null
     * <p>原文：the LootTable, or null if no LootTable is found with that name
     */
    @Nullable
    LootTable getLootTable(@NotNull NamespacedKey key);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用给定的Vanilla选择器选择实体.
     * <br>
     * 对选择器格式不作保证, 只要其与当前Minecraft版本的Vanilla格式匹配即可.
     * <br>
     * 选择器通常以'@'开头, 除非是选择玩家, 此时可以直接使用玩家名或UUID.
     * <br>
     * 注意在Vanilla中, 使用'@'选择器通常需要提升的权限, 但此方法不会检查发送者的此类权限.
     * <p>原文：
     * Selects entities using the given Vanilla selector.
     * <br>
     * No guarantees are made about the selector format, other than they match
     * the Vanilla format for the active Minecraft version.
     * <br>
     * Usually a selector will start with '@', unless selecting a Player in
     * which case it may simply be the Player's name or UUID.
     * <br>
     * Note that in Vanilla, elevated permissions are usually required to use
     * '@' selectors, but this method should not check such permissions from the
     * sender.
     *
     * @param sender 作为执行者的发送者, 必须提供
     * <p>原文：the sender to execute as, must be provided
     * @param selector 选择器字符串
     * <p>原文：the selection string
     * @return 被选中的实体列表. 列表不会为null, 但不作进一步保证.
     * <p>原文：a list of the selected entities. The list will not be null, but
     * no further guarantees are made.
     * @throws IllegalArgumentException 如果选择器格式错误或参数为null
     * <p>原文：if the selector is malformed in any way
     * or a parameter is null
     */
    @NotNull
    List<Entity> selectEntities(@NotNull CommandSender sender, @NotNull String selector) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于加载和保存结构的结构管理器.
     * <p>原文：
     * Gets the structure manager for loading and saving structures.
     *
     * @return 结构管理器实例
     * <p>原文：the structure manager
     */
    @NotNull
    StructureManager getStructureManager();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回给定类的注册表.
     * <br>
     * 如果给定类没有注册表则返回null.
     * <br>
     * 根据实现, 并非{@link Registry}中的每个注册表都会由此方法返回.
     * <p>原文：
     * Returns the registry for the given class.
     * <br>
     * If no registry is present for the given class null will be returned.
     * <br>
     * Depending on the implementation not every registry present in
     * {@link Registry} will be returned by this method.
     *
     * @param tClass 要获取的注册表的类
     * <p>原文：of the registry to get
     * @param <T> 注册表的类型
     * <p>原文：type of the registry
     * @return 对应的注册表, 如果不存在则返回null
     * <p>原文：the corresponding registry or null if not present
     * @deprecated use {@link io.papermc.paper.registry.RegistryAccess#getRegistry(io.papermc.paper.registry.RegistryKey)}
     * with keys from {@link io.papermc.paper.registry.RegistryKey}
     */
    @Nullable
    @Deprecated(since = "1.20.6") // Paper
    <T extends Keyed> Registry<T> getRegistry(@NotNull Class<T> tClass);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return UnsafeValues实例
     * <p>原文：the unsafe values instance
     * @see UnsafeValues
     */
    @Deprecated(since = "1.7.2")
    @NotNull
    UnsafeValues getUnsafe();

    // Spigot start
    public class Spigot {

        /**
         * @deprecated Server config options may be renamed or removed without notice. Prefer using existing API
         *  wherever possible, rather than directly reading from a server config.
         *
         * @see #getServerConfig()
         * @return The server's spigot config.
         */
        @Deprecated(since = "1.21.4", forRemoval = true)
        @NotNull
        public org.bukkit.configuration.file.YamlConfiguration getConfig() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * @deprecated Server config options may be renamed or removed without notice. Prefer using existing API
         *  wherever possible, rather than directly reading from a server config.
         *
         * @see #getServerConfig()
         * @return The server's bukkit config.
         */
        // Paper start
        @Deprecated(since = "1.21.4", forRemoval = true)
        @NotNull
        public org.bukkit.configuration.file.YamlConfiguration getBukkitConfig()
        {
            throw new UnsupportedOperationException( "Not supported yet." );
        }

        /**
         * @deprecated Server config options may be renamed or removed without notice. Prefer using existing API
         *  wherever possible, rather than directly reading from a server config.
         *
         * @see #getServerConfig()
         * @return The server's spigot config.
         */
        @Deprecated(since = "1.21.4", forRemoval = true)
        @NotNull
        public org.bukkit.configuration.file.YamlConfiguration getSpigotConfig()
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * @deprecated Server config options may be renamed or removed without notice. Prefer using existing API
         *  wherever possible, rather than directly reading from a server config.
         *
         * @see #getServerConfig()
         * @return The server's paper config.
         */
        @Deprecated(since = "1.21.4", forRemoval = true)
        @NotNull
        public org.bukkit.configuration.file.YamlConfiguration getPaperConfig()
        {
            throw new UnsupportedOperationException("Not supported yet.");
        }
        // Paper end

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向全体玩家广播一条消息.
         * <p>原文：
         * Sends the component to the player
         *
         * @param component 要发送的聊天消息组件
         * <p>原文：the components to send
         * @deprecated use {@link #broadcast(net.kyori.adventure.text.Component)}
         */
        @Deprecated // Paper
        public void broadcast(@NotNull net.md_5.bungee.api.chat.BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 将多个聊天消息组件作为单条消息广播给所有在线玩家.
         * <p>原文：
         * Sends an array of components as a single message to the player
         *
         * @param components 要发送的聊天消息组件
         * <p>原文：the components to send
         * @deprecated use {@link #broadcast(net.kyori.adventure.text.Component)}
         */
        @Deprecated // Paper
        public void broadcast(@NotNull net.md_5.bungee.api.chat.BaseComponent... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 重启服务器. 如果服务器管理员没有配置/未正确配置"spigot.yml"中的重启配置, 服务器将停止运行.
         * <p>原文：
         * Restart the server. If the server administrator has not configured restarting, the server will stop.
         *
         * @deprecated Use {@link Server#restart()} instead.
         */
        @Deprecated(since = "1.21.4", forRemoval = true)
        public void restart() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    /**
     * @deprecated All methods on this class have been deprecated, see the individual methods for replacements.
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    @NotNull
    Spigot spigot();
    // Spigot end

    /**
     * Restarts the server. If the server administrator has not configured restarting, the server will stop.
     */
    void restart();

    void reloadPermissions(); // Paper

    boolean reloadCommandAliases(); // Paper

    // Paper start - allow preventing player name suggestions by default
    /**
     * Checks if player names should be suggested when a command returns {@code null} as
     * their tab completion result.
     *
     * @return true if player names should be suggested
     */
    boolean suggestPlayerNamesWhenNullTabCompletions();

    /**
     * Gets the default no permission message used on the server
     *
     * @return the default message
     * @deprecated use {@link #permissionMessage()}
     */
    @NotNull
    @Deprecated
    String getPermissionMessage();

    /**
     * Gets the default no permission message used on the server
     *
     * @return the default message
     */
    @NotNull net.kyori.adventure.text.Component permissionMessage();

    /**
     * Creates a PlayerProfile for the specified uuid, with name as null.
     *
     * If a player with the passed uuid exists on the server at the time of creation, the returned player profile will
     * be populated with the properties of said player (including their uuid and name).
     *
     * @param uuid UUID to create profile for
     * @return A PlayerProfile object
     */
    @NotNull
    com.destroystokyo.paper.profile.PlayerProfile createProfile(@NotNull UUID uuid);

    /**
     * Creates a PlayerProfile for the specified name, with UUID as null.
     *
     * If a player with the passed name exists on the server at the time of creation, the returned player profile will
     * be populated with the properties of said player (including their uuid and name).
     * <p>
     * E.g. if the player 'jeb_' is currently playing on the server, calling {@code createProfile("JEB_")} will
     * yield a profile with the name 'jeb_', their uuid and their textures.
     * To bypass this pre-population on a case-insensitive name match, see {@link #createProfileExact(UUID, String)}.
     * <p>
     *
     * @param name Name to create profile for
     * @return A PlayerProfile object
     * @throws IllegalArgumentException if the name is longer than 16 characters
     * @throws IllegalArgumentException if the name contains invalid characters
     */
    @NotNull
    com.destroystokyo.paper.profile.PlayerProfile createProfile(@NotNull String name);

    /**
     * Creates a PlayerProfile for the specified name/uuid
     *
     * Both UUID and Name can not be null at same time. One must be supplied.
     * If a player with the passed uuid or name exists on the server at the time of creation, the returned player
     * profile will be populated with the properties of said player (including their uuid and name).
     * <p>
     * E.g. if the player 'jeb_' is currently playing on the server, calling {@code createProfile(null, "JEB_")} will
     * yield a profile with the name 'jeb_', their uuid and their textures.
     * To bypass this pre-population on a case-insensitive name match, see {@link #createProfileExact(UUID, String)}.
     * <p>
     *
     * The name comparison will compare the {@link String#toLowerCase()} version of both the passed name parameter and
     * a players name to honour the case-insensitive nature of a mojang profile lookup.
     *
     * @param uuid UUID to create profile for
     * @param name Name to create profile for
     * @return A PlayerProfile object
     * @throws IllegalArgumentException if the name is longer than 16 characters
     * @throws IllegalArgumentException if the name contains invalid characters
     */
    @NotNull
    com.destroystokyo.paper.profile.PlayerProfile createProfile(@Nullable UUID uuid, @Nullable String name);

    /**
     * Creates an exact PlayerProfile for the specified name/uuid
     *
     * Both UUID and Name can not be null at same time. One must be supplied.
     * If a player with the passed uuid or name exists on the server at the time of creation, the returned player
     * profile will be populated with the properties of said player.
     * <p>
     * Compared to {@link #createProfile(UUID, String)}, this method will never mutate the passed uuid or name.
     * If a player with either the same uuid or a matching name (case-insensitive) is found on the server, their
     * properties, such as textures, will be pre-populated in the profile, however the passed uuid and name stay intact.
     *
     * @param uuid UUID to create profile for
     * @param name Name to create profile for
     * @return A PlayerProfile object
     * @throws IllegalArgumentException if the name is longer than 16 characters
     * @throws IllegalArgumentException if the name contains invalid characters
     */
    @NotNull
    com.destroystokyo.paper.profile.PlayerProfile createProfileExact(@Nullable UUID uuid, @Nullable String name);

    /**
     * Get the current internal server tick
     *
     * @return Current tick
     */
    int getCurrentTick();

    /**
     * Checks if the server is in the process of being shutdown.
     *
     * @return true if server is in the process of being shutdown
     */
    boolean isStopping();

    /**
     * Returns the {@link com.destroystokyo.paper.entity.ai.MobGoals} manager
     *
     * @return the mob goals manager
     */
    @NotNull
    com.destroystokyo.paper.entity.ai.MobGoals getMobGoals();

    /**
     * @return the datapack manager
     */
    @NotNull
    io.papermc.paper.datapack.DatapackManager getDatapackManager();

    /**
     * Gets the potion brewer.
     *
     * @return the potion brewer
     */
    @NotNull org.bukkit.potion.PotionBrewer getPotionBrewer();
    // Paper end

    // Paper start - Folia region threading API
    /**
     * Returns the Folia region task scheduler. The region task scheduler can be used to schedule
     * tasks by location to be executed on the region which owns the location.
     * <p>
     * <b>Note</b>: It is entirely inappropriate to use the region scheduler to schedule tasks for entities.
     * If you wish to schedule tasks to perform actions on entities, you should be using {@link Entity#getScheduler()}
     * as the entity scheduler will "follow" an entity if it is teleported, whereas the region task scheduler
     * will not.
     * </p>
     * <p><b>If you do not need/want to make your plugin run on Folia, use {@link #getScheduler()} instead.</b></p>
     * @return the region task scheduler
     */
    @NotNull io.papermc.paper.threadedregions.scheduler.RegionScheduler getRegionScheduler();

    /**
     * Returns the Folia async task scheduler. The async task scheduler can be used to schedule tasks
     * that execute asynchronously from the server tick process.
     * @return the async task scheduler
     */
    @NotNull io.papermc.paper.threadedregions.scheduler.AsyncScheduler getAsyncScheduler();

    /**
     * Returns the Folia global region task scheduler. The global task scheduler can be used to schedule
     * tasks to execute on the global region.
     * <p>
     * The global region is responsible for maintaining world day time, world game time, weather cycle,
     * sleep night skipping, executing commands for console, and other misc. tasks that do not belong to any specific region.
     * </p>
     * <p><b>If you do not need/want to make your plugin run on Folia, use {@link #getScheduler()} instead.</b></p>
     * @return the global region scheduler
     */
    @NotNull io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler getGlobalRegionScheduler();

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the chunk at the specified world and block position.
     * @param world Specified world.
     * @param position Specified block position.
     */
    boolean isOwnedByCurrentRegion(@NotNull World world, @NotNull io.papermc.paper.math.Position position);

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the chunks centered at the specified block position within the specified square radius.
     * Specifically, this function checks that every chunk with position x in [centerX - radius, centerX + radius] and
     * position z in [centerZ - radius, centerZ + radius] is owned by the current ticking region.
     * @param world Specified world.
     * @param position Specified block position.
     * @param squareRadiusChunks Specified square radius. Must be >= 0. Note that this parameter is <i>not</i> a <i>squared</i>
     *                           radius, but rather a <i>Chebyshev Distance</i>.
     */
    boolean isOwnedByCurrentRegion(@NotNull World world, @NotNull io.papermc.paper.math.Position position, int squareRadiusChunks);

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the chunk at the specified world and block position as included in the specified location.
     * @param location Specified location, must have a non-null world.
     */
    boolean isOwnedByCurrentRegion(@NotNull Location location);

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the chunks centered at the specified world and block position as included in the specified location
     * within the specified square radius.
     * Specifically, this function checks that every chunk with position x in [centerX - radius, centerX + radius] and
     * position z in [centerZ - radius, centerZ + radius] is owned by the current ticking region.
     * @param location Specified location, must have a non-null world.
     * @param squareRadiusChunks Specified square radius. Must be >= 0. Note that this parameter is <i>not</i> a <i>squared</i>
     *                           radius, but rather a <i>Chebyshev Distance</i>.
     */
    boolean isOwnedByCurrentRegion(@NotNull Location location, int squareRadiusChunks);

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the chunk at the specified block position.
     * @param block Specified block position.
     */
    default boolean isOwnedByCurrentRegion(@NotNull org.bukkit.block.Block block) {
        return isOwnedByCurrentRegion(block.getLocation());
    }

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the chunk at the specified world and chunk position.
     * @param world Specified world.
     * @param chunkX Specified x-coordinate of the chunk position.
     * @param chunkZ Specified z-coordinate of the chunk position.
     */
    boolean isOwnedByCurrentRegion(@NotNull World world, int chunkX, int chunkZ);

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the chunks centered at the specified world and chunk position within the specified
     * square radius.
     * Specifically, this function checks that every chunk with position x in [centerX - radius, centerX + radius] and
     * position z in [centerZ - radius, centerZ + radius] is owned by the current ticking region.
     * @param world Specified world.
     * @param chunkX Specified x-coordinate of the chunk position.
     * @param chunkZ Specified z-coordinate of the chunk position.
     * @param squareRadiusChunks Specified square radius. Must be >= 0. Note that this parameter is <i>not</i> a <i>squared</i>
     *                           radius, but rather a <i>Chebyshev Distance</i>.
     */
    boolean isOwnedByCurrentRegion(@NotNull World world, int chunkX, int chunkZ, int squareRadiusChunks);

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the chunks in the rectangle specified by the min and max parameters.
     * Specifically, this function checks that every chunk with position x in [minChunkX, maxChunkX] and
     * position z in [minChunkZ, maxChunkZ] is owned by the current ticking region.
     * @param world Specified world.
     * @param minChunkX Specified x-coordinate of the minimum chunk position.
     * @param minChunkZ Specified z-coordinate of the minimum chunk position.
     * @param maxChunkX Specified x-coordinate of the maximum chunk position.
     * @param maxChunkZ Specified z-coordinate of the maximum chunk position.
     */
    boolean isOwnedByCurrentRegion(@NotNull World world, int minChunkX, int minChunkZ, int maxChunkX, int maxChunkZ);

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the specified entity. Note that this function is the only appropriate method of checking
     * for ownership of an entity, as retrieving the entity's location is undefined unless the entity is owned
     * by the current region.
     * @param entity Specified entity.
     */
    boolean isOwnedByCurrentRegion(@NotNull Entity entity);

    /**
     * Returns whether the current thread is ticking the global region.
     * @see io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler
     */
    public boolean isGlobalTickThread();
    // Paper end - Folia region threading API

    // Paper start - API to check if the server is sleeping
    /**
     * Returns whether the server is sleeping/paused.
     */
    boolean isPaused();

    /**
     * Allows or disallows the server to sleep/pause.
     * If any plugin disallows pausing, the server will never pause.
     *
     * @param plugin The {@link org.bukkit.plugin.Plugin} that's allowing or disallowing pausing.
     * @param value Whether to allow sleeping of the server (defaults to true).
     */
    void allowPausing(@NotNull org.bukkit.plugin.Plugin plugin, boolean value);
    // Paper end - API to check if the server is sleeping
}
