package net.minecraft.server.dedicated;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.ImmutableMap.Builder;
import com.google.common.net.HostAndPort;
import com.mojang.datafixers.DataFixer;
import com.mojang.logging.LogUtils;
import io.netty.handler.ssl.SslContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.InetAddress;
import java.net.Proxy;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.function.BooleanSupplier;
import java.util.stream.Stream;
import net.minecraft.DefaultUncaughtExceptionHandler;
import net.minecraft.DefaultUncaughtExceptionHandlerWithName;
import net.minecraft.SharedConstants;
import net.minecraft.SystemReport;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.core.BlockPos;
import net.minecraft.server.ConsoleInput;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerInterface;
import net.minecraft.server.ServerLinks;
import net.minecraft.server.Services;
import net.minecraft.server.WorldStem;
import net.minecraft.server.gui.MinecraftServerGui;
import net.minecraft.server.jsonrpc.JsonRpcNotificationService;
import net.minecraft.server.jsonrpc.ManagementServer;
import net.minecraft.server.jsonrpc.internalapi.MinecraftApi;
import net.minecraft.server.jsonrpc.security.AuthenticationHandler;
import net.minecraft.server.jsonrpc.security.JsonRpcSslContextProvider;
import net.minecraft.server.jsonrpc.security.SecurityConfig;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.progress.LoggingLevelLoadListener;
import net.minecraft.server.network.ServerTextFilter;
import net.minecraft.server.network.TextFilter;
import net.minecraft.server.packs.repository.PackRepository;
import net.minecraft.server.permissions.LevelBasedPermissionSet;
import net.minecraft.server.permissions.PermissionSet;
import net.minecraft.server.players.NameAndId;
import net.minecraft.server.players.OldUsersConverter;
import net.minecraft.server.rcon.RconConsoleSource;
import net.minecraft.server.rcon.thread.QueryThreadGs4;
import net.minecraft.server.rcon.thread.RconThread;
import net.minecraft.util.Mth;
import net.minecraft.util.StringUtil;
import net.minecraft.util.TimeUtil;
import net.minecraft.util.Util;
import net.minecraft.util.debug.DebugSubscriptions;
import net.minecraft.util.debugchart.RemoteDebugSampleType;
import net.minecraft.util.debugchart.RemoteSampleLogger;
import net.minecraft.util.debugchart.SampleLogger;
import net.minecraft.util.debugchart.TpsDebugDimensions;
import net.minecraft.util.monitoring.jmx.MinecraftServerStatistics;
import net.minecraft.world.Difficulty;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.GameType;
import net.minecraft.world.level.gamerules.GameRules;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.level.storage.LevelStorageSource;
import org.jspecify.annotations.Nullable;
import org.slf4j.Logger;

public class DedicatedServer extends MinecraftServer implements ServerInterface {
    static final Logger LOGGER = LogUtils.getLogger();
    private static final int CONVERSION_RETRY_DELAY_MS = 5000;
    private static final int CONVERSION_RETRIES = 2;
    private final java.util.Queue<ConsoleInput> serverCommandQueue = new java.util.concurrent.ConcurrentLinkedQueue<>(); // Paper - 性能优化:使用合适的队列
    private @Nullable QueryThreadGs4 queryThreadGs4;
    // private final RconConsoleSource rconConsoleSource; // CraftBukkit - 移除字段
    private @Nullable RconThread rconThread;
    public DedicatedServerSettings settings;
    private @Nullable MinecraftServerGui gui;
    private final @Nullable ServerTextFilter serverTextFilter;
    private @Nullable RemoteSampleLogger tickTimeLogger;
    private boolean isTickTimeLoggingEnabled;
    public ServerLinks serverLinks;
    private final Map<String, String> codeOfConductTexts;
    private @Nullable ManagementServer jsonRpcServer;
    private long lastHeartbeat;

    public DedicatedServer(
        joptsimple.OptionSet options, net.minecraft.server.WorldLoader.DataLoadContext worldLoader, // CraftBukkit - 签名已更改
        Thread serverThread,
        LevelStorageSource.LevelStorageAccess storageSource,
        PackRepository packRepository,
        WorldStem worldStem,
        DedicatedServerSettings settings,
        DataFixer fixerUpper,
        Services services
    ) {
        super(options, worldLoader, serverThread, storageSource, packRepository, worldStem, Proxy.NO_PROXY, fixerUpper, services, LoggingLevelLoadListener.forDedicatedServer()); // CraftBukkit - 签名已更改
        this.settings = settings;
        this.setMotd(settings.getProperties().motd.get()); // Paper - 从初始属性设置字段
        //this.rconConsoleSource = new RconConsoleSource(this); // CraftBukkit - 移除字段
        this.serverTextFilter = ServerTextFilter.createFromConfig(settings.getProperties());
        this.serverLinks = createServerLinks(settings);
        if (settings.getProperties().codeOfConduct) {
            this.codeOfConductTexts = readCodeOfConducts();
        } else {
            this.codeOfConductTexts = Map.of();
        }
    }

