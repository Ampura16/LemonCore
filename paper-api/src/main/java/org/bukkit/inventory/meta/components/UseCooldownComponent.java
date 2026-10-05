package org.bukkit.inventory.meta.components;

import org.bukkit.NamespacedKey;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.jetbrains.annotations.ApiStatus;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 表示一个决定此物品使用冷却时间的组件。
 * <p>原文：
 * Represents a component which determines the cooldown applied when using this item before it is available for use again.
 */
@ApiStatus.Experimental
public interface UseCooldownComponent extends ConfigurationSerializable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此冷却组中的物品可以再次使用所需的时间（以秒为单位）。
     * <p>原文：
     * Gets the time in seconds it will take for an item in this cooldown group
     * to be available to use again.
     *
     * @return 冷却时间
     * <p>原文：cooldown time
     */
    float getCooldownSeconds();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此冷却组中的物品可以再次使用所需的时间（以秒为单位）。
     * <p>原文：
     * Sets the time in seconds it will take for an item in this cooldown group
     * to be available to use again.
     *
     * @param cooldown 新的冷却时间，必须大于0
     * <p>原文：new cooldown time, must be greater than 0
     */
    void setCooldownSeconds(float cooldown);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取用于相似物品的自定义冷却组（如果已设置）。
     * <p>原文：
     * Gets the custom cooldown group to be used for similar items, if set.
     *
     * @return 冷却组
     * <p>原文：the cooldown group
     */
    @Nullable
    NamespacedKey getCooldownGroup();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置用于相似物品的自定义冷却组。
     * <p>原文：
     * Sets the custom cooldown group to be used for similar items.
     *
     * @param group the cooldown group
     */
    void setCooldownGroup(@Nullable NamespacedKey group);
}
