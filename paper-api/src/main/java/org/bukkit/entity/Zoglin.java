package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个僵尸疣猪兽。
 * <p>原文：
 * Represents a Zoglin.
 */
public interface Zoglin extends Monster, Ageable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取僵尸疣猪兽是否为幼年。
     * <p>原文：
     * Gets whether the zoglin is a baby
     *
     * @return 僵尸疣猪兽是否为幼年
     * <p>原文：Whether the zoglin is a baby
     * @deprecated see {@link Ageable#isAdult()}
     * <p>原文：Gets whether the zoglin is a baby
     * <p>原文：see {@link Ageable#isAdult()}
     */
    @Deprecated(since = "1.6.2")
    public boolean isBaby();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置僵尸疣猪兽是否为幼年。
     * <p>原文：
     * Sets whether the zoglin is a baby
     *
     * @param flag 僵尸疣猪兽是否为幼年
     * <p>原文：Whether the zoglin is a baby
     * @deprecated see {@link Ageable#setBaby()} and {@link Ageable#setAdult()}
     * <p>原文：Sets whether the zoglin is a baby
     * <p>原文：see {@link Ageable#setBaby()} and {@link Ageable#setAdult()}
     */
    @Deprecated(since = "1.16.2")
    public void setBaby(boolean flag);
}
