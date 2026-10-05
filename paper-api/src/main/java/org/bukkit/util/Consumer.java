package org.bukkit.util;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示接受一个输入参数且没有返回值的操作.
 * <p>原文：
 * Represents an operation that accepts a single input argument and returns no
 * result.
 *
 * @param <T> 输入参数类型
 * <p>原文：the type of the input to the operation
 * @deprecated 请使用 {@link java.util.function.Consumer}
 * <p>原文：Use {@link java.util.function.Consumer} instead
 */
// Bukkit developer note (NOT plugin developers):
// NEVER use this consumer in the API.
// API methods which use this consumer will be remapped to Java's consumer at runtime, resulting in an error.
@Deprecated(since = "1.20.2", forRemoval = true)
public interface Consumer<T> extends java.util.function.Consumer<T> {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用给定的参数执行操作.
     * <p>原文：
     * Performs this operation on the given argument.
     *
     * @param t 输入参数
     * <p>原文：the input argument
     */
    @Override
    void accept(T t);
}
