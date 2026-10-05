package com.destroystokyo.paper.event.server;

import static java.util.Objects.requireNonNull;

import com.destroystokyo.paper.network.StatusClient;
import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import com.google.common.base.Preconditions;
import io.papermc.paper.util.TransformingRandomAccessList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.profile.PlayerTextures;
import org.bukkit.util.CachedServerIcon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link ServerListPingEvent} 的扩展版本，可完全控制发送给客户端的响应。
 * <p>原文：
 * Extended version of {@link ServerListPingEvent} that allows full control
 * of the response sent to the client.
 */
public class PaperServerListPingEvent extends ServerListPingEvent implements Cancellable {

    @NotNull private final StatusClient client;

    private int numPlayers;
    private boolean hidePlayers;
    @NotNull private final List<ListedPlayerInfo> listedPlayers = new ArrayList<>();
    @NotNull private final TransformingRandomAccessList<ListedPlayerInfo, PlayerProfile> playerSample = new TransformingRandomAccessList<>(
        listedPlayers,
        info -> new UncheckedPlayerProfile(info.name(), info.id()),
        profile -> new ListedPlayerInfo(profile.getName(), profile.getId())
    );

    @NotNull private String version;
    private int protocolVersion;

    @Nullable private CachedServerIcon favicon;

    private boolean cancelled;

    private boolean originalPlayerCount = true;
    private Object[] players;

