package org.bukkit.entity;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表一个有大小的火球。
 * <p>原文：
 * Represents a sized fireball.
 */
public interface SizedFireball extends Fireball {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取显示用的 {@link ItemStack}。
     * <p>原文：
     * Gets the display {@link ItemStack}.
     *
     * @return 显示用的ItemStack
     * <p>原文：display ItemStack
     */
    @NotNull
    ItemStack getDisplayItem();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置火球显示用的 {@link ItemStack}。
     * <p>原文：
     * Sets the display {@link ItemStack} for the fireball.
     *
     * @param item 要显示的ItemStack
     * <p>原文：the ItemStack to display
     */
    void setDisplayItem(@NotNull ItemStack item);
}
