package org.bukkit.conversations;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * Conversation 类负责跟踪对话的当前状态，向用户显示提示，并将用户的响应分派到适当的位置。
 * Conversation 对象通常不会直接实例化，而是使用 {@link ConversationFactory} 按需构建相同的对话。
 * <p>
 * 对话流程由 {@link Prompt} 对象的有向图组成。每次提示从用户获取输入时，它必须返回图中的下一个提示。
 * 由于每个 Prompt 选择下一个 Prompt，因此可以实现复杂的对话树，其中玩家响应的性质引导对话的流程。
 * <p>
 * 每个对话都有一个 {@link ConversationPrefix}，它将对话的所有输出前置到玩家。
 * ConversationPrefix 可用于在对话演变时显示插件名称或对话状态。
 * <p>
 * 每个对话都有一个超时时间，以等待放弃对话前的非活动秒数衡量。
 * 如果达到非活动超时，对话将被放弃，用户的传入和传出聊天将恢复正常。
 * <p>
 * 不应手动构造对话，而应使用 {@link ConversationFactory} 来访问所有可用选项。
 * <p>原文：
 * The Conversation class is responsible for tracking the current state of a
 * conversation, displaying prompts to the user, and dispatching the user's
 * response to the appropriate place. Conversation objects are not typically
 * instantiated directly. Instead a {@link ConversationFactory} is used to
 * construct identical conversations on demand.
 * <p>
 * Conversation flow consists of a directed graph of {@link Prompt} objects.
 * Each time a prompt gets input from the user, it must return the next prompt
 * in the graph. Since each Prompt chooses the next Prompt, complex
 * conversation trees can be implemented where the nature of the player's
 * response directs the flow of the conversation.
 * <p>
 * Each conversation has a {@link ConversationPrefix} that prepends all output
 * from the conversation to the player. The ConversationPrefix can be used to
 * display the plugin name or conversation status as the conversation evolves.
 * <p>
 * Each conversation has a timeout measured in the number of inactive seconds
 * to wait before abandoning the conversation. If the inactivity timeout is
 * reached, the conversation is abandoned and the user's incoming and outgoing
 * chat is returned to normal.
 * <p>
 * You should not construct a conversation manually. Instead, use the {@link
 * ConversationFactory} for access to all available options.
 *
 * @deprecated The conversation API has been deprecated for removal. This system does not support component based messages
 * and has been slowly losing functionality over the years as Minecraft has changed that this API can not adapt to.
 * It is recommended you instead manually listen to the {@link io.papermc.paper.event.player.AsyncChatEvent}
 * or alternatively using {@link io.papermc.paper.dialog.Dialog} to get user input.
 */
@Deprecated(forRemoval = true)
public class Conversation {

