package io.papermc.paper.event.player;

import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.server.ServerEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 当服务器为聊天用途装饰组件时触发此事件。它在 {@link AsyncChatEvent} 及其他聊天事件之前调用。建议在此修改消息，并使用聊天事件修改接收者以及之后的聊天类型。如果希望消息对接收它的客户端保持“已签名”，请务必在最终消息中的某处包含完整的原始消息。<br> 通过命令发送的消息的装饰请参阅 {@link AsyncChatCommandDecorateEvent}
 * <p>原文：
 * This event is fired when the server decorates a component for chat purposes. This is called
 * before {@link AsyncChatEvent} and the other chat events. It is recommended that you modify the
 * message here, and use the chat events for modifying receivers and later the chat type. If you
 * want to keep the message as "signed" for the clients who get it, be sure to include the entire
 * original message somewhere in the final message.
 * <br>
 * See {@link AsyncChatCommandDecorateEvent} for the decoration of messages sent via commands
 */
@ApiStatus.Experimental
@NullMarked
public class AsyncChatDecorateEvent extends ServerEvent implements Cancellable {

    private static final HandlerList HANDLER_LIST = new HandlerList();

    private final @Nullable Player player;
    private final Component originalMessage;
    private Component result;

    private boolean cancelled;

    @ApiStatus.Internal
    public AsyncChatDecorateEvent(final @Nullable Player player, final Component originalMessage) {
        super(true);
        this.player = player;
        this.originalMessage = originalMessage;
        this.result = originalMessage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取与此事件关联的玩家（如果有）。
     * <p>
     * 某些命令会在没有玩家上下文的情况下请求装饰，因此可能为 {@code null}。
     * <p>原文：
     * Gets the player (if available) associated with this event.
     * <p>
     * Certain commands request decorations without a player context
     * which is why this is possibly {@code null}.
     *
     * @return 玩家或 {@code null}
     * <p>原文：the player or {@code null}
     */
    public @Nullable Player player() {
        return this.player;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取原始的装饰输入
     * <p>原文：
     * Gets the original decoration input
     *
     * @return 输入
     * <p>原文：the input
     */
    public Component originalMessage() {
        return this.originalMessage;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取装饰结果。如果此事件的其他监听器更改了结果，该值可能已与 {@link #originalMessage()} 不同。
     * <p>原文：
     * Gets the decoration result. This may already be different from
     * {@link #originalMessage()} if some other listener to this event
     * changed the result.
     *
     * @return 结果
     * <p>原文：the result
     */
    public Component result() {
        return this.result;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置最终的装饰组件。
     * <p>原文：
     * Sets the resulting decorated component.
     *
     * @param result 结果
     * <p>原文：the result
     */
    public void result(final Component result) {
        this.result = result;
    }

    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 被取消的装饰事件意味着对结果组件的任何更改都不会生效，装饰后的组件将与原始组件相同。
     * <p>原文：
     * A cancelled decorating event means that no changes to the result component
     * will have any effect. The decorated component will be equal to the original
     * component.
     */
    @Override
    public void setCancelled(final boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return HANDLER_LIST;
    }

    public static HandlerList getHandlerList() {
        return HANDLER_LIST;
    }
}
