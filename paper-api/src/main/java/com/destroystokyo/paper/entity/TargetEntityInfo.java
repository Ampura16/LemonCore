package com.destroystokyo.paper.entity;

import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示被瞄准实体的信息
 * <p>原文：
 * Represents information about a targeted entity
 * @deprecated 请使用 {@link org.bukkit.util.RayTraceResult}
 * <p>原文：use {@link org.bukkit.util.RayTraceResult}
 */
@Deprecated(forRemoval = true, since = "1.19.3")
public class TargetEntityInfo {
    private final Entity entity;
    private final Vector hitVec;

    public TargetEntityInfo(@NotNull Entity entity, @NotNull Vector hitVec) {
        this.entity = entity;
        this.hitVec = hitVec;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取被瞄准的实体
     * <p>原文：
     * Get the entity that is targeted
     *
     * @return 被瞄准的实体
     * <p>原文：Targeted entity
     */
    @NotNull
    public Entity getEntity() {
        return entity;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取实体被瞄准的位置
     * <p>原文：
     * Get the position the entity is targeted at
     *
     * @return 被瞄准的位置
     * <p>原文：Targeted position
     */
    @NotNull
    public Vector getHitVector() {
        return hitVec;
    }
}
