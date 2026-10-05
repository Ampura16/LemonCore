package org.bukkit.metadata;

import java.util.List;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 此接口由所有可以提供关于自身元数据的对象实现.
 * <p>原文：
 * This interface is implemented by all objects that can provide metadata
 * about themselves.
 *
 * @deprecated This system is extremely misleading and does not cleanup values for metadatable entities that have been
 * removed. It is recommended that when wanting persistent metadata, you use {@link org.bukkit.persistence.PersistentDataContainer}.
 * <p>
 * If you want temporary values on an entity, use the entity lifecycle events and a {@link java.util.Map} of your own. (See {@link com.destroystokyo.paper.event.entity.EntityAddToWorldEvent} and {@link com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent})
 */
@Deprecated
public interface Metadatable {
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 在实现对象的元数据存储中设置元数据值.
     * <p>原文：
     * Sets a metadata value in the implementing object's metadata store.
     *
     * @param metadataKey 用于标识此元数据的唯一键.
     * <p>原文：A unique key to identify this metadata.
     * @param newMetadataValue 要应用的元数据值.
     * <p>原文：The metadata value to apply.
     * @throws IllegalArgumentException 如果值为null, 或拥有插件为null
     * <p>原文：If value is null, or the owning plugin
     *     is null
     */
    public void setMetadata(@NotNull String metadataKey, @NotNull MetadataValue newMetadataValue);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从实现对象的元数据存储中返回先前设置的元数据值列表.
     * <p>原文：
     * Returns a list of previously set metadata values from the implementing
     * object's metadata store.
     *
     * @param metadataKey 正在查找的唯一元数据键.
     * <p>原文：the unique metadata key being sought.
     * @return 值列表, 每个设置了请求值的插件一个.
     * <p>原文：A list of values, one for each plugin that has set the
     *     requested value.
     */
    @NotNull
    public List<MetadataValue> getMetadata(@NotNull String metadataKey);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试实现对象的元数据存储中是否包含给定的元数据值.
     * <p>原文：
     * Tests to see whether the implementing object contains the given
     * metadata value in its metadata store.
     *
     * @param metadataKey 正在查询的唯一元数据键.
     * <p>原文：the unique metadata key being queried.
     * @return metadataKey在主体中的存在性.
     * <p>原文：the existence of the metadataKey within subject.
     */
    public boolean hasMetadata(@NotNull String metadataKey);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从实现对象的元数据存储中移除给定的元数据值.
     * <p>原文：
     * Removes the given metadata value from the implementing object's
     * metadata store.
     *
     * @param metadataKey 标识要移除元数据的唯一元数据键.
     * <p>原文：the unique metadata key identifying the metadata to
     *     remove.
     * @param owningPlugin 此插件的元数据值将被移除. 所有其他值将保持不变.
     * <p>原文：This plugin's metadata value will be removed. All
     *     other values will be left untouched.
     * @throws IllegalArgumentException 如果插件为null
     * <p>原文：If plugin is null
     */
    public void removeMetadata(@NotNull String metadataKey, @NotNull Plugin owningPlugin);
}
