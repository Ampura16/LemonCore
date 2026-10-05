package org.bukkit.event.server;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 用于插件的启用和禁用事件.
 * <p>原文：
 * Used for plugin enable and disable events
 */
public abstract class PluginEvent extends ServerEvent {

    private final Plugin plugin;

    protected PluginEvent(@NotNull final Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到启用/禁用的插件.
     * <p>原文：
     * Gets the plugin involved in this event
     *
     * @return 激活这个事件的插件
     * <p>原文：Plugin for this event
     */
    @NotNull
    public Plugin getPlugin() {
        return this.plugin;
    }
}
