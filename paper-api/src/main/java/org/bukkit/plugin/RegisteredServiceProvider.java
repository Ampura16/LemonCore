package org.bukkit.plugin;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 已注册的服务提供者.
 * <p>原文：
 * A registered service provider.
 *
 * @param <T> 服务类型
 * <p>原文：Service
 */
public class RegisteredServiceProvider<T> implements Comparable<RegisteredServiceProvider<?>> {

    private Class<T> service;
    private Plugin plugin;
    private T provider;
    private ServicePriority priority;

    public RegisteredServiceProvider(@NotNull Class<T> service, @NotNull T provider, @NotNull ServicePriority priority, @NotNull Plugin plugin) {
        this.service = service;
        this.plugin = plugin;
        this.provider = provider;
        this.priority = priority;
    }

    @NotNull
    public Class<T> getService() {
        return service;
    }

    @NotNull
    public Plugin getPlugin() {
        return plugin;
    }

    @NotNull
    public T getProvider() {
        return provider;
    }

    @NotNull
    public ServicePriority getPriority() {
        return priority;
    }

    @Override
    public int compareTo(@NotNull RegisteredServiceProvider<?> other) {
        if (priority.ordinal() == other.getPriority().ordinal()) {
            return 0;
        } else {
            return priority.ordinal() < other.getPriority().ordinal() ? 1 : -1;
        }
    }
}
