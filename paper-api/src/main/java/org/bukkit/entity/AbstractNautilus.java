package org.bukkit.entity;

import org.bukkit.inventory.ArmoredSaddledMountInventory;
import org.bukkit.inventory.InventoryHolder;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一种鹦鹉螺类型的生物.
 */
@NullMarked
public interface AbstractNautilus extends Tameable, InventoryHolder, Vehicle {

    @Override
    ArmoredSaddledMountInventory getInventory();
}
