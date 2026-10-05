package org.bukkit.conversations;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * MessagePrompt 是任何仅向用户显示消息且不需要输入的提示的基类。
 * <p>原文：
 * MessagePrompt is the base class for any prompt that only displays a message
 * to the user and requires no input.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public abstract class MessagePrompt implements Prompt {

    public MessagePrompt() {
        super();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 消息提示在继续之前从不等待用户输入。
     * <p>原文：
     * Message prompts never wait for user input before continuing.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @return 始终返回 false。
     * <p>原文：Always false.
     */
    @Override
    public boolean blocksForInput(@NotNull ConversationContext context) {
        return false;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 接受并忽略任何用户输入，返回提示图中的下一个提示。
     * <p>原文：
     * Accepts and ignores any user input, returning the next prompt in the
     * prompt graph instead.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @param input 被忽略。
     * <p>原文：Ignored.
     * @return 提示图中的下一个提示。
     * <p>原文：The next prompt in the prompt graph.
     */
    @Override
    @Nullable
    public Prompt acceptInput(@NotNull ConversationContext context, @Nullable String input) {
        return getNextPrompt(context);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 覆盖此方法以返回提示图中的下一个提示。
     * <p>原文：
     * Override this method to return the next prompt in the prompt graph.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @return 提示图中的下一个提示。
     * <p>原文：The next prompt in the prompt graph.
     */
    @Nullable
    protected abstract Prompt getNextPrompt(@NotNull ConversationContext context);
}
