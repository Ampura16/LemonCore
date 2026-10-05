package io.papermc.paper.registry.set;

import io.papermc.paper.registry.data.dialog.DialogRegistryEntry;
import java.util.Collection;
import java.util.Iterator;
import org.jetbrains.annotations.Unmodifiable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 与某个注册表相关的匿名值集合。这些值与注册表类型相同，但不会出现在注册表中，因此称为匿名值。可通过 {@link RegistrySet#valueSet(io.papermc.paper.registry.RegistryKey, Iterable)} 创建，或在 {@link io.papermc.paper.registry.RegistryBuilder} 的上下文中通过 {@link DialogRegistryEntry.Builder#registryValueSet()} 等方法创建。
 * <p>原文：
 * A collection of anonymous values relating to a registry. These
 * are values of the same type as the registry, but will not be found
 * in the registry, hence, anonymous. Created via {@link RegistrySet#valueSet(io.papermc.paper.registry.RegistryKey, Iterable)} or
 * in the context of a {@link io.papermc.paper.registry.RegistryBuilder},
 * there are methods to create them like {@link DialogRegistryEntry.Builder#registryValueSet()}.
 * @param <T> 注册表值类型
 * <p>原文：registry value type
 */
public sealed interface RegistryValueSet<T> extends Iterable<T>, RegistrySet<T> permits RegistryValueSetImpl {

    @Override
    default int size() {
        return this.values().size();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此直接集合中的值集合。
     * <p>原文：
     * Get the collection of values in this direct set.
     *
     * @return 值
     * <p>原文：the values
     */
    @Unmodifiable Collection<T> values();

    @Override
    default Iterator<T> iterator() {
        return this.values().iterator();
    }
}
