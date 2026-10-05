package org.bukkit.enchantments;

import org.bukkit.Material;
import org.bukkit.Tag;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表了{@link Enchantment}的适用目标
 * <p>原文：
 * Represents the applicable target for a {@link Enchantment}
 *
 * @deprecated enchantment groupings are now managed by tags, not categories
 */
@Deprecated(since = "1.20.5", forRemoval = true)
public enum EnchantmentTarget {
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将这个附魔用于所有的物品上.
     * <p>原文：
     * Allows the Enchantment to be placed on all items
     *
     * @deprecated 原版中该适用目标不复存在
     * <p>原文：this target no longer exists in Vanilla
     */
    @Deprecated(since = "1.16.1", forRemoval = true)
    ALL {
        @Override
        public boolean includes(@NotNull Material item) {
            for (EnchantmentTarget target : EnchantmentTarget.values()) {
                if (target != this && target.includes(item)) {
                    return true;
                }
            }

            return false;
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将该附魔用于装备上. (包括鞋子，护腿，衣服以及帽子)
     * <p>原文：
     * Allows the Enchantment to be placed on armor
     */
    ARMOR {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_ARMOR.isTagged(item);
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将该附魔用于鞋子上.
     * <p>原文：
     * Allows the Enchantment to be placed on feet slot armor
     */
    ARMOR_FEET {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_FOOT_ARMOR.isTagged(item);
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将该附魔用于护腿上.
     * <p>原文：
     * Allows the Enchantment to be placed on leg slot armor
     */
    ARMOR_LEGS {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_LEG_ARMOR.isTagged(item);
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将该附魔用于衣服上.
     * <p>原文：
     * Allows the Enchantment to be placed on torso slot armor
     */
    ARMOR_TORSO {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_CHEST_ARMOR.isTagged(item);
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将该附魔用于帽子上.
     * <p>原文：
     * Allows the Enchantment to be placed on head slot armor
     */
    ARMOR_HEAD {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_HEAD_ARMOR.isTagged(item);
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将该附魔用于剑上.
     * <p>原文：
     * Allows the Enchantment to be placed on weapons (swords, spears)
     */
    WEAPON {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_MELEE_WEAPON.isTagged(item);
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将该附魔用于工具上，如锹，稿，斧。
     * <p>原文：
     * Allows the Enchantment to be placed on tools (spades, pickaxe, axes)
     */
    TOOL {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_MINING_LOOT.isTagged(item);
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将该附魔用于弓上.
     * <p>原文：
     * Allows the Enchantment to be placed on bows.
     */
    BOW {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_BOW.isTagged(item);
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将该附魔用于钓鱼竿上.
     * <p>原文：
     * Allows the Enchantment to be placed on fishing rods.
     */
    FISHING_ROD {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_FISHING.isTagged(item);
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将该附魔用于带耐久度的物品上.
     * <p>原文：
     * Allows the enchantment to be placed on items with durability.
     */
    BREAKABLE {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_DURABILITY.isTagged(item);
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将这类附魔附加于可穿戴物品.
     * <p>原文：
     * Allows the enchantment to be placed on wearable items.
     */
    WEARABLE {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_EQUIPPABLE.isTagged(item);
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将这类附魔附加于三叉戟.
     * <p>原文：
     * Allow the Enchantment to be placed on tridents.
     */
    TRIDENT {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_TRIDENT.isTagged(item);
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将这类附魔附加于弩上.
     * <p>原文：
     * Allow the Enchantment to be placed on crossbows.
     */
    CROSSBOW {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_CROSSBOW.isTagged(item);
        }
    },

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 允许将这类附魔附加于vanishing items.
     * <p>原文：
     * Allow the Enchantment to be placed on vanishing items.
     */
    VANISHABLE {
        @Override
        public boolean includes(@NotNull Material item) {
            return Tag.ITEMS_ENCHANTABLE_VANISHING.isTagged(item);
        }
    };

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查本适用目标是否包含该物品类型.
     * <p>原文：
     * Check whether this target includes the specified item.
     *
     * @param item 物品
     * <p>原文：The item to check
     * @return 如果包含则返回true
     * <p>原文：True if the target includes the item
     */
    public abstract boolean includes(@NotNull Material item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查本适用目标是否包含该物品.
     * <p>原文：
     * Check whether this target includes the specified item.
     *
     * @param item 物品
     * <p>原文：The item to check
     * @return 如果包含则返回true
     * <p>原文：True if the target includes the item
     */
    public boolean includes(@NotNull ItemStack item) {
        return includes(item.getType());
    }
}
