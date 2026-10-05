package org.bukkit.event.player;

import java.net.InetAddress;
import java.util.UUID;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Warning;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家尝试登录服务器事件.
 * <p>原文：
 * Stores details for players attempting to log in
 * <p>
 * When this event is fired, the player's locale is not
 * available. Therefore, any translatable component will be
 * rendered with the default locale, {@link java.util.Locale#US}.
 * <p>
 * Consider rendering any translatable yourself with {@link net.kyori.adventure.translation.GlobalTranslator#render}
 * if the client's language is known.
 *
 * @deprecated 这个事件使登录线程同步;
 * 建议使用{@link AsyncPlayerPreLoginEvent}.
 * <p>原文：This event causes synchronization from the login thread; {@link
 *     AsyncPlayerPreLoginEvent} is preferred to keep the secondary threads
 *     asynchronous.
 */
@Deprecated(since = "1.3.2")
@Warning(reason = "This event causes a login thread to synchronize with the main thread")
public class PlayerPreLoginEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final String name;
    private final InetAddress ipAddress;
    private final UUID uniqueId;
    private Result result;
    private Component message;

    @ApiStatus.Internal
    @Deprecated(since = "1.7.5", forRemoval = true)
    public PlayerPreLoginEvent(@NotNull final String name, @NotNull final InetAddress ipAddress) {
        this(name, ipAddress, null);
    }

    @ApiStatus.Internal
    public PlayerPreLoginEvent(@NotNull final String name, @NotNull final InetAddress ipAddress, @NotNull final UUID uniqueId) {
        this.result = Result.ALLOWED;
        this.message = Component.empty();
        this.name = name;
        this.ipAddress = ipAddress;
        this.uniqueId = uniqueId;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前事件的状态.
     * <p>原文：
     * Gets the current result of the login, as an enum
     *
     * @return 当前事件的状态
     * <p>原文：Current Result of the login
     */
    @NotNull
    public Result getResult() {
        return this.result;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前事件的状态.
     * <p>原文：
     * Sets the new result of the login, as an enum
     *
     * @param result 当前事件的状态
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
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果getResult() !=Result.ALLOWED (拒绝玩家进入), 那么获取踢出的信息.
     * <p>原文：
     * Gets the current kick message that will be used when the outcome is not allowed
     *
     * @return result 踢出的信息
     * <p>原文：Current kick message
     * @deprecated in favour of {@link #kickMessage()}
     */
    @Deprecated // Paper
    @NotNull
    public String getKickMessage() {
        return LegacyComponentSerializer.legacySection().serialize(this.message);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置如果getResult() !=Result.ALLOWED (拒绝玩家进入), 那么将要踢出的消息.
     * <p>原文：
     * Sets the kick message to display when the outcome is not allowed
     *
     * @param message 设置踢出的消息
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
     * 不允许玩家登录.
     * <p>原文：
     * Disallows the player from logging in, with the given reason
     *
     * @param result 结果
     * <p>原文：New result for disallowing the player
     * @param message 理由
     * <p>原文：Kick message to display to the user
     * @deprecated in favour of {@link #disallow(org.bukkit.event.player.PlayerPreLoginEvent.Result, Component)}
     */
    @Deprecated // Paper
    public void disallow(@NotNull final Result result, @NotNull final String message) {
        this.result = result;
        this.message = LegacyComponentSerializer.legacySection().deserialize(message);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此玩家的名字.
     * <p>原文：
     * Gets the player's name.
     *
     * @return 玩家名字
     * <p>原文：the player's name
     */
    @NotNull
    public String getName() {
        return this.name;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此玩家的IP地址.
     * <p>原文：
     * Gets the player IP address.
     *
     * @return 玩家IP地址
     * <p>原文：The IP address
     */
    @NotNull
    public InetAddress getAddress() {
        return this.ipAddress;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此玩家的唯一标识.
     * <p>原文：
     * Gets the player's unique ID.
     *
     * @return 唯一标识
     * <p>原文：The unique ID
     */
    @NotNull
    public UUID getUniqueId() {
        return this.uniqueId;
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
     * 踢出玩家的原因.
     * <p>原文：
     * Basic kick reasons for communicating to plugins
     */
    public enum Result {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 允许玩家进入服务器.
         * <p>原文：
         * The player is allowed to log in
         */
        ALLOWED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于服务器已满,不允许进入.
         * <p>原文：
         * The player is not allowed to log in, due to the server being full
         */
        KICK_FULL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于玩家被服务器封禁,不允许进入.
         * <p>原文：
         * The player is not allowed to log in, due to them being banned
         */
        KICK_BANNED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 由于玩家不在白名单内,不允许进入.
         * <p>原文：
         * The player is not allowed to log in, due to them not being on the
         * white list
         */
        KICK_WHITELIST,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 其他原因,就是不允许进入.
         * <p>原文：
         * The player is not allowed to log in, for reasons undefined
         */
        KICK_OTHER
    }
}
