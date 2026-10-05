package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import io.papermc.paper.text.Filtered;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有书与笔的页面。
 * <p>原文：
 * Holds the pages for a writable book.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#WRITABLE_BOOK_CONTENT
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface WritableBookContent {

    @Contract(value = "-> new", pure = true)
    static WritableBookContent.Builder writeableBookContent() {
        return ItemComponentTypesBridge.bridge().writeableBookContent();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 持有此组件中可书写的页面。
     * <p>原文：
     * Holds the pages that can be written to for this component.
     *
     * @return 页面（可过滤对象形式）
     * <p>原文：pages, as filtered objects
     */
    @Contract(pure = true)
    @Unmodifiable List<Filtered<String>> pages();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link WritableBookContent} 的构建器。
     * <p>原文：
     * Builder for {@link WritableBookContent}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<WritableBookContent> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加一个可书写的页面。
         * <p>原文：
         * Adds a page that can be written to for this builder.
         *
         * @param page 页面
         * <p>原文：page
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #pages()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addPage(String page);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加多个可书写的页面。
         * <p>原文：
         * Adds pages that can be written to for this builder.
         *
         * @param pages 页面
         * <p>原文：pages
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #pages()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addPages(List<String> pages);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加一个可过滤的、可书写的页面。
         * <p>原文：
         * Adds a filterable page that can be written to for this builder.
         *
         * @param page 页面
         * <p>原文：page
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #pages()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addFilteredPage(Filtered<String> page);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加多个可过滤的、可书写的页面。
         * <p>原文：
         * Adds filterable pages that can be written to for this builder.
         *
         * @param pages 页面
         * <p>原文：pages
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #pages()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addFilteredPages(List<Filtered<String>> pages);
    }
}
