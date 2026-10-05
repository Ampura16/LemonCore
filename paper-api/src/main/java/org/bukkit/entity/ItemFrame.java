package org.bukkit.entity;

import org.bukkit.Rotation;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 代表物品展示框.
 * <p>原文：
 * Represents an Item Frame
 */
public interface ItemFrame extends Hanging {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在展示框内的物品.
     * <p>原文：
     * Get the item in this frame
     *
     * @return 展示框内的物品
     * <p>原文：a defensive copy the item in this item frame
     */
    @NotNull
    public ItemStack getItem();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置在展示框内的物品.
     * <p>原文：
     * Set the item in this frame
     *
     * @param item 新的物品
     * <p>原文：the new item
     */
    public void setItem(@Nullable ItemStack item);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置在此物品展示框中的物品.
     * <p>原文：
     * Set the item in this frame
     *
     * @param item 新的物品
     * <p>原文：the new item
     * @param playSound 是否播放物品放置的声音
     * <p>原文：whether or not to play the item placement sound
     */
    public void setItem(@Nullable ItemStack item, boolean playSound);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取在此物品展示框被摧毁时物品掉落的几率.
     *
     * <ul>
     * <li>0.0F的几率将永远不会掉落
     * <li>1.0F的几率将总是会掉落
     * </ul>
     * <p>原文：
     * Gets the chance of the item being dropped upon this frame's destruction.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of 1.0F will always drop
     * </ul>
     *
     * @return 物品掉落的几率
     * <p>原文：chance of the off hand item being dropped
     */
    float getItemDropChance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置在此物品展示框被摧毁时物品掉落的几率.
     *
     * <ul>
     * <li>0.0F的几率将永远不会掉落
     * <li>1.0F的几率将总是会掉落
     * </ul>
     * <p>原文：
     * Sets the chance of the off hand item being dropped upon this frame's
     * destruction.
     *
     * <ul>
     * <li>A drop chance of 0.0F will never drop
     * <li>A drop chance of 1.0F will always drop
     * </ul>
     *
     * @param chance 物品掉落的几率
     * <p>原文：the chance of off hand item being dropped
     */
    void setItemDropChance(float chance);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取展示框内物品的旋转角度.
     * <p>原文：
     * Get the rotation of the frame's item
     *
     * @return 旋转角度
     * <p>原文：the direction
     */
    @NotNull
    public Rotation getRotation();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置展示框内物品的旋转角度.
     * <p>原文：
     * Set the rotation of the frame's item
     *
     * @param rotation 新的旋转角度
     * <p>原文：the new rotation
     * @throws IllegalArgumentException 如果旋转角度为null
     * <p>原文：if rotation is null
     */
    public void setRotation(@NotNull Rotation rotation) throws IllegalArgumentException;

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回物品展示框是否可见.
     * <p>原文：
     * Returns whether the item frame is visible or not.
     *
     * @return 物品展示框是否可见
     * <p>原文：whether the item frame is visible or not
     */
    boolean isVisible();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品展示框是否可见.
     * <p>原文：
     * Sets whether the item frame should be visible or not.
     *
     * @param visible 物品展示框是否可见
     * <p>原文：whether the item frame is visible or not
     */
    void setVisible(boolean visible);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 返回物品展示框是否是"固定"的.
     *
     * 如果为true, 则无法破坏/移动物品展示框(例如, 由于破坏、交互、活塞或缺少支撑方块),
     * 旋转物品或放置/移除物品.
     * <p>原文：
     * Returns whether the item frame is "fixed" or not.
     *
     * When true it's not possible to destroy/move the frame (e.g. by damage,
     * interaction, pistons, or missing supporting blocks), rotate the item or
     * place/remove items.
     *
     * @return 物品展示框是否固定
     * <p>原文：whether the item frame is fixed or not
     */
    boolean isFixed();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置物品展示框是否应固定.
     *
     * 当设置为true时, 不可能破坏/移动物品展示框(例如, 由于破坏、交互、活塞或缺少支撑方块),
     * 旋转物品或放置/移除物品.
     * <p>原文：
     * Sets whether the item frame should be fixed or not.
     *
     * When set to true it's not possible to destroy/move the frame (e.g. by
     * damage, interaction, pistons, or missing supporting blocks), rotate the
     * item or place/remove items.
     *
     * @param fixed 物品展示框是否固定
     * <p>原文：whether the item frame is fixed or not
     */
    void setFixed(boolean fixed);
}
