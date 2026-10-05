package org.bukkit.inventory;

import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 物品的稀有度决定了其名称的默认颜色. 此枚举按从最不稀有到最稀有的顺序排列.
 * <p>原文：
 * An item's rarity determines the default color of its name. This enum is
 * ordered from least rare to most rare.
 */
public enum ItemRarity {

    // Start generate - ItemRarity
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 白色物品名称.
     */
    COMMON(NamedTextColor.WHITE),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 黄色物品名称.
     */
    UNCOMMON(NamedTextColor.YELLOW),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 青色物品名称.
     */
    RARE(NamedTextColor.AQUA),
    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 淡紫色物品名称.
     */
    EPIC(NamedTextColor.LIGHT_PURPLE);
    // End generate - ItemRarity

    private final net.kyori.adventure.text.format.NamedTextColor color;

    ItemRarity(final net.kyori.adventure.text.format.NamedTextColor color) {
        this.color = color;
    }

    /**
     * Gets the color formatting associated with this rarity.
     *
     * @return the color
     */
    public @org.jetbrains.annotations.NotNull TextColor color() {
        return this.color;
    }
}
