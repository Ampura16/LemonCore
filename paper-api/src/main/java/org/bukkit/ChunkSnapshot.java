package org.bukkit;

import org.bukkit.block.Biome;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示一个静态的，线程安全的方块的区块的快照.
 * <p>
 * 目的是允许清洁高效地复制一个区块的数据，然后在另一个线程（例如，地图渲染）中处理.
 * <p>原文：
 * Represents a static, thread-safe snapshot of chunk of blocks.
 * <p>
 * Purpose is to allow clean, efficient copy of a chunk data to be made, and
 * then handed off for processing in another thread (e.g. map rendering)
 */
public interface ChunkSnapshot {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定区块的X坐标.
     * <p>原文：
     * Gets the X-coordinate of this chunk
     *
     * @return X坐标
     * <p>原文：X-coordinate
     */
    int getX();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定区块的Z坐标.
     * <p>原文：
     * Gets the Z-coordinate of this chunk
     *
     * @return Z坐标
     * <p>原文：Z-coordinate
     */
    int getZ();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定区块所在世界的名称.
     * <p>原文：
     * Gets name of the world containing this chunk
     *
     * @return 所在世界的名称
     * <p>原文：Parent World Name
     */
    @NotNull
    String getWorldName();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取区块中对应坐标方块的方块类型.
     * <p>原文：
     * Get block type for block at corresponding coordinate in the chunk
     *
     * @param x 0-15
     * @param y [世界最低高度, 世界最高高度)
     * <p>原文：world minHeight (inclusive) - world maxHeight (exclusive)
     * @param z 0-15
     * @return 方块物品类型
     * <p>原文：block material type
     */
    @NotNull
    Material getBlockType(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取区块中对应坐标方块的方块数据.
     * <p>原文：
     * Get block data for block at corresponding coordinate in the chunk
     *
     * @param x 0-15
     * @param y [世界最低高度, 世界最高高度)
     * <p>原文：world minHeight (inclusive) - world maxHeight (exclusive)
     * @param z 0-15
     * @return 方块数据
     * <p>原文：block material type
     */
    @NotNull
    BlockData getBlockData(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取区块中对应坐标方块的方块数据.
     * <p>原文：
     * Get block data for block at corresponding coordinate in the chunk
     *
     * @param x 0-15
     * @param y [世界最低高度, 世界最高高度)
     * <p>原文：world minHeight (inclusive) - world maxHeight (exclusive)
     * @param z 0-15
     * @return 0-15
     * @deprecated 不安全的参数
     * <p>原文：Magic value
     */
    @Deprecated(since = "1.6.2", forRemoval = true)
    int getData(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取区块中对应坐标方块的天空亮度等级.
     * <p>原文：
     * Get sky light level for block at corresponding coordinate in the chunk
     *
     * @param x 0-15
     * @param y [世界最低高度, 世界最高高度)
     * <p>原文：world minHeight (inclusive) - world maxHeight (exclusive)
     * @param z 0-15
     * @return 0-15
     */
    int getBlockSkyLight(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取区块中对应坐标方块的发光亮度等级.
     * <p>原文：
     * Get light level emitted by block at corresponding coordinate in the
     * chunk
     *
     * @param x 0-15
     * @param y [世界最低高度, 世界最高高度)
     * <p>原文：world minHeight (inclusive) - world maxHeight (exclusive)
     * @param z 0-15
     * @return 0-15
     */
    int getBlockEmittedLight(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标最高非空气方块的Y坐标.
     * <p>原文：
     * Gets the highest non-air coordinate at the given coordinates
     *
     * @param x 方块X坐标 (0-15)
     * <p>原文：X-coordinate of the blocks (0-15)
     * @param z 方块Z坐标 (0-15)
     * <p>原文：Z-coordinate of the blocks (0-15)
     * @return 最高非空气方块的Y坐标
     * <p>原文：Y-coordinate of the highest non-air block
     */
    int getHighestBlockYAt(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标的生物群系.
     * <p>原文：
     * Get biome at given coordinates
     *
     * @param x X坐标 (0-15)
     * <p>原文：X-coordinate (0-15)
     * @param z Z坐标 (0-15)
     * <p>原文：Z-coordinate (0-15)
     * @return 指定坐标的生物群系
     * <p>原文：Biome at given coordinate
     * @deprecated biomes are now 3-dimensional
     */
    @NotNull
    @Deprecated(since = "1.15")
    Biome getBiome(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标的生物群系.
     * <p>原文：
     * Get biome at given coordinates
     *
     * @param x X坐标 (0-15)
     * <p>原文：X-coordinate (0-15)
     * @param y Y坐标 [世界最低高度, 世界最高高度)
     * <p>原文：Y-coordinate (world minHeight (inclusive) - world maxHeight (exclusive))
     * @param z Z坐标 (0-15)
     * <p>原文：Z-coordinate (0-15)
     * @return 指定坐标的生物群系
     * <p>原文：Biome at given coordinate
     */
    @NotNull
    Biome getBiome(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标原始生物群系的温度（范围为0.0到1.0）.
     * <p>原文：
     * Get raw biome temperature at given coordinates
     *
     * @param x X坐标 (0-15)
     * <p>原文：X-coordinate (0-15)
     * @param z Z坐标 (0-15)
     * <p>原文：Z-coordinate (0-15)
     * @return 指定坐标的温度
     * <p>原文：temperature at given coordinate
     * @deprecated biomes are now 3-dimensional
     */
    @Deprecated(since = "1.15")
    double getRawBiomeTemperature(int x, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定坐标的原始生物群系温度.
     * <p>原文：
     * Get raw biome temperature at given coordinates
     *
     * @param x X坐标 (0-15)
     * <p>原文：X-coordinate (0-15)
     * @param y Y坐标 (0-15)
     * <p>原文：Y-coordinate (world minHeight (inclusive) - world maxHeight (exclusive))
     * @param z Z坐标 (0-15)
     * <p>原文：Z-coordinate (0-15)
     * @return 指定坐标的温度
     * <p>原文：temperature at given coordinate
     */
    double getRawBiomeTemperature(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 抓取区块快照时获取世界的完整时间.
     * <p>原文：
     * Get world full time when chunk snapshot was captured
     *
     * @return 时间，用tick表示
     * <p>原文：time in ticks
     */
    long getCaptureFullTime();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试区块是否为空.
     * <p>原文：
     * Test if section is empty
     *
     * @param sy -区块的Y坐标, 为方块Y坐标整除16, 区间:[世界最低高度, 世界最高高度)
     * <p>原文：- section Y coordinate (block Y / 16, world minHeight (inclusive) - world maxHeight (exclusive))
     * @return 如果为空则返回true，否则返回false
     * <p>原文：true if empty, false if not
     */
    boolean isSectionEmpty(int sy);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试此快照是否包含指定的方块.
     * <p>原文：
     * Tests if this snapshot contains the specified block.
     *
     * @param block 要测试的方块
     * <p>原文：block to test
     * @return 是否包含该方块
     * <p>原文：if the block is contained within
     */
    boolean contains(@NotNull BlockData block);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试此区块是否包含指定的生物群系.
     * <p>原文：
     * Tests if this chunk contains the specified biome.
     *
     * @param biome 要测试的生物群系
     * <p>原文：biome to test
     * @return 是否包含该生物群系
     * <p>原文：if the biome is contained within
     */
    boolean contains(@NotNull Biome biome);
}
