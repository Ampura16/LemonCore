package org.bukkit;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表附加了 {@link NamespacedKey} 的对象.
 * <p>原文：
 * Represents an object which has a {@link NamespacedKey} attached to it.
 */
public interface Keyed extends net.kyori.adventure.key.Keyed { // Paper -- extend Adventure Keyed

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回用于此对象的命名空间标识符.
     * <p>原文：
     * Return the namespaced identifier for this object.
     *
     * @return 标识此对象的 key
     * <p>原文：this object's key
     */
    @NotNull
    NamespacedKey getKey();

    // Paper start
    /**
     * Returns the unique identifier for this object.
     *
     * @return this object's key
     */
    @Override
    default net.kyori.adventure.key.@NotNull Key key() {
        return this.getKey();
    }
    // Paper end
}
