package org.bukkit.persistence;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * {@link PersistentDataHolder} 接口定义了一个可以在其上存储自定义持久元数据的对象。
 * <p>原文：
 * The {@link PersistentDataHolder} interface defines an object that can store
 * custom persistent meta data on it.
 * <p>Prefer using {@link io.papermc.paper.persistence.PersistentDataViewHolder} for read-only operations
 * as it covers more types.</p>
 */
public interface PersistentDataHolder extends io.papermc.paper.persistence.PersistentDataViewHolder { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回一个可以在对象上存储标签的自定义标签容器。
     * <p>原文：
     * Returns a custom tag container capable of storing tags on the object.
     * <p>
     * Note that the tags stored on this container are all stored under their
     * own custom namespace therefore modifying default tags using this
     * {@link PersistentDataHolder} is impossible.
     *
     * @return 持久元数据容器
     * <p>原文：the persistent metadata container
     */
    @NotNull
    PersistentDataContainer getPersistentDataContainer();

}
