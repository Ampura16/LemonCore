package org.bukkit.event.weather;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 和天气有关的事件
 * <p>原文：
 * Represents a Weather-related event
 */
public abstract class WeatherEvent extends Event {

    protected World world;

    protected WeatherEvent(@NotNull final World where) {
        this.world = where;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回发生此次天气事件所在的世界.
     * <p>原文：
     * Returns the World where this event is occurring
     *
     * @return 天气事件所在的世界
     * <p>原文：World this event is occurring in
     */
    @NotNull
    public final World getWorld() {
        return this.world;
    }
}
