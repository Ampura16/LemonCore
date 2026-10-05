package io.papermc.paper.registry.data.dialog;

import io.papermc.paper.registry.data.dialog.action.DialogAction;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示对话框中的操作按钮，可用于触发操作或在对话框内导航。操作按钮可以有标签、提示和关联的操作。
 * <p>原文：
 * Represents an action button in a dialog, which can be used to trigger actions or navigate within the dialog.
 * Action buttons can have labels, tooltips, and associated actions.
 */
@ApiStatus.NonExtendable
public interface ActionButton {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用指定的标签、提示、宽度和操作创建新的操作按钮。
     * <p>原文：
     * Creates a new action button with the specified label, tooltip, width, and action.
     *
     * @param label 按钮的标签
     * <p>原文：the label of the button
     * @param tooltip 悬停在按钮上时显示的工具提示；如果不需要则为 null
     * <p>原文：the tooltip to display when hovering over the button, or null if no tooltip is needed
     * @param width 按钮的宽度
     * <p>原文：the width of the button
     * @param action 点击按钮时要执行的操作；如果没有关联操作则为 null
     * <p>原文：the action to perform when the button is clicked, or null if no action is associated
     * @return 新的 ActionButton 实例
     * <p>原文：a new ActionButton instance
     */
    @Contract(value = "_, _, _, _ -> new", pure = true)
    static ActionButton create(final Component label, final @Nullable Component tooltip, final @Range(from = 1, to = 1024) int width, final @Nullable DialogAction action) {
        return builder(label).tooltip(tooltip).width(width).action(action).build();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 使用指定标签创建新的操作按钮构建器。
     * <p>原文：
     * Creates a new action button builder with the specified label.
     *
     * @param label 按钮的标签
     * <p>原文：the label of the button
     * @return 新的 ActionButton.Builder 实例
     * <p>原文：a new ActionButton.Builder instance
     */
    @Contract(pure = true, value = "_ -> new")
    static ActionButton.Builder builder(final Component label) {
        return DialogInstancesProvider.instance().actionButtonBuilder(label);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回操作按钮的标签。
     * <p>原文：
     * Returns the label of the action button.
     *
     * @return 按钮的标签
     * <p>原文：the label of the button
     */
    @Contract(pure = true)
    Component label();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回操作按钮的提示；如果未设置提示则返回 null。
     * <p>原文：
     * Returns the tooltip of the action button, or null if no tooltip is set.
     *
     * @return 按钮的工具提示，或 null
     * <p>原文：the tooltip of the button, or null
     */
    @Contract(pure = true)
    @Nullable Component tooltip();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回操作按钮的宽度。
     * <p>原文：
     * Returns the width of the action button.
     *
     * @return 按钮的宽度
     * <p>原文：the width of the button
     */
    @Contract(pure = true)
    @Range(from = 1, to = 1024) int width();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回与此按钮关联的操作；如果未关联操作则返回 null。
     * <p>原文：
     * Returns the action associated with this button, or null if no action is associated.
     *
     * @return 点击按钮时要执行的操作，或 null
     * <p>原文：the action to perform when the button is clicked, or null
     */
    @Contract(pure = true)
    @Nullable DialogAction action();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于创建 ActionButton 实例的构建器。
     * <p>原文：
     * A builder for creating ActionButton instances.
     */
    @ApiStatus.NonExtendable
    interface Builder {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置操作按钮的提示；如果不需要提示则设为 null。
         * <p>原文：
         * Sets the tooltip of the action button, or null if no tooltip is desired.
         *
         * @param tooltip 按钮的工具提示，或 null
         * <p>原文：the tooltip of the button, or null
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder tooltip(@Nullable Component tooltip);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置操作按钮的宽度。
         * <p>原文：
         * Sets the width of the action button.
         *
         * @param width 按钮的宽度
         * <p>原文：the width of the button
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder width(@Range(from = 1, to = 1024) int width);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置与此按钮关联的操作；如果不需要操作则设为 null。
         * <p>原文：
         * Sets the action associated with this button, or null if no action is desired.
         *
         * @param action 点击按钮时要执行的操作，或 null
         * <p>原文：the action to perform when the button is clicked, or null
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder action(@Nullable DialogAction action);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 使用已配置的值构建 ActionButton 实例。
         * <p>原文：
         * Builds the ActionButton instance with the configured values.
         *
         * @return 新的 ActionButton 实例
         * <p>原文：a new ActionButton instance
         */
        @Contract(value = "-> new", pure = true)
        ActionButton build();
    }
}
