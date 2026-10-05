package io.papermc.paper.block;

import org.bukkit.block.TileState;
import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.Inventory;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示具有物品栏的 {@link TileState} 方块。
 * <p>原文：
 * Represents a {@link TileState} block that has an inventory.
 */
@NullMarked
public interface TileStateInventoryHolder extends TileState, BlockInventoryHolder {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此方块状态所代表方块的物品栏。
     * <p>
     * 如果在此期间方块被改为其他类型，返回的物品栏可能不再有效。
     * <p>
     * 如果此方块状态未被放置，则返回捕获的物品栏快照。
     * <p>原文：
     * Gets the inventory of the block represented by this block state.
     * <p>
     * If the block was changed to a different type in the meantime, the
     * returned inventory might no longer be valid.
     * <p>
     * If this block state is not placed this will return the captured inventory
     * snapshot instead.
     *
     * @return 物品栏
     * <p>原文：the inventory
     */
    @Override
    Inventory getInventory();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此容器的物品栏快照。
     * <p>
     * 返回的物品栏不与任何方块关联。在调用 {@link #update(boolean, boolean)} 之前，对返回物品栏的任何修改都不会应用到此方块状态所代表的方块上。
     * <p>原文：
     * Gets the captured inventory snapshot of this container.
     * <p>
     * The returned inventory is not linked to any block. Any modifications to
     * the returned inventory will not be applied to the block represented by
     * this block state up until {@link #update(boolean, boolean)} has been
     * called.
     *
     * @return 捕获的物品栏快照
     * <p>原文：the captured inventory snapshot
     */
    Inventory getSnapshotInventory();
}
