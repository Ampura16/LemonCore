package org.bukkit.event.server;

import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当服务器启动/重载完成时调用.
 * <p>原文：
 * This event is called when either the server startup or reload has completed.
 */
public class ServerLoadEvent extends ServerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final LoadType type;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 以指定加载类型创建一个 {@code ServerLoadEvent}.
     *
     * @param type 服务器加载类型
     */
    @ApiStatus.Internal
    public ServerLoadEvent(@NotNull LoadType type) {
        this.type = type;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取服务器是以何种形式完成加载的.
     * <p>原文：
     * Gets the context in which the server was loaded.
     *
     * @return 服务器加载类型
     * <p>原文：the context in which the server was loaded
     */
    @NotNull
    public LoadType getType() {
        return this.type;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表事件完成时, 服务器成功加载的方式.
     * <p>原文：
     * Represents the context in which the enclosing event has been completed.
     */
    public enum LoadType {
        STARTUP,
        RELOAD
    }
}
