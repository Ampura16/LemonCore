package io.papermc.paper.threadedregions;

import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 此事件在服务器初始化之后、服务器开始并行对区域进行刻处理<b>之前</b>调用。插件可以将其作为钩子运行初始化后的逻辑，而不必担心区域可能正在并行刻处理。
 * <p>原文：
 * This event is called after the server is initialised but <b>before</b> the server begins ticking regions in parallel.
 * Plugins may use this as a hook to run post initialisation logic without worrying about the possibility that
 * regions are ticking in parallel.
 */
@NullMarked
public class RegionizedServerInitEvent extends ServerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
