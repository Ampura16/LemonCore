package org.bukkit.entity;

import org.bukkit.Location;
import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表蜜蜂.
 * <p>原文：
 * Represents a Bee.
 */
public interface Bee extends Animals {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取蜜蜂的家(蜂巢)的位置.
     * <p>原文：
     * Get the bee's hive location.
     *
     * @return 蜂巢位置或null
     * <p>原文：hive location or null
     */
    @Nullable
    Location getHive();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置蜜蜂所住蜂巢的位置.
     * <p>原文：
     * Set the bee's hive location.
     *
     * @param location 蜂巢位置或null
     * <p>原文：or null
     */
    void setHive(@Nullable Location location);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取蜜蜂盘旋的花的位置.
     * <p>原文：
     * Get the bee's flower location.
     *
     * @return 蜜蜂盘旋的花的位置或null
     * <p>原文：flower location or null
     */
    @Nullable
    Location getFlower();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置蜜蜂盘旋的花的位置.
     * <p>原文：
     * Set the bee's flower location.
     *
     * @param location 蜜蜂盘旋的花的位置或null
     * <p>原文：or null
     */
    void setFlower(@Nullable Location location);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 蜜蜂是否携带花粉.
     * <p>原文：
     * Get if the bee has nectar.
     *
     * @return 蜜蜂是否携带花粉.
     * <p>原文：nectar
     */
    boolean hasNectar();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置蜜蜂是否携带花粉.
     * <p>原文：
     * Set if the bee has nectar.
     *
     * @param nectar 蜜蜂是否携带花粉
     * <p>原文：whether the entity has nectar
     */
    void setHasNectar(boolean nectar);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取蜜蜂是否蜇过玩家或其它生物.
     * <p>原文：
     * Get if the bee has stung.
     *
     * @return 蜜蜂是否蜇过玩家或其它生物
     * <p>原文：has stung
     */
    boolean hasStung();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置蜜蜂是否蜇过玩家或其它生物.
     * <p>原文：
     * Set if the bee has stung.
     *
     * @param stung 蜜蜂是否蜇过玩家或其它生物
     * <p>原文：has stung
     */
    void setHasStung(boolean stung);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取蜜蜂的激怒等级.
     * <p>
     * 译注:此数值代表蜜蜂平息怒气所需时间, 为0时蜜蜂不再愤怒.
     * <p>原文：
     * Get the bee's anger level.
     *
     * @return 激怒等级
     * <p>原文：anger level
     */
    int getAnger();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置蜜蜂的激怒等级.
     * <p>
     * 译注:此数值代表蜜蜂平息怒气所需时间, 为0时蜜蜂不再愤怒.
     * <p>原文：
     * Set the bee's new anger level.
     *
     * @param anger 激怒等级
     * <p>原文：new anger
     */
    void setAnger(int anger);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取蜜蜂离其能再次进入蜂巢的剩余时间(以tick计数).
     * <p>原文：
     * Get the amount of ticks the bee cannot enter the hive for.
     *
     * @return 蜜蜂离其能再次进入蜂巢的剩余时间(以tick计数)
     * <p>原文：Ticks the bee cannot enter a hive for
     */
    int getCannotEnterHiveTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置蜜蜂离其能再次进入蜂巢的剩余时间(以tick计数).
     * <p>原文：
     * Set the amount of ticks the bee cannot enter a hive for.
     *
     * @param ticks 蜜蜂离其能再次进入蜂巢的剩余时间(以tick计数)
     * <p>原文：Ticks the bee cannot enter a hive for
     */
    void setCannotEnterHiveTicks(int ticks);

    // Paper start
    /**
     * Sets the override for if the bee is currently rolling.
     *
     * @param rolling is rolling, or unset for vanilla behavior
     */
    void setRollingOverride(@org.jetbrains.annotations.NotNull net.kyori.adventure.util.TriState rolling);

    /**
     * Gets the plugin set override for if the bee is currently rolling.
     *
     * @return plugin set rolling override
     */
    @org.jetbrains.annotations.NotNull
    net.kyori.adventure.util.TriState getRollingOverride();

    /**
     * Gets if the bee is currently rolling.
     *
     * @return is rolling
     */
    boolean isRolling();

    /**
     * Sets how many crops this bee has grown since it last
     * pollinated.
     * @param crops number of crops
     */
    void setCropsGrownSincePollination(int crops);

    /**
     * Gets how many crops this bee has grown since it last
     * pollinated.
     * @return number of crops
     */
    int getCropsGrownSincePollination();

    /**
     * Sets how many ticks this bee has gone without pollinating.
     *
     * @param ticks number of ticks
     */
    void setTicksSincePollination(int ticks);

    /**
     * Gets how many ticks this bee has gone without pollinating
     *
     * @return number of ticks
     */
    int getTicksSincePollination();

    /**
     * Sets how many ticks have passed since this bee last stung.
     * This value is used to determine when the bee should die after stinging.
     * <p>
     * Note that bees don’t die at a fixed time. Instead, every few ticks, they
     * have a random chance of dying, and that chance increases with this value.
     *
     * @param time number of ticks since last sting
     */
    void setTimeSinceSting(@NonNegative int time);

    /**
     * Gets how many ticks have passed since this bee last stung.
     * This value increases each tick after the bee stings and is used
     * to determine when the bee should die.
     * <p>
     * Note that bees don’t die at a fixed time. Instead, every few ticks, they
     * have a random chance of dying, and that chance increases with this value.
     *
     * @return number of ticks since last sting
     */
    int getTimeSinceSting();
    // Paper end
}
