package org.bukkit.entity;

import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 监守者.
 * <p>原文：
 * A Warden.
 */
public interface Warden extends Monster {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此监守者的愤怒等级。
     * <p>
     * 愤怒值为0到150的整数。当监守者对某个目标的愤怒值达到80时，它会主动追击该目标。
     * <p>原文：
     * Gets the anger level of this warden.
     *
     * Anger is an integer from 0 to 150. Once a Warden reaches 80 anger at a
     * target it will actively pursue it.
     *
     * @return 愤怒等级
     * <p>原文：anger level
     */
    int getAnger();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此监守者对指定实体的愤怒等级。
     * <p>
     * 愤怒值为0到150的整数。当监守者对某个目标的愤怒值达到80时，它会主动追击该目标。
     * <p>原文：
     * Gets the anger level of this warden.
     *
     * Anger is an integer from 0 to 150. Once a Warden reaches 80 anger at a
     * target it will actively pursue it.
     *
     * @param entity 目标实体
     * <p>原文：target entity
     * @return 愤怒等级
     * <p>原文：anger level
     */
    int getAnger(@NotNull Entity entity);

    /**
     * Gets the highest anger level of this warden.
     * <p>
     * Anger is an integer from 0 to 150. Once a Warden reaches 80 anger at a
     * target it will actively pursue it.
     *
     * @return highest anger level
     */
    int getHighestAnger();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 增加此监守者的愤怒等级。
     * <p>
     * 愤怒值为0到150的整数。当监守者对某个目标的愤怒值达到80时，它会主动追击该目标。
     * <p>原文：
     * Increases the anger level of this warden.
     *
     * Anger is an integer from 0 to 150. Once a Warden reaches 80 anger at a
     * target it will actively pursue it.
     *
     * @param entity 目标实体
     * <p>原文：target entity
     * @param increase 要增加的数量
     * <p>原文：number to increase by
     * @see #getAnger(org.bukkit.entity.Entity)
     */
    void increaseAnger(@NotNull Entity entity, int increase);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此监守者的愤怒等级。
     * <p>
     * 愤怒值为0到150的整数。当监守者对某个目标的愤怒值达到80时，它会主动追击该目标。
     * <p>原文：
     * Sets the anger level of this warden.
     *
     * Anger is an integer from 0 to 150. Once a Warden reaches 80 anger at a
     * target it will actively pursue it.
     *
     * @param entity 目标实体
     * <p>原文：target entity
     * @param anger 新的愤怒等级
     * <p>原文：new anger level
     * @see #getAnger(org.bukkit.entity.Entity)
     */
    void setAnger(@NotNull Entity entity, int anger);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 清除此监守者对指定实体的愤怒等级。
     * <p>原文：
     * Clears the anger level of this warden.
     *
     * @param entity 目标实体
     * <p>原文：target entity
     */
    void clearAnger(@NotNull Entity entity);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此监守者最为愤怒的 {@link LivingEntity}。
     * <p>原文：
     * Gets the {@link LivingEntity} at which this warden is most angry.
     *
     * @return 目标 {@link LivingEntity}，若无则为null
     * <p>原文：The target {@link LivingEntity} or null
     */
    @Nullable
    LivingEntity getEntityAngryAt();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 让监守者感知到指定位置的扰动。
     * <p>原文：
     * Make the warden sense a disturbance in the force at the location given.
     *
     * @param location 扰动的位置
     * <p>原文：location of the disturbance
     */
    void setDisturbanceLocation(@NotNull Location location);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此监守者的愤怒等级。
     * <p>原文：
     * Get the level of anger of this warden.
     *
     * @return 愤怒等级
     * <p>原文：The level of anger
     */
    @NotNull
    AngerLevel getAngerLevel();

    public enum AngerLevel {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 愤怒等级 0-39.
         * <p>原文：
         * Anger level 0-39.
         */
        CALM,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 愤怒等级 40-79.
         * <p>原文：
         * Anger level 40-79.
         */
        AGITATED,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 愤怒等级 80 或以上.
         * <p>原文：
         * Anger level 80 or above.
         */
        ANGRY;
    }
}
