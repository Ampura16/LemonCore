package org.bukkit.entity.minecart;

import com.destroystokyo.paper.loottable.LootableEntityInventory;
import org.bukkit.entity.Minecart;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.loot.Lootable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表运输矿车，你可以使用{@link InventoryHolder}里的方法来访问.
 * <p>原文：
 * Represents a minecart with a chest. These types of {@link Minecart
 * minecarts} have their own inventory that can be accessed using methods
 * from the {@link InventoryHolder} interface.
 */
public interface StorageMinecart extends Minecart, InventoryHolder, LootableEntityInventory { // Paper
}
