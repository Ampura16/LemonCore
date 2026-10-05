package org.bukkit.event.player;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当向玩家显示隐藏实体时触发.
 * <br>
 * 此事件仅在实体的可见性状态实际改变时触发.
 * <br>
 * 无论实体是否在追踪范围内，此事件都会触发.
 * <p>原文：
 * Called when a hidden entity is shown to a player.
 * <br>
 * This event is only called when the entity's visibility status is actually
 * changed.
 * <br>
 * This event is called regardless of whether the entity was within tracking
 * range.
 *
 * @see Player#showEntity(org.bukkit.plugin.Plugin, org.bukkit.entity.Entity)
 */
public class PlayerShowEntityEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity entity;

    @ApiStatus.Internal
    public PlayerShowEntityEvent(@NotNull Player player, @NotNull Entity entity) {
        super(player);
        this.entity = entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取已对玩家显示的实体.
     * <p>原文：
     * Gets the entity which has been shown to the player.
     *
     * @return 被显示的实体
     * <p>原文：the shown entity
     */
    @NotNull
    public Entity getEntity() {
        return this.entity;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
