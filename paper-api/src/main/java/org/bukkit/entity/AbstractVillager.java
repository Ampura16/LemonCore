package org.bukkit.entity;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.Merchant;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个抽象村民NPC.
 * <p>原文：
 * Represents a villager NPC
 */
public interface AbstractVillager extends Breedable, NPC, InventoryHolder, Merchant {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获得这个村民的物品栏.
     * <br>
     * 注意这个物品栏不是商人村民交易的物品, 而是村民收集的物品(收割庄稼).
     * <p>原文：
     * Gets this villager's inventory.
     * <br>
     * Note that this inventory is not the Merchant inventory, rather, it is the
     * items that a villager might have collected (from harvesting crops, etc.)
     *
     * {@inheritDoc}
     */
    @NotNull
    @Override
    Inventory getInventory();

    // Paper start
    /**
     * Reset this villager's trade offers.
     * <br>
     * For {@link org.bukkit.entity.Villager Villagers}, only two trades are
     * created, rather than the number of trades expected for the villager's
     * level. You should use {@link org.bukkit.entity.Villager#addTrades(int)}
     * to add the remaining trades.
     */
    public void resetOffers();
    // Paper end
}
