package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当实体 targeting 一个 {@link LivingEntity} 且只能 targeting 生物实体时调用。
 * <p>原文：
 * Called when an Entity targets a {@link LivingEntity} and can only target
 * LivingEntity's.
 */
public class EntityTargetLivingEntityEvent extends EntityTargetEvent {

    @ApiStatus.Internal
    public EntityTargetLivingEntityEvent(@NotNull final Entity entity, @Nullable final LivingEntity target, @NotNull final TargetReason reason) {
        super(entity, target, reason);
    }

    @Override
    @Nullable
    public LivingEntity getTarget() {
        return (LivingEntity) super.getTarget();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置你希望生物 targeting 的实体。
     * <p>
     * 可以设置为 null，null 会导致实体没有目标。
     * <p>
     * 必须是 LivingEntity 或 null。
     * <p>原文：
     * Set the Entity that you want the mob to target.
     * <p>
     * It is possible to be {@code null}, {@code null} will cause the entity to be
     * target-less.
     * <p>
     * Must be a LivingEntity, or {@code null}.
     *
     * @param target 要 targeting 的实体
     * <p>原文：The entity to target
     */
    @Override
    public void setTarget(@Nullable Entity target) {
        if (target == null || target instanceof LivingEntity) {
            super.setTarget(target);
        }
    }
}
