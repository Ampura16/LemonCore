package io.papermc.paper.event.player;

import io.papermc.paper.chat.ChatRenderer;
import java.util.Set;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.chat.SignedMessage;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当 {@link Player} 向服务器发送聊天消息时触发的事件。
 * <p>
 * 根据触发方式的不同，该事件有时会同步触发。
 * <p>
 * 如果玩家通过传入的数据包直接导致该事件，则事件是异步的；如果插件通过强制玩家聊天来触发该事件，则事件是同步的。
 * <p>
 * 应注意检查 {@link #isAsynchronous()} 并据此妥善处理事件。
 * <p>原文：
 * An event fired when a {@link Player} sends a chat message to the server.
 * <p>
 * This event will sometimes fire synchronously, depending on how it was
 * triggered.
 * <p>
 * If a player is the direct cause of this event by an incoming packet, this
 * event will be asynchronous. If a plugin triggers this event by compelling a
 * player to chat, this event will be synchronous.
 * <p>
 * Care should be taken to check {@link #isAsynchronous()} and treat the event
 * appropriately.
 */
@NullMarked
public final class AsyncChatEvent extends AbstractChatEvent {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    @ApiStatus.Internal
    public AsyncChatEvent(final boolean async, final Player player, final Set<Audience> viewers, final ChatRenderer renderer, final Component message, final Component originalMessage, final SignedMessage signedMessage) {
        super(async, player, viewers, renderer, message, originalMessage, signedMessage);
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
