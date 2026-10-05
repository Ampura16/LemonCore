package org.bukkit.conversations;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * ManuallyAbandonedConversationCanceller 仅用作 {@link ConversationAbandonedEvent} 的一部分，
 * 以指示对话是通过编程方式调用其 abandon() 方法手动放弃的。
 * <p>原文：
 * The ManuallyAbandonedConversationCanceller is only used as part of a {@link
 * ConversationAbandonedEvent} to indicate that the conversation was manually
 * abandoned by programmatically calling the abandon() method on it.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public class ManuallyAbandonedConversationCanceller implements ConversationCanceller {
    @Override
    public void setConversation(@NotNull Conversation conversation) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean cancelBasedOnInput(@NotNull ConversationContext context, @NotNull String input) {
        throw new UnsupportedOperationException();
    }

    @Override
    @NotNull
    public ConversationCanceller clone() {
        throw new UnsupportedOperationException();
    }
}
