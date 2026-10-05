package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 在实体被解除拴绳之前立即调用。
 * <p>原文：
 * Called immediately prior to an entity being unleashed.
 * <p>
 * Cancelling this event when either:
 * <ul>
 *     <li>the leashed entity dies,</li>
 *     <li>the entity changes dimension, or</li>
 *     <li>the client has disconnected the leash</li>
 * </ul>
 * will have no effect.
 */
public class EntityUnleashEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final UnleashReason reason;
    private boolean dropLeash;

    private boolean cancelled;

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public EntityUnleashEvent(@NotNull Entity entity, @NotNull UnleashReason reason) {
        this(entity, reason, false);
    }

    @ApiStatus.Internal
    public EntityUnleashEvent(@NotNull Entity entity, @NotNull UnleashReason reason, boolean dropLeash) {
        super(entity);
        this.reason = reason;
        this.dropLeash = dropLeash;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回解除拴绳的原因。
     * <p>原文：
     * Returns the reason for the unleashing.
     *
     * @return 原因
     * <p>原文：The reason
     */
    @NotNull
    public UnleashReason getReason() {
        return this.reason;
    }

    /**
     * Returns whether a leash item will be dropped.
     *
     * @return Whether the leash item will be dropped
     */
    public boolean isDropLeash() {
        return this.dropLeash;
    }

    /**
     * Sets whether a leash item should be dropped.
     *
     * @param dropLeash Whether the leash item should be dropped
     */
    public void setDropLeash(boolean dropLeash) {
        this.dropLeash = dropLeash;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

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

    public enum UnleashReason {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体的拴绳持有者已死亡或登出，因此被解除拴绳。
         * <p>原文：
         * When the entity's leashholder has died or logged out, and so is
         * unleashed
         */
        HOLDER_GONE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体的拴绳持有者尝试解除其拴绳时。
         * <p>原文：
         * When the entity's leashholder attempts to unleash it
         */
        PLAYER_UNLEASH,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 当实体的拴绳持有者距离超过 10 个方块时。
         * <p>原文：
         * When the entity's leashholder is more than 10 blocks away
         */
        DISTANCE,
        UNKNOWN;
    }
}