    private static Map<String, String> readCodeOfConducts() {
        Path path = Path.of("codeofconduct");
        if (!Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
            throw new IllegalArgumentException("行为准则文件夹不存在:" + path);
        } else {
            try {
                Builder<String, String> builder = ImmutableMap.builder();

                try (Stream<Path> stream = Files.list(path)) {
                    for (Path path1 : stream.toList()) {
                        String string = path1.getFileName().toString();
                        if (string.endsWith(".txt")) {
                            String string1 = string.substring(0, string.length() - 4).toLowerCase(Locale.ROOT);
                            if (!path1.toRealPath().getParent().equals(path.toAbsolutePath())) {
                                throw new IllegalArgumentException(
                                    "无法读取行为准则文件 \"" + string + "\",因为它链接到了允许目录之外的文件"
                                );
                            }

                            try {
                                String string2 = String.join("\n", Files.readAllLines(path1, StandardCharsets.UTF_8));
                                builder.put(string1, StringUtil.stripColor(string2));
                            } catch (IOException var9) {
                                throw new IllegalArgumentException("无法读取行为准则文件 " + string, var9);
                            }
                        }
                    }
                }

                return builder.build();
            } catch (IOException var11) {
                throw new IllegalArgumentException("无法读取行为准则文件夹", var11);
            }
        }
    }

    private SslContext createSslContext() {
        try {
            return JsonRpcSslContextProvider.createFrom(
                this.getProperties().managementServerTlsKeystore, this.getProperties().managementServerTlsKeystorePassword
            );
        } catch (Exception var2) {
            JsonRpcSslContextProvider.printInstructions();
            throw new IllegalStateException("无法为服务器管理协议配置 TLS", var2);
        }
    }

    @Override
    public boolean initServer() throws IOException {
        int i = this.getProperties().managementServerPort;
        if (this.getProperties().managementServerEnabled) {
            String string = this.settings.getProperties().managementServerSecret;
            if (!SecurityConfig.isValid(string)) {
                throw new IllegalStateException("无效的管理服务器密钥,必须为 40 个字母或数字字符");
            }

            String string1 = this.getProperties().managementServerHost;
            HostAndPort hostAndPort = HostAndPort.fromParts(string1, i);
            SecurityConfig securityConfig = new SecurityConfig(string);
            String string2 = this.getProperties().managementServerAllowedOrigins;
            AuthenticationHandler authenticationHandler = new AuthenticationHandler(securityConfig, string2);
            LOGGER.info("正在 {} 上启动 JSON RPC 服务器", hostAndPort);
            this.jsonRpcServer = new ManagementServer(hostAndPort, authenticationHandler);
            MinecraftApi minecraftApi = MinecraftApi.of(this);
            minecraftApi.notificationManager().registerService(new JsonRpcNotificationService(minecraftApi, this.jsonRpcServer));
            if (this.getProperties().managementServerTlsEnabled) {
                SslContext sslContext = this.createSslContext();
                this.jsonRpcServer.startWithTls(minecraftApi, sslContext);
            } else {
                this.jsonRpcServer.startWithoutTls(minecraftApi);
            }
        }

        Thread thread = new Thread("服务器控制台处理程序") {
            @Override
            public void run() {
                if (!org.bukkit.craftbukkit.Main.useConsole) return; // CraftBukkit
                // Paper 开始 - 使用 TerminalConsoleAppender
                new com.destroystokyo.paper.console.PaperConsole(DedicatedServer.this).start();
                /*
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8));

                String string4;
                try {
                    while (!DedicatedServer.this.isStopped() && DedicatedServer.this.isRunning() && (string4 = bufferedReader.readLine()) != null) {
                        DedicatedServer.this.handleConsoleInput(string4, DedicatedServer.this.createCommandSourceStack());
                    }
                } catch (IOException var4) {
                    DedicatedServer.LOGGER.error("处理控制台输入时发生异常", (Throwable)var4);
                }*/
                // Paper 结束 - 使用 TerminalConsoleAppender
            }
        };
        // CraftBukkit 开始 - TODO:处理命令行日志参数
        java.util.logging.Logger global = java.util.logging.Logger.getLogger("");
        global.setUseParentHandlers(false);
        for (java.util.logging.Handler handler : global.getHandlers()) {
            global.removeHandler(handler);
        }
        global.addHandler(new org.bukkit.craftbukkit.util.ForwardLogHandler());

        final org.apache.logging.log4j.Logger logger = org.apache.logging.log4j.LogManager.getRootLogger();

        System.setOut(org.apache.logging.log4j.io.IoBuilder.forLogger(logger).setLevel(org.apache.logging.log4j.Level.INFO).buildPrintStream());
        System.setErr(org.apache.logging.log4j.io.IoBuilder.forLogger(logger).setLevel(org.apache.logging.log4j.Level.WARN).buildPrintStream());
        // CraftBukkit 结束
        thread.setDaemon(true);
        thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(LOGGER));
        // thread.start(); // Paper - 增强 Brigadier 命令的控制台 Tab 补全；已移至下方
        LOGGER.info("正在启动 Minecraft 服务器,版本 {}", SharedConstants.getCurrentVersion().name());
        if (Runtime.getRuntime().maxMemory() / 1024L / 1024L < 512L) {
            LOGGER.warn("若要使用更多内存启动服务器,请使用 \"java -Xmx1024M -Xms1024M -jar minecraft_server.jar\" 启动");
        }

