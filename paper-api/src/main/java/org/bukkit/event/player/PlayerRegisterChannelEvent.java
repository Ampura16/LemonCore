package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 玩家注册通信通道之后立即触发此事件.
 * <p>原文：
 * This is called immediately after a player registers for a plugin channel.
 */
public class PlayerRegisterChannelEvent extends PlayerChannelEvent {

    @ApiStatus.Internal
    public PlayerRegisterChannelEvent(@NotNull final Player player, @NotNull final String channel) {
        super(player, channel);
    }
}
