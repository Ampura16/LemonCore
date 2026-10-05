package io.papermc.paper.entity;

import io.papermc.paper.math.Position;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示在决定朝向某个位置/实体时应使用实体的哪个部位。
 * <p>原文：
 * Represents what part of the entity should be used when determining where to face a position/entity.
 *
 * @see org.bukkit.entity.Player#lookAt(Position, LookAnchor)
 * @see org.bukkit.entity.Player#lookAt(Entity, LookAnchor, LookAnchor)
 */
public enum LookAnchor {
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示实体的脚。
     * <p>原文：
     * Represents the entity's feet.
     * @see LivingEntity#getLocation()
     */
    FEET,
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示实体的眼睛。
     * <p>原文：
     * Represents the entity's eyes.
     * @see LivingEntity#getEyeLocation()
     */
    EYES;
}
