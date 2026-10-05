package org.bukkit.entity;

import java.util.Collection;
import org.bukkit.Location;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表嗅探兽。
 * <p>原文：
 * Represents a Sniffer.
 */
public interface Sniffer extends Animals {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取嗅探兽已探索的位置。
     * <br>
     * <b>注意：</b>返回的位置使用嗅探兽当前的世界。
     * <p>原文：
     * Gets the locations explored by the sniffer.
     *
     * @return 位置集合
     * <p>原文：a collection of locations
     */
    @NotNull
    public Collection<Location> getExploredLocations();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从已探索位置中移除一个位置。
     * <br>
     * <b>注意：</b>位置必须在嗅探兽当前世界中才能生效。
     * <p>原文：
     * Remove a location of the explored locations.
     *
     * @param location 要移除的位置
     * <p>原文：the location to remove
     * @see #getExploredLocations()
     */
    public void removeExploredLocation(@NotNull Location location);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向已探索位置中添加一个位置。
     * <br>
     * <b>注意：</b>位置必须在嗅探兽当前世界中才能生效。
     * <p>原文：
     * Add a location to the explored locations.
     * <br>
     * <b>Note:</b> the location must be in the sniffer's current world for this
     * method to have any effect.
     *
     * @param location 要添加的位置
     * <p>原文：the location to add
     * @see #getExploredLocations()
     */
    public void addExploredLocation(@NotNull Location location);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取嗅探兽的当前状态。
     * <p>原文：
     * Get the current state of the sniffer.
     *
     * @return 嗅探兽的状态
     * <p>原文：the state of the sniffer
     */
    @NotNull
    public Sniffer.State getState();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为嗅探兽设置新状态。
     * <br>
     * 这将使嗅探兽过渡到新状态。
     * <p>原文：
     * Set a new state for the sniffer.
     * <br>
     * This will also make the sniffer make the transition to the new state.
     *
     * @param state 新状态
     * <p>原文：the new state
     */
    public void setState(@NotNull Sniffer.State state);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 尝试获取嗅探兽可能挖掘的位置。
     * <p>原文：
     * Try to get a possible location where the sniffer can dig.
     *
     * @return 如果找到则返回 {@link Location}，否则返回null
     * <p>原文：a {@link Location} if found or null
     */
    @Nullable
    public Location findPossibleDigLocation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取嗅探兽是否能在其头部下方的当前位置挖掘。
     * <p>原文：
     * Gets whether the sniffer can dig in the current {@link Location} below
     * its head.
     *
     * @return 如果能挖掘则为 {@code true}，否则为 {@code false}
     * <p>原文：{@code true} if can dig or {@code false} otherwise
     */
    public boolean canDig();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表嗅探兽的当前状态。
     * <p>原文：
     * Represents the current state of the Sniffer.
     */
    public enum State {
        // Start generate - SnifferState
        IDLING,
        FEELING_HAPPY,
        SCENTING,
        SNIFFING,
        SEARCHING,
        DIGGING,
        RISING;
        // End generate - SnifferState
    }
}
