package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.BuildableDataComponent;
import io.papermc.paper.datacomponent.DataComponentBuilder;
import io.papermc.paper.datacomponent.item.consumable.ConsumeEffect;
import io.papermc.paper.datacomponent.item.consumable.ItemUseAnimation;
import java.util.List;
import net.kyori.adventure.key.Key;
import org.checkerframework.checker.index.qual.NonNegative;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有此物品被食用时的属性。
 * <p>原文：
 * Holds the properties for this item for when it is consumed.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#CONSUMABLE
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface Consumable extends BuildableDataComponent<Consumable, Consumable.Builder> {

    @Contract(value = "-> new", pure = true)
    static Consumable.Builder consumable() {
        return ItemComponentTypesBridge.bridge().consumable();
    }

    @Contract(pure = true)
    @NonNegative float consumeSeconds();

    @Contract(pure = true)
    ItemUseAnimation animation();

    @Contract(pure = true)
    Key sound();

    @Contract(pure = true)
    boolean hasConsumeParticles();

    @Contract(pure = true)
    @Unmodifiable List<ConsumeEffect> consumeEffects();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link Consumable} 的构建器。
     * <p>原文：
     * Builder for {@link Consumable}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<Consumable> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置食用该物品所需的时间（秒）。
         * <p>原文：
         * Sets the amount of time (in seconds) it takes to consume the item.
         *
         * @param consumeSeconds 食用时长（秒）
         * <p>原文：the consumption duration in seconds
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder consumeSeconds(@NonNegative float consumeSeconds);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置玩家食用该物品时使用的手部动画。
         * <p>原文：
         * Sets the hand animation used when a player consumes the item.
         *
         * @param animation 表示要使用的手部动画的 {@link ItemUseAnimation}
         * <p>原文：the {@link ItemUseAnimation} representing the hand animation to be used
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder animation(ItemUseAnimation animation);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置食用该物品时播放的声音。
         * <p>原文：
         * Sets the sound played when consuming the item.
         *
         * @param sound 表示要使用的声音的 {@link Key}
         * <p>原文：the {@link Key} representing the sound to be used
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder sound(Key sound);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置食用该物品时是否产生粒子效果。
         * <p>原文：
         * Sets whether consuming the item results in particle effects.
         *
         * @param hasConsumeParticles true 表示食用时启用粒子效果，false 表示禁用
         * <p>原文：true to enable particle effects upon consumption, false to disable
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder hasConsumeParticles(boolean hasConsumeParticles);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置物品被食用时产生的效果。<br> <b>注意：</b>这会清除之前设置的所有效果。
         * <p>原文：
         * Sets the effects that occur when an item is consumed.
         * <br>
         * <b>Note:</b> this clears any previous effects set.
         *
         * @param effects 表示食用时要施加的效果的 {@link ConsumeEffect} 实例列表
         * <p>原文：a list of {@link ConsumeEffect} instances representing the effects to apply upon consumption
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder effects(List<ConsumeEffect> effects);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向正在构建的可食用物品添加一个 {@link ConsumeEffect}。
         * <p>原文：
         * Adds a single {@link ConsumeEffect} to the consumable item being built.
         *
         * @param effect 要添加的 {@link ConsumeEffect} 实例
         * <p>原文：the {@link ConsumeEffect} instance to add
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addEffect(ConsumeEffect effect);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向正在构建的可食用物品添加多个 {@link ConsumeEffect} 实例。
         * <p>原文：
         * Adds multiple {@link ConsumeEffect} instances to the consumable item being built.
         *
         * @param effects 要添加的 {@link ConsumeEffect} 实例列表
         * <p>原文：a list of {@link ConsumeEffect} instances to add
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addEffects(List<ConsumeEffect> effects);
    }
}
