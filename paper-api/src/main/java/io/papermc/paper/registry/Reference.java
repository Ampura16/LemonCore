package io.papermc.paper.registry;

import org.bukkit.Keyed;
import org.bukkit.NamespacedKey;
import org.bukkit.Registry;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示对可能发生变化的、由服务器支持的注册表值的引用。
 * <p>原文：
 * Represents a reference to a server-backed registry value that may
 * change.
 *
 * @param <T> 值的类型
 * <p>原文：type of the value
 */
@Deprecated(forRemoval = true, since = "1.20.6")
public interface Reference<T extends Keyed> extends Keyed {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 通过键从注册表获取值。
     * <p>原文：
     * Gets the value from the registry with the key.
     *
     * @return 值
     * <p>原文：the value
     * @throws java.util.NoSuchElementException 如果不存在具有此键的值
     * <p>原文：if there is no value with this key
     */
    @Deprecated(forRemoval = true, since = "1.20.6")
    @NotNull T value();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 通过键从注册表获取值。
     * <p>原文：
     * Gets the value from the registry with the key.
     *
     * @return 值；如果不存在则为 null
     * <p>原文：the value or null if it doesn't exist
     */
    @Deprecated(forRemoval = true, since = "1.20.6")
    @Nullable T valueOrNull();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 创建对已注册值的引用。
     * <p>原文：
     * Creates a reference to a registered value.
     *
     * @param registry 该值所在的注册表
     * <p>原文：the registry the value is located in
     * @param key 值的键
     * <p>原文：the key to the value
     * @param <T> 值的类型
     * <p>原文：the type of the value
     * @return 引用
     * <p>原文：a reference
     */
    @Deprecated(forRemoval = true, since = "1.20.6")
    static <T extends Keyed> @NotNull Reference<T> create(@NotNull Registry<T> registry, @NotNull NamespacedKey key) {
        return new ReferenceImpl<>(registry, key);
    }
}
