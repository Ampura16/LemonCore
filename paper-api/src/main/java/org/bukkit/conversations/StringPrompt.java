package org.bukkit.conversations;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * StringPrompt是所有接受用户任意字符串输入的提示的基类.
 * <p>原文：
 * StringPrompt is the base class for any prompt that accepts an arbitrary
 * string from the user.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public abstract class StringPrompt implements Prompt {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 确保提示等待用户提供输入.
     * <p>原文：
     * Ensures that the prompt waits for the user to provide input.
     *
     * @param context 对话的上下文信息.
     * <p>原文：Context information about the conversation.
     * @return True.
     */
    @Override
    public boolean blocksForInput(@NotNull ConversationContext context) {
        return true;
    }
}
