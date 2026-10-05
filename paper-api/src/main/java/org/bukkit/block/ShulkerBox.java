package org.bukkit.block;

import com.destroystokyo.paper.loottable.LootableBlockInventory;
import org.bukkit.DyeColor;
import org.bukkit.loot.Lootable;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表潜影盒方块状态.
 * <p>原文：
 * Represents a captured state of a ShulkerBox.
 */
public interface ShulkerBox extends Container, LootableBlockInventory, Lidded { // Paper

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取该潜影盒对应的{@link DyeColor 颜色}.
     * <p>原文：
     * Get the {@link DyeColor} corresponding to this ShulkerBox
     *
     * @return 潜影盒的{@link DyeColor 颜色}, 如为默认则返回 null
     * <p>原文：the {@link DyeColor} of this ShulkerBox, or null if default
     */
    @Nullable
    public DyeColor getColor();
}
