package org.bukkit.block;

import com.destroystokyo.paper.loottable.LootableBlockInventory; // Paper
import org.bukkit.Nameable; // Paper
import org.bukkit.inventory.Inventory;
import org.bukkit.loot.Lootable;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表箱子.
 * <p>原文：
 * Represents a captured state of a chest.
 */
public interface Chest extends Container, LootableBlockInventory, Lidded { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回箱子类型的物品栏.
     * <p>
     * 如果这是大型箱子, 将返回与此箱子对应的一半.
     * <p>
     * 如果方块类型被改变, 返回的物品栏可能不再有效.
     * <p>
     * 如果方块状态未应用(译注:仅仅是一种表示而未应用到实际的方块上, 一种虚拟状态)本方法将返回物品栏快照.
     * <p>原文：
     * Gets the inventory of the chest block represented by this block state.
     * <p>
     * If the chest is a double chest, it returns just the portion of the
     * inventory linked to the half of the chest corresponding to this block state.
     * <p>
     * If the block was changed to a different type in the meantime, the
     * returned inventory might no longer be valid.
     * <p>
     * If this block state is not placed this will return the captured
     * inventory snapshot instead.
     *
     * @return 箱子类型的物品栏
     * <p>原文：the inventory
     */
    @NotNull
    Inventory getBlockInventory();

    // Paper start - More Chest Block API
    /**
     * Checks whether this chest is blocked
     * by either a block above or a sitting cat
     *
     * @return whether this chest is blocked
     */
    boolean isBlocked();
    // Paper end - More Chest Block API
}
