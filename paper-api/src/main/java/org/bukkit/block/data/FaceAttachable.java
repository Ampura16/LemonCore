package org.bukkit.block.data;

import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'face' 值代表一个拉杆或按钮固定在哪一个面上.
 * <br>
 * 该值用于与 {@link Directional} 相结合来计算这些方块的方向.
 * <p>原文：
 * 'face' represents the face to which a lever or button is stuck.
 * <br>
 * This is used in conjunction with {@link Directional} to compute the
 * orientation of these blocks.
 */
public interface FaceAttachable extends BlockData {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'face' 属性的值.
     * <p>原文：
     * Gets the value of the 'face' property.
     *
     * @return 属性 'face' 的值
     * <p>原文：the 'face' value
     */
    @NotNull
    AttachedFace getAttachedFace();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'face' 属性的值.
     * <p>原文：
     * Sets the value of the 'face' property.
     *
     * @param face 新的 'face' 属性值
     * <p>原文：the new 'face' value
     */
    void setAttachedFace(@NotNull AttachedFace face);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 一个开关类型的方块可以固定的方块面.
     * <p>原文：
     * The face to which a switch type block is stuck.
     */
    public enum AttachedFace {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 开关固定在地面上且朝上
         * <p>原文：
         * The switch is mounted to the floor and pointing upwards.
         */
        FLOOR,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 开关固定在墙面上
         * <p>原文：
         * The switch is mounted to the wall.
         */
        WALL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 开关固定在天花板上且朝下
         * <p>原文：
         * The switch is mounted to the ceiling and pointing downwards.
         */
        CEILING;
    }
}
