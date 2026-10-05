package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Waterlogged;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'type' 数据值表示这个方块是大型箱子的哪一部分, 亦或是它为一个独立的箱子.
 * <p>原文：
 * 'type' represents which part of a double chest this block is, or if it is a
 * single chest.
 */
public interface Chest extends Directional, Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'type' 数据值.
     * <p>原文：
     * Gets the value of the 'type' property.
     *
     * @return 'type' 数据值
     * <p>原文：the 'type' value
     */
    @NotNull
    Type getType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'type' 数据值.
     * <p>原文：
     * Sets the value of the 'type' property.
     *
     * @param type 'type' 数据值
     * <p>原文：the new 'type' value
     */
    void setType(@NotNull Type type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 箱子方块的类型.
     * <br>
     * 请注意: 左边还是右边是相对于箱子它本身的, 换句话说,
     * 这与玩家当时放置箱子的朝向有关.
     * <p>原文：
     * Type of this chest block.
     * <br>
     * NB: Left and right are relative to the chest itself, i.e opposite to what
     * a player placing the appropriate block would see.
     */
    public enum Type {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 该箱子孤立, 没有与其它任何箱子相连,
         * 且只有 27 个格子用来存放物品.
         * <p>原文：
         * The chest is not linked to any others and contains only one 27 slot
         * inventory.
         */
        SINGLE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 该箱子是大型箱子的左侧, 与右侧的箱子共享 54 个格子.
         * <p>原文：
         * The chest is the left hand side of a double chest and shares a 54
         * block inventory with the chest to its right.
         */
        LEFT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 该箱子是大型箱子的右侧, 与左侧的箱子共享 54 个格子.
         * <p>原文：
         * The chest is the right hand side of a double chest and shares a 54
         * block inventory with the chest to its left.
         */
        RIGHT;
    }
}
