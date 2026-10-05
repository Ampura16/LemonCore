package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import io.papermc.paper.text.Filtered;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.checkerframework.common.value.qual.IntRange;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有成书的内容和元数据。
 * <p>原文：
 * Holds the contents and metadata of a Written Book.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#WRITTEN_BOOK_CONTENT
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface WrittenBookContent {

    @Contract(value = "_, _ -> new", pure = true)
    static WrittenBookContent.Builder writtenBookContent(final String title, final String author) {
        return writtenBookContent(Filtered.of(title, null), author);
    }

    @Contract(value = "_, _ -> new", pure = true)
    static WrittenBookContent.Builder writtenBookContent(final Filtered<String> title, final String author) {
        return ItemComponentTypesBridge.bridge().writtenBookContent(title, author);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此书的标题。
     * <p>原文：
     * Title of this book.
     *
     * @return 标题
     * <p>原文：title
     */
    @Contract(pure = true)
    Filtered<String> title();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此书作者的玩家名称。
     * <p>原文：
     * Player name of the author of this book.
     *
     * @return 作者
     * <p>原文：author
     */
    @Contract(pure = true)
    String author();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此书被复制的次数（0 = 原稿）。
     * <p>原文：
     * The number of times this book has been copied (0 = original).
     *
     * @return 代数（原稿/副本等）
     * <p>原文：generation
     */
    @Contract(pure = true)
    @IntRange(from = 0, to = 3) int generation();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此书的页面。
     * <p>原文：
     * Gets the pages of this book.
     *
     * @return 页面
     * <p>原文：pages
     */
    @Contract(pure = true)
    @Unmodifiable List<Filtered<Component>> pages();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示此书中的聊天组件是否已被解析（实体选择器、分数已替换）。如果为 {@code false}，将在玩家打开时解析。
     * <p>原文：
     * If the chat components in this book have already been resolved (entity selectors, scores substituted).
     * If {@code false}, will be resolved when opened by a player.
     *
     * @return 是否已解析
     * <p>原文：resolved
     */
    @Contract(pure = true)
    boolean resolved();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link WrittenBookContent} 的构建器。
     * <p>原文：
     * Builder for {@link WrittenBookContent}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<WrittenBookContent> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此书的标题。
         * <p>原文：
         * Sets the title of this book.
         *
         * @param title 标题
         * <p>原文：the title
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #title()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder title(String title);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此书的可过滤标题。
         * <p>原文：
         * Sets the filterable title of this book.
         *
         * @param title 标题
         * <p>原文：the title
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #title()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder filteredTitle(Filtered<String> title);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此书的作者。
         * <p>原文：
         * Sets the author of this book.
         *
         * @param author 作者
         * <p>原文：the author
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #author()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder author(String author);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此书的代数（原稿/副本等）。
         * <p>原文：
         * Sets the generation of this book.
         *
         * @param generation 代数，[0-3]
         * <p>原文：the generation, [0-3]
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #generation()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder generation(@IntRange(from = 0, to = 3) int generation);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此书中的聊天组件是否已被解析（实体选择器、分数已替换）。如果为 {@code false}，将在玩家打开时解析。
         * <p>原文：
         * Sets if the chat components in this book have already been resolved (entity selectors, scores substituted).
         * If {@code false}, will be resolved when opened by a player.
         *
         * @param resolved 是否已解析
         * <p>原文：resolved
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #resolved()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder resolved(boolean resolved);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此书添加一个页面。
         * <p>原文：
         * Adds a page to this book.
         *
         * @param page 页面
         * <p>原文：the page
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #pages()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addPage(ComponentLike page);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此书添加多个页面。
         * <p>原文：
         * Adds pages to this book.
         *
         * @param page 页面
         * <p>原文：the pages
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #pages()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addPages(List<? extends ComponentLike> page);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此书添加一个可过滤的页面。
         * <p>原文：
         * Adds a filterable page to this book.
         *
         * @param page 页面
         * <p>原文：the page
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #pages()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addFilteredPage(Filtered<? extends ComponentLike> page);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此书添加多个可过滤的页面。
         * <p>原文：
         * Adds filterable pages to this book.
         *
         * @param pages 页面
         * <p>原文：the pages
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #pages()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addFilteredPages(List<Filtered<? extends ComponentLike>> pages);
    }
}
