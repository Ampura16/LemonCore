package org.bukkit.conversations;

import com.google.common.collect.ImmutableSet;
import java.util.Locale;
import java.util.Set;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * BooleanPrompt是那些需要用户判断操作是与否的提示的基类.
 * <p>原文：
 * BooleanPrompt is the base class for any prompt that requires a boolean
 * response from the user.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public abstract class BooleanPrompt extends ValidatingPrompt {

    private static final Set<String> TRUE_INPUTS = ImmutableSet.of("true", "on", "yes", "y", "1", "right", "correct", "valid");
    private static final Set<String> FALSE_INPUTS = ImmutableSet.of("false", "off", "no", "n", "0", "wrong", "incorrect", "invalid");
    private static final Set<String> VALID_INPUTS = ImmutableSet.<String>builder().addAll(TRUE_INPUTS).addAll(FALSE_INPUTS).build();

    public BooleanPrompt() {
        super();
    }

    @Override
    protected boolean isInputValid(@NotNull ConversationContext context, @NotNull String input) {
        return VALID_INPUTS.contains(input.toLowerCase(Locale.ROOT));
    }

    @Nullable
    @Override
    protected Prompt acceptValidatedInput(@NotNull ConversationContext context, @NotNull String input) {
        return acceptValidatedInput(context, TRUE_INPUTS.contains(input.toLowerCase(Locale.ROOT)));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 您可以重写这个方法，根据用户对提示的响应来执行对应的操作.
     * <p>原文：
     * Override this method to perform some action with the user's boolean
     * response.
     *
     * @param context 关于这个会话的Context 信息
     * <p>原文：Context information about the conversation.
     * @param input 用户响应的布尔值(true/false)
     * <p>原文：The user's boolean response.
     * @return 对话流程中的下一个 {@link Prompt}
     * <p>原文：The next {@link Prompt} in the prompt graph.
     */
    @Nullable
    protected abstract Prompt acceptValidatedInput(@NotNull ConversationContext context, boolean input);
}
