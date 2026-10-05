package io.papermc.paper.registry.data.dialog;

import io.papermc.paper.registry.data.dialog.body.DialogBody;
import io.papermc.paper.registry.data.dialog.input.DialogInput;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.util.Index;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示所有对话框的基础。
 * <p>原文：
 * Represents the base of all dialogs.
 */
@ApiStatus.NonExtendable
public interface DialogBase {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的对话框基础。
     * <p>原文：
     * Creates a new dialog base.
     *
     * @param title 对话框的标题
     * <p>原文：the title of the dialog
     * @param externalTitle 对话框的外部标题；如果未设置则为 null
     * <p>原文：the external title of the dialog, or null if not set
     * @param canCloseWithEscape 对话框能否通过“Esc”键关闭
     * <p>原文：if the dialog can be closed with the "escape" keybind
     * @param pause 对话框打开时是否应暂停游戏（仅单人游戏）
     * <p>原文：if the dialog should pause the game when opened (single-player only)
     * @param afterAction 对话框关闭后要执行的操作
     * <p>原文：the action to take after the dialog is closed
     * @param body 对话框的主体
     * <p>原文：the body of the dialog
     * @param inputs 对话框的输入项
     * <p>原文：the inputs of the dialog
     * @return 新的对话框基础实例
     * <p>原文：a new dialog base instance
     */
    @Contract(value = "_, _, _, _, _, _, _ -> new", pure = true)
    static DialogBase create(
        final Component title,
        final @Nullable Component externalTitle,
        final boolean canCloseWithEscape,
        final boolean pause,
        final DialogAfterAction afterAction,
        final List<? extends DialogBody> body,
        final List<? extends DialogInput> inputs
    ) {
        return builder(title).externalTitle(externalTitle).canCloseWithEscape(canCloseWithEscape).pause(pause).afterAction(afterAction).body(body).inputs(inputs).build();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的对话框基础构建器。
     * <p>原文：
     * Creates a new dialog base builder.
     *
     * @param title 对话框的标题
     * <p>原文：the title of the dialog
     * @return 新的对话框基础构建器
     * <p>原文：a new dialog base builder
     */
    @Contract(value = "_ -> new", pure = true)
    static Builder builder(final Component title) {
        return DialogInstancesProvider.instance().dialogBaseBuilder(title);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 对话框的标题。
     * <p>原文：
     * The title of the dialog.
     *
     * @return 标题
     * <p>原文：the title
     */
    @Contract(pure = true)
    Component title();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 对话框的外部标题。该标题用于打开此对话框的按钮上。
     * <p>原文：
     * The external title of the dialog. This title
     * is used on buttons that open this dialog.
     *
     * @return 外部标题或 null
     * <p>原文：the external title or null
     */
    @Contract(pure = true)
    @Nullable Component externalTitle();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此对话框能否通过“Esc”键关闭。
     * <p>原文：
     * Returns if this dialog can be closed with the "escape" keybind.
     *
     * @return 对话框能否通过“Esc”关闭
     * <p>原文：if the dialog can be closed with "escape"
     */
    @Contract(pure = true)
    boolean canCloseWithEscape();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此对话框打开时是否应暂停游戏（仅限单人游戏）。
     * <p>原文：
     * Returns if this dialog should pause the game when opened (single-player only).
     *
     * @return 对话框是否会暂停游戏
     * <p>原文：if the dialog pauses the game
     */
    @Contract(pure = true)
    boolean pause();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 对话框关闭后要执行的操作。
     * <p>原文：
     * The action to take after the dialog is closed.
     *
     * @return 对话框关闭后要执行的操作
     * <p>原文：the action to take after the dialog is closed
     */
    @Contract(pure = true)
    DialogAfterAction afterAction();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 对话框的主体。
     * <p>
     * 主体是将在对话框中显示的 {@link DialogBody} 元素列表。
     * <p>原文：
     * The body of the dialog.
     * <p>
     * The body is a list of {@link DialogBody} elements that will be displayed in the dialog.
     *
     * @return 对话框的主体
     * <p>原文：the body of the dialog
     */
    @Contract(pure = true)
    @Unmodifiable List<DialogBody> body();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 对话框的输入。
     * <p>
     * 输入是将在对话框中显示的 {@link DialogInput} 元素列表。
     * <p>原文：
     * The inputs of the dialog.
     * <p>
     * The inputs are a list of {@link DialogInput} elements that will be displayed in the dialog.
     *
     * @return 对话框的输入项
     * <p>原文：the inputs of the dialog
     */
    @Contract(pure = true)
    @Unmodifiable List<DialogInput> inputs();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 对话框关闭后要执行的操作。
     * <p>原文：
     * Actions to take after the dialog is closed.
     */
    enum DialogAfterAction {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 关闭对话框并返回上一个非对话框界面（如果有）。
         * <p>原文：
         * Closes the dialog and returns to the previous non-dialog screen (if any).
         */
        CLOSE("close"),
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 不执行任何操作（保持当前界面打开）。
         * <p>原文：
         * Does nothing (keeps the current screen open).
         */
        NONE("none"),
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 用“等待响应”界面替换对话框。
         * <p>原文：
         * Replaces dialog with a "waiting for response" screen.
         */
        WAIT_FOR_RESPONSE("wait_for_response");

        public static final Index<String, DialogAfterAction> NAMES = Index.create(DialogAfterAction.class, e -> e.name);

        private final String name;

        DialogAfterAction(final String name) {
            this.name = name;
        }
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于创建对话框基础的构建器接口。
     * <p>原文：
     * Builder interface for creating dialog bases.
     */
    @ApiStatus.NonExtendable
    interface Builder {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置对话框的外部标题。该标题用于打开此对话框的按钮上。
         * <p>原文：
         * Sets the external title of the dialog.
         * This title is used on buttons that open this dialog.
         *
         * @param externalTitle 对话框的外部标题；如果未设置则为 null
         * <p>原文：the external title of the dialog, or null if not set
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder externalTitle(@Nullable Component externalTitle);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置对话框能否通过“Esc”键关闭。
         * <p>原文：
         * Sets whether the dialog can be closed with the "escape" keybind.
         *
         * @param canCloseWithEscape 对话框能否通过“Esc”关闭
         * <p>原文：if the dialog can be closed with "escape"
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder canCloseWithEscape(boolean canCloseWithEscape);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置对话框打开时是否应暂停游戏（仅限单人游戏）。
         * <p>原文：
         * Sets whether the dialog should pause the game when opened (single-player only).
         *
         * @param pause 对话框是否应暂停游戏
         * <p>原文：if the dialog should pause the game
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder pause(boolean pause);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置对话框关闭后要执行的操作。
         * <p>原文：
         * Sets the action to take after the dialog is closed.
         *
         * @param afterAction 对话框关闭后要执行的操作
         * <p>原文：the action to take after the dialog is closed
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder afterAction(DialogAfterAction afterAction);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置对话框的主体。
         * <p>原文：
         * Sets the body of the dialog.
         *
         * @param body 对话框的主体
         * <p>原文：the body of the dialog
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder body(List<? extends DialogBody> body);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置对话框的输入。
         * <p>原文：
         * Sets the inputs of the dialog.
         *
         * @param inputs 对话框的输入项
         * <p>原文：the inputs of the dialog
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder inputs(List<? extends DialogInput> inputs);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 构建对话框基础。
         * <p>原文：
         * Builds the dialog base.
         *
         * @return 构建完成的对话框基础
         * <p>原文：the built dialog base
         */
        @Contract(pure = true, value = "-> new")
        DialogBase build();
    }
}
