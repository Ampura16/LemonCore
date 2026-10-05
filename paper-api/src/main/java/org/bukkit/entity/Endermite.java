package org.bukkit.entity;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 名副其实的小不点 - 末影螨.
 */
public interface Endermite extends Monster {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 该末影螨是否因玩家而生成 (玩家使用末影珍珠而导致末影螨的生成).
     * 由于玩家使用末影珍珠而生成的末影螨会被附近的末影人攻击.
     * <p>原文：
     * Gets whether this Endermite was spawned by a player.
     *
     * An Endermite spawned by a player will be attacked by nearby Enderman.
     *
     * @return 该末影螨是否因玩家而生成
     * <p>原文：player spawned status
     * @deprecated 本功能不再存在(自1.17版本起)
     * <p>原文：this functionality no longer exists
     */
    @Deprecated(since = "1.17", forRemoval = true)
    boolean isPlayerSpawned();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 该末影螨是否因玩家而生成 (玩家使用末影珍珠而导致末影螨的生成).
     * 由于玩家使用末影珍珠而生成的末影螨会被附近的末影人攻击.
     * <p>原文：
     * Sets whether this Endermite was spawned by a player.
     *
     * An Endermite spawned by a player will be attacked by nearby Enderman.
     *
     * @param playerSpawned 该末影螨是否因玩家而生成
     * <p>原文：player spawned status
     * @deprecated 本功能不再存在(自1.17版本起)
     * <p>原文：this functionality no longer exists
     */
    @Deprecated(since = "1.17", forRemoval = true)
    void setPlayerSpawned(boolean playerSpawned);
    // Paper start
    /**
     * Sets how many ticks this endermite has been living for.
     * If this value is greater than 2400, this endermite will despawn.
     *
     * @param ticks lifetime ticks
     */
    void setLifetimeTicks(int ticks);

    /**
     * Gets how long this endermite has been living for.
     * This value will tick up while {@link LivingEntity#getRemoveWhenFarAway()} is false.
     * If this value is greater than 2400, this endermite will despawn.
     *
     * @return lifetime ticks
     */
    int getLifetimeTicks();
    // Paper end
}
