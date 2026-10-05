package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Range;
import org.jspecify.annotations.NullMarked;

@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface AttackRange {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回用于创建攻击范围的新构建器。
     * <p>原文：
     * Returns a new builder for creating an Attack Range.
     *
     * @return 构建器实例
     * <p>原文：a builder instance
     */
    @Contract(value = "-> new", pure = true)
    static Builder attackRange() {
        return ItemComponentTypesBridge.bridge().attackRange();
    }

    @Contract(pure = true)
    @Range(from = 0, to = 64) float minReach();

    @Contract(pure = true)
    @Range(from = 0, to = 64) float maxReach();

    @Contract(pure = true)
    @Range(from = 0, to = 64) float minCreativeReach();

    @Contract(pure = true)
    @Range(from = 0, to = 64) float maxCreativeReach();

    @Contract(pure = true)
    @Range(from = 0, to = 1) float hitboxMargin();

    @Contract(pure = true)
    @Range(from = 0, to = 2) float mobFactor();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link AttackRange} 的构建器。
     * <p>原文：
     * Builder for {@link AttackRange}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<AttackRange> {

        @Contract(value = "_ -> this", mutates = "this")
        Builder minReach(@Range(from = 0, to = 64) float minReach);

        @Contract(value = "_ -> this", mutates = "this")
        Builder maxReach(@Range(from = 0, to = 64) float maxReach);

        @Contract(value = "_ -> this", mutates = "this")
        Builder minCreativeReach(@Range(from = 0, to = 64) float minCreativeReach);

        @Contract(value = "_ -> this", mutates = "this")
        Builder maxCreativeReach(@Range(from = 0, to = 64) float maxCreativeReach);

        @Contract(value = "_ -> this", mutates = "this")
        Builder hitboxMargin(@Range(from = 0, to = 1) float hitboxMargin);

        @Contract(value = "_ -> this", mutates = "this")
        Builder mobFactor(@Range(from = 0, to = 2) float mobFactor);
    }
}
