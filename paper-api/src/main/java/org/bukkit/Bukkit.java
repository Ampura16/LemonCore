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
import io.papermc.paper.ServerBuildInfo;
import io.papermc.paper.configuration.ServerConfiguration;
import net.kyori.adventure.text.Component;
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
 * 代表一个 Bukkit 核心, 用来转发 {@link Server} 单例的调用.
 * <p>原文：
 * Represents the Bukkit core, for version and Server singleton handling
 */
public final class Bukkit {
    private static Server server;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 工具类不能被初始化
     * <p>原文：
     * Static class cannot be initialized.
     */
    private Bukkit() {}

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前持有的 {@link Server} 实例.
     * <p>原文：
     * Gets the current {@link Server} singleton
     *
     * @return 正在运行的服务器的实例
     * <p>原文：Server instance being ran
     */
    @NotNull
    public static Server getServer() {
        return server;
    }

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
    public static File getPluginsFolder() {
        return server.getPluginsFolder();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 请求设置所持有的 {@link Server} 实例.
     * 如果 Server 已经被设定过了, 这个操作无法完成.
     * <p>原文：
     * Attempts to set the {@link Server} singleton.
     * <p>
     * This cannot be done if the Server is already set.
     *
     * @param server 服务器实例
     * <p>原文：Server instance
     */
    public static void setServer(@NotNull Server server) {
        if (Bukkit.server != null) {
            throw new UnsupportedOperationException("Cannot redefine singleton Server");
        }

        Bukkit.server = server;
        // Paper start - add git information
        server.getLogger().info(getVersionMessage());
    }
    /**
     * 获取描述服务器正在运行的版本的消息.
     *
     * @return 描述服务器正在运行的版本的消息
     */
    @NotNull
    public static String getVersionMessage() {
        final io.papermc.paper.ServerBuildInfo version = io.papermc.paper.ServerBuildInfo.buildInfo();
        return "此服务器正在运行 " + getName() + " 版本 " + version.asString(ServerBuildInfo.StringRepresentation.VERSION_SIMPLE);
        // Paper end
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个 {@link Server} 实例的名字.
     * <p>原文：
     * Gets the name of this server implementation.
     *
     * @return 这个服务器实例的名字
     * <p>原文：name of this server implementation
     * @see io.papermc.paper.ServerBuildInfo#brandName()
     */
    @NotNull
    public static String getName() {
        return server.getName();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个 {@link Server} 实例的版本.
     * <p>原文：
     * Gets the version string of this server implementation.
     *
     * @return 这个服务器实例的版本
     * <p>原文：version of this server implementation
     * @see io.papermc.paper.ServerBuildInfo
     */
    @NotNull
    public static String getVersion() {
        return server.getVersion();
    }

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
    public static String getBukkitVersion() {
        return server.getBukkitVersion();
    }

    // Paper start - expose game version
    /**
     * Gets the version of game this server implements
     *
     * @return version of game
     * @see io.papermc.paper.ServerBuildInfo#minecraftVersionId()
     * @see io.papermc.paper.ServerBuildInfo#minecraftVersionName()
     */
    @NotNull
    public static String getMinecraftVersion() {
        return server.getMinecraftVersion();
    }
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
    public static Collection<? extends Player> getOnlinePlayers() {
        return server.getOnlinePlayers();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器允许进入的最大玩家数.
     * <p>原文：
     * Get the maximum amount of players which can login to this server.
     *
     * @return 服务器的最大玩家数
     * <p>原文：the amount of players this server allows
     */
    public static int getMaxPlayers() {
        return server.getMaxPlayers();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置服务器允许进入的最大玩家数.
     * <p>原文：
     * Set the maximum amount of players allowed to be logged in at once.
     *
     * @param maxPlayers 服务器的最大玩家数
     * <p>原文：The maximum amount of concurrent players
     */
    public static void setMaxPlayers(int maxPlayers) {
        server.setMaxPlayers(maxPlayers);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器监听的端口号.
     * <p>原文：
     * Get the game port that the server runs on.
     *
     * @return 服务器端口
     * <p>原文：the port number of this server
     */
    public static int getPort() {
        return server.getPort();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器的视距.
     * <p>原文：
     * Get the view distance from this server.
     *
     * @return 服务器当前设置的视距
     * <p>原文：the view distance from this server.
     */
    public static int getViewDistance() {
        return server.getViewDistance();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器的模拟距离.
     * <p>原文：
     * Get the simulation distance from this server.
     *
     * @return 服务器的模拟距离
     * <p>原文：the simulation distance from this server.
     */
    public static int getSimulationDistance() {
        return server.getSimulationDistance();
    }

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
    public static String getIp() {
        return server.getIp();
    }

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
    public static String getWorldType() {
        return server.getWorldType();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此服务器是否自然生成结构.
     * <p>原文：
     * Get generate-structures setting.
     *
     * @return true表示服务器可自然生成结构, false反之
     * <p>原文：true if structure generation is enabled, false otherwise
     */
    public static boolean getGenerateStructures() {
        return server.getGenerateStructures();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取最大世界大小.
     * <p>原文：
     * Get max world size.
     *
     * @return 为服务器指定的最大世界大小
     * <p>原文：the maximum world size as specified for the server
     */
    public static int getMaxWorldSize() {
        return server.getMaxWorldSize();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此服务器是否开启了末路之地.
     * <p>原文：
     * Gets whether this server allows the End or not.
     *
     * @return 是否可以进入末路之地
     * <p>原文：whether this server allows the End or not
     */
    public static boolean getAllowEnd() {
        return server.getAllowEnd();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此服务器是否开启了下界.
     * <p>原文：
     * Gets whether this server allows the Nether or not.
     *
     * @return 是否可以进入下界
     * <p>原文：whether this server allows the Nether or not
     */
    public static boolean getAllowNether() {
        return server.getAllowNether();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否正在记录玩家的IP地址.
     * <p>原文：
     * Gets whether the server is logging the IP addresses of players.
     *
     * @return 服务器是否正在记录玩家的IP地址
     * <p>原文：whether the server is logging the IP addresses of players
     */
    public static boolean isLoggingIPs() {
        return server.isLoggingIPs();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取初始启用的数据包列表.
     *
     * @return 初始启用的数据包名称列表
     */
    @NotNull
    public static List<String> getInitialEnabledPacks() {
        return server.getInitialEnabledPacks();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取初始禁用的数据包列表.
     *
     * @return 初始禁用的数据包名称列表
     */
    @NotNull
    public static List<String> getInitialDisabledPacks() {
        return server.getInitialDisabledPacks();
    }

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
    public static ResourcePack getServerResourcePack() {
        return server.getServerResourcePack();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器Tick管理器.
     * <p>原文：
     * Get the ServerTick Manager.
     *
     * @return 服务器Tick管理器实例
     * <p>原文：the manager
     */
    @NotNull
    public static ServerTickManager getServerTickManager() {
        return server.getServerTickManager();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器资源包的URI, 如果未指定则返回空字符串.
     * <p>原文：
     * Gets the server resource pack uri, or empty string if not specified.
     *
     * @return 服务器资源包的URI, 否则为空字符串
     * <p>原文：the server resource pack uri, otherwise empty string
     */
    @NotNull
    public static String getResourcePack() {
        return server.getResourcePack();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器资源包的SHA-1摘要, 如果未指定则返回空字符串.
     * <p>原文：
     * Gets the SHA-1 digest of the server resource pack, or empty string if
     * not specified.
     *
     * @return 服务器资源包的SHA-1摘要, 否则为空字符串
     * <p>原文：the SHA-1 digest of the server resource pack, otherwise empty
     *     string
     */
    @NotNull
    public static String getResourcePackHash() {
        return server.getResourcePackHash();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器资源包为必填时显示的自定义提示消息, 如果未指定则返回空字符串.
     * <p>原文：
     * Gets the custom prompt message to be shown when the server resource
     * pack is required, or empty string if not specified.
     *
     * @return 服务器资源包为必填时显示的自定义提示消息, 否则为空字符串
     * <p>原文：the custom prompt message to be shown when the server resource,
     *     otherwise empty string
     */
    @NotNull
    public static String getResourcePackPrompt() {
        return server.getResourcePackPrompt();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器资源包是否为强制要求.
     * <p>原文：
     * Gets whether the server resource pack is enforced.
     *
     * @return 服务器资源包是否为强制要求
     * <p>原文：whether the server resource pack is enforced
     */
    public static boolean isResourcePackRequired() {
        return server.isResourcePackRequired();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此服务器是否开启了白名单.
     * <p>原文：
     * Gets whether this server has a whitelist or not.
     *
     * @return 是否开启了白名单
     * <p>原文：whether this server has a whitelist or not
     */
    public static boolean hasWhitelist() {
        return server.hasWhitelist();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此服务器是否开启白名单.
     * <p>原文：
     * Sets if the server is whitelisted.
     *
     * @param value true为开, false为关
     * <p>原文：true for whitelist on, false for off
     */
    public static void setWhitelist(boolean value) {
        server.setWhitelist(value);
    }

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
    public static boolean isWhitelistEnforced() {
        return server.isWhitelistEnforced();
    }

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
    public static void setWhitelistEnforced(boolean value) {
        server.setWhitelistEnforced(value);
    }

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
    public static Set<OfflinePlayer> getWhitelistedPlayers() {
        return server.getWhitelistedPlayers();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从硬盘重载白名单列表.
     * <p>原文：
     * Reloads the whitelist from disk.
     */
    public static void reloadWhitelist() {
        server.reloadWhitelist();
    }

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
     * java.lang.String)} to {@link Server#BROADCAST_CHANNEL_USERS}
     *
     * @param message 要广播的消息
     * <p>原文：the message
     * @return 成功接收此消息的玩家数
     * <p>原文：the number of players
     * @deprecated in favour of {@link Server#broadcast(Component)}
     */
    @Deprecated // Paper
    public static int broadcastMessage(@NotNull String message) {
        return server.broadcastMessage(message);
    }

    // Paper start
    /**
     * Sends the component to all online players.
     *
     * @param component the component to send
     * @deprecated use {@code sendMessage} methods on {@link #getServer()} that accept {@link Component}
     */
    @Deprecated
    public static void broadcast(@NotNull net.md_5.bungee.api.chat.BaseComponent component) {
        server.broadcast(component);
    }

    /**
     * Sends an array of components as a single message to all online players.
     *
     * @param components the components to send
     * @deprecated use {@code sendMessage} methods on {@link #getServer()} that accept {@link Component}
     */
    @Deprecated
    public static void broadcast(@NotNull net.md_5.bungee.api.chat.BaseComponent... components) {
        server.broadcast(components);
    }
    // Paper end

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
    public static String getUpdateFolder() {
        return server.getUpdateFolder();
    }

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
    public static File getUpdateFolderFile() {
        return server.getUpdateFolderFile();
    }

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
    public static long getConnectionThrottle() {
        return server.getConnectionThrottle();
    }

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
    public static int getTicksPerAnimalSpawns() {
        return server.getTicksPerAnimalSpawns();
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
    public static int getTicksPerMonsterSpawns() {
        return server.getTicksPerMonsterSpawns();
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
    public static int getTicksPerWaterSpawns() {
        return server.getTicksPerWaterSpawns();
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
    public static int getTicksPerAmbientSpawns() {
        return server.getTicksPerAmbientSpawns();
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
    public static int getTicksPerWaterAmbientSpawns() {
        return server.getTicksPerAmbientSpawns();
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
    public static int getTicksPerWaterUndergroundCreatureSpawns() {
        return server.getTicksPerWaterUndergroundCreatureSpawns();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每隔多少tick应该生成一次指定{@link SpawnCategory 生成类别}的生物.
     * <p>
     * <b>例如:</b>
     * <ul>
     * <li>此值为 1 表示服务器会在每个tick尝试生成该类别的生物.
     * <li>此值为 400 表示服务器会每隔400tick尝试生成该类别的生物.
     * <li>此值小于 0 表示会使用Minecraft的默认设置.
     * </ul>
     * <p>
     * <b>注意:</b> 如果设为 0, 该类别的生物生成会被禁用.
     * <p>
     * Minecraft使用的默认值: 1.
     * <br>
     * <b>注意: </b> {@link SpawnCategory#MISC} 类别不予考虑.
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
     * <p>
     * Minecraft default: 1.
     * <br>
     * <b>Note: </b> the {@link SpawnCategory#MISC} are not consider.
     *
     * @param spawnCategory 生成类别
     * <p>原文：the category of spawn
     * @return 每隔多少tick应该生成一次该类别生物的默认值
     * <p>原文：the default ticks per {@link SpawnCategory} mobs spawn value
     */
    public static int getTicksPerSpawns(@NotNull SpawnCategory spawnCategory) {
        return server.getTicksPerSpawns(spawnCategory);
    }

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
    public static Player getPlayer(@NotNull String name) {
        return server.getPlayer(name);
    }

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
    public static Player getPlayerExact(@NotNull String name) {
        return server.getPlayerExact(name);
    }

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
    public static List<Player> matchPlayer(@NotNull String name) {
        return server.matchPlayer(name);
    }

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
    public static Player getPlayer(@NotNull UUID id) {
        return server.getPlayer(id);
    }

    // Paper start
    /**
     * Gets the unique ID of the player currently known as the specified player name
     * In Offline Mode, will return an Offline UUID
     *
     * @param playerName the player name to look up the unique ID for
     * @return A UUID, or null if that player name is not registered with Minecraft and the server is in online mode
     */
    @Nullable
    public static UUID getPlayerUniqueId(@NotNull String playerName) {
        return server.getPlayerUniqueId(playerName);
    }
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
    public static PluginManager getPluginManager() {
        return server.getPluginManager();
    }

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
    public static BukkitScheduler getScheduler() {
        return server.getScheduler();
    }

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
    public static ServicesManager getServicesManager() {
        return server.getServicesManager();
    }

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
    public static List<World> getWorlds() {
        return server.getWorlds();
    }

    // Paper start
    /**
     * Gets whether the worlds are being ticked right now.
     *
     * @return true if the worlds are being ticked, false otherwise.
     */
    public static boolean isTickingWorlds(){
        return server.isTickingWorlds();
    }
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
     */
    @Nullable
    public static World createWorld(@NotNull WorldCreator creator) {
        return server.createWorld(creator);
    }

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
     */
    public static boolean unloadWorld(@NotNull String name, boolean save) {
        return server.unloadWorld(name, save);
    }

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
     */
    public static boolean unloadWorld(@NotNull World world, boolean save) {
        return server.unloadWorld(world, save);
    }

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
    public static World getWorld(@NotNull String name) {
        return server.getWorld(name);
    }

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
    public static World getWorld(@NotNull UUID uid) {
        return server.getWorld(uid);
    }
    // Paper start
    /**
     * Gets the world from the given NamespacedKey
     *
     * @param worldKey the NamespacedKey of the world to retrieve
     * @return a world with the given NamespacedKey, or null if none exists
     */
    @Nullable
    public static World getWorld(@NotNull NamespacedKey worldKey) {
        return server.getWorld(worldKey);
    }

    /**
     * Gets the world from the given Key
     *
     * @param worldKey the Key of the world to retrieve
     * @return a world with the given Key, or null if none exists
     */
    @Nullable
    public static World getWorld(@NotNull net.kyori.adventure.key.Key worldKey) {
        return server.getWorld(worldKey);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个虚拟{@link WorldBorder 世界边界}.
     * <p>原文：
     * Create a new virtual {@link WorldBorder}.
     *
     * @return 新创建的虚拟世界边界实例
     * <p>原文：the created world border instance
     * @see Player#setWorldBorder(WorldBorder)
     */
    @NotNull
    public static WorldBorder createWorldBorder() {
        return server.createWorldBorder();
    }

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
    public static MapView getMap(int id) {
        return server.getMap(id);
    }

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
    public static MapView createMap(@NotNull World world) {
        return server.createMap(world);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个以最近的指定{@link StructureType 结构类型}为目标的新探险家地图.
     * <br>
     * 此方法使用实现默认的半径和findUnexplored值 (通常为 100, true).
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
     * @return 新创建的物品堆
     * <p>原文：a newly created item stack
     * @see World#locateNearestStructure(org.bukkit.Location,
     *      org.bukkit.StructureType, int, boolean)
     * @deprecated use {@link #createExplorerMap(World, Location, org.bukkit.generator.structure.StructureType, org.bukkit.map.MapCursor.Type)}
     */
    @Deprecated // Paper
    @NotNull
    public static ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType) {
        return server.createExplorerMap(world, location, structureType);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个以最近的指定{@link StructureType 结构类型}为目标的新探险家地图.
     * <br>
     * 此方法使用实现默认的半径和findUnexplored值 (通常为 100, true).
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
     * @param radius 搜索半径, 详见 World#locateNearestStructure
     * <p>原文：radius to search, see World#locateNearestStructure for more
     *               information
     * @param findUnexplored 是否查找未探索的结构
     * <p>原文：whether to find unexplored structures
     * @return 新创建的物品堆
     * <p>原文：the newly created item stack
     * @see World#locateNearestStructure(org.bukkit.Location,
     *      org.bukkit.StructureType, int, boolean)
     * @deprecated use {@link #createExplorerMap(World, Location, org.bukkit.generator.structure.StructureType, org.bukkit.map.MapCursor.Type, int, boolean)}
     */
    @Deprecated // Paper
    @NotNull
    public static ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull StructureType structureType, int radius, boolean findUnexplored) {
        return server.createExplorerMap(world, location, structureType, radius, findUnexplored);
    }
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
    public static @Nullable ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull org.bukkit.generator.structure.StructureType structureType, @NotNull org.bukkit.map.MapCursor.Type mapIcon) {
        return server.createExplorerMap(world, location, structureType, mapIcon);
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
    public static @Nullable ItemStack createExplorerMap(@NotNull World world, @NotNull Location location, @NotNull org.bukkit.generator.structure.StructureType structureType, @NotNull org.bukkit.map.MapCursor.Type mapIcon, int radius, boolean findUnexplored) {
        return server.createExplorerMap(world, location, structureType, mapIcon, radius, findUnexplored);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 重新加载服务器并刷新设置和插件信息.
     * <p>原文：
     * Reloads the server, refreshing settings and plugin information.
     */
    public static void reload() {
        server.reload();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 只重载Minecraft游戏数据. 这包括自定义的进度和掉落表.
     * <p>原文：
     * Reload only the Minecraft data for the server. This includes custom
     * advancements and loot tables.
     */
    public static void reloadData() {
        server.reloadData();
    }

    // Paper start - update reloadable data
    /**
     * Updates all advancement, tag, and recipe data for all connected clients.
     * Useful for updating clients to new advancements/recipes/tags.
     * @see #updateRecipes()
     */
    public static void updateResources() {
        server.updateResources();
    }

    /**
     * Updates recipe data and the recipe book for all connected clients. Useful for
     * updating clients to new recipes.
     * @see #updateResources()
     */
    public static void updateRecipes() {
        server.updateRecipes();
    }
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
    public static Logger getLogger() {
        return server.getLogger();
    }

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
    public static PluginCommand getPluginCommand(@NotNull String name) {
        return server.getPluginCommand(name);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 保存已加载的玩家信息.
     * <p>原文：
     * Writes loaded players to disk.
     */
    public static void savePlayers() {
        server.savePlayers();
    }

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
    public static boolean dispatchCommand(@NotNull CommandSender sender, @NotNull String commandLine) throws CommandException {
        return server.dispatchCommand(sender, commandLine);
    }

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
    public static boolean addRecipe(@Nullable Recipe recipe) {
        return server.addRecipe(recipe);
    }

    /**
     * Adds a recipe to the crafting manager.
     *
     * @apiNote resendRecipes is ignored at the moment for stability reasons, recipes will always be updated
     * @param recipe the recipe to add
     * @param resendRecipes true to update the client with the full set of recipes
     * @return true if the recipe was added, false if it wasn't for some reason
     */
    @Contract("null, _ -> false")
    public static boolean addRecipe(@Nullable Recipe recipe, boolean resendRecipes) {
        return server.addRecipe(recipe, resendRecipes);
    }

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
    public static List<Recipe> getRecipesFor(@NotNull ItemStack result) {
        return server.getRecipesFor(result);
    }

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
    public static Recipe getRecipe(@NotNull NamespacedKey recipeKey) {
        return server.getRecipe(recipeKey);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定物品堆列表对应的{@link Recipe 配方}.
     *
     * <p>该列表的格式为合成矩阵, 索引遵循以下模式:</p>
     *
     * <pre>
     * [ 0 1 2 ]
     * [ 3 4 5 ]
     * [ 6 7 8 ]
     * </pre>
     *
     * <p>注意: 此方法不会修改提供的物品堆数组, 如需修改, 请使用
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
     * @param craftingMatrix 要合成的物品列表. 最多不能超过9个物品.
     * <p>原文：list of items to be crafted from.
     *                       Must not contain more than 9 items.
     * @param world 合成发生所在的世界
     * <p>原文：The world the crafting takes place in.
     * @return 给定合成矩阵对应的{@link Recipe 配方}
     * <p>原文：the {@link Recipe} resulting from the given crafting matrix.
     */
    @Nullable
    public static Recipe getCraftingRecipe(@NotNull ItemStack @NotNull [] craftingMatrix, @NotNull World world) {
        return server.getCraftingRecipe(craftingMatrix, world);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用提供的{@link ItemStack}列表获取合成后的物品.
     *
     * <p>该列表的格式为合成矩阵, 索引遵循以下模式:</p>
     *
     * <pre>
     * [ 0 1 2 ]
     * [ 3 4 5 ]
     * [ 6 7 8 ]
     * </pre>
     *
     * <p>{@link World}和{@link Player}参数是完成Bukkit合成事件所必需的.</p>
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
     * @param craftingMatrix 要合成的物品列表. 最多不能超过9个物品.
     * <p>原文：list of items to be crafted from.
     *                       Must not contain more than 9 items.
     * @param world 合成发生所在的世界
     * <p>原文：The world the crafting takes place in.
     * @param player 模拟合成事件的玩家
     * <p>原文：The player to imitate the crafting event on.
     * @return 包含合成结果物品、矩阵和任何溢出物品的{@link ItemCraftResult}
     * <p>原文：resulting {@link ItemCraftResult} containing the resulting item, matrix and any overflow items.
     */
    @NotNull
    public static ItemCraftResult craftItemResult(@NotNull ItemStack @NotNull [] craftingMatrix, @NotNull World world, @NotNull Player player) {
        return server.craftItemResult(craftingMatrix, world, player);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用提供的{@link ItemStack}列表获取合成后的物品.
     *
     * <p>该列表的格式为合成矩阵, 索引遵循以下模式:</p>
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
     * @param craftingMatrix 要合成的物品列表. 最多不能超过9个物品.
     * <p>原文：list of items to be crafted from.
     *                       Must not contain more than 9 items.
     * @param world 合成发生所在的世界
     * <p>原文：The world the crafting takes place in.
     * @return 包含合成结果物品、矩阵和任何溢出物品的{@link ItemCraftResult}
     * <p>原文：resulting {@link ItemCraftResult} containing the resulting item, matrix and any overflow items.
     */
    @NotNull
    public static ItemCraftResult craftItemResult(@NotNull ItemStack @NotNull [] craftingMatrix, @NotNull World world) {
        return server.craftItemResult(craftingMatrix, world);
    }


    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用提供的{@link ItemStack}列表获取合成后的物品.
     *
     * <p>该列表的格式为合成矩阵, 索引遵循以下模式:</p>
     *
     * <pre>
     * [ 0 1 2 ]
     * [ 3 4 5 ]
     * [ 6 7 8 ]
     * </pre>
     *
     * <p>{@link World}和{@link Player}参数是完成Bukkit合成事件所必需的.</p>
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
     * @param craftingMatrix 要合成的物品列表. 最多不能超过9个物品.
     * <p>原文：list of items to be crafted from.
     *                       Must not contain more than 9 items.
     * @param world 合成发生所在的世界
     * <p>原文：The world the crafting takes place in.
     * @param player 模拟合成事件的玩家
     * <p>原文：The player to imitate the crafting event on.
     * @return 给定合成矩阵对应的{@link ItemStack}, 如果未找到配方则返回{@link Material#AIR}的物品堆
     * <p>原文：the {@link ItemStack} resulting from the given crafting matrix, if no recipe is found
     * an ItemStack of {@link Material#AIR} is returned.
     */
    @NotNull
    public static ItemStack craftItem(@NotNull ItemStack @NotNull [] craftingMatrix, @NotNull World world, @NotNull Player player) {
        return server.craftItem(craftingMatrix, world, player);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用提供的{@link ItemStack}列表获取合成后的物品.
     *
     * <p>该列表的格式为合成矩阵, 索引遵循以下模式:</p>
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
     * @param craftingMatrix 要合成的物品列表. 最多不能超过9个物品.
     * <p>原文：list of items to be crafted from.
     *                       Must not contain more than 9 items.
     * @param world 合成发生所在的世界
     * <p>原文：The world the crafting takes place in.
     * @return 给定合成矩阵对应的{@link ItemStack}, 如果未找到配方则返回{@link Material#AIR}的物品堆
     * <p>原文：the {@link ItemStack} resulting from the given crafting matrix, if no recipe is found
     * an ItemStack of {@link Material#AIR} is returned.
     */
    @NotNull
    public static ItemStack craftItem(@NotNull ItemStack @NotNull [] craftingMatrix, @NotNull World world) {
        return server.craftItem(craftingMatrix, world);
    }

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
    public static Iterator<Recipe> recipeIterator() {
        return server.recipeIterator();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 清理所有已添加的合成配方.
     * <p>原文：
     * Clears the list of crafting recipes.
     */
    public static void clearRecipes() {
        server.clearRecipes();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 重置自定义合成配方列表至默认状态.
     * <p>原文：
     * Resets the list of crafting recipes to the default.
     */
    public static void resetRecipes() {
        server.resetRecipes();
    }

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
    public static boolean removeRecipe(@NotNull NamespacedKey key) {
        return server.removeRecipe(key);
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
    public static boolean removeRecipe(@NotNull NamespacedKey key, boolean resendRecipes) {
        return server.removeRecipe(key, resendRecipes);
    }
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
    public static Map<String, String[]> getCommandAliases() {
        return server.getCommandAliases();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取出生地保护的范围（以方块为单位）.
     * <p>原文：
     * Gets the radius, in blocks, around each worlds spawn point to protect.
     *
     * @return 出生地保护范围，0为没有保护
     * <p>原文：spawn radius, or 0 if none
     */
    public static int getSpawnRadius() {
        return server.getSpawnRadius();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置出生地保护的范围.
     * <p>原文：
     * Sets the radius, in blocks, around each worlds spawn point to protect.
     *
     * @param value 新的出生地保护的范围，0设为没有保护
     * <p>原文：new spawn radius, or 0 if none
     * @deprecated has not functioned for a long time as the spawn radius is defined by the server.properties file.
     * There is no API replacement for this method. It is generally recommended to implement "protection"-like behaviour
     * via events or third-party plugin APIs.
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    public static void setSpawnRadius(int value) {
        server.setSpawnRadius(value);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否应该在玩家发送消息时向客户端发送玩家聊天消息的预览.
     * <p>原文：
     * Gets whether the server should send a preview of the player's chat
     * message to the client when the player sends a message
     *
     * @return 如果服务器应该发送预览则返回true, 否则返回false
     * <p>原文：true if the server should send a preview, false otherwise
     * @deprecated 聊天预览功能已被移除
     * <p>原文：chat previews have been removed
     */
    @Deprecated(since = "1.19.3", forRemoval = true)
    public static boolean shouldSendChatPreviews() {
        return server.shouldSendChatPreviews();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否只允许拥有Mojang签名公钥的玩家加入.
     * <p>原文：
     * Gets whether the server only allow players with Mojang-signed public key
     * to join
     *
     * @return 如果只有Mojang签名的玩家可以加入则返回true, 否则返回false
     * <p>原文：true if only Mojang-signed players can join, false otherwise
     */
    public static boolean isEnforcingSecureProfiles() {
        return server.isEnforcingSecureProfiles();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此服务器是否允许从其他服务器转入的连接.
     * <p>原文：
     * Gets whether this server is allowing connections transferred from other
     * servers.
     *
     * @return 如果服务器接受转入则返回true, 否则返回false
     * <p>原文：true if the server accepts transfers, false otherwise
     */
    public static boolean isAcceptingTransfers() {
        return server.isAcceptingTransfers();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否在服务器状态中隐藏在线玩家.
     * <p>原文：
     * Gets whether the Server hide online players in server status.
     *
     * @return 如果服务器隐藏在线玩家则返回true, 否则返回false
     * <p>原文：true if the server hide online players, false otherwise
     */
    public static boolean getHideOnlinePlayers() {
        return server.getHideOnlinePlayers();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否开启了正版模式.
     * <p>原文：
     * Gets whether the Server is in online mode or not.
     *
     * @return true则开启/false反之
     * <p>原文：true if the server authenticates clients, false otherwise
     */
    public static boolean getOnlineMode() {
        return server.getOnlineMode();
    }

    /**
     * Retrieves the server configuration.
     *
     * @return the instance of ServerConfiguration containing the server's configuration details
     */
    public static @NotNull ServerConfiguration getServerConfig() {
        return server.getServerConfig();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否允许飞行.
     * <p>原文：
     * Gets whether this server allows flying or not.
     *
     * @return 服务器是否允许飞行
     * <p>原文：true if the server allows flight, false otherwise
     */
    public static boolean getAllowFlight() {
        return server.getAllowFlight();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否处于极限生存模式.
     * <p>原文：
     * Gets whether the server is in hardcore mode or not.
     *
     * @return 服务器是否处于极限生存模式
     * <p>原文：true if the server mode is hardcore, false otherwise
     */
    public static boolean isHardcore() {
        return server.isHardcore();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 关闭服务器, 停止一切在运行的东西.
     * <p>原文：
     * Shutdowns the server, stopping everything.
     */
    public static void shutdown() {
        server.shutdown();
    }

    // Paper start
    /**
     * Broadcast a message to all players.
     * <p>
     * This is the same as calling {@link #broadcast(Component,
     * java.lang.String)} with the {@link Server#BROADCAST_CHANNEL_USERS} permission.
     *
     * @param message the message
     * @return the number of players
     */
    public static int broadcast(net.kyori.adventure.text.@NotNull Component message) {
        return server.broadcast(message);
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
    public static int broadcast(net.kyori.adventure.text.@NotNull Component message, @NotNull String permission) {
        return server.broadcast(message, permission);
    }
    // Paper end
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
     * @deprecated in favour of {@link #broadcast(Component, String)}
     */
    @Deprecated // Paper
    public static int broadcast(@NotNull String message, @NotNull String permission) {
        return server.broadcast(message, permission);
    }

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
    @NotNull
    public static OfflinePlayer getOfflinePlayer(@NotNull String name) {
        return server.getOfflinePlayer(name);
    }

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
    public static OfflinePlayer getOfflinePlayerIfCached(@NotNull String name) {
        return server.getOfflinePlayerIfCached(name);
    }
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
    public static OfflinePlayer getOfflinePlayer(@NotNull UUID id) {
        return server.getOfflinePlayer(id);
    }

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
    public static PlayerProfile createPlayerProfile(@Nullable UUID uniqueId, @Nullable String name) {
        return server.createPlayerProfile(uniqueId, name);
    }

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
    public static PlayerProfile createPlayerProfile(@NotNull UUID uniqueId) {
        return server.createPlayerProfile(uniqueId);
    }

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
    public static PlayerProfile createPlayerProfile(@NotNull String name) {
        return server.createPlayerProfile(name);
    }

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
    public static Set<String> getIPBans() {
        return server.getIPBans();
    }

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
    public static void banIP(@NotNull String address) {
        server.banIP(address);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 解禁指定的IP地址.
     * <p>原文：
     * Unbans the specified address from the server.
     *
     * @param address 要解禁的IP地址
     * <p>原文：the IP address to unban
     * @deprecated 参见 {@link #banIP(InetAddress)}
     * <p>原文：see {@link #unbanIP(InetAddress)}
     */
    @Deprecated(since = "1.20.1")
    public static void unbanIP(@NotNull String address) {
        server.unbanIP(address);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 封禁指定的IP地址.
     * <p>原文：
     * Bans the specified address from the server.
     *
     * @param address 要封禁的IP地址
     * <p>原文：the IP address to ban
     */
    public static void banIP(@NotNull InetAddress address) {
        server.banIP(address);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 解禁指定的IP地址.
     * <p>原文：
     * Unbans the specified address from the server.
     *
     * @param address 要解禁的IP地址
     * <p>原文：the IP address to unban
     */
    public static void unbanIP(@NotNull InetAddress address) {
        server.unbanIP(address);
    }

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
    public static Set<OfflinePlayer> getBannedPlayers() {
        return server.getBannedPlayers();
    }

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
    @NotNull
    @Deprecated(since = "1.20.4") // Paper - add BanListType (which has a generic)
    public static <T extends BanList<?>> T getBanList(@NotNull BanList.Type type) {
        return server.getBanList(type);
    }
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
    public static <B extends BanList<E>, E> B getBanList(final io.papermc.paper.ban.@NotNull BanListType<B> type) {
        return server.getBanList(type);
    }
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
    public static Set<OfflinePlayer> getOperators() {
        return server.getOperators();
    }

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
    public static GameMode getDefaultGameMode() {
        return server.getDefaultGameMode();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置新玩家的默认 {@link GameMode}.
     * <p>原文：
     * Sets the default {@link GameMode} for new players.
     *
     * @param mode 新的游戏模式
     * <p>原文：the new game mode
     */
    public static void setDefaultGameMode(@NotNull GameMode mode) {
        server.setDefaultGameMode(mode);
    }

    /**
     * Gets whether the default gamemode is being enforced.
     *
     * @return {@code true} if the default gamemode is being forced, {@code false} otherwise
     */
    public static boolean forcesDefaultGameMode() {
        return server.forcesDefaultGameMode();
    }

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
    public static ConsoleCommandSender getConsoleSender() {
        return server.getConsoleSender();
    }

    // Paper start
    /**
     * Creates a special {@link CommandSender} which redirects command feedback (in the form of chat messages) to the
     * specified listener. The returned sender will have the same effective permissions as {@link #getConsoleSender()}.
     *
     * @param feedback feedback listener
     * @return a command sender
     */
    @NotNull
    public static CommandSender createCommandSender(final @NotNull java.util.function.Consumer<? super Component> feedback) {
        return server.createCommandSender(feedback);
    }
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
    public static File getWorldContainer() {
        return server.getWorldContainer();
    }

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
    public static @NotNull OfflinePlayer @NotNull [] getOfflinePlayers() {
        return server.getOfflinePlayers();
    }

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
    public static Messenger getMessenger() {
        return server.getMessenger();
    }

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
    public static HelpMap getHelpMap() {
        return server.getHelpMap();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以指定类型创建一个空物品栏. 如果type是{@link InventoryType#CHEST}(箱子类型),
     * 新的物品栏的大小则是27, 否则是与物品栏类型对应的正常大小.
     * <br>
     * 如果type是{@link InventoryType#WORKBENCH}, 则这个物品栏不会处理合成配方.
     * 请使用{@link Player#openWorkbench(Location, boolean)}.
     * <br>
     * 同理, 以{@link InventoryType#ENCHANTING}创建的附魔台物品栏也不起作用.
     * 请使用{@link Player#openEnchanting(Location, boolean)}.
     * <p>原文：
     * Creates an empty inventory with the specified type. If the type
     * is {@link InventoryType#CHEST}, the new inventory has a size of 27;
     * otherwise the new inventory has the normal size for its type.
     * <br>
     * {@link InventoryType#WORKBENCH} will not process crafting recipes if
     * created with this method. Use
     * {@link Player#openWorkbench(Location, boolean)} instead.
     * <br>
     * {@link InventoryType#ENCHANTING} will not process {@link ItemStack}s
     * for possible enchanting results. Use
     * {@link Player#openEnchanting(Location, boolean)} instead.
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
    public static Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type) {
        return server.createInventory(owner, type);
    }

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
     * {@link Player#openWorkbench(Location, boolean)} instead.
     * <br>
     * {@link InventoryType#ENCHANTING} will not process {@link ItemStack}s
     * for possible enchanting results. Use
     * {@link Player#openEnchanting(Location, boolean)} instead.
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
    public static Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type, net.kyori.adventure.text.@NotNull Component title) {
        return server.createInventory(owner, type, title);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以指定类型和标题创建一个空物品栏. 如果type是{@link InventoryType#CHEST}(箱子类型),
     * 新的物品栏的大小则是27, 否则是与物品栏类型对应的正常大小.
     * <br>
     * 如果type是{@link InventoryType#WORKBENCH}, 则这个物品栏不会处理合成配方.
     * 请使用{@link Player#openWorkbench(Location, boolean)}.
     * <br>
     * 同理, 以{@link InventoryType#ENCHANTING}创建的附魔台物品栏也不起作用.
     * 请使用{@link Player#openEnchanting(Location, boolean)}.
     * <p>原文：
     * Creates an empty inventory with the specified type and title. If the type
     * is {@link InventoryType#CHEST}, the new inventory has a size of 27;
     * otherwise the new inventory has the normal size for its type.<br>
     * It should be noted that some inventory types do not support titles and
     * may not render with said titles on the Minecraft client.
     * <br>
     * {@link InventoryType#WORKBENCH} will not process crafting recipes if
     * created with this method. Use
     * {@link Player#openWorkbench(Location, boolean)} instead.
     * <br>
     * {@link InventoryType#ENCHANTING} will not process {@link ItemStack}s
     * for possible enchanting results. Use
     * {@link Player#openEnchanting(Location, boolean)} instead.
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
     * @deprecated in favour of {@link #createInventory(InventoryHolder, InventoryType, Component)}
     * @see InventoryType#isCreatable()
     */
    @Deprecated // Paper
    @NotNull
    public static Inventory createInventory(@Nullable InventoryHolder owner, @NotNull InventoryType type, @NotNull String title) {
        return server.createInventory(owner, type, title);
    }

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
    public static Inventory createInventory(@Nullable InventoryHolder owner, int size) throws IllegalArgumentException {
        return server.createInventory(owner, size);
    }

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
    public static Inventory createInventory(@Nullable InventoryHolder owner, int size, net.kyori.adventure.text.@NotNull Component title) throws IllegalArgumentException {
        return server.createInventory(owner, size, title);
    }
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
     * @deprecated in favour of {@link #createInventory(InventoryHolder, InventoryType, Component)}
     */
    @Deprecated // Paper
    @NotNull
    public static Inventory createInventory(@Nullable InventoryHolder owner, int size, @NotNull String title) throws IllegalArgumentException {
        return server.createInventory(owner, size, title);
    }

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
    public static @NotNull Merchant createMerchant(net.kyori.adventure.text.@Nullable Component title) {
        return server.createMerchant(title);
    }
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
     * @deprecated 当与{@link MenuType#MERCHANT}和{@link MenuType.Typed#builder()}一起使用时, 不再需要title参数.
     * <p>原文：in favour of {@link #createMerchant(Component)}. The title parameter is
     * no-longer needed when used with {@link MenuType#MERCHANT} and {@link MenuType.Typed#builder()}
     */
    @NotNull
    @Deprecated // Paper
    public static Merchant createMerchant(@Nullable String title) {
        return server.createMerchant(title);
    }

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
    public static Merchant createMerchant() {
        return server.createMerchant();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在跳过额外更新之前允许的连续邻居更新数量.
     * <p>原文：
     * Gets the amount of consecutive neighbor updates before skipping
     * additional ones.
     *
     * @return 连续邻居更新的数量, 如果值为负数则表示未使用该限制
     * <p>原文：the amount of consecutive neighbor updates, if the value is
     * negative then the limit it's not used
     */
    public static int getMaxChainedNeighborUpdates() {
        return server.getMaxChainedNeighborUpdates();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个区块最大可生成的怪物数量.
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
    public static int getMonsterSpawnLimit() {
        return server.getMonsterSpawnLimit();
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
    public static int getAnimalSpawnLimit() {
        return server.getAnimalSpawnLimit();
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
    public static int getWaterAnimalSpawnLimit() {
        return server.getWaterAnimalSpawnLimit();
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
    public static int getWaterAmbientSpawnLimit() {
        return server.getAmbientSpawnLimit();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个区块最大可生成的水下穴居生物数量.
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
    public static int getWaterUndergroundCreatureSpawnLimit() {
        return server.getWaterUndergroundCreatureSpawnLimit();
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
    public static int getAmbientSpawnLimit() {
        return server.getAmbientSpawnLimit();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个区块最大可生成的指定{@link SpawnCategory 生成类别}生物的数量.
     * <p>
     * <b>注意: {@link SpawnCategory#MISC}类别不予考虑.</b>
     * <p>原文：
     * Gets user-specified limit for number of {@link SpawnCategory} mobs that can spawn in
     * a chunk.
     *
     * <b>Note: the {@link SpawnCategory#MISC} are not consider.</b>
     *
     * @param spawnCategory 生成类别
     * <p>原文：the category spawn
     * @return 该{@link SpawnCategory 生成类别}的生成限制数
     * <p>原文：the {@link SpawnCategory} spawn limit
     */
    public static int getSpawnLimit(@NotNull SpawnCategory spawnCategory) {
        return server.getSpawnLimit(spawnCategory);
    }

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
    public static boolean isPrimaryThread() {
        return server.isPrimaryThread();
    }

    // Paper start
    /**
     * Gets the message that is displayed on the server list.
     *
     * @return the server's MOTD
     */
    @NotNull public static Component motd() {
        return server.motd();
    }

    /**
     * Set the message that is displayed on the server list.
     *
     * @param motd The message to be displayed
     */
    public static void motd(final net.kyori.adventure.text.@NotNull Component motd) {
        server.motd(motd);
    }

    /**
     * Gets the default message that is displayed when the server is stopped.
     *
     * @return the shutdown message
     */
    public static net.kyori.adventure.text.@Nullable Component shutdownMessage() {
        return server.shutdownMessage();
    }
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
    public static String getMotd() {
        return server.getMotd();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置在客户端服务器列表里显示的消息.
     * <p>原文：
     * Set the message that is displayed on the server list.
     *
     * @param motd 服务器motd
     * <p>原文：The message to be displayed
     * @deprecated in favour of {@link #motd(Component)}
     */
    @Deprecated // Paper
    public static void setMotd(@NotNull String motd) {
        server.setMotd(motd);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将发送给客户端的服务器链接.
     * <p>原文：
     * Gets the server links which will be sent to clients
     *
     * @return 服务器的链接
     * <p>原文：the server's links
     */
    @NotNull
    @ApiStatus.Experimental
    public static ServerLinks getServerLinks() {
        return server.getServerLinks();
    }

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
    public static String getShutdownMessage() {
        return server.getShutdownMessage();
    }

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
    public static WarningState getWarningState() {
        return server.getWarningState();
    }

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
    public static ItemFactory getItemFactory() {
        return server.getItemFactory();
    }

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
    public static EntityFactory getEntityFactory() {
        return server.getEntityFactory();
    }

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
    public static ScoreboardManager getScoreboardManager() {
        return server.getScoreboardManager();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取(或创建)一个按名称指定的新{@link Criteria 计分标准}.
     * <p>原文：
     * Get (or create) a new {@link Criteria} by its name.
     *
     * @param name 计分标准名称
     * <p>原文：the criteria name
     * @return 计分标准实例
     * <p>原文：the criteria
     * @see Criteria Criteria for a list of constants
     */
    @NotNull
    public static Criteria getScoreboardCriteria(@NotNull String name) {
        return server.getScoreboardCriteria(name);
    }

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
    public static CachedServerIcon getServerIcon() {
        return server.getServerIcon();
    }

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
     * <p>原文：the file to load from
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
    public static CachedServerIcon loadServerIcon(@NotNull File file) throws IllegalArgumentException, Exception {
        return server.loadServerIcon(file);
    }

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
    public static CachedServerIcon loadServerIcon(@NotNull BufferedImage image) throws IllegalArgumentException, Exception {
        return server.loadServerIcon(image);
    }

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
    public static void setIdleTimeout(int threshold) {
        server.setIdleTimeout(threshold);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取自动踢出闲置玩家的时间.
     * <p>原文：
     * Gets the idle kick timeout.
     *
     * @return the 闲置超时的分钟数
     * <p>原文：the idle timeout in minutes
     */
    public static int getIdleTimeout() {
        return server.getIdleTimeout();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器空闲暂停阈值(秒). 为节省资源, 当没有玩家在线时, 服务器将在此时间后暂停大部分功能.
     * <p>原文：
     * Gets the pause when empty threshold seconds. To save resources, the
     * server will pause most functions after this time if there are no players
     * online.
     *
     * @return 暂停阈值(秒)
     * <p>原文：the pause threshold in seconds
     */
    public static int getPauseWhenEmptyTime() {
        return server.getPauseWhenEmptyTime();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置服务器空闲暂停阈值(秒). 为节省资源, 当没有玩家在线时, 服务器将在此时间后暂停大部分功能.
     * <p>
     * 小于0的值将禁用此设置.
     * <p>原文：
     * Sets the pause when empty threshold seconds. To save resources, the
     * server will pause most functions after this time if there are no players
     * online.
     * <p>
     * A value of less than 1 will disable the setting
     *
     * @param seconds 暂停阈值(秒)
     * <p>原文：the pause threshold in seconds
     */
    public static void setPauseWhenEmptyTime(int seconds) {
        server.setPauseWhenEmptyTime(seconds);
    }

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
    public static ChunkGenerator.ChunkData createChunkData(@NotNull World world) {
        return server.createChunkData(world);
    }

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
    public static BossBar createBossBar(@Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, @NotNull BarFlag... flags) {
        return server.createBossBar(title, color, style, flags);
    }

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
    public static KeyedBossBar createBossBar(@NotNull NamespacedKey key, @Nullable String title, @NotNull BarColor color, @NotNull BarStyle style, @NotNull BarFlag... flags) {
        return server.createBossBar(key, title, color, style, flags);
    }

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
    public static Iterator<KeyedBossBar> getBossBars() {
        return server.getBossBars();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取由此key指定的{@link KeyedBossBar}.
     * <ul>
     *   <li><b>不</b>绑定到{@link org.bukkit.entity.Boss}</li>
     *   <li>
     *     <b>不是</b>由{@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}创建的
     *   </li>
     * </ul>
     * 例如: 使用bossbar命令创建的boss血量条
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
     * @return boss血量条, 如果不存在则返回null
     * <p>原文：bossbar or null if not exists
     */
    @Nullable
    public static KeyedBossBar getBossBar(@NotNull NamespacedKey key) {
        return server.getBossBar(key);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除此key指定的{@link KeyedBossBar}.
     * <ul>
     *   <li><b>不</b>绑定到{@link org.bukkit.entity.Boss}</li>
     *   <li>
     *     <b>不是</b>由{@link #createBossBar(String, BarColor, BarStyle, BarFlag...)}创建的
     *   </li>
     * </ul>
     * 例如: 使用bossbar命令创建的boss血量条
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
    public static boolean removeBossBar(@NotNull NamespacedKey key) {
        return server.removeBossBar(key);
    }

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
    public static Entity getEntity(@NotNull UUID uuid) {
        return server.getEntity(uuid);
    }

    // Paper start
    /**
     * Gets the current server TPS
     * @return current server TPS (1m, 5m, 15m in Paper-Server)
     */
    public static double @NotNull [] getTPS() {
        return server.getTPS();
    }

    /**
     * Get a sample of the servers last tick times (in nanos)
     *
     * @return A sample of the servers last tick times (in nanos)
     */
    public static long @NotNull [] getTickTimes() {
        return server.getTickTimes();
    }

    /**
     * Get the average tick time (in millis)
     *
     * @return Average tick time (in millis)
     */
    public static double getAverageTickTime() {
        return server == null ? 0D : server.getAverageTickTime();
    }
    // Paper end

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
    public static Advancement getAdvancement(@NotNull NamespacedKey key) {
        return server.getAdvancement(key);
    }

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
    public static Iterator<Advancement> advancementIterator() {
        return server.advancementIterator();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为指定的{@link Material 材质}创建一个新的{@link BlockData 方块数据}实例, 所有属性初始化为未指定的默认值.
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
    public static BlockData createBlockData(@NotNull Material material) {
        return server.createBlockData(material);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为指定的{@link Material 材质}创建一个新的{@link BlockData 方块数据}实例, 所有属性初始化为未指定的默认值.
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
    public static BlockData createBlockData(@NotNull Material material, @Nullable Consumer<? super BlockData> consumer) {
        return server.createBlockData(material, consumer);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个从提供的数据字符串解析材质和属性的新{@link BlockData 方块数据}实例.
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
    public static BlockData createBlockData(@NotNull String data) throws IllegalArgumentException {
        return server.createBlockData(data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为指定的{@link Material 材质}创建一个新的{@link BlockData 方块数据}实例, 所有属性初始化为未指定的默认值,
     * 但数据字符串中提供的属性除外.
     * <p>原文：
     * Creates a new {@link BlockData} instance for the specified Material, with
     * all properties initialized to unspecified defaults, except for those
     * provided in data.
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
    public static BlockData createBlockData(@Nullable Material material, @Nullable String data) throws IllegalArgumentException {
        return server.createBlockData(material, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器中已定义的标签. 建议插件使用{@link Tag}中的具体标签, 而不是使用此方法,
     * 因为此方法不保证哪些标签可用, 且由于缺乏缓存可能导致性能下降.
     * <br>
     * 标签将以实现特定的方式搜索, 但期望的路径格式为 namespace/tags/registry/key.
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
     * @param registry 要查看的标签注册表
     * <p>原文：the tag registry to look at
     * @param tag 标签的名称
     * <p>原文：the name of the tag
     * @param clazz 标签条目的类
     * <p>原文：the class of the tag entries
     * @return 标签实例, 如果不存在则返回null
     * <p>原文：the tag or null
     */
    @Nullable
    public static <T extends Keyed> Tag<T> getTag(@NotNull String registry, @NotNull NamespacedKey tag, @NotNull Class<T> clazz) {
        return server.getTag(registry, tag, clazz);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器定义的所有标签.
     * <br>
     * 服务器实现可以仅处理{@link Tag}中指示的注册表.
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
     * @param <T> 标签的类型
     * <p>原文：type of the tag
     * @param registry 要查看的标签注册表
     * <p>原文：the tag registry to look at
     * @param clazz 标签条目的类
     * <p>原文：the class of the tag entries
     * @return 所有已定义的标签
     * <p>原文：all defined tags
     */
    @NotNull
    public static <T extends Keyed> Iterable<Tag<T>> getTags(@NotNull String registry, @NotNull Class<T> clazz) {
        return server.getTags(registry, clazz);
    }

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
    public static LootTable getLootTable(@NotNull NamespacedKey key) {
        return server.getLootTable(key);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用给定的原版选择器选择实体.
     * <br>
     * 不保证选择器格式, 只保证它们与当前Minecraft版本的原版格式匹配.
     * <br>
     * 通常选择器以'@'开头, 除非是选择玩家, 此时可能只是玩家的名称或UUID.
     * <br>
     * 注意在原版中, 使用'@'选择器通常需要提升权限, 但此方法不应检查发送者的此类权限.
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
     * @param sender 要执行的发送者, 必须提供
     * <p>原文：the sender to execute as, must be provided
     * @param selector 选择字符串
     * <p>原文：the selection string
     * @return 选择的实体列表. 列表不会为null, 但不作进一步保证.
     * <p>原文：a list of the selected entities. The list will not be null, but
     * no further guarantees are made.
     * @throws IllegalArgumentException 如果选择器格式错误或参数为null
     * <p>原文：if the selector is malformed in any way
     * or a parameter is null
     */
    @NotNull
    public static List<Entity> selectEntities(@NotNull CommandSender sender, @NotNull String selector) throws IllegalArgumentException {
        return server.selectEntities(sender, selector);
    }

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
    public static StructureManager getStructureManager() {
        return server.getStructureManager();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回给定类的注册表.
     * <br>
     * 如果给定类没有注册表则返回null.
     * <br>
     * 根据实现的不同, 并非{@link Registry}中的每个注册表都会由此方法返回.
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
    @Deprecated(since = "1.20.6")
    public static <T extends Keyed> Registry<T> getRegistry(@NotNull Class<T> tClass) {
        return server.getRegistry(tClass);
    }

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
    public static UnsafeValues getUnsafe() {
        return server.getUnsafe();
    }


    // Paper start
    /**
     * Gets the active {@link org.bukkit.command.CommandMap}
     *
     * @return the active command map
     */
    @NotNull
    public static org.bukkit.command.CommandMap getCommandMap() {
        return server.getCommandMap();
    }

    /**
     * Reload the Permissions in permissions.yml
     */
    public static void reloadPermissions() {
        server.reloadPermissions();
    }

    /**
     * Reload the Command Aliases in commands.yml
     *
     * @return Whether the reload was successful
     */
    public static boolean reloadCommandAliases() {
        return server.reloadCommandAliases();
    }

    /**
     * Checks if player names should be suggested when a command returns {@code null} as
     * their tab completion result.
     *
     * @return true if player names should be suggested
     */
    public static boolean suggestPlayerNamesWhenNullTabCompletions() {
        return server.suggestPlayerNamesWhenNullTabCompletions();
    }

    /**
     * Gets the default no permission message used on the server
     *
     * @return the default message
     * @deprecated use {@link #permissionMessage()}
     */
    @NotNull
    @Deprecated
    public static String getPermissionMessage() {
        return server.getPermissionMessage();
    }

    /**
     * Gets the default no permission message used on the server
     *
     * @return the default message
     */
    @NotNull
    public static Component permissionMessage() {
        return server.permissionMessage();
    }

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
    public static com.destroystokyo.paper.profile.PlayerProfile createProfile(@NotNull UUID uuid) {
        return server.createProfile(uuid);
    }

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
    public static com.destroystokyo.paper.profile.PlayerProfile createProfile(@NotNull String name) {
        return server.createProfile(name);
    }

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
    public static com.destroystokyo.paper.profile.PlayerProfile createProfile(@Nullable UUID uuid, @Nullable String name) {
        return server.createProfile(uuid, name);
    }

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
    public static com.destroystokyo.paper.profile.PlayerProfile createProfileExact(@Nullable UUID uuid, @Nullable String name) {
        return server.createProfileExact(uuid, name);
    }

    public static int getCurrentTick() {
        return server.getCurrentTick();
    }

    /**
     * Checks if the server is in the process of being shutdown.
     *
     * @return true if server is in the process of being shutdown
     */
    public static boolean isStopping() {
        return server.isStopping();
    }

    /**
     * Returns the {@link com.destroystokyo.paper.entity.ai.MobGoals} manager
     *
     * @return the mob goals manager
     */
    @NotNull
    public static com.destroystokyo.paper.entity.ai.MobGoals getMobGoals() {
        return server.getMobGoals();
    }

    /**
     * @return the datapack manager
     */
    @NotNull
    public static io.papermc.paper.datapack.DatapackManager getDatapackManager() {
        return server.getDatapackManager();
    }

    /**
     * Gets the potion brewer.
     *
     * @return the potion brewer
     */
    public static @NotNull org.bukkit.potion.PotionBrewer getPotionBrewer() {
        return server.getPotionBrewer();
    }
    // Paper end

    // Paper start - Folia region threading API
    /**
     * Returns the region task scheduler. The region task scheduler can be used to schedule
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
    public static @NotNull io.papermc.paper.threadedregions.scheduler.RegionScheduler getRegionScheduler() {
        return server.getRegionScheduler();
    }

    /**
     * Returns the async task scheduler. The async task scheduler can be used to schedule tasks
     * that execute asynchronously from the server tick process.
     * @return the async task scheduler
     */
    public static @NotNull io.papermc.paper.threadedregions.scheduler.AsyncScheduler getAsyncScheduler() {
        return server.getAsyncScheduler();
    }

    /**
     * Returns the global region task scheduler. The global task scheduler can be used to schedule
     * tasks to execute on the global region.
     * <p>
     * The global region is responsible for maintaining world day time, world game time, weather cycle,
     * sleep night skipping, executing commands for console, and other misc. tasks that do not belong to any specific region.
     * </p>
     * <p><b>If you do not need/want to make your plugin run on Folia, use {@link #getScheduler()} instead.</b></p>
     * @return the global region scheduler
     */
    public static @NotNull io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler getGlobalRegionScheduler() {
        return server.getGlobalRegionScheduler();
    }

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the chunk at the specified world and block position.
     * @param world Specified world.
     * @param position Specified block position.
     */
    public static boolean isOwnedByCurrentRegion(@NotNull World world, @NotNull io.papermc.paper.math.Position position) {
        return server.isOwnedByCurrentRegion(world, position);
    }

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
    public static boolean isOwnedByCurrentRegion(@NotNull World world, @NotNull io.papermc.paper.math.Position position, int squareRadiusChunks) {
        return server.isOwnedByCurrentRegion(world, position, squareRadiusChunks);
    }

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the chunk at the specified world and block position as included in the specified location.
     * @param location Specified location, must have a non-null world.
     */
    public static boolean isOwnedByCurrentRegion(@NotNull Location location) {
        return server.isOwnedByCurrentRegion(location);
    }

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
    public static boolean isOwnedByCurrentRegion(@NotNull Location location, int squareRadiusChunks) {
        return server.isOwnedByCurrentRegion(location, squareRadiusChunks);
    }

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the chunk at the specified block position.
     * @param block Specified block position.
     */
    public static boolean isOwnedByCurrentRegion(@NotNull org.bukkit.block.Block block) {
        return server.isOwnedByCurrentRegion(block.getLocation());
    }

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the chunk at the specified world and chunk position.
     * @param world Specified world.
     * @param chunkX Specified x-coordinate of the chunk position.
     * @param chunkZ Specified z-coordinate of the chunk position.
     */
    public static boolean isOwnedByCurrentRegion(@NotNull World world, int chunkX, int chunkZ) {
        return server.isOwnedByCurrentRegion(world, chunkX, chunkZ);
    }

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
    public static boolean isOwnedByCurrentRegion(@NotNull World world, int chunkX, int chunkZ, int squareRadiusChunks) {
        return server.isOwnedByCurrentRegion(world, chunkX, chunkZ, squareRadiusChunks);
    }

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
    public static boolean isOwnedByCurrentRegion(@NotNull World world, int minChunkX, int minChunkZ, int maxChunkX, int maxChunkZ) {
        return server.isOwnedByCurrentRegion(world, minChunkX, minChunkZ, maxChunkX, maxChunkZ);
    }

    /**
     * Returns whether the current thread is ticking a region and that the region being ticked
     * owns the specified entity. Note that this function is the only appropriate method of checking
     * for ownership of an entity, as retrieving the entity's location is undefined unless the entity is owned
     * by the current region.
     * @param entity Specified entity.
     */
    public static boolean isOwnedByCurrentRegion(@NotNull Entity entity) {
        return server.isOwnedByCurrentRegion(entity);
    }

    /**
     * Returns whether the current thread is ticking the global region.
     * @see io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler
     */
    public static boolean isGlobalTickThread() {
        return server.isGlobalTickThread();
    }
    // Paper end - Folia region threading API

    /**
     * @deprecated All methods on this class have been deprecated, see the individual methods for replacements.
     */
    @Deprecated(since = "1.21.4", forRemoval = true)
    @NotNull
    public static Server.Spigot spigot() {
        return server.spigot();
    }

    /**
     * Restarts the server. If the server administrator has not configured restarting, the server will stop.
     */
    public static void restart() {
        server.restart();
    }
}
