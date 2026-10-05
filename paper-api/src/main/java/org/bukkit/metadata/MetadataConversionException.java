package org.bukkit.metadata;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 当{@link LazyMetadataValue}尝试将元数据值转换为不适当的数据类型时, 会抛出此异常.
 * <p>原文：
 * A MetadataConversionException is thrown any time a {@link
 * LazyMetadataValue} attempts to convert a metadata value to an inappropriate
 * data type.
 *
 * @deprecated This system is extremely misleading and does not cleanup values for metadatable entities that have been
 * removed. It is recommended that when wanting persistent metadata, you use {@link org.bukkit.persistence.PersistentDataContainer}.
 * <p>
 * If you want temporary values on an entity, use the entity lifecycle events and a {@link java.util.Map} of your own. (See {@link com.destroystokyo.paper.event.entity.EntityAddToWorldEvent} and {@link com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent})
 */
@Deprecated
@SuppressWarnings("serial")
public class MetadataConversionException extends RuntimeException {
    MetadataConversionException(String message) {
        super(message);
    }
}
