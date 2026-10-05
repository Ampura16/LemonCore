package org.bukkit.event.server;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 各种服务器事件.
 * <p>原文：
 * Miscellaneous server events
 */
public abstract class ServerEvent extends Event {

    public ServerEvent() {
        super(!Bukkit.isPrimaryThread()); // Paper
    }

    public ServerEvent(boolean isAsync) {
        super(isAsync);
    }
}
