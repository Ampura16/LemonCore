package io.papermc.paper.registry.data.dialog.action;

import io.papermc.paper.registry.data.dialog.DialogInstancesProvider;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.nbt.api.BinaryTagHolder;
import net.kyori.adventure.text.event.ClickCallback;
import net.kyori.adventure.text.event.ClickEvent;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示可在对话框中执行的操作。
 * <p>原文：
 * Represents an action that can be performed in a dialog.
 */
public sealed interface DialogAction permits DialogAction.CommandTemplateAction, DialogAction.CustomClickAction, DialogAction.StaticAction {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的命令模板操作。模板格式查找 {@code $(variable_name)} 以替换变量。{@code variable_name} 应对应一个 {@link io.papermc.paper.registry.data.dialog.input.DialogInput#key()}。
     * <p>原文：
     * Creates a new command template action. The template format
     * looks for {@code $(variable_name)} to substitute variables.
     * {@code variable_name} should correspond to a {@link io.papermc.paper.registry.data.dialog.input.DialogInput#key()}.
     *
     * @param template 要执行的命令模板
     * <p>原文：the command template to execute
     * @return 新的命令模板操作实例
     * <p>原文：a new command template action instance
     */
    @Contract(pure = true, value = "_ -> new")
    static CommandTemplateAction commandTemplate(final String template) {
        return DialogInstancesProvider.instance().commandTemplate(template);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建执行点击事件的新静态操作。
     * <p>原文：
     * Creates a new static action that performs a click event.
     *
     * @param value 要执行的点击事件
     * <p>原文：the click event to perform
     * @return 新的静态操作实例
     * <p>原文：a new static action instance
     */
    @Contract(pure = true, value = "_ -> new")
    static StaticAction staticAction(final ClickEvent value) {
        return DialogInstancesProvider.instance().staticAction(value);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建执行自定义操作的新自定义点击操作。每个 {@link io.papermc.paper.registry.data.dialog.input.DialogInput#key()} 都会被添加到复合二进制标签持有者中。
     * <p>原文：
     * Creates a new custom click action that executes a custom action.
     * Each {@link io.papermc.paper.registry.data.dialog.input.DialogInput#key()} is added
     * to the compound binary tag holder.
     *
     * @param id 自定义操作的标识符
     * <p>原文：the identifier of the custom action
     * @param additions 随操作发送的附加数据；如果不需要则为 null
     * <p>原文：additional data to be sent with the action, or null if not needed
     * @return 新的自定义点击操作实例
     * <p>原文：a new custom click action instance
     */
    @Contract(pure = true, value = "_, _ -> new")
    static CustomClickAction customClick(final Key id, final @Nullable BinaryTagHolder additions) {
        return DialogInstancesProvider.instance().customClick(id, additions);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建执行自定义操作的新自定义点击操作。
     * <p>原文：
     * Creates a new custom click action that executes a custom action.
     *
     * @param callback 要执行的自定义操作
     * <p>原文：the custom action to execute
     * @param options 自定义操作的选项
     * <p>原文：the options for the custom action
     * @return 新的自定义点击操作实例
     * <p>原文：a new custom click action instance
     */
    @Contract(pure = true, value = "_, _ -> new")
    static CustomClickAction customClick(final DialogActionCallback callback, final ClickCallback.Options options) {
        return DialogInstancesProvider.instance().register(callback, options);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示执行命令模板的操作。
     * <p>原文：
     * Represents an action that executes a command template.
     */
    @ApiStatus.NonExtendable
    non-sealed interface CommandTemplateAction extends DialogAction {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 要执行的命令模板。
         * <p>原文：
         * The command template to execute.
         *
         * @return 命令模板
         * <p>原文：the command template
         */
        @Contract(pure = true)
        String template();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示执行静态点击事件的操作。
     * <p>原文：
     * Represents an action that performs a static click event.
     */
    @ApiStatus.NonExtendable
    non-sealed interface StaticAction extends DialogAction {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 要执行的点击事件。
         * <p>原文：
         * The click event to perform.
         *
         * @return 点击事件
         * <p>原文：the click event
         */
        @Contract(pure = true)
        ClickEvent value();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 表示携带附加数据执行自定义操作的操作。
     * <p>原文：
     * Represents an action that executes a custom action with additional data.
     */
    @ApiStatus.NonExtendable
    non-sealed interface CustomClickAction extends DialogAction {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 自定义操作的标识符。
         * <p>原文：
         * The identifier of the custom action.
         *
         * @return 标识符
         * <p>原文：the identifier
         */
        @Contract(pure = true)
        Key id();

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 随操作一同发送的附加数据。这是一个复合二进制标签持有者，可以包含与该操作相关的各种数据。
         * <p>原文：
         * Additional data to be sent with the action.
         * This is a compound binary tag holder that can contain
         * various data related to the action.
         *
         * @return 附加数据；如果不需要则为 null
         * <p>原文：the additional data, or null if not needed
         */
        @Contract(pure = true)
        @Nullable BinaryTagHolder additions();
    }
}
