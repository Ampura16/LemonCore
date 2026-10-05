package org.bukkit.block;

import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.ChiseledBookshelfInventory;
import org.bukkit.util.Vector;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表雕纹书架的捕获状态.
 * <p>原文：
 * Represents a captured state of a chiseled bookshelf.
 */
public interface ChiseledBookshelf extends io.papermc.paper.block.TileStateInventoryHolder { // Paper - TileStateInventoryHolder

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取最后交互的物品栏槽位.
     * <p>原文：
     * Gets the last interacted inventory slot.
     *
     * @return 最后交互的槽位
     * <p>原文：the last interacted slot
     */
    int getLastInteractedSlot();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置最后交互的物品栏槽位.
     * <p>原文：
     * Sets the last interacted inventory slot.
     *
     * @param lastInteractedSlot 新的最后交互槽位
     * <p>原文：the new last interacted slot
     */
    void setLastInteractedSlot(int lastInteractedSlot);

    // Paper - move docs to TileStateInventoryHolder
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 物品栏
     * @see Container#getInventory()
     */
    @NotNull
    @Override
    ChiseledBookshelfInventory getInventory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     *
     *
     * @return 快照物品栏
     * @see Container#getSnapshotInventory()
     */
    @Override // Paper - move docs to TileStateInventoryHolder
    @NotNull
    ChiseledBookshelfInventory getSnapshotInventory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 根据相对于此方块的向量获取适当的槽位.<br>
     * 如果给定向量不在任何槽位的边界内, 将返回-1.
     * <p>原文：
     * Gets the appropriate slot based on a vector relative to this block.<br>
     * Will return -1 if the given vector is not within the bounds of any slot.
     * <p>
     * The supplied vector should only contain components with values between 0.0
     * and 1.0, inclusive.
     *
     * @param position 相对于此方块的向量
     * <p>原文：a vector relative to this block
     * @return 给定向量下的槽位或-1
     * <p>原文：the slot under the given vector or -1
     */
    int getSlot(@NotNull Vector position);
}
