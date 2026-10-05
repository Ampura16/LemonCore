package org.bukkit.inventory.meta.tags;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataHolder;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 此接口表示 {@link ItemTagType} 可以序列化和反序列化传入值的上下文.
 * <p>原文：
 * This interface represents the context in which the {@link ItemTagType} can
 * serialize and deserialize the passed values.
 *
 * @deprecated 此 API 部分已被 {@link PersistentDataHolder} 取代。请使用 {@link PersistentDataAdapterContext} 代替.
 * <p>原文：this API part has been replaced by {@link PersistentDataHolder}.
 * Please use {@link PersistentDataAdapterContext} instead of this.
 */
@Deprecated(since = "1.14", forRemoval = true)
public interface ItemTagAdapterContext {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个新的空标签容器实例.
     * <p>原文：
     * Creates a new and empty tag container instance.
     *
     * @return 新的容器实例
     * <p>原文：the fresh container instance
     */
    @NotNull
    CustomItemTagContainer newTagContainer();
}
