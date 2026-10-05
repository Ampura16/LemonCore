package org.bukkit.conversations;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * NullConversationPrefix是一个{@link ConversationPrefix}实现, 不在对话输出前显示任何内容.
 * <p>原文：
 * NullConversationPrefix is a {@link ConversationPrefix} implementation that
 * displays nothing in front of conversation output.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public class NullConversationPrefix implements ConversationPrefix {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在每条对话消息前添加空字符串.
     * <p>原文：
     * Prepends each conversation message with an empty string.
     *
     * @param context 对话的上下文信息.
     * <p>原文：Context information about the conversation.
     * @return 空字符串.
     * <p>原文：An empty string.
     */
    @Override
    @NotNull
    public String getPrefix(@NotNull ConversationContext context) {
        return "";
    }
}
