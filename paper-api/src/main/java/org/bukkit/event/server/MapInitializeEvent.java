package org.bukkit.event.server;

import org.bukkit.event.HandlerList;
import org.bukkit.map.MapView;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 地图初始化时调用.
 * <p>原文：
 * Called when a map is initialized.
 */
public class MapInitializeEvent extends ServerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final MapView mapView;

    @ApiStatus.Internal
    public MapInitializeEvent(@NotNull final MapView mapView) {
        this.mapView = mapView;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得本事件初始化的地图.
     * <p>原文：
     * Gets the map initialized in this event.
     *
     * @return 地图物品.
     * <p>原文：Map for this event
     */
    @NotNull
    public MapView getMap() {
        return this.mapView;
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
