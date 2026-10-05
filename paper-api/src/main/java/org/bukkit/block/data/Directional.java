package org.bukkit.block.data;

import java.util.Set;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'facing' 代表方块的朝向.
 * <br>
 * 一些方块可能不能够面向东南西北所有方向, 可以用
 * {@link #getFaces()} 来获取这个方块所有可能的朝向.
 * <p>原文：
 * 'facing' represents the face towards which the block is pointing.
 * <br>
 * Some blocks may not be able to face in all directions, use
 * {@link #getFaces()} to get all possible directions for this block.
 */
public interface Directional extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取方块朝向属性.
     * <p>原文：
     * Gets the value of the 'facing' property.
     *
     * @return 方块朝向属性
     * <p>原文：the 'facing' value
     */
    @NotNull
    BlockFace getFacing();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置方块的朝向.
     * <p>原文：
     * Sets the value of the 'facing' property.
     *
     * @param facing 方块的朝向
     * <p>原文：the new 'facing' value
     */
    void setFacing(@NotNull BlockFace facing);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取可应用于此方块的朝向.
     * <p>原文：
     * Gets the faces which are applicable to this block.
     *
     * @return 允许的朝向值
     * <p>原文：the allowed 'facing' values
     */
    @NotNull
    Set<BlockFace> getFaces();
}
