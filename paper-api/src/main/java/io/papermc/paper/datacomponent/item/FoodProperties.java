package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.BuildableDataComponent;
import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有物品的食物属性。
 * <p>原文：
 * Holds the food properties of an item.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#FOOD
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface FoodProperties extends BuildableDataComponent<FoodProperties, FoodProperties.Builder> {

    @Contract(value = "-> new", pure = true)
    static FoodProperties.Builder food() {
        return ItemComponentTypesBridge.bridge().food();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 食用时恢复的食物点数。
     * <p>原文：
     * Number of food points to restore when eaten.
     *
     * @return 饥饿值
     * <p>原文：the nutrition
     */
    @Contract(pure = true)
    @NonNegative int nutrition();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 食用时恢复的饱和度。
     * <p>原文：
     * Amount of saturation to restore when eaten.
     *
     * @return 饱和度
     * <p>原文：the saturation
     */
    @Contract(pure = true)
    float saturation();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 如果为 {@code true}，即使不饿也可以食用此食物。
     * <p>原文：
     * If {@code true}, this food can be eaten even if not hungry.
     *
     * @return 是否总能被食用
     * <p>原文：can always be eaten
     */
    @Contract(pure = true)
    boolean canAlwaysEat();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link FoodProperties} 的构建器。
     * <p>原文：
     * Builder for {@link FoodProperties}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<FoodProperties> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此食物是否总能被食用，即使玩家不饿。
         * <p>原文：
         * Set if this food can always be eaten, even if the
         * player is not hungry.
         *
         * @param canAlwaysEat true 表示允许随时进食
         * <p>原文：true to allow always eating
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #canAlwaysEat()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder canAlwaysEat(boolean canAlwaysEat);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置食物的饱和度。
         * <p>原文：
         * Sets the saturation of the food.
         *
         * @param saturation 饱和度
         * <p>原文：the saturation
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #saturation()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder saturation(float saturation);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置食物的营养值。
         * <p>原文：
         * Sets the nutrition of the food.
         *
         * @param nutrition 饥饿值，必须为非负数
         * <p>原文：the nutrition, must be non-negative
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #nutrition()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder nutrition(@NonNegative int nutrition);
    }
}
