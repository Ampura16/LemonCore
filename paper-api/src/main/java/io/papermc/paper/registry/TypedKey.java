package io.papermc.paper.registry;

import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import net.kyori.adventure.key.Keyed;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示特定注册表中某个值的键。
 * <p>原文：
 * Represents a key for a value in a specific registry.
 *
 * @param <T> 注册表的值类型
 * <p>原文：the value type for the registry
 */
@NullMarked
public sealed interface TypedKey<T> extends Key permits TypedKeyImpl {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取注册表中该值的键。
     * <p>原文：
     * Gets the key for the value in the registry.
     *
     * @return 值的键
     * <p>原文：the value's key
     */
    @Override
    Key key();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此键所表示值的注册表键。
     * <p>原文：
     * Gets the registry key for the value this key
     * represents.
     *
     * @return 注册表键
     * <p>原文：the registry key
     */
    RegistryKey<T> registryKey();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从键和注册表键创建类型化键。
     * <p>原文：
     * Create a typed key from a key and a registry key.
     *
     * @param registryKey 此键所属的注册表
     * <p>原文：the registry this key is for
     * @param key 该值在注册表中的键
     * <p>原文：the key for the value in the registry
     * @param <T> 值类型
     * <p>原文：value type
     * @return 由值键和注册表键组成的新键
     * <p>原文：a new key for the value key and registry key
     */
    static <T> TypedKey<T> create(final RegistryKey<T> registryKey, final Key key) {
        return new TypedKeyImpl<>(key, registryKey);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从字符串和注册表键创建类型化键。
     * <p>原文：
     * Create a typed key from a string and a registry key.
     *
     * @param registryKey 此键所属的注册表
     * <p>原文：the registry this key is for
     * @param key {@link Key} 的字符串形式，将传递给 {@link Key#key(String)} 进行解析。
     * <p>原文：the string version of a {@link Key} that will be passed to {@link Key#key(String)} for parsing.
     * @param <T> 值类型
     * <p>原文：value type
     * @return 由值键和注册表键组成的新键
     * <p>原文：a new key for the value key and registry key
     * @see Key#key(String)
     */
    static <T> TypedKey<T> create(final RegistryKey<T> registryKey, @KeyPattern final String key) {
        return create(registryKey, Key.key(key));
    }
}
