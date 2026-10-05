package org.bukkit.event.player;

import java.net.InetAddress;
import io.papermc.paper.event.connection.PlayerConnectionValidateLoginEvent;
import org.bukkit.Warning;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家尝试登录的事件.
 * <br>
 * 请注意本事件在玩家初始化的早期阶段被触发. 建议与玩家实体有关的大部分选项在{@link PlayerJoinEvent}后应用.
 * <p>原文：
 * Stores details for players attempting to log in.
 * <br>
 * Note that this event is called <i>early</i> in the player initialization
 * process. It is recommended that most options involving the Player
 * <i>entity</i> be postponed to the {@link PlayerJoinEvent} instead.
 *
 * @deprecated Use {@link PlayerConnectionValidateLoginEvent} to handle pre-login logic
 * (e.g. authentication or ban checks), or {@link io.papermc.paper.event.player.PlayerServerFullCheckEvent} to allow
 * players to bypass the server's maximum player limit.
 * Minecraft triggers this twice internally, using this event skips one of the validation checks done by the server.
 * Additionally, this event causes the full player entity to be created much earlier than it would be in Vanilla,
 * leaving it with mostly dysfunctional methods and state.
 */
@Warning(reason = "Listening to this event causes the player to be created early.")
@Deprecated(since = "1.21.6")
public class PlayerLoginEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final String hostname;
    private final InetAddress address;
    private final InetAddress realAddress;
    private Result result = Result.ALLOWED;
    private Component message = Component.empty();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 这个构造器默认踢出消息为空、登录状态为ALLOWED.
     *
     * @param player 这个事件的{@link Player 玩家}
     * @param hostname 用于连接服务器的主机名
     * @param address 玩家的IP地址
     * @param realAddress 真实的、未被伪造的连接地址
     */
    @ApiStatus.Internal
    public PlayerLoginEvent(@NotNull final Player player, @NotNull final String hostname, @NotNull final InetAddress address, final @NotNull InetAddress realAddress) {
        super(player);
        this.hostname = hostname;
        this.address = address;
        this.realAddress = realAddress;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 这个构造器默认踢出消息为空、登录状态为ALLOWED.
     *
     * @param player 这个事件的{@link Player 玩家}
     * @param hostname 用于连接服务器的主机名
     * @param address 玩家的IP地址
     */
    @ApiStatus.Internal
    public PlayerLoginEvent(@NotNull final Player player, @NotNull final String hostname, @NotNull final InetAddress address) {
        this(player, hostname, address, address);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 此构造器预配置事件的结果和消息.
     *
     * @param player 这个事件的{@link Player 玩家}
     * @param hostname 用于连接服务器的主机名
     * @param address 玩家的IP地址
     * @param result 事件的登录状态结果
     * @param message 拒绝登录时显示的消息
     * @param realAddress 真实的、未被伪造的连接地址
     */
    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public PlayerLoginEvent(@NotNull final Player player, @NotNull String hostname, @NotNull final InetAddress address, @NotNull final Result result, @NotNull final String message, @NotNull final InetAddress realAddress) {
        this(player, hostname, address, realAddress);
        this.result = result;
        this.message = LegacyComponentSerializer.legacySection().deserialize(message);
    }

    @ApiStatus.Internal
    public PlayerLoginEvent(@NotNull final Player player, @NotNull String hostname, @NotNull final InetAddress address, @NotNull final Result result, @NotNull final net.kyori.adventure.text.Component message, @NotNull final InetAddress realAddress) {
        this(player, hostname, address, realAddress);
        this.result = result;
        this.message = message;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家用于连接服务器的主机名，如果未知则为空
     * <p>原文：
     * Gets the hostname that the player used to connect to the server, or
     * blank if unknown
     *
     * @return 主机名
     * <p>原文：The hostname
     */
    @NotNull
    public String getHostname() {
        return this.hostname;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家的{@link InetAddress IP地址}.
     * 这个方法是在此事件期间使用 <code>player.getAddress()</code> 为null的解决办法
     * <p>原文：
     * Gets the {@link InetAddress} for the Player associated with this event.
     * This method is provided as a workaround for player.getAddress()
     * returning {@code null} during PlayerLoginEvent.
     *
     * @return 玩家的IP地址。为兼容旧版，这可能是null
     * <p>原文：The address for this player. For legacy compatibility, this may
     *     be {@code null}.
     */
    @NotNull
    public InetAddress getAddress() {
        return this.address;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此玩家的连接地址，无论其是否被伪造。
     * <p>原文：
     * Gets the connection address of this player, regardless of whether it has
     * been spoofed or not.
     *
     * @return 玩家的连接地址
     * <p>原文：the player's connection address
     * @see #getAddress()
     */
    @NotNull
    public InetAddress getRealAddress() {
        return this.realAddress;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前的登录状态.
     * <p>原文：
     * Gets the current result of the login, as an enum
     *
     * @return 登录状态
     * <p>原文：Current Result of the login
     */
    @NotNull
    public Result getResult() {
        return this.result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置登录的状态.
     * <p>原文：
     * Sets the new result of the login, as an enum
     *
     * @param result 登录状态
     * <p>原文：New result to set
     */
    public void setResult(@NotNull final Result result) {
        this.result = result;
    }

    /**
     * Gets the current kick message that will be used when the outcome is not allowed
     *
     * @return Current kick message
     */
    public @NotNull Component kickMessage() {
        return this.message;
    }

    /**
     * Sets the kick message to display when the outcome is not allowed
     *
     * @param message New kick message
     */
    public void kickMessage(@NotNull Component message) {
        this.message = message;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果<code>getResult() != Result.ALLOWED</code>,获取将使用的踢出消息
     * <p>原文：
     * Gets the current kick message that will be used when the outcome is not allowed
     *
     * @return 踢出消息
     * <p>原文：Current kick message
     * @deprecated in favour of {@link #kickMessage()}
     */
    @NotNull
    @Deprecated // Paper
    public String getKickMessage() {
        return LegacyComponentSerializer.legacySection().serialize(this.message);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果<code>getResult() != Result.ALLOWED</code>,设置要显示的踢出消息
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
     * 允许玩家登录
     * <p>原文：
     * Allows the player to log in
     */
    public void allow() {
        this.result = Result.ALLOWED;
        this.message = Component.empty();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以给定的理由不允许玩家登录
     * <p>原文：
     * Disallows the player from logging in, with the given reason
     *
     * @param result 不允许玩家登录的理由
     * <p>原文：New result for disallowing the player
     * @param message 给用户显示的踢出消息
     * <p>原文：Kick message to display to the user
     * @deprecated in favour of {@link #disallow(Result, Component)}
     */
    @Deprecated
    public void disallow(@NotNull final Result result, @NotNull final String message) {
        this.result = result;
        this.message = LegacyComponentSerializer.legacySection().deserialize(message);
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
     * 踢出玩家的理由
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
         * 由于服务器已满,不允许登录
         * <p>原文：
         * The player is not allowed to log in, due to the server being full
         */
        KICK_FULL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于玩家被封禁，不允许登录
         * <p>原文：
         * The player is not allowed to log in, due to them being banned
         */
        KICK_BANNED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于玩家不在白名单上而不允许登录
         * white list
         * <p>原文：
         * The player is not allowed to log in, due to them not being on the
         * white list
         */
        KICK_WHITELIST,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于其他原因而不允许登录
         * <p>原文：
         * The player is not allowed to log in, for reasons undefined
         */
        KICK_OTHER
    }
}
