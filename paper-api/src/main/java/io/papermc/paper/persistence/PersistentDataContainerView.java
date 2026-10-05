package io.papermc.paper.persistence;

import java.util.Set;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示持久化数据容器的视图。此接口上的任何方法都不会修改容器。
 * <p>原文：
 * This represents a view of a persistent data container. No
 * methods on this interface mutate the container.
 *
 * @see PersistentDataContainer
 */
@NullMarked
@ApiStatus.NonExtendable
public interface PersistentDataContainerView {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回持久化元数据提供者是否注册了与所提供参数匹配的元数据。
     * <p>
     * 仅当找到的值与所提供键具有相同的基本数据类型时，该方法才返回 true。
     * <p>
     * 使用自定义 {@link PersistentDataType} 实现存储值时不会存储复杂数据类型。因此，存储的 UUID（通过存储 byte[]）会匹配 has("key" , {@link PersistentDataType#BYTE_ARRAY})。同样，存储的 byte[] 总会匹配你的 UUID {@link PersistentDataType}，即使它不是 16 字节长。
     * <p>
     * 该方法仅可用于自定义对象键。覆盖现有标签（例如显示名称）不起作用，因为这些值是使用你的命名空间存储的。
     * <p>原文：
     * Returns if the persistent metadata provider has metadata registered
     * matching the provided parameters.
     * <p>
     * This method will only return true if the found value has the same primitive
     * data type as the provided key.
     * <p>
     * Storing a value using a custom {@link PersistentDataType} implementation
     * will not store the complex data type. Therefore storing a UUID (by
     * storing a byte[]) will match has("key" ,
     * {@link PersistentDataType#BYTE_ARRAY}). Likewise a stored byte[] will
     * always match your UUID {@link PersistentDataType} even if it is not 16
     * bytes long.
     * <p>
     * This method is only usable for custom object keys. Overwriting existing
     * tags, like the display name, will not work as the values are stored
     * using your namespace.
     *
     * @param key 存储该值所用的键
     * <p>原文：the key the value is stored under
     * @param type 存储的基本类型值必须匹配的类型
     * <p>原文：the type the primitive stored value has to match
     * @param <P> 所存储基本类型的泛型类型
     * <p>原文：the generic type of the stored primitive
     * @param <C> 最终创建的复杂对象的泛型类型
     * <p>原文：the generic type of the eventually created complex object
     * @return 是否存在具有所提供键和类型的值
     * <p>原文：if a value with the provided key and type exists
     * @throws IllegalArgumentException 如果要查找的键为 null
     * <p>原文：if the key to look up is null
     * @throws IllegalArgumentException 如果要将找到的对象转换成的类型为 null
     * <p>原文：if the type to cast the found object to is
     * null
     */
    <P, C> boolean has(NamespacedKey key, PersistentDataType<P, C> type);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回持久化元数据提供者是否注册了与所提供参数匹配的元数据。
     * <p>
     * 只要存在具有给定键的值，无论其类型如何，该方法都会返回 true。
     * <p>
     * 该方法仅可用于自定义对象键。覆盖现有标签（例如显示名称）不起作用，因为这些值是使用你的命名空间存储的。
     * <p>原文：
     * Returns if the persistent metadata provider has metadata registered matching
     * the provided parameters.
     * <p>
     * This method will return true as long as a value with the given key exists,
     * regardless of its type.
     * <p>
     * This method is only usable for custom object keys. Overwriting existing tags,
     * like the display name, will not work as the values are stored using your
     * namespace.
     *
     * @param key 存储该值所用的键
     * <p>原文：the key the value is stored under
     * @return 是否存在具有所提供键的值
     * <p>原文：if a value with the provided key exists
     * @throws IllegalArgumentException 如果要查找的键为 null
     * <p>原文：if the key to look up is null
     */
    boolean has(NamespacedKey key);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回存储在 {@link PersistentDataHolder} 实例上的元数据值。
     * <p>原文：
     * Returns the metadata value that is stored on the
     * {@link PersistentDataHolder} instance.
     *
     * @param key 要在自定义标签映射中查找的键
     * <p>原文：the key to look up in the custom tag map
     * @param type 值必须具有并将被转换成的类型
     * <p>原文：the type the value must have and will be cast to
     * @param <P> 所存储基本类型的泛型类型
     * <p>原文：the generic type of the stored primitive
     * @param <C> 最终创建的复杂对象的泛型类型
     * <p>原文：the generic type of the eventually created complex object
     * @return 值；如果给定值下没有映射值则为 {@code null}
     * <p>原文：the value or {@code null} if no value was mapped under the given
     * value
     * @throws IllegalArgumentException 如果要查找的键为 null
     * <p>原文：if the key to look up is null
     * @throws IllegalArgumentException 如果要将找到的对象转换成的类型为 null
     * <p>原文：if the type to cast the found object to is
     * null
     * @throws IllegalArgumentException 如果给定键下存在值，但无法以给定类型访问
     * <p>原文：if a value exists under the given key,
     * but cannot be accessed using the given type
     * @throws IllegalArgumentException 如果未为 {@link
     * PersistentDataType#getPrimitiveType()} 找到合适的适配器
     * <p>原文：if no suitable adapter was found for
     * the {@link
     * PersistentDataType#getPrimitiveType()}
     */
    <P, C> @Nullable C get(NamespacedKey key, PersistentDataType<P, C> type);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回存储在 {@link PersistentDataHolder} 实例上的元数据值。如果容器中不存在该值，则返回提供的默认值。
     * <p>原文：
     * Returns the metadata value that is stored on the
     * {@link PersistentDataHolder} instance. If the value does not exist in the
     * container, the default value provided is returned.
     *
     * @param key 要在自定义标签映射中查找的键
     * <p>原文：the key to look up in the custom tag map
     * @param type 值必须具有并将被转换成的类型
     * <p>原文：the type the value must have and will be cast to
     * @param defaultValue 如果所提供键下没有找到值时返回的默认值
     * <p>原文：the default value to return if no value was found for
     * the provided key
     * @param <P> 所存储基本类型的泛型类型
     * <p>原文：the generic type of the stored primitive
     * @param <C> 最终创建的复杂对象的泛型类型
     * <p>原文：the generic type of the eventually created complex object
     * @return 值；如果给定键下没有映射值则为默认值
     * <p>原文：the value or the default value if no value was mapped under the
     * given key
     * @throws IllegalArgumentException 如果要查找的键为 null
     * <p>原文：if the key to look up is null
     * @throws IllegalArgumentException 如果要将找到的对象转换成的类型为 null
     * <p>原文：if the type to cast the found object to is
     * null
     * @throws IllegalArgumentException 如果给定键下存在值，但无法以给定类型访问
     * <p>原文：if a value exists under the given key,
     * but cannot be accessed using the given type
     * @throws IllegalArgumentException 如果未为 {@link PersistentDataType#getPrimitiveType()} 找到合适的适配器
     * <p>原文：if no suitable adapter was found for
     * the {@link PersistentDataType#getPrimitiveType()}
     */
    <P, C> C getOrDefault(NamespacedKey key, PersistentDataType<P, C> type, C defaultValue);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此 {@link PersistentDataContainer} 实例上存在的键集合。
     * <p>
     * 对返回集合所做的任何更改都不会反映到该实例上。
     * <p>原文：
     * Get the set of keys present on this {@link PersistentDataContainer}
     * instance.
     * <p>
     * Any changes made to the returned set will not be reflected on the
     * instance.
     *
     * @return 键集合
     * <p>原文：the key set
     */
    Set<NamespacedKey> getKeys();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回容器实例是否为空，即其中没有任何条目。
     * <p>原文：
     * Returns if the container instance is empty, therefore has no entries
     * inside it.
     *
     * @return 布尔值
     * <p>原文：the boolean
     */
    boolean isEmpty();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将此 {@link PersistentDataContainer} 中的所有值复制到提供的容器中。
     * <p>
     * 该方法只复制自定义对象键。显示名称等现有标签不会被复制，因为这些值是使用你的命名空间存储的。
     * <p>原文：
     * Copies all values from this {@link PersistentDataContainer} to the provided
     * container.
     * <p>
     * This method only copies custom object keys. Existing tags, like the display
     * name, will not be copied as the values are stored using your namespace.
     *
     * @param other 要复制到的容器
     * <p>原文：the container to copy to
     * @param replace 是否替换目标容器中任何匹配的值
     * <p>原文：whether to replace any matching values in the target container
     * @throws IllegalArgumentException 如果另一个容器为 null
     * <p>原文：if the other container is null
     */
    void copyTo(PersistentDataContainer other, boolean replace);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此标签容器使用的适配器上下文。
     * <p>原文：
     * Returns the adapter context this tag container uses.
     *
     * @return 标签上下文
     * <p>原文：the tag context
     */
    PersistentDataAdapterContext getAdapterContext();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将此 {@link PersistentDataContainer} 实例序列化为字节数组。
     * <p>原文：
     * Serialize this {@link PersistentDataContainer} instance to a
     * byte array.
     *
     * @return 此容器的二进制表示
     * <p>原文：a binary representation of this container
     * @throws java.io.IOException 如果无法将此容器写入字节数组
     * <p>原文：if we fail to write this container to a byte array
     */
    byte[] serializeToBytes() throws java.io.IOException;

    /**
     * {@return the size of the data container}
     */
    int getSize();
}
