package org.bukkit.conversations;

import java.util.EventObject;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * ConversationAbandonedEvent包含一个被终止的会话的细节信息.
 * <p>原文：
 * ConversationAbandonedEvent contains information about an abandoned
 * conversation.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public class ConversationAbandonedEvent extends EventObject {

    private ConversationContext context;
    private ConversationCanceller canceller;

    public ConversationAbandonedEvent(@NotNull Conversation conversation) {
        this(conversation, null);
    }

    public ConversationAbandonedEvent(@NotNull Conversation conversation, @Nullable ConversationCanceller canceller) {
        super(conversation);
        this.context = conversation.getContext();
        this.canceller = canceller;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取终止该会话的{@link ConversationCanceller}.
     * <p>原文：
     * Gets the object that caused the conversation to be abandoned.
     *
     * @return 终止该会话的{@link ConversationCanceller}
     * <p>原文：The object that abandoned the conversation.
     */
    @Nullable
    public ConversationCanceller getCanceller() {
        return canceller;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个被终止的会话的上下文.
     * <p>原文：
     * Gets the abandoned conversation's conversation context.
     *
     * @return 被终止的对话的上下文
     * <p>原文：The abandoned conversation's conversation context.
     */
    @NotNull
    public ConversationContext getContext() {
        return context;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 指示该对话是怎样被终止的 - 在对话提示流程完成后自然终止
     * 还是较早地通过{@link ConversationCanceller}终止.
     * <p>
     * Indicates how the conversation was abandoned - naturally as part of the
     * prompt chain or prematurely via a {@link ConversationCanceller}.
     * <p>原文：
     * Indicates how the conversation was abandoned - naturally as part of the
     * prompt chain or prematurely via a {@link ConversationCanceller}.
     *
     * @return 若会话是由于{@link Prompt}返回了空值或下一个prompt被优雅地终止返回true;
     * 若会话是被ConversationCanceller终止的返回false.
     * <p>原文：True if the conversation is abandoned gracefully by a {@link
     *     Prompt} returning null or the next prompt. False of the
     *     conversations is abandoned prematurely by a ConversationCanceller.
     */
    public boolean gracefulExit() {
        return canceller == null;
    }
}
