package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * {@link Frog}的幼崽.
 * <p>原文：
 * A baby {@link Frog}.
 */
public interface Tadpole extends Fish {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此生物的年龄.
     * <p>原文：
     * Gets the age of this mob.
     *
     * @return 年龄
     * <p>原文：Age
     */
    public int getAge();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此生物的年龄.
     * <p>原文：
     * Sets the age of this mob.
     *
     * @param age 新的年龄
     * <p>原文：New age
     */
    public void setAge(int age);

    /**
     * Lock the age of the animal, setting this will prevent the animal from
     * maturing.
     *
     * @param lock new lock
     */
    void setAgeLock(boolean lock);

    /**
     * Gets the current agelock.
     *
     * @return the current agelock
     */
    boolean getAgeLock();
}
