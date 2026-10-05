package io.papermc.paper.registry.data;

import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.RegistryBuilderFactory;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.holder.RegistryHolder;
import java.util.function.Consumer;
import net.kyori.adventure.text.Component;
import org.bukkit.JukeboxSong;
import org.bukkit.Sound;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link JukeboxSong} 类型的以数据为中心、特定于版本的注册表条目。
 * <p>原文：
 * A data-centric version-specific registry entry for the {@link JukeboxSong} type.
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface JukeboxSongRegistryEntry {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此歌曲的声音事件。
     * <p>原文：
     * Gets the sound event for this song.
     *
     * @return 声音事件
     * <p>原文：the sound event
     */
    @Contract(pure = true)
    RegistryHolder<Sound, SoundEventRegistryEntry> soundEvent();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此歌曲的描述。
     * <p>原文：
     * Gets the description for this song.
     *
     * @return 描述
     * <p>原文：the description
     */
    @Contract(pure = true)
    Component description();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此歌曲的时长（秒）。
     * <p>原文：
     * Gets the length in seconds for this song.
     *
     * @return 长度（秒）
     * <p>原文：the length in seconds
     */
    @Contract(pure = true)
    @Positive float lengthInSeconds();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此歌曲的比较器输出。
     * <p>原文：
     * Gets the comparator output for this song.
     *
     * @return 比较器输出
     * <p>原文：the comparator output
     */
    @Contract(pure = true)
    @Range(from = 0, to = 15) int comparatorOutput();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 插件可在相应注册表事件中修改的 {@link JukeboxSongRegistryEntry} 的可变构建器。
     * <p>
     * 每个构建器必须提供以下值：
     * <ul>
     * <li>{@link #soundEvent(TypedKey)}、{@link #soundEvent(Consumer)} 或 {@link #soundEvent(RegistryHolder)} </li>
     * <li>{@link #description(Component)}</li>
     * <li>{@link #lengthInSeconds(float)}</li>
     * <li>{@link #comparatorOutput(int)}</li>
     * </ul>
     * <p>原文：
     * A mutable builder for the {@link JukeboxSongRegistryEntry} plugins may change in applicable registry events.
     * <p>
     * The following values are required for each builder:
     * <ul>
     *     <li>
     *         {@link #soundEvent(TypedKey)}, {@link #soundEvent(Consumer)} or {@link #soundEvent(RegistryHolder)}
     *     </li>
     *     <li>{@link #description(Component)}</li>
     *     <li>{@link #lengthInSeconds(float)}</li>
     *     <li>{@link #comparatorOutput(int)}</li>
     * </ul>
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends JukeboxSongRegistryEntry, RegistryBuilder<JukeboxSong> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将此歌曲的声音事件设为 {@link io.papermc.paper.registry.RegistryKey#SOUND_EVENT} 注册表中存在的声音事件。
         * <p>
         * 这将同时覆盖 {@link #soundEvent(Consumer)} 和 {@link #soundEvent(RegistryHolder)}
         * </p>
         * <p>原文：
         * Sets the sound event for this song to a sound event present
         * in the {@link io.papermc.paper.registry.RegistryKey#SOUND_EVENT} registry.
         * <p>This will override both {@link #soundEvent(Consumer)} and {@link #soundEvent(RegistryHolder)}</p>
         *
         * @param soundEvent 声音事件
         * <p>原文：the sound event
         * @return 此构建器
         * <p>原文：this builder
         * @see #soundEvent(Consumer)
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder soundEvent(TypedKey<Sound> soundEvent);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将此歌曲的声音事件设为新的声音事件。
         * <p>
         * 这将同时覆盖 {@link #soundEvent(TypedKey)} 和 {@link #soundEvent(RegistryHolder)}
         * </p>
         * <p>原文：
         * Sets the sound event for this song to a new sound event.
         * <p>This will override both {@link #soundEvent(TypedKey)} and {@link #soundEvent(RegistryHolder)}</p>
         *
         * @param soundEvent 声音事件
         * <p>原文：the sound event
         * @return 此构建器
         * <p>原文：this builder
         * @see #soundEvent(TypedKey)
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder soundEvent(Consumer<RegistryBuilderFactory<Sound, ? extends SoundEventRegistryEntry.Builder>> soundEvent);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此歌曲的声音事件。
         * <p>
         * 这将同时覆盖 {@link #soundEvent(Consumer)} 和 {@link #soundEvent(TypedKey)}
         * </p>
         * <p>原文：
         * Sets the sound event for this song.
         * <p>This will override both {@link #soundEvent(Consumer)} and {@link #soundEvent(TypedKey)}</p>
         *
         * @param soundEvent 声音事件
         * <p>原文：the sound event
         * @return 此构建器
         * <p>原文：this builder
         * @see #soundEvent(TypedKey)
         * @see #soundEvent(Consumer)
         */
        @Contract( value = "_ -> this", mutates = "this")
        Builder soundEvent(RegistryHolder<Sound, SoundEventRegistryEntry> soundEvent);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此歌曲的描述。
         * <p>原文：
         * Sets the description for this song.
         *
         * @param description 描述
         * <p>原文：the description
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder description(Component description);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此歌曲的时长（秒）。
         * <p>原文：
         * Sets the length in seconds for this song.
         *
         * @param lengthInSeconds 长度（秒，正数）
         * <p>原文：the length in seconds (positive)
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder lengthInSeconds(@Positive float lengthInSeconds);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此歌曲的比较器输出。
         * <p>原文：
         * Sets the comparator output for this song.
         *
         * @param comparatorOutput 比较器输出 [0-15]
         * <p>原文：the comparator output [0-15]
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder comparatorOutput(@Range(from = 0, to = 15) int comparatorOutput);
    }
}
