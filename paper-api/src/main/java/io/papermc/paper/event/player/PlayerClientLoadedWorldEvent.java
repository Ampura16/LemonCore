package io.papermc.paper.event.player;

import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当玩家被标记为已加载时调用。
 * <p>
 * 这发生在玩家加载世界后通知服务器时（关闭下载地形界面），或玩家在加入服务器或重生后 60 刻内未通知时。
 * <p>原文：
 * Called when a player is marked as loaded.
 * <p>
 * This either happens when the player notifies the server after loading the world (closing the downloading terrain screen)
 * or when the player has not done so for 60 ticks after joining the server or respawning.
 */
@NullMarked
public class PlayerClientLoadedWorldEvent extends PlayerEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final boolean timeout;

    @ApiStatus.Internal
    public PlayerClientLoadedWorldEvent(final Player player, final boolean timeout) {
        super(player);
        this.timeout = timeout;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果该事件是因为玩家加入服务器或重生后 60 刻内服务器未收到玩家通知而触发的，则为 true。
     * <p>原文：
     * True if the event was triggered because the server has not been notified by the player
     * for 60 ticks after the player joined the server or respawned.
     *
     * @return 如果事件因超时而触发则为 true
     * <p>原文：true if the event was triggered because of a timeout
     */
    public boolean isTimeout() {
        return timeout;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
