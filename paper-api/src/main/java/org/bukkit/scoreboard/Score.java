package org.bukkit.scoreboard;

import org.bukkit.OfflinePlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * {@link #getObjective() 计分项}跟踪的某条{@link #getEntry() 项目}的单条分数记录.
 * 改变此分数记录不会影响其它的计分项或计分板.
 * <p>原文：
 * A score entry for an {@link #getEntry() entry} on an {@link
 * #getObjective() objective}. Changing this will not affect any other
 * objective or scoreboard.
 */
public interface Score {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此分数为哪个玩家而记.
     * <p>原文：
     * Gets the OfflinePlayer being tracked by this Score
     *
     * @return 分数对应的玩家
     * <p>原文：this Score's tracked player
     * @see #getEntry()
     * @deprecated 计分板可包含非玩家项目
     * <p>原文：Scoreboards can contain entries that aren't players
     */
    @Deprecated(since = "1.7.8")
    @NotNull
    OfflinePlayer getPlayer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此分数为哪个项目而记.
     * <p>原文：
     * Gets the entry being tracked by this Score
     *
     * @return 分数对应的项目
     * <p>原文：this Score's tracked entry
     */
    @NotNull
    String getEntry();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此分数记录所属的计分项.
     * <p>原文：
     * Gets the Objective being tracked by this Score
     *
     * @return 所属计分项
     * <p>原文：this Score's tracked objective
     */
    @NotNull
    Objective getObjective();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取当前分数.
     * <p>原文：
     * Gets the current score
     *
     * @return 分数
     * <p>原文：the current score
     * @throws IllegalStateException 若相关的计分项已被注销
     * <p>原文：if the associated objective has been
     *     unregistered
     */
    int getScore();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置当前分数.
     * <p>原文：
     * Sets the current score.
     *
     * @param score 分数
     * <p>原文：New score
     * @throws IllegalStateException 若相关的计分项已被注销
     * <p>原文：if the associated objective has been
     *     unregistered
     */
    void setScore(int score);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 判断是否曾设置过分数数据.
     * <p>原文：
     * Shows if this score has been set at any point in time.
     *
     * @return 是否曾设置过分数数据
     * <p>原文：if this score has been set before
     * @throws IllegalStateException 若相关的计分项已被注销
     * <p>原文：if the associated objective has been
     *     unregistered
     */
    boolean isScoreSet();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取相关计分项所属的计分板.
     * <p>原文：
     * Gets the scoreboard for the associated objective.
     *
     * @return 相关计分项所属的计分板, 如果计分项已被{@link Objective#unregister() 注销}则为 null
     * <p>原文：the owning objective's scoreboard, or null if it has been
     *     {@link Objective#unregister() unregistered}
     */
    @Nullable
    Scoreboard getScoreboard();

    // Paper start
    /**
     * Resets this score, if a value has been set.
     *
     * @throws IllegalStateException if the associated objective has been
     *     unregistered
     */
    void resetScore() throws IllegalStateException;
    // Paper end

    // Paper start - add more score API
    /**
     * Gets if this score is triggerable and cannot
     * be used by the {@code /trigger} command executed
     * by the owner of this score.
     *
     * @return true if triggerable, false if not triggerable, score isn't set, or the objective isn't {@link Criteria#TRIGGER}
     * @throws IllegalStateException if the associated objective has been unregistered
     */
    boolean isTriggerable();

    /**
     * Sets if this score is triggerable and can
     * be used by the {@code /trigger} command
     * executed by the owner of this score. Can
     * only be set on {@link Criteria#TRIGGER} objectives.
     * <p>
     * If the score doesn't exist (aka {@link #isScoreSet()} returns false),
     * this will create the score with a 0 value.
     *
     * @param triggerable true to enable trigger, false to disable
     * @throws IllegalArgumentException if this objective isn't {@link Criteria#TRIGGER}
     * @throws IllegalStateException if the associated objective has been unregistered
     */
    void setTriggerable(boolean triggerable);

    /**
     * Get the custom name for this entry.
     *
     * @return the custom name or null if not set (or score isn't set)
     * @throws IllegalStateException if the associated objective has been unregistered
     */
    @Nullable net.kyori.adventure.text.Component customName();

    /**
     * Sets the custom name for this entry.
     * <p>
     * If the score doesn't exist (aka {@link #isScoreSet()} returns false),
     * this will create the score with a 0 value.
     *
     * @param customName the custom name or null to reset
     * @throws IllegalStateException if the associated objective has been unregistered
     */
    void customName(net.kyori.adventure.text.@Nullable Component customName);
    // Paper end - add more score API

    // Paper start - number format api
    /**
     * Gets the number format for this score or null if the score has not been set yet
     * or the objective's default is being used.
     *
     * @return this score's number format, or null if the objective's default is used or the score doesn't exist
     * @throws IllegalStateException if the associated objective has been
     *     unregistered
     */
    @Nullable io.papermc.paper.scoreboard.numbers.NumberFormat numberFormat();

    /**
     * Sets the number format for this score. If this score has not been set yet {@link #isScoreSet()}, it will be created
     *
     * @param format the number format to set, pass null to reset format to default
     * @throws IllegalStateException if the associated objective has been
     *     unregistered
     */
    void numberFormat(@Nullable io.papermc.paper.scoreboard.numbers.NumberFormat format);
    // Paper end - number format api

}
