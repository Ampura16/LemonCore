package org.bukkit.inventory;

import org.bukkit.block.ChiseledBookshelf;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 雕纹书架的物品栏接口.
 * <p>原文：
 * Interface to the inventory of a chiseled bookshelf.
 */
public interface ChiseledBookshelfInventory extends Inventory {

    @Nullable
    @Override
    public ChiseledBookshelf getHolder();
}
