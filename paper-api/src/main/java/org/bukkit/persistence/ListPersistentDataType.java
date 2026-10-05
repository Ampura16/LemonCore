package org.bukkit.persistence;

import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 列表持久数据表示一种数据类型，能够在 {@link PersistentDataContainer} 中存储其他数据类型的列表。
 * <p>原文：
 * The list persistent data represents a data type that is capable of storing a
 * list of other data types in a {@link PersistentDataContainer}.
 *
 * @param <P> 列表元素的原始类型。
 * <p>原文：the primitive type of the list element.
 * @param <C> 列表元素的复杂类型。
 * <p>原文：the complex type of the list elements.
 */
public interface ListPersistentDataType<P, C> extends PersistentDataType<List<P>, List<C>> {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 提供列表中元素的持久数据类型。
     * <p>原文：
     * Provides the persistent data type of the elements found in the list.
     *
     * @return 持久数据类型。
     * <p>原文：the persistent data type.
     */
    @NotNull
    PersistentDataType<P, C> elementType();
}
