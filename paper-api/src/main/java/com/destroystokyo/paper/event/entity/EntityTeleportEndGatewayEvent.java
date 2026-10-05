package com.destroystokyo.paper.event.entity;

import org.bukkit.Location;
import org.bukkit.block.EndGateway;
import org.bukkit.entity.Entity;
import org.bukkit.event.entity.EntityTeleportEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 每当实体尝试在末地折跃门中传送时触发
 * <p>原文：
 * Fired any time an entity attempts to teleport in an end gateway
 */
@NullMarked
public class EntityTeleportEndGatewayEvent extends EntityTeleportEvent {

    private final EndGateway gateway;

    @ApiStatus.Internal
    public EntityTeleportEndGatewayEvent(final Entity entity, final Location from, final Location to, final EndGateway gateway) {
        super(entity, from, to);
        this.gateway = gateway;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 触发传送的折跃门
     * <p>原文：
     * The gateway triggering the teleport
     *
     * @return 所使用的末地折跃门
     * <p>原文：EndGateway used
     */
    public EndGateway getGateway() {
        return this.gateway;
    }

}
