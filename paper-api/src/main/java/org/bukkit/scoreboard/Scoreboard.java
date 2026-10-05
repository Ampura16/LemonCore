package org.bukkit.scoreboard;

import java.util.Set;
import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 单个计分板.
 * <p>原文：
 * A scoreboard
 */
public interface Scoreboard {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向计分板注册一个计分项.
     * <p>原文：
     * Registers an Objective on this Scoreboard
     *
     * @param name 计分项名称
     * <p>原文：Name of the Objective
     * @param criteria 计分项的准则
     * <p>原文：Criteria for the Objective
     * @return 注册的计分项实例
     * <p>原文：The registered Objective
     * @throws IllegalArgumentException 若 name 长度超过 16 个字符
     * (注:此参数在1.18+支持最长32767个字符)
     * <p>原文：if name is longer than 32767
     *     characters.
     * @throws IllegalArgumentException 若 name 长度超过 16 个字符
     * (注:此参数在1.18+支持最长32767个字符)
     * <p>原文：if an objective by that name already
     *     exists
     * @deprecated 应明确指定计分项的显示名
     * <p>原文：a displayName should be explicitly specified
     */
    @Deprecated(since = "1.13")
    @NotNull
    default Objective registerNewObjective(@NotNull String name, @NotNull String criteria) {
        return this.registerNewObjective(name, criteria, name);
    }

    /**
     * Registers an Objective on this Scoreboard
     *
     * @param name Name of the Objective
     * @param criteria Criteria for the Objective
     * @param displayName display name for the Objective.
     * @return The registered Objective
     * @throws IllegalArgumentException if name is longer than 32767
     *     characters.
     * @throws IllegalArgumentException if an objective by that name already
     *     exists
     * @deprecated use {@link #registerNewObjective(String, Criteria, net.kyori.adventure.text.Component)}
     */
    @NotNull
    @Deprecated
    default Objective registerNewObjective(@NotNull String name, @NotNull String criteria, net.kyori.adventure.text.@Nullable Component displayName) {
        return this.registerNewObjective(name, criteria, displayName, RenderType.INTEGER);
    }

    /**
     * Registers an Objective on this Scoreboard
     *
     * @param name Name of the Objective
     * @param criteria Criteria for the Objective
     * @param displayName Name displayed to players for the Objective.
     * @param renderType Manner of rendering the Objective
     * @return The registered Objective
     * @throws IllegalArgumentException if name is longer than 32767
     *     characters.
     * @throws IllegalArgumentException if an objective by that name already
     *     exists
     * @deprecated use {@link #registerNewObjective(String, Criteria, net.kyori.adventure.text.Component, RenderType)}
     */
    @NotNull
    @Deprecated
    Objective registerNewObjective(@NotNull String name, @NotNull String criteria, net.kyori.adventure.text.@Nullable Component displayName, @NotNull RenderType renderType) throws IllegalArgumentException;

    /**
     * Registers an Objective on this Scoreboard
     *
     * @param name Name of the Objective
     * @param criteria Criteria for the Objective
     * @param displayName Name displayed to players for the Objective.
     * @return The registered Objective
     * @throws IllegalArgumentException if name is longer than 32767
     *     characters.
     * @throws IllegalArgumentException if an objective by that name already
     *     exists
     */
    @NotNull
    default Objective registerNewObjective(@NotNull String name, @NotNull Criteria criteria, net.kyori.adventure.text.@Nullable Component displayName) throws IllegalArgumentException {
        return this.registerNewObjective(name, criteria, displayName, RenderType.INTEGER);
    }

