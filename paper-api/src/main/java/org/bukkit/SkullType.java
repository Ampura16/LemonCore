package org.bukkit;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表不同种类的头颅.
 * <p>原文：
 * Represents the different types of skulls.
 *
 * @deprecated 现在(Bukkit 1.13版本及以上)不同种类的头颅都有他们自己的常量了.
 * <p>原文：check {@link Material} instead
 */
@Deprecated(since = "1.13", forRemoval = true)
public enum SkullType {
    SKELETON,
    WITHER,
    ZOMBIE,
    PLAYER,
    CREEPER,
    DRAGON,
    PIGLIN;
}
