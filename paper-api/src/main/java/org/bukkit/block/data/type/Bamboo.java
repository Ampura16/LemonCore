package org.bukkit.block.data.type;

import org.bukkit.block.data.Ageable;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'leaves' 值代表该竹子方块上叶子的大小.
 * <p>原文：
 * 'leaves' represents the size of the leaves on this bamboo block.
 */
public interface Bamboo extends Ageable, Sapling {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'leaves' 属性的值.
     * <p>原文：
     * Gets the value of the 'leaves' property.
     *
     * @return 属性 'leaves' 的值
     * <p>原文：the 'leaves' value
     */
    @NotNull
    Leaves getLeaves();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'leaves' 属性的值.
     * <p>原文：
     * Sets the value of the 'leaves' property.
     *
     * @param leaves 新的 'leaves' 属性值
     * <p>原文：the new 'leaves' value
     */
    void setLeaves(@NotNull Leaves leaves);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 竹叶的大小.
     * <p>原文：
     * Bamboo leaf size.
     */
    public enum Leaves {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 没有竹叶
         * <p>原文：
         * No leaves.
         */
        NONE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 小竹叶
         * <p>原文：
         * Small leaves.
         */
        SMALL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 大竹叶
         * <p>原文：
         * Large leaves.
         */
        LARGE;
    }
}
