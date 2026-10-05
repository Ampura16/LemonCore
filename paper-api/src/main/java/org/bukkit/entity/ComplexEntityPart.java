package org.bukkit.entity;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表 {@link ComplexLivingEntity} 的一部分.
 * <p>原文：
 * Represents a single part of a {@link ComplexLivingEntity}
 */
public interface ComplexEntityPart extends Entity {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得此组件的父对象 {@link ComplexLivingEntity}
     * <p>原文：
     * Gets the parent {@link ComplexLivingEntity} of this part.
     *
     * @return Parent 复杂实体
     * <p>原文：Parent complex entity
     */
    @NotNull
    public ComplexLivingEntity getParent();
}
