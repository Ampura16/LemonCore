package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家注销插件通道时触发事件.
 * <p>原文：
 * This is called immediately after a player unregisters for a plugin channel.
 */
public class PlayerUnregisterChannelEvent extends PlayerChannelEvent {

    @ApiStatus.Internal
    public PlayerUnregisterChannelEvent(@NotNull final Player player, @NotNull final String channel) {
        super(player, channel);
    }
}
