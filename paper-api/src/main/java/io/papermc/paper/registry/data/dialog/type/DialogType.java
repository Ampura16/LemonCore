package io.papermc.paper.registry.data.dialog.type;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.data.dialog.ActionButton;
import io.papermc.paper.registry.data.dialog.DialogInstancesProvider;
import io.papermc.paper.registry.set.RegistrySet;
import java.util.List;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示一种对话框类型。
 * <p>原文：
 * Represents a type of dialog.
 */
public sealed interface DialogType permits ConfirmationType, DialogListType, MultiActionType, NoticeType, ServerLinksType {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用指定的“是”和“否”按钮创建确认对话框。
     * <p>原文：
     * Creates a confirmation dialog with the specified yes and no buttons.
     *
     * @param yesButton 确认操作的按钮
     * <p>原文：the button to confirm the action
     * @param noButton 取消操作的按钮
     * <p>原文：the button to cancel the action
     * @return 新实例
     * <p>原文：a new instance
     */
    @Contract(value = "_, _ -> new", pure = true)
    static ConfirmationType confirmation(final ActionButton yesButton, final ActionButton noButton) {
        return DialogInstancesProvider.instance().confirmation(yesButton, noButton);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用指定的对话框、退出操作、列数和按钮宽度创建对话框列表对话框。
     * <p>原文：
     * Creates a dialog list dialog with the specified dialogs, exit action, columns, and button width.
     *
     * @param dialogs 要显示的对话框集合
     * <p>原文：the set of dialogs to display
     * @param exitAction 退出对话框的操作按钮
     * <p>原文：the action button to exit the dialog
     * @param columns 对话框中显示的列数
     * <p>原文：the number of columns to display in the dialog
     * @param buttonWidth 对话框中每个按钮的宽度
     * <p>原文：the width of each button in the dialog
     * @return 新实例
     * <p>原文：a new instance
     */
    @Contract(value = "_, _, _, _ -> new", pure = true)
    static DialogListType dialogList(
        final RegistrySet<Dialog> dialogs,
        final @Nullable ActionButton exitAction,
        final @Positive int columns,
        final @Range(from = 1, to = 1024) int buttonWidth
    ) {
        return dialogList(dialogs).exitAction(exitAction).columns(columns).buttonWidth(buttonWidth).build();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用指定对话框创建对话框列表构建器。
     * <p>原文：
     * Creates a dialog list builder with the specified dialogs.
     *
     * @param dialogs 要显示的对话框集合
     * <p>原文：the set of dialogs to display
     * @return 新的构建器实例
     * <p>原文：a new builder instance
     */
    @Contract(value = "_ -> new", pure = true)
    static DialogListType.Builder dialogList(final RegistrySet<Dialog> dialogs) {
        return DialogInstancesProvider.instance().dialogList(dialogs);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用指定的操作、退出操作和列数创建多操作对话框。
     * <p>原文：
     * Creates a multi-action dialog with the specified actions, exit action, and number of columns.
     *
     * @param actions 要显示的操作按钮列表
     * <p>原文：the list of action buttons to display
     * @param exitAction 退出对话框的操作按钮
     * <p>原文：the action button to exit the dialog
     * @param columns 对话框中显示的列数
     * <p>原文：the number of columns to display in the dialog
     * @return 新实例
     * <p>原文：a new instance
     */
    @Contract(value = "_, _, _ -> new", pure = true)
    static MultiActionType multiAction(
        final List<ActionButton> actions,
        final @Nullable ActionButton exitAction,
        final @Positive int columns
    ) {
        return multiAction(actions).exitAction(exitAction).columns(columns).build();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用指定操作创建多操作对话框构建器。
     * <p>原文：
     * Creates a multi-action dialog builder with the specified actions.
     *
     * @param actions 要显示的操作按钮列表
     * <p>原文：the list of action buttons to display
     * @return 新的构建器实例
     * <p>原文：a new builder instance
     */
    @Contract(value = "_ -> new", pure = true)
    static MultiActionType.Builder multiAction(final List<ActionButton> actions) {
        return DialogInstancesProvider.instance().multiAction(actions);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用默认操作按钮创建通知对话框。
     * <p>原文：
     * Creates a notice dialog with the default action button.
     *
     * @return 新实例
     * <p>原文：a new instance
     */
    @Contract(value = "-> new", pure = true)
    static NoticeType notice() {
        return DialogInstancesProvider.instance().notice();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用指定操作按钮创建通知对话框。
     * <p>原文：
     * Creates a notice dialog with the specified action button.
     *
     * @param action 通知中显示的操作按钮
     * <p>原文：the action button to display in the notice
     * @return 新实例
     * <p>原文：a new instance
     */
    @Contract(value = "_ -> new", pure = true)
    static NoticeType notice(final ActionButton action) {
        return DialogInstancesProvider.instance().notice(action);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用指定的退出操作、列数和按钮宽度创建服务器链接对话框。
     * <p>原文：
     * Creates a server links dialog with the specified exit action, number of columns, and button width.
     *
     * @param exitAction 退出对话框的操作按钮
     * <p>原文：the action button to exit the dialog
     * @param columns 对话框中显示的列数
     * <p>原文：the number of columns to display in the dialog
     * @param buttonWidth 对话框中每个按钮的宽度
     * <p>原文：the width of each button in the dialog
     * @return 新实例
     * <p>原文：a new instance
     */
    @Contract(value = "_, _, _ -> new", pure = true)
    static ServerLinksType serverLinks(
        final @Nullable ActionButton exitAction,
        final @Positive int columns,
        final @Range(from = 1, to = 1024) int buttonWidth
    ) {
        return DialogInstancesProvider.instance().serverLinks(exitAction, columns, buttonWidth);
    }
}
