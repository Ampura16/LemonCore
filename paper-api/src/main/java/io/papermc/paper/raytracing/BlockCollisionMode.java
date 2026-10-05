package io.papermc.paper.raytracing;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 决定射线追踪期间击中方块时的碰撞行为。
 * <p>原文：
 * Determines the collision behavior when blocks get hit during ray tracing.
 */
public enum BlockCollisionMode {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用碰撞形状。
     * <p>原文：
     * Use the collision shape.
     */
    COLLIDER,
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用轮廓形状。
     * <p>原文：
     * Use the outline shape.
     */
    OUTLINE,
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用视觉形状。
     * <p>原文：
     * Use the visual shape.
     */
    VISUAL
}
