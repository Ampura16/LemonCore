package org.bukkit.util;

import org.bukkit.entity.Entity;
import org.bukkit.generator.LimitedRegion;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * EntityTransformer 用于修改由结构生成的实体。
 * <p>原文：
 * A EntityTransformer is used to modify entities that are spawned by structure.
 */
@FunctionalInterface
@ApiStatus.Experimental
public interface EntityTransformer {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 转换结构中的实体。
     * <p>原文：
     * Transforms an entity in a structure.
     *
     * @param region 可访问区域
     * <p>原文：the accessible region
     * @param x 实体的 x 坐标
     * <p>原文：the x position of the entity
     * @param y 实体的 y 坐标
     * <p>原文：the y position of the entity
     * @param z 实体的 z 坐标
     * <p>原文：the z position of the entity
     * @param entity 实体
     * <p>原文：the entity
     * @param allowedToSpawn 实体是否被允许生成
     * <p>原文：if the entity is allowed to spawn
     * @return {@code true} 如果实体应该生成，否则 {@code false}
     * <p>原文：{@code true} if the entity should be spawned otherwise
     * {@code false}
     */
    boolean transform(@NotNull LimitedRegion region, int x, int y, int z, @NotNull Entity entity, boolean allowedToSpawn);
}
