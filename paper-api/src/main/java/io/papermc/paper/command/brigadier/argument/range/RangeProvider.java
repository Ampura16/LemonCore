package io.papermc.paper.command.brigadier.argument.range;

import com.google.common.collect.Range;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 数值范围的提供器
 * <p>原文：
 * A provider for a range of numbers
 *
 * @param <T>
 * @see io.papermc.paper.command.brigadier.argument.ArgumentTypes
 */
public sealed interface RangeProvider<T extends Comparable<?>> permits DoubleRangeProvider, IntegerRangeProvider {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 提供给定的范围。
     * <p>原文：
     * Provides the given range.
     * @return 范围
     * <p>原文：range
     */
    Range<T> range();
}
