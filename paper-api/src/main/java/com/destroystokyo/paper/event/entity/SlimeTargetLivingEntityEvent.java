package com.destroystokyo.paper.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Slime;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当史莱姆决定改变方向以瞄准某个 LivingEntity 时触发。
 * <p>
 * 该事件不会因实体的实际移动而触发，仅在其选择开始移动时触发。
 * <p>原文：
 * Fired when a Slime decides to change direction to target a LivingEntity.
 * <p>
 * This event does not fire for the entity's actual movement. Only when it
 * is choosing to start moving.
 */
@NullMarked
public class SlimeTargetLivingEntityEvent extends SlimePathfindEvent {

    private final LivingEntity target;

    @ApiStatus.Internal
    public SlimeTargetLivingEntityEvent(final Slime slime, final LivingEntity target) {
        super(slime);
        this.target = target;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取被瞄准的实体
     * <p>原文：
     * Get the targeted entity
     *
     * @return 被瞄准的实体
     * <p>原文：Targeted entity
     */
    public LivingEntity getTarget() {
        return this.target;
    }
}
