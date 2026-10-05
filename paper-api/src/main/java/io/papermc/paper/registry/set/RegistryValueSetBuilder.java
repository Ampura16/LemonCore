package io.papermc.paper.registry.set;

import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.RegistryBuilderFactory;
import java.util.function.Consumer;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link RegistryValueSet} 的构建器。
 * <p>原文：
 * A builder for a {@link RegistryValueSet}.
 *
 * @param <API> API 类型
 * <p>原文：the API type
 * @param <ENTRY_BUILDER> 条目构建器的类型，
 * <p>原文：the type of the entry builder,
 */
@ApiStatus.NonExtendable
public interface RegistryValueSetBuilder<API, ENTRY_BUILDER extends RegistryBuilder<API>> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 向注册表值集合添加一个值。
     * <p>原文：
     * Adds a value to the registry value set.
     *
     * @param builder 要添加的值的构建器
     * <p>原文：the builder for the value to add
     * @return 此构建器，用于链式调用
     * <p>原文：this builder for chaining
     */
    RegistryValueSetBuilder<API, ENTRY_BUILDER> add(Consumer<RegistryBuilderFactory<API, ? extends ENTRY_BUILDER>> builder);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 构建 {@link RegistryValueSet}。
     * <p>原文：
     * Builds the {@link RegistryValueSet}.
     *
     * @return 构建完成的注册表值集合
     * <p>原文：the built registry value set
     */
    RegistryValueSet<API> build();
}
