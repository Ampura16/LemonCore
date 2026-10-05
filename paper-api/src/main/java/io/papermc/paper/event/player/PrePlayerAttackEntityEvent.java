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
 * 当玩家尝试攻击实体时调用。
 * <p>
 * 该事件发生在任何伤害逻辑之前，因此取消该事件将阻止攻击时播放任何声音。
 * <p>
 * 对于某些实体，该事件会以已取消状态触发，并且 {@link PrePlayerAttackEntityEvent#willAttack()} 为 false，表示该实体实际上不会被攻击。
 * <p>
 * 注意：可能存在其他因素（无敌等）阻止该实体被攻击，而该事件不会涵盖这些因素
 * <p>原文：
 * Called when the player tries to attack an entity.
 * <p>
 * This occurs before any of the damage logic, so cancelling this event
 * will prevent any sort of sounds from being played when attacking.
 * <p>
 * This event will fire as cancelled for certain entities, with {@link PrePlayerAttackEntityEvent#willAttack()} being false
 * to indicate that this entity will not actually be attacked.
 * <p>
 * Note: there may be other factors (invulnerability, etc.) that will prevent this entity from being attacked that this event will not cover
 */
@NullMarked
public class PrePlayerAttackEntityEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Entity attacked;
    private final boolean willAttack;

    private boolean cancelled;

    @ApiStatus.Internal
    public PrePlayerAttackEntityEvent(final Player player, final Entity attacked, final boolean willAttack) {
        super(player);
        this.attacked = attacked;
        this.willAttack = willAttack;
        this.cancelled = !willAttack;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此事件中被攻击的实体。
     * <p>原文：
     * Gets the entity that was attacked in this event.
     *
     * @return 被攻击的实体
     * <p>原文：entity that was attacked
     */
    public Entity getAttacked() {
        return this.attacked;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此实体是否会被正常攻击。像下落的沙子这样的实体会返回 {@code false}，因为其实体类型不允许被攻击。
     * <p>
     * 注意：可能存在其他因素（无敌等）阻止该实体被攻击，而该事件不会涵盖这些因素
     * <p>原文：
     * Gets if this entity will be attacked normally.
     * Entities like falling sand will return {@code false} because
     * their entity type does not allow them to be attacked.
     * <p>
     * Note: there may be other factors (invulnerability, etc.) that will prevent this entity from being attacked that this event will not cover
     *
     * @return 实体是否会真正被攻击
     * <p>原文：if the entity will actually be attacked
     */
    public boolean willAttack() {
        return this.willAttack;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置是否应取消此次攻击；注意，如果 {@link PrePlayerAttackEntityEvent#willAttack()} 返回 false，此事件将始终被取消。
     * <p>原文：
     * Sets if this attack should be cancelled, note if {@link PrePlayerAttackEntityEvent#willAttack()} returns false
     * this event will always be cancelled.
     *
     * @param cancel 如果希望取消此事件则为 {@code true}
     * <p>原文：{@code true} if you wish to cancel this event
     */
    @Override
    public void setCancelled(final boolean cancel) {
        if (!this.willAttack) {
            return;
        }

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
