package io.papermc.paper.datacomponent.item;

import io.papermc.paper.block.BlockPredicate;
import io.papermc.paper.datacomponent.DataComponentBuilder;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 控制冒险模式玩家可以用此物品对哪些方块执行特定操作。
 * <p>原文：
 * Controls which blocks a player in Adventure mode can do a certain action with this item.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#CAN_BREAK
 * @see io.papermc.paper.datacomponent.DataComponentTypes#CAN_PLACE_ON
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface ItemAdventurePredicate {

    @Contract(value = "_ -> new", pure = true)
    static ItemAdventurePredicate itemAdventurePredicate(final List<BlockPredicate> predicates) {
        return itemAdventurePredicate().addPredicates(predicates).build();
    }

    @Contract(value = "-> new", pure = true)
    static ItemAdventurePredicate.Builder itemAdventurePredicate() {
        return ItemComponentTypesBridge.bridge().itemAdventurePredicate();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 控制是否允许该操作的方块谓词列表。
     * <p>原文：
     * List of block predicates that control if the action is allowed.
     *
     * @return 谓词
     * <p>原文：predicates
     */
    @Contract(pure = true)
    @Unmodifiable List<BlockPredicate> predicates();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link ItemAdventurePredicate} 的构建器。
     * <p>原文：
     * Builder for {@link ItemAdventurePredicate}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<ItemAdventurePredicate> {
        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加一个方块谓词。
         * <p>原文：
         * Adds a block predicate to this builder.
         *
         * @param predicate 谓词
         * <p>原文：predicate
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #predicates()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addPredicate(BlockPredicate predicate);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加多个方块谓词。
         * <p>原文：
         * Adds block predicates to this builder.
         *
         * @param predicates 谓词
         * <p>原文：predicates
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #predicates()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addPredicates(List<BlockPredicate> predicates);
    }
}
