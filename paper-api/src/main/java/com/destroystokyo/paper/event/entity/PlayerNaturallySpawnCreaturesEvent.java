package com.destroystokyo.paper.event.entity;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在每次怪物生成刻事件中，服务器计算要在哪些区块尝试生成怪物时触发
 * <p>原文：
 * Fired when the server is calculating what chunks to try to spawn monsters in every Monster Spawn Tick event
 */
@NullMarked
public class PlayerNaturallySpawnCreaturesEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private byte radius;
    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerNaturallySpawnCreaturesEvent(final Player player, final byte radius) {
        super(player);
        this.radius = radius;
    }

    /**
     * @return 此玩家周围计入自然生成选择的区块半径
     * <p>原文：The radius of chunks around this player to be included in natural spawn selection
     */
    public byte getSpawnRadius() {
        return this.radius;
    }

    /**
     * @param radius 此玩家周围纳入自然生成选择的区块半径
     * <p>原文：The radius of chunks around this player to be included in natural spawn selection
     */
    public void setSpawnRadius(final byte radius) {
        this.radius = radius;
    }

    /**
     * @return 此玩家的区块是否会被排除在自然生成之外
     * <p>原文：If this player's chunks will be excluded from natural spawns
     */
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * @param cancel 如果希望取消此事件且不将此玩家的区块纳入自然生成则为 {@code true}
     * <p>原文：{@code true} if you wish to cancel this event, and not include this player's chunks for natural spawning
     */
    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
