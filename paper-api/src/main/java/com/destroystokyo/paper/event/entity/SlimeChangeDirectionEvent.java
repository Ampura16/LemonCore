package com.destroystokyo.paper.event.entity;

import org.bukkit.entity.Slime;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当史莱姆决定改变其朝向时触发。
 * <p>
 * 该事件不会因实体的实际移动而触发，仅在其选择改变方向时触发。
 * <p>原文：
 * Fired when a Slime decides to change its facing direction.
 * <p>
 * This event does not fire for the entity's actual movement. Only when it
 * is choosing to change direction.
 */
@NullMarked
public class SlimeChangeDirectionEvent extends SlimePathfindEvent {

    private float yaw;

    @ApiStatus.Internal
    public SlimeChangeDirectionEvent(final Slime slime, final float yaw) {
        super(slime);
        this.yaw = yaw;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取新选定的偏航角
     * <p>原文：
     * Get the new chosen yaw
     *
     * @return 选定的偏航角
     * <p>原文：Chosen yaw
     */
    public float getNewYaw() {
        return this.yaw;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置新选定的偏航角
     * <p>原文：
     * Set the new chosen yaw
     *
     * @param yaw 选定的偏航角
     * <p>原文：Chosen yaw
     */
    public void setNewYaw(final float yaw) {
        this.yaw = yaw;
    }
}
