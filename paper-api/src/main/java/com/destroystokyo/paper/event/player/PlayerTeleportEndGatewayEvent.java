package com.destroystokyo.paper.event.player;

import org.bukkit.Location;
import org.bukkit.block.EndGateway;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当末地折跃门触发传送时触发
 * <p>原文：
 * Fired when a teleport is triggered for an End Gateway
 */
@NullMarked
public class PlayerTeleportEndGatewayEvent extends PlayerTeleportEvent {

    private final EndGateway gateway;

    @ApiStatus.Internal
    public PlayerTeleportEndGatewayEvent(final Player player, final Location from, final Location to, final EndGateway gateway) {
        super(player, from, to, PlayerTeleportEvent.TeleportCause.END_GATEWAY);
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
