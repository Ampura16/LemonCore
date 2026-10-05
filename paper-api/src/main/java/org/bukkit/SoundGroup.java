package org.bukkit;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示方块的声音组，当各种动作发生时播放（例如踩踏、破坏、击打等）。
 * <p>原文：
 * Represents a group of sounds for blocks that are played when various actions
 * happen (ie stepping, breaking, hitting, etc).
 */
public interface SoundGroup {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这些声音的播放音量。
     *
     * 请注意，此音量并不总是代表客户端接收的实际音量。
     * <p>原文：
     * Get the volume these sounds are played at.
     *
     * Note that this volume does not always represent the actual volume
     * received by the client.
     *
     * @return 音量。
     *
     * 原文：
     * Get the volume these sounds are played at.
     *
     * Note that this volume does not always represent the actual volume
     * received by the client.
     * <p>原文：volume
     */
    public float getVolume();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这些声音的播放音高。
     *
     * 请注意，此音高并不总是代表客户端接收的实际音高。
     * <p>原文：
     * Gets the pitch these sounds are played at.
     *
     * Note that this pitch does not always represent the actual pitch received
     * by the client.
     *
     * @return 音高。
     *
     * 原文：
     * Gets the pitch these sounds are played at.
     *
     * Note that this pitch does not always represent the actual pitch received
     * by the client.
     * <p>原文：pitch
     */
    public float getPitch();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此组对应的破坏声音。
     * <p>原文：
     * Gets the corresponding breaking sound for this group.
     *
     * @return 破坏声音。
     *
     * 原文：
     * Gets the corresponding breaking sound for this group.
     * <p>原文：the break sound
     */
    @NotNull
    public Sound getBreakSound();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此组对应的踩踏声音。
     * <p>原文：
     * Gets the corresponding step sound for this group.
     *
     * @return 踩踏声音。
     *
     * 原文：
     * Gets the corresponding step sound for this group.
     * <p>原文：the step sound
     */
    @NotNull
    public Sound getStepSound();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此组对应的放置声音。
     * <p>原文：
     * Gets the corresponding place sound for this group.
     *
     * @return 放置声音。
     *
     * 原文：
     * Gets the corresponding place sound for this group.
     * <p>原文：the place sound
     */
    @NotNull
    public Sound getPlaceSound();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此组对应的击打声音。
     * <p>原文：
     * Gets the corresponding hit sound for this group.
     *
     * @return 击打声音。
     *
     * 原文：
     * Gets the corresponding hit sound for this group.
     * <p>原文：the hit sound
     */
    @NotNull
    public Sound getHitSound();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此组对应的摔落声音。
     * <p>原文：
     * Gets the corresponding fall sound for this group.
     *
     * @return 摔落声音。
     *
     * 原文：
     * Gets the corresponding fall sound for this group.
     * <p>原文：the fall sound
     */
    @NotNull
    public Sound getFallSound();
}
