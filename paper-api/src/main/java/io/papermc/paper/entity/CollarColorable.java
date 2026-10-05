package io.papermc.paper.entity;

import org.bukkit.DyeColor;
import org.bukkit.entity.LivingEntity;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 项圈可以染色的实体。
 * <p>原文：
 * Entities that can have their collars colored.
 */
@NullMarked
public interface CollarColorable extends LivingEntity {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此实体的项圈颜色
     * <p>原文：
     * Get the collar color of this entity
     *
     * @return 项圈的颜色
     * <p>原文：the color of the collar
     */
    DyeColor getCollarColor();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置此实体的项圈颜色
     * <p>原文：
     * Set the collar color of this entity
     *
     * @param color 要应用的颜色
     * <p>原文：the color to apply
     */
    void setCollarColor(DyeColor color);
}
