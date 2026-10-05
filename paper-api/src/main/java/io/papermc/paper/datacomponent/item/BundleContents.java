package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import java.util.List;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有收纳袋中存放的所有物品。
 * <p>原文：
 * Holds all items stored inside of a Bundle.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#BUNDLE_CONTENTS
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface BundleContents {

    @Contract(value = "_ -> new", pure = true)
    static BundleContents bundleContents(final List<ItemStack> contents) {
        return ItemComponentTypesBridge.bridge().bundleContents().addAll(contents).build();
    }

    @Contract(value = "-> new", pure = true)
    static BundleContents.Builder bundleContents() {
        return ItemComponentTypesBridge.bridge().bundleContents();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 列出此组件中当前存储的物品。
     * <p>原文：
     * Lists the items that are currently stored inside of this component.
     *
     * @return 物品
     * <p>原文：items
     */
    @Contract(pure = true)
    @Unmodifiable List<ItemStack> contents();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link BundleContents} 的构建器。
     * <p>原文：
     * Builder for {@link BundleContents}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<BundleContents> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加一个物品。
         * <p>原文：
         * Adds an item to this builder.
         *
         * @param stack 物品
         * <p>原文：item
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #contents()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder add(ItemStack stack);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加多个物品。
         * <p>原文：
         * Adds items to this builder.
         *
         * @param stacks 物品
         * <p>原文：items
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #contents()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addAll(List<ItemStack> stacks);
    }
}
