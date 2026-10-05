package io.papermc.paper.datacomponent.item;

import org.checkerframework.common.value.qual.IntRange;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有不祥之瓶的倍率。
 * <p>原文：
 * Holds the ominous bottle amplifier.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#OMINOUS_BOTTLE_AMPLIFIER
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface OminousBottleAmplifier {

    @Contract(value = "_ -> new", pure = true)
    static OminousBottleAmplifier amplifier(final @IntRange(from = 0, to = 4) int amplifier) {
        return ItemComponentTypesBridge.bridge().ominousBottleAmplifier(amplifier);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取瓶子的倍率。
     * <p>原文：
     * Gets the bottle amplifier.
     *
     * @return 倍率
     * <p>原文：the amplifier
     */
    @Contract(pure = true)
    @IntRange(from = 0, to = 4) int amplifier();
}
