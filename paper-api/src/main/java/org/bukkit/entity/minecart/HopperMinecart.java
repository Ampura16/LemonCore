package org.bukkit.entity.minecart;

import com.destroystokyo.paper.loottable.LootableEntityInventory;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.loot.Lootable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表漏斗矿车.
 * <p>原文：
 * Represents a Minecart with a Hopper inside it
 */
public interface HopperMinecart extends Minecart, InventoryHolder, LootableEntityInventory {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测这个矿车是否会将物品捡进背包.
     * <p>原文：
     * Checks whether or not this Minecart will pick up
     * items into its inventory.
     *
     * @return 这个矿车是否会将物品捡进背包.
     * <p>原文：true if the Minecart will pick up items
     */
    boolean isEnabled();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个矿车是否会将物品捡进背包.
     * <p>原文：
     * Sets whether this Minecart will pick up items.
     *
     * @param enabled 新的启用状态
     * <p>原文：new enabled state
     */
    void setEnabled(boolean enabled);
    // Paper start
    /**
     * Gets the number of ticks that this hopper minecart cannot pickup items up for.
     *
     * @return ticks left on cooldown
     * @deprecated Hopper minecarts don't have cooldowns anymore
     */
    @Deprecated(forRemoval = true, since = "1.19.4")
    int getPickupCooldown();

    /**
     * Sets the number of ticks that this hopper minecart cannot pickup items for.
     *
     * @param cooldown cooldown length in ticks
     * @deprecated Hopper minecarts don't have cooldowns anymore
     */
    @Deprecated(forRemoval = true, since = "1.19.4")
    void setPickupCooldown(int cooldown);
    // Paper end
}
