package org.bukkit.inventory;

import io.papermc.paper.datacomponent.DataComponentType;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 隐藏ItemStacks的某些属性.
 * <p>原文：
 * A ItemFlag can hide some Attributes from ItemStacks
 *
 * @apiNote Setting these without also setting the data they are hiding
 * may not result in the item flag being persisted in the ItemMeta/ItemStack.
 */
public enum ItemFlag {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置显示/隐藏附魔.
     * <p>原文：
     * Setting to show/hide enchants
     */
    HIDE_ENCHANTS,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置显示/隐藏该Item的属性,例如伤害.
     * <p>原文：
     * Setting to show/hide Attributes like Damage
     */
    HIDE_ATTRIBUTES,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置显示/隐藏不可破坏属性.
     * <p>原文：
     * Setting to show/hide the unbreakable State
     */
    HIDE_UNBREAKABLE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置显示/隐藏哪些ItemStack可以被破坏.
     * <p>原文：
     * Setting to show/hide what the ItemStack can break/destroy
     */
    HIDE_DESTROYS,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置显示/隐藏哪些ItemStack可以被放置.
     * <p>原文：
     * Setting to show/hide where this ItemStack can be build/placed on
     */
    HIDE_PLACED_ON,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置显示/隐藏ItemStack的药水效果、书本和烟花信息、地图提示、旗帜图案以及附魔书的附魔词条.
     * <p>原文：
     * Setting to show/hide item-specific information, including, but not limited to:
     * <ul>
     *     <li>Potion effects on potions, tipped arrows, and suspicious stew</li>
     *     <li>Enchanted book enchantments</li>
     *     <li>Book author and generation</li>
     *     <li>Record names</li>
     *     <li>Patterns of banners and shields</li>
     *     <li>Fish bucket variants</li>
     *     <li>Instrument item descriptions (i.e. goat horn sounds)</li>
     *     <li>Map data</li>
     *     <li>Firework data</li>
     *     <li>Crossbow projectile info</li>
     *     <li>Bundle fullness</li>
     *     <li>Shulker box contents</li>
     *     <li>Spawner descriptions</li>
     * </ul>
     *
     * @see #HIDE_STORED_ENCHANTS HIDE_STORED_ENCHANTS for hiding stored enchants (like on enchanted books)
     * @deprecated does not exist anymore and will not properly work with individually hidden data components; see {@link io.papermc.paper.datacomponent.item.TooltipDisplay} and {@link ItemStack#setData(DataComponentType.Valued, Object)}
     */
    @Deprecated
    HIDE_ADDITIONAL_TOOLTIP,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置显示/隐藏彩色皮革盔甲的染料.
     * <p>原文：
     * Setting to show/hide dyes from colored leather armor.
     */
    HIDE_DYE,
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 显示/隐藏皮革盔甲上的盔甲纹饰设置
     * <p>原文：
     * Setting to show/hide armor trim from armor.
     */
    HIDE_ARMOR_TRIM,
    /**
     * Setting to show/hide stored enchants on an item, such as enchantments
     * on an enchanted book.
     */
    HIDE_STORED_ENCHANTS,
    ;
}
