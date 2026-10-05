package io.papermc.paper.registry.data.dialog.body;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 对话框的物品主体。
 * <p>
 * 通过 {@link DialogBody#item(ItemStack, PlainMessageDialogBody, boolean, boolean, int, int)}
 * </p>
 * 创建
 * <p>原文：
 * An item body for a dialog.
 * <p>Created via {@link DialogBody#item(ItemStack, PlainMessageDialogBody, boolean, boolean, int, int)}</p>
 */
@ApiStatus.NonExtendable
public non-sealed interface ItemDialogBody extends DialogBody {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 要在对话框中显示的物品。
     * <p>原文：
     * The item to display in the dialog.
     *
     * @return 物品堆
     * <p>原文：the item stack
     */
    @Contract(pure = true)
    ItemStack item();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 主体的描述；未设置时为 null。
     * <p>原文：
     * The description of the body, or null if not set.
     *
     * @return 描述主体
     * <p>原文：the description body
     */
    @Contract(pure = true)
    @Nullable PlainMessageDialogBody description();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 是否在物品周围显示装饰。
     * <p>
     * 装饰包括耐久度、物品堆数量等。
     * </p>
     * <p>原文：
     * Whether to show decorations around the item.
     * <p>Decorations include damage, itemstack count, etc.</p>
     *
     * @return 如果应显示装饰则为 true
     * <p>原文：true if decorations should be shown
     */
    @Contract(pure = true)
    boolean showDecorations();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 是否为该物品显示提示框。
     * <p>原文：
     * Whether to show a tooltip for the item.
     *
     * @return 如果应显示工具提示则为 true
     * <p>原文：true if a tooltip should be shown
     */
    @Contract(pure = true)
    boolean showTooltip();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 物品主体的宽度。
     * <p>原文：
     * The width of the item body.
     *
     * @return 宽度
     * <p>原文：the width
     */
    @Contract(pure = true)
    @Range(from = 1, to = 256) int width();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 物品主体的高度。
     * <p>原文：
     * The height of the item body.
     *
     * @return 高度
     * <p>原文：the height
     */
    @Contract(pure = true)
    @Range(from = 1, to = 256) int height();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 物品对话框主体的构建器。
     * <p>原文：
     * A builder for an item dialog body.
     */
    @ApiStatus.NonExtendable
    interface Builder {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置物品对话框主体的描述；未设置时为 null。
         * <p>原文：
         * Sets the description of the item dialog body, or null if not set.
         *
         * @param description 主体的描述，或 null
         * <p>原文：the description of the body, or null
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder description(@Nullable PlainMessageDialogBody description);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置是否在物品周围显示装饰。
         * <p>原文：
         * Sets whether to show decorations around the item.
         *
         * @param showDecorations true 表示显示装饰，否则为 false
         * <p>原文：true to show decorations, false otherwise
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder showDecorations(boolean showDecorations);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置是否为该物品显示提示框。
         * <p>原文：
         * Sets whether to show a tooltip for the item.
         *
         * @param showTooltip true 表示显示工具提示，否则为 false
         * <p>原文：true to show a tooltip, false otherwise
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder showTooltip(boolean showTooltip);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置物品主体的宽度。
         * <p>原文：
         * Sets the width of the item body.
         *
         * @param width 宽度，必须在 1 到 256 之间
         * <p>原文：the width, must be between 1 and 256
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder width(@Range(from = 1, to = 256) int width);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置物品主体的高度。
         * <p>原文：
         * Sets the height of the item body.
         *
         * @param height 高度，必须在 1 到 256 之间
         * <p>原文：the height, must be between 1 and 256
         * @return 此构建器
         * <p>原文：this builder
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder height(@Range(from = 1, to = 256) int height);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 构建新的 {@link ItemDialogBody} 实例。
         * <p>原文：
         * Builds a new instance of {@link ItemDialogBody}.
         *
         * @return 新的物品对话框主体实例
         * <p>原文：a new item dialog body instance
         */
        @Contract(value = "-> new", pure = true)
        ItemDialogBody build();
    }
}
