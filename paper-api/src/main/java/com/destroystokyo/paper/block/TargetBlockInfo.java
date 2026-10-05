package com.destroystokyo.paper.block;

import org.bukkit.FluidCollisionMode;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示被瞄准方块的信息
 * <p>原文：
 * Represents information about a targeted block
 * @deprecated 请使用 {@link org.bukkit.util.RayTraceResult}
 * <p>原文：use {@link org.bukkit.util.RayTraceResult}
 */
@Deprecated(forRemoval = true, since = "1.19.3")
public class TargetBlockInfo {
    private final Block block;
    private final BlockFace blockFace;

    public TargetBlockInfo(@NotNull Block block, @NotNull BlockFace blockFace) {
        this.block = block;
        this.blockFace = blockFace;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取被瞄准的方块
     * <p>原文：
     * Get the block that is targeted
     *
     * @return 被瞄准的方块
     * <p>原文：Targeted block
     */
    @NotNull
    public Block getBlock() {
        return block;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取被瞄准的 BlockFace
     * <p>原文：
     * Get the targeted BlockFace
     *
     * @return 被瞄准的方块面
     * <p>原文：Targeted blockface
     */
    @NotNull
    public BlockFace getBlockFace() {
        return blockFace;
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取被瞄准方块在被瞄准面一侧的相邻方块
     * <p>原文：
     * Get the relative Block to the targeted block on the side it is targeted at
     *
     * @return 被瞄准方块的相邻方块
     * <p>原文：Block relative to targeted block
     */
    @NotNull
    public Block getRelativeBlock() {
        return block.getRelative(blockFace);
    }

    /**
     * @deprecated 请使用 {@link org.bukkit.FluidCollisionMode}
     * <p>原文：use {@link org.bukkit.FluidCollisionMode}
     */
    @Deprecated(forRemoval = true, since = "1.19.3")
    public enum FluidMode {
        NEVER(FluidCollisionMode.NEVER),
        SOURCE_ONLY(FluidCollisionMode.SOURCE_ONLY),
        ALWAYS(FluidCollisionMode.ALWAYS);

        public final FluidCollisionMode bukkit;

        FluidMode(FluidCollisionMode bukkit) {
            this.bukkit = bukkit;
        }
    }
}
