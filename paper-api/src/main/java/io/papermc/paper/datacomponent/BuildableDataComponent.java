package io.papermc.paper.datacomponent;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface BuildableDataComponent<C extends BuildableDataComponent<C, B>, B extends DataComponentBuilder<C>> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从此数据组件创建新的构建器。
     * <p>原文：
     * Creates a new builder from this data component.
     *
     * @return 新的构建器
     * <p>原文：a new builder
     */
    @Contract(value = "-> new", pure = true)
    B toBuilder();
}
