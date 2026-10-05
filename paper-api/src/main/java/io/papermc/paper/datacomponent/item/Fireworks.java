package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import java.util.List;
import org.bukkit.FireworkEffect;
import org.checkerframework.common.value.qual.IntRange;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 存储合成到烟花火箭中的所有爆炸效果以及飞行时间。
 * <p>原文：
 * Stores all explosions crafted into a Firework Rocket, as well as flight duration.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#FIREWORKS
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface Fireworks {

    @Contract(value = "_, _ -> new", pure = true)
    static Fireworks fireworks(final List<FireworkEffect> effects, final int flightDuration) {
        return fireworks().addEffects(effects).flightDuration(flightDuration).build();
    }

    @Contract(value = "-> new", pure = true)
    static Fireworks.Builder fireworks() {
        return ItemComponentTypesBridge.bridge().fireworks();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 列出此组件中存储的效果。
     * <p>原文：
     * Lists the effects stored in this component.
     *
     * @return 效果
     * <p>原文：the effects
     */
    @Contract(pure = true)
    @Unmodifiable List<FireworkEffect> effects();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此组件中火药的数量。
     * <p>原文：
     * Number of gunpowder in this component.
     *
     * @return 飞行时长
     * <p>原文：the flight duration
     */
    @Contract(pure = true)
    @IntRange(from = 0, to = 255) int flightDuration();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link Fireworks} 的构建器。
     * <p>原文：
     * Builder for {@link Fireworks}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<Fireworks> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此构建器中使用的火药数量。
         * <p>原文：
         * Sets the number of gunpowder used in this builder.
         *
         * @param duration 持续时间
         * <p>原文：duration
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #flightDuration()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder flightDuration(@IntRange(from = 0, to = 255) int duration);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加一个爆炸效果。
         * <p>原文：
         * Adds an explosion to this builder.
         *
         * @param effect 效果
         * <p>原文：effect
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #effects()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addEffect(FireworkEffect effect);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加多个爆炸效果。
         * <p>原文：
         * Adds explosions to this builder.
         *
         * @param effects 效果
         * <p>原文：effects
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #effects()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addEffects(List<FireworkEffect> effects);
    }
}
