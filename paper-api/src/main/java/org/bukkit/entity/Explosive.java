package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表可爆炸的实体.
 * <p>原文：
 * A representation of an explosive entity
 */
public interface Explosive extends Entity {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置爆炸的影响半径.
     * <br>
     * 这是爆炸的初始影响半径，可能会被其他实体属性影响.
     * <p>原文：
     * Set the radius affected by this explosive's explosion.
     * <br>
     * This is the base yield, which may be affected by other entity attributes.
     *
     * @param yield 爆炸的影响半径
     * <p>原文：The explosive yield
     */
    public void setYield(float yield);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回爆炸的影响半径.
     * <br>
     * 这是爆炸的初始影响半径，可能会被其他实体属性影响.
     * <p>原文：
     * Return the radius or yield of this explosive's explosion.
     * <br>
     * This is the base yield, which may be affected by other entity attributes.
     *
     * @return 爆炸的影响半径
     * <p>原文：the radius of blocks affected
     */
    public float getYield();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此次爆炸是否会生火.
     * <p>原文：
     * Set whether or not this explosive's explosion causes fire
     *
     * @param isIncendiary 此次爆炸是否会生火
     * <p>原文：Whether it should cause fire
     */
    public void setIsIncendiary(boolean isIncendiary);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此次爆炸是否会生火.
     * <p>原文：
     * Return whether or not this explosive creates a fire when exploding
     *
     * @return 此次爆炸是否会生火
     * <p>原文：true if the explosive creates fire, false otherwise
     */
    public boolean isIncendiary();
}