    /**
     * Registers an Objective on this Scoreboard
     *
     * @param name Name of the Objective
     * @param criteria Criteria for the Objective
     * @param displayName Name displayed to players for the Objective.
     * @param renderType Manner of rendering the Objective
     * @return The registered Objective
     * @throws IllegalArgumentException if name is longer than 32767
     *     characters.
     * @throws IllegalArgumentException if an objective by that name already
     *     exists
     */
    @NotNull
    Objective registerNewObjective(@NotNull String name, @NotNull Criteria criteria, net.kyori.adventure.text.@Nullable Component displayName, @NotNull RenderType renderType) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向计分板注册一个计分项.
     * <p>原文：
     * Registers an Objective on this Scoreboard
     *
     * @param name 计分项名称
     * <p>原文：Name of the Objective
     * @param criteria 计分项的准则
     * <p>原文：Criteria for the Objective
     * @param displayName 展示给玩家的计分项显示名称
     * <p>原文：Name displayed to players for the Objective.
     * @return 注册的计分项实例
     * <p>原文：The registered Objective
     * @throws IllegalArgumentException 若 name 长度超过 16 个字符
     * (注:此参数在1.18+支持最长32767个字符)
     * <p>原文：if name is longer than 32767
     *     characters.
     * @throws IllegalArgumentException 若 name 长度超过 16 个字符
     * (注:此参数在1.18+支持最长32767个字符)
     * <p>原文：if an objective by that name already
     *     exists
     * @deprecated 请使用 {@link #registerNewObjective(String, Criteria, String)}
     * <p>原文：use {@link #registerNewObjective(String, Criteria, net.kyori.adventure.text.Component)}
     */
    @Deprecated(since = "1.20.5")
    @NotNull
    default Objective registerNewObjective(@NotNull String name, @NotNull String criteria, @NotNull String displayName) {
        return this.registerNewObjective(name, criteria, displayName, RenderType.INTEGER);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向计分板注册一个计分项.
     * <p>原文：
     * Registers an Objective on this Scoreboard
     *
     * @param name 计分项名称
     * <p>原文：Name of the Objective
     * @param criteria 计分项的准则
     * <p>原文：Criteria for the Objective
     * @param displayName 展示给玩家的计分项显示名称
     * <p>原文：Name displayed to players for the Objective.
     * @param renderType 计分项的渲染方式
     * <p>原文：Manner of rendering the Objective
     * @return 注册的计分项实例
     * <p>原文：The registered Objective
     * @throws IllegalArgumentException 若 name 长度超过 16 个字符
     * (注:此参数在1.18+支持最长32767个字符)
     * <p>原文：if name is longer than 32767
     *     characters.
     * @throws IllegalArgumentException 若 name 长度超过 16 个字符
     * (注:此参数在1.18+支持最长32767个字符)
     * <p>原文：if an objective by that name already
     *     exists
     * @deprecated 请使用 {@link #registerNewObjective(String, Criteria, String, RenderType)}
     * <p>原文：use {@link #registerNewObjective(String, Criteria, net.kyori.adventure.text.Component, RenderType)}
     */
    @Deprecated(since = "1.20.5")
    @NotNull
    Objective registerNewObjective(@NotNull String name, @NotNull String criteria, @NotNull String displayName, @NotNull RenderType renderType);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向计分板注册一个计分项.
     * <p>原文：
     * Registers an Objective on this Scoreboard
     *
     * @param name 计分项名称
     * <p>原文：Name of the Objective
     * @param criteria 计分项的准则
     * <p>原文：Criteria for the Objective
     * @param displayName 展示给玩家的计分项显示名称
     * <p>原文：Name displayed to players for the Objective.
     * @return 注册的计分项实例
     * <p>原文：The registered Objective
     * @throws IllegalArgumentException 若名称长度超过32767个字符
     * <p>原文：if name is longer than 32767
     *     characters.
     * @throws IllegalArgumentException 若名称长度超过32767个字符
     * <p>原文：if an objective by that name already
     *     exists
     * @deprecated in favour of {@link #registerNewObjective(String, Criteria, net.kyori.adventure.text.Component)}
     */
    @NotNull
    @Deprecated // Paper
    default Objective registerNewObjective(@NotNull String name, @NotNull Criteria criteria, @NotNull String displayName) {
        return this.registerNewObjective(name, criteria, displayName, RenderType.INTEGER);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向计分板注册一个计分项.
     * <p>原文：
     * Registers an Objective on this Scoreboard
     *
     * @param name 计分项名称
     * <p>原文：Name of the Objective
     * @param criteria 计分项的准则
     * <p>原文：Criteria for the Objective
     * @param displayName 展示给玩家的计分项显示名称
     * <p>原文：Name displayed to players for the Objective.
     * @param renderType 计分项的渲染方式
     * <p>原文：Manner of rendering the Objective
     * @return 注册的计分项实例
     * <p>原文：The registered Objective
     * @throws IllegalArgumentException 若名称长度超过32767个字符
     * <p>原文：if name is longer than 32767
     *     characters.
     * @throws IllegalArgumentException 若名称长度超过32767个字符
     * <p>原文：if an objective by that name already
     *     exists
     * @deprecated in favour of {@link #registerNewObjective(String, Criteria, net.kyori.adventure.text.Component, RenderType)}
     */
    @NotNull
    @Deprecated // Paper
    default Objective registerNewObjective(@NotNull String name, @NotNull Criteria criteria, @NotNull String displayName, @NotNull RenderType renderType) {
        return this.registerNewObjective(name, criteria, net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().deserialize(displayName), renderType); // Paper - Adventure
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据名称获取计分项.
     * <p>原文：
     * Gets an Objective on this Scoreboard by name
     *
     * @param name 计分项名称
     * <p>原文：Name of the Objective
     * @return 计分项对象, 如果不存在则为 null
     * <p>原文：the Objective or null if it does not exist
     */
    @Nullable
    Objective getObjective(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取使用此准则的所有计分项.
     * <p>原文：
     * Gets all Objectives of a Criteria on the Scoreboard
     *
     * @param criteria 准则
     * <p>原文：Criteria to search by
     * @return 使用指定准测的计分项的不可变集合
     * <p>原文：an immutable set of Objectives using the specified Criteria
     * @deprecated 请使用 {@link #getObjectivesByCriteria(Criteria)}
     * <p>原文：use {@link #getObjectivesByCriteria(Criteria)}
     */
    @Deprecated(since = "1.19.2")
    @NotNull
    Set<Objective> getObjectivesByCriteria(@NotNull String criteria);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取使用此准则的所有计分项.
     * <p>原文：
     * Gets all Objectives of a Criteria on the Scoreboard
     *
     * @param criteria 准则
     * <p>原文：Criteria to search by
     * @return 使用指定准测的计分项的不可变集合
     * <p>原文：an immutable set of Objectives using the specified Criteria
     */
    @NotNull
    Set<Objective> getObjectivesByCriteria(@NotNull Criteria criteria);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取计分板上的所有计分项.
     * <p>原文：
     * Gets all Objectives on this Scoreboard
     *
     * @return 所有计分项的不可变集合
     * <p>原文：An immutable set of all Objectives on this Scoreboard
     */
    @NotNull
    Set<Objective> getObjectives();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取计分板展示在指定位置的计分项.
     * <p>原文：
     * Gets the Objective currently displayed in a DisplaySlot on this
     * Scoreboard
     *
     * @param slot 显示位置
     * <p>原文：The DisplaySlot
     * @return 此处的计分项, 如果没有则为 null
     * <p>原文：the Objective currently displayed or null if nothing is
     *     displayed in that DisplaySlot
     */
    @Nullable
    Objective getObjective(@NotNull DisplaySlot slot);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取某玩家的所有分数记录.
     * <p>原文：
     * Gets all scores for a player on this Scoreboard
     *
     * @param player the player whose scores are being retrieved
     * @return 该玩家的所有分数记录的不可变集合
     * <p>原文：immutable set of all scores tracked for the player
     * @see #getScores(String)
     */
    // @Deprecated(since = "1.7.8") // Paper
    @NotNull
    Set<Score> getScores(@NotNull OfflinePlayer player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取某项目的所有分数记录.
     * <p>原文：
     * Gets all scores for an entry on this Scoreboard
     *
     * @param entry 项目
     * <p>原文：the entry whose scores are being retrieved
     * @return 该项目的所有分数记录的不可变集合
     * <p>原文：immutable set of all scores tracked for the entry
     */
    @NotNull
    Set<Score> getScores(@NotNull String entry);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除某玩家在此计分板上的所有分数.
     * <p>原文：
     * Removes all scores for a player on this Scoreboard
     *
     * @param player 清空哪一玩家的分数
     * <p>原文：the player to drop all current scores for
     * @see #resetScores(String)
     */
    // @Deprecated(since = "1.7.8") // Paper
    void resetScores(@NotNull OfflinePlayer player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除某项目在此计分板上的所有分数.
     * <p>原文：
     * Removes all scores for an entry on this Scoreboard
     *
     * @param entry 清空哪一项目的分数
     * <p>原文：the entry to drop all current scores for
     */
    void resetScores(@NotNull String entry);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取玩家所处的队伍.
     * <p>原文：
     * Gets a player's Team on this Scoreboard
     *
     * @param player 要搜索的玩家
     * <p>原文：the player to search for
     * @return 玩家所处队伍, 若玩家不在队伍内则为 null
     * <p>原文：the player's Team or null if the player is not on a team
     * @see #getEntryTeam(String)
     */
    // @Deprecated(since = "1.8.6") // Paper
    @Nullable
    Team getPlayerTeam(@NotNull OfflinePlayer player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取项目所处的队伍.
     * <p>原文：
     * Gets an entry's Team on this Scoreboard
     *
     * @param entry 要搜索的项目
     * <p>原文：the entry to search for
     * @return 项目所处队伍, 若此项目不在队伍内则为 null
     * <p>原文：the entry's Team or null if the entry is not on a team
     */
    @Nullable
    Team getEntryTeam(@NotNull String entry);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据名称获取指定队伍.
     * <p>原文：
     * Gets a Team by name on this Scoreboard
     *
     * @param teamName 队伍名
     * <p>原文：Team name
     * @return 匹配的队伍, 若无此队伍则为 null
     * <p>原文：the matching Team or null if no matches
     */
    @Nullable
    Team getTeam(@NotNull String teamName);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取计分板上的所有队伍.
     * <p>原文：
     * Gets all teams on this Scoreboard
     *
     * @return 所有队伍的不可变集合
     * <p>原文：an immutable set of Teams
     */
    @NotNull
    Set<Team> getTeams();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向计分板注册一个队伍.
     * <p>原文：
     * Registers a Team on this Scoreboard
     *
     * @param name 队伍名
     * <p>原文：Team name
     * @return 注册的队伍
     * <p>原文：registered Team
     * @throws IllegalArgumentException 若同名队伍已存在
     * <p>原文：if team by that name already exists
     */
    @NotNull
    Team registerNewTeam(@NotNull String name);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取计分板跟踪的所有玩家.
     * <p>原文：
     * Gets all players tracked by this Scoreboard
     *
     * @return 跟踪的所有玩家的不可变集合
     * <p>原文：immutable set of all tracked players
     * @see #getEntries()
     * @deprecated 计分板可包含非玩家项目
     * <p>原文：Scoreboards can contain entries that aren't players
     */
    @Deprecated(since = "1.7.8")
    @NotNull
    Set<OfflinePlayer> getPlayers();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取计分板跟踪的所有项目.
     * <p>原文：
     * Gets all entries tracked by this Scoreboard
     *
     * @return 跟踪的所有项目的不可变集合
     * <p>原文：immutable set of all tracked entries
     */
    @NotNull
    Set<String> getEntries();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 清空指定位置处的计分项.
     * <p>原文：
     * Clears any objective in the specified slot.
     *
     * @param slot 计分项位置
     * <p>原文：the slot to remove objectives
     */
    void clearSlot(@NotNull DisplaySlot slot);

    // Paper start - improve scoreboard entries
    /**
     * Gets all scores for an entity on this Scoreboard
     *
     * @param entity the entity whose scores are being retrieved
     * @return immutable set of all scores tracked for the entity
     * @throws IllegalArgumentException if entity is null
     * @see #getScores(String)
     */
    @NotNull Set<Score> getScoresFor(@NotNull org.bukkit.entity.Entity entity) throws IllegalArgumentException;

    /**
     * Removes all scores for an entity on this Scoreboard
     *
     * @param entity the entity to drop all current scores for
     * @throws IllegalArgumentException if entity is null
     * @see #resetScores(String)
     */
    void resetScoresFor(@NotNull org.bukkit.entity.Entity entity) throws IllegalArgumentException;

    /**
     * Gets an entity's Team on this Scoreboard
     *
     * @param entity the entity to search for
     * @return the entity's Team or null if the entity is not on a team
     * @throws IllegalArgumentException if entity is null
     * @see #getEntryTeam(String)
     */
    @Nullable Team getEntityTeam(@NotNull org.bukkit.entity.Entity entity) throws IllegalArgumentException;
    // Paper end - improve scoreboard entries
}