    @ApiStatus.Internal
    public PaperServerListPingEvent(@NotNull StatusClient client, @NotNull net.kyori.adventure.text.Component motd, int numPlayers, int maxPlayers,
                                    @NotNull String version, int protocolVersion, @Nullable CachedServerIcon favicon) {
        super("", client.getAddress().getAddress(), motd, numPlayers, maxPlayers);
        this.client = client;
        this.numPlayers = numPlayers;
        this.version = version;
        this.protocolVersion = protocolVersion;
        setServerIcon(favicon);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回正在 ping 服务器的 {@link StatusClient}。
     * <p>原文：
     * Returns the {@link StatusClient} pinging the server.
     *
     * @return 客户端
     * <p>原文：The client
     */
    @NotNull
    public StatusClient getClient() {
        return this.client;
    }

    /**
     * {@inheritDoc}
     *
     * <p>Returns {@code -1} if players are hidden using
     * {@link #shouldHidePlayers()}.</p>
     */
    @Override
    public int getNumPlayers() {
        if (this.hidePlayers) {
            return -1;
        }

        return this.numPlayers;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置服务器列表中显示的玩家数量。
     * <p>
     * 注意，如果启用了 {@link #shouldHidePlayers()}，此设置不会有任何效果。
     * <p>原文：
     * Sets the number of players displayed in the server list.
     * <p>
     * Note that this won't have any effect if {@link #shouldHidePlayers()}
     * is enabled.
     *
     * @param numPlayers 在线玩家数
     * <p>原文：The number of online players
     */
    public void setNumPlayers(int numPlayers) {
        if (this.numPlayers != numPlayers) {
            this.numPlayers = numPlayers;
            this.originalPlayerCount = false;
        }
    }

    /**
     * {@inheritDoc}
     * <p>
     * Returns {@code -1} if players are hidden using
     * {@link #shouldHidePlayers()}.
     */
    @Override
    public int getMaxPlayers() {
        if (this.hidePlayers) {
            return -1;
        }

        return super.getMaxPlayers();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回是否在服务器列表中隐藏所有与玩家相关的信息。这将导致响应中跳过 {@link #getNumPlayers()}、{@link #getMaxPlayers()} 和 {@link #getPlayerSample()}。
     * <p>
     * 启用此选项时，原版 Minecraft 客户端会将玩家数量显示为 {@code ???}。
     * <p>原文：
     * Returns whether all player related information is hidden in the server
     * list. This will cause {@link #getNumPlayers()}, {@link #getMaxPlayers()}
     * and {@link #getPlayerSample()} to be skipped in the response.
     * <p>
     * The Vanilla Minecraft client will display the player count as {@code ???}
     * when this option is enabled.
     *
     * @return 如果玩家数被隐藏则为 {@code true}
     * <p>原文：{@code true} if the player count is hidden
     */
    public boolean shouldHidePlayers() {
        return this.hidePlayers;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否在服务器列表中隐藏所有与玩家相关的信息。这将导致响应中跳过 {@link #getNumPlayers()}、{@link #getMaxPlayers()} 和 {@link #getPlayerSample()}。
     * <p>
     * 启用此选项时，原版 Minecraft 客户端会将玩家数量显示为 {@code ???}。
     * <p>原文：
     * Sets whether all player related information is hidden in the server
     * list. This will cause {@link #getNumPlayers()}, {@link #getMaxPlayers()}
     * and {@link #getPlayerSample()} to be skipped in the response.
     * <p>
     * The Vanilla Minecraft client will display the player count as {@code ???}
     * when this option is enabled.
     *
     * @param hidePlayers 如果应隐藏玩家数则为 {@code true}
     * <p>原文：{@code true} if the player count should be hidden
     */
    public void setHidePlayers(boolean hidePlayers) {
        this.hidePlayers = hidePlayers;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回将在客户端显示为在线玩家的 {@link ListedPlayerInfo} 可变列表。
     * <p>
     * 原版 Minecraft 客户端会在鼠标悬停于玩家数量上时显示它们。
     * <p>原文：
     * Returns a mutable list of {@link ListedPlayerInfo} that will be displayed
     * as online players on the client.
     * <p>
     * The Vanilla Minecraft client will display them when hovering the
     * player count with the mouse.
     *
     * @return 可变的玩家示例列表
     * <p>原文：The mutable player sample list
     */
    @NotNull
    public List<ListedPlayerInfo> getListedPlayers() {
        return this.listedPlayers;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回将在客户端显示为在线玩家的 {@link PlayerProfile} 可变列表。
     * <p>
     * 原版 Minecraft 客户端会在鼠标悬停于玩家数量上时显示它们。
     * <p>原文：
     * Returns a mutable list of {@link PlayerProfile} that will be displayed
     * as online players on the client.
     * <p>
     * The Vanilla Minecraft client will display them when hovering the
     * player count with the mouse.
     *
     * @return 可变的玩家示例列表
     * <p>原文：The mutable player sample list
     * @deprecated 请使用 {@link #getListedPlayers()}，因为这里不包含真实的玩家档案
     * <p>原文：Use {@link #getListedPlayers()}, as this does not contain real player profiles
     */
    @NotNull
    @Deprecated(forRemoval = true, since = "1.20.6")
    public List<PlayerProfile> getPlayerSample() {
        return this.playerSample;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回将作为服务器版本发送给客户端的版本。
     * <p>原文：
     * Returns the version that will be sent as server version on the client.
     *
     * @return 服务器版本
     * <p>原文：The server version
     */
    @NotNull
    public String getVersion() {
        return this.version;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置将作为服务器版本发送给客户端的版本。
     * <p>原文：
     * Sets the version that will be sent as server version to the client.
     *
     * @param version 服务器版本
     * <p>原文：The server version
     */
    public void setVersion(@NotNull String version) {
        this.version = requireNonNull(version, "version");
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回将作为服务器协议版本发送给客户端的协议版本。
     * <p>原文：
     * Returns the protocol version that will be sent as the protocol version
     * of the server to the client.
     *
     * @return 服务器的协议版本；如果服务器尚未完成初始化则为 {@code -1}
     * <p>原文：The protocol version of the server, or {@code -1} if the server
     * has not finished initialization yet
     */
    public int getProtocolVersion() {
        return this.protocolVersion;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置将作为服务器协议版本发送给客户端的协议版本。
     * <p>原文：
     * Sets the protocol version that will be sent as the protocol version
     * of the server to the client.
     *
     * @param protocolVersion 服务器的协议版本
     * <p>原文：The protocol version of the server
     */
    public void setProtocolVersion(int protocolVersion) {
        this.protocolVersion = protocolVersion;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取发送给客户端的服务器图标。
     * <p>原文：
     * Gets the server icon sent to the client.
     *
     * @return 要发送给客户端的图标；为 {@code null} 则表示没有图标
     * <p>原文：The icon to send to the client, or {@code null} for none
     */
    @Nullable
    public CachedServerIcon getServerIcon() {
        return this.favicon;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置发送给客户端的服务器图标。
     * <p>原文：
     * Sets the server icon sent to the client.
     *
     * @param icon 发送给客户端的图标，或 {@code null} 表示无
     * <p>原文：The icon to send to the client, or {@code null} for none
     */
    @Override
    public void setServerIcon(@Nullable CachedServerIcon icon) {
        if (icon != null && icon.isEmpty()) {
            // Represent empty icons as null
            icon = null;
        }

        this.favicon = icon;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Cancelling this event will cause the connection to be closed immediately,
     * without sending a response to the client.
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * {@inheritDoc}
     * <p>
     * Cancelling this event will cause the connection to be closed immediately,
     * without sending a response to the client.
     */
    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <b>Note:</b> For compatibility reasons, this method will return all
     * online players, not just the ones referenced in {@link #getPlayerSample()}.
     * Removing a player will:
     *
     * <ul>
     *     <li>Decrement the online player count (if and only if) the player
     *     count wasn't changed by another plugin before.</li>
     *     <li>Remove all entries from {@link #getPlayerSample()} that refer to
     *     the removed player (based on their {@link UUID}).</li>
     * </ul>
     * @deprecated Iterable 接口将在某个时候被移除
     * <p>原文：the Iterable interface will be removed at some point
     */
    @NotNull
    @Override
    @Deprecated(forRemoval = true, since = "1.20.6")
    public Iterator<Player> iterator() {
        if (this.players == null) {
            this.players = getOnlinePlayers();
        }

        return new PlayerIterator();
    }

    protected @NotNull Object @NotNull [] getOnlinePlayers() {
        return Bukkit.getOnlinePlayers().toArray();
    }

    @NotNull
    protected Player getBukkitPlayer(@NotNull Object player) {
        return (Player) player;
    }

    @ApiStatus.Internal
    private final class PlayerIterator implements Iterator<Player> {

        private int next;
        private int current;
        @Nullable private Player player;

        @Override
        public boolean hasNext() {
            for (; this.next < players.length; this.next++) {
                if (players[this.next] != null) {
                    return true;
                }
            }

            return false;
        }

        @NotNull
        @Override
        public Player next() {
            if (!hasNext()) {
                this.player = null;
                throw new NoSuchElementException();
            }

            this.current = this.next++;
            return this.player = getBukkitPlayer(players[this.current]);
        }

        @Override
        public void remove() {
            if (this.player == null) {
                throw new IllegalStateException();
            }

            UUID uniqueId = this.player.getUniqueId();
            this.player = null;

            // Remove player from iterator
            players[this.current] = null;

            // Remove player from sample
            getPlayerSample().removeIf(p -> uniqueId.equals(p.getId()));

            // Decrement player count
            if (originalPlayerCount) {
                numPlayers--;
            }
        }
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示将显示在服务器列表玩家示例中的玩家。
     * <p>原文：
     * Represents a player that will be displayed in the player sample of the server list.
     *
     * @param name name of the listed player
     * @param id   UUID of the listed player
     */
    public record ListedPlayerInfo(@NotNull String name, @NotNull UUID id) {
    }

    @ApiStatus.Internal
    private static final class UncheckedPlayerProfile implements PlayerProfile {
        private String name;
        private UUID uuid;

        public UncheckedPlayerProfile(final @NotNull String name, final @NotNull UUID uuid) {
            Preconditions.checkNotNull(name, "name cannot be null");
            Preconditions.checkNotNull(uuid, "uuid cannot be null");
            this.name = name;
            this.uuid = uuid;
        }

        @Override
        public @Nullable UUID getUniqueId() {
            return uuid;
        }

        @Override
        public @Nullable String getName() {
            return name;
        }

        @Override
        public @NotNull String setName(@Nullable final String name) {
            Preconditions.checkNotNull(name, "name cannot be null");
            return this.name = name;
        }

        @Override
        public @Nullable UUID getId() {
            return uuid;
        }

        @Override
        public @Nullable UUID setId(@Nullable final UUID uuid) {
            Preconditions.checkNotNull(uuid, "uuid cannot be null");
            return this.uuid = uuid;
        }

        @Override
        public @NotNull PlayerTextures getTextures() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setTextures(@Nullable final PlayerTextures textures) {
            throw new UnsupportedOperationException();
        }

        @Override
        public @NotNull Set<ProfileProperty> getProperties() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasProperty(@Nullable final String property) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setProperty(@NotNull final ProfileProperty property) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void setProperties(@NotNull final Collection<ProfileProperty> properties) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean removeProperty(@Nullable final String property) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void clearProperties() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean isComplete() {
            return false;
        }

        @Override
        public boolean completeFromCache() {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean completeFromCache(final boolean onlineMode) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean completeFromCache(final boolean lookupUUID, final boolean onlineMode) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean complete(final boolean textures) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean complete(final boolean textures, final boolean onlineMode) {
            throw new UnsupportedOperationException();
        }

        @Override
        public @NotNull CompletableFuture<PlayerProfile> update() {
            throw new UnsupportedOperationException();
        }

        @Override
        public @NotNull PlayerProfile clone() {
            throw new UnsupportedOperationException();
        }

        @Override
        public @NotNull Map<String, Object> serialize() {
            throw new UnsupportedOperationException();
        }
    }
}
