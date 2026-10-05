package org.bukkit.event.hanging;

import org.bukkit.entity.Hanging;
import org.bukkit.event.Event;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表与悬挂实体有关的事件.
 * <p>原文：
 * Represents a hanging entity-related event.
 */
public abstract class HangingEvent extends Event {

    protected Hanging hanging;

    protected HangingEvent(@NotNull final Hanging painting) {
        this.hanging = painting;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取涉及了这个事件的悬挂实体.
     * <p>原文：
     * Gets the hanging entity involved in this event.
     *
     * @return 悬挂实体
     * <p>原文：the hanging entity
     */
    @NotNull
    public Hanging getEntity() {
        return this.hanging;
    }
}
