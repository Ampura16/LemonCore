package org.bukkit.persistence;

import org.bukkit.NamespacedKey;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 此接口表示一个类似映射的对象，能够在其中存储自定义标签。
 * <p>原文：
 * This interface represents a map like object, capable of storing custom tags
 * in it.
 */
public interface PersistentDataContainer extends io.papermc.paper.persistence.PersistentDataContainerView { // Paper - split up view and mutable

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在 {@link PersistentDataHolder} 实例上存储元数据值。
     * <p>
     * 此 API 不能用于操纵 Minecraft 数据，因为值将使用您的命名空间存储。此方法将覆盖 {@link PersistentDataHolder} 在提供的键下可能已存储的任何现有值。
     * <p>原文：
     * Stores a metadata value on the {@link PersistentDataHolder} instance.
     * <p>
     * This API cannot be used to manipulate minecraft data, as the values will
     * be stored using your namespace. This method will override any existing
     * value the {@link PersistentDataHolder} may have stored under the provided
     * key.
     *
     * @param key 此值将存储在的键
     * <p>原文：the key this value will be stored under
     * @param type 此标签使用的类型
     * <p>原文：the type this tag uses
     * @param value 要存储在标签中的值
     * <p>原文：the value to store in the tag
     * @param <P> 标签值的通用 Java 类型
     * <p>原文：the generic java type of the tag value
     * @param <C> 要存储的对象的通用类型
     * <p>原文：the generic type of the object to store
     * @throws IllegalArgumentException 如果键为 null
     * <p>原文：if the key is null
     * @throws IllegalArgumentException 如果键为 null
     * <p>原文：if the type is null
     * @throws IllegalArgumentException 如果键为 null
     * <p>原文：if the value is null. Removing a tag should
     * be done using {@link #remove(NamespacedKey)}
     * @throws IllegalArgumentException 如果键为 null
     * <p>原文：if no suitable adapter was found for
     * the {@link PersistentDataType#getPrimitiveType()}
     */
    <P, C> void set(@NotNull NamespacedKey key, @NotNull PersistentDataType<P, C> type, @NotNull C value);
    // Paper - move to PersistentDataContainerView

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从 {@link PersistentDataHolder} 实例中移除自定义键。
     * <p>原文：
     * Removes a custom key from the {@link PersistentDataHolder} instance.
     *
     * @param key 要移除的键
     * <p>原文：the key to remove
     * @throws IllegalArgumentException 如果提供的键为 null
     * <p>原文：if the provided key is null
     */
    void remove(@NotNull NamespacedKey key);
    // Paper - move to PersistentDataContainerView

    // Paper start - byte array serialization
    // Paper - move to PersistentDataContainerView
    /**
     * Read values from a serialized byte array into this
     * {@link PersistentDataContainer} instance.
     *
     * @param bytes the byte array to read from
     * @param clear if true, this {@link PersistentDataContainer} instance
     *              will be cleared before reading
     * @throws java.io.IOException if the byte array has an invalid format
     */
    void readFromBytes(byte @NotNull [] bytes, boolean clear) throws java.io.IOException;

    /**
     * Read values from a serialized byte array into this
     * {@link PersistentDataContainer} instance.
     * This method has the same effect as
     * <code>PersistentDataContainer#readFromBytes(bytes, true)</code>
     *
     * @param bytes the byte array to read from
     * @throws java.io.IOException if the byte array has an invalid format
     */
    default void readFromBytes(final byte @NotNull [] bytes) throws java.io.IOException {
        this.readFromBytes(bytes, true);
    }
    // Paper end - byte array serialization
}
