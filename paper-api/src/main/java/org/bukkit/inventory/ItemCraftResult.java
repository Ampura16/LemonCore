package org.bukkit.inventory;

import java.util.List;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 包含合成事件结果的容器类.
 * <br>
 * 此类不对返回值的性质或可变性作任何保证.
 * <p>原文：
 * Container class containing the results of a Crafting event.
 * <br>
 * This class makes no guarantees about the nature or mutability of the returned
 * values.
 */
public interface ItemCraftResult {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 合成产出的 {@link ItemStack}.
     * <p>原文：
     * The resulting {@link ItemStack} that was crafted.
     *
     * @return 合成产出的 {@link ItemStack}
     * <p>原文：{@link ItemStack} that was crafted.
     */
    @NotNull
    public ItemStack getResult();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取合成操作产出的矩阵.
     * <p>原文：
     * Gets the resulting matrix from the crafting operation.
     *
     * @return 产出的矩阵
     * <p>原文：resulting matrix
     */
    public @NotNull ItemStack @NotNull [] getResultingMatrix();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取无法放回合成矩阵的溢出物品.
     * <p>原文：
     * Gets the overflowed items for items that don't fit back into the crafting
     * matrix.
     *
     * @return 溢出物品
     * <p>原文：overflow items
     */
    @NotNull
    public List<ItemStack> getOverflowItems();
}
