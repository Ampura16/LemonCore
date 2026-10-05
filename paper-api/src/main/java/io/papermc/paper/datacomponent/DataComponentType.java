package io.papermc.paper.datacomponent;

import org.bukkit.Keyed;
import org.jetbrains.annotations.ApiStatus;
import org.jspecify.annotations.NullMarked;

@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface DataComponentType extends Keyed {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查此数据组件类型是否持久化，即是否会随其所附加的物品堆一同保存。
     * <p>原文：
     * Checks if this data component type is persistent, or
     * that it will be saved with any itemstack it's attached to.
     *
     * @return 如果持久存在则为 {@code true}，否则为 {@code false}
     * <p>原文：{@code true} if persistent, {@code false} otherwise
     */
    boolean isPersistent();

    @SuppressWarnings("unused")
    @ApiStatus.NonExtendable
    interface Valued<T> extends DataComponentType {

    }

    @ApiStatus.NonExtendable
    interface NonValued extends DataComponentType {

    }
}
