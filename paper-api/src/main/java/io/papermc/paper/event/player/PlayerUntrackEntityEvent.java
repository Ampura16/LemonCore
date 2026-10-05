package io.papermc.paper.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当 {@link Player} 取消追踪 {@link Entity} 时调用。
 * <p>
 * 在调用此事件时向世界添加或从中移除实体是完全不受支持的，应当避免。
 * <p>原文：
 * Is called when a {@link Player} untracks an {@link Entity}.
 * <p>
 * Adding or removing entities from the world at the point in time this event is called is completely
 * unsupported and should be avoided.
 */
@NullMarked
public class PlayerUntrackEntityEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity entity;

    @ApiStatus.Internal
    public PlayerUntrackEntityEvent(final Player player, final Entity entity) {
        super(player);
        this.entity = entity;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取将被取消追踪的实体
     * <p>原文：
     * Gets the entity that will be untracked
     *
     * @return 被取消追踪的实体
     * <p>原文：the entity untracked
     */
    public Entity getEntity() {
        return this.entity;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }
}
