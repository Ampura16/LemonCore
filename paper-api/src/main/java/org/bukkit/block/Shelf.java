package org.bukkit.block;

import io.papermc.paper.block.TileStateInventoryHolder;
import org.bukkit.inventory.ShelfInventory;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示书架的捕获状态。
 */
@NullMarked
public interface Shelf extends TileStateInventoryHolder {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 物品栏
     * @see Container#getInventory()
     */
    @Override
    ShelfInventory getInventory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 快照物品栏
     * @see Container#getSnapshotInventory()
     */
    @Override
    ShelfInventory getSnapshotInventory();
}
