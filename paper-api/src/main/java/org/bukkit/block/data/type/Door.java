package org.bukkit.block.data.type;

import org.bukkit.block.data.Bisected;
import org.bukkit.block.data.Directional;
import org.bukkit.block.data.Openable;
import org.bukkit.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

/**
 * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
 * 'hinge' 数据值表示门的门轴依靠哪一边, 并且开关门时会旋转.
 * <p>原文：
 * 'hinge' indicates which hinge this door is attached to and will rotate around
 * when opened.
 */
public interface Door extends Bisected, Directional, Openable, Powerable {

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 获取 'hinge' 数据值.
     * <p>原文：
     * Gets the value of the 'hinge' property.
     *
     * @return 门轴依靠哪一侧
     * <p>原文：the 'hinge' value
     */
    @NotNull
    Hinge getHinge();

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 设置 'hinge' 数据值.
     * <p>原文：
     * Sets the value of the 'hinge' property.
     *
     * @param hinge 门轴依靠哪一侧
     * <p>原文：the new 'hinge' value
     */
    void setHinge(@NotNull Hinge hinge);

    /**
     * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
     * 门轴.
     * <p>原文：
     * The hinge of a door.
     */
    public enum Hinge {
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 门 (轴) 依靠在左侧.
         * <p>原文：
         * Door is attached to the left side.
         */
        LEFT,
        /**
         * <!-- Chinese_BukkitAPI copied translation; CC BY-NC 4.0 -->
         * 门 (轴) 依靠在右侧.
         * <p>原文：
         * Door is attached to the right side.
         */
        RIGHT;
    }
}
