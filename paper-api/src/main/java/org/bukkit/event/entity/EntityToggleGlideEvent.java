package org.bukkit.event.entity;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体使用鞘翅滑翔状态被切换时发送。
 * 此事件被调用的示例：
 * <ul>
 *     <li>玩家在空中使用鞘翅时按下跳跃键</li>
 *     <li>玩家在滑翔时（使用鞘翅）落地</li>
 * </ul>
 * 这可以通过玩家变为水平姿势的动画来直观估计。
 * <p>原文：
 * Sent when an entity's gliding status is toggled with an Elytra.
 * Examples of when this event would be called:
 * <ul>
 *     <li>Player presses the jump key while in midair and using an Elytra</li>
 *     <li>Player lands on ground while they are gliding (with an Elytra)</li>
 * </ul>
 * This can be visually estimated by the animation in which a player turns horizontal.
 */
public class EntityToggleGlideEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final boolean isGliding;

    private boolean cancelled;

    @ApiStatus.Internal
    public EntityToggleGlideEvent(@NotNull LivingEntity livingEntity, boolean isGliding) {
        super(livingEntity);
        this.isGliding = isGliding;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 如果实体现在正在滑翔则返回 true，如果实体停止滑翔则返回 false。
     * <p>原文：
     * Returns {@code true} if the entity is now gliding or
     * {@code false} if the entity stops gliding.
     *
     * @return 新的滑翔状态
     * <p>原文：new gliding state
     */
    public boolean isGliding() {
        return this.isGliding;
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
