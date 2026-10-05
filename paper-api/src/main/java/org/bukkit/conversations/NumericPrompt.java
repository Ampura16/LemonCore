package org.bukkit.conversations;

import org.apache.commons.lang3.math.NumberUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * NumericPrompt 是任何需要用户 {@link Number} 响应的提示的基类。
 * <p>原文：
 * NumericPrompt is the base class for any prompt that requires a {@link
 * Number} response from the user.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public abstract class NumericPrompt extends ValidatingPrompt {
    public NumericPrompt() {
        super();
    }

    @Override
    protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
        return NumberUtils.isNumber(input) && isNumberValid(context, NumberUtils.createNumber(input));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 覆盖此方法以在输入被确定为数字后对数字玩家输入进行进一步验证。
     * <p>原文：
     * Override this method to do further validation on the numeric player
     * input after the input has been determined to actually be a number.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @param input 玩家提供的数字。
     * <p>原文：The number the player provided.
     * @return 玩家输入的有效性。
     * <p>原文：The validity of the player's input.
     */
    protected boolean isNumberValid(@NotNull ConversationContext context, @NotNull Number input) {
        return true;
    }

    @Nullable
    @Override
    protected Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
        try {
            return acceptValidatedInput(context, NumberUtils.createNumber(input));
        } catch (NumberFormatException e) {
            return acceptValidatedInput(context, NumberUtils.INTEGER_ZERO);
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 覆盖此方法以对用户的整数响应执行某些操作。
     * <p>原文：
     * Override this method to perform some action with the user's integer
     * response.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @param input 用户的响应作为 {@link Number}。
     * <p>原文：The user's response as a {@link Number}.
     * @return 提示图中的下一个 {@link Prompt}。
     * <p>原文：The next {@link Prompt} in the prompt graph.
     */
    @Nullable
    protected abstract Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull Number input);

    @Nullable
    @Override
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull String invalidInput) {
        if (NumberUtils.isNumber(invalidInput)) {
            return getFailedValidationText(context, NumberUtils.createNumber(invalidInput));
        } else {
            return getInputNotNumericText(context, invalidInput);
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 可选择覆盖此方法以在用户输入无效数字时显示额外消息。
     * <p>原文：
     * Optionally override this method to display an additional message if the
     * user enters an invalid number.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @param invalidInput 用户提供的无效输入。
     * <p>原文：The invalid input provided by the user.
     * @return 解释如何纠正输入的消息。
     * <p>原文：A message explaining how to correct the input.
     */
    @Nullable
    protected String getInputNotNumericText(@NotNull ConversationContext context, @NotNull String invalidInput) {
        return null;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 可选择覆盖此方法以在用户输入无效数字输入时显示额外消息。
     * <p>原文：
     * Optionally override this method to display an additional message if the
     * user enters an invalid numeric input.
     *
     * @param context 关于对话的上下文信息。
     * <p>原文：Context information about the conversation.
     * @param invalidInput 用户提供的无效输入。
     * <p>原文：The invalid input provided by the user.
     * @return 解释如何纠正输入的消息。
     * <p>原文：A message explaining how to correct the input.
     */
    @Nullable
    protected String getFailedValidationText(@NotNull ConversationContext context, @NotNull Number invalidInput) {
        return null;
    }
}
