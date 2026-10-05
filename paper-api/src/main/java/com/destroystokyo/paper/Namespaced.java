package com.destroystokyo.paper;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示带命名空间的资源，单个元素参见 {@link org.bukkit.NamespacedKey}，元素集合参见 {@link com.destroystokyo.paper.NamespacedTag}。命名空间只能包含小写字母数字、句点、下划线和连字符。
 * <p>
 * 键只能包含小写字母数字、句点、下划线、连字符和正斜杠。
 * <p>
 * 你不应自行实现此接口，请根据需要改用 {@link org.bukkit.NamespacedKey} 或 {@link com.destroystokyo.paper.NamespacedTag}。
 * <p>原文：
 * Represents a namespaced resource, see {@link org.bukkit.NamespacedKey} for single elements
 * or {@link com.destroystokyo.paper.NamespacedTag} for a collection of elements
 *
 * Namespaces may only contain lowercase alphanumeric characters, periods,
 * underscores, and hyphens.
 * <p>
 * Keys may only contain lowercase alphanumeric characters, periods,
 * underscores, hyphens, and forward slashes.
 * <p>
 * You should not be implementing this interface yourself, use {@link org.bukkit.NamespacedKey}
 * or {@link com.destroystokyo.paper.NamespacedTag} as needed instead.
 */
@Deprecated(forRemoval = true, since = "1.20.6")
public interface Namespaced {
    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取此资源所属的命名空间
     * <p>
     * 按约定，它只能包含小写字母数字、句点、下划线和连字符。
     * <p>原文：
     * Gets the namespace this resource is a part of
     * <p>
     * This is contractually obligated to only contain lowercase alphanumeric characters,
     * periods, underscores, and hyphens.
     *
     * @return 资源命名空间
     * <p>原文：resource namespace
     */
    @NotNull
    String getNamespace();

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取与此资源对应的键
     * <p>
     * 按约定，它只能包含小写字母数字、句点、下划线、连字符和正斜杠。
     * <p>原文：
     * Gets the key corresponding to this resource
     * <p>
     * This is contractually obligated to only contain lowercase alphanumeric characters,
     * periods, underscores, hyphens, and forward slashes.
     *
     * @return 资源键
     * <p>原文：resource key
     */
    @NotNull
    String getKey();
}
