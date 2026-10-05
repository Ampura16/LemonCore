package org.bukkit.entity;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表僵尸.
 * <p>原文：
 * Represents a Zombie.
 */
public interface Zombie extends Monster, Ageable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个僵尸是否为小僵尸.
     * <p>原文：
     * Gets whether the zombie is a baby
     *
     * @return 这个僵尸是否为小僵尸
     * <p>原文：Whether the zombie is a baby
     * @deprecated 另请参阅 {@link Ageable#isAdult()}
     * <p>原文：see {@link Ageable#isAdult()}
     */
    @Deprecated(since = "1.16.2")
    public boolean isBaby();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个僵尸是否为小僵尸.
     * <p>原文：
     * Sets whether the zombie is a baby
     *
     * @param flag 这个僵尸是否为小僵尸
     * <p>原文：Whether the zombie is a baby
     * @deprecated 另请参阅 {@link Ageable#setBaby()} 和 {@link Ageable#setAdult()}
     * <p>原文：see {@link Ageable#setBaby()} and {@link Ageable#setAdult()}
     */
    @Deprecated(since = "1.16.2")
    public void setBaby(boolean flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个僵尸是否为村民僵尸.
     * <p>原文：
     * Gets whether the zombie is a villager
     *
     * @return 这个僵尸是否为村民僵尸
     * <p>原文：Whether the zombie is a villager
     * @deprecated 检查 instanceof {@link ZombieVillager} 的是与否.
     * <p>原文：check if instanceof {@link ZombieVillager}.
     */
    @Deprecated(since = "1.10.2", forRemoval = true)
    public boolean isVillager();

    /**
     * @param flag flag
     * @deprecated must spawn {@link ZombieVillager}.
     */
    @Deprecated(since = "1.9", forRemoval = true)
    @Contract("_ -> fail")
    public void setVillager(boolean flag);

    /**
     * @param profession profession
     * @see ZombieVillager#getVillagerProfession()
     */
    @Deprecated(since = "1.10.2", forRemoval = true)
    @Contract("_ -> fail")
    public void setVillagerProfession(Villager.Profession profession);

    /**
     * @return profession
     * @see ZombieVillager#getVillagerProfession()
     */
    @Deprecated(since = "1.10.2", forRemoval = true)
    @Nullable
    @Contract("-> null")
    public Villager.Profession getVillagerProfession();

    /**
     * Get if this entity is in the process of converting to a Drowned as a
     * result of being underwater.
     *
     * @return conversion status
     */
    boolean isConverting();

    /**
     * Gets the amount of ticks until this entity will be converted to a Drowned
     * as a result of being underwater.
     * <br>
     * When this reaches 0, the entity will be converted.
     *
     * @return conversion time
     * @throws IllegalStateException if {@link #isConverting()} is false.
     */
    int getConversionTime();

    /**
     * Sets the amount of ticks until this entity will be converted to a Drowned
     * as a result of being underwater.
     * <br>
     * When this reaches 0, the entity will be converted. A value of less than 0
     * will stop the current conversion process without converting the current
     * entity.
     *
     * @param time new conversion time
     */
    void setConversionTime(int time);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得这个僵尸是否能破坏门.
     * <p>原文：
     * Gets whether this zombie can break doors
     *
     * @return 僵尸是否能破坏门
     * <p>原文：Whether this zombie can break doors
     */
    boolean canBreakDoors();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置僵尸是否能破坏门.
     *
     * 当实体是溺尸时将被忽略.当实体破坏门时也会停止破坏.
     * <p>原文：
     * Sets whether this zombie can break doors
     * <p>
     * Check {@link #supportsBreakingDoors()} to see
     * if this zombie type will even be affected by using
     * this method. Will also stop the action if
     * the entity is currently breaking a door.
     *
     * @param flag Whether this zombie can break doors
     */
    void setCanBreakDoors(boolean flag);

    /**
     * Check if zombie is drowning
     *
     * @return True if zombie conversion process has begun
     */
    boolean isDrowning();

    /**
     * Make zombie start drowning
     *
     * @param drownedConversionTime Amount of time until zombie converts from drowning
     * @deprecated See {@link #setConversionTime(int)}
     */
    @Deprecated
    void startDrowning(int drownedConversionTime);

    /**
     * Stop a zombie from starting the drowning conversion process
     */
    void stopDrowning();

    /**
     * Set if zombie has its arms raised
     *
     * @param raised True to raise arms
     * @deprecated use {@link #setAggressive(boolean)}
     */
    @Deprecated
    void setArmsRaised(boolean raised);

    /**
     * Check if zombie has arms raised
     *
     * @return True if arms are raised
     * @deprecated use {@link #isAggressive()}
     */
    @Deprecated
    boolean isArmsRaised();

    /**
     * Check if this zombie will burn in the sunlight
     *
     * @return True if zombie will burn in sunlight
     */
    boolean shouldBurnInDay();

    /**
     * Set if this zombie should burn in the sunlight
     *
     * @param shouldBurnInDay True to burn in sunlight
     */
    void setShouldBurnInDay(boolean shouldBurnInDay);

    /**
     * Checks if this zombie type supports breaking doors.
     * {@link Drowned} do not have support for breaking doors
     * so using {@link #setCanBreakDoors(boolean)} on them has
     * no effect.
     *
     * @return true if entity supports breaking doors
     * @deprecated Since 1.21.2 all zombie types can break doors if instructed as MC-137053 was fixed.
     */
    @Deprecated(since = "1.21.2", forRemoval = true)
    boolean supportsBreakingDoors();
}
