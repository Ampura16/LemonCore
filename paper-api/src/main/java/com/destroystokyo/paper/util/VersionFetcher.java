package com.destroystokyo.paper.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface VersionFetcher {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 结果缓存时长（毫秒）
     * <p>
     * 负值表示从不缓存。
     * <p>原文：
     * Amount of time to cache results for in milliseconds
     * <p>
     * Negative values will never cache.
     *
     * @return 缓存时间
     * <p>原文：cache time
     */
    long getCacheTime();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取要缓存并显示给命令发送者的版本消息。
     * <p>原文：
     * Gets the version message to cache and show to command senders.
     *
     * @return 请求版本时显示的消息
     * <p>原文：the message to show when requesting a version
     * @apiNote 此方法可能涉及网络请求，会阻塞执行线程
     * <p>原文：This method may involve a web request which will block the executing thread
     */
    Component getVersionMessage();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取要缓存并显示给命令发送者的版本消息。
     * <p>原文：
     * Gets the version message to cache and show to command senders.
     *
     * @param serverVersion 服务器的当前版本（将与 {@link Bukkit#getVersion()} 一致）
     * <p>原文：the current version of the server (will match {@link Bukkit#getVersion()})
     * @return 请求版本时显示的消息
     * <p>原文：the message to show when requesting a version
     * @apiNote 此方法可能涉及网络请求，会阻塞当前线程
     * <p>原文：This method may involve a web request which will block the current thread
     * @see #getVersionMessage()
     * @deprecated {@code serverVersion} 不是必需的
     * <p>原文：{@code serverVersion} is not required
     */
    @Deprecated
    default Component getVersionMessage(String serverVersion) {
        return getVersionMessage();
    }

    /**
     * @hidden
     */
    @ApiStatus.Internal
    class DummyVersionFetcher implements VersionFetcher {

        @Override
        public long getCacheTime() {
            return -1;
        }

        @Override
        public Component getVersionMessage() {
            Bukkit.getLogger().warning("Version provider has not been set, cannot check for updates!");
            Bukkit.getLogger().info("Override the default implementation of org.bukkit.UnsafeValues#getVersionFetcher()");
            new Throwable().printStackTrace();
            return Component.text("Unable to check for updates. No version provider set.", NamedTextColor.RED);
        }
    }
}
