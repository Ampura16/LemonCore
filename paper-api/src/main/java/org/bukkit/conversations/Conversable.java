package org.bukkit.conversations;

import java.util.UUID;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * Conversable 接口用于表示可以进行对话的对象。
 * <p>原文：
 * The Conversable interface is used to indicate objects that can have
 * conversations.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public interface Conversable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试 Conversable 对象是否积极参与对话。
     * <p>原文：
     * Tests to see of a Conversable object is actively engaged in a
     * conversation.
     *
     * @return 如果对话正在进行中则返回 true。
     * <p>原文：True if a conversation is in progress
     */
    public boolean isConversing();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 接受输入到活动对话中。如果没有对话正在进行，此方法不执行任何操作。
     * <p>原文：
     * Accepts input into the active conversation. If no conversation is in
     * progress, this method does nothing.
     *
     * @param input 输入到对话中的消息。
     * <p>原文：The input message into the conversation
     */
    public void acceptConversationInput(@NotNull String input);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 与 Conversation 对象进行对话。
     * <p>原文：
     * Enters into a dialog with a Conversation object.
     *
     * @param conversation 要开始的对话。
     * <p>原文：The conversation to begin
     * @return 如果对话应继续则返回 true，如果已排队则返回 false。
     * <p>原文：True if the conversation should proceed, false if it has been
     *     enqueued
     */
    public boolean beginConversation(@NotNull Conversation conversation);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 放弃一个活动对话。
     * <p>原文：
     * Abandons an active conversation.
     *
     * @param conversation 要放弃的对话。
     * <p>原文：The conversation to abandon
     */
    public void abandonConversation(@NotNull Conversation conversation);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 放弃一个活动对话。
     * <p>原文：
     * Abandons an active conversation.
     *
     * @param conversation 要放弃的对话。
     * <p>原文：The conversation to abandon
     * @param details 关于对话被放弃原因的详细信息。
     * <p>原文：Details about why the conversation was abandoned
     */
    public void abandonConversation(@NotNull Conversation conversation, @NotNull ConversationAbandonedEvent details);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向此发送者发送原始消息。
     * <p>原文：
     * Sends this sender a message raw
     *
     * @param message 要显示的消息。
     * <p>原文：Message to be displayed
     */
    @org.jetbrains.annotations.ApiStatus.Obsolete // Paper
    public void sendRawMessage(@NotNull String message);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向此发送者发送原始消息。
     * <p>原文：
     * Sends this sender a message raw
     *
     * @param message 要显示的消息。
     * <p>原文：Message to be displayed
     * @param sender 此消息的发送者。
     * <p>原文：The sender of this message
     * @deprecated sender UUID is ignored
     */
    @Deprecated // Paper
    public void sendRawMessage(@Nullable UUID sender, @NotNull String message);
}
