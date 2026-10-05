package org.bukkit.advancement;

import java.util.Collection;
import java.util.Date;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家达成一个进度的状态. 这个类不是指针安全的, 因为底层的进度可能会被重载.
 * <p>原文：
 * The individual status of an advancement for a player. This class is not
 * reference safe as the underlying advancement may be reloaded.
 */
public interface AdvancementProgress {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此进度对象所关联的进度.
     * <p>原文：
     * The advancement this progress is concerning.
     *
     * @return 相应的进度
     * <p>原文：the relevant advancement
     */
    @NotNull
    Advancement getAdvancement();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查是否已满足此进度的所有条件 (即是否完成了此进度).
     * <p>原文：
     * Check if all criteria for this advancement have been met.
     *
     * @return 是否已达成此进度
     * <p>原文：true if this advancement is done
     */
    boolean isDone();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在当前时间标记指定的条件为已完成.
     * <p>原文：
     * Mark the specified criteria as awarded at the current time.
     *
     * @param criteria 要标记的条件
     * <p>原文：the criteria to mark
     * @return 如果操作成功返回true, 如果条件不存在或已完成返回false
     * <p>原文：true if awarded, false if criteria does not exist or already
     * awarded.
     */
    boolean awardCriteria(@NotNull String criteria);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 标记指定的条件为未完成.
     * <p>原文：
     * Mark the specified criteria as uncompleted.
     *
     * @param criteria 要标记的条件
     * <p>原文：the criteria to mark
     * @return 如果成功移除条件返回true, 如果条件不存在或未完成返回false
     * <p>原文：true if removed, false if criteria does not exist or not awarded
     */
    boolean revokeCriteria(@NotNull String criteria);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定条件被授予的日期.
     * <p>原文：
     * Get the date the specified criteria was awarded.
     *
     * @param criteria 要检查的条件
     * <p>原文：the criteria to check
     * @return 条件被授予的时间, 如果条件不存在或未完成则为null
     * <p>原文：date awarded or null if unawarded or criteria does not exist
     */
    @Nullable
    Date getDateAwarded(@NotNull String criteria);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取达成此进度尚未满足的条件.
     * <p>原文：
     * Get the criteria which have not been awarded.
     *
     * @return 剩余条件的不可编辑副本
     * <p>原文：unmodifiable copy of criteria remaining
     */
    @NotNull
    Collection<String> getRemainingCriteria();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取达成此进度已经满足的条件.
     * <p>原文：
     * Gets the criteria which have been awarded.
     *
     * @return 已满足条件的不可编辑副本
     * <p>原文：unmodifiable copy of criteria awarded
     */
    @NotNull
    Collection<String> getAwardedCriteria();
}
