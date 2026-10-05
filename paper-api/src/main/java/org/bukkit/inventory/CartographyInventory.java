package org.bukkit.inventory;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 制图台的物品栏接口.
 * <p>原文：
 * Interface to the inventory of a Cartography table.
 */
public interface CartographyInventory extends Inventory {
    // Paper begin - add getResult/setResult to CartographyInventory
    /**
     * Check what item is in the result slot of this cartography table.
     *
     * @return the result item
     */
    @org.jetbrains.annotations.Nullable
    default ItemStack getResult() {
        return this.getItem(2); // net.minecraft.world.inventory.CartographyTableMenu.RESULT_SLOT
    }

    /**
     * Set the item in the result slot of the cartography table
     *
     * @param newResult the new result item
     */
    default void setResult(final @org.jetbrains.annotations.Nullable ItemStack newResult) {
        this.setItem(2, newResult); // net.minecraft.world.inventory.CartographyTableMenu.RESULT_SLOT
    }
    // Paper end - add getResult/setResult to CartographyInventory
}
