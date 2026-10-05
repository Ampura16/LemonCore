package org.bukkit.event.entity;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 在实体退出传送门前触发本事件.
 * <p>
 * 本事件允许你修改实体退出传送门之后的速度.
 * <p>原文：
 * Called before an entity exits a portal.
 * <p>
 * This event allows you to modify the velocity of the entity after they have
 * successfully exited the portal.
 * <p>
 * Cancelling this event does not prevent the teleport, but it does prevent
 * any changes to velocity and location from taking place.
 */
public class EntityPortalExitEvent extends EntityTeleportEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Vector before;
    private Vector after;

    @ApiStatus.Internal
    public EntityPortalExitEvent(@NotNull final Entity entity, @NotNull final Location from, @NotNull final Location to, @NotNull final Vector before, @NotNull final Vector after) {
        super(entity, from, to);
        this.before = before;
        this.after = after;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体进入传送门前的速度的副本.
     * <p>原文：
     * Gets a copy of the velocity that the entity has before entering the
     * portal.
     *
     * @return 实体进入传送门前的速度的副本
     * <p>原文：velocity of entity before entering the portal
     */
    @NotNull
    public Vector getBefore() {
        return this.before.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取实体退出传送门后的速度的副本.
     * <p>原文：
     * Gets a copy of the velocity that the entity will have after exiting the
     * portal.
     *
     * @return 实体退出传送门后的速度的副本
     * <p>原文：velocity of entity after exiting the portal
     */
    @NotNull
    public Vector getAfter() {
        return this.after.clone();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置实体退出传送门后的速度.
     * <p>原文：
     * Sets the velocity that the entity will have after exiting the portal.
     *
     * @param after 实体退出传送门后的速度
     * <p>原文：the velocity after exiting the portal
     */
    public void setAfter(@NotNull Vector after) {
        this.after = after.clone();
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
