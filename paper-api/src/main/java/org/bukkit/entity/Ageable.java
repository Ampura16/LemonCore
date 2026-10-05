package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个有年龄的实体.
 * <p>原文：
 * Represents an entity that can age.
 */
public interface Ageable extends Creature {
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这只动物的年龄.
     * <p>原文：
     * Gets the age of this mob.
     *
     * @return 年龄
     * <p>原文：Age
     */
    public int getAge();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这只动物的年龄.
     * <p>原文：
     * Sets the age of this mob.
     *
     * @param age 新的年龄
     * <p>原文：New age
     */
    public void setAge(int age);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 锁定这只动物的年龄, 设置这个将防止动物从长成以准备进行交配.
     * <p>原文：
     * Lock the age of the animal, setting this will prevent the animal from
     * maturing or getting ready for mating.
     *
     * @param lock 是否锁定
     * <p>原文：new lock
     * @deprecated 另请参阅 {@link Breedable#setAgeLock(boolean)}
     * <p>原文：see {@link Breedable#setAgeLock(boolean)}
     */
    @Deprecated(since = "1.16.2")
    public void setAgeLock(boolean lock);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个动物的年龄是否被锁定.
     * <p>原文：
     * Gets the current agelock.
     *
     * @return 这个动物的年龄是否被锁定
     * <p>原文：the current agelock
     * @deprecated 另请参阅 {@link Breedable#getAgeLock()}
     * <p>原文：see {@link Breedable#getAgeLock()}
     */
    @Deprecated(since = "1.16.2")
    public boolean getAgeLock();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这只动物为年幼状态.
     * <p>原文：
     * Sets the age of the mob to a baby
     */
    public void setBaby();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这只动物为成年状态.
     * <p>原文：
     * Sets the age of the mob to an adult
     */
    public void setAdult();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个动物是否成年.
     * <p>原文：
     * Returns true if the mob is an adult.
     *
     * @return 如果动物成年，则返回true
     * <p>原文：return true if the mob is an adult
     */
    public boolean isAdult();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回动物是否具有繁殖能力.
     * <p>原文：
     * Return the ability to breed of the animal.
     *
     * @return 动物是否具有繁殖能力
     * <p>原文：the ability to breed of the animal
     * @deprecated 另请参阅 {@link Breedable#canBreed()}
     * <p>原文：see {@link Breedable#canBreed()}
     */
    @Deprecated(since = "1.16.2")
    public boolean canBreed();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置动物是否具有繁殖能力,如果动物是年幼的设置此将立即长大.
     * <p>原文：
     * Set breedability of the animal, if the animal is a baby and set to
     * breed it will instantly grow up.
     *
     * @param breed 动物是否具有繁殖能力
     * <p>原文：breedability of the animal
     * @deprecated 另请参阅 {@link Breedable#canBreed()}
     * <p>原文：see {@link Breedable#setBreed(boolean)}
     */
    @Deprecated(since = "1.16.2")
    public void setBreed(boolean breed);
}
