package io.papermc.paper.event.server;

import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当数据包等资源被重载时调用（例如 /minecraft:reload）
 * <p>
 * 用于重新注册此类重载中可能丢失的自定义配方、进度等。
 * <p>原文：
 * Called when resources such as datapacks are reloaded (e.g. /minecraft:reload)
 * <p>
 * Intended for use to re-register custom recipes, advancements that may be lost during a reload like this.
 */
@NullMarked
public class ServerResourcesReloadedEvent extends ServerEvent {

    public static final HandlerList HANDLER_LIST = new HandlerList();

    private final Cause cause;

    @ApiStatus.Internal
    public ServerResourcesReloadedEvent(final Cause cause) {
        this.cause = cause;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取资源重载的原因。
     * <p>原文：
     * Gets the cause of the resource reload.
     *
     * @return 重载原因
     * <p>原文：the reload cause
     */
    public Cause getCause() {
        return this.cause;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public enum Cause {
        COMMAND,
        PLUGIN,
    }
}
