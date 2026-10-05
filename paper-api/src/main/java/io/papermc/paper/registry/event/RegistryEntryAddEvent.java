package io.papermc.paper.registry.event;

import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.tag.Tag;
import io.papermc.paper.registry.tag.TagKey;
import org.bukkit.Keyed;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link RegistryEventProvider#entryAdd()} 的事件对象。该事件在特定条目注册/添加到注册表之前立即触发，为插件提供修改该条目部分内容的途径。
 * <p>原文：
 * Event object for {@link RegistryEventProvider#entryAdd()}. This
 * event is fired right before a specific entry is registered in/added to registry.
 * It provides a way for plugins to modify parts of this entry.
 *
 * @param <T> 注册表条目类型
 * <p>原文：registry entry type
 * @param <B> 注册表条目构建器类型
 * <p>原文：registry entry builder type
 */
@ApiStatus.NonExtendable
public interface RegistryEntryAddEvent<T, B extends RegistryBuilder<T>> extends RegistryEvent<T> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取正被添加到注册表的条目的构建器。
     * <p>原文：
     * Gets the builder for the entry being added to the registry.
     *
     * @return 对象构建器
     * <p>原文：the object builder
     */
    B builder();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取注册表中此条目的键。
     * <p>原文：
     * Gets the key for this entry in the registry.
     *
     * @return 键
     * <p>原文：the key
     */
    TypedKey<T> key();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取或创建给定标签键对应的标签。之后需要通过内置或自定义数据包填充该标签。
     * <p>原文：
     * Gets or creates a tag for the given tag key. This tag
     * is then required to be filled either from the built-in or
     * custom datapack.
     *
     * @param tagKey 标签键
     * <p>原文：the tag key
     * @return 标签
     * <p>原文：the tag
     * @param <V> 标签值类型
     * <p>原文：the tag value type
     */
    <V extends Keyed> Tag<V> getOrCreateTag(TagKey<V> tagKey);
}
