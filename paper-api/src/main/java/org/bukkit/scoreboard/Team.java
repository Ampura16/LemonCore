package org.bukkit.scoreboard;

import java.util.Set;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.potion.PotionEffectType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 计分板上的队伍, 拥有共同的显示名和其它属性.
 * 此队伍只与 {@link #getScoreboard()} 获取的计分板相关联.
 * <p>原文：
 * A team on a scoreboard that has a common display theme and other
 * properties. This team is only relevant to the display of the associated
 * {@link #getScoreboard() scoreboard}.
 */
public interface Team extends net.kyori.adventure.audience.ForwardingAudience { // Paper - Make Team extend ForwardingAudience

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此队伍的名称.
     * <p>原文：
     * Gets the name of this Team
     *
     * @return 队伍名
     * <p>原文：Objective name
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     */
    @NotNull
    String getName();

    // Paper start - Adventure
    /**
     * Gets the display name for this team
     *
     * @return Team display name
     * @throws IllegalStateException if this team has been unregistered
     */
    net.kyori.adventure.text.@NotNull Component displayName();

    /**
     * Sets the name displayed to entries for this team
     *
     * @param displayName New display name
     * @throws IllegalStateException if this team has been unregistered
     */
    void displayName(net.kyori.adventure.text.@Nullable Component displayName);

    /**
     * Gets the prefix prepended to the display of entries on this team.
     *
     * @return Team prefix
     * @throws IllegalStateException if this team has been unregistered
     */
    net.kyori.adventure.text.@NotNull Component prefix();

    /**
     * Sets the prefix prepended to the display of entries on this team.
     *
     * @param prefix New prefix
     * @throws IllegalStateException if this team has been unregistered
     */
    void prefix(net.kyori.adventure.text.@Nullable Component prefix);

    /**
     * Gets the suffix appended to the display of entries on this team.
     *
     * @return the team's current suffix
     * @throws IllegalStateException if this team has been unregistered
     */
    net.kyori.adventure.text.@NotNull Component suffix();

    /**
     * Sets the suffix appended to the display of entries on this team.
     *
     * @param suffix the new suffix for this team.
     * @throws IllegalStateException if this team has been unregistered
     */
    void suffix(net.kyori.adventure.text.@Nullable Component suffix);

    /**
     * Checks if the team has a color specified
     *
     * @return true if it has a <b>color</b>
     * @throws IllegalStateException if this team has been unregistered
     */
    boolean hasColor();

    /**
     * Gets the color of the team.
     * <br>
     * This only sets the team outline, other occurrences of colors such as in
     * names are handled by prefixes / suffixes.
     *
     * @return team color
     * @throws IllegalStateException if this team has been unregistered
     * @throws IllegalStateException if the team doesn't have a color
     * @see #hasColor()
     */
    net.kyori.adventure.text.format.@NotNull TextColor color();

    /**
     * Sets the color of the team.
     * <br>
     * This only sets the team outline, other occurrences of colors such as in
     * names are handled by prefixes / suffixes.
     *
     * @param color new color, null for no color
     */
    void color(net.kyori.adventure.text.format.@Nullable NamedTextColor color);
    // Paper end - Adventure

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此队伍的显示名.
     * <p>原文：
     * Gets the name displayed to entries for this team
     *
     * @return 队伍显示名
     * <p>原文：Team display name
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     * @deprecated in favour of {@link #displayName()}
     */
    @NotNull
    @Deprecated // Paper
    String getDisplayName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置队伍的显示名称.
     * <p>原文：
     * Sets the name displayed to entries for this team
     *
     * @param displayName 队伍显示名
     * <p>原文：New display name
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     * @deprecated in favour of {@link #displayName(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    void setDisplayName(@NotNull String displayName);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取添加于队员名前的前缀.
     * <p>原文：
     * Gets the prefix prepended to the display of entries on this team.
     *
     * @return 队员名前缀
     * <p>原文：Team prefix
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     * @deprecated in favour of {@link #prefix()}
     */
    @NotNull
    @Deprecated // Paper
    String getPrefix();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置添加于队员名前的前缀.
     * <p>原文：
     * Sets the prefix prepended to the display of entries on this team.
     *
     * @param prefix 队员名前缀
     * <p>原文：New prefix
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     * @deprecated in favour of {@link #prefix(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    void setPrefix(@NotNull String prefix);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取添加于队员名后的后缀.
     * <p>原文：
     * Gets the suffix appended to the display of entries on this team.
     *
     * @return 队员名后缀
     * <p>原文：the team's current suffix
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     * @deprecated in favour of {@link #suffix()}
     */
    @NotNull
    @Deprecated // Paper
    String getSuffix();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置添加于队员名后的后缀.
     * <p>原文：
     * Sets the suffix appended to the display of entries on this team.
     *
     * @param suffix 要设置的队员名后缀
     * <p>原文：the new suffix for this team.
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     * @deprecated in favour of {@link #suffix(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    void setSuffix(@NotNull String suffix);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取队伍的颜色.
     * <p>
     * 此颜色只代表队伍主要颜色,
     * 其它颜色比如队员名前后缀的颜色由其前缀/后缀处理.
     * <p>原文：
     * Gets the color of the team.
     * <br>
     * This only sets the team outline, other occurrences of colors such as in
     * names are handled by prefixes / suffixes.
     *
     * @return 队伍颜色, 默认为 {@link ChatColor#RESET}
     * <p>原文：team color, defaults to {@link ChatColor#RESET}
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     * @deprecated in favour of {@link #color()}
     */
    @NotNull
    @Deprecated // Paper
    ChatColor getColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置队伍的颜色.
     * <p>
     * 本方法只是设置了队伍主要颜色,
     * 其它颜色比如队员名前后缀的颜色由其前缀/后缀处理.
     * <p>原文：
     * Sets the color of the team.
     * <br>
     * This only sets the team outline, other occurrences of colors such as in
     * names are handled by prefixes / suffixes.
     *
     * @param color 新颜色, 不能为 null. 使用 {@link ChatColor#RESET} 重置颜色
     * <p>原文：new color, must be non-null. Use {@link ChatColor#RESET} for
     * no color
     * @deprecated in favour of {@link #color(net.kyori.adventure.text.format.NamedTextColor)}
     */
    @Deprecated // Paper
    void setColor(@NotNull ChatColor color);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取队伍成员能否互相攻击.
     * <p>原文：
     * Gets the team friendly fire state
     *
     * @return 如果允许互相攻击则为true
     * <p>原文：true if friendly fire is enabled
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     */
    boolean allowFriendlyFire();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置队伍成员能否互相攻击.
     * <p>原文：
     * Sets the team friendly fire state
     *
     * @param enabled 如果允许互相攻击则为true
     * <p>原文：true if friendly fire is to be allowed
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     */
    void setAllowFriendlyFire(boolean enabled);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此队伍能否看见处于{@link PotionEffectType#INVISIBILITY
     * 隐身}状态效果下的队友.
     * <p>原文：
     * Gets the team's ability to see {@link PotionEffectType#INVISIBILITY
     * invisible} teammates.
     *
     * @return 如果隐身队友可被同队队友看见则为true
     * <p>原文：true if team members can see invisible members
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     */
    boolean canSeeFriendlyInvisibles();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此队伍能否看见处于{@link PotionEffectType#INVISIBILITY
     * 隐身}状态效果下的队友.
     * <p>原文：
     * Sets the team's ability to see {@link PotionEffectType#INVISIBILITY
     * invisible} teammates.
     *
     * @param enabled 如果隐身队友可被同队队友看见则为true
     * <p>原文：true if invisible teammates are to be visible
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     */
    void setCanSeeFriendlyInvisibles(boolean enabled);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此队伍的玩家名可见性.
     * <p>原文：
     * Gets the team's ability to see name tags
     *
     * @return 当前设置的可见性
     * <p>原文：the current name tag visibility for the team
     * @throws IllegalArgumentException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     * @deprecated 另请参阅 {@link #getOption(Team.Option)}
     * <p>原文：see {@link #getOption(Team.Option)}
     */
    @Deprecated(since = "1.9")
    @NotNull
    NameTagVisibility getNameTagVisibility();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此队伍的玩家名可见性.
     * <p>原文：
     * Set's the team's ability to see name tags
     *
     * @param visibility 要设置的 nameTagVisibility
     * <p>原文：The nameTagVisibility to set
     * @throws IllegalArgumentException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     * @deprecated 另请参阅 {@link #setOption(Team.Option, Team.OptionStatus)}
     * <p>原文：see {@link #setOption(Team.Option, Team.OptionStatus)}
     */
    @Deprecated(since = "1.9")
    void setNameTagVisibility(@NotNull NameTagVisibility visibility);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取队伍内所有玩家的集合.
     * <p>原文：
     * Gets the Set of players on the team
     *
     * @return 队伍玩家集合
     * <p>原文：players on the team
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     * @see #getEntries()
     * @deprecated 队伍可包含非玩家项目
     * <p>原文：Teams can contain entries that aren't players
     */
    @Deprecated(since = "1.8.6")
    @NotNull
    Set<OfflinePlayer> getPlayers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取队伍内所有项目的集合.
     * <p>原文：
     * Gets the Set of entries on the team
     *
     * @return 队伍项目集合
     * <p>原文：entries on the team
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     */
    @NotNull
    Set<String> getEntries();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此队伍的队员数.
     * <p>原文：
     * Gets the size of the team
     *
     * @return 队伍队员数
     * <p>原文：number of entries on the team
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     */
    int getSize();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此队伍所附属的计分板.
     * <p>原文：
     * Gets the Scoreboard to which this team is attached
     *
     * @return 管理此队伍的计分板, 如果此队伍已被{@link #unregister() 注销}则返回 null
     * <p>原文：Owning scoreboard, or null if this team has been {@link
     *     #unregister() unregistered}
     */
    @Nullable
    Scoreboard getScoreboard();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将某玩家加入这个队伍.
     * <p>
     * 这将使此玩家离开其它队伍.
     * <p>原文：
     * This puts the specified player onto this team for the scoreboard.
     * <p>
     * This will remove the player from any other team on the scoreboard.
     *
     * @param player 要加入的玩家
     * <p>原文：the player to add
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     * @throws IllegalArgumentException if {@link OfflinePlayer#getName()} is null
     * @see #addEntry(String)
     */
    void addPlayer(@NotNull OfflinePlayer player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将某条项目加入这个队伍.
     * <p>
     * 这将使此项目离开其它队伍.
     * <p>原文：
     * This puts the specified entry onto this team for the scoreboard.
     * <p>
     * This will remove the entry from any other team on the scoreboard.
     *
     * @param entry 要加入的项目
     * <p>原文：the entry to add
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     */
    void addEntry(@NotNull String entry);

    // Paper start
    /**
     * This puts a collection of entities onto this team for the scoreboard which results in one
     * packet for the updates rather than a packet-per-entity.
     * <p>
     * Entities on other teams will be removed from their respective teams.
     *
     * @param entities the entities to add
     * @throws IllegalArgumentException if entities are null
     * @throws IllegalStateException if this team has been unregistered
     */
    default void addEntities(@NotNull org.bukkit.entity.Entity @NotNull ...entities) {
        this.addEntities(java.util.List.of(entities));
    }

    /**
     * This puts a collection of entities onto this team for the scoreboard which results in one
     * packet for the updates rather than a packet-per-entity.
     * <p>
     * Entities on other teams will be removed from their respective teams.
     *
     * @param entities the entities to add
     * @throws IllegalArgumentException if entities are null
     * @throws IllegalStateException if this team has been unregistered
     */
    void addEntities(@NotNull java.util.Collection<org.bukkit.entity.Entity> entities) throws IllegalStateException, IllegalArgumentException;

    /**
     * This puts a collection of entries onto this team for the scoreboard which results in one
     * packet for the updates rather than a packet-per-entry.
     * <p>
     * Entries on other teams will be removed from their respective teams.
     *
     * @param entries the entries to add
     * @throws IllegalArgumentException if entries are null
     * @throws IllegalStateException if this team has been unregistered
     */
    default void addEntries(@NotNull String... entries) throws IllegalStateException, IllegalArgumentException {
        this.addEntries(java.util.List.of(entries));
    }

    /**
     * This puts a collection of entries onto this team for the scoreboard which results in one
     * packet for the updates rather than a packet-per-entry.
     * <p>
     * Entries on other teams will be removed from their respective teams.
     *
     * @param entries the entries to add
     * @throws IllegalArgumentException if entries are null
     * @throws IllegalStateException if this team has been unregistered
     */
    void addEntries(@NotNull java.util.Collection<String> entries) throws IllegalStateException, IllegalArgumentException;
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使某玩家离开此队伍.
     * <p>原文：
     * Removes the player from this team.
     *
     * @param player 要移除的玩家
     * <p>原文：the player to remove
     * @return 此玩家是否曾为队伍中的一员
     * <p>原文：if the player was on this team
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     * @throws IllegalArgumentException if {@link OfflinePlayer#getName()} is null
     * @see #removeEntry(String)
     */
    boolean removePlayer(@NotNull OfflinePlayer player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除队伍中的某条项目.
     * <p>原文：
     * Removes the entry from this team.
     *
     * @param entry 要移除的项目
     * <p>原文：the entry to remove
     * @return 此项目是否曾为队伍中的一员
     * <p>原文：if the entry was a part of this team
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     */
    boolean removeEntry(@NotNull String entry);

    // Paper start
    /**
     * Removes a collection of entities from this team which results in one
     * packet for the updates rather than a packet-per-entity.
     *
     * @param entities the entries to remove
     * @return if any of the entities were a part of this team
     * @throws IllegalArgumentException if entities is null
     * @throws IllegalStateException if this team has been unregistered
     */
    default boolean removeEntities(@NotNull org.bukkit.entity.Entity @NotNull ... entities) throws IllegalStateException, IllegalArgumentException {
        return this.removeEntities(java.util.List.of(entities));
    }

    /**
     * Removes a collection of entities from this team which results in one
     * packet for the updates rather than a packet-per-entity.
     *
     * @param entities the entries to remove
     * @return if any of the entities were a part of this team
     * @throws IllegalArgumentException if entities is null
     * @throws IllegalStateException if this team has been unregistered
     */
    boolean removeEntities(@NotNull java.util.Collection<org.bukkit.entity.Entity> entities) throws IllegalStateException, IllegalArgumentException;

    /**
     * Removes a collection of entries from this team which results in one
     * packet for the updates rather than a packet-per-entry.
     *
     * @param entries the entries to remove
     * @return if any of the entries were a part of this team
     * @throws IllegalArgumentException if entries is null
     * @throws IllegalStateException if this team has been unregistered
     */
    default boolean removeEntries(@NotNull String... entries) throws IllegalStateException, IllegalArgumentException {
        return this.removeEntries(java.util.List.of(entries));
    }

    /**
     * Removes a collection of entries from this team which results in one
     * packet for the updates rather than a packet-per-entry.
     *
     * @param entries the entries to remove
     * @return if any of the entries were a part of this team
     * @throws IllegalArgumentException if entries is null
     * @throws IllegalStateException if this team has been unregistered
     */
    boolean removeEntries(@NotNull java.util.Collection<String> entries) throws IllegalStateException, IllegalArgumentException;
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从这个计分板上注销这个队伍.
     * <p>原文：
     * Unregisters this team from the Scoreboard
     *
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     */
    void unregister();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测某玩家是否在此队伍内.
     * <p>原文：
     * Checks to see if the specified player is a member of this team.
     *
     * @param player 要查找的玩家
     * <p>原文：the player to search for
     * @return 如果在队伍内就返回true
     * <p>原文：true if the player is a member of this team
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     * @throws IllegalArgumentException if {@link OfflinePlayer#getName()} is null
     * @see #hasEntry(String)
     */
    boolean hasPlayer(@NotNull OfflinePlayer player);
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测某项目是否在此队伍内.
     * <p>原文：
     * Checks to see if the specified entry is a member of this team.
     *
     * @param entry 要查找的项目
     * <p>原文：the entry to search for
     * @return 如果在队伍内就返回true
     * <p>原文：true if the entry is a member of this team
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     */
    boolean hasEntry(@NotNull String entry);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取队伍的一个选项.
     * <p>原文：
     * Get an option for this team
     *
     * @param option 要获取的选项
     * <p>原文：the option to get
     * @return 选项状态值
     * <p>原文：the option status
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     */
    @NotNull
    OptionStatus getOption(@NotNull Option option);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为队伍设置一个选项.
     * <p>原文：
     * Set an option for this team
     *
     * @param option 要设置的选项
     * <p>原文：the option to set
     * @param status 选项状态值
     * <p>原文：the new option status
     * @throws IllegalStateException 若此队伍已被注销
     * <p>原文：if this team has been unregistered
     */
    void setOption(@NotNull Option option, @NotNull OptionStatus status);

    // Paper start - improve scoreboard entries
    /**
     * This puts the specified entity onto this team for the scoreboard.
     * <p>
     * This will remove the entity from any other team on the scoreboard.
     *
     * @param entity the entity to add
     * @throws IllegalArgumentException if entity is null
     * @throws IllegalStateException if this team has been unregistered
     * @see #addEntry(String)
     */
    void addEntity(@NotNull org.bukkit.entity.Entity entity) throws IllegalStateException, IllegalArgumentException;

    /**
     * Removes the entity from this team.
     *
     * @param entity the entity to remove
     * @return if the entity was on this team
     * @throws IllegalArgumentException if entity is null
     * @throws IllegalStateException if this team has been unregistered
     * @see #removeEntry(String)
     */
    boolean removeEntity(@NotNull org.bukkit.entity.Entity entity) throws IllegalStateException, IllegalArgumentException;

    /**
     * Checks to see if the specified entity is a member of this team.
     *
     * @param entity the entity to search for
     * @return true if the entity is a member of this team
     * @throws IllegalArgumentException if entity is null
     * @throws IllegalStateException if this team has been unregistered
     * @see #hasEntry(String)
     */
    boolean hasEntity(@NotNull org.bukkit.entity.Entity entity) throws IllegalStateException, IllegalArgumentException;
    // Paper end - improve scoreboard entries

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表可应用于队伍的选项.
     * <p>
     * 译注:对于所有的 Option, 其 OptionStatus 默认值均为 ALWAYS.
     * 这些 Option 对应的 OptionStatus 的意义不尽相同, 为避免歧义,
     * 对应 OptionStatus 的效果用表格列举如下:
     * <table border=1>
     * <caption>Option - OptionStatus 对照表</caption>
     * <tr>
     *     <th></th>
     *     <th>NAME_TAG_VISIBILITY</th>
     *     <th>DEATH_MESSAGE_VISIBILITY</th>
     *     <th>COLLISION_RULE</th>
     * </tr>
     * <tr>
     *     <th>ALWAYS</th>
     *     <td>一直显示玩家名称</td>
     *     <td>一直显示死亡消息</td>
     *     <td>启用碰撞</td>
     * </tr>
     * <tr>
     *     <th>NEVER</th>
     *     <td>对所有玩家隐藏玩家名称</td>
     *     <td>对所有玩家隐藏死亡消息</td>
     *     <td>队内没有玩家可被推动</td>
     * </tr>
     * <tr>
     *     <th>FOR_OTHER_TEAMS</th>
     *     <td>对其它队伍玩家隐藏玩家名称(仅本队能看到)</td>
     *     <td>对其它队伍玩家隐藏死亡消息</td>
     *     <td>仅队内玩家可互相推动</td>
     * </tr>
     * <tr>
     *     <th>FOR_OWN_TEAM</th>
     *     <td>对同队玩家隐藏玩家名称(其他人能看到)</td>
     *     <td>对同队玩家隐藏死亡消息</td>
     *     <td>仅其它队伍玩家可推动本队玩家</td>
     * </tr>
     * </table>
     * <p>原文：
     * Represents an option which may be applied to this team.
     */
    public enum Option {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家显示名的可见性.
         * <p>原文：
         * How to display the name tags of players on this team.
         */
        NAME_TAG_VISIBILITY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 玩家死亡消息的可见性.
         * <p>原文：
         * How to display the death messages for players on this team.
         */
        DEATH_MESSAGE_VISIBILITY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 队伍中玩家与其他人的碰撞规则.
         * <p>原文：
         * How players of this team collide with others.
         */
        COLLISION_RULE;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 选项应用给队伍成员的方式.
     * <p>
     * 译注:对于所有的 Option, 其 OptionStatus 默认值均为 ALWAYS.
     * 这些 Option 对应的 OptionStatus 的意义不尽相同, 为避免歧义,
     * 对应 OptionStatus 的效果用表格列举如下:
     * <table border=1>
     * <caption>Option - OptionStatus 对照表</caption>
     * <tr>
     *     <th></th>
     *     <th>NAME_TAG_VISIBILITY</th>
     *     <th>DEATH_MESSAGE_VISIBILITY</th>
     *     <th>COLLISION_RULE</th>
     * </tr>
     * <tr>
     *     <th>ALWAYS</th>
     *     <td>一直显示玩家名称</td>
     *     <td>一直显示死亡消息</td>
     *     <td>启用碰撞</td>
     * </tr>
     * <tr>
     *     <th>NEVER</th>
     *     <td>对所有玩家隐藏玩家名称</td>
     *     <td>对所有玩家隐藏死亡消息</td>
     *     <td>队内没有玩家可被推动</td>
     * </tr>
     * <tr>
     *     <th>FOR_OTHER_TEAMS</th>
     *     <td>对其它队伍玩家隐藏玩家名称(仅本队能看到)</td>
     *     <td>对其它队伍玩家隐藏死亡消息</td>
     *     <td>仅队内玩家可互相推动</td>
     * </tr>
     * <tr>
     *     <th>FOR_OWN_TEAM</th>
     *     <td>对同队玩家隐藏玩家名称(其他人能看到)</td>
     *     <td>对同队玩家隐藏死亡消息</td>
     *     <td>仅其它队伍玩家可推动本队玩家</td>
     * </tr>
     * </table>
     * <p>原文：
     * How an option may be applied to members of this team.
     */
    public enum OptionStatus {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向所有人应用此选项.
         * <p>原文：
         * Apply this option to everyone.
         */
        ALWAYS,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 永不应用此选项.
         * <p>原文：
         * Never apply this option.
         */
        NEVER,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向其它队伍应用选项.
         * <p>原文：
         * Apply this option only for opposing teams.
         */
        FOR_OTHER_TEAMS,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 只向当前队伍的成员应用此选项.
         * <p>原文：
         * Apply this option for only team members.
         */
        FOR_OWN_TEAM;
    }
}
