package org.bukkit.block.data.type;

import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'attachment' 值表示钟是怎么固定到其他方块上的.
 * <p>原文：
 * 'attachment' denotes how the bell is attached to its block.
 */
public interface Bell extends Directional, Powerable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'attachment' 属性的值.
     * <p>原文：
     * Gets the value of the 'attachment' property.
     *
     * @return 属性 'attachment' 的值
     * <p>原文：the 'attachment' value
     */
    @NotNull
    Attachment getAttachment();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'attachment' 属性的值.
     * <p>原文：
     * Sets the value of the 'attachment' property.
     *
     * @param attachment 新的 'attachment' 属性值
     * <p>原文：the new 'attachment' value
     */
    void setAttachment(@NotNull Attachment attachment);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 钟固定在什么东西上.
     * <p>原文：
     * What the bell is attached to.
     */
    public enum Attachment {

        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 放在地板上
         * <p>原文：
         * Placed on floor.
         */
        FLOOR,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 挂在天花板上
         * <p>原文：
         * Placed on ceiling.
         */
        CEILING,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 固定在一面墙上
         * <p>原文：
         * Placed on one wall.
         */
        SINGLE_WALL,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 架在两面墙之间
         * <p>原文：
         * Placed between two walls.
         */
        DOUBLE_WALL;
    }
}
