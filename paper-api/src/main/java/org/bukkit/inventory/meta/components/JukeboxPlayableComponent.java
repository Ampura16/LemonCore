package org.bukkit.inventory.meta.components;

import org.bukkit.JukeboxSong;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示一个可以插入唱片机的组件。
 * <p>原文：
 * Represents a component which can be inserted into a jukebox.
 */
@ApiStatus.Experimental
public interface JukeboxPlayableComponent extends ConfigurationSerializable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取分配给此组件的歌曲。
     * <p>原文：
     * Gets the song assigned to this component.
     *
     * @return 歌曲，如果服务器上不存在该歌曲则返回null
     * <p>原文：song, or null if the song does not exist on the server
     */
    @Nullable
    JukeboxSong getSong();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取分配给此组件的歌曲的键。
     * <p>原文：
     * Gets the key of the song assigned to this component.
     *
     * @return 歌曲键
     * <p>原文：the song key
     */
    @NotNull
    NamespacedKey getSongKey();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置分配给此组件的歌曲。
     * <p>原文：
     * Sets the song assigned to this component.
     *
     * @param song 歌曲
     * <p>原文：the song
     */
    void setSong(@NotNull JukeboxSong song);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置分配给此组件的歌曲的键。
     * <p>原文：
     * Sets the key of the song assigned to this component.
     *
     * @param song 歌曲键
     * <p>原文：the song key
     */
    void setSongKey(@NotNull NamespacedKey song);

    /**
     * Gets if the song will show in the item tooltip.
     *
     * @return if the song will show in the tooltip
     * @deprecated no longer available on the component directly
     */
    @Deprecated(since = "1.21.5", forRemoval = true)
    @Contract("-> true") // todo add new item flag for compat? or just tell people to use the new data component api
    default boolean isShowInTooltip() {
        return true;
    }

    /**
     * Sets if the song will show in the item tooltip.
     *
     * @param show true if the song will show in the tooltip
     * @deprecated no longer available on the component directly
     */
    @Deprecated(since = "1.21.5", forRemoval = true)
    default void setShowInTooltip(boolean show) {
    }
}
