package io.papermc.paper.datacomponent.item;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Contract;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 持有物品使用时的物品转化信息内容。
 * <p>原文：
 * Holds the contents of item transformation information when an item is used.
 * @see io.papermc.paper.datacomponent.DataComponentTypes#USE_REMAINDER
 */
@NullMarked
@ApiStatus.Experimental
@ApiStatus.NonExtendable
public interface UseRemainder {

    @Contract(value = "_ -> new", pure = true)
    static UseRemainder useRemainder(final ItemStack itemStack) {
        return ItemComponentTypesBridge.bridge().useRemainder(itemStack);
    }

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 被食用的物品转化成的物品。
     * <p>原文：
     * The item that the item that is consumed is transformed into.
     *
     * @return 物品
     * <p>原文：item
     */
    @Contract(value = "-> new", pure = true)
    ItemStack transformInto();
}
