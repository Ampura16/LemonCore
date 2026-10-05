package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 猪灵 / 猪灵蛮兵。
 * <p>原文：
 * Piglin / Piglin Brute.
 */
public interface PiglinAbstract extends Monster, Ageable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取猪灵是否免疫僵尸化。
     * <p>原文：
     * Gets whether the piglin is immune to zombification.
     *
     * @return Whether the piglin is immune to zombification
     */
    public boolean isImmuneToZombification();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置猪灵是否免疫僵尸化。
     * <p>原文：
     * Sets whether the piglin is immune to zombification.
     *
     * @param flag Whether the piglin is immune to zombification
     */
    public void setImmuneToZombification(boolean flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体转换为僵尸猪灵所需的刻数。
     * <p>原文：
     * Gets the amount of ticks until this entity will be converted to a
     * Zombified Piglin.
     *
     * When this reaches 300, the entity will be converted.
     *
     * @return conversion time
     * @throws IllegalStateException if {@link #isConverting()} is false.
     */
    public int getConversionTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体转换为僵尸猪灵所需的刻数。
     * <p>原文：
     * Sets the conversion counter value. The counter is incremented
     * every tick the method {@link #isConverting()} returns true. Setting
     * this value will not start the conversion if the {@link PiglinAbstract} is
     * not in a valid environment ({@link org.bukkit.World#isPiglinSafe})
     * to convert, is immune to zombification ({@link #isImmuneToZombification()})
     * or has no AI ({@link #hasAI}).
     *
     * When this reaches 300, the entity will be converted. To stop the
     * conversion use {@link #setImmuneToZombification(boolean)}.
     *
     * @param time new conversion counter
     */
    public void setConversionTime(int time);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体是否正在转换为僵尸猪灵。
     * <p>原文：
     * Get if this entity is in the process of converting to a Zombified Piglin.
     *
     * @return conversion status
     */
    boolean isConverting();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取猪灵是否为幼年。
     * <p>原文：
     * Gets whether the piglin is a baby
     *
     * @return Whether the piglin is a baby
     * @deprecated see {@link Ageable#isAdult()}
     */
    @Deprecated(since = "1.16.2")
    public boolean isBaby();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置猪灵是否为幼年。
     * <p>原文：
     * Sets whether the piglin is a baby
     *
     * @param flag Whether the piglin is a baby
     * @deprecated see {@link Ageable#setBaby()} and {@link Ageable#setAdult()}
     */
    @Deprecated(since = "1.16.2")
    public void setBaby(boolean flag);
}
