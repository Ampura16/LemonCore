package io.papermc.paper.registry.data.dialog.input;

import io.papermc.paper.registry.data.dialog.DialogInstancesProvider;
import net.kyori.adventure.text.Component;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 文本对话框输入。
 * <p>
 * 通过 {@link DialogInput#text(String, int, Component, boolean, String, int, MultilineOptions)}
 * </p>
 * 创建
 * <p>原文：
 * A text dialog input.
 * <p>Created via {@link DialogInput#text(String, int, Component, boolean, String, int, MultilineOptions)}</p>
 */
@ApiStatus.NonExtendable
public non-sealed interface TextDialogInput extends DialogInput {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 输入的宽度。
     * <p>原文：
     * The width of the input.
     *
     * @return 输入框的宽度
     * <p>原文：the width of the input
     */
    @Contract(pure = true)
    @Range(from = 1, to = 1024) int width();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 输入的标签。
     * <p>原文：
     * The label for the input.
     *
     * @return 标签组件
     * <p>原文：the label component
     */
    @Contract(pure = true)
    Component label();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 标签是否可见。
     * <p>原文：
     * Whether the label should be visible.
     *
     * @return 如果标签可见则为 true，否则为 false
     * <p>原文：true if the label is visible, false otherwise
     */
    @Contract(pure = true)
    boolean labelVisible();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 输入的初始值。
     * <p>原文：
     * The initial value of the input.
     *
     * @return 初始文本
     * <p>原文：the initial text
     */
    @Contract(pure = true)
    String initial();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 标签的格式（翻译键或格式字符串）。
     * <p>原文：
     * The format for the label (a translation key or format string).
     *
     * @return 标签格式
     * <p>原文：the label format
     */
    @Contract(pure = true)
    @Positive int maxLength();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 输入的多行选项；未设置时为 null。
     * <p>原文：
     * The multiline options for the input, or null if not set.
     *
     * @return 多行选项
     * <p>原文：the multiline options
     */
    @Contract(pure = true)
    @Nullable MultilineOptions multiline();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示文本对话框输入的多行选项。
     * <p>原文：
     * Represents the multiline options for a text dialog input.
     */
    @ApiStatus.NonExtendable
    interface MultilineOptions {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 创建新的多行选项实例。
         * <p>原文：
         * Creates a new multiline options instance.
         *
         * @param maxLines 最大行数；如果未设置则为 null
         * <p>原文：the maximum number of lines, or null if not set
         * @param height 输入框的高度；如果未设置则为 null
         * <p>原文：the height of the input, or null if not set
         * @return 新的 MultilineOptions 实例
         * <p>原文：a new MultilineOptions instance
         */
        static MultilineOptions create(
            final @Positive @Nullable Integer maxLines,
            final @Range(from = 1, to = 512) @Nullable Integer height
        ) {
            return DialogInstancesProvider.instance().multilineOptions(maxLines, height);
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取最大行数。
         * <p>原文：
         * Gets the maximum number of lines.
         *
         * @return 最大行数；如果未设置则为 null
         * <p>原文：the maximum number of lines, or null if not set
         */
        @Contract(pure = true)
        @Positive @Nullable Integer maxLines();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 获取输入框的高度。
         * <p>原文：
         * Gets the height of the input.
         *
         * @return 输入框的高度；如果未设置则为 null
         * <p>原文：the height of the input, or null if not set
         */
        @Contract(pure = true)
        @Range(from = 1, to = 512) @Nullable Integer height();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 文本对话框输入的构建器。
     * <p>
     * 通过 {@link DialogInput#text(String, Component)}
     * </p>
     * 创建
     * <p>原文：
     * A builder for a text dialog input.
     * <p>Created via {@link DialogInput#text(String, Component)}</p>
     */
    @ApiStatus.NonExtendable
    interface Builder {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置输入的宽度。
         * <p>原文：
         * Sets the width of the input.
         *
         * @param width 输入框的宽度
         * <p>原文：the width of the input
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder width(final @Range(from = 1, to = 1024) int width);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置标签是否可见。
         * <p>原文：
         * Sets whether the label should be visible.
         *
         * @param labelVisible 如果标签应可见则为 true，否则为 false
         * <p>原文：true if the label should be visible, false otherwise
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder labelVisible(final boolean labelVisible);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置输入的初始值。
         * <p>原文：
         * Sets the initial value of the input.
         *
         * @param initial 输入的初始值
         * <p>原文：the initial value of the input
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder initial(final String initial);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置输入的最大长度。
         * <p>原文：
         * Sets the maximum length of the input.
         *
         * @param maxLength 输入的最大长度
         * <p>原文：the maximum length of the input
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder maxLength(final @Positive int maxLength);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置输入的多行选项。
         * <p>原文：
         * Sets the multiline options for the input.
         *
         * @param multiline 多行选项；如果未设置则为 null
         * <p>原文：the multiline options, or null if not set
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder multiline(final @Nullable MultilineOptions multiline);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 构建文本对话框输入。
         * <p>原文：
         * Builds the text dialog input.
         *
         * @return 文本对话框输入
         * <p>原文：the text dialog input
         */
        @Contract(value = "-> new", pure = true)
        TextDialogInput build();
    }
}
