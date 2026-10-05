package org.bukkit;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 与高度图有关的信息.
 * <p>原文：
 * Further information regarding heightmaps.
 *
 * @see <a href="https://minecraft.wiki/w/Chunk_format">Minecraft Wiki</a>
 */
public enum HeightMap {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 最高的阻碍移动/含有流体的方块.
     * <p>原文：
     * The highest block that blocks motion or contains a fluid.
     */
    MOTION_BLOCKING,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 最高的阻碍移动、含有液体或在 {@link Tag#LEAVES} 标签内的方块.
     * <p>原文：
     * The highest block that blocks motion or contains a fluid, excluding leaves.
     */
    MOTION_BLOCKING_NO_LEAVES,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 最高的非空气固体方块.
     * <p>原文：
     * The highest non-air block, solid block.
     */
    OCEAN_FLOOR,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 最高的既不是空气也不含流体的方块, 用于世界生成.
     * <p>原文：
     * The highest block that is neither air nor contains a fluid, for worldgen.
     */
    OCEAN_FLOOR_WG,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 最高的非空气方块.
     * <p>原文：
     * The highest non-air block.
     */
    WORLD_SURFACE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 最高的非空气方块, 用于世界生成.
     * <p>原文：
     * The highest non-air block, for worldgen.
     */
    WORLD_SURFACE_WG,
}
