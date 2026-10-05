package io.papermc.paper.datacomponent;

import org.bukkit.Utility;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示数据组件持有者的视图。此接口上的任何方法都不会修改持有者。
 * <p>原文：
 * This represents a view of a data component holder. No
 * methods on this interface mutate the holder.
 *
 * @see DataComponentHolder
 */
@NullMarked
@ApiStatus.NonExtendable
public interface DataComponentView {
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此物品堆上该数据组件类型的值。
     * <p>原文：
     * Gets the value for the data component type on this stack.
     *
     * @param type 数据组件类型
     * <p>原文：the data component type
     * @param <T> 值类型
     * <p>原文：the value type
     * @return 该数据组件类型的值；如果未设置或被标记为已移除则为 {@code null}
     * <p>原文：the value for the data component type, or {@code null} if not set or marked as removed
     * @see #hasData(DataComponentType) for DataComponentType.NonValued
     */
    @Contract(pure = true)
    @ApiStatus.Experimental
    <T> @Nullable T getData(final DataComponentType.Valued<T> type);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此持有者上该数据组件类型的值，并提供后备值。
     * <p>原文：
     * Gets the value for the data component type on this holder with
     * a fallback value.
     *
     * @param type 数据组件类型
     * <p>原文：the data component type
     * @param fallback 值不存在时的回退值
     * <p>原文：the fallback value if the value isn't present
     * @param <T> 值类型
     * <p>原文：the value type
     * @return 该数据组件类型的值或回退值
     * <p>原文：the value for the data component type or the fallback value
     */
    @Utility
    @Contract(value = "_, !null -> !null", pure = true)
    @ApiStatus.Experimental
    <T> @Nullable T getDataOrDefault(final DataComponentType.Valued<? extends T> type, final @Nullable T fallback);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查此持有者上是否设置了该数据组件类型。
     * <p>原文：
     * Checks if the data component type is set on this holder.
     *
     * @param type 数据组件类型
     * <p>原文：the data component type
     * @return 如果已设置则为 {@code true}，否则为 {@code false}
     * <p>原文：{@code true} if set, {@code false} otherwise
     */
    @Contract(pure = true)
    @ApiStatus.Experimental
    boolean hasData(final DataComponentType type);

    // Not applicable to entities
    // /**
    //  * Gets all the data component types set on this holder.
    //  *
    //  * @return an immutable set of data component types
    //  */
    // @Contract("-> new")
    // @ApiStatus.Experimental
    // @Unmodifiable Set<DataComponentType> getDataTypes();
}
