package io.papermc.paper.registry.data;

import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.RegistryBuilderFactory;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.holder.RegistryHolder;
import net.kyori.adventure.text.Component;
import org.bukkit.MusicInstrument;
import org.bukkit.Sound;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import java.util.function.Consumer;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link org.bukkit.MusicInstrument} 类型的以数据为中心、特定于版本的注册表条目。
 * <p>原文：
 * A data-centric version-specific registry entry for the {@link org.bukkit.MusicInstrument} type.
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface InstrumentRegistryEntry {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供乐器的声音事件。
     * <p>原文：
     * Provides the sound event of the instrument.
     *
     * @return 声音事件。
     * <p>原文：the sound event.
     * @see MusicInstrument#getSound()
     */
    @Contract(pure = true)
    RegistryHolder<Sound, SoundEventRegistryEntry> soundEvent();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供乐器的持续时间，即使用时间。
     * <p>原文：
     * Provides the duration of the instrument, which is time to use.
     *
     * @return 持续时间。
     * <p>原文：the duration.
     * @see MusicInstrument#getDuration()
     */
    @Contract(pure = true)
    @Positive float duration();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供乐器的范围，即声音的范围。
     * <p>原文：
     * Provides the range of the instrument, which is range of the sound.
     *
     * @return 范围。
     * <p>原文：the range.
     * @see MusicInstrument#getRange()
     */
    @Contract(pure = true)
    @Positive float range();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供乐器的描述，用于物品提示框。
     * <p>原文：
     * Provides the description of the instrument, which is used in the item tooltip.
     *
     * @return 描述。
     * <p>原文：the description.
     * @see MusicInstrument#description()
     */
    @Contract(pure = true)
    Component description();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 插件可在相应注册表事件中修改的 {@link InstrumentRegistryEntry} 的可变构建器。
     * <p>
     * 每个构建器必须提供以下值：
     * <ul>
     * <li>{@link #soundEvent(TypedKey)}、{@link #soundEvent(Consumer)} 或 {@link #soundEvent(RegistryHolder)} </li>
     * <li>{@link #duration(float)}</li>
     * <li>{@link #range(float)}</li>
     * <li>{@link #description(Component)}</li>
     * </ul>
     * <p>原文：
     * A mutable builder for the {@link InstrumentRegistryEntry} plugins may change in applicable registry events.
     * <p>
     * The following values are required for each builder:
     * <ul>
     *     <li>
     *         {@link #soundEvent(TypedKey)}, {@link #soundEvent(Consumer)} or {@link #soundEvent(RegistryHolder)}
     *     </li>
     *     <li>{@link #duration(float)}</li>
     *     <li>{@link #range(float)}</li>
     *     <li>{@link #description(Component)}</li>
     * </ul>
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends InstrumentRegistryEntry, RegistryBuilder<MusicInstrument> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将此乐器的声音事件设为 {@link io.papermc.paper.registry.RegistryKey#SOUND_EVENT} 注册表中存在的声音事件。
         * <p>
         * 这将同时覆盖 {@link #soundEvent(Consumer)} 和 {@link #soundEvent(RegistryHolder)}
         * </p>
         * <p>原文：
         * Sets the sound event for this instrument to a sound event present
         * in the {@link io.papermc.paper.registry.RegistryKey#SOUND_EVENT} registry.
         * <p>This will override both {@link #soundEvent(Consumer)} and {@link #soundEvent(RegistryHolder)}</p>
         *
         * @param soundEvent 声音事件
         * <p>原文：the sound event
         * @return 此构建器
         * <p>原文：this builder
         * @see #soundEvent(Consumer)
         * @see InstrumentRegistryEntry#soundEvent()
         * @see MusicInstrument#getSound()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder soundEvent(TypedKey<Sound> soundEvent);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 将此乐器的声音事件设为新的声音事件。
         * <p>
         * 这将同时覆盖 {@link #soundEvent(TypedKey)} 和 {@link #soundEvent(RegistryHolder)}
         * </p>
         * <p>原文：
         * Sets the sound event for this instrument to a new sound event.
         * <p>This will override both {@link #soundEvent(TypedKey)} and {@link #soundEvent(RegistryHolder)}</p>
         *
         * @param soundEvent 声音事件
         * <p>原文：the sound event
         * @return 此构建器
         * <p>原文：this builder
         * @see #soundEvent(TypedKey)
         * @see InstrumentRegistryEntry#soundEvent()
         * @see MusicInstrument#getSound()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder soundEvent(Consumer<RegistryBuilderFactory<Sound, ? extends SoundEventRegistryEntry.Builder>> soundEvent);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此乐器的声音事件。
         * <p>
         * 这将同时覆盖 {@link #soundEvent(Consumer)} 和 {@link #soundEvent(TypedKey)}
         * </p>
         * <p>原文：
         * Sets the sound event for this instrument.
         * <p>This will override both {@link #soundEvent(Consumer)} and {@link #soundEvent(TypedKey)}</p>
         *
         * @param soundEvent 声音事件
         * <p>原文：the sound event
         * @return 此构建器
         * <p>原文：this builder
         * @see #soundEvent(TypedKey)
         * @see #soundEvent(Consumer)
         * @see InstrumentRegistryEntry#soundEvent()
         * @see MusicInstrument#getSound()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder soundEvent(RegistryHolder<Sound, SoundEventRegistryEntry> soundEvent);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此乐器的使用持续时间。
         * <p>原文：
         * Sets the duration of use for this instrument.
         *
         * @param duration 持续时间（正数）
         * <p>原文：the duration (positive)
         * @return 此构建器
         * <p>原文：this builder
         * @see InstrumentRegistryEntry#duration()
         * @see MusicInstrument#getDuration()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder duration(@Positive float duration);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此乐器的范围。
         * <p>原文：
         * Sets the range for this instrument.
         *
         * @param range 范围（正数）
         * <p>原文：the range (positive)
         * @return 此构建器
         * <p>原文：this builder
         * @see InstrumentRegistryEntry#range()
         * @see MusicInstrument#getRange()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder range(@Positive float range);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此乐器的描述。
         * <p>原文：
         * Sets the description for this instrument.
         *
         * @param description 描述
         * <p>原文：the description
         * @return 此构建器
         * <p>原文：this builder
         * @see InstrumentRegistryEntry#description()
         * @see MusicInstrument#description()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder description(Component description);
    }
}
