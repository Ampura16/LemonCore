package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.bukkit.inventory.meta.trim.ArmorTrim;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有应用于物品的纹饰。
 * <p>原文：
 * Holds the trims applied to an item.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#TRIM
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface ItemArmorTrim  {

    @Contract(value = "_ -> new", pure = true)
    static ItemArmorTrim.Builder itemArmorTrim(final ArmorTrim armorTrim) {
        return ItemComponentTypesBridge.bridge().itemArmorTrim(armorTrim);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此物品上的盔甲纹饰。
     * <p>原文：
     * Armor trim present on this item.
     *
     * @return 纹饰
     * <p>原文：trim
     */
    @Contract(pure = true)
    ArmorTrim armorTrim();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link ItemArmorTrim} 的构建器。
     * <p>原文：
     * Builder for {@link ItemArmorTrim}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<ItemArmorTrim> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 为此构建器设置盔甲纹饰。
         * <p>原文：
         * Sets the armor trim for this builder.
         *
         * @param armorTrim 纹饰
         * <p>原文：trim
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #armorTrim()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder armorTrim(ArmorTrim armorTrim);
    }
}
