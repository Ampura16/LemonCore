package org.bukkit.block.data;

import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'rotation' 表示方块的旋转方向.
 * <p>原文：
 * 'rotation' represents the current rotation of this block.
 */
public interface Rotatable extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'rotation' 属性值.
     * <p>原文：
     * Gets the value of the 'rotation' property.
     *
     * @return 方块的旋转方向
     * <p>原文：the 'rotation' value
     */
    @NotNull
    BlockFace getRotation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'rotation' 属性值.
     * <p>原文：
     * Sets the value of the 'rotation' property.
     *
     * @param rotation 方块的旋转方向
     * <p>原文：the new 'rotation' value
     */
    void setRotation(@NotNull BlockFace rotation);
}
