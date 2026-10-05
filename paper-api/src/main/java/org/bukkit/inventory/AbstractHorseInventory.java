package org.bukkit.inventory;

import org.bukkit.entity.AbstractHorse;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 马(抽象概念的)({@link AbstractHorse})的物品栏界面接口.
 * <p>原文：
 * An interface to the inventory of an {@link AbstractHorse}.
 */
public interface AbstractHorseInventory extends SaddledMountInventory {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取马所装备的马鞍.
     *
     * @return 马鞍
     * @deprecated 不能给羊驼装配鞍
     */
    @Override
    @Nullable ItemStack getSaddle();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 为这匹马装备一个马鞍.
     *
     * @param stack 马鞍
     * @deprecated 不能给羊驼装配鞍
     */
    @Override
    void setSaddle(@Nullable ItemStack stack);
}
