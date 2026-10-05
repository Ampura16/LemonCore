package org.bukkit.block.data.type;

import org.bukkit.block.data.Waterlogged;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'type' 值代表台阶 (/半砖) 的状态 - 上台阶, 下台阶, 或占据了整个方块的双层台阶.
 * <p>原文：
 * 'type' represents what state the slab is in - either top, bottom, or a double
 * slab occupying the full block.
 */
public interface Slab extends Waterlogged {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'type' 属性的值.
     * <p>原文：
     * Gets the value of the 'type' property.
     *
     * @return 属性 'type' 的值
     * <p>原文：the 'type' value
     */
    @NotNull
    Type getType();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'type' 属性的值.
     * <p>原文：
     * Sets the value of the 'type' property.
     *
     * @param type 新的 'type' 属性值
     * <p>原文：the new 'type' value
     */
    void setType(@NotNull Type type);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 台阶的类型.
     * <p>原文：
     * The type of the slab.
     */
    public enum Type {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 台阶占据方块的上半部分
         * <p>原文：
         * The slab occupies the upper y half of the block.
         */
        TOP,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 台阶占据方块的下半部分
         * <p>原文：
         * The slab occupies the lower y half of the block.
         */
        BOTTOM,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * (双层) 台阶占据了整个方块
         * <p>原文：
         * The slab occupies the entire block.
         */
        DOUBLE;
    }
}
