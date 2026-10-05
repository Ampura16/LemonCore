package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.bukkit.Color;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示应用于可染色物品的颜色。
 * <p>原文：
 * Represents a color applied to a dyeable item.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#DYED_COLOR
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface DyedItemColor {

    @Contract(value = "_ -> new", pure = true)
    static DyedItemColor dyedItemColor(final Color color) {
        return dyedItemColor().color(color).build();
    }

    @Contract(value = "-> new", pure = true)
    static DyedItemColor.Builder dyedItemColor() {
        return ItemComponentTypesBridge.bridge().dyedItemColor();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 物品的颜色。
     * <p>原文：
     * Color of the item.
     *
     * @return 颜色
     * <p>原文：color
     */
    @Contract(value = "-> new", pure = true)
    Color color();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link DyedItemColor} 的构建器。
     * <p>原文：
     * Builder for {@link DyedItemColor}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<DyedItemColor> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此构建器的颜色。
         * <p>原文：
         * Sets the color of this builder.
         *
         * @param color 颜色
         * <p>原文：color
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #color()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder color(Color color);
    }
}
