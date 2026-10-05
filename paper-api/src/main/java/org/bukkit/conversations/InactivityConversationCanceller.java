package org.bukkit.conversations;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * InactivityConversationCanceller 会在用户一段时间不活动后取消 {@link Conversation}。
 * <p>原文：
 * An InactivityConversationCanceller will cancel a {@link Conversation} after
 * a period of inactivity by the user.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public class InactivityConversationCanceller implements ConversationCanceller {
    protected Plugin plugin;
    protected int timeoutSeconds;
    protected Conversation conversation;
    private int taskId = -1;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个 InactivityConversationCanceller。
     * <p>原文：
     * Creates an InactivityConversationCanceller.
     *
     * @param plugin 拥有此取消器的插件。
     * <p>原文：The owning plugin.
     * @param timeoutSeconds 等待不活动的秒数。
     * <p>原文：The number of seconds of inactivity to wait.
     */
    public InactivityConversationCanceller(@NotNull Plugin plugin, int timeoutSeconds) {
        this.plugin = plugin;
        this.timeoutSeconds = timeoutSeconds;
    }

    @Override
    public void setConversation(@NotNull Conversation conversation) {
        this.conversation = conversation;
        startTimer();
    }

    @Override
    public boolean cancelBasedOnInput(@NotNull ConversationContext context, @NotNull String input) {
        // Reset the inactivity timer
        stopTimer();
        startTimer();
        return false;
    }

    @Override
    @NotNull
    public ConversationCanceller clone() {
        return new InactivityConversationCanceller(plugin, timeoutSeconds);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 启动不活动计时器。
     * <p>原文：
     * Starts an inactivity timer.
     */
    private void startTimer() {
        taskId = plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (conversation.getState() == Conversation.ConversationState.UNSTARTED) {
                    startTimer();
                } else if (conversation.getState() == Conversation.ConversationState.STARTED) {
                    cancelling(conversation);
                    conversation.abandon(new ConversationAbandonedEvent(conversation, InactivityConversationCanceller.this));
                }
            }
        }, timeoutSeconds * 20);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 停止活动的不活动计时器。
     * <p>原文：
     * Stops the active inactivity timer.
     */
    private void stopTimer() {
        if (taskId != -1) {
            plugin.getServer().getScheduler().cancelTask(taskId);
            taskId = -1;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * InactivityConversationCanceller 的子类可以覆盖此方法，以在不活动计时器放弃对话时采取额外操作。
     * <p>原文：
     * Subclasses of InactivityConversationCanceller can override this method
     * to take additional actions when the inactivity timer abandons the
     * conversation.
     *
     * @param conversation 正在被放弃的对话。
     * <p>原文：The conversation being abandoned.
     */
    protected void cancelling(@NotNull Conversation conversation) {

    }
}
