package io.papermc.paper.registry.data.dialog.input;

import net.kyori.adventure.text.Component;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 数值范围对话框输入。
 * <p>
 * 通过 {@link DialogInput#numberRange(String, int, Component, String, float, float, Float, Float)}
 * </p>
 * 创建
 * <p>原文：
 * A number range dialog input.
 * <p>Created via {@link DialogInput#numberRange(String, int, Component, String, float, float, Float, Float)}</p>
 */
@ApiStatus.NonExtendable
public non-sealed interface NumberRangeDialogInput extends DialogInput {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 输入的宽度。
     * <p>原文：
     * The width of the input.
     *
     * @return 宽度
     * <p>原文：the width
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
     * 标签的格式，可以是翻译键或格式字符串。
     * <p>
     * 示例：{@code "%s: %s"} 或 {@code "options.generic_value"}
     * </p>
     * <p>原文：
     * The format for the label, which can be a translation key or a format string.
     * <p>Example: {@code "%s: %s"} or {@code "options.generic_value"}</p>
     *
     * @return 标签格式
     * <p>原文：the label format
     */
    @Contract(pure = true)
    String labelFormat();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 范围的起点。
     * <p>原文：
     * The start of the range.
     *
     * @return 起始值
     * <p>原文：the start value
     */
    @Contract(pure = true)
    float start();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 范围的终点。
     * <p>原文：
     * The end of the range.
     *
     * @return 终点值
     * <p>原文：the end value
     */
    @Contract(pure = true)
    float end();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 输入的初始值；未设置时为 null。
     * <p>原文：
     * The initial value of the input, or null if not set.
     *
     * @return 初始值，或 null
     * <p>原文：the initial value, or null
     */
    @Contract(pure = true)
    @Nullable Float initial();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 输入的步长；未设置时为 null。
     * <p>原文：
     * The step size for the input, or null if not set.
     *
     * @return 步长，或 null
     * <p>原文：the step size, or null
     */
    @Contract(pure = true)
    @Positive @Nullable Float step();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于创建 {@link NumberRangeDialogInput} 实例的构建器。
     * <p>
     * 通过 {@link DialogInput#numberRange(String, Component, float, float)}
     * </p>
     * 创建
     * <p>原文：
     * A builder for creating instances of {@link NumberRangeDialogInput}.
     * <p>Created via {@link DialogInput#numberRange(String, Component, float, float)}</p>
     */
    @ApiStatus.NonExtendable
    interface Builder {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置输入的宽度。
         * <p>原文：
         * Sets the width of the input.
         *
         * @param width 宽度
         * <p>原文：the width
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder width(@Range(from = 1, to = 1024) int width);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置标签的格式。
         * <p>
         * 示例：{@code "%s: %s"} 或 {@code "options.generic_value"}
         * </p>
         * <p>原文：
         * Sets the format for the label.
         * <p>Example: {@code "%s: %s"} or {@code "options.generic_value"}</p>
         *
         * @param labelFormat 标签格式
         * <p>原文：the label format
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder labelFormat(String labelFormat);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置输入的初始值。
         * <p>原文：
         * Sets the initial value for the input.
         *
         * @param initial 初始值；如果未设置则为 null
         * <p>原文：the initial value, or null if not set
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder initial(@Nullable Float initial);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置范围的步长。
         * <p>原文：
         * Sets the step of the range.
         *
         * @param step 步长；如果未设置则为 null
         * <p>原文：the step size, or null if not set
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder step(@Positive @Nullable Float step);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 使用已配置的值构建实例。
         * <p>原文：
         * Builds the instance with the configured values.
         *
         * @return 新实例
         * <p>原文：a new instance
         */
        @Contract(pure = true, value = "-> new")
        NumberRangeDialogInput build();
    }
}
