package com.destroystokyo.paper.loottable;

import org.bukkit.block.Block;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示可以生成战利品的物品栏，例如要塞和废弃矿井中的箱子
 * <p>原文：
 * Represents an Inventory that can generate loot, such as Chests inside of Fortresses and Mineshafts
 */
@NullMarked
public interface LootableBlockInventory extends LootableInventory {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取可搜刮的方块
     * <p>原文：
     * Gets the block that is lootable
     * @return 方块
     * <p>原文：The Block
     */
    Block getBlock();
}
