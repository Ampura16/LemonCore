package org.bukkit.metadata;

import com.google.common.base.Preconditions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator; // Paper
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

/**
 * @deprecated This system is extremely misleading and does not cleanup values for metadatable entities that have been
 * removed. It is recommended that when wanting persistent metadata, you use {@link org.bukkit.persistence.PersistentDataContainer}.
 * <p>
 * If you want temporary values on an entity, use the entity lifecycle events and a {@link java.util.Map} of your own. (See {@link com.destroystokyo.paper.event.entity.EntityAddToWorldEvent} and {@link com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent})
 */
@Deprecated
public abstract class MetadataStoreBase<T> {
    private Map<String, Map<Plugin, MetadataValue>> metadataMap = new java.util.concurrent.ConcurrentHashMap<String, Map<Plugin, MetadataValue>>(); // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向对象添加元数据值. 每个元数据值由特定{@link Plugin}拥有.
     * 如果插件已经向对象添加了元数据值, 该值将被{@code newMetadataValue}的值替换.
     * 多个插件可以为相同的{@code metadataKey}设置独立的值而不会冲突.
     * <p>
     * 实现说明：我考虑使用{@link java.util.concurrent.locks.ReadWriteLock}来控制对{@code metadataMap}的访问,
     * 但认为增加的开销不值得更细粒度的访问控制.
     * <p>
     * Bukkit几乎完全是单线程的, 所以锁定开销应该不会造成问题.
     * <p>原文：
     * Adds a metadata value to an object. Each metadata value is owned by a
     * specific {@link Plugin}. If a plugin has already added a metadata value
     * to an object, that value will be replaced with the value of {@code
     * newMetadataValue}. Multiple plugins can set independent values for the
     * same {@code metadataKey} without conflict.
     * <p>
     * Implementation note: I considered using a {@link
     * java.util.concurrent.locks.ReadWriteLock} for controlling access to
     * {@code metadataMap}, but decided that the added overhead wasn't worth
     * the finer grained access control.
     * <p>
     * Bukkit is almost entirely single threaded so locking overhead shouldn't
     * pose a problem.
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
     * @see MetadataStore#setMetadata(Object, String, MetadataValue)
     */
    public synchronized void setMetadata(@NotNull T subject, @NotNull String metadataKey, @NotNull MetadataValue newMetadataValue) {
        Preconditions.checkArgument(newMetadataValue != null, "Value cannot be null");
        Plugin owningPlugin = newMetadataValue.getOwningPlugin();
        Preconditions.checkArgument(owningPlugin != null, "Plugin cannot be null");
        String key = disambiguate(subject, metadataKey);
        Map<Plugin, MetadataValue> entry = metadataMap.get(key);
        if (entry == null) {
            entry = new WeakHashMap<Plugin, MetadataValue>(1);
            metadataMap.put(key, entry);
        }
        synchronized (entry) {
            entry.put(owningPlugin, newMetadataValue);
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回附加到对象的所有元数据值. 如果多个插件附加了元数据, 每个值都将被包含.
     * <p>原文：
     * Returns all metadata values attached to an object. If multiple
     * have attached metadata, each will value will be included.
     *
     * @param subject 正在查询的对象.
     * <p>原文：the object being interrogated.
     * @param metadataKey 正在查找的唯一元数据键.
     * <p>原文：the unique metadata key being sought.
     * @return 值列表, 每个设置了请求值的插件一个.
     * <p>原文：A list of values, one for each plugin that has set the
     *     requested value.
     * @see MetadataStore#getMetadata(Object, String)
     */
    @NotNull
    public List<MetadataValue> getMetadata(@NotNull T subject, @NotNull String metadataKey) { // Paper
        String key = disambiguate(subject, metadataKey);
        Map<Plugin, MetadataValue> entry = metadataMap.get(key);
        if (entry != null) {
            Collection<MetadataValue> values = entry.values();
            return Collections.unmodifiableList(new ArrayList<MetadataValue>(values));
        } else {
            return Collections.emptyList();
        }
    }

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
    public boolean hasMetadata(@NotNull T subject, @NotNull String metadataKey) { // Paper
        String key = disambiguate(subject, metadataKey);
        return metadataMap.containsKey(key);
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 从对象中移除指定插件拥有的元数据项.
     * <p>原文：
     * Removes a metadata item owned by a plugin from a subject.
     *
     * @param subject 要移除元数据的对象.
     * <p>原文：the object to remove the metadata from.
     * @param metadataKey 用于标识要移除的元数据的唯一键.
     * <p>原文：the unique metadata key identifying the metadata to
     *     remove.
     * @param owningPlugin 尝试移除元数据项的插件.
     * <p>原文：the plugin attempting to remove a metadata item.
     * @throws IllegalArgumentException 如果插件为null
     * <p>原文：If plugin is null
     * @see MetadataStore#removeMetadata(Object, String,
     *     org.bukkit.plugin.Plugin)
     */
    public void removeMetadata(@NotNull T subject, @NotNull String metadataKey, @NotNull Plugin owningPlugin) { // Paper
        Preconditions.checkArgument(owningPlugin != null, "Plugin cannot be null");
        String key = disambiguate(subject, metadataKey);
        Map<Plugin, MetadataValue> entry = metadataMap.get(key);
        if (entry == null) {
            return;
        }
        synchronized (entry) {
            entry.remove(owningPlugin);
            if (entry.isEmpty()) {
                metadataMap.remove(key);
            }
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 使元数据存储中所有来自指定插件的元数据失效.
     * 这样做将强制每个失效的元数据项在下次被访问时重新计算.
     * <p>原文：
     * Invalidates all metadata in the metadata store that originates from the
     * given plugin. Doing this will force each invalidated metadata item to
     * be recalculated the next time it is accessed.
     *
     * @param owningPlugin 请求失效的插件.
     * <p>原文：the plugin requesting the invalidation.
     * @throws IllegalArgumentException 如果插件为null
     * <p>原文：If plugin is null
     * @see MetadataStore#invalidateAll(org.bukkit.plugin.Plugin)
     */
    public void invalidateAll(@NotNull Plugin owningPlugin) { // Paper
        Preconditions.checkArgument(owningPlugin != null, "Plugin cannot be null");
        for (Map<Plugin, MetadataValue> values : metadataMap.values()) {
            if (values.containsKey(owningPlugin)) {
                values.get(owningPlugin).invalidate();
            }
        }
    }

    /**
     * Removes all metadata in the metadata store that originates from the
     * given plugin.
     *
     * @param owningPlugin the plugin requesting the invalidation.
     * @throws IllegalArgumentException If plugin is null
     */
    public void removeAll(@NotNull Plugin owningPlugin) {
        Preconditions.checkNotNull(owningPlugin, "Plugin cannot be null");
        for (Iterator<Map<Plugin, MetadataValue>> iterator = metadataMap.values().iterator(); iterator.hasNext(); ) {
            Map<Plugin, MetadataValue> values = iterator.next();
            if (values.containsKey(owningPlugin)) {
                values.remove(owningPlugin);
            }
            if (values.isEmpty()) {
                iterator.remove();
            }
        }
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 通过组合对象的唯一数据和metadataKey, 为接收元数据的对象创建唯一名称.
     * <p>
     * 创建的名称对于给定对象必须是全局唯一的, 任何两个等价的对象必须生成相同的唯一名称.
     * 例如, 如果两个Player对象代表同一个玩家, 即使这两个对象无法通过引用相等性测试, 它们也必须生成相同的字符串.
     * <p>原文：
     * Creates a unique name for the object receiving metadata by combining
     * unique data from the subject with a metadataKey.
     * <p>
     * The name created must be globally unique for the given object and any
     * two equivalent objects must generate the same unique name. For example,
     * two Player objects must generate the same string if they represent the
     * same player, even if the objects would fail a reference equality test.
     *
     * @param subject 为此对象生成键.
     * <p>原文：The object for which this key is being generated.
     * @param metadataKey 标识元数据值的名称.
     * <p>原文：The name identifying the metadata value.
     * @return 给定对象的唯一元数据键.
     * <p>原文：a unique metadata key for the given subject.
     */
    @NotNull
    protected abstract String disambiguate(@NotNull T subject, @NotNull String metadataKey);
}
