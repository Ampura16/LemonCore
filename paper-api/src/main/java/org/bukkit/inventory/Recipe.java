package org.bukkit.inventory;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表某种类型的制作配方.
 * <br>
 * <p>原文：
 * Represents some type of crafting recipe.
 */
public interface Recipe {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 得到这个配方的结果.
     * <p>原文：
     * Get the result of this recipe.
     *
     * @return The result stack
     */
    @NotNull
    ItemStack getResult();
}
