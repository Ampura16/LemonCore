package org.bukkit.block.data.type;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'tilt' 表示叶片的倾斜程度.
 * <p>原文：
 * 'tilt' indicates how far the leaf is tilted.
 */
public interface BigDripleaf extends Dripleaf {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'tilt' 属性的值.
     * <p>原文：
     * Gets the value of the 'tilt' property.
     *
     * @return 'tilt' 的值
     * <p>原文：the 'tilt' value
     */
    @NotNull
    Tilt getTilt();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'tilt' 属性的值.
     * <p>原文：
     * Sets the value of the 'tilt' property.
     *
     * @param tilt 新的 'tilt' 值
     * <p>原文：the new 'tilt' value
     */
    void setTilt(@NotNull Tilt tilt);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 叶片的倾斜状态.
     * <p>原文：
     * The tilt of a leaf.
     */
    public enum Tilt {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 无倾斜.
         * <p>原文：
         * No tilt.
         */
        NONE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 不稳定倾斜.
         * <p>原文：
         * Unstable tilt.
         */
        UNSTABLE,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 部分倾斜.
         * <p>原文：
         * Partial tilt.
         */
        PARTIAL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 完全倾斜.
         * <p>原文：
         * Pinball.
         */
        FULL;
    }
}
