package org.bukkit.inventory.meta;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFactory;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表{@link Material#LEATHER_BOOTS 皮革靴子}，{@link Material#LEATHER_CHESTPLATE 皮革外套}，{@link Material#LEATHER_HELMET 皮革帽子}，{@link Material#LEATHER_LEGGINGS 皮革裤子}，可以被染色.
 * <p>
 * 译注：下文中的“盔甲”可以是皮革靴子、外套、帽子、裤子任意一种，因为这个类代表了四种嘛.
 * <p>原文：
 * Represents leather armor ({@link Material#LEATHER_BOOTS}, {@link
 * Material#LEATHER_LEGGINGS}, {@link Material#LEATHER_CHESTPLATE}, {@link
 * Material#LEATHER_HELMET}, {@link Material#LEATHER_HORSE_ARMOR} or {@link
 * Material#WOLF_ARMOR}) that can be colored.
 */
public interface LeatherArmorMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取这个盔甲的颜色.如果它还没有被设置，就会返回{@link ItemFactory#getDefaultLeatherColor()}.
     * <p>原文：
     * Gets the color of the armor. If it has not been set otherwise, it will
     * be {@link ItemFactory#getDefaultLeatherColor()}.
     *
     * @return 这个盔甲的颜色，注意这永远都不会是null
     * <p>原文：the color of the armor, never null
     * @apiNote The method yielding {@link ItemFactory#getDefaultLeatherColor()} is incorrect
     * for {@link Material#WOLF_ARMOR} as its default color differs. Generally, it is recommended to check
     * {@link #isDyed()} to determine if this leather armor is dyed than to compare this color to the default.
     */
    @NotNull
    Color getColor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置这个盔甲的颜色.
     * <p>原文：
     * Sets the color of the armor.
     *
     * @param color 要设置的颜色.设置此为null等于设置成{@link ItemFactory#getDefaultLeatherColor()}.
     * <p>原文：the color to set.
     */
    void setColor(@Nullable Color color);

    @Override
    @NotNull
    LeatherArmorMeta clone();

    // Paper start - Expose #hasColor to leather armor
    /**
     * Checks whether this leather armor is dyed.
     *
     * @return whether this leather armor is dyed
     */
    boolean isDyed();
    // Paper end - Expose #hasColor to leather armor
}
