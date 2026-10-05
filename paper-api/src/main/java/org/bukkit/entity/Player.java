package org.bukkit.entity;

import com.destroystokyo.paper.ClientOption;
import io.papermc.paper.connection.PlayerGameConnection;
import io.papermc.paper.entity.LookAnchor;
import io.papermc.paper.entity.PlayerGiveResult;
import io.papermc.paper.math.Position;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.object.PlayerHeadObjectContents;
import org.bukkit.BanEntry;
import org.bukkit.DyeColor;
import org.bukkit.Effect;
import org.bukkit.GameMode;
import org.bukkit.Input;
import org.bukkit.Instrument;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Note;
import org.bukkit.OfflinePlayer;
import org.bukkit.Particle;
import org.bukkit.Server;
import org.bukkit.ServerLinks;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.WeatherType;
import org.bukkit.WorldBorder;
import org.bukkit.advancement.Advancement;
import org.bukkit.advancement.AdvancementProgress;
import org.bukkit.ban.IpBanList;
import org.bukkit.ban.ProfileBanList;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Sign;
import org.bukkit.block.TileState;
import org.bukkit.block.data.BlockData;
import org.bukkit.block.sign.Side;
import org.bukkit.conversations.Conversable;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockDropItemEvent;
import org.bukkit.event.player.PlayerExpCooldownChangeEvent;
import org.bukkit.event.player.PlayerResourcePackStatusEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.map.MapView;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.messaging.PluginMessageRecipient;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scoreboard.Scoreboard;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家对象
 * <p>原文：
 * Represents a player, connected or not
 */
@NullMarked
public interface Player extends HumanEntity, Conversable, OfflinePlayer, PluginMessageRecipient, net.kyori.adventure.identity.Identified, net.kyori.adventure.bossbar.BossBarViewer, com.destroystokyo.paper.network.NetworkClient { // Paper

    // Paper start
    @Override
    default net.kyori.adventure.identity.Identity identity() {
        return net.kyori.adventure.identity.Identity.identity(this.getUniqueId());
    }

    /**
     * Gets an unmodifiable view of all known currently active bossbars.
     * <p>
     * <b>This currently only returns bossbars shown to the player via
     * {@link #showBossBar(net.kyori.adventure.bossbar.BossBar)} and does not contain bukkit
     * {@link org.bukkit.boss.BossBar} instances shown to the player.</b>
     *
     * @return an unmodifiable view of all known currently active bossbars
     * @since 4.14.0
     */
    @Override
    @org.jetbrains.annotations.UnmodifiableView Iterable<? extends net.kyori.adventure.bossbar.BossBar> activeBossBars();

    /**
     * Gets the "friendly" name to display of this player.
     *
     * @return the display name
     */
    net.kyori.adventure.text.Component displayName();

