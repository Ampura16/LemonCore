package org.bukkit.event.server;

import java.util.Set;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.command.CommandSender;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * Event triggered for server broadcast messages such as from
 * {@link org.bukkit.Server#broadcast(Component)}.
 * <p>
 * This event behaves similarly to {@link io.papermc.paper.event.player.AsyncChatEvent} in that it
 * should be async if fired from an async thread. Please see that event for
 * further information.
 */
public class BroadcastMessageEvent extends ServerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final Set<CommandSender> recipients;
    private Component message;

    private boolean cancelled;

    @ApiStatus.Internal
    @Deprecated(since = "1.14", forRemoval = true)
    public BroadcastMessageEvent(@NotNull String message, @NotNull Set<CommandSender> recipients) {
        this(false, message, recipients);
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public BroadcastMessageEvent(boolean isAsync, @NotNull String message, @NotNull Set<CommandSender> recipients) {
        super(isAsync);
        this.message = LegacyComponentSerializer.legacySection().deserialize(message);
        this.recipients = recipients;
    }

    @ApiStatus.Internal
    @Deprecated(forRemoval = true)
    public BroadcastMessageEvent(@NotNull Component message, @NotNull Set<CommandSender> recipients) {
        this(false, message, recipients);
    }

    @ApiStatus.Internal
    public BroadcastMessageEvent(boolean isAsync, @NotNull Component message, @NotNull Set<CommandSender> recipients) {
        super(isAsync);
        this.message = message;
        this.recipients = recipients;
    }

    /**
     * Get the broadcast message.
     *
     * @return Message to broadcast
     */
    public @NotNull Component message() {
        return this.message;
    }

    /**
     * Set the broadcast message.
     *
     * @param message New message to broadcast
     */
    public void message(@NotNull Component message) {
        this.message = message;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取将广播的消息.
     * <p>原文：
     * Get the message to broadcast.
     *
     * @return 广播的消息
     * <p>原文：Message to broadcast
     * @deprecated in favour of {@link #message()}
     */
    @NotNull
    @Deprecated
    public String getMessage() {
        return LegacyComponentSerializer.legacySection().serialize(this.message);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置将广播的消息.
     * <p>原文：
     * Set the message to broadcast.
     *
     * @param message 广播的消息
     * <p>原文：New message to broadcast
     * @deprecated in favour of {@link #message(Component)}
     */
    @Deprecated // Paper
    public void setMessage(@NotNull String message) {
        this.message = LegacyComponentSerializer.legacySection().deserialize(message);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个将显示这条消息的接收者的集合.
     * <p>
     * 本方法返回的集合不保证可以改变和访问时可能自动填充.
     * 任何访问这个集合的监听器都应注意对于一个 lazy set 的实现可能会降低性能.
     * <p>
     * 监听器应注意到如果事件传唤者提供了一个不可修改的Set集合的话修改这个列表可能会抛出{@link
     * UnsupportedOperationException}异常.
     * <p>原文：
     * Gets a set of recipients that this chat message will be displayed to.
     * <p>
     * The set returned is not guaranteed to be mutable and may auto-populate
     * on access. Any listener accessing the returned set should be aware that
     * it may reduce performance for a lazy set implementation.
     * <p>
     * Listeners should be aware that modifying the list may throw {@link
     * UnsupportedOperationException} if the event caller provides an
     * unmodifiable set.
     *
     * @return 所有会看到这条消息的接收者
     * <p>原文：All CommandSenders who will see this chat message
     */
    @NotNull
    public Set<CommandSender> getRecipients() {
        return this.recipients;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    @NotNull
    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