        // Paper 开始 - 检测是否以 root 用户运行
        if (io.papermc.paper.util.ServerEnvironment.userIsRootOrAdmin()) {
            LOGGER.warn("****************************");
            LOGGER.warn("你正在以管理员或 ROOT 用户身份运行此服务器.不建议这样做.");
            LOGGER.warn("这样做可能会使你面临潜在风险.");
            LOGGER.warn("有关更多信息,请参阅 https://madelinemiller.dev/blog/root-minecraft-server/");
            LOGGER.warn("****************************");
        }
        // Paper 结束 - 检测是否以 root 用户运行

        LOGGER.info("正在加载属性");
        DedicatedServerProperties properties = this.settings.getProperties();
        if (this.isSingleplayer()) {
            this.setLocalIp("127.0.0.1");
        } else {
            this.setUsesAuthentication(properties.onlineMode);
            this.setPreventProxyConnections(properties.preventProxyConnections);
            this.setLocalIp(properties.serverIp);
        }

        // Spigot 开始
        this.setPlayerList(new DedicatedPlayerList(this, this.registries(), this.playerDataStorage));
        org.spigotmc.SpigotConfig.init((java.io.File) this.options.valueOf("spigot-settings"));
        org.spigotmc.SpigotConfig.registerCommands();
        // Spigot 结束
        io.papermc.paper.util.ObfHelper.INSTANCE.getClass(); // Paper - 加载用于堆栈跟踪反混淆等功能的映射
        // Paper 开始 - 初始化全局配置和世界默认配置
        this.paperConfigurations.initializeGlobalConfiguration(this.registryAccess());
        this.paperConfigurations.initializeWorldDefaultsConfiguration(this.registryAccess());
        // Paper 结束 - 初始化全局配置和世界默认配置
        this.server.spark.enableEarlyIfRequested(); // Paper - spark
        // Paper 开始 - 修复将 txt 转换为 json 文件的问题；在创建 PlayerList 之后、加载或保存文件之前提前转换旧用户
        if (this.convertOldUsers()) {
            this.services().nameToIdCache().save(false); // Paper
        }
        this.getPlayerList().loadAndSaveFiles(); // 必须在 convertNames 之后执行
        // Paper 结束 - 修复将 txt 转换为 json 文件的问题
        org.spigotmc.WatchdogThread.doStart(org.spigotmc.SpigotConfig.timeoutTime, org.spigotmc.SpigotConfig.restartOnCrash); // Paper - 启动看门狗线程
        thread.start(); // Paper - 增强 Brigadier 命令的控制台 Tab 补全；在 MinecraftServer.console 和 PaperConfig 初始化后启动控制台线程
        io.papermc.paper.command.PaperCommands.registerCommands(this); // Paper - 设置 /paper 命令
        this.server.spark.registerCommandBeforePlugins(this.server); // Paper - spark
        com.destroystokyo.paper.Metrics.PaperMetrics.startMetrics(); // Paper - 启动指标统计
        com.destroystokyo.paper.VersionHistoryManager.INSTANCE.getClass(); // Paper - 立即加载版本历史记录

        // this.worldData.setGameType(properties.gameMode.get()); // CraftBukkit - 已移至世界加载阶段
        LOGGER.info("默认游戏模式:{}", properties.gameMode.get());
        // Paper 开始 - Unix 域套接字支持
        java.net.SocketAddress bindAddress;
        if (this.getLocalIp().startsWith("unix:")) {
            if (!io.netty.channel.epoll.Epoll.isAvailable()) {
                LOGGER.error("**** 配置无效!");
                LOGGER.error("你正在尝试使用 Unix 域套接字,但当前操作系统不受支持.");
                return false;
            } else if (!io.papermc.paper.configuration.GlobalConfiguration.get().proxies.velocity.enabled && !org.spigotmc.SpigotConfig.bungee) {
                LOGGER.error("**** 配置无效!");
                LOGGER.error("Unix 域套接字要求代理转发 IP.");
                return false;
            }
            bindAddress = new io.netty.channel.unix.DomainSocketAddress(this.getLocalIp().substring("unix:".length()));
        } else {
            InetAddress inetAddress = null;
            if (!this.getLocalIp().isEmpty()) {
                inetAddress = InetAddress.getByName(this.getLocalIp());
            }

            if (this.getPort() < 0) {
                this.setPort(properties.serverPort);
            }
            bindAddress = new java.net.InetSocketAddress(inetAddress, this.getPort());
        }
        // Paper 结束 - Unix 域套接字支持

        this.initializeKeyPair();
        LOGGER.info("正在 {}:{} 上启动 Minecraft 服务器", this.getLocalIp().isEmpty() ? "*" : this.getLocalIp(), this.getPort());

        try {
            this.getConnection().startTcpServerListener(bindAddress); // Paper - Unix 域套接字支持
        } catch (IOException var11) {
            LOGGER.warn("**** 无法绑定端口!");
            LOGGER.warn("异常信息:{}", var11.toString());
            LOGGER.warn("可能已有服务器正在该端口上运行？");
            if (true) throw new IllegalStateException("无法绑定端口", var11); // Paper - 继续抛出端口绑定失败错误
            return false;
        }

