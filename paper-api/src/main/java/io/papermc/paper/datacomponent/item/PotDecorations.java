package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.bukkit.inventory.ItemType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有花盆上装饰所用的物品类型。
 * <p>原文：
 * Holds the item types for the decorations on a flower pot.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#POT_DECORATIONS
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface PotDecorations {

    @Contract(value = "_, _, _, _ -> new", pure = true)
    static PotDecorations potDecorations(final @Nullable ItemType back, final @Nullable ItemType left, final @Nullable ItemType right, final @Nullable ItemType front) {
        return potDecorations().back(back).left(left).right(right).front(front).build();
    }

    @Contract(value = "-> new", pure = true)
    static PotDecorations.Builder potDecorations() {
        return ItemComponentTypesBridge.bridge().potDecorations();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取后方的物品类型。
     * <p>原文：
     * Get the item type for the back.
     *
     * @return 后方的物品类型。
     * <p>原文：the back item type.
     */
    @Contract(pure = true)
    @Nullable ItemType back();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取左方的物品类型。
     * <p>原文：
     * Get the item type for the left.
     *
     * @return 左侧的物品类型。
     * <p>原文：the left item type.
     */
    @Contract(pure = true)
    @Nullable ItemType left();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取右方的物品类型。
     * <p>原文：
     * Get the item type for the right.
     *
     * @return 右侧的物品类型。
     * <p>原文：the right item type.
     */
    @Contract(pure = true)
    @Nullable ItemType right();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取前方的物品类型。
     * <p>原文：
     * Get the item type for the front.
     *
     * @return 前方的物品类型。
     * <p>原文：the front item type.
     */
    @Contract(pure = true)
    @Nullable ItemType front();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link PotDecorations} 的构建器。
     * <p>原文：
     * Builder for {@link PotDecorations}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<PotDecorations> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置后方的 {@link ItemType}。
         * <p>原文：
         * Set the {@link ItemType} for the back.
         *
         * @param back 后方的物品
         * <p>原文：item for the back
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #back()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder back(@Nullable ItemType back);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置左方的 {@link ItemType}。
         * <p>原文：
         * Set the {@link ItemType} for the left.
         *
         * @param left 左侧的物品
         * <p>原文：item for the left
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #left()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder left(@Nullable ItemType left);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置右方的 {@link ItemType}。
         * <p>原文：
         * Set the {@link ItemType} for the right.
         *
         * @param right 右侧的物品
         * <p>原文：item for the right
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #right()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder right(@Nullable ItemType right);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置前方的 {@link ItemType}。
         * <p>原文：
         * Set the {@link ItemType} for the front.
         *
         * @param front 前方的物品
         * <p>原文：item for the front
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #front()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder front(@Nullable ItemType front);
    }
}
