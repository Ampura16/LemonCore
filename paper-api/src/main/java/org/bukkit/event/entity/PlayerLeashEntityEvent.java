package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.bukkit.inventory.EquipmentSlot;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 在玩家将生物拴绳之前立即调用。
 * <p>原文：
 * Called immediately prior to a creature being leashed by a player.
 */
public class PlayerLeashEntityEvent extends Event implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity leashHolder;
    private final Entity entity;
    private final Player player;
    private final EquipmentSlot hand;

    private boolean cancelled;

    @ApiStatus.Internal
    public PlayerLeashEntityEvent(@NotNull Entity entity, @NotNull Entity leashHolder, @NotNull Player leasher, @NotNull EquipmentSlot hand) {
        this.leashHolder = leashHolder;
        this.entity = entity;
        this.player = leasher;
        this.hand = hand;
    }

    @ApiStatus.Internal
    @Deprecated(since = "1.19.2", forRemoval = true)
    public PlayerLeashEntityEvent(@NotNull Entity entity, @NotNull Entity leashHolder, @NotNull Player leasher) {
        this(entity, leashHolder, leasher, EquipmentSlot.HAND);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回持有拴绳的实体。
     * <p>原文：
     * Returns the entity that is holding the leash.
     *
     * @return 拴绳持有者
     * <p>原文：The leash holder
     */
    @NotNull
    public Entity getLeashHolder() {
        return this.leashHolder;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回被拴绳的实体。
     * <p>原文：
     * Returns the entity being leashed.
     *
     * @return 实体
     * <p>原文：The entity
     */
    @NotNull
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回参与此事件的玩家。
     * <p>原文：
     * Returns the player involved in this event
     *
     * @return 参与此事件的玩家
     * <p>原文：Player who is involved in this event
     */
    @NotNull
    public final Player getPlayer() {
        return this.player;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回玩家用于拴绳实体的手。
     * <p>原文：
     * Returns the hand used by the player to leash the entity.
     *
     * @return 手
     * <p>原文：the hand
     */
    @NotNull
    public EquipmentSlot getHand() {
        return this.hand;
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
}
