package org.bukkit.persistence;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 此接口代表{@link PersistentDataType}可以序列化和反序列化传入值的上下文.
 * <p>原文：
 * This interface represents the context in which the {@link PersistentDataType} can
 * serialize and deserialize the passed values.
 */
public interface PersistentDataAdapterContext {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 创建一个新的空元数据容器实例.
     * <p>原文：
     * Creates a new and empty meta container instance.
     *
     * @return 新的容器实例
     * <p>原文：the fresh container instance
     */
    @NotNull
    PersistentDataContainer newPersistentDataContainer();
}
