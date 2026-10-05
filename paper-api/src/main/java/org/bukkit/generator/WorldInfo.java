package org.bukkit.generator;

import java.util.UUID;
import org.bukkit.World;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 用于存储一个世界的各种信息.
 * <p>原文：
 * Holds various information of a World
 */
public interface WorldInfo extends io.papermc.paper.world.flag.FeatureFlagSetHolder { // Paper - feature flag API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界的唯一名称.
     * <p>原文：
     * Gets the unique name of this world
     *
     * @return 世界的名称
     * <p>原文：Name of this world
     */
    @NotNull
    String getName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界的唯一UUID.
     * <p>原文：
     * Gets the Unique ID of this world
     *
     * @return 世界的唯一UUID
     * <p>原文：Unique ID of this world.
     */
    @NotNull
    UUID getUID();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界的{@link World.Environment 环境}类型.
     * <p>原文：
     * Gets the {@link World.Environment} type of this world
     *
     * @return 这个世界的环境类型
     * <p>原文：This worlds Environment type
     */
    @NotNull
    World.Environment getEnvironment();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界的种子.
     * <p>原文：
     * Gets the Seed for this world.
     *
     * @return 这个世界的种子
     * <p>原文：This worlds Seed
     */
    long getSeed();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界的最低高度.
     * <p>
     * 如果最低高度为0, 则只有y=0处才有方块.
     * <p>原文：
     * Gets the minimum height of this world.
     * <p>
     * If the min height is 0, there are only blocks from y=0.
     *
     * @return 世界的最低高度
     * <p>原文：Minimum height of the world
     */
    int getMinHeight();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个世界的最高高度.
     * <p>
     * 如果最大高度为100, 则只有y=0到y=99才有方块.
     * <p>原文：
     * Gets the maximum height of this world.
     * <p>
     * If the max height is 100, there are only blocks from y=0 to y=99.
     *
     * @return 世界的最高高度
     * <p>原文：Maximum height of the world
     */
    int getMaxHeight();

    // Paper start
    /**
     * Get the vanilla {@link BiomeProvider} for this world.
     *
     * @return vanilla biome provider
     */
    @NotNull BiomeProvider vanillaBiomeProvider();
    // Paper end
}
