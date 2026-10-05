package org.bukkit.inventory.meta;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表有耐久度、可损耗的物品.
 * <p>原文：
 * Represents an item that has durability and can take damage.
 */
public interface Damageable extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测此物品是否有耐久度.
     * <p>原文：
     * Checks to see if this item has damage greater than 0.
     *
     * @return 此物品是否有耐久度
     * <p>原文：true if this has damage > 0
     */
    boolean hasDamage();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取物品的耐久度.
     * <p>原文：
     * Gets the damage
     * <p>
     * Call {@link #hasDamageValue()} to be sure
     * a damage value is set.
     *
     * @return 耐久度
     * <p>原文：the damage
     */
    int getDamage();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品的耐久度.
     * <p>原文：
     * Sets the damage
     *
     * @param damage 物品耐久度
     * <p>原文：item damage
     * @see #resetDamage() to reset and clear the damage data component
     */
    void setDamage(int damage);

    // Paper start
    /**
     * Checks if any damage value, including 0,
     * is set on this meta.
     *
     * @return true if any value is set
     */
    boolean hasDamageValue();

    /**
     * Clears the damage component from the meta. Differs
     * from {@code setDamage(0)} in that it removes the component
     * instead of adding the component with a value of 0.
     */
    void resetDamage();
    // Paper end

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检查此物品是否有最大耐久度设置.
     * <p>原文：
     * Checks to see if this item has a maximum amount of damage.
     *
     * @return 如果设置了最大耐久度则返回 true
     * <p>原文：true if this has maximum amount of damage
     */
    boolean hasMaxDamage();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取最大耐久度.
     * <p>
     * 插件在调用此方法前应检查 {@link #hasMaxDamage()} 是否返回 true.
     * <p>原文：
     * Gets the maximum amount of damage.
     *
     * Plugins should check {@link #hasMaxDamage()} before calling this method.
     *
     * @return 最大耐久度
     * <p>原文：the maximum amount of damage
     */
    int getMaxDamage();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置最大耐久度.
     * <p>原文：
     * Sets the maximum amount of damage.
     *
     * @param maxDamage 最大耐久度
     * <p>原文：maximum amount of damage
     */
    void setMaxDamage(@Nullable Integer maxDamage);

    @NotNull
    @Override
    Damageable clone();
}
