package org.bukkit;

import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个用于处理方块改变的代理.
 * <p>原文：
 * A delegate for handling block changes. This serves as a direct interface
 * between generation algorithms in the server implementation and utilizing
 * code.
 */
public interface BlockChangeDelegate {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在指定坐标处设置方块数据.
     * <p>原文：
     * Set a block data at the specified coordinates.
     *
     * @param x X 坐标
     * <p>原文：X coordinate
     * @param y Y 坐标
     * <p>原文：Y coordinate
     * @param z Z 坐标
     * <p>原文：Z coordinate
     * @param blockData 方块数据
     * <p>原文：Block data
     * @return 如果方块设置成功则返回 true
     * <p>原文：true if the block was set successfully
     */
    public boolean setBlockData(int x, int y, int z, @NotNull BlockData blockData);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定位置的方块数据.
     * <p>原文：
     * Get the block data at the location.
     *
     * @param x X 坐标
     * <p>原文：X coordinate
     * @param y Y 坐标
     * <p>原文：Y coordinate
     * @param z Z 坐标
     * <p>原文：Z coordinate
     * @return 方块数据
     * <p>原文：The block data
     */
    @NotNull
    public BlockData getBlockData(int x, int y, int z);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取世界的高度.
     * <p>原文：
     * Gets the height of the world.
     *
     * @return 世界的高度
     * <p>原文：Height of the world
     */
    public int getHeight();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查指定方块是否为空（空气）.
     * <p>原文：
     * Checks if the specified block is empty (air) or not.
     *
     * @param x X 坐标
     * <p>原文：X coordinate
     * @param y Y 坐标
     * <p>原文：Y coordinate
     * @param z Z 坐标
     * <p>原文：Z coordinate
     * @return 如果方块被视为空则返回 true
     * <p>原文：True if the block is considered empty.
     */
    public boolean isEmpty(int x, int y, int z);
}
