package com.destroystokyo.paper.event.server;

import com.destroystokyo.paper.exception.ServerException;
import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 每当服务器中可恢复的代码段抛出异常时调用。
 * <p>原文：
 * Called whenever an exception is thrown in a recoverable section of the server.
 */
@NullMarked
public class ServerExceptionEvent extends Event {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final ServerException exception;

    @ApiStatus.Internal
    public ServerExceptionEvent(final ServerException exception) {
        super(!Bukkit.isPrimaryThread());
        this.exception = exception;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取被抛出的包装异常。
     * <p>原文：
     * Gets the wrapped exception that was thrown.
     *
     * @return 抛出的异常
     * <p>原文：Exception thrown
     */
    public ServerException getException() {
        return this.exception;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