    private Prompt firstPrompt;
    private boolean abandoned;
    protected Prompt currentPrompt;
    protected ConversationContext context;
    protected boolean modal;
    protected boolean localEchoEnabled;
    protected ConversationPrefix prefix;
    protected List<ConversationCanceller> cancellers;
    protected List<ConversationAbandonedListener> abandonedListeners;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 初始化一个新的 Conversation。
     * <p>原文：
     * Initializes a new Conversation.
     *
     * @param plugin 拥有此对话的插件。
     * <p>原文：The plugin that owns this conversation.
     * @param forWhom 此对话所中介的实体。
     * <p>原文：The entity for whom this conversation is mediating.
     * @param firstPrompt 对话图中的第一个提示。
     * <p>原文：The first prompt in the conversation graph.
     */
    public Conversation(@Nullable Plugin plugin, @NotNull Conversable forWhom, @Nullable Prompt firstPrompt) {
        this(plugin, forWhom, firstPrompt, new HashMap<Object, Object>());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 初始化一个新的 Conversation。
     * <p>原文：
     * Initializes a new Conversation.
     *
     * @param plugin 拥有此对话的插件。
     * <p>原文：The plugin that owns this conversation.
     * @param forWhom 此对话所中介的实体。
     * <p>原文：The entity for whom this conversation is mediating.
     * @param firstPrompt 对话图中的第一个提示。
     * <p>原文：The first prompt in the conversation graph.
     * @param initialSessionData 放入对话上下文 sessionData 映射中的任何初始值。
     * <p>原文：Any initial values to put in the conversation
     *     context sessionData map.
     */
    public Conversation(@Nullable Plugin plugin, @NotNull Conversable forWhom, @Nullable Prompt firstPrompt, @NotNull Map<Object, Object> initialSessionData) {
        this.firstPrompt = firstPrompt;
        this.context = new ConversationContext(plugin, forWhom, initialSessionData);
        this.modal = true;
        this.localEchoEnabled = true;
        this.prefix = new NullConversationPrefix();
        this.cancellers = new ArrayList<ConversationCanceller>();
        this.abandonedListeners = new ArrayList<ConversationAbandonedListener>();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此对话所中介的实体。
     * <p>原文：
     * Gets the entity for whom this conversation is mediating.
     *
     * @return 实体。
     * <p>原文：The entity.
     */
    @NotNull
    public Conversable getForWhom() {
        return context.getForWhom();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此对话的模态性。如果对话是模态的，则在对话期间抑制所有指向玩家的消息。
     * <p>原文：
     * Gets the modality of this conversation. If a conversation is modal, all
     * messages directed to the player are suppressed for the duration of the
     * conversation.
     *
     * @return 对话的模态性。
     * <p>原文：The conversation modality.
     */
    public boolean isModal() {
        return modal;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此对话的模态性。如果对话是模态的，则在对话期间抑制所有指向玩家的消息。
     * <p>原文：
     * Sets the modality of this conversation.  If a conversation is modal,
     * all messages directed to the player are suppressed for the duration of
     * the conversation.
     *
     * @param modal 新的对话模态性。
     * <p>原文：The new conversation modality.
     */
    void setModal(boolean modal) {
        this.modal = modal;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此对话的本地回显状态。如果启用了本地回显，则提交到对话的任何文本都会被回显到提交者的聊天窗口中。
     * <p>原文：
     * Gets the status of local echo for this conversation. If local echo is
     * enabled, any text submitted to a conversation gets echoed back into the
     * submitter's chat window.
     *
     * @return 本地回显的状态。
     * <p>原文：The status of local echo.
     */
    public boolean isLocalEchoEnabled() {
        return localEchoEnabled;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此对话的本地回显状态。如果启用了本地回显，则提交到对话的任何文本都会被回显到提交者的聊天窗口中。
     * <p>原文：
     * Sets the status of local echo for this conversation. If local echo is
     * enabled, any text submitted to a conversation gets echoed back into the
     * submitter's chat window.
     *
     * @param localEchoEnabled 本地回显的状态。
     * <p>原文：The status of local echo.
     */
    public void setLocalEchoEnabled(boolean localEchoEnabled) {
        this.localEchoEnabled = localEchoEnabled;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此前置所有对话输出的 {@link ConversationPrefix}。
     * <p>原文：
     * Gets the {@link ConversationPrefix} that prepends all output from this
     * conversation.
     *
     * @return 正在使用的 ConversationPrefix。
     * <p>原文：The ConversationPrefix in use.
     */
    @NotNull
    public ConversationPrefix getPrefix() {
        return prefix;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此前置所有对话输出的 {@link ConversationPrefix}。
     * <p>原文：
     * Sets the {@link ConversationPrefix} that prepends all output from this
     * conversation.
     *
     * @param prefix 要使用的 ConversationPrefix。
     * <p>原文：The ConversationPrefix to use.
     */
    void setPrefix(@NotNull ConversationPrefix prefix) {
        this.prefix = prefix;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将 {@link ConversationCanceller} 添加到取消器集合中。
     * <p>原文：
     * Adds a {@link ConversationCanceller} to the cancellers collection.
     *
     * @param canceller 要添加的 {@link ConversationCanceller}。
     * <p>原文：The {@link ConversationCanceller} to add.
     */
    void addConversationCanceller(@NotNull ConversationCanceller canceller) {
        canceller.setConversation(this);
        this.cancellers.add(canceller);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 {@link ConversationCanceller} 的列表。
     * <p>原文：
     * Gets the list of {@link ConversationCanceller}s
     *
     * @return 列表。
     * <p>原文：The list.
     */
    @NotNull
    public List<ConversationCanceller> getCancellers() {
        return cancellers;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回 Conversation 的 {@link ConversationContext}。
     * <p>原文：
     * Returns the Conversation's {@link ConversationContext}.
     *
     * @return The ConversationContext.
     */
    @NotNull
    public ConversationContext getContext() {
        return context;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 显示此对话的第一个提示，并开始重定向用户的聊天响应。
     * <p>原文：
     * Displays the first prompt of this conversation and begins redirecting
     * the user's chat responses.
     */
    public void begin() {
        if (currentPrompt == null) {
            abandoned = false;
            currentPrompt = firstPrompt;
            context.getForWhom().beginConversation(this);
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回对话的当前状态。
     * <p>原文：
     * {@return the current state of the conversation}
     *
     * @return 对话的当前状态。
     */
    @NotNull
    public ConversationState getState() {
        if (currentPrompt != null) {
            return ConversationState.STARTED;
        } else if (abandoned) {
            return ConversationState.ABANDONED;
        } else {
            return ConversationState.UNSTARTED;
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将玩家输入传递到当前提示。然后显示下一个提示（由当前提示确定）给用户。
     * <p>原文：
     * Passes player input into the current prompt. The next prompt (as
     * determined by the current prompt) is then displayed to the user.
     *
     * @param input 用户的聊天文本。
     * <p>原文：The user's chat text.
     */
    public void acceptInput(@NotNull String input) {
        if (currentPrompt != null) {

            // Echo the user's input
            if (localEchoEnabled) {
                context.getForWhom().sendRawMessage(prefix.getPrefix(context) + input);
            }

            // Test for conversation abandonment based on input
            for (ConversationCanceller canceller : cancellers) {
                if (canceller.cancelBasedOnInput(context, input)) {
                    abandon(new ConversationAbandonedEvent(this, canceller));
                    return;
                }
            }

            // Not abandoned, output the next prompt
            currentPrompt = currentPrompt.acceptInput(context, input);
            outputNextPrompt();
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加一个 {@link ConversationAbandonedListener}。
     * <p>原文：
     * Adds a {@link ConversationAbandonedListener}.
     *
     * @param listener 要添加的监听器。
     * <p>原文：The listener to add.
     */
    public synchronized void addConversationAbandonedListener(@NotNull ConversationAbandonedListener listener) {
        abandonedListeners.add(listener);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除一个 {@link ConversationAbandonedListener}。
     * <p>原文：
     * Removes a {@link ConversationAbandonedListener}.
     *
     * @param listener 要移除的监听器。
     * <p>原文：The listener to remove.
     */
    public synchronized void removeConversationAbandonedListener(@NotNull ConversationAbandonedListener listener) {
        abandonedListeners.remove(listener);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 放弃并重置当前对话。恢复用户正常的聊天行为。
     * <p>原文：
     * Abandons and resets the current conversation. Restores the user's
     * normal chat behavior.
     */
    public void abandon() {
        abandon(new ConversationAbandonedEvent(this, new ManuallyAbandonedConversationCanceller()));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 放弃并重置当前对话。恢复用户正常的聊天行为。
     * <p>原文：
     * Abandons and resets the current conversation. Restores the user's
     * normal chat behavior.
     *
     * @param details 关于对话被放弃原因的详细信息。
     * <p>原文：Details about why the conversation was abandoned
     */
    public synchronized void abandon(@NotNull ConversationAbandonedEvent details) {
        if (!abandoned) {
            abandoned = true;
            currentPrompt = null;
            context.getForWhom().abandonConversation(this);
            for (ConversationAbandonedListener listener : abandonedListeners) {
                listener.conversationAbandoned(details);
            }
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 显示下一个用户提示，如果下一个提示为 null 则放弃对话。
     * <p>原文：
     * Displays the next user prompt and abandons the conversation if the next
     * prompt is null.
     */
    public void outputNextPrompt() {
        if (currentPrompt == null) {
            abandon(new ConversationAbandonedEvent(this));
        } else {
            context.getForWhom().sendRawMessage(prefix.getPrefix(context) + currentPrompt.getPromptText(context));
            if (!currentPrompt.blocksForInput(context)) {
                currentPrompt = currentPrompt.acceptInput(context, null);
                outputNextPrompt();
            }
        }
    }

    public enum ConversationState {
        UNSTARTED,
        STARTED,
        ABANDONED
    }
}
