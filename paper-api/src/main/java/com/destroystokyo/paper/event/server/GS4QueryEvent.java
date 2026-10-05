package com.destroystokyo.paper.event.server;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import org.bukkit.Server;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.checkerframework.checker.nullness.qual.MonotonicNonNull;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当服务器通过 GS4 Query 协议被查询时触发此事件。<br> 改编自 Velocity 的 ProxyQueryEvent
 * <p>原文：
 * This event is fired if server is getting queried over GS4 Query protocol.
 * <br>
 * Adapted from Velocity's ProxyQueryEvent
 *
 * @author Mark Vainomaa
 */
@NullMarked
public final class GS4QueryEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final QueryType queryType;
    private final InetAddress querierAddress;
    private QueryResponse response;

    @ApiStatus.Internal
    public GS4QueryEvent(final QueryType queryType, final InetAddress querierAddress, final QueryResponse response) {
        super(true); // should always be called async
        this.queryType = queryType;
        this.querierAddress = querierAddress;
        this.response = response;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取查询类型
     * <p>原文：
     * Get query type
     *
     * @return 查询类型
     * <p>原文：query type
     */
    public QueryType getQueryType() {
        return this.queryType;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取查询者地址
     * <p>原文：
     * Get querier address
     *
     * @return 查询者地址
     * <p>原文：querier address
     */
    public InetAddress getQuerierAddress() {
        return this.querierAddress;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取查询响应
     * <p>原文：
     * Get query response
     *
     * @return 查询响应
     * <p>原文：query response
     */
    public QueryResponse getResponse() {
        return this.response;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置查询响应
     * <p>原文：
     * Set query response
     *
     * @param response 查询响应
     * <p>原文：query response
     */
    public void setResponse(final QueryResponse response) {
        this.response = Preconditions.checkNotNull(response, "response");
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 查询的类型
     * <p>原文：
     * The type of query
     */
    public enum QueryType {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 基础查询只请求部分信息，例如 motd、游戏类型（硬编码为 <pre>MINECRAFT</pre>）、地图、当前玩家数、最大玩家数、服务器端口和服务器 motd
         * <p>原文：
         * Basic query asks only a subset of information, such as motd, game type (hardcoded to <pre>MINECRAFT</pre>), map,
         * current players, max players, server port and server motd
         */
        BASIC,

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 完整查询几乎会请求此事件中的所有内容（只有硬编码的值无法在此修改）。
         * <p>原文：
         * Full query asks pretty much everything present on this event (only hardcoded values cannot be modified here).
         */
        FULL
    }

    public static final class QueryResponse {

        private final String motd;
        private final String gameVersion;
        private final String map;
        private final int currentPlayers;
        private final int maxPlayers;
        private final String hostname;
        private final int port;
        private final Collection<String> players;
        private final String serverVersion;
        private final Collection<PluginInformation> plugins;

        private QueryResponse(final String motd, final String gameVersion, final String map, final int currentPlayers, final int maxPlayers, final String hostname, final int port, final Collection<String> players, final String serverVersion, final Collection<PluginInformation> plugins) {
            this.motd = motd;
            this.gameVersion = gameVersion;
            this.map = map;
            this.currentPlayers = currentPlayers;
            this.maxPlayers = maxPlayers;
            this.hostname = hostname;
            this.port = port;
            this.players = players;
            this.serverVersion = serverVersion;
            this.plugins = plugins;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取用于回复查询的 motd。默认为 {@link Server#getMotd()}。
         * <p>原文：
         * Get motd which will be used to reply to the query. By default, it is {@link Server#getMotd()}.
         *
         * @return motd
         */
        public String getMotd() {
            return this.motd;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取用于回复查询的游戏版本。默认发送所支持的 Minecraft 版本范围。
         * <p>原文：
         * Get game version which will be used to reply to the query. By default, supported Minecraft versions range is sent.
         *
         * @return 游戏版本
         * <p>原文：game version
         */
        public String getGameVersion() {
            return this.gameVersion;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取用于回复查询的地图名称。默认发送 {@code world}。
         * <p>原文：
         * Get map name which will be used to reply to the query. By default {@code world} is sent.
         *
         * @return 地图名称
         * <p>原文：map name
         */
        public String getMap() {
            return this.map;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取用于回复查询的当前在线玩家数。
         * <p>原文：
         * Get current online player count which will be used to reply to the query.
         *
         * @return 在线玩家数
         * <p>原文：online player count
         */
        public int getCurrentPlayers() {
            return this.currentPlayers;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取用于回复查询的最大玩家数。
         * <p>原文：
         * Get max player count which will be used to reply to the query.
         *
         * @return 最大玩家数
         * <p>原文：max player count
         */
        public int getMaxPlayers() {
            return this.maxPlayers;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取服务器（对外公开的）主机名。
         * <p>原文：
         * Get server (public facing) hostname.
         *
         * @return 服务器主机名
         * <p>原文：server hostname
         */
        public String getHostname() {
            return this.hostname;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取服务器（对外公开的）端口。
         * <p>原文：
         * Get server (public facing) port.
         *
         * @return 服务器端口
         * <p>原文：server port
         */
        public int getPort() {
            return this.port;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取用于回复查询的玩家集合。
         * <p>原文：
         * Get collection of players which will be used to reply to the query.
         *
         * @return 玩家集合
         * <p>原文：collection of players
         */
        public Collection<String> getPlayers() {
            return this.players;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取用于回复查询的服务器软件（名称和版本）。
         * <p>原文：
         * Get server software (name and version) which will be used to reply to the query.
         *
         * @return 服务器软件
         * <p>原文：server software
         */
        public String getServerVersion() {
            return this.serverVersion;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取用于回复查询的插件列表。
         * <p>原文：
         * Get list of plugins which will be used to reply to the query.
         *
         * @return 插件集合
         * <p>原文：collection of plugins
         */
        public Collection<PluginInformation> getPlugins() {
            return this.plugins;
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 根据此响应所表示的数据创建新的 {@link Builder} 实例。
         * <p>原文：
         * Creates a new {@link Builder} instance from data represented by this response.
         *
         * @return {@link QueryResponse} 构建器
         * <p>原文：{@link QueryResponse} builder
         */
        public Builder toBuilder() {
            return QueryResponse.builder()
                .motd(this.getMotd())
                .gameVersion(this.getGameVersion())
                .map(this.getMap())
                .currentPlayers(this.getCurrentPlayers())
                .maxPlayers(this.getMaxPlayers())
                .hostname(this.getHostname())
                .port(this.getPort())
                .players(this.getPlayers())
                .serverVersion(this.getServerVersion())
                .plugins(this.getPlugins());
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 创建新的 {@link Builder} 实例。
         * <p>原文：
         * Creates a new {@link Builder} instance.
         *
         * @return {@link QueryResponse} 构建器
         * <p>原文：{@link QueryResponse} builder
         */
        public static Builder builder() {
            return new Builder();
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * {@link QueryResponse} 对象的构建器。
         * <p>原文：
         * A builder for {@link QueryResponse} objects.
         */
        public static final class Builder {

            private @MonotonicNonNull String motd;
            private @MonotonicNonNull String gameVersion;
            private @MonotonicNonNull String map;
            private @MonotonicNonNull String hostname;
            private @MonotonicNonNull String serverVersion;

            private int currentPlayers;
            private int maxPlayers;
            private int port;

            private final List<String> players = new ArrayList<>();
            private final List<PluginInformation> plugins = new ArrayList<>();

            private Builder() {
            }

            public Builder motd(final String motd) {
                this.motd = Preconditions.checkNotNull(motd, "motd");
                return this;
            }

            public Builder gameVersion(final String gameVersion) {
                this.gameVersion = Preconditions.checkNotNull(gameVersion, "gameVersion");
                return this;
            }

            public Builder map(final String map) {
                this.map = Preconditions.checkNotNull(map, "map");
                return this;
            }

            public Builder currentPlayers(final int currentPlayers) {
                Preconditions.checkArgument(currentPlayers >= 0, "currentPlayers cannot be negative");
                this.currentPlayers = currentPlayers;
                return this;
            }

            public Builder maxPlayers(final int maxPlayers) {
                Preconditions.checkArgument(maxPlayers >= 0, "maxPlayers cannot be negative");
                this.maxPlayers = maxPlayers;
                return this;
            }

            public Builder hostname(final String hostname) {
                this.hostname = Preconditions.checkNotNull(hostname, "hostname");
                return this;
            }

            public Builder port(final int port) {
                Preconditions.checkArgument(port >= 1 && port <= 65535, "port must be between 1-65535");
                this.port = port;
                return this;
            }

            public Builder players(final Collection<String> players) {
                this.players.addAll(Preconditions.checkNotNull(players, "players"));
                return this;
            }

            public Builder players(final String... players) {
                this.players.addAll(Arrays.asList(Preconditions.checkNotNull(players, "players")));
                return this;
            }

            public Builder clearPlayers() {
                this.players.clear();
                return this;
            }

            public Builder serverVersion(final String serverVersion) {
                this.serverVersion = Preconditions.checkNotNull(serverVersion, "serverVersion");
                return this;
            }

            public Builder plugins(final Collection<PluginInformation> plugins) {
                this.plugins.addAll(Preconditions.checkNotNull(plugins, "plugins"));
                return this;
            }

            public Builder plugins(final PluginInformation... plugins) {
                this.plugins.addAll(Arrays.asList(Preconditions.checkNotNull(plugins, "plugins")));
                return this;
            }

            public Builder clearPlugins() {
                this.plugins.clear();
                return this;
            }

            /**
             * <!-- Paper HTML javadoc zh translation -->
             * 使用提供的数据构建新的 {@link QueryResponse}。
             * <p>原文：
             * Builds new {@link QueryResponse} with supplied data.
             *
             * @return 响应
             * <p>原文：response
             */
            public QueryResponse build() {
                return new QueryResponse(
                    Preconditions.checkNotNull(this.motd, "motd"),
                    Preconditions.checkNotNull(this.gameVersion, "gameVersion"),
                    Preconditions.checkNotNull(this.map, "map"),
                    this.currentPlayers,
                    this.maxPlayers,
                    Preconditions.checkNotNull(this.hostname, "hostname"),
                    this.port,
                    ImmutableList.copyOf(this.players),
                    Preconditions.checkNotNull(this.serverVersion, "serverVersion"),
                    ImmutableList.copyOf(this.plugins)
                );
            }
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 插件信息
         * <p>原文：
         * Plugin information
         */
        public static class PluginInformation {

            private String name;
            private String version;

            public PluginInformation(final String name, final String version) {
                this.name = Preconditions.checkNotNull(name, "name");
                this.version = Preconditions.checkNotNull(version, "version");
            }

            public String getName() {
                return this.name;
            }

            public void setName(final String name) {
                this.name = name;
            }

            public void setVersion(final String version) {
                this.version = version;
            }

            public String getVersion() {
                return this.version;
            }

            public static PluginInformation of(final String name, final String version) {
                return new PluginInformation(name, version);
            }
        }
    }
}
