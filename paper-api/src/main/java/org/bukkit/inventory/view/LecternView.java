package org.bukkit.inventory.view;

import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.LecternInventory;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 一个提供与讲台视图数据相关额外方法的 {@link InventoryView} 实例。
 * <p>原文：
 * An instance of {@link InventoryView} which provides extra methods related to
 * lectern view data.
 */
public interface LecternView extends InventoryView {

    @NotNull
    @Override
    LecternInventory getTopInventory();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取LecternView当前所在的页码。
     * <p>原文：
     * Gets the page that the LecternView is on.
     *
     * @return 书本当前的页码
     * <p>
     * 原文：Gets the page that the LecternView is on.
     * <p>原文：The page the book is on
     */
    int getPage();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置讲台书本的页码。
     * <p>原文：
     * Sets the page of the lectern book.
     *
     * @param page 讲台书本的页码
     * <p>
     * 原文：Sets the page of the lectern book.
     * <p>原文：the lectern book page
     */
    void setPage(final int page);
}
