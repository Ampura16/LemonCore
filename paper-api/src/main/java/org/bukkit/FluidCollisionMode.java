package org.bukkit;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 确定在光线跟踪时, 碰撞到流体时的表现.
 * <p>原文：
 * Determines the collision behavior when fluids get hit during ray tracing.
 */
public enum FluidCollisionMode {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 忽略流体.
     * <p>原文：
     * Ignore fluids.
     */
    NEVER,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 仅与源流体块碰撞.
     * <p>原文：
     * Only collide with source fluid blocks.
     */
    SOURCE_ONLY,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 与所有流体碰撞.
     * <p>原文：
     * Collide with all fluids.
     */
    ALWAYS;
}
