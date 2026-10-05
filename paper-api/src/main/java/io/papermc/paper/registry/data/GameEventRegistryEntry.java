package io.papermc.paper.registry.data;

import io.papermc.paper.registry.RegistryBuilder;
import org.bukkit.GameEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link GameEvent} 类型的以数据为中心、特定于版本的注册表条目。
 * <p>原文：
 * A data-centric version-specific registry entry for the {@link GameEvent} type.
 */
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface GameEventRegistryEntry {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供此游戏事件通知其监听器的范围。
     * <p>原文：
     * Provides the range in which this game event will notify its listeners.
     *
     * @return 方块范围，以 int 表示。
     * <p>原文：the range of blocks, represented as an int.
     * @see GameEvent#getRange()
     */
    @Range(from = 0, to = Integer.MAX_VALUE) int range();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 插件可在相应注册表事件中修改的 {@link GameEventRegistryEntry} 的可变构建器。
     * <p>
     * 每个构建器必须提供以下值：
     * <ul>
     * <li>{@link #range(int)}</li>
     * </ul>
     * <p>原文：
     * A mutable builder for the {@link GameEventRegistryEntry} plugins may change in applicable registry events.
     * <p>
     * The following values are required for each builder:
     * <ul>
     *     <li>{@link #range(int)}</li>
     * </ul>
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends GameEventRegistryEntry, RegistryBuilder<GameEvent> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此游戏事件应通知其监听器的范围。
         * <p>原文：
         * Sets the range in which this game event should notify its listeners.
         *
         * @param range 方块范围。
         * <p>原文：the range of blocks.
         * @return 此构建器实例。
         * <p>原文：this builder instance.
         * @see GameEventRegistryEntry#range()
         * @see GameEvent#getRange()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder range(@Range(from = 0, to = Integer.MAX_VALUE) int range);
    }
}
