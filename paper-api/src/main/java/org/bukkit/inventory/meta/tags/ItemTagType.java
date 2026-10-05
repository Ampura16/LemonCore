package org.bukkit.inventory.meta.tags;

import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 此类表示一个具有泛型内容类型的枚举。它定义了自定义物品标签可以拥有的类型.
 * <p>
 * 此接口可用于创建具有不同复杂类型的自定义 {@link ItemTagType}。这对于 UUIDItemTagType 等场景很有用：
 * <pre>
 * {@code
 * public class UUIDItemTagType implements ItemTagType<byte[], UUID> {
 *
 *         {@literal @Override}
 *         public Class<byte[]> getPrimitiveType() {
 *             return byte[].class;
 *         }
 *
 *         {@literal @Override}
 *         public Class<UUID> getComplexType() {
 *             return UUID.class;
 *         }
 *
 *         {@literal @Override}
 *         public byte[] toPrimitive(UUID complex, ItemTagAdapterContext context) {
 *             ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
 *             bb.putLong(complex.getMostSignificantBits());
 *             bb.putLong(complex.getLeastSignificantBits());
 *             return bb.array();
 *         }
 *
 *         {@literal @Override}
 *         public UUID fromPrimitive(byte[] primitive, ItemTagAdapterContext context) {
 *             ByteBuffer bb = ByteBuffer.wrap(primitive);
 *             long firstLong = bb.getLong();
 *             long secondLong = bb.getLong();
 *             return new UUID(firstLong, secondLong);
 *         }
 *     }}</pre>
 * <p>原文：
 * This class represents an enum with a generic content type. It defines the
 * types a custom item tag can have.
 * <p>
 * This interface can be used to create your own custom {@link ItemTagType} with
 * different complex types. This may be useful for the likes of a
 * UUIDItemTagType:
 * <pre>
 * {@code
 * public class UUIDItemTagType implements ItemTagType<byte[], UUID> {
 *
 *         {@literal @Override}
 *         public Class<byte[]> getPrimitiveType() {
 *             return byte[].class;
 *         }
 *
 *         {@literal @Override}
 *         public Class<UUID> getComplexType() {
 *             return UUID.class;
 *         }
 *
 *         {@literal @Override}
 *         public byte[] toPrimitive(UUID complex, ItemTagAdapterContext context) {
 *             ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
 *             bb.putLong(complex.getMostSignificantBits());
 *             bb.putLong(complex.getLeastSignificantBits());
 *             return bb.array();
 *         }
 *
 *         {@literal @Override}
 *         public UUID fromPrimitive(byte[] primitive, ItemTagAdapterContext context) {
 *             ByteBuffer bb = ByteBuffer.wrap(primitive);
 *             long firstLong = bb.getLong();
 *             long secondLong = bb.getLong();
 *             return new UUID(firstLong, secondLong);
 *         }
 *     }}</pre>
 *
 * @param <T> 存储在给定标签中的主要对象类型
 * <p>原文：the primary object type that is stored in the given tag
 * @param <Z> 应用此物品标签类型时检索到的对象类型
 * <p>原文：the retrieved object type when applying this item tag type
 * @deprecated 请使用 {@link PersistentDataType}，因为此 API 部分正在被取代
 * <p>原文：please use {@link PersistentDataType} as this part of the api is being replaced
 */
@Deprecated(since = "1.14", forRemoval = true)
public interface ItemTagType<T, Z> {

    /*
        The primitive one value types.
     */
    ItemTagType<Byte, Byte> BYTE = new PrimitiveTagType<>(Byte.class);
    ItemTagType<Short, Short> SHORT = new PrimitiveTagType<>(Short.class);
    ItemTagType<Integer, Integer> INTEGER = new PrimitiveTagType<>(Integer.class);
    ItemTagType<Long, Long> LONG = new PrimitiveTagType<>(Long.class);
    ItemTagType<Float, Float> FLOAT = new PrimitiveTagType<>(Float.class);
    ItemTagType<Double, Double> DOUBLE = new PrimitiveTagType<>(Double.class);

    /*
        String.
     */
    ItemTagType<String, String> STRING = new PrimitiveTagType<>(String.class);

    /*
        Primitive Arrays.
     */
    ItemTagType<byte[], byte[]> BYTE_ARRAY = new PrimitiveTagType<>(byte[].class);
    ItemTagType<int[], int[]> INTEGER_ARRAY = new PrimitiveTagType<>(int[].class);
    ItemTagType<long[], long[]> LONG_ARRAY = new PrimitiveTagType<>(long[].class);

    /*
        Nested TagContainer.
     */
    ItemTagType<CustomItemTagContainer, CustomItemTagContainer> TAG_CONTAINER = new PrimitiveTagType<>(CustomItemTagContainer.class);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回此标签的原始数据类型.
     * <p>原文：
     * Returns the primitive data type of this tag.
     *
     * @return 类
     * <p>原文：the class
     */
    @NotNull
    Class<T> getPrimitiveType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回原始值所对应的复杂对象类型.
     * <p>原文：
     * Returns the complex object type the primitive value resembles.
     *
     * @return 类类型
     * <p>原文：the class type
     */
    @NotNull
    Class<Z> getComplexType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回与传递给此方法的复杂对象相对应的原始数据.
     * <p>原文：
     * Returns the primitive data that resembles the complex object passed to
     * this method.
     *
     * @param complex 复杂对象实例
     * <p>原文：the complex object instance
     * @param context 此操作运行的上下文
     * <p>原文：the context this operation is running in
     * @return 原始值
     * <p>原文：the primitive value
     */
    @NotNull
    T toPrimitive(@NotNull Z complex, @NotNull ItemTagAdapterContext context);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 基于传递的原始值创建复杂对象.
     * <p>原文：
     * Creates a complex object based of the passed primitive value
     *
     * @param primitive 原始值
     * <p>原文：the primitive value
     * @param context 此操作运行的上下文
     * <p>原文：the context this operation is running in
     * @return 复杂对象实例
     * <p>原文：the complex object instance
     */
    @NotNull
    Z fromPrimitive(@NotNull T primitive, @NotNull ItemTagAdapterContext context);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 默认实现，仅用于将检索或插入的值传递到下一层.
     * <p>
     * 此实现不添加任何逻辑，用于为原始类型提供默认实现.
     * <p>原文：
     * A default implementation that simply exists to pass on the retrieved or
     * inserted value to the next layer.
     *
     * This implementation does not add any kind of logic, but is used to
     * provide default implementations for the primitive types.
     *
     * @param <T> 原始对象的泛型类型
     * <p>原文：the generic type of the primitive objects
     */
    class PrimitiveTagType<T> implements ItemTagType<T, T> {

        private final Class<T> primitiveType;

        PrimitiveTagType(@NotNull Class<T> primitiveType) {
            this.primitiveType = primitiveType;
        }

        @NotNull
        @Override
        public Class<T> getPrimitiveType() {
            return primitiveType;
        }

        @NotNull
        @Override
        public Class<T> getComplexType() {
            return primitiveType;
        }

        @NotNull
        @Override
        public T toPrimitive(@NotNull T complex, @NotNull ItemTagAdapterContext context) {
            return complex;
        }

        @NotNull
        @Override
        public T fromPrimitive(@NotNull T primitive, @NotNull ItemTagAdapterContext context) {
            return primitive;
        }
    }
}
