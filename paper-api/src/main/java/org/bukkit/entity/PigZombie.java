package org.bukkit.entity;

import org.jetbrains.annotations.Contract;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表僵尸猪人.
 * <p>原文：
 * Represents a Zombified piglin.
 */
public interface PigZombie extends Zombie {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取僵尸猪人当前的愤怒程度.
     * <p>原文：
     * Get the zombified piglin's current anger level.
     *
     * @return 愤怒程度
     * <p>原文：The anger level.
     */
    int getAnger();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置僵尸猪人当前的愤怒程度.
     * <p>原文：
     * Set the zombified piglin's current anger level.
     *
     * @param level 愤怒程度.更高的愤怒程度需要更多的时间来摆脱.
     * <p>原文：The anger level. Higher levels of anger take longer to
     *     wear off.
     */
    void setAnger(int level);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将僵尸猪人的愤怒程度设置为0或默认等级.
     * <p>原文：
     * Shorthand; sets to either 0 or the default level.
     *
     * @param angry 僵尸猪人是否愤怒
     * <p>原文：Whether the piglin should be angry.
     */
    void setAngry(boolean angry);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取僵尸是否愤怒.
     * <p>原文：
     * Shorthand; gets whether the piglin is angry.
     *
     * @return 僵尸猪人愤怒为true,否则为false
     * <p>原文：True if the piglin is angry, otherwise false.
     */
    boolean isAngry();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * <b>不适用于此实体</b>
     * <p>原文：
     * <b>Not applicable to this entity</b>
     *
     * @return {@code false}
     */
    @Override
    @Contract("-> false")
    public boolean isConverting();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * <b>不适用于此实体</b>
     * <p>原文：
     * <b>Not applicable to this entity</b>
     */
    @Override
    @Contract("-> fail")
    public int getConversionTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * <b>不适用于此实体</b>
     * <p>原文：
     * <b>Not applicable to this entity</b>
     *
     * @param time 未使用
     * <p>原文：unused
     */
    @Override
    @Contract("_ -> fail")
    public void setConversionTime(int time);
}
