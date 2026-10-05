package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import net.kyori.adventure.key.Key;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有容器的战利品表和种子。
 * <p>原文：
 * Holds the loot table and seed for a container.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#CONTAINER_LOOT
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface SeededContainerLoot {

    @Contract(value = "_, _ -> new", pure = true)
    static SeededContainerLoot seededContainerLoot(final Key lootTableKey, final long seed) {
        return SeededContainerLoot.seededContainerLoot(lootTableKey).seed(seed).build();
    }

    @Contract(value = "_ -> new", pure = true)
    static SeededContainerLoot.Builder seededContainerLoot(final Key lootTableKey) {
        return ItemComponentTypesBridge.bridge().seededContainerLoot(lootTableKey);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取战利品表键。
     * <p>原文：
     * Gets the loot table key.
     *
     * @return 战利品表键
     * <p>原文：the loot table key
     */
    @Contract(pure = true)
    Key lootTable();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取战利品表种子。
     * <p>原文：
     * Gets the loot table seed.
     *
     * @return 种子
     * <p>原文：the seed
     */
    @Contract(pure = true)
    long seed();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link SeededContainerLoot} 的构建器。
     * <p>原文：
     * Builder for {@link SeededContainerLoot}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<SeededContainerLoot> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置战利品表键。
         * <p>原文：
         * Sets the loot table key.
         *
         * @param key 战利品表键
         * <p>原文：the loot table key
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #lootTable()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder lootTable(Key key);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 设置战利品表种子。
         * <p>原文：
         * Sets the loot table seed.
         *
         * @param seed 种子
         * <p>原文：the seed
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #seed()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder seed(long seed);
    }
}
