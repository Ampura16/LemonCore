package io.papermc.paper.registry.data.dialog.type;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.set.RegistrySet;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示显示对话框列表的对话框。
 * <p>原文：
 * Represents a dialog that displays a list of dialogs.
 * @see DialogType#dialogList(RegistrySet, ActionButton, int, int)
 */
@ApiStatus.NonExtendable
public non-sealed interface DialogListType extends DialogType {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回对话框列表中要显示的对话框集合。
     * <p>原文：
     * Returns the set of dialogs to display in the dialog list.
     *
     * @return 对话框集合
     * <p>原文：the set of dialogs
     */
    @Contract(pure = true)
    RegistrySet<Dialog> dialogs();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回退出对话框的操作按钮；如果没有退出操作则返回 null。
     * <p>原文：
     * Returns the action button to exit the dialog, or null if there is no exit action.
     *
     * @return 退出操作按钮，或 null
     * <p>原文：the exit action button, or null
     */
    @Contract(pure = true)
    @Nullable ActionButton exitAction();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回对话框列表中显示的列数。
     * <p>原文：
     * Returns the number of columns to display in the dialog list.
     *
     * @return 列数
     * <p>原文：the number of columns
     */
    @Contract(pure = true)
    @Positive int columns();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回对话框列表中每个按钮的宽度。
     * <p>原文：
     * Returns the width of each button in the dialog list.
     *
     * @return 各按钮的宽度
     * <p>原文：the width of the buttons
     */
    @Contract(pure = true)
    @Range(from = 1, to = 1024) int buttonWidth();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于创建对话框列表类型的构建器。
     * <p>原文：
     * A builder for creating a dialog list type.
     */
    @ApiStatus.NonExtendable
    interface Builder {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置退出对话框的操作按钮；如果没有退出操作则设为 null。
         * <p>原文：
         * Sets the action button to exit the dialog, or null if there is no exit action.
         *
         * @param exitAction 退出操作按钮，或 null
         * <p>原文：the exit action button, or null
         * @return 构建器
         * <p>原文：the builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder exitAction(final @Nullable ActionButton exitAction);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置对话框列表中显示的列数。
         * <p>原文：
         * Sets the number of columns to display in the dialog list.
         *
         * @param columns 列数
         * <p>原文：the number of columns
         * @return 构建器
         * <p>原文：the builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder columns(final @Positive int columns);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置对话框列表中每个按钮的宽度。
         * <p>原文：
         * Sets the width of each button in the dialog list.
         *
         * @param buttonWidth 各按钮的宽度
         * <p>原文：the width of the buttons
         * @return 构建器
         * <p>原文：the builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder buttonWidth(final @Range(from = 1, to = 1024) int buttonWidth);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 构建对话框列表类型。
         * <p>原文：
         * Builds the dialog list type.
         *
         * @return 构建完成的对话框列表类型
         * <p>原文：the built dialog list type
         */
        @Contract(value = "-> new", pure = true)
        DialogListType build();
    }
}
