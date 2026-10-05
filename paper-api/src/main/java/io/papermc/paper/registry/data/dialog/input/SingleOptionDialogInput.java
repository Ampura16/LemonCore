package io.papermc.paper.registry.data.dialog.input;

import io.papermc.paper.registry.data.dialog.DialogInstancesProvider;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 单选项对话框输入。
 * <p>
 * 通过 {@link DialogInput#singleOption(String, int, List, Component, boolean)}
 * </p>
 * 创建
 * <p>原文：
 * A single option dialog input.
 * <p>Created via {@link DialogInput#singleOption(String, int, List, Component, boolean)}</p>
 */
@ApiStatus.NonExtendable
public non-sealed interface SingleOptionDialogInput extends DialogInput {

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
     * 输入的选项列表。
     * <p>原文：
     * The list of options for the input.
     *
     * @return 选项条目列表
     * <p>原文：the list of option entries
     */
    @Contract(pure = true)
    @Unmodifiable List<OptionEntry> entries();

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
     * 表示单选项对话框输入中的一个选项条目。
     * <p>
     * 只允许 1 个选项被初始选中。
     * </p>
     * <p>原文：
     * Represents a single option entry in a single option dialog input.
     * <p>Only 1 option is allowed to have initial selected.</p>
     */
    @ApiStatus.NonExtendable
    interface OptionEntry {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 创建新的选项条目。
         * <p>原文：
         * Creates a new option entry.
         *
         * @param id 选项的唯一标识符
         * <p>原文：the unique identifier for the option
         * @param display 选项的显示名称；如果未设置则为 null
         * <p>原文：the display name for the option, or null if not set
         * @param initial 此选项是否初始被选中
         * <p>原文：whether this option is initially selected
         * @return 新的选项条目实例
         * <p>原文：a new option entry instance
         */
        @Contract(pure = true, value = "_, _, _ -> new")
        static OptionEntry create(final String id, final @Nullable Component display, final boolean initial) {
            return DialogInstancesProvider.instance().singleOptionEntry(id, display, initial);
        }

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 选项的唯一标识符。
         * <p>原文：
         * The unique identifier for the option.
         *
         * @return 选项 ID
         * <p>原文：the option ID
         */
        @Contract(pure = true)
        String id();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 选项的显示名称；未设置时为 null。
         * <p>原文：
         * The display name for the option, or null if not set.
         *
         * @return 显示组件，或 null
         * <p>原文：the display component, or null
         */
        @Contract(pure = true)
        @Nullable Component display();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 此选项是否初始被选中。
         * <p>
         * 只允许 1 个选项被初始选中。
         * </p>
         * <p>原文：
         * Whether this option is initially selected.
         * <p>Only 1 option is allowed to have initial selected.</p>
         *
         * @return 如果该选项初始被选中则为 true，否则为 false
         * <p>原文：true if the option is initially selected, false otherwise
         */
        @Contract(pure = true)
        boolean initial();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 用于创建 {@link SingleOptionDialogInput} 的构建器。
     * <p>
     * 通过 {@link DialogInput#singleOption(String, Component, List)}
     * </p>
     * 创建
     * <p>原文：
     * A builder for creating a {@link SingleOptionDialogInput}.
     * <p>Created via {@link DialogInput#singleOption(String, Component, List)}</p>
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
         * 设置标签是否可见。
         * <p>原文：
         * Sets whether the label should be visible.
         *
         * @param labelVisible 标签是否应可见
         * <p>原文：whether the label should be visible
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder labelVisible(boolean labelVisible);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 构建 {@link SingleOptionDialogInput}。
         * <p>原文：
         * Builds the {@link SingleOptionDialogInput}.
         *
         * @return 构建完成的对话框输入
         * <p>原文：the built dialog input
         */
        @Contract(value = "-> new", pure = true)
        SingleOptionDialogInput build();
    }
}
