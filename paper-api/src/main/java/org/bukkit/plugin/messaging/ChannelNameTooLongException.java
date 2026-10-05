package org.bukkit.plugin.messaging;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 如果插件通道(Plugin Channel)过长则抛出此异常。
 * <p>原文：
 * Thrown if a Plugin Channel is too long.
 */
@SuppressWarnings("serial")
public class ChannelNameTooLongException extends RuntimeException {
    public ChannelNameTooLongException() {
        super("Attempted to send a Plugin Message to a channel that was too large. The maximum length a channel may be is " + Messenger.MAX_CHANNEL_SIZE + " chars.");
    }

    // Paper start
    public ChannelNameTooLongException(int length, String shortenedChannel) {
        super("Attempted to send a Plugin Message to a channel that was too large. The maximum length a channel may be is " + Messenger.MAX_CHANNEL_SIZE + " chars (attempted " + length + " - '" + shortenedChannel + ".");
        // Paper end
    }
}
