package io.papermc.paper.registry.event;

import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.RegistryBuilderFactory;
import io.papermc.paper.registry.TypedKey;
import java.util.function.Consumer;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 支持注册新对象的注册表。
 * <p>原文：
 * A registry which supports registering new objects.
 *
 * @param <T> 注册表条目类型
 * <p>原文：registry entry type
 * @param <B> 注册表条目构建器类型
 * <p>原文：registry entry builder type
 */
@ApiStatus.NonExtendable
public interface WritableRegistry<T, B extends RegistryBuilder<T>> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 以指定键注册新值。这将为新条目触发 {@link RegistryEntryAddEvent}。
     * <p>原文：
     * Register a new value with the specified key. This will
     * fire a {@link RegistryEntryAddEvent} for the new entry.
     *
     * @param key 条目的键（必须与其他条目唯一区分）
     * <p>原文：the entry's key (must be unique from others)
     * @param value 条目构建器的消费者
     * <p>原文：a consumer for the entry's builder
     */
    default void register(final TypedKey<T> key, final Consumer<? super B> value) {
        this.registerWith(key, factory -> value.accept(factory.empty()));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 以指定键注册新值。这将为新条目触发 {@link RegistryEntryAddEvent}。{@link RegistryBuilderFactory} 允许你用已有条目的属性预填充构建器。
     * <p>原文：
     * Register a new value with the specified key. This will
     * fire a {@link RegistryEntryAddEvent} for the new entry. The
     * {@link RegistryBuilderFactory} lets you pre-fill a builder with
     * an already-existing entry's properties.
     *
     * @param key 条目的键（必须与其他条目唯一区分）
     * <p>原文：the entry's key (must be unique from others)
     * @param value 构建器工厂的消费者
     * <p>原文：a consumer of a builder factory
     */
    void registerWith(TypedKey<T> key, Consumer<RegistryBuilderFactory<T, B>> value);
}
