package org.bukkit.block;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表合成器的捕获状态.
 * <p>原文：
 * Represents a captured state of a crafter.
 */
public interface Crafter extends Container, com.destroystokyo.paper.loottable.LootableBlockInventory { // Paper - LootTable API

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此方块将保持在合成状态的剩余刻数.
     * <p>原文：
     * Gets the number of ticks which this block will remain in the crafting
     * state for.
     *
     * @return 剩余刻数
     * <p>原文：number of ticks remaining
     * @see org.bukkit.block.data.type.Crafter#isCrafting()
     */
    int getCraftingTicks();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此方块将保持在合成状态的剩余刻数.
     * <p>原文：
     * Sets the number of ticks which this block will remain in the crafting
     * state for.
     *
     * @param ticks 剩余刻数
     * <p>原文：number of ticks remaining
     * @see org.bukkit.block.data.type.Crafter#isCrafting()
     */
    void setCraftingTicks(int ticks);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取指定索引处的槽位是否被禁用, 不会将物品放入其中.
     * <p>原文：
     * Gets whether the slot at the specified index is disabled and will not
     * have items placed in it.
     *
     * @param slot 槽位索引
     * <p>原文：slot index
     * @return 禁用状态
     * <p>原文：whether the slot is disabled
     */
    boolean isSlotDisabled(int slot);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置指定索引处的槽位是否被禁用, 不会将物品放入其中.
     * <p>原文：
     * Sets whether the slot at the specified index is disabled and will not
     * have items placed in it.
     *
     * @param slot 槽位索引
     * <p>原文：slot index
     * @param disabled 禁用状态
     * <p>原文：whether the slot should be disabled
     */
    void setSlotDisabled(int slot, boolean disabled);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取此合成器是否被激活.
     * <p>原文：
     * Gets whether this Crafter is powered.
     *
     * @return 激活状态
     * <p>原文：powered status
     */
    boolean isTriggered();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置此合成器是否被激活.
     * <p>原文：
     * Sets whether this Crafter is powered.
     *
     * @param triggered 激活状态
     * <p>原文：powered status
     */
    void setTriggered(boolean triggered);
}
