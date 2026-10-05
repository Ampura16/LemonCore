package org.bukkit.event.server;

import com.google.common.base.Preconditions;
import java.net.InetAddress;
import java.util.Iterator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.UndefinedNullability;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.util.CachedServerIcon;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当收到MOTD请求时被调用。显示的玩家会被检查并会在这个事件里被{@link #iterator() iterating}移出。
 * <br>
 * <b>注意:</b> 如果 {@link Bukkit#getHideOnlinePlayers()} 为 true,
 * 则在 {@link #iterator()} 内的玩家不会被展示到 motd 信息上.
 * <p>原文：
 * Called when a server list ping is coming in. Displayed players can be
 * checked and removed by {@link #iterator() iterating} over this event.
 * <br>
 * <b>Note:</b> The players in {@link #iterator()} will not be shown in the
 * server info if {@link Bukkit#getHideOnlinePlayers()} is {@code true}.
 */
public class ServerListPingEvent extends ServerEvent implements Iterable<Player> {

    private static final int MAGIC_PLAYER_COUNT = Integer.MIN_VALUE;

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final String hostname;
    private final InetAddress address;
    private final int numPlayers;
    private Component motd;
    private int maxPlayers;

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public ServerListPingEvent(@NotNull final String hostname, @NotNull final InetAddress address, @NotNull final String motd, final int numPlayers, final int maxPlayers) {
        super(true);
        Preconditions.checkArgument(numPlayers >= 0, "Cannot have negative number of players online", numPlayers);
        this.hostname = hostname;
        this.address = address;
        this.motd = LegacyComponentSerializer.legacySection().deserialize(motd);
        this.numPlayers = numPlayers;
        this.maxPlayers = maxPlayers;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 这个构造器用于实现提供{@link #iterator()}方法，例如提供{@link #getNumPlayers()}玩家总数.
     *
     * @param hostname 连接服务器时使用的主机名
     * @param address 请求者的地址
     * @param motd 每日信息
     * @param maxPlayers 最大玩家数量
     */
    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    protected ServerListPingEvent(@NotNull final String hostname, @NotNull final InetAddress address, @NotNull final String motd, final int maxPlayers) {
        super(true);
        this.numPlayers = MAGIC_PLAYER_COUNT;
        this.hostname = hostname;
        this.address = address;
        this.motd = LegacyComponentSerializer.legacySection().deserialize(motd);
        this.maxPlayers = maxPlayers;
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public ServerListPingEvent(@NotNull final InetAddress address, @NotNull final Component motd, final int numPlayers, final int maxPlayers) {
        this("", address, motd, numPlayers, maxPlayers);
    }

    @ApiStatus.Internal
    public ServerListPingEvent(@NotNull final String hostname, @NotNull final InetAddress address, @NotNull final Component motd, final int numPlayers, final int maxPlayers) {
        super(true);
        this.hostname = hostname;
        this.address = address;
        this.motd = motd;
        this.numPlayers = numPlayers;
        this.maxPlayers = maxPlayers;
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    protected ServerListPingEvent(@NotNull final InetAddress address, @NotNull final Component motd, final int maxPlayers) {
        this("", address, motd, maxPlayers);
    }

    /*
     * This constructor is intended for implementations that provide the
     * {@link #iterator()} method, thus provided the {@link #getNumPlayers()}
     * count.
     */
    @ApiStatus.Internal
    protected ServerListPingEvent(final @NotNull String hostname, final @NotNull InetAddress address, final @NotNull Component motd, final int maxPlayers) {
        this.numPlayers = MAGIC_PLAYER_COUNT;
        this.hostname = hostname;
        this.address = address;
        this.motd = motd;
        this.maxPlayers = maxPlayers;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家连接服务器时使用的主机名，若未知则为空字符串。
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
     * 获取请求来源地址.
     * <p>原文：
     * Get the address the ping is coming from.
     *
     * @return 地址
     * <p>原文：the address
     */
    @NotNull
    public InetAddress getAddress() {
        return this.address;
    }

    /**
     * Get the message of the day message.
     *
     * @return the message of the day
     */
    public @NotNull Component motd() {
        return this.motd;
    }

    /**
     * Change the message of the day message.
     *
     * @param motd the message of the day
     */
    public void motd(@NotNull Component motd) {
        this.motd = motd;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取每日信息.
     * <p>原文：
     * Get the message of the day message.
     *
     * @return 每日信息
     * <p>原文：the message of the day
     * @deprecated in favour of {@link #motd()}
     */
    @NotNull
    @Deprecated
    public String getMotd() {
        return LegacyComponentSerializer.legacySection().serialize(this.motd);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 更改每日信息.
     * <p>原文：
     * Change the message of the day message.
     *
     * @param motd 每日信息
     * <p>原文：the message of the day
     * @deprecated in favour of {@link #motd(Component)}
     */
    @Deprecated
    public void setMotd(@NotNull String motd) {
        this.motd = LegacyComponentSerializer.legacySection().deserialize(motd);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家数量.
     * <p>原文：
     * Get the number of players sent.
     *
     * @return 玩家数量
     * <p>原文：the number of players
     */
    public int getNumPlayers() {
        int numPlayers = this.numPlayers;
        if (numPlayers == MAGIC_PLAYER_COUNT) {
            numPlayers = 0;
            for (@SuppressWarnings("unused") final Player player : this) {
                numPlayers++;
            }
        }
        return numPlayers;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取最大玩家数量.
     * <p>原文：
     * Get the maximum number of players sent.
     *
     * @return 最大玩家数量
     * <p>原文：the maximum number of players
     */
    public int getMaxPlayers() {
        return this.maxPlayers;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置最大玩家数量.
     * <p>原文：
     * Set the maximum number of players sent.
     *
     * @param maxPlayers 最大玩家数量
     * <p>原文：the maximum number of player
     */
    public void setMaxPlayers(int maxPlayers) {
        this.maxPlayers = maxPlayers;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是否需要向客户端发送聊天预览。
     * <p>原文：
     * Gets whether the server needs to send a preview of the chat to the
     * client.
     *
     * @return 如果聊天预览已启用则为true，否则为false
     * <p>原文：{@code true} if chat preview is enabled, {@code false} otherwise
     * @deprecated 聊天预览已被移除
     * <p>原文：chat previews have been removed
     */
    @Contract("-> false")
    @Deprecated(since = "1.19.3", forRemoval = true)
    public boolean shouldSendChatPreviews() {
        return false;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置发送给客户端的服务器图标.
     * <p>原文：
     * Sets the server-icon sent to the client.
     *
     * @param icon 发送给客户端的图标
     * <p>原文：the icon to send to the client
     * @throws IllegalArgumentException 如果{@link CachedServerIcon}在这个事件中未被调用者创建则抛出错误；一些接口可能会接受null
     * <p>原文：if the {@link CachedServerIcon} is not
     *     created by the caller of this event; {@code null} may be accepted for some
     *     implementations
     * @throws UnsupportedOperationException 如果这个事件的调用者不支持设置这个服务器图标则抛出错误
     * <p>原文：if the caller of this event does
     *     not support setting the server icon
     */
    public void setServerIcon(@UndefinedNullability("implementation dependent") CachedServerIcon icon) throws IllegalArgumentException, UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * {@inheritDoc}
     * <p>
     * 调用{@link Iterator#remove()}方法将会强制部分玩家不会在玩家列表里显示，减小{@link #getNumPlayers()}返回的大小，并且不会再被任何一个新的迭代器返回.
     * <br>
     * <b>注意:</b> 如果 {@link Bukkit#getHideOnlinePlayers()} 为 true,
     * 则在 {@link #iterator()} 内的玩家不会被展示到 motd 信息上.
     * <p>原文：
     * {@inheritDoc}
     * <p>
     * Calling the {@link Iterator#remove()} method will force that particular
     * player to not be displayed on the player list, decrease the size
     * returned by {@link #getNumPlayers()}, and will not be returned again by
     * any new iterator.
     * <br>
     * <b>Note:</b> The players here will not be shown in the server info if
     * {@link Bukkit#getHideOnlinePlayers()} is {@code true}.
     *
     * @throws UnsupportedOperationException 如果这个事件的调用者不支持移除玩家则会抛出错误。
     * <p>原文：if the caller of this event does
     *     not support removing players
     * @deprecated the Iterable interface will be removed at some point
     */
    @NotNull
    @Override
    @Deprecated(forRemoval = true, since = "1.20.6")
    public Iterator<Player> iterator() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
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
}
