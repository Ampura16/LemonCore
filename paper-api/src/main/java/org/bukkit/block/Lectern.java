package org.bukkit.block;

import org.bukkit.inventory.BlockInventoryHolder;
import org.bukkit.inventory.Inventory;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表讲台.
 * <p>原文：
 * Represents a captured state of a lectern.
 */
public interface Lectern extends io.papermc.paper.block.TileStateInventoryHolder { // Paper - TileStateInventoryHolder

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取讲台上书翻到了哪一页.
     * <p>原文：
     * Get the current lectern page.
     *
     * @return 书的页码
     * <p>原文：current page
     */
    int getPage();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 把讲台上的书翻到哪一页.
     * 如果给定的页数大于讲台上书的页数, 则相关行为是未定义的.
     * <p>原文：
     * Set the current lectern page.
     *
     * If the page is greater than the number of pages of the book currently in
     * the inventory, then behavior is undefined.
     *
     * @param page 书的页码
     * <p>原文：new page
     */
    void setPage(int page);

    // Paper - moved to TileStateInventoryHolder
}
