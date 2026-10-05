package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import java.util.List;
import org.bukkit.Color;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有药水（药水、喷溅药水、滞留药水）的内容，或应用于药箭的药水。
 * <p>原文：
 * Holds the contents of a potion (Potion, Splash Potion, Lingering Potion), or potion applied to a Tipped Arrow.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#POTION_CONTENTS
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface PotionContents {

    @Contract(value = "-> new", pure = true)
    static PotionContents.Builder potionContents() {
        return ItemComponentTypesBridge.bridge().potionContents();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此物品中的药水类型：物品将继承其所有效果。
     * <p>原文：
     * The potion type in this item: the item will inherit all effects from this.
     *
     * @return 药水类型；如果不存在则为 {@code null}
     * <p>原文：potion type, or {@code null} if not present
     */
    @Contract(pure = true)
    @Nullable PotionType potion();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 覆盖药水的显示颜色。
     * <p>原文：
     * Overrides the visual color of the potion.
     *
     * @return 颜色覆盖；如果不存在则为 {@code null}
     * <p>原文：color override, or {@code null} if not present
     * @apiNote 颜色的 alpha 通道仅对药箭有意义
     * <p>原文：alpha channel of the color is only relevant
     * for Tipped Arrow
     */
    @Contract(pure = true)
    @Nullable Color customColor();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 该物品应施加的额外效果实例列表。
     * <p>原文：
     * Additional list of effect instances that this item should apply.
     *
     * @return 效果
     * <p>原文：effects
     */
    @Contract(pure = true)
    @Unmodifiable List<PotionEffect> customEffects();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 药水物品翻译键的后缀。
     * <p>原文：
     * Suffix to the translation key of the potion item.
     *
     * @return 翻译键后缀；如果不存在则为 {@code null}
     * <p>原文：translation key suffix, or {@code null} if not present
     * @apiNote 用于药箭和药水物品的显示。
     * <p>原文：This is used in the display of tipped arrow and potion items.
     */
    @Contract(pure = true)
    @Nullable String customName();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 该组件施加的全部效果。
     * <p>
     * 这是基础药水类型与所有自定义效果的组合。
     * <p>原文：
     * All effects that this component applies.
     * <p>
     * This is a combination of the base potion type and any custom effects.
     *
     * @return 所有效果的不可修改列表。
     * <p>原文：an unmodifiable list of all effects.
     */
    @Contract(pure = true)
    @Unmodifiable List<PotionEffect> allEffects();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 计算此药水内容组件的实际颜色。
     * <p>
     * 会混合所有自定义效果，或使用默认的后备颜色。可能带有也可能不带 alpha 通道（用于药箭）。
     * <p>原文：
     * Computes the effective colour of this potion contents component.
     * <p>
     * This blends all custom effects, or uses a default fallback colour.
     * It may or may not have an alpha channel, used for tipped arrows.
     *
     * @return 此组件实际显示的颜色。
     * <p>原文：the effective colour this component would display with.
     */
    @Contract(pure = true)
    Color computeEffectiveColor();

    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<PotionContents> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 为此构建器设置药水类型。
         * <p>原文：
         * Sets the potion type for this builder.
         *
         * @param type 构建器
         * <p>原文：builder
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #potion()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder potion(@Nullable PotionType type);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置此构建器的颜色覆盖。
         * <p>原文：
         * Sets the color override for this builder.
         *
         * @param color 颜色
         * <p>原文：color
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @apiNote 仅药箭支持颜色的 alpha 通道
         * <p>原文：alpha channel of the color is supported only for Tipped Arrow
         * @see #customColor()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder customColor(@Nullable Color color);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置药水物品翻译键的后缀。
         * <p>原文：
         * Sets the suffix to the translation key of the potion item.
         *
         * @param name 名称
         * <p>原文：name
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #customName()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder customName(@Nullable String name);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加一个自定义效果实例。
         * <p>原文：
         * Adds a custom effect instance to this builder.
         *
         * @param effect 效果
         * <p>原文：effect
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #customEffects()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addCustomEffect(PotionEffect effect);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加多个自定义效果实例。
         * <p>原文：
         * Adds custom effect instances to this builder.
         *
         * @param effects 效果
         * <p>原文：effects
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #customEffects()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addCustomEffects(List<PotionEffect> effects);
    }
}
