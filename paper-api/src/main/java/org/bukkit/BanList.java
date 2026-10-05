package org.bukkit;

import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个封禁列表，包含了一些{@link Type 封禁类型}
 * <p>原文：
 * A ban list, containing bans of some {@link Type}.
 *
 * @param <T> 封禁目标
 * <p>原文：The ban target
 */
public interface BanList<T> {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 封禁类型.
     * <p>原文：
     * Represents a ban-type that a {@link BanList} may track.
     *
     * @deprecated use {@link io.papermc.paper.ban.BanListType} to enforce the correct return value at compile time.
     */
    @Deprecated(since = "1.20.4") // Paper - BanList Type Improvements
    public enum Type {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 已封禁的玩家名称
         * <p>原文：
         * Banned player names
         *
         * @deprecated 被 {@link #PROFILE} 取代
         * <p>原文：deprecated in favor of {@link #PROFILE}
         */
        @Deprecated(since = "1.20.1")
        NAME,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 已封禁的玩家ip地址
         * <p>原文：
         * Banned IP addresses
         */
        IP,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 已封禁的玩家资料
         * <p>原文：
         * Banned player profiles
         */
        PROFILE,
        ;
    }

    /**
     * Gets a {@link BanEntry} by target.
     * <p>
     * Bans by name for ban type {@link Type#NAME NAME} are no longer supported and this method will return
     * null when trying to request them. The replacement is bans by UUID.
     *
     * @param target entry parameter to search for
     * @return the corresponding entry, or null if none found
     * @deprecated see {@link #getBanEntry(Object)}
     */
    @Deprecated(since = "1.20.1")
    @Nullable
    public <E extends BanEntry<? super T>> E getBanEntry(@NotNull String target); // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据封禁目标(IP地址或玩家资料)来获取对应的 {@link BanEntry}.
     * <p>原文：
     * Gets a {@link BanEntry} by target.
     *
     * @param target 封禁目标(IP地址或玩家资料)
     * <p>原文：entry parameter to search for
     * @return 对应的封禁条目, 如果没有则返回null
     * <p>原文：the corresponding entry, or null if none found
     */
    @Nullable
    public BanEntry<T> getBanEntry(@NotNull T target);

    /**
     * Adds a ban to this list. If a previous ban exists, this will
     * update the previous entry.
     * <p>
     * Bans by name for ban type {@link Type#NAME NAME} are no longer supported and this method will return
     * null when trying to request them. The replacement is bans by UUID.
     *
     * @param target the target of the ban
     * @param reason reason for the ban, null indicates implementation default
     * @param expires date for the ban's expiration (unban), or null to imply
     *     forever
     * @param source source of the ban, null indicates implementation default
     * @return the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     * @deprecated see {@link #addBan(Object, String, Date, String)}
     */
    @Deprecated(since = "1.20.1")
    @Nullable
    public <E extends BanEntry<? super T>> E addBan(@NotNull String target, @Nullable String reason, @Nullable Date expires, @Nullable String source); // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加一个封禁到这个列表。如果以前的封禁存在，这将更新以前的封禁.
     * <p>原文：
     * Adds a ban to this list. If a previous ban exists, this will
     * update the previous entry.
     *
     * @param target 封禁目标
     * <p>原文：the target of the ban
     * @param reason 封禁理由，null则使用默认
     * <p>原文：reason for the ban, null indicates implementation default
     * @param expires 封禁的截止日期(解除封禁)，null则为永远封禁
     * <p>原文：date for the ban's expiration (unban), or null to imply
     *     forever
     * @param source 封禁来源，null则使用默认
     * <p>原文：source of the ban, null indicates implementation default
     * @return 新创建的封禁条目，或为更新之前的封禁
     * <p>原文：the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     */
    @Nullable
    public BanEntry<T> addBan(@NotNull T target, @Nullable String reason, @Nullable Date expires, @Nullable String source);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加一个封禁到这个列表。如果以前的封禁存在，这将更新以前的封禁.
     * <p>原文：
     * Adds a ban to this list. If a previous ban exists, this will
     * update the previous entry.
     *
     * @param target 封禁目标
     * <p>原文：the target of the ban
     * @param reason 封禁理由，null则使用默认
     * <p>原文：reason for the ban, null indicates implementation default
     * @param expires 封禁的截止日期(解除封禁)，null则为永远封禁
     * <p>原文：instant for the ban's expiration (unban), or null to imply
     *     forever
     * @param source 封禁来源，null则使用默认
     * <p>原文：source of the ban, null indicates implementation default
     * @return 新创建的封禁条目，或为更新之前的封禁
     * <p>原文：the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     */
    @Nullable
    public BanEntry<T> addBan(@NotNull T target, @Nullable String reason, @Nullable Instant expires, @Nullable String source);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加一个封禁到这个列表。如果以前的封禁存在，这将更新以前的封禁.
     * <p>原文：
     * Adds a ban to this list. If a previous ban exists, this will
     * update the previous entry.
     *
     * @param target 封禁目标
     * <p>原文：the target of the ban
     * @param reason 封禁理由，null则使用默认
     * <p>原文：reason for the ban, null indicates implementation default
     * @param duration 封禁持续时长, null则为永远封禁
     * <p>原文：the duration of the ban, or null to imply
     *     forever
     * @param source 封禁来源，null则使用默认
     * <p>原文：source of the ban, null indicates implementation default
     * @return 新创建的封禁条目，或为更新之前的封禁
     * <p>原文：the entry for the newly created ban, or the entry for the
     *     (updated) previous ban
     */
    @Nullable
    public BanEntry<T> addBan(@NotNull T target, @Nullable String reason, @Nullable Duration duration, @Nullable String source);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此列表包含的所有{@link BanEntry}.
     * <p>原文：
     * Gets a set containing every {@link BanEntry} in this list.
     *
     * @return 包含了通过此列表跟踪的每个BanEntry的一个不可变列表
     * <p>原文：an immutable set containing every entry tracked by this list
     * @deprecated 该方法返回了一个形参化类的原始使用, 推荐使用带泛型参数的 {@link #getEntries()}
     * <p>原文：This return a generic class, prefer use {@link #getEntries()}
     */
    @Deprecated(since = "1.20.1")
    @NotNull
    public Set<BanEntry> getBanEntries();

    /**
     * <!-- Chinese_BukkitAPI CC BY-NC 4.0 -->
     * 获取此列表包含的所有{@link BanEntry}.
     * <p>原文：
     * Gets a set containing every {@link BanEntry} in this list.
     *
     * @return an immutable set containing every entry tracked by this list
     */
    @NotNull
    public <E extends BanEntry<? super T>> Set<E> getEntries(); // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此目标的封禁状态.
     * <p>原文：
     * Gets if a {@link BanEntry} exists for the target, indicating an active
     * ban status.
     * <p>
     * Bans by name for ban type {@link Type#NAME NAME} are no longer supported.
     * The replacement is bans by UUID.
     *
     * @param target 寻找的目标
     * <p>原文：the target to find
     * @return 如果{@link BanEntry}存在这个目标则表示被封禁了，否则为false
     * <p>原文：true if a {@link BanEntry} exists for the target, indicating an
     *     active ban status, false otherwise
     */
    public boolean isBanned(@NotNull T target);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此目标的封禁状态.
     * <p>原文：
     * Gets if a {@link BanEntry} exists for the target, indicating an active
     * ban status.
     *
     * @param target 寻找的目标
     * <p>原文：the target to find
     * @return 如果{@link BanEntry}存在这个目标则表示被封禁了，否则为false
     * <p>原文：true if a {@link BanEntry} exists for the target, indicating an
     *     active ban status, false otherwise
     * @deprecated 参见 {@link #isBanned(Object)}
     * <p>原文：see {@link #isBanned(Object)}
     */
    @Deprecated(since = "1.20.1")
    public boolean isBanned(@NotNull String target);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从列表中移除指定目标，因此表示“无封禁”的状态(即解除封禁).
     * <p>原文：
     * Removes the specified target from this list, therefore indicating a
     * "not banned" status.
     * <p>
     * Bans by name for ban type {@link Type#NAME NAME} are no longer supported.
     * The replacement is bans by UUID.
     *
     * @param target 从这个列表移除的目标(解除封禁)
     * <p>原文：the target to remove from this list
     */
    public void pardon(@NotNull T target);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从列表中移除指定目标，因此表示“无封禁”的状态(即解除封禁).
     * <p>原文：
     * Removes the specified target from this list, therefore indicating a
     * "not banned" status.
     *
     * @param target 从这个列表移除的目标(解除封禁)
     * <p>原文：the target to remove from this list
     * @deprecated 参见 {@link #pardon(Object)}
     * <p>原文：see {@link #pardon(Object)}
     */
    @Deprecated(since = "1.20.1")
    public void pardon(@NotNull String target);
}
