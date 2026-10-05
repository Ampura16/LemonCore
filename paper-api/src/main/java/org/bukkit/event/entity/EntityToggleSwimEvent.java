package org.bukkit.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体游泳状态改变时触发本事件.
 * <p>原文：
 * Sent when an entity's swimming status is toggled.
 */
public class EntityToggleSwimEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final boolean isSwimming;
    private boolean cancelled;

    @ApiStatus.Internal
    public EntityToggleSwimEvent(@NotNull LivingEntity livingEntity, final boolean isSwimming) {
        super(livingEntity);
        this.isSwimming = isSwimming;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 实体是否在游泳, false 表示实体停止游泳.
     * <p>原文：
     * Returns {@code true} if the entity is now swims or
     * {@code false} if the entity stops swimming.
     *
     * @return 游泳状态
     * <p>原文：new swimming state
     */
    public boolean isSwimming() {
        return this.isSwimming;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * @deprecated This does nothing, the server and the client doesn't work
     * correctly when the server try to bypass this. A current workaround
     * exists. If you want to cancel the switch from the ground state to the
     * swimming state you need to disable the sprinting flag for the player after
     * the cancel action.
     */
    @Deprecated
    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
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
