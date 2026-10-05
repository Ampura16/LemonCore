package org.bukkit.entity;

import org.bukkit.block.BlockFace;
import org.bukkit.material.Attachable;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表悬挂实体.
 * <p>原文：
 * Represents a Hanging entity
 */
public interface Hanging extends Entity, Attachable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置悬挂实体的方向，这可能会重写所有规则. 注意：如果结果无效，实体可能会掉落为物品.
     * <p>原文：
     * Sets the direction of the hanging entity, potentially overriding rules
     * of placement. Note that if the result is not valid the object would
     * normally drop as an item.
     *
     * @param face 新的方向
     * <p>原文：The new direction.
     * @param force 是否强制
     * <p>原文：Whether to force it.
     * @return 如果强制执行失败或者那里没有方块附着给定的方向则为false
     * <p>原文：False if force was false and there was no block for it to
     *     attach to in order to face the given direction.
     */
    public boolean setFacingDirection(@NotNull BlockFace face, boolean force);
}
