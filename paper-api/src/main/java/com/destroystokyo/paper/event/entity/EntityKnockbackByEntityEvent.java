package com.destroystokyo.paper.event.entity;

import io.papermc.paper.event.entity.EntityKnockbackEvent;
import io.papermc.paper.event.entity.EntityPushedByEntityAttackEvent;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当实体被另一实体击中而被击退时触发。可以修改加速度向量。如果该事件被取消，实体不会被击退。
 * <p>原文：
 * Fired when an Entity is knocked back by the hit of another Entity. The acceleration
 * vector can be modified. If this event is cancelled, the entity is not knocked back.
 */
@NullMarked
public class EntityKnockbackByEntityEvent extends EntityPushedByEntityAttackEvent {

    private final float knockbackStrength;

    @ApiStatus.Internal
    public EntityKnockbackByEntityEvent(final LivingEntity entity, final Entity hitBy, final EntityKnockbackEvent.Cause cause, final float knockbackStrength, final Vector knockback) {
        super(entity, cause, hitBy, knockback);
        this.knockbackStrength = knockbackStrength;
    }

    /**
     * @return 被击退的实体
     * <p>原文：the entity which was knocked back
     */
    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) super.getEntity();
    }

    /**
     * @return 原始击退强度。
     * <p>原文：the original knockback strength.
     * @apiNote 此值不一定与 {@link #getKnockback()} 相关。
     * <p>原文：this value doesn't necessarily relate to {@link #getKnockback()}.
     */
    @ApiStatus.Obsolete(since = "1.20.6")
    public float getKnockbackStrength() {
        return this.knockbackStrength;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取造成此事件的实体。与 {@link #getPushedBy()} 相同。
     * <p>原文：
     * Gets the causing entity. Same as {@link #getPushedBy()}.
     *
     * @return 击中的实体
     * <p>原文：the Entity which hit
     */
    public Entity getHitBy() {
        return super.getPushedBy();
    }

}
