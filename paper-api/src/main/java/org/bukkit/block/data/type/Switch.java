package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.FaceAttachable;
import org.bukkit.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

public interface Switch extends Directional, FaceAttachable, Powerable {

    @NotNull
    AttachedFace getAttachedFace();

    void setAttachedFace(@NotNull AttachedFace face);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'face' 属性的值.
     * <p>原文：
     * Gets the value of the 'face' property.
     *
     * @return 属性 'face' 的值
     * <p>原文：the 'face' value
     * @deprecated 请使用 {@link #getAttachedFace()}
     * <p>原文：use {@link #getAttachedFace()}
     */
    @NotNull
    @Deprecated(since = "1.15.2")
    default Face getFace() {
        return Face.valueOf(this.getAttachedFace().name());
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'face' 属性的值.
     * <p>原文：
     * Sets the value of the 'face' property.
     *
     * @param face 新的 'face' 属性值
     * <p>原文：the new 'face' value
     * @deprecated 请使用 {@link #getAttachedFace()}
     * <p>原文：use {@link #setAttachedFace(AttachedFace)}
     */
    @Deprecated(since = "1.15.2")
    default void setFace(@NotNull Face face) {
        this.setAttachedFace(AttachedFace.valueOf(face.name()));
    }

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 固定拉杆的的方块面.
     * <p>原文：
     * The face to which a switch type block is stuck.
     *
     * @deprecated 请使用 {@link AttachedFace}
     * <p>原文：use {@link AttachedFace}
     */
    @Deprecated(since = "1.15.2")
    public enum Face {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 拉杆安装在地板上且朝上
         * <p>原文：
         * The switch is mounted to the floor and pointing upwards.
         */
        FLOOR,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 拉杆安装在墙上
         * <p>原文：
         * The switch is mounted to the wall.
         */
        WALL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 拉杆安装在天花板上且朝下
         * <p>原文：
         * The switch is mounted to the ceiling and pointing downwards.
         */
        CEILING;
    }
}
