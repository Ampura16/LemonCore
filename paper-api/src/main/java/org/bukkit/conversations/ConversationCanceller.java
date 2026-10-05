package org.bukkit.conversations;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * ConversationCanceller可以用来终止一个活跃的{@link
 * Conversation}. 一个Conversation可以有多个ConversationCanceller.
 * <p>原文：
 * A ConversationCanceller is a class that cancels an active {@link
 * Conversation}. A Conversation can have more than one ConversationCanceller.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public interface ConversationCanceller extends Cloneable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置本ConversationCanceller可以取消的会话.
     * <p>原文：
     * Sets the conversation this ConversationCanceller can optionally cancel.
     *
     * @param conversation 一个会话
     * <p>原文：A conversation.
     */
    public void setConversation(@NotNull Conversation conversation);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据用户的输入反馈取消一个会话.
     * <p>原文：
     * Cancels a conversation based on user input.
     *
     * @param context 关于会话的Context信息
     * <p>原文：Context information about the conversation.
     * @param input 用户的输入值
     * <p>原文：The input text from the user.
     * @return true取消该会话，false反之
     * <p>原文：True to cancel the conversation, False otherwise.
     */
    public boolean cancelBasedOnInput(@NotNull ConversationContext context, @NotNull String input);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许某一{@link ConversationFactory}在创建一个新{@link Conversation}时
     * 复制这个ConversationCanceller.
     * <p>
     * 实现这个方法应当重置一切内部的对象状态.
     * <p>原文：
     * Allows the {@link ConversationFactory} to duplicate this
     * ConversationCanceller when creating a new {@link Conversation}.
     * <p>
     * Implementing this method should reset any internal object state.
     *
     * @return 一个副本
     * <p>原文：A clone.
     */
    @NotNull
    public ConversationCanceller clone();
}
