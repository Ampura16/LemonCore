package org.bukkit.metadata;

import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * FixedMetadataValue是一种特殊情况的元数据项, 在初始化后永远包含相同的值.
 * 使FixedMetadataValue无效没有任何效果.
 * <p>
 * 此类出于历史原因继承LazyMetadataValue, 即使它覆盖了所有实现方法.
 * 未来继承层次结构可能会改变.
 * <p>原文：
 * A FixedMetadataValue is a special case metadata item that contains the same
 * value forever after initialization. Invalidating a FixedMetadataValue has
 * no effect.
 * <p>
 * This class extends LazyMetadataValue for historical reasons, even though it
 * overrides all the implementation methods. it is possible that in the future
 * that the inheritance hierarchy may change.
 *
 * @deprecated This system is extremely misleading and does not cleanup values for metadatable entities that have been
 * removed. It is recommended that when wanting persistent metadata, you use {@link org.bukkit.persistence.PersistentDataContainer}.
 * <p>
 * If you want temporary values on an entity, use the entity lifecycle events and a {@link java.util.Map} of your own. (See {@link com.destroystokyo.paper.event.entity.EntityAddToWorldEvent} and {@link com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent})
 */
@Deprecated
public class FixedMetadataValue extends LazyMetadataValue {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 存储由此固定值表示的内部值.
     * <p>原文：
     * Store the internal value that is represented by this fixed value.
     */
    private final Object internalValue;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使用Object初始化FixedMetadataValue.
     * <p>原文：
     * Initializes a FixedMetadataValue with an Object
     *
     * @param owningPlugin 创建此元数据值的{@link Plugin}
     * <p>原文：the {@link Plugin} that created this metadata value
     * @param value 分配给此元数据值的值
     * <p>原文：the value assigned to this metadata value
     */
    public FixedMetadataValue(@NotNull Plugin owningPlugin, @Nullable final Object value) {
        super(owningPlugin);
        this.internalValue = value;
    }

    @Override
    public void invalidate() {

    }

    @Nullable
    @Override
    public Object value() {
        return internalValue;
    }
}
