package io.papermc.paper.registry.data.dialog.body;

import io.papermc.paper.registry.data.dialog.DialogInstancesProvider;
import net.kyori.adventure.text.Component;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示对话框的主体。
 * <p>原文：
 * Represents the body of a dialog.
 */
public sealed interface DialogBody permits ItemDialogBody, PlainMessageDialogBody {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为对话框创建物品主体。
     * <p>原文：
     * Creates an item body for a dialog.
     *
     * @param item 在对话框中显示的物品
     * <p>原文：the item to display in the dialog
     * @param description 主体的描述；如果未设置则为 null
     * <p>原文：the description of the body, or null if not set
     * @param showDecorations 是否在物品周围显示装饰
     * <p>原文：whether to show decorations around the item
     * @param showTooltip 是否为物品显示工具提示
     * <p>原文：whether to show a tooltip for the item
     * @param width 物品主体的宽度
     * <p>原文：the width of the item body
     * @param height 物品主体的高度
     * <p>原文：the height of the item body
     * @return 新的物品主体实例
     * <p>原文：a new item body instance
     */
    @Contract(pure = true, value = "_, _, _, _, _, _ -> new")
    static ItemDialogBody item(
        final ItemStack item,
        final @Nullable PlainMessageDialogBody description,
        final boolean showDecorations,
        final boolean showTooltip,
        final @Range(from = 1, to = 256) int width,
        final @Range(from = 1, to = 256) int height
    ) {
        return item(item)
            .description(description)
            .showDecorations(showDecorations)
            .showTooltip(showTooltip)
            .width(width)
            .height(height)
            .build();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的物品对话框主体构建器。
     * <p>原文：
     * Creates a new item dialog body builder.
     *
     * @param item 在对话框中显示的物品
     * <p>原文：the item to display in the dialog
     * @return 新的物品对话框主体构建器实例
     * <p>原文：a new item dialog body builder instance
     */
    @Contract(pure = true, value = "_ -> new")
    static ItemDialogBody.Builder item(final ItemStack item) {
        return DialogInstancesProvider.instance().itemDialogBodyBuilder(item);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为对话框创建纯文本消息主体。
     * <p>原文：
     * Creates a plain message body for a dialog.
     *
     * @param contents 消息内容
     * <p>原文：the contents of the message
     * @return 新的纯文本消息主体实例
     * <p>原文：a new plain message body instance
     */
    @Contract(pure = true, value = "_ -> new")
    static PlainMessageDialogBody plainMessage(final Component contents) {
        return DialogInstancesProvider.instance().plainMessageDialogBody(contents);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为对话框创建纯文本消息主体。
     * <p>原文：
     * Creates a plain message body for a dialog.
     *
     * @param contents 消息内容
     * <p>原文：the contents of the message
     * @param width 消息主体的宽度
     * <p>原文：the width of the message body
     * @return 新的纯文本消息主体实例
     * <p>原文：a new plain message body instance
     */
    @Contract(pure = true, value = "_, _ -> new")
    static PlainMessageDialogBody plainMessage(final Component contents, final @Range(from = 1, to = 1024) int width) {
        return DialogInstancesProvider.instance().plainMessageDialogBody(contents, width);
    }
}
