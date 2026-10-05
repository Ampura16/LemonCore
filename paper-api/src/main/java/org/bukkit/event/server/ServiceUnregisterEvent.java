package org.bukkit.event.server;

import org.bukkit.event.HandlerList;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个服务注销时调用.
 * <p>
 * 注意:注册和注销的事件顺序不互相依赖.
 * <p>原文：
 * This event is called when a service is unregistered.
 * <p>
 * Warning: The order in which register and unregister events are called
 * should not be relied upon.
 */
public class ServiceUnregisterEvent extends ServiceEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @ApiStatus.Internal
    public ServiceUnregisterEvent(@NotNull RegisteredServiceProvider<?> serviceProvider) {
        super(serviceProvider);
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
