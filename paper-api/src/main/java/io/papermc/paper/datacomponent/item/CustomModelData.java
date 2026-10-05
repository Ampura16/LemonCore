package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import java.util.List;
import org.bukkit.Color;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有自定义模型数据。
 * <p>原文：
 * Holds the custom model data.
 *
 * @see io.papermc.paper.datacomponent.DataComponentTypes#CUSTOM_MODEL_DATA
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface CustomModelData {

    @Contract(value = "-> new", pure = true)
    static CustomModelData.Builder customModelData() {
        return ItemComponentTypesBridge.bridge().customModelData();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取自定义模型数据的浮点值。
     * <p>原文：
     * Gets the custom model data float values.
     *
     * @return 浮点值
     * <p>原文：the float values
     */
    @Contract(pure = true)
    @Unmodifiable List<Float> floats();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取自定义模型数据的布尔值。
     * <p>原文：
     * Gets the custom model data boolean values.
     *
     * @return 布尔值
     * <p>原文：the boolean values
     */
    @Contract(pure = true)
    @Unmodifiable List<Boolean> flags();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取自定义模型数据的字符串值。
     * <p>原文：
     * Gets the custom model data string values.
     *
     * @return 字符串值
     * <p>原文：the string values
     */
    @Contract(pure = true)
    @Unmodifiable List<String> strings();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取自定义模型数据的颜色值。
     * <p>原文：
     * Gets the custom model data color values.
     *
     * @return 颜色值
     * <p>原文：the color values
     */
    @Contract(pure = true)
    @Unmodifiable List<Color> colors();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link CustomModelData} 的构建器。
     * <p>原文：
     * Builder for {@link CustomModelData}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<CustomModelData> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此自定义模型数据添加一个浮点数。
         * <p>原文：
         * Adds a float to this custom model data.
         *
         * @param f 浮点数
         * <p>原文：the float
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #floats()
         */
        @Contract(value = "_ -> this", mutates = "this")
        CustomModelData.Builder addFloat(float f);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此自定义模型数据添加多个浮点数。
         * <p>原文：
         * Adds multiple floats to this custom model data.
         *
         * @param floats 浮点数
         * <p>原文：the floats
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #floats()
         */
        @Contract(value = "_ -> this", mutates = "this")
        CustomModelData.Builder addFloats(List<Float> floats);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此自定义模型数据添加一个标志。
         * <p>原文：
         * Adds a flag to this custom model data.
         *
         * @param flag 标志
         * <p>原文：the flag
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #flags()
         */
        @Contract(value = "_ -> this", mutates = "this")
        CustomModelData.Builder addFlag(boolean flag);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此自定义模型数据添加多个标志。
         * <p>原文：
         * Adds multiple flags to this custom model data.
         *
         * @param flags 标志
         * <p>原文：the flags
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #flags()
         */
        @Contract(value = "_ -> this", mutates = "this")
        CustomModelData.Builder addFlags(List<Boolean> flags);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此自定义模型数据添加一个字符串。
         * <p>原文：
         * Adds a string to this custom model data.
         *
         * @param string 字符串
         * <p>原文：the string
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #strings()
         */
        @Contract(value = "_ -> this", mutates = "this")
        CustomModelData.Builder addString(String string);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此自定义模型数据添加多个字符串。
         * <p>原文：
         * Adds multiple strings to this custom model data.
         *
         * @param strings 字符串
         * <p>原文：the strings
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #strings()
         */
        @Contract(value = "_ -> this", mutates = "this")
        CustomModelData.Builder addStrings(List<String> strings);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此自定义模型数据添加一个颜色。
         * <p>原文：
         * Adds a color to this custom model data.
         *
         * @param color 颜色
         * <p>原文：the color
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #colors()
         */
        @Contract(value = "_ -> this", mutates = "this")
        CustomModelData.Builder addColor(Color color);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此自定义模型数据添加多个颜色。
         * <p>原文：
         * Adds multiple colors to this custom model data.
         *
         * @param colors 颜色
         * <p>原文：the colors
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #colors()
         */
        @Contract(value = "_ -> this", mutates = "this")
        CustomModelData.Builder addColors(List<Color> colors);
    }
}
