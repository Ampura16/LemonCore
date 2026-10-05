package org.bukkit.entity;

import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表物品展示实体。
 * <p>原文：
 * Represents an item display entity.
 */
public interface ItemDisplay extends Display {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取显示的物品堆。
     * <p>原文：
     * Gets the displayed item stack.
     *
     * @return the displayed item stack
     */
    @NotNull // Paper
    ItemStack getItemStack();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置显示的物品堆。
     * <p>原文：
     * Sets the displayed item stack.
     *
     * @param item the new item stack
     */
    void setItemStack(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此实体的物品显示变换。
     * <p>原文：
     * Gets the item display transform for this entity.
     *
     * Defaults to {@link ItemDisplayTransform#FIXED}.
     *
     * @return item display transform
     */
    @NotNull
    ItemDisplayTransform getItemDisplayTransform();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此实体的物品显示变换。
     * <p>原文：
     * Sets the item display transform for this entity.
     *
     * Defaults to {@link ItemDisplayTransform#FIXED}.
     *
     * @param display new display
     */
    void setItemDisplayTransform(@NotNull ItemDisplayTransform display);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 代表要应用于显示物品的物品模型变换。
     * <p>原文：
     * Represents the item model transform to be applied to the displayed item.
     */
    public enum ItemDisplayTransform {

        NONE,
        THIRDPERSON_LEFTHAND,
        THIRDPERSON_RIGHTHAND,
        FIRSTPERSON_LEFTHAND,
        FIRSTPERSON_RIGHTHAND,
        HEAD,
        GUI,
        GROUND,
        FIXED;
    }
}
