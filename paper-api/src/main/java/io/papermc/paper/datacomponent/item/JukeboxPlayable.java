package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.bukkit.JukeboxSong;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有物品的唱片机歌曲。
 * <p>原文：
 * Holds the jukebox song for an item.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#JUKEBOX_PLAYABLE
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface JukeboxPlayable  {

    @Contract(value = "_ -> new", pure = true)
    static JukeboxPlayable.Builder jukeboxPlayable(final JukeboxSong song) {
        return ItemComponentTypesBridge.bridge().jukeboxPlayable(song);
    }

    @Contract(pure = true)
    JukeboxSong jukeboxSong();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link JukeboxPlayable} 的构建器。
     * <p>原文：
     * Builder for {@link JukeboxPlayable}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<JukeboxPlayable> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置唱片机歌曲。
         * <p>原文：
         * Sets the jukebox song.
         *
         * @param song 唱片曲目
         * <p>原文：the song
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #jukeboxSong()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder jukeboxSong(JukeboxSong song);
    }
}
