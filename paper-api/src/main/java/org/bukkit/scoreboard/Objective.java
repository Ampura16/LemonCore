package org.bukkit.scoreboard;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表单个计分板计分项, 可展示特定项目的分数.
 * 此计分项只与{@link #getScoreboard()}获取的计分板相关联.
 * <p>原文：
 * An objective on a scoreboard that can show scores specific to entries. This
 * objective is only relevant to the display of the associated {@link
 * #getScoreboard() scoreboard}.
 */
public interface Objective {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取记分项的名称.
     * <p>原文：
     * Gets the name of this Objective
     *
     * @return 记分项的名称
     * <p>原文：this objective's name
     * @throws IllegalStateException 若此计分项已被注销
     * <p>原文：if this objective has been unregistered
     */
    @NotNull
    String getName();

    // Paper start - Adventure
    /**
     * Gets the display name for this objective
     *
     * @return this objective's display name
     * @throws IllegalStateException if this objective has been unregistered
     */
    net.kyori.adventure.text.@NotNull Component displayName();
    /**
     * Sets the name displayed to players for this objective.
     *
     * @param displayName Display name to set
     * @throws IllegalStateException if this objective has been unregistered
     * @throws IllegalArgumentException if displayName is null
     * @throws IllegalArgumentException if displayName is longer than 128
     *     characters.
     */
    void displayName(net.kyori.adventure.text.@Nullable Component displayName);
    // Paper end - Adventure

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此计分项展示给玩家的名称.
     * <p>原文：
     * Gets the name displayed to players for this objective
     *
     * @return 计分项显示名
     * <p>原文：this objective's display name
     * @throws IllegalStateException 若此计分项已被注销
     * <p>原文：if this objective has been unregistered
     * @deprecated in favour of {@link #displayName()}
     */
    @NotNull
    @Deprecated // Paper
    String getDisplayName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此计分项展示给玩家的名称.
     * <p>原文：
     * Sets the name displayed to players for this objective.
     *
     * @param displayName 计分项显示名
     * <p>原文：Display name to set
     * @throws IllegalStateException 若此计分项已被注销
     * <p>原文：if this objective has been unregistered
     * @deprecated in favour of {@link #displayName(net.kyori.adventure.text.Component)}
     */
    @Deprecated // Paper
    void setDisplayName(@NotNull String displayName);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此计分项跟踪的准则.
     * <p>原文：
     * Gets the criteria this objective tracks.
     *
     * @return 计分项的准则
     * <p>原文：this objective's criteria
     * @throws IllegalStateException 若此计分项已被注销
     * <p>原文：if this objective has been unregistered
     * @deprecated 请使用 {@link #getTrackedCriteria()}
     * <p>原文：use {@link #getTrackedCriteria()}
     */
    @Deprecated(since = "1.19.2")
    @NotNull
    String getCriteria();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此计分项跟踪的准则.
     * <p>原文：
     * Gets the criteria this objective tracks.
     *
     * @return 计分项的准则
     * <p>原文：this objective's criteria
     * @throws IllegalStateException 若此计分项已被注销
     * <p>原文：if this objective has been unregistered
     */
    @NotNull
    Criteria getTrackedCriteria();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断此计分项的分数能否被插件直接修改.
     * <p>原文：
     * Gets if the objective's scores can be modified directly by a plugin.
     *
     * @return 若可修改则为true
     * <p>原文：true if scores are modifiable
     * @throws IllegalStateException 若此计分项已被注销
     * <p>原文：if this objective has been unregistered
     * @see Criteria#HEALTH
     */
    boolean isModifiable();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此计分项附属的计分板.
     * <p>原文：
     * Gets the scoreboard to which this objective is attached.
     *
     * @return 所属的计分板, 如果此计分项已被{@link #unregister() 注销}则为 null
     * <p>原文：Owning scoreboard, or null if it has been {@link #unregister()
     *     unregistered}
     */
    @Nullable
    Scoreboard getScoreboard();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从这个计分板注销这个计分项.
     * <p>原文：
     * Unregisters this objective from the {@link Scoreboard scoreboard.}
     *
     * @throws IllegalStateException 若此计分项已被注销
     * <p>原文：if this objective has been unregistered
     */
    void unregister();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此计分项显示在指定位置, 并将其从原位置移除 (就是从A处移到B处的意思).
     * <p>原文：
     * Sets this objective to display on the specified slot for the
     * scoreboard, removing it from any other display slot.
     *
     * @param slot 显示的位置, null 为不显示/隐藏
     * <p>原文：display slot to change, or null to not display
     * @throws IllegalStateException 若此计分项已被注销
     * <p>原文：if this objective has been unregistered
     */
    void setDisplaySlot(@Nullable DisplaySlot slot);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此计分项显示的位置.
     * <p>原文：
     * Gets the display slot this objective is displayed at.
     *
     * @return 显示位置, 若不显示则为 null
     * <p>原文：the display slot for this objective, or null if not displayed
     * @throws IllegalStateException 若此计分项已被注销
     * <p>原文：if this objective has been unregistered
     */
    @Nullable
    DisplaySlot getDisplaySlot();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置计分项数值的渲染方式.
     * <p>原文：
     * Sets manner in which this objective will be rendered.
     *
     * @param renderType 渲染方式
     * <p>原文：new render type
     * @throws IllegalStateException 若此计分项已被注销
     * <p>原文：if this objective has been unregistered
     */
    void setRenderType(@NotNull RenderType renderType);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取计分项数值的渲染方式.
     * <p>原文：
     * Sets manner in which this objective will be rendered.
     *
     * @return 渲染方式
     * <p>原文：the render type
     * @throws IllegalStateException 若此计分项已被注销
     * <p>原文：if this objective has been unregistered
     */
    @NotNull
    RenderType getRenderType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取某玩家的分数.
     * <p>原文：
     * Gets a player's Score for an Objective on this Scoreboard
     *
     * @param player 玩家名
     * <p>原文：Player for the Score
     * @return 指定玩家的分数
     * <p>原文：Score tracking the Objective and player specified
     * @throws IllegalStateException 若此计分项已被注销
     * <p>原文：if this objective has been unregistered
     * @see #getScore(String)
     */
    // @Deprecated(since = "1.7.8") // Paper
    @NotNull
    Score getScore(@NotNull OfflinePlayer player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取某条项目的分数.
     * <p>原文：
     * Gets an entry's Score for an Objective on this Scoreboard.
     *
     * @param entry 项目名
     * <p>原文：Entry for the Score
     * @return 指定项目的分数
     * <p>原文：Score tracking the Objective and entry specified
     * @throws IllegalStateException 若此计分项已被注销
     * <p>原文：if this objective has been unregistered
     * @throws IllegalArgumentException 若 entry 长度超过 32767 个字符
     * <p>原文：if entry is longer than 32767 characters.
     */
    @NotNull
    Score getScore(@NotNull String entry);

    // Paper start - improve scoreboard entries
    /**
     * Gets an entity's Score for an Objective on this Scoreboard.
     *
     * @param entity Entity for the Score
     * @return Score tracking the Objective and entity specified
     * @throws IllegalArgumentException if entity is null
     * @throws IllegalStateException if this objective has been unregistered
     */
    @NotNull Score getScoreFor(@NotNull org.bukkit.entity.Entity entity) throws IllegalArgumentException, IllegalStateException;
    // Paper end - improve scoreboard entries

    // Paper start - add more score API
    /**
     * Gets if this objective will auto update score
     * displays on changes.
     *
     * @return true if auto updating
     * @throws IllegalStateException if this objective has been unregistered
     */
    boolean willAutoUpdateDisplay();

    /**
     * Sets if this objective will auto update
     * score displays on changes.
     *
     * @param autoUpdateDisplay true to auto update
     * @throws IllegalStateException if this objective has been unregistered
     */
    void setAutoUpdateDisplay(boolean autoUpdateDisplay);
    // Paper end - add more score API

    // Paper start - number format api
    /**
     * Gets the number format for this objective's scores or null if the client default is used.
     *
     * @return this objective's number format, or null if the client default is used
     * @throws IllegalStateException if this objective has been unregistered
     */
    @Nullable io.papermc.paper.scoreboard.numbers.NumberFormat numberFormat();

    /**
     * Sets the number format for this objective's scores.
     *
     * @param format the number format to set, pass null to reset format to default
     * @throws IllegalStateException if this objective has been unregistered
     */
    void numberFormat(@Nullable io.papermc.paper.scoreboard.numbers.NumberFormat format);
    // Paper end - number format api
}
