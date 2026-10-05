package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一种可以坐下的动物。
 * <p>原文：
 * An entity that can sit still.
 */
public interface Sittable extends Entity {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此动物是否正在坐下。
     * <p>原文：
     * Checks if this entity is sitting
     *
     * @return 如果正在坐下则为 true
     * <p>原文：Checks if this animal is sitting
     * <p>原文：true if sitting
     */
    boolean isSitting();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此动物是否坐下。将移除动物之前正在跟随的任何路径。
     * <p>原文：
     * Sets if this entity is sitting. Will remove any path that the entity
     * was following beforehand.
     *
     * @param sitting 如果坐下则为 true
     * <p>原文：Sets if this animal is sitting. Will remove any path that the animal was following beforehand.
     * <p>原文：true if sitting
     */
    void setSitting(boolean sitting);

}
