package org.bukkit.event.server;

import org.bukkit.plugin.RegisteredServiceProvider;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 与已注册服务有关的事件.
 * {@link org.bukkit.plugin.ServicesManager}会调用本事件.
 * <p>原文：
 * An event relating to a registered service. This is called in a {@link
 * org.bukkit.plugin.ServicesManager}
 */
public abstract class ServiceEvent extends ServerEvent {

    private final RegisteredServiceProvider<?> provider;

    protected ServiceEvent(@NotNull final RegisteredServiceProvider<?> provider) {
        this.provider = provider;
    }

    @NotNull
    public RegisteredServiceProvider<?> getProvider() {
        return this.provider;
    }
}
