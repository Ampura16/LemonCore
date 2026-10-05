package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一只骆驼
 * <p>原文：
 * Represents a Camel.
 */
public interface Camel extends AbstractHorse, Sittable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这只骆驼是否处于奔跑状态（冲刺）
     * <p>原文：
     * Gets whether this camel is dashing (sprinting).
     *
     * @return 奔跑状态
     * <p>原文：dashing status
     */
    boolean isDashing();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这只骆驼是否处于奔跑状态（冲刺）
     * <p>原文：
     * Sets whether this camel is dashing (sprinting).
     *
     * @param dashing 新的奔跑状态
     * <p>原文：new dashing status
     */
    void setDashing(boolean dashing);
}
