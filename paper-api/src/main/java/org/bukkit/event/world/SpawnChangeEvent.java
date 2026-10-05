package org.bukkit.event.world;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个在世界的出生点被改变时调用的事件.包含这个世界之前的出生点.
 * <p>原文：
 * An event that is called when a world's spawn changes. The world's previous
 * spawn location is included.
 */
public class SpawnChangeEvent extends WorldEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Location previousLocation;

    @ApiStatus.Internal
    public SpawnChangeEvent(@NotNull final World world, @NotNull final Location previousLocation) {
        super(world);
        this.previousLocation = previousLocation;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取之前的出生点位置.
     * <p>原文：
     * Gets the previous spawn location
     *
     * @return 用于出生点的位置
     * <p>原文：Location that used to be spawn
     */
    @NotNull
    public Location getPreviousLocation() {
        return this.previousLocation.clone();
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
