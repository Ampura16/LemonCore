package io.papermc.paper.chat;

import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 聊天渲染器负责渲染由 {@link Player} 发送到服务器的聊天消息。
 * <p>原文：
 * A chat renderer is responsible for rendering chat messages sent by {@link Player}s to the server.
 */
@NullMarked
@FunctionalInterface
public interface ChatRenderer {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 渲染聊天消息。对每个接收消息的 {@link Audience} 调用一次。
     * <p>原文：
     * Renders a chat message. This will be called once for each receiving {@link Audience}.
     *
     * @param source 消息来源
     * <p>原文：the message source
     * @param sourceDisplayName 来源玩家的显示名称
     * <p>原文：the display name of the source player
     * @param message 聊天消息
     * <p>原文：the chat message
     * @param viewer 接收的 {@link Audience}
     * <p>原文：the receiving {@link Audience}
     * @return 渲染后的聊天消息
     * <p>原文：a rendered chat message
     */
    @ApiStatus.OverrideOnly
    Component render(Player source, Component sourceDisplayName, Component message, Audience viewer);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建默认 {@link ChatRenderer} 的新实例。
     * <p>原文：
     * Create a new instance of the default {@link ChatRenderer}.
     *
     * @return 新的 {@link ChatRenderer}
     * <p>原文：a new {@link ChatRenderer}
     */
    static ChatRenderer defaultRenderer() {
        return new ViewerUnawareImpl.Default((source, sourceDisplayName, message) -> Component.translatable("chat.type.text", sourceDisplayName, message));
    }

    @ApiStatus.Internal
    sealed interface Default extends ChatRenderer, ViewerUnaware permits ViewerUnawareImpl.Default {
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的与观看者无关的 {@link ChatRenderer}，它只渲染一次聊天消息，并向每个观看的 {@link Audience} 显示相同的渲染结果。
     * <p>原文：
     * Creates a new viewer-unaware {@link ChatRenderer}, which will render the chat message a single time,
     * displaying the same rendered message to every viewing {@link Audience}.
     *
     * @param renderer 不感知查看者的渲染器
     * <p>原文：the viewer unaware renderer
     * @return 新的 {@link ChatRenderer}
     * <p>原文：a new {@link ChatRenderer}
     */
    static ChatRenderer viewerUnaware(final ViewerUnaware renderer) {
        return new ViewerUnawareImpl(renderer);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 类似于 {@link ChatRenderer}，但不知道消息的查看者。
     * <p>原文：
     * Similar to {@link ChatRenderer}, but without knowledge of the message viewer.
     *
     * @see ChatRenderer#viewerUnaware(ViewerUnaware)
     */
    interface ViewerUnaware {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 渲染聊天消息。
         * <p>原文：
         * Renders a chat message.
         *
         * @param source 消息来源
         * <p>原文：the message source
         * @param sourceDisplayName 来源玩家的显示名称
         * <p>原文：the display name of the source player
         * @param message 聊天消息
         * <p>原文：the chat message
         * @return 渲染后的聊天消息
         * <p>原文：a rendered chat message
         */
        @ApiStatus.OverrideOnly
        Component render(Player source, Component sourceDisplayName, Component message);
    }
}
