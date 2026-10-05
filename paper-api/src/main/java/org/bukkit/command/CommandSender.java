package org.bukkit.command;

import java.util.UUID;
import net.kyori.adventure.audience.MessageType;
import net.kyori.adventure.identity.Identity;
import net.kyori.adventure.text.Component;
import org.bukkit.Server;
import org.bukkit.permissions.Permissible;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface CommandSender extends net.kyori.adventure.audience.Audience, Permissible { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向命令发送者发送消息.
     * <p>原文：
     * Sends this sender a message
     *
     * @param message 要发送的消息
     * <p>原文：Message to be displayed
     * @see #sendMessage(net.kyori.adventure.text.Component)
     * @see #sendPlainMessage(String)
     * @see #sendRichMessage(String)
     */
    @org.jetbrains.annotations.ApiStatus.Obsolete // Paper
    public void sendMessage(@NotNull String message);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向命令发送者发送多条消息.
     * <p>原文：
     * Sends this sender multiple messages
     *
     * @param messages 要发送的消息
     * <p>原文：An array of messages to be displayed
     * @see #sendMessage(net.kyori.adventure.text.Component)
     * @see #sendPlainMessage(String)
     * @see #sendRichMessage(String)
     */
    @org.jetbrains.annotations.ApiStatus.Obsolete // Paper
    public void sendMessage(@NotNull String... messages);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向命令发送者发送消息.
     * <p>原文：
     * Sends this sender a message
     *
     * @param message 要发送的消息
     * <p>原文：Message to be displayed
     * @param sender 消息的发送者
     * <p>原文：The sender of this message
     * @see #sendMessage(net.kyori.adventure.identity.Identified, net.kyori.adventure.text.Component)
     * @deprecated sender UUID is ignored
     */
    @Deprecated // Paper
    public void sendMessage(@Nullable UUID sender, @NotNull String message);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向命令发送者发送多条消息.
     * <p>原文：
     * Sends this sender multiple messages
     *
     * @param messages 要发送的消息
     * <p>原文：An array of messages to be displayed
     * @param sender 消息的发送者
     * <p>原文：The sender of this message
     * @see #sendMessage(net.kyori.adventure.identity.Identified, net.kyori.adventure.text.Component)
     * @deprecated sender UUID is ignored
     */
    @Deprecated // Paper
    public void sendMessage(@Nullable UUID sender, @NotNull String... messages);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回运行此命令的服务器实例.
     * <p>原文：
     * Returns the server instance that this command is running on
     *
     * @return 服务器实例
     * <p>原文：Server instance
     */
    @NotNull
    public Server getServer();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取命令发送者的名字.
     * <p>原文：
     * Gets the name of this command sender
     *
     * @return 发送者的名字
     * <p>原文：Name of the sender
     */
    @NotNull
    public String getName();

    // Spigot start
    public class Spigot {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向命令发送者发送一个聊天消息组件.
         * <p>原文：
         * Sends this sender a chat component.
         *
         * @param component 要发送的聊天消息组件
         * <p>原文：the components to send
         * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
         */
        @Deprecated // Paper
        public void sendMessage(@NotNull net.md_5.bungee.api.chat.BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 将多个聊天消息组件作为单条消息发送给命令发送者.
         * <p>原文：
         * Sends an array of components as a single message to the sender.
         *
         * @param components 要发送的聊天消息组件
         * <p>原文：the components to send
         * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
         */
        @Deprecated // Paper
        public void sendMessage(@NotNull net.md_5.bungee.api.chat.BaseComponent... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 向命令发送者发送一个聊天消息组件.
         * <p>原文：
         * Sends this sender a chat component.
         *
         * @param component 要发送的聊天消息组件
         * <p>原文：the components to send
         * @param sender 消息的发送者
         * <p>原文：the sender of the message
         * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
         */
        @Deprecated // Paper
        public void sendMessage(@Nullable UUID sender, @NotNull net.md_5.bungee.api.chat.BaseComponent component) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 将多个聊天消息组件作为单条消息发送给命令发送者.
         * <p>原文：
         * Sends an array of components as a single message to the sender.
         *
         * @param components 要发送的聊天消息组件
         * <p>原文：the components to send
         * @param sender 消息的发送者
         * <p>原文：the sender of the message
         * @deprecated use {@code sendMessage} methods that accept {@link net.kyori.adventure.text.Component}
         */
        @Deprecated // Paper
        public void sendMessage(@Nullable UUID sender, @NotNull net.md_5.bungee.api.chat.BaseComponent... components) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @NotNull
    Spigot spigot();
    // Spigot end

    // Paper start
    /**
     * Gets the name of this command sender
     *
     * @return Name of the sender
     */
    public net.kyori.adventure.text.@NotNull Component name();

    @Override
    default void sendMessage(final net.kyori.adventure.identity.@NotNull Identity identity, final net.kyori.adventure.text.@NotNull Component message, final net.kyori.adventure.audience.@NotNull MessageType type) {
        this.sendMessage(net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer.legacySection().serialize(message));
    }

    /**
     * Sends a message with the MiniMessage format to the command sender.
     * <p>
     * See <a href="https://docs.advntr.dev/minimessage/">MiniMessage docs</a>
     * for more information on the format.
     *
     * @param message MiniMessage content
     */
    default void sendRichMessage(final @NotNull String message) {
        this.sendMessage(net.kyori.adventure.text.minimessage.MiniMessage.miniMessage().deserialize(message, this));
    }

    /**
     * Sends a message with the MiniMessage format to the command sender.
     * <p>
     * See <a href="https://docs.advntr.dev/minimessage/">MiniMessage docs</a> and <a href="https://docs.advntr.dev/minimessage/dynamic-replacements">MiniMessage Placeholders docs</a>
     * for more information on the format.
     *
     * @param message MiniMessage content
     * @param resolvers resolvers to use
     */
    default void sendRichMessage(final @NotNull String message, final net.kyori.adventure.text.minimessage.tag.resolver.@NotNull TagResolver... resolvers) {
        this.sendMessage(net.kyori.adventure.text.minimessage.MiniMessage.miniMessage().deserialize(message, this, resolvers));
    }

    /**
     * Sends a plain message to the command sender.
     *
     * @param message plain message
     */
    default void sendPlainMessage(final @NotNull String message) {
        this.sendMessage(net.kyori.adventure.text.Component.text(message));
    }

    /**
     * Sends the component to the sender
     *
     * <p>If this sender does not support sending full components then
     * the component will be sent as legacy text.</p>
     *
     * @param component the component to send
     * @deprecated use {@link #sendMessage(Identity, Component, MessageType)} instead
     */
    @Deprecated
    default void sendMessage(@NotNull net.md_5.bungee.api.chat.BaseComponent component) {
        this.sendMessage(component.toLegacyText());
    }

    /**
     * Sends an array of components as a single message to the sender
     *
     * <p>If this sender does not support sending full components then
     * the components will be sent as legacy text.</p>
     *
     * @param components the components to send
     * @deprecated use {@link #sendMessage(Identity, Component, MessageType)} instead
     */
    @Deprecated
    default void sendMessage(@NotNull net.md_5.bungee.api.chat.BaseComponent... components) {
        this.sendMessage(new net.md_5.bungee.api.chat.TextComponent(components).toLegacyText());
    }
    // Paper end
}
