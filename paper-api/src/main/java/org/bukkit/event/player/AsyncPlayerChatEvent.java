package org.bukkit.event.player;

import java.util.IllegalFormatException;
import java.util.Set;
import com.google.common.base.Preconditions;
import org.bukkit.Warning;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当玩家聊天时触发这个事件.
 * <p>
 * 构造器提供了一个表示事件同步触发还是异步触发的布尔值。
 * 当异步时，本事件可以被任何线程调用，无主线程，访问API受限。
 * <p>
 * 如果玩家通过传入的聊天数据包导致触发本事件，本事件将是异步的。
 * 如果一个插件通过迫使玩家聊天（比如<code>Player.chat</code>）而触发本事件，本事件将是同步的。
 * <p>
 * 应注意通过{@link #isAsynchronous()}检查本事件是同步的还是异步的，适当地处理本事件。
 * <p>原文：
 * This event will sometimes fire synchronously, depending on how it was
 * triggered.
 * <p>
 * The constructor provides a boolean to indicate if the event was fired
 * synchronously or asynchronously. When asynchronous, this event can be
 * called from any thread, sans the main thread, and has limited access to the
 * API.
 * <p>
 * If a player is the direct cause of this event by an incoming packet, this
 * event will be asynchronous. If a plugin triggers this event by compelling a
 * player to chat, this event will be synchronous.
 * <p>
 * Care should be taken to check {@link #isAsynchronous()} and treat the event
 * appropriately.
 *
 * @deprecated use {@link io.papermc.paper.event.player.AsyncChatEvent} instead
 */
@Deprecated
@Warning(reason = "Don't nag on old event yet") // Paper
public class AsyncPlayerChatEvent extends PlayerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private String message;
    private String format = "<%1$s> %2$s";
    private final Set<Player> recipients;

    private boolean cancelled;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @param async 设置该事件为同步事件
     * @param message 发送的信息
     * @param players 消息发送者
     */
    @ApiStatus.Internal
    public AsyncPlayerChatEvent(final boolean async, @NotNull final Player player, @NotNull final String message, @NotNull final Set<Player> players) {
        super(player, async);
        this.message = message;
        this.recipients = players;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得这个玩家试图发送的信息。这个消息
     * 将以{@link #getFormat()}的格式被获取。
     * <p>原文：
     * Gets the message that the player is attempting to send. This message
     * will be used with {@link #getFormat()}.
     *
     * @return 这个玩家试图发送的信息
     * <p>原文：Message the player is attempting to send
     */
    @NotNull
    public String getMessage() {
        return this.message;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个玩家将发送的信息。这个消息
     * 将以{@link #getFormat()}的格式被设置。
     * <p>原文：
     * Sets the message that the player will send. This message will be used
     * with {@link #getFormat()}.
     *
     * @param message 设置的这个玩家将发送的新消息
     * <p>原文：New message that the player will send
     */
    public void setMessage(@NotNull String message) {
        this.message = message;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于展示这条字符消息的格式。
     * <p>
     * 这个事件执行完毕后，获取的格式的第一个部分是
     * {@link Player#getDisplayName()} 第二个部分是 {@link#getMessage()}
     * <p>原文：
     * Gets the format to use to display this chat message.
     * <p>
     * When this event finishes execution, the first format parameter is the
     * {@link Player#getDisplayName()} and the second parameter is {@link
     * #getMessage()}
     *
     * @return 输出格式：{@link String#format(String, Object...)}
     * <p>原文：{@link String#format(String, Object...)} compatible format
     *     string
     */
    @NotNull
    public String getFormat() {
        return this.format;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置用于展示这条字符消息的格式。
     * <p>
     * 这个事件执行完毕后，设置的格式的第一个部分是
     * {@link Player#getDisplayName()} 第二个部分是 {@link#getMessage()}
     * <p>原文：
     * Sets the format to use to display this chat message.
     * <p>
     * When this event finishes execution, the first format parameter is the
     * {@link Player#getDisplayName()} and the second parameter is {@link
     * #getMessage()}
     *
     * @param format 输出格式：{@link String#format(String, Object...)}
     * <p>原文：{@link String#format(String, Object...)} compatible
     *     format string
     * @throws IllegalFormatException 底层API抛出错误
     * <p>原文：if the underlying API throws the
     *     exception
     * @see String#format(String, Object...)
     */
    public void setFormat(@NotNull final String format) throws IllegalFormatException, NullPointerException {
        Preconditions.checkArgument(format != null, "format cannot be null");
        // Oh for a better way to do this!
        try {
            String.format(format, this.player, this.message);
        } catch (RuntimeException ex) {
            ex.fillInStackTrace();
            throw ex;
        }

        this.format = format;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取一个将看到这条消息的玩家的集合。
     * <p>
     * 本方法返回的集合不保证可以改变和访问时可能自动填充。
     * 任何监听器访问这个返回的集合应该知道对于一个lazy set的实现可能会降低性能.
     * <p>
     * 监听器应注意到如果事件传唤者提供了一个不可修改的Set集合的话修改这个列表可能会抛出{@link
     * UnsupportedOperationException}异常。
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
     * @return 所有会看到这个消息的玩家
     * <p>原文：All Players who will see this chat message
     */
    @NotNull
    public Set<Player> getRecipients() {
        return this.recipients;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
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
