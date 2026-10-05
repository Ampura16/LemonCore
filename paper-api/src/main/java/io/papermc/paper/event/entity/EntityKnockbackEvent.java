package io.papermc.paper.event.entity;

import com.google.common.base.Preconditions;
import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当实体受到击退时调用。
 * <p>原文：
 * Called when an entity receives knockback.
 * @see EntityPushedByEntityAttackEvent
 * @see com.destroystokyo.paper.event.entity.EntityKnockbackByEntityEvent
 */
@NullMarked
public class EntityKnockbackEvent extends EntityEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Cause cause;
    protected Vector knockback;
    private boolean cancelled;

    @ApiStatus.Internal
    public EntityKnockbackEvent(final Entity entity, final EntityKnockbackEvent.Cause cause, final Vector knockback) {
        super(entity);
        this.cause = cause;
        this.knockback = knockback;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取击退的原因。
     * <p>原文：
     * Gets the cause of the knockback.
     *
     * @return 击退的原因
     * <p>原文：the cause of the knockback
     */
    public EntityKnockbackEvent.Cause getCause() {
        return this.cause;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取将施加给实体的击退力。<br> 该值是只读的，对其所做的更改<b>不会</b>影响最终受到的击退。请使用 {@link #setKnockback(Vector)} 进行更改。
     * <p>原文：
     * Gets the knockback force that will be applied to the entity. <br>
     * This value is read-only, changes made to it <b>will not</b> have any
     * effect on the final knockback received. Use {@link #setKnockback(Vector)}
     * to make changes.
     *
     * @return 击退
     * <p>原文：the knockback
     */
    public Vector getKnockback() {
        return this.knockback.clone();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置将施加给实体的击退力。
     * <p>原文：
     * Sets the knockback force that will be applied to the entity.
     *
     * @param knockback 击退
     * <p>原文：the knockback
     */
    public void setKnockback(final Vector knockback) {
        Preconditions.checkArgument(knockback != null, "knockback");
        this.knockback = knockback.clone();
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

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

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于指定击退原因的枚举。
     * <p>原文：
     * An enum to specify the cause of the knockback.
     */
    public enum Cause {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 非实体伤害造成的击退。
         * <p>原文：
         * Knockback caused by non-entity damage.
         */
        DAMAGE,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 攻击实体造成的击退。
         * <p>原文：
         * Knockback caused by an attacking entity.
         */
        ENTITY_ATTACK,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 爆炸造成的击退。
         * <p>原文：
         * Knockback caused by an explosion.
         */
        EXPLOSION,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 目标用盾牌格挡造成的击退。
         * <p>原文：
         * Knockback caused by the target blocking with a shield.
         */
        SHIELD_BLOCK,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 横扫攻击造成的击退。
         * <p>原文：
         * Knockback caused by a sweeping attack.
         */
        SWEEP_ATTACK,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 通用推动。
         * <p>原文：
         * A generic push.
         */
        PUSH,
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 原因未知的击退。
         * <p>原文：
         * Knockback with an unknown cause.
         */
        UNKNOWN
    }
}
