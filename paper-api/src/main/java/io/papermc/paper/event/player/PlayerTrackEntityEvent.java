package io.papermc.paper.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当 {@link Player} 追踪 {@link Entity} 时调用。
 * <p>
 * 如果取消，该实体不会显示给玩家，双向交互也将无法进行。
 * <p>
 * 在调用此事件时向世界添加或从中移除实体是完全不受支持的，应当避免。
 * <p>原文：
 * Is called when a {@link Player} tracks an {@link Entity}.
 * <p>
 * If cancelled entity is not shown to the player and interaction in both directions is not possible.
 * <p>
 * Adding or removing entities from the world at the point in time this event is called is completely
 * unsupported and should be avoided.
 */
@NullMarked
public class PlayerTrackEntityEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity entity;
    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerTrackEntityEvent(final Player player, final Entity entity) {
        super(player);
        this.entity = entity;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取将被追踪的实体
     * <p>原文：
     * Gets the entity that will be tracked
     *
     * @return 被追踪的实体
     * <p>原文：the entity tracked
     */
    public Entity getEntity() {
        return this.entity;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
