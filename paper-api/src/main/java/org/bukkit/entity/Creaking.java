package org.bukkit.entity;

import org.bukkit.Location;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 咔嗒嘎.
 * <p>原文：
 * Represents a Creaking.
 */
@NullMarked
public interface Creaking extends Monster {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此咔嗒嘎的家位置 (即其对应的 {@link org.bukkit.block.CreakingHeart} 所在位置).
     * <p>原文：
     * Gets the home location for this creaking (where its {@link org.bukkit.block.CreakingHeart} could be found).
     *
     * @return 家的位置
     * <p>原文：the location of the home if available, null otherwise
     */
    @Nullable
    Location getHome();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 激活此咔嗒嘎以追踪和跟随玩家.
     * <p>原文：
     * Activates this creaking to target and follow a player.
     *
     * @param player 目标玩家
     * <p>原文：the target
     */
    void activate(final Player player);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使此咔嗒嘎从当前目标玩家处失活.
     * <p>原文：
     * Deactivates the creaking, clearing its current attack target and
     * marking it as inactive.
     */
    void deactivate();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此咔嗒嘎是否处于激活状态.
     * <p>原文：
     * Returns if this creaking is currently active and hunting.
     *
     * @see #activate(Player)
     * @return 如果处于激活状态则为 true
     * <p>原文：true if active
     */
    boolean isActive();

}
