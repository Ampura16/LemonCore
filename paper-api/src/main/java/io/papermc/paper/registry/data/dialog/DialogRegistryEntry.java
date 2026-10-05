package io.papermc.paper.registry.data.dialog;

import io.papermc.paper.dialog.Dialog;
import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.data.dialog.type.DialogListType;
import io.papermc.paper.registry.data.dialog.type.DialogType;
import io.papermc.paper.registry.set.RegistryValueSetBuilder;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link io.papermc.paper.dialog.Dialog} 类型的以数据为中心、特定于版本的注册表条目。
 * <p>原文：
 * A data-centric version-specific registry entry for the {@link io.papermc.paper.dialog.Dialog} type.
 */
@ApiStatus.NonExtendable
public interface DialogRegistryEntry {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此条目的基础对话框。
     * <p>原文：
     * The base dialog for this entry.
     *
     * @return 基础对话框
     * <p>原文：the base dialog
     */
    @Contract(pure = true)
    DialogBase base();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此条目的对话框类型。
     * <p>原文：
     * The type of dialog for this entry.
     *
     * @return 对话框类型
     * <p>原文：the dialog type
     */
    @Contract(pure = true)
    DialogType type();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 插件可在相应注册表事件中修改的 {@link DialogRegistryEntry} 的可变构建器。
     * <p>
     * 每个构建器必须提供以下值：
     * <ul>
     * <li>{@link #base(DialogBase)}</li>
     * <li>{@link #type(DialogType)}</li>
     * </ul>
     * <p>原文：
     * A mutable builder for the {@link DialogRegistryEntry} plugins may change in applicable registry events.
     * <p>
     * The following values are required for each builder:
     * <ul>
     *     <li>{@link #base(DialogBase)}</li>
     *     <li>{@link #type(DialogType)}</li>
     * </ul>
     */
    @ApiStatus.NonExtendable
    interface Builder extends DialogRegistryEntry, RegistryBuilder<Dialog> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 提供可在 {@link DialogListType} 中使用的对话框 {@link io.papermc.paper.registry.set.RegistryValueSet} 构建器。
         * <p>
         * 不属于注册表条目的一部分。
         * </p>
         * <p>原文：
         * Provides a builder for dialog {@link io.papermc.paper.registry.set.RegistryValueSet} which
         * can be used inside {@link DialogListType}.
         * <p>Not a part of the registry entry.</p>
         *
         * @return 新的注册表值集合构建器
         * <p>原文：a new registry value set builder
         */
        @Contract(value = "-> new", pure = true)
        RegistryValueSetBuilder<Dialog, DialogRegistryEntry.Builder> registryValueSet();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 为此条目设置基础对话框。
         * <p>原文：
         * Sets the base dialog for this entry.
         *
         * @param dialogBase 基础对话框
         * <p>原文：the base dialog
         * @return 此构建器实例
         * <p>原文：this builder instance
         * @see DialogRegistryEntry#base()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder base(DialogBase dialogBase);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 为此条目设置对话框类型。
         * <p>原文：
         * Sets the type of dialog for this entry.
         *
         * @param dialogType 对话框类型
         * <p>原文：the type of dialog
         * @return 此构建器实例
         * <p>原文：this builder instance
         * @see DialogRegistryEntry#type()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder type(DialogType dialogType);
    }
}
