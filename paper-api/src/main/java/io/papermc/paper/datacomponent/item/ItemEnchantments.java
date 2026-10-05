package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import java.util.Map;
import org.bukkit.enchantments.Enchantment;
import org.checkerframework.common.value.qual.IntRange;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在物品上存储魔咒及其等级的列表。
 * <p>原文：
 * Stores a list of enchantments and their levels on an item.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#ENCHANTMENTS
 * @see io.papermc.paper.datacomponent.DataComponentTypes#STORED_ENCHANTMENTS
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface ItemEnchantments {

    @Contract(value = "_ -> new", pure = true)
    static ItemEnchantments itemEnchantments(final Map<Enchantment, @IntRange(from = 1, to = 255) Integer> enchantments) {
        return itemEnchantments().addAll(enchantments).build();
    }

    @Contract(value = "-> new", pure = true)
    static ItemEnchantments.Builder itemEnchantments() {
        return ItemComponentTypesBridge.bridge().enchantments();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此物品当前拥有的魔咒。
     * <p>原文：
     * Enchantments currently present on this item.
     *
     * @return 魔咒
     * <p>原文：enchantments
     */
    @Contract(pure = true)
    @Unmodifiable Map<Enchantment, @IntRange(from = 1, to = 255) Integer> enchantments();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link ItemEnchantments} 的构建器。
     * <p>原文：
     * Builder for {@link ItemEnchantments}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<ItemEnchantments> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此组件添加一个指定等级的魔咒。
         * <p>原文：
         * Adds an enchantment with the given level to this component.
         *
         * @param enchantment 魔咒
         * <p>原文：enchantment
         * @param level 等级
         * <p>原文：level
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #enchantments()
         */
        @Contract(value = "_, _ -> this", mutates = "this")
        Builder add(Enchantment enchantment, @IntRange(from = 1, to = 255) int level);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此组件添加多个指定等级的魔咒。
         * <p>原文：
         * Adds enchantments with the given level to this component.
         *
         * @param enchantments 魔咒
         * <p>原文：enchantments
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #enchantments()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addAll(Map<Enchantment, @IntRange(from = 1, to = 255) Integer> enchantments);
    }
}
