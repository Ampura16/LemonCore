package org.bukkit.event.entity;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表实体相关事件.
 * <p>原文：
 * Represents an Entity-related event
 */
public abstract class EntityEvent extends Event {

    protected Entity entity;

    protected EntityEvent(@NotNull final Entity entity) {
        this.entity = entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回这个事件所涉及的实体.
     * <p>原文：
     * Returns the Entity involved in this event
     *
     * @return 涉及这个事件的实体
     * <p>原文：Entity who is involved in this event
     */
    @NotNull
    public Entity getEntity() {
        return this.entity;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取涉及这个事件的实体的EntityType.
     * <p>原文：
     * Gets the EntityType of the Entity involved in this event.
     *
     * @return 涉及这个事件的实体的EntityType
     * <p>原文：EntityType of the Entity involved in this event
     */
    @NotNull
    public EntityType getEntityType() {
        return this.entity.getType();
    }
}
