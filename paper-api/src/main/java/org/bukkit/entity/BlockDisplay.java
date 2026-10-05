package org.bukkit.entity;

import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个方块展示实体。
 * <p>原文：
 * Represents a block display entity.
 */
public interface BlockDisplay extends Display {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取展示的方块。
     * <p>原文：
     * Gets the displayed block.
     *
     * @return 展示的方块
     * <p>原文：Gets the displayed block.
     * <p>原文：the displayed block
     */
    @NotNull
    public BlockData getBlock();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置展示的方块。
     * <p>原文：
     * Sets the displayed block.
     *
     * @param block 新的方块
     * <p>原文：Sets the displayed block.
     * <p>原文：the new block
     */
    public void setBlock(@NotNull BlockData block);
}
