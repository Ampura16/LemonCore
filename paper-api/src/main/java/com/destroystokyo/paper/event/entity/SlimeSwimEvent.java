package com.destroystokyo.paper.event.entity;

import org.bukkit.entity.Slime;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当史莱姆在水/熔岩中游动时决定开始跳跃时触发。
 * <p>
 * 该事件不会因实体的实际移动而触发，仅在其选择开始跳跃时触发。
 * <p>原文：
 * Fired when a Slime decides to start jumping while swimming in water/lava.
 * <p>
 * This event does not fire for the entity's actual movement. Only when it
 * is choosing to start jumping.
 */
@NullMarked
public class SlimeSwimEvent extends SlimeWanderEvent {

    @ApiStatus.Internal
    public SlimeSwimEvent(final Slime slime) {
        super(slime);
    }
}
