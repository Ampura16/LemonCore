package org.bukkit.event.player;

import java.net.InetAddress;
import java.util.UUID;
import com.destroystokyo.paper.profile.PlayerProfile;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import io.papermc.paper.connection.PlayerLoginConnection;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 存储尝试登录的玩家的详细信息，玩家尝试登录服务器的事件.
 * <p>
 * 这个事件是异步的，不在主线程上执行.
 * <p>原文：
 * Stores details for players attempting to log in.
 * <p>
 * This event is asynchronous, and not run using main thread.
 * <p>
 * This event is fired after the server has successfully completed
 * Mojang authentication. The event is still fired if the server is in offline mode.
 * <p>
 * When this event is fired, the player's locale is not
 * available. Therefore, any translatable component will be
 * rendered with the default locale, {@link java.util.Locale#US}.
 * <p>
 * Consider rendering any translatable yourself with {@link net.kyori.adventure.translation.GlobalTranslator#render}
 * if the client's language is known.
 */
public class AsyncPlayerPreLoginEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final InetAddress ipAddress;
    private final InetAddress rawAddress;
    private final String hostname;
    private final boolean transferred;
    private Result result;
    private Component message;
    private PlayerProfile profile;
    private final PlayerLoginConnection playerLoginConnection;

    @ApiStatus.Internal
    @Deprecated(since = "1.7.5", forRemoval = true)
    public AsyncPlayerPreLoginEvent(@NotNull final String name, @NotNull final InetAddress ipAddress) {
        this(name, ipAddress, null);
    }

    @ApiStatus.Internal
    @Deprecated(since = "1.20.5", forRemoval = true)
    public AsyncPlayerPreLoginEvent(@NotNull final String name, @NotNull final InetAddress ipAddress, @NotNull final UUID uniqueId) {
        this(name, ipAddress, uniqueId, false);
    }

    @ApiStatus.Internal
    public AsyncPlayerPreLoginEvent(@NotNull final String name, @NotNull final InetAddress ipAddress, @NotNull final UUID uniqueId, boolean transferred) {
        this(name, ipAddress, uniqueId, transferred, org.bukkit.Bukkit.createProfile(uniqueId, name));
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public AsyncPlayerPreLoginEvent(@NotNull final String name, @NotNull final InetAddress ipAddress, @NotNull final UUID uniqueId, boolean transferred, @NotNull com.destroystokyo.paper.profile.PlayerProfile profile) {
        this(name, ipAddress, ipAddress, uniqueId, transferred, profile);
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public AsyncPlayerPreLoginEvent(@NotNull final String name, @NotNull final InetAddress ipAddress, @NotNull final InetAddress rawAddress, @NotNull final UUID uniqueId, boolean transferred, @NotNull com.destroystokyo.paper.profile.PlayerProfile profile) {
        this(name, ipAddress, rawAddress, uniqueId, transferred, profile, "", null);
    }

    @ApiStatus.Internal
    public AsyncPlayerPreLoginEvent(@NotNull final String name, @NotNull final InetAddress ipAddress, @NotNull final InetAddress rawAddress, @NotNull final UUID uniqueId, boolean transferred, @NotNull com.destroystokyo.paper.profile.PlayerProfile profile, @NotNull String hostname, final PlayerLoginConnection playerLoginConnection) {
        super(true);
        this.result = Result.ALLOWED;
        this.message = Component.empty();
        this.profile = profile;
        this.ipAddress = ipAddress;
        this.rawAddress = rawAddress;
        this.hostname = hostname;
        this.transferred = transferred;
        this.playerLoginConnection = playerLoginConnection;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取登录的状态.
     * <p>原文：
     * Gets the current result of the login, as an enum
     *
     * @return 登录的状态
     * <p>原文：Current Result of the login
     */
    @NotNull
    public Result getLoginResult() {
        return this.result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取登录的状态.
     * <p>原文：
     * Gets the current result of the login, as an enum
     *
     * @return 登录的状态
     * <p>原文：Current Result of the login
     * @see #getLoginResult()
     * @deprecated 这个方法使用了来自 {@link PlayerPreLoginEvent} 的已弃用的枚举
     * <p>原文：This method uses a deprecated enum from {@link
     *     PlayerPreLoginEvent}
     */
    @Deprecated(since = "1.3.2")
    @NotNull
    public PlayerPreLoginEvent.Result getResult() {
        return this.result == null ? null : this.result.old(); // todo a lot of nullability issues in this class + player profile
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置登录的状态.
     * <p>原文：
     * Sets the new result of the login, as an enum
     *
     * @param result 状态
     * <p>原文：New result to set
     */
    public void setLoginResult(@NotNull final Result result) {
        this.result = result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置登录的状态.
     * <p>原文：
     * Sets the new result of the login, as an enum
     *
     * @param result 状态
     * <p>原文：New result to set
     * @see #setLoginResult(Result)
     * @deprecated 这个方法使用了来自 {@link PlayerPreLoginEvent} 的已弃用的枚举
     * <p>原文：This method uses a deprecated enum from {@link
     *     PlayerPreLoginEvent}
     */
    @Deprecated(since = "1.3.2")
    public void setResult(@NotNull final PlayerPreLoginEvent.Result result) {
        this.result = result == null ? null : Result.valueOf(result.name());
    }

    /**
     * Gets the current kick message that will be used when the outcome is not allowed
     *
     * @return Current kick message
     */
    @NotNull
    public Component kickMessage() {
        return this.message;
    }

    /**
     * Sets the kick message to display when the outcome is not allowed
     *
     * @param message New kick message
     */
    public void kickMessage(@NotNull final Component message) {
        this.message = message;
    }

    /**
     * Disallows the player from logging in, with the given reason
     *
     * @param result New result for disallowing the player
     * @param message Kick message to display to the user
     */
    public void disallow(@NotNull final Result result, @NotNull final Component message) {
        this.result = result;
        this.message = message;
    }

    /**
     * Disallows the player from logging in, with the given reason
     *
     * @param result New result for disallowing the player
     * @param message Kick message to display to the user
     * @deprecated This method uses a deprecated enum from {@link
     *     PlayerPreLoginEvent}
     * @see #disallow(Result, String)
     */
    @Deprecated
    public void disallow(@NotNull final PlayerPreLoginEvent.Result result, @NotNull final net.kyori.adventure.text.Component message) {
        this.result = result == null ? null : Result.valueOf(result.name());
        this.message = message;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将要使用的踢出消息，如果getResult() != Result.ALLOWED.
     * <p>原文：
     * Gets the current kick message that will be used when the outcome is not allowed
     *
     * @return 踢出消息
     * <p>原文：Current kick message
     * @deprecated in favour of {@link #kickMessage()}
     */
    @NotNull
    @Deprecated
    public String getKickMessage() {
        return LegacyComponentSerializer.legacySection().serialize(this.message);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置要显示的踢出消息，如果getResult() != Result.ALLOWED.
     * <p>原文：
     * Sets the kick message to display when the outcome is not allowed
     *
     * @param message 踢出消息
     * <p>原文：New kick message
     * @deprecated in favour of {@link #kickMessage(Component)}
     */
    @Deprecated
    public void setKickMessage(@NotNull final String message) {
        this.message = LegacyComponentSerializer.legacySection().deserialize(message);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许玩家登录.
     * <p>原文：
     * Allows the player to log in
     */
    public void allow() {
        this.result = Result.ALLOWED;
        this.message = Component.empty();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以给定的理由不允许玩家登录.
     * <p>原文：
     * Disallows the player from logging in, with the given reason
     *
     * @param result 不允许玩家登录的理由
     * <p>原文：New result for disallowing the player
     * @param message 给用户显示的踢出消息
     * <p>原文：Kick message to display to the user
     * @deprecated in favour of {@link #disallow(org.bukkit.event.player.AsyncPlayerPreLoginEvent.Result, Component)}
     */
    @Deprecated
    public void disallow(@NotNull final Result result, @NotNull final String message) {
        this.result = result;
        this.message = LegacyComponentSerializer.legacySection().deserialize(message);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以给定的理由不允许玩家登录.
     * <p>原文：
     * Disallows the player from logging in, with the given reason
     *
     * @param result 不允许玩家登录的理由
     * <p>原文：New result for disallowing the player
     * @param message 给用户显示的踢出消息
     * <p>原文：Kick message to display to the user
     * @see #disallow(Result, String)
     * @deprecated 这个方法使用了来自 {@link PlayerPreLoginEvent} 的已弃用的枚举
     * <p>原文：This method uses a deprecated enum from {@link
     *     PlayerPreLoginEvent}
     */
    @Deprecated(since = "1.3.2")
    public void disallow(@NotNull final PlayerPreLoginEvent.Result result, @NotNull final String message) {
        this.result = result == null ? null : Result.valueOf(result.name());
        this.message = LegacyComponentSerializer.legacySection().deserialize(message);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家的名字.
     * <p>原文：
     * Gets the player's name.
     *
     * @return 玩家名
     * <p>原文：the player's name
     */
    @NotNull
    public String getName() {
        return this.profile.getName();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家的IP地址.
     * <p>原文：
     * Gets the player IP address.
     *
     * @return IP地址
     * <p>原文：The IP address
     */
    @NotNull
    public InetAddress getAddress() {
        return this.ipAddress;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家的唯一标识.
     * <p>原文：
     * Gets the player's unique ID.
     *
     * @return The unique ID
     */
    @NotNull
    public UUID getUniqueId() {
        return this.profile.getId();
    }

    /**
     * Gets the PlayerProfile of the player logging in
     * @return The Profile
     */
    @NotNull
    public com.destroystokyo.paper.profile.PlayerProfile getPlayerProfile() {
        return this.profile;
    }

    /**
     * Changes the PlayerProfile the player will login as
     * @param profile The profile to use
     */
    public void setPlayerProfile(@NotNull com.destroystokyo.paper.profile.PlayerProfile profile) {
        this.profile = profile;
    }

    /**
     * Gets the raw address of the player logging in
     * @return The address
     */
    @NotNull
    public InetAddress getRawAddress() {
        return this.rawAddress;
    }

    /**
     * Gets the hostname that the player used to connect to the server, or
     * blank if unknown
     *
     * @return The hostname
     */
    @NotNull
    public String getHostname() {
        return this.hostname;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此连接是否从另一个服务器转移而来.
     * <p>原文：
     * Gets if this connection has been transferred from another server.
     *
     * @return 如果连接已被转移则返回 true
     * <p>原文：{@code true} if the connection has been transferred
     */
    public boolean isTransferred() {
        return this.transferred;
    }

    /**
     * Gets the connection for the player logging in.
     * @return connection
     */
    @ApiStatus.Experimental
    @NotNull
    public PlayerLoginConnection getConnection() {
        return playerLoginConnection;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 基本的踢出理由，为了插件间的通信
     * <p>原文：
     * Basic kick reasons for communicating to plugins
     */
    public enum Result {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家被允许登录
         * <p>原文：
         * The player is allowed to log in
         */
        ALLOWED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于服务器已满而不允许登录
         * <p>原文：
         * The player is not allowed to log in, due to the server being full
         */
        KICK_FULL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于该玩家被封禁而不允许登录
         * <p>原文：
         * The player is not allowed to log in, due to them being banned
         */
        KICK_BANNED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于玩家不在白名单上而不允许登录
         * <p>原文：
         * The player is not allowed to log in, due to them not being on the
         * white list
         */
        KICK_WHITELIST,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于未定义的理由而不允许登录
         * <p>原文：
         * The player is not allowed to log in, for reasons undefined
         */
        KICK_OTHER;

        @Deprecated(since = "1.3.2")
        @NotNull
        private PlayerPreLoginEvent.Result old() {
            return PlayerPreLoginEvent.Result.valueOf(name());
        }
    }
}
