package io.papermc.paper.registry.set;

import com.google.common.collect.Lists;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.tag.Tag;
import org.bukkit.Keyed;
import org.jetbrains.annotations.Contract;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示与注册表关联的集合。
 * <p>
 * 注册表集合有 3 种类型：
 * <ul>
 * <li>{@link Tag}，即来自原版或数据包的标签，通过 {@link org.bukkit.Registry#getTag(io.papermc.paper.registry.tag.TagKey)} 获取。</li>
 * <li>{@link RegistryKeySet}，即与注册表中存在的值相关联的键集合，通过 {@link #keySet(RegistryKey, Iterable)} 或 {@link #keySetFromValues(RegistryKey, Iterable)} 创建。</li>
 * <li>{@link RegistryValueSet}，即匿名值（在注册表中没有键）的集合，通过 {@link #valueSet(RegistryKey, Iterable)} 创建。</li>
 * </ul>
 * <p>原文：
 * Represents a collection tied to a registry.
 * <p>
 * There are 3 types of registry sets:
 * <ul>
 *     <li>{@link Tag} which is a tag from vanilla or a datapack.
 *     These are obtained via {@link org.bukkit.Registry#getTag(io.papermc.paper.registry.tag.TagKey)}.</li>
 *     <li>{@link RegistryKeySet} which is a set of keys linked to values that are present in the registry. These are
 *     created via {@link #keySet(RegistryKey, Iterable)} or {@link #keySetFromValues(RegistryKey, Iterable)}.</li>
 *     <li>{@link RegistryValueSet} which is a set of values which are anonymous (don't have keys in the registry). These are
 *     created via {@link #valueSet(RegistryKey, Iterable)}.</li>
 * </ul>
 *
 * @param <T> 注册表值类型
 * <p>原文：registry value type
 */
public sealed interface RegistrySet<T> permits RegistryKeySet, RegistryValueSet {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从匿名值创建 {@link RegistryValueSet}。
     * <p>
     * 提供的所有值在给定注册表中都<b>不得</b>具有键。
     * </p>
     * <p>原文：
     * Creates a {@link RegistryValueSet} from anonymous values.
     * <p>All values provided <b>must not</b> have keys in the given registry.</p>
     *
     * @param registryKey 这些值类型的注册表键
     * <p>原文：the registry key for the type of these values
     * @param values 值
     * <p>原文：the values
     * @return 新的注册表集合
     * <p>原文：a new registry set
     * @param <T> 各值的类型
     * <p>原文：the type of the values
     */
    @Contract(value = "_, _ -> new", pure = true)
    static <T> RegistryValueSet<T> valueSet(final RegistryKey<T> registryKey, final Iterable<? extends T> values) {
        return RegistryValueSetImpl.create(registryKey, values);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从注册表支持的值创建 {@link RegistryKeySet}。
     * <p>
     * 提供的所有值在给定注册表中都<b>必须</b>具有键。<!--For anonymous values, use {@link #valueSet(RegistryKey, Iterable)}-->
     * </p>
     * <p>
     * 如果尚无法获得实际对象的引用，请使用 {@link #keySet(RegistryKey, Iterable)}，仅凭 {@link TypedKey TypedKeys} 创建等效的 {@link RegistryKeySet}。
     * </p>
     * <p>原文：
     * Creates a {@link RegistryKeySet} from registry-backed values.
     * <p>All values provided <b>must</b> have keys in the given registry.
     * <!--For anonymous values, use {@link #valueSet(RegistryKey, Iterable)}--></p>
     * <p>If references to actual objects are not available yet, use {@link #keySet(RegistryKey, Iterable)} to
     * create an equivalent {@link RegistryKeySet} using just {@link TypedKey TypedKeys}.</p>
     *
     * @param registryKey 这些值所属者的注册表键
     * <p>原文：the registry key for the owner of these values
     * @param values 值
     * <p>原文：the values
     * @return 新的注册表集合
     * <p>原文：a new registry set
     * @param <T> 各值的类型
     * <p>原文：the type of the values
     * @throws IllegalArgumentException 如果注册表尚不可用，或任何值在该注册表中没有键
     * <p>原文：if the registry isn't available yet or if any value doesn't have a key in that registry
     */
    @Contract(value = "_, _ -> new", pure = true)
    static <T extends Keyed> RegistryKeySet<T> keySetFromValues(final RegistryKey<T> registryKey, final Iterable<? extends T> values) { // TODO remove Keyed
        return RegistryKeySetImpl.create(registryKey, values);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从 {@link TypedKey TypedKeys} 创建直接的 {@link RegistrySet}。
     * <p>原文：
     * Creates a direct {@link RegistrySet} from {@link TypedKey TypedKeys}.
     *
     * @param registryKey 这些键所属者的注册表键
     * <p>原文：the registry key for the owner of these keys
     * @param keys 各值的键
     * <p>原文：the keys for the values
     * @return 新的注册表集合
     * <p>原文：a new registry set
     * @param <T> 各值的类型
     * <p>原文：the type of the values
     */
    @SafeVarargs
    static <T extends Keyed> RegistryKeySet<T> keySet(final RegistryKey<T> registryKey, final TypedKey<T>... keys) { // TODO remove Keyed
        return keySet(registryKey, Lists.newArrayList(keys));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 从 {@link TypedKey TypedKeys} 创建直接的 {@link RegistrySet}。
     * <p>原文：
     * Creates a direct {@link RegistrySet} from {@link TypedKey TypedKeys}.
     *
     * @param registryKey 这些键所属者的注册表键
     * <p>原文：the registry key for the owner of these keys
     * @param keys 各值的键
     * <p>原文：the keys for the values
     * @return 新的注册表集合
     * <p>原文：a new registry set
     * @param <T> 各值的类型
     * <p>原文：the type of the values
     */
    @SuppressWarnings("BoundedWildcard")
    @Contract(value = "_, _ -> new", pure = true)
    static <T extends Keyed> RegistryKeySet<T> keySet(final RegistryKey<T> registryKey, final Iterable<TypedKey<T>> keys) { // TODO remove Keyed
        return new RegistryKeySetImpl<>(registryKey, Lists.newArrayList(keys));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此集合的注册表键。
     * <p>原文：
     * Get the registry key for this set.
     *
     * @return 注册表键
     * <p>原文：the registry key
     */
    RegistryKey<T> registryKey();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此集合的大小。
     * <p>原文：
     * Get the size of this set.
     *
     * @return 大小
     * <p>原文：the size
     */
    int size();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查注册表集合是否为空。
     * <p>原文：
     * Checks if the registry set is empty.
     *
     * @return 如果为空则为 true
     * <p>原文：true, if empty
     */
    default boolean isEmpty() {
        return this.size() == 0;
    }
}
