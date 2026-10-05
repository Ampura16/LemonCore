package com.destroystokyo.paper.loottable;

import org.bukkit.entity.Entity;
import org.jspecify.annotations.NullMarked;

/**
 * <!-- Paper HTML javadoc zh translation -->
 * 表示可以生成战利品的物品栏，例如废弃矿井中的矿车
 * <p>原文：
 * Represents an Inventory that can generate loot, such as Minecarts inside of Mineshafts
 */
@NullMarked
public interface LootableEntityInventory extends LootableInventory {

    /**
     * <!-- Paper HTML javadoc zh translation -->
     * 获取可搜刮的实体
     * <p>原文：
     * Gets the entity that is lootable
     * @return 实体
     * <p>原文：The Entity
     */
    Entity getEntity();
}
