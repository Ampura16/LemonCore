package org.bukkit.inventory;

import org.bukkit.entity.Llama;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * {@linkplain Llama 羊驼}的用户界面接口
 * <p>原文：
 * An interface to the inventory of a {@link Llama}.
 */
public interface LlamaInventory extends SaddledHorseInventory {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到羊驼背包中装饰槽的物品, 通常是毛毯.
     * <p>原文：
     * Gets the item in the llama's decor slot.
     *
     * @return 身上的装饰的ItemStack对象
     * <p>原文：the decor item
     */
    @Nullable
    ItemStack getDecor();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置羊驼背包中装饰槽的物品.
     * <p>原文：
     * Sets the item in the llama's decor slot.
     *
     * @param stack 给定的物品
     * <p>原文：the new item
     */
    void setDecor(@Nullable ItemStack stack);
}
