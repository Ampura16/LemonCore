package org.bukkit.metadata;

import java.util.List;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * @deprecated This system is extremely misleading and does not cleanup values for metadatable entities that have been
 * removed. It is recommended that when wanting persistent metadata, you use {@link org.bukkit.persistence.PersistentDataContainer}.
 * <p>
 * If you want temporary values on an entity, use the entity lifecycle events and a {@link java.util.Map} of your own. (See {@link com.destroystokyo.paper.event.entity.EntityAddToWorldEvent} and {@link com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent})
 */
@Deprecated
public interface MetadataStore<T> {
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向对象添加元数据值.
     * <p>原文：
     * Adds a metadata value to an object.
     *
     * @param subject 接收元数据的对象.
     * <p>原文：The object receiving the metadata.
     * @param metadataKey 用于标识此元数据的唯一键.
     * <p>原文：A unique key to identify this metadata.
     * @param newMetadataValue 要应用的元数据值.
     * <p>原文：The metadata value to apply.
     * @throws IllegalArgumentException 如果值为null, 或拥有插件为null
     * <p>原文：If value is null, or the owning plugin
     *     is null
     */
    public void setMetadata(@NotNull T subject, @NotNull String metadataKey, @NotNull MetadataValue newMetadataValue);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回附加到对象的所有元数据值. 如果多个插件附加了元数据, 每个值都将被包含.
     * <p>原文：
     * Returns all metadata values attached to an object. If multiple plugins
     * have attached metadata, each will value will be included.
     *
     * @param subject 正在查询的对象.
     * <p>原文：the object being interrogated.
     * @param metadataKey 正在查找的唯一元数据键.
     * <p>原文：the unique metadata key being sought.
     * @return 值列表, 每个设置了请求值的插件一个.
     * <p>原文：A list of values, one for each plugin that has set the
     *     requested value.
     */
    @NotNull
    public List<MetadataValue> getMetadata(@NotNull T subject, @NotNull String metadataKey);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 测试对象上是否设置了元数据属性.
     * <p>原文：
     * Tests to see if a metadata attribute has been set on an object.
     *
     * @param subject 执行has-metadata测试的对象.
     * <p>原文：the object upon which the has-metadata test is
     *     performed.
     * @param metadataKey 正在查询的唯一元数据键.
     * <p>原文：the unique metadata key being queried.
     * @return metadataKey在主体中的存在性.
     * <p>原文：the existence of the metadataKey within subject.
     */
    public boolean hasMetadata(@NotNull T subject, @NotNull String metadataKey);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从主体中移除插件拥有的元数据项.
     * <p>原文：
     * Removes a metadata item owned by a plugin from a subject.
     *
     * @param subject 要从中移除元数据的对象.
     * <p>原文：the object to remove the metadata from.
     * @param metadataKey 标识要移除元数据的唯一元数据键.
     * <p>原文：the unique metadata key identifying the metadata to
     *     remove.
     * @param owningPlugin 尝试移除元数据项的插件.
     * <p>原文：the plugin attempting to remove a metadata item.
     * @throws IllegalArgumentException 如果插件为null
     * <p>原文：If plugin is null
     */
    public void removeMetadata(@NotNull T subject, @NotNull String metadataKey, @NotNull Plugin owningPlugin);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使元数据存储中来自给定插件的所有元数据无效. 这将强制每个无效的元数据项在下次访问时重新计算.
     * <p>原文：
     * Invalidates all metadata in the metadata store that originates from the
     * given plugin. Doing this will force each invalidated metadata item to
     * be recalculated the next time it is accessed.
     *
     * @param owningPlugin 请求无效化的插件.
     * <p>原文：the plugin requesting the invalidation.
     * @throws IllegalArgumentException 如果插件为null
     * <p>原文：If plugin is null
     */
    public void invalidateAll(@NotNull Plugin owningPlugin);
}
