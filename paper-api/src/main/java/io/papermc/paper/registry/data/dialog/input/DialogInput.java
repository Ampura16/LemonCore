package io.papermc.paper.registry.data.dialog.input;

import io.papermc.paper.registry.data.dialog.DialogInstancesProvider;
import java.util.List;
import net.kyori.adventure.text.Component;
import org.checkerframework.checker.index.qual.Positive;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示对话框的输入。
 * <p>原文：
 * Represents an input for dialog.
 */
public sealed interface DialogInput permits BooleanDialogInput, NumberRangeDialogInput, SingleOptionDialogInput, TextDialogInput {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建布尔型对话框输入。
     * <p>原文：
     * Creates a boolean dialog input.
     *
     * @param key 输入的键标识符
     * <p>原文：the key identifier for the input
     * @param label 输入的标签
     * <p>原文：the label for the input
     * @param initial 输入的初始值
     * <p>原文：the initial value of the input
     * @param onTrue 值为 true 时该输入在模板中的值
     * <p>原文：the input's value in a template when the value is true
     * @param onFalse 值为 false 时该输入在模板中的值
     * <p>原文：the input's value in a template when the value is false
     * @return 新的布尔型对话框输入实例
     * <p>原文：a new boolean dialog input instance
     */
    @Contract(pure = true, value = "_, _, _, _, _ -> new")
    static BooleanDialogInput bool(final String key, final Component label, final boolean initial, final String onTrue, final String onFalse) {
        return bool(key, label)
            .initial(initial)
            .onTrue(onTrue)
            .onFalse(onFalse)
            .build();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建布尔型对话框输入的新构建器。
     * <p>原文：
     * Creates a new builder for a boolean dialog input.
     *
     * @param key 输入的键标识符
     * <p>原文：the key identifier for the input
     * @param label 输入的标签
     * <p>原文：the label for the input
     * @return 新的构建器实例
     * <p>原文：a new builder instance
     */
    @Contract(pure = true, value = "_, _ -> new")
    static BooleanDialogInput.Builder bool(final String key, final Component label) {
        return DialogInstancesProvider.instance().booleanBuilder(key, label);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建数值范围对话框输入。
     * <p>原文：
     * Creates a number range dialog input.
     *
     * @param key 输入的键标识符
     * <p>原文：the key identifier for the input
     * @param width 输入框的宽度
     * <p>原文：the width of the input
     * @param label 输入的标签
     * <p>原文：the label for the input
     * @param labelFormat 标签的格式（翻译键或格式字符串）
     * <p>原文：the format for the label (a translation key or format string)
     * @param start 范围的起点
     * <p>原文：the start of the range
     * @param end 范围的终点
     * <p>原文：the end of the range
     * @param initial 初始值；如果未设置则为 null
     * <p>原文：the initial value, or null if not set
     * @param step 步长；如果未设置则为 null
     * <p>原文：the step size, or null if not set
     * @return 新的数值范围对话框输入实例
     * <p>原文：a new number range dialog input instance
     */
    @Contract(pure = true, value = "_, _, _, _, _, _, _, _ -> new")
    static NumberRangeDialogInput numberRange(
        final String key,
        final @Range(from = 1, to = 1024) int width,
        final Component label,
        final String labelFormat,
        final float start,
        final float end,
        final @Nullable Float initial,
        final @Positive @Nullable Float step
    ) {
        return numberRange(key, label, start, end).width(width).labelFormat(labelFormat).initial(initial).step(step).build();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建数值范围对话框输入的新构建器。
     * <p>原文：
     * Creates a new builder for a number range dialog input.
     *
     * @param key 输入的键标识符
     * <p>原文：the key identifier for the input
     * @param label 输入的标签
     * <p>原文：the label for the input
     * @param start 范围的起点
     * <p>原文：the start of the range
     * @param end 范围的终点
     * <p>原文：the end of the range
     * @return 新的构建器实例
     * <p>原文：a new builder instance
     */
    @Contract(pure = true, value = "_, _, _, _ -> new")
    static NumberRangeDialogInput.Builder numberRange(final String key, final Component label, final float start, final float end) {
        return DialogInstancesProvider.instance().numberRangeBuilder(key, label, start, end);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建单选项对话框输入（单选框）。
     * <p>原文：
     * Creates a single option dialog input (radio input).
     *
     * @param key 输入的键标识符
     * <p>原文：the key identifier for the input
     * @param width 输入框的宽度
     * <p>原文：the width of the input
     * @param entries 输入的选项列表
     * <p>原文：the list of options for the input
     * @param label 输入的标签
     * <p>原文：the label for the input
     * @param labelVisible 标签是否应可见
     * <p>原文：whether the label should be visible
     * @return 新的单选项对话框输入实例
     * <p>原文：a new single option dialog input instance
     */
    @Contract(pure = true, value = "_, _, _, _, _ -> new")
    static SingleOptionDialogInput singleOption(
        final String key,
        final @Range(from = 1, to = 1024) int width,
        final List<SingleOptionDialogInput.OptionEntry> entries,
        final Component label,
        final boolean labelVisible
    ) {
        return singleOption(key, label, entries).width(width).labelVisible(labelVisible).build();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建单选项对话框输入的新构建器。
     * <p>原文：
     * Creates a new builder for a single option dialog input.
     *
     * @param key 输入的键标识符
     * <p>原文：the key identifier for the input
     * @param label 输入的标签
     * <p>原文：the label for the input
     * @param entries 输入的选项列表
     * <p>原文：the list of options for the input
     * @return 新的构建器实例
     * <p>原文：a new builder instance
     */
    @Contract(pure = true, value = "_, _, _ -> new")
    static SingleOptionDialogInput.Builder singleOption(final String key, final Component label, final List<SingleOptionDialogInput.OptionEntry> entries) {
        return DialogInstancesProvider.instance().singleOptionBuilder(key, label, entries);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建文本对话框输入。
     * <p>原文：
     * Creates a text dialog input.
     *
     * @param key 输入的键标识符
     * <p>原文：the key identifier for the input
     * @param width 输入框的宽度
     * <p>原文：the width of the input
     * @param label 输入的标签
     * <p>原文：the label for the input
     * @param labelVisible 标签是否应可见
     * <p>原文：whether the label should be visible
     * @param initial 输入的初始值
     * <p>原文：the initial value of the input
     * @param maxLength 输入的最大长度
     * <p>原文：the maximum length of the input
     * @param multilineOptions 多行选项；如果未设置则为 null
     * <p>原文：the multiline options, or null if not set
     * @return 新的文本对话框输入实例
     * <p>原文：a new text dialog input instance
     */
    @Contract(pure = true, value = "_, _, _, _, _, _, _ -> new")
    static TextDialogInput text(
        final String key,
        final @Range(from = 1, to = 1024) int width,
        final Component label,
        final boolean labelVisible,
        final String initial,
        final @Positive int maxLength,
        final TextDialogInput.@Nullable MultilineOptions multilineOptions
    ) {
        return text(key, label).width(width).labelVisible(labelVisible).initial(initial).maxLength(maxLength).multiline(multilineOptions).build();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建文本对话框输入的新构建器。
     * <p>原文：
     * Creates a new builder for a text dialog input.
     *
     * @param key 输入的键标识符
     * <p>原文：the key identifier for the input
     * @param label 输入的标签
     * <p>原文：the label for the input
     * @return 新的构建器实例
     * <p>原文：a new builder instance
     */
    @Contract(value = "_, _ -> new", pure = true)
    static TextDialogInput.Builder text(final String key, final Component label) {
        return DialogInstancesProvider.instance().textBuilder(key, label);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此输入的键。
     * <p>
     * 在对话框操作中用于标识此对话框输入的值
     * </p>
     * <p>原文：
     * Gets the key for this input.
     * <p>Used in dialog actions to identify this dialog input's value</p>
     *
     * @return 键
     * <p>原文：the key
     */
    @Contract(pure = true)
    String key();
}
