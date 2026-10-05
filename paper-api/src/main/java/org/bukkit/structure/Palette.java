package org.bukkit.structure;

import java.util.List;
import org.bukkit.block.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示结构的一个变体。
 *
 * 大多数结构，例如使用结构方块生成的结构，只有一个变体。
 * <p>原文：
 * Represent a variation of a structure.
 * <p>
 * Most structures, like the ones generated with structure blocks, only have a
 * single variant.
 */
public interface Palette {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取构成此调色板的方块的副本。
     *
     * 返回的方块状态的 {@link BlockState#getLocation() 位置} 是相对于结构位置的偏移量，该位置在结构放置到世界中后提供。
     * <p>原文：
     * Gets a copy of the blocks this Palette is made of.
     * <p>
     * The {@link BlockState#getLocation() positions} of the returned block
     * states are offsets relative to the structure's position that is provided
     * once the structure is placed into the world.
     *
     * @return The blocks in this palette
     */
    @NotNull
    List<BlockState> getBlocks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此调色板中存储的方块数量。
     * <p>原文：
     * Gets the number of blocks stored in this palette.
     *
     * @return The number of blocks in this palette
     */
    int getBlockCount();
}