        // CraftBukkit 开始
        this.server.loadPlugins();
        this.server.enablePlugins(org.bukkit.plugin.PluginLoadOrder.STARTUP);
        // CraftBukkit 结束

        // Paper 开始 - 添加 Velocity IP 转发支持
        boolean usingProxy = org.spigotmc.SpigotConfig.bungee || io.papermc.paper.configuration.GlobalConfiguration.get().proxies.velocity.enabled;
        String proxyFlavor = (io.papermc.paper.configuration.GlobalConfiguration.get().proxies.velocity.enabled) ? "Velocity" : "BungeeCord";
        String proxyLink = (io.papermc.paper.configuration.GlobalConfiguration.get().proxies.velocity.enabled) ? "https://docs.papermc.io/velocity/security" : "http://www.spigotmc.org/wiki/firewall-guide/";
        // Paper 结束 - 添加 Velocity IP 转发支持
        if (!this.usesAuthentication()) {
            LOGGER.warn("**** 服务器正以离线／不安全模式运行!");
            LOGGER.warn("服务器不会尝试验证用户名,请务必注意.");
            // Spigot 开始
            // Paper 开始 - 添加 Velocity IP 转发支持
            if (usingProxy) {
                LOGGER.warn("虽然这使得使用 {} 成为可能,但如果未正确限制对服务器的访问,也会使攻击者能够使用他们选择的任意用户名连接.", proxyFlavor);
                LOGGER.warn("有关更多信息,请参阅 {}.", proxyLink);
                // Paper 结束 - 添加 Velocity IP 转发支持
            } else {
                LOGGER.warn("虽然这使游戏可以在没有互联网连接的情况下运行,但也会使攻击者能够使用他们选择的任意用户名连接.");
            }
            // Spigot 结束
            LOGGER.warn("若要更改此设置,请在 server.properties 文件中将 \"online-mode\" 设置为 \"true\".");
        }

        // CraftBukkit 开始
        /*
        if (this.convertOldUsers()) {
            this.services.nameToIdCache().save();
        }
        */
        // CraftBukkit 结束

        if (!OldUsersConverter.serverReadyAfterUserconversion(this)) {
            return false;
        } else {
            // this.setPlayerList(new DedicatedPlayerList(this, this.registries(), this.playerDataStorage)); // CraftBukkit - 已移至上方
            this.tickTimeLogger = new RemoteSampleLogger(TpsDebugDimensions.values().length, this.debugSubscribers(), RemoteDebugSampleType.TICK_TIME);
            long nanos = Util.getNanos();
            this.services.nameToIdCache().resolveOfflineUsers(!this.usesAuthentication());
            LOGGER.info("正在准备世界 \"{}\"", this.getLevelIdName());
            this.loadLevel(this.storageSource.getLevelId()); // CraftBukkit
            long l = Util.getNanos() - nanos;
            String string3 = String.format(Locale.ROOT, "%.3fs", l / 1.0E9);
            LOGGER.info("世界 \"{}\" 准备完成（{}）", this.getLevelIdName(), string3); // Paper - 改进启动消息,添加总耗时
            this.initPostWorld(); // Paper - 不将插件计入世界准备时间
            if (properties.announcePlayerAchievements != null) {
                this.worldData.getGameRules().set(GameRules.SHOW_ADVANCEMENT_MESSAGES, properties.announcePlayerAchievements, this.overworld()); // Paper - 每个世界独立的游戏规则
            }

            if (properties.enableQuery) {
                LOGGER.info("正在启动 GS4 状态监听器");
                this.queryThreadGs4 = QueryThreadGs4.create(this);
            }

            if (properties.enableRcon) {
                LOGGER.info("正在启动远程控制监听器");
                this.rconThread = RconThread.create(this);
            }

            if (false && this.getMaxTickLength() > 0L) { // Spigot - 禁用
                Thread thread1 = new Thread(new ServerWatchdog(this));
                thread1.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandlerWithName(LOGGER));
                thread1.setName("服务器看门狗");
                thread1.setDaemon(true);
                thread1.start();
            }

            if (properties.enableJmxMonitoring) {
                MinecraftServerStatistics.registerJmxMonitoring(this);
                LOGGER.info("JMX 监控已启用");
            }