    /**
     * Sets the "friendly" name to display of this player.
     *
     * @param displayName the display name to set
     */
    void displayName(final net.kyori.adventure.text.@Nullable Component displayName);
    // Paper end

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得玩家在聊天信息中的昵称.
     * <p>
     * 这个昵称只显示在聊天信息中，能以颜色加以修饰.
     * <p>原文：
     * Gets the "friendly" name to display of this player. This may include
     * color.
     * <p>
     * Note that this name will not be displayed in game, only in chat and
     * places defined by plugins.
     *
     * @return 显示的名称.
     * <p>原文：the friendly name
     * @deprecated in favour of {@link #displayName()}
     */
    @Deprecated // Paper
    public String getDisplayName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家在聊天信息中的昵称.
     * <p>
     * 这个名字只显示在聊天信息中,能以颜色加以修饰.
     * <p>原文：
     * Sets the "friendly" name to display of this player. This may include
     * color.
     * <p>
     * Note that this name will not be displayed in game, only in chat and
     * places defined by plugins.
     *
     * @param name 显示的名称.
     * <p>原文：The new display name.
     * @deprecated in favour of {@link #displayName(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    public void setDisplayName(@Nullable String name);

    // Paper start
    /**
     * Sets the name that is shown on the in-game player list.
     * <p>
     * If the value is null, the name will be identical to {@link #getName()}.
     *
     * @param name new player list name
     */
    void playerListName(net.kyori.adventure.text.@Nullable Component name);

    /**
     * Gets the name that is shown on the in-game player list.
     *
     * @return the player list name
     */
    net.kyori.adventure.text.Component playerListName();

    /**
     * Gets the currently displayed player list header for this player.
     *
     * @return player list header or null
     */
    net.kyori.adventure.text.@Nullable Component playerListHeader();

    /**
     * Gets the currently displayed player list footer for this player.
     *
     * @return player list footer or null
     */
    net.kyori.adventure.text.@Nullable Component playerListFooter();
    // Paper end
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到玩家显示在tab列表中的名称.
     * <p>原文：
     * Gets the name that is shown on the player list.
     *
     * @return 玩家名(显示于tab列表)
     * <p>原文：the player list name
     * @deprecated in favour of {@link #playerListName()}
     */
    @Deprecated // Paper
    public String getPlayerListName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家显示在Tab列表中的名称.
     * <p>
     * 如果设置为null则此名字与{@link #getName()}相同.
     * <p>原文：
     * Sets the name that is shown on the in-game player list.
     * <p>
     * If the value is null, the name will be identical to {@link #getName()}.
     *
     * @param name 新的显示在玩家列表中的名字
     * <p>原文：new player list name
     * @deprecated in favour of {@link #playerListName(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    public void setPlayerListName(@Nullable String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家在玩家列表中的相对排序位置.
     * <p>原文：
     * Gets the relative order that the player is shown on the player list.
     *
     * @return 玩家列表排序值
     * <p>原文：the player list order
     */
    public int getPlayerListOrder();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家在玩家列表中的相对排序位置.
     * <p>原文：
     * Sets the relative order that the player is shown on the in-game player
     * list.
     *
     * @param order 新的玩家列表排序值, 必须为非负数
     * <p>原文：new player list order, must be positive
     */
    public void setPlayerListOrder(int order);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前显示给该玩家的玩家列表头部文本.
     * <p>原文：
     * Gets the currently displayed player list header for this player.
     *
     * @return 玩家列表头部文本或null
     * <p>原文：player list header or null
     * @deprecated in favour of {@link #playerListHeader()}
     */
    @Deprecated // Paper
    @Nullable
    public String getPlayerListHeader();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前显示给该玩家的玩家列表底部文本.
     * <p>原文：
     * Gets the currently displayed player list footer for this player.
     *
     * @return 玩家列表底部文本或null
     * <p>原文：player list header or null
     * @deprecated in favour of {@link #playerListFooter()}
     */
    @Deprecated // Paper
    @Nullable
    public String getPlayerListFooter();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前显示给该玩家的玩家列表头部文本.
     * <p>原文：
     * Sets the currently displayed player list header for this player.
     *
     * @param header 玩家列表头部文本, null表示空
     * <p>原文：player list header, null for empty
     * @deprecated in favour of {@link #sendPlayerListHeader(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    public void setPlayerListHeader(@Nullable String header);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前显示给该玩家的玩家列表底部文本.
     * <p>原文：
     * Sets the currently displayed player list footer for this player.
     *
     * @param footer 玩家列表底部文本, null表示空
     * <p>原文：player list footer, null for empty
     * @deprecated in favour of {@link #sendPlayerListFooter(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    public void setPlayerListFooter(@Nullable String footer);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前显示给该玩家的玩家列表头部和底部文本.
     * <p>原文：
     * Sets the currently displayed player list header and footer for this
     * player.
     *
     * @param header 玩家列表头部文本, null表示空
     * <p>原文：player list header, null for empty
     * @param footer 玩家列表底部文本, null表示空
     * <p>原文：player list footer, null for empty
     * @deprecated in favour of {@link #sendPlayerListHeaderAndFooter(net.kyori.adventure.text.Component, net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    public void setPlayerListHeaderFooter(@Nullable String header, @Nullable String footer);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家指南针的指向的位置({@link Location}).
     * <p>原文：
     * Set the target of the player's compass.
     *
     * @param loc 指向
     * <p>原文：Location to point to
     */
    public void setCompassTarget(Location loc);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到玩家指南针的指向的位置({@link Location}).
     * <p>
     * 译注:默认为出生点.
     * <p>原文：
     * Get the previously set compass target.
     *
     * @return 指向
     * <p>原文：location of the target
     */
    public Location getCompassTarget();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到一个Address对象,包括这个玩家的IP以及登入端口.
     * <p>原文：
     * Gets the socket address of this player
     *
     * @return 玩家的Address对象
     * <p>原文：the player's address
     */
    @Nullable
    public InetSocketAddress getAddress();

    // Paper start - Add API to get player's proxy address
    /**
     * Gets the socket address of this player's proxy
     *
     * @return the player's proxy address, null if the server doesn't have Proxy Protocol enabled, or the player didn't connect to an HAProxy instance
     */
    @Nullable
    public InetSocketAddress getHAProxyAddress();
    // Paper end - Add API to get player's proxy address

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该连接是否是从另一个服务器转移过来的.
     * <p>原文：
     * Gets if this connection has been transferred from another server.
     *
     * @return 如果该连接是转移过来的则返回true
     * <p>原文：true if the connection has been transferred
     */
    public boolean isTransferred();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从该玩家客户端检索一个Cookie.
     * <p>原文：
     * Retrieves a cookie from this player.
     *
     * @param key 标识Cookie的键
     * <p>原文：the key identifying the cookie
     * @return 一个 {@link CompletableFuture}, 当收到Cookie响应或可用时将完成.
     *         如果客户端中未设置该Cookie, {@link CompletableFuture} 将以null值完成.
     * <p>原文：a {@link CompletableFuture} that will be completed when the
     * Cookie response is received or otherwise available. If the cookie is not
     * set in the client, the {@link CompletableFuture} will complete with a
     * null value.
     */
    CompletableFuture<byte @Nullable []> retrieveCookie(NamespacedKey key);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在该玩家客户端中存储一个Cookie.
     * <p>原文：
     * Stores a cookie in this player's client.
     *
     * @param key 标识Cookie的键
     * <p>原文：the key identifying the cookie
     * @param value 要存储在Cookie中的数据
     * <p>原文：the data to store in the cookie
     * @throws IllegalStateException 如果此时无法存储Cookie
     * <p>原文：if a cookie cannot be stored at this time
     */
    void storeCookie(NamespacedKey key, byte[] value);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 请求该玩家连接到由主机和端口指定的另一个服务器.
     * <p>原文：
     * Requests this player to connect to a different server specified by host
     * and port.
     *
     * @param host 要转移到的服务器主机
     * <p>原文：the host of the server to transfer to
     * @param port 要转移到的服务器端口
     * <p>原文：the port of the server to transfer to
     * @throws IllegalStateException 如果此时无法进行转移
     * <p>原文：if a transfer cannot take place at this
     * time
     */
    void transfer(String host, int port);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 发送一条不含颜色代码的消息.
     * <p>
     * 译注:就是会把颜色代码过滤掉然后{@link #sendMessage}
     * <p>原文：
     * Sends this sender a message raw
     *
     * @param message 要发送的消息
     * <p>原文：Message to be displayed
     */
    @Override
    public void sendRawMessage(String message);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 踢出玩家,并且发送一条自定义的踢出消息.
     * <p>原文：
     * Kicks player with custom kick message.
     *
     * @param message 踢出消息
     * <p>原文：kick message
     * @deprecated in favour of {@link #kick(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    public void kickPlayer(@Nullable String message);

    /**
     * Kicks the player with the default kick message.
     *
     * @see #kick(net.kyori.adventure.text.Component)
     */
    default void kick() {
        class Holder {
            public static final Component DEFAULT_KICK_MESSAGE = Component.translatable("multiplayer.disconnect.kicked");
        }
        this.kick(Holder.DEFAULT_KICK_MESSAGE);
    }

    /**
     * Kicks player with custom kick message.
     *
     * @param message kick message
     */
    default void kick(final net.kyori.adventure.text.@Nullable Component message) {
        this.kick(message, org.bukkit.event.player.PlayerKickEvent.Cause.PLUGIN);
    }

    /**
     * Kicks player with custom kick message and cause.
     *
     * @param message kick message
     * @param cause kick cause
     */
    void kick(final net.kyori.adventure.text.@Nullable Component message, org.bukkit.event.player.PlayerKickEvent.Cause cause);

    /**
     * <!-- Chinese_BukkitAPI CC BY-NC 4.0 -->
     * 将该用户添加到 {@link ProfileBanList}. 如果已存在封禁记录, 则会更新该记录.
     * <p>原文：
     * Adds this user to the {@link ProfileBanList}. If a previous ban exists, this will
     * update the entry.
     *
     * @param reason reason for the ban, null indicates implementation default
     * @param expires date for the ban's expiration (unban), or null to imply
     *     forever
     * @param source source of the ban, null indicates implementation default
     * @param kickPlayer if the player need to be kick
     *
     * @return the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     */
    @Nullable
    public <E extends BanEntry<? super com.destroystokyo.paper.profile.PlayerProfile>> E ban(@Nullable String reason, @Nullable Date expires, @Nullable String source, boolean kickPlayer); // Paper - fix ban list API

    /**
     * <!-- Chinese_BukkitAPI CC BY-NC 4.0 -->
     * 将该用户添加到 {@link ProfileBanList}. 如果已存在封禁记录, 则会更新该记录.
     * <p>原文：
     * Adds this user to the {@link ProfileBanList}. If a previous ban exists, this will
     * update the entry.
     *
     * @param reason reason for the ban, null indicates implementation default
     * @param expires date for the ban's expiration (unban), or null to imply
     *     forever
     * @param source source of the ban, null indicates implementation default
     * @param kickPlayer if the player need to be kick
     *
     * @return the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     */
    @Nullable
    public <E extends BanEntry<? super com.destroystokyo.paper.profile.PlayerProfile>> E ban(@Nullable String reason, @Nullable Instant expires, @Nullable String source, boolean kickPlayer); // Paper - fix ban list API

    /**
     * <!-- Chinese_BukkitAPI CC BY-NC 4.0 -->
     * 将该用户添加到 {@link ProfileBanList}. 如果已存在封禁记录, 则会更新该记录.
     * <p>原文：
     * Adds this user to the {@link ProfileBanList}. If a previous ban exists, this will
     * update the entry.
     *
     * @param reason reason for the ban, null indicates implementation default
     * @param duration the duration how long the ban lasts, or null to imply
     *     forever
     * @param source source of the ban, null indicates implementation default
     * @param kickPlayer if the player need to be kick
     *
     * @return the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     */
    @Nullable
    public <E extends BanEntry<? super com.destroystokyo.paper.profile.PlayerProfile>> E ban(@Nullable String reason, @Nullable Duration duration, @Nullable String source, boolean kickPlayer); // Paper - fix ban list API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将该用户的当前IP地址添加到 {@link IpBanList}. 如果已存在封禁记录, 则会更新该记录.
     * 如果 {@link #getAddress()} 返回null, 则此方法将抛出异常.
     * <p>原文：
     * Adds this user's current IP address to the {@link IpBanList}. If a previous ban exists, this will
     * update the entry. If {@link #getAddress()} is null this method will throw an exception.
     *
     * @param reason 封禁原因, null表示使用默认值
     * <p>原文：reason for the ban, null indicates implementation default
     * @param expires 封禁到期(解封)日期, null表示永久封禁
     * <p>原文：date for the ban's expiration (unban), or null to imply
     *     forever
     * @param source 封禁来源, null表示使用默认值
     * <p>原文：source of the ban, null indicates implementation default
     * @param kickPlayer 是否需要踢出该玩家
     * <p>原文：if the player need to be kick
     * @return 新创建的封禁记录, 或(更新后的)已有封禁记录
     * <p>原文：the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     */
    @Nullable
    public BanEntry<InetAddress> banIp(@Nullable String reason, @Nullable Date expires, @Nullable String source, boolean kickPlayer);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将该用户的当前IP地址添加到 {@link IpBanList}. 如果已存在封禁记录, 则会更新该记录.
     * 如果 {@link #getAddress()} 返回null, 则此方法将抛出异常.
     * <p>原文：
     * Adds this user's current IP address to the {@link IpBanList}. If a previous ban exists, this will
     * update the entry. If {@link #getAddress()} is null this method will throw an exception.
     *
     * @param reason 封禁原因, null表示使用默认值
     * <p>原文：reason for the ban, null indicates implementation default
     * @param expires 封禁到期(解封)时间点, null表示永久封禁
     * <p>原文：date for the ban's expiration (unban), or null to imply
     *     forever
     * @param source 封禁来源, null表示使用默认值
     * <p>原文：source of the ban, null indicates implementation default
     * @param kickPlayer 是否需要踢出该玩家
     * <p>原文：if the player need to be kick
     * @return 新创建的封禁记录, 或(更新后的)已有封禁记录
     * <p>原文：the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     */
    @Nullable
    public BanEntry<InetAddress> banIp(@Nullable String reason, @Nullable Instant expires, @Nullable String source, boolean kickPlayer);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将该用户的当前IP地址添加到 {@link IpBanList}. 如果已存在封禁记录, 则会更新该记录.
     * 如果 {@link #getAddress()} 返回null, 则此方法将抛出异常.
     * <p>原文：
     * Adds this user's current IP address to the {@link IpBanList}. If a previous ban exists, this will
     * update the entry. If {@link #getAddress()} is null this method will throw an exception.
     *
     * @param reason 封禁原因, null表示使用默认值
     * <p>原文：reason for the ban, null indicates implementation default
     * @param duration 封禁持续时长, null表示永久封禁
     * <p>原文：the duration how long the ban lasts, or null to imply
     *     forever
     * @param source 封禁来源, null表示使用默认值
     * <p>原文：source of the ban, null indicates implementation default
     * @param kickPlayer 是否需要踢出该玩家
     * <p>原文：if the player need to be kick
     * @return 新创建的封禁记录, 或(更新后的)已有封禁记录
     * <p>原文：the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     */
    @Nullable
    public BanEntry<InetAddress> banIp(@Nullable String reason, @Nullable Duration duration, @Nullable String source, boolean kickPlayer);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 强制玩家发送一个聊天消息,或强制使用命令(需要在内容前加 "/").
     * <p>原文：
     * Says a message (or runs a command).
     *
     * @param msg 要发送的聊天消息
     * <p>原文：message to print
     */
    public void chat(String msg);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 强制玩家执行某个命令.
     * <p>原文：
     * Makes the player perform the given command
     *
     * @param command 要执行的命令(不带'/')
     * <p>原文：Command to perform
     * @return JavaPlugin内的onCommand()方法的返回值
     * <p>原文：true if the command was successful, otherwise false
     */
    public boolean performCommand(String command);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断实体是否被方块支撑着(是否在地面上).
     * <p>
     * 该值是由客户端在每次移动后更新的状态.
     * <p>原文：
     * Returns true if the entity is supported by a block.
     *
     * This value is a state updated by the client after each movement.
     *
     * @return 如果实体在地面上则返回true
     * <p>原文：True if entity is on ground.
     * @deprecated 该值仅由客户端控制, 因此不可靠, 易于被伪造和/或因访问时机不同而不同步
     * <p>原文：This value is controlled only by the client and is therefore
     * unreliable and vulnerable to spoofing and/or desync depending on the
     * context/time which it is accessed
     */
    @Override
    @Deprecated(since = "1.16.1")
    public boolean isOnGround();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断玩家是否在潜行中.
     * <p>原文：
     * Returns if the player is in sneak mode
     *
     * @return 如果在潜行模式返回true
     * <p>原文：true if player is in sneak mode
     */
    @Override // Paper
    public boolean isSneaking();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家潜行模式开启/取消.
     * <p>原文：
     * Sets the sneak mode the player
     *
     * @param sneak true表示在潜行,false反之
     * <p>原文：true if player should appear sneaking
     */
    @Override // Paper
    public void setSneaking(boolean sneak);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断玩家是否在疾跑.
     * <p>原文：
     * Gets whether the player is sprinting or not.
     *
     * @return true表示玩家在疾跑,false反之
     * <p>原文：true if player is sprinting.
     */
    public boolean isSprinting();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家疾跑状态开启/取消.
     * <p>原文：
     * Sets whether the player is sprinting or not.
     *
     * @param sprinting true表示在疾跑,false反之
     * <p>原文：true if the player should be sprinting
     */
    public void setSprinting(boolean sprinting);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 保存玩家数据(位置,血量,背包,移动方向
     * 及其他信息至在 &lt;主世界&gt;/playerdata 文件夹中的"玩家名.dat"文件).
     * <p>原文：
     * Saves the players current location, health, inventory, motion, and
     * other information into the &lt;uuid&gt;.dat file, in the
     * &lt;level-name&gt;/playerdata/ folder.
     */
    public void saveData();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 加载上一次保存的数据(从在 &lt;主世界&gt;/playerdata 文件夹中的玩家名.dat文件中加载
     * 位置,血量,背包,移动方向及其他信息).<p>
     * 这将会覆盖当前内存中的数据.
     * <p>原文：
     * Loads the players current location, health, inventory, motion, and
     * other information from the &lt;uuid&gt;.dat file, in the
     * &lt;level-name&gt;/playerdata/ folder.
     * <p>
     * Note: This will overwrite the players current inventory, health,
     * motion, etc, with the state from the saved dat file.
     */
    public void loadData();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 是否忽略该玩家.如果设置为true,不需要该玩家睡觉,只需其他人睡觉,时间也能改变. <p>
     * 如果所有玩家都把这个项设置为true但是没有人睡觉,则什么事也不会发生.
     * <p>原文：
     * Sets whether the player is ignored as not sleeping. If everyone is
     * either sleeping or has this flag set, then time will advance to the
     * next day. If everyone has this flag set but no one is actually in bed,
     * then nothing will happen.
     *
     * @param isSleeping 是否忽略该玩家
     * <p>原文：Whether to ignore.
     */
    public void setSleepingIgnored(boolean isSleeping);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查看{@link #setSleepingIgnored(boolean) }方法所设置的值. <p>
     * 默认为false.
     * <p>原文：
     * Returns whether the player is sleeping ignored.
     *
     * @return 判断是否全部睡觉时是否忽略该玩家
     * <p>原文：Whether player is ignoring sleep.
     */
    public boolean isSleepingIgnored();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家的重生位置, 如果没有有效的重生点则返回null.
     * <p>原文：
     * Gets the Location where the player will spawn at, {@code null} if they
     * don't have a valid respawn point.
     * <br>
     * Unlike offline players, the location if found will be loaded to validate by default.
     *
     * @return 如果重生点存在则返回重生位置, 否则返回null
     * <p>原文：respawn location if exists, otherwise {@code null}.
     * @see #getRespawnLocation(boolean) for more fine-grained control over chunk loading and validation behaviour.
     */
    @Override
    default @Nullable Location getRespawnLocation() {
        return this.getRespawnLocation(true);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家在床上的重生位置.
     * <p>原文：
     * Sets the Location where the player will spawn at their bed.
     *
     * @param location 要设置的重生位置
     * <p>原文：where to set the respawn location
     * @see #setRespawnLocation(Location)
     * @deprecated 名称有误导性. 该方法设置玩家重生位置的范围更广, 不仅限于床.
     * <p>原文：Misleading name. This method sets the player's respawn
     * location more generally and is not limited to beds.
     */
    @Deprecated(since = "1.20.4")
    default void setBedSpawnLocation(@Nullable Location location) {
        this.setBedSpawnLocation(location, false);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家的重生位置.
     * <p>原文：
     * Sets the Location where the player will respawn.
     *
     * @param location 要设置的重生位置
     * <p>原文：where to set the respawn location
     */
    default void setRespawnLocation(@Nullable Location location) {
        this.setRespawnLocation(location, false);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家在床上的重生位置.
     * <p>原文：
     * Sets the Location where the player will spawn at their bed.
     *
     * @param location 要设置的重生位置
     * <p>原文：where to set the respawn location
     * @param force 是否强制设置重生位置, 即使没有有效的床
     * <p>原文：whether to forcefully set the respawn location even if a
     *     valid bed is not present
     * @see #setRespawnLocation(Location, boolean)
     * @deprecated 名称有误导性. 该方法设置玩家重生位置的范围更广, 不仅限于床.
     * <p>原文：Misleading name. This method sets the player's respawn
     * location more generally and is not limited to beds.
     */
    @Deprecated(since = "1.20.4")
    default void setBedSpawnLocation(@Nullable Location location, boolean force) {
        this.setRespawnLocation(location, force);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家的重生位置.
     * <p>原文：
     * Sets the Location where the player will respawn.
     *
     * @param location 要设置的重生位置
     * <p>原文：where to set the respawn location
     * @param force 是否强制设置重生位置, 即使没有有效的重生点
     * <p>原文：whether to forcefully set the respawn location even if a
     *     valid respawn point is not present
     */
    public void setRespawnLocation(@Nullable Location location, boolean force);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前与该实体关联的末影珍珠.
     * <p>
     * 返回的列表不会直接链接到实体当前的末影珍珠, 且不保证其可变性.
     * <p>原文：
     * Gets the ender pearls currently associated with this entity.
     * <p>
     * The returned list will not be directly linked to the entity's current
     * pearls, and no guarantees are made as to its mutability.
     *
     * @return 对应于当前末影珍珠的实体集合
     * <p>原文：collection of entities corresponding to current pearls.
     */
    @ApiStatus.Experimental
    public Collection<EnderPearl> getEnderPearls();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家当前的移动输入, 即玩家最后提供的输入.
     * <br>
     * <b>注意: 这可能并不总是与玩家当前的移动一致.</b>
     * <p>原文：
     * Gets the current movement input, as last provided by the player.
     * <br>
     * <b>Note: that this may not always be consistent with the current movement
     * of the player.</b>
     *
     * @return 当前输入
     * <p>原文：current input
     */
    public Input getCurrentInput();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置处为玩家播放一个音符. <br>
     * This <i>will</i> work with cake.
     * <p>原文：
     * Play a note for the player at a location. <br>
     * This <i>will</i> work with cake.
     *
     * @param loc 播放音符的位置
     * <p>原文：The location to play the note
     * @param instrument 乐器ID
     * <p>原文：The instrument ID.
     * @param note 音符ID.
     * <p>原文：The note ID.
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    default void playNote(Location loc, byte instrument, byte note) {
        this.playNote(loc, Instrument.getByType(instrument), new Note(note));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置处为玩家播放一个音符. <br>
     * This <i>will</i> work with cake.
     * <p>
     * 如果使用 {@link Instrument#CUSTOM_HEAD} 调用此方法, 将静默失败.
     * <p>原文：
     * Play a note for the player at a location. <br>
     * This <i>will</i> work with cake.
     * <p>
     * This method will fail silently when called with {@link Instrument#CUSTOM_HEAD}.
     *
     * @param loc 播放音符的位置
     * <p>原文：The location to play the note
     * @param instrument 乐器ID
     * <p>原文：The instrument
     * @param note 音符
     * <p>原文：The note
     */
    public void playNote(Location loc, Instrument instrument, Note note);


    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向玩家在某个位置播放一个声音. <p>
     * 当位置或声音为null或玩家的客户端没开启声音时,这个方法无效.
     * <p>原文：
     * Play a sound for a player at the location.
     * <p>
     * This function will fail silently if Location or Sound are null.
     *
     * @param location 要播放声音的位置
     * <p>原文：The location to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param volume 音量 默认 1F
     * <p>原文：The volume of the sound
     * @param pitch 音高 默认 0F
     * <p>原文：The pitch of the sound
     */
    default void playSound(Location location, Sound sound, float volume, float pitch) {
        this.playSound(location, sound, SoundCategory.MASTER, volume, pitch);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向玩家在某个位置播放一个声音. <p>
     * 当位置或声音为null或玩家的客户端没开启声音时,这个方法无效. 玩家客户端不存在指定声音本操作也将无效.
     * <p>原文：
     * Play a sound for a player at the location.
     * <p>
     * This function will fail silently if Location or Sound are null. No
     * sound will be heard by the player if their client does not have the
     * respective sound for the value passed.
     *
     * @param location 要播放声音的位置
     * <p>原文：The location to play the sound
     * @param sound 要播放的声音
     * <p>原文：The internal sound name to play
     * @param volume 音量 默认 1F
     * <p>原文：The volume of the sound
     * @param pitch 音高 默认 0F
     * <p>原文：The pitch of the sound
     */
    default void playSound(Location location, String sound, float volume, float pitch) {
        this.playSound(location, sound, SoundCategory.MASTER, volume, pitch);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向玩家在指定位置播放声音.
     * <p>
     * 当位置或声音为null或玩家的客户端没开启声音时,这个方法无效.
     * <p>原文：
     * Play a sound for a player at the location.
     * <p>
     * This function will fail silently if Location or Sound are null.
     *
     * @param location 声音播放位置
     * <p>原文：The location to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param category 声音的分类
     * <p>原文：The category of the sound
     * @param volume 音量
     * <p>原文：The volume of the sound
     * @param pitch 音调
     * <p>原文：The pitch of the sound
     */
    public void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向玩家在指定位置播放声音.
     * <p>
     * 当位置或声音为null或玩家的客户端没开启声音时,这个方法无效. 玩家客户端不存在指定声音本操作也将无效.
     * <p>原文：
     * Play a sound for a player at the location.
     * <p>
     * This function will fail silently if Location or Sound are null. No sound
     * will be heard by the player if their client does not have the respective
     * sound for the value passed.
     *
     * @param location 声音播放位置
     * <p>原文：The location to play the sound
     * @param sound 要播放的声音
     * <p>原文：The internal sound name to play
     * @param category 声音的分类
     * <p>原文：The category of the sound
     * @param volume 音量
     * <p>原文：The volume of the sound
     * @param pitch 音调
     * <p>原文：The pitch of the sound
     */
    public void playSound(Location location, String sound, SoundCategory category, float volume, float pitch);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置为玩家播放声音. 对于有多种变体的声音, 传递相同的种子将始终播放相同的变体.
     * <p>
     * 如果位置或声音为null, 此方法将静默失败.
     * <p>原文：
     * Play a sound for a player at the location. For sounds with multiple
     * variations passing the same seed will always play the same variation.
     * <p>
     * This function will fail silently if Location or Sound are null.
     *
     * @param location 播放声音的位置
     * <p>原文：The location to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param category 声音的分类
     * <p>原文：The category of the sound
     * @param volume 音量
     * <p>原文：The volume of the sound
     * @param pitch 音调
     * <p>原文：The pitch of the sound
     * @param seed 声音的种子
     * <p>原文：The seed for the sound
     */
    public void playSound(Location location, Sound sound, SoundCategory category, float volume, float pitch, long seed);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置为玩家播放声音. 对于有多种变体的声音, 传递相同的种子将始终播放相同的变体.
     * <p>
     * 如果位置或声音为null, 此方法将静默失败. 如果玩家客户端不存在指定声音, 则不会播放任何声音.
     * <p>原文：
     * Play a sound for a player at the location. For sounds with multiple
     * variations passing the same seed will always play the same variation.
     * <p>
     * This function will fail silently if Location or Sound are null. No sound
     * will be heard by the player if their client does not have the respective
     * sound for the value passed.
     *
     * @param location 播放声音的位置
     * <p>原文：The location to play the sound
     * @param sound 要播放的声音的内部名称
     * <p>原文：The internal sound name to play
     * @param category 声音的分类
     * <p>原文：The category of the sound
     * @param volume 音量
     * <p>原文：The volume of the sound
     * @param pitch 音调
     * <p>原文：The pitch of the sound
     * @param seed 声音的种子
     * <p>原文：The seed for the sound
     */
    public void playSound(Location location, String sound, SoundCategory category, float volume, float pitch, long seed);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在实体所在位置为玩家播放声音.
     * <p>
     * 如果实体或声音为null, 此方法将静默失败.
     * <p>原文：
     * Play a sound for a player at the location of the entity.
     * <p>
     * This function will fail silently if Entity or Sound are null.
     *
     * @param entity 播放声音的实体
     * <p>原文：The entity to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param volume 音量
     * <p>原文：The volume of the sound
     * @param pitch 音调
     * <p>原文：The pitch of the sound
     */
    default void playSound(Entity entity, Sound sound, float volume, float pitch) {
        this.playSound(entity, sound, SoundCategory.MASTER, volume, pitch);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在实体所在位置为玩家播放声音.
     * <p>
     * 如果实体或声音为null, 此方法将静默失败.
     * <p>原文：
     * Play a sound for a player at the location of the entity.
     * <p>
     * This function will fail silently if Entity or Sound are null.
     *
     * @param entity 播放声音的实体
     * <p>原文：The entity to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param volume 音量
     * <p>原文：The volume of the sound
     * @param pitch 音调
     * <p>原文：The pitch of the sound
     */
    default void playSound(Entity entity, String sound, float volume, float pitch) {
        this.playSound(entity, sound, SoundCategory.MASTER, volume, pitch);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在实体所在位置为玩家播放声音.
     * <p>
     * 如果实体或声音为null, 此方法将静默失败.
     * <p>原文：
     * Play a sound for a player at the location of the entity.
     * <p>
     * This function will fail silently if Entity or Sound are null.
     *
     * @param entity 播放声音的实体
     * <p>原文：The entity to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param category 声音的分类
     * <p>原文：The category of the sound
     * @param volume 音量
     * <p>原文：The volume of the sound
     * @param pitch 音调
     * <p>原文：The pitch of the sound
     */
    public void playSound(Entity entity, Sound sound, SoundCategory category, float volume, float pitch);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在实体所在位置为玩家播放声音.
     * <p>
     * 如果实体或声音为null, 此方法将静默失败.
     * <p>原文：
     * Play a sound for a player at the location of the entity.
     * <p>
     * This function will fail silently if Entity or Sound are null.
     *
     * @param entity 播放声音的实体
     * <p>原文：The entity to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param category 声音的分类
     * <p>原文：The category of the sound
     * @param volume 音量
     * <p>原文：The volume of the sound
     * @param pitch 音调
     * <p>原文：The pitch of the sound
     */
    public void playSound(Entity entity, String sound, SoundCategory category, float volume, float pitch);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在实体所在位置为玩家播放声音. 对于有多种变体的声音, 传递相同的种子将始终播放相同的变体.
     * <p>
     * 如果实体或声音为null, 此方法将静默失败.
     * <p>原文：
     * Play a sound for a player at the location of the entity. For sounds with
     * multiple variations passing the same seed will always play the same variation.
     * <p>
     * This function will fail silently if Entity or Sound are null.
     *
     * @param entity 播放声音的实体
     * <p>原文：The entity to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param category 声音的分类
     * <p>原文：The category of the sound
     * @param volume 音量
     * <p>原文：The volume of the sound
     * @param pitch 音调
     * <p>原文：The pitch of the sound
     * @param seed 声音的种子
     * <p>原文：The seed for the sound
     */
    public void playSound(Entity entity, Sound sound, SoundCategory category, float volume, float pitch, long seed);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在实体所在位置为玩家播放声音. 对于有多种变体的声音, 传递相同的种子将始终播放相同的变体.
     * <p>
     * 如果实体或声音为null, 此方法将静默失败.
     * <p>原文：
     * Play a sound for a player at the location of the entity. For sounds with
     * multiple variations passing the same seed will always play the same variation.
     * <p>
     * This function will fail silently if Entity or Sound are null.
     *
     * @param entity 播放声音的实体
     * <p>原文：The entity to play the sound
     * @param sound 要播放的声音
     * <p>原文：The sound to play
     * @param category 声音的分类
     * <p>原文：The category of the sound
     * @param volume 音量
     * <p>原文：The volume of the sound
     * @param pitch 音调
     * <p>原文：The pitch of the sound
     * @param seed 声音的种子
     * <p>原文：The seed for the sound
     */
    public void playSound(Entity entity, String sound, SoundCategory category, float volume, float pitch, long seed);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 停止播放指定的声音.
     * <p>原文：
     * Stop the specified sound from playing.
     *
     * @param sound 指定声音
     * <p>原文：the sound to stop
     */
    default void stopSound(Sound sound) {
        this.stopSound(sound, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 停止播放指定的声音.
     * <p>原文：
     * Stop the specified sound from playing.
     *
     * @param sound 指定声音
     * <p>原文：the sound to stop
     */
    default void stopSound(String sound) {
        this.stopSound(sound, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 停止播放指定的声音.
     * <p>原文：
     * Stop the specified sound from playing.
     *
     * @param sound 指定声音
     * <p>原文：the sound to stop
     * @param category 声音类别
     * <p>原文：the category of the sound
     */
    default void stopSound(Sound sound, @Nullable SoundCategory category) {
        this.stopSound(sound.getKey().getKey(), category);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 停止播放指定的声音.
     * <p>原文：
     * Stop the specified sound from playing.
     *
     * @param sound 指定声音
     * <p>原文：the sound to stop
     * @param category 声音类别
     * <p>原文：the category of the sound
     */
    public void stopSound(String sound, @Nullable SoundCategory category);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 停止播放指定分类的声音.
     * <p>原文：
     * Stop the specified sound category from playing.
     *
     * @param category 要停止播放的声音分类
     * <p>原文：the sound category to stop
     */
    public void stopSound(SoundCategory category);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 停止播放所有声音.
     * <p>原文：
     * Stop all sounds from playing.
     */
    public void stopAllSounds();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在某个位置({@link Location})向玩家播放一个粒子效果({@link Effect}).
     * <p>原文：
     * Plays an effect to just this player.
     *
     * @param loc 要播放粒子效果的位置
     * <p>原文：the location to play the effect at
     * @param effect 要播放的粒子效果
     * <p>原文：the {@link Effect}
     * @param data 某些效果需要的附加值.
     * <p>原文：a data bit needed for some effects
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public void playEffect(Location loc, Effect effect, int data);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在某个位置({@link Location})向玩家播放一个粒子效果({@link Effect}).
     * <p>原文：
     * Plays an effect to just this player.
     *
     * @param <T> the data based on the type of the effect
     * @param loc 要播放粒子效果的位置
     * <p>原文：the location to play the effect at
     * @param effect 要播放的粒子效果
     * <p>原文：the {@link Effect}
     * @param data Effect.StepSound所需的附加值,一般为{@link Material}
     * <p>原文：a data bit needed for some effects
     */
    public <T> void playEffect(Location loc, Effect effect, @Nullable T data);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 强制玩家使用主手中的物品破坏方块.
     * <p>
     * 此方法会考虑附魔效果, 处理物品耐久度(如适用), 并根据玩家手中的工具/物品掉落经验和正确的物品.
     * <p>
     * 注意: 此方法会调用 {@link BlockBreakEvent}, 这意味着如果事件被第三方插件取消, 此方法可能无法成功破坏方块.
     * 如果在 BlockBreakEvent 监听器中运行此方法, 请注意可能会对同一 {@link Block} 产生递归调用.
     * <p>
     * 此外, 此方法掉落的物品会触发 {@link BlockDropItemEvent}(如果成功).
     * <p>
     * 方块必须与玩家处于同一世界.
     * <p>原文：
     * Force this player to break a Block using the item in their main hand.
     *
     * This method will respect enchantments, handle item durability (if
     * applicable) and drop experience and the correct items according to the
     * tool/item in the player's hand.
     * <p>
     * Note that this method will call a {@link BlockBreakEvent}, meaning that
     * this method may not be successful in breaking the block if the event was
     * cancelled by a third party plugin. Care should be taken if running this
     * method in a BlockBreakEvent listener as recursion may be possible if it
     * is invoked on the same {@link Block} being broken in the event.
     * <p>
     * Additionally, a {@link BlockDropItemEvent} is called for the items
     * dropped by this method (if successful).
     * <p>
     * The block must be in the same world as the player.
     *
     * @param block 要破坏的方块
     * <p>原文：the block to break
     * @return 如果方块被成功破坏返回true, 如果破坏失败返回false
     * <p>原文：true if the block was broken, false if the break failed
     */
    public boolean breakBlock(Block block);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向该玩家发送一个伪造的指定位置的方块({@link org.bukkit.block.Block})更改数据包.这不会改变世界中的方块.
     * <p>原文：
     * Send a block change. This fakes a block change packet for a user at a
     * certain location. This will not actually change the world in any way.
     *
     * @param loc 要改变的方块的位置
     * <p>原文：The location of the changed block
     * @param material 要改变成的方块的类型
     * <p>原文：The new block
     * @param data 要改变成的方块的副ID
     * <p>原文：The block data
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2")
    public void sendBlockChange(Location loc, Material material, byte data);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向该玩家发送一个伪造的指定位置的方块({@link org.bukkit.block.Block})更改数据包.这不会改变世界中的方块.
     * <p>原文：
     * Send a block change. This fakes a block change packet for a user at a
     * certain location. This will not actually change the world in any way.
     *
     * @param loc 要改变的方块的位置
     * <p>原文：The location of the changed block
     * @param block 新方块
     * <p>原文：The new block
     */
    public void sendBlockChange(Location loc, BlockData block);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向该玩家发送多个伪造的方块更改数据包. 这不会实际改变世界中的任何方块.
     * <p>
     * 根据集合中的方块数量, 此方法可能会向客户端发送多个数据包. 每个被修改的区块区域(16x16x16)需要发送一个数据包.
     * 即使在两个不同的区块区域中各只更改了一个方块, 也会发送两个数据包.
     * <p>
     * 此外, 此方法无法保证对客户端未加载的区块中的更改能够正常发送给玩家. 调用者有责任确保客户端在更改方块的范围内, 或处理由此引起的任何副作用.
     * <p>原文：
     * Send a multi-block change. This fakes a block change packet for a user
     * at multiple locations. This will not actually change the world in any
     * way.
     * <p>
     * This method may send multiple packets to the client depending on the
     * blocks in the collection. A packet must be sent for each chunk section
     * modified, meaning one packet for each 16x16x16 block area. Even if only
     * one block is changed in two different chunk sections, two packets will
     * be sent.
     * <p>
     * Additionally, this method cannot guarantee the functionality of changes
     * being sent to the player in chunks not loaded by the client. It is the
     * responsibility of the caller to ensure that the client is within range
     * of the changed blocks or to handle any side effects caused as a result.
     *
     * @param blocks 要发送给玩家的方块状态
     * <p>原文：the block states to send to the player
     */
    public void sendBlockChanges(Collection<BlockState> blocks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向该玩家发送多个伪造的方块更改数据包. 这不会实际改变世界中的任何方块.
     * <p>
     * 根据集合中的方块数量, 此方法可能会向客户端发送多个数据包. 每个被修改的区块区域(16x16x16)需要发送一个数据包.
     * 即使在两个不同的区块区域中各只更改了一个方块, 也会发送两个数据包.
     * <p>
     * 此外, 此方法无法保证对客户端未加载的区块中的更改能够正常发送给玩家. 调用者有责任确保客户端在更改方块的范围内, 或处理由此引起的任何副作用.
     * <p>原文：
     * Send a multi-block change. This fakes a block change packet for a user
     * at multiple locations. This will not actually change the world in any
     * way.
     * <p>
     * This method may send multiple packets to the client depending on the
     * blocks in the collection. A packet must be sent for each chunk section
     * modified, meaning one packet for each 16x16x16 block area. Even if only
     * one block is changed in two different chunk sections, two packets will
     * be sent.
     * <p>
     * Additionally, this method cannot guarantee the functionality of changes
     * being sent to the player in chunks not loaded by the client. It is the
     * responsibility of the caller to ensure that the client is within range
     * of the changed blocks or to handle any side effects caused as a result.
     *
     * @param blocks 要发送给玩家的方块状态
     * <p>原文：the block states to send to the player
     * @param suppressLightUpdates 在客户端更新方块时是否抑制光照更新
     * <p>原文：whether or not light updates should be
     * suppressed when updating the blocks on the client
     * @deprecated suppressLightUpdates 在 1.19.4 以上版本中不起作用
     * <p>原文：suppressLightUpdates is not functional in versions greater
     * than 1.19.4
     */
    @Deprecated(since = "1.20")
    default void sendBlockChanges(Collection<BlockState> blocks, boolean suppressLightUpdates) {
        this.sendBlockChanges(blocks);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 发送方块损坏效果. 这会伪造指定位置的方块破坏进度, 由该玩家作为来源. 这不会实际改变方块的破坏进度.
     * <p>原文：
     * Send block damage. This fakes block break progress at a certain location
     * sourced by this player. This will not actually change the block's break
     * progress in any way.
     *
     * @param loc 被损坏方块的位置
     * <p>原文：the location of the damaged block
     * @param progress 进度, 范围 0.0 - 1.0, 其中 0 表示无损坏, 1.0 表示完全损坏
     * <p>原文：the progress from 0.0 - 1.0 where 0 is no damage and
     * 1.0 is the most damaged
     */
    default void sendBlockDamage(Location loc, float progress) {
        this.sendBlockDamage(loc, progress, this.getEntityId());
    }

    // Paper start
    /**
     * Send multiple block changes. This fakes a multi block change packet for each
     * chunk section that a block change occurs. This will not actually change the world in any way.
     *
     * @param blockChanges A map of the positions you want to change to their new block data
     */
    void sendMultiBlockChange(Map<? extends io.papermc.paper.math.Position, BlockData> blockChanges);

    /**
     * Send multiple block changes. This fakes a multi block change packet for each
     * chunk section that a block change occurs. This will not actually change the world in any way.
     *
     * @param blockChanges A map of the positions you want to change to their new block data
     * @param suppressLightUpdates Whether to suppress light updates or not
     * @deprecated suppressLightUpdates is no longer available in 1.20+, use {@link #sendMultiBlockChange(Map)}
     */
    @Deprecated
    default void sendMultiBlockChange(Map<? extends io.papermc.paper.math.Position, BlockData> blockChanges, boolean suppressLightUpdates) {
        this.sendMultiBlockChange(blockChanges);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 发送方块损坏效果. 这会伪造指定位置的方块破坏进度, 由提供的实体作为来源. 这不会实际改变方块的破坏进度.
     * <p>
     * 在同一位置, 对于发送给玩家的每个不同的损坏来源, 都会显示一个带有给定进度的独立损坏覆盖层.
     * 这允许同时显示来自多个实体的不同进度的方块损坏.
     * <p>原文：
     * Send block damage. This fakes block break progress at a certain location
     * sourced by the provided entity. This will not actually change the block's
     * break progress in any way.
     * <p>
     * At the same location for each unique damage source sent to the player, a
     * separate damage overlay will be displayed with the given progress. This allows
     * for block damage at different progress from multiple entities at once.
     *
     * @param loc 被损坏方块的位置
     * <p>原文：the location of the damaged block
     * @param progress 进度, 范围 0.0 - 1.0, 其中 0 表示无损坏, 1.0 表示完全损坏
     * <p>原文：the progress from 0.0 - 1.0 where 0 is no damage and
     * 1.0 is the most damaged
     * @param source 损坏所属的实体
     * <p>原文：the entity to which the damage belongs
     */
    public void sendBlockDamage(Location loc, float progress, Entity source);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 发送方块损坏效果. 这会伪造指定位置的方块破坏进度, 由提供的实体ID作为来源. 这不会实际改变方块的破坏进度.
     * <p>
     * 在同一位置, 对于发送给玩家的每个不同的损坏来源, 都会显示一个带有给定进度的独立损坏覆盖层.
     * 这允许同时显示来自多个实体的不同进度的方块损坏.
     * <p>原文：
     * Send block damage. This fakes block break progress at a certain location
     * sourced by the provided entity id. This will not actually change the block's
     * break progress in any way.
     * <p>
     * At the same location for each unique damage source sent to the player, a
     * separate damage overlay will be displayed with the given progress. This allows
     * for block damage at different progress from multiple entities at once.
     *
     * @param loc 被损坏方块的位置
     * <p>原文：the location of the damaged block
     * @param progress 进度, 范围 0.0 - 1.0, 其中 0 表示无损坏, 1.0 表示完全损坏
     * <p>原文：the progress from 0.0 - 1.0 where 0 is no damage and
     * 1.0 is the most damaged
     * @param sourceId 损坏所属实体的实体ID. 可以是不与已存在或已加载实体直接关联的ID
     * <p>原文：the entity id of the entity to which the damage belongs.
     * Can be an id that does not associate directly with an existing or loaded entity.
     */
    public void sendBlockDamage(Location loc, float progress, int sourceId);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向玩家发送某个实体的盔甲槽变化数据包. 本方法可针对指定玩家伪造某个实体的盔甲,
     * 且不会实际改变指定实体的盔甲槽内容.
     * <p>原文：
     * Send an equipment change for the target entity. This will not
     * actually change the entity's equipment in any way.
     *
     * @param entity 玩家会看到哪个实体的变化
     * <p>原文：the entity whose equipment to change
     * @param slot 要伪造哪一盔甲槽的变化
     * <p>原文：the slot to change
     * @param item 玩家将看到的盔甲物品
     * <p>原文：the item to which the slot should be changed, or null to set
     * it to air
     */
    public void sendEquipmentChange(LivingEntity entity, EquipmentSlot slot, @Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 发送目标实体的多个装备槽变更数据包. 这不会实际改变实体的装备.
     * <p>原文：
     * Send multiple equipment changes for the target entity. This will not
     * actually change the entity's equipment in any way.
     *
     * @param entity 要更改装备的实体
     * <p>原文：the entity whose equipment to change
     * @param items 要更改的装备槽, 其中值为该槽位应更改到的物品. null值会将该槽位设为空气
     * <p>原文：the slots to change, where the values are the items to which
     * the slot should be changed. null values will set the slot to air, empty map is not allowed
     */
    public void sendEquipmentChange(LivingEntity entity, Map<EquipmentSlot, @Nullable ItemStack> items);

    // Paper start
    /**
     * Send a sign change. This fakes a sign change packet for a user at
     * a certain location. This will not actually change the world in any way.
     * This method will use a sign at the location's block or a faked sign
     * sent via
     * {@link #sendBlockChange(org.bukkit.Location, org.bukkit.Material, byte)}.
     * <p>
     * If the client does not have a sign at the given location it will
     * display an error message to the user.
     *
     * @param loc the location of the sign
     * @param lines the new text on the sign or null to clear it
     * @throws IllegalArgumentException if location is null
     * @throws IllegalArgumentException if lines is non-null and has a length less than 4
     * @deprecated Use {@link #sendBlockUpdate(Location, TileState)} by creating a new virtual
     * {@link org.bukkit.block.Sign} block state via {@link BlockData#createBlockState()}
     * (constructed e.g. via {@link Material#createBlockData()})
     */
    @Deprecated
    default void sendSignChange(Location loc, java.util.@Nullable List<? extends net.kyori.adventure.text.Component> lines) throws IllegalArgumentException {
        this.sendSignChange(loc, lines, DyeColor.BLACK);
    }

    /**
     * Send a sign change. This fakes a sign change packet for a user at
     * a certain location. This will not actually change the world in any way.
     * This method will use a sign at the location's block or a faked sign
     * sent via
     * {@link #sendBlockChange(org.bukkit.Location, org.bukkit.Material, byte)}.
     * <p>
     * If the client does not have a sign at the given location it will
     * display an error message to the user.
     *
     * @param loc the location of the sign
     * @param lines the new text on the sign or null to clear it
     * @param dyeColor the color of the sign
     * @throws IllegalArgumentException if location is null
     * @throws IllegalArgumentException if dyeColor is null
     * @throws IllegalArgumentException if lines is non-null and has a length less than 4
     * @deprecated Use {@link #sendBlockUpdate(Location, TileState)} by creating a new virtual
     * {@link org.bukkit.block.Sign} block state via {@link BlockData#createBlockState()}
     * (constructed e.g. via {@link Material#createBlockData()})
     */
    @Deprecated
    default void sendSignChange(Location loc, java.util.@Nullable List<? extends net.kyori.adventure.text.Component> lines, DyeColor dyeColor) throws IllegalArgumentException {
        this.sendSignChange(loc, lines, dyeColor, false);
    }

    /**
     * Send a sign change. This fakes a sign change packet for a user at
     * a certain location. This will not actually change the world in any way.
     * This method will use a sign at the location's block or a faked sign
     * sent via
     * {@link #sendBlockChange(org.bukkit.Location, org.bukkit.Material, byte)}.
     * <p>
     * If the client does not have a sign at the given location it will
     * display an error message to the user.
     *
     * @param loc the location of the sign
     * @param lines the new text on the sign or null to clear it
     * @param hasGlowingText whether the text of the sign should glow as if dyed with a glowing ink sac
     * @throws IllegalArgumentException if location is null
     * @throws IllegalArgumentException if dyeColor is null
     * @throws IllegalArgumentException if lines is non-null and has a length less than 4
     * @deprecated Use {@link #sendBlockUpdate(Location, TileState)} by creating a new virtual
     * {@link org.bukkit.block.Sign} block state via {@link BlockData#createBlockState()}
     * (constructed e.g. via {@link Material#createBlockData()})
     */
    @Deprecated
    default void sendSignChange(Location loc, java.util.@Nullable List<? extends net.kyori.adventure.text.Component> lines, boolean hasGlowingText) throws IllegalArgumentException {
        this.sendSignChange(loc, lines, DyeColor.BLACK, hasGlowingText);
    }

    /**
     * Send a sign change. This fakes a sign change packet for a user at
     * a certain location. This will not actually change the world in any way.
     * This method will use a sign at the location's block or a faked sign
     * sent via
     * {@link #sendBlockChange(org.bukkit.Location, org.bukkit.Material, byte)}.
     * <p>
     * If the client does not have a sign at the given location it will
     * display an error message to the user.
     *
     * @param loc the location of the sign
     * @param lines the new text on the sign or null to clear it
     * @param dyeColor the color of the sign
     * @param hasGlowingText whether the text of the sign should glow as if dyed with a glowing ink sac
     * @throws IllegalArgumentException if location is null
     * @throws IllegalArgumentException if dyeColor is null
     * @throws IllegalArgumentException if lines is non-null and has a length less than 4
     * @deprecated Use {@link #sendBlockUpdate(Location, TileState)} by creating a new virtual
     * {@link org.bukkit.block.Sign} block state via {@link BlockData#createBlockState()}
     * (constructed e.g. via {@link Material#createBlockData()})
     */
    @Deprecated
    void sendSignChange(Location loc, java.util.@Nullable List<? extends net.kyori.adventure.text.Component> lines, DyeColor dyeColor, boolean hasGlowingText)
        throws IllegalArgumentException;
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向该玩家发送一个伪造的牌子({@link org.bukkit.block.Sign})上的字的更改数据包.这不会改变世界中的任何方块. <p>
     * 如果那个位置没有牌子,这个方法将用{@link #sendBlockChange(org.bukkit.Location, org.bukkit.block.data.BlockData) }
     * 方法在那个位置伪造一个牌子然后更改它.<p>
     * 如果客户端认为在指定的位置没有牌子,则会显示一个错误给玩家.<p>
     * 要改变告示牌的所有属性(包括告示牌背面), 请使用
     * {@link #sendBlockUpdate(org.bukkit.Location, org.bukkit.block.TileState)}.
     * <p>原文：
     * Send a sign change. This fakes a sign change packet for a user at
     * a certain location. This will not actually change the world in any way.
     * This method will use a sign at the location's block or a faked sign
     * sent via
     * {@link #sendBlockChange(org.bukkit.Location, org.bukkit.block.data.BlockData)}.
     * <p>
     * If the client does not have a sign at the given location it will
     * display an error message to the user.
     * <p>
     * To change all attributes of a sign, including the back Side, use
     * {@link #sendBlockUpdate(org.bukkit.Location, org.bukkit.block.TileState)}.
     *
     * @param loc 要让玩家看起来改变了的牌子的位置
     * <p>原文：the location of the sign
     * @param lines null或大小等于4的String数组;数组中每个元素都代表一行
     * <p>原文：the new text on the sign or null to clear it
     * @throws IllegalArgumentException 如果location参数为null
     * <p>原文：if location is null
     * @throws IllegalArgumentException 如果location参数为null
     * <p>原文：if lines is non-null and has a length less than 4
     * @deprecated Use {@link #sendBlockUpdate(Location, TileState)} by creating a new virtual
     * {@link org.bukkit.block.Sign} block state via {@link BlockData#createBlockState()}
     * (constructed e.g. via {@link Material#createBlockData()})
     */
    @Deprecated // Paper
    public void sendSignChange(Location loc, @Nullable String @Nullable [] lines) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向该玩家发送一个伪造的牌子({@link org.bukkit.block.Sign})上的字的更改数据包.这不会改变世界中的任何方块. <p>
     * 如果那个位置没有牌子,这个方法将用{@link #sendBlockChange(org.bukkit.Location, org.bukkit.block.data.BlockData) }
     * 方法在那个位置伪造一个牌子然后更改它.<p>
     * 如果客户端认为在指定的位置没有牌子,则会显示一个错误给玩家.
     * <p>
     * 要改变告示牌的所有属性(包括告示牌背面), 请使用
     * {@link #sendBlockUpdate(org.bukkit.Location, org.bukkit.block.TileState)}.
     * <p>原文：
     * Send a sign change. This fakes a sign change packet for a user at
     * a certain location. This will not actually change the world in any way.
     * This method will use a sign at the location's block or a faked sign
     * sent via
     * {@link #sendBlockChange(org.bukkit.Location, org.bukkit.block.data.BlockData)}.
     * <p>
     * If the client does not have a sign at the given location it will
     * display an error message to the user.
     * <p>
     * To change all attributes of a sign, including the back Side, use
     * {@link #sendBlockUpdate(org.bukkit.Location, org.bukkit.block.TileState)}.
     *
     * @param loc 要让玩家看起来改变了的牌子的位置
     * <p>原文：the location of the sign
     * @param lines null或大小等于4的String数组;数组中每个元素都代表一行
     * <p>原文：the new text on the sign or null to clear it
     * @param dyeColor 告示牌的颜色(文字的颜色)
     * <p>原文：the color of the sign
     * @throws IllegalArgumentException 如果location参数为null
     * <p>原文：if location is null
     * @throws IllegalArgumentException 如果location参数为null
     * <p>原文：if dyeColor is null
     * @throws IllegalArgumentException 如果location参数为null
     * <p>原文：if lines is non-null and has a length less than 4
     * @deprecated Use {@link #sendBlockUpdate(Location, TileState)} by creating a new virtual
     * {@link org.bukkit.block.Sign} block state via {@link BlockData#createBlockState()}
     * (constructed e.g. via {@link Material#createBlockData()})
     */
    @Deprecated // Paper
    public void sendSignChange(Location loc, @Nullable String @Nullable [] lines, DyeColor dyeColor) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向该玩家发送一个伪造的牌子({@link org.bukkit.block.Sign})上的字的更改数据包.这不会改变世界中的任何方块. <p>
     * 如果那个位置没有牌子,这个方法将用{@link #sendBlockChange(org.bukkit.Location, org.bukkit.block.data.BlockData) }
     * 方法在那个位置伪造一个牌子然后更改它.<p>
     * 如果客户端认为在指定的位置没有牌子,则会显示一个错误给玩家.
     * <p>
     * 要改变告示牌的所有属性(包括告示牌背面), 请使用
     * {@link #sendBlockUpdate(org.bukkit.Location, org.bukkit.block.TileState)}.
     * <p>原文：
     * Send a sign change. This fakes a sign change packet for a user at
     * a certain location. This will not actually change the world in any way.
     * This method will use a sign at the location's block or a faked sign
     * sent via
     * {@link #sendBlockChange(org.bukkit.Location, org.bukkit.block.data.BlockData)}.
     * <p>
     * If the client does not have a sign at the given location it will
     * display an error message to the user.
     * <p>
     * To change all attributes of a sign, including the back Side, use
     * {@link #sendBlockUpdate(org.bukkit.Location, org.bukkit.block.TileState)}.
     *
     * @param loc 要让玩家看起来改变了的牌子的位置
     * <p>原文：the location of the sign
     * @param lines null或大小等于4的String数组;数组中每个元素都代表一行
     * <p>原文：the new text on the sign or null to clear it
     * @param dyeColor 告示牌的颜色(文字的颜色)
     * <p>原文：the color of the sign
     * @param hasGlowingText 文字是否发光
     * <p>原文：if the sign's text should be glowing
     * @throws IllegalArgumentException 如果location参数为null
     * <p>原文：if location is null
     * @throws IllegalArgumentException 如果location参数为null
     * <p>原文：if dyeColor is null
     * @throws IllegalArgumentException 如果location参数为null
     * <p>原文：if lines is non-null and has a length less than 4
     * @deprecated Use {@link #sendBlockUpdate(Location, TileState)} by creating a new virtual
     * {@link org.bukkit.block.Sign} block state via {@link BlockData#createBlockState()}
     * (constructed e.g. via {@link Material#createBlockData()})
     */
    @Deprecated // Paper
    public void sendSignChange(Location loc, @Nullable String @Nullable [] lines, DyeColor dyeColor, boolean hasGlowingText) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 发送一个 TileState 变更数据包. 这会在给定位置为用户伪造一个 TileState 变更. 这不会实际改变世界中的任何方块.
     * 此方法会使用该位置方块的 TileState, 或通过
     * {@link #sendBlockChange(org.bukkit.Location, org.bukkit.block.data.BlockData)} 发送伪造的 TileState.
     * <p>
     * 如果客户端在给定位置没有合适的方块实体, 可能会向用户显示错误消息.
     * <p>
     * 可以使用 {@link BlockData#createBlockState()} 创建 {@link BlockState}.
     * <p>原文：
     * Send a TileState change. This fakes a TileState change for a user at
     * the given location. This will not actually change the world in any way.
     * This method will use a TileState at the location's block or a faked TileState
     * sent via
     * {@link #sendBlockChange(org.bukkit.Location, org.bukkit.block.data.BlockData)}.
     * <p>
     * If the client does not have an appropriate tile at the given location it
     * may display an error message to the user.
     * <p>
     * {@link BlockData#createBlockState()} can be used to create a {@link BlockState}.
     *
     * @param loc 告示牌的位置
     * <p>原文：the location of the sign
     * @param tileState 方块实体状态
     * <p>原文：the tile state
     * @throws IllegalArgumentException 如果location为null
     * <p>原文：if location is null
     * @throws IllegalArgumentException 如果location为null
     * <p>原文：if tileState is null
     */
    public void sendBlockUpdate(Location loc, TileState tileState) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 变更目标实体的药水效果. 这不会实际改变实体的药水效果.
     * <p>
     * <b>注意:</b> 向玩家自身发送效果变更可能会在客户端上引起意外行为. 以这种方式发送的效果在其计时器到达0时也不会被移除, 可以使用
     * {@link #sendPotionEffectChangeRemove(LivingEntity, PotionEffectType)} 移除它们.
     * <p>原文：
     * Change a potion effect for the target entity. This will not actually
     * change the entity's potion effects in any way.
     * <p>
     * <b>Note:</b> Sending an effect change to a player for themselves may
     * cause unexpected behavior on the client. Effects sent this way will also
     * not be removed when their timer reaches 0, they can be removed with
     * {@link #sendPotionEffectChangeRemove(LivingEntity, PotionEffectType)}
     *
     * @param entity 要更改药水效果的实体
     * <p>原文：the entity whose potion effects to change
     * @param effect 要变更的效果
     * <p>原文：the effect to change
     */
    public void sendPotionEffectChange(LivingEntity entity, PotionEffect effect);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除目标实体的药水效果. 这不会实际改变实体的药水效果.
     * <p>
     * <b>注意:</b> 向玩家自身发送效果变更可能会在客户端上引起意外行为.
     * <p>原文：
     * Remove a potion effect for the target entity. This will not actually
     * change the entity's potion effects in any way.
     * <p>
     * <b>Note:</b> Sending an effect change to a player for themselves may
     * cause unexpected behavior on the client.
     *
     * @param entity 要移除药水效果的实体
     * <p>原文：the entity whose potion effects to change
     * @param type 要移除的效果类型
     * <p>原文：the effect type to remove
     */
    public void sendPotionEffectChangeRemove(LivingEntity entity, PotionEffectType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 渲染地图并将其完整地发送给玩家. 当不希望通过正常方式流式传输地图时, 可以使用此方法.
     * <p>原文：
     * Render a map and send it to the player in its entirety. This may be
     * used when streaming the map in the normal manner is not desirable.
     *
     * @param map 要发送的地图
     * <p>原文：The map to be sent
     */
    public void sendMap(MapView map);

    /**
     * Shows the player the win screen that normally is only displayed after one kills the ender dragon
     * and exits the end for the first time.
     * In vanilla, the win screen starts with a poem and then continues with the credits but its content can be
     * changed by using a resource pack.
     * <br>
     * Calling this method does not change the value of {@link #hasSeenWinScreen()}.
     * That means that the win screen is still displayed to a player if they leave the end for the first time, even though
     * they have seen it before because this method was called.
     * Note this method does not make the player invulnerable, which is normally expected when viewing credits.
     *
     * @see #hasSeenWinScreen()
     * @see #setHasSeenWinScreen(boolean)
     * @see <a href="https://minecraft.wiki/wiki/End_Poem#Technical_details">https://minecraft.wiki/wiki/End_Poem#Technical_details</a>
     */
    public void showWinScreen();

    /**
     * Returns whether this player has seen the win screen before.
     * When a player leaves the end the win screen is shown to them if they have not seen it before.
     *
     * @return Whether this player has seen the win screen before
     * @see #setHasSeenWinScreen(boolean)
     * @see #showWinScreen()
     * @see <a href="https://minecraft.wiki/wiki/End_Poem">https://minecraft.wiki/wiki/End_Poem</a>
     */
    public boolean hasSeenWinScreen();

    /**
     * Changes whether this player has seen the win screen before.
     * When a player leaves the end the win screen is shown to them if they have not seen it before.
     *
     * @param hasSeenWinScreen Whether this player has seen the win screen before
     * @see #hasSeenWinScreen()
     * @see #showWinScreen()
     * @see <a href="https://minecraft.wiki/wiki/End_Poem">https://minecraft.wiki/wiki/End_Poem</a>
     */
    public void setHasSeenWinScreen(boolean hasSeenWinScreen);

    /**
     * Permanently Bans the Profile and IP address currently used by the player.
     *
     * @param reason Reason for ban
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)} and {@link #banIp(String, Date, String, boolean)}
     */
    // For reference, Bukkit defines this as nullable, while they impl isn't, we'll follow API.
    @Deprecated(since = "1.20.4")
    public default org.bukkit.@Nullable BanEntry banPlayerFull(@Nullable String reason) {
        return this.banPlayerFull(reason, null, null);
    }

    /**
     * Permanently Bans the Profile and IP address currently used by the player.
     *
     * @param reason Reason for ban
     * @param source Source of ban, or null for default
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)} and {@link #banIp(String, Date, String, boolean)}
     */
    @Deprecated(since = "1.20.4")
    public default org.bukkit.@Nullable BanEntry banPlayerFull(@Nullable String reason, @Nullable String source) {
        return this.banPlayerFull(reason, null, source);
    }

    /**
     * Bans the Profile and IP address currently used by the player.
     *
     * @param reason Reason for Ban
     * @param expires When to expire the ban
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)} and {@link #banIp(String, Date, String, boolean)}
     */
    @Deprecated(since = "1.20.4")
    public default org.bukkit.@Nullable BanEntry banPlayerFull(@Nullable String reason, java.util.@Nullable Date expires) {
        return this.banPlayerFull(reason, expires, null);
    }

    /**
     * Bans the Profile and IP address currently used by the player.
     *
     * @param reason Reason for Ban
     * @param expires When to expire the ban
     * @param source Source of the ban, or null for default
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)} and {@link #banIp(String, Date, String, boolean)}
     */
    @Deprecated(since = "1.20.4")
    public default org.bukkit.@Nullable BanEntry banPlayerFull(@Nullable String reason, java.util.@Nullable Date expires, @Nullable String source) {
        this.banPlayer(reason, expires, source);
        return this.banPlayerIP(reason, expires, source, true);
    }

    /**
     * Permanently Bans the IP address currently used by the player.
     * Does not ban the Profile, use {@link #banPlayerFull(String, java.util.Date, String)}
     *
     * @param reason Reason for ban
     * @param kickPlayer Whether or not to kick the player afterwards
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)} and {@link #banIp(String, Date, String, boolean)}
     */
    @Deprecated(since = "1.20.4")
    public default org.bukkit.@Nullable BanEntry banPlayerIP(@Nullable String reason, boolean kickPlayer) {
        return this.banPlayerIP(reason, null, null, kickPlayer);
    }

    /**
     * Permanently Bans the IP address currently used by the player.
     * Does not ban the Profile, use {@link #banPlayerFull(String, java.util.Date, String)}
     * @param reason Reason for ban
     * @param source Source of ban, or null for default
     * @param kickPlayer Whether or not to kick the player afterwards
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)} and {@link #banIp(String, Date, String, boolean)}
     */
    @Deprecated(since = "1.20.4")
    public default org.bukkit.@Nullable BanEntry banPlayerIP(@Nullable String reason, @Nullable String source, boolean kickPlayer) {
        return this.banPlayerIP(reason, null, source, kickPlayer);
    }

    /**
     * Bans the IP address currently used by the player.
     * Does not ban the Profile, use {@link #banPlayerFull(String, java.util.Date, String)}
     * @param reason Reason for Ban
     * @param expires When to expire the ban
     * @param kickPlayer Whether or not to kick the player afterwards
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)} and {@link #banIp(String, Date, String, boolean)}
     */
    @Deprecated(since = "1.20.4")
    public default org.bukkit.@Nullable BanEntry banPlayerIP(@Nullable String reason, java.util.@Nullable Date expires, boolean kickPlayer) {
        return this.banPlayerIP(reason, expires, null, kickPlayer);
    }

    /**
     * Permanently Bans the IP address currently used by the player.
     * Does not ban the Profile, use {@link #banPlayerFull(String, java.util.Date, String)}
     *
     * @param reason Reason for ban
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)} and {@link #banIp(String, Date, String, boolean)}
     */
    @Deprecated(since = "1.20.4")
    public default org.bukkit.@Nullable BanEntry banPlayerIP(@Nullable String reason) {
        return this.banPlayerIP(reason, null, null);
    }

    /**
     * Permanently Bans the IP address currently used by the player.
     * Does not ban the Profile, use {@link #banPlayerFull(String, java.util.Date, String)}
     * @param reason Reason for ban
     * @param source Source of ban, or null for default
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)} and {@link #banIp(String, Date, String, boolean)}
     */
    @Deprecated(since = "1.20.4")
    public default org.bukkit.@Nullable BanEntry banPlayerIP(@Nullable String reason, @Nullable String source) {
        return this.banPlayerIP(reason, null, source);
    }

    /**
     * Bans the IP address currently used by the player.
     * Does not ban the Profile, use {@link #banPlayerFull(String, java.util.Date, String)}
     * @param reason Reason for Ban
     * @param expires When to expire the ban
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)} and {@link #banIp(String, Date, String, boolean)}
     */
    @Deprecated(since = "1.20.4")
    public default org.bukkit.@Nullable BanEntry banPlayerIP(@Nullable String reason, java.util.@Nullable Date expires) {
        return this.banPlayerIP(reason, expires, null);
    }

    /**
     * Bans the IP address currently used by the player.
     * Does not ban the Profile, use {@link #banPlayerFull(String, java.util.Date, String)}
     * @param reason Reason for Ban
     * @param expires When to expire the ban
     * @param source Source of the ban or null for default
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)} and {@link #banIp(String, Date, String, boolean)}
     */
    @Deprecated(since = "1.20.4")
    public default org.bukkit.@Nullable BanEntry banPlayerIP(@Nullable String reason, java.util.@Nullable Date expires, @Nullable String source) {
        return this.banPlayerIP(reason, expires, source, true);
    }

    /**
     * Bans the IP address currently used by the player.
     * Does not ban the Profile, use {@link #banPlayerFull(String, java.util.Date, String)}
     * @param reason Reason for Ban
     * @param expires When to expire the ban
     * @param source Source of the ban or null for default
     * @param kickPlayer if the targeted player should be kicked
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)} and {@link #banIp(String, Date, String, boolean)}
     */
    @Deprecated(since = "1.20.4")
    public default org.bukkit.@Nullable BanEntry banPlayerIP(@Nullable String reason, java.util.@Nullable Date expires, @Nullable String source, boolean kickPlayer) {
        org.bukkit.BanEntry banEntry = org.bukkit.Bukkit.getServer().getBanList(org.bukkit.BanList.Type.IP).addBan(getAddress().getAddress().getHostAddress(), reason, expires, source);
        if (kickPlayer && isOnline()) {
            this.getPlayer().kickPlayer(reason);
        }

        return banEntry;
    }

    /**
     * Sends an Action Bar message to the client.
     *
     * Use Section symbols for legacy color codes to send formatting.
     *
     * @param message The message to send
     * @deprecated use {@link #sendActionBar(net.kyori.adventure.text.Component)}
     */
    @Deprecated
    public void sendActionBar(String message);

    /**
     * Sends an Action Bar message to the client.
     *
     * Use supplied alternative character to the section symbol to represent legacy color codes.
     *
     * @param alternateChar Alternate symbol such as '&amp;'
     * @param message The message to send
     * @deprecated use {@link #sendActionBar(net.kyori.adventure.text.Component)}
     */
    @Deprecated
    public void sendActionBar(char alternateChar, String message);

    /**
     * Sends an Action Bar message to the client.
     *
     * @param message The components to send
     * @deprecated use {@link #sendActionBar(net.kyori.adventure.text.Component)}
     */
    @Deprecated
    public void sendActionBar(net.md_5.bungee.api.chat.BaseComponent... message);

    /**
     * Sends the component to the player
     *
     * @param component the components to send
     * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
     */
    @Override
    @Deprecated
    public default void sendMessage(net.md_5.bungee.api.chat.BaseComponent component) {
        this.spigot().sendMessage(component);
    }

    /**
     * Sends an array of components as a single message to the player
     *
     * @param components the components to send
     * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
     */
    @Override
    @Deprecated
    public default void sendMessage(net.md_5.bungee.api.chat.BaseComponent... components) {
        this.spigot().sendMessage(components);
    }

    /**
     * Sends an array of components as a single message to the specified screen position of this player
     *
     * @deprecated This is unlikely the API you want to use. See {@link #sendActionBar(String)} for a more proper Action Bar API. This deprecated API may send unsafe items to the client.
     * @param position the screen position
     * @param components the components to send
     */
    @Deprecated
    public default void sendMessage(net.md_5.bungee.api.ChatMessageType position, net.md_5.bungee.api.chat.BaseComponent... components) {
        this.spigot().sendMessage(position, components);
    }

    /**
     * Set the text displayed in the player list header and footer for this player
     *
     * @param header content for the top of the player list
     * @param footer content for the bottom of the player list
     * @deprecated in favour of {@link #sendPlayerListHeaderAndFooter(net.kyori.adventure.text.Component, net.kyori.adventure.text.Component)}
     */
    @Deprecated
    public void setPlayerListHeaderFooter(net.md_5.bungee.api.chat.BaseComponent @Nullable [] header, net.md_5.bungee.api.chat.BaseComponent @Nullable [] footer);

    /**
     * Set the text displayed in the player list header and footer for this player
     *
     * @param header content for the top of the player list
     * @param footer content for the bottom of the player list
     * @deprecated in favour of {@link #sendPlayerListHeaderAndFooter(net.kyori.adventure.text.Component, net.kyori.adventure.text.Component)}
     */
    @Deprecated
    public void setPlayerListHeaderFooter(net.md_5.bungee.api.chat.@Nullable BaseComponent header, net.md_5.bungee.api.chat.@Nullable BaseComponent footer);

    /**
     * Update the times for titles displayed to the player
     *
     * @param fadeInTicks  ticks to fade-in
     * @param stayTicks    ticks to stay visible
     * @param fadeOutTicks ticks to fade-out
     * @deprecated Use {@link #showTitle(net.kyori.adventure.title.Title)} or {@link #sendTitlePart(net.kyori.adventure.title.TitlePart, Object)}
     */
    @Deprecated
    public void setTitleTimes(int fadeInTicks, int stayTicks, int fadeOutTicks);

    /**
     * Update the subtitle of titles displayed to the player
     *
     * @param subtitle Subtitle to set
     * @deprecated Use {@link #showTitle(net.kyori.adventure.title.Title)} or {@link #sendTitlePart(net.kyori.adventure.title.TitlePart, Object)}
     */
    @Deprecated
    public void setSubtitle(net.md_5.bungee.api.chat.BaseComponent[] subtitle);

    /**
     * Update the subtitle of titles displayed to the player
     *
     * @param subtitle Subtitle to set
     * @deprecated Use {@link #showTitle(net.kyori.adventure.title.Title)} or {@link #sendTitlePart(net.kyori.adventure.title.TitlePart, Object)}
     */
    @Deprecated
    public void setSubtitle(net.md_5.bungee.api.chat.BaseComponent subtitle);

    /**
     * Show the given title to the player, along with the last subtitle set, using the last set times
     *
     * @param title Title to set
     * @deprecated Use {@link #showTitle(net.kyori.adventure.title.Title)} or {@link #sendTitlePart(net.kyori.adventure.title.TitlePart, Object)}
     */
    @Deprecated
    public void showTitle(net.md_5.bungee.api.chat.@Nullable BaseComponent[] title);

    /**
     * Show the given title to the player, along with the last subtitle set, using the last set times
     *
     * @param title Title to set
     * @deprecated Use {@link #showTitle(net.kyori.adventure.title.Title)} or {@link #sendTitlePart(net.kyori.adventure.title.TitlePart, Object)}
     */
    @Deprecated
    public void showTitle(net.md_5.bungee.api.chat.@Nullable BaseComponent title);

    /**
     * Show the given title and subtitle to the player using the given times
     *
     * @param title        big text
     * @param subtitle     little text under it
     * @param fadeInTicks  ticks to fade-in
     * @param stayTicks    ticks to stay visible
     * @param fadeOutTicks ticks to fade-out
     * @deprecated Use {@link #showTitle(net.kyori.adventure.title.Title)} or {@link #sendTitlePart(net.kyori.adventure.title.TitlePart, Object)}
     */
    @Deprecated
    public void showTitle(net.md_5.bungee.api.chat.@Nullable BaseComponent[] title, net.md_5.bungee.api.chat.@Nullable BaseComponent[] subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks);

    /**
     * Show the given title and subtitle to the player using the given times
     *
     * @param title        big text
     * @param subtitle     little text under it
     * @param fadeInTicks  ticks to fade-in
     * @param stayTicks    ticks to stay visible
     * @param fadeOutTicks ticks to fade-out
     * @deprecated Use {@link #showTitle(net.kyori.adventure.title.Title)} or {@link #sendTitlePart(net.kyori.adventure.title.TitlePart, Object)}
     */
    @Deprecated
    public void showTitle(net.md_5.bungee.api.chat.@Nullable BaseComponent title, net.md_5.bungee.api.chat.@Nullable BaseComponent subtitle, int fadeInTicks, int stayTicks, int fadeOutTicks);

    /**
     * Show the title to the player, overriding any previously displayed title.
     *
     * <p>This method overrides any previous title, use {@link #updateTitle(com.destroystokyo.paper.Title)} to change the existing one.</p>
     *
     * @param title the title to send
     * @throws NullPointerException if the title is null
     * @deprecated Use {@link #showTitle(net.kyori.adventure.title.Title)} or {@link #sendTitlePart(net.kyori.adventure.title.TitlePart, Object)}
     */
    @Deprecated
    void sendTitle(com.destroystokyo.paper.Title title);

    /**
     * Show the title to the player, overriding any previously displayed title.
     *
     * <p>This method doesn't override previous titles, but changes their values.</p>
     *
     * @param title the title to send
     * @throws NullPointerException if title is null
     * @deprecated Use {@link #showTitle(net.kyori.adventure.title.Title)} or {@link #sendTitlePart(net.kyori.adventure.title.TitlePart, Object)}
     */
    @Deprecated
    void updateTitle(com.destroystokyo.paper.Title title);

    /**
     * Hide any title that is currently visible to the player
     *
     * @deprecated use {@link #clearTitle()}
     */
    @Deprecated
    public void hideTitle();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 发送受伤动画. 这会伪造来自给定偏航角(相对于玩家方向)的伤害效果.
     * <p>原文：
     * Send a hurt animation. This fakes incoming damage towards the player from
     * the given yaw relative to the player's direction.
     *
     * @param yaw 相对于玩家方向的偏航角(以度为单位), 其中 0 为玩家前方, 90 为右方, 180 为后方, 270 为左方
     * <p>原文：the yaw in degrees relative to the player's direction where 0
     * is in front of the player, 90 is to the right, 180 is behind, and 270 is
     * to the left
     */
    public void sendHurtAnimation(float yaw);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向玩家发送给定的服务器链接.
     * <p>原文：
     * Sends the given server links to the player.
     *
     * @param links 要发送的链接
     * <p>原文：links to send
     */
    public void sendLinks(ServerLinks links);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加在玩家输入消息时显示的自定义聊天补全建议.
     * <p>原文：
     * Add custom chat completion suggestions shown to the player while typing a
     * message.
     *
     * @param completions 要发送的补全建议
     * <p>原文：the completions to send
     */
    public void addCustomChatCompletions(Collection<String> completions);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除在玩家输入消息时显示的自定义聊天补全建议.
     * <p>
     * 在线玩家名称无法通过此方法移除. 此方法仅影响通过 {@link #addCustomChatCompletions(Collection)}
     * 或 {@link #setCustomChatCompletions(Collection)} 添加的自定义补全建议.
     * <p>原文：
     * Remove custom chat completion suggestions shown to the player while
     * typing a message.
     *
     * Online player names cannot be removed with this method. This will affect
     * only custom completions added by {@link #addCustomChatCompletions(Collection)}
     * or {@link #setCustomChatCompletions(Collection)}.
     *
     * @param completions 要移除的补全建议
     * <p>原文：the completions to remove
     */
    public void removeCustomChatCompletions(Collection<String> completions);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置在玩家输入消息时显示的聊天补全建议列表.
     * <p>
     * 如果之前已设置过补全建议, 此方法将移除所有旧的补全建议并替换为提供的补全建议.
     * <p>原文：
     * Set the list of chat completion suggestions shown to the player while
     * typing a message.
     * <p>
     * If completions were set previously, this method will remove them all and
     * replace them with the provided completions.
     *
     * @param completions 要设置的补全建议
     * <p>原文：the completions to set
     */
    public void setCustomChatCompletions(Collection<String> completions);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 刷新玩家的背包.确保玩家的背包和服务器内存中玩家的背包一致. <p>
     * 译注:在以前的版本(好吧我也不知道是什么版本)中假如不调用该方法当更改背包时会出现莫名其妙的事情,比如
     * 背包看起来是空的,点一下空的格突然出现了东西之类的....
     * <p>原文：
     * Forces an update of the player's entire inventory.
     */
    // @ApiStatus.Internal // Paper - is valid API
    public void updateInventory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该玩家之前的游戏模式.
     * <p>原文：
     * Gets this player's previous {@link GameMode}
     *
     * @return 之前的游戏模式或null
     * <p>原文：Previous game mode or null
     */
    @Nullable
    public GameMode getPreviousGameMode();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该玩家客户端的时间,单位为tick. <p>
     * 假如relative为true则玩家时间会随着世界时间变动而变动,并且保持一个差值,反之客户端时间固定不动. <p>
     * 注意!如果relative为true,那么time表示的就是客户端的时间与世界的时间之差(相对于世界的时间),而如果为false
     * 那么time表示的就是一个绝对的时间.
     * <p>原文：
     * Sets the current time on the player's client. When relative is true the
     * player's time will be kept synchronized to its world time with the
     * specified offset.
     * <p>
     * When using non relative time the player's time will stay fixed at the
     * specified time parameter. It's up to the caller to continue updating
     * the player's time. To restore player time to normal use
     * resetPlayerTime().
     *
     * @param time 绝对时间或与世界的时间之差,单位为tick
     * <p>原文：The current player's perceived time or the player's time
     *     offset from the server time.
     * @param relative 是否让玩家的时间和世界的时间保持一个差值
     * <p>原文：When true the player time is kept relative to its world
     *     time.
     */
    public void setPlayerTime(long time, boolean relative);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到玩家的客户端的当前时间,单位为tick.
     * <p>原文：
     * Returns the player's current timestamp.
     *
     * @return 玩家客户端的时间,单位为tick
     * <p>原文：The player's time
     */
    public long getPlayerTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回玩家的客户端的当前时间与玩家当前世界时间的差值. <p>
     * 如玩家时间是固定的，则返回玩家时间.
     * <p>原文：
     * Returns the player's current time offset relative to server time, or
     * the current player's fixed time if the player's time is absolute.
     *
     * @return 玩家当前时间与当前世界时间的差或玩家当前时间.单位为tick
     * <p>原文：The player's time
     */
    public long getPlayerTimeOffset();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果玩家时间于当前世界时间保持了一定的差值则返回true.
     * <p>原文：
     * Returns true if the player's time is relative to the server time,
     * otherwise the player's time is absolute and will not change its current
     * time unless done so with setPlayerTime().
     *
     * @return true if the player's time is relative to the server time.
     */
    public boolean isPlayerTimeRelative();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将玩家时间变为当前世界时间并与世界时间保持同步.
     * <p>原文：
     * Restores the normal condition where the player's time is synchronized
     * with the server time.
     * <p>
     * Equivalent to calling setPlayerTime(0, true).
     */
    public void resetPlayerTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家的客户端的天气. <p>
     * 使用{@link #resetPlayerWeather()}来恢复正常.
     * <p>原文：
     * Sets the type of weather the player will see.  When used, the weather
     * status of the player is locked until {@link #resetPlayerWeather()} is
     * used.
     *
     * @param type 要让玩家看到的天气
     * <p>原文：The WeatherType enum type the player should experience
     */
    public void setPlayerWeather(WeatherType type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到玩家所看到的天气.如果返回null,玩家的天气跟世界的天气一致.
     * <p>原文：
     * Returns the type of weather the player is currently experiencing.
     *
     * @return 玩家所看到的天气或null
     * <p>原文：The WeatherType that the player is currently experiencing or
     *     null if player is seeing server weather.
     */
    @Nullable
    public WeatherType getPlayerWeather();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 恢复玩家所看到的天气为世界的天气.
     * <p>原文：
     * Restores the normal condition where the player's weather is controlled
     * by server conditions.
     */
    public void resetPlayerWeather();

    // Paper start
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 给玩家指定经验.
     * <p>原文：
     * Gives the player the amount of experience specified.
     *
     * @param amount 要给的经验
     * <p>原文：Exp amount to give
     */
    public default void giveExp(int amount) {
        giveExp(amount, false);
    }
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家拾取经验球之间的冷却时间.
     * <p>原文：
     * Gets the player's cooldown between picking up experience orbs.
     *
     * @return 冷却时间(以tick为单位)
     * <p>原文：The cooldown in ticks
     */
    public int getExpCooldown();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家拾取经验球之间的冷却时间.
     * <p>
     * <strong>注意:</strong> 设置为0允许玩家立即拾取, 但设置为负值会导致玩家无法拾取经验球.
     * <p>
     * 调用此方法将触发 {@link PlayerExpCooldownChangeEvent} 事件.
     * <p>原文：
     * Sets the player's cooldown between picking up experience orbs.
     *
     * <strong>Note:</strong> Setting this to 0 allows the player to pick up
     * instantly, but setting this to a negative value will cause the player to
     * be unable to pick up xp-orbs.
     *
     * Calling this Method will result in {@link PlayerExpCooldownChangeEvent}
     * being called.
     *
     * @param ticks 冷却时间(以tick为单位)
     * <p>原文：The cooldown in ticks
     */
    public void setExpCooldown(int ticks);

    /**
     * Gives the player the amount of experience specified.
     *
     * @param amount Exp amount to give
     * @param applyMending Mend players items with mending, with same behavior as picking up orbs. calls {@link #applyMending(int)}
     */
    public void giveExp(int amount, boolean applyMending);

    /**
     * Applies the mending effect to any items just as picking up an orb would.
     *
     * Can also be called with {@link #giveExp(int, boolean)} by passing true to applyMending
     *
     * @param amount Exp to apply
     * @return the remaining experience
     */
    public int applyMending(int amount);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 增加玩家的等级. <p>
     * 支持负数(减少等级).
     * <p>原文：
     * Gives the player the amount of experience levels specified. Levels can
     * be taken by specifying a negative amount.
     *
     * @param amount 要增加的等级(正数)或要减少的等级(负数)
     * <p>原文：amount of experience levels to give or take
     */
    public void giveExpLevels(int amount);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到当前级别升到下一级别经验进度的百分比. <p>
     * 0表示毫无进展(0%), 0.99表示差一点点(99%), 1表示要升级了(100%).
     * <p>原文：
     * Gets the players current experience points towards the next level.
     * <p>
     * This is a percentage value. 0 is "no progress" and 1 is "next level".
     *
     * @return 离下一级的小数形式百分比
     * <p>原文：Current experience points
     */
    public float getExp();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前级别升到下一级别经验进度的百分比. <p>
     * 0表示毫无进展(0%), 0.99表示差一点点(99%), 1表示要升级了(100%). <p>
     * Sets the players current experience points towards the next level
     * <p>
     * This is a percentage value. 0 is "no progress" and 1 is "next level".
     * <p>原文：
     * Sets the players current experience points towards the next level
     * <p>
     * This is a percentage value. 0 is "no progress" and 1 is "next level".
     *
     * @param exp 新的离下一级的小数形式百分比
     * <p>原文：New experience points
     */
    public void setExp(float exp);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到玩家的等级.
     * <p>原文：
     * Gets the players current experience level
     *
     * @return 玩家的经验等级
     * <p>原文：Current experience level
     */
    public int getLevel();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家的等级 <p>.
     * <p>原文：
     * Sets the players current experience level
     *
     * @param level 新的等级
     * <p>原文：New experience level
     */
    public void setLevel(int level);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到玩家总共获得了多少经验(等级和经验).
     * <br>
     * 这个数值指玩家随着时间的推移收集的全部经验, 并且此值目前不会在客户端上显示.
     * <p>原文：
     * Gets the players total experience points.
     * <br>
     * This refers to the total amount of experience the player has collected
     * over time and is not currently displayed to the client.
     *
     * @return 玩家总共有多少经验
     * <p>原文：Current total experience points
     */
    public int getTotalExperience();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家的总经验值(等级和经验).
     * <br>
     * 这个数值指玩家随着时间的推移收集的全部经验, 并且此值目前不会在客户端上显示.
     * <p>原文：
     * Sets the players current experience points.
     * <br>
     * This refers to the total amount of experience the player has collected
     * over time and is not currently displayed to the client.
     *
     * @param exp 总经验值
     * <p>原文：New total experience points
     */
    public void setTotalExperience(int exp);
    // Paper start
    /**
     * Gets the players total amount of experience points he collected to reach the current level and level progress.
     *
     * <p>This method differs from {@link #getTotalExperience()} in that this method always returns an
     * up-to-date value that reflects the players{@link #getLevel() level} and {@link #getExp() level progress}</p>
     *
     * @return Current total experience points
     * @see #getLevel()
     * @see #getExp()
     * @see #setExperienceLevelAndProgress(int)
     */
    @org.jetbrains.annotations.Range(from = 0, to = Integer.MAX_VALUE) int calculateTotalExperiencePoints();

    /**
     * Updates the players level and level progress to that what would be reached when the total amount of experience
     * had been collected.
     *
     * <p>This method differs from {@link #setTotalExperience(int)} in that this method actually updates the
     * {@link #getLevel() level} and {@link #getExp() level progress} so that a subsequent call of
     * {@link #calculateTotalExperiencePoints()} yields the same amount of points that have been set</p>
     *
     * @param totalExperience New total experience points
     * @see #setLevel(int)
     * @see #setExp(float)
     * @see #calculateTotalExperiencePoints()
     */
    void setExperienceLevelAndProgress(@org.jetbrains.annotations.Range(from = 0, to = Integer.MAX_VALUE) int totalExperience);

    /**
     * Gets the total amount of experience points that are needed to reach the next level from zero progress towards it.
     *
     * <p>Can be used with {@link #getExp()} to calculate the current points for the current level and alike</p>
     *
     * @return The required experience points
     * @see #getExp()
     */
    int getExperiencePointsNeededForNextLevel();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 发送经验变更数据包. 这会伪造用户的经验变更. 这不会实际改变经验值.
     * <p>原文：
     * Send an experience change.
     *
     * This fakes an experience change packet for a user. This will not actually
     * change the experience points in any way.
     *
     * @param progress 经验进度百分比(0.0 到 1.0 之间)
     * <p>原文：Experience progress percentage (between 0.0 and 1.0)
     * @see #setExp(float)
     */
    public void sendExperienceChange(float progress);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 发送经验变更数据包. 这会伪造用户的经验变更. 这不会实际改变经验值.
     * <p>原文：
     * Send an experience change.
     *
     * This fakes an experience change packet for a user. This will not actually
     * change the experience points in any way.
     *
     * @param progress 新的经验进度百分比(0.0 到 1.0 之间)
     * <p>原文：New experience progress percentage (between 0.0 and 1.0)
     * @param level 新的经验等级
     * <p>原文：New experience level
     * @see #setExp(float)
     * @see #setLevel(int)
     */
    public void sendExperienceChange(float progress, int level);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断玩家是否能飞起来.
     * <p>
     * 译注:如果玩家确实在创造模式,那么一般返回true,除非被setAllowFlight(false).
     * <p>原文：
     * Determines if the Player is allowed to fly via jump key double-tap like
     * in creative mode.
     *
     * @return 玩家能不能飞起来
     * <p>原文：True if the player is allowed to fly.
     */
    public boolean getAllowFlight();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家是否能够飞起来(就像创造模式).
     * <p>
     * 译注:如果被设置为false,即便是创造模式也不能飞.
     * <p>原文：
     * Sets if the Player is allowed to fly via jump key double-tap like in
     * creative mode.
     *
     * @param flight 是否允许该玩家飞行.
     * <p>原文：If flight should be allowed.
     */
    public void setAllowFlight(boolean flight);

    // Paper start - flying fall damage
    /**
     * Allows you to enable fall damage while {@link #getAllowFlight()} is {@code true}
     *
     * @param flyingFallDamage Enables fall damage when {@link #getAllowFlight()} is {@code true}
     */
    public void setFlyingFallDamage(net.kyori.adventure.util.TriState flyingFallDamage);

    /**
     * Allows you to get if fall damage is enabled while {@link #getAllowFlight()} is {@code true}
     *
     * @return A tristate of whether fall damage is enabled, not set, or disabled when {@link #getAllowFlight()} is {@code true}
     */
    public net.kyori.adventure.util.TriState hasFlyingFallDamage();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让该玩家看不见某玩家.
     * <p>原文：
     * Hides a player from this player
     *
     * @param player 要让该玩家看不见的玩家.
     * <p>原文：Player to hide
     * @deprecated 另请参阅 {@link #hidePlayer(Plugin, Player)}
     * <p>原文：see {@link #hidePlayer(Plugin, Player)}
     */
    @Deprecated(since = "1.12.2")
    public void hidePlayer(Player player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让该玩家看不见某玩家.
     * <p>原文：
     * Hides a player from this player
     *
     * @param plugin Plugin 要隐藏该玩家的插件
     * <p>原文：Plugin that wants to hide the player
     * @param player Player 要让该玩家看不见的玩家.
     * <p>原文：Player to hide
     */
    default void hidePlayer(Plugin plugin, Player player) {
        this.hideEntity(plugin, player);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让该玩家能看到某玩家.
     * <p>原文：
     * Allows this player to see a player that was previously hidden
     *
     * @param player 要让该玩家看得见的玩家.
     * <p>原文：Player to show
     * @deprecated 另请参阅 {@link #showPlayer(Plugin, Player)}
     * <p>原文：see {@link #showPlayer(Plugin, Player)}
     */
    @Deprecated(since = "1.12.2")
    public void showPlayer(Player player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让该玩家能看到之前被隐藏的玩家. 如果另一个插件也隐藏了这个玩家,
     * 那么玩家将继续处于隐藏状态直至其他插件也调用了此方法.
     * <p>原文：
     * Allows this player to see a player that was previously hidden. If
     * another plugin had hidden the player too, then the player will
     * remain hidden until the other plugin calls this method too.
     *
     * @param plugin Plugin 要使某玩家现身的插件
     * <p>原文：Plugin that wants to show the player
     * @param player Player 使某玩家现身
     * <p>原文：Player to show
     */
    default void showPlayer(Plugin plugin, Player player) {
        this.showEntity(plugin, player);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查该玩家是否能看到某玩家.
     * <p>原文：
     * Checks to see if a player has been hidden from this player
     *
     * @param player 要检查该玩家是否能看到的玩家
     * <p>原文：Player to check
     * @return true表示能看到,false反之.
     * <p>原文：True if the provided player is not being hidden from this
     *     player
     */
    public boolean canSee(Player player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从视觉上隐藏一个实体.
     * <p>原文：
     * Visually hides an entity from this player.
     *
     * @param plugin 要隐藏此实体的插件实例
     * <p>原文：Plugin that wants to hide the entity
     * @param entity 要隐藏的实体
     * <p>原文：Entity to hide
     */
    public void hideEntity(Plugin plugin, Entity entity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许该玩家看到之前被隐藏的实体. 如果另一个插件也隐藏了该实体, 则该实体将继续处于隐藏状态, 直到另一个插件也调用此方法.
     * <p>原文：
     * Allows this player to see an entity that was previously hidden. If
     * another plugin had hidden the entity too, then the entity will
     * remain hidden until the other plugin calls this method too.
     *
     * @param plugin 要显示该实体的插件
     * <p>原文：Plugin that wants to show the entity
     * @param entity 要显示的实体
     * <p>原文：Entity to show
     */
    public void showEntity(Plugin plugin, Entity entity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查实体是否在视觉上对该玩家隐藏.
     * <p>原文：
     * Checks to see if an entity has been visually hidden from this player.
     *
     * @param entity 要检查的实体
     * <p>原文：Entity to check
     * @return 如果该实体未被隐藏则返回true
     * <p>原文：True if the provided entity is not being hidden from this
     *     player
     */
    public boolean canSee(Entity entity);

    // Paper start
    /**
     * Returns whether the {@code other} player is listed for {@code this}.
     *
     * @param other The other {@link Player} to check for listing.
     * @return True if the {@code other} player is listed for {@code this}.
     */
    boolean isListed(Player other);

    /**
     * Unlists the {@code other} player from the tablist.
     *
     * @param other The other {@link Player} to de-list.
     * @return True if the {@code other} player was listed.
     */
    boolean unlistPlayer(Player other);

    /**
     * Lists the {@code other} player.
     *
     * @param other The other {@link Player} to list.
     * @return True if the {@code other} player was not listed.
     * @throws IllegalStateException if this player can't see the other player
     * @see #canSee(Player)
     */
    boolean listPlayer(Player other);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查玩家是否在飞.
     * <p>原文：
     * Checks to see if this player is currently flying or not.
     *
     * @return true表示该玩家在飞,false反之.
     * <p>原文：True if the player is flying, else false.
     */
    public boolean isFlying();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该玩家飞行状态.
     * <p>原文：
     * Makes this player start or stop flying.
     *
     * @param value true表示正在飞
     * <p>原文：True to fly.
     */
    public void setFlying(boolean value);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该玩家飞行速度. <p>
     * 速度为-1~1之间,负数代表玩家会反着飞... <p>
     * 译注:不代表行走的速度!.
     * <p>原文：
     * Sets the speed at which a client will fly. Negative values indicate
     * reverse directions.
     *
     * @param value 新的飞行速度,在-1~1之间.
     * <p>原文：The new speed, from -1 to 1.
     * @throws IllegalArgumentException 如果速度不在-1~1之间则抛出.
     * <p>原文：If new speed is less than -1 or
     *     greater than 1
     */
    public void setFlySpeed(float value) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该玩家行走速度. <p>
     * 速度在-1~1之间,负数代表玩家会倒着走(整人专用233~)... <p>
     * 译注:不代表飞行的速度!
     * <p>原文：
     * Sets the speed at which a client will walk. Negative values indicate
     * reverse directions.
     *
     * @param value 新的行走速度.
     * <p>原文：The new speed, from -1 to 1.
     * @throws IllegalArgumentException 当速度不在-1~1时间则抛出
     * <p>原文：If new speed is less than -1 or
     *     greater than 1
     */
    public void setWalkSpeed(float value) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到该玩家飞行速度. <p>
     * 译注:不代表行走速度!
     * <p>原文：
     * Gets the current allowed speed that a client can fly.
     *
     * @return 飞行速度.取值-1~1.
     * <p>原文：The current allowed speed, from -1 to 1
     */
    public float getFlySpeed();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到行走速度. <p>
     * 译注:不代表飞行速度!
     * <p>原文：
     * Gets the current allowed speed that a client can walk.
     *
     * @return 行走速度,取值-1~1
     * <p>原文：The current allowed speed, from -1 to 1
     */
    public float getWalkSpeed();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置服务器材质包.(因为已过时所以不详解了). <p>
     * 介绍同{@link #setResourcePack(java.lang.String) }
     * Request that the player's client download and switch texture packs.
     * <p>
     * The player's client will download the new texture pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached the same
     * texture pack in the past, it will perform a file size check against
     * the response content to determine if the texture pack has changed and
     * needs to be downloaded again. When this request is sent for the very
     * first time from a given server, the client will first display a
     * confirmation GUI to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server textures on their client, in which
     *     case this method will have no affect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>There is no concept of resetting texture packs back to default
     *     within Minecraft, so players will have to relog to do so or you
     *     have to send an empty pack.
     * <li>The request is send with "null" as the hash. This might result
     *     in newer versions not loading the pack correctly.
     * </ul>
     * <p>原文：
     * Request that the player's client download and switch texture packs.
     * <p>
     * The player's client will download the new texture pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached the same
     * texture pack in the past, it will perform a file size check against
     * the response content to determine if the texture pack has changed and
     * needs to be downloaded again. When this request is sent for the very
     * first time from a given server, the client will first display a
     * confirmation GUI to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server textures on their client, in which
     *     case this method will have no effect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>The request is sent with "null" as the hash. This might result
     *     in newer versions not loading the pack correctly.
     * </ul>
     *
     * @param url The URL from which the client will download the texture
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long.
     * @deprecated Minecraft已经不使用材质包了,应该用{@link #setResourcePack(String)}设置资源包(额这不是一个意思么..).
     * <p>原文：Minecraft no longer uses textures packs. Instead you
     *     should use {@link #setResourcePack(UUID, String, byte[], net.kyori.adventure.text.Component, boolean)}.
     */
    @Deprecated(since = "1.7.2")
    default void setTexturePack(String url) {
        this.setResourcePack(url);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 请求玩家的客户端下载并且使用指定资源包. <p>
     * 玩家的客户端将在后台异步下载新的资源包,并且下载完成后会自动使用那个资源包.如果
     * 这个资源包已经下载好了,客户端先会检查给定URL的资源包跟已经下载的资源包是否一样.
     * 如果不一样就会重新下载,一样就直接使用. <p>
     * 在开始下载之前,客户端会显示一个GUI确定界面,提示要不要下载资源包.如果玩家选择不要,
     * 就不能下载. <p>
     * 注意:
     * <ul>
     * <li>如果玩家的客户端没有开启"使用服务器资源包"这个方法将失效. 使用
     * {@link PlayerResourcePackStatusEvent} 方法以推断玩家是否加载了你设置的资源包!
     * <li>在Minecraft中没有将资源包重置为默认的概念,所以玩家必须重新登陆才能这么做,或者你必须发送一个空白的资源包.
     * <li>请求以空字符串作hash发送. 这可能导致较新版本的客户端不能正确加载资源包.
     * </ul>
     * <p>原文：
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached the same
     * resource pack in the past, it will perform a file size check against
     * the response content to determine if the resource pack has changed and
     * needs to be downloaded again. When this request is sent for the very
     * first time from a given server, the client will first display a
     * confirmation GUI to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>The request is sent with empty string as the hash. This might result
     *     in newer versions not loading the pack correctly.
     * </ul>
     *
     * @param url 资源包的URL地址.只能包含US-ASCII字符并且使用RFC 1738编码.
     * <p>原文：The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @throws IllegalArgumentException 当URL为null时抛出
     * <p>原文：Thrown if the URL is null.
     * @throws IllegalArgumentException 当URL为null时抛出
     * <p>原文：Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     * @deprecated in favour of {@link #sendResourcePacks(net.kyori.adventure.resource.ResourcePackRequest)}
     */
    @Deprecated // Paper - adventure
    default void setResourcePack(String url) {
        this.setResourcePack(url, (byte[]) null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 请求玩家的客户端下载并且使用指定资源包. <p>
     * 玩家的客户端将在后台异步下载新的资源包,并且下载完成后会自动使用那个资源包.如果
     * 这个资源包已经下载好了,客户端先会检查给定URL的资源包跟已经下载的资源包是否一样.
     * 如果不一样就会重新下载,一样就直接使用. <p>
     * 在开始下载之前,客户端会显示一个GUI确定界面,提示要不要下载资源包.如果玩家选择不要,
     * 就不能下载. <p>
     * 注意:
     * <ul>
     * <li>如果玩家的客户端没有开启"使用服务器资源包"这个方法将失效. 使用
     * {@link PlayerResourcePackStatusEvent} 方法以推断玩家是否加载了你设置的资源包!
     * <li>在Minecraft中没有将资源包重置为默认的概念,所以玩家必须重新登陆才能这么做,或者你必须发送一个空白的资源包.
     * </ul>
     * <p>原文：
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached a
     * resource pack with the same hash in the past it will not download but
     * directly apply the cached pack. If the hash is null and the client has
     * downloaded and cached the same resource pack in the past, it will
     * perform a file size check against the response content to determine if
     * the resource pack has changed and needs to be downloaded again. When
     * this request is sent for the very first time from a given server, the
     * client will first display a confirmation GUI to the player before
     * proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>The request is sent with empty string as the hash when the hash is
     *     not provided. This might result in newer versions not loading the
     *     pack correctly.
     * </ul>
     *
     * @deprecated in favour of {@link #sendResourcePacks(net.kyori.adventure.resource.ResourcePackRequest)}
     * @param url 资源包的URL地址.只能包含US-ASCII字符并且使用RFC 1738编码.
     * <p>原文：The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @param hash 资源包文件的sha1哈希值，被用于正确地应用缓存版本的资源包而不需再重新下载(如果之前下载过).必须是20字节长!
     * <p>原文：The sha1 hash sum of the resource pack file which is used
     *     to apply a cached version of the pack directly without downloading
     *     if it is available. Hast to be 20 bytes long!
     * @throws IllegalArgumentException 当URL为null时抛出
     * <p>原文：Thrown if the URL is null.
     * @throws IllegalArgumentException 当URL为null时抛出
     * <p>原文：Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     * @throws IllegalArgumentException 当URL为null时抛出
     * <p>原文：Thrown if the hash is not 20 bytes
     *     long.
     */
    @Deprecated // Paper - adventure
    default void setResourcePack(String url, byte @Nullable [] hash) {
        this.setResourcePack(url, hash, false);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 请求玩家的客户端下载并切换资源包.
     * <p>
     * 玩家的客户端将在后台异步下载新的资源包, 下载完成后会自动切换到该资源包. 如果客户端之前下载并缓存过相同哈希值的资源包, 则不会下载, 而是直接应用缓存的资源包.
     * 如果哈希值为null且客户端之前下载并缓存过相同的资源包, 则会对响应内容执行文件大小检查, 以确定资源包是否已更改并需要重新下载.
     * 当此请求首次从给定服务器发送时, 客户端会在继续下载之前向玩家显示确认界面.
     * <p>
     * 注意:
     * <ul>
     * <li>玩家可以在客户端上禁用服务器资源, 在这种情况下此方法对他们无效. 使用
     * {@link PlayerResourcePackStatusEvent} 来判断玩家是否加载了资源包!
     * <li>要移除资源包, 可以使用 {@link #removeResourcePack(UUID)} 或 {@link #removeResourcePacks()}.
     * <li>当未提供哈希值时, 请求会以空字符串作为哈希值发送. 这可能导致较新版本无法正确加载资源包.
     * </ul>
     * <p>原文：
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached a
     * resource pack with the same hash in the past it will not download but
     * directly apply the cached pack. If the hash is null and the client has
     * downloaded and cached the same resource pack in the past, it will
     * perform a file size check against the response content to determine if
     * the resource pack has changed and needs to be downloaded again. When
     * this request is sent for the very first time from a given server, the
     * client will first display a confirmation GUI to the player before
     * proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>To remove a resource pack you can use
     *     {@link #removeResourcePacks(UUID, UUID...)} or {@link #clearResourcePacks()}.
     * <li>The request is sent with empty string as the hash when the hash is
     *     not provided. This might result in newer versions not loading the
     *     pack correctly.
     * </ul>
     *
     * @deprecated in favour of {@link #sendResourcePacks(net.kyori.adventure.resource.ResourcePackRequest)}
     * @param url 客户端下载资源包的URL地址. 字符串必须仅包含US-ASCII字符, 并应按照RFC 1738编码.
     * <p>原文：The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @param hash 资源包文件的SHA1哈希值, 用于在可用时直接应用缓存版本的资源包而无需重新下载. 必须为20字节长!
     * <p>原文：The sha1 hash sum of the resource pack file which is used
     *     to apply a cached version of the pack directly without downloading
     *     if it is available. Hast to be 20 bytes long!
     * @param prompt 要显示给客户端的可选自定义提示消息
     * <p>原文：The optional custom prompt message to be shown to client.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the URL is null.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the hash is not 20 bytes
     *     long.
     */
    @Deprecated // Paper - adventure
    default void setResourcePack(String url, byte @Nullable [] hash, @Nullable String prompt) {
        this.setResourcePack(url, hash, prompt, false);
    }

    // Paper start
    /**
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached a
     * resource pack with the same hash in the past it will not download but
     * directly apply the cached pack. If the hash is null and the client has
     * downloaded and cached the same resource pack in the past, it will
     * perform a file size check against the response content to determine if
     * the resource pack has changed and needs to be downloaded again. When
     * this request is sent for the very first time from a given server, the
     * client will first display a confirmation GUI to the player before
     * proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>To remove a resource pack you can use
     *     {@link #removeResourcePacks(UUID, UUID...)} or {@link #clearResourcePacks()}.
     * <li>The request is sent with empty string as the hash when the hash is
     *     not provided. This might result in newer versions not loading the
     *     pack correctly.
     * </ul>
     *
     * @param url The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @param hash The sha1 hash sum of the resource pack file which is used
     *     to apply a cached version of the pack directly without downloading
     *     if it is available. Hast to be 20 bytes long!
     * @param prompt The optional custom prompt message to be shown to client.
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     * @throws IllegalArgumentException Thrown if the hash is not 20 bytes
     *     long.
     * @see #sendResourcePacks(net.kyori.adventure.resource.ResourcePackRequest)
     */
    default void setResourcePack(final String url, final byte @Nullable [] hash, final net.kyori.adventure.text.@Nullable Component prompt) {
        this.setResourcePack(url, hash, prompt, false);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 请求玩家的客户端下载并切换资源包.
     * <p>
     * 玩家的客户端将在后台异步下载新的资源包, 下载完成后会自动切换到该资源包. 如果客户端之前下载并缓存过相同哈希值的资源包, 则不会下载, 而是直接应用缓存的资源包.
     * 如果哈希值为null且客户端之前下载并缓存过相同的资源包, 则会对响应内容执行文件大小检查, 以确定资源包是否已更改并需要重新下载.
     * 当此请求首次从给定服务器发送时, 客户端会在继续下载之前向玩家显示确认界面.
     * <p>
     * 注意:
     * <ul>
     * <li>玩家可以在客户端上禁用服务器资源, 在这种情况下此方法对他们无效. 使用
     * {@link PlayerResourcePackStatusEvent} 来判断玩家是否加载了资源包!
     * <li>要移除资源包, 可以使用 {@link #removeResourcePack(UUID)} 或 {@link #removeResourcePacks()}.
     * <li>当未提供哈希值时, 请求会以空字符串作为哈希值发送. 这可能导致较新版本无法正确加载资源包.
     * </ul>
     * <p>原文：
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached a
     * resource pack with the same hash in the past it will not download but
     * directly apply the cached pack. If the hash is null and the client has
     * downloaded and cached the same resource pack in the past, it will
     * perform a file size check against the response content to determine if
     * the resource pack has changed and needs to be downloaded again. When
     * this request is sent for the very first time from a given server, the
     * client will first display a confirmation GUI to the player before
     * proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>To remove a resource pack you can use
     *     {@link #removeResourcePacks(UUID, UUID...)} or {@link #clearResourcePacks()}.
     * <li>The request is sent with empty string as the hash when the hash is
     *     not provided. This might result in newer versions not loading the
     *     pack correctly.
     * </ul>
     *
     * @param url 客户端下载资源包的URL地址. 字符串必须仅包含US-ASCII字符, 并应按照RFC 1738编码.
     * <p>原文：The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @param hash 资源包文件的SHA1哈希值, 用于在可用时直接应用缓存版本的资源包而无需重新下载. 必须为20字节长!
     * <p>原文：The sha1 hash sum of the resource pack file which is used
     *     to apply a cached version of the pack directly without downloading
     *     if it is available. Hast to be 20 bytes long!
     * @param force 如果为true, 当客户端拒绝使用资源包时将断开与服务器的连接
     * <p>原文：If true, the client will be disconnected from the server
     *     when it declines to use the resource pack.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the URL is null.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the hash is not 20 bytes
     *     long.
     * @deprecated in favour of {@link #sendResourcePacks(net.kyori.adventure.resource.ResourcePackRequest)}
     */
    @Deprecated // Paper - adventure
    default void setResourcePack(String url, byte @Nullable [] hash, boolean force) {
        this.setResourcePack(url, hash, (String) null, force);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 请求玩家的客户端下载并切换资源包.
     * <p>
     * 玩家的客户端将在后台异步下载新的资源包, 下载完成后会自动切换到该资源包. 如果客户端之前下载并缓存过相同哈希值的资源包, 则不会下载, 而是直接应用缓存的资源包.
     * 如果哈希值为null且客户端之前下载并缓存过相同的资源包, 则会对响应内容执行文件大小检查, 以确定资源包是否已更改并需要重新下载.
     * 当此请求首次从给定服务器发送时, 客户端会在继续下载之前向玩家显示确认界面.
     * <p>
     * 注意:
     * <ul>
     * <li>玩家可以在客户端上禁用服务器资源, 在这种情况下此方法对他们无效. 使用
     * {@link PlayerResourcePackStatusEvent} 来判断玩家是否加载了资源包!
     * <li>要移除资源包, 可以使用 {@link #removeResourcePack(UUID)} 或 {@link #removeResourcePacks()}.
     * <li>当未提供哈希值时, 请求会以空字符串作为哈希值发送. 这可能导致较新版本无法正确加载资源包.
     * </ul>
     * <p>原文：
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached a
     * resource pack with the same hash in the past it will not download but
     * directly apply the cached pack. If the hash is null and the client has
     * downloaded and cached the same resource pack in the past, it will
     * perform a file size check against the response content to determine if
     * the resource pack has changed and needs to be downloaded again. When
     * this request is sent for the very first time from a given server, the
     * client will first display a confirmation GUI to the player before
     * proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>To remove a resource pack you can use
     *     {@link #removeResourcePacks(UUID, UUID...)} or {@link #clearResourcePacks()}.
     * <li>The request is sent with empty string as the hash when the hash is
     *     not provided. This might result in newer versions not loading the
     *     pack correctly.
     * </ul>
     *
     * @param url 客户端下载资源包的URL地址. 字符串必须仅包含US-ASCII字符, 并应按照RFC 1738编码.
     * <p>原文：The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @param hash 资源包文件的SHA1哈希值, 用于在可用时直接应用缓存版本的资源包而无需重新下载. 必须为20字节长!
     * <p>原文：The sha1 hash sum of the resource pack file which is used
     *     to apply a cached version of the pack directly without downloading
     *     if it is available. Hast to be 20 bytes long!
     * @param prompt 要显示给客户端的可选自定义提示消息
     * <p>原文：The optional custom prompt message to be shown to client.
     * @param force 如果为true, 当客户端拒绝使用资源包时将断开与服务器的连接
     * <p>原文：If true, the client will be disconnected from the server
     *     when it declines to use the resource pack.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the URL is null.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the hash is not 20 bytes
     *     long.
     * @deprecated in favour of {@link #sendResourcePacks(net.kyori.adventure.resource.ResourcePackRequest)}
     */
    @Deprecated // Paper
    public void setResourcePack(String url, byte @Nullable [] hash, @Nullable String prompt, boolean force);

    // Paper start
    /**
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached a
     * resource pack with the same hash in the past it will not download but
     * directly apply the cached pack. If the hash is null and the client has
     * downloaded and cached the same resource pack in the past, it will
     * perform a file size check against the response content to determine if
     * the resource pack has changed and needs to be downloaded again. When
     * this request is sent for the very first time from a given server, the
     * client will first display a confirmation GUI with a custom prompt
     * to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>To remove a resource pack you can use
     *     {@link #removeResourcePacks(UUID, UUID...)} or {@link #clearResourcePacks()}.
     * <li>The request is sent with empty string as the hash when the hash is
     *     not provided. This might result in newer versions not loading the
     *     pack correctly.
     * </ul>
     *
     * @param url The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @param hash The sha1 hash sum of the resource pack file which is used
     *     to apply a cached version of the pack directly without downloading
     *     if it is available. Hast to be 20 bytes long!
     * @param prompt The optional custom prompt message to be shown to client.
     * @param force If true, the client will be disconnected from the server
     *     when it declines to use the resource pack.
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     * @throws IllegalArgumentException Thrown if the hash is not 20 bytes
     *     long.
     * @see #sendResourcePacks(net.kyori.adventure.resource.ResourcePackRequest)
     */
    default void setResourcePack(final String url, final byte @Nullable [] hash, final net.kyori.adventure.text.@Nullable Component prompt, final boolean force) {
        this.setResourcePack(UUID.nameUUIDFromBytes(url.getBytes(java.nio.charset.StandardCharsets.UTF_8)), url, hash, prompt, force);
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 请求玩家的客户端下载并切换资源包.
     * <p>
     * 玩家的客户端将在后台异步下载新的资源包, 下载完成后会自动切换到该资源包. 如果客户端之前下载并缓存过相同哈希值的资源包, 则不会下载, 而是直接应用缓存的资源包.
     * 如果哈希值为null且客户端之前下载并缓存过相同的资源包, 则会对响应内容执行文件大小检查, 以确定资源包是否已更改并需要重新下载.
     * 当此请求首次从给定服务器发送时, 客户端会在继续下载之前向玩家显示确认界面.
     * <p>
     * 注意:
     * <ul>
     * <li>玩家可以在客户端上禁用服务器资源, 在这种情况下此方法对他们无效. 使用
     * {@link PlayerResourcePackStatusEvent} 来判断玩家是否加载了资源包!
     * <li>要移除资源包, 可以使用 {@link #removeResourcePack(UUID)} 或 {@link #removeResourcePacks()}.
     * <li>当未提供哈希值时, 请求会以空字符串作为哈希值发送. 这可能导致较新版本无法正确加载资源包.
     * </ul>
     * <p>原文：
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached a
     * resource pack with the same hash in the past it will not download but
     * directly apply the cached pack. If the hash is null and the client has
     * downloaded and cached the same resource pack in the past, it will
     * perform a file size check against the response content to determine if
     * the resource pack has changed and needs to be downloaded again. When
     * this request is sent for the very first time from a given server, the
     * client will first display a confirmation GUI to the player before
     * proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>To remove a resource pack you can use
     *     {@link #removeResourcePacks(UUID, UUID...)} or {@link #clearResourcePacks()}.
     * <li>The request is sent with empty string as the hash when the hash is
     *     not provided. This might result in newer versions not loading the
     *     pack correctly.
     * </ul>
     *
     * @param id 唯一的资源包ID
     * <p>原文：Unique resource pack ID.
     * @param url 客户端下载资源包的URL地址. 字符串必须仅包含US-ASCII字符, 并应按照RFC 1738编码.
     * <p>原文：The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @param hash 资源包文件的SHA1哈希值, 用于在可用时直接应用缓存版本的资源包而无需重新下载. 必须为20字节长!
     * <p>原文：The sha1 hash sum of the resource pack file which is used
     *     to apply a cached version of the pack directly without downloading
     *     if it is available. Hast to be 20 bytes long!
     * @param prompt 要显示给客户端的可选自定义提示消息
     * <p>原文：The optional custom prompt message to be shown to client.
     * @param force 如果为true, 当客户端拒绝使用资源包时将断开与服务器的连接
     * <p>原文：If true, the client will be disconnected from the server
     *     when it declines to use the resource pack.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the URL is null.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the hash is not 20 bytes
     *     long.
     * @deprecated in favour of {@link #sendResourcePacks(net.kyori.adventure.resource.ResourcePackRequest)}
     */
    @Deprecated // Paper - adventure
    public void setResourcePack(UUID id, String url, byte @Nullable [] hash, @Nullable String prompt, boolean force);

    // Paper start
    /**
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached a
     * resource pack with the same hash in the past it will not download but
     * directly apply the cached pack. If the hash is null and the client has
     * downloaded and cached the same resource pack in the past, it will
     * perform a file size check against the response content to determine if
     * the resource pack has changed and needs to be downloaded again. When
     * this request is sent for the very first time from a given server, the
     * client will first display a confirmation GUI to the player before
     * proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>To remove a resource pack you can use
     *     {@link #removeResourcePacks(UUID, UUID...)} or {@link #clearResourcePacks()}.
     * <li>The request is sent with empty string as the hash when the hash is
     *     not provided. This might result in newer versions not loading the
     *     pack correctly.
     * </ul>
     *
     * @param uuid Unique resource pack ID.
     * @param url The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @param hash The sha1 hash sum of the resource pack file which is used
     *     to apply a cached version of the pack directly without downloading
     *     if it is available. Hast to be 20 bytes long!
     * @param prompt The optional custom prompt message to be shown to client.
     * @param force If true, the client will be disconnected from the server
     *     when it declines to use the resource pack.
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     * @throws IllegalArgumentException Thrown if the hash is not 20 bytes
     *     long.
     * @see #sendResourcePacks(net.kyori.adventure.resource.ResourcePackRequest)
     */
    void setResourcePack(UUID uuid, String url, byte @Nullable [] hash, net.kyori.adventure.text.@Nullable Component prompt, boolean force);
    // Paper end

    // Paper start - more resource pack API
    /**
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached the same
     * resource pack in the past, it will perform a quick timestamp check
     * over the network to determine if the resource pack has changed and
     * needs to be downloaded again. When this request is sent for the very
     * first time from a given server, the client will first display a
     * confirmation GUI to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them.
     * <li>To remove a resource pack you can use
     *     {@link #removeResourcePacks(UUID, UUID...)} or {@link #clearResourcePacks()}.
     * </ul>
     *
     * @param url The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @param hash A 40 character hexadecimal and lowercase SHA-1 digest of
     *     the resource pack file.
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     */
    default void setResourcePack(final String url, final String hash) {
        this.setResourcePack(url, hash, false);
    }

    /**
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached the same
     * resource pack in the past, it will perform a quick timestamp check
     * over the network to determine if the resource pack has changed and
     * needs to be downloaded again. When this request is sent for the very
     * first time from a given server, the client will first display a
     * confirmation GUI to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them.
     * <li>To remove a resource pack you can use
     *     {@link #removeResourcePacks(UUID, UUID...)} or {@link #clearResourcePacks()}.
     * </ul>
     *
     * @param url The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @param hash A 40 character hexadecimal and lowercase SHA-1 digest of
     *     the resource pack file.
     * @param required Marks if the resource pack should be required by the client
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     */
    default void setResourcePack(final String url, final String hash, final boolean required) {
        this.setResourcePack(url, hash, required, null);
    }

    /**
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached the same
     * resource pack in the past, it will perform a quick timestamp check
     * over the network to determine if the resource pack has changed and
     * needs to be downloaded again. When this request is sent for the very
     * first time from a given server, the client will first display a
     * confirmation GUI to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them.
     * <li>To remove a resource pack you can use
     *     {@link #removeResourcePacks(UUID, UUID...)} or {@link #clearResourcePacks()}.
     * </ul>
     *
     * @param url The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @param hash A 40 character hexadecimal and lowercase SHA-1 digest of
     *     the resource pack file.
     * @param required Marks if the resource pack should be required by the client
     * @param resourcePackPrompt A Prompt to be displayed in the client request
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     */
    default void setResourcePack(final String url, final String hash, final boolean required, final net.kyori.adventure.text.@Nullable Component resourcePackPrompt) {
        this.setResourcePack(UUID.nameUUIDFromBytes(url.getBytes(java.nio.charset.StandardCharsets.UTF_8)), url, hash, resourcePackPrompt, required);
    }

    /**
     * Request that the player's client download and switch resource packs.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically switch to it once the
     * download is complete. If the client has downloaded and cached the same
     * resource pack in the past, it will perform a quick timestamp check
     * over the network to determine if the resource pack has changed and
     * needs to be downloaded again. When this request is sent for the very
     * first time from a given server, the client will first display a
     * confirmation GUI to the player before proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them.
     * <li>To remove a resource pack you can use
     *     {@link #removeResourcePacks(UUID, UUID...)} or {@link #clearResourcePacks()}.
     * </ul>
     *
     * @param uuid Unique resource pack ID.
     * @param url The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @param hash A 40 character hexadecimal and lowercase SHA-1 digest of
     *     the resource pack file.
     * @param resourcePackPrompt A Prompt to be displayed in the client request
     * @param required Marks if the resource pack should be required by the client
     * @throws IllegalArgumentException Thrown if the URL is null.
     * @throws IllegalArgumentException Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     */
    default void setResourcePack(final UUID uuid, final String url, final String hash, final net.kyori.adventure.text.@Nullable Component resourcePackPrompt, final boolean required) {
        this.sendResourcePacks(net.kyori.adventure.resource.ResourcePackRequest.resourcePackRequest()
            .required(required)
            .replace(true)
            .prompt(resourcePackPrompt)
            .packs(net.kyori.adventure.resource.ResourcePackInfo.resourcePackInfo(uuid, java.net.URI.create(url), hash))
        );
    }

    /**
     * Gets the most recent resource pack status from the player.
     *
     * @return the most recent status or null
     */
    org.bukkit.event.player.PlayerResourcePackStatusEvent.@Nullable Status getResourcePackStatus();

    /**
     * Gets the most recent pack hash from the player.
     *
     * @return the most recent hash or null
     * @deprecated This is no longer sent from the client and will always be null
     */
    @Deprecated(forRemoval = true, since = "1.13.2")
    @org.jetbrains.annotations.Contract("-> null")
    default @Nullable String getResourcePackHash() {
        return null;
    }

    /**
     * Gets if the last resource pack status from the player
     * was {@link org.bukkit.event.player.PlayerResourcePackStatusEvent.Status#SUCCESSFULLY_LOADED}.
     *
     * @return true if last status was successfully loaded
     */
    default boolean hasResourcePack() {
        return this.getResourcePackStatus() == org.bukkit.event.player.PlayerResourcePackStatusEvent.Status.SUCCESSFULLY_LOADED;
    }
    // Paper end - more resource pack API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 请求玩家的客户端下载并添加另一个资源包.
     * <p>
     * 玩家的客户端将在后台异步下载新的资源包, 下载完成后会自动添加到现有资源包中. 如果客户端之前下载并缓存过相同哈希值的资源包, 则不会下载, 而是直接应用缓存的资源包.
     * 如果哈希值为null且客户端之前下载并缓存过相同的资源包, 则会对响应内容执行文件大小检查, 以确定资源包是否已更改并需要重新下载.
     * 当此请求首次从给定服务器发送时, 客户端会在继续下载之前向玩家显示确认界面.
     * <p>
     * 注意:
     * <ul>
     * <li>玩家可以在客户端上禁用服务器资源, 在这种情况下此方法对他们无效. 使用
     * {@link PlayerResourcePackStatusEvent} 来判断玩家是否加载了资源包!
     * <li>要移除资源包, 可以使用 {@link #removeResourcePack(UUID)} 或 {@link #removeResourcePacks()}.
     * <li>当未提供哈希值时, 请求会以空字符串作为哈希值发送. 这可能导致较新版本无法正确加载资源包.
     * </ul>
     * <p>原文：
     * Request that the player's client download and include another resource pack.
     * <p>
     * The player's client will download the new resource pack asynchronously
     * in the background, and will automatically add to it once the
     * download is complete. If the client has downloaded and cached a
     * resource pack with the same hash in the past it will not download but
     * directly apply the cached pack. If the hash is null and the client has
     * downloaded and cached the same resource pack in the past, it will
     * perform a file size check against the response content to determine if
     * the resource pack has changed and needs to be downloaded again. When
     * this request is sent for the very first time from a given server, the
     * client will first display a confirmation GUI to the player before
     * proceeding with the download.
     * <p>
     * Notes:
     * <ul>
     * <li>Players can disable server resources on their client, in which
     *     case this method will have no effect on them. Use the
     *     {@link PlayerResourcePackStatusEvent} to figure out whether or not
     *     the player loaded the pack!
     * <li>To remove a resource pack you can use
     *     {@link #removeResourcePack(UUID)} or {@link #removeResourcePacks()}.
     * <li>The request is sent with empty string as the hash when the hash is
     *     not provided. This might result in newer versions not loading the
     *     pack correctly.
     * </ul>
     *
     * @param id 唯一的资源包ID
     * <p>原文：Unique resource pack ID.
     * @param url 客户端下载资源包的URL地址. 字符串必须仅包含US-ASCII字符, 并应按照RFC 1738编码.
     * <p>原文：The URL from which the client will download the resource
     *     pack. The string must contain only US-ASCII characters and should
     *     be encoded as per RFC 1738.
     * @param hash 资源包文件的SHA1哈希值, 用于在可用时直接应用缓存版本的资源包而无需重新下载. 必须为20字节长!
     * <p>原文：The sha1 hash sum of the resource pack file which is used
     *     to apply a cached version of the pack directly without downloading
     *     if it is available. Hast to be 20 bytes long!
     * @param prompt 要显示给客户端的可选自定义提示消息
     * <p>原文：The optional custom prompt message to be shown to client.
     * @param force 如果为true, 当客户端拒绝使用资源包时将断开与服务器的连接
     * <p>原文：If true, the client will be disconnected from the server
     *     when it declines to use the resource pack.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the URL is null.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the URL is too long. The
     *     length restriction is an implementation specific arbitrary value.
     * @throws IllegalArgumentException 如果URL为null
     * <p>原文：Thrown if the hash is not 20 bytes
     *     long.
     */
    public void addResourcePack(UUID id, String url, byte @Nullable [] hash, @Nullable String prompt, boolean force);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 请求玩家的客户端移除服务器发送的资源包.
     * <p>原文：
     * Request that the player's client remove a resource pack sent by the
     * server.
     *
     * @param id 资源包的ID
     * <p>原文：the id of the resource pack.
     * @throws IllegalArgumentException 如果ID为null
     * <p>原文：If the ID is null.
     * @see #removeResourcePacks(UUID, UUID...)
     */
    public void removeResourcePack(UUID id);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 请求玩家的客户端移除服务器发送的所有已加载的资源包.
     * <p>原文：
     * Request that the player's client remove all loaded resource pack sent by
     * the server.
     *
     * @see #clearResourcePacks()
     */
    public void removeResourcePacks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家的计分板.
     * <p>原文：
     * Gets the Scoreboard displayed to this player
     *
     * @return The current scoreboard seen by this player
     */
    public Scoreboard getScoreboard();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置玩家的计分板.
     * <p>原文：
     * Sets the player's visible Scoreboard.
     *
     * @param scoreboard 要设置成的计分板
     * <p>原文：New Scoreboard for the player
     * @throws IllegalArgumentException 如果计分板为null则抛出
     * <p>原文：if scoreboard is null
     * @throws IllegalArgumentException 如果计分板为null则抛出
     * <p>原文：if scoreboard was not created by the
     *     {@link org.bukkit.scoreboard.ScoreboardManager scoreboard manager}
     * @throws IllegalStateException 如果没有该玩家的数据,即该玩家没有进入过服务器则抛出.
     * <p>原文：if this is a player that is not logged
     *     yet or has logged out
     */
    public void setScoreboard(Scoreboard scoreboard) throws IllegalArgumentException, IllegalStateException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取对该玩家可见的 {@link WorldBorder}, 如果查看的是世界的世界边界则返回null.
     * <p>原文：
     * Gets the {@link WorldBorder} visible to this Player, or null if viewing
     * the world's world border.
     *
     * @return 玩家的世界边界
     * <p>原文：the player's world border
     */
    @Nullable
    public WorldBorder getWorldBorder();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置对该玩家可见的 {@link WorldBorder}.
     * <p>原文：
     * Sets the {@link WorldBorder} visible to this Player.
     *
     * @param border 要设置的边界, 或null表示设置为玩家当前世界的世界边界
     * <p>原文：the border to set, or null to set to the world border of
     * the player's current world
     * @throws UnsupportedOperationException 如果将边界设置为玩家当前不在的世界
     * <p>原文：if setting the border to that of
     * a world in which the player is not currently present.
     * @see Server#createWorldBorder()
     */
    public void setWorldBorder(@Nullable WorldBorder border);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向玩家发送健康状态更新. 这会调整客户端上的生命值、饥饿值和饱和度, 不会影响服务器上玩家的实际值.
     * 一旦服务器上这些值中的任何一个发生变化, 此方法发送的变更将不再可见.
     * <p>原文：
     * Send a health update to the player. This will adjust the health, food, and
     * saturation on the client and will not affect the player's actual values on
     * the server. As soon as any of these values change on the server, changes sent
     * by this method will no longer be visible.
     *
     * @param health 生命值. 如果为0.0, 客户端会认为玩家已死亡
     * <p>原文：the health. If 0.0, the client will believe it is dead
     * @param foodLevel 饥饿值
     * <p>原文：the food level
     * @param saturation 饱和度
     * <p>原文：the saturation
     */
    public void sendHealthUpdate(double health, int foodLevel, float saturation);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用已知的服务器值向玩家发送健康状态更新. 这会同步客户端上的生命值、饥饿值和饱和度, 因此在更改玩家的最大生命值属性时可能很有用.
     * <p>原文：
     * Send a health update to the player using its known server values. This will
     * synchronize the health, food, and saturation on the client and therefore may
     * be useful when changing a player's maximum health attribute.
     */
    public void sendHealthUpdate();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取客户端显示的玩家血量是否被"压缩"了. <p>
     * 译注:当玩家的最大血量过多时({@link #setMaxHealth(double) }),每一排血量将会被
     * 挤在一起,以免挡住玩家的视线,这就是"压缩".这个方法就是判断血量是否被压缩了.(完全没用的说..).
     * <p>原文：
     * Gets if the client is displayed a 'scaled' health, that is, health on a
     * scale from 0-{@link #getHealthScale()}.
     *
     * @return 客户端显示的血量是否被压缩了
     * <p>原文：if client health display is scaled
     * @see Player#setHealthScaled(boolean)
     */
    public boolean isHealthScaled();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置客户端是否要显示一个"压缩"了的血量. <p>
     * 显示的血量遵循一个规则: <code>显示的血量 =
     * getHealth() / getMaxHealth() * getHealthScale()</code>. <p>
     * 译注:当玩家的最大血量过多时({@link #setMaxHealth(double) }),每一排血量将会被
     * 挤在一起,以免挡住玩家的视线,这就是"压缩".这个方法就是设置血量是否要被"压缩".(完全没用的说..).
     * <p>原文：
     * Sets if the client is displayed a 'scaled' health, that is, health on a
     * scale from 0-{@link #getHealthScale()}.
     * <p>
     * Displayed health follows a simple formula <code>displayedHealth =
     * getHealth() / getMaxHealth() * getHealthScale()</code>.
     *
     * @param scale 血量是否要被压缩
     * <p>原文：if the client health display is scaled
     */
    public void setHealthScaled(boolean scale);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置客户端显示的血量的"压缩率". <p>
     * 显示的血量遵循一个规则: <code>显示的血量 =
     * getHealth() / getMaxHealth() * getHealthScale()</code>. <p>
     * 译注:当玩家的最大血量过多时({@link #setMaxHealth(double) }),每一排血量将会被
     * 挤在一起,以免挡住玩家的视线,这就是"压缩".这个方法就是设置血量的"压缩率".(完全没用的说..).
     * <p>原文：
     * Sets the number to scale health to for the client; this will also
     * {@link #setHealthScaled(boolean) setHealthScaled(true)}.
     * <p>
     * Displayed health follows a simple formula <code>displayedHealth =
     * getHealth() / getMaxHealth() * getHealthScale()</code>.
     *
     * @param scale 血量的"压缩率"
     * <p>原文：the number to scale health to
     * @throws IllegalArgumentException 如果scale &lt;0
     * <p>原文：if scale is &lt;0
     * @throws IllegalArgumentException 如果scale &lt;0
     * <p>原文：if scale is {@link Double#NaN}
     * @throws IllegalArgumentException 如果scale &lt;0
     * <p>原文：if scale is too high
     */
    public void setHealthScale(double scale) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取客户端显示的血量的"压缩率".  <p>
     * 详见{@link #setHealthScale(double) }
     * <p>原文：
     * Gets the number that health is scaled to for the client.
     *
     * @return 客户端显示的血量条的"压缩率"
     * <p>原文：the number that health would be scaled to for the client if
     *     HealthScaling is set to true
     * @see Player#setHealthScale(double)
     * @see Player#setHealthScaled(boolean)
     */
    public double getHealthScale();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取旁观者模式下镜头跟随的实体.
     * <p>原文：
     * Gets the entity which is followed by the camera when in
     * {@link GameMode#SPECTATOR}.
     *
     * @return 正在跟随的实体, 如果未跟随实体或不处于旁观者模式返回null
     * <p>原文：the followed entity, or null if not in spectator mode or not
     * following a specific entity.
     */
    @Nullable
    public Entity getSpectatorTarget();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置模式下镜头跟随的实体.
     * <p>原文：
     * Sets the entity which is followed by the camera when in
     * {@link GameMode#SPECTATOR}.
     *
     * @param entity 要跟随的实体, 设为null重置
     * <p>原文：the entity to follow or null to reset
     * @throws IllegalStateException 若玩家不处于
     * {@link GameMode#SPECTATOR 旁观者模式}
     * <p>原文：if the player is not in
     * {@link GameMode#SPECTATOR}
     */
    public void setSpectatorTarget(@Nullable Entity entity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向玩家发送屏幕标题.如果标题和副标题内容都为null, 那么标题将不会被发送出去, 玩家的屏幕也不会有变化.
     * 如果这些参数是空字符串(注意空字符串与null有区别), 那么玩家的屏幕将会被更新(本质上讲, 虽然看起来没啥变化)
     * 如果字符串包含多行文本, 那么只有第一行文本才会被发送出去.
     * 标题将以玩家客户端默认的淡入淡出时间显示.
     * <p>原文：
     * Sends a title and a subtitle message to the player. If either of these
     * values are null, they will not be sent and the display will remain
     * unchanged. If they are empty strings, the display will be updated as
     * such. If the strings contain a new line, only the first line will be
     * sent. The titles will be displayed with the client's default timings.
     *
     * @param title 标题文本
     * <p>原文：Title text
     * @param subtitle 副标题文本
     * <p>原文：Subtitle text
     * @deprecated API行为有所改变
     * <p>原文：Use {@link #showTitle(net.kyori.adventure.title.Title)} or {@link #sendTitlePart(net.kyori.adventure.title.TitlePart, Object)}
     */
    @Deprecated(since = "1.8.7")
    public void sendTitle(@Nullable String title, @Nullable String subtitle);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向玩家发送屏幕标题.如果标题和副标题内容都为null, 那么标题将不会被发送出去, 玩家的屏幕也不会有变化.
     * 如果这些参数是空字符串(注意空字符串与null有区别), 那么玩家的屏幕将会被更新(本质上讲, 虽然看起来没啥变化)
     * 如果字符串包含多行文本, 那么只有第一行文本才会被发送出去.
     * 所有时间值都可以取-1来表示最后一次(或者上一次)发送标题所用的值(如果尚无任何标题曾被显示过则取默认值).
     * <p>原文：
     * Sends a title and a subtitle message to the player. If either of these
     * values are null, they will not be sent and the display will remain
     * unchanged. If they are empty strings, the display will be updated as
     * such. If the strings contain a new line, only the first line will be
     * sent. All timings values may take a value of -1 to indicate that they
     * will use the last value sent (or the defaults if no title has been
     * displayed).
     *
     * @param title 标题文本
     * <p>原文：Title text
     * @param subtitle 副标题文本
     * <p>原文：Subtitle text
     * @param fadeIn 标题淡入时间,以tick为单位.默认值取10.
     * <p>原文：time in ticks for titles to fade in. Defaults to 10.
     * @param stay 标题停留/展示时长,以tick为单位.默认值取70.
     * <p>原文：time in ticks for titles to stay. Defaults to 70.
     * @param fadeOut 标题淡出时间,以tick为单位.默认值取20.
     * <p>原文：time in ticks for titles to fade out. Defaults to 20.
     * @deprecated Use {@link #showTitle(net.kyori.adventure.title.Title)} or {@link #sendTitlePart(net.kyori.adventure.title.TitlePart, Object)}
     */
    @Deprecated // Paper - Adventure
    public void sendTitle(@Nullable String title, @Nullable String subtitle, int fadeIn, int stay, int fadeOut);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 重置想转玩家显示的屏幕标题.
     * 这将清除已显示的标题/副标题并重置标题显示计时器至默认值.
     * <p>原文：
     * Resets the title displayed to the player. This will clear the displayed
     * title / subtitle and reset timings to their default values.
     */
    public void resetTitle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置产生粒子效果 (the number of times specified by count).
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param particle 要产生的粒子效果
     * <p>原文：the particle to spawn
     * @param location 粒子效果产生位置
     * <p>原文：the location to spawn at
     * @param count the number of particles
     */
    default void spawnParticle(Particle particle, Location location, int count) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置产生粒子效果 (the number of times specified by count).
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param particle 要产生的粒子效果
     * <p>原文：the particle to spawn
     * @param x 粒子效果产生位置x轴
     * <p>原文：the position on the x axis to spawn at
     * @param y 粒子效果产生位置y轴
     * <p>原文：the position on the y axis to spawn at
     * @param z 粒子效果产生位置z轴
     * <p>原文：the position on the z axis to spawn at
     * @param count the number of particles
     */
    default void spawnParticle(Particle particle, double x, double y, double z, int count) {
        this.spawnParticle(particle, x, y, z, count, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置产生粒子效果 (the number of times specified by count).
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param <T> 粒子效果数据类型 (请参阅 {@link Particle#getDataType()}
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle 要产生的粒子效果
     * <p>原文：the particle to spawn
     * @param location 粒子效果产生位置
     * <p>原文：the location to spawn at
     * @param count 粒子数目
     * <p>原文：the number of particles
     * @param data 粒子效果的数据或null, 其数据类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     */
    default <T> void spawnParticle(Particle particle, Location location, int count, @Nullable T data) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * Spawns the particle (the number of times specified by count)
     * at the target location.
     *
     * @param <T> 粒子效果数据类型 (请参阅 {@link Particle#getDataType()}
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle the particle to spawn
     * @param x the position on the x axis to spawn at
     * @param y the position on the y axis to spawn at
     * @param z the position on the z axis to spawn at
     * @param count the number of particles
     * @param data 粒子效果的数据或null, 其数据类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     */
    default <T> void spawnParticle(Particle particle, double x, double y, double z, int count, @Nullable T data) {
        this.spawnParticle(particle, x, y, z, count, 0, 0, 0, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置产生粒子效果(按count指定的次数). 每个粒子的位置将由各轴上的偏移参数进行正负方向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle 要产生的粒子效果
     * <p>原文：the particle to spawn
     * @param location 产生位置
     * <p>原文：the location to spawn at
     * @param count 粒子数目
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     */
    default void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置产生粒子效果(按count指定的次数). 每个粒子的位置将由各轴上的偏移参数进行正负方向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle 要产生的粒子效果
     * <p>原文：the particle to spawn
     * @param x 产生位置的x轴坐标
     * <p>原文：the position on the x axis to spawn at
     * @param y 产生位置的y轴坐标
     * <p>原文：the position on the y axis to spawn at
     * @param z 产生位置的z轴坐标
     * <p>原文：the position on the z axis to spawn at
     * @param count 粒子数目
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     */
    default void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T> 粒子效果数据类型 (请参阅 {@link Particle#getDataType()}
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     * @param data 粒子效果的数据或null, 其数据类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     */
    default <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, @Nullable T data) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T> 粒子效果数据类型 (请参阅 {@link Particle#getDataType()}
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle the particle to spawn
     * @param x the position on the x axis to spawn at
     * @param y the position on the y axis to spawn at
     * @param z the position on the z axis to spawn at
     * @param count the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     * @param data 粒子效果的数据或null, 其数据类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     */
    default <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, @Nullable T data) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, 1, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置产生粒子效果(按count指定的次数). 每个粒子的位置将由各轴上的偏移参数进行正负方向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle 要产生的粒子效果
     * <p>原文：the particle to spawn
     * @param location 产生位置
     * <p>原文：the location to spawn at
     * @param count 粒子数目
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     * @param extra 此粒子的额外数据, 取决于所使用的粒子效果(通常为速度)
     * <p>原文：the extra data for this particle, depends on the
     *              particle used (normally speed)
     */
    default void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置产生粒子效果(按count指定的次数). 每个粒子的位置将由各轴上的偏移参数进行正负方向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param particle 要产生的粒子效果
     * <p>原文：the particle to spawn
     * @param x 产生位置的x轴坐标
     * <p>原文：the position on the x axis to spawn at
     * @param y 产生位置的y轴坐标
     * <p>原文：the position on the y axis to spawn at
     * @param z 产生位置的z轴坐标
     * <p>原文：the position on the z axis to spawn at
     * @param count 粒子数目
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     * @param extra 此粒子的额外数据, 取决于所使用的粒子效果(通常为速度)
     * <p>原文：the extra data for this particle, depends on the
     *              particle used (normally speed)
     */
    default void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, null);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T> 粒子效果数据类型 (请参阅 {@link Particle#getDataType()}
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle the particle to spawn
     * @param location the location to spawn at
     * @param count the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     * @param extra the extra data for this particle, depends on the
     *              particle used (normally speed)
     * @param data 粒子效果的数据或null, 其数据类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     */
    default <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T> 粒子效果数据类型 (请参阅 {@link Particle#getDataType()}
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle the particle to spawn
     * @param x the position on the x axis to spawn at
     * @param y the position on the y axis to spawn at
     * @param z the position on the z axis to spawn at
     * @param count the number of particles
     * @param offsetX the maximum random offset on the X axis
     * @param offsetY the maximum random offset on the Y axis
     * @param offsetZ the maximum random offset on the Z axis
     * @param extra the extra data for this particle, depends on the
     *              particle used (normally speed)
     * @param data 粒子效果的数据或null, 其数据类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     */
    default <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data) {
        this.spawnParticle(particle, x, y, z, count, offsetX, offsetY, offsetZ, extra, data, false);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置产生粒子效果(按count指定的次数). 每个粒子的位置将由各轴上的偏移参数进行正负方向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T> 粒子效果数据类型 (请参阅 {@link Particle#getDataType()})
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle 要产生的粒子效果
     * <p>原文：the particle to spawn
     * @param location 产生位置
     * <p>原文：the location to spawn at
     * @param count 粒子数目
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     * @param extra 此粒子的额外数据, 取决于所使用的粒子效果(通常为速度)
     * <p>原文：the extra data for this particle, depends on the
     *              particle used (normally speed)
     * @param data 粒子效果的数据或null, 其数据类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     * @param force 是否在扩展范围内向玩家发送粒子效果, 并强制客户端渲染, 无论设置如何
     * <p>原文：whether to send the particle to the player in an extended
     *              range and encourage their client to render it regardless of
     *              settings
     */
    default <T> void spawnParticle(Particle particle, Location location, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data, boolean force) {
        this.spawnParticle(particle, location.getX(), location.getY(), location.getZ(), count, offsetX, offsetY, offsetZ, extra, data, force);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定位置产生粒子效果(按count指定的次数). 每个粒子的位置将由各轴上的偏移参数进行正负方向的随机偏移.
     * <p>原文：
     * Spawns the particle (the number of times specified by count)
     * at the target location. The position of each particle will be
     * randomized positively and negatively by the offset parameters
     * on each axis.
     *
     * @param <T> 粒子效果数据类型 (请参阅 {@link Particle#getDataType()})
     * <p>原文：type of particle data (see {@link Particle#getDataType()}
     * @param particle 要产生的粒子效果
     * <p>原文：the particle to spawn
     * @param x 产生位置的x轴坐标
     * <p>原文：the position on the x axis to spawn at
     * @param y 产生位置的y轴坐标
     * <p>原文：the position on the y axis to spawn at
     * @param z 产生位置的z轴坐标
     * <p>原文：the position on the z axis to spawn at
     * @param count 粒子数目
     * <p>原文：the number of particles
     * @param offsetX X轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the X axis
     * @param offsetY Y轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Y axis
     * @param offsetZ Z轴上的最大随机偏移量
     * <p>原文：the maximum random offset on the Z axis
     * @param extra 此粒子的额外数据, 取决于所使用的粒子效果(通常为速度)
     * <p>原文：the extra data for this particle, depends on the
     *              particle used (normally speed)
     * @param data 粒子效果的数据或null, 其数据类型取决于{@link Particle#getDataType()}
     * <p>原文：the data to use for the particle or null,
     *             the type of this depends on {@link Particle#getDataType()}
     * @param force 是否在扩展范围内向玩家发送粒子效果, 并强制客户端渲染, 无论设置如何
     * <p>原文：whether to send the particle to the player in an extended
     *              range and encourage their client to render it regardless of
     *              settings
     */
    public <T> void spawnParticle(Particle particle, double x, double y, double z, int count, double offsetX, double offsetY, double offsetZ, double extra, @Nullable T data, boolean force);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回玩家指定进度的完成进度.
     * <p>原文：
     * Return the player's progression on the specified advancement.
     *
     * @param advancement 进度
     * <p>原文：advancement
     * @return 有关玩家进度的详细信息
     * <p>原文：object detailing the player's progress
     */
    public AdvancementProgress getAdvancementProgress(Advancement advancement);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家当前客户端侧的视距.
     * <br>
     * 如果客户端尚未通信此信息, 则默认为服务器视距.
     * <p>原文：
     * Get the player's current client side view distance.
     * <br>
     * Will default to the server view distance if the client has not yet
     * communicated this information,
     *
     * @return 客户端视距
     * <p>原文：client view distance as above
     */
    public int getClientViewDistance();

    // Paper start
    /**
     * Gets the player's current locale.
     *
     * @return the player's locale
     */
    java.util.Locale locale();
    // Paper end
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家的估计延迟(以毫秒为单位).
     * <p>
     * 在原版中, 此值表示发送的应用层ping数据包响应时间的加权平均值. 此值不代表网络往返时间, 因此可能粒度较低且受其他因素影响.
     * 因此, 它<b>不应</b>被用于反作弊目的. 建议仅将其作为连接质量的<b>定性</b>指标(原版在标签列表中就是为此目的使用它).
     * <p>原文：
     * Gets the player's estimated ping in milliseconds.
     *
     * In Vanilla this value represents a weighted average of the response time
     * to application layer ping packets sent. This value does not represent the
     * network round trip time and as such may have less granularity and be
     * impacted by other sources. For these reasons it <b>should not</b> be used
     * for anti-cheat purposes. Its recommended use is only as a
     * <b>qualitative</b> indicator of connection quality (Vanilla uses it for
     * this purpose in the tab list).
     *
     * @return 玩家延迟
     * <p>原文：player ping
     */
    public int getPing();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回玩家本地语言环境.
     * 语言环境值的格式尚未被适当地定义.
     * <br>
     * 原生 Minecraft 客户端将使用全小写 语言/国家, 用短下划线分隔. 但自定义资源包可能会使用他们想要的任意格式.
     * <p>原文：
     * Gets the player's current locale.
     *
     * The value of the locale String is not defined properly.
     * <br>
     * The vanilla Minecraft client will use lowercase language / country pairs
     * separated by an underscore, but custom resource packs may use any format
     * they wish.
     *
     * @return 玩家语言环境
     * <p>原文：the player's locale
     * @deprecated in favour of {@link #locale()}
     */
    @Deprecated // Paper
    public String getLocale();

    // Paper start
    /**
     * Get whether the player can affect mob spawning
     *
     * @return if the player can affect mob spawning
     */
    public boolean getAffectsSpawning();

    /**
     * Set whether the player can affect mob spawning
     *
     * @param affects Whether the player can affect mob spawning
     */
    public void setAffectsSpawning(boolean affects);

    /**
     * Gets the view distance for this player
     *
     * @return the player's view distance
     * @see org.bukkit.World#getViewDistance()
     */
    public int getViewDistance();

    /**
     * Sets the view distance for this player
     *
     * @param viewDistance the player's view distance
     * @see org.bukkit.World#setViewDistance(int)
     */
    public void setViewDistance(int viewDistance);

    /**
     * Gets the simulation distance for this player
     *
     * @return the player's simulation distance
     */
    public int getSimulationDistance();

    /**
     * Sets the simulation distance for this player
     *
     * @param simulationDistance the player's new simulation distance
     */
    public void setSimulationDistance(int simulationDistance);

    /**
     * Gets the no-ticking view distance for this player.
     * <p>
     * No-tick view distance is the view distance where chunks will load, however the chunks and their entities will not
     * be set to tick.
     * </p>
     * @return The no-tick view distance for this player.
     * @deprecated Use {@link #getViewDistance()}
     */
    @Deprecated
    default int getNoTickViewDistance() {
        return this.getViewDistance();
    }

    /**
     * Sets the no-ticking view distance for this player.
     * <p>
     * No-tick view distance is the view distance where chunks will load, however the chunks and their entities will not
     * be set to tick.
     * </p>
     * @param viewDistance view distance in [2, 32] or -1
     * @deprecated Use {@link #setViewDistance(int)}
     */
    @Deprecated
    default void setNoTickViewDistance(int viewDistance) {
        this.setViewDistance(viewDistance);
    }

    /**
     * Gets the sending view distance for this player.
     * <p>
     * Sending view distance is the view distance where chunks will load in for players.
     * </p>
     * @return The sending view distance for this player.
     */
    public int getSendViewDistance();

    /**
     * Sets the sending view distance for this player.
     * <p>
     * Sending view distance is the view distance where chunks will load in for players.
     * </p>
     * @param viewDistance view distance in [2, 32] or -1
     */
    public void setSendViewDistance(int viewDistance);
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 更新发送给客户端的命令列表.
     * <br>
     * 通常在权限变更完成后用于确保客户端拥有完整的命令列表.
     * <p>原文：
     * Update the list of commands sent to the client.
     * <br>
     * Generally useful to ensure the client has a complete list of commands
     * after permission changes are done.
     */
    public void updateCommands();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为玩家打开一本{@link Material#WRITTEN_BOOK 成书}.
     * <p>原文：
     * Open an ItemStack with {@link io.papermc.paper.datacomponent.DataComponentTypes#WRITTEN_BOOK_CONTENT} for a Player
     *
     * @param book 要为玩家打开的书
     * <p>原文：the item with written book content to open for this player
     * @throws IllegalArgumentException if the ItemStack is null, empty or doesn't have a {@link io.papermc.paper.datacomponent.DataComponentTypes#WRITTEN_BOOK_CONTENT}
     */
    void openBook(ItemStack book);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为玩家打开告示牌以进行编辑.
     * <p>
     * 告示牌必须放置在与玩家相同的世界中.
     * <p>原文：
     * Open a Sign for editing by the Player.
     *
     * The Sign must be in the same world as the player.
     *
     * @param sign 要编辑的告示牌
     * <p>原文：The sign to edit
     * @deprecated use {@link #openSign(Sign, Side)}
     */
    @Deprecated
    default void openSign(Sign sign) {
        this.openSign(sign, org.bukkit.block.sign.Side.FRONT);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为玩家打开告示牌以进行编辑.
     * <p>
     * 告示牌必须放置在与玩家相同的世界中.
     * <p>原文：
     * Open a Sign for editing by the Player.
     *
     * The Sign must be placed in the same world as the player.
     *
     * @param sign 要编辑的告示牌
     * <p>原文：The sign to edit
     * @param side 要编辑的面
     * <p>原文：The side to edit
     */
    public void openSign(Sign sign, Side side);

    /**
     * Open a sign for editing by the player.
     * <p>
     * The sign must only be placed locally for the player, which can be done with {@link #sendBlockChange(Location, BlockData)} and {@link #sendBlockUpdate(Location, TileState)}.
     * A side-effect of this is that normal events, like {@link org.bukkit.event.block.SignChangeEvent} will not be called (unless there is an actual sign in the world).
     * Additionally, the client may enforce distance limits to the opened position.
     * </p>
     *
     * @param block The block where the client has a sign placed
     * @param side The side to edit
     * @see io.papermc.paper.event.packet.UncheckedSignChangeEvent
     */
    @ApiStatus.Experimental
    void openVirtualSign(Position block, Side side);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向玩家显示演示界面, 此界面通常只在游戏的演示版本中可见.
     * <br>
     * 服务器可以使用资源包修改此界面上的文本.
     * <p>原文：
     * Shows the demo screen to the player, this screen is normally only seen in
     * the demo version of the game.
     * <br>
     * Servers can modify the text on this screen using a resource pack.
     */
    public void showDemoScreen();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家是否启用了"允许服务器列表显示"设置.
     * <p>原文：
     * Gets whether the player has the "Allow Server Listings" setting enabled.
     *
     * @return 玩家是否允许服务器列表显示
     * <p>原文：whether the player allows server listings
     */
    public boolean isAllowingServerListings();

    // Paper start
    @Override
    default net.kyori.adventure.text.event.HoverEvent<net.kyori.adventure.text.event.HoverEvent.ShowEntity> asHoverEvent(final java.util.function.UnaryOperator<net.kyori.adventure.text.event.HoverEvent.ShowEntity> op) {
        return net.kyori.adventure.text.event.HoverEvent.showEntity(op.apply(net.kyori.adventure.text.event.HoverEvent.ShowEntity.of(this.getType().getKey(), this.getUniqueId(), this.displayName())));
    }
    // Paper end

    @Override
    default void applySkinToPlayerHeadContents(final PlayerHeadObjectContents.Builder builder) {
        OfflinePlayer.super.applySkinToPlayerHeadContents(builder);
        builder.hat(this.getClientOption(ClientOption.SKIN_PARTS).hasHatsEnabled());
    }

    // Paper start - Player Profile API
    /**
     * Gets a copy of this players profile
     *
     * @return The players profile object
     */
    com.destroystokyo.paper.profile.PlayerProfile getPlayerProfile();

    /**
     * Changes the PlayerProfile for this player. This will cause this player
     * to be re-registered to all clients that can currently see this player.
     * <p>
     * After executing this method, the player {@link java.util.UUID} won't
     * be swapped, only their name and profile properties.
     *
     * @param profile The new profile to use
     */
    void setPlayerProfile(com.destroystokyo.paper.profile.PlayerProfile profile);
    // Paper end - Player Profile API

    // Paper start - attack cooldown API
    /**
     * Returns the amount of ticks the current cooldown lasts
     *
     * @return Amount of ticks cooldown will last
     */
    float getCooldownPeriod();

    /**
     * Returns the percentage of attack power available based on the cooldown (zero to one).
     *
     * @param adjustTicks Amount of ticks to add to cooldown counter for this calculation
     * @return Percentage of attack power available
     */
    float getCooledAttackStrength(float adjustTicks);

    /**
     * Reset the cooldown counter to 0, effectively starting the cooldown period.
     */
    void resetCooldown();
    // Paper end - attack cooldown API

    // Paper start - client option API
    /**
     * @return the client option value of the player
     */
    <T> T getClientOption(ClientOption<T> option);
    // Paper end - client option API

    // Paper start - elytra boost API
    /**
     * Boost a Player that's {@link #isGliding()} using a {@link Firework}.
     * If the creation of the entity is cancelled, no boosting is done.
     * This method does not fire {@link com.destroystokyo.paper.event.player.PlayerElytraBoostEvent}.
     *
     * @param firework The {@link Material#FIREWORK_ROCKET} to boost the player with
     * @return The {@link Firework} boosting the Player or null if the spawning of the entity was cancelled
     * @throws IllegalArgumentException if {@link #isGliding()} is false
     * or if the {@code firework} isn't a {@link Material#FIREWORK_ROCKET}
     * @deprecated use {@link HumanEntity#fireworkBoost(ItemStack)} instead. Note that this method <b>does not</b>
     * check if the player is gliding or not.
     */
    default @Nullable Firework boostElytra(final ItemStack firework) {
        com.google.common.base.Preconditions.checkState(this.isGliding(), "Player must be gliding");
        return this.fireworkBoost(firework);
    }
    // Paper end - elytra boost API

    // Paper start - sendOpLevel API
    /**
     * Send a packet to the player indicating its operator status level.
     * <p>
     * <b>Note:</b> This will not persist across more than the current connection, and setting the player's operator
     * status as a later point <i>will</i> override the effects of this.
     *
     * @param level The level to send to the player. Must be in {@code [0, 4]}.
     * @throws IllegalArgumentException If the level is negative or greater than {@code 4} (i.e. not within {@code [0, 4]}).
     */
    void sendOpLevel(byte level);
    // Paper end - sendOpLevel API

    // Paper start - custom chat completions API
    /**
     * Adds custom chat completion suggestions that the client will
     * suggest when typing in chat.
     *
     * @param completions custom completions
     * @deprecated use {@link #addCustomChatCompletions(Collection)}
     */
    @Deprecated(since = "1.20.1")
    void addAdditionalChatCompletions(java.util.Collection<String> completions);

    /**
     * Removes custom chat completion suggestions that the client
     * suggests when typing in chat.
     *
     * Note: this only applies to previously added custom completions,
     * online player names are always suggested and cannot be removed.
     *
     * @param completions custom completions
     * @deprecated use {@link #addCustomChatCompletions(Collection)}
     */
    @Deprecated(since = "1.20.1")
    void removeAdditionalChatCompletions(java.util.Collection<String> completions);
    // Paper end - custom chat completions API

    // Spigot start
    public class Spigot extends Entity.Spigot {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 返回该玩家的连接地址, 无论地址的真实与否.
         * <p>原文：
         * Gets the connection address of this player, regardless of whether it
         * has been spoofed or not.
         *
         * @return 该玩家的连接地址
         * <p>原文：the player's connection address
         */
        public InetSocketAddress getRawAddress() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 若玩家死亡, 令其重生.
         * <p>原文：
         * Respawns the player if dead.
         */
        public void respawn() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获取所有用 {@link #hidePlayer(org.bukkit.entity.Player)} 隐藏的玩家.
         * <p>原文：
         * Gets all players hidden with {@link #hidePlayer(org.bukkit.entity.Player)}.
         *
         * @return 所有隐藏玩家的集合
         * <p>原文：a Set with all hidden players
         */
        public java.util.Set<Player> getHiddenPlayers() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Deprecated // Paper
        @Override
        public void sendMessage(net.md_5.bungee.api.chat.BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Deprecated // Paper
        @Override
        public void sendMessage(net.md_5.bungee.api.chat.BaseComponent... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向玩家发送在指定位置展示的聊天消息组件.
         * <p>原文：
         * Sends the component to the specified screen position of this player
         *
         * @param position 在屏幕上的位置
         * <p>原文：the screen position
         * @param component 要发送的聊天消息组件
         * <p>原文：the components to send
         * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
         */
        @Deprecated // Paper
        public void sendMessage(net.md_5.bungee.api.ChatMessageType position, net.md_5.bungee.api.chat.BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 将多个聊天消息组件作为单条消息发送给玩家, 并指定消息的展示位置.
         * <p>原文：
         * Sends an array of components as a single message to the specified screen position of this player
         *
         * @param position 在屏幕上的位置
         * <p>原文：the screen position
         * @param components 要发送的聊天消息组件
         * <p>原文：the components to send
         * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
         */
        @Deprecated // Paper
        public void sendMessage(net.md_5.bungee.api.ChatMessageType position, net.md_5.bungee.api.chat.BaseComponent... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向玩家发送在指定位置展示的聊天消息组件.
         * <p>原文：
         * Sends the component to the specified screen position of this player
         *
         * @param position 在屏幕上的位置
         * <p>原文：the screen position
         * @param sender 消息的发送者
         * <p>原文：the sender of the message
         * @param component 要发送的聊天消息组件
         * <p>原文：the components to send
         * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
         */
        @Deprecated // Paper
        public void sendMessage(net.md_5.bungee.api.ChatMessageType position, java.util.@Nullable UUID sender, net.md_5.bungee.api.chat.BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 将多个聊天消息组件作为单条消息发送给玩家, 并指定消息的展示位置.
         * <p>原文：
         * Sends an array of components as a single message to the specified screen position of this player
         *
         * @param position 在屏幕上的位置
         * <p>原文：the screen position
         * @param sender 消息的发送者
         * <p>原文：the sender of the message
         * @param components 要发送的聊天消息组件
         * <p>原文：the components to send
         * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
         */
        @Deprecated // Paper
        public void sendMessage(net.md_5.bungee.api.ChatMessageType position, java.util.@Nullable UUID sender, net.md_5.bungee.api.chat.BaseComponent... components) {
            throw new UnsupportedOperationException("Not supported yet.");

        }

        // Paper start
        /**
         * @return the player's ping
         * @deprecated use {@link Player#getPing()}
         */
        @Deprecated
        public int getPing() {
            throw new UnsupportedOperationException( "Not supported yet." );
        }
        // Paper end
    }

    // Paper start - brand support
    /**
     * Returns player's client brand name. If the client didn't send this information, the brand name will be null.<br>
     * For the Notchian client this name defaults to <code>vanilla</code>. Some modified clients report other names such as <code>forge</code>.<br>
     * @return client brand name
     */
    @Nullable
    String getClientBrandName();
    // Paper end

    // Paper start - Teleport API
    /**
     * Sets the player's rotation.
     *
     * @param yaw the yaw
     * @param pitch the pitch
     */
    void setRotation(float yaw, float pitch);

    /**
     * Causes the player to look towards the given entity.
     *
     * @param entity Entity to look at
     * @param playerAnchor What part of the player should face the entity
     * @param entityAnchor What part of the entity the player should face
     */
    void lookAt(org.bukkit.entity.Entity entity, LookAnchor playerAnchor, LookAnchor entityAnchor);
    // Paper end - Teleport API

    // Paper start
    /**
     * Displays elder guardian effect with a sound
     *
     * @see #showElderGuardian(boolean)
     */
    default void showElderGuardian() {
        showElderGuardian(false);
    }

    /**
     * Displays elder guardian effect and optionally plays a sound
     *
     * @param silent whether sound should be silenced
     */
    void showElderGuardian(boolean silent);

    /**
     * Returns the player's cooldown in ticks until the next Warden warning can occur.
     *
     * @return ticks until next Warden warning can occur. 0 means there is no cooldown left.
     */
    int getWardenWarningCooldown();

    /**
     * Sets the player's cooldown in ticks until next Warden warning can occur.
     *
     * @param cooldown ticks until next Warden warning can occur. 0 means there is no cooldown left. Values less than 0 are set to 0.
     */
    void setWardenWarningCooldown(int cooldown);

    /**
     * Returns time since last Warden warning in ticks.
     *
     * @return ticks since last Warden warning
     */
    int getWardenTimeSinceLastWarning();

    /**
     * Sets time since last Warden warning in ticks.
     *
     * @param time ticks since last Warden warning
     */
    void setWardenTimeSinceLastWarning(int time);

    /**
     * Returns the player's current Warden warning level.
     *
     * @return current Warden warning level
     */
    int getWardenWarningLevel();

    /**
     * Sets the player's Warden warning level.
     * <p>
     * <b>Note:</b> This will not actually spawn the Warden.
     * Even if the warning level is over threshold, the player still needs to activate a Shrieker in order to summon the Warden.
     *
     * @param warningLevel player's Warden warning level. The warning level is internally limited to valid values.
     */
    void setWardenWarningLevel(int warningLevel);

    /**
     * Increases the player's Warden warning level if possible and not on cooldown.
     * <p>
     * <b>Note:</b> This will not actually spawn the Warden.
     * Even if the warning level is over threshold, the player still needs to activate a Shrieker in order to summon the Warden.
     */
    void increaseWardenWarningLevel();
    // Paper end

    // Paper start
    /**
     * The idle duration is reset when the player
     * sends specific action packets.
     * <p>
     * After the idle duration exceeds {@link org.bukkit.Bukkit#getIdleTimeout()}, the
     * player will be kicked for {@link org.bukkit.event.player.PlayerKickEvent.Cause#IDLING}.
     *
     * @return the current idle duration of this player
     */
    Duration getIdleDuration();

    /**
     * Resets this player's idle duration.
     * <p>
     * After the idle duration exceeds {@link org.bukkit.Bukkit#getIdleTimeout()}, the
     * player will be kicked for {@link org.bukkit.event.player.PlayerKickEvent.Cause#IDLING}.
     *
     * @see #getIdleDuration()
     */
    void resetIdleDuration();
    // Paper end

    // Paper start - Add chunk view API
    /**
     * Gets the set of chunk keys for all chunks that have been sent to the player.
     *
     * @return an immutable set of chunk keys
     * @apiNote currently marked as experimental to gather feedback regarding the returned set being an immutable copy
     * vs it potentially being an unmodifiable view of the set chunks.
     */
    @ApiStatus.Experimental
    java.util.@org.jetbrains.annotations.Unmodifiable Set<Long> getSentChunkKeys();

    /**
     * Gets the set of chunks that have been sent to the player.
     *
     * @return an immutable set of chunks
     * @apiNote currently marked as experimental to gather feedback regarding the returned set being an immutable copy
      * vs it potentially being an unmodifiable view of the set chunks.
     */
    @ApiStatus.Experimental
    java.util.@org.jetbrains.annotations.Unmodifiable Set<org.bukkit.Chunk> getSentChunks();

    /**
     * Checks if the player has been sent a specific chunk.
     *
     * @param chunk the chunk to check
     * @return true if the player has been sent the chunk, false otherwise
     */
    default boolean isChunkSent(org.bukkit.Chunk chunk) {
        return this.isChunkSent(chunk.getChunkKey());
    }

    /**
     * Checks if the player has been sent a specific chunk.
     *
     * @param chunkKey the chunk key to check
     * @return true if the player has been sent the chunk, false otherwise
     * @see org.bukkit.Chunk#getChunkKey()
     */
    boolean isChunkSent(long chunkKey);
    // Paper end

    @Override
    Spigot spigot();
    // Spigot end

    // Paper start - entity effect API
    /**
     * Plays an entity effect to this player for the target entity
     * <p>
     * If the effect is not applicable to this class of entity, it will not play.
     *
     * @param effect the entity effect
     * @param target the target entity
     */
    void sendEntityEffect(org.bukkit.EntityEffect effect, Entity target);
    // Paper end - entity effect API

    /**
     * Gives the player the items following full vanilla logic,
     * making the player drop the items that did not fit into
     * the inventory.
     *
     * @param items the items to give.
     * @return the result of this method, holding leftovers and spawned items.
     */
    default PlayerGiveResult give(final ItemStack ... items) {
        return this.give(List.of(items));
    }

    /**
     * Gives the player those items following full vanilla logic,
     * making the player drop the items that did not fit into
     * the inventory.
     *
     * @param items the items to give
     * @return the result of this method, holding leftovers and spawned items.
     */
    default PlayerGiveResult give(final Collection<ItemStack> items) {
        return this.give(items, true);
    }

    /**
     * Gives the player those items following full vanilla logic.
     *
     * @param items      the items to give
     * @param dropIfFull whether the player should drop items that
     *                   did not fit the inventory
     * @return the result of this method, holding leftovers and spawned items.
     */
    PlayerGiveResult give(Collection<ItemStack> items, boolean dropIfFull);

    /**
     * Get the score that shows in the death screen of the player.
     * <p>This amount is added to when the player gains experience.</p>
     *
     * @return Death screen score of player
     */
    int getDeathScreenScore();

    /**
     * Set the score that shows in the death screen of the player.
     * <p>This amount is added to when the player gains experience.</p>
     *
     * @param score New death screen score of player
     */
    void setDeathScreenScore(int score);

    /**
     * Gets the game connection for this player.
     *
     * @return the game connection
     */
    @ApiStatus.Experimental
    PlayerGameConnection getConnection();
}
