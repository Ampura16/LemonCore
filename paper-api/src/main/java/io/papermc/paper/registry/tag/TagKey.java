package io.papermc.paper.registry.tag;

import io.papermc.paper.registry.RegistryKey;
import net.kyori.adventure.key.Key;
import net.kyori.adventure.key.KeyPattern;
import net.kyori.adventure.key.Keyed;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

@NullMarked
public sealed interface TagKey<T> extends Keyed permits TagKeyImpl {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为注册表创建新的标签键。
     * <p>原文：
     * Creates a new tag key for a registry.
     *
     * @param registryKey 该标签的注册表
     * <p>原文：the registry for the tag
     * @param key 标签的特定键
     * <p>原文：the specific key for the tag
     * @param <T> 注册表值类型
     * <p>原文：the registry value type
     * @return 新的标签键
     * <p>原文：a new tag key
     */
    @Contract(value = "_, _ -> new", pure = true)
    static <T> TagKey<T> create(final RegistryKey<T> registryKey, final Key key) {
        return new TagKeyImpl<>(registryKey, key);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为注册表创建新的标签键。
     * <p>原文：
     * Creates a new tag key for a registry.
     *
     * @param registryKey 该标签的注册表
     * <p>原文：the registry for the tag
     * @param key {@link Key} 的字符串形式，将传递给 {@link Key#key(String)} 进行解析。
     * <p>原文：the string version of a {@link Key} that will be passed to {@link Key#key(String)} for parsing.
     * @param <T> 注册表值类型
     * <p>原文：the registry value type
     * @return 新的标签键
     * <p>原文：a new tag key
     * @see Key#key(String)
     */
    static <T> TagKey<T> create(final RegistryKey<T> registryKey, @KeyPattern final String key) {
        return create(registryKey, Key.key(key));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此标签键的注册表键。
     * <p>原文：
     * Get the registry key for this tag key.
     *
     * @return 注册表键
     * <p>原文：the registry key
     */
    RegistryKey<T> registryKey();
}
