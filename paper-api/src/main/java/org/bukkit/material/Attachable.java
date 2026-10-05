package org.bukkit.material;

import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示一个方块可以附着在另一个方块上.
 * <p>原文：
 * Indicates that a block can be attached to another block
 */
public interface Attachable extends Directional {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此方块附着的面.
     * <p>原文：
     * Gets the face that this block is attached on
     *
     * @return 附着的面
     * <p>原文：BlockFace attached to
     */
    @NotNull
    public BlockFace getAttachedFace();
}
