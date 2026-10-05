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
 * 持有物品容器的内容。
 * <p>原文：
 * Holds the contents of an item container.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#CONTAINER
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface ItemContainerContents {

    @Contract(value = "_ -> new", pure = true)
    static ItemContainerContents containerContents(final List<ItemStack> contents) {
        return containerContents().addAll(contents).build();
    }

    @Contract(value = "-> new", pure = true)
    static ItemContainerContents.Builder containerContents() {
        return ItemComponentTypesBridge.bridge().itemContainerContents();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取容器的内容。
     * <p>原文：
     * Gets the contents of the container.
     *
     * @return 内容
     * <p>原文：the contents
     */
    @Contract(value = "-> new", pure = true)
    @Unmodifiable List<ItemStack> contents();

    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<ItemContainerContents> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向容器添加一个物品堆。
         * <p>原文：
         * Adds an item stack to the container.
         *
         * @param stack 物品堆
         * <p>原文：the item stack
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #contents()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder add(ItemStack stack);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向容器添加多个物品堆。
         * <p>原文：
         * Adds item stacks to the container.
         *
         * @param stacks 物品堆
         * <p>原文：the item stacks
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #contents()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addAll(List<ItemStack> stacks);
    }
}
