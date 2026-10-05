package org.bukkit.inventory.meta;

import java.util.List;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一本书 ({@link Material#WRITABLE_BOOK} 或 {@link
 * Material#WRITTEN_BOOK})，可以拥有页面.
 * <p>原文：
 * Represents a book ({@link Material#WRITABLE_BOOK}) that can have pages.
 * <p>
 * For {@link Material#WRITTEN_BOOK}, use {@link BookMeta}.
 * <p>
 * Before using this type, make sure to check the itemstack's material with
 * {@link org.bukkit.inventory.ItemStack#getType()}. {@code instanceof} on
 * the meta instance is not sufficient due to unusual inheritance
 * with relation to {@link BookMeta}.
 */
public interface WritableBookMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查书中是否存在页面.
     * <p>原文：
     * Checks for the existence of pages in the book.
     *
     * @return 如果书中有页面则为 true
     * <p>原文：true if the book has pages
     */
    boolean hasPages();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取书中指定的页面。给定的页面必须存在.
     * <p>
     * 页面从 1 开始索引.
     * <p>原文：
     * Gets the specified page in the book. The given page must exist.
     * <p>
     * Pages are 1-indexed.
     *
     * @param page 要获取的页码，范围为 [1, getPageCount()]
     * <p>原文：the page number to get, in range [1, getPageCount()]
     * @return 书中的页面
     * <p>原文：the page from the book
     */
    @NotNull
    String getPage(int page);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置书中指定的页面。书中的页面必须是连续的.
     * <p>
     * 数据长度最多为 1024 个字符，多余的字符将被截断.
     * <p>
     * 页面从 1 开始索引.
     * <p>原文：
     * Sets the specified page in the book. Pages of the book must be
     * contiguous.
     * <p>
     * The data can be up to 1024 characters in length, additional characters
     * are truncated.
     * <p>
     * Pages are 1-indexed.
     *
     * @param page 要设置的页码，范围为 [1, getPageCount()]
     * <p>原文：the page number to set, in range [1, getPageCount()]
     * @param data 要为该页面设置的数据
     * <p>原文：the data to set for that page
     */
    void setPage(int page, @NotNull String data);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取书中所有页面.
     * <p>原文：
     * Gets all the pages in the book.
     *
     * @return 书中所有页面的列表
     * <p>原文：list of all the pages in the book
     */
    @NotNull
    List<String> getPages();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 清除现有的书页，并将书设置为使用提供的页面。最多 100 页，每页 1024 个字符.
     * <p>原文：
     * Clears the existing book pages, and sets the book to use the provided
     * pages. Maximum 100 pages with 1024 characters per page.
     *
     * @param pages 要设置的页面列表
     * <p>原文：A list of pages to set the book to use
     */
    void setPages(@NotNull List<String> pages);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 清除现有的书页，并将书设置为使用提供的页面。最多 100 页，每页 1024 个字符.
     * <p>原文：
     * Clears the existing book pages, and sets the book to use the provided
     * pages. Maximum 100 pages with 1024 characters per page.
     *
     * @param pages 字符串列表，每个字符串为一页
     * <p>原文：A list of strings, each being a page
     */
    void setPages(@NotNull String... pages);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向书末尾添加新页面。最多 100 页，每页 1024 个字符.
     * <p>原文：
     * Adds new pages to the end of the book. Up to a maximum of 100 pages with
     * 1024 characters per page.
     *
     * @param pages 字符串列表，每个字符串为一页
     * <p>原文：A list of strings, each being a page
     */
    void addPage(@NotNull String... pages);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取书中的页数.
     * <p>原文：
     * Gets the number of pages in the book.
     *
     * @return 书中的页数
     * <p>原文：the number of pages in the book
     */
    int getPageCount();

    @Override
    @NotNull
    WritableBookMeta clone();
}
