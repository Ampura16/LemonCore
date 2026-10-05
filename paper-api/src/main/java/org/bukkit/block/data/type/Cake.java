package org.bukkit.block.data.type;

import org.bukkit.block.data.BlockData;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'bites' 数据值表示蛋糕被吃掉了多少片.
 * <br>
 * '0' 值表示蛋糕一口也没吃, 同时 {@link #getMaximumBites()}
 * 的返回值表示蛋糕被吃完了. :(
 * <p>原文：
 * 'bites' represents the amount of bites which have been taken from this slice
 * of cake.
 * <br>
 * A value of 0 indicates that the cake has not been eaten, whilst a value of
 * {@link #getMaximumBites()} indicates that it is all gone :(
 */
public interface Cake extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'bites' 属性值.
     * <p>原文：
     * Gets the value of the 'bites' property.
     *
     * @return 蛋糕被吃掉了多少片
     * <p>原文：the 'bites' value
     */
    int getBites();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'bites' 属性值.
     * <p>原文：
     * Sets the value of the 'bites' property.
     *
     * @param bites 蛋糕被吃掉了多少片
     * <p>原文：the new 'bites' value
     */
    void setBites(int bites);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'bites' 数据值允许的最大值.
     * <p>原文：
     * Gets the maximum allowed value of the 'bites' property.
     *
     * @return 'bites' 的最大值
     * <p>原文：the maximum 'bites' value
     */
    int getMaximumBites();
}
