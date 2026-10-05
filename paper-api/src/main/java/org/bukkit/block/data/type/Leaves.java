package org.bukkit.block.data.type;

import org.bukkit.block.data.Waterlogged;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'persistent' 值决定树叶是否会被服务器检查以腐烂 (自动消失).
 * <br>
 * 'distance' 值表示该树叶距离一棵树有多远, 用于与 'persistent' 属性结合判断该树叶是否会腐烂.
 * <p>原文：
 * 'persistent' indicates whether or not leaves will be checked by the server to
 * see if they are subject to decay or not.
 * <br>
 * 'distance' denotes how far the block is from a tree and is used in
 * conjunction with 'persistent' flag to determine if the leaves will decay or
 * not.
 */
public interface Leaves extends Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'persistent' 属性的值.
     * <p>原文：
     * Gets the value of the 'persistent' property.
     *
     * @return 属性 'persistent' 的值
     * <p>原文：the persistent value
     */
    boolean isPersistent();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'persistent' 属性的值.
     * <p>原文：
     * Sets the value of the 'persistent' property.
     *
     * @param persistent 新的 'persistent' 属性值
     * <p>原文：the new 'persistent' value
     */
    void setPersistent(boolean persistent);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'distance' 属性的值.
     * <p>原文：
     * Gets the value of the 'distance' property.
     *
     * @return 属性 'distance' 的值
     * <p>原文：the 'distance' value
     */
    int getDistance();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'distance' 属性的值.
     * <p>原文：
     * Sets the value of the 'distance' property.
     *
     * @param distance 新的 'distance' 属性值
     * <p>原文：the new 'distance' value
     */
    void setDistance(int distance);

    // Paper start
    /**
     * Gets the maximum allowed value of the 'distance' property.
     *
     * @return the maximum 'distance' value
     */
    int getMaximumDistance();

    /**
     * Gets the minimum allowed value of the 'distance' property.
     *
     * @return the minimum 'distance' value
     */
    int getMinimumDistance();
    // Paper end
}
