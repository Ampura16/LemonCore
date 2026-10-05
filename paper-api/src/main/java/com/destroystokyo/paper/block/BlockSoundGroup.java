package com.destroystokyo.paper.block;

import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示 {@link Block} 在特定情况下发出的声音
 * <p>
 * 声音组包括破坏、踩踏、放置、敲击和掉落的声音。
 * <p>原文：
 * Represents the sounds that a {@link Block} makes in certain situations
 * <p>
 * The sound group includes break, step, place, hit, and fall sounds.
 * @deprecated 请使用 {@link org.bukkit.SoundGroup}
 * <p>原文：use {@link org.bukkit.SoundGroup}
 */
@Deprecated(forRemoval = true, since = "1.18.2")
public interface BlockSoundGroup {
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取破坏此方块时播放的声音
     * <p>原文：
     * Gets the sound that plays when breaking this block
     *
     * @return 破坏声音
     * <p>原文：The break sound
     * @deprecated 请使用 {@link org.bukkit.SoundGroup#getBreakSound()}
     * <p>原文：use {@link org.bukkit.SoundGroup#getBreakSound()}
     */
    @NotNull
    @Deprecated(forRemoval = true, since = "1.18.2")
    Sound getBreakSound();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取踩在此方块上时播放的声音
     * <p>原文：
     * Gets the sound that plays when stepping on this block
     *
     * @return 踩踏声音
     * <p>原文：The step sound
     * @deprecated 请使用 {@link org.bukkit.SoundGroup#getStepSound()}
     * <p>原文：use {@link org.bukkit.SoundGroup#getStepSound()}
     */
    @NotNull
    @Deprecated(forRemoval = true, since = "1.18.2")
    Sound getStepSound();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取放置此方块时播放的声音
     * <p>原文：
     * Gets the sound that plays when placing this block
     *
     * @return 放置声音
     * <p>原文：The place sound
     * @deprecated 请使用 {@link org.bukkit.SoundGroup#getPlaceSound()}
     * <p>原文：use {@link org.bukkit.SoundGroup#getPlaceSound()}
     */
    @NotNull
    @Deprecated(forRemoval = true, since = "1.18.2")
    Sound getPlaceSound();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取敲击此方块时播放的声音
     * <p>原文：
     * Gets the sound that plays when hitting this block
     *
     * @return 敲击声音
     * <p>原文：The hit sound
     * @deprecated 请使用 {@link org.bukkit.SoundGroup#getHitSound()}
     * <p>原文：use {@link org.bukkit.SoundGroup#getHitSound()}
     */
    @NotNull
    @Deprecated(forRemoval = true, since = "1.18.2")
    Sound getHitSound();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此方块掉落时播放的声音
     * <p>原文：
     * Gets the sound that plays when this block falls
     *
     * @return 掉落声音
     * <p>原文：The fall sound
     * @deprecated 请使用 {@link org.bukkit.SoundGroup#getFallSound()}
     * <p>原文：use {@link org.bukkit.SoundGroup#getFallSound()}
     */
    @NotNull
    @Deprecated(forRemoval = true, since = "1.18.2")
    Sound getFallSound();
}
