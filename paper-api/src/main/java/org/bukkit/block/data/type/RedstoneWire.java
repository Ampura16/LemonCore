package org.bukkit.block.data.type;

import java.util.Set;
import org.bukkit.block.BlockFace;
import org.bukkit.block.data.AnaloguePowerable;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'north', 'east', 'south', 'west' 代表该红石线与临近方块连接的方式.
 * <p>原文：
 * 'north', 'east', 'south', 'west' represent the types of connections this
 * redstone wire has to adjacent blocks.
 */
public interface RedstoneWire extends AnaloguePowerable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 查看指定方块朝向上的连接类型.
     * <p>原文：
     * Checks the type of connection on the specified face.
     *
     * @param face 目标方块朝向
     * <p>原文：to check
     * @return 连接类型
     * <p>原文：connection type
     */
    @NotNull
    Connection getFace(@NotNull BlockFace face);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定方块朝向上的连接类型.
     * <p>原文：
     * Sets the type of connection on the specified face.
     *
     * @param face 目标方块朝向
     * <p>原文：to set
     * @param connection 目标连接类型
     * <p>原文：the connection type
     */
    void setFace(@NotNull BlockFace face, @NotNull Connection connection);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该方块所有可以连接的方块朝向.
     * <p>原文：
     * Gets all of this faces which may be set on this block.
     *
     * @return 所有允许的方块朝向
     * <p>原文：all allowed faces
     */
    @NotNull
    Set<BlockFace> getAllowedFaces();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 红石线可连接到临近方块面的类型.
     * <p>原文：
     * The way in which a redstone wire can connect to an adjacent block face.
     */
    public enum Connection {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 红石线沿着与该面相邻的方块向上移动
         * <p>原文：
         * The wire travels up the side of the block adjacent to this face.
         */
        UP,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 红石线沿着平面移动并进入 (强充能) 方块.
         * <p>原文：
         * The wire travels flat from this face and into the adjacent block.
         */
        SIDE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 红石线还没有与任何方块连接.
         * <p>原文：
         * The wire does not connect in this direction.
         */
        NONE;
    }
}
