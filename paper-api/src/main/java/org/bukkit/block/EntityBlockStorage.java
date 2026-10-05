package org.bukkit.block;

import java.util.List;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表可存储实体的方块 (译注:或者称为实体容器方块).
 * <p>原文：
 * Represents a captured state of a block which stores entities.
 *
 * @param <T> 本方块可存储的实体类型
 * <p>原文：Entity this block can store
 */
public interface EntityBlockStorage<T extends Entity> extends TileState {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测本实体容器是否已满.
     * <p>原文：
     * Check if the block is completely full of entities.
     *
     * @return 是否存满实体
     * <p>原文：True if block is full
     */
    boolean isFull();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取已存储的实体的数量.
     * <p>原文：
     * Get the amount of entities currently in this block.
     *
     * @return 当前存放的实体的数量
     * <p>原文：Amount of entities currently in this block
     */
    int getEntityCount();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取最大可存储的实体数.
     * <p>原文：
     * Get the maximum amount of entities this block can hold.
     *
     * @return 最大可存储的实体数
     * <p>原文：Maximum amount of entities this block can hold
     */
    int getMaxEntities();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置最大可存储的实体数.
     * <p>原文：
     * Set the maximum amount of entities this block can hold.
     *
     * @param max 最大可存储的实体数
     * <p>原文：Maximum amount of entities this block can hold
     */
    void setMaxEntities(int max);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 释放方块存储的所有实体.
     * <p>原文：
     * Release all the entities currently stored in the block.
     *
     * @return 被释放的实体的列表
     * <p>原文：List of all entities which were released
     */
    @NotNull
    List<T> releaseEntities();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 入驻一个实体至此方块.
     * <p>原文：
     * Add an entity to the block.
     *
     * @param entity 要入驻的实体
     * <p>原文：Entity to add to the block
     */
    void addEntity(@NotNull T entity);

    // Paper start - Add EntityBlockStorage clearEntities
    /**
     * Clear all currently stored entities in the block.
     */
    void clearEntities();
    // Paper end
}
