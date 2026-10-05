package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个实体造成另外一个实体燃烧时触发该事件.
 * <p>原文：
 * Called when an entity causes another entity to combust.
 */
public class EntityCombustByEntityEvent extends EntityCombustEvent {

    private final Entity combuster;

    @ApiStatus.Internal
    @Deprecated(since = "1.21", forRemoval = true)
    public EntityCombustByEntityEvent(@NotNull final Entity combuster, @NotNull final Entity combustee, final int duration) {
        this(combuster, combustee, (float) duration);
    }

    @ApiStatus.Internal
    public EntityCombustByEntityEvent(@NotNull final Entity combuster, @NotNull final Entity combustee, final float duration) {
        super(combustee, duration);
        this.combuster = combuster;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回造成燃烧的实体.
     * <p>原文：
     * Get the entity that caused the combustion event.
     *
     * @return 造成燃烧的实体
     * <p>原文：the Entity that set the combustee alight.
     */
    @NotNull
    public Entity getCombuster() {
        return this.combuster;
    }
}
