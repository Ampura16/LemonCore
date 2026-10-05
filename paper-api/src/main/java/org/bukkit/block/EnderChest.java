package org.bukkit.block;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表末影箱.
 * <p>原文：
 * Represents a captured state of an ender chest.
 */
public interface EnderChest extends Lidded, TileState {
    // Paper start - More Chest Block API
    /**
     * Checks whether this ender chest is blocked by a block above
     *
     * @return whether this ender chest is blocked
     */
    boolean isBlocked();
    // Paper end - More Chest Block API
}
