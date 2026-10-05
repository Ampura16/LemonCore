package io.papermc.paper.registry.data;

import io.papermc.paper.registry.RegistryBuilder;
import net.kyori.adventure.key.Key;
import org.bukkit.Sound;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link Sound} 类型的以数据为中心、特定于版本的注册表条目。
 * <p>原文：
 * A data-centric version-specific registry entry for the {@link Sound} type.
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface SoundEventRegistryEntry {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此声音事件的资源包位置。
     * <p>原文：
     * Gets the resource pack location for this sound event.
     *
     * @return 位置
     * <p>原文：the location
     */
    @Contract(pure = true)
    Key location();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此声音事件的固定范围（如果存在）。
     * <p>原文：
     * Gets the fixed range for this sound event, if present.
     *
     * @return 固定范围；如果不存在则为 {@code null}
     * <p>原文：the fixed range, or {@code null} if not present
     */
    @Contract(pure = true)
    @Nullable Float fixedRange();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 插件可在相应注册表事件中修改的 {@link SoundEventRegistryEntry} 的可变构建器。
     * <p>
     * 每个构建器必须提供以下值：
     * <ul>
     * <li>{@link #location(Key)}</li>
     * </ul>
     * <p>原文：
     * A mutable builder for the {@link SoundEventRegistryEntry} plugins may change in applicable registry events.
     * <p>
     * The following values are required for each builder:
     * <ul>
     *     <li>{@link #location(Key)}</li>
     * </ul>
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends SoundEventRegistryEntry, RegistryBuilder<Sound> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此声音事件的资源包位置。
         * <p>原文：
         * Sets the resource pack location for this sound event.
         *
         * @param location 位置
         * <p>原文：the location
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder location(Key location);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此声音事件的固定范围。
         * <p>原文：
         * Sets the fixed range for this sound event.
         *
         * @param fixedRange 固定范围
         * <p>原文：the fixed range
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder fixedRange(@Nullable Float fixedRange);
    }
}
