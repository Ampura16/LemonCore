package io.papermc.paper.event.player;

import io.papermc.paper.chat.ChatRenderer;
import java.util.Set;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.chat.SignedMessage;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.player.PlayerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

import static java.util.Objects.requireNonNull;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 聊天事件的抽象实现，处理共享逻辑。
 * <p>原文：
 * An abstract implementation of a chat event, handling shared logic.
 */
@ApiStatus.NonExtendable
@NullMarked
public abstract class AbstractChatEvent extends PlayerEvent implements Cancellable {

    private final Set<Audience> viewers;
    private final Component originalMessage;
    private final SignedMessage signedMessage;
    private ChatRenderer renderer;
    private Component message;

    private boolean cancelled;

    AbstractChatEvent(final boolean async, final Player player, final Set<Audience> viewers, final ChatRenderer renderer, final Component message, final Component originalMessage, final SignedMessage signedMessage) {
        super(player, async);
        this.viewers = viewers;
        this.renderer = renderer;
        this.message = message;
        this.originalMessage = originalMessage;
        this.signedMessage = signedMessage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取将显示此聊天消息的 {@link Audience audiences} 集合。
     * <p>
     * 返回的集合可能在访问时自动填充。任何访问返回集合的监听器都应注意，对于惰性集合实现，这可能会降低性能。
     * <p>原文：
     * Gets a set of {@link Audience audiences} that this chat message will be displayed to.
     * <p>
     * The set returned may auto-populate on access. Any listener accessing the returned set should be aware that
     * it may reduce performance for a lazy set implementation.
     *
     * @return 将接收聊天消息的 {@link Audience audiences} 的可变集合
     * <p>原文：a mutable set of {@link Audience audiences} who will receive the chat message
     */
    public final Set<Audience> viewers() {
        return this.viewers;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置聊天渲染器。
     * <p>原文：
     * Sets the chat renderer.
     *
     * @param renderer 聊天渲染器
     * <p>原文：the chat renderer
     * @throws NullPointerException 如果 {@code renderer} 为 {@code null}
     * <p>原文：if {@code renderer} is {@code null}
     */
    public final void renderer(final ChatRenderer renderer) {
        this.renderer = requireNonNull(renderer, "renderer");
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取聊天渲染器。
     * <p>原文：
     * Gets the chat renderer.
     *
     * @return 聊天渲染器
     * <p>原文：the chat renderer
     */
    public final ChatRenderer renderer() {
        return this.renderer;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取用户提供的消息。返回值会反映通过 {@link #message(Component)} 所做的更改。
     * <p>原文：
     * Gets the user-supplied message.
     * The return value will reflect changes made using {@link #message(Component)}.
     *
     * @return 用户提供的消息
     * <p>原文：the user-supplied message
     */
    public final Component message() {
        return this.message;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置用户提供的消息。
     * <p>原文：
     * Sets the user-supplied message.
     *
     * @param message 用户提供的消息
     * <p>原文：the user-supplied message
     * @throws NullPointerException 如果 {@code message} 为 {@code null}
     * <p>原文：if {@code message} is {@code null}
     */
    public final void message(final Component message) {
        this.message = requireNonNull(message, "message");
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取用户提供的原始且未修改的消息。返回值<b>不会</b>反映通过 {@link #message(Component)} 所做的更改。
     * <p>原文：
     * Gets the original and unmodified user-supplied message.
     * The return value will <b>not</b> reflect changes made using
     * {@link #message(Component)}.
     *
     * @return 用户提供的原始消息
     * <p>原文：the original user-supplied message
     */
    public final Component originalMessage() {
        return this.originalMessage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取签名消息。在此事件中所做的更改<b>不会</b>更新签名消息。
     * <p>原文：
     * Gets the signed message.
     * Changes made in this event will <b>not</b> update
     * the signed message.
     *
     * @return 已签名消息
     * <p>原文：the signed message
     */
    public final SignedMessage signedMessage() {
        return this.signedMessage;
    }

    @Override
    public final boolean isCancelled() {
        return this.cancelled;
    }

    @Override
    public final void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }
}
