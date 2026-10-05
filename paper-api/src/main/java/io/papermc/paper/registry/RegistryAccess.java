package io.papermc.paper.registry;

import org.bukkit.Keyed;
import org.bukkit.Registry;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 用于通过 {@link RegistryKey} 访问不同的 {@link Registry} 实例。使用 {@link RegistryAccess#registryAccess()} 获取 {@link RegistryAccess} 的主实例。
 * <p>原文：
 * Used for accessing different {@link Registry} instances
 * by a {@link RegistryKey}. Get the main instance of {@link RegistryAccess}
 * with {@link RegistryAccess#registryAccess()}.
 */
@NullMarked
@ApiStatus.NonExtendable
public interface RegistryAccess {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取服务器的 {@link RegistryAccess} 实例。
     * <p>原文：
     * Get the {@link RegistryAccess} instance for the server.
     *
     * @return RegistryAccess 实例
     * <p>原文：the RegistryAccess instance
     */
    static RegistryAccess registryAccess() {
        return RegistryAccessHolder.INSTANCE.orElseThrow(() -> new IllegalStateException("No RegistryAccess implementation found"));
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 根据类型获取注册表。
     * <p>原文：
     * Gets the registry based on the type.
     *
     * @param type 类型
     * <p>原文：the type
     * @return 注册表；如果未找到则为 null
     * <p>原文：the registry or null if none found
     * @param <T> 类型
     * <p>原文：the type
     * @deprecated 请结合 {@link RegistryKey} 中的键使用 {@link #getRegistry(RegistryKey)}
     * <p>原文：use {@link #getRegistry(RegistryKey)} with keys from {@link RegistryKey}
     */
    @Deprecated(since = "1.20.6", forRemoval = true)
    <T extends Keyed> @Nullable Registry<T> getRegistry(Class<T> type);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取具有指定键的注册表。
     * <p>原文：
     * Gets the registry with the specified key.
     *
     * @param registryKey 键
     * <p>原文：the key
     * @return 注册表
     * <p>原文：the registry
     * @param <T> 类型
     * <p>原文：the type
     * @throws java.util.NoSuchElementException 如果未找到具有该键的注册表
     * <p>原文：if no registry with the key is found
     * @throws IllegalArgumentException 如果注册表尚不可用
     * <p>原文：if the registry is not available yet
     */
    // Future note: We should have no trouble removing this generic qualifier when
    // registry types no longer have to be "keyed" as it shouldn't break ABI or API.
    <T extends Keyed> Registry<T> getRegistry(RegistryKey<T> registryKey);
}
