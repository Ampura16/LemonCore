package io.papermc.paper.registry.data.dialog.type;


import io.papermc.paper.registry.data.dialog.ActionButton;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示显示链接的服务器链接对话框。
 * <p>原文：
 * Represents a server links dialog that displays links.
 * @see DialogType#serverLinks(ActionButton, int, int)
 */
@ApiStatus.NonExtendable
public non-sealed interface ServerLinksType extends DialogType {

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
     * 返回服务器链接对话框中显示的列数。
     * <p>原文：
     * Returns the number of columns to display in the server links dialog.
     *
     * @return 列数
     * <p>原文：the number of columns
     */
    @Contract(pure = true)
    @Positive int columns();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回服务器链接对话框中每个按钮的宽度。
     * <p>原文：
     * Returns the width of each button in the server links dialog.
     *
     * @return 各按钮的宽度
     * <p>原文：the width of the buttons
     */
    @Contract(pure = true)
    @Range(from = 1, to = 1024) int buttonWidth();
}
