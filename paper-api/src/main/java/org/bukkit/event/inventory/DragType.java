package org.bukkit.event.inventory;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 在物品栏中拖动物品的拖动类型.
 * <p>原文：
 * Represents the effect of a drag that will be applied to an Inventory in an
 * InventoryDragEvent.
 */
public enum DragType {
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 每个物品槽中会放置一个光标上的物品.
     * <p>原文：
     * One item from the cursor is placed in each selected slot.
     */
    SINGLE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 光标上的物品会平均分配到每一个物品槽中, 但物品槽中的物品不会超过该物品的最大堆叠数量, 剩余的物品仍会在光标上.
     * <p>原文：
     * The cursor is split evenly across all selected slots, not to exceed the
     * Material's max stack size, with the remainder going to the cursor.
     */
    EVEN,
}
