package io.papermc.paper.datacomponent.item;

import io.papermc.paper.registry.set.RegistryKeySet;
import org.bukkit.inventory.ItemType;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有此物品是否可修复，以及可以用哪些物品类型修复。
 * <p>原文：
 * Holds if this item is repairable, and what item types it can be repaired with.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#REPAIRABLE
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface Repairable {

    @Contract(value = "_ -> new", pure = true)
    static Repairable repairable(final RegistryKeySet<ItemType> types) {
        return ItemComponentTypesBridge.bridge().repairable(types);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 此物品可用于修复的类型。
     * <p>原文：
     * The types that this item is repairable to.
     *
     * @return 物品
     * <p>原文：item
     */
    @Contract(value = "-> new", pure = true)
    RegistryKeySet<ItemType> types();
}
