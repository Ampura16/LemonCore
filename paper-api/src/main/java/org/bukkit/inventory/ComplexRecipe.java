package org.bukkit.inventory;

import org.bukkit.Keyed;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个具有服务端定义行为的复杂配方, 例如盔甲染色.
 * <p>
 * 注意：由于复杂配方具有动态输出, {@link #getResult()}有时会返回一个空物品堆.
 * <p>原文：
 * Represents a complex recipe which has imperative server-defined behavior, eg
 * armor dyeing.
 *
 * Note: Since a complex recipe has dynamic outputs, {@link #getResult()} will
 * sometimes return an AIR ItemStack.
 */
public interface ComplexRecipe extends Recipe, Keyed {}
