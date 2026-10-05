package org.bukkit.inventory.meta;

import java.util.List;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Material;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表{@link Material#WRITTEN_BOOK 成书}，有标题，作者，页面.
 * <p>原文：
 * Represents a {@link Material#WRITTEN_BOOK} that can have a title, an author,
 * and pages.
 * <p>
 * Before using this type, make sure to check the itemstack's material with
 * {@link org.bukkit.inventory.ItemStack#getType()}. {@code instanceof} on
 * the meta instance is not sufficient due to unusual inheritance
 * with relation to {@link WritableBookMeta}.
 * <p>
 * <b>Warning:</b> in an upcoming version of Paper, this interface will no
 * longer extend Adventure's {@link net.kyori.adventure.inventory.Book}.
 */
public interface BookMeta extends WritableBookMeta, net.kyori.adventure.inventory.Book { // Paper - adventure

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表成书的代次 (或副本级别).
     * <p>原文：
     * Represents the generation (or level of copying) of a written book
     */
    enum Generation {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 写进书与笔的书(原著). 可被复制. (默认值)
         * <p>原文：
         * Book written into a book-and-quill. Can be copied. (Default value)
         */
        ORIGINAL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 原著的副本. 可被复制.
         * <p>原文：
         * Book that was copied from an original. Can be copied.
         */
        COPY_OF_ORIGINAL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 副本的副本. 不可复制.
         * <p>原文：
         * Book that was copied from a copy of an original. Can't be copied.
         */
        COPY_OF_COPY,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 未被使用; 无法被玩家取得. 不可复制.
         * <p>原文：
         * Unused; unobtainable by players. Can't be copied.
         */
        TATTERED;
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测书是否存在标题.
     * <p>原文：
     * Checks for the existence of a title in the book.
     *
     * @return 这本书是否有标题
     * <p>原文：true if the book has a title
     */
    boolean hasTitle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这本书的标题.
     * <p>
     * 插件应该在调用这个方法之前检测hasTitle()是否返回true.
     * <p>原文：
     * Gets the title of the book.
     * <p>
     * Plugins should check that hasTitle() returns true before calling this
     * method.
     *
     * @return 这本书的标题
     * <p>原文：the title of the book
     */
    @Nullable
    String getTitle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这本书的标题.
     * <p>
     * 仅限32个字符.如果设置为null将移除标题.
     * <p>原文：
     * Sets the title of the book.
     * <p>
     * Limited to 32 characters. Removes title when given null.
     *
     * @param title 要设置的标题
     * <p>原文：the title to set
     * @return 如果成功设置标题则为true
     * <p>原文：true if the title was successfully set
     */
    boolean setTitle(@Nullable String title);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测这本书是否存在作者.
     * <p>原文：
     * Checks for the existence of an author in the book.
     *
     * @return 这本书是否有作者
     * <p>原文：true if the book has an author
     */
    boolean hasAuthor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这本书的作者.
     * <p>
     * 插件应该在调用这个方法之前检测hasAuthor()是否返回true.
     * <p>原文：
     * Gets the author of the book.
     * <p>
     * Plugins should check that hasAuthor() returns true before calling this
     * method.
     *
     * @return 这本书的作者
     * <p>原文：the author of the book
     */
    @Nullable
    String getAuthor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这本书的作者.如果设置为null将移除作者.
     * <p>原文：
     * Sets the author of the book. Removes author when given null.
     *
     * @param author 这本书的作者
     * <p>原文：the author to set
     */
    void setAuthor(@Nullable String author);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测本书是否存在代次级别.
     * <p>原文：
     * Checks for the existence of generation level in the book.
     *
     * @return 本书是否存在代次级别
     * <p>原文：true if the book has a generation level
     */
    boolean hasGeneration();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取本书的代次.
     * 插件应该在调用这个方法之前检测 hasGeneration() 是否返回 <code>true</code>.
     * <p>原文：
     * Gets the generation of the book.
     * <p>
     * Plugins should check that hasGeneration() returns true before calling
     * this method.
     *
     * @return 本书的代次
     * <p>原文：the generation of the book
     */
    @Nullable
    Generation getGeneration();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置本书的代次. 当参数为 null 时移除代次.
     * <p>原文：
     * Sets the generation of the book. Removes generation when given null.
     *
     * @param generation 要设置的代次
     * <p>原文：the generation to set
     */
    void setGeneration(@Nullable Generation generation);

    @Override
    @NotNull
    BookMeta clone();

    // Paper start - adventure
    //<editor-fold desc="deprecations" defaultstate="collapsed">
    /**
     * @deprecated use {@link #page(int)}
     */
    @Deprecated
    @Override
    @NotNull String getPage(int page);

    /**
     * @deprecated use {@link #page(int, net.kyori.adventure.text.Component)}
     */
    @Deprecated
    @Override
    void setPage(int page, @NotNull String data);

    /**
     * @deprecated use {@link #pages()}
     */
    @Deprecated
    @Override
    @NotNull List<String> getPages();

    /**
     * @deprecated use {@link #pages(List)}
     */
    @Deprecated
    @Override
    void setPages(@NotNull List<String> pages);

    /**
     * @deprecated use {@link #pages(net.kyori.adventure.text.Component...)}
     */
    @Deprecated
    @Override
    void setPages(@NotNull String... pages);

    /**
     * @deprecated use {@link #addPages(net.kyori.adventure.text.Component...)}
     */
    @Deprecated
    @Override
    void addPage(@NotNull String... pages);
    //</editor-fold>

    /**
     * Gets the title of the book.
     * <p>
     * Plugins should check that hasTitle() returns true before calling this
     * method.
     *
     * @return the title of the book
     */
    @Override
    net.kyori.adventure.text.@Nullable Component title();

    /**
     * Sets the title of the book.
     * <p>
     * Limited to 32 characters. Removes title when given null.
     *
     * @param title the title to set
     * @return the same {@link BookMeta} instance
     */
    @org.jetbrains.annotations.Contract(value = "_ -> this", pure = false)
    @Override
    @NotNull BookMeta title(net.kyori.adventure.text.@Nullable Component title);

    /**
     * Gets the author of the book.
     * <p>
     * Plugins should check that hasAuthor() returns true before calling this
     * method.
     *
     * @return the author of the book
     */
    @Override
    net.kyori.adventure.text.@Nullable Component author();

    /**
     * Sets the author of the book. Removes author when given null.
     *
     * @param author the author to set
     * @return the same {@link BookMeta} instance
     */
    @org.jetbrains.annotations.Contract(value = "_ -> this", pure = false)
    @Override
    @NotNull BookMeta author(net.kyori.adventure.text.@Nullable Component author);


    /**
     * Gets the specified page in the book. The page must exist.
     * <p>
     * Pages are 1-indexed.
     *
     * @param page the page number to get, in range [1, getPageCount()]
     * @return the page from the book
     */
    net.kyori.adventure.text.@NotNull Component page(int page);

    /**
     * Sets the specified page in the book. Pages of the book must be
     * contiguous.
     * <p>
     * The data can be up to 1024 characters in length, additional characters
     * are truncated.
     * <p>
     * Pages are 1-indexed.
     *
     * @param page the page number to set, in range [1, getPageCount()]
     * @param data the data to set for that page
     */
    void page(int page, net.kyori.adventure.text.@NotNull Component data);

    /**
     * Adds new pages to the end of the book. Up to a maximum of 100 pages with
     * 1024 characters per page.
     *
     * @param pages A list of strings, each being a page
     */
    void addPages(net.kyori.adventure.text.@NotNull Component @NotNull ... pages);

    /**
     * @deprecated BookMeta is mutable, there is no need for a builder.
     */
    @Deprecated(forRemoval = true, since = "1.21.11")
    interface BookMetaBuilder extends net.kyori.adventure.inventory.Book.Builder {
        /**
         * @deprecated BookMeta is mutable, there is no need for a builder.
         */
        @Deprecated(forRemoval = true, since = "1.21.11")
        @Override
        @NotNull BookMetaBuilder title(net.kyori.adventure.text.@Nullable Component title);

        /**
         * @deprecated BookMeta is mutable, there is no need for a builder.
         */
        @Deprecated(forRemoval = true, since = "1.21.11")
        @Override
        @NotNull BookMetaBuilder author(net.kyori.adventure.text.@Nullable Component author);

        /**
         * @deprecated BookMeta is mutable, there is no need for a builder.
         */
        @Deprecated(forRemoval = true, since = "1.21.11")
        @Override
        @NotNull BookMetaBuilder addPage(net.kyori.adventure.text.@NotNull Component page);

        /**
         * @deprecated BookMeta is mutable, there is no need for a builder.
         */
        @Deprecated(forRemoval = true, since = "1.21.11")
        @Override
        @NotNull BookMetaBuilder pages(net.kyori.adventure.text.@NotNull Component @NotNull ... pages);

        /**
         * @deprecated BookMeta is mutable, there is no need for a builder.
         */
        @Deprecated(forRemoval = true, since = "1.21.11")
        @Override
        @NotNull BookMetaBuilder pages(java.util.@NotNull Collection<net.kyori.adventure.text.Component> pages);

        /**
         * @deprecated BookMeta is mutable, there is no need for a builder.
         */
        @Deprecated(forRemoval = true, since = "1.21.11")
        @Override
        @NotNull BookMeta build();
    }

    /**
     * @deprecated BookMeta is mutable, there is no need for a builder.
     */
    @Deprecated(forRemoval = true, since = "1.21.11")
    @Override
    @NotNull BookMetaBuilder toBuilder();
    // Paper end

    // Spigot start
    public class Spigot {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获取书本指定书页的内容. 指定页码必须存在.
         * <p>原文：
         * Gets the specified page in the book. The given page must exist.
         *
         * @param page 页码
         * <p>原文：the page number to get
         * @return 指定书页的内容
         * <p>原文：the page from the book
         * @deprecated in favour of {@link #page(int)}
         */
        @NotNull
        @Deprecated // Paper
        public BaseComponent[] getPage(int page) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 设置书本指定书页的内容. 页码必须是相邻的.
         * <p>
         * 数据最长可达256个字符, 修饰性的字符不会被统计在内.
         * <p>原文：
         * Sets the specified page in the book. Pages of the book must be
         * contiguous.
         * <p>
         * The data can be up to 256 characters in length, additional characters
         * are truncated.
         *
         * @param page 页码
         * <p>原文：the page number to set
         * @param data 为此页码设置的内容数据
         * <p>原文：the data to set for that page
         * @deprecated in favour of {@link #page(int, net.kyori.adventure.text.Component)}
         */
        @Deprecated // Paper
        public void setPage(int page, @Nullable BaseComponent... data) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 获取书本全部书页的内容.
         * <p>原文：
         * Gets all the pages in the book.
         *
         * @return 书本全部书页的内容
         * <p>原文：list of all the pages in the book
         * @deprecated in favour of {@link #pages()}
         */
        @NotNull
        @Deprecated // Paper
        public List<BaseComponent[]> getPages() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 清除书本所有书页, 并设置书本使用提供的书页内容.
         * 最多50页, 每页最长256个字符.
         * <p>原文：
         * Clears the existing book pages, and sets the book to use the provided
         * pages. Maximum 50 pages with 256 characters per page.
         *
         * @param pages 书页列表
         * <p>原文：A list of pages to set the book to use
         * @deprecated in favour of {@link #pages(java.util.List)}
         */
        @Deprecated // Paper
        public void setPages(@NotNull List<BaseComponent[]> pages) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 清除书本所有书页, 并设置书本使用提供的书页内容.
         * 最多50页, 每页最长256个字符.
         * <p>原文：
         * Clears the existing book pages, and sets the book to use the provided
         * pages. Maximum 50 pages with 256 characters per page.
         *
         * @param pages 聊天消息组件数组, 数组中的每一个元素将作为单独的书页
         * <p>原文：A list of component arrays, each being a page
         * @deprecated in favour of {@link #pages(net.kyori.adventure.text.Component...)}
         */
        @Deprecated // Paper
        public void setPages(@NotNull BaseComponent[]... pages) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 在书本末尾追加新的书页.
         * 书本最多容纳50页, 每页最长256个字符.
         * <p>原文：
         * Adds new pages to the end of the book. Up to a maximum of 50 pages
         * with 256 characters per page.
         *
         * @param pages 聊天消息组件数组, 数组中的每一个元素将作为单独的书页
         * <p>原文：A list of component arrays, each being a page
         * @deprecated in favour of {@link #addPages(net.kyori.adventure.text.Component...)}
         */
        @Deprecated // Paper
        public void addPage(@NotNull BaseComponent[]... pages) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }

    @NotNull
    Spigot spigot();
    // Spigot end
}
