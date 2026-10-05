package org.bukkit.conversations;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * Prompt 是 {@link Conversation} 的主要组成部分。每个提示向用户显示文本，并可选择等待用户的响应。
 * 提示链接成一个有向图，表示对话流程。要停止对话，返回 END_OF_CONVERSATION 代替另一个 Prompt 对象。
 * <p>原文：
 * A Prompt is the main constituent of a {@link Conversation}. Each prompt
 * displays text to the user and optionally waits for a user's response.
 * Prompts are chained together into a directed graph that represents the
 * conversation flow. To halt a conversation, END_OF_CONVERSATION is returned
 * in liu of another Prompt object.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public interface Prompt extends Cloneable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用于指示对话结束的便捷常量。
     * <p>原文：
     * A convenience constant for indicating the end of a conversation.
     */
    static final Prompt END_OF_CONVERSATION = null;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取首次呈现此提示时向用户显示的文本。
     * <p>原文：
     * Gets the text to display to the user when this prompt is first
     * presented.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @return 要显示的文本。
     * <p>原文：The text to display.
     */
    @NotNull
    String getPromptText(@NotNull ConversationContext context);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此提示实现是否应等待用户输入或立即显示下一个提示。
     * <p>原文：
     * Checks to see if this prompt implementation should wait for user input
     * or immediately display the next prompt.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @return 如果为 true，{@link Conversation} 将在继续之前等待输入。如果为 false，{@link #acceptInput(ConversationContext, String)} 将立即以 {@code null} 输入调用。
     * <p>原文：If true, the {@link Conversation} will wait for input before
     *     continuing. If false, {@link #acceptInput(ConversationContext, String)} will be called immediately with {@code null} input.
     */
    boolean blocksForInput(@NotNull ConversationContext context);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 接受并处理来自用户的输入。使用输入，返回提示图中的下一个 Prompt。
     * <p>原文：
     * Accepts and processes input from the user. Using the input, the next
     * Prompt in the prompt graph is returned.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @param input 来自用户的输入文本。
     * <p>原文：The input text from the user.
     * @return 提示图中的下一个 Prompt。
     * <p>原文：The next Prompt in the prompt graph.
     */
    @Nullable
    Prompt acceptInput(@NotNull ConversationContext context, @Nullable String input);
}
