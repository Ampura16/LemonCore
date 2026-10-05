package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体受到来自另一个实体的击退时调用。
 * <p>原文：
 * Called when an entity receives knockback from another entity.
 *
 * @deprecated use {@link com.destroystokyo.paper.event.entity.EntityKnockbackByEntityEvent}
 */
@Deprecated(forRemoval = true) // Paper
public class EntityKnockbackByEntityEvent extends EntityKnockbackEvent {

    private final Entity source;

    @ApiStatus.Internal
    public EntityKnockbackByEntityEvent(@NotNull final LivingEntity entity, @NotNull final Entity source, @NotNull final KnockbackCause cause, final double force, @NotNull final Vector rawKnockback, @NotNull final Vector knockback) {
        super(entity, cause, force, rawKnockback, knockback);

        this.source = source;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取对防御者造成击退的实体。
     * <p>原文：
     * Get the entity that has caused knockback to the defender.
     *
     * @return 造成击退的实体
     * <p>原文：entity that caused knockback
     */
    @NotNull
    public Entity getSourceEntity() {
        return this.source;
    }
}
