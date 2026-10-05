package io.papermc.paper.tag;

import io.papermc.paper.registry.TypedKey;
import io.papermc.paper.registry.tag.TagKey;
import net.kyori.adventure.key.Keyed;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 条目是预先展开的标签，表示单个注册表条目或整个标签。
 * <p>原文：
 * An entry is a pre-flattened tag. Represents
 * either an individual registry entry or a whole tag.
 *
 * @param <T> 标签中值的类型
 * <p>原文：the type of value in the tag
 * @see PreFlattenTagRegistrar
 */
@ApiStatus.Experimental
@NullMarked
@ApiStatus.NonExtendable
public interface TagEntry<T> extends Keyed {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为单个值创建必需的标签条目。
     * <p>原文：
     * Create required tag entry for a single value.
     *
     * @param entryKey 条目的键
     * <p>原文：the key of the entry
     * @return 值的新标签条目
     * <p>原文：a new tag entry for a value
     * @param <T> 值的类型
     * <p>原文：the type of value
     */
    @Contract(value = "_ -> new", pure = true)
    static <T> TagEntry<T> valueEntry(final TypedKey<T> entryKey) {
        return valueEntry(entryKey, true);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为单个值创建标签条目。
     * <p>原文：
     * Create tag entry for a single value.
     *
     * @param entryKey 条目的键
     * <p>原文：the key of the entry
     * @param isRequired 此条目是否为必需（参见 {@link #isRequired()}）
     * <p>原文：if this entry is required (see {@link #isRequired()})
     * @return 值的新标签条目
     * <p>原文：a new tag entry for a value
     * @param <T> 值的类型
     * <p>原文：the type of value
     */
    @Contract(value = "_, _ -> new", pure = true)
    static <T> TagEntry<T> valueEntry(final TypedKey<T> entryKey, final boolean isRequired) {
        return new TagEntryImpl<>(entryKey.key(), false, isRequired);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为嵌套标签创建必需的标签条目。
     * <p>原文：
     * Create a required tag entry for a nested tag.
     *
     * @param tagKey 标签的键
     * <p>原文：they key for the tag
     * @return 标签的新标签条目
     * <p>原文：a new tag entry for a tag
     * @param <T> 值的类型
     * <p>原文：the type of value
     */
    @Contract(value = "_ -> new", pure = true)
    static <T> TagEntry<T> tagEntry(final TagKey<T> tagKey) {
        return tagEntry(tagKey, true);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 为嵌套标签创建标签条目。
     * <p>原文：
     * Create a tag entry for a nested tag.
     *
     * @param tagKey 标签的键
     * <p>原文：they key for the tag
     * @param isRequired 此条目是否为必需（参见 {@link #isRequired()}）
     * <p>原文：if this entry is required (see {@link #isRequired()})
     * @return 标签的新标签条目
     * <p>原文：a new tag entry for a tag
     * @param <T> 值的类型
     * <p>原文：the type of value
     */
    @Contract(value = "_, _ -> new", pure = true)
    static <T> TagEntry<T> tagEntry(final TagKey<T> tagKey, final boolean isRequired) {
        return new TagEntryImpl<>(tagKey.key(), true, isRequired);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此条目是否表示一个标签。
     * <p>原文：
     * Returns if this entry represents a tag.
     *
     * @return 如果此条目是标签则为 true，如果是单个条目则为 false
     * <p>原文：true if this entry is a tag, false if it is an individual entry
     */
    @Contract(pure = true)
    boolean isTag();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 返回此条目是否为必需。如果条目是必需的，该值或标签必须存在于服务器上，标签才能正确加载。缺失的值会导致持有该缺失值的标签无法创建。
     * <p>原文：
     * Returns if this entry is required. If an entry is required,
     * the value or tag must exist on the server in order for the tag
     * to load correctly. A missing value will prevent the tag holding
     * that missing value from being created.
     *
     * @return 如果此条目是必需的则为 true，如果是可选的则为 false
     * <p>原文：true if this entry is required, false if it is optional
     */
    @Contract(pure = true)
    boolean isRequired();
}
