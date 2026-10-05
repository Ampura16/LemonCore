package io.papermc.paper.configuration;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示服务器的配置设置。
 * <p>
 * 此接口并不打算涵盖所有可能的服务器配置选项，而是侧重于部分关键设置和行为。
 * <p>原文：
 * Represents the configuration settings for a server.
 * <p>
 * This interface doesn't aim to cover every possible server configuration
 * option but focuses on selected critical settings and behaviors.
 */
public interface ServerConfiguration {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取服务器是否处于正版验证模式。
     * <p>
     * 在以下情况下该方法返回 true：
     * <ul>
     * <li>服务器处于 {@link org.bukkit.Server#getOnlineMode online mode}，</li>
     * <li>启用了 Velocity 且配置为正版验证模式，或</li>
     * <li>启用了 BungeeCord 且配置为正版验证模式。</li>
     * </ul>
     * <p>原文：
     * Gets whether the server is in online mode.
     * <p>
     * This method returns true if:
     * <ul>
     * <li>The server is in {@link org.bukkit.Server#getOnlineMode online mode},</li>
     * <li>Velocity is enabled and configured to be in online mode, or</li>
     * <li>BungeeCord is enabled and configured to be in online mode.</li>
     * </ul>
     *
     * @return 服务器是否处于正版模式或位于配置为正版模式的代理之后
     * <p>原文：whether the server is in online mode or behind a proxy configured for online mode
     */
    boolean isProxyOnlineMode();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取服务器是否被配置为在代理之后工作。
     * <p>
     * 如果启用了 Velocity 或 BungeeCord，则返回 true。
     * <p>原文：
     * Gets whether the server is configured to work behind a proxy.
     * <p>
     * This returns true if either Velocity or BungeeCord is enabled.
     *
     * @return 服务器是否被配置为在代理之后运行
     * <p>原文：whether the server is configured to work behind a proxy
     */
    boolean isProxyEnabled();
}
