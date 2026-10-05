package org.bukkit.inventory.meta;

import java.util.Map;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * EnchantmentMeta 特定于专门<i>存储</i>附魔的物品, 而非被附魔的物品.
 * {@link Material#ENCHANTED_BOOK 附魔书} 就是一个可存储附魔的物品的例子.
 * <p>原文：
 * EnchantmentMeta is specific to items that can <i>store</i> enchantments, as
 * opposed to being enchanted. {@link Material#ENCHANTED_BOOK} is an example
 * of an item with enchantment storage.
 */
public interface EnchantmentStorageMeta extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测是否存在任何附魔.
     * <p>原文：
     * Checks for the existence of any stored enchantments.
     *
     * @return 这个meta是否存在一个附魔
     * <p>原文：true if an enchantment exists on this meta
     */
    boolean hasStoredEnchants();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测是否存储特定的附魔.
     * <p>原文：
     * Checks for storage of the specified enchantment.
     *
     * @param enchant enchantment to check
     * @return 若此附魔存放于本 meta 中返回 true
     * <p>原文：true if this enchantment is stored in this meta
     */
    boolean hasStoredEnchant(@NotNull Enchantment enchant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测指定附魔的等级.
     * <p>原文：
     * Checks for the level of the stored enchantment.
     *
     * @param enchant enchantment to check
     * @return 附魔等级, 0 代表不存在此附魔
     * <p>原文：The level that the specified stored enchantment has, or 0 if
     *     none
     */
    int getStoredEnchantLevel(@NotNull Enchantment enchant);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在ItemMeta存储的附魔的副本.
     * <p>原文：
     * Gets a copy the stored enchantments in this ItemMeta.
     *
     * @return 一个不可变的存储的附魔的副本
     * <p>原文：An immutable copy of the stored enchantments
     */
    @NotNull
    Map<Enchantment, Integer> getStoredEnchants();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 向本物品存放一个指定的附魔.
     * <p>原文：
     * Stores the specified enchantment in this item meta.
     *
     * @param enchant Enchantment to store
     * @param level 附魔等级
     * <p>原文：Level for the enchantment
     * @param ignoreLevelRestriction 是否忽略附魔的等级限制
     * <p>原文：this indicates the enchantment should be
     *     applied, ignoring the level limit
     * @return 添加附魔成功返回 true, false反之
     * <p>原文：true if the item meta changed as a result of this call, false
     *     otherwise
     * @throws IllegalArgumentException 若指定附魔为 null
     * <p>原文：if enchantment is null
     */
    boolean addStoredEnchant(@NotNull Enchantment enchant, int level, boolean ignoreLevelRestriction);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 移除物品上指定的附魔.
     * <p>原文：
     * Remove the specified stored enchantment from this item meta.
     *
     * @param enchant Enchantment to remove
     * @return 移除附魔成功返回 true, false反之
     * <p>原文：true if the item meta changed as a result of this call, false
     *     otherwise
     * @throws IllegalArgumentException 若指定附魔为 null
     * <p>原文：if enchantment is null
     */
    boolean removeStoredEnchant(@NotNull Enchantment enchant) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测指定的附魔是否与任何 ItemMeta 里的附魔冲突.
     * <p>原文：
     * Checks if the specified enchantment conflicts with any enchantments in
     * this ItemMeta.
     *
     * @param enchant enchantment to test
     * @return 有冲突的附魔返回 true, false 反之
     * <p>原文：true if the enchantment conflicts, false otherwise
     */
    boolean hasConflictingStoredEnchant(@NotNull Enchantment enchant);

    @Override
    @NotNull
    EnchantmentStorageMeta clone();
}
