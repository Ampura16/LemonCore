package org.bukkit;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;
import net.kyori.adventure.text.object.PlayerHeadObjectContents;
import org.bukkit.ban.ProfileBanList;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.AnimalTamer;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.permissions.ServerOperator;
import org.bukkit.profile.PlayerProfile;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个对玩家身份及其附属数据的引用, 数据存储于磁盘中, 因此不需要玩家在线即可获取到玩家的引用.
 * <p>原文：
 * Represents a reference to a player identity and the data belonging to a
 * player that is stored on the disk and can, thus, be retrieved without the
 * player needing to be online.
 */
@NullMarked
public interface OfflinePlayer extends ServerOperator, AnimalTamer, ConfigurationSerializable, io.papermc.paper.persistence.PersistentDataViewHolder, PlayerHeadObjectContents.SkinSource { // Paper - Add Offline PDC API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查玩家是否在线
     * <p>原文：
     * Checks if this player is currently online
     *
     * It should be noted that this will return true if any instance of this player is
     * online! This instance may have disconnected. If you wish to check if this specific
     * instance of the player is still online, see {@link OfflinePlayer#isConnected()}.
     *
     * @return 在线返回true,反之返回false
     * <p>原文：true if they are online
     */
    public boolean isOnline();

    // Paper start
    /**
     * Checks whether the connection to this player is still valid. This will return
     * true as long as this specific instance of the player is still connected. This
     * will return false after this instance has disconnected, even if the same player
     * has reconnected since.
     *
     * @return true if this player instance is connected
     */
    public boolean isConnected();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回该玩家的游戏名
     * <p>
     * 游戏名并不再是唯一的游戏标识,如果你希望长时间储存,建议使用{@link #getUniqueId()}来替代
     * <p>原文：
     * Returns the name of this player
     * <p>
     * Names are no longer unique past a single game session. For persistent storage
     * it is recommended that you use {@link #getUniqueId()} instead.
     *
     * @return 返回玩家游戏名,如果玩家不存在返回null
     * <p>原文：Player name or null if we have not seen a name for this player yet
     */
    @Override
    @Nullable
    public String getName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回该玩家的游戏UUID标识
     * <p>原文：
     * Returns the UUID of this player
     *
     * @return 玩家游戏UUID
     * <p>原文：Player UUID
     */
    @Override
    public UUID getUniqueId();

    /**
     * <!-- Chinese_BukkitAPI CC BY-NC 4.0 -->
     * 获取玩家资料的一份副本.
     * <p>
     * 如果此玩家在线, 则返回的资料是完整的.
     * 否则, 只保证唯一 id 存在于资料中. 您可以使用
     * {@link PlayerProfile#update()} 来补全返回的资料.
     * <p>原文：
     * Gets a copy of the player's profile.
     * <p>
     * If the player is online, the returned profile will be complete.
     * Otherwise, only the unique id is guaranteed to be present. You can use
     * {@link PlayerProfile#update()} to complete the returned profile.
     *
     * @return the player's profile
     */
    com.destroystokyo.paper.profile.PlayerProfile getPlayerProfile(); // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测该玩家是否被封禁
     * <p>原文：
     * Checks if this player has had their profile banned.
     *
     * @return 是否被封禁
     * <p>原文：true if banned, otherwise false
     */
    public boolean isBanned();
    // Paper start
    /**
     * Permanently Bans this player from the server
     *
     * @param reason Reason for Ban
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)}
     */
    @Deprecated(since = "1.20.4")
    public default BanEntry banPlayer(@Nullable String reason) {
        return banPlayer(reason, null, null);
    }

    /**
     * Permanently Bans this player from the server
     * @param reason Reason for Ban
     * @param source Source of the ban, or null for default
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)}
     */
    @Deprecated(since = "1.20.4")
    public default BanEntry banPlayer(@Nullable String reason, @Nullable String source) {
        return banPlayer(reason, null, source);
    }

    /**
     * Bans this player from the server
     * @param reason Reason for Ban
     * @param expires When to expire the ban
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)}
     */
    @Deprecated(since = "1.20.4")
    public default BanEntry banPlayer(@Nullable String reason, java.util.@Nullable Date expires) {
        return banPlayer(reason, expires, null);
    }

    /**
     * Bans this player from the server
     * @param reason Reason for Ban
     * @param expires When to expire the ban
     * @param source Source of the ban or null for default
     * @return Ban Entry
     * @deprecated use {@link #ban(String, Date, String)}
     */
    @Deprecated(since = "1.20.4")
    public default BanEntry banPlayer(@Nullable String reason, java.util.@Nullable Date expires, @Nullable String source) {
        return banPlayer(reason, expires, source, true);
    }

    /**
     * @deprecated use {@link #ban(String, Date, String)}
     */
    @Deprecated(since = "1.20.4")
    public default BanEntry banPlayer(@Nullable String reason, java.util.@Nullable Date expires, @Nullable String source, boolean kickIfOnline) {
        BanEntry banEntry = Bukkit.getServer().getBanList(BanList.Type.NAME).addBan(getName(), reason, expires, source);
        if (kickIfOnline && isOnline()) {
            getPlayer().kickPlayer(reason);
        }
        return banEntry;
    }
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI CC BY-NC 4.0 -->
     * 将此用户添加到 {@link ProfileBanList} 中. 如果之前已有封禁记录, 则会更新该记录.
     * <p>原文：
     * Adds this user to the {@link ProfileBanList}. If a previous ban exists, this will
     * update the entry.
     *
     * @param reason reason for the ban, null indicates implementation default
     * @param expires date for the ban's expiration (unban), or null to imply
     *     forever
     * @param source source of the ban, null indicates implementation default
     * @return the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     */
    @Nullable
    public <E extends BanEntry<? super com.destroystokyo.paper.profile.PlayerProfile>> E ban(@Nullable String reason, @Nullable Date expires, @Nullable String source); // Paper - fix ban list API

    /**
     * <!-- Chinese_BukkitAPI CC BY-NC 4.0 -->
     * 将此用户添加到 {@link ProfileBanList} 中. 如果之前已有封禁记录, 则会更新该记录.
     * <p>原文：
     * Adds this user to the {@link ProfileBanList}. If a previous ban exists, this will
     * update the entry.
     *
     * @param reason reason for the ban, null indicates implementation default
     * @param expires instant for the ban's expiration (unban), or null to imply
     *     forever
     * @param source source of the ban, null indicates implementation default
     * @return the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     */
    @Nullable
    public <E extends BanEntry<? super com.destroystokyo.paper.profile.PlayerProfile>> E ban(@Nullable String reason, @Nullable Instant expires, @Nullable String source); // Paper - fix ban list API

    /**
     * <!-- Chinese_BukkitAPI CC BY-NC 4.0 -->
     * 将此用户添加到 {@link ProfileBanList} 中. 如果之前已有封禁记录, 则会更新该记录.
     * <p>原文：
     * Adds this user to the {@link ProfileBanList}. If a previous ban exists, this will
     * update the entry.
     *
     * @param reason reason for the ban, null indicates implementation default
     * @param duration how long the ban last, or null to imply
     *     forever
     * @param source source of the ban, null indicates implementation default
     * @return the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     */
    @Nullable
    public <E extends BanEntry<? super com.destroystokyo.paper.profile.PlayerProfile>> E ban(@Nullable String reason, @Nullable Duration duration, @Nullable String source); // Paper - fix ban list API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测玩家是否存在白名单列表中
     * <p>原文：
     * Checks if this player is whitelisted or not
     *
     * @return 是否加白
     * <p>原文：true if whitelisted
     */
    public boolean isWhitelisted();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将玩家添加到白名单列表或移除
     * <p>原文：
     * Sets if this player is whitelisted or not
     *
     * @param value 如果要添加到白名单列表内使用true,反之使用false
     * <p>原文：true if whitelisted
     */
    public void setWhitelisted(boolean value);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个 {@link Player}对象
     * <p>原文：
     * Gets a {@link Player} object that this represents, if there is one
     * <p>
     * If the player is online, this will return that player. Otherwise,
     * it will return null.
     *
     * @return 玩家Player对象
     * <p>原文：Online player
     */
    @Nullable
    public Player getPlayer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该玩家第一次进入服务器的时间
     * <p>原文：
     * Gets the first date and time that this player was witnessed on this
     * server.
     * <p>
     * If the player has never played before, this will return 0. Otherwise,
     * it will be the amount of milliseconds since midnight, January 1, 1970
     * UTC.
     *
     * @return 返回玩家第一次进入服务器的时间
     * <p>原文：Date of first log-in for this player, or 0
     */
    public long getFirstPlayed();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该玩家最后一次登录服务器的时间
     * <p>原文：
     * Gets the last date and time that this player was witnessed on this
     * server.
     * <p>
     * If the player has never played before, this will return 0. Otherwise,
     * it will be the amount of milliseconds since midnight, January 1, 1970
     * UTC.
     *
     * @return 返回最后登录的时间
     * <p>原文：Date of last log-in for this player, or 0
     * @deprecated The API contract is ambiguous and the implementation may or may not return the correct value given this API ambiguity. It is instead recommended use {@link #getLastLogin()} or {@link #getLastSeen()} depending on your needs.
     */
    @Deprecated
    public long getLastPlayed();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测该玩家是否在先前在此服游戏过
     * <p>原文：
     * Checks if this player has played on this server before.
     *
     * @return 如果是返回true,反之返回false
     * <p>原文：True if the player has played before, otherwise false
     */
    public boolean hasPlayedBefore();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该玩家在床的重生点的Location对象,但如果该玩家从未使用过床或该重生点无效,则会返回null
     * <p>原文：
     * Gets the Location where the player will spawn at their bed, null if
     * they have not slept in one or their current bed spawn is invalid.
     *
     * @return 获取玩家的床重生点Location对象信息.如果不存在返回null
     * <p>原文：Bed Spawn Location if bed exists, otherwise null.
     * @see #getRespawnLocation()
     * @deprecated 误导性的方法名称. 此方法也同时返回复活锚点的位置
     * <p>原文：Misleading name. This method also returns the location of
     * respawn anchors.
     */
    @Nullable
    @Deprecated(since = "1.20.4")
    default Location getBedSpawnLocation() {
        return this.getRespawnLocation();
    }

    // Paper start
    /**
     * Gets the last date and time that this player logged into the server.
     * <p>
     * If the player has never played before, this will return 0. Otherwise,
     * it will be the amount of milliseconds since midnight, January 1, 1970
     * UTC.
     *
     * @return last login time
     */
    public long getLastLogin();

    /**
     * Gets the last date and time that this player was seen on the server.
     * <p>
     * If the player has never played before, this will return 0. If the
     * player is currently online, this will return the current time.
     * Otherwise it will be the amount of milliseconds since midnight,
     * January 1, 1970 UTC.
     *
     * @return last seen time
     */
    public long getLastSeen();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家的重生点位置, 如果没有有效的重生点则返回 null.
     * <p>原文：
     * Gets the Location where the player will spawn at, {@code null} if they
     * don't have a valid respawn point.
     * <br>
     * Unlike online players, the location if found will not be loaded by default.
     *
     * @return 重生点位置, 如果不存在则返回 null.
     * <p>原文：respawn location if exists, otherwise {@code null}.
     * @see #getRespawnLocation(boolean) for more fine-grained control over chunk loading and validation behaviour.
     */
    default @Nullable Location getRespawnLocation() {
        return this.getRespawnLocation(false); // keep old behavior for offline players
    }

    /**
     * Gets the Location where the player will spawn at, {@code null} if they
     * don't have a valid respawn point.
     *
     * @param loadLocationAndValidate load the expected respawn location to retrieve the exact position of the spawn
     *                                block and check if this position is still valid or not. Loading the location
     *                                will induce a sync chunk load and must hence be used with caution.
     * @return respawn location if exists, otherwise {@code null}.
     */
    @Nullable Location getRespawnLocation(boolean loadLocationAndValidate);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把该玩家的统计信息里的某项+1. <p>
     * 这相当于下面的代码: <p>
     * <code>incrementStatistic(Statistic, 1)</code>
     * <p>原文：
     * Increments the given statistic for this player.
     * <p>
     * This is equivalent to the following code:
     * <code>incrementStatistic(Statistic, 1)</code>
     *
     * @param statistic 要+1的统计项
     * <p>原文：Statistic to increment
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the statistic requires an
     *     additional parameter
     */
    public void incrementStatistic(Statistic statistic) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把该玩家的统计信息里的某项-1. <p>
     * 这相当于下面的代码: <p>
     * <code>decrementStatistic(Statistic, 1)</code>
     * <p>原文：
     * Decrements the given statistic for this player.
     * <p>
     * This is equivalent to the following code:
     * <code>decrementStatistic(Statistic, 1)</code>
     *
     * @param statistic 要减少的统计项目
     * <p>原文：Statistic to decrement
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the statistic requires an
     *     additional parameter
     */
    public void decrementStatistic(Statistic statistic) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把该玩家的统计信息里的某项加上指定的值.
     * <p>原文：
     * Increments the given statistic for this player.
     *
     * @param statistic 要增加的统计项
     * <p>原文：Statistic to increment
     * @param amount 要增加多少
     * <p>原文：Amount to increment this statistic by
     * @throws IllegalArgumentException 当statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 当statistic为null
     * <p>原文：if amount is negative
     * @throws IllegalArgumentException 当statistic为null
     * <p>原文：if the statistic requires an
     *     additional parameter
     */
    public void incrementStatistic(Statistic statistic, int amount) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把该玩家的统计信息里的某项减少指定的值.
     * <p>原文：
     * Decrements the given statistic for this player.
     *
     * @param statistic 要减少的统计项
     * <p>原文：Statistic to decrement
     * @param amount 要减少多少
     * <p>原文：Amount to decrement this statistic by
     * @throws IllegalArgumentException 当statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 当statistic为null
     * <p>原文：if amount is negative
     * @throws IllegalArgumentException 当statistic为null
     * <p>原文：if the statistic requires an
     *     additional parameter
     */
    public void decrementStatistic(Statistic statistic, int amount) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该玩家的统计信息里的某项的值.
     * <p>原文：
     * Sets the given statistic for this player.
     *
     * @param statistic 要设置的统计项
     * <p>原文：Statistic to set
     * @param newValue 要把statistic设置成的值
     * <p>原文：The value to set this statistic to
     * @throws IllegalArgumentException 当statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 当statistic为null
     * <p>原文：if newValue is negative
     * @throws IllegalArgumentException 当statistic为null
     * <p>原文：if the statistic requires an
     *     additional parameter
     */
    public void setStatistic(Statistic statistic, int newValue) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到该玩家的统计信息中某项的值.
     * <p>原文：
     * Gets the value of the given statistic for this player.
     *
     * @param statistic 要获取值的统计项
     * <p>原文：Statistic to check
     * @return statistic的值
     * <p>原文：the value of the given statistic
     * @throws IllegalArgumentException 当statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 当statistic为null
     * <p>原文：if the statistic requires an
     *     additional parameter
     */
    public int getStatistic(Statistic statistic) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把该玩家的统计信息中跟方块/物品有关的统计项加上一个{@link Material}. <p>
     * 这相当于下面的代码:
     * <code>incrementStatistic(Statistic, Material, 1)</code>
     * <p>原文：
     * Increments the given statistic for this player for the given material.
     * <p>
     * This is equivalent to the following code:
     * <code>incrementStatistic(Statistic, Material, 1)</code>
     *
     * @param statistic 要操纵的统计
     * <p>原文：Statistic to increment
     * @param material 要给statistic增加的Material
     * <p>原文：Material to offset the statistic with
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if material is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the given parameter is not valid
     *     for the statistic
     */
    public void incrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把该玩家的统计信息中跟方块/物品有关的统计项减少一个{@link Material}.
     * <p>原文：
     * Decrements the given statistic for this player for the given material.
     * <p>
     * This is equivalent to the following code:
     * <code>decrementStatistic(Statistic, Material, 1)</code>
     *
     * @param statistic 要操纵的统计
     * <p>原文：Statistic to decrement
     * @param material 要给statistic减少的Material
     * <p>原文：Material to offset the statistic with
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if material is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the given parameter is not valid
     *     for the statistic
     */
    public void decrementStatistic(Statistic statistic, Material material) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该玩家的统计信息中跟方块/物品有关的统计项的某{@link Material}有多少.
     * <p>原文：
     * Gets the value of the given statistic for this player.
     *
     * @param statistic 要获取的统计项
     * <p>原文：Statistic to check
     * @param material statistic中,要获取值的Material
     * <p>原文：Material offset of the statistic
     * @return statistic中,material的数量
     * <p>原文：the value of the given statistic
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if material is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the given parameter is not valid
     *     for the statistic
     */
    public int getStatistic(Statistic statistic, Material material) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把该玩家的统计信息中跟方块/物品有关的统计项中的某{@link Material}加上指定的值.
     * <p>原文：
     * Increments the given statistic for this player for the given material.
     *
     * @param statistic 要操作的统计项
     * <p>原文：Statistic to increment
     * @param material statistic里要操作的某个Material
     * <p>原文：Material to offset the statistic with
     * @param amount 要把statistic中的material增加多少
     * <p>原文：Amount to increment this statistic by
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if material is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if amount is negative
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the given parameter is not valid
     *     for the statistic
     */
    public void incrementStatistic(Statistic statistic, Material material, int amount) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把该玩家的统计信息中跟方块/物品有关的统计项中的某{@link Material}减少指定的值.
     * <p>原文：
     * Decrements the given statistic for this player for the given material.
     *
     * @param statistic 要减少的统计项
     * <p>原文：Statistic to decrement
     * @param material statistic中要操作的某个Material
     * <p>原文：Material to offset the statistic with
     * @param amount 要把statistic中的material减少多少
     * <p>原文：Amount to decrement this statistic by
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if material is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if amount is negative
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the given parameter is not valid
     *     for the statistic
     */
    public void decrementStatistic(Statistic statistic, Material material, int amount) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把该玩家的统计信息中的某个跟方块/物品有关的统计项中的某{@link Material}的数量设定为某值.
     * <p>原文：
     * Sets the given statistic for this player for the given material.
     *
     * @param statistic 要设置的统计项
     * <p>原文：Statistic to set
     * @param material statistic中的某个Material
     * <p>原文：Material to offset the statistic with
     * @param newValue 要把statistic中的material设置的值
     * <p>原文：The value to set this statistic to
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if material is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if newValue is negative
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the given parameter is not valid
     *     for the statistic
     */
    public void setStatistic(Statistic statistic, Material material, int newValue) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把该玩家的统计信息中跟实体有关的统计项中的某种实体({@link EntityType})的数量+1. <p>
     * 这相当于下面的代码: <p>
     * <code>incrementStatistic(Statistic, EntityType, 1)</code>
     * <p>原文：
     * Increments the given statistic for this player for the given entity.
     * <p>
     * This is equivalent to the following code:
     * <code>incrementStatistic(Statistic, EntityType, 1)</code>
     *
     * @param statistic 要更改的统计项
     * <p>原文：Statistic to increment
     * @param entityType statistic中的某种实体
     * <p>原文：EntityType to offset the statistic with
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if entityType is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the given parameter is not valid
     *     for the statistic
     */
    public void incrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把该玩家的统计信息中跟实体有关的统计项中的某种实体({@link EntityType})的数量-1. <p>
     * 这相当于下面的代码: <p>
     * <code>decrementStatistic(Statistic, EntityType, 1)</code>
     * <p>原文：
     * Decrements the given statistic for this player for the given entity.
     * <p>
     * This is equivalent to the following code:
     * <code>decrementStatistic(Statistic, EntityType, 1)</code>
     *
     * @param statistic 要更改的统计项
     * <p>原文：Statistic to decrement
     * @param entityType statistic中的某种实体
     * <p>原文：EntityType to offset the statistic with
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if entityType is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the given parameter is not valid
     *     for the statistic
     */
    public void decrementStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得该玩家的统计信息中跟实体有关的统计项中的某实体({@link EntityType})数量.
     * <p>原文：
     * Gets the value of the given statistic for this player.
     *
     * @param statistic 要获得某实体数的统计项
     * <p>原文：Statistic to check
     * @param entityType 要获取数量的statistic的某实体
     * <p>原文：EntityType offset of the statistic
     * @return statistic中entitytype中的值
     * <p>原文：the value of the given statistic
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if entityType is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the given parameter is not valid
     *     for the statistic
     */
    public int getStatistic(Statistic statistic, EntityType entityType) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将该玩家的统计信息中的某个跟实体有关的统计项的某实体类型({@link EntityType})加上指定的数值.
     * <p>原文：
     * Increments the given statistic for this player for the given entity.
     *
     * @param statistic 要操作的统计项
     * <p>原文：Statistic to increment
     * @param entityType 要增加的statistic中的某实体
     * <p>原文：EntityType to offset the statistic with
     * @param amount 要增加的数量
     * <p>原文：Amount to increment this statistic by
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if entityType is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if amount is negative
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the given parameter is not valid
     *     for the statistic
     */
    public void incrementStatistic(Statistic statistic, EntityType entityType, int amount) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将该玩家的统计信息中某个跟实体有关的统计项中的某实体类型({@link EntityType})减少指定的值.
     * <p>原文：
     * Decrements the given statistic for this player for the given entity.
     *
     * @param statistic 要操作的统计项
     * <p>原文：Statistic to decrement
     * @param entityType 要减少的statistic中的某实体
     * <p>原文：EntityType to offset the statistic with
     * @param amount 要减少的数量
     * <p>原文：Amount to decrement this statistic by
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if entityType is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if amount is negative
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the given parameter is not valid
     *     for the statistic
     */
    public void decrementStatistic(Statistic statistic, EntityType entityType, int amount);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置该玩家的统计信息中某个跟实体有关的统计项的某实体类型({@link EntityType})为某个数值.
     * <p>原文：
     * Sets the given statistic for this player for the given entity.
     *
     * @param statistic 要设置的统计项
     * <p>原文：Statistic to set
     * @param entityType 要设置的statistic中的某实体类型
     * <p>原文：EntityType to offset the statistic with
     * @param newValue 要把statistic中entitytype的数量设置成的值
     * <p>原文：The value to set this statistic to
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if statistic is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if entityType is null
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if newValue is negative
     * @throws IllegalArgumentException 如果statistic为null
     * <p>原文：if the given parameter is not valid
     *     for the statistic
     */
    public void setStatistic(Statistic statistic, EntityType entityType, int newValue);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家最后一次死亡的位置.
     * <p>原文：
     * Gets the player's last death location.
     *
     * @return 最后一次死亡的位置, 如果不存在则返回 null.
     * <p>原文：the last death location if it exists, otherwise null.
     */
    @Nullable
    public Location getLastDeathLocation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家当前位置.
     * <p>原文：
     * Gets the player's current location.
     *
     * @return 玩家的位置, 如果玩家从未游玩过则返回 {@code null}.
     * <p>原文：the player's location, {@code null} if player hasn't ever played
     * before.
     */
    @Nullable
    public Location getLocation();
    // Paper start - add pdc to offline player
    /**
     * Yields a view of the persistent data container for this offline player.
     * In case this {@link OfflinePlayer} instance was created for an offline player, the returned view will wrap the persistent
     * data on disk.
     * <p>
     * As such, this method as well as queries to the {@link io.papermc.paper.persistence.PersistentDataContainerView}
     * may produce blocking IO requests to read the requested data from disk.
     * Caution in its usage is hence advised.
     *
     * @return the persistent data container view
     * @see io.papermc.paper.persistence.PersistentDataViewHolder#getPersistentDataContainer()
     */
    @Override
    io.papermc.paper.persistence.PersistentDataContainerView getPersistentDataContainer();
    // Paper end - add pdc to offline player

    @Override
    default void applySkinToPlayerHeadContents(final PlayerHeadObjectContents.Builder builder) {
        builder.skin(this.getPlayerProfile());
    }
}
