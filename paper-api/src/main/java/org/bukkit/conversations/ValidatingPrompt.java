package org.bukkit.conversations;

import org.bukkit.ChatColor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * ValidatingPrompt 是任何需要验证的提示的基类。ValidatingPrompt 将不断重放提示文本，直到用户输入有效的响应。
 * <p>原文：
 * ValidatingPrompt is the base class for any prompt that requires validation.
 * ValidatingPrompt will keep replaying the prompt text until the user enters
 * a valid response.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public abstract class ValidatingPrompt implements Prompt {
    public ValidatingPrompt() {
        super();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 接受并处理来自用户的输入并进行验证。如果验证失败，返回此提示以重新执行，否则返回提示图中的下一个 Prompt。
     * <p>原文：
     * Accepts and processes input from the user and validates it. If
     * validation fails, this prompt is returned for re-execution, otherwise
     * the next Prompt in the prompt graph is returned.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @param input 来自用户的输入文本。
     * <p>原文：The input text from the user.
     * @return 此提示或提示图中的下一个 Prompt。
     * <p>原文：This prompt or the next Prompt in the prompt graph.
     */
    @Override
    @Nullable
    public Prompt acceptInput(@NotNull ConversationContext context, @Nullable String input) {
        if (isInputValid(context, input)) {
            return acceptValidatedInput(context, input);
        } else {
            String failPrompt = getFailedValidationText(context, input);
            if (failPrompt != null) {
                context.getForWhom().sendRawMessage(ChatColor.RED + failPrompt);
            }
            // Redisplay this prompt to the user to re-collect input
            return this;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 确保提示等待用户提供输入。
     * <p>原文：
     * Ensures that the prompt waits for the user to provide input.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @return True.
     */
    @Override
    public boolean blocksForInput(@NotNull ConversationContext context) {
        return true;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 覆盖此方法以检查玩家输入的有效性。
     * <p>原文：
     * Override this method to check the validity of the player's input.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @param input 玩家的原始控制台输入。
     * <p>原文：The player's raw console input.
     * @return 根据输入的有效性返回 true 或 false。
     * <p>原文：True or false depending on the validity of the input.
     */
    protected abstract boolean isInputValid(@NotNull ConversationContext context, @NotNull String input);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 覆盖此方法以接受并处理来自用户的验证输入。使用输入，应返回提示图中的下一个 Prompt。
     * <p>原文：
     * Override this method to accept and processes the validated input from
     * the user. Using the input, the next Prompt in the prompt graph should
     * be returned.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @param input 来自用户的验证输入文本。
     * <p>原文：The validated input text from the user.
     * @return 提示图中的下一个 Prompt。
     * <p>原文：The next Prompt in the prompt graph.
     */
    @Nullable
    protected abstract Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 可选择覆盖此方法以在用户输入无效输入时显示额外消息。
     * <p>原文：
     * Optionally override this method to display an additional message if the
     * user enters an invalid input.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @param invalidInput 用户提供的无效输入。
     * <p>原文：The invalid input provided by the user.
     * @return 解释如何纠正输入的消息。
     * <p>原文：A message explaining how to correct the input.
     */
    @Nullable
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull String invalidInput) {
        return null;
    }
}
