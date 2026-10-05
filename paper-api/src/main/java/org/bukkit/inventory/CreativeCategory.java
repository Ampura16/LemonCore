package org.bukkit.inventory;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表创造模式物品栏中的一个分类.
 * <p>原文：
 * Represents a category in the creative inventory.
 */
public enum CreativeCategory implements net.kyori.adventure.translation.Translatable { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 各种建筑方块, 包括泥土、砖块、木板、矿石、台阶等.
     * <p>原文：
     * An assortment of building blocks including dirt, bricks, planks, ores
     * slabs, etc.
     */
    BUILDING_BLOCKS("buildingBlocks"), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 通常用于装饰的方块和物品, 包括蜡烛、树苗、植物、动物、栅栏、墙壁、地毯等.
     * <p>原文：
     * Blocks and items typically used for decorative purposes including
     * candles, saplings, flora, fauna, fences, walls, carpets, etc.
     */
    DECORATIONS("decorations"), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用于红石装置的方块和物品, 包括按钮、拉杆、压力板、红石元件、活塞等.
     * <p>原文：
     * Blocks used and associated with redstone contraptions including buttons,
     * levers, pressure plates, redstone components, pistons, etc.
     */
    REDSTONE("redstone"), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 与运输相关的物品, 包括矿车、铁轨、船、鞘翅等.
     * <p>原文：
     * Items pertaining to transportation including minecarts, rails, boats,
     * elytra, etc.
     */
    TRANSPORTATION("transportation"), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 不属于其他分类的杂项物品和方块, 包括宝石、染料、刷怪蛋、唱片、旗帜图案等.
     * <p>原文：
     * Miscellaneous items and blocks that do not fit into other categories
     * including gems, dyes, spawn eggs, discs, banner patterns, etc.
     */
    MISC("misc"), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 玩家可食用的食物物品, 包括肉类、浆果、生物掉落的可食用物等.
     * <p>原文：
     * Food items consumable by the player including meats, berries, edible
     * drops from creatures, etc.
     */
    FOOD("food"), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用于一般用途的装备物品, 包括镐、斧、锄、打火石, 以及适用于上述工具的附魔书.
     * <p>原文：
     * Equipment items meant for general utility including pickaxes, axes, hoes,
     * flint and steel, and useful enchantment books for said tools.
     */
    TOOLS("tools"), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 用于战斗的装备物品, 包括盔甲、剑、弓、药箭, 以及适用于上述装备的附魔书.
     * <p>原文：
     * Equipment items meant for combat including armor, swords, bows, tipped
     * arrows, and useful enchantment books for said equipment.
     */
    COMBAT("combat"), // Paper
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 所有与酿造和药水相关的物品, 包括所有类型的药水、其变体以及酿造原料.
     * <p>原文：
     * All items related to brewing and potions including all types of potions,
     * their variants, and ingredients to brew them.
     */
    BREWING("brewing"); // Paper
    // Paper start
    private final String translationKey;

    CreativeCategory(String translationKey) {
        this.translationKey = "itemGroup." + translationKey;
    }

    @Override
    public @org.jetbrains.annotations.NotNull String translationKey() {
        return this.translationKey;
    }
    // Paper end

}
