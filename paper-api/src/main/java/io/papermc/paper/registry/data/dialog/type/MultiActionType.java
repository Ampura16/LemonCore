package io.papermc.paper.registry.data.dialog.type;

import io.papermc.paper.registry.data.dialog.ActionButton;
import java.util.List;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示允许执行多个操作的对话框。该对话框用于创建带有多个操作按钮的对话框，让用户从多个选项中选择。
 * <p>原文：
 * Represents a dialog that allows multiple actions to be performed.
 * This dialog is used to create dialogs with multiple action buttons, allowing users to choose from several options.
 * @see DialogType#multiAction(List, ActionButton, int)
 */
@ApiStatus.NonExtendable
public non-sealed interface MultiActionType extends DialogType {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此多操作对话框中可用的操作按钮列表。
     * <p>原文：
     * Returns the list of action buttons available in this multi-action dialog.
     *
     * @return 操作按钮的不可修改列表
     * <p>原文：an unmodifiable list of action buttons
     */
    @Contract(pure = true)
    @Unmodifiable List<ActionButton> actions();

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
     * 返回对话框中显示的列数。
     * <p>原文：
     * Returns the number of columns to display in the dialog.
     *
     * @return 列数
     * <p>原文：the number of columns
     */
    @Contract(pure = true)
    @Positive int columns();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于创建多操作对话框的构建器。
     * <p>原文：
     * A builder for creating a multi-action dialog.
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
         * 设置对话框中显示的列数。
         * <p>原文：
         * Sets the number of columns to display in the dialog.
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
         * 构建多操作对话框。
         * <p>原文：
         * Builds the multi-action dialog.
         *
         * @return 新实例
         * <p>原文：a new instance
         */
        @Contract(value = "-> new", pure = true)
        MultiActionType build();
    }
}
