package io.papermc.paper.datacomponent.item;

import io.papermc.paper.datacomponent.DataComponentBuilder;
import io.papermc.paper.potion.SuspiciousEffectEntry;
import java.util.Collection;
import java.util.List;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有食用迷之炖菜时将施加的效果。
 * <p>原文：
 * Holds the effects that will be applied when consuming Suspicious Stew.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#SUSPICIOUS_STEW_EFFECTS
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface SuspiciousStewEffects {

    @Contract(value = "_ -> new", pure = true)
    static SuspiciousStewEffects suspiciousStewEffects(final Collection<SuspiciousEffectEntry> effects) {
        return suspiciousStewEffects().addAll(effects).build();
    }

    @Contract(value = "-> new", pure = true)
    static SuspiciousStewEffects.Builder suspiciousStewEffects() {
        return ItemComponentTypesBridge.bridge().suspiciousStewEffects();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 食用迷之炖菜时将施加的效果。
     * <p>原文：
     * Effects that will be applied when consuming Suspicious Stew.
     *
     * @return 效果
     * <p>原文：effects
     */
    @Contract(pure = true)
    @Unmodifiable List<SuspiciousEffectEntry> effects();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * {@link SuspiciousStewEffects} 的构建器。
     * <p>原文：
     * Builder for {@link SuspiciousStewEffects}.
     */
    @ApiStatus.Experimental
    @ApiStatus.NonExtendable
    interface Builder extends DataComponentBuilder<SuspiciousStewEffects> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加一个施加的效果。
         * <p>原文：
         * Adds an effect applied to this builder.
         *
         * @param entry 效果
         * <p>原文：effect
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #effects()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder add(SuspiciousEffectEntry entry);

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 向此构建器添加多个施加的效果。
         * <p>原文：
         * Adds effects applied to this builder.
         *
         * @param entries 效果
         * <p>原文：effect
         * @return 构建器，用于链式调用
         * <p>原文：the builder for chaining
         * @see #effects()
         */
        @Contract(value = "_ -> this", mutates = "this")
        Builder addAll(Collection<SuspiciousEffectEntry> entries);
    }
}
