package io.papermc.paper.datacomponent;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 所有组件构建器的基础构建器类型。
 * <p>原文：
 * Base builder type for all component builders.
 *
 * @param <C> 构建的组件类型
 * <p>原文：built component type
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface DataComponentBuilder<C> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 构建不可变的组件值。
     * <p>原文：
     * Builds the immutable component value.
     *
     * @return 新的组件值
     * <p>原文：a new component value
     */
    @Contract(value = "-> new", pure = true)
    C build();
}
