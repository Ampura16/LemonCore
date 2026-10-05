package io.papermc.paper.registry.set;

import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.tag.Tag;
import io.papermc.paper.registry.tag.TagKey;
import java.util.Collection;
import java.util.Iterator;
import org.bukkit.Keyed;
import org.bukkit.Registry;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Unmodifiable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示与注册表关联的集合。
 * <p>
 * 注册表键集合有 2 种类型：
 * <ul>
 * <li>{@link Tag}，即来自原版或数据包的标签，通过 {@link org.bukkit.Registry#getTag(io.papermc.paper.registry.tag.TagKey)} 获取。</li>
 * <li>{@link RegistryKeySet}，即与注册表中存在的值相关联的键集合，通过 {@link #keySet(RegistryKey, Iterable)} 或 {@link #keySetFromValues(RegistryKey, Iterable)} 创建。</li>
 * </ul>
 * <p>原文：
 * Represents a collection tied to a registry.
 * <p>
 * There are 2 types of registry key sets:
 * <ul>
 *     <li>{@link Tag} which is a tag from vanilla or a datapack.
 *     These are obtained via {@link org.bukkit.Registry#getTag(io.papermc.paper.registry.tag.TagKey)}.</li>
 *     <li>{@link RegistryKeySet} which is a set of keys linked to values that are present in the registry. These are
 *     created via {@link #keySet(RegistryKey, Iterable)} or {@link #keySetFromValues(RegistryKey, Iterable)}.</li>
 * </ul>
 *
 * @param <T> 注册表值类型
 * <p>原文：registry value type
 */
@ApiStatus.NonExtendable
public non-sealed interface RegistryKeySet<T extends Keyed> extends Iterable<TypedKey<T>>, RegistrySet<T> { // TODO remove Keyed

    @Override
    default int size() {
        return this.values().size();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此集合中各值的键。
     * <p>原文：
     * Get the keys for the values in this set.
     *
     * @return 键
     * <p>原文：the keys
     */
    @Unmodifiable Collection<TypedKey<T>> values();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将此集合解析为值的集合。建议优先使用 {@link #values()}。
     * <p>原文：
     * Resolve this set into a collection of values. Prefer using
     * {@link #values()}.
     *
     * @param registry 用于解析值的注册表（必须与 {@link #registryKey()} 匹配）
     * <p>原文：the registry to resolve the values from (must match {@link #registryKey()})
     * @return 解析后的值
     * <p>原文：the resolved values
     * @see RegistryKeySet#values()
     * @see Registry#getTagValues(TagKey)
     */
    @Unmodifiable Collection<T> resolve(final Registry<T> registry);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查此集合是否包含给定键的值。
     * <p>原文：
     * Checks if this set contains the value with the given key.
     *
     * @param valueKey 要检查的键
     * <p>原文：the key to check
     * @return 如果该值在此集合中则为 true
     * <p>原文：true if the value is in this set
     */
    boolean contains(TypedKey<T> valueKey);

    @Override
    default Iterator<TypedKey<T>> iterator() {
        return this.values().iterator();
    }
}
