package org.bukkit;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表世界中的传送门.
 * <p>原文：
 * Represents various types of portals that can be made in a world.
 */
public enum PortalType {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 下界传送门.
     * <p>原文：
     * This is a Nether portal, made of obsidian.
     */
    NETHER,

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 末路之地传送门.
     * <p>原文：
     * This is an Ender portal.
     */
    ENDER,
    // Paper start
    /**
     * This is an end gateway
     */
    END_GATEWAY,
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 自定义传送门.
     * <p>原文：
     * This is a custom Plugin portal.
     */
    CUSTOM;
}
