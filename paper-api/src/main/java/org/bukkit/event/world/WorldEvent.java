package org.bukkit.event.world;

import org.bukkit.World;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表世界范围内的事件.
 * <p>原文：
 * Represents events within a world
 */
public abstract class WorldEvent extends Event {

    protected final World world;

    protected WorldEvent(@NotNull final World world) {
        this(world, false);
    }

    protected WorldEvent(@NotNull World world, boolean isAsync) {
        super(isAsync);
        this.world = world;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个事件主要涉及的世界.
     * <p>原文：
     * Gets the world primarily involved with this event
     *
     * @return 引发这个事件的世界
     * <p>原文：World which caused this event
     */
    @NotNull
    public World getWorld() {
        return this.world;
    }
}
