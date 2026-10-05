package org.bukkit.inventory.meta.components;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.ApiStatus;

/**
 * Represents a component which can turn any item into food.
 */
@ApiStatus.Experimental
public interface FoodComponent extends ConfigurationSerializable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取食用此物品时恢复的食物值。
     * <p>原文：
     * Gets the food restored by this item when eaten.
     *
     * @return 营养值
     * <p>原文：nutrition value
     */
    int getNutrition();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置食用此物品时恢复的食物值。
     * <p>原文：
     * Sets the food restored by this item when eaten.
     *
     * @param nutrition 新的营养值，必须为非负数
     * <p>原文：new nutrition value, must be non-negative
     */
    void setNutrition(int nutrition);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取食用此物品时恢复的饱和度。
     * <p>原文：
     * Gets the saturation restored by this item when eaten.
     *
     * @return 饱和度值
     * <p>原文：saturation value
     */
    float getSaturation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置食用此物品时恢复的饱和度。
     * <p>原文：
     * Sets the saturation restored by this item when eaten.
     *
     * @param saturation 新的饱和度值
     * <p>原文：new saturation value
     */
    void setSaturation(float saturation);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此物品是否可以在不饥饿时食用。
     * <p>原文：
     * Gets if this item can be eaten even when not hungry.
     *
     * @return 如果总是可食用则返回true
     * <p>原文：true if always edible
     */
    boolean canAlwaysEat();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此物品是否可以在不饥饿时食用。
     * <p>原文：
     * Sets if this item can be eaten even when not hungry.
     *
     * @param canAlwaysEat 是否总是可食用
     * <p>原文：whether always edible
     */
    void setCanAlwaysEat(boolean canAlwaysEat);
}
