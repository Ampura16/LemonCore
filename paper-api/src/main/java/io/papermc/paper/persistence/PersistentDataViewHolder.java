package io.papermc.paper.persistence;

import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link PersistentDataViewHolder} 接口定义了一个可以查看其上自定义持久化数据的对象。
 * <p>原文：
 * The {@link PersistentDataViewHolder} interface defines an object that can view
 * custom persistent data on it.
 */
@NullMarked
@ApiStatus.NonExtendable
public interface PersistentDataViewHolder {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回可以查看对象上标签的自定义标签容器视图。
     * <p>
     * 注意，此容器中存储的标签都存储在各自的自定义命名空间下，因此无法使用此 {@link PersistentDataViewHolder} 修改默认标签。
     * <p>原文：
     * Returns a custom tag container view capable of viewing tags on the object.
     * <p>
     * Note that the tags stored on this container are all stored under their
     * own custom namespace therefore modifying default tags using this
     * {@link PersistentDataViewHolder} is impossible.
     *
     * @return 持久化数据容器视图
     * <p>原文：the persistent data container view
     */
    PersistentDataContainerView getPersistentDataContainer();
}
