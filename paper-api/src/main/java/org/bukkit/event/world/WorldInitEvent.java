package org.bukkit.event.world;

import org.bukkit.World;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当一个世界被初始化时调用.
 * <p>
 * 如要获取到每个世界, 建议在 plugin.yml 加入以下代码(调整插件的加载时机):
 * <pre>load: STARTUP</pre>
 * <p>原文：
 * Called when a World is initializing.
 * <p>
 * To get every world it is recommended to add following to the plugin.yml.
 * <pre>load: STARTUP</pre>
 */
public class WorldInitEvent extends WorldEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @ApiStatus.Internal
    public WorldInitEvent(@NotNull final World world) {
        super(world);
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
}
