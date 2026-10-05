package com.destroystokyo.paper.event.entity;

import org.bukkit.entity.Slime;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当史莱姆决定开始游荡时触发。
 * <p>
 * 该事件不会因实体的实际移动而触发，仅在其选择开始移动时触发。
 * <p>原文：
 * Fired when a Slime decides to start wandering.
 * <p>
 * This event does not fire for the entity's actual movement. Only when it
 * is choosing to start moving.
 */
@NullMarked
public class SlimeWanderEvent extends SlimePathfindEvent {

    @ApiStatus.Internal
    public SlimeWanderEvent(final Slime slime) {
        super(slime);
    }
}
