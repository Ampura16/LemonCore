package io.papermc.paper.registry;

import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 为给定 {@link TypedKey} 创建 {@link RegistryBuilder} 的工厂。对于该类的每个实例，一旦调用过 {@link #empty()} 或 {@link #copyFrom(TypedKey)} 中的任一方法，之后再调用任一方法都会抛出 {@link IllegalStateException}。
 * <p>原文：
 * A factory to create a {@link RegistryBuilder} for a given {@link TypedKey}. For
 * each instance of this class, once either {@link #empty()} or {@link #copyFrom(TypedKey)}
 * is called once, any future calls to either method will throw an {@link IllegalStateException}.
 *
 * @param <T> 注册表的类型
 * <p>原文：The type of the registry
 * @param <B> 注册表构建器的类型
 * <p>原文：The type of the registry builder
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface RegistryBuilderFactory<T, B extends RegistryBuilder<T>> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建新的空 {@link RegistryBuilder}。
     * <p>原文：
     * Creates a new empty {@link RegistryBuilder}.
     *
     * @return 新的空 {@link RegistryBuilder}
     * <p>原文：A new empty {@link RegistryBuilder}
     * @throws IllegalStateException 如果此方法或 {@link #copyFrom(TypedKey)}) 已被调用过一次
     * <p>原文：if this method or {@link #copyFrom(TypedKey)}) has already been called once
     */
    @Contract("-> new")
    B empty();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建与给定 {@link TypedKey} 属性相同的新 {@link RegistryBuilder}。
     * <p>原文：
     * Creates a new {@link RegistryBuilder} with the same properties as the given {@link TypedKey}.
     *
     * @param key 要从中复制属性的键
     * <p>原文：The key to copy properties from
     * @return 与给定键属性相同的新 {@link RegistryBuilder}
     * <p>原文：A new {@link RegistryBuilder} with the same properties as the given key
     * @throws IllegalStateException 如果此方法或 {@link #empty()} 已被调用过一次
     * <p>原文：if this method or {@link #empty()} has already been called once
     * @throws IllegalArgumentException 如果键不存在
     * <p>原文：if key doesn't exist
     */
    @Contract("_ -> new")
    B copyFrom(TypedKey<T> key);
}
