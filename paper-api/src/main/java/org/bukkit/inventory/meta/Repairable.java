package org.bukkit.inventory.meta;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表可以在铁砧上修复的物品.
 * <p>原文：
 * Represents an item that can be repaired at an anvil.
 */
public interface Repairable extends ItemMeta {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 检测修复此物品是否需要消耗额外经验等级.
     * <p>原文：
     * Checks to see if this has a repair penalty
     *
     * @return 修复此物品是否需要消耗额外经验等级
     * <p>原文：true if this has a repair penalty
     */
    boolean hasRepairCost();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取修复此物品所需的额外经验等级.
     * <p>原文：
     * Gets the repair penalty
     *
     * @return 修复此物品所需的额外经验等级
     * <p>原文：the repair penalty
     */
    int getRepairCost();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置修复此物品所需的额外经验等级.
     * <p>原文：
     * Sets the repair penalty
     *
     * @param cost 修复此物品所需的额外经验等级
     * <p>原文：repair penalty
     */
    void setRepairCost(int cost);

    @SuppressWarnings("javadoc")
    @NotNull
    @Override
    Repairable clone();
}
