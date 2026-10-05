package org.bukkit.plugin.messaging;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 如果插件尝试去注册一个预留通道则抛出此异常(例如"REGISTER通道")。
 * <p>原文：
 * Thrown if a plugin attempts to register for a reserved channel (such as
 * "REGISTER")
 */
@SuppressWarnings("serial")
public class ReservedChannelException extends RuntimeException {
    public ReservedChannelException() {
        this("Attempted to register for a reserved channel name.");
    }

    public ReservedChannelException(String name) {
        super("Attempted to register for a reserved channel name ('" + name + "')");
    }
}
