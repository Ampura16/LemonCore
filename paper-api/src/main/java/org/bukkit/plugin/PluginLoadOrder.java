package org.bukkit.plugin;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表插件应被初始化并启用的时机.
 * <p>原文：
 * Represents the order in which a plugin should be initialized and enabled
 */
public enum PluginLoadOrder {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示插件会在服务器启动时加载
     * <p>原文：
     * Indicates that the plugin will be loaded at startup
     */
    STARTUP,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示插件会在第一个/默认世界被创建/加载后加载
     * <p>原文：
     * Indicates that the plugin will be loaded after the first/default world
     * was created
     */
    POSTWORLD
}
