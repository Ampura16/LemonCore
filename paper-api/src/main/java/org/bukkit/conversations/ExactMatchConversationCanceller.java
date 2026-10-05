package org.bukkit.conversations;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * ExactMatchConversationCanceller 会在用户输入精确匹配的字符串时取消对话。
 * <p>原文：
 * An ExactMatchConversationCanceller cancels a conversation if the user
 * enters an exact input string
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public class ExactMatchConversationCanceller implements ConversationCanceller {
    private String escapeSequence;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 构建一个 ExactMatchConversationCanceller。
     * <p>原文：
     * Builds an ExactMatchConversationCanceller.
     *
     * @param escapeSequence 如果用户输入将取消对话的字符串。
     * <p>原文：The string that, if entered by the user, will
     *     cancel the conversation.
     */
    public ExactMatchConversationCanceller(@NotNull String escapeSequence) {
        this.escapeSequence = escapeSequence;
    }

    @Override
    public void setConversation(@NotNull Conversation conversation) {}

    @Override
    public boolean cancelBasedOnInput(@NotNull ConversationContext context, @NotNull String input) {
        return input.equals(escapeSequence);
    }

    @Override
    @NotNull
    public ConversationCanceller clone() {
        return new ExactMatchConversationCanceller(escapeSequence);
    }
}
