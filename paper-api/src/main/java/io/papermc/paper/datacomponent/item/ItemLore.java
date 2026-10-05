package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import java.util.List;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.ComponentLike;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 要添加到物品提示框中的额外行。
 * <p>原文：
 * Additional lines to include in an item's tooltip.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#LORE
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface ItemLore {

    @Contract(value = "_ -> new", pure = true)
    static ItemLore lore(final List<? extends ComponentLike> lines) {
        return lore().lines(lines).build();
    }

    @Contract(value = "-> new", pure = true)
    static ItemLore.Builder lore() {
        return ItemComponentTypesBridge.bridge().lore();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 列出添加到物品提示框中的组件。
     * <p>原文：
     * Lists the components that are added to an item's tooltip.
     *
     * @return 组件列表
     * <p>原文：component list
     */
    @Contract(pure = true)
    @Unmodifiable List<Component> lines();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 列出添加到物品提示框中的带样式组件（例如斜体紫色）。
     * <p>原文：
     * Lists the styled components (example: italicized and purple) that are added to an item's tooltip.
     *
     * @return 组件列表
     * <p>原文：component list
     */
    @Contract(pure = true)
    @Unmodifiable List<Component> styledLines();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link ItemLore} 的构建器。
     * <p>原文：
     * Builder for {@link ItemLore}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<ItemLore> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此物品描述（lore）的组件。
         * <p>原文：
         * Sets the components of this lore.
         *
         * @param lines 组件
         * <p>原文：components
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #lines()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder lines(List<? extends ComponentLike> lines);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向物品描述（lore）添加一个组件。
         * <p>原文：
         * Adds a component to the lore.
         *
         * @param line 组件
         * <p>原文：component
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #lines()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addLine(ComponentLike line);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向物品描述（lore）添加多个组件。
         * <p>原文：
         * Adds components to the lore.
         *
         * @param lines 组件
         * <p>原文：components
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #lines()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addLines(List<? extends ComponentLike> lines);
    }
}
