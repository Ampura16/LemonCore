package io.papermc.paper.datacomponent;

import org.bukkit.Utility;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示能够持有并修改数据组件的对象。
 * <p>原文：
 * This represents an object capable of holding and mutating data components.
 *
 * @see PersistentDataContainer
 */
@NullMarked
@ApiStatus.NonExtendable
public interface DataComponentHolder extends DataComponentView {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为此持有者设置该数据组件类型的值。
     * <p>原文：
     * Sets the value of the data component type for this holder.
     *
     * @param type 数据组件类型
     * <p>原文：the data component type
     * @param valueBuilder 值构建器
     * <p>原文：value builder
     * @param <T> 值类型
     * <p>原文：value type
     */
    @Utility
    @ApiStatus.Experimental
    <T> void setData(final DataComponentType.Valued<T> type, final DataComponentBuilder<T> valueBuilder);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为此持有者设置该数据组件类型的值。
     * <p>原文：
     * Sets the value of the data component type for this holder.
     *
     * @param type 数据组件类型
     * <p>原文：the data component type
     * @param value 要设置的值
     * <p>原文：value to set
     * @param <T> 值类型
     * <p>原文：value type
     */
    @ApiStatus.Experimental
    <T> void setData(final DataComponentType.Valued<T> type, final T value);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 将此无值数据组件类型标记为存在于此物品堆中。
     * <p>原文：
     * Marks this non-valued data component type as present in this itemstack.
     *
     * @param type 数据组件类型
     * <p>原文：the data component type
     */
    @ApiStatus.Experimental
    void setData(final DataComponentType.NonValued type);

   // TODO: Do we even want to have the concept of overriding here? Not sure what is going on with entity components
}
