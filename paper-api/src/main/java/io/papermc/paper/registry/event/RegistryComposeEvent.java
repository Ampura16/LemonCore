package io.papermc.paper.registry.event;

import io.papermc.paper.registry.RegistryBuilder;
import io.papermc.paper.registry.tag.Tag;
import io.papermc.paper.registry.tag.TagKey;
import org.bukkit.Keyed;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * {@link RegistryEventProvider#compose()} 的事件对象。该事件在注册表加载完其常规值之后触发，为插件提供向注册表添加新对象的途径。
 * <p>原文：
 * Event object for {@link RegistryEventProvider#compose()}. This
 * event is fired after a registry is loaded with its normal values.
 * It provides a way for plugins to add new objects to the registry.
 *
 * @param <T> 注册表条目类型
 * <p>原文：registry entry type
 * @param <B> 注册表条目构建器类型
 * <p>原文：registry entry builder type
 */
@ApiStatus.NonExtendable
public interface RegistryComposeEvent<T, B extends RegistryBuilder<T>> extends RegistryEvent<T> {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取可写注册表。
     * <p>原文：
     * Get the writable registry.
     *
     * @return 可写注册表
     * <p>原文：a writable registry
     */
    WritableRegistry<T, B> registry();

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
    <V extends Keyed> Tag<V> getOrCreateTag(TagKey<V> tagKey); // TODO remove Keyed
}
