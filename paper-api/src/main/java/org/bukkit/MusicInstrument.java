package org.bukkit;

import io.papermc.paper.registry.RegistryAccess;
import io.papermc.paper.registry.RegistryBuilderFactory;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.data.InlinedRegistryBuilderProvider;
import io.papermc.paper.registry.data.InstrumentRegistryEntry;
import java.util.Collection;
import java.util.function.Consumer;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public abstract class MusicInstrument implements Keyed, net.kyori.adventure.translation.Translatable {

    /**
     * Creates an inlined music instrument.
     *
     * @param value a consumer for the builder factory
     * @return the created music instrument
     */
    @ApiStatus.Experimental
    public static MusicInstrument create(final Consumer<RegistryBuilderFactory<MusicInstrument, ? extends InstrumentRegistryEntry.Builder>> value) {
        return InlinedRegistryBuilderProvider.instance().createInstrument(value);
    }

    // Start generate - MusicInstrument
    public static final MusicInstrument ADMIRE_GOAT_HORN = getInstrument("admire_goat_horn");

    public static final MusicInstrument CALL_GOAT_HORN = getInstrument("call_goat_horn");

    public static final MusicInstrument DREAM_GOAT_HORN = getInstrument("dream_goat_horn");

    public static final MusicInstrument FEEL_GOAT_HORN = getInstrument("feel_goat_horn");

    public static final MusicInstrument PONDER_GOAT_HORN = getInstrument("ponder_goat_horn");

    public static final MusicInstrument SEEK_GOAT_HORN = getInstrument("seek_goat_horn");

    public static final MusicInstrument SING_GOAT_HORN = getInstrument("sing_goat_horn");

    public static final MusicInstrument YEARN_GOAT_HORN = getInstrument("yearn_goat_horn");
    // End generate - MusicInstrument

    private static MusicInstrument getInstrument(final @KeyPattern.Value String key) {
        return RegistryAccess.registryAccess().getRegistry(RegistryKey.INSTRUMENT).getOrThrow(Key.key(Key.MINECRAFT_NAMESPACE, key));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 通过 {@link NamespacedKey} 返回一个 {@link MusicInstrument}。
     * <p>原文：
     * Returns a {@link MusicInstrument} by a {@link NamespacedKey}.
     *
     * @param namespacedKey 键。
     * <p>原文：the key
     * @return 事件或 null。
     * <p>原文：the event or null
     * @deprecated 请改用 {@link Registry#get(NamespacedKey)}。
     *
     * 原文：
     * Returns a {@link MusicInstrument} by a {@link NamespacedKey}.
     * <p>原文：use {@link Registry#get(NamespacedKey)} instead
     */
    @Nullable
    @Deprecated(since = "1.20.1")
    public static MusicInstrument getByKey(final NamespacedKey namespacedKey) {
        return RegistryAccess.registryAccess().getRegistry(RegistryKey.INSTRUMENT).get(namespacedKey);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回所有已知的 MusicInstrument。
     * <p>原文：
     * Returns all known music instruments.
     *
     * @return 内存键。
     * <p>原文：the music instruments
     * @deprecated 请使用 {@link Registry#iterator()}。
     *
     * 原文：
     * Returns all known MusicInstruments.
     * <p>原文：use {@link Registry#iterator()}
     */
    @Deprecated(since = "1.20.1")
    public static Collection<MusicInstrument> values() {
        return RegistryAccess.registryAccess().getRegistry(RegistryKey.INSTRUMENT).stream().toList();
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取乐器的使用持续时间。
     * <p>原文：
     * Gets the use duration of this music instrument.
     *
     * @return 持续时间。
     *
     * 原文：
     * Gets how long the use duration is for the instrument.
     * <p>原文：the duration expressed in seconds
     */
    public abstract float getDuration();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取声音的范围。
     * <p>原文：
     * Gets the range of the sound.
     *
     * @return 声音的范围。
     *
     * 原文：
     * Gets the range of the sound.
     * <p>原文：the range of the sound
     */
    public abstract float getRange();

    /**
     * Gets the description of this instrument as displayed to the client.
     *
     * @return the description component
     */
    public abstract Component description();

    /**
     * Gets the sound for this instrument.
     *
     * @return the sound
     */
    public abstract Sound getSound();

    /**
     * @deprecated use {@link Registry#getKey(Keyed)}, {@link io.papermc.paper.registry.RegistryAccess#getRegistry(io.papermc.paper.registry.RegistryKey)},
     * and {@link io.papermc.paper.registry.RegistryKey#INSTRUMENT}. MusicInstruments can exist without a key.
     */
    @Deprecated(forRemoval = true, since = "1.20.5")
    @Override
    public abstract NamespacedKey getKey();

    /**
     * @deprecated use {@link Registry#getKey(Keyed)}, {@link io.papermc.paper.registry.RegistryAccess#getRegistry(io.papermc.paper.registry.RegistryKey)},
     * and {@link io.papermc.paper.registry.RegistryKey#INSTRUMENT}. MusicInstruments can exist without a key.
     */
    @Deprecated(forRemoval = true, since = "1.20.5")
    @Override
    public net.kyori.adventure.key.@org.jetbrains.annotations.NotNull Key key() {
        return Keyed.super.key();
    }

    /**
     * @deprecated this method assumes that the instrument description
     * always be a translatable component which is not guaranteed.
     */
    @Override
    @Deprecated(forRemoval = true)
    public abstract String translationKey();
}
