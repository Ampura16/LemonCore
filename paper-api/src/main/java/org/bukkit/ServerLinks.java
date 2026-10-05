package org.bukkit;

import java.net.URI;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示可以发送给客户端的链接集合。
 * <p>原文：
 * Represents a collections of links which may be sent to a client.
 */
@ApiStatus.Experimental
public interface ServerLinks {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取给定类型的链接（如果存在）。
     * <p>原文：
     * Gets the link of a given type, if it exists.
     *
     * @param type 链接类型。
     * <p>原文：link type
     * @return 链接或 null。
     *
     * 原文：
     * Gets the link of a given type, if it exists.
     * <p>原文：link or null
     */
    @Nullable
    ServerLink getLink(@NotNull Type type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取所有链接的不可变列表。
     * <p>原文：
     * Gets an immutable list of all links.
     *
     * @return 不可变列表。
     *
     * 原文：
     * Gets an immutable list of all links.
     * <p>原文：immutable list
     */
    @NotNull
    List<ServerLink> getLinks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 添加给定的链接，如果已设置相同类型的链接，则覆盖第一个链接。
     * <p>原文：
     * Adds the given link, overwriting the first link of the same type if
     * already set.
     *
     * @param type 链接类型。
     * <p>原文：link type
     * @param url 链接 URL。
     * <p>原文：link url
     * @return 添加的链接。
     *
     * 原文：
     * Adds the given link, overwriting the first link of the same type if
     * already set.
     * <p>原文：the added link
     */
    @NotNull
    ServerLink setLink(@NotNull Type type, @NotNull URI url);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将给定的链接添加到链接列表中。
     * <p>原文：
     * Adds the given link to the list of links.
     *
     * @param type 链接类型。
     * <p>原文：link type
     * @param url 链接 URL。
     * <p>原文：link url
     * @return 添加的链接。
     *
     * 原文：
     * Adds the given link to the list of links.
     * <p>原文：the added link
     */
    @NotNull
    ServerLink addLink(@NotNull Type type, @NotNull URI url);

    // Paper start - Adventure
    /**
     * Adds the given link to the list of links.
     *
     * @param displayName link name / display text Component
     * @param url link url
     * @return the added link
     */
    @NotNull
    ServerLink addLink(@NotNull net.kyori.adventure.text.Component displayName, @NotNull URI url);
    // Paper end - Adventure

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 将给定的链接添加到链接列表中。
     * <p>原文：
     * Adds the given link to the list of links.
     *
     * @param displayName 链接名称/显示文本。
     * <p>原文：link name / display text
     * @param url 链接 URL。
     * <p>原文：link url
     * @return 添加的链接。
     *
     * 原文：
     * Adds the given link to the list of links.
     * <p>原文：the added link
     * @deprecated in favour of {@link ServerLinks#addLink(net.kyori.adventure.text.Component, URI)}
     */
    @Deprecated // Paper - Adventure
    @NotNull
    ServerLink addLink(@NotNull String displayName, @NotNull URI url);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除给定的链接。
     * <p>原文：
     * Removes the given link.
     *
     * @param link 要移除的链接。
     * <p>原文：the link to remove
     * @return 链接是否存在并被移除。
     *
     * 原文：
     * Removes the given link.
     * <p>原文：if the link existed and was removed
     */
    boolean removeLink(@NotNull ServerLink link);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此链接集合的副本，与服务器解除关联。
     * <p>原文：
     * Returns a copy of this link collection, unassociated from the server.
     *
     * @return 复制的链接。
     *
     * 原文：
     * Returns a copy of this link collection, unassociated from the server.
     * <p>原文：copied links
     */
    @NotNull
    ServerLinks copy();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示服务器链接。
     * <p>原文：
     * Represents a server link.
     */
    public interface ServerLink {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获取此链接的类型（如果它是已知的特殊类型）。
         * <p>原文：
         * Gets the type of this link if it is a known special type.
         *
         * @return 类型或 null。
         *
         * 原文：
         * Gets the type of this link if it is a known special type.
         * <p>原文：type or null
         */
        @Nullable
        Type getType();

        // Paper start - Adventure
        /**
         * Gets the display name/text Component of this link.
         *
         * @return display name Component
         */
        @NotNull
        net.kyori.adventure.text.Component displayName();
        // Paper end - Adventure

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获取此链接的显示名称/文本。
         * <p>原文：
         * Gets the display name/text of this link.
         *
         * @return 显示名称。
         *
         * 原文：
         * Gets the display name/text of this link.
         * <p>原文：display name
         * @deprecated in favour of {@link ServerLink#displayName()}
         */
        @Deprecated // Paper - Adventure
        @NotNull
        String getDisplayName();

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获取此链接的 URL。
         * <p>原文：
         * Gets the url of this link.
         *
         * @return 链接 URL。
         *
         * 原文：
         * Gets the url of this link.
         * <p>原文：link url
         */
        @NotNull
        URI getUrl();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 表示已知类型的链接，将由客户端翻译，并可能具有特殊功能。
     * <p>原文：
     * Represents a known type of link which will be translated by the client
     * and may have special functionality.
     */
    public enum Type {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 错误报告链接，可能出现在断开连接/崩溃屏幕上。
         * <p>原文：
         * Bug report links which may appear on disconnect/crash screens.
         */
        REPORT_BUG,
        COMMUNITY_GUIDELINES,
        SUPPORT,
        STATUS,
        FEEDBACK,
        COMMUNITY,
        WEBSITE,
        FORUMS,
        NEWS,
        ANNOUNCEMENTS;
    }
}
