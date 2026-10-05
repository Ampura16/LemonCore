package org.bukkit.conversations;

import java.util.EventListener;
import org.jetbrains.annotations.NotNull;

/**
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public interface ConversationAbandonedListener extends EventListener {
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 当一个{@link Conversation}被终止时调用此方法.
     * <p>原文：
     * Called whenever a {@link Conversation} is abandoned.
     *
     * @param abandonedEvent 包含关于会话被终止的细节的事件对象
     * <p>原文：Contains details about the abandoned
     *     conversation.
     */
    public void conversationAbandoned(@NotNull ConversationAbandonedEvent abandonedEvent);
}
