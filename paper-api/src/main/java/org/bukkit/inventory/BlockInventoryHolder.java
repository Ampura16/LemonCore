package org.bukkit.inventory;

import org.bukkit.block.Block;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个方块物品栏持有者 - 可以是BlockState或普通Block.
 * <p>原文：
 * Represents a block inventory holder - either a BlockState, or a regular
 * Block.
 */
public interface BlockInventoryHolder extends InventoryHolder {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取与此持有者关联的方块.
     * <p>原文：
     * Gets the block associated with this holder.
     *
     * @return 与此持有者关联的方块
     * <p>原文：the block associated with this holder
     * @throws IllegalStateException 如果持有者是方块状态且未被放置
     * <p>原文：if the holder is a block state and is not
     * placed
     */
    @NotNull
    Block getBlock();
}
