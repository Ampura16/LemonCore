package org.bukkit.block.data.type;

import java.util.Set;
import org.bukkit.block.data.Directional;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示书架上外部可见的哪些物品槽已被占用的接口，其标记名为“slot_0_occupied”、“slot_1_occupied”等。
 * <br>
 * 一个方块可能没有任何槽被占用，也可能最多只有{@link #getMaximumOccupiedSlots()} - 1个槽被占用。
 * 该接口提供了方法来获取当前已占用的槽数量和位置信息。
 * <p>原文：
 * Interface to the 'slot_0_occupied', 'slow_1_occupied' ... 'slot_5_occupied'
 * flags on a bookshelf which indicate which slots are occupied rendered on the
 * outside.
 * <br>
 * Block may have 0, 1... {@link #getMaximumOccupiedSlots()}-1 occupied slots.
 */
public interface ChiseledBookshelf extends Directional {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查下一个槽位是否被占用.
     * <p>原文：
     * Checks if the following slot is occupied.
     *
     * @param slot 要检测的槽位
     * <p>原文：to check
     * @return 槽位是否被占用
     * <p>原文：if slot is occupied
     */
    boolean isSlotOccupied(int slot);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置下一个槽位是否被占用.
     * <p>原文：
     * Sets whether the following slot is occupied.
     *
     * @param slot 要检测的槽位
     * <p>原文：to set
     * @param occupied 槽位是否被占用
     * <p>原文：book
     */
    void setSlotOccupied(int slot, boolean occupied);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此方块上所有已占用槽位的索引.
     * <p>原文：
     * Get the indexes of all the occupied slots present on this block.
     *
     * @return 所有已占用槽位的索引
     * <p>原文：set of all occupied slots
     */
    @NotNull
    Set<Integer> getOccupiedSlots();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此方块上的最大物品槽数量.
     * <p>原文：
     * Get the maximum amount of slots on this block.
     *
     * @return 最大物品槽数量
     * <p>原文：maximum occupied slots count
     */
    int getMaximumOccupiedSlots();
}
