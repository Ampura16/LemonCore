package io.papermc.paper.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当实体被另一实体的攻击推动时触发。可以修改加速度向量。如果该事件被取消，实体不会被推动。
 * <p>
 * 注意：由于会进行多次加速度计算，某些实体可能对同一实体多次触发该事件。
 * <p>原文：
 * Fired when an entity is pushed by another entity's attack. The acceleration vector can be
 * modified. If this event is cancelled, the entity will not get pushed.
 * <p>
 * Note: Some entities might trigger this multiple times on the same entity
 * as multiple acceleration calculations are done.
 */
@NullMarked
public class EntityPushedByEntityAttackEvent extends EntityKnockbackEvent {

    private final Entity pushedBy;

    @ApiStatus.Internal
    public EntityPushedByEntityAttackEvent(final Entity entity, final EntityKnockbackEvent.Cause cause, final Entity pushedBy, final Vector knockback) {
        super(entity, cause, knockback);
        this.pushedBy = pushedBy;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取推动受影响实体的实体。
     * <p>原文：
     * Gets the entity which pushed the affected entity.
     *
     * @return 进行推动的实体
     * <p>原文：the pushing entity
     */
    public Entity getPushedBy() {
        return this.pushedBy;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取将施加给受影响实体的加速度。
     * <p>原文：
     * Gets the acceleration that will be applied to the affected entity.
     *
     * @return 加速度向量
     * <p>原文：the acceleration vector
     * @deprecated 请使用 {@link #getKnockback()}
     * <p>原文：use {@link #getKnockback()}
     */
    @Deprecated(since = "1.20.6", forRemoval = true)
    public Vector getAcceleration() {
        return this.knockback; // TODO Clone in 1.21 to not instantly break what was technically already modifiable (call super.getKnockback())
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置将施加给受影响实体的相对加速度。
     * <p>原文：
     * Sets the relative acceleration that will be applied to the affected entity.
     *
     * @param acceleration 新的加速度向量
     * <p>原文：the new acceleration vector
     * @deprecated 请使用 {@link #setKnockback(Vector)}
     * <p>原文：use {@link #setKnockback(Vector)}
     */
    @Deprecated(since = "1.20.6", forRemoval = true)
    public void setAcceleration(final Vector acceleration) {
        super.setKnockback(acceleration);
    }
}
