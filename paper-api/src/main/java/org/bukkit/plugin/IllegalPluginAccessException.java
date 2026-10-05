package org.bukkit.plugin;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当插件在未启用的状态下尝试与服务器交互时抛出异常
 * <p>原文：
 * Thrown when a plugin attempts to interact with the server when it is not
 * enabled
 */
@SuppressWarnings("serial")
public class IllegalPluginAccessException extends RuntimeException {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个没有细节信息的<code>IllegalPluginAccessException</code>实例.
     * <p>原文：
     * Creates a new instance of <code>IllegalPluginAccessException</code>
     * without detail message.
     */
    public IllegalPluginAccessException() {}

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个有错误细节的<code>IllegalPluginAccessException</code>实例.
     * <p>原文：
     * Constructs an instance of <code>IllegalPluginAccessException</code>
     * with the specified detail message.
     *
     * @param msg 细节信息
     * <p>原文：the detail message.
     */
    public IllegalPluginAccessException(String msg) {
        super(msg);
    }
}
