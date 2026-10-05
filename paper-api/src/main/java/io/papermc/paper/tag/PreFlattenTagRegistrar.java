package io.papermc.paper.tag;

import io.papermc.paper.plugin.lifecycle.event.registrar.Registrar;
import io.papermc.paper.registry.RegistryKey;
import io.papermc.paper.registry.tag.TagKey;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.Unmodifiable;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 标签展开前的注册器。展开后的标签是指其中所有嵌套标签都已解析为嵌套标签所指向的标记值的标签。作为展开前注册器，它允许在展开发生之前（标签既指向单个条目也指向其他嵌套标签时）进行修改。<p> 在展开前注册器中创建标签的示例：<pre>{@code
 * class YourBootstrapClass implements PluginBootstrap {
 *
 *     public static final TagKey<ItemType> AXE_PICKAXE = ItemTypeTagKeys.create(Key.key("papermc:axe_pickaxe"));
 *
 *     @Override
 *     public void bootstrap(BootstrapContext context) {
 *         final LifecycleEventManager<BootstrapContext> manager = context.getLifecycleManager();
 *         manager.registerEventHandler(LifecycleEvents.TAGS.preFlatten(RegistryKey.ITEM), event -> {
 *             final PreFlattenTagRegistrar<ItemType> registrar = event.registrar();
 *             registrar.setTag(AXE_PICKAXE, Set.of(
 *                 TagEntry.tagEntry(ItemTypeTagKeys.PICKAXES),
 *                 TagEntry.tagEntry(ItemTypeTagKeys.AXES)
 *             ));
 *         });
 *     }
 * }
 * }</pre>
 * <p>原文：
 * Registrar for tags before they are flattened. Flattened
 * tags are tags which have any nested tags resolved to the tagged
 * values the nested tags point to. This registrar, being a pre-flatten
 * registrar, allows for modification before that flattening has happened, when
 * tags both point to individual entries and other nested tags.
 * <p>
 * An example of a tag being created in a pre-flatten registrar:
 * <pre>{@code
 * class YourBootstrapClass implements PluginBootstrap {
 *
 *     public static final TagKey<ItemType> AXE_PICKAXE = ItemTypeTagKeys.create(Key.key("papermc:axe_pickaxe"));
 *
 *     @Override
 *     public void bootstrap(BootstrapContext context) {
 *         final LifecycleEventManager<BootstrapContext> manager = context.getLifecycleManager();
 *         manager.registerEventHandler(LifecycleEvents.TAGS.preFlatten(RegistryKey.ITEM), event -> {
 *             final PreFlattenTagRegistrar<ItemType> registrar = event.registrar();
 *             registrar.setTag(AXE_PICKAXE, Set.of(
 *                 TagEntry.tagEntry(ItemTypeTagKeys.PICKAXES),
 *                 TagEntry.tagEntry(ItemTypeTagKeys.AXES)
 *             ));
 *         });
 *     }
 * }
 * }</pre>
 *
 * @param <T> 标签中值的类型
 * <p>原文：the type of value in the tag
 * @see PostFlattenTagRegistrar
 */
@ApiStatus.Experimental
@NullMarked
@ApiStatus.NonExtendable
public interface PreFlattenTagRegistrar<T> extends Registrar {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此标签注册器的注册表键。
     * <p>原文：
     * Get the registry key for this tag registrar.
     *
     * @return 注册表键
     * <p>原文：the registry key
     */
    RegistryKey<T> registryKey();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此注册器当前持有的所有标签的副本。
     * <p>原文：
     * Get a copy of all tags currently held in this registrar.
     *
     * @return 所有标签的不可变映射
     * <p>原文：an immutable map of all tags
     */
    @Contract(value = "-> new", pure = true)
    @Unmodifiable Map<TagKey<T>, Collection<TagEntry<T>>> getAllTags();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 检查此注册器是否具有给定键的标签。
     * <p>原文：
     * Checks if this registrar has a tag with the given key.
     *
     * @param tagKey 要检查的键
     * <p>原文：the key to check for
     * @return 如果标签存在则为 true，否则为 false
     * <p>原文：true if the tag exists, false otherwise
     */
    @Contract(pure = true)
    boolean hasTag(TagKey<T> tagKey);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取具有给定键的标签。请先使用 {@link #hasTag(TagKey)} 检查标签是否存在。
     * <p>原文：
     * Get the tag with the given key. Use {@link #hasTag(TagKey)} to check
     * if a tag exists first.
     *
     * @param tagKey 要获取的标签的键
     * <p>原文：the key of the tag to get
     * @return 标签条目的不可变列表
     * <p>原文：an immutable list of tag entries
     * @throws java.util.NoSuchElementException 如果标签不存在
     * <p>原文：if the tag does not exist
     * @see #hasTag(TagKey)
     */
    @Contract(value = "_ -> new", pure = true)
    @Unmodifiable List<TagEntry<T>> getTag(TagKey<T> tagKey);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 向给定标签添加条目。如果标签不存在，将创建该标签。
     * <p>原文：
     * Adds entries to the given tag. If the tag does not exist, it will be created.
     *
     * @param tagKey 要添加到的标签的键
     * <p>原文：the key of the tag to add to
     * @param entries 要添加的条目
     * <p>原文：the entries to add
     * @see #setTag(TagKey, Collection)
     */
    @Contract(mutates = "this")
    void addToTag(TagKey<T> tagKey, Collection<TagEntry<T>> entries);

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 设置给定标签的条目。如果标签不存在，将创建该标签；如果标签已存在，将被覆盖。
     * <p>原文：
     * Sets the entries of the given tag. If the tag does not exist, it will be created.
     * If the tag does exist, it will be overwritten.
     *
     * @param tagKey 要设置的标签的键
     * <p>原文：the key of the tag to set
     * @param entries 要设置的条目
     * <p>原文：the entries to set
     * @see #addToTag(TagKey, Collection)
     */
    @Contract(mutates = "this")
    void setTag(TagKey<T> tagKey, Collection<TagEntry<T>> entries);
}
