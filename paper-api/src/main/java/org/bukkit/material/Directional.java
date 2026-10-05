package org.bukkit.material;

import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

public interface Directional {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此方块的朝向.
     * <p>原文：
     * Sets the direction that this block is facing in
     *
     * @param face 朝向
     * <p>原文：The facing direction
     */
    public void setFacingDirection(@NotNull BlockFace face);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此方块的朝向.
     * <p>原文：
     * Gets the direction this block is facing
     *
     * @return 此方块的朝向
     * <p>原文：the direction this block is facing
     */
    @NotNull
    public BlockFace getFacing();
}
