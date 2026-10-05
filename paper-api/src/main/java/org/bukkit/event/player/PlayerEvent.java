package org.bukkit.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表玩家事件.
 * <p>原文：
 * Represents a player related event
 */
public abstract class PlayerEvent extends Event {

    protected Player player;

    protected PlayerEvent(@NotNull final Player player) {
        this.player = player;
    }

    protected PlayerEvent(@NotNull final Player player, boolean async) {
        super(async);
        this.player = player;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回哪个玩家触发了此事件.
     * <p>原文：
     * Returns the player involved in this event
     *
     * @return 触发此事件的玩家
     * <p>原文：Player who is involved in this event
     */
    @NotNull
    public final Player getPlayer() {
        return this.player;
    }
}
