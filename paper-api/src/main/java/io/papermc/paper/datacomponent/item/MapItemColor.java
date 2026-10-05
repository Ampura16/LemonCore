package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.bukkit.Color;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示已填充地图物品上装饰的色调。
 * <p>原文：
 * Represents the tint of the decorations on the Filled Map item.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#MAP_COLOR
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface MapItemColor {

    @Contract(value = "-> new", pure = true)
    static MapItemColor.Builder mapItemColor() {
        return ItemComponentTypesBridge.bridge().mapItemColor();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 要应用的色调。
     * <p>原文：
     * The tint to apply.
     *
     * @return 颜色
     * <p>原文：color
     */
    Color color();

    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<MapItemColor> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此地图的色调颜色。
         * <p>原文：
         * Sets the tint color of this map.
         *
         * @param color 染色颜色
         * <p>原文：tint color
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #color()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder color(Color color);
    }
}
