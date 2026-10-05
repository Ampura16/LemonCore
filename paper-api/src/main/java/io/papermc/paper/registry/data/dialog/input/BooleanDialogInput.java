package io.papermc.paper.registry.data.dialog.input;

import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 布尔型对话框输入。
 * <p>
 * 通过 {@link DialogInput#bool(String, Component, boolean, String, String)}
 * </p>
 * 创建
 * <p>原文：
 * A boolean dialog input.
 * <p>Created via {@link DialogInput#bool(String, Component, boolean, String, String)}</p>
 */
@ApiStatus.NonExtendable
public non-sealed interface BooleanDialogInput extends DialogInput {

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
     * 输入的初始值。
     * <p>原文：
     * The initial value of the input.
     *
     * @return 如果输入初始为 true 则为 true，否则为 false
     * <p>原文：true if the input is initially true, false otherwise
     */
    @Contract(pure = true)
    boolean initial();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 值为 true 时输入在模板中的值。
     * <p>原文：
     * The input's value in a template when the value is true.
     *
     * @return 输入为 true 时使用的字符串
     * <p>原文：the string to use when the input is true
     */
    @Contract(pure = true)
    String onTrue();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 值为 false 时输入在模板中的值。
     * <p>原文：
     * The input's value in a template when the value is false.
     *
     * @return 输入为 false 时使用的字符串
     * <p>原文：the string to use when the input is false
     */
    @Contract(pure = true)
    String onFalse();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 布尔型对话框输入的构建器。
     * <p>
     * 通过 {@link DialogInput#bool(String, Component)}
     * </p>
     * 创建
     * <p>原文：
     * A builder for a boolean dialog input.
     * <p>Created via {@link DialogInput#bool(String, Component)}</p>
     */
    @ApiStatus.NonExtendable
    interface Builder {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置输入的初始值。
         * <p>原文：
         * Sets the initial value of the input.
         *
         * @param initial 如果输入初始为 true 则为 true，否则为 false
         * <p>原文：true if the input is initially true, false otherwise
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder initial(boolean initial);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置值为 true 时输入在模板中的值。
         * <p>原文：
         * Sets the input's value in a template when the value is true.
         *
         * @param onTrue 输入为 true 时使用的字符串
         * <p>原文：the string to use when the input is true
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder onTrue(String onTrue);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置值为 false 时输入在模板中的值。
         * <p>原文：
         * Sets the input's value in a template when the value is false.
         *
         * @param onFalse 输入为 false 时使用的字符串
         * <p>原文：the string to use when the input is false
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder onFalse(String onFalse);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 使用已配置的值构建实例。
         * <p>原文：
         * Builds the instance with the configured values.
         *
         * @return 新实例
         * <p>原文：a new instance
         */
        @Contract(value = "-> new", pure = true)
        BooleanDialogInput build();
    }
}
