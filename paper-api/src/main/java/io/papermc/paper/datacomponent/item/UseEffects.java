package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface UseEffects {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回用于创建 UseEffects 组件的新构建器。
     * <p>原文：
     * Returns a new builder for creating a UseEffects component.
     *
     * @return 构建器实例。
     * <p>原文：a builder instance.
     */
    static Builder useEffects() {
        return ItemComponentTypesBridge.bridge().useEffects();
    }

    @Contract(pure = true)
    boolean canSprint();

    @Contract(pure = true)
    boolean interactVibrations();

    @Contract(pure = true)
    float speedMultiplier();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link UseEffects} 的构建器。
     * <p>原文：
     * Builder for {@link UseEffects}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<UseEffects> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置玩家在使用物品时能否疾跑。
         * <p>原文：
         * Sets whether the player can sprint while using the item.
         *
         * @param canSprint true 表示允许疾跑
         * <p>原文：true to allow sprinting
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #canSprint()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder canSprint(boolean canSprint);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置使用该物品是否会产生交互振动。
         * <p>原文：
         * Sets whether using the item generates interaction vibrations.
         *
         * @param interactVibrations true 表示产生振动
         * <p>原文：true to generate vibrations
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #interactVibrations()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder interactVibrations(boolean interactVibrations);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置使用物品时的速度乘数。
         * <p>原文：
         * Sets the speed multiplier while using the item.
         *
         * @param speedMultiplier multiplier (1.0 >= value >= 0.0)
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #speedMultiplier()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder speedMultiplier(float speedMultiplier);
    }
}
