package org.bukkit.inventory;

import org.bukkit.block.DoubleChest;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 大箱子的用户界面接口.
 * <p>原文：
 * Interface to the inventory of a Double Chest.
 */
public interface DoubleChestInventory extends Inventory {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个大箱子的左半部分界面.
     * <p>原文：
     * Get the left half of this double chest.
     *
     * @return 左半部分界面
     * <p>原文：The left side inventory
     */
    @NotNull
    Inventory getLeftSide();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个大箱子的右半部分界面
     * <p>原文：
     * Get the right side of this double chest.
     *
     * @return 左半部分界面
     * <p>原文：The right side inventory
     */
    @NotNull
    Inventory getRightSide();

    @Override
    @Nullable
    DoubleChest getHolder();
}