            this.notificationManager().serverStarted();
            return true;
        }
    }

    // Paper start
    public java.io.File getPluginsFolder() {
        return (java.io.File) this.options.valueOf("plugins");
    }
    // Paper end

    @Override
    public boolean isEnforceWhitelist() {
        return this.settings.getProperties().enforceWhitelist.get();
    }

    @Override
    public void setEnforceWhitelist(boolean enforceWhitelist) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.enforceWhitelist.update(this.registryAccess(), enforceWhitelist));
    }

    @Override
    public boolean isUsingWhitelist() {
        return this.settings.getProperties().whiteList.get();
    }

    @Override
    public void setUsingWhitelist(boolean usingWhitelist) {
        new com.destroystokyo.paper.event.server.WhitelistToggleEvent(usingWhitelist).callEvent(); // Paper - WhitelistToggleEvent
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.whiteList.update(this.registryAccess(), usingWhitelist));
    }

    @Override
    public void tickServer(BooleanSupplier hasTimeLeft) {
        super.tickServer(hasTimeLeft);
        if (this.jsonRpcServer != null) {
            this.jsonRpcServer.tick();
        }

        long millis = Util.getMillis();
        int i = this.statusHeartbeatInterval();
        if (i > 0) {
            long l = i * TimeUtil.MILLISECONDS_PER_SECOND;
            if (millis - this.lastHeartbeat >= l) {
                this.lastHeartbeat = millis;
                this.notificationManager().statusHeartbeat();
            }
        }
    }

    @Override
    public boolean saveAllChunks(boolean suppressLogs, boolean flush, boolean force) {
        this.notificationManager().serverSaveStarted();
        boolean flag = super.saveAllChunks(suppressLogs, flush, force);
        this.notificationManager().serverSaveCompleted();
        return flag;
    }

    @Override
    public boolean allowFlight() {
        return this.settings.getProperties().allowFlight.get();
    }

    public void setAllowFlight(boolean allowFlight) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.allowFlight.update(this.registryAccess(), allowFlight));
    }

    @Override
    public DedicatedServerProperties getProperties() {
        return this.settings.getProperties();
    }

    public void setDifficulty(Difficulty difficulty) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.difficulty.update(this.registryAccess(), difficulty));
        this.forceDifficulty();
    }

    @Override
    public void forceDifficulty() {
        // this.setDifficulty(this.getProperties().difficulty.get(), true); // Paper - per level difficulty; Don't overwrite level.dat's difficulty, keep current
    }

    public int viewDistance() {
        return this.settings.getProperties().viewDistance.get();
    }

    public void setViewDistance(int viewDistance) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.viewDistance.update(this.registryAccess(), viewDistance));
        this.getPlayerList().setViewDistance(viewDistance);
    }

    public int simulationDistance() {
        return this.settings.getProperties().simulationDistance.get();
    }

    public void setSimulationDistance(int simulationDistance) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.simulationDistance.update(this.registryAccess(), simulationDistance));
        this.getPlayerList().setSimulationDistance(simulationDistance);
    }

    @Override
    public SystemReport fillServerSystemReport(SystemReport report) {
        report.setDetail("Is Modded", () -> this.getModdedStatus().fullDescription());
        report.setDetail("Type", () -> "Dedicated Server (map_server.txt)");
        return report;
    }

    @Override
    public void dumpServerProperties(Path path) throws IOException {
        DedicatedServerProperties properties = this.getProperties();

        try (Writer bufferedWriter = Files.newBufferedWriter(path)) {
            bufferedWriter.write(String.format(Locale.ROOT, "sync-chunk-writes=%s%n", properties.syncChunkWrites));
            bufferedWriter.write(String.format(Locale.ROOT, "gamemode=%s%n", properties.gameMode.get()));
            bufferedWriter.write(String.format(Locale.ROOT, "entity-broadcast-range-percentage=%d%n", properties.entityBroadcastRangePercentage.get()));
            bufferedWriter.write(String.format(Locale.ROOT, "max-world-size=%d%n", properties.maxWorldSize));
            bufferedWriter.write(String.format(Locale.ROOT, "view-distance=%d%n", properties.viewDistance.get()));
            bufferedWriter.write(String.format(Locale.ROOT, "simulation-distance=%d%n", properties.simulationDistance.get()));
            bufferedWriter.write(String.format(Locale.ROOT, "generate-structures=%s%n", properties.worldOptions.generateStructures()));
            bufferedWriter.write(String.format(Locale.ROOT, "use-native=%s%n", properties.useNativeTransport));
            bufferedWriter.write(String.format(Locale.ROOT, "rate-limit=%d%n", properties.rateLimitPacketsPerSecond));
        }
    }

    @Override
    public void onServerExit() {
        if (this.serverTextFilter != null) {
            this.serverTextFilter.close();
        }

        if (this.gui != null) {
            this.gui.close();
        }

        if (this.rconThread != null) {
            this.rconThread.stopNonBlocking(); // Paper - don't wait for remote connections
        }

        if (this.queryThreadGs4 != null) {
            // this.remoteStatusListener.stop(); // Paper - don't wait for remote connections
        }

        if (this.jsonRpcServer != null) {
            try {
                this.jsonRpcServer.stop(true);
            } catch (InterruptedException var2) {
                LOGGER.error("Interrupted while stopping the management server", (Throwable)var2);
            }
        }

        this.hasFullyShutdown = true; // Paper - Improved watchdog support
        System.exit(this.abnormalExit ? 70 : 0); // CraftBukkit // Paper - Improved watchdog support
    }

    @Override
    public void tickConnection() {
        super.tickConnection();
        this.handleConsoleInputs();
    }

    private static final java.util.concurrent.atomic.AtomicInteger ASYNC_DEBUG_CHUNKS_COUNT = new java.util.concurrent.atomic.AtomicInteger(); // Paper - rewrite chunk system

    public void handleConsoleInput(String msg, CommandSourceStack source) {
        // Paper start - rewrite chunk system
        if (msg.equalsIgnoreCase("paper debug chunks --async")) {
            LOGGER.info("Scheduling async debug chunks");
            Runnable run = () -> {
                LOGGER.info("Async debug chunks executing");
                ca.spottedleaf.moonrise.patches.chunk_system.scheduling.ChunkTaskScheduler.dumpAllChunkLoadInfo(this, false);
                org.bukkit.command.CommandSender sender = MinecraftServer.getServer().console;
                java.io.File file = ca.spottedleaf.moonrise.patches.chunk_system.scheduling.ChunkTaskScheduler.getChunkDebugFile();
                sender.sendMessage(net.kyori.adventure.text.Component.text("Writing chunk information dump to " + file, net.kyori.adventure.text.format.NamedTextColor.GREEN));
                try {
                    ca.spottedleaf.moonrise.common.util.JsonUtil.writeJson(ca.spottedleaf.moonrise.patches.chunk_system.scheduling.ChunkTaskScheduler.debugAllWorlds(this), file);
                    sender.sendMessage(net.kyori.adventure.text.Component.text("Successfully written chunk information!", net.kyori.adventure.text.format.NamedTextColor.GREEN));
                } catch (Throwable thr) {
                    MinecraftServer.LOGGER.warn("Failed to dump chunk information to file " + file.toString(), thr);
                    sender.sendMessage(net.kyori.adventure.text.Component.text("Failed to dump chunk information, see console", net.kyori.adventure.text.format.NamedTextColor.RED));
                }
            };
            Thread t = new Thread(run);
            t.setName("Async debug thread #" + ASYNC_DEBUG_CHUNKS_COUNT.getAndIncrement());
            t.setDaemon(true);
            t.start();
            return;
        }
        // Paper end - rewrite chunk system
        this.serverCommandQueue.add(new ConsoleInput(msg, source)); // Paper - Perf: use proper queue
    }

    public void handleConsoleInputs() {
        // Paper start - Perf: use proper queue
        ConsoleInput consoleInput;
        while ((consoleInput = this.serverCommandQueue.poll()) != null) {
            // Paper end - Perf: use proper queue
            // CraftBukkit start - ServerCommand for preprocessing
            org.bukkit.event.server.ServerCommandEvent event = new org.bukkit.event.server.ServerCommandEvent(this.console, consoleInput.msg);
            this.server.getPluginManager().callEvent(event);
            if (event.isCancelled()) continue;
            consoleInput = new ConsoleInput(event.getCommand(), consoleInput.source);
            // CraftBukkit end
            this.getCommands().performPrefixedCommand(consoleInput.source, consoleInput.msg);
        }
    }

    @Override
    public boolean isDedicatedServer() {
        return true;
    }

    @Override
    public int getRateLimitPacketsPerSecond() {
        return this.getProperties().rateLimitPacketsPerSecond;
    }

    @Override
    public boolean useNativeTransport() {
        return this.getProperties().useNativeTransport;
    }

    @Override
    public DedicatedPlayerList getPlayerList() {
        return (DedicatedPlayerList)super.getPlayerList();
    }

    @Override
    public int getMaxPlayers() {
        return this.settings.getProperties().maxPlayers.get();
    }

    public void setMaxPlayers(int maxPlayers) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.maxPlayers.update(this.registryAccess(), maxPlayers));
    }

    @Override
    public boolean isPublished() {
        return true;
    }

    @Override
    public String getServerIp() {
        return this.getLocalIp();
    }

    @Override
    public int getServerPort() {
        return this.getPort();
    }

    @Override
    public String getServerName() {
        return this.getMotd();
    }

    public void showGui() {
        if (this.gui == null) {
            this.gui = MinecraftServerGui.showFrameFor(this);
        }
    }

    public int spawnProtectionRadius() {
        return this.getProperties().spawnProtection.get();
    }

    public void setSpawnProtectionRadius(int radius) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.spawnProtection.update(this.registryAccess(), radius));
    }

    @Override
    public boolean isUnderSpawnProtection(ServerLevel level, BlockPos pos, Player player) {
        LevelData.RespawnData respawnData = level.getRespawnData();
        if (level.dimension() != respawnData.dimension()) {
            return false;
        } else if (this.getPlayerList().getOps().isEmpty()) {
            return false;
        } else if (this.getPlayerList().isOp(player.nameAndId())) {
            return false;
        } else if (this.spawnProtectionRadius() <= 0) {
            return false;
        } else {
            BlockPos blockPos = respawnData.pos();
            int abs = Mth.abs(pos.getX() - blockPos.getX());
            int abs1 = Mth.abs(pos.getZ() - blockPos.getZ());
            int max = Math.max(abs, abs1);
            return max <= this.spawnProtectionRadius();
        }
    }

    @Override
    public boolean repliesToStatus() {
        return this.getProperties().enableStatus.get();
    }

    public void setRepliesToStatus(boolean repliesToStatus) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.enableStatus.update(this.registryAccess(), repliesToStatus));
    }

    @Override
    public boolean hidesOnlinePlayers() {
        return this.getProperties().hideOnlinePlayers.get();
    }

    public void setHidesOnlinePlayers(boolean hidesOnlinePlayers) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.hideOnlinePlayers.update(this.registryAccess(), hidesOnlinePlayers));
    }

    @Override
    public LevelBasedPermissionSet operatorUserPermissions() {
        return this.getProperties().opPermissions.get();
    }

    public void setOperatorUserPermissions(LevelBasedPermissionSet permissions) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.opPermissions.update(this.registryAccess(), permissions));
    }

    @Override
    public PermissionSet getFunctionCompilationPermissions() {
        return this.getProperties().functionPermissions;
    }

    @Override
    public int playerIdleTimeout() {
        return this.settings.getProperties().playerIdleTimeout.get();
    }

    @Override
    public void setPlayerIdleTimeout(int idleTimeout) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.playerIdleTimeout.update(this.registryAccess(), idleTimeout));
    }

    public int statusHeartbeatInterval() {
        return this.settings.getProperties().statusHeartbeatInterval.get();
    }

    public void setStatusHeartbeatInterval(int heartbeatInterval) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.statusHeartbeatInterval.update(this.registryAccess(), heartbeatInterval));
    }

    @Override
    public String getMotd() {
        return super.getMotd(); // Paper
    }

    @Override
    public void setMotd(String motd) {
        // Paper start
        super.setMotd(motd);
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.motd.update(this.registryAccess(), this.getMotd()));
        // Paper end
    }

    @Override
    public boolean shouldRconBroadcast() {
        return this.getProperties().broadcastRconToOps;
    }

    @Override
    public boolean shouldInformAdmins() {
        return this.getProperties().broadcastConsoleToOps;
    }

    @Override
    public int getAbsoluteMaxWorldSize() {
        return this.getProperties().maxWorldSize;
    }

    @Override
    public int getCompressionThreshold() {
        return this.getProperties().networkCompressionThreshold;
    }

    @Override
    public boolean enforceSecureProfile() {
        DedicatedServerProperties properties = this.getProperties();
        // Paper start - Add setting for proxy online mode status
        return properties.enforceSecureProfile
            && io.papermc.paper.configuration.GlobalConfiguration.get().proxies.isProxyOnlineMode()
            && this.services.canValidateProfileKeys();
        // Paper end - Add setting for proxy online mode status
    }

    @Override
    public boolean logIPs() {
        return this.getProperties().logIPs;
    }

    protected boolean convertOldUsers() {
        boolean flag = false;

        for (int i = 0; !flag && i <= 2; i++) {
            if (i > 0) {
                LOGGER.warn("Encountered a problem while converting the user banlist, retrying in a few seconds");
                this.waitForRetry();
            }

            flag = OldUsersConverter.convertUserBanlist(this);
        }

        boolean flag1 = false;

        for (int var7 = 0; !flag1 && var7 <= 2; var7++) {
            if (var7 > 0) {
                LOGGER.warn("Encountered a problem while converting the ip banlist, retrying in a few seconds");
                this.waitForRetry();
            }

            flag1 = OldUsersConverter.convertIpBanlist(this);
        }

        boolean flag2 = false;

        for (int var8 = 0; !flag2 && var8 <= 2; var8++) {
            if (var8 > 0) {
                LOGGER.warn("Encountered a problem while converting the op list, retrying in a few seconds");
                this.waitForRetry();
            }

            flag2 = OldUsersConverter.convertOpsList(this);
        }

        boolean flag3 = false;

        for (int var9 = 0; !flag3 && var9 <= 2; var9++) {
            if (var9 > 0) {
                LOGGER.warn("Encountered a problem while converting the whitelist, retrying in a few seconds");
                this.waitForRetry();
            }

            flag3 = OldUsersConverter.convertWhiteList(this);
        }

        boolean flag4 = false;

        for (int var10 = 0; !flag4 && var10 <= 2; var10++) {
            if (var10 > 0) {
                LOGGER.warn("Encountered a problem while converting the player save files, retrying in a few seconds");
                this.waitForRetry();
            }

            flag4 = OldUsersConverter.convertPlayers(this);
        }

        return flag || flag1 || flag2 || flag3 || flag4;
    }

    private void waitForRetry() {
        try {
            Thread.sleep(5000L);
        } catch (InterruptedException var2) {
        }
    }

    public long getMaxTickLength() {
        return this.getProperties().maxTickTime;
    }

    @Override
    public int getMaxChainedNeighborUpdates() {
        return this.getProperties().maxChainedNeighborUpdates;
    }

    @Override
    public String getPluginNames() {
        // CraftBukkit start - Whole method
        StringBuilder result = new StringBuilder();
        org.bukkit.plugin.Plugin[] plugins = this.server.getPluginManager().getPlugins();

        result.append(this.server.getName());
        result.append(" on Bukkit ");
        result.append(this.server.getBukkitVersion());

        if (plugins.length > 0 && this.server.getQueryPlugins()) {
            result.append(": ");

            for (int i = 0; i < plugins.length; i++) {
                if (i > 0) {
                    result.append("; ");
                }

                result.append(plugins[i].getDescription().getName());
                result.append(" ");
                result.append(plugins[i].getDescription().getVersion().replaceAll(";", ","));
            }
        }

        return result.toString();
        // CraftBukkit end
    }

    @Override
    public String runCommand(String command) {
        // CraftBukkit start - fire RemoteServerCommandEvent
        throw new UnsupportedOperationException("Not supported - remote source required.");
    }

    public String runCommand(RconConsoleSource rconConsoleSource, String s) {
        if (s.isBlank()) return ""; // Paper - Do not process empty rcon commands

        rconConsoleSource.prepareForCommand();
        this.executeBlocking(() -> {
            CommandSourceStack wrapper = rconConsoleSource.createCommandSourceStack();
            org.bukkit.event.server.RemoteServerCommandEvent event = new org.bukkit.event.server.RemoteServerCommandEvent(rconConsoleSource.getBukkitSender(wrapper), s);
            this.server.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return;
            }
            this.getCommands().performPrefixedCommand(wrapper, event.getCommand());
        });
        return rconConsoleSource.getCommandResponse();
        // CraftBukkit end
    }

    @Override
    public void stopServer() {
        this.notificationManager().serverShuttingDown();
        super.stopServer();
        //Util.shutdownExecutors(); // Paper - Improved watchdog support; moved into super
    }

    @Override
    public boolean isSingleplayerOwner(NameAndId nameAndId) {
        return false;
    }

    @Override
    public int getScaledTrackingDistance(int trackingDistance) {
        return this.entityBroadcastRangePercentage() * trackingDistance / 100;
    }

    public int entityBroadcastRangePercentage() {
        return this.getProperties().entityBroadcastRangePercentage.get();
    }

    public void setEntityBroadcastRangePercentage(int entityBroadcastRangePercentage) {
        this.settings
            .update(
                dedicatedServerProperties -> dedicatedServerProperties.entityBroadcastRangePercentage
                    .update(this.registryAccess(), entityBroadcastRangePercentage)
            );
    }

    @Override
    public String getLevelIdName() {
        return this.storageSource.getLevelId();
    }

    @Override
    public boolean forceSynchronousWrites() {
        return this.settings.getProperties().syncChunkWrites;
    }

    @Override
    public TextFilter createTextFilterForPlayer(ServerPlayer player) {
        return this.serverTextFilter != null ? this.serverTextFilter.createContext(player.getGameProfile()) : TextFilter.DUMMY;
    }

    @Override
    public @Nullable GameType getForcedGameType() {
        return this.forceGameMode() ? this.worldData.getGameType() : null;
    }

    public boolean forceGameMode() {
        return this.settings.getProperties().forceGameMode.get();
    }

    public void setForceGameMode(boolean forceGameMode) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.forceGameMode.update(this.registryAccess(), forceGameMode));
        this.enforceGameTypeForPlayers(this.getForcedGameType());
    }

    public GameType gameMode() {
        return this.getProperties().gameMode.get();
    }

    public void setGameMode(GameType gameMode) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.gameMode.update(this.registryAccess(), gameMode));
        this.worldData.setGameType(this.gameMode());
        this.enforceGameTypeForPlayers(this.getForcedGameType());
    }

    @Override
    public Optional<MinecraftServer.ServerResourcePackInfo> getServerResourcePack() {
        return this.settings.getProperties().serverResourcePackInfo;
    }

    @Override
    public void endMetricsRecordingTick() {
        super.endMetricsRecordingTick();
        this.isTickTimeLoggingEnabled = this.debugSubscribers().hasAnySubscriberFor(DebugSubscriptions.DEDICATED_SERVER_TICK_TIME);
    }

    @Override
    public SampleLogger getTickTimeLogger() {
        return this.tickTimeLogger;
    }

    @Override
    public boolean isTickTimeLoggingEnabled() {
        return this.isTickTimeLoggingEnabled;
    }

    @Override
    public boolean acceptsTransfers() {
        return this.settings.getProperties().acceptsTransfers.get();
    }

    public void setAcceptsTransfers(boolean acceptsTransfers) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.acceptsTransfers.update(this.registryAccess(), acceptsTransfers));
    }

    @Override
    public ServerLinks serverLinks() {
        return this.serverLinks;
    }

    @Override
    public int pauseWhenEmptySeconds() {
        return this.settings.getProperties().pauseWhenEmptySeconds.get();
    }

    public void setPauseWhenEmptySeconds(int pauseWhenEmptySeconds) {
        this.settings.update(dedicatedServerProperties -> dedicatedServerProperties.pauseWhenEmptySeconds.update(this.registryAccess(), pauseWhenEmptySeconds));
    }

    private static ServerLinks createServerLinks(DedicatedServerSettings settings) {
        Optional<URI> optional = parseBugReportLink(settings.getProperties());
        return optional.<ServerLinks>map(uri -> new ServerLinks(List.of(ServerLinks.KnownLinkType.BUG_REPORT.create(uri)))).orElse(ServerLinks.EMPTY);
    }

    private static Optional<URI> parseBugReportLink(DedicatedServerProperties properties) {
        String string = properties.bugReportLink;
        if (string.isEmpty()) {
            return Optional.empty();
        } else {
            try {
                return Optional.of(Util.parseAndValidateUntrustedUri(string));
            } catch (Exception var3) {
                LOGGER.warn("Failed to parse bug link {}", string, var3);
                return Optional.empty();
            }
        }
    }

    @Override
    public Map<String, String> getCodeOfConducts() {
        return this.codeOfConductTexts;
    }

    // CraftBukkit start
    public boolean isDebugging() {
        return this.getProperties().debug;
    }

    @Override
    public org.bukkit.command.CommandSender getBukkitSender(CommandSourceStack wrapper) {
        return this.console;
    }
    // CraftBukkit end
}
