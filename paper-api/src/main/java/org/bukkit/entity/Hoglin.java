package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一只疣猪兽。
 * <p>原文：
 * Represents a Hoglin.
 */
public interface Hoglin extends Animals, Enemy {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取疣猪兽是否免疫僵尸化.
     * <p>原文：
     * Gets whether the hoglin is immune to zombification.
     *
     * @return 疣猪兽是否免疫僵尸化
     * <p>原文：Whether the hoglin is immune to zombification
     */
    public boolean isImmuneToZombification();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置疣猪兽是否免疫僵尸化.
     * <p>原文：
     * Sets whether the hoglin is immune to zombification.
     *
     * @param flag 疣猪兽是否免疫僵尸化
     * <p>原文：Whether the hoglin is immune to zombification
     */
    public void setImmuneToZombification(boolean flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取疣猪兽是否能被猪灵猎杀.
     * <p>原文：
     * Get whether the hoglin is able to be hunted by piglins.
     *
     * @return 疣猪兽是否能被猪灵猎杀
     * <p>原文：Whether the hoglin is able to be hunted by piglins
     */
    public boolean isAbleToBeHunted();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置疣猪兽是否能被猪灵猎杀.
     * <p>原文：
     * Sets whether the hoglin is able to be hunted by piglins.
     *
     * @param flag 疣猪兽是否能被猪灵猎杀
     * <p>原文：Whether the hoglin is able to be hunted by piglins.
     */
    public void setIsAbleToBeHunted(boolean flag);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体转换为僵尸疣猪兽所需的刻数.
     * <p>原文：
     * Gets the amount of ticks until this entity will be converted to a Zoglin.
     *
     * When this reaches 300, the entity will be converted.
     *
     * @return 转换所需时间
     * <p>原文：conversion time
     * @throws IllegalStateException 如果 {@link #isConverting()} 为false
     * <p>原文：if {@link #isConverting()} is false.
     */
    public int getConversionTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体转换为僵尸疣猪兽所需的刻数.
     * <p>原文：
     * Sets the conversion counter value. The counter is incremented
     * every tick the method {@link #isConverting()} returns true. Setting
     * this value will not start the conversion if the {@link Hoglin} is
     * not in a valid environment ({@link org.bukkit.World#isPiglinSafe})
     * to convert, is immune to zombification ({@link #isImmuneToZombification()})
     * or has no AI ({@link #hasAI}).
     *
     * When this reaches 300, the entity will be converted. To stop the
     * conversion use {@link #setImmuneToZombification(boolean)}.
     *
     * @param time 新的转换时间
     * <p>原文：new conversion counter
     */
    public void setConversionTime(int time);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体是否正在转换为僵尸疣猪兽.
     * <p>原文：
     * Get if this entity is in the process of converting to a Zoglin.
     *
     * @return 转换状态
     * <p>原文：conversion status
     */
    boolean isConverting();
}
