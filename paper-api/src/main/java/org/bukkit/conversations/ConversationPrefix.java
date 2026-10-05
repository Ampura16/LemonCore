package org.bukkit.conversations;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * ConversationPrefix实现在对话的所有输出前添加前缀.
 * ConversationPrefix可用于显示插件名称或随对话进展显示对话状态.
 * <p>原文：
 * A ConversationPrefix implementation prepends all output from the
 * conversation to the player. The ConversationPrefix can be used to display
 * the plugin name or conversation status as the conversation evolves.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public interface ConversationPrefix {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取发送给玩家的每条消息前使用的前缀.
     * <p>原文：
     * Gets the prefix to use before each message to the player.
     *
     * @param context 对话的上下文信息.
     * <p>原文：Context information about the conversation.
     * @return 前缀文本.
     * <p>原文：The prefix text.
     */
    @NotNull
    String getPrefix(@NotNull ConversationContext context);
}
