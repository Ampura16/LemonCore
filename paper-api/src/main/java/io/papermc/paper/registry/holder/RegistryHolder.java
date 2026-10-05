package io.papermc.paper.registry.holder;

import io.papermc.paper.registry.TypedKey;
import org.jetbrains.annotations.ApiStatus;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 在注册表加载阶段，某些值可能是对将来才加载的值的引用，也可能是已存在的内联匿名值。此类型（及其子类型）表示这种结构。
 * <p>原文：
 * During the registry loading phase, some values can be references to values to-be-loaded
 * in the future, or inlined, anonymous values that already exist. This type (and subtypes) represent
 * that structure.
 * @param <API> 注册表的类型
 * <p>原文：the registry's type
 * @param <ENTRY> 注册表条目的类型（用于内联值）
 * <p>原文：the type of the registry entry (for inlined values)
 */
@ApiStatus.Experimental
public sealed interface RegistryHolder<API, ENTRY> permits RegistryHolder.Reference, RegistryHolder.Inlined {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 通过键引用注册表值但本身不包含该条目的持有者。用于仅通过键引用、可能尚未关联任何值的条目。
     * <p>原文：
     * A holder that references a registry value by key, but does not have the entry itself.
     * This is used for entries that are only referenced by key and may not yet have any value associated with them.
     *
     * @param <API> 注册表的类型
     * <p>原文：the registry's type
     * @param <ENTRY> 注册表条目的类型
     * <p>原文：the type of the registry entry
     */
    @ApiStatus.NonExtendable
    non-sealed interface Reference<API, ENTRY> extends RegistryHolder<API, ENTRY> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 被引用值的键。
         * <p>原文：
         * The key of the referenced value.
         *
         * @return 值的键
         * <p>原文：the key of the value
         */
        TypedKey<API> key();
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 包含内联注册表条目（即没有键的匿名值）的持有者。
     * <p>原文：
     * A holder that contains an inlined registry entry, an anonymous value (does not have a key).
     *
     * @param <API> 注册表的类型
     * <p>原文：the registry's type
     * @param <ENTRY> 注册表条目的类型
     * <p>原文：the type of the registry entry
     */
    @ApiStatus.NonExtendable
    non-sealed interface Inlined<API, ENTRY> extends RegistryHolder<API, ENTRY> {

        /**
         * <!-- Paper HTML javadoc zh translation -->
         * 内联条目。
         * <p>原文：
         * The inlined entry.
         *
         * @return 内联条目
         * <p>原文：the inlined entry
         */
        ENTRY entry();
    }
}
